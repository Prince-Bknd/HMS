package controllers;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;

@WebServlet({"/activationcheck.do", "/activation.do","/activationstart.do", "/verify.do"})
public class ActivationServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        // String NextPage = "error.do";  
        
        if (uri.equals("/activationcheck.do")) {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            int statusId = user.getStatus().getStatusId();
            if(statusId == 2){              
                response.getWriter().write("0");
                return;
            } else if(statusId == 1) {
                response.getWriter().write("Done");
                return;
            } else{
                response.getWriter().write("Error");
                return;
            }
        } else if(uri.equals("/activation.do")){
            // response.getWriter().write("");
            // return;
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        if (uri.equals("/activationstart.do")){
            String owner = "princeku.pk1212@gmail.com";
            String password = "dxnxslioocihpxnl";

            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication(){
                    return new javax.mail.PasswordAuthentication(owner, password);
                } 
            });

            MimeMessage mm = new MimeMessage(session);
            try{
                mm.setFrom(owner);
                mm.addRecipients(Message.RecipientType.TO, request.getParameter("email"));
                mm.setSubject("OTP for Verification");
                String link = request.getRequestURL().toString();       //Returns StringBuffer!!!
                link = link.substring(0, link.length() - 9) + "/verify.do?email=" + request.getParameter("email") + 
                "&code=121212";
                System.out.println(link);
                String obj = "Your OTP is <b>121232</b><br><br>Click here to verify: <a href='" + 
                link + 
                "'>link</a>";
                mm.setText(obj , "utf-8", "html");


                Transport.send(mm);
            }catch(MessagingException e){
                e.printStackTrace();
            }
        } else if (uri.equals("/verify.do")){
            System.out.println("FINALLY HERE!!");
        }
    }
}
