package fr.xebia.entretientechnique.thierrydoucet.mower.test.bean;

import static fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection.E;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Position;

/**
 * Test unitaire de la classe {@link Position}
 * 
 * @author Thierry Doucet
 */
public class PositionTest {

	/**
	 * Test de l'instanciation de la classe {@link Position}
	 */
	@Test
	public void instanciation() {
		assertEquals("4, 5, E", new Position(4, 5, E).toString());
	}
}
