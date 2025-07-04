package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class City {
    // ==================VARIABLES===================
    private static String className = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/hmsdb";
    private static String DBUser = "root";
    private static String DBPassword = "4321";

    private Integer cityId;
    private String name;
    
    private State state;


    // ================================================CONSTRUCTORS===================================================
    public City(){ }

    public City(Integer cityId){
        this.cityId = cityId;
    }

    public City(Integer cityId, String name, State state){
        this.cityId = cityId;
        this.name = name;
        this.state = state;
    }

    public City(String name, State state){
        this.name = name;
        this.state = state;
    }
    
    // ================================================METHODS===================================================
    public static ArrayList<City> getCities(){
        ArrayList<City> cities = new ArrayList<>();
        String query = "select * from cities as c inner join states as s where c.state_id=s.state_id order by c.name";

        try{
            Class.forName(className);

            Connection con = DriverManager.getConnection(url, DBUser, DBPassword);
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                cities.add(new City(rs.getInt(1), rs.getString(2), new State(rs.getInt(3), rs.getString(5))));
            }


        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }


        return cities;
    }
    
    
    // ================================================SETTERS===================================================
    public void setCityId(Integer cityId){
        this.cityId = cityId;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setState(State state){
        this.state = state;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getCityId(){
        return cityId;
    }
    public String getName(){
        return name;
    }
    public State getState(){
        return state;
    }

}

/*
+----------+----------+------+-----+---------+----------------+
| Field    | Type     | Null | Key | Default | Extra          |
+----------+----------+------+-----+---------+----------------+
| city_id  | int      | NO   | PRI | NULL    | auto_increment |
| name     | char(45) | NO   |     | NULL    |                |
| state_id | int      | NO   | MUL | NULL    |                |
+----------+----------+------+-----+---------+----------------+
 */