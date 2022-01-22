package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Staff;

public class StaffDao {

	Connection connection = null;

	public StaffDao() {
		String sqlUrl = "jdbc:sqlserver://localhost:1433;databaseName=Resort;user=stefan;password=000";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(sqlUrl);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Staff> getAllStaff() throws SQLException {
		String query = "select * from Staff";
		return getStaffListFromQuery(query, null);
	}
	
	public Staff getStaffById(int staffId) throws SQLException{
		String query = "select * from Staff where staff_id = ?";
		List<String> properties = new ArrayList<>();
		properties.add(Integer.toString(staffId));
		return getStaffListFromQuery(query, properties).get(0);
	}
	
	public Staff addStaff(String name, String surname, String occupation) throws SQLException{
		Staff staff = new Staff(0, name, surname, occupation);
		String query = "insert into Staff(name, surname, occupation) values (?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, surname);
		preparedStatement.setString(3, occupation);
		preparedStatement.executeUpdate();
		return staff;
	}
	
	public Staff updateStaff(int staffId, String name, String surname, String occupation) throws SQLException{
		Staff staff = new Staff(staffId, name, surname, occupation);
		String query = "update Staff set name = ?, surname = ?, occupation = ? where staff_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, surname);
		preparedStatement.setString(3, occupation);
		preparedStatement.setInt(4, staffId);
		preparedStatement.executeUpdate();
		return staff;
	}
	
	public Staff deleteStaff(int staffId) throws SQLException{
		Staff staff = getStaffById(staffId);
		String query = "delete from Staff where staff_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, staffId);
		preparedStatement.executeUpdate();
		return staff;
	}

	public List<Staff> getStaffByHotel(int hotelNumber) throws SQLException {
		String query = "select s.* \r\n" + "	from Staff s, Hotels h, StaffHotels sh\r\n"
				+ "	where s.staff_id = sh.staff_id and h.hotel_number = sh.hotel_number and h.hotel_number = ?\r\n";
		List<String> properties = new ArrayList<>();
		properties.add(Integer.toString(hotelNumber));
		return getStaffListFromQuery(query, properties);
	}

	public List<Staff> getStaffListFromResultSet(ResultSet resultSet) throws SQLException {
		List<Staff> staffList = new ArrayList<>();
		while (resultSet.next()) {
			staffList.add(new Staff(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4)));
		}
		return staffList;
	}

	public List<Staff> getStaffListFromQuery(String query, List<String> properties) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		if (properties != null)
			for (String property : properties) {
				preparedStatement.setString(i, property);
				i++;
			}
		ResultSet resultSet = preparedStatement.executeQuery();
		return getStaffListFromResultSet(resultSet);
	}
}
