package fr.xebia.entretientechnique.thierrydoucet.mower.test.builder;

import java.beans.PropertyEditor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.objenesis.instantiator.basic.AccessibleInstantiator;

import fr.xebia.entretientechnique.thierrydoucet.mower.builder.MowerCommandCodePropertyEditor;

/**
 * Test unitaire de la classe {@link }
 * 
 * @author Thierry Doucet
 */
public class MowerCommandCodePropertyEditorTest {

	private PropertyEditor editor;

	@Before
	public void createPropertyEditor() {
		editor = (PropertyEditor) new AccessibleInstantiator(MowerCommandCodePropertyEditor.class).newInstance();
	}

	/**
	 * Test des méthodes
	 * {@link MowerCommandCodePropertyEditor#setAsText(String)} et
	 * {@link MowerCommandCodePropertyEditor#getAsText()}.
	 */
	@Test
	public void setAsText() {
		editor.setAsText("A");
		Assert.assertEquals("A", editor.getAsText());
	}

	/**
	 * Test de validation de la méthode
	 * {@link MowerCommandCodePropertyEditor#setAsText(String)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void setAsText_invalid() {
		editor.setAsText("R");
	}
}
