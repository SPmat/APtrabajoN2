package utilitarias;

import excepciones.*;
import model.TipoDeAtraccion;

public class Validacion {

	public static int validar(int numero) throws NumeroNegativoException{
			if(numero>=0) {
				return numero;
			} else {
				throw new NumeroNegativoException("Se ha ingresado un número negativo.");
			}
	}
	
	
	public static float validar(float numero) throws NumeroNegativoException {
	
			if(numero>=0) {
				return numero;
			} else {
				throw new NumeroNegativoException("Se ha ingresado un número negativo.");
			}
	}
	
	public static double validar(double numero) throws NumeroNegativoException {
			if(numero>=0) {
				return numero;
			} else {
				throw new NumeroNegativoException("Se ha ingresado un número negativo.");
			}
	}
	
	public static String validar(String cadena) throws NullPointerException {
			if(cadena!= null) {
				return cadena;
			} else {
				throw new NullPointerException("No se ingreso ninguna cadena de caracteres.");
			}
	}
	
	//solo tira error si los datos no son correctos
	
	public static void validarTipo(TipoDeAtraccion referencia, TipoDeAtraccion dato) throws DistintoTipoException {
		if(referencia!=dato) {
			throw new DistintoTipoException("Discrepancia de tipos de atraccion.");
		}
	}
}