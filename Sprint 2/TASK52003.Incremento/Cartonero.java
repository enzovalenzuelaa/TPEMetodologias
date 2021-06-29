package tpetercersprint;

import java.sql.Date;
import java.util.Hashtable;

public class Cartonero {
	
	private String nombre;
	private long DNI;
	private String direccion;
	private Date fecha_nacimiento;
	
	private double capacidad_transporte;
	
	private boolean cambio_atributo;
	
	private Hashtable<String, Double> kilos_recolectados;
	
	public Cartonero(String nombre, long dNI, String direccion, Date fecha_nacimiento, double capacidad_transporte) {
		super();
		this.nombre = nombre;
		DNI = dNI;
		this.direccion = direccion;
		this.fecha_nacimiento = fecha_nacimiento;
		this.capacidad_transporte = capacidad_transporte;
		cambio_atributo = false;
		
		kilos_recolectados = new Hashtable<String, Double>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		cambio_atributo = true;
	}

	public long getDNI() {
		return DNI;
	}

	public void setDNI(long dNI) {
		DNI = dNI;
		cambio_atributo = true;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
		cambio_atributo = true;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
		cambio_atributo = true;
	}
	
	public double getCapacidad_transporte() {
		return capacidad_transporte;
	}

	public void setCapacidad_transporte(double capacidad_transporte) {
		this.capacidad_transporte = capacidad_transporte;
		cambio_atributo = true;
	}

	public boolean getCambioAtributo() {
		return cambio_atributo;
	}
	
	protected void setCambioAtributo() {
		cambio_atributo = false;
	}
	

	protected void addMaterial(String material, double peso) {
		Double aux = kilos_recolectados.get(material);
		if (aux == null)
			kilos_recolectados.put(material, peso);
		else {
			aux += peso;
			kilos_recolectados.put(material, aux);
		}
	}
	
	protected double getCantidadMaterial(String material) {
		Double aux = kilos_recolectados.get(material);
		if (aux == null)
			return 0;
		else
			return aux;
	}
	
	protected void setCantidadMaterial(String material, double cant) {
		kilos_recolectados.put(material, cant);
	}
	
	public Hashtable<String, Double> getKilosRecolectados(){
		return new Hashtable<String,Double>(kilos_recolectados);
	}
	
	@Override
	public boolean equals(Object o1) {
		try {
			Cartonero c = (Cartonero)o1;
			
			return nombre.equals(c.getNombre())&&
					DNI == c.getDNI()&&
					direccion.equals(c.getDireccion())&&
					fecha_nacimiento.equals(c.getFecha_nacimiento())&&
					capacidad_transporte == c.getCapacidad_transporte();
			
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Cartonero; Nombre:" + this.nombre + ", DNI: " + this.DNI + ". Materiales"
				+ this.kilos_recolectados +"\n";
	}
}
