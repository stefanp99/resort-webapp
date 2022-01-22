package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dao.ActivityDao;
import model.Activity;
import model.Staff;

@Path("/activities")
public class ActivityController {
	static ActivityDao activityDao = new ActivityDao();

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Activity> getAllActivities() throws SQLException {
		return activityDao.getAllActivities();
	}

	@GET
	@Path("upcoming")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Activity> getUpcomingActivities(@QueryParam("daysDifference") int daysDifference) throws SQLException {
		return activityDao.getUpcomingActivities(daysDifference);
	}

	@GET
	@Path("upcoming/week")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, List<Activity>> getUpcomingActivitiesAndDayMap() {
		return activityDao.getUpcomingActivitiesAndDayMap();
	}

	@GET
	@Path("staff/byActivity")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Staff> getStaffByActivity(@QueryParam("activity") @DefaultValue("%") String activity) throws SQLException {
		return activityDao.getListOfStaffByActivity(activity);
	}
}
