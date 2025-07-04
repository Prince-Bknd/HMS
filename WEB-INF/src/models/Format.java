package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Format {
    // ==================VARIABLES===================
    private static String className = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/hmsdb";
    private static String DBUser = "root";
    private static String DBPassword = "4321";

    private Integer formatId;
    private String name;


    // ================================================CONSTRUCTORS===================================================
    public Format(){}

    public Format(String name){
        this.name = name;
    }

    public Format(Integer formatId){
        this.formatId = formatId;
    }

    public Format(Integer formatId, String name){
        this.formatId = formatId;
        this.name = name;
    }
    
    // ================================================METHODS===================================================
    public static ArrayList<Format> getFormats(){
        ArrayList<Format> formats = new ArrayList<>();
        String query = "Select * from formats";

        try{
            Class.forName(className);

            Connection con = DriverManager.getConnection(url, DBUser, DBPassword);
            
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                formats.add(new Format(rs.getInt("format_id"), rs.getString("name")));
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return formats;
    }
    
    
    // ================================================SETTERS===================================================
    public void setFormatId(Integer formatId){
        this.formatId = formatId;
    }
    public void setName(String name){
        this.name = name;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getFormatId(){
        return formatId;
    }
    public String getName(){
        return name;
    }
}

/*
+-----------+----------+------+-----+---------+----------------+
| Field     | Type     | Null | Key | Default | Extra          |
+-----------+----------+------+-----+---------+----------------+
| format_id | int      | NO   | PRI | NULL    | auto_increment |
| name      | char(25) | NO   |     | NULL    |                |
+-----------+----------+------+-----+---------+----------------+
 */
