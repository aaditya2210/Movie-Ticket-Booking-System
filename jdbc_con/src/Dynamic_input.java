import java.sql.*;
import java.util.Scanner;
import java.io.*;

 
public class Dynamic_input{

    public static void main(String[] args) {
        try{

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3300/db";
            String username = "root";
            String password = "aadigandhi@2210";


            Connection con = DriverManager.getConnection(url, username, password);
            
            String query = "INSERT into Users(name,email,country,password) values (?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(query);
           // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Scanner sc = new Scanner(new InputStreamReader(System.in));
           
           
            System.out.print("enter name : ");
            String name = sc.nextLine();
            System.out.print("enter email : ");
            String email = sc.nextLine();
            System.out.print("enter country : ");
            String country = sc.nextLine();
            System.out.print("enter pass : ");
            String pass = sc.nextLine();




            pstmt.setString(1,name);
            pstmt.setString(2,email);
            pstmt.setString(3,country);
            pstmt.setString(4,pass);

            pstmt.executeUpdate();

            System.out.println("inserted....");


        }

        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
      
}