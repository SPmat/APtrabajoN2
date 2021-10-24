package model;

import java.util.List;

public class PromoAbsoluta extends Promocion {

/*
 * Toma como costo de la promocion al valor indicado con valorPromo (leido desde archivo).
 */
	
	
	public PromoAbsoluta(List<Atraccion> atraccionesEnPromo, int valorPromo) {
		super(atraccionesEnPromo);
		this.atraccionesEnPromocion=atraccionesEnPromo;
		this.valor= valorPromo;
		this.tipoAtraccion = atraccionesEnPromo.get(0).getTipo();
		this.tipo = TipoDePromo.ABSOLUTA;
		
	}
	
}