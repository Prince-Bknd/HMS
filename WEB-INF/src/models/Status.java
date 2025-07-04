package models;

public class Status {
    // ==================VARIABLES===================
    private Integer statusId;
    private String name;


    // ================================================CONSTRUCTORS===================================================
    public Status(Integer statusId){
        this.statusId = statusId;
    }
    public Status(String name){
        this.name = name;
    }
    public Status(Integer statusId, String name){
        this.statusId = statusId;
        this.name = name;
    }
    
    // ================================================METHODS===================================================
    
    
    // ================================================SETTERS===================================================
    public void setStatusId(Integer statusId){
        this.statusId = statusId;
    }
    public void setName(String name){
        this.name = name;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getStatusId(){
        return statusId;
    }
    public String getName(){
        return name;
    }
}


/*
+-----------+----------+------+-----+---------+-------+
| Field     | Type     | Null | Key | Default | Extra |
+-----------+----------+------+-----+---------+-------+
| status_id | int      | NO   | PRI | NULL    |       |
| name      | char(15) | NO   |     | NULL    |       |
+-----------+----------+------+-----+---------+-------+
 */