import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Loft implements ObjetDessinable {

	/**
	 * Attributs
	 */

	protected int w; // taille horizontale de la matrice
	protected int h; // taille verticale de la matrice
	protected int population;
	protected CaseLoft[][] maison;
	protected ZoneGraphique affichage;

	/**
	 * Constructeurs
	 */

	public Loft() {
		this.w = 0;
		this.h = 0;
		this.population = 0;
		this.setMaison(new CaseLoft[0][0]);
		this.affichage = new ZoneGraphique();
	}

	public Loft(int w, int h, ZoneGraphique z) {
		this.w = w;
		this.h = h;
		this.population = 0;
		this.setMaison(new CaseLoft[w][h]);
		this.affichage = z;

		// remplissage de la maison de CaseLoft
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				this.maison[i][j] = new CaseLoft(i, j, this);
			}
		}
	}

	/**
	 * Getters et Setters
	 */
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

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public CaseLoft[][] getMaison() {
		return maison;
	}

	public void setMaison(CaseLoft[][] maison) {
		this.maison = maison;
	}

	public ZoneGraphique getAffichage() {
		return affichage;
	}

	public void setAffichage(ZoneGraphique affichage) {
		this.affichage = affichage;
	}

	/**
	 * Mise a jour de la population du Loft
	 */
	public void majPopulation() {
		LinkedList<Neuneu> L = new LinkedList<Neuneu>();
		// on parcourt les cases du Jeu
		for (int i = 0; i < this.getW(); i++) {
			for (int j = 0; j < this.getH(); j++) {
				CaseLoft A = this.getMaison()[i][j];
				// pour chaque Neuneu de la case
				// on verifie son energie
				for (Neuneu N : A.getPopulationCase()) {
					if (N.energieSuffisante() == false) {
						L.add(N);
					}
				}
			}
		}
		// on enleve tous les Neuneus "morts"
		for (Neuneu k : L) {
			this.virerNeuneu(k);
		}
	}

	/**
	 * Lancement d'un tour de jeu
	 */
	public void lancerTourDeJeu() {
		
		LinkedList<Neuneu> listeTemp = new LinkedList<Neuneu>();
		
		// on parcourt les cases du Loft
		for (int i = 0; i < this.getW(); i++) {
			for (int j = 0; j < this.getH(); j++) {
				CaseLoft A = this.getMaison()[i][j];
				// on recupere la liste des Neuneus presents
				for (Neuneu N : A.getPopulationCase()) {
					listeTemp.add(N);
				}
			}
		}
		
		// pour chaque Neuneu present dans le Loft
		for (Neuneu N : listeTemp) {
			// on lance la methode seComporter
			N.seComporter();
			// on actualise
			N.getCoord().getLoft().getAffichage().repaint();
			// on met a jour la population du Loft
			this.majPopulation();
			// on actualise
			N.getCoord().getLoft().getAffichage().repaint();
		}
	}

	/**
	 * Insertion d'un Neuneu dans le Loft
	 */
	public void introduireNeuneu(Neuneu N) {

		boolean estInsere = false;
		int tailleMaxi = this.getH() * this.getW();

		// si on a pas atteint la population maximale
		if (this.getPopulation() < tailleMaxi) {
			do {
				// on choisit une CaseLoft au hasard
				Random randomX = new Random();
				Random randomY = new Random();
				CaseLoft A = this.getMaison()[randomX.nextInt(this.w)][randomY
						.nextInt(this.h)];
				// si la CaseLoft est vide
				if (A.getPopulationCase().size() < 1) {
					// on ajoute le Neuneu
					A.ajouterNeuneu(N);
					// on l'ajoute pour l'affichage
					this.affichage.ajouterObjet(N);
					// on met a jour la population du Loft
					this.setPopulation(this.getPopulation() + 1);
					estInsere = true;
				}
			} while (estInsere == false);
		} else {
			// sinon on l'enleve
			N.setCoord(-10, -10);
			N.setPresenceLoft(false);
		}
	}

	/**
	 * Retirer un Neuneu du Loft
	 */
	public void virerNeuneu(Neuneu N) {
		// on recupere les coordonnees de la CaseLoft de ce Neuneu
		int X = N.getCoordX();
		int Y = N.getCoordY();
		// on s'assure que le Neuneu est bien dans le Loft
		if (X >= 0 && Y >= 0) {
			CaseLoft A = maison[X][Y];
			// on enleve le Neuneu
			A.retirerNeuneu(N);
			// on le retire de l'affichage
			this.affichage.retirerObjet(N);
			// on met a jour la population du Loft
			this.setPopulation(this.getPopulation() - 1);
		}
		// sinon il est deja hors du Loft
	}

	/**
	 * Introduire de la Nourriture dans le Loft
	 */
	public void introduireNourriture(Nourriture N) {
		// on choisit une CaseLoft au hasard
		Random randomX = new Random();
		Random randomY = new Random();
		CaseLoft A = this.getMaison()[randomX.nextInt(this.w)][randomY
				.nextInt(this.h)];
		// on ajoute la nourriture
		A.ajouterNourriture(N);
		// on met a jour l'affichage
		this.affichage.ajouterObjet(N);
	}

	/**
	 * Remplir aleatoirement le Loft de Neuneus
	 */
	public void remplissageAleatoire(LinkedList<Neuneu> L,
			LinkedList<Nourriture> M) {
		for (Neuneu Ne : L) {
			this.introduireNeuneu(Ne);
		}
		for (Nourriture No : M) {
			this.introduireNourriture(No);
		}
		this.getAffichage().repaint();
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
