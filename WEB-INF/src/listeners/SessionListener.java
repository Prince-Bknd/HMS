package listeners;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;

@WebListener
public class SessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("Session created, So this session also called");
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("Session Destroyed, so listener for destroy also called");
    }
}