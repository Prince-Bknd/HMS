package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient {
    // ==================VARIABLES===================
    private String DBClassName = "com.mysql.cj.jdbc.Driver";
    private String DBurl = "jdbc:mysql://localhost:3306/hmsdb";
    private String DBuser = "root";
    private String DBpassword = "4321";

    private Integer patientId;
    private String gender;
    private String bloodGroup;
    private Float weight;
    private Integer height;
    private String uid;
    
    private User user;

    // ================================================CONSTRUCTORS===================================================
    public Patient() {}

    public Patient(Integer patientId){
        this.patientId = patientId;
    }

    public Patient(String gender, String bloodGroup, float weight, int height, String uid){
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.height = height;
        this.weight = weight;
        this.uid = uid;
    }

    public Patient(String gender, String bloodGroup, Float weight, Integer height, String uid, User user){
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.weight = weight;
        this.height = height;
        this.uid = uid;
        this.user = user;
    }

    public Patient(Integer patientId, User user, String gender, String bloodGroup, Float weight, Integer height, String uid){
        this(gender, bloodGroup, weight, height, uid, user);
        this.patientId = patientId;
    }
    
    // ================================================METHODS===================================================
    public Boolean insertData(User user){
        Boolean flag = false;
        String query = "insert into patients (user_id, gender, blood_group, weight, height, uid) value (?, ?, ?, ?, ?, ?)";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, user.getUserId());
            ps.setString(2, gender);
            ps.setString(3, bloodGroup);
            ps.setFloat(4, weight);
            ps.setInt(5, height);
            ps.setString(6, uid);
            int rs = ps.executeUpdate();

            if(rs == 1){
                flag = true;
            } 
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }

    public void setValues(User user){
        String query = "select * from patients where user_id=?";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                patientId = rs.getInt("patient_id");
                gender = rs.getString("gender");
                bloodGroup = rs.getString("blood_group");
                weight = rs.getFloat("weight");
                height = rs.getInt("height");
                uid = rs.getString("uid");
                this.user = user;
            }

            
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public Boolean checkPatient(int id){
        Boolean flag = false;
        String query = "select * from patients where user_id=?";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                flag = true;
            }

            
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }

    
    
    // ================================================SETTERS===================================================
    public void setPatientId(Integer patientId){
        this.patientId = patientId;
    }
    public void setUser(User user){
        this.user = user;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setBloodGroup(String bloodGroup){
        this.bloodGroup = bloodGroup;
    }
    public void setWeight(Float weight){
        this.weight = weight;
    }
    public void setHeight(Integer height){
        this.height = height;
    }
    public void setUid(String uid){
        this.uid = uid;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getPatientId(){
        return patientId;
    }
    public User getUser(){
        return user;
    }
    public String getGender(){
        return gender;
    }
    public String getBloodGroup(){
        return bloodGroup;
    }
    public Float getWeight(){
        return weight;
    }
    public Integer getHeight(){
        return height;
    }
    public String getUid(){
        return uid;
    }
}

/* 
+-------------+----------+------+-----+---------+----------------+
| Field       | Type     | Null | Key | Default | Extra          |
+-------------+----------+------+-----+---------+----------------+
| patient_id  | int      | NO   | PRI | NULL    | auto_increment |
| user_id     | int      | NO   | MUL | NULL    |                |
| gender      | char(1)  | NO   |     | NULL    |                |
| blood_group | char(5)  | YES  |     | NULL    |                |
| weight      | float    | YES  |     | NULL    |                |
| height      | int      | YES  |     | NULL    |                |
| uid         | char(12) | NO   |     | NULL    |                |
+-------------+----------+------+-----+---------+----------------+
*/