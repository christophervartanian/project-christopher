package src;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;

public class UserInputDateRange {
//	private Date dateWeatherData;
//	private Scanner tempFileScanner;
//	private Scanner precipFileScanner;
//	private boolean validInputDate;
//	private Scanner inputDateScanner;
//	
//	public UserInputDateRange() throws FileNotFoundException {
//		this.dateWeatherData = new Date();
//		this.tempFileScanner = new Scanner(new File("data/H_Temp.csv"));
//		this.precipFileScanner = new Scanner(new File("data/H_Precip.csv"));
//		this.validInputDate = false;
//		this.inputDateScanner = new Scanner(System.in);
//	}
//	
//	public Date getDateWeatherData() {
//		return this.dateWeatherData;
//	}
//	
//	public void gatherUserInput() {
//		tempFileScanner.useDelimiter(",");
//		precipFileScanner.useDelimiter(",");
//		promptUserForDate();
//		System.out.println(validInputDate);
//	}
//
//	public void promptUserForDate() {
//		System.out.println("Please enter a date between 1940/05/16 and 2026/12/31 with the format YYYYMMDD: ");
//		while (validInputDate == false) { 
//			String chosenDate = inputDateScanner.nextLine();
//			checkInputValidFormat(chosenDate);
//			
//		}
//		inputDateScanner.close();
//	}
//
//	public void checkInputValidFormat(String chosenDate) {
//		boolean inputIsNumeric = isNumeric(chosenDate);
//		if (chosenDate.length() == 8 && inputIsNumeric) {
//			dateWeatherData.setDateString(chosenDate);
//			checkInputInValidDateRange();
//		} else {
//			System.out.println("Please enter a valid date");
//		}
//	}
//	
//	public boolean isNumeric (String strToCheck) {
//		boolean numeric = false;
//		try {  
//		    Integer.parseInt(strToCheck);  
//		    numeric = true;
//		  } catch(NumberFormatException e){  
//		    numeric = false;
//		  }  
//		return numeric;
//	}
//
//	public void checkInputInValidDateRange() {
//		while (tempFileScanner.hasNextLine()) {
//			String line = tempFileScanner.nextLine();
//			checkIfInputInCSV(line);
//		}
//		tempFileScanner.close();
//	}
//
//
//	public void checkIfInputInCSV(String line) {
//		if (line.contains(dateWeatherData.getDateString())) {
//			this.validInputDate = true;
//		}
//	}
//	public static void main(String[] args) throws FileNotFoundException {
//		UserInputDateRange uidr = new UserInputDateRange();
//		uidr.gatherUserInput();
//	}

}
