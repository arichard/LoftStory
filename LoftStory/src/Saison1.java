import java.util.LinkedList;

public class Saison1 {

	/**
	 * Méthode utilisée par le main pour lancer la saison
	 */
	public void primeTime() throws InterruptedException {
		
		// initialisation du Loft
		int hLoft = 25;
		int lLoft = 25;
		ZoneGraphique zone = new ZoneGraphique("Mon premier loft", lLoft, hLoft);
		Loft loft1 = new Loft(lLoft, hLoft, zone);
		zone.ajouterObjet(loft1);
		
		// initialisation de la CaseLoft "dehors"
		CaseLoft X = new CaseLoft(-10, -10, loft1);
		
		// initialisation des Neuneus
		LinkedList<Neuneu> L = new LinkedList<Neuneu>();
		int nombreLofteurs = 10;
		float proportionErratique = .6f;
		float proportionVorace = .1f;
		float proportionCannibale = .2f;
		float proportionLapin = .1f;

		int EnergieParDefaut = 10;
		int nombreErratique = (int) (nombreLofteurs * proportionErratique);
		int nombreVorace = (int) (nombreLofteurs * proportionVorace);
		int nombreCannibale = (int) (nombreLofteurs * proportionCannibale);
		int nombreLapin = (int) (nombreLofteurs * proportionLapin);

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
		
		Lapin[] lapin = new Lapin[nombreLapin];
		for (int l = 0; l < nombreLapin; l++) {
			lapin[l] = new Lapin(l, EnergieParDefaut, EnergieParDefaut, false,
					X);
			L.add(lapin[l]);
		}
		
		// initialisation de la Nourriture
		LinkedList<Nourriture> NourritureLoft = new LinkedList<Nourriture>();
		int quantiteNourriture = (nombreErratique + nombreVorace + nombreCannibale + nombreLapin)*2;
		
		String[] typeNourriture = {"Viande", "Poisson", "Fruits", "Biscuits", "Légumes"};
		int[] qteEnergieNourriture = {15,10,10,3,12};
		Nourriture[] bouffe = new Nourriture[quantiteNourriture];
		
		for (int b = 0; b < quantiteNourriture; b++) {
			int random = (int) (Math.random() * (5 - 1)) + 1;
			bouffe[b] = new Nourriture(typeNourriture[random], qteEnergieNourriture[random], X);
			NourritureLoft.add(bouffe[b]);
		}

		// remplissage du Loft avec les Neuneus et la Nourriture
		loft1.remplissageAleatoire(L, NourritureLoft);
		Thread.sleep(2000);

		// lancement des tours de jeu
		for(int time=0; time<20; time++){
			loft1.lancerTourDeJeu();
			Thread.sleep(500);
		};
		
		System.out.println("Fin de la saison 1 !");

	}
	
	public static void main(String[] args) throws InterruptedException {
		new Saison1().primeTime();
	}

}
