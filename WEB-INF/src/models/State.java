package models;

public class State {
    // ==================VARIABLES===================
    private Integer stateId;
    private String name;


    // ================================================CONSTRUCTORS===================================================
    public State(Integer stateId, String name){
        this.stateId = stateId;
        this.name = name;
    }

    public State(String name){
        this.name = name;
    }
    
    // ================================================METHODS===================================================
    
    
    // ================================================SETTERS===================================================
    public void setStateId(Integer stateId){
        this.stateId = stateId;
    }
    public void setName(String name){
        this.name = name;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getStateId(){
        return stateId;
    }   
    public String getName(){
        return name;
    }
}

/*
+----------+----------+------+-----+---------+----------------+
| Field    | Type     | Null | Key | Default | Extra          |
+----------+----------+------+-----+---------+----------------+
| state_id | int      | NO   | PRI | NULL    | auto_increment |
| name     | char(45) | NO   |     | NULL    |                |
+----------+----------+------+-----+---------+----------------+
 */