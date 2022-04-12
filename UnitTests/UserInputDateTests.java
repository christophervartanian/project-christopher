package UnitTests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Date;
import src.UserInputDate;

public class UserInputDateTests {
	private UserInputDate inputDate;
	
	@BeforeEach 
	void setup() throws FileNotFoundException {
		inputDate = new UserInputDate();
	}
	
	//*** For CheckInputValidFormat Tests ***
	//Couldn't create tests for the actual method so we reconstructed the method within the tests
	@Test
	void testCheckInputValidFormatValid() {
		boolean validFormat = false;
		String testDate = "19770404";
		if (testDate.length() == 8 && inputDate.isNumeric(testDate)) {
			validFormat = true;
		}
		assertTrue(validFormat);
	}
	@Test
	void testCheckInputValidFormatInvalidNonNumeric() {
		boolean validFormat = false;
		String testDate = "YYYYMMDD";
		if (testDate.length() == 8 && inputDate.isNumeric(testDate)) {
			validFormat = true;
		}
		assertFalse(validFormat);
	}
	@Test
	void testCheckInputValidFormatInvalidWrongLength() {
		boolean validFormat = false;
		String testDate = "200109271";
		if (testDate.length() == 8 && inputDate.isNumeric(testDate)) {
			validFormat = true;
		}
		assertFalse(validFormat);
	}
	
	@Test
	void testIsNumericFalse() {
		boolean isNumeric = inputDate.isNumeric("notnumeric");
		assertFalse(isNumeric);
	}
	@Test
	void testIsNumericTrue() {
		boolean isNumeric = inputDate.isNumeric("19990404");
		assertTrue(isNumeric);
	}
	@Test
	void testInputInValidDateRangeTrue() {
		boolean isInValidDateRange = inputDate.checkInputInValidDateRange("19400516");
		assertTrue(isInValidDateRange);
	}
	@Test
	void testConvertDateStringtoYYYYMMDD() {
		int[] YYYYMMDD = inputDate.convertDateStringToYYYYMMDD("20010715");
		assertEquals(2001,YYYYMMDD[0]);
		assertEquals(07,YYYYMMDD[1]);
		assertEquals(15,YYYYMMDD[2]);
	}
	@Test
	void testCheckValidDateRangeInvalidYear() {
		int[] testDate = new int[3];
		testDate[0]= 1903;
		testDate[1] = 12;
		testDate[2] = 29;
		boolean dateRangeValid = inputDate.checkValidDateRange(testDate);
		assertFalse(dateRangeValid);
	}
	@Test
	void testCheckValidDateRangeInvalidMonth() {
		int[] testDate = new int[3];
		testDate[0]= 1977;
		testDate[1] = 14;
		testDate[2] = 13;
		boolean dateRangeValid = inputDate.checkValidDateRange(testDate);
		assertFalse(dateRangeValid);
	}
	@Test
	void testCheckValidDateRangeInvalidDay() {
		int[] testDate = new int[3];
		testDate[0]= 1987;
		testDate[1] = 02;
		testDate[2] = 29;
		boolean dateRangeValid = inputDate.checkValidDateRange(testDate);
		assertFalse(dateRangeValid);
	}
	@Test
	void testCheckValidDateRangeValidDate() {
		int[] testDate = new int[3];
		testDate[0]= 1987;
		testDate[1] = 02;
		testDate[2] = 05;
		boolean dateRangeValid = inputDate.checkValidDateRange(testDate);
		assertTrue(dateRangeValid);
	}
	@Test
	void testCheckDateInCSV() {
		String line = "19430513,69.0,83.0,71.2,84.6";
		Date day = inputDate.getDateWeatherData();
		day.setDateString("19430513");
		inputDate.checkDateInCSVForTemp(line);
		assertTrue("69.0".equals(day.getPredLow()));
		assertTrue("83.0".equals(day.getPredHigh()));
		assertTrue("71.2".equals(day.getRealLow()));
		assertTrue("84.6".equals(day.getRealHigh()));
	}
	@Test
	void testSetWeatherData() {
		String line = "19840408,72.0,84.0,69.8,82.7";
		inputDate.setTempData(line);
		Date day = inputDate.getDateWeatherData();
		assertTrue("72.0".equals(day.getPredLow()));
		assertTrue("84.0".equals(day.getPredHigh()));
		assertTrue("69.8".equals(day.getRealLow()));
		assertTrue("82.7".equals(day.getRealHigh()));
	}
	
}
