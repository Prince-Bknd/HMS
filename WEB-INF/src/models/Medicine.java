package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Medicine {
    // ==================VARIABLES===================
    private static String className = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/hmsdb";
    private static String DBUser = "root";
    private static String DBPassword = "4321";

    private Integer medicineId;
    private String name;
    private String description;
    private String ingredients;
    private String effectiveBodypart;
    private String sideEffects;
    private String warnings;
    private String pic;

    private ArrayList<MedicineFormat> medicineFormats;

    private PharmaCompany pharmaCompany;

    // ================================================CONSTRUCTORS===================================================
    public Medicine(){}

    public Medicine(int medicineId){
        this.medicineId = medicineId;
    }

    public Medicine(int medicineId, String name, String ingredients, String effectiveBodypart, String sideEffects, String description, String warnings){
        this(medicineId);
        this.name = name;
        this.ingredients = ingredients;
        this.effectiveBodypart = effectiveBodypart;
        this.sideEffects = sideEffects;
        this.description = description;
        this.warnings = warnings;
    }

    public Medicine(String name, String description, String ingredients, String effectiveBodypart, String sideEffects, String warnings, String pic, PharmaCompany pharmaCompany){
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.effectiveBodypart = effectiveBodypart;
        this.sideEffects = sideEffects;
        this.warnings = warnings;
        this.pic = pic;
        this.pharmaCompany = pharmaCompany;
    }

    public Medicine(Integer medicineId, PharmaCompany pharmaCompany, String name, String description, String ingredients, String effectiveBodypart, String sideEffects, String warnings, String pic, ArrayList<MedicineFormat> medicineFormats){
        this(name, description, ingredients, effectiveBodypart, sideEffects, warnings, pic, pharmaCompany);
        this.medicineId = medicineId;
        this.medicineFormats = medicineFormats;
        
    }

    public Medicine(String name, String description, String ingredients, String effectiveBodypart, String sideEffects, String warnings, String pic, PharmaCompany pharmaCompany, ArrayList<MedicineFormat> medicineFormats){
        this(name, description, ingredients, effectiveBodypart, sideEffects, warnings, pic, pharmaCompany);
        this.medicineFormats = medicineFormats;
    }
    
    // ================================================METHODS===================================================
    public Boolean updateMedicine(){
        boolean flag = false;
        String query = "update medicines set name = ?, description = ?, ingredients = ?, effective_bodypart = ?, side_effects = ?, warnings = ? where medicine_id = ?";

        try{
            Class.forName(className);

            Connection con = DriverManager.getConnection(url, DBUser, DBPassword);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, ingredients);
            ps.setString(4, effectiveBodypart);
            ps.setString(5, sideEffects);
            ps.setString(6, warnings);
            ps.setInt(7, medicineId);

            if(ps.executeUpdate() != 0){
                flag = true;
            }
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }

    public ArrayList<Medicine> collectMyMedicines(PharmaCompany pharmaCompany){
        ArrayList<Medicine> medicines = new ArrayList<>();
        String query = "select * from medicines where pharma_company_id = ?";
        String query2 = "select * from medicine_formats inner join formats on medicine_formats.format_id = formats.format_id where medicine_id = ?";
        String query3 = "SELECT * " +
                        "FROM medicine_denominations AS md " +
                        "INNER JOIN medicine_formats AS mf ON md.medicine_format_id = mf.medicine_format_id " +
                        "INNER JOIN units AS u ON md.unit_id = u.unit_id " +
                        "INNER JOIN formats AS f ON mf.format_id = f.format_id " +
                        "WHERE md.medicine_format_id = ?";
        

        try{
            Class.forName(className);

            Connection con = DriverManager.getConnection(url, DBUser, DBPassword);

            PreparedStatement ps = con.prepareStatement(query);
            PreparedStatement ps2 = con.prepareStatement(query2);
            PreparedStatement ps3 = con.prepareStatement(query3);
            ps.setInt(1, pharmaCompany.getPharmaCompanyId());

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ps2.setInt(1, rs.getInt("medicine_id"));
                ResultSet rs2 = ps2.executeQuery();
                ArrayList<MedicineFormat> mfs = new ArrayList<>();
                while(rs2.next()){
                    ps3.setInt(1, rs2.getInt("medicine_format_id"));
                    ResultSet rs3 = ps3.executeQuery();
                    ArrayList<MedicineDenomination> medicineDenominations = new ArrayList<>();
                    while(rs3.next()){
                        medicineDenominations.add(new MedicineDenomination(
                                                    rs3.getInt("medicine_denomination_id"),
                                                    new MedicineFormat(
                                                        rs3.getInt("medicine_format_id"),
                                                        new Format(
                                                            rs3.getInt("format_id"),
                                                            rs3.getString(11)
                                                        )
                                                    ),
                                                    rs3.getInt("quantity"),
                                                    new Unit(
                                                        rs3.getInt("unit_id"),
                                                        rs3.getString(9)
                                                        )
                                                    )
                                                );
                    }

                    mfs.add(new MedicineFormat(
                                            rs2.getInt("medicine_format_id"),
                                            new Medicine(
                                                rs2.getInt("medicine_id")
                                            ), 
                                            new Format(
                                                rs2.getInt("format_id"), 
                                                rs2.getString("name")
                                            ),
                                            medicineDenominations
                                        )
                                    );
                }
                medicines.add(new Medicine(
                                rs.getInt("medicine_id"), 
                                new PharmaCompany(rs.getInt("pharma_company_id")),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getString("ingredients"),
                                rs.getString("effective_bodypart"),
                                rs.getString("side_effects"),
                                rs.getString("warnings"),
                                rs.getString("pic"),
                                mfs
                                )
                            );
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return medicines;
    }

    public Boolean insertData(PharmaCompany pharmaCompany){
        Boolean flag = false;
        String query = "insert into medicines (pharma_company_id, name, description, ingredients, effective_bodypart, side_effects, warnings, pic) value (?, ?, ?, ?, ?, ?, ?, ?)";
        String query2 = "insert into medicine_formats (medicine_id, format_id) value (?, ?)";
        
        try{
            Class.forName(className);

            Connection con = DriverManager.getConnection(url, DBUser, DBPassword);

            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pharmaCompany.getPharmaCompanyId());
            ps.setString(2, name);
            ps.setString(3, description);
            ps.setString(4, ingredients);
            ps.setString(5, effectiveBodypart);
            ps.setString(6, sideEffects);
            ps.setString(7, warnings);
            ps.setString(8, pic);

            int a = ps.executeUpdate();
            if(a != 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    for(MedicineFormat mf : medicineFormats){
                        PreparedStatement ps2 = con.prepareStatement(query2);
                        ps2.setInt(1, rs.getInt(1));
                        ps2.setInt(2, mf.getFormat().getFormatId());

                        int a1 = ps2.executeUpdate();
                        if(a1 != 0){
                            flag = true;
                        }
                    }
                } else{
                    System.out.println("resultset not found");
                }
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return flag;
    }
    
    
    // ================================================SETTERS===================================================
    public void setMedicineId(Integer medicineId){
        this.medicineId = medicineId;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setIngredients(String ingredients){
        this.ingredients = ingredients;
    }
    public void setEffectiveBodypart(String effectiveBodypart){
        this.effectiveBodypart = effectiveBodypart;
    }
    public void setSideEffects(String sideEffects){
        this.sideEffects = sideEffects;
    }
    public void setWarnings(String warnings){
        this.warnings = warnings;
    }
    public void setpic(String pic){
        this.pic = pic;
    }
    public void setPharmaCompany(PharmaCompany pharmaCompany){
        this.pharmaCompany = pharmaCompany;
    }

    public void setMedicineFormats(ArrayList<MedicineFormat> medicineFormats){
        this.medicineFormats = medicineFormats;
    }
    
    
    // ================================================GETTERS===================================================
    public Integer getMedicineId(){
        return medicineId;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public String getIngredients(){
        return ingredients;
    }
    public String getEffectiveBodypart(){
        return effectiveBodypart;
    }
    public String getSideEffects(){
        return sideEffects;
    }
    public String getWarnings(){
        return warnings;
    }
    public String getpic(){
        return pic;
    }
    public PharmaCompany getPharmaCompany(){
        return pharmaCompany;
    }

    public ArrayList<MedicineFormat> getMedicineFormats(){
        return medicineFormats;
    }
}

/*
+--------------------+--------------+------+-----+---------+----------------+
| Field              | Type         | Null | Key | Default | Extra          |
+--------------------+--------------+------+-----+---------+----------------+
| medicine_id        | int          | NO   | PRI | NULL    | auto_increment |
| pharma_company_id  | int          | NO   | MUL | NULL    |                |
| name               | char(100)    | NO   |     | NULL    |                |
| description        | varchar(500) | NO   |     | NULL    |                |
| ingredients        | varchar(500) | NO   |     | NULL    |                |
| effective_bodypart | char(100)    | NO   |     | NULL    |                |
| side_effects       | char(100)    | NO   |     | NULL    |                |
| warnings           | char(100)    | NO   |     | NULL    |                |
+--------------------+--------------+------+-----+---------+----------------+
 */