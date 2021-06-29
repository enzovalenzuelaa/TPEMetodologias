
public class CiudadanoConMaterial extends Ciudadano {
	
	private String volumen;
	
	public CiudadanoConMaterial(String vol, String nom, String tel, String dir) {
		nombre=nom;
		direccion=dir;
		telefono=tel;
		
		switch(vol) {
			case ("a"):
				this.volumen="entra en una caja";
				break;
			case ("b"):
				this.volumen="entra en el baúl de un auto";
				break;
			case ("c"):
				this.volumen=" entra en la caja de una camioneta";
				break;
			case ("d"):
				this.volumen="es necesario un camión";
				break;
			default:
				System.out.println("No ingreso una letra valida.");
		}
	}

	public String getVolumen() {
		return volumen;
	}

	public void setVolumen(String vol) {
		switch(vol) {
			case ("a"):
				this.volumen="entra en una caja";
				break;
			case ("b"):
				this.volumen="entra en el baúl de un auto";
			break;
			case ("c"):
				this.volumen=" entra en la caja de una camioneta";
			break;
			case ("d"):
				this.volumen="es necesario un camión";
			break;
			default:
				System.out.println("No ingreso una letra valida.");
		}
	}
}
