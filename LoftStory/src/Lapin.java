
public class Lapin extends Neuneu {

	/**
	 * Constructeur par d�faut
	 */
	public Lapin() {
		this.setId(0);
		this.setEnergie(1);
		this.setPresenceLoft(true);
	}
	
	/**
	 * (re)D�finition de la classe seReproduire
	 */
	public Lapin seReproduire() {
		Lapin babyLapin = new Lapin() ;
		return babyLapin;
	}

	/**
	 * (re)D�finition de la classe seComporter
	 */
	public void seComporter() {
		
	}
	
}
