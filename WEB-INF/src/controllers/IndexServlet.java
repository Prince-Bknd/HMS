package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/index.do", "/home.do"})
public class IndexServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String uri = request.getServletPath();
        if(uri.equals("/home.do")){
            request.getRequestDispatcher("hmsHome.html").forward(request, response);
        }else if(uri.equals("/index.do")){
            String sendingTo = "static/pages/index.jsp";
            String parameter = request.getParameter("signup");
            if(parameter != null){
                sendingTo += "?signup=" + parameter;
            }
            request.getRequestDispatcher(sendingTo).forward(request, response);
        }else{
            PrintWriter pw = response.getWriter();
            pw.write("Some error occur while loading HOME page");
            request.getRequestDispatcher("test.html").forward(request, response);
        }
    }
}
