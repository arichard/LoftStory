import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;

public class Vorace extends Neuneu {

	/**
	 * Constructeur par défaut
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
	 * (re)Définition de la classe seReproduire
	 */
	public void seReproduire(Neuneu N) {
		// Reproduction = consommation d'énergie pour les Neuneus
		this.setEnergie(this.getEnergie() - 2);
		N.setEnergie(N.getEnergie() - 2);
		// Définition des attributs du bébé
		int idBaby = 0;
		int energieBaby = this.getEnergieDefaut();
		int energieDefautBaby = this.getEnergieDefaut();
		boolean presenceLoftBaby = false;
		CaseLoft coordBaby = new CaseLoft(-1, -1, this.getCoord().getLoft());
		// Instanciation du bébé
		Vorace babyVorace = new Vorace(idBaby, energieBaby, energieDefautBaby,
				presenceLoftBaby, coordBaby);
		this.getCoord().getLoft().introduireNeuneu(babyVorace);
		babyVorace.setCoord(this.getCoordX(), this.getCoordY());
	}

	/**
	 * (re)Définition de la classe seComporter
	 */
	public void seComporter() {
		// instanciation des variables nécessaires
		boolean aMange = false, aReprodui = false;
		LinkedList<CaseLoft> listeCasesAdj = new LinkedList<CaseLoft>();
		listeCasesAdj = this.getCoord().casesAdj();

		// il regarde d'abord s'il y a de la nourriture sur sa case
		// si oui il mange et se déplace
		if (0 < this.getCoord().getPresenceNourriture().size()) {
			this.manger(this.getCoord());
			aMange = true;
			this.seDeplacer();
		}

		// si non il regarde la nourriture à proximité
		if (aMange == false) {
			// liste la nourriture
			LinkedList<Double> listeDistances = new LinkedList<Double>();
			for (CaseLoft C : listeCasesAdj) {
				if (0 < C.getPresenceNourriture().size()) {
					double distance = -Math.pow((this.getCoordX() - C.getX()),
							2.0) - Math.pow((this.getCoordY() - C.getY()), 2.0);
					listeDistances.add(distance);
				} else {
					listeDistances.add(0.0);
				}
			}

			// calcul distance mini sur laquelle se trouve de la nourriture
			Object obj = Collections.min(listeDistances);
			Integer indiceDistanceMini = (Integer) obj;
			// si la distance n'est pas nulle c'est qu'il y a de la nourriture
			// donc le Neuneu mange
			if (indiceDistanceMini < 0) {
				this.manger(listeCasesAdj.get(indiceDistanceMini));
				aMange = true;
				// le Neuneu se déplace sur la case sur laquelle il vient de
				// manger
				this.setCoord(listeCasesAdj.get(indiceDistanceMini).getX(),
						listeCasesAdj.get(indiceDistanceMini).getY());
			}
		}

		// s'il n'a pas mangé, il va chercher à se reproduire
		if (aMange == false) {
			int k = 0;
			do {
				// s'il y a un Neuneu disponible, il va se reproduire
				if (0 < listeCasesAdj.get(k).getPopulationCase().size()
						&& aReprodui == false) {
					// le Neuneu se reproduit
					this.seReproduire(listeCasesAdj.get(k).getPopulationCase()
							.get(0));
					aReprodui = true;
					// le Neuneu se déplace sur la case sur laquelle il vient de
					// se reproduire
					this.setCoord(listeCasesAdj.get(k).getX(), listeCasesAdj
							.get(k).getY());
				}
				k++;
			} while (k <= listeCasesAdj.size() || aReprodui == false);
		}

		// s'il n'a ni mangé ni ne s'est reproduit, il se déplace
		if (aMange == false && aReprodui == false) {
			this.seDeplacer();
		}
	}

	@Override
	public void dessinerObjet(Graphics g) {
		// TODO Auto-generated method stub

	}

}
