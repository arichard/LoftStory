
public class Nourriture {

	private String type;
	private int qteEnergetique;
	
	
	public Nourriture() {
		this.type = "";
		this.qteEnergetique = 0;
	}
	
	public Nourriture(String type, int qteEnergetique) {
		this.type = type;
		this.qteEnergetique = qteEnergetique;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQteEnergetique() {
		return qteEnergetique;
	}

	public void setQteEnergetique(int qteEnergetique) {
		this.qteEnergetique = qteEnergetique;
	}
	
		
}
