package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Clinic;
import models.Doctor;
import models.Medicine;
import models.Patient;
import models.PharmaCompany;
import models.User;
 
@WebServlet({"/activate.do","/dashboard.do"})
public class DashboardServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        HttpSession session = request.getSession();
        if(uri.equals("/activate.do")){
            if(session.getAttribute("user") != null){
                response.sendRedirect("dashboard.do");
            } else {
                response.sendRedirect("error1.do");
            }
        } else if(uri.equals("/dashboard.do")){
            User user = (User)session.getAttribute("user");
            if(user != null){
                if(user.getUserType().getUserTypeId() == 1){ 
                    // Doctor 
                    Doctor doctor = new Doctor();
                    Clinic clinic = new Clinic();
                    if(doctor.checkDoctor(user.getUserId())){
                        doctor.setValues(user); 
                        ArrayList<Clinic> clinics = clinic.getClinics(doctor);
                        session.setAttribute("doctor", doctor);
                        session.setAttribute("clinics", clinics);
                    }
                    request.getRequestDispatcher("static/pages/dashboard.jsp").forward(request, response);
                } else if(user.getUserType().getUserTypeId() == 2){
                    // Patient 
                    Patient patient = new Patient();
                    if(patient.checkPatient(user.getUserId())){
                        patient.setValues(user);
                        session.setAttribute("patient", patient);
                    }

                    request.getRequestDispatcher("static/pages/dashboard.jsp").forward(request, response);
                } else if(user.getUserType().getUserTypeId() == 3){
                    // Pharmacy  
                    PharmaCompany pharmaCompany = new PharmaCompany();
                    if(pharmaCompany.checkPharmaCompany(user.getUserId())){
                        pharmaCompany.setValues(user);
                        Medicine medicine = new Medicine();
                        ArrayList<Medicine> myMedicines = medicine.collectMyMedicines(pharmaCompany);
                        session.setAttribute("myMedicines", myMedicines);
                        session.setAttribute("pharmaCompany", pharmaCompany);
                    }

                    request.getRequestDispatcher("static/pages/dashboard.jsp").forward(request, response);
                } else{
                    request.getRequestDispatcher("userTypeNOtFound.jsp").forward(request, response);
                }
            } else{
                response.sendRedirect("error2.do");
            }
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        HttpSession session = request.getSession();
        if(uri.equals("/activate.do")){
            if(session.getAttribute("user") != null){
                response.sendRedirect("SuccessAfterActivation.do");
            } else{
                response.sendRedirect("error12.do");
            }
        }
    }
}
