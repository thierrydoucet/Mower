package fr.xebia.entretientechnique.thierrydoucet.mower.command;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;

/**
 * Gestionnaire des tondeuse. Permet de regrouper les tondeuses ainsi que les
 * commandes qui leur sont associées. Permet egalement le lancement et la
 * supervision du processus de tonte de la pelouse.
 * 
 * @author Thierry Doucet
 */
public class MowerManager {

	private static final long DELAY = 500;

	private final Map<AutomaticMower, List<MowerCommand>> mowers;

	/**
	 * Constructeur avec injection des tondeuses et de leurs commandes
	 * associées.
	 */
	public MowerManager(Map<AutomaticMower, List<MowerCommand>> mowers) {
		this.mowers = mowers;
	}

	/**
	 * Lancement du processus de tonte de la pelouse.
	 */
	public void mowLawn() {
		for (Entry<AutomaticMower, List<MowerCommand>> mowerCommands : mowers.entrySet()) {
			AutomaticMower mower = mowerCommands.getKey();
			mower.start();
			for (MowerCommand command : mowerCommands.getValue()) {
				command.executeCommand(mower);
				try {
					Thread.sleep(DELAY);
				}
				catch (InterruptedException e) {
					return;
				}
			}
			mower.stop();
		}
	}
}
