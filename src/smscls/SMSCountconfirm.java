package smscls;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import frs_cls.JdbcConnect;

/**
 * Servlet implementation class SMSCountconfirm
 */
public class SMSCountconfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SMSCountconfirm() {
        
    	
    	super();
        // TODO Auto-generated constructor stub
    }
	public JdbcConnect jc;
	public static String line = "";// comment it later
	private Log SMSTRACK = LogFactory.getLog("SMSTRACK");
	static BufferedWriter out;
	

    Connection con, conn;
  /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("Msr debug1");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession ses=request.getSession(false);
			////System.out.println("Msr debug 123");
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/SMStoODconform.jsp");
			//RequestDispatcher rd=getServletContext().getRequestDispatcher("/pleasewait.jsp");
			//System.out.println("Msr debug");
			String URL = "jdbc:oracle:thin:@172.16.1.56:1521:DSTAT";
			String USER = "DSTAT_NOV13";
			String PASS = "TATSD";
			//String URL = "jdbc:oracle:thin:@172.16.1.189:1521:srinu";
			//String USER = "STEMSLIVE";
			//String PASS = "STEMSLIVE";
			jc = new JdbcConnect();
			// Connection conn =null;
			Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			DriverManager.registerDriver(myDriver);
			conn = DriverManager.getConnection(URL, USER, PASS);
			// Query for other than AP, ORISSA
			// System.out.println("MSR @DEBUG1");
			String oddays=(String) ses.getAttribute("oddays");
			//System.out.println(request.getParameter("oddays"));
			//String huma_id[] = request.getParameterValues("huma_id");
			//System.out.println(huma_id.length);
			String areaList = (String) ses.getAttribute("areaList");
			String unitList = "''";
/*			int areacount=huma_id.length;
			for (String name : huma_id) {
				if (name != null)
					areaList += ",'" + name + "'";
			}	
*/			ses.setAttribute("oddays",oddays);
			ses.setAttribute("areaList", areaList);
			
			System.out.println("areaList="+areaList.trim());
			String sql3 = "select distinct BSFLUNIT_UCODE from BSFLUNIT_MSTR where area_id in(select area_id from area_mstr where area_id in ("
						+ areaList + ")) order by BSFLUNIT_UCODE";
				// conn.
				// Connection con;
				con = jc.getConnection();
				PreparedStatement ps3 = con.prepareStatement(sql3);
				ResultSet rs3 = ps3.executeQuery();
				int count=0;
				while (rs3.next()) {
					unitList += ",'" + rs3.getString(1) + "'";
					count++;
				}
				ses.setAttribute("unitList", unitList);
			System.out.println("unitList="+unitList);
			System.out.println("unitList count="+count);
			String sql = "select count(*) as total from SMS where PHONE_NO IS NOT NULL and ODDAYS >="+oddays+" AND UNIT_CODE IN ("+unitList+ ")";
			//String sql = "select count(*) as total from SMS where PHONE_NO IS NOT NULL and ODDAYS >="+oddays+" AND UNIT_CODE IN ("+unitList+ ")";
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ResultSet rs = null;
			int total=0;
			try {
				rs = ps1.executeQuery();
			} catch (Exception e) {
			}
			while (rs.next()) {
				total=rs.getInt("total");
			}// while
			System.out.println("total :"+total);
			ses.setAttribute("total",total);
			ses.setAttribute("unitcount",count);
			//ses.setAttribute("areacount",areacount);
			try {
				Thread.currentThread();
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally { /**/// //System.out.println("inside finally of post");
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	} 
	}
