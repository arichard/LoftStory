import java.awt.Color;
import java.awt.Graphics;

public class Nourriture implements ObjetDessinable {

	protected String type;
	protected int qteEnergetique;
	protected CaseLoft coord;

	public Nourriture() {
		this.type = "";
		this.qteEnergetique = 0;
		
	}

	public Nourriture(String type, int qteEnergetique, CaseLoft place) {
		this.type = type;
		this.qteEnergetique = qteEnergetique;
		this.coord = coord;
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

	
	public CaseLoft getCoord() {
		return coord;
	}
	
	/**
	 * Pour modifier les coordonnées de la nourriture
	 */
	public void setCoord(int coordX, int coordY) {
		this.coord.setX(coordX);
		this.coord.setY(coordY);
	}

	/**
	 * Pour récupérer la position X de la nourriture
	 */
	public int getCoordX() {
		return getCoord().getX();
	}

	/**
	 * Pour récupérer la position Y de la nourriture
	 */
	public int getCoordY() {
		return getCoord().getY();
	}
	
	@Override
	public void dessinerObjet(Graphics g) {
		// TODO Auto-generated method stub
		
		int taille = ZoneGraphique.TAILLE_CASELOFT;
		g.setColor(Color.GREEN);
		g.fillRect(this.getCoordX() * taille, this.getCoordY() * taille,
				taille, taille);
		
	}

}
