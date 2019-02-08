package hangmanMain;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	private static boolean wonGame = false;
	private static char[] guessedLetters;

	public static void main(String[] args) {
		try {
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
			}
			System.out.println("The word has now been randomly selected.");
			guessedLetters = new char[word.length()];
			for (int i = 0; i < guessedLetters.length; i++) {
				guessedLetters[i] = ' ';
			}
			for(int i = 0; i< word.length(); i++) {
				System.out.print("_ ");
			}
			do {
				if(checkVoctory(word)) {
					System.out.println();
					System.out.println("YOU WON!!!");
					break;
				}
				
				System.out.println();
				System.out.print("Guess a letter: ");
				char guess = sc.nextLine().charAt(0);
				guessedLetters = printLettersAndLines(word, guess);
				numOfAttempts++;
			} while (wonGame != true && numOfAttempts <= 10);
			if (wonGame == false && numOfAttempts > 10) {
				System.out.println();
				System.out.println("YOU LOSE...");
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Someting went wrong (T.T)");
		}
	}

	public static String getEnglishWord() throws IOException {
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

	public static String getSwedishWord() throws IOException {
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

	public static char[] printLettersAndLines(String word, char guess) {
	
		for(int i = 0; i < word.length(); i++) {
			if(Character.toLowerCase(word.charAt(i)) == guess) {
				guessedLetters[i] = guess;
			}
		}
		for (int i = 0; i < word.length(); i++) {
			System.out.print(guessedLetters[i]+ " ");
		}
		System.out.println();
		for (int i = 0; i < word.length(); i++) {
			System.out.print("_ ");
		}
		return guessedLetters;
	}
	
	public static boolean checkVoctory(String word) {
		boolean retVal = false;
		for (int i = 0; i < guessedLetters.length; i++) {
			if(word.charAt(i) == guessedLetters[i]) {
				retVal = true;
			}else {
				retVal = false;
			}
		}
		return retVal;
	}
}
