package tpetercersprint;

import java.util.Hashtable;
import java.util.Random;

public class Ciudadano {
	private String nombre;
	private String telefono;
	private String direccion;
	
	private String franjahoraria;
	private Hashtable<Character, String> categoria;
	private double tamanio; // de materiales a retirar
	private String foto;
	
	
	public Ciudadano(String nom, String dir, String tel, String franjahoraria, char categoria,
					 double tamanio) {
		nombre = nom;
		telefono = tel;
		direccion = dir;
		
		this.franjahoraria = franjahoraria;
		this.tamanio = tamanio;
		this.categoria = new Hashtable<Character, String>();
		
		switch(categoria) {
		case ('a'):
			this.categoria.put(categoria, "Entra en una caja");
			break;
		case ('b'):
			this.categoria.put(categoria, "Entra en el baúl de un auto");
			break;
		case ('c'):
			this.categoria.put(categoria, "Entra en la caja de una camioneta");
			break;
		case ('d'):
			this.categoria.put(categoria, "Es necesario un camión");
			break;
		default:
			System.out.println("No ingreso una letra valida.");
		}
	}
	
	public Ciudadano(String nom, String dir, String tel, String franjahoraria, char categoria,
					 double tamanio, String foto) {
		nombre = nom;
		telefono = tel;
		direccion = dir;
		this.foto = foto;

		this.franjahoraria = franjahoraria;
		this.tamanio = tamanio;
		this.categoria = new Hashtable<Character, String>();
		
		switch(categoria) {
		case ('a'):
			this.categoria.put(categoria, "Entra en una caja");
			break;
		case ('b'):
			this.categoria.put(categoria, "Entra en el baúl de un auto");
			break;
		case ('c'):
			this.categoria.put(categoria, "Entra en la caja de una camioneta");
			break;
		case ('d'):
			this.categoria.put(categoria, "Es necesario un camión");
			break;
		default:
			System.out.println("No ingreso una letra valida.");
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	public String getFranjahoraria() {
		return franjahoraria;
	}

	public void setFranjahoraria(String franjahoraria) {
		this.franjahoraria = franjahoraria;
	}

	public Hashtable<Character, String> getCategoriaHash() {
		return new Hashtable<Character, String>(categoria);
	}
	
	public char getCategoria() {
		Character a = null;
		for (Character c : this.categoria.keySet()) {
			a = c;
		}
		return a;
	}

	public void setCategoria(char cat) {
		for (Character c : this.categoria.keySet()) {
			this.categoria.remove(c);
		}
		
		switch(cat) {
		case ('a'):
			this.categoria.put(cat, "Entra en una caja");
			break;
		case ('b'):
			this.categoria.put(cat, "Entra en el baúl de un auto");
			break;
		case ('c'):
			this.categoria.put(cat, "Entra en la caja de una camioneta");
			break;
		case ('d'):
			this.categoria.put(cat, "Es necesario un camión");
			break;
		default:
			System.out.println("No ingreso una letra valida.");
		}
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public double getTamanio() {
		return tamanio;
	}

	public void setTamanio(double tamanio) {
		this.tamanio = tamanio;
	}
	
	
	
	public boolean acpetaPedido(Ciudadano c) {
		Random rd= new Random();
		return rd.nextBoolean();
	}
	
	
	
	@Override
	public boolean equals(Object o1) {
		try {
			Ciudadano c = (Ciudadano)o1;
			
			return  nombre.equals(c.getNombre())&&
					direccion.equals(c.getDireccion())&&
					telefono.equals(c.getTelefono());
			
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Ciudadano: Nombre: " + this.nombre + ", Direccion: " + this.direccion + ", Telefono: "
				+ this.telefono + ".\n";
	}
}