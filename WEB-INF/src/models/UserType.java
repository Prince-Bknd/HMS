package models;

public class UserType {
    // ==================VARIABLES===================
    public Integer userTypeId;
    public String type;


    // ================================================CONSTRUCTORS===================================================
    public UserType(){}
    
    public UserType(Integer userTypeId){
        this.userTypeId = userTypeId;
    }
    
    // ================================================METHODS===================================================
    
    
    // ================================================SETTERS===================================================
    public void setUserTypeId(Integer userTypeId){
        this.userTypeId = userTypeId;
    }
    public void setType(String type){
        this.type = type;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getUserTypeId(){
        return userTypeId;
    }
    public String getType(){
        return type;
    }
}

/*
+--------------+----------+------+-----+---------+-------+
| Field        | Type     | Null | Key | Default | Extra |
+--------------+----------+------+-----+---------+-------+
| user_type_id | int      | NO   | PRI | NULL    |       |
| type         | char(15) | NO   |     | NULL    |       |
+--------------+----------+------+-----+---------+-------+
 */
