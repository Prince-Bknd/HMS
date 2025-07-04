package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Specialization {
    // ==================VARIABLES===================
    private static String DBClassName = "com.mysql.cj.jdbc.Driver";
    private static String DBurl = "jdbc:mysql://localhost:3306/hmsdb";
    private static String DBuser = "root";
    private static String DBpassword = "4321";

    public Integer specializationId;
    public String name;


    // ================================================CONSTRUCTORS===================================================
    public Specialization(){}

    public Specialization(Integer specializationId, String name){
        this.specializationId = specializationId;
        this.name = name;
    }

    public Specialization(Integer specializationId){
        this.specializationId = specializationId;
    }
    
    // ================================================METHODS===================================================
    public static ArrayList<Specialization> getSpecializations(){
        ArrayList<Specialization> specializations = new ArrayList<>();

        String query = "Select * from specializations";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                specializations.add(new Specialization(rs.getInt("specialization_id"), rs.getString("name")));
            }
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }


        return specializations;
    }
    
    // ================================================SETTERS===================================================
    public void setSpecializationId(Integer specializationId){
        this.specializationId = specializationId;
    }
    public void setName(String name){
        this.name = name;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getSpecializationId(){
        return specializationId;
    }
    public String getName(){
        return name;
    }
}

/*
+-------------------+----------+------+-----+---------+----------------+
| Field             | Type     | Null | Key | Default | Extra          |
+-------------------+----------+------+-----+---------+----------------+
| specialization_id | int      | NO   | PRI | NULL    | auto_increment |
| name              | char(45) | NO   |     | NULL    |                |
+-------------------+----------+------+-----+---------+----------------+
 */