
package lab3;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Item {

	private static String url = "jdbc:mysql://127.0.0.1:3306/paf2";
	private static String username = "root";
	private static String password = "";
	
   private  Connection getConnection() {
		
		Connection connection = null;
		
		
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(url,username,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		
		return connection;
		
		
	}
	
   public String insertItem(String code,String name,String price,String desc) {
	   
	   
	            String output = "";
	        try
	        {
	         
	            Connection connection = getConnection();
	            
	            if(connection == null)
	            {
	            	
	            	return "Error while connecting to the DataBase ";
	            	
	            	
	            }
	            //create prepared statement
	            String query = "insert into items" + "(itemID,itemCode,itemName,itemPrice,itemDesc)" + " values (?,?,?,?,?)"; 
	   
	            PreparedStatement preparedstatement = connection.prepareStatement(query);
	            
	            //binding values
	            
	            preparedstatement.setInt(1, 0);
	            preparedstatement.setString(2,code);
	            preparedstatement.setString(3,name);
	            preparedstatement.setDouble(4,Double.parseDouble(price));
	            preparedstatement.setString(5,desc);
	            
	            //execute statements
	            preparedstatement.execute();
	            connection.close();
	   
	             output = "Inserted Successfully...";
	        }
	        catch(Exception e)
	        {
	        	output = "Error while inserting the item..";
	        	System.err.println(e.getMessage());
	        }
			return output;
	        
	        
	        
   }
   
       public String readItems() {
    	   
         String output = "";
	      try
	      {
	         
	            Connection connection = getConnection();
	            
	            if(connection == null)
	            {
	            	
	            	return "Error while connecting to the DataBase ";
	            	
	            	
	            } 
    	       //prepare the html table to be displayed
    	       output = "<table border='1'><tr><th>Item Code</th><th>Item Name</th><th><th>Item Price</th><th>"
    	   		 + "Item Description</th><th>Update</th><th>Remove</th><th>";
    	   		
              String query = "select * from items";
    	      Statement stmt =  connection.createStatement();
    	      ResultSet rs = stmt.executeQuery(query);
    	   
    	    //Iterate through the rows in the result set
    	    while(rs.next())
    	    {
    		    String itemID = Integer.toString(rs.getInt("itemID"));
    		    String itemCode = rs.getString("itemCode");
    		    String itemName = rs.getString("itemName");
    		    String itemPrice = Double.toString(rs.getDouble("itemPrice"));
    		    String itemDesc = rs.getString("itemDesc");
  
             //Add in to html table
             output += "<tr><td>" + itemID + "</td>";
             output += "<td>" +itemCode + "</td>";
             output += "<td>" +itemName + "</td>";
             output += "<td>" +itemPrice + "</td>";
             output += "<td>" +itemDesc + "</td>";
    		  
    	     //Buttons
    		 output += "<td><input name='btnUpdate' type='button' value='Update'></td>"
    			   +  "<td><form method='post' action='item.jsp'>"
    			   + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-primary'>";	   		  
    		       
    	    }
    	  
    	    connection.close();
    	  
    	    //complete the html table
    	    output += "</table>";
    			  
            }			  
        catch(Exception e)
        {
    	   output = "Error while reading the items";
    	   System.err.println(e.getMessage());
    	   
    	   
        }
        return output;
       

}
	     
	   
   }
	


