package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.util.ArrayList;

import models.Specialization;
import models.Unit;
import models.City;
import models.Format;

@WebListener
public class ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent ev) {
        ServletContext context = ev.getServletContext();

        // Collects all specializations
        ArrayList<Specialization> specializations = Specialization.getSpecializations();
        context.setAttribute("specializations", specializations);

        // COllects all cities
        ArrayList<City> cities = City.getCities();
        context.setAttribute("cities", cities);

        // Collects all Medicine's Format
        ArrayList<Format> formats = Format.getFormats();
        context.setAttribute("formats", formats);

        // Colletes all Units
        ArrayList<Unit> units = Unit.getUnits();
        context.setAttribute("units", units);
    }
    
    public void contextDestroyed(ServletContextEvent ev) {
        System.out.println("Context Destroyed");
        
    }
}