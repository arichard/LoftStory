import java.awt.Graphics;
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

		// parcourt des cases pour manger
		int i = 0;
		do {
			int j = 0;
			// s'il y a de la nourriture disponible et qu'il n'a pas mangé
			// il va chercher à manger
			while (j <= listeCasesAdj.get(i).getPresenceNourriture().size()
					|| aMange == false) {
				if (listeCasesAdj.get(i).getPresenceNourriture().get(j)
						.getQteEnergetique() > 0) {
					this.manger();
					aMange = true;
				}
				j++;
			}
			i++;
		} while (i <= listeCasesAdj.size() || aMange == false);

		// s'il n'a pas mangé, il va chercher à se reproduire
		if (aMange == false) {
			int k = 0;
			do {
				int m = 0;
				// s'il y a un Neuneu disponible, il va se reproduire
				while (m <= listeCasesAdj.get(k).getPopulationCase().size()) {
					this.seReproduire(listeCasesAdj.get(k).getPopulationCase()
							.get(m));
				}
			} while (k < listeCasesAdj.size() || aReprodui == false);
		}
	}

	@Override
	public void dessinerObjet(Graphics g) {
		// TODO Auto-generated method stub

	}

}
