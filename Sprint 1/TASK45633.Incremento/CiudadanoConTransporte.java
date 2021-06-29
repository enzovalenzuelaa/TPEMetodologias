import java.util.Random;

public class CiudadanoConTransporte extends Ciudadano {
	
	private String email;
	private String espaciolibre; //en volumen de material.
	private String texto;
	
	public CiudadanoConTransporte(String em, String espacio, String txt, String nom, String tel, String dir) {
		nombre=nom;
		direccion=dir;
		telefono=tel;
		this.texto=txt;
		this.email=em;
		
		switch (espacio) {
		case ("a"):
			this.espaciolibre="entra en una caja";
			break;
		case ("b"):
			this.espaciolibre="entra en el baúl de un auto";
			break;
		case ("c"):
			this.espaciolibre="entra en la caja de una camioneta";
			break;
		case ("d"):
			this.espaciolibre="es necesario un camión";
			break;
		default:
			System.out.println("No ingreso una letra valida.");
		}
	}
	
	public boolean acepetaPedido() {
		Random rd= new Random();
		return rd.nextBoolean();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEspaciolibre() {
		return espaciolibre;
	}

	public void setEspaciolibre(String espacio) {
		switch(espacio) {
		case ("a"):
			this.espaciolibre="entra en una caja";
			break;
		case ("b"):
			this.espaciolibre="entra en el baúl de un auto";
		break;
		case ("c"):
			this.espaciolibre=" entra en la caja de una camioneta";
		break;
		case ("d"):
			this.espaciolibre="es necesario un camión";
		break;
		default:
			System.out.println("No ingreso una letra valida.");
	}
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
