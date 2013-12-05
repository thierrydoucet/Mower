package fr.xebia.entretientechnique.thierrydoucet.mower.builder;

import java.util.List;
import java.util.Set;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerManager;

/**
 * Monteur (Builder) pour les objets présent dans le jardin.
 * 
 * @author Thierry Doucet
 */
public interface GardenBuilder {

	/**
	 * Lance la construction des différents élements du jardin.
	 */
	GardenBuilder buildGarden();

	/**
	 * @return La pelouse construite.
	 */
	Lawn getLawn();

	/**
	 * @return Les tondeuses construites.
	 */
	Set<AutomaticMower> getMowers();

	/**
	 * @return Les commandes de la tondeuse en paramètre.
	 */
	List<MowerCommand> getCommands(AutomaticMower mower);

	/**
	 * @return Le gestionnaire de tondeuses.
	 */
	MowerManager getMowerManager();
}
