package fr.xebia.entretientechnique.thierrydoucet.mower.test.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerManager;

/**
 * Test unitaire de la classe {@link MowerManager}
 * 
 * @author Thierry Doucet
 */
public class MowerManagerTest {

	/**
	 * Test de la méthode {@link MowerManager#mowLawn()}
	 */
	@Test
	public void mowLawn() {
		Lawn lawn = new Lawn(3, 3);
		AutomaticMower mower = new AutomaticMower(1, 1, CardinalDirection.N, lawn);
		MowerCommand command = mock(MowerCommand.class);
		Map<AutomaticMower, List<MowerCommand>> mowersCommands = new HashMap<AutomaticMower, List<MowerCommand>>();
		mowersCommands.put(mower, Collections.singletonList(command));

		MowerManager manager = new MowerManager(mowersCommands);
		manager.mowLawn();

		verify(command).executeCommand(mower);
	}
}
