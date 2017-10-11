package project;

import org.apache.log4j.PropertyConfigurator;
import project.external_systems.PhoneStation;
import project.external_systems.PhoneSystem;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

/**
 * This class implements {@link ServletContextListener} and runs on servlet deploying.
 * This class helps to initialize log4j library from log4j.properties file.
 * It also instantiates external system for generating phone calls.
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class ApplicationInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initLog4j(servletContextEvent);
        startExternalPhoneStation();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    /**
     * Runs phone system as daemon thread.
     * */
    private void startExternalPhoneStation() {
        PhoneSystem phoneSystem = new PhoneSystem();
        PhoneStation phoneStation = new PhoneStation();
        phoneStation.addPhoneSystemObserver(phoneSystem);

        Thread phoneSystemThread = new Thread(phoneStation);
        phoneSystemThread.setDaemon(true);
        phoneSystemThread.start();
    }

    private void initLog4j(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("/") + log4jConfigFile;
        PropertyConfigurator.configure(fullPath);
    }
}
