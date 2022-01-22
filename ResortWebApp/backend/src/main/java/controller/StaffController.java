package controller;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dao.StaffDao;
import model.Staff;

@Path("/staff")
public class StaffController {
	static StaffDao staffDao = new StaffDao();

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Staff> getAllStaff() throws SQLException {
		return staffDao.getAllStaff();
	}
	
	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public Staff addStaff(@QueryParam("name") String name, 
			@QueryParam("surname") String surname, 
			@QueryParam("occupation") String occupation) throws SQLException{
		return staffDao.addStaff(name, surname, occupation);
	}
	
	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public Staff updateStaff(@QueryParam("staffId") int staffId,
			@QueryParam("name") String name, 
			@QueryParam("surname") String surname, 
			@QueryParam("occupation") String occupation) throws SQLException {
		return staffDao.updateStaff(staffId, name, surname, occupation);
	}
	
	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Staff deleteStaff(@QueryParam("staffId") int staffId) throws SQLException {
		return staffDao.deleteStaff(staffId);
	}
	
	@GET
	@Path("byHotel")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Staff> getStaffByHotel(@QueryParam("hotelNumber") int hotelNumber) throws SQLException{
		return staffDao.getStaffByHotel(hotelNumber);
	}
}
