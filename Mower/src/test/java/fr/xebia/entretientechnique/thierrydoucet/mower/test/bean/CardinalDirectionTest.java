package fr.xebia.entretientechnique.thierrydoucet.mower.test.bean;

import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection.E;
import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection.N;
import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection.S;
import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection.W;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection;

/**
 * Test unitaire de la classe {@link CardinalDirection}
 * 
 * @author Thierry Doucet
 */
public class CardinalDirectionTest {
	/**
	 * Test des méthodes
	 * {@link CardinalDirection#getNextAnticlockwiseDirection()} et
	 * {@link CardinalDirection#getNextClockwiseDirection()} depuis le nord.
	 */
	@Test
	public void getNextDirectionFromNorth() {
		assertEquals(W, N.getNextAnticlockwiseDirection());
		assertEquals(E, N.getNextClockwiseDirection());
	}

	/**
	 * Test des méthodes
	 * {@link CardinalDirection#getNextAnticlockwiseDirection()} et
	 * {@link CardinalDirection#getNextClockwiseDirection()} depuis le sud.
	 */
	@Test
	public void getNextDirectionFromSouth() {
		assertEquals(E, S.getNextAnticlockwiseDirection());
		assertEquals(W, S.getNextClockwiseDirection());
	}

	/**
	 * Test des méthodes
	 * {@link CardinalDirection#getNextAnticlockwiseDirection()} et
	 * {@link CardinalDirection#getNextClockwiseDirection()} depuis l'est.
	 */
	@Test
	public void getNextDirectionFromEast() {
		assertEquals(N, E.getNextAnticlockwiseDirection());
		assertEquals(S, E.getNextClockwiseDirection());
	}

	/**
	 * Test des méthodes
	 * {@link CardinalDirection#getNextAnticlockwiseDirection()} et
	 * {@link CardinalDirection#getNextClockwiseDirection()} depuis l'ouest.
	 */
	@Test
	public void getNextDirectionFromWest() {
		assertEquals(S, W.getNextAnticlockwiseDirection());
		assertEquals(N, W.getNextClockwiseDirection());
	}
}
