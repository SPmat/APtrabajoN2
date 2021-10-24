package model;

public class Atraccion {

	private String nombre;
	private int valor;
	private double tiempoDeUso;
	private int usosDisponibles;
	private TipoDeAtraccion tipo;
	
	public Atraccion(String nombre, int valor, double tiempoDeUso, int usosMaximos, TipoDeAtraccion tipo) {

		this.nombre=nombre;
		this.valor=valor;
		this.tiempoDeUso=tiempoDeUso;
		this.usosDisponibles=usosMaximos;
		this.tipo=tipo;
	}

	//constructor por defecto
	public Atraccion() {
		this(" ", 0, 0,0, TipoDeAtraccion.DEFAULT);
	}

//--------------------------GETTERS----------------------------------
	public int getValor() {
		return this.valor;
	}

	public double getTiempoDeUso() {
		return this.tiempoDeUso;
	}

	public int getUsosDisponibles() {
		return this.usosDisponibles;

	}
	public TipoDeAtraccion getTipo() {
		return tipo;
	}
	
	public String getNombreTipo() {
		return this.tipo.getNombreDeTipo();
	}


	public String getNombre() {
		return this.nombre;
	}

	
	//--------------------SETTERS----------------

	protected void setTipo(TipoDeAtraccion tipo) {
		this.tipo = tipo;
	}
	
	//-----------------METODOS---------------------
	//Reescribe el toString de la superclase para poder usarlo en println
    @Override
    public String toString() {
        return nombre;
    }

	public void reservarLugar(Usuario unUsuario) {
		this.usosDisponibles-=1;
		unUsuario.agregarAtraccion(this);
		
		
	}

	//--------------equals y hashcode----------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(tiempoDeUso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + usosDisponibles;
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
		Atraccion other = (Atraccion) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(tiempoDeUso) != Double.doubleToLongBits(other.tiempoDeUso))
			return false;
		if (tipo != other.tipo)
			return false;
		if (usosDisponibles != other.usosDisponibles)
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}   
	
	
}