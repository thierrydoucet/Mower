package fr.xebia.entretientechnique.thierrydoucet.mower.observer;

import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;

/**
 * Ecouteur (Observer) journalisant les évenements.
 * 
 * @author Thierry Doucet
 */
public class LoggerObserver implements Observer {

	private static final String MESSAGE_FORMAT = "Mower '%s' executed order '%s', position is %s\n";

	private final PrintStream logger;

	/**
	 * Constructeur avec injection du flux où journaliser.
	 */
	public LoggerObserver(PrintStream logger) {
		this.logger = logger;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void update(Observable o, Object arg) {
		if (o instanceof AutomaticMower) {
			AutomaticMower mower = (AutomaticMower) o;
			logger.printf(MESSAGE_FORMAT, mower.getName(), arg, mower.getPosition());
		}
	}
}
