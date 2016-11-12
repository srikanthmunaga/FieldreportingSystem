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
 * Servlet implementation class EmpDetail
 */
public class EmpDetailUnit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection con;
	private JdbcConnect jc=new JdbcConnect();
	private lsrbean lsrdetails;
	private lsrbean lb;
	private Logger log = Logger.getLogger(EmpDetail.class);



       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpDetailUnit() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Boolean checkhuma_id(String huma_id) throws ClassNotFoundException
    {	
    	String sql="select distinct huma_id from huma_mstr where huma_id='"+huma_id+"'";//select * from huma_mstr where huma_id='E2317'
    	con=jc.getConnection();
    	Boolean res=false;
    	try {
			Statement st=con.createStatement();
			//log.info("JDBC Connection was created");
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
			log.warn("",e);
			e.printStackTrace();
		}

    	return res;
    }
    public lsrbean getLsrBean(String huma_id) throws SQLException, ClassNotFoundException
    {
    	//System.out.println("inside the EmpDetail.java");
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
					String huma_id=null;
					HttpSession ses=request.getSession(false);
					String userrole=(String)ses.getAttribute("userrole");
					System.out.println("user role"+userrole);
					RequestDispatcher lsrsuccessrd=null;
					RequestDispatcher successrd=null;
					RequestDispatcher failrd=null;
					Boolean Status=false;
		//below three lines of code to get just ID from LOV, as a parameter
			if(userrole.equals("admin"))
			{
			////System.out.println("inside the if block and user role is Admin");
			String huma_id1=request.getParameter("huma_id");
			int i = huma_id1.lastIndexOf('-');  
			huma_id=huma_id1.substring(i+1);System.out.print("huma_id="+huma_id);
							
			successrd=getServletContext().getRequestDispatcher("/LsrDetailsSubmit.jsp");
			failrd=getServletContext().getRequestDispatcher("/failmpid.jsp");
			}
			else if(userrole.equals("unit"))
			{
			////System.out.println("inside the else if block and user role is unit");
			String huma_id1=request.getParameter("huma_id");
			int i = huma_id1.lastIndexOf('-');  
			huma_id=huma_id1.substring(i+1);System.out.print("huma_id="+huma_id);
							
			successrd=getServletContext().getRequestDispatcher("/UnitLsrDetailsSubmit.jsp");
			failrd=getServletContext().getRequestDispatcher("/failmpid.jsp");
			}
			
			else
			{	
				
				huma_id=(String) ses.getAttribute("username");
				try {
					Status=checkhuma_id(huma_id);
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("huma_id is ="+huma_id);
				if(Status){
					System.out.println("status was "+Status);
				lsrsuccessrd=getServletContext().getRequestDispatcher("/LsrSubmit.jsp");}
				else
				failrd=getServletContext().getRequestDispatcher("/failmpid.jsp");
				
			}

		try {
			lsrdetails=getLsrBean(huma_id);
			if (checkhuma_id(huma_id))
			{

			ses.setAttribute("huma_id",lsrdetails.gethuma_id());
			ses.setAttribute("fname", lsrdetails.getFname());
			ses.setAttribute("lname",lsrdetails.getLname());
			ses.setAttribute("BSFLUNIT_UCODE", lsrdetails.getBSFLUNIT_UCODE());
			ses.setAttribute("BSFLUNIT_NAME",lsrdetails.getBSFLUNIT_NAME());
			//ses.setAttribute("userrole",userrole);
			System.out.println("user role was  .........:"+userrole);
			if(userrole.equals("admin"))
			{
				successrd.include(request, response);	
			}
			if(userrole.equals("unit"))
			{
				successrd.include(request, response);	
			}
			else if(userrole.equals("user"))
			{
			lsrsuccessrd.include(request, response);	
			}else
			{
				failrd.include(request, response);
			}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.warn("",e);
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}}