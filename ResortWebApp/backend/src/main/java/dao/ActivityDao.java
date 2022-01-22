package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import model.Activity;
import model.Staff;

public class ActivityDao {
	Connection connection = null;
	StaffDao staffDao = new StaffDao();

	public ActivityDao() {
		String sqlUrl = "jdbc:sqlserver://localhost:1433;databaseName=Resort;user=stefan;password=000";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(sqlUrl);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Activity> getAllActivities() throws SQLException {
		String query = "select * from Activities";
		return getActivityListFromQuery(query, null);
	}

	public List<Activity> getUpcomingActivities(int daysDifference) throws SQLException {
		String query = "select * from Activities "
				+ "where activity_days like '%' + (select left(DATENAME(dw, GETDATE() + cast(? as int)), 2)) + '%'";
		List<String> properties = new ArrayList<>();
		properties.add(String.valueOf(daysDifference));
		return getActivityListFromQuery(query, properties);
	}

	public Map<String, List<Activity>> getUpcomingActivitiesAndDayMap() {
		Map<String, List<Activity>> returnedMap = new HashMap<>();
		LocalDate currentDate = LocalDate.now();
		for (int i = 0; i < 7; i++) {
			try {
				DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
				String dayOfWeekString = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
				System.out.println(dayOfWeekString);
				returnedMap.put(dayOfWeekString, getUpcomingActivities(i));
				currentDate = currentDate.plusDays(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnedMap;
	}

	public List<Staff> getListOfStaffByActivity(String activity) throws SQLException {
		String query = "select s.staff_id, s.name, s.surname, s.occupation, a.activity_name\r\n"
				+ "	from Staff s, StaffActivities sa, Activities a\r\n"
				+ "	where s.staff_id = sa.staff_id and a.activity_id = sa.activity_id and a.activity_name like ?";
		List<String> properties = new ArrayList<>();
		properties.add(activity);
		return staffDao.getStaffListFromQuery(query, properties);
	}

	private List<Activity> getActivityListFromResultSet(ResultSet resultSet) throws SQLException {
		List<Activity> activities = new ArrayList<>();
		while (resultSet.next()) {
			activities.add(new Activity(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
					Arrays.asList(resultSet.getString(4).split("/", -1)), resultSet.getInt(5) == 1,
					resultSet.getInt(6)));
		}
		return activities;
	}

	private List<Activity> getActivityListFromQuery(String query, List<String> properties) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		if (properties != null) {
			for (String property : properties) {
				preparedStatement.setString(i, property);
				i++;
			}
		}
		ResultSet resultSet = preparedStatement.executeQuery();
		return getActivityListFromResultSet(resultSet);
	}
}
