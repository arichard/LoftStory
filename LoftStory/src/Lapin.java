
public class Lapin extends Neuneu {

	/**
	 * Constructeur par défaut
	 */
	public Lapin() {
		this.setId(0);
		this.setEnergie(1);
		this.setPresenceLoft(true);
	}
	
	/**
	 * (re)Définition de la classe seReproduire
	 */
	public Lapin seReproduire() {
		Lapin babyLapin = new Lapin() ;
		return babyLapin;
	}

	/**
	 * (re)Définition de la classe seComporter
	 */
	public void seComporter() {
		
	}
	
}
