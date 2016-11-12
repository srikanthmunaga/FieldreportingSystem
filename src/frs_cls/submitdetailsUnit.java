package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class submitdetails
 */
public class submitdetailsUnit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private JdbcConnect jc=new JdbcConnect();
	private lsrbean lsrdetails;
	private lsrbean lb;
	private recoverybean rb;
	private Logger log = Logger.getLogger(submitdetails.class);
	String date="";

	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public submitdetailsUnit() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Boolean checkhuma_id(String huma_id) throws ClassNotFoundException
    {	
    	String sql="select distinct huma_id from huma_mstr where huma_id='"+huma_id+"'";//select * from huma_mstr where huma_id='E2317'
    	con=jc.getConnection();
    	//log.info("JDBC Connection was created");
    	Boolean res=false;
    	try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				//if(huma_id.equals(rs.getString(1)))
				//{
				res=true;
				break;
				//}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn(e);
			//failrd.include(request, response);
			e.printStackTrace();
		}

    	return res;
    }

    public Boolean checkrecovery(String huma_id,String date) throws ClassNotFoundException
    {	
    	//System.out.println("inside the submitdetails.java");
    	String sql="select *from FRS_RECOVERY where huma_id='"+huma_id+"' AND FRS_DATE = TO_DATE('"+date+"','DD-MM-YYYY')";//select * from huma_mstr where huma_id='E2317'
    	con=jc.getConnection();
    	//log.info("JDBC Connection was created");
    	System.out.println("debug in check recovery");
    	Boolean res=false;
    	try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				//if(huma_id.equals(rs.getString(1)))
				//{
				res=true;
				break;
				//}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("msr check",e);
			//failrd.include(request, response);
			//e.printStackTrace();
		}
    	System.out.println("Status was :"+res);
    	return res;
    }

    public lsrbean getLsrBean(String huma_id) throws SQLException, ClassNotFoundException
    {
    	String sql="select * from huma_mstr where huma_id='"+huma_id+"'";//select * from huma_mstr where huma_id='E2317'
    	con=jc.getConnection();
    	Statement st=con.createStatement();
    	ResultSet rs=st.executeQuery(sql);
    	while(rs.next())
    	{	
    		lb=new lsrbean();
    		//System.out.println(rs.getString(2));
    		//System.out.println(rs.getString(3));
    		//System.out.println(rs.getString(6));
    		//System.out.println(rs.getString(7));

    		lb.sethuma_id(rs.getString(1));
    		lb.setFname(rs.getString(2));
    		lb.setLname(rs.getString(3));
    		lb.setDesignation(rs.getString(4));
    		lb.setarea_name(rs.getString(5));
    		lb.setBSFLUNIT_UCODE(rs.getString(6));
    		lb.setBSFLUNIT_NAME(rs.getString(7));
    		lb.setMoblieno(rs.getString(8));
    		lb.setMobileno2(rs.getString(9));
    		lb.setEmail(rs.getString(9));
    	}
		return lb;
    	
    }
    public recoverybean getRecoverybean(String huma_id,String date) throws ClassNotFoundException, SQLException
    {
    	System.out.println("hi msr huma_id="+huma_id+" :and date= "+date);
    	//String sql="select FRS_seqid,huma_id,to_char(FRS_date,'dd-mm-yyyy'),FRS_village_count,FRS_Cust_count,FRS_total_amt,FRS_total_accounts,FRS_od_amt,FRS_od_accounts from frs_recovery where huma_id = '"+huma_id+"' AND FRS_DATE = TO_DATE('"+date+"','DD-MM-YYYY')";
    	String sql="select FRS_seqid,huma_id,to_char(FRS_date,'dd-mm-yyyy'),FRS_village_count," +
    			"FRS_Cust_count,FRS_total_amt,FRS_total_accounts,FRS_od_amt,FRS_od_accounts " +
    			"from frs_recovery where huma_id = '"+huma_id+"' AND FRS_DATE = TO_DATE('"+date+"','DD-MM-YYYY')";
    	//System.out.println("hi msr con");
    	try
    	{
    	
    	con=jc.getConnection();
    	Statement st=con.createStatement();
    	ResultSet rs=st.executeQuery(sql);
    	//System.out.println("Msr debug 2");
    	//System.out.println("hi msr bsfter rs");
       	while(rs.next())
    	{
       		System.out.println("hi msr while loop");
       		rb=new recoverybean();
       		rb.sethuma_id(rs.getString(2));
       		rb.setFrs_date(rs.getString(3));
       		rb.setFrs_village_count(rs.getInt(4));
       		rb.setCust_count(rs.getInt(5));
       		rb.setTotal_amt(rs.getInt(6));
       		rb.setTotal_accounts(rs.getInt(7));
       		rb.setOd_amt(rs.getInt(8));
       		rb.setTotal_od_accounts(rs.getInt(9));
       		
    	}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn(e);
//			failrd.include(request, response);
			e.printStackTrace();
		}
    	return rb;
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
		System.out.println("doPost method of submitdetails.java");
		//Below five lines code is to session checking & login redirect
		if (request.getSession().getAttribute("username") == null ) {
			response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
			}
		else
		{
			
		String huma_id1=request.getParameter("huma_id");
		int i = huma_id1.lastIndexOf('-');  
		String huma_id=huma_id1.substring(i+1);System.out.print("huma_id="+huma_id);
		date=request.getParameter("FRS_date");
		System.out.println("\n"+date);
						
	RequestDispatcher successrd=getServletContext().getRequestDispatcher("/lsrdetailsupdateUnit.jsp");
	//RequestDispatcher lsrsuccessrd=getServletContext().getRequestDispatcher("/LsrSubmit.jsp");
	RequestDispatcher failrd=getServletContext().getRequestDispatcher("/lsrdetailsupdatefail.jsp");
	RequestDispatcher failrd2=getServletContext().getRequestDispatcher("/failrdsubmit.jsp");

	try {
		lsrdetails=getLsrBean(huma_id);
		recoverybean rb1=getRecoverybean(huma_id, date);
		boolean b=checkhuma_id(huma_id) && checkrecovery(huma_id, date);
		System.out.println("total Status was :"+b);
		if (b)
		{
		HttpSession ses=request.getSession(false);
		ses.setAttribute("huma_id",lsrdetails.gethuma_id());
		ses.setAttribute("fname", lsrdetails.getFname());
		ses.setAttribute("lname",lsrdetails.getLname());
		ses.setAttribute("BSFLUNIT_UCODE", lsrdetails.getBSFLUNIT_UCODE());
		ses.setAttribute("BSFLUNIT_NAME",lsrdetails.getBSFLUNIT_NAME());
		//String userrole=(String)ses.getAttribute("userrole");
		//System.out.println("user role was  .........:"+userrole);
		ses.setAttribute("nvv", rb1.getFrs_village_count());
		ses.setAttribute("cc",rb1.getCust_count());
		ses.setAttribute("frsdate", rb1.getFrs_date());
		ses.setAttribute("oda",rb1.getOd_amt());
		ses.setAttribute("odac",rb1.getTotal_od_accounts());
		ses.setAttribute("ta",rb1.getTotal_amt());
		ses.setAttribute("tac", rb1.getTotal_accounts());
		String userrole=(String)ses.getAttribute("userrole");

		if(userrole.equals("admin"))
		{
			successrd.include(request, response);	
		}
		else if(userrole.equals("unit"))
		{
			successrd.include(request, response);	
		}
		else if(userrole.equals("user"))
		{
		//lsrsuccessrd.include(request, response);	
		}else
		{
			System.out.println("debug msr");
			failrd.include(request, response);
		}
		}else
		{
			failrd.include(request, response);
		}
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		log.warn("",e);
		//e.printStackTrace();
	}catch (NumberFormatException e) {
		// TODO: handle exception
		failrd2.include(request, response);
		//e.printStackTrace();
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		log.warn("",e);
		//failrd.include(request, response);
		//e.printStackTrace();
	}
	

	}
	}
}