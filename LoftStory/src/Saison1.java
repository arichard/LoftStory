import java.util.LinkedList;

//package com.objet.lofteurs;

public class Saison1 {

	public static int nombreLofteurs = 10;
	public static int hLoft = 25;
	public static int lLoft = 25;
	public static float proportionErratique = .6f;
	public static float proportionVorace = .1f;
	public static float proportionCannibale = .2f;
	public static float proportionLapin = .1f;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Saison1().primeTime();
	}

	public void primeTime() {

		LinkedList<Neuneu> L = new LinkedList<Neuneu>();
		int EnergieParDefaut = 10;

		int nombreErratique = (int) (nombreLofteurs * proportionErratique);
		int nombreVorace = (int) (nombreLofteurs * proportionVorace);
		int nombreCannibale = (int) (nombreLofteurs * proportionCannibale);
		int nombreLapin = (int) (nombreLofteurs * proportionLapin);

		ZoneGraphique zone = new ZoneGraphique("Mon premier loft", lLoft, hLoft);
		Loft loft1 = new Loft(lLoft, hLoft, zone);
		zone.ajouterObjet(loft1);
		CaseLoft X = new CaseLoft(-1, -1, loft1);

		//
		Erratique[] e = new Erratique[nombreErratique];
		for (int i = 1; i < nombreErratique; i++) {
			e[i - 1] = new Erratique(i, 10, EnergieParDefaut, false, X);
			L.add(e[i - 1]);
		}

		Vorace[] v = new Vorace[nombreVorace];
		for (int j = 1; j < nombreVorace; j++) {
			v[j - 1] = new Vorace(j, 10, EnergieParDefaut, false, X);
			L.add(v[j - 1]);
		}

		Cannibale[] c = new Cannibale[nombreCannibale];
		for (int k = 1; k < nombreCannibale; k++) {
			c[k - 1] = new Cannibale(k, 10, EnergieParDefaut, false, X);
			L.add(c[k - 1]);
		}

		Lapin[] la = new Lapin[nombreLapin];
		for (int l = 1; l < nombreCannibale; l++) {
			la[l - 1] = new Lapin(l, 10, EnergieParDefaut, false, X);
			L.add(la[l - 1]);
		}

		loft1.remplissageAleatoire(L);

		/*
		for (Neuneu n : L) {
			zone.ajouterObjet(n);
		}
		*/

		// lancement d'un nombre limitŽ de tours de jeu
		for (int i = 0; i < 10000; i++) {
			loft1.lancerTourDeJeu();
		}
		System.out.println("Fin du Loft Saison 1 !");

		/*
		 * LinkedList<Neuneu> M = new LinkedList<Neuneu>(); for (int i = 0; i <
		 * lLoft; i++) { for (int j = 0; j < hLoft; j++) { CaseLoft A =
		 * loft1.getMaison()[i][j]; M.addAll(A.getPopulationCase()); if
		 * (M.isEmpty() == true) { System.out .println(
		 * "LoftStory 1 c'est fini, rendez-vous très bientôt pour la saison 2!"
		 * ); } else { loft1.lancerTourDeJeu(); }
		 * 
		 * } }
		 */

	}

}
