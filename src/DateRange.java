package src;

import java.util.ArrayList;
import java.util.List;

public class DateRange {
	private List<Date> dates;
	private Double avgLowPredTemp;
	private Double avgHighPredTemp;
	private Double avgLowRealTemp;
	private Double avgHighRealTemp;
	private Double totalPredPrecip;
	private Double totalRealPrecip;
	
	public DateRange() {
		this.dates = new ArrayList<>();
		this.avgLowPredTemp = 0.0;
		this.avgHighPredTemp = 0.0;
		this.avgLowRealTemp = 0.0;
		this.avgHighRealTemp = 0.0;
		this.totalPredPrecip = 0.0;
		this.totalRealPrecip = 0.0;
	}
	
	public List<Date> getDates() {
		return dates;
	}
	public Double getAvgLowPredTemp() {
		return avgLowPredTemp;
	}
	public Double getAvgHighPredTemp() {
		return avgHighPredTemp;
	}
	public Double getAvgLowRealTemp() {
		return avgLowRealTemp;
	}
	public Double getAvgHighRealTemp() {
		return avgHighRealTemp;
	}
	public Double getTotalPredPrecip() {
		return totalPredPrecip;
	}
	public Double getTotalRealPrecip() {
		return totalRealPrecip;
	}


	public void addDateToDates(Date day) {
		dates.add(day);
	}
	public void computeAverageTemps() {
		for (Date day: dates) {
			this.avgLowPredTemp += Double.parseDouble(day.getPredLow());
			this.avgHighPredTemp += Double.parseDouble(day.getPredHigh());
			this.avgLowRealTemp += Double.parseDouble(day.getRealLow());
			this.avgHighRealTemp += Double.parseDouble(day.getRealHigh());
		}
		this.avgLowPredTemp = this.avgLowPredTemp/7.0;
		this.avgHighPredTemp = this.avgHighPredTemp/7.0;
		this.avgLowRealTemp = this.avgLowRealTemp/7.0;
		this.avgHighRealTemp = this.avgHighRealTemp/7.0;
	}
	
	//Currently doesn't function properly because precipitation is YTD measurement
	//Plan on fixing for iteration 3
	public void computeTotalPrecips() {
		for (Date day:dates) {
			this.totalPredPrecip += Double.parseDouble(day.getPredPrecip()) - this.totalPredPrecip;
			this.totalRealPrecip += Double.parseDouble(day.getRealPrecip()) - this.totalRealPrecip;
		}
	}
	
	
	
}
