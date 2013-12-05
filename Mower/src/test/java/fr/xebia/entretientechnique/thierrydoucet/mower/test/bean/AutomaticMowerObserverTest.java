package fr.xebia.entretientechnique.thierrydoucet.mower.test.bean;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower.MowerOrder;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;

/**
 * Test unitaire (envoi des événements) de la classe {@link AutomaticMower}.
 * 
 * @author Thierry Doucet
 */
public class AutomaticMowerObserverTest {

	private AutomaticMower mower;

	@Before
	public void createMower() {
		Lawn lawn = new Lawn(3, 3);
		this.mower = new AutomaticMower(1, 1, CardinalDirection.N, lawn);
	}

	/**
	 * Test d'envoi d'événement lors de l'appel de la méthode
	 * {@link AutomaticMower#start()}
	 */
	@Test
	public void start() {
		Observer observer = mock(Observer.class);
		mower.addObserver(observer);
		mower.start();
		verify(observer).update(mower, MowerOrder.START);
	}

	/**
	 * Test d'envoi d'événement lors de l'appel de la méthode
	 * {@link AutomaticMower#stop()}
	 */
	@Test
	public void stop() {
		Observer observer = mock(Observer.class);
		mower.addObserver(observer);
		mower.stop();
		verify(observer).update(mower, MowerOrder.STOP);
	}

	/**
	 * Test d'envoi d'événement lors de l'appel de la méthode
	 * {@link AutomaticMower#moveForward()}
	 */
	@Test
	public void moveForward() {
		Observer observer = mock(Observer.class);
		mower.addObserver(observer);
		mower.moveForward();
		verify(observer).update(mower, MowerOrder.MOVE);
	}

	/**
	 * Test d'envoi d'événement lors de l'appel de la méthode
	 * {@link AutomaticMower#moveForward()}. Cas trondeuse bloquée.
	 */
	@Test
	public void moveForward_notReachable() {
		mower.moveForward();
		mower.moveForward();
		assertEquals("1, 3, N", mower.getPosition().toString());

		Observer observer = mock(Observer.class);
		mower.addObserver(observer);
		mower.moveForward();
		verify(observer).update(mower, MowerOrder.BLOCK);
	}

	/**
	 * Test d'envoi d'événement lors de l'appel de la méthode
	 * {@link AutomaticMower#turnLeft()}
	 */
	@Test
	public void turnLeft() {
		Observer observer = mock(Observer.class);
		mower.addObserver(observer);
		mower.turnLeft();
		verify(observer).update(mower, MowerOrder.TURN_LEFT);
	}

	/**
	 * Test d'envoi d'événement lors de l'appel de la méthode
	 * {@link AutomaticMower#turnRight()}
	 */
	@Test
	public void turnRight() {
		Observer observer = mock(Observer.class);
		mower.addObserver(observer);
		mower.turnRight();
		verify(observer).update(mower, MowerOrder.TURN_RIGHT);
	}

	/**
	 * Test d'envoi d'événement lors de l'appel de la méthode
	 * {@link AutomaticMower#mow()}
	 */
	@Test
	public void mow() {
		Observer observer = mock(Observer.class);
		mower.addObserver(observer);
		mower.mow();
		verify(observer).update(mower, MowerOrder.MOW);
	}

}
