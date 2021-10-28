package app;

import dao.ItinerarioDAOImpl;

public class vaciarItinerarioPorConsolaDAO {
	
//TODO fijarse si sirve
	public static void main(String[] args) {

		ItinerarioDAOImpl itinerarios = new ItinerarioDAOImpl();
		
		itinerarios.restaurar();
	}

}
