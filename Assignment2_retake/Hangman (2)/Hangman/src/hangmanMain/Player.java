package hangmanMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Player {
	private String email;
	private String password;
	private int currentScore;

	public boolean registerUser(String filePath, String tempEmail, String tempPassword) throws IOException {
		
		File file = new File(filePath);
		if(!file.exists()) {
			return false;
		}else {
			Scanner sc = new Scanner(file);
			PrintWriter printer = new PrintWriter(new FileWriter(file.getPath(), true));
			StringBuilder builder = new StringBuilder();
			builder.append(tempEmail + ";");
			builder.append(tempPassword + "\n");
			printer.print(builder.toString());
			printer.close();
			sc.close();
			System.out.println("User successfully registered!");
			System.out.println();
			return true;
		}
		
	}

	public boolean logIn(String tempEmail, String tempPassword) throws FileNotFoundException {
		File file = new File("src\\Players.txt");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String info = sc.nextLine();
			String[] infoSeparated = info.split(";");
			if (tempEmail.equals(infoSeparated[0]) && tempPassword.equals(infoSeparated[1])) {
				email = tempEmail;
				password = tempPassword;
				System.out.println("User successfully logged in!");
				return true;

			}
		}
		return false;
	}

	public boolean checkEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

}
