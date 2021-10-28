package model;

public class Itinerario  {
	
	//clase cuya unica funcion es traducir los datos de la base de datos
	
	
	
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
