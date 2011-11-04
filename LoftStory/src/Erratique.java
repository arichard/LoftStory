
public class Erratique extends Neuneu {

	/**
	 * Constructeur par défaut
	 */
	public Erratique() {
		this.setId(0);
		this.setEnergie(1);
		this.setPresenceLoft(true);
	}
	
	/**
	 * (re)Définition de la classe seReproduire
	 */
	public Erratique seReproduire() {
		Erratique babyErratique = new Erratique() ;
		return babyErratique;
	}

	/**
	 * (re)Définition de la classe seComporter
	 */
	public void seComporter() {
		
	}
	
}
