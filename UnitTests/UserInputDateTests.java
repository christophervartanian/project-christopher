package UnitTests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import src.UserInputDate;

public class UserInputDateTests {
	private UserInputDate inputDate;
	
	@BeforeEach 
	void setup() throws FileNotFoundException {
		inputDate = new UserInputDate();
		inputDate.location = "Dallas";
		inputDate.startDate = "19480509";
		inputDate.endDate = "19480510";
		
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
	void testHonoluluLocation() throws FileNotFoundException {
		inputDate.tempFileScanner = new Scanner(new File("data/Honolulu Temp and Precip.csv")) ;
		inputDate.location = "Honolulu";
		inputDate.searchInCSVAndSetValues();
		assertEquals(inputDate.highPredictedTempAvg, 82.0);
		assertEquals(inputDate.lowPredictedTempAvg, 71.0);
		assertEquals(inputDate.lowActualTempAvg, 71.0);
		assertEquals(inputDate.highActualTempAvg, 84.4);
		assertEquals(inputDate.predictedPrecipAvg, 12.13);
		assertEquals(inputDate.actualPrecipAvg, 7.17);
	}
	@Test
	void testDallasLocation() throws FileNotFoundException{
		inputDate.tempFileScanner = new Scanner(new File("data/Dallas Temp and Precip.csv"));
		inputDate.location = "Dallas";
		inputDate.searchInCSVAndSetValues();
		assertEquals(inputDate.highPredictedTempAvg, 84.0);
		assertEquals(inputDate.lowPredictedTempAvg, 72.0);
		assertEquals(inputDate.lowActualTempAvg, 89.0);
		assertEquals(inputDate.highActualTempAvg, 83.0);
		assertEquals(inputDate.predictedPrecipAvg, 9.0);
		assertEquals(inputDate.actualPrecipAvg, 8.0);
		
	}

	@Test
	void testMiamiLocation() throws FileNotFoundException{
		inputDate.tempFileScanner = new Scanner(new File("data/Miami Temp and Precip.csv"));
		inputDate.location = "Miami";
		inputDate.searchInCSVAndSetValues();
		assertEquals(inputDate.highPredictedTempAvg, 88.0);
		assertEquals(inputDate.lowPredictedTempAvg, 75.0);
		assertEquals(inputDate.lowActualTempAvg, 72.0);
		assertEquals(inputDate.highActualTempAvg, 81.0);
		assertEquals(inputDate.predictedPrecipAvg, 11.0);
		assertEquals(inputDate.actualPrecipAvg, 6.0);
		
	}
		
	
}
