import java.util.Collections;
import java.util.LinkedList;

public abstract class Neuneu implements ObjetDessinable {

	/**
	 * Attributs g�n�riques des Neuneus
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
	 * Pour r�cup�rer les coordonn�es du Neuneu
	 */
	public CaseLoft getCoord() {
		return coord;
	}

	/**
	 * Pour r�cup�rer la position X du Neuneu
	 */
	public int getCoordX() {
		return getCoord().getX();
	}

	/**
	 * Pour r�cup�rer la position Y du Neuneu
	 */
	public int getCoordY() {
		return getCoord().getY();
	}

	/**
	 * Pour modifier les coordonn�es du Neuneu
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
	 * Pour l'action de d�placement al�atoire des Neuneus
	 */
	protected void seDeplacer() {
		// on choisit al�atoirement la case adjacente sur laquelle va aller le
		// Neuneu
		int mini = 1, maxi = this.getCoord().casesAdj().size() + 1;
		int random = (int) (Math.random() * (maxi - mini)) + mini;

		// on va d�finir les nouvelles coordonn�es du Neuneu
		// en r�cup�rant les coordonn�es de la case choisie al�atoirement
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
		// choix de la nourriture ayant la plus grande quantit� �nerg�tique
		LinkedList<Integer> listeQteEnergetique = new LinkedList<Integer>();
		for (Nourriture N : C.getPresenceNourriture()) {
			listeQteEnergetique.add(N.getQteEnergetique());
		}
		Object obj = Collections.min(listeQteEnergetique);
		Integer indiceQteEnergetiqueMax = (Integer) obj;

		// on ajoute l'�nergie de la nourriture choisie au Neuneu
		int nouvelleEnergie = this.getEnergie()
				+ C.getPresenceNourriture().get(indiceQteEnergetiqueMax)
						.getQteEnergetique();
		this.setEnergie(nouvelleEnergie);
		// on enl�ve la nourriture mang�e de la liste
		C.getPresenceNourriture().remove(indiceQteEnergetiqueMax);
	}

	/**
	 * Pour savoir si un Neuneu a encore de l'�nergie Donc s'il est encore
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
	 * M�thode abstraite pour la reproduction Red�finie pour chaque type de
	 * Neuneu Pour cr�er un type de Neuneu ad�quat en tant que b�b�
	 */
	abstract void seReproduire(Neuneu N);

	/**
	 * M�thode abstraite pour le comportement Red�finie pour chaque type de
	 * Neuneu
	 */
	abstract void seComporter();

}
