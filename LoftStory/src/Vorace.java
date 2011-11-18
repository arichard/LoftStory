import java.awt.Graphics;
import java.util.LinkedList;

public class Vorace extends Neuneu {

	/**
	 * Constructeur par d�faut
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
		Vorace babyVorace = new Vorace(idBaby, energieBaby, energieDefautBaby,
				presenceLoftBaby, coordBaby);
		this.getCoord().getLoft().introduireNeuneu(babyVorace);
		babyVorace.setCoord(this.getCoordX(), this.getCoordY());
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
		LinkedList<CaseLoft> listeNourriture = new LinkedList<CaseLoft>();
		do {
			// liste la nourriture � proximit�
			for (CaseLoft C : listeCasesAdj) {
				if(0<C.getPresenceNourriture().size()){
					listeNourriture.add(C);
				}
			}
			// cherche la plus proche
			for(CaseLoft C : listeNourriture) {
				double distance = sqrt(pow((this.getCoordX()-C.getX()),2) + pow((this.getCoordY()-C.getY()),2));
			}
		}while();

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
