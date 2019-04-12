package hangmanMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Highscore {
	private Game game;

	public int displayHighScore() throws FileNotFoundException {
	
		File file = new File("src\\Highscore.txt");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		ArrayList<Integer> scores = new ArrayList<>();
		while(sc.hasNextLine()) {
			scores.add(Integer.parseInt(sc.nextLine()));
		}
		scores.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		System.out.println("Current high score is " + scores.get(scores.size() - 1));
		return scores.get(scores.size() - 1);
	}

	public void writeHighScore() throws IOException {
		File file = new File("src\\Highscore.txt");
		PrintWriter printer = new PrintWriter(new FileWriter(file.getPath(), true));
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		FileReader reader = new FileReader(file);
		LineNumberReader lines = new LineNumberReader(reader);
		if (sc.hasNextLine()) {
		 String currentScoreLine = sc.next();
			if (game.getPlayer().getCurrentScore() > Integer.parseInt(currentScoreLine)) {
				printer.println(Integer.toString(game.getPlayer().getCurrentScore()));
				printer.close();
			}
		}
		printer.close();
		lines.close();

	}

	public void setGame(Game game) {
		this.game = game;
	}
}
