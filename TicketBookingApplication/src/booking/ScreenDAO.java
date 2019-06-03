package booking;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScreenDAO {
	DatabaseFactory df = null;
	ScreenDAO() {
		df = new DatabaseFactory();
	}
	public int getAvailableSeats(String type,String time,int screen_no) throws SQLException {
		ResultSet rs = df.getAvailableSeats(time, screen_no);
		rs.next();
		return (rs.getInt(type));
	}
}
