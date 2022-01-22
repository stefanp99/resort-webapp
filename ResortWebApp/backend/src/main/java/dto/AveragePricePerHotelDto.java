package dto;

public class AveragePricePerHotelDto {
	private int hotelNumber;
	private int averagePricePerNight;
	
	public AveragePricePerHotelDto() {
		super();
	}

	public AveragePricePerHotelDto(int hotelNumber, int averagePricePerNight) {
		super();
		this.hotelNumber = hotelNumber;
		this.averagePricePerNight = averagePricePerNight;
	}

	public int getHotelNumber() {
		return hotelNumber;
	}

	public void setHotelNumber(int hotelNumber) {
		this.hotelNumber = hotelNumber;
	}

	public int getAveragePricePerNight() {
		return averagePricePerNight;
	}

	public void setAveragePricePerNight(int averagePricePerNight) {
		this.averagePricePerNight = averagePricePerNight;
	}

	@Override
	public String toString() {
		return "AveragePricePerHotelDto [hotelNumber=" + hotelNumber + ", averagePricePerNight=" + averagePricePerNight
				+ "]";
	}
	
}
