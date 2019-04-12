package hangmanMain;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HighScoreTest {
	
	Highscore highscore = new Highscore();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDisplayHighScoreActualHighScore() throws FileNotFoundException {
		assertEquals(2700, highscore.displayHighScore());
	}
	@Test
	void testDisplayHighScoreFraudulentHighScore() throws FileNotFoundException {
		assertEquals(5500, highscore.displayHighScore());
	}

}
