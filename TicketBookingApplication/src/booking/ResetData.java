package booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ResetData {
	  Connection con = null;
	  public String URL = "jdbc:mysql://localhost:3306/booking";
	  public String USER = "root";
	  public String PASS = "Admin123";
	  
		  ResetData() {
			      try {  
			    	  Class.forName("com.mysql.jdbc.Driver");  
			          con =DriverManager.getConnection(URL, USER, PASS);
			      } catch (Exception ex) {
			          throw new RuntimeException("Error connecting to the database", ex);
			      }
			    }
		  public void resetBook() throws SQLException 
		  {
			  Statement stmt = con.createStatement();
			  stmt.executeQuery("SET FOREIGN_KEY_CHECKS = 0;");
			  stmt.execute("truncate table book");
			  stmt.executeQuery("SET FOREIGN_KEY_CHECKS = 1;");
		  }
		  public void resetTicket() throws SQLException {
			  Statement stmt = con.createStatement();
			  stmt.execute("truncate table ticket");
		  }
		  public void resetScreen() throws SQLException {
			  Statement stmt1 = con.createStatement();
			  stmt1.execute("truncate table screen");
			  Statement stmt = con.createStatement();
			  System.out.println(stmt.executeUpdate("insert into screen values(1,'10AM',5,15,0)")); 
			  stmt.executeUpdate("insert into screen values(1,'2PM',5,15,0)");
			  stmt.executeUpdate("insert into screen values(1,'9PM',5,15,0)");
			  stmt.executeUpdate("insert into screen values(2,'10AM',5,15,0)");
			  stmt.executeUpdate("insert into screen values(2,'2PM',5,15,0)");
			  stmt.executeUpdate("insert into screen values(2,'9PM',5,15,0)");
		  }
		  public void resetSeats() throws SQLException {
			  Statement stmt = con.createStatement();
			  stmt.execute("truncate table seat");
			  for(int i=1;i<=5;i++) {
				  stmt.executeUpdate("insert into seat values("+i+",'first_class',0,0,0,0,0,0)"); 
			  }
			  for(int i=6;i<16;i++) {
				  stmt.executeUpdate("insert into seat values("+i+",'second_class',0,0,0,0,0,0)"); 
			  }
		  }
}
