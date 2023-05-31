package delivery;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import pcd.util.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static io.reactivex.rxjava3.core.Observable.just;
import static java.lang.Thread.currentThread;

public class ControlMoteros {
	int numeroMoteros;
	//Restaurante r;
	ArrayList<Pedido> ListaPedidos = new ArrayList<>();

	public ControlMoteros ( int _numeroMoteros) {
		TodosMoteros todosMoteros = new TodosMoteros();
		//r=_r;
		numeroMoteros = _numeroMoteros;
		for(int i=0; i < numeroMoteros; i++){
			Traza.traza(ColoresConsola.YELLOW,1,"Generando Motero " + i);
			Thread m = new Thread(new Motero( this, 10+(300*i), i, todosMoteros));
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
