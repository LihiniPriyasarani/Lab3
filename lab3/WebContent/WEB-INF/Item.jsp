<%@ page import="lab3.Item" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
 <% 
   
    if(request.getParameter("itemCode") != null)
    {
    	Item itemObj =  new Item();
    	
    	String stsMsg = itemObj.insertItem(request.getParameter("itemCode"),request.getParameter("itemName"),
    			request.getParameter("itemPrice"),request.getParameter("itemDesc"));
    	
    	session.setAttribute("statusMsg",stsMsg);
    	
    	
    }
    if(request.getParameter("itemID") != null){
    	
    	
    	
    }



%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
</head>
<body>


             <h1>Items Management...</h1>
             <form method="post" action="Item.jsp">
                   Item Code: <input name="itemCode" type="text"><br>
                   Name: <input name="itemName"type="text"><br>
                   Item Price : <input name="itemPrice"type="text"><br>
                   Item Description : <input name="itemDesc" type="text"><br>
                   <input name="btnSubmit" type="submit" value="SAVE" class="btn btn-primary">
             
             </form>
             <%
             
               out.print(session.getAttribute("statusMsg"));
             
             %>
             <br>
             <%
                Item itemObj = new Item();
                out.print(itemObj.readItems());
             
             %>

</body>
</html>