package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class ClinicShift {
    // ==================VARIABLES===================
    private String DBClassName = "com.mysql.cj.jdbc.Driver";
    private String DBurl = "jdbc:mysql://localhost:3306/hmsdb";
    private String DBuser = "root";
    private String DBpassword = "4321";

    private Integer clinicShiftId;
    private Time startTime;
    private Time endTime;
    private Integer maxAppointment;

    private Clinic clinic;
    private Day day;

    private ArrayList<ClinicShift> clinicShifts;

    // ================================================CONSTRUCTORS===================================================
    public ClinicShift(Integer clinicShiftId, Clinic clinic, Time startTime, Time endTime, Day day){
        this.clinicShiftId = clinicShiftId;
        this.clinic = clinic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    public ClinicShift(Integer clinicShiftId){
        this.clinicShiftId = clinicShiftId;
    }

    public ClinicShift(Integer clinicShiftId, Clinic clinic, Time startTime, Time endTime, Integer maxAppointment, Day day){
        this(clinicShiftId, clinic, startTime, endTime, day);
        this.maxAppointment = maxAppointment;
    }

    public ClinicShift(Time startTime, Time endTime, Integer maxAppointment, Clinic clinic){
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxAppointment = maxAppointment;
        this.clinic = clinic;
    }

    public ClinicShift(Time startTime, Time endTime, Integer maxAppointment, Day day){
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxAppointment = maxAppointment;
        this.day = day;
    }

    public ClinicShift(Clinic clinic, ArrayList<ClinicShift> clinicShifts){
        this.clinic = clinic;
        this.clinicShifts = clinicShifts;
    }

    
    // ================================================METHODS===================================================
    public Boolean insertData(){
        Boolean flag = false;
        String query = "insert into clinic_shifts (clinic_id, start_time, end_time, max_appointment, day_id) value (?, ?, ?, ?, ?)";
        String query2 = "delete from clinic_shifts where clinic_id=?";
        int clinicKiId = clinic.getClinicId();

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);

            PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement ps2 = con.prepareStatement(query2);
            ps2.setInt(1, clinicKiId);
            ps2.executeUpdate();

            int res = 0;
            for(ClinicShift cs : clinicShifts){
                ps.setInt(1, clinicKiId);
                ps.setTime(2, cs.getStartTime());
                ps.setTime(3, cs.getEndTime());
                ps.setInt(4, cs.getMaxAppointment());
                ps.setInt(5, cs.getDay().getDayId());
                res += ps.executeUpdate();
            }
            if(res != 0){
                flag = true;
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }
    
    // ================================================SETTERS===================================================
    public void setClinicShiftId(Integer clinicShiftId){
        this.clinicShiftId = clinicShiftId;
    }
    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }
    public void setEndTime(Time endTime){
        this.endTime = endTime;
    }
    public void setMaxAppointment(Integer maxAppointment){
        this.maxAppointment = maxAppointment;
    }
    public void setClinic(Clinic clinic){
        this.clinic = clinic;
    }
    public void setDay(Day day){
        this.day = day;
    }

    public void setClinicShifts(ArrayList<ClinicShift> clinicShifts){
        this.clinicShifts = clinicShifts;
    }
    
    // ================================================GETTERS===================================================  
    public Integer getClinicShiftId(){
        return clinicShiftId;
    }
    public Time getStartTime(){
        return startTime;
    }
    public Time getEndTime(){
        return endTime;
    }
    public Integer getMaxAppointment(){
        return maxAppointment;
    }
    public Clinic getClinic(){
        return clinic;
    }
    public Day getDay(){
        return day;
    }

    public ArrayList<ClinicShift> getClinicShifts(){
        return clinicShifts;
    }
}

/*
+-----------------+------+------+-----+---------+----------------+
| Field           | Type | Null | Key | Default | Extra          |
+-----------------+------+------+-----+---------+----------------+
| clinic_shift_id | int  | NO   | PRI | NULL    | auto_increment |
| clinic_id       | int  | NO   | MUL | NULL    |                |
| start_time      | time | NO   |     | NULL    |                |
| end_time        | time | NO   |     | NULL    |                |
| max_appointment | int  | NO   |     | NULL    |                |
+-----------------+------+------+-----+---------+----------------+
 */