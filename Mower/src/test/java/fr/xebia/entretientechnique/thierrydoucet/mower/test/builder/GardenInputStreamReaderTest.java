package fr.xebia.entretientechnique.thierrydoucet.mower.test.builder;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;
import fr.xebia.entretientechnique.thierrydoucet.mower.builder.GardenInputStreamReader;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.AMowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.DMowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.GMowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand;

/**
 * Test unitaire de la classe {@link GardenInputStreamReader}
 * 
 * @author Thierry Doucet
 */
public class GardenInputStreamReaderTest {

	private final static String TEST_DATA = "5 10\n" + //
			"1 2 E\n" + //
			"GAD\n";

	private GardenInputStreamReader reader;

	@Before
	public void createReader() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		Constructor<GardenInputStreamReader> constructor = GardenInputStreamReader.class.getDeclaredConstructor(
				InputStream.class, Charset.class);
		constructor.setAccessible(true);
		reader = constructor.newInstance(new ByteArrayInputStream(TEST_DATA.getBytes()), Charset.defaultCharset());
	}

	/**
	 * Test de la méthode {@link GardenInputStreamReader#readLawn()}
	 */
	@Test
	public void readLawn() {
		reader.open();

		Lawn lawn = reader.readLawn();
		assertNotNull(lawn);
		assertEquals(5, lawn.getDimension().width);
		assertEquals(10, lawn.getDimension().height);

		reader.close();
	}

	/**
	 * Test de la méthode {@link GardenInputStreamReader#readMower(Lawn)}
	 */
	@Test
	public void readMower() {
		reader.open();
		Lawn lawn = reader.readLawn();

		AutomaticMower mower = reader.readMower(lawn);
		assertEquals("1, 2, E", mower.getPosition().toString());

		reader.close();
	}

	/**
	 * Test de la méthode {@link GardenInputStreamReader#readCommands()}
	 */
	@Test
	public void readMowerCommands() {
		reader.open();
		Lawn lawn = reader.readLawn();
		reader.readMower(lawn);

		List<MowerCommand> commands = reader.readCommands();
		assertThat(commands.get(0), instanceOf(GMowerCommand.class));
		assertThat(commands.get(1), instanceOf(AMowerCommand.class));
		assertThat(commands.get(2), instanceOf(DMowerCommand.class));

		reader.close();
	}
}
