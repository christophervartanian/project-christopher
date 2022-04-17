package src;

import src.UserInputDate;
import java.io.FileNotFoundException;

public class WeatherData {
	 public static void main(String[] args) throws FileNotFoundException {
		 UserInputDate userInput = new UserInputDate();
		 userInput.gatherUserInput();
	 }
}
