
public class Cannibale extends Neuneu {

	/**
	 * Constructeur par d�faut
	 */
	public Cannibale() {
		this.setId(0);
		this.setEnergie(1);
		this.setPresenceLoft(true);
	}
	
	/**
	 * (re)D�finition de la classe seReproduire
	 */
	public Cannibale seReproduire() {
		Cannibale babyCannibale = new Cannibale() ;
		return babyCannibale;
	}

	/**
	 * (re)D�finition de la classe seComporter
	 */
	public void seComporter() {
		
	}
	
}
