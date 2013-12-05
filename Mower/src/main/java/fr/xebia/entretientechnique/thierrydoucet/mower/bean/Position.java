package fr.xebia.entretientechnique.thierrydoucet.mower.bean;

import java.awt.Point;

/**
 * Java bean représentant une position :
 * <ul>
 * <li>Location : coordonnées cartésiennes sur un plan</li>
 * <li>Direction : coordonnées géographiques</li>
 * </ul>
 * 
 * @author Thierry Doucet
 */
public class Position extends Point {

	private static final long serialVersionUID = 1L;

	private CardinalDirection direction;

	/**
	 * Constructeur par defaut
	 */
	public Position() {
	}

	/**
	 * Constructeur avec injection des coordonnées initiales.
	 */
	public Position(int x, int y, CardinalDirection direction) {
		super(x, y);
		this.direction = direction;
	}

	/**
	 * Constructeur de copie.
	 */
	public Position(Position position) {
		this(position.x, position.y, position.direction);
	}

	/**
	 * @return La direction actuelle.
	 */
	public CardinalDirection getDirection() {
		return direction;
	}

	/**
	 * @param direction La direction à définir.
	 */
	public void setDirection(CardinalDirection direction) {
		this.direction = direction;
	}

	/**
	 * Change la direction actuelle par celle se trouvant directement dans le
	 * sens anti horraire.
	 */
	public void turnAnticlockwiseDirection() {
		direction = direction.getNextAnticlockwiseDirection();
	}

	/**
	 * Change la direction actuelle par celle se trouvant directement dans le
	 * sens horraire.
	 */
	public void turnClockwiseDirection() {
		direction = direction.getNextClockwiseDirection();
	}

	/**
	 * @see java.awt.Point#toString()
	 */
	@Override
	public String toString() {
		return x + ", " + y + ", " + direction;
	}
}
