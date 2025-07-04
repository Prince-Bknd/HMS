package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class User {
    // ==================VARIABLES===================
    private String DBClassName = "com.mysql.cj.jdbc.Driver";
    private String DBurl = "jdbc:mysql://localhost:3306/hmsdb";
    private String DBuser = "root";
    private String DBpassword = "4321";
    
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private Date dob;
    private String contact;
    private String address;
    private String profilePic;
    private String activationCode;

    private Status status;
    private UserType userType;

    static private StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
    
    // ================================================CONSTRUCTORS===================================================
    public User(Integer userId, String name){
        this.userId = userId;
        this.name =  name;
    }

    public User(Integer userId, String name, Date dob, String profilePic, String email, String contact){
        this.userId = userId;
        this.name =  name;
        this.dob =  dob;
        this.profilePic = profilePic;
        this.email = email;
        this.contact = contact;
    }

    public User(Integer userId, String name, Date dob, String contact, String address){
        this(userId, name);
        this.dob = dob;
        this.contact = contact;
        this.address = address;
    }

    public User(String name, String email, String password, Date dob, String contact, String address, String profilePic, String activationCode, Status status, UserType userType){
        this.name = name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.contact = contact;
        this.address = address;
        this.profilePic = profilePic;
        this.activationCode = activationCode;
        this.status = status;
        this.userType = userType;
    }

    public User(String name, String email, String password, String contact, UserType userType){
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.userType = userType;
    }

    public User(String email){
        this.email = email;
    }
    
    // ================================================METHODS===================================================
    public int verifyUser(){
        int a = 0;
        String query = "select * from users where email=?";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                userId = rs.getInt("user_id");
                name = rs.getString("name");
                email = rs.getString("email"); 
                password = rs.getString("password");
                dob = rs.getDate("dob");   
                contact = rs.getString("contact");
                address = rs.getString("address");
                profilePic = rs.getString("profile_pic");                
                activationCode = rs.getString("activation_code");
                status = new Status(rs.getInt("status_id"));
                userType = new UserType(rs.getInt("user_type_id"));

                a = 1;
            }
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return a;
    }

    public Boolean signupSuccess(){
        Boolean flag = false;
        String query = "insert into users (name, email, password, contact, user_type_id) value (?, ?, ?, ?, ?)";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, spe.encryptPassword(password));
            ps.setString(4, contact);
            ps.setInt(5, userType.getUserTypeId());
            
            if(ps.executeUpdate() == 1){
                flag = true;
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }
    
    // ================================================SETTERS===================================================
    public void setUserId(Integer userId){
        this.userId = userId;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setDob(Date dob){
        this.dob = dob;
    }
    public void setContact(String contact){
        this.contact = contact;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setProfilePic(String profilePic){
        this.profilePic = profilePic;
    }
    public void setActivationCode(String activationCode){
        this.activationCode = activationCode;
    }
    public void setStatus(Status status){
        this.status = status;
    }
    public void setUserType(UserType userType){
        this.userType = userType;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getUserId(){
        return userId;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public Date getDob(){
        return dob;
    }
    public String getContact(){
        return contact;
    }
    public String getAddress(){
        return address;
    }
    public String getProfilePic(){
        return profilePic;
    }
    public String getActivationCode(){
        return activationCode;
    }
    public Status getStatus(){
        return status;
    }
    public UserType getUserType(){
        return userType;
    }
}

/*
+-----------------+--------------+------+-----+------------------------------+-------+
| Field           | Type         | Null | Key | Default                      | Extra |
+-----------------+--------------+------+-----+------------------------------+-------+
| user_id         | int          | NO   | PRI | NULL                         |       |
| name            | char(45)     | NO   |     | NULL                         |       |
| email           | varchar(255) | NO   | UNI | NULL                         |       |
| password        | varchar(255) | NO   |     | NULL                         |       |
| dob             | date         | YES  |     | NULL                         |       |
| contact         | char(10)     | NO   | UNI | NULL                         |       |
| address         | varchar(500) | YES  |     | NULL                         |       |
| profile_pic     | varchar(255) | YES  |     | static/media/images/user.png |       |
| status_id       | int          | NO   | MUL | NULL                         |       |
| user_type_id    | int          | NO   | MUL | NULL                         |       |
| activation_code | varchar(255) | YES  |     | NULL                         |       |
+-----------------+--------------+------+-----+------------------------------+-------+
 */