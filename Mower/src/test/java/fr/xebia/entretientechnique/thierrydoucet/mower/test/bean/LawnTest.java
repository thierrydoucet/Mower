package fr.xebia.entretientechnique.thierrydoucet.mower.test.bean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;

/**
 * Test unitaire de la classe {@link Lawn}
 * 
 * @author Thierry Doucet
 */
public class LawnTest {
	/**
	 * Test de la méthode {@link Lawn#isReachable(Point)}
	 */
	@Test
	public void isReachable() {
		assertTrue(new Lawn(3, 3).isReachable(new Point(1, 1)));
	}

	/**
	 * Test de la méthode {@link Lawn#isReachable(Point)}. Abscisse en dehors de
	 * la pelouse.
	 */
	@Test
	public void isReachable_xaxisOutOfBound() {
		assertFalse(new Lawn(1, 3).isReachable(new Point(2, 1)));
		assertFalse(new Lawn(1, 3).isReachable(new Point(-1, 1)));
	}

	/**
	 * Test de la méthode {@link Lawn#isReachable(Point)}. Ordonnée en dehors de
	 * la pelouse.
	 */
	@Test
	public void isReachable_yaxisOutOfBound() {
		assertFalse(new Lawn(3, 1).isReachable(new Point(1, 2)));
		assertFalse(new Lawn(3, 1).isReachable(new Point(1, -1)));
	}

	/**
	 * Test de la méthode {@link Lawn#isReachable(Point)}. Tondeuse au point de
	 * destination.
	 */
	@Test
	public void isReachable_mowerOnDestination() {
		Lawn lawn = new Lawn(3, 3);
		new AutomaticMower(1, 1, CardinalDirection.N, lawn);
		assertFalse(lawn.isReachable(new Point(1, 1)));
	}
}
