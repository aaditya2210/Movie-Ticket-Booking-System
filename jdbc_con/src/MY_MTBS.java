import java.sql.*;
import java.util.Scanner;
import java.io.*;

 
public class MY_MTBS{
    

    public static void execute_query( String query) {
 

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
    

    public static boolean login() throws SQLException{
        System.out.println(ANSI_PURPLE+"\n\n\n\n\t\t\t\t------------------------"+ANSI_RESET);
        System.out.println(ANSI_YELLOW_BACKGROUND+"\t\t\t\tAUTHENTICATION REQUIRED"+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"\t\t\t\t------------------------\n\n"+ANSI_RESET);
        java.io.Console console=System.console();
        System.out.print("\t\t\t\t");
        String username =console.readLine(ANSI_BLUE+"Username: "+ANSI_RESET);
        System.out.print("\t\t\t\t");
        String password =new String(console.readPassword(ANSI_BLUE+"Password: "+ANSI_RESET));
        System.out.println("\n\n\n");
        if(username.equals("root") && password.equals("root")){
            //System.out.println("\t\t\t\tAUTHENTICATION SUCCESSFULL");
            return true;
        }
        else{
            return false;
        }
    }

    
    public static void AddNewMovie() throws SQLException{

        try{
            
        
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            System.out.print("enter movie name : ");
            String name = br.readLine();
            System.out.print("enter time : ");
            String time = br.readLine();
            System.out.print("enter price : ");
            String price = br.readLine();
            System.out.print("enter seats : ");
            String seat = br.readLine();
            System.out.print("enter location : ");
            String loc = br.readLine();
            
            
            
            
            
        
        String query = "INSERT into cinema(movie_name,show_time,price,seats,C_name) values ('"+name+"','"+time+"','"+price+"','"+seat+"','"+loc+"')";

        //System.out.println(query);
        execute_query(query);

        System.out.println(ANSI_CYAN+" movie inserted successfully...."+ANSI_RESET);


    }

    catch(Exception e)
    {
        System.out.println(e);
    }
}
    public static void BookTicket() throws SQLException{

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
        
            // 3. Create a connection to the database.
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3300/db", "root", "2210");
            
        
            
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

        //System.out.println(query);
        execute_query(query);

        System.out.println(ANSI_PURPLE+" TICKET BOOKED SUCCESSFULLY...."+ANSI_RESET);




        java.sql.Statement pstmt = conn.createStatement();

        String quer = "select seats,price from cinema where movie_name= '"+mov_name+"'" ; 
        //System.out.println(quer);

        // 5. Execute the SQL statement.
        ResultSet rs = pstmt.executeQuery(quer);

        // 6. Process the results.
        while (rs.next()) {
            // String movname = rs.getString("movie_name");
            // String time = rs.getString("time");
            int price = rs.getInt("price");
          double RemainingSeats = rs.getInt("seats");

        
         

              System.out.println(seat);
              RemainingSeats = RemainingSeats - Integer.parseInt(seat);
              System.out.println(RemainingSeats);
    
              System.out.println(price);
              price = (price * Integer.parseInt(seat));
              System.out.println(price);
    
    
             String que = "update cinema set seats = '"+RemainingSeats+"' where movie_name= '"+mov_name+"'"; 
              //System.out.println(que);
              pstmt.executeUpdate(que);
    
    
              String q = "update customer set total_amt = '"+price+"' where cust_name= '"+cust_name+"'"; 
              //System.out.println(q);
             pstmt.executeUpdate(q);
          }
           // System.out.println(movname + " " + time + " " + price + " " + seat);
        



    }

    catch(Exception e)
    {
        System.out.println(e);
    }
}

    public static void DeleteMovie() throws SQLException{

        try{
            
        
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String query = "select * from cinema";
            execute_query(query);
            

            
            
            System.out.print("enter movie name : ");
            String name = br.readLine();
            
            
            
            
            
        
         query = "DELETE from cinema where movie_name = '"+name+"'";
        //System.out.println(query);
        execute_query(query);

        System.out.println(ANSI_RED+" movie deleted successfully...."+ANSI_RESET);


    }

    catch(Exception e)
    {
        System.out.println(e);
    }
}
public static void bookinglist() {

    try{
            
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3300/db";
        String username = "root";
        String password = "aadigandhi@2210";

        Connection con1 = DriverManager.getConnection(url, username, password);

       String query = "select customer.cust_name,cinema.movie_name,cinema.show_time,customer.seats_booked,cinema.C_name,customer.total_amt from cinema inner join customer on cinema.movie_name=customer.movie_name;"; 
        
        //String query = "INSERT into cinema(movie_name,show_time,price,seats,C_name) values (?,?,?,?,?)";

       java.sql.Statement pstmt = con1.createStatement();

       ResultSet rs = pstmt.executeQuery(query);
       

        //pstmt.executeUpdate();
        System.out.println(ANSI_BLUE+"DISPLAYING BOOKING LIST..."+ANSI_RESET);
        DBTablePrinter.printResultSet(rs);

}

catch(Exception e)
{
    System.out.println(e);
}

    
}
   
public static void checkticket() {

    try{
            
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3300/db";
        String username = "root";
        String password = "aadigandhi@2210";

        Connection con1 = DriverManager.getConnection(url, username, password);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            System.out.print("enter your name : ");
            String cust_name = br.readLine();

       String query = "select customer.cust_name,cinema.movie_name,cinema.show_time,customer.seats_booked,cinema.C_name,customer.total_amt from cinema inner join customer on cinema.movie_name=customer.movie_name where customer.cust_name='"+cust_name+"'"; 
        
        //String query = "INSERT into cinema(movie_name,show_time,price,seats,C_name) values (?,?,?,?,?)";

       java.sql.Statement pstmt = con1.createStatement();

       ResultSet rs = pstmt.executeQuery(query);
       

        //pstmt.executeUpdate();
        System.out.println(ANSI_YELLOW+"DISPLAYING YOUR TICKET..."+ANSI_RESET);
        DBTablePrinter.printResultSet(rs);

}

catch(Exception e)
{
    System.out.println(e);
}

    
}







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
public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";







   
        
    public static void main(String[] args) throws SQLException{

        System.out.print(ANSI_PURPLE+"\n\n\n\n\n"+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"\t\t\t\t------------------------------------------"+ANSI_RESET);
        System.out.println(ANSI_PURPLE_BACKGROUND+"\t\t\t\t--WELCOME TO MOVIE TICKET BOOKING SYSTEM--"+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"\t\t\t\t------------------------------------------"+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"\t\t\t\t1. ADMIN"+ANSI_RESET);
        System.out.println(ANSI_BLUE+"\t\t\t\t2. CUSTOMER"+ANSI_RESET);
        System.out.println(ANSI_RED+"\t\t\t\t0. EXIT"+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"\t\t\t\t------------------------------------------"+ANSI_RESET);
        System.out.println(ANSI_GREEN+"\t\t\t\tEnter your Choice...:"+ANSI_RESET);
        System.out.print("\t\t\t\t");
        Scanner sc = new Scanner(System.in);
       int Choice = sc.nextInt();

       switch(Choice)
       {
        case 1:
        login();
        boolean flag=true;

        while(flag)
        {

        int choice_admin;

        System.out.print("\n\n\n\n\n");
        System.out.println(ANSI_PURPLE+"\t\t\t\t------------------------------------------"+ANSI_RESET+ANSI_RESET);
        System.out.println(ANSI_PURPLE_BACKGROUND+"\t\t\t\t--WELCOME TO MOVIE TICKET BOOKING SYSTEM--"+ANSI_RESET+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"\t\t\t\t------------------------------------------"+ANSI_RESET+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"\t\t\t\t------------ADMIN CONTROL PANEL------------"+ANSI_RESET+ANSI_RESET);
        System.out.println();
        System.out.println(ANSI_YELLOW+"\t\t\t\t1. ADD NEW MOVIE"+ANSI_RESET+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"\t\t\t\t2. DELETE MOVIE"+ANSI_RESET+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"\t\t\t\t3. MOVIE LISTINGS"+ANSI_RESET+ANSI_RESET);
        System.out.println(ANSI_YELLOW+"\t\t\t\t4. BOOKING LIST"+ANSI_RESET+ANSI_RESET);
        System.out.println(ANSI_RED+"\t\t\t\t0. EXIT"+ANSI_RESET+ANSI_RESET);
        System.out.println(ANSI_PURPLE+"\t\t\t\t------------------------------------------"+ANSI_RESET+ANSI_RESET);
        System.out.println(ANSI_GREEN+"\t\t\t\tEnter your Choice...:"+ANSI_RESET+ANSI_RESET);
        System.out.print("\t\t\t\t");
        //Scanner sc = new Scanner(System.in);
       choice_admin = sc.nextInt();

      
        
       


       switch(choice_admin)
       {
        case 1:
        AddNewMovie();
        break;

        case 2:
        print_table("cinema");
        DeleteMovie();
        break;


        case 3:
        print_table("cinema");
        break;


        case 4:
        bookinglist();
        break;

        case 0:
        System.out.print("\n\n\n\n\n\n");
                        System.out.println(ANSI_BLUE+"\t\t\t\t\t--------------------------------------------"+ANSI_RESET);
                        System.out.println(ANSI_BLUE+"\t\t\t\t\t\tTHANK YOU FOR USING MTBS ADMIN CONTROL PANEL....!!!!"+ANSI_RESET);
                        System.out.println(ANSI_BLUE+"\t\t\t\t\t\tDEVELOPED BY:--"+ANSI_RESET);
                        System.out.println(ANSI_BLUE+"\t\t\t\t\t\tAADITYA GANDHI & AYUSH DAVRA"+ANSI_RESET);
                        System.out.println(ANSI_BLUE+"\t\t\t\t\t--------------------------------------------\n\n\n"+ANSI_RESET); 
                        flag=false;
                       
                       

       }
    }
    break;

       case 2:
       
       boolean flag2 = true;
       while(flag2)
        {
       int choice_customer;
       System.out.print("\n\n\n\n");
       System.out.println(ANSI_PURPLE+"\t\t\t\t------------------------------------------"+ANSI_RESET);
       System.out.println(ANSI_YELLOW_BACKGROUND+"\t\t\t\t--WELCOME TO MOVIE TICKET BOOKING SYSTEM--"+ANSI_RESET);
       System.out.println(ANSI_PURPLE+"\t\t\t\t------------------------------------------"+ANSI_RESET);
       System.out.println(ANSI_PURPLE+"\t\t\t\t....MENU...."+ANSI_RESET);
       System.out.println(ANSI_PURPLE+"\t\t\t\t1. BOOK TICKET"+ANSI_RESET);
       System.out.println(ANSI_PURPLE+"\t\t\t\t2. CHECK TICKET"+ANSI_RESET);
       System.out.println(ANSI_PURPLE+"\t\t\t\t3. MOVIE LIST"+ANSI_RESET);
       System.out.println(ANSI_RED+"\t\t\t\t0. EXIT"+ANSI_RESET);
       //System.out.println("\t\t\t\t3. Cancle Ticket");
       //System.out.println("\t\t\t\t4. Check Seat");
       //System.out.println("\t\t\t\t6. Back");
       System.out.println(ANSI_PURPLE+"\t\t\t\t------------------------------------------"+ANSI_RESET);
       System.out.println(ANSI_GREEN+"\t\t\t\tEnter your Choice...:"+ANSI_RESET);
       System.out.print("\t\t\t\t");
       choice_customer=sc.nextInt();

       switch(choice_customer)
       {

        case 1:
        print_table("cinema");
        BookTicket();
        break;

        case 2:
        checkticket();
        break;

        case 3:
        print_table("cinema");
        break;

        case 0:
        System.out.print("\n\n\n\n\n\n");
                        System.out.println(ANSI_BLUE+"\t\t\t\t\t--------------------------------------------"+ANSI_RESET);
                        System.out.println(ANSI_BLUE+"\t\t\t\t\t\tTHANK YOU FOR USING MTBS....!!!!"+ANSI_RESET);
                        System.out.println(ANSI_BLUE+"\t\t\t\t\t\tDEVELOPED BY:--"+ANSI_RESET);
                        System.out.println(ANSI_BLUE+"\t\t\t\t\t\tAADITYA GANDHI & AYUSH DAVRA"+ANSI_RESET);
                        System.out.println(ANSI_BLUE+"\t\t\t\t\t--------------------------------------------\n\n\n"+ANSI_RESET); 
                        flag2 = false;
                        break;
                        


       }
    }
       break;

       case 0:
       System.out.print("\n\n\n\n\n\n");
                       System.out.println(ANSI_BLUE+"\t\t\t\t\t--------------------------------------------"+ANSI_RESET);
                       System.out.println(ANSI_BLUE+"\t\t\t\t\t\tTHANK YOU FOR USING MTBS....!!!!"+ANSI_RESET);
                       System.out.println(ANSI_BLUE+"\t\t\t\t\t\tDEVELOPED BY:--"+ANSI_RESET);
                       System.out.println(ANSI_BLUE+"\t\t\t\t\t\tAADITYA GANDHI & AYUSH DAVRA"+ANSI_RESET);
                       System.out.println(ANSI_BLUE+"\t\t\t\t\t--------------------------------------------\n\n\n"+ANSI_RESET); 
                       
                       break;




        


       }
       
        
    }
      
}
