package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.AtraccionDAOImpl;
import dao.ItinerarioDAOImpl;
import dao.PromocionDAOImpl;
import dao.UsuarioDAOImpl;
import model.Atraccion;
import model.Itinerario;
import model.Promocion;
import model.Usuario;

public class NuevoMenuMain {
	

	public static void main(String[] args) throws IOException, SQLException {

		List<Atraccion> todasLasAtracciones = new ArrayList<Atraccion>();
		List<Usuario> todosLosUsuarios = new ArrayList<Usuario>();
		List<Promocion> todasLasPromociones = new ArrayList<Promocion>();
		List<Itinerario> todosLosItinerarios = new ArrayList<Itinerario>();

		//LectorDeArchivos lector = new LectorDeArchivos();

	//	lector.leerUsuarios(todosLosUsuarios);
	//	lector.leerAtracciones(todasLasAtracciones);
	//	lector.leerPromos(todasLasPromociones, todasLasAtracciones);
		
		
		UsuarioDAOImpl usuarios = new UsuarioDAOImpl();
		AtraccionDAOImpl atracciones = new AtraccionDAOImpl();
		PromocionDAOImpl promociones = new PromocionDAOImpl();
		ItinerarioDAOImpl itinerarios = new ItinerarioDAOImpl();
		
		
		todasLasAtracciones= atracciones.findAll();
		todosLosUsuarios= usuarios.findAll();
		todasLasPromociones= promociones.findAll();
		todosLosItinerarios= itinerarios.findAll();
		

		Aplicacion app = new Aplicacion(todasLasAtracciones, todasLasPromociones);

		app.separarEnListas();	
		
		
		app.separarItinerario(todosLosUsuarios, todosLosItinerarios);
		
		

		
		/*
		System.out.println("Las atracciones separadas por tipo son:\n ");	
		
		
		System.out.print("Aventura: ");
		System.out.println(app.atraccionesDeAventura);
		System.out.print("Degustacion: ");
		System.out.println(app.atraccionesDeDegustacion);
		System.out.print("Paisaje : ");
		System.out.println(app.atraccionesDePaisaje);
		
		
		*/
		
		int opcion=-1;
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("BIENVENIDO A TIERRA MEDIA!  \n \n ");	
		
		while (opcion != 5) {


			System.out.println("Seleccione una opcion de la siguiente lista:");	
			System.out.println("1 - Ofrecer atracciones a todos los usuarios");	
			System.out.println("2 - Ofrecer atracciones a un solo usuario");
			System.out.println("3 - Reestablecer la base de datos");
			System.out.println("5 - Cerrar aplicacíon\n");
			
			opcion = entrada.nextInt();
			
			System.out.println("\n\n\n\n\n\n");
			
			
			switch(opcion) {
			
			
			case 1:
				
				for (int i = 0; i < todosLosUsuarios.size(); i++) {

					app.ofrecerTodo(todosLosUsuarios.get(i));

					System.out.println(todosLosUsuarios.get(i).getNombre() + " va a ir a:");
					System.out.println(todosLosUsuarios.get(i).itinerarioToString());
					
					//se reemplaza por la nueva funcion itinerarioToString
					/*List <Atraccion> itinerarioIndividual= new ArrayList<Atraccion>();
					itinerarioIndividual=todosLosUsuarios.get(i).getItinerario();
					
					
					for(Atraccion atraccion : itinerarioIndividual) {
											
						System.out.println(atraccion);
							
					}*/
					
					app.actualizarUsuarios(todosLosUsuarios);
					//app.actualizarAtracciones(todasLasAtracciones);
					//app.actualizarPromociones(todasLasPromociones);
					
					System.out.println(" \n \n FIN DE USUARIO \n \n");

					//para mantener igualadas las listas con la base de datos
					
					//todasLasAtracciones= atracciones.findAll();
					//todosLosUsuarios= usuarios.findAll();
					//todosLosItinerarios= itinerarios.findAll();
					
					
					

				}
				
				
				System.out.println("Se ofrecieron las atracciones disponibles a todos los usuarios.");
				
				break;
				
			case 2:
				
		int opcion2 = -1;
		
				while (opcion2!=0) {
				
				System.out.println("Seleccione un usuario:\n");	
				
				for (int i=0; i< todosLosUsuarios.size(); i++) {
					
					System.out.println(i+1 + " - Para seleccionar a " + todosLosUsuarios.get(i).getNombre());	
					
				}
				
				System.out.println("0 - Para volver");	
				
				
				opcion2 = entrada.nextInt();
				
			if(opcion2 > 0) {
				
				System.out.println("\n\n\n\n\n\n");
				
				app.ofrecerTodo(todosLosUsuarios.get(opcion2-1));

				System.out.println(todosLosUsuarios.get(opcion2-1).getNombre() + " va a ir a:");
				List <Atraccion> itinerarioIndividual= new ArrayList<Atraccion>();
				itinerarioIndividual=todosLosUsuarios.get(opcion2+1).getItinerario();
				
				
				for(Atraccion atraccion : itinerarioIndividual) {
										
					System.out.println(atraccion);
						
				}
				
				app.actualizarUsuarios(todosLosUsuarios);
				app.actualizarAtracciones(todasLasAtracciones);
				todasLasAtracciones= atracciones.findAll();
				todosLosUsuarios= usuarios.findAll();
				todosLosItinerarios= itinerarios.findAll();
				
				System.out.println(" \n \n FIN DE USUARIO \n \n");
				
				
				
				
				
			}
				
				
				}
				
				
				break;
				
			case 3:
				
				//Aca tenemos que llamar a la clase que actualiza la base de datos
				
				
				break;
				
				
			case 4:
				
				
				break;
				
			case 5:
				
				
				break;
				
			default: 
			
				System.out.println("\n\n\n\n\n\n");
				System.out.println("Error, seleccione una opcion valida \n");	
				
			
			}
		


		


	}
		
		
		System.out.println("FIN DE LA APLICACION");	
	
	}
	
	

	
	
}

