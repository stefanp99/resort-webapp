package dto;

public class GuestsHotelDto {
	private String name;
	private String surname;
	private int hotelNumber;
	
	public GuestsHotelDto() {
		super();
	}

	public GuestsHotelDto(String name, String surname, int hotelNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.hotelNumber = hotelNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getHotelNumber() {
		return hotelNumber;
	}

	public void setHotelNumber(int hotelNumber) {
		this.hotelNumber = hotelNumber;
	}

	@Override
	public String toString() {
		return "GuestsHotelDto [name=" + name + ", surname=" + surname + ", hotelNumber=" + hotelNumber + "]";
	}
}
