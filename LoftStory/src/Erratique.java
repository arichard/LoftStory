import java.awt.Graphics;
import java.util.LinkedList;

public class Erratique extends Neuneu {

	/**
	 * Constructeur par d�faut
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
		Erratique babyErratique = new Erratique(idBaby, energieBaby,
				energieDefautBaby, presenceLoftBaby, coordBaby);
		this.getCoord().getLoft().introduireNeuneu(babyErratique);
		babyErratique.setCoord(this.getCoordX(), this.getCoordY());
	}

	/**
	 * (re)D�finition de la classe seComporter
	 */
	public void seComporter() {
		// instanciation des variables n�cessaires
		boolean aMange = false, aReprodui = false;
		LinkedList<CaseLoft> listeCasesAdj = new LinkedList<CaseLoft>();
		listeCasesAdj = this.getCoord().casesAdj();

		// parcourt des cases pour manger
		int i = 0;
		do {
			// s'il y a de la nourriture disponible et qu'il n'a pas mang�
			// il va chercher � manger
			if (0 < listeCasesAdj.get(i).getPresenceNourriture().size()
					&& aMange == false) {
				// le Neuneu mange
				this.manger(listeCasesAdj.get(i));
				aMange = true;
				// le Neuneu se d�place sur la case sur laquelle il vient de
				// manger
				this.setCoord(listeCasesAdj.get(i).getX(), listeCasesAdj.get(i)
						.getY());
			}
			i++;
		} while (i <= listeCasesAdj.size() || aMange == false);

		// s'il n'a pas mang�, il va chercher � se reproduire
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
					// le Neuneu se d�place sur la case sur laquelle il vient de
					// se reproduire
					this.setCoord(listeCasesAdj.get(k).getX(), listeCasesAdj
							.get(k).getY());
				}
				k++;
			} while (k <= listeCasesAdj.size() || aReprodui == false);
		}

		// s'il n'a ni mang� ni ne s'est reproduit, il se d�place
		if (aMange == false && aReprodui == false) {
			this.seDeplacer();
		}
	}

	@Override
	public void dessinerObjet(Graphics g) {
		// TODO Auto-generated method stub

	}

}
