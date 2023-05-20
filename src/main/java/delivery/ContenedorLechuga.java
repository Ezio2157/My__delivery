package delivery;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ContenedorLechuga {
    int cuantos;
    int MAX;
    int ID;
    BlockingQueue<Object> q = new LinkedBlockingQueue<Object>(2);

    public ContenedorLechuga (int _MAX, int _ID) {
        cuantos = 0;
        MAX = _MAX;
        ID = _ID;
        Thread Bot = new Thread(new RobotLechuga(ID, this));
        Bot.start();
    }

    public void poner () throws InterruptedException {
        q.put(new Object());
    }

    public void coger () throws InterruptedException {
        q.take();
        System.out.println("...Extraigo Lechuga...");
    }
}
