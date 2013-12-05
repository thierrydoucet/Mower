package fr.xebia.entretientechnique.thierrydoucet.mower.test.observer;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;
import fr.xebia.entretientechnique.thierrydoucet.mower.observer.LoggerObserver;

/**
 * Test unitaire de la classe {@link LoggerObserver}
 * 
 * @author Thierry Doucet
 */
public class LoggerObserverTest {

	/**
	 * Test de la méthode
	 * {@link LoggerObserver#update(java.util.Observable, Object)}
	 */
	@Test
	public void logFormat() {
		ByteArrayOutputStream actuals = new ByteArrayOutputStream();
		LoggerObserver observer = new LoggerObserver(new PrintStream(actuals));
		observer.update(new AutomaticMower(1, 2, CardinalDirection.W, new Lawn(3, 3)), AutomaticMower.MowerOrder.MOVE);
		assertThat(
				actuals.toString(),
				allOf(startsWith("Mower 'Automatic Mower #"),
						endsWith("' executed order 'MOVE', position is 1, 2, W\n")));
	}
}
