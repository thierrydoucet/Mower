package fr.xebia.entretientechnique.thierrydoucet.mower.builder;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerManager;

/**
 * Monteur des élements du jardin basé sur un flux.
 * 
 * Description du fichier d'entrée :
 * <ul>
 * <li>La première ligne correspond aux coordonnées du coin supérieur droit de
 * la pelouse, celles du coin inférieur gauche sont supposées être (0,0)</li>
 * <li>La suite du fichier permet de piloter toutes les tondeuses qui ont été
 * déployées. Chaque tondeuse a deux lignes la concernant
 * <ul>
 * <li>la première ligne donne la position initiale de la tondeuse, ainsi que
 * son orientation. La position et l'orientation sont fournies sous la forme de
 * 2 chiffres et une lettre, séparés par un espace.</li>
 * <li>la seconde ligne est une série d'instructions ordonnant à la tondeuse
 * d'explorer la pelouse. Les instructions sont une suite de caractères sans
 * espaces.</li>
 * </ul>
 * </li>
 * </ul>
 * 
 * @author Thierry Doucet
 */
public class InputStreamGardenBuilder implements GardenBuilder {

	private boolean built = false;

	private Lawn lawn;

	private MowerManager mowerManager;

	private SortedMap<AutomaticMower, List<MowerCommand>> mowersAndCommands = new TreeMap<AutomaticMower, List<MowerCommand>>();

	private final GardenInputStreamReader inputReader;

	/**
	 * Constructeur avec injection du flux d'entrée et de l'encoding de ce
	 * dernier.
	 */
	public InputStreamGardenBuilder(InputStream inputStream, Charset inputCharset) {
		this.inputReader = new GardenInputStreamReader(inputStream, inputCharset);
	}

	/**
	 * @see fr.xebia.entretientechnique.thierrydoucet.mower.builder.GardenBuilder#getLawn()
	 */
	@Override
	public Lawn getLawn() {
		checkBuild();
		return lawn;
	}

	/**
	 * @see fr.xebia.entretientechnique.thierrydoucet.mower.builder.GardenBuilder#getMowers()
	 */
	@Override
	public Set<AutomaticMower> getMowers() {
		checkBuild();
		return mowersAndCommands.keySet();
	}

	/**
	 * @see fr.xebia.entretientechnique.thierrydoucet.mower.builder.GardenBuilder#getCommands(fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower)
	 */
	@Override
	public List<MowerCommand> getCommands(AutomaticMower mower) {
		checkBuild();
		return mowersAndCommands.get(mower);
	}

	/**
	 * @see fr.xebia.entretientechnique.thierrydoucet.mower.builder.GardenBuilder#getMowerManager()
	 */
	@Override
	public MowerManager getMowerManager() {
		checkBuild();
		return mowerManager;
	}

	/**
	 * @see fr.xebia.entretientechnique.thierrydoucet.mower.builder.GardenBuilder#buildGarden()
	 */
	@Override
	public synchronized GardenBuilder buildGarden() {
		if (!built) {
			try {
				inputReader.open();
				lawn = inputReader.readLawn();
				AutomaticMower mower = inputReader.readMower(lawn);
				while (mower != null) {
					List<MowerCommand> commands = inputReader.readCommands();
					if (commands == null) {
						throw new GardenInputDataException(
								"Input stream seems to be corrupted (can't read mower's commands)");
					}
					mowersAndCommands.put(mower, commands);
					mower = inputReader.readMower(lawn);
				}
				mowerManager = new MowerManager(mowersAndCommands);
			}
			finally {
				inputReader.close();
			}
			built = true;
		}
		return this;
	}

	/**
	 * Contrôle que les objets géré par ce monteur sont construits.
	 */
	private void checkBuild() {
		if (!built) {
			throw new IllegalStateException("Garden not yet built: call buildGarden method.");
		}
	}
}
