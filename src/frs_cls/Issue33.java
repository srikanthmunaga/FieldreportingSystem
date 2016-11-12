package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import smscls.SMSreceiver;
/**
	 * Servlet implementation class RDsubmit
 */
public class Issue33 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	java.sql.Date sd1;
	Connection con=null;
	private Logger log = Logger.getLogger("LOGFILE");
	
	JdbcConnect js=new JdbcConnect();
	
    public Issue33() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else
				{
		////System.out.println("inside the doPost() of RDsubmit.java file");
		//System.out.println("request object = "+request+" response object is = "+response);
		String unit_id=request.getParameter("bsflunit_ucode");
		String unit_name=request.getParameter("unit_name");
		String st_id=request.getParameter("st_id");
		String st_name=request.getParameter("st_name");
		String last_issued=request.getParameter("last_issued");
		String closing_stock=request.getParameter("closing_stock");
		String available_stock=request.getParameter("available_stock");
		String requested_stock=request.getParameter("req_stock");
		String date_of_indent=request.getParameter("d_oi");
		String indent_by=request.getParameter("ind_by");
		String issue_stock=request.getParameter("issue_stock");
		String date_of_issue=request.getParameter("date_is");
		String cou_id=request.getParameter("cou_id");
		String cou_name=request.getParameter("cou_name");
		//System.out.println("Collecting all te values");
		
		
		
		RequestDispatcher successrd=getServletContext().getRequestDispatcher("/Mail");
		
		////System.out.println("Msr debug 1");
		RequestDispatcher failrd=getServletContext().getRequestDispatcher("/fail.jsp");
		//RequestDispatcher failuserrd=getServletContext().getRequestDispatcher("/failrdsubmit.jsp");
		////System.out.println("Msr debug 2"+failrd);

		//System.out.println("Before executing the query");
			
		//String sql=();
		
/*		String sql="insert into FRS_RECOVERY " +"" +
				"(huma_id ,FRS_VILLAGE_COUNT ,FRS_CUST_COUNT ,FRS_TOTAL_AMT ,FRS_TOTAL_ACCOUNTS ,FRS_OD_AMT ,FRS_OD_ACCOUNTS)"+
				"values(?,?,?,?,?,?,?)";*/
		try {
			con=js.getConnection();
			System.out.println("connection sucesfull");
			////log.info("JDBC Connection was created");
			////System.out.println("Msr debug 3");
			//con.setAutoCommit(false);
			PreparedStatement ps=con.prepareStatement("insert into issue_indent values(?,?,?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'),?,?,to_date(?,'dd-mm-yyyy'),?,?)");
			
			ps.setString(1,unit_id);
			ps.setString(2,unit_name);
			ps.setString(3,st_id);
			ps.setString(4,st_name);
			ps.setString(5,last_issued);
			ps.setString(6, closing_stock);
			ps.setString(7,available_stock);
			ps.setString(8,requested_stock);
			ps.setString(9,date_of_indent);
			ps.setString(10,indent_by);
			ps.setString(11,issue_stock);
			ps.setString(12,date_of_issue);
			ps.setString(13,cou_id);//sender_number
			ps.setString(14,cou_name);//sent_dt	
			
			boolean status=ps.execute();
			System.out.println(status);
			PreparedStatement ps1=con.prepareStatement("update stockindent set status='Y' where bsflunit_ucode='"+unit_id+"' and s_id='"+st_id+"'" );
			int f=ps1.executeUpdate();
			System.out.println(f);
			System.out.println("updated succesfully");

			
			
			
		//	ps.setInt(12,FRS_SDRCUST_COUNT);
			////System.out.println("Msr debug 4");
			if(status!=true){
					
				HttpSession ses=request.getSession(false);
				String userrole=(String)ses.getAttribute("userrole");
				if(userrole.equals("admin") || userrole.equals("areahead") || userrole.equals("unithead"))
				{
					System.out.println("ONE RECOERD WAS INSERTED from admin user" );
					log.info("Admin user submitted Details");
//					if(f>0){
						successrd.include(request, response);
					}
				
				
				
					
				
				
				//below lines code for sendTargetVsAchievementSMS starts----------------------  
				
			//Successful insertion of records
			
			ps.close();
			//ps1.close();
		}
			
		}catch (SQLException e) {
			//log.error("please cjheck :",e);
			//System.out.println("Debug MSR 7");
			failrd.include(request, response);
			//e.printStackTrace();
			
			////System.out.println("Msr debug after include 8"+failrd);
//			e.getMessage();//printStackTrace();
		} catch (Exception e) {
			log.warn(e);
			e.printStackTrace();
		} finally 
		{ 
			 if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		
		
		
		
	}

				}
			 //request.getRequestDispatcher("/Mail").forward(request,response);
	}}
