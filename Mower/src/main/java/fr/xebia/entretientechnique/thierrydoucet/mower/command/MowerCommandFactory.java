package fr.xebia.entretientechnique.thierrydoucet.mower.command;

/**
 * Fabrique (Factory Method) permettant la construction des commandes recevable
 * par une tondeuse.
 * 
 * @author Thierry Doucet
 */
public class MowerCommandFactory {

	/**
	 * @return La commande correspondant au code en paramètre.
	 */
	public MowerCommand createMowerCommand(char code) {
		switch (code) {
		case 'A':
			return new AMowerCommand();
		case 'D':
			return new DMowerCommand();
		case 'G':
			return new GMowerCommand();
		default:
			throw new IllegalArgumentException("Unknown mower command code: " + code);
		}
	}
}
