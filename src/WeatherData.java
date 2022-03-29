package src;

import java.io.*;
import java.util.Scanner;




public class WeatherData {
	private String date;

	public WeatherData() {
		this.date = "";
	}

	private void setDate(String inputDate) {
		this.date = inputDate;
	}

	private String getDate() {
		return this.date;
	}

	public static void main(String[] args) throws FileNotFoundException {

		WeatherData dateWeatherData = new WeatherData();
		Scanner sc = new Scanner(new File("data/H_Temp.csv"));
		sc.useDelimiter(",");
		promptUserForDate(dateWeatherData);
		printDateWeather(dateWeatherData, sc);
		sc.close();

	}

	private static void promptUserForDate(WeatherData dateWeather) {
		boolean validInputDate = false;
		System.out.println("Please enter a date between 1940 and 2026 with the format YYYYMMDD: ");
		Scanner dateIn = new Scanner(System.in);
		storeInputDate(dateWeather, validInputDate, dateIn);
		dateIn.close();
	}

	private static void storeInputDate(WeatherData dateWeather, boolean validInput, Scanner dateIn) {
		while (validInput == false) {
			String chosenDate = dateIn.nextLine();
			if (chosenDate.length() == 8) {
				validInput = checkInputInValidDateRange(dateWeather, validInput, chosenDate);
			} else {
				System.out.println("Please enter a valid date");
			}
		}
	}

	private static boolean checkInputInValidDateRange(WeatherData dateWeather, boolean validInput, String chosenDate) {
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
			dateWeather.setDate(chosenDate);
		}
		return validInput;
	}

	private static void printDateWeather(WeatherData inputedDate, Scanner sc) {
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.contains(inputedDate.getDate())) {
				String[] contents = line.split(",");
				System.out.println("Date: " + contents[0]);
				System.out.println("Real Temperature Low: " + contents[1]);
				System.out.println("Real Temperature High: " + contents[2]);
				System.out.println("Predicted Temperature Low: " + contents[3]);
				System.out.println("Predicted Temperature High: " + contents[4]);

			}
		}
	}
}
