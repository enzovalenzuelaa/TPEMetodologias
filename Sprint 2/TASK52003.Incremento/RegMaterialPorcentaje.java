package tpetercersprint;

public class RegMaterialPorcentaje {

	private double cant_material;
	private double porcentaje_material;
	
	public RegMaterialPorcentaje(double cant_material, double porcentaje_material) {
		super();
		this.cant_material = cant_material;
		this.porcentaje_material = porcentaje_material;
	}

	public double getCant_material() {
		return cant_material;
	}

	public double getPorcentaje_material() {
		return porcentaje_material;
	}
	
	@Override
	public String toString() {
		return "CantMat: " + this.cant_material + ", porcentaje: " + this.porcentaje_material + "\n";
	}
}
