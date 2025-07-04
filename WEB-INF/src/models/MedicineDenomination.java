package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineDenomination {
    // ==================VARIABLES===================
    private static String DBClassName = "com.mysql.cj.jdbc.Driver";
    private static String DBurl = "jdbc:mysql://localhost:3306/hmsdb";
    private static String DBuser = "root";
    private static String DBpassword = "4321"; 

    private Integer medicineDenominationId;
    private Integer quantity;

    private MedicineFormat medicineFormat;
    private Unit unit;

    private Integer unitId;
    private Integer medicineFormatId;


    // ================================================CONSTRUCTORS===================================================
    public MedicineDenomination(){ }

    public MedicineDenomination(Integer medicineDenominationId, MedicineFormat medicineFormat, Integer quantity, Unit unit){
        this(quantity, medicineFormat, unit);
        this.medicineDenominationId = medicineDenominationId;
    }

    public MedicineDenomination(Integer quantity, MedicineFormat medicineFormat, Unit unit){
        this.quantity = quantity;
        this.medicineFormat = medicineFormat;
        this.unit = unit;
    }
    
    // ================================================METHODS===================================================
    public boolean insertDenominations(ArrayList<MedicineDenomination> denominations){
        boolean flag = false;
        String query1 = "update medicine_denominations set quantity = ?, unit_id = ? where medicine_denomination_id = ?";
        String query2 = "insert into medicine_denominations (medicine_format_id, quantity, unit_id) values (?, ?, ?)";
        int result = 0;

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);
            PreparedStatement ps1 = con.prepareStatement(query1);
            PreparedStatement ps2 = con.prepareStatement(query2);

            for(MedicineDenomination md: denominations){
                if(md.getMedicineDenominationId() != 0){
                    System.out.println("To Update: " + md.getQuantity() + " ~ " + md.getUnitId() + " ~ " + md.getMedicineDenominationId());
                    ps1.setInt(1, md.getQuantity());
                    ps1.setInt(2, md.getUnitId());
                    ps1.setInt(3, md.getMedicineDenominationId());
                    if(ps1.executeUpdate() != 0){
                        result += 1;
                    }
                } else{
                    System.out.println("To Insert: " + md.getMedicineFormatId() + " ~ " + md.getQuantity() + " ~ " + md.getUnitId());
                    ps2.setInt(1, md.getMedicineFormatId());
                    ps2.setInt(2, md.getQuantity());
                    ps2.setInt(3, md.getUnitId());
                    if(ps2.executeUpdate() != 0){
                        result += 1;
                    }
                }
            }

            if(result != 0){
                flag = true;
            }
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }

    public boolean insertData(){
        boolean flag = false;
        String query = "insert into medicine_denominations (medicine_format_id, quantity, unit_id) value (?, ?, ?)";
        
        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBuser, DBpassword);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, medicineFormat.getMedicineFormatId());
            ps.setInt(2, quantity);
            ps.setInt(3, unit.getUnitId());

            if(ps.executeUpdate() != 0){
                flag = true;
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }
    
    
    // ================================================SETTERS===================================================
    public void setMedicineDenominationId(Integer medicineDenominationId){
        this.medicineDenominationId = medicineDenominationId;
    }
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }
    public void setMedicineFormat(MedicineFormat medicineFormat){
        this.medicineFormat = medicineFormat;
    }
    public void setUnit(Unit unit){
        this.unit = unit;
    }

    public void setUnitId(Integer unitId){
        this.unitId = unitId;
    }
    public void setMedicineFormatId(Integer medicineFormatId){
        this.medicineFormatId = medicineFormatId;
    } 
    
    // ================================================GETTERS===================================================
    public Integer getMedicineDenominationId(){
        return medicineDenominationId;
    }
    public Integer getQuantity(){
        return quantity;
    }
    public MedicineFormat getMedicineFormat(){
        return medicineFormat;
    }
    public Unit getUnit(){
        return unit;
    }

    public Integer getUnitId(){
        return unitId;
    }
    public Integer getMedicineFormatId(){
        return medicineFormatId;
    }
}

/*
+--------------------------+------+------+-----+---------+----------------+
| Field                    | Type | Null | Key | Default | Extra          |
+--------------------------+------+------+-----+---------+----------------+
| medicine_denomination_id | int  | NO   | PRI | NULL    | auto_increment |
| medicine_format_id       | int  | NO   | MUL | NULL    |                |
| quantity                 | int  | NO   |     | NULL    |                |
| unit_id                  | int  | NO   | MUL | NULL    |                |
+--------------------------+------+------+-----+---------+----------------+
 */