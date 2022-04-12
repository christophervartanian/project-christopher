package UnitTests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.Date;
import src.DateRange;

public class DateRangeTests {
	private DateRange dr;
	
	@BeforeEach
	void setup() {
		dr = new DateRange();
	}
	@Test
	void testComputeAverageTemps() {
		Date day = new Date();
		day.setPredictedTemperatures("49.0", "70.0");
		day.setRealTemperatures("56.0", "77.0");
		for (int i=0; i < 7; ++i) {
			dr.addDateToDates(day);
		}
		dr.computeAverageTemps();
		assertEquals(49.0,dr.getAvgLowPredTemp());
		assertEquals(70.0,dr.getAvgHighPredTemp());
		assertEquals(56.0,dr.getAvgLowRealTemp());
		assertEquals(77.0,dr.getAvgHighRealTemp());
	}
	@Test
	void testComputeTotalPrecips() {
		Date day = new Date();
		day.setPredictedPrecipitation("4.7");
		day.setRealPrecipitation("5.5");
		for (int i=0; i < 7; ++i) {
			dr.addDateToDates(day);
		}
		dr.computeTotalPrecips();
		assertEquals(4.7,dr.getTotalPredPrecip());
		assertEquals(5.5,dr.getTotalRealPrecip());
	}

}
