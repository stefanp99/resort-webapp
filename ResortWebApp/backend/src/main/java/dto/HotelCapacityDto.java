package dto;

public class HotelCapacityDto {
	private int hotelNumber;
	private int hotelCapacity;
	
	public HotelCapacityDto() {
		super();
	}

	public HotelCapacityDto(int hotelNumber, int hotelCapacity) {
		super();
		this.hotelNumber = hotelNumber;
		this.hotelCapacity = hotelCapacity;
	}

	public int getHotelNumber() {
		return hotelNumber;
	}

	public void setHotelNumber(int hotelNumber) {
		this.hotelNumber = hotelNumber;
	}

	public int getHotelCapacity() {
		return hotelCapacity;
	}

	public void setHotelCapacity(int hotelCapacity) {
		this.hotelCapacity = hotelCapacity;
	}

	@Override
	public String toString() {
		return "HotelCapacityDto [hotelNumber=" + hotelNumber + ", hotelCapacity=" + hotelCapacity + "]";
	}
}
