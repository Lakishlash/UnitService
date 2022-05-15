package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



//data base connection

public class Unit {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ceb_api", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//insert

	public String insertUnit(String id, String uid, String units, String created_at) { 
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " INSERT into units (`id`,`uid`,`units`,`created_at`)"
					+ " values (?, ?, ?, ?)"; 
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, uid);
			preparedStmt.setString(3, units);
			preparedStmt.setString(4, created_at);
	 
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newUnit = readUnit(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newUnit + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Unit.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	    } 
	}
	
	//read
	
	public String readUnit()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border='1'><tr>"
					+ "<th> ID</th>"
					+ "<th> UID</th>"
					+ "<th> Units </th>"
					+ "<th>CreatedAt</th></tr>";
			
			String query = "select * from Unit"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String id = rs.getString("id");
				String uid = rs.getString("uid"); 
				String units = rs.getString("units");   
				String created_at = rs.getString("created_at"); 
		 
			
			
	 
				// Add into the html table 
				
				output += "<tr><td><input id=\'hididUpdate\' name=\'hididUpdate\' type=\'hidden\' value=\'" + id + "'>"
				            + uid + "</td>"; 
				output += "<td>" + units + "</td>";
				output += "<td>" + created_at + "</td>";
				
			  
 
			// buttons     
			output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
					+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-iid='" + uid + "'>" + "</td></tr>"; 
		 		 
			}
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Unit.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//update
	
	public String updateUnit(String id, String uid, String units, String created_at) { 
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = " UPDATE units SET uid = ? , units = ? , created_at = ? WHERE id = ? ";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, uid);
			preparedStmt.setString(2, units);
			preparedStmt.setString(3, created_at);
			preparedStmt.setString(4, id);
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newUnit = readUnit();    
			output = "{\"status\":\"success\", \"data\": \"" + newUnit + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the unit.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	    } 
	}
	
	//delete
	
	public String deleteUnit(String uid)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
				
			} 
	 
			// create a prepared statement    
			String query = "DELETE from units WHERE id=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(uid)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newUnit = readUnit();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newUnit + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Unit.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}



