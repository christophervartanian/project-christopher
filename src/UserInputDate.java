package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserInputDate {
	private Date dateWeatherData;
	private Scanner tempFileScanner;
	private Scanner precipFileScanner;
	private boolean validInputDate;
	private Scanner inputDateScanner;
	private boolean inputInRange;
	
	public UserInputDate() throws FileNotFoundException {
		this.dateWeatherData = new Date();
		this.tempFileScanner = new Scanner(new File("data/H_Temp.csv"));
		this.precipFileScanner = new Scanner(new File("data/H_Precip.csv"));
		this.validInputDate = false;
		this.inputDateScanner = new Scanner(System.in);
		this.inputInRange = false;
	}
	
	public Date getDateWeatherData() {
		return this.dateWeatherData;
	}
	
	public void gatherUserInput() throws FileNotFoundException {
		tempFileScanner.useDelimiter(",");
		precipFileScanner.useDelimiter(",");
		promptUserForDate();
		searchForDateInTempCSV();
	}

	public void promptUserForDate() throws FileNotFoundException {
		System.out.println("Please enter a date between 1940/05/16 and 2026/12/31 with the format YYYYMMDD: ");
		while (validInputDate == false) { 
			String chosenDate = inputDateScanner.nextLine();
			checkInputValidFormat(chosenDate);
		}
		inputDateScanner.close();
	}

	public void checkInputValidFormat(String chosenDate) throws FileNotFoundException {
		boolean inputIsNumeric = isNumeric(chosenDate);
		if (chosenDate.length() == 8 && inputIsNumeric) {
			checkInputInValidDateRange(chosenDate);
		} else {
			System.out.println("Please enter a valid date");
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
	
	public boolean checkInputInValidDateRange(String chosenDate) throws FileNotFoundException {
		inputInRange = setupCSVSearch(chosenDate);
		if (inputInRange) {
			validInputDate = true;
			dateWeatherData.setDateString(chosenDate);
			
		}
		else {
			System.out.println("Please enter a valid date");
		}
		return inputInRange;
	}

	public boolean setupCSVSearch(String chosenDate) throws FileNotFoundException {
		Scanner tempFileScannerForValidation = new Scanner(new File("data/H_Temp.csv"));
		while (tempFileScannerForValidation.hasNextLine()) {
			String line = tempFileScannerForValidation.nextLine();
			if (line.contains(chosenDate)) {
				this.inputInRange = true;
			}
		}
		return inputInRange;
	}

	public void searchForDateInTempCSV() {
		while (tempFileScanner.hasNextLine()) {
			String line = tempFileScanner.nextLine();
			checkDateInCSVForTemp(line);
		}
		tempFileScanner.close();
	}


	public void checkDateInCSVForTemp(String line) {
		if (line.contains(dateWeatherData.getDateString())) {
			setTempData(line);
		}
	}
	

	public void setTempData(String line) {
		String[] contents = line.split(",");
		dateWeatherData.setPredictedTemperatures(contents[1], contents[2]);
		dateWeatherData.setRealTemperatures(contents[3], contents[4]);
		searchForDateInPrecipCSV();
	}
	
	public void searchForDateInPrecipCSV() {
		while (precipFileScanner.hasNextLine()) {
			String line = precipFileScanner.nextLine();
			checkDateInCSVForPrecip(line);
		}
		precipFileScanner.close();
	}
	
	public void checkDateInCSVForPrecip(String line) {
		if (line.contains(dateWeatherData.getDateString())) {
			setPrecipData(line);
		}
	}

	public void setPrecipData(String line) {
		String[] contents = line.split(",");
		dateWeatherData.setPredictedPrecipitation(contents[1]);
		dateWeatherData.setRealPrecipitation(contents[2]);
		printDateWeatherData();
	}

	private String checkEmptyData(String data) {
		if (data.equals(" ") || data.equals("")) {
			return "N/A";
		}
		else {
			return data;
		}
	}

	public void printDateWeatherData() {
		System.out.println("Date: " + checkEmptyData(dateWeatherData.getDateString()));
		System.out.println("Predicted Temperature Low: " + checkEmptyData(dateWeatherData.getPredLow()));
		System.out.println("Predicted Temperature High: " + checkEmptyData(dateWeatherData.getPredHigh()));
		System.out.println("Real Temperature Low: " + checkEmptyData(dateWeatherData.getRealLow()));
		System.out.println("Real Temperature High: " + checkEmptyData(dateWeatherData.getRealHigh()));
		System.out.println("Predicted Year-to-Date Precipitation: " + checkEmptyData(dateWeatherData.getPredPrecip()));
		System.out.println("Real Year-to-Date Precipitation: " + checkEmptyData(dateWeatherData.getRealPrecip()));
	}
}


