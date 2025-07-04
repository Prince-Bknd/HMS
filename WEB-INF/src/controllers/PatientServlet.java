package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import models.Appointment;
import models.Doctor;
import models.Patient;
import models.Specialization;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

@WebServlet({"/get_appointments.do", "/get_doctors.do"})
public class PatientServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        String responsePage = "index.do";

        if(uri.equals("/get_appointments.do")){
            HttpSession session = request.getSession();
            Patient patient = (Patient)session.getAttribute("patient");
            String serverResponse = "expired";

            if(patient != null){
                Appointment appointment = new Appointment(new Patient(Integer.parseInt(request.getParameter("patientId"))));
                ArrayList<Appointment> appointments = appointment.getAppointments();

                Gson gson = new Gson();
                serverResponse = gson.toJson(appointments);
            }

            response.getWriter().print(serverResponse);
            return;
        } else if(uri.equals("/get_doctors.do")){
            HttpSession session = request.getSession();
            Patient patient = (Patient)session.getAttribute("patient");
            String responseFromServer = "expired";

            if(patient != null){
                Integer specializationId = Integer.parseInt(request.getParameter("spId"));

                Doctor doctor = new Doctor(new Specialization(specializationId));
                ArrayList<Doctor> doctors = doctor.getDoctors();

                Gson gson = new Gson();
                if(doctors.size() != 0){
                    responseFromServer = gson.toJson(doctors);
                } else{
                    responseFromServer = "NO " + specializationId + " has doctor";
                }
            }

            response.getWriter().write(responseFromServer);
            return;
        }

        request.getRequestDispatcher(responsePage).forward(request, response);
    }
}
