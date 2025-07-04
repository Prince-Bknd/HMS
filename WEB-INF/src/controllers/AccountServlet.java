package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import org.jasypt.util.password.StrongPasswordEncryptor;

import models.Doctor;
import models.Patient;
import models.PharmaCompany;
import models.Specialization;
import models.User;
import models.UserType;

@WebServlet({"/signin.do", "/signup.do", "/signup1.do", "/signin1.do", "/signinCheck.do", "/logout.do", "/signupDoc.do", "/signupPhar.do", "/signupPat.do"})
public class AccountServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        String NextPage = "error.do";

        if (uri.equals("/signin1.do")) {
            NextPage = "success.do";
        } else if(uri.equals("/signinCheck.do")){
            StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = new User(email);
            int test = user.verifyUser();
            if(test == 1){
                if(spe.checkPassword(password, user.getPassword())){
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    response.getWriter().write("verified");
                } else {
                    response.getWriter().write("unverified");
                }
            } else {
                response.getWriter().write("asdf");
            }
            return;
        } else if(uri.equals("/logout.do")){
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("index.do");
            return;
        } else if (uri.equals("/signup.do")){
            NextPage = "static/pages/signing.jsp";
        } else if (uri.equals("/signin.do")){
            NextPage = "static/pages/signing.jsp";
            request.setAttribute("signin", true);
        }
        
        request.getRequestDispatcher(NextPage).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        String NextPage = "error.do";

        User user;
        
        if(uri.equals("/signup1.do")){
            String name = request.getParameter("suname");
            String email = request.getParameter("suemail");
            String password = request.getParameter("supassword");
            String contact = request.getParameter("sucontact");
            Integer sutype = Integer.parseInt(request.getParameter("sutype"));

            user = new User(name, email, password, contact, new UserType(sutype));

            if(user.signupSuccess()){
                NextPage = "index.do?signup=true";
            } else{
                NextPage = "index.do?signup=false";
            }

        } else if(uri.equals("/signupDoc.do")){
            HttpSession session = request.getSession();
            String certificate = null;
            Integer experience = null;
            Integer specializationId = null;
            String gender = null;
            user = (User)session.getAttribute("user");
            if(ServletFileUpload.isMultipartContent(request)){
                try{
                    List<FileItem> fis = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    for(FileItem fi : fis){
                        if(!fi.isFormField()){
                            String path = getServletContext().getRealPath("/WEB-INF/uploads/");
                            String compPath = path + File.separator + user.getUserId();
                            File file = new File(compPath);
                            if(!file.exists()){
                                file.mkdir();
                            }
                            File f = new File(compPath, "certificate.png");
                            try{
                                fi.write(f);
                            } catch(Exception e){
                                e.printStackTrace();
                            }
                        } else{
                            String fname = fi.getFieldName();
                            String fvalue = fi.getString();

                            if(fname.equals("certificate")){
                                certificate =  fvalue;
                            } else if(fname.equals("experience")){
                                experience = Integer.parseInt(fvalue);
                            } else if(fname.equals("specialization")){
                                specializationId = Integer.parseInt(fvalue);
                            } else if(fname.equals("gender")){
                                gender = fvalue;
                            }
                        }
                    }
                } catch(FileUploadException e){
                    e.printStackTrace();
                }
            }
            
            System.out.println(certificate);
            Doctor doctor = new Doctor(certificate, experience, new Specialization(specializationId), gender);
            
            if(doctor.insertData(user)){
                session.setAttribute("doctor", doctor);
                NextPage = "dashboard.do";
            } else{
                NextPage = "doctor_not_inserted.do";
            }

            // NextPage = "dashboard.do";
        } else if(uri.equals("/signupPhar.do")){
            HttpSession session = request.getSession();
            String license = null;
            String detail = null;

            user = (User)session.getAttribute("user");

            if(ServletFileUpload.isMultipartContent(request)){

                try{
                    List<FileItem> fis = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                    for(FileItem fi: fis){
                        if(!fi.isFormField()){
                            String path = getServletContext().getRealPath("/WEB-INF/uploads/");
                            String compPath = path + File.separator + user.getUserId();
                            File file = new File(compPath);
                            if(!file.exists()){
                                file.mkdir();
                            }
                            File f = new File(compPath, "certificate.png");
                            try{
                                fi.write(f);
                            } catch(Exception e){
                                e.printStackTrace();
                            }
                        } else {
                            String fname = fi.getFieldName();
                            String fvalue = fi.getString();

                            if(fname.equals("license")){
                                license =  fvalue;
                            } else if(fname.equals("detail")){
                                detail = fvalue;
                            }
                        }
                    }
                } catch(FileUploadException e){
                    e.printStackTrace();
                }
            }

            PharmaCompany pharmaCompany = new PharmaCompany(detail, license, user);

            if(pharmaCompany.insertData(user)){
                session.setAttribute("pharmaCompany", pharmaCompany);
                NextPage = "dashboard.do";
            } else{
                NextPage = "doctor_not_inserted.do";
            }
        } else if(uri.equals("/signupPat.do")){
            String aadhar = request.getParameter("aadhar");
            Integer height = 0;
            if(request.getParameter("height") == ""){
                try{
                    height = Integer.parseInt(request.getParameter("height"));
                } catch(NumberFormatException e){
                    System.out.println("Height is missing");
                }
            }
            Float weight = 0f;
            if(request.getParameter("weight") == ""){
                try{
                    weight = Float.parseFloat(request.getParameter("weight"));
                } catch(NumberFormatException e){
                    System.out.println("Weight is missing");
                }
            }
            String gender = request.getParameter("gender");
            String blood = request.getParameter("blood");
            HttpSession session = request.getSession();
            user = (User)session.getAttribute("user");

            Patient patient = new Patient(gender, blood, weight, height, aadhar);
            if(patient.insertData(user)){
                NextPage = "dashboard.do";
            } else{
                NextPage = "PatientNotInserted.jsp";
            }
        }

        response.sendRedirect(NextPage);
    }

}
