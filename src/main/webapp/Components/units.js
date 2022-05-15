$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateUnitForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hiduidSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "UnitService",  
			type : type,  
			data : $("#formUnit").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onUnitSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onUnitSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divUnitGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidInnovatorIDSave").val("");  
	$("#formInnovator")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hiduidSave").val($(this).closest("tr").find('#hiduidUpdate').val());     
	$("#uid").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#units").val($(this).closest("tr").find('td:eq(1)').text());      
	$("#created_at").val($(this).closest("tr").find('td:eq(6)').text());      
}); 


//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "UnitService",   
		type : "DELETE",   
		data : "uid=" + $(this).data("iid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onUnitDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onUnitDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divUnitGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateUnitForm() 
{  
	// UID  
	if ($("#uid").val().trim() == "")  
	{   
		return "Insert payee_uid.";  
	} 

	// Amount------------------------  
	if ($("#amount").val().trim() == "")  
	{   
		return "Insert due amount.";  
	} 
	
	

	//address-------------------------------
	if ($("#units").val().trim() == "")  
	{   
		return "Insert units.";  
	} 
	
	
	
	//created_at-------------------------------
	if ($("#created_at").val().trim() == "")  
	{   
		return "Insert created_at.";  
	} 
	
	return true; 
}


