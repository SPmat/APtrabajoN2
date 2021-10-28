package model;

import java.util.List;

public class PromoAxB extends Promocion {
	
	/*
	 * Suma el valor de cada atraccion de la lista sin tener en cuenta el valor de la ultima. 
	 */

	public PromoAxB(List<Atraccion> atraccionesEnPromo) {
		super(atraccionesEnPromo);
		this.atraccionesEnPromocion=atraccionesEnPromo;
		this.setValor();
		this.tipoAtraccion = atraccionesEnPromo.get(0).getTipo();
		this.tipo = TipoDePromo.AxB;
		
	}	
	
	public void setValor() {
		this.valor=0;
		//recorre las atracciones sin incluir la ultima, que supuestamente es gratis
		for(int i=0; i<atraccionesEnPromocion.size()-1;i++) {
			this.valor+= this.atraccionesEnPromocion.get(i).getValor();
		}
	}
}
