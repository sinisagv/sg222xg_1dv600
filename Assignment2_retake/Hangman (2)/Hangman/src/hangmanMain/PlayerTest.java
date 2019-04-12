package hangmanMain;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	Player player = new Player();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test 
	void testRegisterUserCorrectFile() throws IOException {
		assertEquals(player.registerUser("src/Players.txt", "awerad@gmail.com", "agddwefgfrewdef"), true);
	}
	@Test
	void testRegisterUserIncorrectFIle() throws IOException {
		assertEquals(player.registerUser("src\\Player.txt", "awwead@gmail.com", "afdsdfssd"), false);;
	}
	
	@Test
	void testLogInCorrectData() throws FileNotFoundException {
		assertEquals(true, player.logIn("gvojics@gmail.com", "damaica1.2"));
	}
	
	@Test
	void testLogInIncorrectData() throws FileNotFoundException {
		assertEquals(false, player.logIn("asfgsf@asf.com", "asgfhewdfghft"));
	}
	
	@Test 
	void testCheckEmailValidAddressFormat() {
		assertEquals(true, player.checkEmail("nngvjc@gmail.com"));
	}
	@Test 
	void testCheckEmailInvalidAddressFormat() {
		assertEquals(false, player.checkEmail("asafdewdfewdefed.com"));
	}

}
