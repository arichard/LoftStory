
public class Vorace extends Neuneu {

	/**
	 * Constructeur par d�faut
	 */
	public Vorace() {
		this.setId(0);
		this.setEnergie(1);
		this.setPresenceLoft(true);
	}
	
	/**
	 * (re)D�finition de la classe seReproduire
	 */
	public Vorace seReproduire() {
		Vorace babyVorace = new Vorace() ;
		return babyVorace;
	}

	/**
	 * (re)D�finition de la classe seComporter
	 */
	public void seComporter() {
		
	}
	
}