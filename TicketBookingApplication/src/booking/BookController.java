package booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookController")
public class BookController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScreenDAO screenDAO = new ScreenDAO();
		BookDAO bookDAO = new BookDAO();
		int available = 0;
		try {
			available = screenDAO.getAvailableSeats(request.getParameter("type"),request.getParameter("time"),Integer.parseInt(request.getParameter("screen")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(available<Integer.parseInt(request.getParameter("no"))) {
			
			response.sendRedirect( "book.jsp?error=No seats available" );
		} else {
			try {
				response.setContentType("text/html");
				Book ticket  = bookDAO.getTicket(request.getParameter("time"),request.getParameter("screen"));
				ticket.setAmount(calculateAmount(request.getParameter("type"), Integer.parseInt(request.getParameter("no"))));
				ArrayList<Integer> seats = bookDAO.getSeats(request.getParameter("type"),Integer.parseInt(request.getParameter("screen")),request.getParameter("time"),Integer.parseInt(request.getParameter("no")));
				bookDAO.bookTicket(seats, ticket);
				PrintWriter out = response.getWriter();
				out.println("<div style='margin-left: 40%;margin-top: 11%;'>");
				out.println("<b>Ticket Summary</b>");
				out.println("<table style='line-height:35px'><tr>");
				out.println("<td>Booking id : </td><td>"+ticket.getB_id()+"</td></tr>");
				out.println("<tr><td>Seats  :</td><td>");
				
				for(int i=0;i<seats.size();i++){out.print(seats.get(i)+" ");}
				
				out.println("</td></tr><tr><td>Screen No : </td><td>"+ticket.getScreen_no()+"</td></tr>");
				out.println("<tr><td>Time : </td><td>"+ticket.getTime()+"</td></tr>");
				out.println("<tr><td>Ticket price :</td><td> "+ticket.getAmount()+"</td></tr></table><br><a href='options.jsp'>Back to home</a><div>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
 private int calculateAmount(String type,int no) {
	 	int sum = 0;
	 	
		if(type.equals("first_class")) {
			sum = no*120;
		}
		else {
			sum = no*100;
		}
		return sum;
 }
}
