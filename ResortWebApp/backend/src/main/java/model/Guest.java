package model;

import java.sql.Date;

import javax.json.bind.annotation.JsonbDateFormat;

public class Guest {
	private int guestId;
	private String name;
	private String surname;
	private int age;
	private int roomNumber;
	@JsonbDateFormat("yyyy-MM-dd")
	private Date arrivalDate;
	@JsonbDateFormat("yyyy-MM-dd")
	private Date departureDate;

	public Guest() {
		super();
	}

	public Guest(int guestId, String name, String surname, int age, int roomNumber, Date arrivalDate,
			Date departureDate) {
		super();
		this.guestId = guestId;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.roomNumber = roomNumber;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	@Override
	public String toString() {
		return "Guest [guestId=" + guestId + ", name=" + name + ", surname=" + surname + ", age=" + age
				+ ", roomNumber=" + roomNumber + ", arrivalDate=" + arrivalDate + ", departureDate=" + departureDate
				+ "]";
	}

}
