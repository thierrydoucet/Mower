package fr.xebia.entretientechnique.thierrydoucet.mower.builder;

import java.awt.Dimension;
import java.beans.PropertyEditorSupport;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;

/**
 * <p>
 * Property Editor permettant la transformation d'un chaine contenant les
 * dimensions d'une pelouse en {@link Lawn}.
 * </p>
 * <p>
 * Format de la chaine : "N N" avec N = nombre entier
 * </p>
 * 
 * @author Thierry Doucet
 */
public class LawnDimensionPropertyEditor extends PropertyEditorSupport {

	private static final String VALID_INPUT = "^\\d+ \\d+$";

	/**
	 * Constructeur par défaut.
	 */
	protected LawnDimensionPropertyEditor() {
	}

	/**
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {
		Dimension dimension = ((Lawn) getValue()).getDimension();
		return ((int) dimension.getWidth()) + " " + ((int) dimension.getHeight());
	}

	/**
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (!Pattern.matches(VALID_INPUT, text)) {
			throw new IllegalArgumentException("Lawn input seems to be corrupted: dont' match pattern: " + VALID_INPUT);
		}
		StringTokenizer st = new StringTokenizer(text, " ");
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		setValue(new Lawn(width, height));
	}
}
