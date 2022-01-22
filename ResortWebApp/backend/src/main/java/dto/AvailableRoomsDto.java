package dto;

public class AvailableRoomsDto {
	private int roomNumber;
	private int guestNumber;
	private int maxNumberOfGuests;
	
	public AvailableRoomsDto() {
		super();
	}

	public AvailableRoomsDto(int roomNumber, int guestNumber, int maxNumberOfGuests) {
		super();
		this.roomNumber = roomNumber;
		this.guestNumber = guestNumber;
		this.maxNumberOfGuests = maxNumberOfGuests;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getGuestNumber() {
		return guestNumber;
	}

	public void setGuestNumber(int guestNumber) {
		this.guestNumber = guestNumber;
	}

	public int getMaxNumberOfGuests() {
		return maxNumberOfGuests;
	}

	public void setMaxNumberOfGuests(int maxNumberOfGuests) {
		this.maxNumberOfGuests = maxNumberOfGuests;
	}

	@Override
	public String toString() {
		return "AvailableRoomsDto [roomNumber=" + roomNumber + ", guestNumber=" + guestNumber + ", maxNumberOfGuests="
				+ maxNumberOfGuests + "]";
	}
		
}
