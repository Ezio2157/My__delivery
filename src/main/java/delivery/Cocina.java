package delivery;
import grpc.ControlVentas;
import io.grpc.ManagedChannel;
import pcd.util.ColoresConsola;
import pcd.util.Traza;

import java.util.Iterator;

public class Cocina {
	Restaurante r;
	Contenedor ContPan;
	Contenedor ContPollo;
	ContenedorLechuga ContLechuga;
	
	public Cocina (Restaurante _r) {
		r=_r;
		ContPan = new Contenedor(3, 1);
		ContPollo = new Contenedor(1, 2);
		ContLechuga = new ContenedorLechuga(2,3);
	}
	
	public void cocinar (Pedido p) {

		Traza.traza(ColoresConsola.GREEN, 2, "Cocinando el pedido: "+p.printConRetorno());
		try {
			for(int i = 0; i < p.productos.size(); i++){
				if(p.productos.get(i).idProducto.equals("0")){
					ContPan.coger();
					ContPollo.coger();
					ContLechuga.coger();
				}
				ModulosGRPC.registrarVenta(p);
			}
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}