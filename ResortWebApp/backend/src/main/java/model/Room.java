package model;

public class Room {
	private int roomNumber;
	private String roomSize;
	private int maxNumberOfGuests;
	private int hasBalcony;
	private int pricePerNight;
	private int hotelNumber;
	
	public Room() {
		super();
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public int getMaxNumberOfGuests() {
		return maxNumberOfGuests;
	}

	public void setMaxNumberOfGuests(int maxNumberOfGuests) {
		this.maxNumberOfGuests = maxNumberOfGuests;
	}

	public int getHasBalcony() {
		return hasBalcony;
	}

	public void setHasBalcony(int hasBalcony) {
		this.hasBalcony = hasBalcony;
	}

	public int getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(int pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public int getHotelNumber() {
		return hotelNumber;
	}

	public void setHotelNumber(int hotelNumber) {
		this.hotelNumber = hotelNumber;
	}

	public Room(int roomNumber, String roomSize, int maxNumberOfGuests, int hasBalcony, int pricePerNight,
			int hotelNumber) {
		super();
		this.roomNumber = roomNumber;
		this.roomSize = roomSize;
		this.maxNumberOfGuests = maxNumberOfGuests;
		this.hasBalcony = hasBalcony;
		this.pricePerNight = pricePerNight;
		this.hotelNumber = hotelNumber;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomSize=" + roomSize + ", maxNumberOfGuests=" + maxNumberOfGuests
				+ ", hasBalcony=" + hasBalcony + ", pricePerNight=" + pricePerNight + ", hotelNumber=" + hotelNumber
				+ "]";
	}
	
}
