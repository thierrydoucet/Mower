package fr.xebia.entretientechnique.thierrydoucet.mower.observer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Position;

/**
 * Ecouteur (Observer) affichant le jardin.
 * 
 * @author thierry
 */
public class GardenFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;

	private final Lawn lawn;

	private final Collection<AutomaticMower> mowers;

	/**
	 * Constructeur avec injection du modèle.
	 */
	public GardenFrame(Lawn lawn, Collection<AutomaticMower> mowers) {
		super("My Garden");
		this.lawn = lawn;
		this.mowers = mowers;
		init();
	}

	/**
	 * Initialisation de l'IHM.
	 */
	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);
		setIconImage(CRITTER_E_ICON.getImage());

		add(new GardenPanel());
	}

	/**
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				repaint();
			}
		});
	}

	/**
	 * Panel responsable de l'affichage du jardin.
	 */
	private final class GardenPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private static final int FACTOR = 32;

		/**
		 * Constructeur par défaut.
		 */
		private GardenPanel() {
			Dimension ld = lawn.getDimension();
			Dimension size = new Dimension((ld.width + 1) * FACTOR, (ld.height + 1) * FACTOR);
			setSize(size);
			setPreferredSize(size);
		}

		/**
		 * @see javax.swing.JComponent#paint(java.awt.Graphics)
		 */
		@Override
		public void paint(Graphics g) {
			paintLawn(g);
			paintMowers(g);
		}

		/**
		 * Peind la pelouse.
		 */
		private void paintLawn(Graphics g) {
			Dimension ld = lawn.getDimension();
			for (int x = 0; x <= ld.width; x++) {
				for (int y = 0; y <= ld.height; y++) {
					g.drawImage(GRASS_ICON.getImage(), x * FACTOR, y * FACTOR, this);
				}
			}
		}

		/**
		 * Peind les tondeuses.
		 */
		private void paintMowers(Graphics g) {
			for (AutomaticMower mower : mowers) {
				Position mp = mower.getPosition();
				switch (mp.getDirection()) {
				case N:
					drawMower(g, mp, CRITTER_N_ICON.getImage());
					break;
				case E:
					drawMower(g, mp, CRITTER_E_ICON.getImage());
					break;
				case S:
					drawMower(g, mp, CRITTER_S_ICON.getImage());
					break;
				case W:
					drawMower(g, mp, CRITTER_W_ICON.getImage());
					break;
				default:
					throw new IllegalArgumentException("Unknown cardinal direction: " + mp.getDirection());
				}
			}
		}

		/**
		 * Dessine la tondeuse en parametre.
		 */
		private void drawMower(Graphics g, Position mowerPosition, Image mowerImage) {
			g.drawImage(mowerImage, mowerPosition.x * FACTOR, (lawn.getDimension().height - mowerPosition.y) * FACTOR,
					this);
		}
	}

	private static final ImageIcon GRASS_ICON = new ImageIcon(ClassLoader.getSystemResource("icons/grass_32x32.png"));

	private static final ImageIcon CRITTER_N_ICON = new ImageIcon(
			ClassLoader.getSystemResource("icons/critter-N_32x32.png"));

	private static final ImageIcon CRITTER_E_ICON = new ImageIcon(
			ClassLoader.getSystemResource("icons/critter-E_32x32.png"));

	private static final ImageIcon CRITTER_S_ICON = new ImageIcon(
			ClassLoader.getSystemResource("icons/critter-S_32x32.png"));

	private static final ImageIcon CRITTER_W_ICON = new ImageIcon(
			ClassLoader.getSystemResource("icons/critter-W_32x32.png"));
}
