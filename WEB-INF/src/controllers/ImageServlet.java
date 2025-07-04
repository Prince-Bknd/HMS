package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/getImage.do"})
public class ImageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String userId = request.getParameter("userId");
        String img = request.getParameter("img");
        String path = "/WEB-INF/uploads/" + userId;
        if(img != null){
            path += "/";
            path += img;
        }

        InputStream is = getServletContext().getResourceAsStream(path);
        OutputStream os = response.getOutputStream();

        byte []b = new byte[1024];

        while(is.read(b) != -1){
            os.write(b);
        }

        os.flush();
        os.close();
    }
    
}
