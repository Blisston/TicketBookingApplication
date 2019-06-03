<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*,javax.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Book Ticket</title>
</head>
<body onload="calculateTicketPrice()">
<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand" href="options.jsp">Home</a>
</nav>
    <div class="container">
        <div style="text-align:center" class="mt-3">
            <form action="BookController" method="post">
                <h3>Book Ticket</h3>
                <br>
                <div class="row">
				     <%if(request.getParameter("error")!= null){
				    	 out.println("<p style='width: 100%;color:red'>"+request.getParameter("error")+"<p>");
				     }
				    	 %>           	
     
                    <div class="col-3">
                    

                    	<%Class.forName("com.mysql.jdbc.Driver"); 
                        java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booking","root","Admin123"); 
                        Statement st= con.createStatement(); 
                        ResultSet rs=st.executeQuery("select distinct screen_no from screen"); 
                        %>
                        <label>Select Screen</label>
                        <select name="screen" >
	                        <%while(rs.next()){
	                        	out.println("<option value="+rs.getString("screen_no")+">"+rs.getString("screen_no") +" Screen</option>");
	                        }
	                        	%>
                        </select>
                    </div>
                     <div class="col-3">
                    	<%Class.forName("com.mysql.jdbc.Driver");  
                          rs=st.executeQuery("select distinct time from screen"); 
                        %>
                        <label>Select Time</label>
                        <select  name="time">
	                        <%while(rs.next()){
	                        	out.println("<option value="+rs.getString("time")+">"+rs.getString("time") +" Show</option>");
	                        }
	                        	%>  
                        </select>
                    </div>
                     <div class="col-3">
                    
                        <label>Select Type</label>
                        <select onChange=calculateTicketPrice() id="type" name="type">
                        	<option value="first_class">1st Class</option>
                        	<option value = "second_class">2nd Class</option>
                        </select>
                    </div>
                     <div class="col-3">
                    
                        <label>No of Seats</label>
                        <select onChange=calculateTicketPrice() name="no" id="no_tic">
                        	 <%for(int i=1;i<6;i++){
                        	out.println("<option value="+i+">"+i +"</option>");
                        }
                        	%>  
                        </select>
                    </div>
                </div>
                <br>
                <p id="price"><p>
                <br>
                <button type="submit">Book</button>
            </form>
        </div>
    </div>
	<script> function  calculateTicketPrice() {
		var sum = 0;
		var noOfTickets = document.getElementById("no_tic").value;
		var type = document.getElementById("type").value;
		if(type === "first_class") {
			sum = noOfTickets*120;
		}
		else {
			sum = noOfTickets*100;
		}
		document.getElementById("price").innerHTML="Total price is &#8377;"+sum;
		
    
	}
	
	</script>
</body>
</html>