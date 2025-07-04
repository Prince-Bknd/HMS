package models;

import java.sql.Date;

public class PatientPreviousPrescription {
    // ==================VARIABLES===================
    private Integer prescriptionId;
    private Date prescriptionDate;
    private String dosage;
    private String duration;
    private String medication;

    private Patient patient;
    private Doctor doctor;
    private Clinic clinic;

    // ================================================CONSTRUCTORS===================================================
    public PatientPreviousPrescription(Date prescriptionDate, String dosage, String duration, String medication, Patient patient, Doctor doctor, Clinic clinic){
        this.prescriptionDate = prescriptionDate;
        this.dosage = dosage;
        this.duration = duration;
        this.medication = medication;
        this.patient = patient;
        this.doctor = doctor;
        this.clinic = clinic;
    }
    
    // ================================================METHODS===================================================
    
    
    // ================================================SETTERS===================================================
    public void setPrescriptionId(Integer prescriptionId){
        this.prescriptionId = prescriptionId;
    }
    public void setPrescriptionDate(Date prescriptionDate){
        this.prescriptionDate = prescriptionDate;
    }
    public void setDosage(String dosage){
        this.dosage = dosage;
    }
    public void setDuration(String duration){
        this.duration = duration;
    }
    public void setMedication(String medication){
        this.medication = medication;
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
    public Integer getPrescriptionId(){
        return prescriptionId;
    }
    public Date getPrescriptionDate(){
        return prescriptionDate;
    }
    public String getDosage(){
        return dosage;
    }
    public String getDuration(){
        return duration;
    }
    public String getMedication(){
        return medication;
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
+-------------------+--------------+------+-----+---------+----------------+
| Field             | Type         | Null | Key | Default | Extra          |
+-------------------+--------------+------+-----+---------+----------------+
| prescription_id   | int          | NO   | PRI | NULL    | auto_increment |
| patient_id        | int          | NO   | MUL | NULL    |                |
| doctor_id         | int          | NO   | MUL | NULL    |                |
| clinic_id         | int          | NO   | MUL | NULL    |                |
| prescription_date | date         | NO   |     | NULL    |                |
| dosage            | varchar(300) | NO   |     | NULL    |                |
| duration          | char(100)    | NO   |     | NULL    |                |
| medication        | varchar(300) | NO   |     | NULL    |                |
+-------------------+--------------+------+-----+---------+----------------+
 */