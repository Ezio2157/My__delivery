package delivery;

import pcd.util.ColoresConsola;
import pcd.util.Traza;

public class Robot implements Runnable{
    int ID;
    Contenedor Cont;
    boolean fin = false;

    public Robot(int _ID, Contenedor _Cont){
        ID = _ID;
        Cont = _Cont;
    }

    public void run(){
        while(!fin){
            try {
                Cont.poner();
                if (ID == 1){
                    Traza.traza(ColoresConsola.RED_BOLD_BRIGHT, 2, "Añadiendo Pan al contenedor");
                }
                else{
                    Traza.traza(ColoresConsola.PURPLE_BOLD_BRIGHT, 2, "Añadiendo Pollo al contenedor");
                }
            } catch (Exception e) {}
        }
    }
}
