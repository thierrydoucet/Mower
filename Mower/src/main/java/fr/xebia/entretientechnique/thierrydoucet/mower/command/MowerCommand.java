package fr.xebia.entretientechnique.thierrydoucet.mower.command;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;

/**
 * Interface représentant les commandes recevable par une tondeuse.
 * 
 * @author Thierry Doucet
 */
public interface MowerCommand {

	/**
	 * @return Le code de la commande. Celui ci doit être unique.
	 */
	char getCommandCode();

	/**
	 * Execute la commande.
	 */
	void executeCommand(AutomaticMower mower);
}
