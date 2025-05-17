import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class demo {

    public static void BookTicket() throws SQLException{

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
        
            // 3. Create a connection to the database.
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3300/db", "root", "aadigandhi@2210");
            
            
        
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            System.out.print("enter your name : ");
            String cust_name = br.readLine();
            System.out.print("enter movie name (same as in the table) : ");
            String mov_name = br.readLine();
            System.out.print("enter your phone number : ");
            String mob = br.readLine();
            System.out.print("enter seats : ");
           String seat = br.readLine();
           
           
         String query = "INSERT into customer(cust_name,movie_name,phone_no,seats_booked) values ('"+cust_name+"','"+mov_name+"','"+mob+"','"+seat+"')";

        System.out.println(query);
        execute_query(query);

        System.out.println(" TICKET BOOKED SUCCESSFULLY....");


        java.sql.Statement pstmt = conn.createStatement();

        String quer = "select price,seats from cinema where movie_name= '"+mov_name+"'" ; 
        System.out.println(quer);

        // 5. Execute the SQL statement.
        ResultSet rs = pstmt.executeQuery(quer);

        // 6. Process the results.
        while (rs.next()) {
            // String movname = rs.getString("movie_name");
            // String time = rs.getString("time");
          int price = rs.getInt("price");
          double RemainingSeats = rs.getInt("seats");
           // System.out.println(movname + " " + time + " " + price + " " + seat);
           System.out.println(seat);
           RemainingSeats = RemainingSeats - Integer.parseInt(seat);
           System.out.println(RemainingSeats);

           System.out.println(price);
           price = (price * Integer.parseInt(seat));
           System.out.println(price);
          String que = "update cinema set seats = '"+RemainingSeats+"' where movie_name= '"+mov_name+"'"; 
           System.out.println(que);
          pstmt.executeUpdate(que);
          String q = "update customer set total_amt = '"+price+"' where cust_name= '"+cust_name+"'"; 
           System.out.println(q);
          pstmt.executeUpdate(q);
        }


    }

    catch(Exception e)
    {
        System.out.println(e);
    }
}




    public static void print_join_table() {

        try{
                
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3300/db";
            String username = "root";
            String password = "aadigandhi@2210";
    
            Connection con1 = DriverManager.getConnection(url, username, password);
    
           String query = "select customer.cust_name,cinema.movie_name,cinema.show_time,customer.seats_booked,cinema.C_name from cinema inner join customer on cinema.movie_name=customer.movie_name;"; 
            
            //String query = "INSERT into cinema(movie_name,show_time,price,seats,C_name) values (?,?,?,?,?)";
    
           java.sql.Statement pstmt = con1.createStatement();
    
           ResultSet rs = pstmt.executeQuery(query);
           
    
            //pstmt.executeUpdate();
            System.out.println("Printing table in method");
            DBTablePrinter.printResultSet(rs);
    
    }
    
    catch(Exception e)
    {
        System.out.println(e);
    }
    
        
    }
    
    public static void seat_calc() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // 3. Create a connection to the database.
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3300/temp", "root", "aadigandhi@2210");
        
        Scanner sc = new Scanner(new InputStreamReader(System.in));
           int RemainingSeats;
           
            System.out.print("enter name : ");
            String name = sc.nextLine();
            System.out.print("enter seats : ");
            int seatselected = sc.nextInt();
        // 4. Prepare a SQL statement.
        //Statement stmt = conn.prepareStatement("SELECT * FROM movie_temp");
        java.sql.Statement pstmt = conn.createStatement();

        String query = "select seats from movie_temp where movie_name= '"+name+"'" ; 
        System.out.println(query);

        // 5. Execute the SQL statement.
        ResultSet rs = pstmt.executeQuery(query);

        // 6. Process the results.
        while (rs.next()) {
            // String movname = rs.getString("movie_name");
            // String time = rs.getString("time");
            // String price = rs.getString("price");
          int seat = rs.getInt("seats");
           // System.out.println(movname + " " + time + " " + price + " " + seat);
           System.out.println(seat);
           seat = seat-seatselected;
           System.out.println(seat);
          String que = "update movie_temp set seats = '"+seat+"' where movie_name= '"+name+"'"; 
           System.out.println(que);
   
           
          pstmt.executeUpdate(que);
        }
        
    }
		
	


        
    
public static void print_table(String tableName) {

    try{
            
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3300/db";
        String username = "root";
        String password = "aadigandhi@2210";

        Connection con1 = DriverManager.getConnection(url, username, password);

        String query = "select * from " + tableName; 
        
        //String query = "INSERT into cinema(movie_name,show_time,price,seats,C_name) values (?,?,?,?,?)";

       java.sql.Statement pstmt = con1.createStatement();

       ResultSet rs = pstmt.executeQuery( query );
       

        //pstmt.executeUpdate();
        System.out.println("Printing table in method");
        DBTablePrinter.printResultSet(rs);

}

catch(Exception e)
{
    System.out.println(e);
}

    
}


    public static void execute_query(String query) {
 

        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3300/db";
            String username = "root";
            String password = "aadigandhi@2210";
    
            Connection con = DriverManager.getConnection(url, username, password);
            
            //String query = "INSERT into cinema(movie_name,show_time,price,seats,C_name) values (?,?,?,?,?)";
    
            PreparedStatement pstmt = con.prepareStatement(query);
           

            pstmt.executeUpdate();

    }

    catch(Exception e)
    {
        System.out.println(e);
    }

        
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException{

        //     String query = "INSERT into cinema(movie_name,show_time,price,seats,C_name) values ('Avengers','3pm','500','1000','inox')";
        //  execute_query(query);
// System.out.println("Printing table");
 //print_join_table();
//          System.out.println("Table Printed");

BookTicket();
       }
}

