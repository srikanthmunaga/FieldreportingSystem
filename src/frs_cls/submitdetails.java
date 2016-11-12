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
public class submitdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private JdbcConnect jc=new JdbcConnect();
	private lsrbean lsrdetails;
	private lsrbean lb;
	private recoverybean rb;
	private Logger log = Logger.getLogger("LOGFILE");
	String date="";

	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public submitdetails() {
        super();
    }
    public Boolean checkhuma_id(String huma_id) throws ClassNotFoundException
    {	
    	String sql="select distinct huma_id from huma_mstr where huma_id='"+huma_id+"'";//select * from huma_mstr where huma_id='E2317'
    	con=jc.getConnection();
    	////log.info("JDBC Connection was created");
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
			log.warn(e);
			//failrd.include(request, response);
			e.printStackTrace();
		}finally
		{
		 if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	} //finally


    	return res;
    }

    public Boolean checkrecovery(String huma_id,String date) throws ClassNotFoundException
    {	
    	String sql="select *from FRS_RECOVERY where huma_id='"+huma_id+"' AND FRS_DATE = TO_DATE('"+date+"','DD-MM-YYYY')";//select * from huma_mstr where huma_id='E2317'
    	con=jc.getConnection();
    	////log.info("JDBC Connection was created");
    	//System.out.println("debug in check recovery");
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
			log.warn("msr check",e);
			//failrd.include(request, response);
			//e.printStackTrace();
		}finally
		{
			 
				try {
					//if(st!=null)st.close();
					if(con!=null)con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
		} //finally
    	//System.out.println("Status was :"+res);
    	return res;
    }

    public lsrbean getLsrBean(String huma_id) throws SQLException, ClassNotFoundException
    {	try {
    	String sql="select HUMA_ID,HUMA_FNAME,HUMA_LNAME,HUMA_DESIGNATION,(SELECT AREA_NAME FROM AREA_MSTR WHERE AREA_ID=(SELECT AREA_ID FROM BSFLUNIT_MSTR WHERE BSFLUNIT_UCODE=HUMA_MSTR.BSFLUNIT_UCODE)) AS AREA_NAME," +
    			"((SELECT BSFLUNIT_NAME FROM BSFLUNIT_MSTR WHERE BSFLUNIT_UCODE=HUMA_MSTR.BSFLUNIT_UCODE))AS BSFLUNIT_NAME,BSFLUNIT_UCODE,HUMA_MOBILE,HUMA_PHONE,HUMA_EMAIL from huma_mstr where huma_id='"+huma_id+"'";//select * from huma_mstr where huma_id='E2317'
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
    	
    			lb.sethuma_id(rs.getString("HUMA_ID"));
        		lb.setFname(rs.getString("HUMA_FNAME"));
        		lb.setLname(rs.getString("HUMA_LNAME"));
        		lb.setDesignation(rs.getString("HUMA_DESIGNATION"));
        		lb.setarea_name(rs.getString("AREA_NAME"));
        		lb.setBSFLUNIT_UCODE(rs.getString("BSFLUNIT_UCODE"));
        		lb.setBSFLUNIT_NAME(rs.getString("BSFLUNIT_NAME"));
        		lb.setMoblieno(rs.getString("HUMA_MOBILE"));
        		lb.setMobileno2(rs.getString("HUMA_PHONE"));
        		lb.setEmail(rs.getString("HUMA_EMAIL"));	
			
    		
    	}
    } catch (Exception e) {
		e.printStackTrace();
	}
    finally 
	{
	 
     if(con!=null)con.close(); 
    }
    	return lb;
    	
    }
    public recoverybean getRecoverybean(String huma_id,String date) throws ClassNotFoundException, SQLException
    {
    	//System.out.println("hi msr huma_id="+huma_id+" :and date= "+date);
    	//String sql="select FRS_seqid,huma_id,to_char(FRS_date,'dd-mm-yyyy'),FRS_village_count,FRS_Cust_count,FRS_total_amt,FRS_total_accounts,FRS_od_amt,FRS_od_accounts from frs_recovery where huma_id = '"+huma_id+"' AND FRS_DATE = TO_DATE('"+date+"','DD-MM-YYYY')";
    	String sql="select FRS_seqid,huma_id,to_char(FRS_date,'dd-mm-yyyy'),FRS_village_count," +
    			"FRS_Cust_count,FRS_total_amt,FRS_total_accounts,FRS_od_amt,FRS_od_accounts,FRS_SDRCUST_COUNT " +
    			"from frs_recovery where huma_id = '"+huma_id+"' AND FRS_DATE = TO_DATE('"+date+"','DD-MM-YYYY')";
    	//System.out.println("hi msr con");
    	try
    	{
    	
    	con=jc.getConnection();
    	Statement st=con.createStatement();
    	ResultSet rs=st.executeQuery(sql);
    	////System.out.println("Msr debug 2");
    	//System.out.println("hi msr bsfter rs");
       	while(rs.next())
    	{
       		//System.out.println("hi msr while loop");
       		rb=new recoverybean();
       		rb.sethuma_id(rs.getString(2));
       		rb.setFrs_date(rs.getString(3));
       		rb.setFrs_village_count(rs.getInt(4));
       		rb.setCust_count(rs.getInt(5));
       		rb.setTotal_amt(rs.getInt(6));
       		rb.setTotal_accounts(rs.getInt(7));
       		rb.setOd_amt(rs.getInt(8));
       		rb.setTotal_od_accounts(rs.getInt(9));
       		rb.setFRS_SDRCUST_COUNT(rs.getInt(10));
       		
    	}
    	} catch (SQLException e) {
			log.warn(e);
//			failrd.include(request, response);
			e.printStackTrace();
		}
    	finally 
    	{
         if(con!=null)con.close(); 
        }
    	
    	return rb;
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//System.out.println("doPost method of submitdetails.java");
		HttpSession ses=request.getSession(false);
		String userrole=(String)ses.getAttribute("userrole");
		String huma_id2=(String)request.getSession().getAttribute("huma_id");
		//Below five lines code is to session checking & login redirect
		if (request.getSession().getAttribute("username") == null ) {
			//System.out.println("Username is null");
			//System.out.println("Username= "+request.getSession().getAttribute("username"));
			response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
			}
		else
		{
		System.out.println("The user name is not null and the user name is "+request.getSession().getAttribute("username"));	
		String huma_id1=request.getParameter("huma_id");
		int i = huma_id1.lastIndexOf('-');  
		String huma_id=huma_id1.substring(i+1);
		//System.out.print("huma_id="+huma_id);
		date=request.getParameter("FRS_date");
		//System.out.println("\n"+date);
						
	RequestDispatcher successrd=getServletContext().getRequestDispatcher("/lsrdetailsupdate.jsp");
	//RequestDispatcher lsrsuccessrd=getServletContext().getRequestDispatcher("/LsrSubmit.jsp");
	RequestDispatcher failrd=getServletContext().getRequestDispatcher("/lsrdetailsupdatefail.jsp");
	RequestDispatcher failrd2=getServletContext().getRequestDispatcher("/failrdsubmit.jsp");
	RequestDispatcher fail=getServletContext().getRequestDispatcher("/Updatelsrdtlsunauthorised.jsp");

	try {
		/*
		 * Codded by Rajesh for server side validation to implement roles 
		 * 
		 */
		String sql1;
		String sql2;
		String areano1=null;
		String areano2=null;
		String result="false";
		con=jc.getConnection();
		if(userrole.equals("areahead"))
		{

			//logged in user belongs to which area
			sql1="select area_id from area_mstr where huma_id in('"+huma_id2+"')";
			//The user to be deleted belongs to which area
			sql2="select area_id from bsflunit_mstr where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='"+huma_id+"')";
			try{
			/*Connection con1=jc.getConnection();
			Statement st1=con1.createStatement();
			ResultSet rs1=st1.executeQuery(sql1);
			if(rs1!=null)
			  {
				while(rs1.next())
			       {
				      areano1=rs1.getString(1);
			       }//while
			  }//if
			System.out.println("areano1= "+areano1);
			*/
			
			Statement st2=con.createStatement();
			ResultSet rs2=st2.executeQuery(sql2);
			if(rs2!=null)
			  {
			    System.out.println("rs2 not null"); 
				while(rs2.next())
			       {
				      areano2=rs2.getString(1);
			       }//while
			  }//if
			//Connection con1=jc.getConnection();
			Statement st1=con.createStatement();
			ResultSet rs1=st1.executeQuery(sql1);
			if(rs1!=null)
			  {
				while(rs1.next())
			       {
				      areano1=rs1.getString(1);
				      if(areano1.equals(areano2))
				    	 {
				    		 result="true";
				    		 break;
				    	 }//if
				    	 else
				    	 {
				    		 result="false";
				    	 }//else
			       }//while
			  }//if
			//System.out.println("areano1= "+areano1);
			
			//if(!areano1.equals(areano2))
			if(!result.equals("true"))
			{
				//out.println("You are not allowed to delete other Region's user");
				//return;
				failrd.include(request, response);
				return;
			}
			
			}catch(Exception e)
			{
				e.getMessage();
			}
			
		}//if(role.equals("areahead"))
		
		
		if(userrole.equals("unithead"))
		{

			//logged in user belongs to which area
			//sql1="select bsflunit_ucode from huma_mstr where huma_id='"+huma_id2+"'";
			sql1="select bsflunit_ucode from bsflunit_mstr where huma_id in('"+huma_id2+"')";
			//The user to be deleted belongs to which area
			sql2="select bsflunit_ucode from huma_mstr where huma_id='"+huma_id+"'";
			try{
			/*Connection con1=jc.getConnection();
			Statement st1=con1.createStatement();
			ResultSet rs1=st1.executeQuery(sql1);
			if(rs1!=null)
			  {
				while(rs1.next())
			       {
				      areano1=rs1.getString(1);
			       }//while
			  }//if
			*///System.out.println("areano1= "+areano1);
			
			//Connection con2=jc.getConnection();
			Statement st2=con.createStatement();
			ResultSet rs2=st2.executeQuery(sql2);
			if(rs2!=null)
			  {
			    //System.out.println("rs2 not null"); 
				while(rs2.next())
			       {
				      areano2=rs2.getString(1);
			       }//while
			  }//if
			//Connection con1=jc.getConnection();
			Statement st1=con.createStatement();
			ResultSet rs1=st1.executeQuery(sql1);
			if(rs1!=null)
			  {
				while(rs1.next())
			       {
				      areano1=rs1.getString(1);
				      if(areano1.equals(areano2))
				    	 {
				    		 result="true";
				    		 break;
				    	 }//if
				    	 else
				    	 {
				    		 result="false";
				    	 }//else
			       }//while
			  }//if
			
			//if(!areano1.equals(areano2))
			if(!result.equals("true"))
			{
				//out.println("You are not allowed to delete other Region's user");
				//return;
				failrd.include(request, response);
				return;
			}
			
			}  //inner try
			catch(Exception e)
			{
				e.getMessage();
			}
		}//if(role.equals("unithead"))

		/*
		if(userrole.equals("areahead"))
		{

			//logged in user belongs to which area
			sql1="select area_id from area_mstr where huma_id='"+huma_id2+"'";
			//The user to be deleted belongs to which area
			sql2="select area_id from bsflunit_mstr where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='"+huma_id+"')";
			try{
			Connection con1=jc.getConnection();
			Statement st1=con1.createStatement();
			ResultSet rs1=st1.executeQuery(sql1);
			if(rs1!=null)
			  {
				while(rs1.next())
			       {
				      areano1=rs1.getString(1);
			       }//while
			  }//if
			System.out.println("areano1= "+areano1);
			
			Connection con2=jc.getConnection();
			Statement st2=con2.createStatement();
			ResultSet rs2=st2.executeQuery(sql2);
			if(rs2!=null)
			  {
			    System.out.println("rs2 not null"); 
				while(rs2.next())
			       {
				      areano2=rs2.getString(1);
			       }//while
			  }//if
			if(!areano1.equals(areano2))
			{
				//out.println("You are not allowed to delete other Region's user");
				//return;
				fail.include(request, response);
				return;
			}
			
			}catch(Exception e)
			{
				e.getMessage();
			}
		}//if(role.equals("areahead"))
		
		
		if(userrole.equals("unithead"))
		{

			//logged in user belongs to which area
			sql1="select bsflunit_ucode from huma_mstr where huma_id='"+huma_id2+"'";
			//The user to be deleted belongs to which area
			sql2="select bsflunit_ucode from huma_mstr where huma_id='"+huma_id+"'";
			try{
			Connection con1=jc.getConnection();
			Statement st1=con1.createStatement();
			ResultSet rs1=st1.executeQuery(sql1);
			if(rs1!=null)
			  {
				while(rs1.next())
			       {
				      areano1=rs1.getString(1);
			       }//while
			  }//if
			System.out.println("areano1= "+areano1);
			
			Connection con2=jc.getConnection();
			Statement st2=con2.createStatement();
			ResultSet rs2=st2.executeQuery(sql2);
			if(rs2!=null)
			  {
			    System.out.println("rs2 not null"); 
				while(rs2.next())
			       {
				      areano2=rs2.getString(1);
			       }//while
			  }//if
			if(!areano1.equals(areano2))
			{
				//out.println("You are not allowed to delete other Region's user");
				//return;
				fail.include(request, response);
				return;
			}
			
			}catch(Exception e)
			{
				e.getMessage();
			}
		}//if(role.equals("unithead"))
*/
		
		/*
		 * code completed here
		 * 
		 */
		lsrdetails=getLsrBean(huma_id);
		recoverybean rb1=getRecoverybean(huma_id, date);
		boolean b=checkhuma_id(huma_id) && checkrecovery(huma_id, date);
		//System.out.println("total Status was :"+b);
		if (b)
		{
		//HttpSession ses=request.getSession(false);
		ses.setAttribute("huma_id",lsrdetails.gethuma_id());
		ses.setAttribute("fname", lsrdetails.getFname());
		ses.setAttribute("lname",lsrdetails.getLname());
		ses.setAttribute("BSFLUNIT_UCODE", lsrdetails.getBSFLUNIT_UCODE());
		//System.out.println("BSFLUNIT_CODE="+lsrdetails.getBSFLUNIT_UCODE());
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
		ses.setAttribute("sdr",rb1.getFRS_SDRCUST_COUNT());
		//String userrole=(String)ses.getAttribute("userrole");

		if(userrole.equals("admin") || userrole.equals("audit") || userrole.equals("areahead") || userrole.equals("unithead"))
		{
			//System.out.println("Role is either Admin/AreaHead/Unithead");
			successrd.include(request, response);	
		}
		else if(userrole.equals("user"))
		{
		//lsrsuccessrd.include(request, response);	
		}else
		{
			//System.out.println("debug msr");
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
	finally
	{ try{
		 if(con!=null)con.close();
	}
			 catch (SQLException e) {
				e.printStackTrace();
			} 
	}//finally

	}
	}
}