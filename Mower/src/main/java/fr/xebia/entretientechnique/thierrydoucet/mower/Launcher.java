package fr.xebia.entretientechnique.thierrydoucet.mower;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Observer;

import javax.swing.SwingUtilities;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.builder.GardenBuilder;
import fr.xebia.entretientechnique.thierrydoucet.mower.builder.InputStreamGardenBuilder;
import fr.xebia.entretientechnique.thierrydoucet.mower.observer.GardenFrame;
import fr.xebia.entretientechnique.thierrydoucet.mower.observer.LoggerObserver;

/**
 * Point d'entrée et initialisation du test technique "Mower" de Xebia.
 * 
 * @author Thierry Doucet
 */
public final class Launcher {

	private static final PrintStream SYSOUT = System.out;

	private static final PrintStream SYSERR = System.err;

	private static final int FILE_ERROR = -1;

	private static final int ENCODING_ERROR = -2;

	private static final int UNCAUGHT_ERROR = -3;

	/**
	 * Point d'entrée.
	 */
	public static void main(String[] args) {
		// check help
		if (args.length == 1 && args[0].equals("-h")) {
			printUsageAndExit();
		}
		// check arg number
		if (args.length == 0 || args.length > 2) {
			printUsageAndExit();
		}

		File input = checkInputFile(args);
		Charset encoding = checkEncoding(args);

		try {
			launch(input, encoding);
		}
		catch (Exception e) {
			printErrorAndExit("Uncaught error: " + e.getMessage(), UNCAUGHT_ERROR);
		}
	}

	/**
	 * Affiche l'usage sur la sysout et quitte.
	 */
	private static void printUsageAndExit() {
		SYSOUT.println("Usage: java -jar Mower-1.0.0.jar <input configuration file> [file encoding]");
		SYSOUT.println("   if [file encoding] no is provided, default plateform encoding is used");
		SYSOUT.println("   Ex: java -jar Mower-1.0.0.jar /tmp/myGardenConfiguration UTF-8");
		System.exit(1);
	}

	/**
	 * Contrôle la validité de l'encoding défini par l'utilisateur.
	 */
	private static Charset checkEncoding(String[] args) {
		Charset encoding = Charset.defaultCharset();
		if (args.length == 2) {
			try {
				encoding = Charset.forName(args[1]);
			}
			catch (IllegalArgumentException e) {
				printErrorAndExit("Unknown encoding: " + encoding, ENCODING_ERROR);
			}
		}
		return encoding;
	}

	/**
	 * Contrôle la validité du fichier d'entrée défini par l'utilisateur.
	 */
	private static File checkInputFile(String[] args) {
		File input = new File(args[0]);
		if (!input.exists()) {
			printErrorAndExit("Can not find file: " + input, FILE_ERROR);
		}
		if (!input.isFile()) {
			printErrorAndExit(input + " is not a regular file", FILE_ERROR);
		}
		return input;
	}

	/**
	 * Affiche le message d'erreur <code>message</code> sur la syserr et quitte
	 * avec le code <code>exitCode</code>.
	 */
	private static void printErrorAndExit(String message, int exitCode) {
		SYSERR.println(message);
		System.exit(exitCode);
	}

	/**
	 * Initialise l'application et lance le processus de tonte de la pelouse.
	 */
	private static void launch(File input, Charset charset) throws FileNotFoundException {
		// build context
		GardenBuilder builder = new InputStreamGardenBuilder(new FileInputStream(input), charset);
		builder.buildGarden();

		// add observers
		Observer observer = new LoggerObserver(SYSOUT);
		for (AutomaticMower mower : builder.getMowers()) {
			mower.addObserver(observer);
		}

		// add ui
		final GardenFrame frame = new GardenFrame(builder.getLawn(), builder.getMowers());
		for (AutomaticMower mower : builder.getMowers()) {
			mower.addObserver(frame);
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.pack();
				frame.setVisible(true);
			}
		});

		// launch application
		builder.getMowerManager().mowLawn();
	}

	private Launcher() {
	}
}
