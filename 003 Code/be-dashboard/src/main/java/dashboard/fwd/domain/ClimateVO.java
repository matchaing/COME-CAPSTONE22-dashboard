package dashboard.fwd.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ClimateVO implements Serializable{
	private static final long serialVersionUID = -362832784958159537L;
	
	private int climateDataNo;
	private String regionCode;
	private String dailyDataDt;
	private String dailyDataTime;
	private String humidity;
	private String temperature;
	
	// Climate Data No
	public int getClimateDataNo() {
		return climateDataNo;
	}
	public void setClimateDataNo(int climateDataNo) {
		this.climateDataNo = climateDataNo;
	}
	
	// Region Code
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	// Daily data dt
	public String getDailyDt() {
		return dailyDataDt;
	}
	public void setDailyDt(String dailyDataDt) {
		this.dailyDataDt = dailyDataDt;
	}
	
	// daily data time
	public String getDailyTime() {
		return dailyDataTime;
	}
	public void setDailyTime(String dailyDataTime) {
		this.dailyDataTime = dailyDataTime;
	}
	
	// humidity
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}	
	
	// temperature
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
