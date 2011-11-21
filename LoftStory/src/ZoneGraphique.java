//package com.objet.lofteurs;

import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JFrame;

/**
 * une classe comportant une zone graphique dans laquelle on peut dessiner ; le
 * dessin est refait automatiquement par la classe Panel associee ; tous les
 * objets de type ObjetDessinable ajoutes a la liste sont redessines par un
 * appel a leur methode dessinerObjet(Graphics g)
 */
public class ZoneGraphique extends JFrame {

	/**
	 * la liste d'objets a dessiner
	 */
	LinkedList<ObjetDessinable> liste;

	/**
	 * definition par une constante de la taille d'une CaseLoft
	 */
	public static final int TAILLE_CASELOFT = 15;

	/**
	 * Constructeur
	 */
	public ZoneGraphique(String titre, int lLoft, int hLoft) {
		// appel au constructeur de base
		super(titre);

		// ajout d'une taille par defaut
		setSize(lLoft * TAILLE_CASELOFT + TAILLE_CASELOFT, hLoft
				* TAILLE_CASELOFT + TAILLE_CASELOFT);

		// creation de la liste d'objets
		liste = new LinkedList<ObjetDessinable>();

		// ajout d'un listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// creation du panneau
		LoftPanel a = new LoftPanel(liste);
		getContentPane().add(a);

		setVisible(true);
	}

	public ZoneGraphique() throws HeadlessException {
		super();
	}

	/**
	 * ajout d'un objet dans la zone graphique
	 */
	void ajouterObjet(ObjetDessinable o) {
		liste.add(o);
	}

	/**
	 * retirer un objet de la zone graphique
	 */
	void retirerObjet(ObjetDessinable o) {
		liste.remove(o);
	}

	/**
	 * largeur de la partie dessinable
	 */
	public int getWidth() {
		return getContentPane().getWidth();
	}

	/**
	 * hauteur de la partie dessinable
	 */
	public int getHeight() {
		return getContentPane().getHeight();
	}

}