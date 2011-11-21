import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Erratique extends Neuneu {

	/**
	 * Constructeur par défaut
	 */
	public Erratique() {
		super();
	}

	/**
	 * Constructeur
	 */
	public Erratique(int id, int energie, int energieDefaut,
			boolean presenceLoft, CaseLoft coord) {
		super(id, energie, energieDefaut, presenceLoft, coord);
	}

	/**
	 * (re)Définition de la classe seReproduire
	 */
	public void seReproduire(Neuneu N) {
		// Reproduction = consommation d'énergie pour les Neuneus
		this.setEnergie(this.getEnergie() - 4);
		N.setEnergie(N.getEnergie() - 4);

		// Définition des attributs du bébé
		int idBaby = 0;
		int energieBaby = this.getEnergieDefaut();
		int energieDefautBaby = this.getEnergieDefaut();
		boolean presenceLoftBaby = false;
		CaseLoft coordBaby = new CaseLoft(-1, -1, this.getCoord().getLoft());

		// Instanciation du bébé
		Erratique babyErratique = new Erratique(idBaby, energieBaby,
				energieDefautBaby, presenceLoftBaby, coordBaby);
		this.getCoord().getLoft().introduireNeuneu(babyErratique);
		//babyErratique.setCoord(this.getCoordX(), this.getCoordY());
	}

	/**
	 * (re)Définition de la classe seComporter
	 */
	public void seComporter() {
		// instanciation des variables nécessaires
		boolean aMange = false, aReprodui = false;

		// il regarde d'abord s'il y a de la nourriture sur sa case
		// si oui il mange et se déplace
		if (0 < this.getCoord().getPresenceNourriture().size()) {
			this.manger(this.getCoord());
			aMange = true;
			this.seDeplacer();
		}

		// sinon sur les cases adjacentes
		int i = 0;
		LinkedList<CaseLoft> listeCasesAdj = new LinkedList<CaseLoft>();
		listeCasesAdj = this.getCoord().casesAdj();
		while (i < listeCasesAdj.size()) {
			if (aMange == false) {
				if (0 < listeCasesAdj.get(i).getPresenceNourriture().size()
						&& aMange == false) {
					// le Neuneu mange
					this.manger(listeCasesAdj.get(i));
					aMange = true;
					// le Neuneu se déplace sur la case sur laquelle il vient de
					// manger
					this.setCoord(listeCasesAdj.get(i).getX(), listeCasesAdj
							.get(i).getY());
				}
			}
			i++;
		}

		// s'il n'a pas mangé, il va chercher à se reproduire
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
						// le Neuneu se déplace sur la case sur laquelle il
						// vient de
						// se reproduire
						this.setCoord(listeCasesAdj.get(k).getX(),
								listeCasesAdj.get(k).getY());
					}
				}
				k++;
			}
		}

		// s'il n'a ni mangé ni ne s'est reproduit, il se déplace
		if (aMange == false && aReprodui == false) {
			this.seDeplacer();
		}
		
		this.getCoord().getLoft().getAffichage().repaint();
	}

	@Override
	public void dessinerObjet(Graphics g) {
		int taille = ZoneGraphique.TAILLE_CASELOFT;
		g.setColor(Color.BLACK);
		g.fillOval(this.getCoordX() * taille, this.getCoordY() * taille,
				taille, taille);

	}

}
