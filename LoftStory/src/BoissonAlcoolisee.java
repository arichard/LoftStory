import java.awt.Color;
import java.awt.Graphics;


public class BoissonAlcoolisee extends Nourriture{
	
		
	public BoissonAlcoolisee(){
	super();
	}
	
	
	@Override
	public void dessinerObjet(Graphics g) {
		// TODO Auto-generated method stub
		
		int taille = ZoneGraphique.TAILLE_CASELOFT;
		g.setColor(Color.YELLOW);
		g.fillRect(this.getCoordX() * taille, this.getCoordY() * taille,
				taille, taille);
		
	}
}
