
public abstract class Neuneu {

	/**
	 * Attributs génériques des Neuneus
	 */
	protected int id, energie;
	protected boolean presenceLoft;
	protected CaseLoft coord;

	/**
	 * Getters and setters
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public boolean isPresenceLoft() {
		return presenceLoft;
	}

	public void setPresenceLoft(boolean presenceLoft) {
		this.presenceLoft = presenceLoft;
	}
	
	/**
	 * Pour récupérer la position X du Neuneu
	 */
	//public int getCoordX() {
	//	
	//}
	
	/**
	 * Pour récupérer la position Y du Neuneu
	 */
	//public int getCoordY() {
	//	
	//}

	/**
	 * Pour l'action de déplacement des Neuneus
	 */
	protected void seDeplacer() {

	}

	/**
	 * Pour l'action de manger des Neuneus
	 */
	protected void manger() {

	}

	/**
	 * Pour savoir si un Neuneu a encore de l'énergie Donc s'il est encore
	 * vivant !
	 */
	protected boolean energieSuffisante() {
		boolean statutEnergie = false;

		return statutEnergie;
	}

	/**
	 * Méthode abstraite pour la reproduction Redéfinie pour chaque type de
	 * Neuneu Pour créer un type de Neuneu adéquat en tant que bébé
	 */
	abstract Neuneu seReproduire();

	/**
	 * Méthode abstraite pour le comportement Redéfinie pour chaque type de
	 * Neuneu
	 */
	abstract void seComporter();

}
