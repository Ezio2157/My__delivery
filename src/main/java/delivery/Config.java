package delivery;
public class Config {
	public final static int modoTraza = 2;					// nivel de profundidad de la traza
	public final static int numeroRestaurantes = 3;			// número de restaurantes a crear
	public final static int numeroPedidos = 5;				// número de pedidos por canal a crear
	public final static int numeroMoteros = 4;				// número de moteros por restaurante a crear
	public final static int numeroProductos = 3; 			// límite de cantidad de productos a crear en pedido
	public final static int maximoIdProducto = 5; 			// número máximo de id de producto
	public final static int maximoPrecioProducto = 5;		// precio máximo de cada producto.
	public final static boolean semaforo = true;			//True = Utilización de semaforos
															//False = Utilización del CyclicBarrier
	public final static int lanzamientoThreads = 1; 		// 0 = Utilizacion de executor
															// 1 = Utilizacion de Streams
															// 2 = Utilizacion de Observables
}
