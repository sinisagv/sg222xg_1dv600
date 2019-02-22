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
		int numOfAttempts = 0;
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
		System.out.println("The word has now been randomly selected.");
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
			char guess = sc.nextLine().charAt(0);
			if (!Character.isLetter(guess)) {
				System.out.println("1. Confirm exit");
				System.out.println("2. Return to game");
				selection = sc.nextLine();
				switch (selection) {
				case "1":
					mainMenu();
					break;
				case "2":
					continue;
				default:
					System.out.println("Invalid input");
					continue;
				}
			} else {
				guessedLetters = printLettersAndLines(word, guess);
				numOfAttempts++;
				isGameWon = checkVictory(word);
			}
		} while (isGameWon == false && numOfAttempts <= 10);
		if (isGameWon) {
			System.out.println();
			System.out.println("YOU WON!!!");
		} else if (isGameWon == false && numOfAttempts > 10) {
			System.out.println();
			System.out.println("YOU LOSE...");
		}
		playAgain();
		sc.close();
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
		File file = new File("C:\\Users\\gvoji\\java_courses\\Hangman\\SwedishWords.txt");
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
		File file = new File("C:\\Users\\gvoji\\java_courses\\Hangman\\EnglishWords.txt");
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

	public void mainMenu() throws IOException {
		System.out.println("1. Play game");
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

}
