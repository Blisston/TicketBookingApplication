<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*,javax.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
<title>Booking summary</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand" href="options.jsp">Home</a>
</nav>
	 <div class="container">
        <div style="text-align:center" class="mt-3">
                <h3>Booking summary</h3>
                <br>
                <div class="row">
                	<%Class.forName("com.mysql.jdbc.Driver"); 
                        java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booking","root","Admin123"); 
                        Statement st= con.createStatement(); 
                        ResultSet rs=st.executeQuery("select * from book"); 
                        Statement st1= con.createStatement(); 
                        ResultSet tc =st1.executeQuery("select * from ticket");
                        %>
                       <%! public String getSeats(ResultSet du, ResultSet tc) throws Exception {
                    	 	String seats = "";
							while(tc.next()) {
								if(tc.getInt("booking_id")==du.getInt("booking_id")&& tc.getString("status").equals("book")) {
									seats += tc.getInt("seat_no")+" ";
								} else if(tc.getInt("booking_id")==du.getInt("booking_id")&& tc.getString("status").equals("cancel")) {
									seats += tc.getInt("seat_no")+"(c) ";
								}
							}
						
							tc.beforeFirst();
                    	   return seats;
                       }
                       
                    	   %>
                       
               		<table style="width:100%" >
               			<thead>
               				<th>Booking Id</th>
               				<th>Screen No</th>
               				<th>Time</th>
               				<th>Seat numbers</th>
               				<th>Amount</th>
               			</thead>
               			 <%while(rs.next()){
	                        	out.println("<tr><td>"+rs.getString("booking_id")+"</td><td>"+rs.getString("screen_no")+"</td><td>"+rs.getString("time")+"</td><td>"+getSeats(rs,tc)+"</td><td>"+rs.getString("amount")+"</td></tr>");
	                        }
	                        	%> 
               		</table> 
                <p>(c) - Cancelled tickets</p>
                </div>
       	</div>
   	</div>
</body>
</html>