
public class Erratique extends Neuneu {

	/**
	 * Constructeur par d�faut
	 */
	public Erratique() {
		this.setId(0);
		this.setEnergie(1);
		this.setPresenceLoft(true);
	}
	
	/**
	 * (re)D�finition de la classe seReproduire
	 */
	public Erratique seReproduire() {
		Erratique babyErratique = new Erratique() ;
		return babyErratique;
	}

	/**
	 * (re)D�finition de la classe seComporter
	 */
	public void seComporter() {
		
	}
	
}
