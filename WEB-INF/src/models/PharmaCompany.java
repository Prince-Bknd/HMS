package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PharmaCompany {
    // ==================VARIABLES===================
    private String DBClassName = "com.mysql.cj.jdbc.Driver";
    private String DBurl = "jdbc:mysql://localhost:3306/hmsdb";
    private String DBuser = "root";
    private String DBpassword = "4321";

    private Integer pharmaCompanyId;
    private String details;
    private String license;

    private User user;


    // ================================================CONSTRUCTORS===================================================
    public PharmaCompany() {}

    public PharmaCompany(Integer pharmaCompanyId){
        this.pharmaCompanyId = pharmaCompanyId;
    }
    
    public PharmaCompany(String details, String license, User user){
        this.details = details;
        this.license = license;
        this.user = user;
    }
    
    // ================================================METHODS===================================================
    public Boolean insertData(User user){
        Boolean flag = false;
        String query = "insert into pharma_companies (user_id, details, license) value (?, ?, ?)";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, user.getUserId());
            ps.setString(2, details);
            ps.setString(3, license);
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
        String query = "select * from pharma_companies where user_id=?";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                pharmaCompanyId = rs.getInt("pharma_company_id");
                details = rs.getString("details");
                license = rs.getString("license");
                this.user = user;
            }

            
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public Boolean checkPharmaCompany(int id){
        Boolean flag = false;
        String query = "select * from pharma_companies where user_id=?";

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
    public void setPharmaCompanyId(Integer pharmaCompanyId){
        this.pharmaCompanyId = pharmaCompanyId;
    }
    public void setDetails(String details){
        this.details = details;
    }
    public void setlicense(String license){
        this.license = license;
    }
    public void setUser(User user){
        this.user = user;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getPharmaCompanyId(){
        return pharmaCompanyId;
    }
    public String getDetails(){
        return details;
    }
    public String getlicense(){
        return license;
    }
    public User getUser(){
        return user;
    }
}

/*
+-------------------+--------------+------+-----+---------+-------+
| Field             | Type         | Null | Key | Default | Extra |
+-------------------+--------------+------+-----+---------+-------+
| pharma_company_id | int          | NO   | PRI | NULL    |       |
| user_id           | int          | NO   | MUL | NULL    |       |
| details           | varchar(500) | NO   |     | NULL    |       |
| license           | varchar(255) | NO   |     | NULL    |       |
+-------------------+--------------+------+-----+---------+-------+
 */