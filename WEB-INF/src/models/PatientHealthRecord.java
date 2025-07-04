package models;

import java.sql.Date;

public class PatientHealthRecord {
    // ==================VARIABLES===================
    private Integer recordId;
    private String diagnosis;
    private String treatment;
    private Date recordDate;
    private String medication;

    private Patient patient;
    private Doctor doctor;
    private Clinic clinic;


    // ================================================CONSTRUCTORS===================================================
    public PatientHealthRecord(String diagnosis, String treatment, Date recordDate, String medication, Patient patient, Doctor doctor, Clinic clinic){
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.recordDate = recordDate;
        this.medication = medication;
        this.patient = patient;
        this.doctor = doctor;
        this.clinic = clinic;

    }
    
    // ================================================METHODS===================================================
    
    
    // ================================================SETTERS===================================================
    public void setRecordId(Integer recordId){
        this.recordId = recordId;
    }
    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }
    public void setTreatment(String treatment){
        this.treatment = treatment;
    }
    public void setRecordDate(Date recordDate){
        this.recordDate = recordDate;
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
    public Integer getRecordId(){
        return recordId;
    }
    public String getDiagnosis(){
        return diagnosis;
    }
    public String getTreatment(){
        return treatment;
    }
    public Date getRecordDate(){
        return recordDate;
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
+-------------+--------------+------+-----+---------+----------------+
| Field       | Type         | Null | Key | Default | Extra          |
+-------------+--------------+------+-----+---------+----------------+
| record_id   | int          | NO   | PRI | NULL    | auto_increment |
| patient_id  | int          | NO   | MUL | NULL    |                |
| doctor_id   | int          | NO   | MUL | NULL    |                |
| clinic_id   | int          | NO   | MUL | NULL    |                |
| diagnosis   | varchar(500) | NO   |     | NULL    |                |
| treatment   | varchar(500) | NO   |     | NULL    |                |
| record_date | date         | NO   |     | NULL    |                |
| medication  | varchar(300) | NO   |     | NULL    |                |
+-------------+--------------+------+-----+---------+----------------+
 */