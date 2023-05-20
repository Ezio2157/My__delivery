package delivery;

import grpc.ControlVentas;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

import static delivery.MyDelivery.StubNonBlocking;

public class ModulosGRPC {

    static final CountDownLatch Latch = new CountDownLatch(1);
    static DecimalFormat df = new DecimalFormat("#.##");

    static void registrarVenta(Pedido p) {

        //Preparación de la respuesta hacia el servidor como parámetro de entrada para responder al cliente posteriormente
        StreamObserver<ControlVentas.BeneficioGRPC> StreamBeneficio = new StreamObserver<ControlVentas.BeneficioGRPC>() {
            @Override
            public void onNext(ControlVentas.BeneficioGRPC Beneficio) {
                System.out.println("Para el pedido '" + p.getId() + "' el beneficio es de: " + df.format(Beneficio.getTotal()));
            }

            @Override
            public void onCompleted() {
                Latch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                System.out.print("Streaming Failed: {0}" + Status.fromThrowable(t) + "\n");
                Latch.countDown();
            }
        };


        StreamObserver<ControlVentas.VentaGRPC> StreamVenta = StubNonBlocking.registrarVenta(StreamBeneficio);

        try {
            for (Producto proc : p.productos) {
                ControlVentas.VentaGRPC venta = ControlVentas.VentaGRPC.newBuilder()
                        .setIdProducto(proc.getId())
                        .setPrecio(proc.getPrecio())
                        .setCantidad(proc.getCantidad())
                        .build();
                StreamVenta.onNext(venta);
            }


            StreamVenta.onCompleted();

        } catch (RuntimeException e) {
            StreamVenta.onError(e);
            throw e;
        }
    }

    static void ventas(LinkedList<Restaurante> listaRestaurantes) {

        //Preparación de la respuesta hacia el servidor como parámetro de entrada para responder al cliente posteriormente
        StreamObserver<ControlVentas.ProductoGRPC> StreamProducto = new StreamObserver<ControlVentas.ProductoGRPC>() {
            @Override
            public void onNext(ControlVentas.ProductoGRPC producto) {
                // Leer y mostrar la respuesta del servidor.
                System.out.println("      ID: " + producto.getIdProducto());
                System.out.println("      Cantidad: " + producto.getCantidad());
            }

            @Override
            public void onCompleted() {
                Latch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                System.out.print("Streaming Failed: {0}" + Status.fromThrowable(t) + "\n");
                Latch.countDown();
            }
        };

        StreamObserver<ControlVentas.RestauranteGRPC> StreamRestaurante = StubNonBlocking.ventas(StreamProducto);

        try {

            for (Restaurante r : listaRestaurantes) {
                ControlVentas.RestauranteGRPC restaurante = ControlVentas.RestauranteGRPC.newBuilder()
                        .setIdRestaurante(r.getNombre())
                        .build();
                System.out.println("Los productos del Restaurante " + r.getNombre() + " son:");
                StreamRestaurante.onNext(restaurante);
                //Retardar los mensajes para que lleguen en orden
                Thread.sleep(1000);
            }

            StreamRestaurante.onCompleted();

        } catch (RuntimeException e) {
            StreamRestaurante.onError(e);
            throw e;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
