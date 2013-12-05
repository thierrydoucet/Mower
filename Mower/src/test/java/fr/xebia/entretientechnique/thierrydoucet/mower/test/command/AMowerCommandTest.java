package fr.xebia.entretientechnique.thierrydoucet.mower.test.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.objenesis.instantiator.basic.AccessibleInstantiator;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.AMowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand;

/**
 * Test unitaire de la classe {@link AMowerCommand}
 * 
 * @author Thierry Doucet
 */
public class AMowerCommandTest {

	private MowerCommand command;

	@Before
	public void createCommand() {
		command = (MowerCommand) new AccessibleInstantiator(AMowerCommand.class).newInstance();
	}

	/**
	 * Test de la méthode {@link AMowerCommand#getCommandCode()}
	 */
	@Test
	public void getCommandCode() {
		assertEquals('A', command.getCommandCode());
	}

	/**
	 * Test de la méthode {@link AMowerCommand#executeCommand(AutomaticMower)}
	 */
	@Test
	public void executeCommand() {
		AutomaticMower mower = mock(AutomaticMower.class);

		command.executeCommand(mower);

		verify(mower).moveForward();
		verify(mower).mow();
	}
}
