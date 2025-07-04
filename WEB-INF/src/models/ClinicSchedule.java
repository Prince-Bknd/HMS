package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class ClinicSchedule {
    // ==================VARIABLES===================
    private String DBClassName = "com.mysql.cj.jdbc.Driver";
    private String DBurl = "jdbc:mysql://localhost:3306/hmsdb";
    private String DBuser = "root";
    private String DBpassword = "4321";

    private Integer clinicScheduleId;
    private Time openingTime;
    private Time closingTime;
    private Integer maxAppointment;
    private String shiftType;

    private Clinic clinic;


    // ================================================CONSTRUCTORS===================================================
    public ClinicSchedule(Clinic clinic){
        this.clinic = clinic;
    }

    public ClinicSchedule(Integer clinicScheduleId){
        this.clinicScheduleId = clinicScheduleId;
    }

    public ClinicSchedule(Integer clinicScheduleId, Clinic clinic, Time openingTime, Time closingTime, Integer maxAppointment, String shiftType){
        this(clinic, openingTime, closingTime, maxAppointment, shiftType);
        this.clinicScheduleId = clinicScheduleId;
    }

    public ClinicSchedule(Clinic clinic, Time openingTime, Time closingTime, Integer maxAppointment, String shiftType){
        this(clinic);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.maxAppointment = maxAppointment;
        this.shiftType = shiftType;
    }

    public ClinicSchedule(Integer clinicScheduleId, Time openingTime, Time closingTime, Integer maxAppointment, String shiftType){
        this.clinicScheduleId = clinicScheduleId;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.maxAppointment = maxAppointment;
        this.shiftType = shiftType;
    }

    public ClinicSchedule(Time openingTime, Time closingTime, Integer maxAppointment, String shiftType, Clinic clinic){
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.maxAppointment = maxAppointment;
        this.shiftType = shiftType;
        this.clinic = clinic;
    }
    
    // ================================================METHODS===================================================
    public boolean deleteSchedule(){
        boolean flag = false;
        String query = "delete from clinic_schedules where schedule_id=?";
        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clinicScheduleId);

            if(ps.executeUpdate() != 0){
                flag = true;
            }
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }

    public boolean saveData(){
        boolean flag = false;
        String query = "insert into clinic_schedules (clinic_id, opening_time, closing_time, max_appointment, shift_type) value (?, ?, ?, ?, ?)";
        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clinic.getClinicId());
            ps.setTime(2, openingTime);
            ps.setTime(3, closingTime);
            ps.setInt(4, maxAppointment);
            ps.setString(5, shiftType);

            if(ps.executeUpdate() != 0){
                flag = true;
            }
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return flag;
    }

    public ArrayList<ClinicSchedule> getClinicSchedules(){
        ArrayList<ClinicSchedule> clinicSchedules = new ArrayList<>();
        String query = "select * from clinic_schedules where clinic_id = ?";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, clinic.getClinicId());

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                clinicSchedules.add(
                    new ClinicSchedule(
                        rs.getInt("schedule_id"),
                        rs.getTime("opening_time"),
                        rs.getTime("closing_time"),
                        rs.getInt("max_appointment"),
                        rs.getString("shift_type")
                    )
                );
            }
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return clinicSchedules;
    }
    
    
    // ================================================SETTERS===================================================
    public void setClinicScheduleId(Integer clinicScheduleId){
        this.clinicScheduleId = clinicScheduleId;
    }
    public void setOpeningTime(Time openingTime){
        this.openingTime = openingTime;
    }
    public void setClosingTime(Time closingTime){
        this.closingTime = closingTime;
    }
    public void setMaxAppointment(Integer maxAppointment){
        this.maxAppointment = maxAppointment;
    }
    public void setShiftType(String shiftType){
        this.shiftType = shiftType;
    }
    public void setClinic(Clinic clinic){
        this.clinic = clinic;
    }
    
    
    // ================================================GETTERS===================================================  
    public Integer getClinicScheduleId(){
        return clinicScheduleId;
    }
    public Time getOpeningTime(){
        return openingTime;
    }
    public Time getClosingTime(){
        return closingTime;
    }
    public Integer getMaxAppointment(){
        return maxAppointment;
    }
    public String getShiftType(){
        return shiftType;
    }
    public Clinic getClinic(){
        return clinic;
    }
}

/*
+-----------------+-----------+------+-----+---------+----------------+
| Field           | Type      | Null | Key | Default | Extra          |
+-----------------+-----------+------+-----+---------+----------------+
| schedule_id     | int       | NO   | PRI | NULL    | auto_increment |
| clinic_id       | int       | NO   | MUL | NULL    |                |
| opening_time    | time      | NO   |     | NULL    |                |
| closing_time    | time      | NO   |     | NULL    |                |
| max_appointment | int       | NO   |     | NULL    |                |
| shift_type      | char(100) | NO   |     | NULL    |                |
+-----------------+-----------+------+-----+---------+----------------+
 */