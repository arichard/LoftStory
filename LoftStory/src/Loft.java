import java.util.LinkedList;


public class Loft {
	
	private int w; //taille horizontale de la matrice
	private int h; //taille verticale de la matrice
	private CaseLoft maison[][];
	
	
	public Loft() {
		w = 0;
		h = 0;
		CaseLoft maison[][]= new CaseLoft [w][h];
	}
	
	public Loft(int w, int h) {
		this.w = w;
		this.h = h;
		CaseLoft maison[][]= new CaseLoft[w][h];
	}


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
	
		
	public void majPopulation(){
			
		for (int i; i<w;i++){
			for(int j; j<h;j++){
			CaseLoft A = maison[i][j];
			 LinkedList<Neuneu> L = new LinkedList<Neuneu>();
			          for(Neuneu N : A.getPopulationCase()){
			        	  if (N.energieSuffisante()==false){
			        		 L.add(N);
			        	  }
			          }
			          for (Neuneu k : L){
			          A.getPopulationCase().virerNeuneu(k); }    
			}
		}
			
	}
	
	public void lancerTourDeJeu(Loft Loft1){
		
		for (int i; i<w;i++){
			for(int j; j<h;j++){
			CaseLoft A = maison[i][j];
			          for(Neuneu N : A.getPopulationCase()){
			        	  N.seComporter();
			        	  }
			          }
			}
		Loft1.majPopulation();
		
		}
		
	
	public void introduireNeuneu(Neuneu N, Loft Loft1){
	
		for (int i; i<w;i++){
			for(int j; j<h;j++){
			CaseLoft A = maison[i][j];
				if(A.getPopulationCase().size()<1){
				A.getPopulationCase().ajouterNeuneu(N);
				}
			}
		}
			
		Loft1.majPopulation();
	}
		
	
	public void virerNeuneu(Neuneu N, Loft Loft1){
		
	int X=N.getCoordX();
	int Y=N.getCoordY();	
	CaseLoft A = maison[X][Y];
	A.getPopulationCase().retirerNeuneu(N);	
	Loft1.majPopulation();
	
	}
		
}
