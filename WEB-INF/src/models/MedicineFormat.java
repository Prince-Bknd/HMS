package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineFormat {
    // ==================VARIABLES===================
    private static String DBClassName = "com.mysql.cj.jdbc.Driver";
    private static String DBurl = "jdbc:mysql://localhost:3306/hmsdb";
    private static String DBName = "root";
    private static String DBPassword = "4321";

    private Integer medicineFormatId;

    private Medicine medicine;
    private Format format;


    private ArrayList<MedicineDenomination> medicineDenominations;


    // ================================================CONSTRUCTORS===================================================
    public MedicineFormat(){ }

    public MedicineFormat(Integer medicineFormatId){
        this.medicineFormatId = medicineFormatId;
    }

    public MedicineFormat(Medicine medicine, Format format){
        this.medicine = medicine;
        this.format = format;
    }

    public MedicineFormat(Integer medicineFormatId, Format format){
        this.medicineFormatId = medicineFormatId;
        this.format = format;
    }

    public MedicineFormat(Format format){
        this.format = format;
    }

    public MedicineFormat(Medicine medicine){
        this.medicine = medicine;
    }

    public MedicineFormat(Integer medicineFormatId, Medicine medicine, Format format, ArrayList<MedicineDenomination> medicineDenominations){
        this.medicineFormatId = medicineFormatId;
        this.medicine = medicine;
        this.format = format;
        this.medicineDenominations = medicineDenominations;
    }
    
    // ================================================METHODS===================================================
    public Integer saveMedicineFormatId(){
        Integer result = 0;
        String query = "insert into medicine_formats (medicine_id, format_id) value (?, ?)";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBName, DBPassword);
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, medicine.getMedicineId());
            ps.setInt(2, format.getFormatId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                result = rs.getInt(1);
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public boolean insertUpdatedFormats(Integer medicineId, String[] formatIds){
        boolean flag = false;
        String query = "insert into medicine_formats (medicine_id, format_id) values (?, ?)";
        String query2 = "delete from medicine_formats where medicine_id = ?";
        String query3 = "DELETE FROM medicine_denominations " + 
        "WHERE medicine_format_id IN (SELECT medicine_format_id FROM medicine_formats WHERE medicine_id = ?)";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBName, DBPassword);

            PreparedStatement ps2 = con.prepareStatement(query2);
            PreparedStatement ps3 = con.prepareStatement(query3);
            ps3.setInt(1, medicineId);
            ps2.setInt(1, medicineId);
            ps3.executeUpdate();
            ps2.executeUpdate();
            PreparedStatement ps = con.prepareStatement(query);
            int res = 0;
            for(String fid : formatIds){
                ps.setInt(1, medicineId);
                ps.setInt(2, Integer.parseInt(fid));
                ps.executeUpdate();
                res += 1;
            }

            if(res != 0){
                flag = true;
            }
            
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }

    public ArrayList<Integer> getAllMedicineFormats(){
        ArrayList<Integer> medicineFormats = new ArrayList<>();
        String query = "select * from medicine_formats inner join formats on medicine_formats.format_id = formats.format_id where medicine_id = ?";

        try{
            Class.forName(DBClassName);

            Connection con = DriverManager.getConnection(DBurl, DBName, DBPassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, medicine.getMedicineId());

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                medicineFormats.add(
                    rs.getInt("format_id")
                );
            }
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return medicineFormats;
    }

    // ================================================SETTERS===================================================
    public void setMedicineFormatId(Integer medicineFormatId){
        this.medicineFormatId = medicineFormatId;
    }
    public void setMedicine(Medicine medicine){
        this.medicine = medicine;
    }
    public void setFormat(Format format){
        this.format = format;
    }
    
    public void setMedicineDenominations(ArrayList<MedicineDenomination> medicineDenominations){
        this.medicineDenominations = medicineDenominations;
    }
    
    // ================================================GETTERS===================================================
    public Integer getMedicineFormatId(){
        return medicineFormatId;
    }
    public Medicine getMedicine(){
        return medicine;
    }
    public Format getFormat(){
        return format;
    }
    
    public ArrayList<MedicineDenomination> getMedicineDenominations(){
        return medicineDenominations;
    }
}

/*
+--------------------+------+------+-----+---------+----------------+
| Field              | Type | Null | Key | Default | Extra          |
+--------------------+------+------+-----+---------+----------------+
| medicine_format_id | int  | NO   | PRI | NULL    | auto_increment |
| medicine_id        | int  | NO   | MUL | NULL    |                |
| format_id          | int  | NO   | MUL | NULL    |                |
+--------------------+------+------+-----+---------+----------------+
 */