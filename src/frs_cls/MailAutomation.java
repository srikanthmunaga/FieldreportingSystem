package frs_cls;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



/**
 * Application Lifecycle Listener implementation class MailAutomation
 *
 */
public class MailAutomation implements ServletContextListener {

    /**
     * Default constructor.  
     */
	MailToStatehead m=new MailToStatehead();
    public MailAutomation() {
        // TODO Auto-generated constructor stub
    }
 
	/** 
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	try {
    		System.out.println("STARTING SERVLET LISTENER MSR DEBUG");
    		Timer time = new Timer(); // Instantiate Timer Object
    		Calendar c=Calendar.getInstance();
    	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    	    String CurrentDate = sdf.format(c.getTime());
    	    System.out.println(CurrentDate);
    	    Calendar c1=Calendar.getInstance();
    	    c1.add(Calendar.DATE,7);
    	    String PreviousDate=sdf.format(c1.getTime());
    	    System.out.println(PreviousDate);
    	    time.schedule(m, c.getTime(),c1.getTimeInMillis());
    	    System.out.println("COMPLETING SERVLET LISTENER EXECUTION MSR DEBUG");
    		

		} catch (Exception e) {
			// TODO: handle exception
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
