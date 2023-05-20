package delivery;

import pcd.util.ColoresConsola;
import pcd.util.Traza;

public class RobotLechuga implements Runnable{
    int ID;
    ContenedorLechuga Cont;
    boolean fin = false;

    public RobotLechuga(int _ID, ContenedorLechuga _Cont){
        ID = _ID;
        Cont = _Cont;
    }
    public void run(){
        while(!fin){
            try {
                Cont.poner();
                Traza.traza(ColoresConsola.GREEN_BOLD_BRIGHT, 2, "Añadiendo Lechuga al contenedor");
            } catch (Exception e) {}
        }
    }
}
