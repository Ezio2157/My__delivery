package delivery;
public class Config {
	public final static int modoTraza = 2;					// nivel de profundidad de la traza
	public final static int numeroRestaurantes = 3;			// n�mero de restaurantes a crear
	public final static int numeroPedidos = 5;				// n�mero de pedidos por canal a crear
	public final static int numeroMoteros = 4;				// n�mero de moteros por restaurante a crear
	public final static int numeroProductos = 3; 			// l�mite de cantidad de productos a crear en pedido
	public final static int maximoIdProducto = 5; 			// n�mero m�ximo de id de producto
	public final static int maximoPrecioProducto = 5;		// precio m�ximo de cada producto.
	public final static boolean semaforo = true;			//True = Utilizaci�n de semaforos
															//False = Utilizaci�n del CyclicBarrier
	public final static int lanzamientoThreads = 1; 		// 0 = Utilizacion de executor
															// 1 = Utilizacion de Streams
															// 2 = Utilizacion de Observables
}
