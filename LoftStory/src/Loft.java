import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import sun.java2d.loops.DrawLine;

public class Loft implements ObjetDessinable {

	protected int w; // taille horizontale de la matrice
	protected int h; // taille verticale de la matrice
	protected int tailleLoft; // nombre de cases
	protected CaseLoft[][] maison;
	protected ZoneGraphique affichage;

	// constructeurs
	public Loft() {
		this.setW(0);
		this.setH(0);
		this.setMaison(new CaseLoft[0][0]);
		this.setTailleLoft(0);
		this.affichage = new ZoneGraphique();
	}

	public Loft(int w, int h, ZoneGraphique z) {
		this.w = w;
		this.h = h;
		this.setMaison(new CaseLoft[w][h]);
		this.setTailleLoft(w * h);
		this.affichage = z;

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
				// liste temporaire pour Žviter les
				// "ConcurrentModificationException"
				// qui apparaissent lorsqu'on modifie la liste en cours
				// d'itŽration
				// cad la modification de la population d'une CaseLoft
				// alors qu'on est en train de regarder cette population dans
				// une boucle for
				LinkedList<Neuneu> listeTemp = new LinkedList<Neuneu>();
				for (Neuneu N : A.getPopulationCase()) {
					listeTemp.add(N);
				}
				for (Neuneu N : listeTemp) {
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
					this.affichage.ajouterObjet(N);
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
		this.affichage.retirerObjet(N);
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
		int taille = ZoneGraphique.TAILLE_CASELOFT;
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, w * taille, h * taille);
		for (int i = 0; i < w; i++) {
			g.setColor(Color.GRAY);
			g.drawLine(i * taille, 0, i * taille, h * taille);
		}
		for (int j = 0; j < h; j++) {
			g.setColor(Color.GRAY);
			g.drawLine(0, j * taille, w * taille, j * taille);
		}

	}

}
