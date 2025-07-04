package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import models.Doctor;
import models.Patient;
import models.Appointment;
import models.City;
import models.Clinic;
import models.ClinicSchedule;
import models.ClinicShift;
import models.Day;
import models.Status;

@WebServlet({"/signupclinic.do", "/save_clinic_img.do", "/save_working_hours.do", "/get_schedules.do", "/save_schedules.do", "/delete_schedules.do", "/get_clinics.do", "/save_appointment.do"})
public class ClinicServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        String NextPage = "error.do";

        if(uri.equals("/get_schedules.do")){
            HttpSession session = request.getSession();
            Doctor doctor = (Doctor)session.getAttribute("doctor");
            String responseFromServlet = "expired";
            Integer ClinicId = Integer.parseInt(request.getParameter("clinicId"));

            if(doctor != null){
                ClinicSchedule clinicSchedule = new ClinicSchedule(new Clinic(ClinicId));

                ArrayList<ClinicSchedule> clinicSchedules = clinicSchedule.getClinicSchedules();

                if(clinicSchedules.size() > 0){
                    Gson gson = new Gson();
                    responseFromServlet = gson.toJson(clinicSchedules);
                } else{
                    responseFromServlet = "No record Found";
                }
            }
            response.getWriter().write(responseFromServlet);
            return;
        } else if(uri.equals("/get_clinics.do")){
            HttpSession session = request.getSession();
            String responseFromServer = "Expired";
            Doctor doctor = (Doctor)session.getAttribute("doctor");
            Patient patient = (Patient)session.getAttribute("patient");

            if(doctor != null || patient != null){
                Integer DoctorId = Integer.parseInt(request.getParameter("doctorId"));
                Clinic clinic = new Clinic();
                ArrayList<Clinic> clinics = clinic.getClinics(new Doctor(DoctorId));

                Gson gson = new Gson();
                responseFromServer = gson.toJson(clinics);
            }
            response.getWriter().write(responseFromServer);
            return;
        }

        request.getRequestDispatcher(NextPage).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        String NextPage = "error.do";

        if(uri.equals("/signupclinic.do")){
            HttpSession session = request.getSession();

            Doctor doctor = (Doctor)session.getAttribute("doctor");

            if(doctor != null){
                String cityId = request.getParameter("cityId");
                String contact = request.getParameter("contact");
                String address = request.getParameter("address");
                String fees = request.getParameter("fees");
                String name = request.getParameter("name");
                String closedDay = request.getParameter("closedDay");
                String certifications = request.getParameter("certifications");
                String photoName = request.getParameter("photoName");
                String description = request.getParameter("description");
    
                Clinic clinic = new Clinic(doctor, address, new City(Integer.parseInt(cityId)), contact, Integer.parseInt(fees), name, closedDay, certifications, photoName, description);
                Boolean res = clinic.insertData();
                response.getWriter().print(res);
                return;
            }
        } else if(uri.equals("/save_clinic_img.do")){
            HttpSession session = request.getSession();
            Doctor doctor = (Doctor)session.getAttribute("doctor");
    
            String pth = "ERROR";
            if(doctor != null){
                if (ServletFileUpload.isMultipartContent(request)) {
                    try {
                        List<FileItem> fis = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                        
                        for (FileItem fi : fis) {
                            if (!fi.isFormField()) {                
                                // Define the directory path
                                String path = getServletContext().getRealPath("/WEB-INF/uploads" + File.separator + doctor.getUser().getUserId() + File.separator + "clinics");
                                File file = new File(path);
                                if (!file.exists())
                                    file.mkdirs();
                                String fileName = new java.util.Date().getTime() + fi.getName();
                                File f = new File(file, fileName);
                
                                fi.write(f);
                
                                pth = fileName;
                            }
                        }
                    } catch (FileUploadException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
            }
            response.getWriter().write(pth);
            return;
        } else if(uri.equals("/save_working_hours.do")){
            HttpSession session = request.getSession();
            Doctor doctor = (Doctor)session.getAttribute("doctor");
            String responseFromServlet = "expired";

            if(doctor != null){                
                ArrayList<ClinicShift> clinicshifts = new ArrayList<>();

                for(int i = 0; i < 7; i++){
                    switch (i) {
                        case 0:
                            if(request.getParameter("mondays") != "" && request.getParameter("mondaye") != ""){
                                clinicshifts.add(new ClinicShift(java.sql.Time.valueOf(request.getParameter("mondays") + ":00"), java.sql.Time.valueOf(request.getParameter("mondaye") + ":00"), Integer.parseInt(request.getParameter("mx_appoinments")), new Day(i+2)));
                            }
                            break;
                        case 1:
                            if(request.getParameter("tuesdays") != "" && request.getParameter("tuesdaye") != ""){
                                clinicshifts.add(new ClinicShift(java.sql.Time.valueOf(request.getParameter("tuesdays") + ":00"), java.sql.Time.valueOf(request.getParameter("tuesdaye") + ":00"), Integer.parseInt(request.getParameter("mx_appoinments")), new Day(i+2)));
                            }
                            break;
                        case 2:
                            if(request.getParameter("wednesdays") != "" && request.getParameter("wednesdaye") != ""){
                                clinicshifts.add(new ClinicShift(java.sql.Time.valueOf(request.getParameter("wednesdays") + ":00"), java.sql.Time.valueOf(request.getParameter("wednesdaye") + ":00"), Integer.parseInt(request.getParameter("mx_appoinments")), new Day(i+2)));
                            }
                            break;
                        case 3:
                            if(request.getParameter("thursdays") != "" && request.getParameter("thursdaye") != ""){
                                clinicshifts.add(new ClinicShift(java.sql.Time.valueOf(request.getParameter("thursdays") + ":00"), java.sql.Time.valueOf(request.getParameter("thursdaye") + ":00"), Integer.parseInt(request.getParameter("mx_appoinments")), new Day(i+2)));
                            }
                            break;
                        case 4:
                            if(request.getParameter("fridays") != "" && request.getParameter("fridaye") != ""){
                                clinicshifts.add(new ClinicShift(java.sql.Time.valueOf(request.getParameter("fridays") + ":00"), java.sql.Time.valueOf(request.getParameter("fridaye") + ":00"), Integer.parseInt(request.getParameter("mx_appoinments")), new Day(i+2)));
                            }
                            break;
                        case 5:
                            if(request.getParameter("saturdays") != "" && request.getParameter("saturdaye") != ""){
                                clinicshifts.add(new ClinicShift(java.sql.Time.valueOf(request.getParameter("saturdays") + ":00"), java.sql.Time.valueOf(request.getParameter("saturdaye") + ":00"), Integer.parseInt(request.getParameter("mx_appoinments")), new Day(i+2)));
                            }
                            break;
                        case 6:
                            if(request.getParameter("sundays") != "" && request.getParameter("sundaye") != ""){
                                clinicshifts.add(new ClinicShift(java.sql.Time.valueOf(request.getParameter("sundays") + ":00"), java.sql.Time.valueOf(request.getParameter("sundaye") + ":00"), Integer.parseInt(request.getParameter("mx_appoinments")), new Day(1)));
                            }
                            break;
                    }
                }
                ClinicShift clinicShift  = new ClinicShift(new Clinic(Integer.parseInt(request.getParameter("clinic_id"))), clinicshifts);
                if(clinicShift.insertData()){
                    responseFromServlet = "Done";
                } else{
                    responseFromServlet = "Problem in inserting data";
                }

                // for(int i = 0; i < 7; i++){
                //     switch (i) {
                //         case 0:
                //             System.out.println(request.getParameter("clinic_id") + " " + mondays + " - " + mondaye + " with " + request.getParameter("mx_appoinments"));
                //             break;
                //         case 1:
                //             System.out.println(request.getParameter("clinic_id") + " " + request.getParameter("tuesdays") + " - " + request.getParameter("tuesdaye") + " with " + request.getParameter("mx_appoinments"));
                //             break;
                //         case 2:
                //             System.out.println(request.getParameter("clinic_id") + " " + request.getParameter("wednesdays") + " - " + request.getParameter("wednesdaye") + " with " + request.getParameter("mx_appoinments"));
                //             break;
                //         case 3:
                //             System.out.println(request.getParameter("clinic_id") + " " + request.getParameter("thursdays") + " - " + request.getParameter("thursdaye") + " with " + request.getParameter("mx_appoinments"));
                //             break;
                //         case 4:
                //             System.out.println(request.getParameter("clinic_id") + " " + request.getParameter("fridays") + " - " + request.getParameter("fridaye") + " with " + request.getParameter("mx_appoinments"));
                //             break;
                //         case 5:
                //             System.out.println(request.getParameter("clinic_id") + " " + request.getParameter("saturdays") + " - " + request.getParameter("saturdaye") + " with " + request.getParameter("mx_appoinments"));
                //             break;
                //         case 6:
                //             System.out.println(request.getParameter("clinic_id") + " " + request.getParameter("sundays") + " - " + request.getParameter("sundaye") + " with " + request.getParameter("mx_appoinments"));
                //             break;
                //     }

                // }

            }
            
            response.getWriter().write(responseFromServlet);
            return;
        } else if(uri.equals("/save_schedules.do")){
            HttpSession session = request.getSession();
            Doctor doctor = (Doctor)session.getAttribute("doctor");
            String responseFromServlet = "expired";
            
            if(doctor != null){
                ClinicSchedule clinicSchedule = new ClinicSchedule(new Clinic(Integer.parseInt(request.getParameter("clinicId"))), java.sql.Time.valueOf(request.getParameter("openingTime") + ":00"), java.sql.Time.valueOf(request.getParameter("closingTime") + ":00"), Integer.parseInt(request.getParameter("maxAppointment")), request.getParameter("shiftType"));
                if(clinicSchedule.saveData()){
                    responseFromServlet = "saved";
                } else{
                    responseFromServlet = "Error in saving";
                }
            }

            response.getWriter().write(responseFromServlet);
            return;
        } else if(uri.equals("/delete_schedules.do")){
            HttpSession session = request.getSession();
            Doctor doctor = (Doctor)session.getAttribute("doctor");
            String responseFromServlet = "expired";

            if(doctor != null){
                ClinicSchedule clinicSchedule = new ClinicSchedule(Integer.parseInt(request.getParameter("clinicScheduleId")));
                if(clinicSchedule.deleteSchedule()){
                    responseFromServlet = "Done";
                } else{
                    responseFromServlet = "Error in deletion";
                }
            }
            response.getWriter().write(responseFromServlet);
            return;
        } else if(uri.equals("/save_appointment.do")){
            HttpSession session = request.getSession();
            Patient patient = (Patient)session.getAttribute("patient");
            String responseFromServer = "expired";

            if(patient != null){
                System.out.println("Appointment Date: " + request.getParameter("AppointmentDate"));
                System.out.println("Selected Schedule: " + request.getParameter("selectedSchedule"));
                System.out.println("Patient Id: " + patient.getPatientId());
                System.out.println("Current Date: " + java.time.LocalDate.now());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                Appointment appointment = new Appointment(Date.valueOf(LocalDate.parse(request.getParameter("AppointmentDate"), formatter)), Date.valueOf(LocalDate.now()), new Patient(patient.getPatientId()), new ClinicSchedule(Integer.parseInt(request.getParameter("selectedSchedule"))), new Status(4));
                if(appointment.saveAppointment()){
                    responseFromServer = "saved";
                } else{
                    responseFromServer = "Unable to save appointments";
                }
            }

            response.getWriter().write(responseFromServer);
            return;
        }

        
        response.sendRedirect(NextPage);
    }
}
