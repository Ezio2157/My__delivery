package delivery;

import pcd.util.*;

import java.util.concurrent.*;

public class TodosMoteros {
    Semaphore mutex = new Semaphore (1);
    Semaphore moterosListos = new Semaphore (0);
    int cuantos;
    CyclicBarrier cb;

    public TodosMoteros(){
        cuantos = 0;
        cb = new CyclicBarrier(Config.numeroMoteros,new Runnable () {public void run () {}});
    }

    public void TodosListos(){
        if(Config.semaforo){
            try {
                mutex.acquire();
            } catch (InterruptedException e) {e.printStackTrace();}
            cuantos++;
            if(cuantos < Config.numeroMoteros){
                Traza.traza(ColoresConsola.BLUE_BOLD_BRIGHT, 1, "No están todos los moteros listos (" + cuantos + "/" + Config.numeroMoteros + ")");
                try {
                    mutex.release();
                    moterosListos.acquire();
                } catch (InterruptedException e) {e.printStackTrace();}
            }
            else{
                moterosListos.release(Config.numeroMoteros-1);
            }
        }
        else{
            try {
                Traza.traza(ColoresConsola.BLUE_BOLD_BRIGHT, 1, "No están todos los moteros listos (" + cb.getNumberWaiting() + "/" + Config.numeroMoteros + ")");
                cb.await();
            } catch (Exception e) {e.printStackTrace();}
        }
    }
}
