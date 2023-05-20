package delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class CheckPedidos extends RecursiveAction {
    List <Pedido> listaPedidos, listaRetorno, lista1, lista2;

    public CheckPedidos(List <Pedido> _listaPedidos, List <Pedido> _listaRetorno){
        listaPedidos = _listaPedidos;
        listaRetorno = _listaRetorno;
    }

    public void compute(){
        if(listaPedidos.size() < 10){
            for(Pedido p: listaPedidos){
                if(p.getPrecioPedido() > 12){
                    listaRetorno.add(p);
                }
            }
        }
        else{
            int mid = listaPedidos.size()/2;
            lista1 = new ArrayList<>();
            lista2 = new ArrayList<>();
            CheckPedidos cp1 = new CheckPedidos(listaPedidos.subList(0,mid), lista1);
            CheckPedidos cp2 = new CheckPedidos(listaPedidos.subList(mid,listaPedidos.size()), lista2);
            invokeAll(cp1, cp2);
            listaRetorno.addAll(lista1);
            listaRetorno.addAll(lista2);
        }
    }
}
