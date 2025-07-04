package models;

public class ClinicDay {
    // ==================VARIABLES===================
    private Integer clinicDayId;

    private Clinic clinic;
    private Day day;


    // ================================================CONSTRUCTORS===================================================
    public ClinicDay(Clinic clinic, Day day){
        this.clinic = clinic;
        this.day = day;
    }
    
    // ================================================METHODS===================================================
    
    
    // ================================================SETTERS===================================================
    public void setClinicId(Integer clinicDayId){
        this.clinicDayId = clinicDayId;
    }
    public void setClinicDay(Clinic clinic){
        this.clinic = clinic;
    }
    public void setDay(Day day){
        this.day = day;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getClinicId(){
        return clinicDayId;
    }
    public Clinic getClinicDay(){
        return clinic;
    }
    public Day getDay(){
        return day;
    }
}

/*
+---------------+------+------+-----+---------+----------------+
| Field         | Type | Null | Key | Default | Extra          |
+---------------+------+------+-----+---------+----------------+
| clinic_day_id | int  | NO   | PRI | NULL    | auto_increment |
| clinic_id     | int  | NO   | MUL | NULL    |                |
| day_id        | int  | NO   | MUL | NULL    |                |
+---------------+------+------+-----+---------+----------------+
 */