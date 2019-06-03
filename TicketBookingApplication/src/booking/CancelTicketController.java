package booking;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CancelTicketController")
public class CancelTicketController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seats[] =  request.getParameterValues("seats");
		int noOfSeats = seats.length;
		int b_id=Integer.parseInt(request.getParameter("booking_id"));
		String type = (Integer.parseInt(seats[0])<6)? "first_class": "second_class";
		CancelBookingDAO cancelDAO = new CancelBookingDAO();
		
		try {
			Book book = cancelDAO.getBookingDetails(b_id);
			book.setAmount(calculateAmount(noOfSeats,type));
			cancelDAO.cancelTicket(noOfSeats,seats,book,type,b_id);
			response.setContentType("text/html");
			response.getWriter().println("<center style='margin-top:15%'><p>Ticket cancelled</p><p>Refunded Amount: "+book.getAmount()+"<p><br><a href='options.jsp'>Back to home</a></center>");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public int calculateAmount(int no,String type) {
		int sum=0;
		if(type.equals("first_class")) {
			sum = no*120;
		} else {
			sum = no*100;
		}
		int fee = sum/10;
		return sum - fee;
	}
}
