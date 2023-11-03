package ApplicationMain;
import java.io.IOException;
import java.util.Scanner;

import Product_Management.ProductManagement;
import User_Management.UserManagement;

public class ApplicationMain {
public static void main(String args[] ) throws IOException{
Scanner sc=new Scanner(System.in);
	
	
    boolean	canIKeepRunninngTheProgram =true;
	
    System.out.println("***Welcome to the Login***");
    
			System.out.println("Enter LoginName:");
			String loginname=sc.nextLine();
			
			System.out.println("Enter Password:");
			String password=sc.nextLine();
		
			if(!UserManagement.validateloginnameandpassword(loginname, password)) {
				System.out.println("!!!!Login Failed.closing the appliacation");
				return;
			}
		 System.out.println("***Welcome to the ShopManagement****");
			
		 	while(canIKeepRunninngTheProgram==true) {
				System.out.println("1.UserManagement()");
				System.out.println("2.ProductManagement()");
				System.out.println("3.Quit");
			
			int optionselectedbyuser=sc.nextInt();
		if(optionselectedbyuser==1) 
		{
			
			UserManagement.userManagement();
		}
		else if(optionselectedbyuser==2) {
			ProductManagement.productMnagement();
		}
		else if(optionselectedbyuser==5)	
		{
		break;
		}
}
}
}
