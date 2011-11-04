
public abstract class Neuneu {

	/**
	 * Attributs g�n�riques des Neuneus
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
	 * Pour r�cup�rer la position X du Neuneu
	 */
	//public int getCoordX() {
	//	
	//}
	
	/**
	 * Pour r�cup�rer la position Y du Neuneu
	 */
	//public int getCoordY() {
	//	
	//}

	/**
	 * Pour l'action de d�placement des Neuneus
	 */
	protected void seDeplacer() {

	}

	/**
	 * Pour l'action de manger des Neuneus
	 */
	protected void manger() {

	}

	/**
	 * Pour savoir si un Neuneu a encore de l'�nergie Donc s'il est encore
	 * vivant !
	 */
	protected boolean energieSuffisante() {
		boolean statutEnergie = false;

		return statutEnergie;
	}

	/**
	 * M�thode abstraite pour la reproduction Red�finie pour chaque type de
	 * Neuneu Pour cr�er un type de Neuneu ad�quat en tant que b�b�
	 */
	abstract Neuneu seReproduire();

	/**
	 * M�thode abstraite pour le comportement Red�finie pour chaque type de
	 * Neuneu
	 */
	abstract void seComporter();

}
