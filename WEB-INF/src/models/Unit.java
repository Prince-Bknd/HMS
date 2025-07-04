package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Unit {
    // ==================VARIABLES===================
    private static String className = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/hmsdb";
    private static String DBUser = "root";
    private static String DBPassword = "4321";

    private Integer unitId;
    private String name;


    // ================================================CONSTRUCTORS===================================================
    public Unit(Integer unitId, String name){
        this.unitId = unitId;
        this.name = name;
    }

    public Unit(String name){
        this.name = name;
    }

    public Unit(Integer unitId){
        this.unitId = unitId;
    }
    
    // ================================================METHODS===================================================
    public static ArrayList<Unit> getUnits(){
        ArrayList<Unit> units = new ArrayList<>();
        String query = "select * from units";

        try{
            Class.forName(className);

            Connection con = DriverManager.getConnection(url, DBUser, DBPassword);
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                units.add(
                    new Unit(
                        rs.getInt("unit_id"), 
                        rs.getString("name")
                        )
                );
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return units;
    }
    
    
    // ================================================SETTERS===================================================
    public void setUnitId(Integer unitId){
        this.unitId = unitId;
    }
    public void setName(String name){
        this.name = name;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getUnitId(){
        return unitId;
    }
    public String getName(){
        return name;
    }
}

/*
+---------+----------+------+-----+---------+----------------+
| Field   | Type     | Null | Key | Default | Extra          |
+---------+----------+------+-----+---------+----------------+
| unit_id | int      | NO   | PRI | NULL    | auto_increment |
| name    | char(15) | NO   |     | NULL    |                |
+---------+----------+------+-----+---------+----------------+
 */