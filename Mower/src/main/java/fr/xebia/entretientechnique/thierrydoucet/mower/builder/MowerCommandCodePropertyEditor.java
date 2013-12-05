package fr.xebia.entretientechnique.thierrydoucet.mower.builder;

import java.beans.PropertyEditorSupport;
import java.util.regex.Pattern;

import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommandFactory;

/**
 * <p>
 * Property Editor permettant la transformation d'un chaine contenant un code
 * commande en {@link MowerCommand}.
 * </p>
 * <p>
 * Format de la chaine : "A|D|G"
 * </p>
 * 
 * @author Thierry Doucet
 */
public class MowerCommandCodePropertyEditor extends PropertyEditorSupport {

	private static final String VALID_INPUT = "^[ADG]$";

	private final MowerCommandFactory commandFactory = new MowerCommandFactory();

	/**
	 * Constructeur par défaut
	 */
	protected MowerCommandCodePropertyEditor() {
	}

	/**
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {
		return Character.toString(((MowerCommand) getValue()).getCommandCode());
	}

	/**
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (!Pattern.matches(VALID_INPUT, text)) {
			throw new IllegalArgumentException("Mower's command input seems to be corrupted: dont' match pattern: "
					+ VALID_INPUT);
		}
		if (text == null || text.length() != 1) {
			throw new IllegalArgumentException("Invalid Mower command code");
		}
		setValue(commandFactory.createMowerCommand(text.charAt(0)));
	}
}
