package src;

public class Date {
	private String dateString;
	private String predTempLow;
	private String predTempHigh;
	private String realTempLow;
	private String realTempHigh;
	

	public Date() {
		this.dateString = "";
		this.predTempLow = "";
		this.predTempHigh = "";
		this.realTempLow = "";
		this.realTempHigh = "";
	}

	public void setDateString(String inputDate) {
		this.dateString = inputDate;
	}
	public void setPredictedTemperatures (String predLow, String predHigh) {
		this.predTempLow = predLow;
		this.predTempHigh = predHigh;
	}
	public void setRealTemperatures (String realLow, String realHigh) {
		this.realTempLow = realLow;
		this.realTempHigh = realHigh;
	}

	public String getDateString() {
		return this.dateString;
	}
	
	public String getPredLow() {
		return this.predTempLow;
	}
	public String getPredHigh() {
		return this.predTempHigh;
	}
	public String getRealLow() {
		return this.realTempLow;
	}
	public String getRealHigh() {
		return this.realTempHigh;
	}
}
