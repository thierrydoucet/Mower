package fr.xebia.entretientechnique.thierrydoucet.mower.command;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;

/**
 * Commande associée a l'action "tourner à droite".
 * 
 * @author Thierry Doucet
 */
public class DMowerCommand implements MowerCommand {

	protected DMowerCommand() {
	}

	/**
	 * @see fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand#getCommandCode()
	 */
	@Override
	public char getCommandCode() {
		return 'D';
	}

	/**
	 * @see fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand#executeCommand(fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower)
	 */
	@Override
	public void executeCommand(AutomaticMower mower) {
		mower.turnRight();
	}
}
