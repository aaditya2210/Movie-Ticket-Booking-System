import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;		
import java.util.*;

class MTBS{

    static Scanner sc=new Scanner(System.in);
    static Connection con;

    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3300/mtbs","root","root");
        }
        catch(Exception e){
            System.out.println("\n\n\n\n\t\t\t\t"+e.getMessage());
        }
        
    }

    public static boolean login() throws SQLException{
        System.out.println("\n\n\n\n\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\tLOGIN TO MOVIE TICKET BOOKING");
        System.out.println("\t\t\t\t-----------------------------\n\n");
        java.io.Console console=System.console();
        System.out.print("\t\t\t\t");
        String username =console.readLine("Username: ");
        System.out.print("\t\t\t\t");
        String password =new String(console.readPassword("Password: "));
        System.out.println("\n\n\n");
        if(username.equals("root") && password.equals("2210")){
            return true;
        }
        else{
            return false;
        }
    }


    public static void BookTicket() throws SQLException{
        try{
            int num=ShowMovieList();
            int Id;
            System.out.println("\n\n\n\n");
            if(num==0){
                System.out.println("\t\t\t\t--------------------------------------");
                System.out.println("\t\t\t\tSorry...!!!");
                System.out.println("\n\t\t\t\tThere is no show going on.....!!!!!!");
                System.out.println("\t\t\t\t--------------------------------------");
                return;
            }
            while(true){
                System.out.println("\t\t\t\tEnter movie Id(0 to Exit):");
                System.out.print("\t\t\t\t");
                Id=sc.nextInt();
                if(Id==0){
                    return;
                }
                PreparedStatement pst=con.prepareStatement("select * from movie where columnid=?");
                pst.setInt(1,Id);
                ResultSet rs=pst.executeQuery();
                if(rs.next()){
                    System.out.println("\t\t\t\tEnter Customer Name: ");
                    sc.nextLine();
                    System.out.print("\t\t\t\t");
                    String Cname=sc.nextLine();
                    System.out.println("\t\t\t\tEnter Phone no.: ");
                    System.out.print("\t\t\t\t");
                    String Pno=sc.nextLine();
                    System.out.println("\t\t\t\tEnter seats: ");
                    System.out.print("\t\t\t\t");
                    int seat=sc.nextInt();
                    int RemainingSeats;
                    java.util.Date d;
                    java.util.Date t;
                    double price;
                    String Mname,format;
                    Mname=rs.getString(2);
                    format=rs.getString(3);
                    d=rs.getDate(4);
                    java.sql.Date date=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
                    t=rs.getTime(5);
                    java.sql.Time time=new Time(t.getHours(),t.getMinutes(),0);
                    price=rs.getDouble(6);
                    RemainingSeats=rs.getInt(7)-seat;
                    if(RemainingSeats<0){
                        System.out.println("\t\t\t\t---------------------------");
                        System.out.println("\t\t\t\tInsufficient seats....!!!");
                        System.out.println("\t\t\t\tBooking is cancelled...!!!!");
                        System.out.println("\t\t\t\t---------------------------");
                        return;
                    }
                    PreparedStatement pst1=con.prepareStatement("insert into customer (name,phoneNo,columnid,Mname,format,date,time,price,seat) values(?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                    pst1.setString(1,Cname);
                    pst1.setString(2,Pno);
                    pst1.setInt(3,Id);
                    pst1.setString(4,Mname);
                    pst1.setString(5,format);
                    pst1.setDate(6,date);
                    pst1.setTime(7,time);
                    pst1.setDouble(8,price*seat);
                    pst1.setInt(9,seat);
                    if(pst1.executeUpdate()>0){
                        System.out.println("\t\t\t\t-------------------");
                        System.out.println("\t\t\t\tTicket Booked...!!!");
                        System.out.println("\t\t\t\t-------------------");
                    }
                    else{
                        System.out.println("\t\t\t\t------------------------------");
                        System.out.println("\t\t\t\tSomething got Wrong.....!!!!!!");
                        System.out.println("\t\t\t\t------------------------------");
                        return;
                    }
                    pst1=con.prepareStatement("update movie set seat=? where columnId=?");
                    pst1.setInt(1,RemainingSeats);
                    pst1.setInt(2,Id);
                    if(pst1.executeUpdate()<=0){
                        System.out.println("\t\t\t\t-------------------------------");
                        System.out.println("\t\t\t\tSomething went Wrong.....!!!!!!");
                        System.out.println("\t\t\t\t-------------------------------");
                        return;
                    }
                    System.out.print("\t\t\t\tPress any key.....");
                    sc.next();
                    pst1=con.prepareStatement("select * from customer where name=? and phoneNo=?");
                    pst1.setString(1,Cname);
                    pst1.setString(2,Pno);
                    ResultSet rs1=pst1.executeQuery();
                    if(rs1.next()){
                    clearscreen();
                    showMyTicket(rs1.getInt(10));
                    }
                    else{
                        System.out.println("\t\t\t\t-------------------------------");
                        System.out.println("\t\t\t\tSomething went Wrong.....!!!!!!");
                        System.out.println("\t\t\t\t-------------------------------");
                    }
                    return;
                }
                else{
                    System.out.println("\t\t\t\t---------------------------");
                    System.out.println("\t\t\t\tPlease Input a valid Id....");
                    System.out.println("\t\t\t\t---------------------------");
                }

            }
        }
        catch(Exception e){
            System.out.println("\t\t\t\tException occured");
        }
    }


    public static void showMyTicket(int num) throws SQLException{
        try{
            PreparedStatement pst=con.prepareStatement("select * from customer where Cid=?");
            pst.setInt(1,num);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                System.out.println("\n\n\n\t\t\t\t\t---MOVIE TICKET--");
                System.out.println("\t\t\t---------------------------------------------------------");
                System.out.println("\t\t\t\tName-\t\t\t"+rs.getString(1));
                System.out.println("\t\t\t\tPhone No.-\t\t"+rs.getString(2));
                System.out.println("\t\t\t---------------------------------------------------------");
                System.out.println("\t\t\t\tMovie Name-\t\t"+rs.getString(4));
                System.out.println("\t\t\t\tType-\t\t\t"+rs.getString(5));
                System.out.println("\t\t\t\tdate-\t\t\t"+rs.getDate(6));
                System.out.println("\t\t\t\tTime-\t\t\t"+rs.getString(7));
                System.out.println("\t\t\t\tSeat(s)-\t\t"+rs.getInt(9));
                System.out.println("\t\t\t\tPrice-\t\t\t"+rs.getDouble(8));
                System.out.println("\t\t\t\tUnique Id-\t\t"+rs.getInt(10));
                System.out.println("\t\t\t---------------------------------------------------------");
                System.out.println("\n\n");

            }
            else{
                System.out.println("\t\t\t\t-------------------------------");
                System.out.println("\t\t\t\tNo Booking available.....!!!!!!");
            }
            
        }
        catch(Exception e){
            System.out.println("\t\t\t\tException occured in showing ticket");
        }
    }


    public static void CancleTicket() throws SQLException{
        try{
            while(true){
                System.out.print("\n\n\n\n");
                System.out.print("\t\t\t\tEnter Unique Id(0 for exit): ");
                int num=sc.nextInt();
                if(num==0){
                    return;
                }
                int seat,Mid;
                PreparedStatement pst=con.prepareStatement("select * from customer where Cid=?");
                pst.setInt(1,num);
                ResultSet rs=pst.executeQuery();
                if(rs.next()){
                    seat=rs.getInt(9);
                    Mid=rs.getInt(3);
                }
                else{
                    System.out.println("\t\t\t\t-------------------------------");
                    System.out.println("\t\t\t\tThe Unique Id doesn\'t Exist...!!");
                    System.out.println("\t\t\t\tPlease enter correct Unique Id...!!\n");
                    System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                    sc.next();
                    clearscreen();
                    continue;
                }
                pst=con.prepareStatement("select * from movie where columnid=?");
                pst.setInt(1,Mid);
                rs=pst.executeQuery();
                if(rs.next()){
                    showMyTicket(num);
                    System.out.println("\n\n\t\t\t\tPress \'c\' to cancle ticket...");
                    System.out.print("\t\t\t\t");
                    char t=sc.next().charAt(0);
                    if(t!='c'&& t!='C'){
                        System.out.println("\n\t\t\t\tTicket is not cancelled....");
                        System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                        sc.next();
                        return;
                    }
                    PreparedStatement pst1=con.prepareStatement("update movie set seat=? where columnid=?");
                    pst1.setInt(1,seat+rs.getInt(7));
                    pst1.setInt(2,Mid);
                    if(pst1.executeUpdate()<=0){
                        System.out.println("\t\t\t\tException occured.........");
                    }
                }
                pst=con.prepareStatement("delete from customer where Cid=?");
                pst.setInt(1,num);
                if(pst.executeUpdate()>0){
                    System.out.println("\t\t\t\t-------------------------------");
                    System.out.println("\t\t\t\tThe ticket is cancelled.......!!");
                    return;
                }
                else{
                    System.out.println("\t\t\t\t-------------------------------");
                    System.out.println("\t\t\t\tThe Unique Id doesn\'t Exist...!!");
                    System.out.println("\t\t\t\tPlese enter correct Unique Id...!!\n");
                    System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                    sc.next();
                    clearscreen();
                }
            }
        }
        catch(Exception e){
            System.out.println("\t\t\t\t----------------------------");
            System.out.println("\t\t\t\tSome Error occured.....!!!!!!\n\n");
        }
    }


    public static void CheckSeat() throws SQLException{
        try{
            PreparedStatement pst=con.prepareStatement("Select * from movie");
            ResultSet rs=pst.executeQuery();
            System.out.print("\n\n\n\n");
            System.out.println("\t\t\t\t\t--CHECK SEATS--");
            System.out.println("\t\t\t\t-------------------------");
            System.out.println("\t\t\t\t| MOVIE NAME\t| SEATS\t|");
            System.out.println("\t\t\t\t-------------------------");
            while(rs.next()){
                System.out.println("\t\t\t\t| "+rs.getString(2)+"\t| "+rs.getInt(7)+"\t|");
            } 
            System.out.println("\t\t\t\t-------------------------\n\n");
        }
        catch(Exception e){
            System.out.println("\t\t\t\t----------------------------");
            System.out.println("\t\t\t\tSome Error occured.....!!!!!!\n\n");
        }
    }


    public static void AddNewMovie() throws SQLException{
        try{
            System.out.print("\n\n\n\n");
            System.out.println("\t\t\t\tEnter Movie name: ");
            sc.nextLine();
            System.out.print("\t\t\t\t");
            String name=sc.nextLine();
            System.out.println("\t\t\t\tFormat: ");
            System.out.print("\t\t\t\t");
            String format=sc.nextLine();
            System.out.println("\t\t\t\tDate(YYYY/MM/DD): ");
            System.out.print("\t\t\t\t");
            String date=sc.nextLine();
            System.out.println("\t\t\t\tTime(HH:MM:SS): ");
            System.out.print("\t\t\t\t");
            String time=sc.nextLine();
            System.out.println("\t\t\t\tPrice: ");
            System.out.print("\t\t\t\t");
            Double price=sc.nextDouble();
            System.out.println("\t\t\t\tSeats: ");
            System.out.print("\t\t\t\t");
            int seat=sc.nextInt();
            DateFormat df=new SimpleDateFormat("HH:MM:SS");
            java.util.Date t=df.parse(time);
            java.util.Date d=new java.util.Date(date);
            java.sql.Date d1=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
            java.sql.Time t1=new java.sql.Time(t.getHours(),t.getMinutes(),t.getSeconds());
            PreparedStatement pst=con.prepareStatement("insert into movie (name,format,showdate,showtime,price,seat) values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,name);
            pst.setString(2,format);
            pst.setDate(3,d1);
            pst.setTime(4,t1);
            pst.setDouble(5,price);
            pst.setInt(6,seat);
            if(pst.executeUpdate()>0){
                System.out.println("\t\t\t\t---------------------------------");
                System.out.println("\t\t\t\tMovie Added Successfully........");
            }
            else{
                System.out.println("\t\t\t\t-----------------------------");
                System.out.println("\t\t\t\tMovie is NOT added........!!!");
            }
        }
        catch(Exception e){
            System.out.println("\t\t\t\tException occured.....\n\n");
        }
    }


    public static void DeleteMovie(){
        try{
            System.out.println("\n\n\n\n");
            System.out.println("\t\t\t\tEnter Movie ID(0 to exit): ");
            System.out.print("\t\t\t\t");
            int num=sc.nextInt();
            if(num==0){
                return;
            }
            PreparedStatement pst=con.prepareStatement("select * from movie where columnid=?");
            pst.setInt(1,num);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                System.out.println("  \t-------------------------------------------------------------------------------------------------");
                System.out.println("  \t|ID\t|\tNAME\t|\tFORMAT\t| SHOW DATE\t| SHOW TIME\t| PRICE\t| AVL.SEAT(s)\t|");
                System.out.println("  \t-------------------------------------------------------------------------------------------------");
                System.out.println("  \t| "+rs.getInt(1)+"\t| "+rs.getString(2)+"\t| "+rs.getString(3)+"\t| "+rs.getDate(4)+"\t| "+rs.getTime(5)+"\t| "+rs.getDouble(6)+"\t|\t"+rs.getInt(7)+"\t|");
                System.out.println("  \t-------------------------------------------------------------------------------------------------");
            }
            else{
                System.out.println("\t\t\t\tInvalid Movie ID.....!!!");
                System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                sc.next();
                return;
            }
            System.out.println("\n\n\t\t\t\tPress \'d\' to delete");
            System.out.print("\t\t\t\t");
            char c=sc.next().charAt(0);
            if(c=='d'||c=='D'){
                pst=con.prepareStatement("delete from movie where columnid=?");
                pst.setInt(1,num);
                if(pst.executeUpdate()>0){
                    System.out.println("\t\t\t\tMovie deleted.....");
                }
                else{
                    System.out.println("\t\t\t\tMovie is NOT deleted.....!!!");
                    System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                    sc.next();
                }
            }
            else{
                System.out.println("\t\t\t\tMovie is NOT deleted.....!!!");
                System.out.println("\t\t\t\tPlease enter any key to continue.... ");
                sc.next(); 
                return;
            }
        }
        catch(Exception e){
            System.out.println("\t\t\t\tException occured.....\n\n");
        }
    }


    public static int ShowMovieList() throws SQLException{
        int num=0;
        try{
            PreparedStatement pst=con.prepareStatement("select * from movie");
            ResultSet rs=pst.executeQuery();
            System.out.println("\n\n\n");
            System.out.println("  \t\t\t\t\t\tDETAILS OF ALL MOVIE");
            System.out.println("  \t---------------------------------------------------------------------------------------------------------");
            System.out.println("  \t| SNO.\t|ID\t|\tNAME\t|\tFORMAT\t| SHOW DATE\t| SHOW TIME\t| PRICE\t| AVL.SEAT(s)\t|");
            System.out.println("  \t---------------------------------------------------------------------------------------------------------");
            int i=1;
            while(rs.next()){
                System.out.println("  \t| "+i+"\t| "+rs.getInt(1)+"\t| "+rs.getString(2)+"\t| "+rs.getString(3)+"\t| "+rs.getDate(4)+"\t| "+rs.getTime(5)+"\t| "+rs.getDouble(6)+"\t|\t"+rs.getInt(7)+"\t|");
                System.out.println("  \t---------------------------------------------------------------------------------------------------------");
                num++;
                i++;
            }
        }
        catch(Exception e){
            System.out.println("\t\t\t\tException occured "+e.getMessage());
        }
        return num;
    }


    public static int SeeTotalBookings() throws SQLException{
        int num=0;
        try{
            num=ShowMovieList();
            PreparedStatement pst=con.prepareStatement("select * from customer");
            ResultSet rs=pst.executeQuery();
            num=0;
            System.out.println("\n");
            System.out.println("  \t\t\t\t\t\tDETAILS OF ALL BOOKINGS");
            System.out.println("  \t\t-----------------------------------------------------------------------------------------");
            System.out.println("  \t\t| SNO.\t| ID\t|\tNAME\t|\t Phone No.\t| Movie ID\t| SEAT(s)\t|");
            System.out.println("  \t\t-----------------------------------------------------------------------------------------");
            int i=1;
            while(rs.next()){
                System.out.println(" \t\t| "+i+"\t|"+rs.getInt(10)+"\t| "+rs.getString(1)+"\t|\t"+rs.getString(2)+"\t|\t"+rs.getInt(3)+"\t|\t"+rs.getInt(9)+"\t|");
                System.out.println("  \t\t-----------------------------------------------------------------------------------------");
                num++;
            }
        }
        catch(Exception e){
            System.out.println("\t\t\t\tException occured.....\n\n");
        }
        return num;
    }


    public static void clearscreen() throws InterruptedException,IOException,SQLException{
        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
    }


    public static void main(String[] arg) throws SQLException{
        try
        {
            int ch;
            do
            {
                clearscreen();
                System.out.print("\n\n\n\n\n");
                System.out.println("\t\t\t\t------------------------------------------");
                System.out.println("\t\t\t\t--WELCOME TO MOVIE TICKET BOOKING SYSTEM--");
                System.out.println("\t\t\t\t------------------------------------------");
                System.out.println("\t\t\t\t1. Customer Panal");
                System.out.println("\t\t\t\t2. Owner Panal");
                System.out.println("\t\t\t\t0. Exit");
                System.out.println("\t\t\t\t------------------------------------------");
                System.out.println("\t\t\t\tEnter your Choice...:");
                System.out.print("\t\t\t\t");
                ch=sc.nextInt();
                switch(ch)
                {
                    case 1:
                        int ch1;
                        do
                        {
                            clearscreen();
                            System.out.print("\n\n\n\n");
                            System.out.println("\t\t\t\t------------------------------------------");
                            System.out.println("\t\t\t\t--WELCOME TO MOVIE TICKET BOOKING SYSTEM--");
                            System.out.println("\t\t\t\t------------------------------------------");
                            System.out.println("\t\t\t\tMENU....");
                            System.out.println("\t\t\t\t1. Book Ticket");
                            System.out.println("\t\t\t\t2. Show my Ticket");
                            System.out.println("\t\t\t\t3. Cancle Ticket");
                            System.out.println("\t\t\t\t4. Check Seat");
                            System.out.println("\t\t\t\t5. Show Movie List");
                            System.out.println("\t\t\t\t6. Back");
                            System.out.println("\t\t\t\t0. Exit");
                            System.out.println("\t\t\t\t------------------------------------------");
                            System.out.println("\t\t\t\tEnter your Choice...:");
                            System.out.print("\t\t\t\t");
                            ch1=sc.nextInt();
                            switch(ch1)
                            {
                                case 1:
                                    clearscreen();
                                    BookTicket();
                                    break;
                                case 2:
                                    clearscreen();
                                    char c='x';
                                    while(c=='x')
                                    {
                                        System.out.print("\n\n\n\n\t\t\tDo you have your unique ID(enter \'Y\' for yes or \'N' for No: ");
                                        c=sc.next().charAt(0);
                                        if(c=='Y'||c=='y')
                                        {
                                            System.out.print("\t\t\t\tEnter unique Id: ");
                                            int num=sc.nextInt();
                                            showMyTicket(num);
                                        }
                                        else if(c=='N'||c=='n')
                                        {
                                            System.out.println("\t\t\t\tEnter your name: ");
                                            sc.nextLine();
                                            System.out.print("\t\t\t\t");
                                            String str=sc.nextLine();
                                            System.out.println("\t\t\t\tEnter Mobile no.: ");
                                            System.out.print("\t\t\t\t");
                                            String ph=sc.nextLine();
                                            PreparedStatement pst=con.prepareStatement("select * from customer where name=? and phoneNo=?");
                                            pst.setString(1,str);
                                            pst.setString(2,ph);
                                            ResultSet rs=pst.executeQuery();
                                            if(rs.next())
                                            {
                                                int num=rs.getInt(10);
                                                showMyTicket(num);
                                            }
                                            else
                                            {
                                                System.out.println("\t\t\t\t-------------------------------");
                                                System.out.println("\t\t\t\tNo Booking available.....!!!!!!\n\n");
                                            }
                                        }
                                        else
                                        {
                                            c='x';
                                            clearscreen();
                                        }
                                    }
                                    break;
                                case 3:
                                    clearscreen();
                                    CancleTicket();
                                    break;
                                case 4:
                                    clearscreen();
                                    CheckSeat();
                                    break;
                                case 5:
                                    clearscreen();
                                    int num=ShowMovieList();
                                    if(num==0)
                                    {
                                        System.out.print("\n\n\n\n");
                                        System.out.println("\t\t\t\t--------------------");
                                        System.out.println("\t\t\t\tList is EMPTY....!!!");
                                        System.out.println("\t\t\t\t--------------------");
                                    }
                                    break;
                                case 6:
                                    break;
                                case 0:
                                   break;
                                default:
                                    clearscreen();
                                    System.out.println("\n\n\n\n\t\t\t\t------------------------");
                                    System.out.println("\t\t\t\tEnter a VALID CHOICE....");
                                    System.out.println("\t\t\t\t------------------------");
                                    System.out.print("\t\t\t\tEnter any key....");
                                    sc.next();
                                    ch1=9;
                            }
                            if(ch1!=0 && ch1!=9 && ch1!=6){
                                System.out.println("\t\t\t\t--------------------");
                                System.out.println("\t\t\t\tPress any key to back to main menu");
                                System.out.println("\t\t\t\t0. Exit");
                                System.out.println("\t\t\t\t--------------------");
                                System.out.println("\t\t\t\tEnter your Choice...:");
                                System.out.print("\t\t\t\t");
                                char c1=sc.next().charAt(0);
                                if(c1!='0')
                                    ch1=9;
                                else
                                    ch1=0;
                            }
                            if(ch1==0){
                                clearscreen();
                                System.out.print("\n\n\n\n\n\n");
                                System.out.println("\t\t\t\t\t--------------------------------------------");
                                System.out.println("\t\t\t\t\t\tTHANK YOU FOR USING MTBS....!!!!");
                                System.out.println("\t\t\t\t\t\tDEVELOPED BY:--");
                                System.out.println("\t\t\t\t\t\tSHUBHAM GUPTA");
                                System.out.println("\t\t\t\t\t--------------------------------------------\n\n\n"); 
                                return;
                            }
                            if(ch1==6){
                                ch=9;
                                break;
                            }
                            
                        }while(ch1==9);
                        break;
                    case 2:
                        clearscreen();
                        if(login()){
                            int ch2;
                            do{
                                clearscreen();
                                System.out.print("\n\n\n\n");
                                System.out.println("\t\t\t\t------------------------------------------");
                                System.out.println("\t\t\t\t\t-------OWNER PANAL------");
                                System.out.println("\t\t\t\t------------------------------------------");
                                System.out.println("\t\t\t\t1. Add New Movie");
                                System.out.println("\t\t\t\t2. Delete Movie");
                                System.out.println("\t\t\t\t3. Show Movie List");
                                System.out.println("\t\t\t\t4. See Total Bookings");
                                System.out.println("\t\t\t\t5. Back");
                                System.out.println("\t\t\t\t0. Exit");
                                System.out.println("\t\t\t\t------------------------------------------");
                                System.out.println("\t\t\t\tEnter your Choice...:");
                                System.out.print("\t\t\t\t");
                                ch2=sc.nextInt();
                                switch(ch2){
                                    case 1:
                                        clearscreen();
                                        AddNewMovie();
                                        break;
                                    case 2:
                                        clearscreen();
                                        DeleteMovie();
                                        break;
                                    case 3:
                                        clearscreen();
                                        int num=ShowMovieList();
                                        if(num==0)
                                        {
                                            System.out.print("\n\n\n\n");
                                            System.out.println("\t\t\t\t--------------------");
                                            System.out.println("\t\t\t\tList is EMPTY....!!!");
                                            System.out.println("\t\t\t\t--------------------");
                                        }
                                        break;
                                    case 4:
                                        clearscreen();
                                        num=SeeTotalBookings();
                                        if(num==0){
                                            System.out.print("\n\n\n\n");
                                            System.out.println("\t\t\t\t---------------------------");
                                            System.out.println("\t\t\t\tNo booking Available....!!!");
                                            System.out.println("\t\t\t\t---------------------------");
                                        }
                                        break;
                                    case 5:
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        clearscreen();
                                        System.out.println("\n\n\n\n\t\t\t\t------------------------");
                                        System.out.println("\t\t\t\tEnter a VALID CHOICE....");
                                        System.out.println("\t\t\t\t------------------------");
                                        System.out.print("\t\t\t\tEnter any key....");
                                        sc.next();
                                        ch2=9;
                                }
                                if(ch2!=0 && ch2!=9 && ch2!=5){
                                    System.out.println("\t\t\t\t--------------------");
                                    System.out.println("\t\t\t\tPress any key Back to main menu");
                                    System.out.println("\t\t\t\t0. Exit");
                                    System.out.println("\t\t\t\t--------------------");
                                    System.out.println("\t\t\t\tEnter your Choice...:");
                                    System.out.print("\t\t\t\t");
                                    char c2=sc.next().charAt(0);
                                    if(c2!='0')
                                        ch2=9;
                                    else
                                        ch2=0;
                                }
                                if(ch2==0){
                                    clearscreen();
                                    System.out.print("\n\n\n\n\n\n");
                                    System.out.println("\t\t\t\t\t--------------------------------------------");
                                    System.out.println("\t\t\t\t\t\tTHANK YOU FOR USING MTBS....!!!!");
                                    System.out.println("\t\t\t\t\t\tDEVELOPED BY:--");
                                    System.out.println("\t\t\t\t\t\tSHUBHAM GUPTA");
                                    System.out.println("\t\t\t\t\t--------------------------------------------\n\n\n"); 
                                    return;
                                }
                                if(ch2==5){
                                    ch=9;
                                    break;
                                }
                            }while(ch2==9);
                        }
                        else{
                            clearscreen();
                            System.out.println("\n\n\n\n\n\t\t\t\t-----------------------------------------------------");
                            System.out.println("\t\t\t\t YOU ARE NOT AUTHORISED TO ACCESS THIS PAGE....");
                            System.out.println("\t\t\t\t-----------------------------------------------------");
                            System.out.println("\n\n\t\t\t\tPress any key to go back.....");
                            System.out.print("\t\t\t\t");
                            sc.next();
                            ch=9;
                        }
                        break;
                    case 0:
                        clearscreen();
                        System.out.print("\n\n\n\n\n\n");
                        System.out.println("\t\t\t\t\t--------------------------------------------");
                        System.out.println("\t\t\t\t\t\tTHANK YOU FOR USING MTBS....!!!!");
                        System.out.println("\t\t\t\t\t\tDEVELOPED BY:--");
                        System.out.println("\t\t\t\t\t\tSHUBHAM GUPTA");
                        System.out.println("\t\t\t\t\t--------------------------------------------\n\n\n"); 
                        return;
                    default:
                        clearscreen();
                        System.out.println("\n\n\n\n\t\t\t\t------------------------");
                        System.out.println("\t\t\t\tEnter a VALID CHOICE....");
                        System.out.println("\t\t\t\t------------------------");
                        System.out.print("\t\t\t\tEnter any key....");
                        sc.next();
                        ch=9;
                }
            }while(ch==9);
        }
        catch(Exception e){
            if(!e.getMessage().equals(null)){
                System.out.print("Exception Occured");
            }
        }
    }
}