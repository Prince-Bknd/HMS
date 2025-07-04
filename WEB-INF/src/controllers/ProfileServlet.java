package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/getdoctorCertificate.do", "/getPharmaLicense.do"})
public class ProfileServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        if(uri.equals("/getdoctorCertificate.do")){
            PrintWriter pw = response.getWriter();
            pw.write("<h1>Avialable soon</h1>");

            pw.flush();
            pw.close();
        } else if(uri.equals("/getPharmaLicense.do")){
            PrintWriter pw = response.getWriter();
            pw.write("<h1>Avialable soon, working to fetch Pharmacy License</h1>");

            pw.flush();
            pw.close();

        }
    }
}
