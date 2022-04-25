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
	private String startDate;
	private String endDate;
	private double highPredictedTempSum;
	private double lowPredictedTempSum;
	private double highActualTempSum;
	private double lowActualTempSum;
	private double actualPrecipSum;
	private double predictedPrecipSum;
	private double daysInRange;
	private double highPredictedTempAvg;
	private double lowPredictedTempAvg;
	private double lowActualTempAvg;
	private double highActualTempAvg;
	private double actualPrecipAvg;
	private double predictedPrecipAvg;

	public UserInputDate() throws FileNotFoundException {
		this.dateWeatherData = new Date();
		this.tempFileScanner = new Scanner(new File("data/H_Temp.csv"));
		this.precipFileScanner = new Scanner(new File("data/H_Precip.csv"));
		this.validInputDate = false;
		this.inputDateScanner = new Scanner(System.in);
		this.inputInRange = false;
		this.startDate = "";
		this.endDate = "";
		this.highPredictedTempSum = 0;
		this.lowPredictedTempSum = 0;
		this.lowActualTempSum = 0;
		this.highActualTempSum = 0;
		this.actualPrecipSum = 0;
		this.predictedPrecipSum = 0;
		this.daysInRange = 0;
		this.highPredictedTempAvg = 0;
		this.lowPredictedTempAvg = 0;
		this.lowActualTempAvg = 0;
		this.highActualTempAvg = 0;
		this.actualPrecipAvg = 0;
		this.predictedPrecipAvg = 0;
	}

	public Date getDateWeatherData() {
		return this.dateWeatherData;
	}

	public void gatherUserInput() throws FileNotFoundException {
		tempFileScanner.useDelimiter(",");
		precipFileScanner.useDelimiter(",");
		promptUserForDate();
		checkDateInCSVForPrecip(startDate);
		checkDateInCSVForPrecip(endDate);
	}

	public void promptUserForDate() throws FileNotFoundException {
		System.out.println(
				"Please enter a date range between 1940/05/16 and 2026/12/31 with the format YYYYMMDD-YYYYMMDD: ");
		while (validInputDate == false) {
			String chosenDate = inputDateScanner.nextLine();
			String[] chosenRange = chosenDate.split("-");
			startDate = chosenRange[0];
			endDate = chosenRange[1];

			boolean validFirst = checkInputValidFormat(chosenRange[0]);
			boolean validSecond = checkInputValidFormat(chosenRange[1]);
			if (validFirst && validSecond) {
				this.validInputDate = true;
				this.dateWeatherData.setDateString(chosenDate);
			}
		}

		inputDateScanner.close();
	}

	public boolean checkInputValidFormat(String chosenDate) throws FileNotFoundException {
		boolean inputIsNumeric = isNumeric(chosenDate);
		if (chosenDate.length() == 8 && inputIsNumeric) {
			return checkInputInValidDateRange(chosenDate);

		} else {
			System.out.println("Please enter a valid date");
			return false;
		}
	}

	public boolean isNumeric(String strToCheck) {
		boolean numeric = false;
		try {
			Integer.parseInt(strToCheck);
			numeric = true;
		} catch (NumberFormatException e) {
			numeric = false;
		}
		return numeric;
	}

	public boolean checkInputInValidDateRange(String chosenDate) throws FileNotFoundException {
		inputInRange = checkInputInCSV(chosenDate);

		return inputInRange;
	}

	public boolean checkInputInCSV(String chosenDate) throws FileNotFoundException {
		Scanner tempFileScannerForValidation = new Scanner(new File("data/H_Temp.csv"));
		while (tempFileScannerForValidation.hasNextLine()) {
			String line = tempFileScannerForValidation.nextLine();
			if (line.contains(chosenDate)) {
				this.inputInRange = true;
			}
		}
		return inputInRange;
	}

	public void setTempAndPrecipData(String line) {
		String[] contents = line.split(",");
		lowPredictedTempSum = Double.parseDouble(contents[1]) + lowPredictedTempSum;
		highPredictedTempSum = Double.parseDouble(contents[2]) + highPredictedTempSum;
		lowActualTempSum = Double.parseDouble(contents[3]) + lowActualTempSum;
		highActualTempSum = Double.parseDouble(contents[4]) + highActualTempSum;
		actualPrecipSum = Double.parseDouble(contents[6]) + actualPrecipSum;
		predictedPrecipSum = Double.parseDouble(contents[5]) + predictedPrecipSum;
		daysInRange += 1;
	}

	public void searchInCSVAndSetValues() {
		boolean isStartDate = false;
		String line = "";
		while (tempFileScanner.hasNextLine() && !isStartDate) {

			line = tempFileScanner.nextLine();

			isStartDate = checkStartDateInCSV(line);
		}
		;
		boolean isEndDate = false;
		while (tempFileScanner.hasNextLine() && !isEndDate) {
			line = tempFileScanner.nextLine();
			setTempAndPrecipData(line);

			isEndDate = checkEndDateInCSV(line);

		}

		tempFileScanner.close();
		this.lowPredictedTempAvg = lowPredictedTempSum / daysInRange;
		this.highPredictedTempAvg = highPredictedTempSum / daysInRange;
		this.lowActualTempAvg = lowActualTempSum / daysInRange;
		this.highActualTempAvg = highActualTempSum / daysInRange;
		this.actualPrecipAvg = actualPrecipSum / daysInRange;
		this.predictedPrecipAvg = (predictedPrecipSum / daysInRange);
	}

	public boolean checkStartDateInCSV(String line) {
		if (line.contains(this.startDate)) {

			return true;
			// setTempData(line);
		}
		return false;
	}

	public boolean checkEndDateInCSV(String line) {
		if (line.contains(this.endDate)) {

			return true;
			// setTempData(line);
		}
		return false;
	}

	public void setTempData(String line) {
		String[] contents = line.split(",");
		lowPredictedTempSum = Integer.parseInt(contents[1]) + lowPredictedTempSum;
		highPredictedTempSum = Integer.parseInt(contents[2]) + highPredictedTempSum;
		lowActualTempSum = Integer.parseInt(contents[3]) + lowActualTempSum;
		highActualTempSum = Integer.parseInt(contents[4]) + highActualTempSum;

	}

	public void searchForDateInPrecipCSV() {
		while (tempFileScanner.hasNextLine()) {
			String line = tempFileScanner.nextLine();
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

	public String checkEmptyData(String data) {
		if (data.equals(" ") || data.equals("")) {
			return "N/A";
		} else {
			return data;
		}
	}

	public void printDateWeatherData() {
		System.out.println("Date: " + checkEmptyData(dateWeatherData.getDateString()));
		System.out.println("Predicted Temperature Range Low: " + this.lowPredictedTempAvg);
		System.out.println("Predicted Temperature Range High: " + this.highPredictedTempAvg);
		System.out.println("Real Temperature Range Low: " + this.lowActualTempAvg);
		System.out.println("Real Temperature Range High: " + this.highActualTempAvg);
		System.out.println("Predicted Precipitation Average: " + this.predictedPrecipAvg);
		System.out.println("Real Precipitation Average: " + this.actualPrecipAvg);
	}
}
