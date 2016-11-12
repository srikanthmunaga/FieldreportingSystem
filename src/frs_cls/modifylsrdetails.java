package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class modifylsrdetails
 */
public class modifylsrdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	private String FRS_date;
	private String huma_id;
	private int FRS_village_count;
	private int FRS_Cust_count;
	private int FRS_total_amt;
	private int FRS_total_accounts;
	private int FRS_od_amt;
	private int FRS_od_accounts;
	private int FRS_SDRCUST_COUNT;
	java.sql.Date sd1;
	private Logger log = Logger.getLogger("LOGFILE");

	

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifylsrdetails() {
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
		
		FRS_Cust_count=Integer.parseInt(request.getParameter("FRS_Cust_count"));
		FRS_date=request.getParameter("FRS_date");
		FRS_total_amt=Integer.parseInt(request.getParameter("FRS_total_amt"));
		huma_id=request.getParameter("huma_id");
		FRS_village_count=Integer.parseInt(request.getParameter("FRS_village_count"));
		FRS_total_accounts=Integer.parseInt(request.getParameter("FRS_total_accounts"));
		FRS_od_amt=Integer.parseInt(request.getParameter("FRS_od_amt"));
		FRS_od_accounts=Integer.parseInt(request.getParameter("FRS_od_accounts"));
		FRS_SDRCUST_COUNT=Integer.parseInt(request.getParameter("FRS_SDRCUST_COUNT"));
		RequestDispatcher successrd=getServletContext().getRequestDispatcher("/persist.jsp");
		//RequestDispatcher usersuccerd=getServletContext().getRequestDispatcher("/userpersist.jsp");
		RequestDispatcher failrd=getServletContext().getRequestDispatcher("/failrdsubmit.jsp");

		String sql="UPDATE FRS_RECOVERY SET FRS_VILLAGE_COUNT=?, FRS_CUST_COUNT=?, FRS_TOTAL_AMT=?, " +
				"FRS_TOTAL_ACCOUNTS=?, FRS_OD_AMT=?, FRS_OD_ACCOUNTS=?, FRS_DATE=to_date(?,'dd-mm-yyyy')," +
				"frs_mdate=sysdate,frs_mby=?,FRS_SDRCUST_COUNT=? WHERE FRS_DATE=to_date(?,'dd-mm-yyyy') and huma_id =?";
		try {
			Connection con=jc.getConnection();
			////log.info("JDBC Connection was created");
			PreparedStatement ps=con.prepareStatement(sql);
			//System.out.println("after jdbc connection and frsdate="+(String)(request.getSession().getAttribute("frsdate")));
			
			ps.setInt(1,FRS_village_count);
			ps.setInt(2,FRS_Cust_count);
			ps.setInt(3,FRS_total_amt);
			ps.setInt(4,FRS_total_accounts);
			ps.setInt(5,FRS_od_amt);
			ps.setInt(6,FRS_od_accounts);
			ps.setString(7,FRS_date);
			//ps.setString(16,request.getParameter("user_freeze"));
			ps.setString(8,(String)(request.getSession().getAttribute("username")));//Added by Rajashekhar for mby field
			ps.setInt(9,FRS_SDRCUST_COUNT);
			ps.setString(10,(String)(request.getSession().getAttribute("frsdate")));//Added by Rajashekhar to get record old date
			ps.setString(11,huma_id);
			
			//System.out.println("before updating");
			ps.executeUpdate();
			//System.out.println("after updating");
			//System.out.println("ONE RECOERD WAS INSERTED" );
			con.commit();
			successrd.include(request, response);
			ps.close();
		}

		//} catch (ClassNotFoundException e) {
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("",e);
			failrd.include(request, response);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("",e);
			e.printStackTrace();
		}finally
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

}
}