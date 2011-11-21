import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 * un panneau de dessin pour le loft
 */
class LoftPanel extends JPanel {
	/**
	 * reference sur la liste des objets a dessiner
	 */
	private LinkedList<ObjetDessinable> listeObjets;
	
	/**
	 * constructeur
	 * @param listeObjets reference sur la liste des objets (geree par la ZoneGraphique)
	 */
	public LoftPanel(LinkedList<ObjetDessinable> listeObjets) {
		this.listeObjets = listeObjets;
	}
	
	/**
	 * on redefinit la methode paint() : elle se contente d'appeler les methodes
	 * dessinerObjet() de la liste d'objets dessinables
	 */
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		// on redessine tout
		for (ObjetDessinable x : listeObjets) {
			x.dessinerObjet(g);
		}
	}
}