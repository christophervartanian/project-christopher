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
		searchForDateInCSV();
	}

	public void promptUserForDate() {
		System.out.println("Please enter a date between 1940/05/16 and 2026/12/31 with the format YYYYMMDD: ");
		checkInputValidFormat();
		inputDateScanner.close();
	}

	public void checkInputValidFormat() {
		while (validInputDate == false) {
			String chosenDate = inputDateScanner.nextLine();
			boolean inputIsNumeric = isNumeric(chosenDate);
			if (chosenDate.length() == 8 && inputIsNumeric) {
				validInputDate = checkInputInValidDateRange(chosenDate);
			} else {
				System.out.println("Please enter a valid date");
			}
		}
	}
	public boolean isNumeric (String strToCheck) {
		boolean numeric = false;
		try {  
		    Integer.parseInt(strToCheck);  
		    numeric = true;
		  } catch(NumberFormatException e){  
		    numeric = false;
		  }  
		return numeric;
	}

	public boolean checkInputInValidDateRange(String chosenDate) {
		int[] YYYYMMDD = convertDateStringToYYYYMMDD(chosenDate);
		boolean isValidDate = checkValidDateRange(YYYYMMDD);
		if (isValidDate) {
			validInputDate = true;
			dateWeatherData.setDateString(chosenDate);
		}
		else {
			System.out.println("Please enter a valid date");
		}
		return validInputDate;
	}
	
	public int[] convertDateStringToYYYYMMDD (String chosenDate) {
		int[] YYYYMMDD = new int[3];
		int year = Integer.parseInt(chosenDate.substring(0, 4));
		int month = Integer.parseInt(chosenDate.substring(4, 6));
		int day = Integer.parseInt(chosenDate.substring(6));
		YYYYMMDD[0] = (year);
		YYYYMMDD[1] = (month);
		YYYYMMDD[2] = (day);
		return YYYYMMDD;
		
	}
	
	public boolean checkValidDateRange (int[] YYYYMMDD) {
		boolean isValidDate = true;
		if ((YYYYMMDD[0] < 1940) || (YYYYMMDD[0] > 2026)) {
			isValidDate = false;
		} else if ((YYYYMMDD[1] < 01) || (YYYYMMDD[1] > 12)) {
			isValidDate = false;
		} else if ((YYYYMMDD[2] < 01) || (YYYYMMDD[2] > 31)) {
			isValidDate = false;
		}
		else if ((YYYYMMDD[0] == 1940) && YYYYMMDD[1] < 05 && YYYYMMDD[2] < 16) {
			isValidDate = false;
		}
		return isValidDate;
	}

	public void searchForDateInCSV() {
		while (weatherFileScanner.hasNextLine()) {
			String line = weatherFileScanner.nextLine();
			checkDateInCSV(line);
		}
		weatherFileScanner.close();
	}

	private void checkDateInCSV(String line) {
		if (line.contains(dateWeatherData.getDateString())) {
			setWeatherData(line);
		}
	}

	private void setWeatherData(String line) {
		String[] contents = line.split(",");
		dateWeatherData.setPredictedTemperatures(contents[1], contents[2]);
		dateWeatherData.setRealTemperatures(contents[3], contents[4]);
		printDateWeatherData();
	}

	public void printDateWeatherData() {
		System.out.println("Date: " + dateWeatherData.getDateString());
		System.out.println("Real Temperature Low: " + dateWeatherData.getPredLow());
		System.out.println("Real Temperature High: " + dateWeatherData.getPredHigh());
		System.out.println("Predicted Temperature Low: " + dateWeatherData.getRealLow());
		System.out.println("Predicted Temperature High: " + dateWeatherData.getRealHigh());
	}

}

