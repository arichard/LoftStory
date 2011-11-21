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
		System.out.println("Cannibales : " + nombreCannibale);
		System.out.println("Erratiques : " + nombreErratique);
		System.out.println("Lapins : " + nombreLapin);
		System.out.println("Voraces : " + nombreVorace);

		ZoneGraphique zone = new ZoneGraphique("Mon premier loft", lLoft, hLoft);
		Loft loft1 = new Loft(lLoft, hLoft, zone);
		zone.ajouterObjet(loft1);
		CaseLoft X = new CaseLoft(-1, -1, loft1);

		//
		Erratique[] e = new Erratique[nombreErratique];
		for (int i = 0; i < nombreErratique; i++) {
			e[i] = new Erratique(i, EnergieParDefaut, EnergieParDefaut, false,
					X);
			L.add(e[i]);
		}

		Vorace[] v = new Vorace[nombreVorace];
		for (int j = 0; j < nombreVorace; j++) {
			v[j] = new Vorace(j, EnergieParDefaut, EnergieParDefaut, false, X);
			L.add(v[j]);
		}

		Cannibale[] c = new Cannibale[nombreCannibale];
		for (int k = 0; k < nombreCannibale; k++) {
			c[k] = new Cannibale(k, EnergieParDefaut, EnergieParDefaut, false,
					X);
			L.add(c[k]);
		}

		Lapin[] la = new Lapin[nombreLapin];
		for (int l = 0; l < nombreCannibale; l++) {
			la[l] = new Lapin(l, EnergieParDefaut, EnergieParDefaut, false, X);
			L.add(la[l]);
		}

		loft1.remplissageAleatoire(L);

		/*
		 * for (Neuneu n : L) { zone.ajouterObjet(n); }
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
