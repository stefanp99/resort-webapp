package controller;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dao.HotelDao;
import dto.AveragePricePerHotelDto;
import dto.HotelActivityDto;
import dto.HotelCapacityDto;
import model.Hotel;

@Path("/hotels")
public class HotelController {
	static HotelDao hotelDao = new HotelDao();

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Hotel> getAllHotels() throws SQLException {
		return hotelDao.getAllHotels();
	}

	@GET
	@Path("hotelActivities")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HotelActivityDto> getHotelActivityDtos() throws SQLException {
		return hotelDao.getHotelActivityDtos();
	}

	@GET
	@Path("averagePriceByHotel")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AveragePricePerHotelDto> getAveragePricePerHotelDtos() throws SQLException {
		return hotelDao.getAveragePricePerHotelDtos();
	}

	@GET
	@Path("hotelCapacity")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HotelCapacityDto> getHotelCapacityDtos() throws SQLException {
		return hotelDao.getHotelCapacityDtos();
	}
}
