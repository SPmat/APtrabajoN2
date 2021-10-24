package model;

public enum TipoDePromo {
	PORCENTUAL("PORCENTUAL"), ABSOLUTA("ABSOLUTA"), AxB("AxB"), DEFAULT("DEFAULT");
	
	String nombre;
	
	TipoDePromo(String nombre){
		this.nombre=nombre;
	}
	
	public String getNombreDeTipo(TipoDePromo tipo) {
		return tipo.nombre;
	}
}
