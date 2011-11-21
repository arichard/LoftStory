import java.util.LinkedList;

public class CaseLoft {

	protected LinkedList<Neuneu> populationCase;
	protected LinkedList<Nourriture> presenceNourriture;
	protected int x;
	protected int y;
	protected Loft loft1;

	public CaseLoft() {
		this.populationCase = new LinkedList<Neuneu>();
		this.presenceNourriture = new LinkedList<Nourriture>();
		this.x = 0;
		this.y = 0;
		this.loft1 = new Loft();
	}

	public CaseLoft(LinkedList<Neuneu> populationCase,
			LinkedList<Nourriture> presenceNourriture, int x, int y, Loft loft1) {

		this.populationCase = populationCase;
		this.presenceNourriture = presenceNourriture;
		this.x = x;
		this.y = y;
		this.loft1 = loft1;
	}

	public CaseLoft(int x, int y, Loft loft1) {

		this.populationCase = new LinkedList<Neuneu>();
		this.presenceNourriture = new LinkedList<Nourriture>();
		this.x = x;
		this.y = y;
		this.loft1 = loft1;
	}

	public LinkedList<Neuneu> getPopulationCase() {
		return populationCase;
	}

	public void setPopulationCase(LinkedList<Neuneu> populationCase) {
		this.populationCase = populationCase;
	}

	public LinkedList<Nourriture> getPresenceNourriture() {
		return presenceNourriture;
	}

	public void setPresenceNourriture(LinkedList<Nourriture> presenceNourriture) {
		this.presenceNourriture = presenceNourriture;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Loft getLoft() {
		return loft1;
	}

	public void setLoft(Loft loft1) {
		this.loft1 = loft1;
	}

	public void ajouterNourriture(Nourriture N) {
		presenceNourriture.add(N);
		// on met à jour ses coordonnées
		N.setCoord(this.getX(), this.getY());
	}

	public void retirerNourriture(Nourriture N) {
		presenceNourriture.remove(N);
		// on met à jour les coordonnées du Neuneu
		N.setCoord(-1, -1);
	}

	public void ajouterNeuneu(Neuneu N) {
		// on ajoute le Neuneu à la liste des Neuneus sur la case
		populationCase.add(N);
		// on met son attribut presenceLoft comme vrai
		N.setPresenceLoft(true);
		// on met à jour ses coordonnées
		N.setCoord(this.getX(), this.getY());
	}

	public void retirerNeuneu(Neuneu N) {
		populationCase.remove(N);
		N.setPresenceLoft(false);
		// on met à jour les coordonnées du Neuneu
		N.setCoord(-1, -1);
	}

	public LinkedList<CaseLoft> casesAdj() {

		LinkedList<CaseLoft> listeCasesAdj = new LinkedList<CaseLoft>();

		// -1 ; -1
		int bX = this.getX() - 1, bY = this.getY() - 1;
		if ((bX >= 0 && bX < this.loft1.getW())
				&& (bY >= 0 && bY < this.loft1.getH())) {
			CaseLoft B = this.loft1.getMaison()[bX][bY];
			listeCasesAdj.add(B);
		}

		// -1 ; -0
		int cX = this.getX() - 1, cY = this.getY();
		if ((cX >= 0 && cX < this.loft1.getW())
				&& (cY >= 0 && cY < this.loft1.getH())) {
			CaseLoft C = this.loft1.getMaison()[cX][cY];
			listeCasesAdj.add(C);
		}

		// -1 ; +1
		int dX = this.getX() - 1, dY = this.getY() + 1;
		if ((dX >= 0 && dX < this.loft1.getW())
				&& (dY >= 0 && dY < this.loft1.getH())) {
			CaseLoft D = this.loft1.getMaison()[dX][dY];
			listeCasesAdj.add(D);
		}

		// 0 ; +1
		int eX = this.getX(), eY = this.getY() + 1;
		if ((eX >= 0 && eX < this.loft1.getW())
				&& (eY >= 0 && eY < this.loft1.getH())) {
			CaseLoft E = this.loft1.getMaison()[eX][eY];
			listeCasesAdj.add(E);
		}

		// +1 ; +1
		int fX = this.getX() + 1, fY = this.getY() + 1;
		if ((fX >= 0 && fX < this.loft1.getW())
				&& (fY >= 0 && fY < this.loft1.getH())) {
			CaseLoft F = this.loft1.getMaison()[fX][fY];
			listeCasesAdj.add(F);
		}

		// +1 ; 0
		int gX = this.getX() + 1, gY = this.getY();
		if ((gX >= 0 && gX < this.loft1.getW())
				&& (gY >= 0 && gY < this.loft1.getH())) {
			CaseLoft G = this.loft1.getMaison()[gX][gY];
			listeCasesAdj.add(G);
		}

		// +1 ; -1
		int hX = this.getX() + 1, hY = this.getY() - 1;
		if ((hX >= 0 && hX < this.loft1.getW())
				&& (hY >= 0 && hY < this.loft1.getH())) {
			CaseLoft H = this.loft1.getMaison()[hX][hY];
			listeCasesAdj.add(H);
		}

		// 0 ; -1
		int iX = this.getX(), iY = this.getY() - 1;
		if ((iX >= 0 && iX < this.loft1.getW())
				&& (iY >= 0 && iY < this.loft1.getH())) {
			CaseLoft I = this.loft1.getMaison()[iX][iY];
			listeCasesAdj.add(I);
		}

		return listeCasesAdj;

	}
}
