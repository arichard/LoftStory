import java.awt.Graphics;

/**
 * interface des objets dessinables ; ils devront simplement implanter une methode de dessin
 * a partir d'un contexte graphique passe par l'application
 */
public interface ObjetDessinable {
	/**
	 * fonction de dessin ; a surcharger
	 * @param g le contexte graphique
	 */
	public void dessinerObjet(Graphics g);

}