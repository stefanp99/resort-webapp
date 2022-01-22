package controller;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dao.RoomDao;
import dto.AvailableRoomsDto;
import model.Room;

@Path("/rooms")
public class RoomController {
	static RoomDao roomDao = new RoomDao();
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> getAllRooms() throws SQLException{
		return roomDao.getAllRooms();
	}
	
	@GET
	@Path("findById")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> getRoomById(@QueryParam("id") int id) throws SQLException{
		return roomDao.getRoomById(id);
	}
	
	@GET
	@Path("available")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AvailableRoomsDto> getAvailableRooms(@QueryParam("status") String status) throws SQLException{
		return roomDao.getAvailableRooms(status);
	}
	
	@GET
	@Path("numberOfOccupiedRooms")
	@Produces(MediaType.APPLICATION_JSON)
	public int getNumberOfOccupiedRooms() throws SQLException {
		return roomDao.getNumberOfFullyOccupiedRooms();
	}
}
