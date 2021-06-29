package tpetercersprint;

public class Cartelera {
	
	public Cartelera() {
	}
	
	public String determinarZona(String direc) {
		return direc+10;//Supongo un proceso que determine la zona
	}
	
	public void enviarEmail(String c, Ciudadano a){
		//Realiza el envio del email con un contenido y un destinatario.
	}

	public String realizarPost(Ciudadano c, String texto, String email) {
		String zona = "Zona: " + determinarZona(c.getDireccion()) + ", ";
		String txt = "Correo: " + email + ", Texto: "+ texto;
		String cat = String.valueOf(c.getCategoria());
		txt.concat(cat);
		zona.concat(txt);
		return zona;
	}
	
	public String postularPeticion(Ciudadano c, Ciudadano e) {
		String nom =c.getNombre();
		String tel =c.getTelefono();
		String dir =c.getDireccion();
		String cat = String.valueOf(c.getCategoria());
		
		dir.concat(cat);
		tel.concat(dir);
		nom.concat(tel);
		return nom;
	}
	
	public void enviarDatos(Ciudadano c, Ciudadano e) {
		if (e.acpetaPedido(c)) {
			enviarEmail(postularPeticion(c, e), e);
		}
		else enviarEmail("Solicitud rechazada.", c);
	}
	
}