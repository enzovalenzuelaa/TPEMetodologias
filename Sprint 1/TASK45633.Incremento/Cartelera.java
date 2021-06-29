
public class Cartelera {
	
	
	public String determinarZona(String direc) {
		return direc+10;//Supongo un proceso que determine la zona
	}
	
	public void enviarEmail(String c, Ciudadano a){
		//Realiza el envio del email con un contenido y un destinatario.
	}

	public String realizarPost(CiudadanoConTransporte c) {
		String zona = determinarZona(c.getDireccion());
		String txt=c.getTexto();
		String espacio = c.getEspaciolibre();
		txt.concat(espacio);
		zona.concat(txt);
		return zona;
	}
	
	public String postularPeticion(CiudadanoConMaterial c, CiudadanoConTransporte e) {
		String nom=c.getNombre();
		String tel=c.getTelefono();
		String dir=c.getDireccion();
		String vol=c.getVolumen();
		
		dir.concat(vol);
		tel.concat(dir);
		nom.concat(tel);
		return nom;
	}
	
	public void enviarDatos(CiudadanoConMaterial c, CiudadanoConTransporte e) {
		if (e.acepetaPedido()) {
			enviarEmail(postularPeticion(c, e), e);
		}
		else enviarEmail("Solicitud rechazada.", c);
	}
	
	
	public static void main(String args[]) {
		CiudadanoConMaterial ciudmat = new CiudadanoConMaterial("a", "Juan", "2494316268", "Rodriguez 934");
		CiudadanoConTransporte ciudtrans = new CiudadanoConTransporte("XxMarcoAntonioSolisxX@email.com", "b", "texto", "Marquito", "2494613026", "Pinto 599");
	}
}