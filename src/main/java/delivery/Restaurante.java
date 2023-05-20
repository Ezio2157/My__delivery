package delivery;

import bank.*;
import pcd.util.ColoresConsola;
import pcd.util.Traza;

import java.io.*;
import java.net.*;

public class Restaurante {
	private String nombre;					// nombre del restaurante
	private Account account;				// cuenta bancaria para registrar la recaudación
	private Cocina cocina; 					// la cocina de este restaurante
	private ControlMoteros controlMoteros;  // los moteros de este restaurante
	static InetAddress serverIPAddress;
	static int serverPort;

	
	public Restaurante (Account _ac, String _nombre, int _numeroMoteros) {
		account = _ac;
		nombre = _nombre;
		cocina = new Cocina (this);
		Traza.traza(ColoresConsola.CYAN_BOLD_BRIGHT, 1,"Creando restaurante: "+nombre);
		controlMoteros = new ControlMoteros (this, Config.numeroMoteros);
	}
	
	public String getNombre () {
		return nombre;
	}
	
	public double getBalance (){
		return account.getBalance();
	}
	
	public Account getAccount () {
		return account;
	}

	public String pagarPedido(Pedido p){
		Socket s;
		String result = null;
		DatosPagoPedido DatosPago = new DatosPagoPedido(p.getId(),p.getPrecioPedido());
		try{
			s = new Socket("localhost",9990);
			BufferedReader entrada = new BufferedReader (new InputStreamReader(s.getInputStream()));
			ObjectOutputStream salida = new ObjectOutputStream (s.getOutputStream());
			salida.writeObject(DatosPago);
			salida.flush();
			result = entrada.readLine ();
			s.close();
		}catch (Exception e) {e.printStackTrace();}

		return result;
	}

	private static void enviarObjeto (Object _b, DatagramSocket _socket, InetAddress _serverIPAddress, int _serverPort) {
		byte[] sendData  = new byte[1024];
		ByteArrayOutputStream baos;
		ObjectOutputStream oos;
		DatagramPacket sendPacket;

		try {
			baos = new ByteArrayOutputStream ();
			oos = new ObjectOutputStream (baos);
			oos.writeObject(_b);
			sendData= new byte[baos.toByteArray().length];
			sendData= baos.toByteArray();
			sendPacket = new DatagramPacket(sendData, sendData.length, _serverIPAddress, _serverPort);
			_socket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tramitarPedido (Pedido _p) throws SocketException, UnknownHostException {
		// Tramitar un pedido es:
		if(pagarPedido(_p).equals("OK")){
			account.deposit(_p.getPrecioPedido()); 	// añadir la cantidad abonada a la cuenta del banco
			controlMoteros.moterosLibres();
			cocina.cocinar(_p);						// mandar el pedido a cocina
			controlMoteros.repartirPedido(_p);			// una vez cocinado, mandarlo a los moteros para que uno lo coja
		}
		else{
			DatosPagoPedido DatPagPed = new DatosPagoPedido(_p.getId(), _p.getPrecioPedido());
			DatagramSocket socket =new DatagramSocket();
			String serverName="localhost";
			serverPort=9990;
			serverIPAddress = InetAddress.getByName(serverName);
			enviarObjeto(DatPagPed, socket,serverIPAddress, serverPort);
			socket.close();
		}
	}
}
