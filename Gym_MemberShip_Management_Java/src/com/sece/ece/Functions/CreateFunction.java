package com.sece.ece.Functions;

import com.sece.ece.Entities.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CreateFunction {

    public static void createAccount(Connection con, List<Member> memberList) throws SQLException {
        String query = "INSERT INTO member values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, memberList.get(0).getMemberId());
        preparedStatement.setString(2,memberList.get(0).getFirstName());;
        preparedStatement.setString(3,memberList.get(0).getLastName());
        preparedStatement.setString(4,memberList.get(0).getEmail());
        preparedStatement.setString(5,memberList.get(0).getPhoneNumber());
        preparedStatement.setString(6,memberList.get(0).getAddress());
        preparedStatement.setString(7,memberList.get(0).getMemberShipType());
        preparedStatement.setString(8,memberList.get(0).getEntryDate());
        preparedStatement.setString(9,memberList.get(0).getMemberShipExpiry());
        int i = preparedStatement.executeUpdate();
        if(i > 0){
            System.out.println("Account created successfully");
            System.out.println("***************************************************");
        }
        else{
            System.out.println("Account creation failed");
        }
    }
}
