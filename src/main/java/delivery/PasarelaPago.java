package delivery;

import pcd.util.Ventana;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Semaphore;

public class PasarelaPago implements Runnable{

    Socket s = null;
    Ventana v = null;
    static Semaphore mutex = new Semaphore (1);

    public PasarelaPago (Socket _s, Ventana _v) {
        this.s = _s;
        this.v =  _v;

    }

    public void run() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(s.getInputStream());
            BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            delivery.DatosPagoPedido DatosPagoS = (delivery.DatosPagoPedido) entrada.readObject();
            if (DatosPagoS.getImporte() < 12) {
                salida.write("KO");
                mutex.acquire();
                v.addText("KO " + DatosPagoS.getId());
                mutex.release();
            } else {
                salida.write("OK");
                mutex.acquire();
                v.addText("OK " + DatosPagoS.getId());
                mutex.release();
            }
            salida.flush();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException {
        Ventana v =  new Ventana ("Server",500,500);
        ServerSocket ss=null;
        try {
            ss = new ServerSocket (9990);
            ss.setSoTimeout(8000); // Establecemos un timeout
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
        Socket s2 = null;

        while (true) {
            try {
                s2 = ss.accept();
                new Thread (new PasarelaPago (s2, v)).start();

            }
            catch (SocketTimeoutException e1) {
                break;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {s2.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println ("fin Server");
    }
}
