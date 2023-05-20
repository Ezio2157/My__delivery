package delivery;

import pcd.util.*;

import java.util.ArrayList;

public class ControlMoteros {
	int numeroMoteros;
	Restaurante r;
	ArrayList<Pedido> ListaPedidos = new ArrayList<>();

	public ControlMoteros (Restaurante _r, int _numeroMoteros) {
		TodosMoteros todosMoteros = new TodosMoteros();
		r=_r;
		numeroMoteros = _numeroMoteros;
		for(int i=0; i < numeroMoteros; i++){
			Traza.traza(ColoresConsola.YELLOW,1,"Generando Motero " + r.getNombre()  + "." + i);
			Thread m = new Thread(new Motero(r, this, 10+(300*i), i, todosMoteros));
			//Fórmula para evitar colocar todas las ventanas en la misma posición
			m.start();
		}
	}

	public synchronized void moterosLibres(){
		while(numeroMoteros == 0){
			try{
				wait();
			}catch(Exception e){e.printStackTrace();}
		}
		numeroMoteros--;
	}

	public synchronized void repartirPedido(Pedido p){
		ListaPedidos.add(p);
		notifyAll();
	}

	public synchronized Pedido getPedido(){
		while(ListaPedidos.size()==0){
			try{
				wait();
			}catch(Exception e){e.printStackTrace();}
		}
		return ListaPedidos.remove(0);
	}

	public synchronized void regresaMotero(){
		numeroMoteros++;
		notifyAll();
	}

}
