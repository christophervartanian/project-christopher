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
	void testCheckRange() throws FileNotFoundException {
		inputDate.startDate = "20200505";
		inputDate.endDate = "20200510";
		inputDate.searchInCSVAndSetValues();
		assertEquals(inputDate.highPredictedTempAvg, 84.8);
		assertEquals(inputDate.lowPredictedTempAvg, 70.0);
		assertEquals(inputDate.lowActualTempAvg, 70.92);
		assertEquals(inputDate.highActualTempAvg, 84.28);
		assertEquals(inputDate.predictedPrecipAvg, 8.338);
		assertEquals(inputDate.actualPrecipAvg, 7.118);
		
		
		
	}
		
	
}
