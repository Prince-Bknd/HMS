package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Clinic {
    // ==================VARIABLES===================
    private String DBClassName = "com.mysql.cj.jdbc.Driver";
    private String DBurl = "jdbc:mysql://localhost:3306/hmsdb";
    private String DBuser = "root";
    private String DBpassword = "4321";

    private Integer clinicId;
    private String name;
    private String closedDay;
    private String address;
    private String contact;
    private String certifications;
    private Integer rating;
    private String photo;
    private String description;
    private Integer consultationFee;
    
    private Doctor doctor;
    private City city;

    private ArrayList<ClinicShift> clinicShifts;
    private ArrayList<ClinicSchedule> clinicSchedules;

    // ================================================CONSTRUCTORS===================================================
    public Clinic(){}

    public Clinic(Integer clinicId, Doctor doctor, String address, City city, String contact, String name, String photo, Integer rating, String description, Integer consultationFee){
        this.clinicId = clinicId;
        this.doctor = doctor;
        this.address = address;
        this.city = city;
        this.contact = contact;
        this.name = name;
        this.photo = photo;
        this.rating = rating;
        this.description = description;
        this.consultationFee = consultationFee;
    }

    public Clinic(Integer clinicId){
        this.clinicId = clinicId;
    }

    public Clinic(int clinicId, Doctor doctor, String address, City city, String contact, Integer consultationFee, String name, String closedDay, String certifications, String photo, String description, Integer rating, ArrayList<ClinicShift> clinicShifts, ArrayList<ClinicSchedule> clinicSchedules){
        this(clinicId, doctor, address, city, contact, name, photo, rating, description, consultationFee);
        this.closedDay = closedDay;
        this.certifications = certifications;
        this.clinicShifts = clinicShifts;
        this.clinicSchedules = clinicSchedules;
    }

    public Clinic(Doctor doctor, String address, City city, String contact, Integer consultationFee, String name, String closedDay, String certifications, String photo, String description){
        this.doctor = doctor;
        this.address = address;
        this.city = city;
        this.contact = contact;
        this.consultationFee = consultationFee;
        this.name = name;
        this.closedDay = closedDay;
        this.certifications = certifications;
        this.photo = photo;
        this.description = description;
    }
    

    // ================================================METHODS===================================================
    public ArrayList<Clinic> getClinics(Doctor doctor){
        ArrayList<Clinic> clinics = new ArrayList<>();
        String query = "SELECT * FROM clinics as cl inner join cities as ci on cl.city_id = ci.city_id inner join states as st on ci.state_id = st.state_id WHERE doctor_id = " + doctor.getDoctorId();
        String query2 = "select * from clinic_shifts where clinic_id = ?";
        String query3 = "select * from clinic_schedules where clinic_id = ?";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement ps2 = con.prepareStatement(query2);
            PreparedStatement ps3 = con.prepareStatement(query3);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ps2.setInt(1, rs.getInt("clinic_id"));
                ps3.setInt(1, rs.getInt("clinic_id"));

                ResultSet rs2 = ps2.executeQuery();
                ResultSet rs3 = ps3.executeQuery();

                clinicShifts = new ArrayList<>();
                clinicSchedules = new ArrayList<>();

                while(rs2.next()){
                    clinicShifts.add(
                        new ClinicShift(
                            rs2.getInt("clinic_shift_id"),
                            new Clinic(
                                rs2.getInt("clinic_id")
                            ),
                            rs2.getTime("start_time"),
                            rs2.getTime("end_time"),
                            rs2.getInt("max_appointment"),
                            new Day(
                                rs2.getInt("day_id")
                            )
                        )
                    );
                }
                
                while(rs3.next()){
                    clinicSchedules.add(
                        new ClinicSchedule(
                            rs3.getInt("schedule_id"),
                            new Clinic(
                                rs3.getInt("clinic_id")
                            ),
                            rs3.getTime("opening_time"),
                            rs3.getTime("closing_time"),
                            rs3.getInt("max_appointment"),
                            rs3.getString("shift_type")
                        )
                    );
                }

                clinics.add(
                    new Clinic(
                        rs.getInt("clinic_id"),
                        doctor, 
                        rs.getString("address"), 
                        new City(
                            rs.getInt("city_id"), 
                            rs.getString(14),
                            new State(
                                rs.getInt("state_id"), 
                                rs.getString(17)
                            )
                        ), 
                        rs.getString("contact"), 
                        rs.getInt("consultation_fee"), 
                        rs.getString("name"), 
                        rs.getString("closed_day"), 
                        rs.getString("certifications"), 
                        rs.getString("photo"),
                        rs.getString("description"),
                        rs.getInt("rating"),
                        clinicShifts,
                        clinicSchedules
                    )
                );
            }

        } catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return clinics;
    }

    public Boolean insertData(){
        String query = "insert into clinics (doctor_id, name, address, city_id, contact, consultation_fee, closed_day, certifications, photo, description) value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Boolean flag = false;
        try{
            Class.forName(DBClassName);
            
            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, doctor.getDoctorId());
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setInt(4, city.getCityId());
            ps.setString(5, contact);
            ps.setInt(6, consultationFee);
            ps.setString(7, closedDay);
            ps.setString(8, certifications);
            ps.setString(9, "clinics/" + photo);
            ps.setString(10, description);
            
            if(ps.executeUpdate() == 1){
                flag = true;
            }
            
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }
    
    
    // ================================================SETTERS===================================================
    public void setClinicId(Integer clinicId){
        this.clinicId = clinicId;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setContact(String contact){
        this.contact = contact;
    }
    public void setConsultationFee(Integer consultationFee){
        this.consultationFee = consultationFee;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCertifications(String certifications){
        this.certifications = certifications;
    }
    public void setCLosedDay(String closedDay){
        this.closedDay = closedDay;
    }
    public void setrating(Integer rating){
        this.rating = rating;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }
    public void setCity(City city){
        this.city = city;
    }
    public void setPhoto(String photo){
        this.photo = photo;
    }
    public void setClinicShifts(ArrayList<ClinicShift> clinicShifts){
        this.clinicShifts = clinicShifts;
    }
    public void setClinicSchedules(ArrayList<ClinicSchedule> clinicSchedules){
        this.clinicSchedules = clinicSchedules;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getClinicId(){
        return clinicId;
    }
    public String getAddress(){
        return address;
    }
    public String getContact(){
        return contact;
    }
    public Integer getConsultationFee(){
        return consultationFee;
    }
    public String getName(){
        return name;
    }
    public String getCertifications(){
        return certifications;
    }
    public String getClosedDay(){
        return closedDay;
    }
    public Integer getRating(){
        return rating;
    }
    public String getDescription(){
        return description;
    }
    public Doctor getDoctor(){
        return doctor;
    }
    public City getCity(){
        return city;
    }
    public String getPhoto(){
        return photo;
    }
    public ArrayList<ClinicShift> getClinicShifts(){
        return clinicShifts;
    }
    public ArrayList<ClinicSchedule> getClinicSchedules(){
        return clinicSchedules;
    }

}

/*
+------------------+--------------+------+-----+---------+----------------+
| Field            | Type         | Null | Key | Default | Extra          |
+------------------+--------------+------+-----+---------+----------------+
| clinic_id        | int          | NO   | PRI | NULL    | auto_increment |
| doctor_id        | int          | NO   | MUL | NULL    |                |
| address          | varchar(500) | NO   |     | NULL    |                |
| city_id          | int          | NO   | MUL | NULL    |                |
| contact          | char(10)     | NO   |     | NULL    |                |
| consultation_fee | int          | NO   |     | NULL    |                |
+------------------+--------------+------+-----+---------+----------------+
 */