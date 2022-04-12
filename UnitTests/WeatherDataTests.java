package UnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Date;
import src.UserInputDate;

public class WeatherDataTests {
	
	
	//can only include one test because testing requires user input
	@Test
	void testUserInputDate20200404() throws FileNotFoundException {
		UserInputDate userInput = new UserInputDate();
		userInput.gatherUserInput();
		Date day = userInput.getDateWeatherData();
		assertTrue("20200404".equals(day.getDateString()));
		assertTrue("69.0".equals(day.getPredLow()));
		assertTrue("83.0".equals(day.getPredHigh()));
		assertTrue("69.5".equals(day.getRealLow()));
		assertTrue("82.4".equals(day.getRealHigh()));
		assertTrue("5.78".equals(day.getPredPrecip()));
		assertTrue("6.31".equals(day.getRealPrecip()));
	}

}
