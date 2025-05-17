import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class seats {


    

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
    public static void cancel_ticket(String cust_name,String movie) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // 3. Create a connection to the database.
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3300/temp", "root", "aadigandhi@2210");
        
        //Scanner sc = new Scanner(new InputStreamReader(System.in));
           int total_Seats;
         int booked;
           
            // System.out.print("enter your name : ");
            // String name = sc.nextLine();
            // System.out.print("enter movie name : ");
            // String movie = sc.nextLine();
        // 4. Prepare a SQL statement.
        //Statement stmt = conn.prepareStatement("SELECT * FROM movie_temp");
        java.sql.Statement pstmt = conn.createStatement();

        String query = "select * from cust_temp where cust_name= '"+name+"'" ; 
        System.out.println(query);

        // 5. Execute the SQL statement.
        ResultSet rs = pstmt.executeQuery(query);

        // 6. Process the results.
        // while (rs.next() ) {
            // String movname = rs.getString("movie_name");
            // String time = rs.getString("time");
            // String price = rs.getString("price");
          int seat = rs.getInt("seats");
        //   String cust_name = rs.getString("cust_name");
        //  String movies_cust = rs.getString("movie_name");
        //    // System.out.println(movname + " " + time + " " + price + " " + seat);
           System.out.println(seat);
           booked = seat;
        //    System.out.println(seat);
        //   String que = "update movie_temp set seats = '"+seat+"' where movie_name= '"+name+"'"; 
        //    System.out.println(que);
   
           
        //   pstmt.executeUpdate(que);
        // }
        
        String cancel = "select * from movie_temp where movie_name= '"+movie+"'" ; 
        System.out.println(cancel);

        // 5. Execute the SQL statement.
        ResultSet ps = pstmt.executeQuery(cancel);

        // 6. Process the results.
        while (ps.next() && rs.next()) {
            // String movname = rs.getString("movie_name");
            // String time = rs.getString("time");
            // String price = rs.getString("price");
          int SEATS = ps.getInt("seats_available");
         // String cust_name = rs.getString("cust_name");
         String movies = ps.getString("movie_name");
           // System.out.println(movname + " " + time + " " + price + " " + seat);
           total_Seats=SEATS;
           System.out.println(total_Seats);

           //total_Seats = seat;
        //    System.out.println(seat);
        String que = "update movie_temp set seats_available = '"+total_Seats+booked+"' where movie_name= '"+movies+"'"; 
         System.out.println(que);
    
           
        pstmt.executeUpdate(que);
            
    }
    }
    // public static void cancel_ticket() throws ClassNotFoundException, SQLException {
    //     Class.forName("com.mysql.cj.jdbc.Driver");
        
    //     // 3. Create a connection to the database.
    //     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3300/temp", "root", "aadigandhi@2210");
        
    //     Scanner sc = new Scanner(new InputStreamReader(System.in));
    //        int RemainingSeats;
           
    //         System.out.print("enter name : ");
    //         String name = sc.nextLine();
    //         System.out.print("enter movie name(same as table) : ");
    //         String movie = sc.nextLine();
    //     // 4. Prepare a SQL statement.
    //     //Statement stmt = conn.prepareStatement("SELECT * FROM movie_temp");
    //     java.sql.Statement pstmt = conn.createStatement();

    //     String query = "select * from cust_temp where cust_name= '"+name+"'"; 
    //     System.out.println(query);

    //     // 5. Execute the SQL statement.
    //     ResultSet rs = pstmt.executeQuery(query);

    //     // 6. Process the results.
    //     String cancel = "select * from movie_temp where movie_name= '"+movie+"'"; 
    //     System.out.println(cancel);


    //     // 5. Execute the SQL statement.
    //     pstmt.executeQuery(cancel);
      
    //     while (rs.next()) {
    //         // String movname = rs.getString("movie_name");
    //         // String time = rs.getString("time");
    //         // String price = rs.getString("price");
    //       int seat = rs.getInt("seats");
    //       String movie_name = rs.getString("movie_name");
    //       String cust_name = rs.getString("cust_name");
    //        // System.out.println(movname + " " + time + " " + price + " " + seat);
    //        System.out.println(seat);
    //         // String movname = rs.getString("movie_name");
    //         // String time = rs.getString("time");
    //         // String price = rs.getString("price");
    //       int total_seats = rs.getInt("seats_available");
    //       String movies_name = rs.getString("movie_name");
    //       //String cust_name = rs.getString("cust_name");
        
   
    //       // seat = seat+seatselected;
    //        System.out.println(total_seats);
    //        String que = "update movie_temp set seats_available = '"+total_seats+seat+"' where movie_name= '"+movies_name+"'"; 
    //         System.out.println(que);
    
            
    //        pstmt.executeUpdate(que);
    //     }
    //   }
        
    

    public static final String ANSI_RESET = "\u001B[0m";
  
    // Declaring the color
    // Custom declaration
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";


    //bg colors
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // System.out.print("enter email : ");
        // String email = sc.nextLine();
        
        // 2. Register the JDBC driver.

        // System.out.print("\n\n\n\n\n");
        // System.out.println(ANSI_YELLOW
        // + "\t\t\t\t------------------------------------------" + ANSI_RESET);
        // System.out.println(ANSI_YELLOW
        // + "\t\t\t\t--WELCOME TO MOVIE TICKET BOOKING SYSTEM--" + ANSI_RESET);
        // System.out.println(ANSI_YELLOW
        // + "\t\t\t\t------------------------------------------" + ANSI_RESET);
        // System.out.println(ANSI_YELLOW
        // + "\t\t\t\t1. Customer Panal" + ANSI_RESET);
        // System.out.println(ANSI_YELLOW
        // + "\t\t\t\t2. Owner Panal" + ANSI_RESET);
        // System.out.println(ANSI_YELLOW
        // + "\t\t\t\t0. Exit" + ANSI_RESET);
        // System.out.println(ANSI_YELLOW
        // + "\t\t\t\t------------------------------------------" + ANSI_RESET);
        
        
        System.out.print("\n\n\n\n\n");
        System.out.println(ANSI_CYAN
        + "\t\t\t\t------------------------------------------" + ANSI_RESET);
        System.out.println(ANSI_YELLOW
        + "\t\t\t\t--WELCOME TO MOVIE TICKET BOOKING SYSTEM--" + ANSI_RESET);
        System.out.println(ANSI_RED
        + "\t\t\t\t------------------------------------------" + ANSI_RESET);
        System.out.println(ANSI_PURPLE
        + "\t\t\t\t1. Customer Panal" + ANSI_RESET);
        System.out.println(ANSI_GREEN
        + "\t\t\t\t2. Owner Panal" + ANSI_RESET);
        System.out.println(ANSI_CYAN
        + "\t\t\t\t0. Exit" + ANSI_RESET);
        System.out.println(ANSI_CYAN
        + "\t\t\t\t------------------------------------------" + ANSI_RESET);
       
        // System.out.print("\n\n\n\n\n");
        // System.out.println(ANSI_GREEN
        // + "\t\t\t\t------------------------------------------" + ANSI_RESET);
        // System.out.println(ANSI_YELLOW
        // + "\t\t\t\t--WELCOME TO MOVIE TICKET BOOKING SYSTEM--" + ANSI_RESET);
        // System.out.println(ANSI_GREEN
        // + "\t\t\t\t------------------------------------------" + ANSI_RESET);
        // System.out.println(ANSI_GREEN
        // + "\t\t\t\t1. Customer Panal" + ANSI_RESET);
        // System.out.println(ANSI_GREEN
        // + "\t\t\t\t2. Owner Panal" + ANSI_RESET);
        // System.out.println(ANSI_GREEN
        // + "\t\t\t\t0. Exit" + ANSI_RESET);
        // System.out.println(ANSI_GREEN
        // + "\t\t\t\t------------------------------------------" + ANSI_RESET);
       
        System.out.println(ANSI_YELLOW 
        + "This text is yellow"
        + ANSI_RESET);
        

        System.out.println(ANSI_WHITE_BACKGROUND
        + "The background color is red"
        + ANSI_RESET);

        
     cancel_ticket();
       // conn.close();
    }
}