package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Appointment;
import models.Doctor;

import com.google.gson.Gson;

@WebServlet({"/fetch_diagonised_appointments.do", "/fetch_undiagonised_appointments.do", "/fetch_all_appointments.do"})
public class DoctorServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        String servletResponse = "error.do";
        String responseFromServer = "expired";
        HttpSession session = request.getSession();

        if(uri.equals("/fetch_diagonised_appointments.do")){
            Doctor doctor = (Doctor)session.getAttribute("doctor");
            Integer doctorId = Integer.parseInt(request.getParameter("doctorId"));
            if(doctor != null){
                Appointment appointment = new Appointment();
                ArrayList<Appointment> appointments = appointment.getDiagonisedAppointments(doctorId);

                Gson gson = new Gson();
                responseFromServer = gson.toJson(appointments);
            }

            response.getWriter().write(responseFromServer);
            return;
        } else if(uri.equals("/fetch_undiagonised_appointments.do")){
            Doctor doctor = (Doctor)session.getAttribute("doctor");
            Integer doctorId = Integer.parseInt(request.getParameter("doctorId"));
            if(doctor != null){
                Appointment appointment = new Appointment();
                ArrayList<Appointment> appointments = appointment.getUndiagonisedAppointments(doctorId);

                Gson gson = new Gson();
                responseFromServer = gson.toJson(appointments);
            }

            response.getWriter().write(responseFromServer);
            return;
        } else if(uri.equals("/fetch_all_appointments.do")){
            Doctor doctor = (Doctor)session.getAttribute("doctor");
            Integer doctorId = Integer.parseInt(request.getParameter("doctorId"));
            if(doctor != null){
                Appointment appointment = new Appointment();
                ArrayList<Appointment> appointments = appointment.getAllAppointments(doctorId);

                Gson gson = new Gson();
                responseFromServer = gson.toJson(appointments);
            }

            response.getWriter().write(responseFromServer);
            return;
        }

        request.getRequestDispatcher(servletResponse).forward(request, response);
    }
    
}
