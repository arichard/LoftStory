import java.awt.Graphics;
import java.util.LinkedList;

public class Loft implements ObjetDessinable {

	protected int w; // taille horizontale de la matrice
	protected int h; // taille verticale de la matrice
	protected int tailleLoft; // nombre de cases
	protected CaseLoft[][] maison;

	// constructeurs
	public Loft() {
		this.setW(0);
		this.setH(0);
		this.setMaison(new CaseLoft[0][0]);
		this.setTailleLoft(0);
	}

	public Loft(int w, int h) {
		this.w = w;
		this.h = h;
		this.setMaison(new CaseLoft[w][h]);
		this.setTailleLoft(w * h);

		// remplissage de la maison de CaseLoft
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				this.maison[i][j] = new CaseLoft(i, j, this);
			}
		}
	}

	// getters et setters
	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public CaseLoft[][] getMaison() {
		return maison;
	}

	public void setMaison(CaseLoft[][] maison) {
		this.maison = maison;
	}

	public int getTailleLoft() {
		return tailleLoft;
	}

	public void setTailleLoft(int tailleLoft) {
		this.tailleLoft = tailleLoft;
	}

	// mise à jour de la population de chaque case, vérifier si aucun Neuneu
	// n'est mort
	public void majPopulation() {

		for (int i = 0; i < this.getW() - 1; i++) {
			for (int j = 0; j < this.getH() - 1; j++) {
				CaseLoft A = this.getMaison()[i][j];
				LinkedList<Neuneu> L = new LinkedList<Neuneu>();
				for (Neuneu N : A.getPopulationCase()) {
					if (N.energieSuffisante() == false) {
						L.add(N);
					}
				}
				for (Neuneu k : L) {
					A.retirerNeuneu(k);
				}
			}
		}

	}

	public void lancerTourDeJeu() {

		for (int i = 1; i < this.getW(); i++) {
			for (int j = 1; j < this.getH(); j++) {
				CaseLoft A = this.getMaison()[i][j];
				for (Neuneu N : A.getPopulationCase()) {
					N.seComporter();
				}
			}
		}
		this.majPopulation();

	}

	public void introduireNeuneu(Neuneu N) {

		for (int i = 0; i < this.getW() - 1; i++) {
			for (int j = 0; j < this.getH() - 1; j++) {
				CaseLoft A = this.getMaison()[i][j];
				if (A.getPopulationCase().size() < 1) {
					A.ajouterNeuneu(N);
				}
			}
		}

		this.majPopulation();
	}

	public void virerNeuneu(Neuneu N) {

		int X = N.getCoordX();
		int Y = N.getCoordY();
		CaseLoft A = maison[X][Y];
		A.retirerNeuneu(N);
		this.majPopulation();

	}

	// méthode de remplissage aléatoire du loft avec une liste donnée de Neuneus
	public void remplissageAleatoire(LinkedList<Neuneu> L) {
		for (Neuneu N : L) {
			this.introduireNeuneu(N);
		}
	}

	@Override
	public void dessinerObjet(Graphics g) {
		// TODO Auto-generated method stub

	}

}
