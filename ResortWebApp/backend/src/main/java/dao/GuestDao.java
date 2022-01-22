package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dto.GuestsHotelDto;
import model.Guest;

public class GuestDao {

	Connection connection = null;

	public GuestDao() {
		String sqlUrl = "jdbc:sqlserver://localhost:1433;databaseName=Resort;user=stefan;password=000";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(sqlUrl);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Guest> getAllGuests() throws SQLException {
		List<Guest> guests = new ArrayList<>();
		String query = "select * from Guests";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		guests = getGuestListFromResultSet(resultSet);
		return guests;
	}

	public List<Guest> getGuestsById(int id) throws SQLException {
		String query = "select * from Guests where guest_id=?";
		List<String> properties = new ArrayList<>();
		properties.add(String.valueOf(id));
		return getGuestListFromQuery(query, properties);
	}

	public List<Guest> getGuestsByRoom(int roomNumber) throws SQLException {
		String query = "select * from Guests where room_number=?";
		List<String> properties = new ArrayList<>();
		properties.add(String.valueOf(roomNumber));
		return getGuestListFromQuery(query, properties);
	}

	public String addGuest(String name, String surname, int age, int roomNumber, java.sql.Date departureDate)
			throws SQLException {
		if (!checkPlaces(roomNumber))
			return "error: room " + roomNumber + " full";

		Calendar calendar = Calendar.getInstance();

		String query = "insert into guests (name, surname, age, room_number, arrival_date, departure_date) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, surname);
		preparedStatement.setInt(3, age);
		preparedStatement.setInt(4, roomNumber);

		calendar.setTime(new java.util.Date());
		calendar.add(Calendar.DATE, 1);
		preparedStatement.setDate(5, new java.sql.Date(calendar.getTimeInMillis()));

		calendar.setTime(departureDate);
		calendar.add(Calendar.DATE, 1);
		preparedStatement.setDate(6, new java.sql.Date(calendar.getTimeInMillis()));
		preparedStatement.executeUpdate();
		return name + "|" + surname + "|" + age + "|" + roomNumber + "|" + new Date(new java.util.Date().getTime())
				+ "|" + departureDate;
	}

	public String updateGuest(int guestId, int roomNumber, String departureDate) throws SQLException {
		if (!checkPlaces(roomNumber) && getGuestsById(guestId).get(0).getRoomNumber() != roomNumber)
			return "error: room " + roomNumber + " full";
		String query = "update Guests set room_number = ?, departure_date = ? where guest_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, roomNumber);
		preparedStatement.setString(2, departureDate);
		preparedStatement.setInt(3, guestId);
		preparedStatement.executeUpdate();
		return "ok";
	}

	public Guest deleteGuestById(int id) throws SQLException {
		Guest guest = getGuestsById(id).get(0);
		String query = "delete from Guests where guest_id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, String.valueOf(id));
		preparedStatement.executeUpdate();
		return guest;
	}

	public String reorgRooms() throws SQLException {
		// delete guests if number of guests from a room > max number of guests from
		// that room
		int nrOfDeletedGuests = 0;
		String query1 = "select room_number from Rooms";
		Statement statement1 = connection.createStatement();
		ResultSet resultSet1 = statement1.executeQuery(query1);
		while (resultSet1.next()) {
			int roomNumber = resultSet1.getInt(1);
			int numberOfGuests = getGuestsByRoom(roomNumber).size();
			String query2 = "select max_number_of_guests from Rooms where room_number = " + roomNumber;
			Statement statement2 = connection.createStatement();
			ResultSet resultSet2 = statement2.executeQuery(query2);
			while (resultSet2.next()) {
				int maxNumberOfGuests = resultSet2.getInt(1);
				if (numberOfGuests > maxNumberOfGuests) {
					List<Guest> guests = getGuestsByRoom(roomNumber);
					int i = 0;
					while (numberOfGuests > maxNumberOfGuests) {
						nrOfDeletedGuests++;
						deleteGuestById(guests.get(i).getGuestId());
						i++;
						numberOfGuests--;
					}
				}
			}
		}

		String query3 = "select count(*) from Guests where departure_date < getdate()";
		Statement statement3 = connection.createStatement();
		ResultSet resultSet3 = statement3.executeQuery(query3);
		resultSet3.next();
		nrOfDeletedGuests += resultSet3.getInt(1);

		String query4 = "delete from Guests where departure_date < getdate()";
		Statement statement4 = connection.createStatement();
		statement4.executeUpdate(query4);

		return "Deleted " + nrOfDeletedGuests + " guests";
	}

	public List<GuestsHotelDto> getGuestsHotelDtos() throws SQLException {
		String query = "select g.name, g.surname, r.hotel_number\r\n" + "	from Guests g\r\n"
				+ "	inner join Rooms r on g.room_number = r.room_number\r\n";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		List<GuestsHotelDto> guestsHotelDtos = new ArrayList<>();
		while (resultSet.next()) {
			guestsHotelDtos
					.add(new GuestsHotelDto(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
		}
		return guestsHotelDtos;
	}

	private List<Guest> getGuestListFromResultSet(ResultSet resultSet) throws SQLException {
		List<Guest> guests = new ArrayList<>();
		while (resultSet.next()) {
			guests.add(new Guest(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getInt(4), resultSet.getInt(5), resultSet.getDate(6), resultSet.getDate(7)));
		}
		return guests;
	}

	private List<Guest> getGuestListFromQuery(String query, List<String> properties) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		for (String property : properties) {
			preparedStatement.setString(i, property);
			i++;
		}
		ResultSet resultSet = preparedStatement.executeQuery();
		return getGuestListFromResultSet(resultSet);
	}

	private Boolean checkPlaces(int roomNumber) throws SQLException {
		String query = "(select max_number_of_guests from Rooms where room_number = ?)\n" + "union all\n"
				+ "(select count(*) as number_of_guests from Guests where room_number = ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, roomNumber);
		preparedStatement.setInt(2, roomNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<Integer> resultList = new ArrayList<>();
		while (resultSet.next()) {
			resultList.add(resultSet.getInt(1));
		}
		int maxNumberOfGuests = resultList.get(0);
		int numberOfGuests = resultList.get(1);
		System.out.println("Room number: " + roomNumber + " Total places: " + maxNumberOfGuests + " Occupied places: "
				+ numberOfGuests);
		if (numberOfGuests >= maxNumberOfGuests)
			return false;
		return true;
	}

}
