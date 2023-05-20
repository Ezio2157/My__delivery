package delivery;

import pcd.util.Ventana;

public class Motero implements Runnable{
    Restaurante r;
    Ventana V;
    ControlMoteros controlMoteros;
    TodosMoteros todosMoteros;
    boolean fin = false;

    public Motero(Restaurante _r, ControlMoteros _controlMoteros, int posicionVentana, int IdMotero, TodosMoteros _todosMoteros){
        r = _r;
        controlMoteros = _controlMoteros;
        todosMoteros = _todosMoteros;
        V = new Ventana ("Motero "+r.getNombre() + "." + IdMotero, posicionVentana,10);
    }

    public void run(){
        todosMoteros.TodosListos();
        while(!fin){
            V.addText("Consulta de pedidos disponibles");
            Pedido P = controlMoteros.getPedido();
            V.addText("Enviando Pedido : " + P.getId());
            try {
                Thread.sleep(100); // Simulamos que tarda 0,1 segundos en repartir
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            V.addText("Regresando a recoger otro pedido");
            controlMoteros.regresaMotero();
        }
    }
}
