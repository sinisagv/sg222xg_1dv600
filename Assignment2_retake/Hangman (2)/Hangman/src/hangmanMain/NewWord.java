package hangmanMain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class NewWord {
	 String word;
	
	
	@SuppressWarnings("resource")
	public void addWord(String newWord) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. Add a new english word");
		System.out.println("2. Add a new swedish word");
		System.out.println("Any other button: Back to the main menu");
		String option = sc.nextLine();
		switch (option) {
		case "1":
			File file = new File("src\\EnglishWords.txt");
			PrintWriter printer = new PrintWriter(new FileWriter(file.getPath(), true));
			
			printer.print("\n" + newWord);
			System.out.println("New word added!");
			printer.close();
			Game game = new Game();
			game.mainMenu();
			break;
		case "2":
			File file1 = new File("src\\SwedishWords.txt");
			PrintWriter printer1 = new PrintWriter(new FileWriter(file1.getPath(), true));
			printer1.print("\n" + newWord);
			System.out.println("New word added!");
			printer1.close();
			game = new Game();
			game.mainMenu();
			break;
		default:
			game = new Game();
			game.mainMenu();
			break;
		}
	}
}
