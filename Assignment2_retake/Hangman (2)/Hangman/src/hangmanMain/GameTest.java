package hangmanMain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
	Game game = new Game();
	Player player = new Player();
	NewWord newWord = new NewWord();

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Testing method");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Finished testing");
	}

	@Test
	void testPrintLettersAndLinesAeroplane() {
		char[] guessedLetters = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
		game.setGuessedLetters(guessedLetters);
		char[] testarray = { 'a', ' ', ' ', ' ', ' ', ' ', 'a', ' ', ' ' };
		game.printLettersAndLines("areoplane", 'a');
		for (int i = 0; i < guessedLetters.length; i++) {
			assertEquals(testarray[i], guessedLetters[i]);
			;
		}
	}

	@Test
	void testPrintLettersAndLinesSand() {
		char[] guessedLetters = { ' ', ' ', ' ', ' ' };
		game.setGuessedLetters(guessedLetters);
		char[] testarray = { 's', ' ', ' ', ' ' };
		game.printLettersAndLines("sand", 's');
		for (int i = 0; i < guessedLetters.length; i++) {
			assertEquals(testarray[i], guessedLetters[i]);
			;
		}
	}

	@Test
	void testGetSwedishWord() throws IOException {
		assertEquals("Yxa", game.getSwedishWord());
	}

	@Test
	void testEnglishWord() throws IOException {
		assertEquals("Hypopotomus", game.getEnglishWord());
	}

}
