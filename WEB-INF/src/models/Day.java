package models;

public class Day {
    // ==================VARIABLES===================
    private Integer dayId;
    private String name;


    // ================================================CONSTRUCTORS===================================================
    public Day(String name){
        this.name = name;
    }

    public Day(Integer dayId){
        this.dayId = dayId;
    }
    
    // ================================================METHODS===================================================
    
    
    // ================================================SETTERS===================================================
    public void setDayId(Integer dayId){
        this.dayId = dayId;
    }
    public void setName(String name){
        this.name = name;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getDayId(){
        return dayId;
    }
    public String getName(){
        return name;
    }
}

/*
+--------+----------+------+-----+---------+----------------+
| Field  | Type     | Null | Key | Default | Extra          |
+--------+----------+------+-----+---------+----------------+
| day_id | int      | NO   | PRI | NULL    | auto_increment |
| name   | char(15) | NO   |     | NULL    |                |
+--------+----------+------+-----+---------+----------------+
 */