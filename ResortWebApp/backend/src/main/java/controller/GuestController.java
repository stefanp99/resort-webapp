package controller;

import java.sql.Date;
//TODO: get list of available rooms
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

import dao.GuestDao;
import dto.GuestsHotelDto;
import model.Guest;

@Path("/guests")
public class GuestController {

	static GuestDao guestDao = new GuestDao();

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Guest> getAllGuests() throws SQLException {
		return guestDao.getAllGuests();
	}

	@GET
	@Path("findById")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Guest> getGuestsById(@QueryParam("id") int id) throws SQLException {
		return guestDao.getGuestsById(id);
	}

	@GET
	@Path("findByRoom")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Guest> getGuestsByRoom(@QueryParam("room_number") int roomNumber) throws SQLException {
		return guestDao.getGuestsByRoom(roomNumber);
	}

	@POST
	@Path("add")
	@Produces(MediaType.APPLICATION_JSON)
	public String addGuest(@QueryParam("name") String name, @QueryParam("surname") String surname,
			@QueryParam("age") int age, @QueryParam("roomNumber") int roomNumber,
			@QueryParam("departureDate") Date departureDate) throws SQLException {
		return guestDao.addGuest(name, surname, age, roomNumber, departureDate);
	}

	@PUT
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public String addGuest(@QueryParam("guestId") int guestId, @QueryParam("roomNumber") int roomNumber,
			@QueryParam("departureDate") String departureDate) throws SQLException {
		return guestDao.updateGuest(guestId, roomNumber, departureDate);
	}

	@DELETE
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Guest deleteGuestById(@QueryParam("id") int id) throws SQLException {
		return guestDao.deleteGuestById(id);
	}

	@DELETE
	@Path("reorg")
	@Produces(MediaType.APPLICATION_JSON)
	public String reorgGuests() throws SQLException {
		return guestDao.reorgRooms();
	}
	
	@GET
	@Path("guestsHotels")
	@Produces(MediaType.APPLICATION_JSON)
	public List<GuestsHotelDto> getGuestsHotelDtos() throws SQLException{
		return guestDao.getGuestsHotelDtos();
	}

}
