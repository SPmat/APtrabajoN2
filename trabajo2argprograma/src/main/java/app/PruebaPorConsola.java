package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Importador.LectorDeArchivos;
import model.*;

public class PruebaPorConsola {

	public static void main(String[] args) throws IOException {

		List<Atraccion> todasLasAtracciones = new ArrayList<Atraccion>();
		List<Usuario> todosLosUsuarios = new ArrayList<Usuario>();
		List<Promocion> todasLasPromociones = new ArrayList<Promocion>();

		//LectorDeArchivos lector = new LectorDeArchivos();

	//	lector.leerUsuarios(todosLosUsuarios);
	//	lector.leerAtracciones(todasLasAtracciones);
	//	lector.leerPromos(todasLasPromociones, todasLasAtracciones);

		Aplicacion app = new Aplicacion(todasLasAtracciones, todasLasPromociones);

		app.separarEnListas();
		
		
		System.out.println("Las atracciones separadas por tipo son:\n ");	
		
		
		System.out.print("Aventura: ");
		System.out.println(app.atraccionesDeAventura);
		System.out.print("Degustacion: ");
		System.out.println(app.atraccionesDeDegustacion);
		System.out.print("Paisaje : ");
		System.out.println(app.atraccionesDePaisaje);
		
		
		System.out.println("\n Iniciamos la oferta de atracciones:  \n \n ");	

		for (int i = 0; i < todosLosUsuarios.size(); i++) {

			app.ofrecerTodo(todosLosUsuarios.get(i));

			System.out.println(todosLosUsuarios.get(i).getNombre() + " va a ir a:");
			List <Atraccion> itinerarioIndividual= new ArrayList<Atraccion>();
			itinerarioIndividual=todosLosUsuarios.get(i).getItinerario();
			for(Atraccion atraccion : itinerarioIndividual) {
				System.out.println(atraccion);
			}
			
			System.out.println(" \n \n FIN DE USUARIO \n \n");
			
			

		}
		System.out.println("FIN DE LA APP. Gracias por utilizar Tierra Media APP");
	}
}
