package UnitTests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import src.Date;

public class DateTests {
	@Test
	void testSetDateString() {
		Date d = new Date();
		d.setDateString("19990303");
		assertTrue("19990303".equals(d.getDateString()));
	}
	
	@Test
	void testSetPredictedTemperatures() {
		Date d = new Date();
		d.setPredictedTemperatures("72.0", "87.2");
		assertTrue("72.0".equals(d.getPredLow()));
		assertTrue("87.2".equals(d.getPredHigh()));
	}
	@Test
	void testSetRealTemperatures() {
		Date d = new Date();
		d.setRealTemperatures("65.0", "81.2");
		assertTrue("65.0".equals(d.getRealLow()));
		assertTrue("81.2".equals(d.getRealHigh()));
	}
}
