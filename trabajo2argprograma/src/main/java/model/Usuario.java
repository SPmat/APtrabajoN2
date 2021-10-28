package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private List<Atraccion> itinerario = new ArrayList<Atraccion>();

	private String nombre;
	private TipoDeAtraccion atraccionFavorita;
	private int presupuesto, monedasIniciales;
	private float tiempoDisponible, tiempoDisponibleOriginal;

	public Usuario(String nombre, TipoDeAtraccion atraccionFavorita, int monedas, float tiempoLibre) {
		this.nombre = nombre;
		this.atraccionFavorita = atraccionFavorita;
		this.presupuesto = monedas;
		this.monedasIniciales = this.presupuesto;
		this.tiempoDisponible = tiempoLibre;
		this.tiempoDisponibleOriginal = this.tiempoDisponible;
	}

	public Usuario() {
		this(" ", TipoDeAtraccion.DEFAULT, 0, 0);
	}

//----------------------------GETTERS---------------------------------------------------
	public int getPresupuesto() {
		return presupuesto;
	}

	public List<Atraccion> getItinerario() {
		return itinerario;
	}

	public float getTiempoDisponible() {
		return tiempoDisponible;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreAtraccionFavorita() {
		return this.atraccionFavorita.getNombreDeTipo();
	}

	public TipoDeAtraccion getAtraccionFavorita() {
		return this.atraccionFavorita;
	}

//-------------------------------METODOS----------------------------
	
	
	public void vaciarItinerario() {
		this.itinerario=new ArrayList<Atraccion>();
	}
	
	public String itinerarioToString() {
		String retorno="";
		for(int i=0; i<itinerario.size() ; i++) {
			retorno+=itinerario.get(i).getNombre();
			retorno+="\n";
		}
		
		return retorno;
	}
	
	
	
	@Override
	public String toString() {
		return this.nombre + ": Gasto " + (this.monedasIniciales - this.presupuesto) + " monedas.\n" + "Pasara "
				+ (this.tiempoDisponibleOriginal - this.tiempoDisponible)
				+ " horas en tierra media.\nSu tipo de atraccion favorito es: " + this.getNombreAtraccionFavorita()
				+ ".\nSu itinerario es: " + this.getItinerario() + ".";
	}
	
	//Datos para validar en junit
	public String userInfo() {
		return this.nombre + " tiene " + this.presupuesto + " monedas, " + (int) (this.tiempoDisponible)
				+ " horas libres y su tipo de atraccion favorito es " + this.getNombreAtraccionFavorita().toLowerCase();
	}
	
	// a usarse cuando el usuario clickea aceptar
	public void agregarAtraccion(Atraccion unaAtraccion) {
		this.itinerario.add(unaAtraccion);
		this.tiempoDisponible -= unaAtraccion.getTiempoDeUso();
	}
	
	

	public void agregarAlItinerario(Atraccion unaAtraccion) {
		this.itinerario.add(unaAtraccion);
	}

	//paga dependiendo si es una promocion o atraccion
	public void pagar(Promocion unaPromocion) {
		this.presupuesto -= unaPromocion.getValorPromo();
	}
	
	public void pagar(Atraccion unaAtraccion) {
		this.presupuesto -= unaAtraccion.getValor();
	}

	// Responde verdadero si tiene las monedas y el tiempo suficiente ---->
	// Verdadero, tengo dinero y tiempo para ir a...
	public boolean podesIrA(Atraccion atraccion) {

		return this.presupuesto >= atraccion.getValor() 
				&& this.tiempoDisponible >= atraccion.getTiempoDeUso() 
				&& todaviaNoVasA(atraccion);								// agrego el m�todo todaviaNoVasA() a este m�todo para
																			// simplificar el la forma de evaluar si el usuario 
																			//puede ir a una atraccion.  
	}
	
	public boolean podesIrA(Promocion promocion) {
		return this.presupuesto>= promocion.getValorPromo() 
				&& this.tiempoDisponible>= promocion.getTiempoDeUso() 
				&& todaviaNoVasA(promocion);
	}
	
	// Si todavia no tiene en el itinerario la atraccion que le pasan como
	// parametro, da true --> Verdad, todavia no voy a .....
	public boolean todaviaNoVasA(Atraccion unaAtraccion) {

		for (Atraccion misAtracciones : itinerario) {
			if (misAtracciones.getNombre().equals(unaAtraccion.getNombre())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean todaviaNoVasA(Promocion promocion) {

		for(Atraccion atraccionPromo : promocion.getAtraccionesEnPromocion()) {
			for (Atraccion misAtracciones : this.itinerario) {
				if(misAtracciones.getNombre().equals(atraccionPromo.getNombre())) {
					return false;
				}
			}
		}
		return true;
	}
//----------------------equals y hashcode------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atraccionFavorita == null) ? 0 : atraccionFavorita.hashCode());
		result = prime * result + ((itinerario == null) ? 0 : itinerario.hashCode());
		result = prime * result + monedasIniciales;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + presupuesto;
		result = prime * result + Float.floatToIntBits(tiempoDisponible);
		result = prime * result + Float.floatToIntBits(tiempoDisponibleOriginal);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (atraccionFavorita != other.atraccionFavorita)
			return false;
		if (itinerario == null) {
			if (other.itinerario != null)
				return false;
		} else if (!itinerario.equals(other.itinerario))
			return false;
		if (monedasIniciales != other.monedasIniciales)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (presupuesto != other.presupuesto)
			return false;
		if (Float.floatToIntBits(tiempoDisponible) != Float.floatToIntBits(other.tiempoDisponible))
			return false;
		if (Float.floatToIntBits(tiempoDisponibleOriginal) != Float.floatToIntBits(other.tiempoDisponibleOriginal))
			return false;
		return true;
	}
	
	
}