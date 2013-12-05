package fr.xebia.entretientechnique.thierrydoucet.mower.bean;

import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower.MowerOrder.BLOCK;
import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower.MowerOrder.MOVE;
import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower.MowerOrder.MOW;
import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower.MowerOrder.START;
import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower.MowerOrder.STOP;
import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower.MowerOrder.TURN_LEFT;
import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower.MowerOrder.TURN_RIGHT;

import java.io.Serializable;
import java.util.Observable;

/**
 * Java bean representant une tondeuse automatique.
 * 
 * @author Thierry Doucet
 */
public class AutomaticMower extends Observable implements Comparable<AutomaticMower>, Serializable {

	private static final long serialVersionUID = 1L;

	private final int id;

	private final String name;

	private final Lawn lawn;

	private Position position;

	/**
	 * Constructeur avec injection dela position intiale et de la pelouse.
	 */
	public AutomaticMower(int x, int y, CardinalDirection direction, Lawn lawn) {
		this.id = nextMowerNumber();
		this.name = "Automatic Mower #" + id;
		this.position = new Position(x, y, direction);
		this.lawn = lawn;
		lawn.addMower(this);
	}

	/**
	 * @return Le nom de la tondeuse.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return La postion de la tondeuse.
	 */
	public Position getPosition() {
		return new Position(position);
	}

	/**
	 * Action permettant de démarrer la tondeuse.
	 */
	public void start() {
		setChanged();
		notifyObservers(START);
	}

	/**
	 * Action permettant d'arrêter la tondeuse.
	 */
	public void stop() {
		setChanged();
		notifyObservers(STOP);
	}

	/**
	 * Action permettant de faire pivoter la tondeuse de 90° sur la gauche.
	 */
	public void turnLeft() {
		position.turnAnticlockwiseDirection();
		setChanged();
		notifyObservers(TURN_LEFT);
	}

	/**
	 * Action permettant de faire pivoter la tondeuse de 90° sur la droite.
	 */
	public void turnRight() {
		position.turnClockwiseDirection();
		setChanged();
		notifyObservers(TURN_RIGHT);
	}

	/**
	 * Action permettant de faire avancer la tondeuse sur la case directement
	 * devant elle.
	 */
	public void moveForward() {
		MowerOrder order;
		Position newLocation = computeNewPosition();
		if (lawn.isReachable(newLocation)) {
			position.setLocation(newLocation);
			order = MOVE;
		}
		else {
			order = BLOCK;
		}
		setChanged();
		notifyObservers(order);
	}

	/**
	 * Calcul de la nouvelle position de la tondeuse lors d'une demande de
	 * déplacement de celle ci.
	 */
	private Position computeNewPosition() {
		Position newPosition = new Position(position);
		switch (position.getDirection()) {
		case N:
			newPosition.y = position.y + 1;
			break;
		case E:
			newPosition.x = position.x + 1;
			break;
		case S:
			newPosition.y = position.y - 1;
			break;
		case W:
			newPosition.x = position.x - 1;
			break;
		default:
			throw new IllegalStateException("Unknown cardinal direction!");
		}
		return newPosition;
	}

	/**
	 * Action permettant de demander à la tondeuse de tondre la case sur
	 * laquelle elle se touve.
	 */
	public void mow() {
		setChanged();
		notifyObservers(MOW);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AutomaticMower)) {
			return false;
		}
		return id == ((AutomaticMower) obj).id;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getName() + "[" + name + " (" + getPosition() + ")]";
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AutomaticMower mower) {
		return getName().compareTo(mower.getName());
	}

	/**
	 * Compteur permettant la génération des identifiants des tondeuses.
	 */
	private static int mowerInitNumber = 0;

	private static synchronized int nextMowerNumber() {
		return ++mowerInitNumber;
	}

	/**
	 * Liste des ordres possible que la tondeuse peut émettre.
	 */
	public static enum MowerOrder {
		START, STOP, TURN_LEFT, TURN_RIGHT, MOVE, BLOCK, MOW
	}
}
