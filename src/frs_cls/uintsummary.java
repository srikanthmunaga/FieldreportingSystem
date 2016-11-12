package frs_cls;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Servlet implementation class uintsummary
 */
public class uintsummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = null;
	private JdbcConnect jc=new JdbcConnect();
    private Connection con;  
    private Logger log = Logger.getLogger(uintsummary.class);
    


    
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uintsummary() {
        super();
        // TODO Auto-generated constructor stub
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
		//Below five lines code is to session checking & login redirect
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else
				{
		String format=request.getParameter("format");
		String fromdate=request.getParameter("fdate");
		String todate=request.getParameter("todate");
		//String BSFLUNIT_NAME=request.getParameter("uname");
		/*try {
			if(format.equals("Generate PDF Report"))
			{
			//PdfReports pr=new PdfReports();
			//pr.unitsummaryreport(request, response, fromdate, todate);
			}else if(format.equals("Generate XLS Report"))
			{
				xlsreports xr=new xlsreports();
				xr.unitWiseProductWiseSummeryXLS(response,fromdate,todate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("",e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.warn("",e);
			e.printStackTrace();
		}*/
	}
	}	
}
