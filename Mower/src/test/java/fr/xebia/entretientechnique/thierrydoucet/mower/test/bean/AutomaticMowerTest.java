package fr.xebia.entretientechnique.thierrydoucet.mower.test.bean;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;

/**
 * Test unitaire de la classe {@link AutomaticMower}
 * 
 * @author Thierry Doucet
 */
public class AutomaticMowerTest {

	private AutomaticMower mower;

	@Before
	public void createMower() {
		Lawn lawn = new Lawn(3, 3);
		this.mower = new AutomaticMower(1, 1, CardinalDirection.N, lawn);
	}

	/**
	 * Test de la méthode {@link AutomaticMower#getName()}
	 */
	@Test
	public void getName() {
		assertThat(mower.getName(), startsWith("Automatic Mower #"));
	}

	/**
	 * Test de la méthode {@link AutomaticMower#getPosition()}
	 */
	@Test
	public void getPosition() {
		assertEquals("1, 1, N", mower.getPosition().toString());
	}

	/**
	 * Test de la méthode {@link AutomaticMower#moveForward()}
	 */
	@Test
	public void moveForward() {
		mower.moveForward();
		assertEquals("1, 2, N", mower.getPosition().toString());
	}

	/**
	 * Test de la méthode {@link AutomaticMower#moveForward()}. Cas case non
	 * atteignable.
	 */
	@Test
	public void moveForward_notReachable() {
		mower.moveForward();
		mower.moveForward();
		assertEquals("1, 3, N", mower.getPosition().toString());
		mower.moveForward();
		assertEquals("1, 3, N", mower.getPosition().toString());
	}

	/**
	 * Test de la méthode {@link AutomaticMower#turnLeft()}
	 */
	@Test
	public void turnLeft() {
		mower.turnLeft();
		assertEquals("1, 1, W", mower.getPosition().toString());
	}

	/**
	 * Test de la méthode {@link AutomaticMower#turnRight()}
	 */
	@Test
	public void turnRight() {
		mower.turnRight();
		assertEquals("1, 1, E", mower.getPosition().toString());
	}
}
