package fr.xebia.entretientechnique.thierrydoucet.mower.command;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;

/**
 * Commande associée a l'action "tourner à gauche".
 * 
 * @author Thierry Doucet
 */
public class GMowerCommand implements MowerCommand {

	protected GMowerCommand() {
	}

	/**
	 * @see fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand#getCommandCode()
	 */
	@Override
	public char getCommandCode() {
		return 'G';
	}

	/**
	 * @see fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand#executeCommand(fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower)
	 */
	@Override
	public void executeCommand(AutomaticMower mower) {
		mower.turnLeft();
	}
}
