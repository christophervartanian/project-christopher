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
	void testCheckInputInValidDateRangeInvalidYear() throws FileNotFoundException {
		String testDate = "19031229";
		boolean dateRangeValid = inputDate.checkInputInValidDateRange(testDate);
		assertFalse(dateRangeValid);
	}
	@Test
	void testCheckInputInValidDateRangeInvalidMonth() throws FileNotFoundException {
		String testDate = "19771413";
		boolean dateRangeValid = inputDate.checkInputInValidDateRange(testDate);
		assertFalse(dateRangeValid);
	}
	@Test
	void testCheckInputInValidDateRangeInvalidDay() throws FileNotFoundException {
		String testDate = "19870229";
		boolean dateRangeValid = inputDate.checkInputInValidDateRange(testDate);
		assertFalse(dateRangeValid);
	}
	@Test
	void testCheckInputInValidDateRangeValidDate() throws FileNotFoundException {
		String testDate = "19870205";
		boolean dateRangeValid = inputDate.checkInputInValidDateRange(testDate);
		assertTrue(dateRangeValid);
	}
	@Test
	void testCheckInputInCSVTrue() throws FileNotFoundException {
		String testDate = "20000229";
		boolean inCSV = inputDate.checkInputInCSV(testDate);
		assertTrue(inCSV);
	}
	@Test
	void testCheckInputInCSVFalse() throws FileNotFoundException {
		String testDate = "19400515";
		boolean inCSV = inputDate.checkInputInCSV(testDate);
		assertFalse(inCSV);
	}
	@Test
	void testCheckDateInCSVForTemp() {
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
	void testSetTempData() {
		String line = "19840408,72.0,84.0,69.8,82.7";
		inputDate.setTempData(line);
		Date day = inputDate.getDateWeatherData();
		assertTrue("72.0".equals(day.getPredLow()));
		assertTrue("84.0".equals(day.getPredHigh()));
		assertTrue("69.8".equals(day.getRealLow()));
		assertTrue("82.7".equals(day.getRealHigh()));
	}
	@Test
	void testCheckDateInCSVForPrecip() {
		String line = "19430513,18.06,7.26";
		Date day = inputDate.getDateWeatherData();
		day.setDateString("19430513");
		inputDate.checkDateInCSVForPrecip(line);
		assertTrue("18.06".equals(day.getPredPrecip()));
		assertTrue("7.26".equals(day.getRealPrecip()));
	}
	@Test
	void testSetPrecipData() {
		String line = "19840408,1.98,6.45";
		inputDate.setPrecipData(line);
		Date day = inputDate.getDateWeatherData();
		assertTrue("1.98".equals(day.getPredPrecip()));
		assertTrue("6.45".equals(day.getRealPrecip()));
	}
	@Test
	void testCheckEmptyDateEmpty() {
		String emptyData = "";
		emptyData = inputDate.checkEmptyData(emptyData);
		assertTrue("N/A".equals(emptyData));
	}
	@Test
	void testCheckEmptyDateNotEmpty() {
		String notEmptyData = "17.3";
		notEmptyData = inputDate.checkEmptyData(notEmptyData);
		assertTrue("17.3".equals(notEmptyData));
	}
	
}
