package model;

import java.util.List;


public class PromoPorcentual extends Promocion {

	/*
	 * Descuenta un porcentaje del valor total (suma) de todas las atracciones incluidas en la promo.
	 */

	public PromoPorcentual(List<Atraccion> atraccionesEnPromo, int valorDescuento) {
		super(atraccionesEnPromo);
		this.atraccionesEnPromocion=atraccionesEnPromo;
		
		this.tipoAtraccion = atraccionesEnPromo.get(0).getTipo();
		this.tipo = TipoDePromo.PORCENTUAL;
		
		int auxValor=0;
		for(Atraccion cadaUna: atraccionesEnPromocion) {
			auxValor+= cadaUna.getValor();
		}
		this.valor= (int) Math.round(auxValor- auxValor*(valorDescuento/100.0));
	}	
	
}