package model;

public enum TipoDeAtraccion {
	PAISAJE("PAISAJE"), AVENTURA("AVENTURA"), DEGUSTACION("DEGUSTACION"), DEFAULT("DEFAULT");
	
	String nombre;
	
	TipoDeAtraccion(String nombre) {
		this.nombre=nombre;
	}
	
	public String getNombreDeTipo(){
		return this.nombre;
	}

}
