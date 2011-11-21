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
		LinkedList<Nourriture> NourritureLoft = new LinkedList<Nourriture>();
		int EnergieParDefaut = 10;
		int nombreErratique = (int) (nombreLofteurs * proportionErratique);
		int nombreVorace = (int) (nombreLofteurs * proportionVorace);
		int nombreCannibale = (int) (nombreLofteurs * proportionCannibale);
		int nombreLapin = (int) (nombreLofteurs * proportionLapin);
		int quantiteNourriture = (nombreErratique + nombreVorace + nombreCannibale + nombreLapin)*2;

		ZoneGraphique zone = new ZoneGraphique("Mon premier loft", lLoft, hLoft);
		Loft loft1 = new Loft(lLoft, hLoft, zone);
		zone.ajouterObjet(loft1);
		CaseLoft X = new CaseLoft(-1, -1, loft1);

		//Création de la linkedList de nos Neuneus
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

		Lapin[] lap = new Lapin[nombreLapin];
		for (int la = 0; la < nombreCannibale; la++) {
			lap[la] = new Lapin(la, EnergieParDefaut, EnergieParDefaut, false,
					X);
			L.add(lap[la]);
		}
		
		// création de la liste de nourriture à ajouter
		String[] typeNourriture = {"Viande", "Poisson", "Fruits", "Biscuits", "Légumes"};
		int[] qteEnergieNourriture = {15,10,10,3,12};
		Nourriture[] bouffe = new Nourriture[quantiteNourriture];
		for (int b = 0; b < quantiteNourriture; b++) {
			int random = (int) (Math.random() * (5 - 1)) + 1;
			bouffe[b] = new Nourriture(typeNourriture[random], qteEnergieNourriture[random], X);
			NourritureLoft.add(bouffe[b]);
		}

		loft1.remplissageAleatoire(L, NourritureLoft);

		loft1.lancerTourDeJeu();

		/*
		 * LinkedList<Neuneu> M = new LinkedList<Neuneu>(); for (int i = 0; i <
		 * lLoft; i++) { for (int j = 0; j < hLoft; j++) { CaseLoft A =
		 * loft1.getMaison()[i][j]; M.addAll(A.getPopulationCase()); if
		 * (M.isEmpty() == true) { System.out .println(
		 * "LoftStory 1 c'est fini, rendez-vous trËs bientÙt pour la saison 2!"
		 * ); } else { loft1.lancerTourDeJeu(); }
		 * 
		 * } }
		 */

	}

}
