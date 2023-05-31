package delivery;

import delivery.Restaurante;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

public class ProcesarPedido implements Runnable{
    Pedido P;
    Restaurante R;
    static List<Pedido> ListaPedidosRepartidos;

    public ProcesarPedido(Pedido _P, Restaurante _R, List<Pedido> _ListaPedidosRepartidos){
         P = _P;
         R = _R;
        ListaPedidosRepartidos = _ListaPedidosRepartidos;
    }

    public void run(){
        try {
            R.tramitarPedido(P, ListaPedidosRepartidos);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
