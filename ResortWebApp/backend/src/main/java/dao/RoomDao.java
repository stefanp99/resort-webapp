package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.AvailableRoomsDto;
import model.Room;

public class RoomDao {
	Connection connection = null;

	public RoomDao() {
		String sqlUrl = "jdbc:sqlserver://localhost:1433;databaseName=Resort;user=stefan;password=000";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(sqlUrl);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Room> getAllRooms() throws SQLException {
		String query = "select * from Rooms";
		return getRoomListFromQuery(query, null);
	}

	public List<Room> getRoomById(int id) throws SQLException {
		String query = "select * from Rooms where room_number = ?";
		List<String> properties = new ArrayList<>();
		properties.add(Integer.toString(id));
		return getRoomListFromQuery(query, properties);
	}

	public List<AvailableRoomsDto> getAvailableRooms(String status) throws SQLException {
		String query = "with guest_number_room_number as(\n"
				+ "	select count(*) as guest_number, room_number from Guests\n" + "	group by room_number\n" + ")\n"
				+ "select r.room_number, g.guest_number, r.max_number_of_guests from guest_number_room_number g\n"
				+ "	right join Rooms r on g.room_number = r.room_number";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		List<AvailableRoomsDto> availableRooms = new ArrayList<>();
		while (resultSet.next()) {
			AvailableRoomsDto availableRoomsDto = new AvailableRoomsDto(resultSet.getInt(1), resultSet.getInt(2),
					resultSet.getInt(3));
			if (status.equals("available")) {
				if (availableRoomsDto.getGuestNumber() < availableRoomsDto.getMaxNumberOfGuests())
					availableRooms.add(availableRoomsDto);
			} else if (status.equals("all"))
				availableRooms.add(availableRoomsDto);
			else if (status.equals("occupied")) {
				if (availableRoomsDto.getGuestNumber() > 0)
					availableRooms.add(availableRoomsDto);
			}
		}
		return availableRooms;
	}

	public int getNumberOfFullyOccupiedRooms() throws SQLException {
		String query = "with guest_number_room_number as(\r\n" + "	select count(*) as guest_number, room_number \r\n"
				+ "		from Guests\r\n" + "		group by room_number\r\n" + "	)\r\n"
				+ "select count(g.room_number) as number_of_occupied_rooms from guest_number_room_number g\r\n"
				+ "right join Rooms r on g.room_number = r.room_number\r\n"
				+ "where g.guest_number = r.max_number_of_guests";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		resultSet.next();
		return resultSet.getInt(1);
	}

	private List<Room> getRoomListFromResultSet(ResultSet resultSet) throws SQLException {
		List<Room> rooms = new ArrayList<>();
		while (resultSet.next()) {
			rooms.add(new Room(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4),
					resultSet.getInt(5), resultSet.getInt(6)));
		}
		return rooms;
	}

	private List<Room> getRoomListFromQuery(String query, List<String> properties) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		if (properties != null) {
			for (String property : properties) {
				preparedStatement.setString(i, property);
				i++;
			}
		}
		ResultSet resultSet = preparedStatement.executeQuery();
		return getRoomListFromResultSet(resultSet);
	}
}
