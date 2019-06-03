<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*,javax.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<title>Cancel booking</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand" href="options.jsp">Home</a>
</nav>
	<div class="container">
        <div style="text-align:center" class="mt-3">
                <h3>Cancel booking</h3>
           		<br>
         	 <div class="row">
            	    <%Class.forName("com.mysql.jdbc.Driver"); 
        	      	 java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booking","root","Admin123"); 
    	             Statement st= con.createStatement(); 
    	             Statement st1= con.createStatement(); 
 	                 ResultSet rs=st.executeQuery("select seat_no from ticket where booking_id="+request.getParameter("booking_id")+" and status='book'"); 
 	                ResultSet book=st1.executeQuery("select * from book where booking_id="+request.getParameter("booking_id")); 
                        %>
                        <form style="margin-left:42%" action="CancelTicketController" method="post">
                        <p>Select seats to be canceled</p>
                        <%while(rs.next()) {
                        
                        	out.print("<input name='seats'  type='checkbox' value="+rs.getInt("seat_no")+"> Seat no"+rs.getInt("seat_no")+"</input><br>");
                        	
                        }	%>
                      <%  out.print("<input type='hidden' name='booking_id' value="+request.getParameter("booking_id")+"></input>"); %>
                      <br>
                        <button type="submit">Cancel Selected</button>
                        </form>
                        
      	   </div>
      	</div>
   	 </div>
                
</body>
</html>