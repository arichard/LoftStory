import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;

public class Cannibale extends Neuneu {

	/**
	 * Constructeur par d�faut
	 */
	public Cannibale() {
		super();
	}

	/**
	 * Constructeur
	 */
	public Cannibale(int id, int energie, int energieDefaut,
			boolean presenceLoft, CaseLoft coord) {
		super(id, energie, energieDefaut, presenceLoft, coord);
	}

	/**
	 * (re)D�finition de la classe seReproduire
	 */
	public void seReproduire(Neuneu N) {
		// Reproduction = consommation d'�nergie pour les Neuneus
		this.setEnergie(this.getEnergie() - 4);
		N.setEnergie(N.getEnergie() - 4);
		// D�finition des attributs du b�b�
		int idBaby = 0;
		int energieBaby = this.getEnergieDefaut();
		int energieDefautBaby = this.getEnergieDefaut();
		boolean presenceLoftBaby = false;
		CaseLoft coordBaby = new CaseLoft(-1, -1, this.getCoord().getLoft());
		// Instanciation du b�b�
		Cannibale babyCannibale = new Cannibale(idBaby, energieBaby,
				energieDefautBaby, presenceLoftBaby, coordBaby);
		this.getCoord().getLoft().introduireNeuneu(babyCannibale);
		babyCannibale.setCoord(this.getCoordX(), this.getCoordY());
	}

	/**
	 * (re)D�finition de la classe seComporter
	 */
	public void seComporter() {
		// instanciation des variables n�cessaires
		boolean aMange = false, aReprodui = false;

		// il regarde d'abord s'il y a Nourriture/Neuneu sur sa case
		// si oui il mange et se d�place
		if (0 < this.getCoord().getPresenceNourriture().size()
				&& 0 < this.getCoord().getPopulationCase().size()) {
			this.manger(this.getCoord());
			this.mangerNeuneu(this.getCoord());
			aMange = true;
			this.seDeplacer();
		} else if (0 < this.getCoord().getPresenceNourriture().size()
				&& 0 == this.getCoord().getPopulationCase().size()) {
			this.manger(this.getCoord());
			aMange = true;
			this.seDeplacer();
		} else if (0 == this.getCoord().getPresenceNourriture().size()
				&& 0 < this.getCoord().getPopulationCase().size()) {
			this.mangerNeuneu(this.getCoord());
			aMange = true;
			this.seDeplacer();
		}

		// si non, il regarde les cases adjacentes
		LinkedList<CaseLoft> listeCasesAdj = new LinkedList<CaseLoft>();
		listeCasesAdj = this.getCoord().casesAdj();
		if (aMange == false) {
			// liste la nourriture et les neuneus � proximit�
			LinkedList<Double> listeDistancesNourriture = new LinkedList<Double>();
			LinkedList<Double> listeDistancesNeuneu = new LinkedList<Double>();
			for (CaseLoft C : listeCasesAdj) {
				double distance = -Math.pow((this.getCoordX() - C.getX()), 2.0)
						- Math.pow((this.getCoordY() - C.getY()), 2.0);
				if (0 < C.getPresenceNourriture().size()
						&& 0 < C.getPopulationCase().size()) {
					listeDistancesNourriture.add(distance);
					listeDistancesNeuneu.add(distance);
				} else if (0 < C.getPresenceNourriture().size()) {
					listeDistancesNourriture.add(distance);
					listeDistancesNeuneu.add(0.0);
				} else if (0 < C.getPopulationCase().size()) {
					listeDistancesNourriture.add(0.0);
					listeDistancesNeuneu.add(distance);
				} else {
					listeDistancesNourriture.add(0.0);
					listeDistancesNeuneu.add(0.0);
				}
			}

			// calcul de la distance minimale sur laquelle se trouve qqc �
			// manger
			Object obj1 = Collections.min(listeDistancesNourriture);
			Integer indiceDistanceMiniNourriture = (Integer) obj1;
			Object obj2 = Collections.min(listeDistancesNeuneu);
			Integer indiceDistanceMiniNeuneu = (Integer) obj2;
			// cas Nourriture pr�sente et Nourriture plus proche que Neuneu
			if (indiceDistanceMiniNourriture < 0
					&& indiceDistanceMiniNourriture < indiceDistanceMiniNeuneu) {
				this.manger(listeCasesAdj.get(indiceDistanceMiniNourriture));
				aMange = true;
				// le Neuneu se d�place sur la case sur laquelle il vient de
				// manger
				this.setCoord(listeCasesAdj.get(indiceDistanceMiniNourriture)
						.getX(), listeCasesAdj
						.get(indiceDistanceMiniNourriture).getY());
			}
			// cas Neuneu pr�sent et Neuneu plus proche que Nourriture
			else if (indiceDistanceMiniNeuneu < 0
					&& indiceDistanceMiniNeuneu < indiceDistanceMiniNourriture) {
				this.mangerNeuneu(listeCasesAdj.get(indiceDistanceMiniNeuneu));
				aMange = true;
				// le Neuneu se d�place sur la case sur laquelle il vient de
				// manger
				this.setCoord(listeCasesAdj.get(indiceDistanceMiniNeuneu)
						.getX(), listeCasesAdj.get(indiceDistanceMiniNeuneu)
						.getY());
			}
			// cas Neuneu pr�sent et Nourriture pr�sent � �gale distance
			else if (indiceDistanceMiniNeuneu < 0
					&& indiceDistanceMiniNeuneu == indiceDistanceMiniNourriture) {
				this.manger(listeCasesAdj.get(indiceDistanceMiniNourriture));
				this.mangerNeuneu(listeCasesAdj.get(indiceDistanceMiniNeuneu));
				aMange = true;
				// le Neuneu se d�place sur la case sur laquelle il vient de
				// manger
				this.setCoord(listeCasesAdj.get(indiceDistanceMiniNeuneu)
						.getX(), listeCasesAdj.get(indiceDistanceMiniNeuneu)
						.getY());
			}
		}

		// s'il n'a pas mang�, il va chercher � se reproduire
		if (aMange == false) {
			// d'abord sur sa case
			if (0 < this.getCoord().getPopulationCase().size()) {
				this.seReproduire(this.getCoord().getPopulationCase().get(0));
				aReprodui = true;
				this.seDeplacer();
			}
			// sinon sur les cases adjacentes
			int k = 0;
			while (k < listeCasesAdj.size()) {
				if (aReprodui == false) {
					if (0 < listeCasesAdj.get(k).getPopulationCase().size()
							&& aReprodui == false) {
						// le Neuneu se reproduit
						this.seReproduire(listeCasesAdj.get(k)
								.getPopulationCase().get(0));
						aReprodui = true;
						// le Neuneu se d�place sur la case sur laquelle il
						// vient de
						// se reproduire
						this.setCoord(listeCasesAdj.get(k).getX(),
								listeCasesAdj.get(k).getY());
					}
				}
				k++;
			}
		}

		// s'il n'a ni mang� ni ne s'est reproduit, il se d�place
		if (aMange == false && aReprodui == false) {
			this.seDeplacer();
		}
	}

	/**
	 * Nouvelle m�thode mangerNeuneu d�finie pour le Cannibale
	 */
	public void mangerNeuneu(CaseLoft C) {
		// choix du Neuneu ayant la plus grande quantit� �nerg�tique
		LinkedList<Integer> listeEnergie = new LinkedList<Integer>();
		for (Neuneu N : C.getPopulationCase()) {
			listeEnergie.add(N.getEnergie());
		}
		Object obj = Collections.min(listeEnergie);
		Integer indiceEnergieMax = (Integer) obj;

		// on ajoute l'�nergie du Neuneu choisi au Cannibale
		int nouvelleEnergie = this.getEnergie()
				+ C.getPopulationCase().get(indiceEnergieMax).getEnergie();
		this.setEnergie(nouvelleEnergie);
		// on enl�ve le Neuneu ainsi mang� du Loft
		C.getLoft().virerNeuneu(C.getPopulationCase().get(indiceEnergieMax));
	}

	@Override
	public void dessinerObjet(Graphics g) {
		int taille = ZoneGraphique.TAILLE_CASELOFT;
		g.setColor(Color.RED);
		g.fillOval(this.getCoordX() * taille, this.getCoordY() * taille,
				taille, taille);

	}

}
