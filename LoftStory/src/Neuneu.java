import java.util.Random;

public abstract class Neuneu implements ObjetDessinable {

	/**
	 * Attributs
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

	public CaseLoft getCoord() {
		return coord;
	}

	public int getCoordX() {
		return getCoord().getX();
	}

	public int getCoordY() {
		return getCoord().getY();
	}

	public void setCoord(int coordX, int coordY) {
		this.coord.setX(coordX);
		this.coord.setY(coordY);
	}

	/**
	 * Constructeurs
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
	 * Pour l'action de deplacement aleatoire des Neuneus
	 */
	protected void seDeplacer() {
		// on choisit aleatoirement la case adjacente sur laquelle va aller le
		// Neuneu
		Random caseRandom = new Random();
		int indiceCaseRandom = caseRandom.nextInt(this.getCoord().casesAdj()
				.size());
		// on va definir les nouvelles coordonnees du Neuneu
		// en recuperant les coordonnees de la case choisie aleatoirement
		this.setCoord(this.getCoord().casesAdj().get(indiceCaseRandom).getX(),
				this.getCoord().casesAdj().get(indiceCaseRandom).getY());
	}

	/**
	 * Pour l'action de manger de la nourriture des Neuneus
	 */
	protected void manger(CaseLoft C) {
		// on ajoute l'energie de la nourriture
		// a l'energie du Neuneu
		int nouvelleEnergie = this.getEnergie()
				+ C.getPresenceNourriture().get(0).getQteEnergetique();
		this.setEnergie(nouvelleEnergie);
		// on enleve la nourriture mangee de la liste
		C.getPresenceNourriture().remove(0);

	}

	/**
	 * Pour savoir si un Neuneu a encore de l'energie donc s'il est encore
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
	 * Methode abstraite pour la reproduction redefinie pour chaque type de
	 * Neuneu pour creer un type de Neuneu adequat en tant que bebe
	 */
	abstract boolean seReproduire(Neuneu N);

	/**
	 * Methode abstraite pour le comportement redefinie pour chaque type de
	 * Neuneu
	 */
	abstract void seComporter();

}
