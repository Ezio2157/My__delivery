package delivery;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import grpc.ControlDeVentasGrpc;
import grpcService.ControlVentasServer;
import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import pcd.util.Traza;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class MyDelivery {

	static ManagedChannel channel;
	static ControlDeVentasGrpc.ControlDeVentasBlockingStub StubBlocking;
	static ControlDeVentasGrpc.ControlDeVentasStub StubNonBlocking;
	public MyDelivery () throws InterruptedException {
		// para facilitar las trazas
		Traza.setNivel(delivery.Config.modoTraza);
		
		// Creando los restaurantes
		CadenaRestaurantes cadenaRestaurantes = null;
		cadenaRestaurantes = new CadenaRestaurantes(delivery.Config.numeroRestaurantes);
		cadenaRestaurantes.crearRestaurantes();

		// CARGAR PEDIDOS DE FICHERO
		// Obtenemos una lista de pedidos
		List<delivery.Pedido> lp;
		lp = new LinkedList<>();
		lp = delivery.Pedido.pedidosDesdeFichero ("C:/Users/symar/OneDrive/Desktop/pedidos7.bin"); // Pon aquí la ruta y nombre de tu fichero

		Observable<delivery.Pedido> obsPedidos = delivery.Pedido.pedidosDesdeFicheroObservable ("C:/Users/symar/OneDrive/Desktop/pedidos7.bin");


		
		// También puedes crear tus propios pedidos usando el método generaPedidos de la clase Pedido. 
		// En la clase Pedido también tienes un método para volcar esos pedidos a un fichero.
		
		// LANZAR PEDIDOS
		// Los estamos lanzando secuencialmente
		long initialTime = new Date().getTime();
		LinkedList<delivery.Restaurante> listaRestaurantes = cadenaRestaurantes.getRestaurantes();


		if(delivery.Config.lanzamientoThreads == 0){
			int numCores = Runtime.getRuntime().availableProcessors();
			ThreadPoolExecutor executor;
			executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(numCores);
			for (delivery.Pedido p:lp) {
				delivery.ProcesarPedido Pp = new delivery.ProcesarPedido(p, listaRestaurantes.get(p.getRestaurante()));
				executor.execute(Pp);
			}

			executor.shutdown();
			try{
				executor.awaitTermination(5, TimeUnit.SECONDS);
			}catch (Exception e){}
		}
		else{
			if(delivery.Config.lanzamientoThreads == 1){
				lp.stream().parallel().forEach(p-> {
					try {
						listaRestaurantes.get(p.getRestaurante()).tramitarPedido(p);
					} catch (SocketException e) {
						throw new RuntimeException(e);
					} catch (UnknownHostException e) {
						throw new RuntimeException(e);
					}
				});
			}
			else{
				obsPedidos.flatMap(ped -> Observable.just(ped))
						  .subscribeOn(Schedulers.computation())
						  .doOnNext(ped -> listaRestaurantes.get(ped.getRestaurante()).tramitarPedido(ped))
						  .subscribe();
				sleep(3000);
			}

		}


		//Pedido mas caro usando Executor
		ThreadPoolExecutor pExecutor;
		pExecutor=(ThreadPoolExecutor) Executors.newFixedThreadPool(1);
		delivery.PedidoMasCaro pMasCaro = new delivery.PedidoMasCaro(lp);
		Future <delivery.Pedido> result = pExecutor.submit(pMasCaro);
		try{
			System.out.println("\nEl pedido mas caro (Executor) es de :" + result.get().getPrecioPedido());
		}catch (Exception e){}

		//Pedido mas caro usando streams
		Optional<Double> strP = lp.stream()
				.map(delivery.Pedido::getPrecioPedido)
				.reduce((a,b)->a<b ? b : a);
		System.out.println ("\nEl pedido mas caro (Streams) es de :" + strP.get()+"\n");



		List<delivery.Pedido> listaCheckPedidos = new ArrayList<>();
		delivery.CheckPedidos cp = new delivery.CheckPedidos(lp, listaCheckPedidos);
		ForkJoinPool pool = new ForkJoinPool();
		//Buscando los pedidos de más de 12€
		pool.execute(cp);
		pool.shutdown();
		try{
			pool.awaitTermination(5, TimeUnit.SECONDS);
		}catch (Exception e){}
		for (delivery.Pedido p: listaCheckPedidos){
			System.out.println("Pedido: " + p.printConRetorno());
		}

		//Lista de pedidos mas baratos que 7€
		List<delivery.Pedido> lp7 = lp.stream().filter(p7->p7.getPrecioPedido() < 7)
									  .parallel()
									  .collect(Collectors.toList());
		for(delivery.Pedido P:lp7){
			System.out.println(P.getId() + " " + P.getPrecioPedido());
		}

		final double[] precioTotal = {0.0};

		Observable <delivery.Pedido> obsPed = Observable.fromIterable(lp);
		obsPed.flatMap(p -> Observable.just(p))
				   .subscribeOn(Schedulers.computation())
				   .doOnNext(i -> precioTotal[0] += i.getPrecioPedido())
				   .subscribe();
		sleep(3000);
		System.out.println("\nThread: " + currentThread() + " -> El precio total de los pedidos es: " + precioTotal[0] + "\n");

		obsPed.flatMap(s-> Observable.just(s))
				  .subscribeOn(Schedulers.computation())
				  .filter(s -> (s.getPrecioPedido() > 12))
				  .doOnNext(s -> System.out.println(currentThread() + " " + s.printConRetorno()))
				  .subscribe();
		sleep(3000);

		//Enumerar Productos de cada restaurante con gRPC
		ModulosGRPC.ventas(listaRestaurantes);

		// AUDITORÍAS
		listaRestaurantes.stream().parallel().forEach(r->System.out.print("\nAuditoria Restaurante "+ r.getNombre() + " " + r.getBalance()));

		
		System.out.println ("\nAuditoria Cadena: "+ cadenaRestaurantes.getBank().audit(0, delivery.Config.numeroRestaurantes));
		
		System.out.println ("Tiempo total invertido en la tramitacion: "+(new Date().getTime() - initialTime));

		if (lp.stream().parallel().anyMatch (d->d.getDireccion().equals("Berna, 11")))
			System.out.println ("Encontrado");

	}

	public static void main(String[] args) throws InterruptedException, IOException {
		String target="localhost:9090";

		channel =  Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();
		StubBlocking = ControlDeVentasGrpc.newBlockingStub(channel);
		StubNonBlocking = ControlDeVentasGrpc.newStub(channel);

		new MyDelivery();
	}
}
