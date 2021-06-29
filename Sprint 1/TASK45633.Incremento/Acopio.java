package tpetercersprint;

import java.sql.Date;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

public class Acopio {

	private Vector<Cartonero> cartoneros;
	private Vector<Ciudadano> ciudadanos_materiales_retirar;
	private Vector<String> registro_cambios;
	
	private String reg_usuario;
	private String reg_contrasenia;
		
	private Hashtable<String, String> explicacion_materiales;
	
	private Cartelera cartelera;
	
	private Hashtable<String, Double> kilos_recolectados;
	
	private static final Cartonero BUENA_ONDA = new Cartonero("Vecino Buena Onda",0,"",new Date(0),0);
	
	public static final double DISTANCIA_MAXIMA = 6; // Distancia maxima permitida entre direccion del ciudadano
	 												 // y el acopio.
	
	public Acopio(String reg_nom, String reg_con, Cartelera car) {
		ciudadanos_materiales_retirar = new Vector<Ciudadano>();
		this.registro_cambios = new Vector<String>();
		
		cartoneros = new Vector<Cartonero>();
		this.addCartonero(BUENA_ONDA);
		
		reg_usuario = reg_nom;
		reg_contrasenia = reg_con;
		
		this.explicacion_materiales = new Hashtable<String, String>();
		this.cartelera = car;
		
		this.kilos_recolectados = new Hashtable<String, Double>();
	}
	
	
	
	public void addCartonero(Cartonero c) {
		cartoneros.add(c);
		String cartnuevo = "Cartonero nuevo: " + c.getNombre() + ", DNI: " + c.getDNI() +
				", Dir: " + c.getDireccion() + ", FechaNac: " + c.getFecha_nacimiento();
		this.generarCambioRegistro(cartnuevo);
	}
	
	public void removeCartonero(Cartonero c) {
		Hashtable<String, Double> material_cartonero = c.getKilosRecolectados();
		for (String mate : material_cartonero.keySet()) {
			double viejo = BUENA_ONDA.getCantidadMaterial(mate);
			viejo += c.getCantidadMaterial(mate);
			BUENA_ONDA.setCantidadMaterial(mate, viejo);
		}// se asignan los materiales del cartonero removido al usuario buena onda.
		
		cartoneros.remove(c);
		String adios = "Cartonero: " + c.getNombre() + ". con DNI: " + c.getDNI() + ", fue removido";
		this.generarCambioRegistro(adios);
	}
	
	public Vector<Cartonero> getCartoneros() {
		return new Vector<Cartonero>(cartoneros);
	}
	
	public boolean cartoneroCambioAtributo(Cartonero c1) {
		boolean aux = c1.getCambioAtributo();
		if (aux) {
			String tt = "El Cartonero con DNI: " + c1.getDNI() + ", realizo cambios en sus atributos.";
			this.generarCambioRegistro(tt);
			c1.setCambioAtributo();
		}
		return aux;
	}
	
	public void generarCambioRegistro(String c) {
		// Se genera un registro cada vez que llega un cartonero nuevo o si se modifica alguno.
		registro_cambios.add(c);
		System.out.println(c);
	}
	
	
	
	
	public void enviarMail(Object correo, Object destinatario) {
		// Dado un correo se envia a un destinatario.
	}
	
	
	public Vector<String> generarListadoRecorridos() {
		Vector<String> salida = new Vector<String>();
		
		int viajesporcartonero;
		if (cartoneros.size() == 1)
			return null;
		
		viajesporcartonero = ciudadanos_materiales_retirar.size() / (cartoneros.size()-1);
		
		for (int j=1; j<cartoneros.size(); j++) {
			double capacidad_restante = cartoneros.get(j).getCapacidad_transporte();
			double distancia_restante = Acopio.DISTANCIA_MAXIMA;
			char categoria_mayor = '1';
			
			for (int i=0; i<viajesporcartonero; i++) {
				if (!ciudadanos_materiales_retirar.isEmpty()) {
					Ciudadano aux = ciudadanos_materiales_retirar.get(0);
					capacidad_restante -= aux.getTamanio();
					distancia_restante -= this.DistanciaAlAcopio(aux);
					char categoria_actual = aux.getCategoria();
					if (capacidad_restante > 0 && distancia_restante > 0 && 
							categoria_actual > categoria_mayor) {
						String aux1 = "Cartonero:" + cartoneros.get(i) + ", Direccion: " + aux.getDireccion()
						 + ", Franja Horaria: " + aux.getFranjahoraria();
						categoria_mayor = categoria_actual;
						salida.add(aux1);
						ciudadanos_materiales_retirar.remove(0);
					} else {
						capacidad_restante += aux.getTamanio();
						distancia_restante += this.DistanciaAlAcopio(aux);
					}
				}
			}
		}
		
		double capacidad_restante = cartoneros.get(cartoneros.size()-1).getCapacidad_transporte();
		double distancia_restante = Acopio.DISTANCIA_MAXIMA;
		char categoria_mayor = '1';
		
		if (cartoneros.size() > 1) { // Para que no se genere una lista con el vecino buena onda
			while (!ciudadanos_materiales_retirar.isEmpty()) { //Caso en que los viajes por cartonero sean impar
				Ciudadano aux = ciudadanos_materiales_retirar.get(0);
				capacidad_restante -= aux.getTamanio();
				distancia_restante -= this.DistanciaAlAcopio(aux);
				char categoria_actual = aux.getCategoria();
				if (capacidad_restante > 0 && distancia_restante > 0 && 
						categoria_actual > categoria_mayor) {
					String aux1 = "Cartonero:" + cartoneros.get(cartoneros.size()-1) + ", Direccion: " + aux.getDireccion()
								+ ", Franja Horaria: " + aux.getFranjahoraria();
					categoria_mayor = categoria_actual;
					salida.add(aux1);
					ciudadanos_materiales_retirar.remove(0);
				} else {
					capacidad_restante += aux.getTamanio();
					distancia_restante += this.DistanciaAlAcopio(aux);
				}
			}
		}
		
		this.enviarMail(salida, "Secretaria");
		return salida;
	}
	
	
	
	public double DistanciaAlAcopio(Ciudadano c1) {
		return Math. random()*10+1;
		//return Double.valueOf(c1.getDireccion());	// Valor generado en base a la direccion del ciudadano.
		// Debido a que el valor generado en base a la direccion genera una excepcion en Java, generamos
		// un valor entre 1 y 10
	}
	
	public boolean aceptaSolicitudRecoleccion(Ciudadano c1) {
		return ((this.DistanciaAlAcopio(c1)) <= (Acopio.DISTANCIA_MAXIMA));
	}
	
	public void enviarEstadoSolicitudRecoleccion(Ciudadano c1) {
		boolean bandera = this.aceptaSolicitudRecoleccion(c1);
		if (bandera == true) {
			this.enviarMail("Solicitud aceptada", c1);
			this.ciudadanos_materiales_retirar.add(c1);
			System.out.println("Se agrego el ciudadano: " + c1);
		}
		else {
			String aux = "Solicitud rechazada, debera acercarse personalmente al centro del acopio";
			this.enviarMail(aux, c1);
			System.out.println("No se agrego al ciudadno: " + c1.getNombre());
		}	
	}
	
	
	
	
	public Vector<String> getRegCambios(String nombre, String contrasenia) {
		if (this.reg_usuario.equals(nombre) && this.reg_contrasenia.equals(contrasenia))
			return new Vector<String>(registro_cambios);
		System.out.println("Error: datos mal ingresados.");
		return null;
	}
	
	
	
	public void addKiloVendido(String material, Double cantidad) {
		Double aux = kilos_recolectados.get(material);
		if (aux == null)
			kilos_recolectados.put(material, cantidad);
		else {
			aux += cantidad;
			kilos_recolectados.put(material, aux);
		}
	}
	
	public void setKilosRestantes(String material, Double cantidad) {
		kilos_recolectados.put(material, cantidad);
	}
	
	
	public double getKilosPesadosCartonero(Cartonero c1) {
		// Como no sabemos cuanto pesa realmente lo que un cartonero trajo, generamos un valor random
		// entre 0 y la capacidad del vehiculo del cartonero.
		// Suponemos que interactua con la balanza que utiliza tecnologia bluetooth
		return Math. random()*c1.getCapacidad_transporte();
	}
	
	public boolean quiereRegistrarse(Cartonero c1) {
		Random random = new Random();
		//For 50% chance of true
		boolean quiere_registrarse = (random.nextInt(2) == 0) ? true : false;
		return quiere_registrarse;
	}
	
	public void addKiloMaterialCartonero(Cartonero c1, String material) {
		double kilos_pesados = this.getKilosPesadosCartonero(c1);
		
		if (cartoneros.contains(c1)) {
			c1.addMaterial(material, kilos_pesados);
			System.out.println("Se agrego material recolectado al cartonero: " + c1.getNombre());
		}
		else if (!cartoneros.contains(c1) && this.quiereRegistrarse(c1)) {
			cartoneros.add(c1);
			c1.addMaterial(material, kilos_pesados);
			System.out.println("Se agrego material recolectado al cartonero que no estaba registrado: "
								+ c1.getNombre());
		} else { // En caso de que no quiera registrarse tiene que guardarse el material recolectado en el vecino buena onda
			Acopio.BUENA_ONDA.addMaterial(material, kilos_pesados);
			System.out.println("Se agrego material al vecino buena onda");
		}
		this.addKiloVendido(material, kilos_pesados);
		System.out.println("se agrego: " + kilos_pesados + " de " + material);
		System.out.println(kilos_recolectados);
	}
	
	
	
	public Vector<Ciudadano> getCiudadanosMaterialesRetirar(){
		return new Vector<Ciudadano>(ciudadanos_materiales_retirar);
	}
	
	
	
	public double getKilosPesadosCiudadano(Ciudadano c1) {
		return Math. random()*c1.getTamanio();
	}
	
	public void addKiloMaterialCiudadano(Ciudadano c1, String material) {
		double kilos_pesados = this.getKilosPesadosCiudadano(c1);
		Acopio.BUENA_ONDA.addMaterial(material, kilos_pesados);
		System.out.println("Se agrego material al vecino buena onda desde ciudadano: " + c1.getNombre());
		this.addKiloVendido(material, kilos_pesados);
	}

	public double getCantidadMaterial(String material) {
		Double aa = kilos_recolectados.get(material);
		if (aa == null)
			aa = (double) 0;
		
		return aa;
	}

	
	
	public Hashtable<String, String> getExplicacionMateriales(){
		return new Hashtable<String, String>(explicacion_materiales);
	}

	public void addExplicacion(String material, String explicacion) {
		explicacion_materiales.put(material, explicacion);
	}
	
	public void removeExplicacionMaterial(String material) {
		if (explicacion_materiales.contains(material))
			explicacion_materiales.remove(material);
	}
	
	

	public Cartelera getCartelera() {
		return cartelera;
	}

	
	public Hashtable<Cartonero, RegMaterialPorcentaje> kilosAcopiados(String material) {
		if (this.getCantidadMaterial(material) == 0) {
			return null;
		}
		Hashtable<Cartonero,RegMaterialPorcentaje> salida = new Hashtable<Cartonero,RegMaterialPorcentaje>();
		for (int i=0; i<cartoneros.size(); i++) {
			double cantmat = cartoneros.get(i).getCantidadMaterial(material);
			double porcen = (cantmat / this.getCantidadMaterial(material)) * 100;
			
			RegMaterialPorcentaje aux = new RegMaterialPorcentaje(cantmat, porcen);
			salida.put(cartoneros.get(i), aux);
		}
		return salida;
	}
	
	public Hashtable<String, Double> getKilosRecolectados(){
		return new Hashtable<String, Double>(kilos_recolectados);
	}
	
	public void registrarKilosVendidos(String material, double cantidad) {
		if (!kilos_recolectados.containsKey(material))
			System.out.println("Error: el material ingresado no existe");
		else if (this.getCantidadMaterial(material) < cantidad) {
			System.out.println("Error: La cantidad a vender supera la cantidad del material actual");
			System.out.println("Cantidad actual: " + this.getCantidadMaterial(material) 
			                   + ", cantidad a vender: " + cantidad);
		}
		else {
			Hashtable<Cartonero, RegMaterialPorcentaje> aa = this.kilosAcopiados(material);
			for (Cartonero cartonero : aa.keySet()) {
				Double porcentaje = aa.get(cartonero).getPorcentaje_material();
				System.out.println("el porcentaje de " + cartonero.getNombre() + ": " + porcentaje);
				double materialreco = cartonero.getCantidadMaterial(material);
				double actualizado = materialreco - (materialreco * porcentaje / 100);
				cartonero.setCantidadMaterial(material, actualizado);
			}
			this.setKilosRestantes(material, this.getCantidadMaterial(material)-cantidad);
			System.out.println("Cantidad de material restante: " + this.getCantidadMaterial(material));
		}
	}
	
	
	
	
	public static void main(String[] args) {
		
		Date fecha1 = new Date(0);
		
		Cartonero cart1 = new Cartonero("Roberto",21456789,"9 de Julio 21",fecha1,100);
		Cartonero cart2 = new Cartonero("Nicolas",21987643,"Maipu 534",fecha1,1000);
		
		Ciudadano ciu1 = new Ciudadano("Santiago","aca","2983654819","9 a 12",'b',50);
		Ciudadano ciu2 = new Ciudadano("Valentino","alla","2983654899","9 a 12",'a',70);
		
		Cartelera cartelera = new Cartelera();
		
		Acopio Cooperativa = new Acopio("pepe","pepe",cartelera);
		Cooperativa.addCartonero(cart1);
		Cooperativa.addCartonero(cart2);
		Cooperativa.removeCartonero(cart2);
		
		Cooperativa.cartoneroCambioAtributo(cart1);
		cart1.setCapacidad_transporte(200);
		Cooperativa.cartoneroCambioAtributo(cart1);
		
		Cooperativa.enviarEstadoSolicitudRecoleccion(ciu1);
		Cooperativa.enviarEstadoSolicitudRecoleccion(ciu2);
		
		System.out.println("Listado Recorridos: " + Cooperativa.generarListadoRecorridos());
		// Recordar que si un cartonero puede buscar el material de un ciudadano, este ultimo se borra.
		
		//System.out.println(Cooperativa.getRegCambios("hola", "chau"));
		System.out.println(Cooperativa.getRegCambios("pepe", "pepe"));

		Cooperativa.addKiloMaterialCartonero(cart1, "Vidrio");
		Cooperativa.addKiloMaterialCartonero(cart2, "Vidrio");
		Cooperativa.addKiloMaterialCiudadano(ciu2, "Vidrio");
		System.out.println(Cooperativa.getCartoneros());
		System.out.println(Cooperativa.getCiudadanosMaterialesRetirar());
		
		Cooperativa.addExplicacion("Vidrio", "Se entrega en una caja copada");
		System.out.println(Cooperativa.getExplicacionMateriales());
		
		System.out.println("Materiales locos: " + Cooperativa.getKilosRecolectados());
		
		Cooperativa.registrarKilosVendidos("Vidrio", 10);
		
		Cooperativa.addKiloMaterialCartonero(cart1, "Plastico");
		Cooperativa.registrarKilosVendidos("Plastico", 10);
	}
}
