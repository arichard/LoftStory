import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;

public class Cannibale extends Neuneu {

	/**
	 * Constructeur par defaut
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
	 * (re)Definition de la classe seReproduire
	 */
	public boolean seReproduire(Neuneu N) {
		boolean aReprodui = false;

		// on verifie que le Neuneu a assez d'energie pour se reproduire
		if (N.getEnergie() > 5) {
			// Reproduction = consommation d'energie pour les Neuneus
			this.setEnergie(this.getEnergie() - 5);
			N.setEnergie(N.getEnergie() - 5);

			// Definition des attributs du bebe
			int idBaby = 0;
			int energieBaby = this.getEnergieDefaut();
			int energieDefautBaby = this.getEnergieDefaut();
			boolean presenceLoftBaby = false;
			CaseLoft coordBaby = new CaseLoft(-10, -10, this.getCoord()
					.getLoft());

			// Instanciation du bebe
			Cannibale babyCannibale = new Cannibale(idBaby, energieBaby,
					energieDefautBaby, presenceLoftBaby, coordBaby);
			this.getCoord().getLoft().introduireNeuneu(babyCannibale);
			aReprodui = true;
		}

		return aReprodui;
	}

	/**
	 * (re)Definition de la classe seComporter
	 */
	public void seComporter() {
		// instanciation des variables necessaires
		boolean aMange = false, aReprodui = false;

		// il regarde d'abord s'il y a Nourriture/Neuneu sur sa case
		// si oui il mange et se deplace
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

			// liste la nourriture et les neuneus a proximite
			double listeDistancesNourriture[] = new double[8];
			double listeDistancesNeuneu[] = new double[8];
			int i = 0;
			for (CaseLoft C : listeCasesAdj) {
				double distance = -Math.pow((this.getCoordX() - C.getX()), 2.0)
						- Math.pow((this.getCoordY() - C.getY()), 2.0);
				if (0 < C.getPresenceNourriture().size()
						&& 0 < C.getPopulationCase().size()) {
					listeDistancesNourriture[i] = distance;
					listeDistancesNeuneu[i] = distance;
				} else if (0 < C.getPresenceNourriture().size()) {
					listeDistancesNourriture[i] = distance;
					listeDistancesNeuneu[i] = 0.0;
				} else if (0 < C.getPopulationCase().size()) {
					listeDistancesNourriture[i] = 0.0;
					listeDistancesNeuneu[i] = distance;
				} else {
					listeDistancesNourriture[i] = 0.0;
					listeDistancesNeuneu[i] = 0.0;
				}
				i++;
			}

			// calcul des distances minimales
			double distanceMiniNourriture = 0.0, distanceMiniNeuneu = 0.0;
			int indiceDistanceMiniNourriture = 0, indiceDistanceMiniNeuneu = 0;
			for (int m = 0; m < listeDistancesNourriture.length; m++) {
				if (listeDistancesNourriture[m] > distanceMiniNourriture) {
					distanceMiniNourriture = listeDistancesNourriture[m];
					indiceDistanceMiniNourriture = m;
				}
			}
			for (int p = 0; p < listeDistancesNeuneu.length; p++) {
				if (listeDistancesNeuneu[p] > distanceMiniNeuneu) {
					distanceMiniNeuneu = listeDistancesNeuneu[p];
					indiceDistanceMiniNeuneu = p;
				}
			}

			// cas Nourriture presente et Nourriture plus proche que Neuneu
			if (indiceDistanceMiniNourriture < 0
					&& indiceDistanceMiniNourriture < indiceDistanceMiniNeuneu) {
				this.manger(listeCasesAdj.get(indiceDistanceMiniNourriture));
				aMange = true;
				// le Neuneu se deplace sur la case sur laquelle il vient de
				// manger
				this.setCoord(listeCasesAdj.get(indiceDistanceMiniNourriture)
						.getX(), listeCasesAdj
						.get(indiceDistanceMiniNourriture).getY());
			}
			// cas Neuneu present et Neuneu plus proche que Nourriture
			else if (indiceDistanceMiniNeuneu < 0
					&& indiceDistanceMiniNeuneu < indiceDistanceMiniNourriture) {
				this.mangerNeuneu(listeCasesAdj.get(indiceDistanceMiniNeuneu));
				aMange = true;
				// le Neuneu se deplace sur la case sur laquelle il vient de
				// manger
				this.setCoord(listeCasesAdj.get(indiceDistanceMiniNeuneu)
						.getX(), listeCasesAdj.get(indiceDistanceMiniNeuneu)
						.getY());
			}
			// cas Neuneu present et Nourriture present a egale distance
			else if (indiceDistanceMiniNeuneu < 0
					&& indiceDistanceMiniNeuneu == indiceDistanceMiniNourriture) {
				this.manger(listeCasesAdj.get(indiceDistanceMiniNourriture));
				this.mangerNeuneu(listeCasesAdj.get(indiceDistanceMiniNeuneu));
				aMange = true;
				// le Neuneu se deplace sur la case sur laquelle il vient de
				// manger
				this.setCoord(listeCasesAdj.get(indiceDistanceMiniNeuneu)
						.getX(), listeCasesAdj.get(indiceDistanceMiniNeuneu)
						.getY());
			}
		}

		// s'il n'a pas mange, il va chercher a se reproduire
		if (aMange == false) {
			// d'abord sur sa case
			if (0 < this.getCoord().getPopulationCase().size()) {
				aReprodui = this.seReproduire(this.getCoord()
						.getPopulationCase().get(0));
				this.seDeplacer();
			}
			// sinon sur les cases adjacentes
			int k = 0;
			while (k < listeCasesAdj.size()) {
				if (aReprodui == false) {
					if (0 < listeCasesAdj.get(k).getPopulationCase().size()
							&& aReprodui == false) {
						// le Neuneu se reproduit
						aReprodui = this.seReproduire(listeCasesAdj.get(k)
								.getPopulationCase().get(0));
						// le Neuneu se deplace sur la case sur laquelle il
						// vient de
						// se reproduire
						this.setCoord(listeCasesAdj.get(k).getX(),
								listeCasesAdj.get(k).getY());
					}
				}
				k++;
			}
		}

		// s'il n'a ni mange ni ne s'est reproduit, il se deplace
		if (aMange == false && aReprodui == false) {
			this.seDeplacer();
		}
	}

	/**
	 * Nouvelle methode mangerNeuneu definie pour le Cannibale
	 */
	public void mangerNeuneu(CaseLoft C) {
		// choix du Neuneu ayant la plus grande quantite energetique
		LinkedList<Integer> listeEnergie = new LinkedList<Integer>();
		for (Neuneu N : C.getPopulationCase()) {
			listeEnergie.add(N.getEnergie());
		}
		Object obj = Collections.min(listeEnergie);
		Integer indiceEnergieMax = (Integer) obj;

		// on ajoute l'energie du Neuneu choisi au Cannibale
		int nouvelleEnergie = this.getEnergie()
				+ C.getPopulationCase().get(indiceEnergieMax).getEnergie();
		this.setEnergie(nouvelleEnergie);
		// on enleve le Neuneu ainsi mange du Loft
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
