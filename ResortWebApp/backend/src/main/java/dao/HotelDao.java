package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.AveragePricePerHotelDto;
import dto.HotelActivityDto;
import dto.HotelCapacityDto;
import model.Hotel;

public class HotelDao {
	Connection connection = null;

	public HotelDao() {
		String sqlUrl = "jdbc:sqlserver://localhost:1433;databaseName=Resort;user=stefan;password=000";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(sqlUrl);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Hotel> getAllHotels() throws SQLException {
		String query = "select * from Hotels";
		return getHotelListFromQuery(query, null);
	}

	public List<HotelActivityDto> getHotelActivityDtos() throws SQLException {
		String query = "select h.hotel_number, a.activity_name\r\n" + "	from Hotels h\r\n"
				+ "	inner join Activities a on h.hotel_number = a.hotel_number\r\n"
				+ "	group by h.hotel_number, a.activity_name\r\n";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		List<HotelActivityDto> hotelActivityDtos = new ArrayList<>();
		while (resultSet.next()) {
			hotelActivityDtos.add(new HotelActivityDto(resultSet.getInt(1), resultSet.getString(2)));
		}
		return hotelActivityDtos;
	}
	
	public List<AveragePricePerHotelDto> getAveragePricePerHotelDtos() throws SQLException{
		String query = "select h.hotel_number, avg(r.price_per_night) as average_price_per_night\r\n"
				+ "	from Hotels h\r\n"
				+ "	inner join Rooms r on h.hotel_number = r.hotel_number\r\n"
				+ "	group by h.hotel_number\r\n";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		List<AveragePricePerHotelDto> averagePricePerHotelDtos = new ArrayList<>();
		while(resultSet.next()) {
			averagePricePerHotelDtos.add(new AveragePricePerHotelDto(resultSet.getInt(1), resultSet.getInt(2)));
		}
		return averagePricePerHotelDtos;
	}
	
	public List<HotelCapacityDto> getHotelCapacityDtos() throws SQLException{
		String query = "select h.hotel_number, sum(r.max_number_of_guests) as hotel_capacity\r\n"
				+ "	from Hotels h\r\n"
				+ "	inner join Rooms r on h.hotel_number = r.hotel_number\r\n"
				+ "	group by h.hotel_number\r\n";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		List<HotelCapacityDto> hotelCapacityDtos = new ArrayList<>();
		while(resultSet.next()) {
			hotelCapacityDtos.add(new HotelCapacityDto(resultSet.getInt(1), resultSet.getInt(2)));
		}
		return hotelCapacityDtos;
	}

	private List<Hotel> getHotelListFromResultSet(ResultSet resultSet) throws SQLException {
		List<Hotel> hotels = new ArrayList<>();
		while (resultSet.next()) {
			hotels.add(new Hotel(resultSet.getInt(1), resultSet.getInt(2) == 1, resultSet.getInt(3) == 1));
		}
		return hotels;
	}

	private List<Hotel> getHotelListFromQuery(String query, List<String> properties) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		if (properties != null) {
			for (String property : properties) {
				preparedStatement.setString(i, property);
				i++;
			}
		}
		ResultSet resultSet = preparedStatement.executeQuery();
		return getHotelListFromResultSet(resultSet);
	}
}
