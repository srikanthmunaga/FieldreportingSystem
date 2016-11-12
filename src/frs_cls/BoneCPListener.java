package frs_cls;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
 
public final class BoneCPListener implements ServletContextListener {
 
    public void contextInitialized(ServletContextEvent sce) {
    	//JdbcConnect.configureConnPool();
    }
 
    public void contextDestroyed(ServletContextEvent sce) {
    	//JdbcConnect.shutdownConnPool();
    }
 
}
