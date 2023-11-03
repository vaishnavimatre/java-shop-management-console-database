package User_Management;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Product_Management.Product;
import db_operations.DbUtil;

public class UserManagement {
	
	public static void userManagement() throws IOException {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("***Welcome to the UserManagement****");
		
	    boolean	canIKeepRunninngTheProgram =true;
		while(canIKeepRunninngTheProgram==true) {
			System.out.println("1.Add User");
			System.out.println("2.Delete User");
			System.out.println("3.edit User");
			System.out.println("4.Search User");
			System.out.println("5.Quit");
			
			int optionselectedbyuser=sc.nextInt();
			 if(optionselectedbyuser==UserOptions.Quit) {
				 
		         System.out.println("!!!program end");
		         canIKeepRunninngTheProgram  = false;
		         
		         
			}
			
			
			 
			 else if(optionselectedbyuser==UserOptions.add_User) {
					addUser();
			}
			else if(optionselectedbyuser==UserOptions.Delete_user) {
				System.out.println("Enter UserName to Delete:");
				sc.nextLine();
				String du=sc.nextLine();
				DeleteUser(du);
			}
			else if(optionselectedbyuser==UserOptions.Edit_User) {
				System.out.println("Enter UserName to edit:");
				sc.nextLine();
				String eu=sc.nextLine();
				editUser(eu);
				
			}else if(optionselectedbyuser==UserOptions.Search_user) {
				System.out.println("Enter UserName to Search:");
				sc.nextLine();
				String sp=sc.nextLine();
				SearchUser(sp);
								
		}
		
	}
		
}
	public static void addUser() {
		Scanner sc=new Scanner(System.in);
	     User u1=new User();
		
		System.out.println("Enter UserName:");
		u1.userName=sc.nextLine();
		
		System.out.println("Enter loginName:");
		u1.LoginName=sc.nextLine();
		
		System.out.println("Enter Password:");
		u1.Password=sc.nextLine();
		
	
		
		System.out.println("Enter userRole:");
         u1.Userrole=sc.nextLine();	
         
         
		System.out.println("Enter UserName:"+u1.userName);
		System.out.println("Enter LoginName:"+u1.LoginName);
		System.out.println("Enter Password:"+u1.Password);
		System.out.println("Enter userrole:"+u1.Userrole);
	
		String query="insert into user3(userName,LoginName,Password,Userrole) VALUES('"+u1.userName+"','"+u1.LoginName+"','"+u1.Password+"','"+u1.Userrole+"')";
		DbUtil.executeQuery(query);

	}
	public static void DeleteUser(String username)
    {
		String query="delete from User where username='"+username+"'";
		 DbUtil.executeQuery(query);; 
		}
	
	public static void editUser(String username) {
		String query="select * from user3 where Username='"+username+"'";
		ResultSet rs = DbUtil.executeQueryGetResult(query); 
		try{
			while(rs.next())
			{
	    	if(rs.getString("Username").equalsIgnoreCase(username)) {
		
		Scanner sc=new Scanner(System.in);
    
		User user=new User();
    		System.out.println("Enter new UserName:");
    		user.userName=sc.nextLine();
    		
    		System.out.println("Enter new loginName:");
    		user.LoginName=sc.nextLine();
    		
    		System.out.println("Enter new Password:");
    		user.Password=sc.nextLine();
    		
    		
    		
    		System.out.println("Enter new userRole:");
             user.Userrole=sc.nextLine();	
            
             System.out.println("Enter userName:"+user.userName);
     		System.out.println("Enter LoginName:"+user.LoginName);
     		System.out.println("Enter passwoed:"+user.Password);
     		System.out.println("Enter  userRole:"+user.Userrole);
     		

    		String updatequery="update  user3 Set" + "username='"+user.userName +"',"+"loginname='"+user.LoginName+"',"+"Password='"+user.Password+"',userrole='"+user.Userrole+"' where username ='"+rs.getString(username)+"'";
    		DbUtil.executeQuery(updatequery);
     		//System.out.println("user info is updated");
    		return;

		}
    	
			}
  
	}
		catch(Exception e)
		{    System.out.println("user not found");
	}
	}
	public static void SearchUser(String Username) {
		String query="select * from user3 where Username='"+Username+"'";
		ResultSet rs = DbUtil.executeQueryGetResult(query); 
		try{
			while(rs.next())
			{
	    	if(rs.getString("Username").equalsIgnoreCase(Username)) {
	    		  System.out.println("Enter userName:"+rs.getString("userName"));
		       		System.out.println("Enter LoginName:"+rs.getString("LoginName"));
		       		System.out.println("Enter passwoed:"+rs.getString("Password"));
		       		System.out.println("Enter  userRole:"+rs.getString("Userrole"));
		       		
		   return;
		      		
	    	}
	}
		}
		catch(Exception e) {
			System.out.println("User not found.");
		}
		
}
/*	public static void loaddatafromusermanagementtofile() throws IOException	{
		File file=new File("\\Users\\VAISHNAVI MATRE\\eclipse-workspace\\ShopManagement3\\src\\User_Management\\User.csv");
		
		 FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		String Line="";
		ResultSet rs=DbUtil.executeQueryGetResult("select * from Users");
		try {
			while(rs.next()) {
		User user=new User();
			   user.userName=rs.getString(1);
			   user.LoginName=rs.getString(2);
			   user.Password=rs.getString(3);
			   user.Userrole=rs.getString(4);
	     al.add(user);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();	
			}
	
		}
		
	*/
		
	
public static boolean validateloginnameandpassword(String loginName,String password)  throws IOException{
	String query="select * from user3 where loginName ='"+loginName+"'and password='"+password+"'";
			
	ResultSet rs=DbUtil.executeQueryGetResult(query);
	try {
		if(rs.next()) {
			return true;
		}
	}
	catch(SQLException e) {
		e.printStackTrace();	
		}
			
		
	return false;
	}

}