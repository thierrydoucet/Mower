package fr.xebia.entretientechnique.thierrydoucet.mower.test.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.objenesis.instantiator.basic.AccessibleInstantiator;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.DMowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand;

/**
 * Test unitaire de la classe {@link DMowerCommand}
 * 
 * @author Thierry Doucet
 */
public class DMowerCommandTest {

	private MowerCommand command;

	@Before
	public void createCommand() {
		command = (MowerCommand) new AccessibleInstantiator(DMowerCommand.class).newInstance();
	}

	/**
	 * Test de la méthode {@link DMowerCommand#getCommandCode()}
	 */
	@Test
	public void getCommandCode() {
		assertEquals('D', command.getCommandCode());
	}

	/**
	 * Test de la méthode {@link DMowerCommand#executeCommand(AutomaticMower)}
	 */
	@Test
	public void executeCommand() {
		AutomaticMower mower = mock(AutomaticMower.class);

		command.executeCommand(mower);

		verify(mower).turnRight();
	}
}
