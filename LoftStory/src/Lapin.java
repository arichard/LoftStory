import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Lapin extends Neuneu {

	/**
	 * Constructeur par d�faut
	 */
	public Lapin() {
		super();
	}

	/**
	 * Constructeur
	 */
	public Lapin(int id, int energie, int energieDefaut, boolean presenceLoft,
			CaseLoft coord) {
		super(id, energie, energieDefaut, presenceLoft, coord);
	}

	/**
	 * (re)D�finition de la classe seReproduire
	 */
	public void seReproduire(Neuneu N) {
		// Reproduction = consommation d'�nergie pour les Neuneus
		this.setEnergie(this.getEnergie() - 2);
		N.setEnergie(N.getEnergie() - 2);
		// D�finition des attributs du b�b�
		int idBaby = 0;
		int energieBaby = this.getEnergieDefaut();
		int energieDefautBaby = this.getEnergieDefaut();
		boolean presenceLoftBaby = false;
		CaseLoft coordBaby = new CaseLoft(-1, -1, this.getCoord().getLoft());
		// Instanciation du b�b�
		Lapin babyLapin = new Lapin(idBaby, energieBaby, energieDefautBaby,
				presenceLoftBaby, coordBaby);
		this.getCoord().getLoft().introduireNeuneu(babyLapin);
		babyLapin.setCoord(this.getCoordX(), this.getCoordY());
	}

	/**
	 * (re)D�finition de la classe seComporter
	 */
	public void seComporter() {
		// instanciation des variables n�cessaires
		boolean aMange = false, aReprodui = false;

		// il regarde d'abord s'il y a un Neuneu sur sa case
		// si oui il se reproduit et se d�place
		if (0 < this.getCoord().getPopulationCase().size()) {
			this.seReproduire(this.getCoord().getPopulationCase().get(0));
			aReprodui = true;
			this.seDeplacer();
		}

		// sinon sur les cases adjacentes
		int k = 0;
		LinkedList<CaseLoft> listeCasesAdj = new LinkedList<CaseLoft>();
		listeCasesAdj = this.getCoord().casesAdj();
		while (k < listeCasesAdj.size()) {
			if (aReprodui == false) {
				if (0 < listeCasesAdj.get(k).getPopulationCase().size()
						&& aReprodui == false) {
					// le Neuneu se reproduit
					this.seReproduire(listeCasesAdj.get(k).getPopulationCase()
							.get(0));
					aReprodui = true;
					// le Neuneu se d�place sur la case sur laquelle il vient de
					// se reproduire
					this.setCoord(listeCasesAdj.get(k).getX(), listeCasesAdj
							.get(k).getY());
				}
			}
			k++;
		}

		// s'il ne s'est pas reprodui, il va chercher � manger
		if (aReprodui == false) {
			// d'abord sur sa case
			if (0 < this.getCoord().getPresenceNourriture().size()) {
				this.manger(this.getCoord());
				aMange = true;
				this.seDeplacer();
			}

			// sinon sur les cases adjacentes
			int i = 0;
			while (i < listeCasesAdj.size()) {
				if (aMange == false) {
					if (0 < listeCasesAdj.get(i).getPresenceNourriture().size()
							&& aMange == false) {
						// le Neuneu mange
						this.manger(listeCasesAdj.get(i));
						aMange = true;
						// le Neuneu se d�place sur la case sur laquelle il
						// vient de
						// manger
						this.setCoord(listeCasesAdj.get(i).getX(),
								listeCasesAdj.get(i).getY());
					}
				}
				i++;
			}
		}

		// s'il n'a ni mang� ni ne s'est reproduit, il se d�place
		if (aMange == false && aReprodui == false) {
			this.seDeplacer();
		}
	}

	@Override
	public void dessinerObjet(Graphics g) {
		int taille = ZoneGraphique.TAILLE_CASELOFT;
		g.setColor(Color.PINK);
		g.fillOval(this.getCoordX() * taille, this.getCoordY() * taille,
				taille, taille);

	}

}
