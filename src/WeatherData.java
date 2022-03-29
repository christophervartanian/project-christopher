package src;

import java.io.*;
import java.util.Scanner;

public class WeatherData {

	public static void main(String[] args) throws FileNotFoundException {
		Date dateWeatherData = new Date();
		Scanner weatherFileScanner = new Scanner(new File("data/H_Temp.csv"));
		weatherFileScanner.useDelimiter(",");
		promptUserForDate(dateWeatherData);
		setDateWeatherData(dateWeatherData, weatherFileScanner);
	}

	private static void promptUserForDate(Date dateWeatherData) {
		boolean validInputDate = false;
		System.out.println("Please enter a date between 1940 and 2026 with the format YYYYMMDD: ");
		Scanner inputDateScanner = new Scanner(System.in);
		checkInputValidFormat(dateWeatherData, validInputDate, inputDateScanner);
		inputDateScanner.close();
	}

	private static void checkInputValidFormat(Date dateWeatherData, boolean validInput, Scanner inputDateScanner) {
		while (validInput == false) {
			String chosenDate = inputDateScanner.nextLine();
			if (chosenDate.length() == 8) {
				validInput = checkInputInValidDateRange(dateWeatherData, validInput, chosenDate);
			} else {
				System.out.println("Please enter a valid date");
			}
		}
	}

	private static boolean checkInputInValidDateRange(Date dateWeatherData, boolean validInput, String chosenDate) {
		int year = Integer.parseInt(chosenDate.substring(0, 4));
		int month = Integer.parseInt(chosenDate.substring(4, 6));
		int day = Integer.parseInt(chosenDate.substring(6));
		if ((year < 1940) || (year > 2026)) {
			System.out.println("Please enter a valid date");
		} else if ((month < 01) || (month > 12)) {
			System.out.println("Please enter a valid date");
		} else if ((day < 01) || (day > 31)) {
			System.out.println("Please enter a valid date");
		} else {
			validInput = true;
			dateWeatherData.setDateString(chosenDate);
		}
		return validInput;
	}

	private static void setDateWeatherData(Date dateWeatherData, Scanner weatherFileScanner) {
		while (weatherFileScanner.hasNextLine()) {
			String line = weatherFileScanner.nextLine();
			if (line.contains(dateWeatherData.getDateString())) {
				String[] contents = line.split(",");
				dateWeatherData.setPredictedTemperatures(contents[1], contents[2]);
				dateWeatherData.setRealTemperatures(contents[3], contents[4]);
				printDateWeatherData(dateWeatherData);
			}
		}
		weatherFileScanner.close();
	}

	private static void printDateWeatherData(Date dateWeatherData) {
		System.out.println("Date: " + dateWeatherData.getDateString());
		System.out.println("Real Temperature Low: " + dateWeatherData.getPredLow());
		System.out.println("Real Temperature High: " + dateWeatherData.getPredHigh());
		System.out.println("Predicted Temperature Low: " + dateWeatherData.getRealLow());
		System.out.println("Predicted Temperature High: " + dateWeatherData.getRealHigh());
	}
}
