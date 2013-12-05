package fr.xebia.entretientechnique.thierrydoucet.mower.test.builder;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Test;

import fr.xebia.entretientechnique.thierrydoucet.mower.bean.AutomaticMower;
import fr.xebia.entretientechnique.thierrydoucet.mower.bean.Lawn;
import fr.xebia.entretientechnique.thierrydoucet.mower.builder.GardenBuilder;
import fr.xebia.entretientechnique.thierrydoucet.mower.builder.InputStreamGardenBuilder;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.AMowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.DMowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.GMowerCommand;
import fr.xebia.entretientechnique.thierrydoucet.mower.command.MowerCommand;

/**
 * Test unitaire de la classe {@link InputStreamGardenBuilder}
 * 
 * @author Thierry Doucet
 */
public class InputStreamGardenBuilderTest {

	private final static String TEST_DATA = "5 10\n" + //
			"1 2 E\n" + //
			"GAD\n";

	/**
	 * Test de la méthode {@link InputStreamGardenBuilder#buildGarden()}
	 */
	@Test
	public void buildGardern() throws UnsupportedEncodingException {
		GardenBuilder builder = new InputStreamGardenBuilder(
				new ByteArrayInputStream(TEST_DATA.getBytes("ISO-8859-1")), Charset.forName("ISO-8859-1"));
		builder.buildGarden();

		Lawn lawn = builder.getLawn();
		assertNotNull(lawn);
		assertEquals(5, lawn.getDimension().width);
		assertEquals(10, lawn.getDimension().height);

		assertEquals(1, builder.getMowers().size());
		AutomaticMower mower = builder.getMowers().iterator().next();
		assertEquals("1, 2, E", mower.getPosition().toString());

		assertEquals(3, builder.getCommands(mower).size());
		List<MowerCommand> commands = builder.getCommands(mower);
		assertThat(commands.get(0), instanceOf(GMowerCommand.class));
		assertThat(commands.get(1), instanceOf(AMowerCommand.class));
		assertThat(commands.get(2), instanceOf(DMowerCommand.class));
	}
}
