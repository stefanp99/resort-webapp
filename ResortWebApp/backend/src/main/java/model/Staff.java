package model;

public class Staff {
	private int staffId;
	private String name;
	private String surname;
	private String occupation;
	
	public Staff() {
		super();
	}

	public Staff(int staffId, String name, String surname, String occupation) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.surname = surname;
		this.occupation = occupation;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
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

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", name=" + name + ", surname=" + surname + ", occupation=" + occupation
				+ "]";
	}
	
}
