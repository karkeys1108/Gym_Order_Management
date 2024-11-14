import com.sece.ece.Entities.Member;
import com.sece.ece.Entities.MemberShipType;
import com.sece.ece.Entities.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.sece.ece.Functions.CreateFunction.createAccount;

public class Main{
    private static String DATABASEURL = "jdbc:mysql://localhost:3306/Gym_Membership_Database";
    private static String USERNAME = "root";
    private static String PASSWORD = "karkey";
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        List<Member> members = new ArrayList<Member>();
        List<Payment> payments = new ArrayList<Payment>();
        List<MemberShipType> memberShipTypes = new ArrayList<MemberShipType>();
        conn = DriverManager.getConnection(DATABASEURL, USERNAME, PASSWORD);
        System.out.println("WELCOME TO GYM MEMBERSHIP MANAGEMENT SYSTEM");
        System.out.println("*********************By Karthikeyan  ***********");

        while(true)
        {
            System.out.println("1.Enroll as new member with membership plans");
            System.out.println("2.Open as an existing member");
            System.out.println("3.EXIT");

            System.out.println();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    System.out.println("************************************");
                    System.out.println("For enrolling as new customer the required details are :");
                    System.out.print("Enter your first name :");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter your last name :");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter your email address :");
                    String email = scanner.nextLine();
                    System.out.print("Enter your phone number ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter your address:");
                    String address = scanner.nextLine();
                    displayPlan(conn);
                    System.out.print("Enter your planID :");
                    int planID = scanner.nextInt();
                    double planPrice = planPrice(conn , planID);
                    String planType = planType(conn , planID);
                    LocalDate  entryDate = LocalDate.now();
                    LocalDate expiryDate = expiryDate(conn,planID);
                    int duration = getDuration(conn , planID);
                    int memberId = getMemberId(conn);
                    Member member = new Member(memberId,firstName , lastName,email, phone, address,planType, entryDate.toString(), expiryDate.toString());
                    members.add(member);
                    createAccount(conn, members);


                    System.out.println("As a next process make your plan ");

                    System.out.println("Your plan price is "+planPrice);
                    System.out.println("Select the payment method \n");
                    System.out.println("1.Google Pay)");
                    System.out.println("2.Paytm");
                    System.out.println("3.Direct Cash");
                    System.out.println("4.Others ");
                    System.out.print("Enter your choice: ");
                    int ch;
                    String methodType = "";
                    ch = scanner.nextInt();
                    scanner.nextLine();
                    switch(ch){
                        case 1:
                            methodType = "Google Pay";
                            break;
                        case 2:
                            methodType = "Paytm";
                            break;
                        case 3:
                            methodType = "Direct Cash";
                            break;
                        case 4:
                            System.out.print("Explain how you gave the amount : ");
                           methodType = scanner.nextLine();
                           break;
                        default:
                            System.out.println("Invalid choice");

                    }
                    System.out.println("To confirm payment ,enter confirm/decline");
                    String confirm = scanner.nextLine();
                    if(confirm.equals("confirm")){
                        confirm = "confirm";
                    }else if(confirm.equals("decline")) {
                        confirm = "decline";
                    }
                    else{
                        System.out.println("Invalid confirmation");
                        return;
                    }
                    LocalDate date = LocalDate.now();

                    Payment payment = new Payment(getPaymentIdIn(conn) ,  memberId,date.toString(),methodType, planPrice, confirm );
                    payments.add(payment);
                    confirmPayment(conn, payments);

                    MemberShipType memberShipType = new MemberShipType(memberId ,planID ,planType, planPrice ,duration);
                    memberShipTypes.add(memberShipType);
                    updateMembership(conn, memberShipTypes);

                    System.out.println("Your profile details are : "+member.toString());



                case 2:
                    int id = 0 ;
                    System.out.println("Enter your member Id : ") ;
                    id = scanner.nextInt();
                   int attendanceId = getAttendaceId(conn);
                    openExistingAccount(conn, id ,attendanceId );
                case 3:
                    System.out.println("Thank you visiting us.....");
                    return ;
                default:
                    System.out.println("Invalid choice");
                    return;

            }

        }
    }




    private static void openExistingAccount(Connection conn, int id , int attendanceId ) throws SQLException {
        String query = "SELECT *  FROM member where member_id = ? ";
        String name = "";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            name = resultSet.getString("first_name");
        }
        while(true) {
            System.out.println("Welcome back to GymMembership Management System ***" + name + "*** ");
            displayMenuExisting(conn, id, name, attendanceId);
        }


    }

    private static void displayMenuExisting(Connection conn, int id , String name , int attendanceId) throws SQLException {
        System.out.println("1.Mark Attendance");
        System.out.println("2.View Existing membership plan");
        System.out.println("3.Change membership plan");
        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice){
            case 1:
                attendanceCheck(conn , id , attendanceId);
                break;
            case 2:
                viewExistingMembership(conn , id);
                break;
            case 3:
                changePlan(conn , id);
                break;

        }
    }

    private static void changePlan(Connection conn, int id) throws SQLException {

        displayPlan(conn);
        System.out.print("Enter your planID :");
        int planID = scanner.nextInt();
        double planPrice = planPrice(conn , planID);
        int duration = getDuration(conn , planID);
        String planType = planType(conn , planID);
        System.out.println("Enter your price :"+planPrice);
        System.out.println("Enter your planType :" +planType);



        String query = "UPDATE membershiptype SET membership_name = ? , duration_months = ? , plan_id = ? where membehsip_type_id = ? ";
        PreparedStatement preparedStatement = conn.prepareStatement(query);

        preparedStatement.setString(1,planType);
        preparedStatement.setInt(2,duration);
        preparedStatement.setInt(3,planID);
        preparedStatement.setInt(4,id);

        int rs = preparedStatement.executeUpdate();

        if(rs > 0)
        {
            System.out.println("Your plan is now changed to "+planType);
        }
        else{
            System.out.println("Your plan is not now changed ");
        }



    }

    private static void viewExistingMembership(Connection conn, int id) throws SQLException {
        String query = "SELECT *  FROM membershiptype where membehsip_type_id = ? ";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
         System.out.println("Member ID : " + resultSet.getString("membehsip_type_id"));
         System.out.println("Member Name : " + resultSet.getString("membership_name"));
         System.out.println("MemberShip plan price : " + resultSet.getString("pricing"));
         System.out.println("MemberShip plan duration : " + resultSet.getString("duration_months"));
        }
    }

    private static void attendanceCheck(Connection conn, int id, int attendanceId) throws SQLException {
        PreparedStatement preparedStatement;

        if (attendanceIdExist(conn, attendanceId)) {

            String query = "UPDATE attendance SET check_out_time = ? WHERE attendance_id = ?";
            preparedStatement = conn.prepareStatement(query);
            LocalTime time = LocalTime.now();
            preparedStatement.setTime(1, Time.valueOf(time));
            preparedStatement.setInt(2, attendanceId);

            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                System.out.println("You have CHECKED OUT successfully");
            }
        } else {

            String query = "INSERT INTO attendance (attendance_id, member_id, attendance_date, check_in_time) VALUES (?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, attendanceId);
            preparedStatement.setInt(2, id);

            Date date = Date.valueOf(LocalDate.now());
            preparedStatement.setDate(3, date);

            LocalTime time = LocalTime.now();
            preparedStatement.setTime(4, Time.valueOf(time));

            int rs = preparedStatement.executeUpdate();
            if (rs > 0) {
                System.out.println("You have Checked IN successfully");
            }
        }
    }


    private static boolean attendanceIdExist(Connection conn, int attendanceId) throws SQLException {
        String query = "SELECT attendance_id from attendance order by attendance_id asc ";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            if(resultSet.getInt("attendance_id") == attendanceId){
                return true;
            }
        }
        return false;
    }


    private static int getAttendaceId(Connection conn) throws SQLException {
        int attendanceId = 1;
        String query = "SELECT attendance_id FROM attendance ORDER BY attendance_id ASC";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int newAttendanceId = rs.getInt(1);
            if(newAttendanceId == attendanceId){
                attendanceId++;
            }
        }
        return attendanceId;

    }

    private static LocalDate expiryDate(Connection conn , int planId) throws SQLException {
        int planDate = 0;
        String query = "SELECT duration_months from membershipplans where plan_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1,planId);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            planDate = rs.getInt("duration_months");
        }
        return LocalDate.now().plusDays(planDate);
    }

    private static String planType(Connection conn, int planID) throws SQLException {
        String plan = " ";
        String query = "SELECT plan_name from membershipplans where plan_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, planID);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            plan = rs.getString("plan_name");
        }
        return plan;
    }
    private static int getDuration(Connection conn, int planID) throws SQLException {
        int planDuartion = 1;
        String query = "SELECT duration_months from membershipplans where plan_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, planID);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            planDuartion = rs.getInt("duration_months");
        }

        return planDuartion;
    }

    private static double planPrice(Connection conn, int planID) throws SQLException {
        double planPrice = 0.0;
        String query = "SELECT monthly_fee from membershipplans where plan_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, planID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            planPrice = rs.getDouble("monthly_fee");
        }

        return planPrice;
    }

    private static void displayPlan(Connection conn) {
        try
        {
            String query = "SELECT * FROM membershipplans";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                System.out.println("The plan id : "+rs.getInt(1));
                System.out.println("The plan name : "+rs.getString(2));
                System.out.println("The plan fee : "+rs.getString(3));
                System.out.println("The plan duration : "+rs.getString(4));
                System.out.println("The plan features  : "+rs.getString(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void confirmPayment(Connection conn, List<Payment> payments) throws SQLException {

        String query = "INSERT INTO payments (payment_id , member_id , payment_date , payment_amount , payment_method , payment_status) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement =  conn.prepareStatement(query);
        preparedStatement.setInt(1,payments.get(0).getPaymentID());
        preparedStatement.setInt(2, payments.get(0).getMemberID());
        preparedStatement.setString(3,payments.get(0).getPaymentDate());
        preparedStatement.setDouble(4,payments.get(0).getPaymentAmount());
        preparedStatement.setString(5,payments.get(0).getPaymentMethod());
        preparedStatement.setString(6,payments.get(0).getPaymentStatus());
        int rs = preparedStatement.executeUpdate();
        if(rs > 0)
        {
            System.out.println("Your payment has been successfully confirmed");
        }else {
            System.out.println("Your payment has not accepted");
        }
    }

    private static int getMemberId(Connection conn)throws SQLException {
        int memberId = 1;
        String query = "SELECT member_id FROM MEMBER ORDER BY member_id ASC";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int newMemberId = rs.getInt(1);
            if(newMemberId == memberId){
                memberId++;
            }
        }
        return memberId;
    }
    private static int getPaymentIdIn(Connection conn)throws SQLException {
        int paymentId = 1;
        String query = "SELECT payment_id  FROM payments ORDER BY payment_id ASC";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int newMemberId = rs.getInt(1);
            if(newMemberId == paymentId){
                paymentId++;
            }
        }
        return paymentId;
    }
    private static void updateMembership(Connection conn, List<MemberShipType> memberShipTypes) throws SQLException {
        String query = "INSERT INTO membershiptype values(?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, memberShipTypes.get(0).getMembershipTypeID());
        preparedStatement.setString(2,memberShipTypes.get(0).getMembershipTypeName());
        preparedStatement.setDouble(3,memberShipTypes.get(0).getPrice());
        preparedStatement.setInt(4, memberShipTypes.get(0).getDuration());
        preparedStatement.setInt(5, memberShipTypes.get(0).getPlanId());
        int rs =  preparedStatement.executeUpdate();
        if(rs  > 0){
            System.out.println("Membership Updated Successfully");
        }
    }

}