package fr.xebia.entretientechnique.thierrydoucet.mower.builder;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand;

/**
 * <p>
 * Reader traitant la lecture du fichier d'entrée.
 * </p>
 * 
 * <p>
 * La lecture des différents objets implique l'avancée du pointeur de lecture du
 * flux passé en paramètre.
 * </p>
 * 
 * <p>
 * Cette classe n'est pas responsable de la logique d'appel des différente
 * méthode read, donc de la structure et de l'organisation des enrgistrements du
 * fichier d'entrée.
 * </p>
 * 
 * @author Thierry Doucet
 */
public class GardenInputStreamReader implements Closeable {

	private final InputStream inputStream;

	private final Charset inputCharset;

	private BufferedReader inputReader = null;

	/**
	 * Constructeur avec injection du flux d'entrée et de l'encoding de ce
	 * dernier.
	 */
	protected GardenInputStreamReader(InputStream inputStream, Charset inputCharset) {
		this.inputStream = inputStream;
		this.inputCharset = inputCharset;
	}

	/**
	 * Réalise l'ouverture du reader.
	 */
	public void open() {
		this.inputReader = new BufferedReader(new InputStreamReader(inputStream, inputCharset));
	}

	/**
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() {
		try {
			if (inputReader != null) {
				inputReader.close();
			}
		}
		catch (IOException e) {
			throw new GardenInputDataException("Can't close input stream", e);
		}
	}

	/**
	 * @return La pelouse correspondant à l'enregistrement courant.
	 */
	public Lawn readLawn() {
		String data = readLine();
		if (data == null) {
			return null;
		}
		LawnDimensionPropertyEditor pe = new LawnDimensionPropertyEditor();
		pe.setAsText(data);
		return (Lawn) pe.getValue();
	}

	/**
	 * @return La tondeuse correspondant à l'enregistrement courant.
	 */
	public AutomaticMower readMower(Lawn lawn) {
		String data = readLine();
		if (data == null) {
			return null;
		}
		MowerPositionPropertyEditor pe = new MowerPositionPropertyEditor(lawn);
		pe.setAsText(data);
		return (AutomaticMower) pe.getValue();
	}

	/**
	 * @return Les commandes correspondants à l'enregistrement courant.
	 */
	public List<MowerCommand> readCommands() {
		String data = readLine();
		if (data == null) {
			return null;
		}
		List<MowerCommand> commands = new ArrayList<MowerCommand>();
		MowerCommandCodePropertyEditor pe = new MowerCommandCodePropertyEditor();
		for (int i = 0; i < data.length(); i++) {
			pe.setAsText(String.valueOf(data.charAt(i)));
			commands.add((MowerCommand) pe.getValue());
		}
		return commands;
	}

	/**
	 * Réalise la lecture de l'enregistrment et avance le pointeur de lecture au
	 * suivant dans le flux.
	 * 
	 * @return L'enregistrement courant.
	 */
	private String readLine() {
		try {
			return inputReader.readLine();
		}
		catch (IOException e) {
			throw new GardenInputDataException("Can't read input stream", e);
		}
	}
}
