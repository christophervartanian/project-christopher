package UnitTests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import src.Date;
import src.UserInputDate;

public class UserInputDateTests {
	@Test
	void testUserInputDate20200404() {
		UserInputDate userInput = new UserInputDate();
		userInput.gatherUserInput();
		Date d = userInput.getDateWeatherData();
		assertTrue("20200404".equals(d.getDateString()));
		assertTrue("69.0".equals(d.getPredLow()));
		assertTrue("83.0".equals(d.getPredHigh()));
		assertTrue("69.5".equals(d.getRealLow()));
		assertTrue("82.4".equals(d.getRealHigh()));
	}
	void testUserInputDate19770303() {
		UserInputDate userInput = new UserInputDate();
		userInput.gatherUserInput();
		Date d = userInput.getDateWeatherData();
		assertTrue("19770303".equals(d.getDateString()));
		assertTrue("71.0".equals(d.getPredLow()));
		assertTrue("86.0".equals(d.getPredHigh()));
		assertTrue("67.4".equals(d.getRealLow()));
		assertTrue("80.6".equals(d.getRealHigh()));
	}
}
