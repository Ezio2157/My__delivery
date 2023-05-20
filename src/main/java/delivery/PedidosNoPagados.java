package delivery;

import pcd.util.Ventana;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class PedidosNoPagados {

    public static void main(String args[]){
        DatagramSocket socket;
        DatagramPacket receivePacket;
        byte[] receiveData = new byte[1024];

        int localPort = 9990;
        InetAddress clientIPAddress = null;
        String mensaje="Todo recibido OK";
        Ventana v = new Ventana("PedidosNoPagados",800,500);

        try {
            //Apertura del socket servidor en el puerto localPort
            v.addText ("Abriendo socket en el puerto: "+localPort);
            socket=new DatagramSocket(localPort);

            receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Lectura en bucle de los libros
            delivery.DatosPagoPedido datPedPag =null;
            boolean fin = false;

            while (!fin) {
                socket.receive(receivePacket);
                ObjectInputStream ois = new ObjectInputStream (new ByteArrayInputStream(receivePacket.getData()));
                try {
                    datPedPag = (DatosPagoPedido) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                v.addText("Pedido recibido: " + datPedPag.getId() + " || Precio: " + datPedPag.getImporte());
            }
            // Cerramos socket
            socket.close();

        } catch (SocketException e) {
            System.err.println("Error al abrir el socket : " + e);
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
