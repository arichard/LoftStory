
public class Cannibale extends Neuneu {

	/**
	 * Constructeur par défaut
	 */
	public Cannibale() {
		this.setId(0);
		this.setEnergie(1);
		this.setPresenceLoft(true);
	}
	
	/**
	 * (re)Définition de la classe seReproduire
	 */
	public Cannibale seReproduire() {
		Cannibale babyCannibale = new Cannibale() ;
		return babyCannibale;
	}

	/**
	 * (re)Définition de la classe seComporter
	 */
	public void seComporter() {
		
	}
	
}
