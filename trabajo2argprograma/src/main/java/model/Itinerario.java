package model;

import java.util.ArrayList;
import java.util.List;

public class Itinerario  {
	
	protected List<Atraccion> atraccionesEnItinerario = new ArrayList<Atraccion>();
	protected Atraccion unaAtraccion;
	protected Usuario unUsuario;
	
	public Itinerario(Atraccion unaAtraccion, Usuario unUsuario) {
		this.unaAtraccion = unaAtraccion;
		this.unUsuario= unUsuario;

	}



	// --------------------------GETTERS----------------------


	public Usuario getUsuario() {
		return this.unUsuario;
	}
	

	
	public Atraccion getAtraccion() {
		return this.unaAtraccion;
	}

	

}
