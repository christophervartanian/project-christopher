package src;

import java.io.FileNotFoundException;

public class WeatherData {
	
	 
	 public static void main(String[] args) throws FileNotFoundException {
		 
		 System.out.println("Hello");
		 UserInputDate userInput = new UserInputDate();
		 userInput.gatherUserInput();
		 userInput.searchInCSVAndSetValues();
		 System.out.println();
		 System.out.println("Weather Report:");
		 userInput.printDateWeatherData();
		 System.out.println();
		 System.out.println("Breakdown:");
		 userInput.precipBreakdown();
		 userInput.tempBreakdown();
		 

	
		 
	 }
}
