package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class Appointment {

    // ==================VARIABLES===================
    private static String DBName = "com.mysql.cj.jdbc.Driver";
    private static String DBUrl = "jdbc:mysql://localhost:3306/hmsdb";
    private static String DBUser = "root";
    private static String DBPassword = "4321";

    private Integer appointmentId;
    private Date appointmentDate;
    private Date currentDate;
    
    private Patient patient;
    private ClinicSchedule clinicSchedule;
    private Status status;


    // ================================================CONSTRUCTORS===================================================
    public Appointment(){ }

    public Appointment(Date appointmentDate, Date currentDate, Patient patient, ClinicSchedule clinicSchedule, Status status){
        this(patient, clinicSchedule, status, appointmentDate);
        this.currentDate = currentDate;
    }

    public Appointment(Patient patient){
        this.patient = patient;
    }

    public Appointment(Integer appointmentId, Patient patient, Date appointmentDate, ClinicSchedule clinicSchedule, Status status, Date currentDate){
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.clinicSchedule = clinicSchedule;
        this.status = status;
        this.currentDate = currentDate;
    }

    public Appointment(Patient patient, ClinicSchedule clinicSchedule, Status status, Date appointmentDate){
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.clinicSchedule = clinicSchedule;
        this.status = status;
    }
    
    // ================================================METHODS===================================================
    public Boolean saveAppointment(){
        Boolean flag = false;
        // current_date is reserved KeyWord
        String query = "insert into appointments (patient_id, appointment_date, schedule_id, status_id, `current_date`) value (?, ?, ?, ?, ?)";

        try{
            Class.forName(DBName);
            
            Connection con = DriverManager.getConnection(DBUrl, DBUser, DBPassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, patient.getPatientId());
            ps.setDate(2, appointmentDate);
            ps.setInt(3, clinicSchedule.getClinicScheduleId());
            ps.setInt(4, 4);
            ps.setDate(5, currentDate);

            if(ps.executeUpdate() != 0){
                flag = true;
            }
           
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }


        return flag;
    }

    public ArrayList<Appointment> getDiagonisedAppointments(Integer doctorId){
        ArrayList<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments as ap " +
                        "inner join statuses as sts ON ap.status_id = sts.status_id " + 
                        "inner join clinic_schedules as cs ON ap.schedule_id = cs.schedule_id " + 
                        "inner join clinics as c ON cs.clinic_id = c.clinic_id " + 
                        "inner join doctors as d on c.doctor_id = d.doctor_id " + 
                        "inner join users as u on d.user_id = u.user_id " +
                        "inner join specializations as s on d.specialization_id = s.specialization_id " +
                        "inner join cities as ci on c.city_id = ci.city_id " +
                        "inner join states as st on ci.state_id = st.state_id " + 
                        "inner join patients as p on ap.patient_id = p.patient_id " + 
                        "inner join users as userp on p.user_id = userp.user_id " + 
                        "WHERE d.doctor_id = ? and ap.status_id = 3";
        
        try{
            Class.forName(DBName);
            
            Connection con = DriverManager.getConnection(DBUrl, DBUser, DBPassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, doctorId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                appointments.add(
                    new Appointment(
                        rs.getInt("appointment_id"),
                        new Patient(
                            rs.getInt("patient_id"),
                            new User(
                                rs.getInt(60),
                                rs.getString(61),
                                rs.getDate(64),
                                rs.getString(67),
                                rs.getString("email"),
                                rs.getString("contact")
                            ),
                            rs.getString("gender"),
                            rs.getString("blood_group"),
                            rs.getFloat("weight"),
                            rs.getInt("height"),
                            rs.getString("uid")
                        ),
                        rs.getDate("appointment_date"),
                        new ClinicSchedule(
                            rs.getInt("schedule_id"),
                            new Clinic(
                                rs.getInt("clinic_id"),
                                new Doctor(
                                    rs.getInt("doctor_id"),
                                    new User(
                                        rs.getInt(35),
                                        rs.getString(36),   //Done
                                        rs.getDate(39),
                                        rs.getString(42),
                                        rs.getString("email"),
                                        rs.getString("contact")
                                    ),
                                    rs.getString("gender"),
                                    new Specialization(
                                        rs.getInt("specialization_id"),
                                        rs.getString(47) //Done
                                    ),
                                    rs.getInt("experience"),
                                    rs.getInt("star")
                                ),
                                rs.getString("address"),
                                new City(
                                    rs.getInt("city_id"),
                                    rs.getString(49),       //Done
                                    new State(
                                        rs.getInt("state_id"),
                                        rs.getString(52)  //Done
                                    )
                                ),
                                rs.getString("contact"),
                                rs.getString(21),      //Done
                                rs.getString("photo"),
                                rs.getInt("rating"),
                                rs.getString("description"),
                                rs.getInt("consultation_fee")
                            ),
                            rs.getTime("opening_time"),
                            rs.getTime("closing_time"),
                            rs.getInt("max_appointment"),
                            rs.getString("shift_type")
                        ),
                        new Status(
                            rs.getInt("status_id"),
                            rs.getString(8)       //Done
                        ),
                        rs.getDate("current_date")
                    )
                );
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return appointments;
    }

    public ArrayList<Appointment> getUndiagonisedAppointments(Integer doctorId){
        ArrayList<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments as ap " +
                        "inner join statuses as sts ON ap.status_id = sts.status_id " + 
                        "inner join clinic_schedules as cs ON ap.schedule_id = cs.schedule_id " + 
                        "inner join clinics as c ON cs.clinic_id = c.clinic_id " + 
                        "inner join doctors as d on c.doctor_id = d.doctor_id " + 
                        "inner join users as u on d.user_id = u.user_id " +
                        "inner join specializations as s on d.specialization_id = s.specialization_id " +
                        "inner join cities as ci on c.city_id = ci.city_id " +
                        "inner join states as st on ci.state_id = st.state_id " + 
                        "inner join patients as p on ap.patient_id = p.patient_id " + 
                        "inner join users as userp on p.user_id = userp.user_id " + 
                        "WHERE d.doctor_id = ? and ap.status_id = 4";
        
        try{
            Class.forName(DBName);
            
            Connection con = DriverManager.getConnection(DBUrl, DBUser, DBPassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, doctorId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                appointments.add(
                    new Appointment(
                        rs.getInt("appointment_id"),
                        new Patient(
                            rs.getInt("patient_id"),
                            new User(
                                rs.getInt(60),
                                rs.getString(61),
                                rs.getDate(64),
                                rs.getString(67),
                                rs.getString("email"),
                                rs.getString("contact")
                            ),
                            rs.getString("gender"),
                            rs.getString("blood_group"),
                            rs.getFloat("weight"),
                            rs.getInt("height"),
                            rs.getString("uid")
                        ),
                        rs.getDate("appointment_date"),
                        new ClinicSchedule(
                            rs.getInt("schedule_id"),
                            new Clinic(
                                rs.getInt("clinic_id"),
                                new Doctor(
                                    rs.getInt("doctor_id"),
                                    new User(
                                        rs.getInt(35),
                                        rs.getString(36),   //Done
                                        rs.getDate(39),
                                        rs.getString(42),
                                        rs.getString("email"),
                                        rs.getString("contact")
                                    ),
                                    rs.getString("gender"),
                                    new Specialization(
                                        rs.getInt("specialization_id"),
                                        rs.getString(47) //Done
                                    ),
                                    rs.getInt("experience"),
                                    rs.getInt("star")
                                ),
                                rs.getString("address"),
                                new City(
                                    rs.getInt("city_id"),
                                    rs.getString(49),       //Done
                                    new State(
                                        rs.getInt("state_id"),
                                        rs.getString(52)  //Done
                                    )
                                ),
                                rs.getString("contact"),
                                rs.getString(21),      //Done
                                rs.getString("photo"),
                                rs.getInt("rating"),
                                rs.getString("description"),
                                rs.getInt("consultation_fee")
                            ),
                            rs.getTime("opening_time"),
                            rs.getTime("closing_time"),
                            rs.getInt("max_appointment"),
                            rs.getString("shift_type")
                        ),
                        new Status(
                            rs.getInt("status_id"),
                            rs.getString(8)       //Done
                        ),
                        rs.getDate("current_date")
                    )
                );
            }
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return appointments;
    }

    public ArrayList<Appointment> getAllAppointments(Integer doctorId){
        ArrayList<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments as ap " +
                        "inner join statuses as sts ON ap.status_id = sts.status_id " + 
                        "inner join clinic_schedules as cs ON ap.schedule_id = cs.schedule_id " + 
                        "inner join clinics as c ON cs.clinic_id = c.clinic_id " + 
                        "inner join doctors as d on c.doctor_id = d.doctor_id " + 
                        "inner join users as u on d.user_id = u.user_id " +
                        "inner join specializations as s on d.specialization_id = s.specialization_id " +
                        "inner join cities as ci on c.city_id = ci.city_id " +
                        "inner join states as st on ci.state_id = st.state_id " + 
                        "inner join patients as p on ap.patient_id = p.patient_id " + 
                        "inner join users as userp on p.user_id = userp.user_id " + 
                        "WHERE d.doctor_id = ?";
        
        try{
            Class.forName(DBName);
            
            Connection con = DriverManager.getConnection(DBUrl, DBUser, DBPassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, doctorId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                appointments.add(
                    new Appointment(
                        rs.getInt("appointment_id"),
                        new Patient(
                            rs.getInt("patient_id"),
                            new User(
                                rs.getInt(60),
                                rs.getString(61),
                                rs.getDate(64),
                                rs.getString(67),
                                rs.getString("email"),
                                rs.getString("contact")
                            ),
                            rs.getString("gender"),
                            rs.getString("blood_group"),
                            rs.getFloat("weight"),
                            rs.getInt("height"),
                            rs.getString("uid")
                        ),
                        rs.getDate("appointment_date"),
                        new ClinicSchedule(
                            rs.getInt("schedule_id"),
                            new Clinic(
                                rs.getInt("clinic_id"),
                                new Doctor(
                                    rs.getInt("doctor_id"),
                                    new User(
                                        rs.getInt(35),
                                        rs.getString(36),   //Done
                                        rs.getDate(39),
                                        rs.getString(42),
                                        rs.getString("email"),
                                        rs.getString("contact")
                                    ),
                                    rs.getString("gender"),
                                    new Specialization(
                                        rs.getInt("specialization_id"),
                                        rs.getString(47) //Done
                                    ),
                                    rs.getInt("experience"),
                                    rs.getInt("star")
                                ),
                                rs.getString("address"),
                                new City(
                                    rs.getInt("city_id"),
                                    rs.getString(49),       //Done
                                    new State(
                                        rs.getInt("state_id"),
                                        rs.getString(52)  //Done
                                    )
                                ),
                                rs.getString("contact"),
                                rs.getString(21),      //Done
                                rs.getString("photo"),
                                rs.getInt("rating"),
                                rs.getString("description"),
                                rs.getInt("consultation_fee")
                            ),
                            rs.getTime("opening_time"),
                            rs.getTime("closing_time"),
                            rs.getInt("max_appointment"),
                            rs.getString("shift_type")
                        ),
                        new Status(
                            rs.getInt("status_id"),
                            rs.getString(8)       //Done
                        ),
                        rs.getDate("current_date")
                    )
                );
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return appointments;
    }

    public ArrayList<Appointment> getAppointments(){
        ArrayList<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments as ap " +
                        "inner join statuses as sts ON ap.status_id = sts.status_id " + 
                        "inner join clinic_schedules as cs ON ap.schedule_id = cs.schedule_id " + 
                        "inner join clinics as c ON cs.clinic_id = c.clinic_id " + 
                        "inner join doctors as d on c.doctor_id = d.doctor_id " + 
                        "inner join users as u on d.user_id = u.user_id " +
                        "inner join specializations as s on d.specialization_id = s.specialization_id " +
                        "inner join cities as ci on c.city_id = ci.city_id " +
                        "inner join states as st on ci.state_id = st.state_id " + 
                        "inner join patients as p on ap.patient_id = p.patient_id " + 
                        "inner join users as userp on p.user_id = userp.user_id " + 
                        "WHERE ap.patient_id = ?";

        try{
            Class.forName(DBName);

            Connection con = DriverManager.getConnection(DBUrl, DBUser, DBPassword);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, patient.getPatientId());

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                appointments.add(
                    new Appointment(
                        rs.getInt("appointment_id"),
                        new Patient(
                            rs.getInt("patient_id"),
                            new User(
                                rs.getInt(60),
                                rs.getString(61),
                                rs.getDate(64),
                                rs.getString(67),
                                rs.getString("email"),
                                rs.getString("contact")
                            ),
                            rs.getString("gender"),
                            rs.getString("blood_group"),
                            rs.getFloat("weight"),
                            rs.getInt("height"),
                            rs.getString("uid")
                        ),
                        rs.getDate("appointment_date"),
                        new ClinicSchedule(
                            rs.getInt("schedule_id"),
                            new Clinic(
                                rs.getInt("clinic_id"),
                                new Doctor(
                                    rs.getInt("doctor_id"),
                                    new User(
                                        rs.getInt(35),
                                        rs.getString(36),   //Done
                                        rs.getDate(39),
                                        rs.getString(42),
                                        rs.getString("email"),
                                        rs.getString("contact")
                                    ),
                                    rs.getString("gender"),
                                    new Specialization(
                                        rs.getInt("specialization_id"),
                                        rs.getString(47) //Done
                                    ),
                                    rs.getInt("experience"),
                                    rs.getInt("star")
                                ),
                                rs.getString("address"),
                                new City(
                                    rs.getInt("city_id"),
                                    rs.getString(49),       //Done
                                    new State(
                                        rs.getInt("state_id"),
                                        rs.getString(52)  //Done
                                    )
                                ),
                                rs.getString("contact"),
                                rs.getString(21),      //Done
                                rs.getString("photo"),
                                rs.getInt("rating"),
                                rs.getString("description"),
                                rs.getInt("consultation_fee")
                            ),
                            rs.getTime("opening_time"),
                            rs.getTime("closing_time"),
                            rs.getInt("max_appointment"),
                            rs.getString("shift_type")
                        ),
                        new Status(
                            rs.getInt("status_id"),
                            rs.getString(8)       //Done
                        ),
                        rs.getDate("current_date")
                    )
                );
            }
            
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return appointments;
    }
    
    
    // ================================================SETTERS===================================================
    public void setAppointmentId(Integer appointmentId){
        this.appointmentId = appointmentId;
    }
    public void setPatient(Patient patient){
        this.patient = patient;
    }
    public void setClinicSchedule(ClinicSchedule clinicSchedule){
        this.clinicSchedule = clinicSchedule;
    }
    public void setStatus(Status status){
        this.status = status;
    }
    public void setAppointmentDate(Date appointmentDate){
        this.appointmentDate = appointmentDate;
    }
    public void setCurrentDate(Date currentDate){
        this.currentDate = currentDate;
    }
    
    // ================================================GETTERS===================================================
    public Integer getAppointmentId(){
        return appointmentId;
    }
    public Patient getPatient(){
        return patient;
    }
    public ClinicSchedule getClinicSchedule(){
        return clinicSchedule;
    }
    public Status getStatus(){
        return status;
    }
    public Date getAppointmentDate(){
        return appointmentDate;
    }
    public Date getCurrentDate(){
        return currentDate;
    }

}

/*
+------------------+------+------+-----+---------+----------------+
| Field            | Type | Null | Key | Default | Extra          |
+------------------+------+------+-----+---------+----------------+
| appointment_id   | int  | NO   | PRI | NULL    | auto_increment |
| patient_id       | int  | NO   | MUL | NULL    |                |
| appointment_date | date | NO   |     | NULL    |                |
| clinic_shift_id  | int  | NO   | MUL | NULL    |                |
| status_id        | int  | NO   | MUL | NULL    |                |
+------------------+------+------+-----+---------+----------------+
 */