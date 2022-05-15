<%@page import="com.Unit"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unit Management</title> 
<script src="Components/jquery-3.6.0.min.js"></script> 
<script src="Components/units.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Unit Details</h1>
				<form id="formUnit" class="form" name="formUnit" method="post" action="Units.jsp">  
					<br>UID:  
 	 				<input id="uid" name="name" type="text"  class="form-control form-control-sm">
					
					<br> Amount Due:   
  					<input id="units" name="nic" type="text" class="form-control form-control-sm">   
  					<br>
					
					<br> created_at:   
  					<input id="created_at" name="created_at" type="text"  class="form-control form-control-sm">
					<br> 
					
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hiduidSave" name="hiduidSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divunitGrid">
					<%
						Unit innoObj = new Unit();
						out.print(innoObj.readUnit());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>


