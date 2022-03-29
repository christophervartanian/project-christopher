package src;
import java.io.*;
import java.util.Scanner;

public class UserInputDate {
	private Date dateWeatherData;
	private Scanner weatherFileScanner;
	private boolean validInputDate;
	private Scanner inputDateScanner;
	
	public UserInputDate() throws FileNotFoundException {
		this.dateWeatherData = new Date();
		this.weatherFileScanner = new Scanner(new File("data/H_Temp.csv"));
		this.validInputDate = false;
		this.inputDateScanner = new Scanner(System.in);
	}
	
	public Date getDateWeatherData() {
		return this.dateWeatherData;
	}
	
	public void gatherUserInput() {
		weatherFileScanner.useDelimiter(",");
		promptUserForDate();
		setDateWeatherData();
	}

	public void promptUserForDate() {
		System.out.println("Please enter a date between 1940 and 2026 with the format YYYYMMDD: ");
		checkInputValidFormat();
		inputDateScanner.close();
	}

	public void checkInputValidFormat() {
		while (validInputDate == false) {
			String chosenDate = inputDateScanner.nextLine();
			if (chosenDate.length() == 8) {
				validInputDate = checkInputInValidDateRange(chosenDate);
			} else {
				System.out.println("Please enter a valid date");
			}
		}
	}

	public boolean checkInputInValidDateRange(String chosenDate) {
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
			validInputDate = true;
			dateWeatherData.setDateString(chosenDate);
		}
		return validInputDate;
	}

	public void setDateWeatherData() {
		while (weatherFileScanner.hasNextLine()) {
			String line = weatherFileScanner.nextLine();
			if (line.contains(dateWeatherData.getDateString())) {
				String[] contents = line.split(",");
				dateWeatherData.setPredictedTemperatures(contents[1], contents[2]);
				dateWeatherData.setRealTemperatures(contents[3], contents[4]);
				printDateWeatherData();
			}
		}
		weatherFileScanner.close();
	}

	public void printDateWeatherData() {
		System.out.println("Date: " + dateWeatherData.getDateString());
		System.out.println("Real Temperature Low: " + dateWeatherData.getPredLow());
		System.out.println("Real Temperature High: " + dateWeatherData.getPredHigh());
		System.out.println("Predicted Temperature Low: " + dateWeatherData.getRealLow());
		System.out.println("Predicted Temperature High: " + dateWeatherData.getRealHigh());
	}
}

