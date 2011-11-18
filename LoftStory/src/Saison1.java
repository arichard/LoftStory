import java.util.LinkedList;

//package com.objet.lofteurs;

public class Saison1 {

	public static int nombreLofteurs = 4;
	public static int tailleLoft = 30;
	public static float proportionErratique = .75f;
	public static float proportionVorace = .25f;
	public static float proportionCannibale = 0f;
	
	public static LinkedList<Neuneu> L;
	Loft loft1=new Loft();
	CaseLoft X = new CaseLoft (-1,-1, loft1);
	Neuneu Samantha = new Neuneu(10,false,X);
	Neuneu Brandon = new Neuneu(10,false,X);
	Neuneu Ashley = new Neuneu(10,false,X);
	Neuneu Steeve = new Neuneu(10,false,X);
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Saison1().primeTime();
	}
	
	public void primeTime() {
		ZoneGraphique zone = new ZoneGraphique("Mon premier loft");
		Loft loft = new Loft(tailleLoft,zone);
		loft.remplissageAleatoire(L);
		zone.ajouterObjet(loft);
		
		
		
		for (int i=0 ; i<nombreLofteurs ; i++) {
			double x = Math.random();
			if (x<proportionVorace) {
				loft.add(new Vorace(loft,
						(int)(Math.random()*29),
						(int)(Math.random()*29),
						3));
			}
			else {
				x -= proportionVorace;
				if (x<proportionErratique) {
					loft.add(new Erratique(loft,
							(int)(Math.random()*29),
							(int)(Math.random()*29)));
				}
				else {
					x -= proportionErratique;
					if (x<proportionCannibale) {
						loft.add(new Cannibale(loft,
						(int)(Math.random()*29),
						(int)(Math.random()*29),
						5));
					}
				}
			}
		}
		
		loft.lancerTourDeJeu();
	}

}