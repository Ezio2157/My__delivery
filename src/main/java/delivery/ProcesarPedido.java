package delivery;

import delivery.Restaurante;

import java.net.SocketException;
import java.net.UnknownHostException;

public class ProcesarPedido implements Runnable{
    Pedido P;
    Restaurante R;

    public ProcesarPedido(Pedido _P, Restaurante _R){
         P = _P;
         R = _R;
    }

    public void run(){
        try {
            R.tramitarPedido(P);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
