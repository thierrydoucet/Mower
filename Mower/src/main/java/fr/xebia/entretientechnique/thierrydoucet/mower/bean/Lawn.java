package fr.xebia.entretientechnique.thierrydoucet.mower.bean;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Java bean représentant la pelouse.
 * 
 * @author Thierry Doucet
 */
public class Lawn implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Dimension dimension;

	private List<AutomaticMower> mowers;

	/**
	 * Constructeur avec injection des dimensions.
	 * 
	 * @param width la largeur de la pelouse (abscisse maximale)
	 * @param height la hauteur de la pelouse (ordonnée maximale)
	 */
	public Lawn(int width, int height) {
		this.dimension = new Dimension(width, height);
		this.mowers = new ArrayList<AutomaticMower>();
	}

	/**
	 * @return les dimensions de la pelouse.
	 */
	public Dimension getDimension() {
		return new Dimension(dimension);
	}

	/**
	 * Ajoute une tondeuse sur la pelouse.
	 */
	protected void addMower(AutomaticMower mower) {
		mowers.add(mower);
	}

	/**
	 * Contrôle si le point de destination est atteignable: dans la zone de la
	 * pelouse, et il n'y a pas d'obstacles a cette destination
	 */
	public boolean isReachable(Point destination) {
		return isInBound(destination) && !isMowerPresent(destination);
	}

	/**
	 * Contrôle si le point de destination se trouve sur la pelouse.
	 */
	private boolean isInBound(Point destination) {
		return destination.x >= 0 && destination.x <= dimension.width && destination.y >= 0
				&& destination.y <= dimension.height;
	}

	/**
	 * Contrôle si une tondeuse est présente au point de destination.
	 */
	private boolean isMowerPresent(Point destination) {
		// test de rencontre d'un autre tondeuse
		for (AutomaticMower mower : mowers) {
			if (destination.equals(mower.getPosition())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getName() + "[" + dimension.width + " x " + dimension.height + ", automatic mowers: "
				+ mowers.size() + "]";
	}
}
