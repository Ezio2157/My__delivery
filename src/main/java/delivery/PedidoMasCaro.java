package delivery;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class PedidoMasCaro implements Callable<Pedido> {
    List<Pedido> lp;

    public PedidoMasCaro(List<Pedido> _lp){
        lp = _lp;
    }

    public Pedido call(){
        double max = 0;
        Pedido pResult = null;
        for(Pedido p:lp){
            if(p.getPrecioPedido() > max){
                max = p.getPrecioPedido();
                pResult = p;
            }
        }
        return pResult;
    }
}
