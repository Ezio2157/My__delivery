package delivery;
import java.util.LinkedList;

import main.java.bank.Bank;

public class CadenaRestaurantes   {
	LinkedList <delivery.Restaurante> lista=new LinkedList<>();
	int numRestaurantes;
	static Bank b = new Bank (delivery.Config.numeroRestaurantes,0);	// todos los restaurantes comparten el mismo banco, pero cada uno tiene su cuenta
	
	public CadenaRestaurantes (int _numRestaurantes) {
		numRestaurantes = _numRestaurantes;
	}
	
	public Bank getBank () {
		return b;
	}
	
	public void crearRestaurantes (ControlMoteros cM) {
		for (int i=0;i<numRestaurantes;i++) {	
			delivery.Restaurante r = new delivery.Restaurante(b.getAccount(i),			// su posición en el array de cuentas será su cuenta
											""+i, 										// el nombre del Restaurante
											delivery.Config.numeroMoteros, cM);  			// número de moteros del restaurante. Por defecto todos los restaurantes con el mismo número de moteros
											
			lista.add(r);
		}
	}
	
	public LinkedList<delivery.Restaurante> getRestaurantes () {
		return lista;
	}
}
