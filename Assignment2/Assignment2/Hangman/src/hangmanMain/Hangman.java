package hangmanMain;

import java.io.IOException;

public class Hangman {

	public static void main(String[] args) {
		Game game = new Game();
		try {
			game.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}