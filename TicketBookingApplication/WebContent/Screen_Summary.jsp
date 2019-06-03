<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*,javax.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<title>Screen Summary</title>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
</head>
<body>
<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand" href="options.jsp">Home</a>
</nav>
	<div class="container">
        <div style="text-align:center" class="mt-3">
                <h3>Screen Summary</h3>
                <br>
                <div class="row">
                	<%Class.forName("com.mysql.jdbc.Driver"); 
                        java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/booking","root","Admin123"); 
                        Statement st= con.createStatement(); 
                        ResultSet rs=st.executeQuery("select * from screen"); 
                        %>
                       
               		<table style="width:100%" >
               			<thead>
               				<th>Screen No</th>
               				<th>Time</th>
               				<th>First class seats</th>
               				<th>Second class seats</th>
               				<th>Total Earnings</th>
               			</thead>
               			 <%while(rs.next()){
	                        	out.println("<tr><td>"+rs.getString("screen_no")+"</td><td>"+rs.getString("time")+"</td><td>"+rs.getString("first_class")+"</td><td>"+rs.getString("second_class")+"</td><td>"+rs.getString("earnings")+"</td></tr>");
	                        }
	                        	%> 
               		</table> 
                
                </div>
       	</div>
   	</div>
</body>
</html>