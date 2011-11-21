import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Vorace extends Neuneu {

	/**
	 * Constructeur par defaut
	 */
	public Vorace() {
		super();
	}

	/**
	 * Constructeur
	 */
	public Vorace(int id, int energie, int energieDefaut, boolean presenceLoft,
			CaseLoft coord) {
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
			Vorace babyVorace = new Vorace(idBaby, energieBaby,
					energieDefautBaby, presenceLoftBaby, coordBaby);
			this.getCoord().getLoft().introduireNeuneu(babyVorace);
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

		// il regarde d'abord s'il y a de la nourriture sur sa case
		// si oui il mange et se deplace
		if (0 < this.getCoord().getPresenceNourriture().size()) {
			this.manger(this.getCoord());
			aMange = true;
			this.seDeplacer();
		}

		// si non il regarde la nourriture a proximite
		LinkedList<CaseLoft> listeCasesAdj = new LinkedList<CaseLoft>();
		listeCasesAdj = this.getCoord().casesAdj();
		double listeDistances[] = new double[8];
		if (aMange == false) {
			// liste la nourriture
			int i = 0;
			for (CaseLoft C : listeCasesAdj) {
				if (0 < C.getPresenceNourriture().size()) {
					double distance = -Math.pow((this.getCoordX() - C.getX()),
							2.0) - Math.pow((this.getCoordY() - C.getY()), 2.0);
					listeDistances[i] = distance;
				} else {
					listeDistances[i] = 0.0;
				}
				i++;
			}

			// calcul de la distance minimale
			double distanceMini = 0.0;
			int indiceDistanceMini = 0;
			for (int m = 0; m < listeDistances.length; m++) {
				if (listeDistances[m] > distanceMini) {
					distanceMini = listeDistances[m];
					indiceDistanceMini = m;
				}
			}

			// si la distance n'est pas nulle c'est qu'il y a de la nourriture
			// donc le Neuneu mange
			if (indiceDistanceMini < 0) {
				this.manger(listeCasesAdj.get(indiceDistanceMini));
				aMange = true;
				// le Neuneu se deplace sur la case sur laquelle il vient de
				// manger
				this.setCoord(listeCasesAdj.get(indiceDistanceMini).getX(),
						listeCasesAdj.get(indiceDistanceMini).getY());
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
						// vient de se reproduire
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

	@Override
	public void dessinerObjet(Graphics g) {
		int taille = ZoneGraphique.TAILLE_CASELOFT;
		g.setColor(Color.BLUE);
		g.fillOval(this.getCoordX() * taille, this.getCoordY() * taille,
				taille, taille);

	}

}
