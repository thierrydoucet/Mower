package fr.xebia.entretientechnique.thierrydoucet.mower.test.builder;

import java.beans.PropertyEditor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.objenesis.instantiator.basic.AccessibleInstantiator;

import fr.xebia.entretientechnique.thierrydoucet.mower.builder.LawnDimensionPropertyEditor;

/**
 * Test unitaire de la classe {@link LawnDimensionPropertyEditor}
 * 
 * @author Thierry Doucet
 */
public class LawnDimensionPropertyEditorTest {

	private PropertyEditor editor;

	@Before
	public void createPropertyEditor() {
		editor = (PropertyEditor) new AccessibleInstantiator(LawnDimensionPropertyEditor.class).newInstance();
	}

	/**
	 * Test des méthodes {@link LawnDimensionPropertyEditor#setAsText(String)}
	 * et {@link LawnDimensionPropertyEditor#getAsText()}.
	 */
	@Test
	public void setAsText() {
		editor.setAsText("24 35");
		Assert.assertEquals("24 35", editor.getAsText());
	}

	/**
	 * Test de validation de la méthode
	 * {@link LawnDimensionPropertyEditor#setAsText(String)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void setAsText_invalid() {
		editor.setAsText("2a 3");
	}
}
