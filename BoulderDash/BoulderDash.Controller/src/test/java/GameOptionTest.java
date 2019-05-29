import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.TomBAN.BoulderDash.Game.Controller.GameMode;
import com.TomBAN.BoulderDash.Game.Controller.GameOption;

public class GameOptionTest {
	private GameOption gameOption;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		gameOption = new GameOption(4, GameMode.MultiCoopRace, "en", false);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetters() {
		if (gameOption.isDualScreen()) {
			fail("isDualScreen");
		}
		if (!gameOption.getLanguage().equals("en")) {
			fail("getLanguage");
		}
		if (gameOption.getGameMode() != GameMode.MultiCoopRace) {
			fail("getGameMode");
		}
		if (gameOption.getPlayerNumber() != 4) {
			fail("getPlayerNumber");
		}
		if (gameOption.getModelNumber() != 2) {
			fail("getModelNumber");
		}
		if (gameOption.getPlayerNumberPerMap() != 2) {
			fail("getPlayerNumberPerMap");
		}
	}

	@Test
	public void testPlayerNumber() {
		try {
			new GameOption(0, GameMode.MultiPlayerRace, "en");
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testGameMode() {
		try {
			new GameOption(2, null, "en");
			fail();
		} catch (Exception e) {
		}
	}
	@Test
	public void testDualScreen() {
		try {
			new GameOption(1, GameMode.SinglePlayer, "en",true);
			fail();
		} catch (Exception e) {
		}
	}
}
