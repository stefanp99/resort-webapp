package model;

import java.util.List;

public class Activity {
	private int activityId;
	private String activityName;
	private int maxParticipants;
	private List<String> activityDays;
	private boolean indoor;
	private int hotelNumber;

//	public List<String> getListOfActivityDays(){
//		return Arrays.asList(activityDays.split("/", -1));
//	}
//	
//	public boolean isIndoor() {
//		return indoor == 1;
//	}

	public Activity() {
		super();
	}

	public Activity(int activityId, String activityName, int maxParticipants, List<String> activityDays, boolean indoor,
			int hotelNumber) {
		super();
		this.activityId = activityId;
		this.activityName = activityName;
		this.maxParticipants = maxParticipants;
		this.activityDays = activityDays;
		this.indoor = indoor;
		this.hotelNumber = hotelNumber;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public int getMaxParticipants() {
		return maxParticipants;
	}

	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}

	public List<String> getActivityDays() {
		return activityDays;
	}

	public void setActivityDays(List<String> activityDays) {
		this.activityDays = activityDays;
	}

	public boolean isIndoor() {
		return indoor;
	}

	public void setIndoor(boolean indoor) {
		this.indoor = indoor;
	}

	public int getHotelNumber() {
		return hotelNumber;
	}

	public void setHotelNumber(int hotelNumber) {
		this.hotelNumber = hotelNumber;
	}

	@Override
	public String toString() {
		return "Activity [activityId=" + activityId + ", activityName=" + activityName + ", maxParticipants="
				+ maxParticipants + ", activityDays=" + activityDays + ", indoor=" + indoor + ", hotelNumber="
				+ hotelNumber + "]";
	}

}
