package dto;

public class HotelActivityDto {
	private int hotelNumber;
	private String activityName;
	public HotelActivityDto() {
		super();
	}
	public HotelActivityDto(int hotelNumber, String activityName) {
		super();
		this.hotelNumber = hotelNumber;
		this.activityName = activityName;
	}
	public int getHotelNumber() {
		return hotelNumber;
	}
	public void setHotelNumber(int hotelNumber) {
		this.hotelNumber = hotelNumber;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	@Override
	public String toString() {
		return "HotelActivityDto [hotelNumber=" + hotelNumber + ", activityName=" + activityName + "]";
	}
	
}
