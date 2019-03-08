package hangmanMain;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;
import java.util.Scanner;

public class Game {

	private boolean isGameWon = false;
	private char[] guessedLetters;
	private Player player;

	public char[] printLettersAndLines(String word, char guess) {
		for (int i = 0; i < word.length(); i++) {
			if (Character.toLowerCase(word.charAt(i)) == guess) {
				guessedLetters[i] = guess;
			}
		}
		for (int i = 0; i < word.length(); i++) {
			System.out.print(guessedLetters[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < word.length(); i++) {
			System.out.print("_ ");
		}
		return guessedLetters;
	}

	public void start() throws IOException {
		mainMenu();
	}

	public void playGame() throws IOException {
		Highscore highscore = new Highscore();
		highscore.setGame(this);
		int numOfMistakes = 0;
		String word = "";
		System.out.print("For english words , press 1. För svenska ord, tryck 2: ");
		Scanner sc = new Scanner(System.in);

		String selection = sc.nextLine();
		switch (selection) {
		case "1":
			word = getEnglishWord();
			break;
		case "2":
			word = getSwedishWord();
			break;
		default:
			System.out.println("Wrong input, please try again: ");
			playGame();
			break;
		}
		System.out.println("The word has now been randomly selected. To quit the game, press any non letter key.");
		guessedLetters = new char[word.length()];
		for (int i = 0; i < guessedLetters.length; i++) {
			guessedLetters[i] = ' ';
		}
		for (int i = 0; i < word.length(); i++) {
			System.out.print("_ ");
		}
		do {
			System.out.println();
			System.out.print("Guess a letter: ");
			char[] guess = sc.next().toCharArray();
			char guessLetter;
			if (guess.length == 0) {
				guessLetter = ' ';
			} else {
				guessLetter = guess[0];
			}
			if (!Character.isLetter(guessLetter)) {
				areYouSure();
			} else {
				guessedLetters = printLettersAndLines(word, guessLetter);
				if (!word.contains(Character.toString(guessLetter))) {
					numOfMistakes++;
					if (player != null)
						player.setCurrentScore(player.getCurrentScore() - 300);
				} else {
					if (player != null)
						player.setCurrentScore(player.getCurrentScore() + 500);
				}
				isGameWon = checkVictory(word);
			}
		} while (isGameWon == false && numOfMistakes <= 10);
		if (isGameWon) {
			System.out.println();
			if (player != null) {
				System.out.println("Your score: " + player.getCurrentScore());
				highscore.writeHighScore();
				player.setCurrentScore(0);
			}

			System.out.println("YOU WON!!!");

		} else if (isGameWon == false && numOfMistakes > 10) {
			System.out.println();
			System.out.println("YOU LOSE...");
		}
		playAgain();
		sc.close();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean checkVictory(String word) {
		boolean retVal = false;
		String guessedWord = "";
		for (int i = 0; i < guessedLetters.length; i++) {
			guessedWord += guessedLetters[i];
		}
		if (guessedWord.equalsIgnoreCase(word)) {
			retVal = true;
		}

		return retVal;
	}

	public String getSwedishWord() throws IOException {
		String word;
		File file = new File("src\\SwedishWords.txt");
		Scanner sc = new Scanner(file);
		FileReader reader = new FileReader(file);
		LineNumberReader lines = new LineNumberReader(reader);
		int length = 0;
		while (lines.readLine() != null) {
			length = lines.getLineNumber();
		}
		String[] words = new String[length];
		for (int i = 0; i < words.length; i++) {
			words[i] = sc.nextLine();
		}
		Random random = new Random();
		word = words[random.nextInt(words.length)];
		sc.close();
		lines.close();
		return word;
	}

	public String getEnglishWord() throws IOException {
		String word;
		File file = new File("src\\EnglishWords.txt");
		Scanner sc = new Scanner(file);
		FileReader reader = new FileReader(file);
		LineNumberReader lines = new LineNumberReader(reader);
		int length = 0;
		while (lines.readLine() != null) {
			length = lines.getLineNumber();
		}
		String[] words = new String[length];
		for (int i = 0; i < words.length; i++) {
			words[i] = sc.nextLine();
		}
		Random random = new Random();
		word = words[random.nextInt(words.length)];
		sc.close();
		lines.close();
		return word;
	}

	public void playAgain() throws IOException {
		System.out.println("1. Play again");
		System.out.println("2. Quit game");
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();
		switch (choice) {
		case "1":
			playGame();
			break;
		case "2":
			areYouSure();
			break;
		default:
			System.out.println("Wrong input, please try again: ");
			playGame();
		}
		sc.close();
	}

	@SuppressWarnings("resource")
	public void mainMenu() throws IOException {
		System.out.println("1. Play game");
		System.out.println("2. Quit game");
		System.out.println("3. Add new word to the set");
		System.out.println("4. Register");
		System.out.println("5. Log in");
		System.out.println("6. View high score");
		Scanner sc = new Scanner(System.in);
		String choice = "";
		choice = sc.next();
		switch (choice) {
		case "1":
			playGame();
			break;
		case "2":
			areYouSure();
			break;
		case "3":
			NewWord newWord = new NewWord();
			System.out.println("Enter the word: ");
			sc = new Scanner(System.in);
			String newWordString = sc.nextLine();
			newWord.addWord(newWordString);
			break;
		case "4":
			Player playerRegister = new Player();
			sc = new Scanner(System.in);
			String tempEmail;
			String tempPassword;
			do {
				System.out.print("Input a valid email adress: ");
				tempEmail = sc.nextLine();
			} while (playerRegister.checkEmail(tempEmail) != true);
			playerRegister.setEmail(tempEmail);
			do {
				System.out.print("Input a password (at least 8 characters long): ");
				tempPassword = sc.nextLine();
			} while (tempPassword.length() < 8);
			playerRegister.setPassword(tempPassword);

			playerRegister.registerUser("src\\Players.txt", playerRegister.getEmail(), playerRegister.getPassword());
			mainMenu();
			break;
		case "5":
			sc = new Scanner(System.in);
			System.out.print("Input email: ");
			tempEmail = sc.nextLine();
			System.out.println("Input password: ");
			tempPassword = sc.nextLine();
			Player playerLogIn = new Player();
			if (playerLogIn.logIn(tempEmail, tempPassword)) {
				player = playerLogIn;
			} else {
				System.out.println("Wrong username or password");
				choice = "5";
			}
			mainMenu();
			break;
		case "6":
			Highscore score = new Highscore();
			score.displayHighScore();
			mainMenu();
		default:
			System.out.println("Wrong input, please try again");
			mainMenu();
			break;
		}
		sc.close();
	}

	public void areYouSure() throws IOException {
		System.out.println("1. Confirm exit");
		System.out.println("2. Go back.");
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();
		switch (choice) {
		case "1":
			System.out.println("The game will now exit.");
			System.exit(0);
			break;
		case "2":
			mainMenu();
			break;
		default:
			System.out.println("Wrong input, please try again");
			areYouSure();
			break;
		}
		sc.close();
	}

	public boolean isGameWon() {
		return isGameWon;
	}

	public void setGameWon(boolean isGameWon) {
		this.isGameWon = isGameWon;
	}

	public char[] getGuessedLetters() {
		return guessedLetters;
	}

	public void setGuessedLetters(char[] guessedLetters) {
		this.guessedLetters = guessedLetters;
	}

}
