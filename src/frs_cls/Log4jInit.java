package frs_cls;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class Log4jInit
 */
public class Log4jInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Log4jInit() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init()
    {
        String prefix =  getServletContext().getRealPath("/");
        String file = getInitParameter("log4jfile");
     
        // if the log4j-init-file context parameter is not set, then no point in trying
        if(file != null){
         PropertyConfigurator.configure(prefix+file);
         System.out.println("Log4J Logging started: " + prefix+file);
        }
        else{
         System.out.println("Log4J Is not configured for your Application: " + prefix + file);
        }    
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
