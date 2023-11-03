package Product_Management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import db_operations.DbUtil;

public class ProductManagement {
	
	public static void productMnagement() throws IOException {
	
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to the ProductManagement:");
		
	    boolean	canIKeepRunninngTheProgram =true;
		while(canIKeepRunninngTheProgram==true) {
			System.out.println("1.Add Product");
			System.out.println("2.Delete Product");
			System.out.println("3.edit Product");
			System.out.println("4.Search Product");
			System.out.println("5.Quit");
			
			int optionselectedbyuser=sc.nextInt();
			 if(optionselectedbyuser==ProductOptions.Quit) {
				
		         System.out.println("!!!program end");
		         canIKeepRunninngTheProgram  = false;
		        
			}
			
			
			 
			 else if(optionselectedbyuser==ProductOptions.Add_Product) {
					addProduct();
			}
			else if(optionselectedbyuser==ProductOptions.Delete_Product) {
				System.out.println("Enter ProductName to Delete:");
				sc.nextLine();
				String dp=sc.nextLine();
				DeleteProduct(dp);
			}
			else if(optionselectedbyuser==ProductOptions.edit_Product) {
				System.out.println("Enter ProductName to edit:");
				sc.nextLine();
				String ep=sc.nextLine();
				editProduct(ep);
				
			}else if(optionselectedbyuser==ProductOptions.Search_product) {
				System.out.println("Enter ProductName to Search:");
				sc.nextLine();
				String sp=sc.nextLine();
				SearchProduct(sp);
								
		}
		
	}
		

}
	public static void addProduct() {
		Scanner sc=new Scanner(System.in);
		Product p1=new Product();
		
		System.out.println("Enter ProductName:");
		p1.ProductName=sc.nextLine();
		
		System.out.println("Enter ProductId:");
		p1.ProductId=sc.nextLine();
		
		System.out.println("Enter Price:");
		p1.Price=sc.nextLine();
		
		System.out.println("Enter Quality:");
		p1.Quality=sc.nextLine();
		
		System.out.println("Enter Category:");
         p1.Category=sc.nextLine();	
         
         
		System.out.println("Enter ProductName:"+p1.ProductName);
		System.out.println("Enter ProductId:"+p1.ProductId);
		System.out.println("Enter Price:"+p1.Price);
		System.out.println("Enter Quality:"+p1.Quality);
		System.out.println("Enter Category:"+p1.Category);
		
		String query="insert into Product(ProductName,ProductID,Price,Quality,Category)VALUES('"+p1.ProductName+"','"+p1.ProductId+"','"+p1.Price+"','"+p1.Quality+"','"+p1.Category+"')";
		DbUtil.executeQuery(query);
	}
	public static void DeleteProduct(String productname)
    {
		String query="delete from product where productname='"+productname+"'";
	 DbUtil.executeQuery(query);
		}
	
	public static void editProduct(String productname) {
		String query="select * from product where Productname='"+productname+"'";
		ResultSet rs = DbUtil.executeQueryGetResult(query); 
		
		Scanner sc=new Scanner(System.in);
		try{
			while(rs.next())
			{
	    	if(rs.getString("productname").equalsIgnoreCase(productname))
		{
	    		
		    Product product=new Product();
    		System.out.println("Enter new ProductName:");
    		product.ProductName=sc.nextLine();
    		
    		System.out.println("Enter new ProductId:");
    		product.ProductId=sc.nextLine();
    		
    		System.out.println("Enter new Price:");
    		product.Price=sc.nextLine();
    		
    		System.out.println("Enter new Quality:");
    		product.Quality=sc.nextLine();
    		
    		System.out.println("Enter new Category:");
             product.Category=sc.nextLine();	
            
             System.out.println("Enter new ProductName:"+product.ProductName);
     		System.out.println("Enter new ProductId:"+product.ProductId);
     		System.out.println("Enter new Price:"+product.Price);
     		System.out.println("Enter  new Quality:"+product.Quality);
     		System.out.println("Enter  new Category:"+product.Category);
     		
     		
    		String updatequery="update  Product Set"+ "Productname='"+product.ProductName +"',"+"Productid='"+product.ProductId+"',"+"price='"+product.Price+"',"+"Quality='"+product.Quality+"',"+"category='"+product.Category+"' where productName='"+rs.getString(productname)+"'";
    	//	System.out.println("Product info is updated");
    		DbUtil.executeQuery(updatequery);
    		return;
     		
		}
		}
    	
}
		catch(Exception e)
		{    System.out.println("Product not found.");
	}
	}
	public static void SearchProduct(String productname) {
		String query="select * from product where productname='"+productname+"'";
		ResultSet rs = DbUtil.executeQueryGetResult(query); 
		try{
			while(rs.next())
			{
	    	if(rs.getString("productname").equalsIgnoreCase(productname)) {
	    		 System.out.println("Enter ProductName:"+rs.getString("productname"));
	    		 System.out.println("Enter ProductID:"+rs.getString("productID"));
	    		 System.out.println("Enter Price:"+rs.getString("price"));
	    		 System.out.println("Enter Quality:"+rs.getString("Quality"));
	    		 System.out.println("Enter Category:"+rs.getString("Category"));
		      		return;
		      		
	    	}
	}
		}
		catch(Exception e) {
			System.out.println("User not found.");
		}
}
	/*public static void loaddatafromproductmanagementtofile() throws IOException 	{
		File file=new File("\\Users\\VAISHNAVI MATRE\\eclipse-workspace\\ShopManagement3\\src\\Product_management\\Product.csv");
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		String Line="";
		while((Line=br.readLine())!=null) {
			Product product = new Product();
			String[] productDataArray=Line.split(",");
			if(productDataArray.length>4) {
				product.ProductName=productDataArray[0];
				product.ProductId=productDataArray[1];
				product.Price=productDataArray[2];
				product.Category=productDataArray[3];
				product.Quality=productDataArray[4];
al.add(product);
			}
			
		}
	}*/
	}
