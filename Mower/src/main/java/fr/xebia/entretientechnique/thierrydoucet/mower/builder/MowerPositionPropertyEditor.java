package fr.xebia.entretientechnique.thierrydoucet.mower.builder;

import java.beans.PropertyEditorSupport;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.CardinalDirection;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Position;

/**
 * <p>
 * Property Editor permettant la transformation de la position d'une tondeuse en
 * {@link AutomaticMower}.
 * </p>
 * <p>
 * Format de la chaine : "NB NB N|E|S|W" avec NB = un nombre entier.
 * </p>
 * 
 * @author Thierry Doucet
 */
public class MowerPositionPropertyEditor extends PropertyEditorSupport {

	private static final String VALID_INPUT = "^\\d+ \\d+ [NESW]$";

	private final Lawn lawn;

	/**
	 * Constructeur par défaut
	 */
	protected MowerPositionPropertyEditor(Lawn lawn) {
		this.lawn = lawn;
	}

	/**
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {
		Position mowerPosition = ((AutomaticMower) getValue()).getPosition();
		return mowerPosition.x + " " + mowerPosition.y + " " + mowerPosition.getDirection();
	}

	/**
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (!Pattern.matches(VALID_INPUT, text)) {
			throw new IllegalArgumentException("Mower input seems to be corrupted: dont' match pattern: " + VALID_INPUT);
		}
		StringTokenizer st = new StringTokenizer(text, " ");
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		CardinalDirection direction = CardinalDirection.valueOf(st.nextToken());
		setValue(new AutomaticMower(x, y, direction, lawn));
	}
}
