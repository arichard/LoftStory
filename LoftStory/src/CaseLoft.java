import java.util.LinkedList;


public class CaseLoft {
	
	private LinkedList<Neuneu> populationCase;
	private LinkedList<Nourriture> presenceNourriture;
	private int x;
	private int y;
	
	public CaseLoft() {
		populationCase = new LinkedList<Neuneu>();
		presenceNourriture = new LinkedList<Nourriture>();
		this.x = 0;
		this.y = 0;
			}
	
		
	public CaseLoft(LinkedList<Neuneu> populationCase,
			LinkedList<Nourriture> presenceNourriture, int x, int y) {
		
		this.populationCase = populationCase;
		this.presenceNourriture = presenceNourriture;
		this.x = x;
		this.y = y;
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


	public void ajouterNourriture(Nourriture N) {
		presenceNourriture.add(N);
	
	}
	
	public void retirerNourriture(Nourriture N) {
		presenceNourriture.remove(N);
	
	}
	
	public void ajouterNeuneu(Neuneu N) {
		populationCase.add(N);
			
	}
	
	public void retirerNeuneu(Neuneu N) {
		populationCase.remove(N);
			
	}
	
	
}