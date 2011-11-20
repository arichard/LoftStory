import java.util.Collections;
import java.util.LinkedList;

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
	 * Pour l'action de déplacement aléatoire des Neuneus
	 */
	protected void seDeplacer() {
		// on choisit aléatoirement la case adjacente sur laquelle va aller le
		// Neuneu
		int mini = 1, maxi = this.getCoord().casesAdj().size() + 1;
		int random = (int) (Math.random() * (maxi - mini)) + mini;

		// on va définir les nouvelles coordonnées du Neuneu
		// en récupérant les coordonnées de la case choisie aléatoirement
		int i = 0;
		do {
			i++;
		} while (i < random);
		this.setCoord(this.getCoord().casesAdj().get(i).getX(), this.getCoord()
				.casesAdj().get(i).getY());
	}

	/**
	 * Pour l'action de manger des Neuneus
	 */
	protected void manger(CaseLoft C) {
		// choix de la nourriture ayant la plus grande quantité énergétique
		LinkedList<Integer> listeQteEnergetique = new LinkedList<Integer>();
		for (Nourriture N : C.getPresenceNourriture()) {
			listeQteEnergetique.add(N.getQteEnergetique());
		}
		Object obj = Collections.min(listeQteEnergetique);
		Integer indiceQteEnergetiqueMax = (Integer) obj;

		// on ajoute l'énergie de la nourriture choisie au Neuneu
		int nouvelleEnergie = this.getEnergie()
				+ C.getPresenceNourriture().get(indiceQteEnergetiqueMax)
						.getQteEnergetique();
		this.setEnergie(nouvelleEnergie);
		// on enlève la nourriture mangée de la liste
		C.getPresenceNourriture().remove(indiceQteEnergetiqueMax);
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
