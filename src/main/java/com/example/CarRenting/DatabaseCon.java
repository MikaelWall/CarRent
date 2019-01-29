package com.example.CarRenting;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DatabaseCon {

    private Connection connection = connect ("jdbc:mysql://localhost:3306/carrenting?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");

    private Connection connect(String url) {
        System.out.println("Creating connection..");
        try {
            System.out.println("uppkopplad");
            return DriverManager.getConnection(url, "root", "hejhej");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createUser(String email, String firstname, String lastname, String password, String confirmPassword){
        if (!password.equals(confirmPassword)){
            return false;
        }
        try {
            String query = "Insert into users (Firstname,Lastname,Email, Password) values(?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,email);
            stmt.setString(2,firstname);
            stmt.setString(3,lastname);
            stmt.setString(4,password);

            if (stmt.executeUpdate()!=0){
                System.out.println("User inserted");
                return true;
            }else {
                return false;
            }

        }catch (SQLException e){
            System.out.println("Error: " + e);
        }
        return false;
    }

}
