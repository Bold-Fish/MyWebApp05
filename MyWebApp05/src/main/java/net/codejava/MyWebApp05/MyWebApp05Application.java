package net.codejava.MyWebApp05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//import net.codejava.MyWebApp04.MyWebApp04Application;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class MyWebApp05Application 
{
	//127.0.0.1:8081/home
	@RequestMapping("/home") //http://localhost:8080/home OR 127.0.0.1:8080/home
	String home() 
	{
		System.out.println("myWebApp05");
		return "<html><body><h1>MyWebApp05. Header 1 (SQLITE commands)</h1></body></html>";
	}
	
	@RequestMapping("/close")
	String close() 
	{
		System.exit(0);
		return "<h2>Test2.</h2>";
	}
	
	//127.0.0.1:8081/get
	@RequestMapping("/get")//get database info
	String newForm() 
	{
		System.out.println("myWebApp05");
		String sql = "SELECT * FROM students";
		String x = "<h2>Data returned: ";
		String y = ".</h2>"; 
		String data = "";
		Connection myConn = null;
		Statement myStmt;
		ResultSet myRs;
		String url = "jdbc:sqlite:C://Users/Connor/Documents/Programming_T-Planner_Docs/database/members.db";
		try 
		{		   
			   //connection to database
			   
			   myConn = DriverManager.getConnection(url);
			   myStmt = myConn.createStatement();
			   //System.out.println("myStmt.execute(sql) returned: " + data);
			   myRs = myStmt.executeQuery(sql);
			   while (myRs.next()) 
			   {
				   System.out.println
				   (
					   myRs.getInt("id") +  "\t" + 
					   myRs.getString("name") + "\t" +
	                   myRs.getString("class") + "\t"
				   );
				   
				   String z = myRs.getInt("id") +  "\t" + 
							myRs.getString("name") + "\t" +
							myRs.getString("class") + "\t";
				   data = data + z; 
				   
			   }
			   /*
			   while (myRs.next()) 
				{
				   String z = myRs.getInt("id") +  "\t" + 
							myRs.getString("name") + "\t" +
							myRs.getString("class") + "\t";
				   data = data + z; 
				   
				}
			   */
		}	  
	    //catch (Exception exc)
		catch (SQLException e)
		{
	    	//exc.printStackTrace();
	    	System.out.println(e.getMessage());
	    }
		
		//myRs.getInt("id")
		
		System.out.println("data:" + data);	
		x = x + data + y;
		return x;
	}
	
	

	public static void main(String[] args) 
	{
		//SpringApplication.run(MyWebApp05Application.class, args);
		SpringApplication app = new SpringApplication(MyWebApp05Application.class);
		//SpringApplication.run(MyWebApp04Application.class, args);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
		app.run(MyWebApp05Application.class, args);
	}

}
