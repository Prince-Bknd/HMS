package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Doctor {
    // ==================VARIABLES===================
    private String DBClassName = "com.mysql.cj.jdbc.Driver";
    private String DBurl = "jdbc:mysql://localhost:3306/hmsdb";
    private String DBuser = "root";
    private String DBpassword = "4321";

    private Integer doctorId;
    private String gender;
    private String qualification;
    private Integer experience;
    private String certificate;
    private Integer star;

    private User user;
    private Specialization specialization;


    // ================================================CONSTRUCTORS===================================================
    public Doctor() {}

    public Doctor(Integer doctorId){
        this.doctorId = doctorId;
    }

    public Doctor(Specialization specialization){
        this.specialization = specialization;
    }

    public Doctor(Integer doctorId, User user, String gender, Specialization specialization, Integer experience, Integer star){
        this.doctorId = doctorId;
        this.user = user;
        this.gender = gender;
        this.specialization = specialization;
        this.experience = experience;
        this.star = star;
    }

    public Doctor(Integer doctorId, User user, String gender, Specialization specialization, Integer experience, Integer star, String qualification){
        this(doctorId, user, gender, specialization, experience, star);
        this.qualification = qualification;
    }

    public Doctor(String certificate, Integer experience, Specialization specialization, String gender){
        this.certificate = certificate;
        this.experience = experience;
        this.specialization = specialization;
        this.gender = gender;
    }
    
    // ================================================METHODS===================================================
    public ArrayList<Doctor> getDoctors(){
        ArrayList<Doctor> doctors = new ArrayList<>();
        String query = "select * from doctors d inner join specializations as sp on d.specialization_id = sp.specialization_id inner join users as u on d.user_id = u.user_id where d.specialization_id = ?";
        String query2 = "select * from doctors d inner join specializations as sp on d.specialization_id = sp.specialization_id inner join users as u on d.user_id = u.user_id";
        int specializationId = specialization.getSpecializationId();

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            if(specializationId != 0){
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, specializationId);

                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    doctors.add(
                        new Doctor(
                            rs.getInt("doctor_id"),
                            new User(
                                rs.getInt("user_id"),
                                rs.getString(12),
                                rs.getDate("dob"),
                                rs.getString("contact"),
                                rs.getString("address")
                            ),
                            rs.getString("gender"), 
                            new Specialization(
                                rs.getInt("specialization_id"),
                                rs.getString(10)
                            ),
                            rs.getInt("experience"),
                            rs.getInt("star"),
                            rs.getString("qualification")
                        )
                    );
                }
                
            } else{
                PreparedStatement ps = con.prepareStatement(query2);

                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    doctors.add(
                        new Doctor(
                            rs.getInt("doctor_id"),
                            new User(
                                rs.getInt("user_id"),
                                rs.getString(12),
                                rs.getDate("dob"),
                                rs.getString("contact"),
                                rs.getString("address")
                            ),
                            rs.getString("gender"), 
                            new Specialization(
                                rs.getInt("specialization_id"),
                                rs.getString(10)
                            ),
                            rs.getInt("experience"),
                            rs.getInt("star"),
                            rs.getString("qualification")
                        )
                    );
                } 
            }
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return doctors;
    }

    public void setValues(User user){
        String query = "select * from doctors as do inner join specializations as sp on do.specialization_id = sp.specialization_id where user_id = ?";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                doctorId = rs.getInt("doctor_id");
                gender = rs.getString("gender");
                experience = rs.getInt("experience");
                certificate = rs.getString("certificate");
                star = rs.getInt("star");
                qualification = rs.getString("qualification");
                specialization = new Specialization(rs.getInt("specialization_id"), rs.getString("name"));
                this.user = user;
            }
            
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public Boolean checkDoctor(int id){
        Boolean flag = false;
        String query = "select * from doctors where user_id=?";

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

    public Boolean insertData(User user){
        Boolean flag = false;
        String query = "insert into doctors (user_id, gender, specialization_id, experience, certificate) values (?, ?, ?, ?, ?);";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, user.getUserId());
            ps.setString(2, gender);
            ps.setInt(3, specialization.getSpecializationId());
            ps.setInt(4, experience);
            ps.setString(5, certificate);
            int rs = ps.executeUpdate();

            if(rs == 1){
                flag = true;
            }

            
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }
    
    
    // ================================================SETTERS===================================================
    public void setDoctorId(Integer doctorId){
        this.doctorId = doctorId;
    }
    public void setUser(User user){
        this.user = user;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setSpecialization(Specialization specialization){
        this.specialization = specialization;
    }
    public void setQualification(String qualification){
        this.qualification = qualification;
    }
    public void setExperience(Integer experience){
        this.experience = experience;
    }
    public void setCertificate(String certificate){
        this.certificate = certificate;
    }
    public void setStar(Integer star){
        this.star = star;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getDoctorId(){
        return doctorId;
    }
    public User getUser(){
        return user;
    }
    public String getGender(){
        return gender;
    }
    public Specialization getSpecialization(){
        return specialization;
    }
    public String getQualification(){
        return qualification;
    }
    public Integer getExperience(){
        return experience;
    }
    public String getCertificate(){
        return certificate;
    }
    public Integer getstar(){
        return star;
    }
}

/*
+-------------------+-----------+------+-----+---------+----------------+
| Field             | Type      | Null | Key | Default | Extra          |
+-------------------+-----------+------+-----+---------+----------------+
| doctor_id         | int       | NO   | PRI | NULL    | auto_increment |
| user_id           | int       | NO   | MUL | NULL    |                |
| gender            | char(1)   | YES  |     | NULL    |                |
| specialization_id | int       | NO   | MUL | NULL    |                |
| qualification     | char(100) | YES  |     | NULL    |                |
| experience        | int       | YES  |     | NULL    |                |
| certificate       | char(255) | YES  |     | NULL    |                |
+-------------------+-----------+------+-----+---------+----------------+
 */