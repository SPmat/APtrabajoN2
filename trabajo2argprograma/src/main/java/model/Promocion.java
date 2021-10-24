package model;

import java.util.ArrayList;
import java.util.List;

public class Promocion {
	protected int valor;
	protected double tiempoPromo;
	protected TipoDeAtraccion tipoAtraccion;
	protected TipoDePromo tipo;
	protected List<Atraccion> atraccionesEnPromocion = new ArrayList<Atraccion>();

	public Promocion(List<Atraccion> atraccionesEnPromo) {

		this.atraccionesEnPromocion = atraccionesEnPromo;
		this.setTiempo();
	}

	// --------------------------GETTERS----------------------


	public List<Atraccion> getAtraccionesEnPromocion() {
		return atraccionesEnPromocion;
	}
	
	public String getNombre() {
		
		String salida="";
		
		for(Atraccion cadaUna :this.getAtraccionesEnPromocion())
			salida += " "+cadaUna.getNombre() + ",";
			
		return salida;
	}
	
	public int getValorPromo() {
		return this.valor;
	}
	
	public double getTiempoDeUso() {
		return tiempoPromo;
	}

	public TipoDePromo getTipo() {
		return tipo;
	}

	public TipoDeAtraccion getTipoDeAtraccion() {
		//asumiendo que est� validado que todas las atracciones son del mismo tipo
		return this.atraccionesEnPromocion.get(0).getTipo();
	}
	
	public boolean tieneCupos() {
		for(Atraccion cadaAtraccion : this.atraccionesEnPromocion) {
			if(cadaAtraccion.getUsosDisponibles()<=0) {
				return false;
			}
		}
		return true;
	}
//-------------------------SETTERS--------------------
	public void setTiempo() {
		this.tiempoPromo=0;
		for(Atraccion cadaUna : atraccionesEnPromocion) {
			this.tiempoPromo += cadaUna.getTiempoDeUso();
		}
	}

	//--------equals y hashcode---------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atraccionesEnPromocion == null) ? 0 : atraccionesEnPromocion.hashCode());
		long temp;
		temp = Double.doubleToLongBits(tiempoPromo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((tipoAtraccion == null) ? 0 : tipoAtraccion.hashCode());
		result = prime * result + valor;
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
		Promocion other = (Promocion) obj;
		if (atraccionesEnPromocion == null) {
			if (other.atraccionesEnPromocion != null)
				return false;
		} else if (!atraccionesEnPromocion.equals(other.atraccionesEnPromocion))
			return false;
		if (Double.doubleToLongBits(tiempoPromo) != Double.doubleToLongBits(other.tiempoPromo))
			return false;
		if (tipo != other.tipo)
			return false;
		if (tipoAtraccion != other.tipoAtraccion)
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}
	
	

}
