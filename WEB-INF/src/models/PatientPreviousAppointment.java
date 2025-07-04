package models;

import java.sql.Timestamp;
import java.sql.Date;

public class PatientPreviousAppointment {
    // ==================VARIABLES===================
    private Integer appointmentId;
    private Date appointmentDate;
    private Timestamp appointmentTime;
    private String reason;
    
    private Patient patient;
    private Doctor doctor;
    private Clinic clinic;

    // ================================================CONSTRUCTORS===================================================
    public PatientPreviousAppointment(Date appointmentDate, Timestamp appointmentTime, String reason, Patient patient, Doctor doctor, Clinic clinic){
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.patient = patient;
        this.doctor = doctor;
        this.clinic = clinic;
    }
    
    // ================================================METHODS===================================================
    
    
    // ================================================SETTERS===================================================
    public void setAppointmentId(Integer appointmentId){
        this.appointmentId = appointmentId;
    }
    public void setAppointmentDate(Date appointmentDate){
        this.appointmentDate = appointmentDate;
    }
    public void setAppointmentTime(Timestamp appointmentTime){
        this.appointmentTime = appointmentTime;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
    public void setPatient(Patient patient){
        this.patient = patient;
    }
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }
    public void setClinic(Clinic clinic){
        this.clinic = clinic;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getAppointmentId(){
        return appointmentId;
    }
    public Date getAppointmentDate(){
        return appointmentDate;
    }
    public Timestamp getAppointmentTime(){
        return appointmentTime;
    }
    public String getReason(){
        return reason;
    }
    public Patient getPatient(){
        return patient;
    }
    public Doctor getDoctor(){
        return doctor;
    }
    public Clinic getClinic(){
        return clinic;
    }
}

/*
+------------------+--------------+------+-----+---------+----------------+
| Field            | Type         | Null | Key | Default | Extra          |
+------------------+--------------+------+-----+---------+----------------+
| appointment_id   | int          | NO   | PRI | NULL    | auto_increment |
| patient_id       | int          | NO   | MUL | NULL    |                |
| doctor_id        | int          | NO   | MUL | NULL    |                |
| clinic_id        | int          | NO   | MUL | NULL    |                |
| appointment_date | date         | NO   |     | NULL    |                |
| appointment_time | time         | NO   |     | NULL    |                |
| reason           | varchar(400) | NO   |     | NULL    |                |
+------------------+--------------+------+-----+---------+----------------+
 */