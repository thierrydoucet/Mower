package fr.xebia.entretientechnique.thierrydoucet.mower.command;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;

/**
 * Commande associée a l'action "avancer".
 * 
 * @author Thierry Doucet
 */
public class AMowerCommand implements MowerCommand {

	protected AMowerCommand() {
	}

	/**
	 * @see fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand#getCommandCode()
	 */
	@Override
	public char getCommandCode() {
		return 'A';
	}

	/**
	 * @see fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand#executeCommand(fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower)
	 */
	@Override
	public void executeCommand(AutomaticMower mower) {
		mower.moveForward();
		mower.mow();
	}
}
