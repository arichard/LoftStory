
public class Vorace extends Neuneu {

	/**
	 * Constructeur par défaut
	 */
	public Vorace() {
		this.setId(0);
		this.setEnergie(1);
		this.setPresenceLoft(true);
	}
	
	/**
	 * (re)Définition de la classe seReproduire
	 */
	public Vorace seReproduire() {
		Vorace babyVorace = new Vorace() ;
		return babyVorace;
	}

	/**
	 * (re)Définition de la classe seComporter
	 */
	public void seComporter() {
		
	}
	
}
