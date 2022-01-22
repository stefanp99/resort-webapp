package model;

public class Hotel {
	private int hotelNumber;
	private boolean hasPool;
	private boolean hasRestaurant;
	
	public Hotel() {
		super();
	}

	public Hotel(int hotelNumber, boolean hasPool, boolean hasRestaurant) {
		super();
		this.hotelNumber = hotelNumber;
		this.hasPool = hasPool;
		this.hasRestaurant = hasRestaurant;
	}

	public int getHotelNumber() {
		return hotelNumber;
	}

	public void setHotelNumber(int hotelNumber) {
		this.hotelNumber = hotelNumber;
	}

	public boolean isHasPool() {
		return hasPool;
	}

	public void setHasPool(boolean hasPool) {
		this.hasPool = hasPool;
	}

	public boolean isHasRestaurant() {
		return hasRestaurant;
	}

	public void setHasRestaurant(boolean hasRestaurant) {
		this.hasRestaurant = hasRestaurant;
	}

	@Override
	public String toString() {
		return "Hotel [hotelNumber=" + hotelNumber + ", hasPool=" + hasPool + ", hasRestaurant=" + hasRestaurant + "]";
	}
	
}
