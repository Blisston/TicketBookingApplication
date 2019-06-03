package booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseFactory {
  Connection con = null;
  public String URL = "jdbc:mysql://localhost:3306/booking";
  public String USER = "root";
  public String PASS = "Admin123";
  
	  DatabaseFactory() {
		
		      try {
		    	  
		    	  Class.forName("com.mysql.jdbc.Driver");  
		          con =DriverManager.getConnection(URL, USER, PASS);
		      } catch (Exception ex) {
		          throw new RuntimeException("Error connecting to the database", ex);
		      }
		    }

	  public ResultSet getAvailableSeats(String time,int screen_no) throws SQLException {
		  Statement stmt = con.createStatement();
		  return stmt.executeQuery("select * from screen where time='"+time+"' and screen_no='"+screen_no + "'");
	  }
	  
	  public ResultSet getBookingId() throws SQLException {
		Statement stmt = con.createStatement(); 
		return stmt.executeQuery("select max(booking_id) from book");
	  }
	  
	  public ResultSet getBookingId(int id) throws SQLException {
	    Statement stmt = con.createStatement(); 
		return stmt.executeQuery("select * from book where booking_id='"+id+"'");
	  }
	  
	  public ResultSet getSeats(String type,String query) throws SQLException {
		  Statement stmt = con.createStatement(); 
		  return stmt.executeQuery("select seat_no from seat where type='"+type+"' and "+query+ "=0");
	  }
	  
	  public void bookTicket(ArrayList<Integer> seat,String query,Book ticket) throws SQLException {
		  Statement stmt = con.createStatement(); 
		  String type = "first_class";
		  int total = 0;
		  PreparedStatement ps = con.prepareStatement("INSERT INTO book VALUES (?, ?, ?,?)");
	 	  ps.setInt(1, ticket.getB_id());
	 	  ps.setString(2, ticket.getTime());
	 	  ps.setInt(3, ticket.getAmount());
	 	  ps.setInt(4, ticket.getScreen_no());
	 	  ps.executeUpdate();
	 	  ResultSet rs = stmt.executeQuery("select * from screen where time='"+ticket.getTime()+"' and screen_no='"+ticket.getScreen_no() + "'");
	 	  rs.next();
	 	  if(seat.get(0)>5) {type = "second_class";}
	 	  int cur = rs.getInt(type);
			  
	 	  for(int i=0;i<seat.size();i++) {
	 	    cur--;
	 	    stmt.executeUpdate("update seat set "+query+ "=1 where seat_no='"+seat.get(i)+"'");
	 	    ps = con.prepareStatement("INSERT INTO ticket VALUES (?, ?,?)");
		 	ps.setInt(1, (int)ticket.getB_id());
		 	
		 	ps.setInt(2, (int)seat.get(i));
		 	ps.setString(3, "book");
		 	ps.executeUpdate();
	 	     }
		
			ResultSet earning = stmt.executeQuery("select earnings from screen where time='"+ticket.getTime()+"' and screen_no="+ticket.getScreen_no() + "");
	 	  	earning.next();
			total = earning.getInt("earnings");
	 	  	total +=ticket.getAmount();
			stmt.executeUpdate("update screen set "+type+"="+cur+" where screen_no="+ticket.getScreen_no()+" and time='"+ticket.getTime()+"'");
			stmt.executeUpdate("update screen set earnings="+total+" where screen_no="+ticket.getScreen_no()+" and time='"+ticket.getTime()+"'");
	  }
	  
	  public ResultSet getBookings() throws SQLException {
		  Statement stmt = con.createStatement(); 
		  return stmt.executeQuery("select * from book");

	  }
	  
	  public void cancelTicketFromScreen(int noOfSeats,int amount,String type,int screen_no,String time) throws SQLException {
		  Statement stmt = con.createStatement(); 
		  ResultSet earning = stmt.executeQuery("select earnings from screen where time='"+time+"' and screen_no="+screen_no + "");
	 	  earning.next();
	 	  int total = earning.getInt("earnings");
	 	  total -=amount;
	 	  	
		  ResultSet seats = stmt.executeQuery("select * from screen where time='"+time+"' and screen_no="+screen_no + "");
		  seats.next();
		  int cur = seats.getInt(type);
		  cur +=noOfSeats;
	 	  	
		  stmt.executeUpdate("update screen set "+type+"="+cur+" where screen_no="+screen_no+" and time='"+time+"'");
		  stmt.executeUpdate("update screen set earnings="+total+" where screen_no="+screen_no+" and time='"+time+"'");
		  
	  }
	  
	  public void cancelTicketFromSeats(String query, String type,String[] seats) throws SQLException {
		  Statement stmt = con.createStatement(); 
		  for(int i=0;i<seats.length;i++) {
			  stmt.executeUpdate("update seat set "+query+"="+0+" where seat_no='"+seats[i]+"'");

		  }
	  }
	  public void cancelTicketFromTicket(String[] seats,int b_id) throws SQLException {
		  Statement stmt = con.createStatement(); 
		  for(int i=0;i<seats.length;i++) {
			  stmt.executeUpdate("update ticket set status='cancel' where seat_no='"+seats[i]+"' and booking_id="+b_id+"");

		  }
	  }
	  public ResultSet getTickets(int b_id) throws SQLException {
		  Statement stmt = con.createStatement(); 
		  return stmt.executeQuery("select * from ticket where booking_id="+b_id);
	  }
}
