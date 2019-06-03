package booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CancelBooking")
public class CancelBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = null;
		printHTML(response,error);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingId = Integer.parseInt(request.getParameter("id"));
		CancelBookingDAO cancel = new CancelBookingDAO();
		try {
			if(cancel.checkForBookingId(bookingId)) { 
				
				if(!cancel.checkForSeats(bookingId)) {
					String error ="All tickets are cancelled";
					printHTML(response,error);
				} else {
				RequestDispatcher rd=request.getRequestDispatcher("cancel.jsp?booking_id="+bookingId);  

				rd.forward(request, response);}
			} 
			
			else {
				String error ="Please enter valid booking id";
				printHTML(response,error);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
private void printHTML(HttpServletResponse response, String error) throws IOException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	out.println("<div style='margin-left: 40%;margin-top: 11%;'>");
	out.println("<form action='CancelBooking' method='post'>");
	if(error != null) {	out.println("<p style='color:red'>"+error+"</p>");
}
	out.println("<label>Enter booking Id :<br>");
	out.println("<input name='id' type='number'></input><br><br>");
	out.println("<button type='submit'>Cancel Ticket</button>");
	out.println("</form></div>");
}
}
