public abstract class Neuneu implements ObjetDessinable {

	/**
	 * Attributs génériques des Neuneus
	 */
	protected int id, energie, energieDefaut;
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

	public int getEnergieDefaut() {
		return energieDefaut;
	}

	public void setEnergieDefaut(int energieDefaut) {
		this.energieDefaut = energieDefaut;
	}

	public boolean isPresenceLoft() {
		return presenceLoft;
	}

	public void setPresenceLoft(boolean presenceLoft) {
		this.presenceLoft = presenceLoft;
	}

	/**
	 * Pour récupérer les coordonnées du Neuneu
	 */
	public CaseLoft getCoord() {
		return coord;
	}

	/**
	 * Pour récupérer la position X du Neuneu
	 */
	public int getCoordX() {
		return getCoord().getX();
	}

	/**
	 * Pour récupérer la position Y du Neuneu
	 */
	public int getCoordY() {
		return getCoord().getY();
	}

	/**
	 * Pour modifier les coordonnées du Neuneu
	 */
	public void setCoord(int coordX, int coordY) {
		this.coord.setX(coordX);
		this.coord.setY(coordY);
	}

	/**
	 * Constructeurs pour un nouveau Neuneu
	 */
	protected Neuneu() {
	}

	protected Neuneu(int id, int energie, int energieDefaut,
			boolean presenceLoft, CaseLoft coord) {
		this.id = id;
		this.energie = energie;
		this.energieDefaut = energieDefaut;
		this.presenceLoft = presenceLoft;
		this.coord = coord;
	}

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
		if (getEnergie() > 0) {
			statutEnergie = true;
		}
		return statutEnergie;
	}

	/**
	 * Méthode abstraite pour la reproduction Redéfinie pour chaque type de
	 * Neuneu Pour créer un type de Neuneu adéquat en tant que bébé
	 */
	abstract void seReproduire(Neuneu N);

	/**
	 * Méthode abstraite pour le comportement Redéfinie pour chaque type de
	 * Neuneu
	 */
	abstract void seComporter();

}
