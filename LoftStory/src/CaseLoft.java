import java.util.LinkedList;

public class CaseLoft {

	private LinkedList<Neuneu> populationCase;
	private LinkedList<Nourriture> presenceNourriture;
	private int x;
	private int y;
	private Loft loft1;

	public CaseLoft() {
		populationCase = new LinkedList<Neuneu>();
		presenceNourriture = new LinkedList<Nourriture>();
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

	}

	public void retirerNourriture(Nourriture N) {
		presenceNourriture.remove(N);

	}

	public void ajouterNeuneu(Neuneu N) {
		populationCase.add(N);
		N.setPresenceLoft(true);

	}

	public void retirerNeuneu(Neuneu N) {
		populationCase.remove(N);
		N.setPresenceLoft(false);

	}

	public LinkedList<CaseLoft> casesAdj() {
		CaseLoft B = new CaseLoft();
		B.setX(this.getX() - 1);
		B.setY(this.getY() - 1);

		CaseLoft C = new CaseLoft();
		C.setX(this.getX() - 1);
		C.setY(this.getY());

		CaseLoft D = new CaseLoft();
		D.setX(this.getX() - 1);
		D.setY(this.getY() + 1);

		CaseLoft E = new CaseLoft();
		E.setX(this.getX());
		E.setY(this.getY() + 1);

		CaseLoft F = new CaseLoft();
		F.setX(this.getX() + 1);
		F.setY(this.getY() + 1);

		CaseLoft G = new CaseLoft();
		G.setX(this.getX() + 1);
		G.setY(this.getY());

		CaseLoft H = new CaseLoft();
		H.setX(this.getX() + 1);
		H.setY(this.getY() - 1);

		CaseLoft I = new CaseLoft();
		I.setX(this.getX());
		I.setY(this.getY() - 1);

		LinkedList<CaseLoft> J = new LinkedList<CaseLoft>();
		J.add(B);
		J.add(C);
		J.add(D);
		J.add(E);
		J.add(F);
		J.add(G);
		J.add(H);
		J.add(I);

		return J;

	}
}
