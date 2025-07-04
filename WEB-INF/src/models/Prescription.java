package models;

public class Prescription {
    // ==================VARIABLES===================
    private Integer prescriptionId;
    private String dosage;

    private Appointment appointment;
    private MedicineDenomination medicineDenomination;


    // ================================================CONSTRUCTORS===================================================
    public Prescription(String dosage, Appointment appointment, MedicineDenomination medicineDenomination){
        this.dosage = dosage;
        this.appointment = appointment;
        this.medicineDenomination = medicineDenomination;
    }
    
    // ================================================METHODS===================================================
    
    
    // ================================================SETTERS===================================================
    public void setPrescriptionId(Integer prescriptionId){
        this.prescriptionId = prescriptionId;
    }
    public void setDosage(String dosage){
        this.dosage = dosage;
    }
    public void setAppointmentId(Appointment appointment){
        this.appointment = appointment;
    }
    public void setMedicineDenominationId(MedicineDenomination medicineDenomination){
        this.medicineDenomination = medicineDenomination;
    }
    
    
    // ================================================GETTERS=================================================== 
    public Integer getPrescriptionId(){
        return prescriptionId;
    }
    public String getDosage(){
        return dosage;
    }
    public Appointment getAppointmentId(){
        return appointment;
    }
    public MedicineDenomination getMedicineDenominationId(){
        return medicineDenomination;
    }
}

/*
+--------------------------+----------+------+-----+---------+----------------+
| Field                    | Type     | Null | Key | Default | Extra          |
+--------------------------+----------+------+-----+---------+----------------+
| prescription_id          | int      | NO   | PRI | NULL    | auto_increment |
| appointment_id           | int      | NO   | MUL | NULL    |                |
| medicine_denomination_id | int      | NO   | MUL | NULL    |                |
| dosage                   | char(50) | NO   |     | NULL    |                |
+--------------------------+----------+------+-----+---------+----------------+
 */