package delivery;

import java.util.ArrayList;
import java.util.concurrent.locks.*;

public class Contenedor {
    int cuantos;
    int MAX;
    int ID;
    ArrayList<Boolean> cola = new ArrayList<>();
    /* Para la exclusión mutua */
    final Lock lock = new ReentrantLock(true);
    /* Variables de condición */
    final Condition lleno = lock.newCondition();
    final Condition vacio = lock.newCondition();

    public Contenedor (int _MAX, int _ID) {
        cuantos = 0;
        MAX = _MAX;
        ID = _ID;
        Thread Bot = new Thread(new Robot(ID, this));
        Bot.start();
    }

    public void poner () throws InterruptedException {
        /* Acceso a la s.c. */
        lock.lock();
        try {
            while (cuantos == MAX){
                lleno.await();
            }

            /* s.c. */
            System.out.print("Elemento Insertado\n");
            cola.add(true);
            cuantos++;

            vacio.signal();
        }
        /* Salida de la s.c. */
        finally  {
            lock.unlock();
        }
    }

    public void coger () throws InterruptedException {
        /* Acceso a la s.c. */
        lock.lock();
        try {
            while (cuantos == 0)
                vacio.await();

            /* s.c. */
            if(ID == 1){
                System.out.print("...Extraigo Pan... \n");
            }
            else{
                System.out.print("...Extraigo Pollo... \n");
            }
            cola.remove(0);
            cuantos--;

            lleno.signal();
        }
        /* Salida de la s.c. */
        finally {
            lock.unlock();
        }
    }
}
