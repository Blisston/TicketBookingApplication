package booking;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CancelBookingDAO {
	DatabaseFactory df = null;
	CancelBookingDAO() {
		df = new DatabaseFactory();
	}
	public boolean checkForBookingId(int b_id) throws SQLException {
		boolean flag = false;
		ResultSet rs  = df.getBookings();
		while(rs.next()) {
			if(rs.getInt("booking_id")==b_id) {
				flag = true; 
			}
		}
		return flag;
	}
	
	public boolean checkForSeats(int b_id) throws SQLException {
		boolean flag = false;
		ResultSet rs  = df.getTickets(b_id);
		while(rs.next()) {
			if(rs.getString("status").equals("book")) {
				flag = true; 
			}
		}
		return flag;
	}
	public Book getBookingDetails(int id) throws SQLException {
		ResultSet rs = df.getBookingId(id);
		rs.next();
		Book book = new Book(rs.getInt("booking_id"),rs.getString("time"),rs.getInt("screen_no"));
		return book;
	}
	public void cancelTicket(int noOfSeats,String[] seats,Book book,String type,int b_id) throws SQLException {
		String time = "1";
	
		df.cancelTicketFromScreen(noOfSeats, book.getAmount(), type, book.getScreen_no(), book.getTime());
		  if(book.getTime().equals("10AM")) {
			  time="1";
		  }
		  if(book.getTime().equals("2PM")) {
			  time ="2";
		  }
		  if(book.getTime().equals("9PM")) {
			  time ="3";
		  }
		 
		String query = "s"+book.getScreen_no()+"t"+time;
		df.cancelTicketFromSeats(query, type, seats);
		df.cancelTicketFromTicket(seats, book.getB_id());
	}
	
}
