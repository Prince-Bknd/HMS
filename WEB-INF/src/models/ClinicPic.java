package models;

public class ClinicPic {
    // ==================VARIABLES===================
    private Integer clinicPicId;
    private String picPath;

    private Clinic clinic;


    // ================================================CONSTRUCTORS===================================================
    public ClinicPic(String picPath, Clinic clinic){
        this.picPath = picPath;
        this.clinic = clinic;
    }
    
    // ================================================METHODS===================================================
    
    
    // ================================================SETTERS===================================================
    public void setClinicPicId(Integer clinicPicId){
        this.clinicPicId = clinicPicId;
    }
    public void setPicPath(String picPath){
        this.picPath = picPath;
    }
    public void setClinic(Clinic clinic){
        this.clinic = clinic;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getClinicPicId(){
        return clinicPicId;
    }
    public String getPicPath(){
        return picPath;
    }
    public Clinic getClinic(){
        return clinic;
    }
}

/*
+---------------+-----------+------+-----+---------+----------------+
| Field         | Type      | Null | Key | Default | Extra          |
+---------------+-----------+------+-----+---------+----------------+
| clinic_pic_id | int       | NO   | PRI | NULL    | auto_increment |
| clinic_id     | int       | NO   | MUL | NULL    |                |
| pic_path      | char(255) | NO   |     | NULL    |                |
+---------------+-----------+------+-----+---------+----------------+
 */