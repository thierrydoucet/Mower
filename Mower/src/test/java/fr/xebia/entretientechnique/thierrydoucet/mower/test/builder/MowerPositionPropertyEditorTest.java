package fr.xebia.entretientechnique.thierrydoucet.mower.test.builder;

import java.beans.PropertyEditor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;
import fr.xebia.entretientechnique.thierrydoucet.mower.builder.MowerPositionPropertyEditor;

/**
 * Test unitaire de la classe {@link MowerPositionPropertyEditor}
 * 
 * @author Thierry Doucet
 */
public class MowerPositionPropertyEditorTest {

	private PropertyEditor editor;

	@Before
	public void createPropertyEditor() throws IllegalArgumentException, InstantiationException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException {
		Constructor<MowerPositionPropertyEditor> constructor = MowerPositionPropertyEditor.class
				.getDeclaredConstructor(Lawn.class);
		constructor.setAccessible(true);
		editor = constructor.newInstance(new Lawn(1, 1));
	}

	/**
	 * Test des méthodes {@link MowerPositionPropertyEditor#setAsText(String)}
	 * et {@link MowerPositionPropertyEditor#getAsText()}.
	 */
	@Test
	public void setAsText() {
		editor.setAsText("2 3 S");
		Assert.assertEquals("2 3 S", editor.getAsText());
	}

	/**
	 * Test de validation de la méthode
	 * {@link MowerPositionPropertyEditor#setAsText(String)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void setAsText_invalid() {
		editor.setAsText("2 3 O");
	}
}
