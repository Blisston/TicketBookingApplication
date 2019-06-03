package booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO {
	DatabaseFactory df = null;
	BookDAO() {
		df = new DatabaseFactory();
	}
	  public Book getTicket(String time, String screen) throws SQLException {
		  System.out.println(time);
		  ResultSet rs = df.getBookingId();
			rs.next();
			Book ticket = new Book(rs.getInt("max(booking_id)")+1,time,Integer.parseInt(screen));
			return ticket;
	  }
	  public ArrayList<Integer> getSeats(String type,int screen_no, String time,int no) throws SQLException {
		 int i=0;
		  ArrayList<Integer> seats = new ArrayList<Integer>();
		  if(time.equals("10AM")) {
			  time="1";
		  }
		  if(time.equals("2PM")) {
			  time ="2";
		  }
		  if(time.equals("9PM")) {
			  time ="3";
		  }
		  String query = "s"+screen_no+"t"+time;
		  ResultSet rs = df.getSeats(type,query);
		  while(rs.next()&&i<no) {
			  seats.add(rs.getInt("seat_no"));
			  i++;
		  }
		  System.out.println(seats);
		  return seats;
	  }
	  public void bookTicket(ArrayList<Integer> seats,Book ticket) throws SQLException {
		  String time="1";
		  if(ticket.getTime().equals("10AM")) {
			  time="1";
		  }
		  if(ticket.getTime().equals("2PM")) {
			  time ="2";
		  }
		  if(ticket.getTime().equals("9PM")) {
			  time ="3";
		  }
		  String query = "s"+ticket.getScreen_no()+"t"+time;
		  System.out.println(seats);
		  df.bookTicket(seats, query, ticket);
	  }
}
