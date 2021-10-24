package comparadores;

import java.util.Comparator;

import model.Promocion;

public class ComparadorPromocion implements Comparator<Promocion> {

	public int compare(Promocion promocion1, Promocion promocion2) {
		if(Integer.compare(promocion1.getValorPromo(), promocion2.getValorPromo())==0) {
			return Double.compare(promocion1.getTiempoDeUso(), promocion2.getTiempoDeUso());
		}
		return Integer.compare(promocion1.getValorPromo(), promocion2.getValorPromo());
	}
	
}
