package frs_cls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jasypt.digest.StandardStringDigester;

/**
 * Servlet implementation class createHRDetails
 */
public class Modifycompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	PreparedStatement ps=null;
	int f5=0;
	private Logger log = Logger.getLogger(createHRDetails.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifycompany() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("inside the doGet() of Modifycompany");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("inside the doPost() of Modifycompany");
		if (request.getSession().getAttribute("username") == null ) {
			response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
			}
		else
		{		
PrintWriter out = response.getWriter();	
RequestDispatcher successrd=getServletContext().getRequestDispatcher("/createusersuccess.jsp");

try {
	con=jc.getConnection();
	//log.info("JDBC Connection was created");
		String sql = "UPDATE COMP_MSTR SET comp_name=? WHERE comp_id=?";
				
		ps=con.prepareStatement(sql);
		ps.setString(1, request.getParameter("cname"));
		System.out.println("Company Id="+request.getParameter("cid"));  
		//System.out.println("first name "+request.getParameter("fname"));
		ps.setString(2, request.getParameter("cid"));
		System.out.println("Company Name="+request.getParameter("cname"));
		
		f5=ps.executeUpdate();
		System.out.println("Executing the update query");
		if(f5!=0)
			out.println("The Company was Modified successfully with the name "+request.getParameter("cname"));//-FRS User Entry Updated successfully
		else
			out.println("The Company was not Modified for some reasons");
		
	//}
	
	  /*String sql = "insert into huma_mstr " +
			"(FIRSTNAME,LASTNAME,huma_id,EMAIL,MOBILENUMBER,MOBILENUMBER2,area_name,BSFLUNIT_NAME," +
			"BSFLUNIT_UCODE,DESIGNATION)"+
			"values(?,?,?,?,?,?,?,?,?,?)";
	ps=con.prepareStatement(sql);
	ps.setString(1, request.getParameter("fname"));
	//System.out.println("first name "+request.getParameter("fname"));
	ps.setString(2, request.getParameter("lname"));
	ps.setString(3,request.getParameter("huma_id"));
	ps.setString(4,request.getParameter("emailid"));
	ps.setString(5, request.getParameter("mobileno1"));
	ps.setString(6, request.getParameter("mobileno2"));
	ps.setString(7, request.getParameter("area_name"));
	ps.setString(8, request.getParameter("BSFLUNIT_NAME"));
	ps.setString(9, request.getParameter("BSFLUNIT_UCODE"));
	ps.setString(10, request.getParameter("designation"));
	
	request.setAttribute("username",request.getParameter("username"));
	request.setAttribute("userrole",request.getParameter("userrole"));
	f5=ps.executeUpdate();
	System.out.println("Executing the insert query");
	if(f5!=0)
		out.println("The HR Details Created successfully & The User name= "+request.getParameter("fname"));//-FRS User Entry Updated successfully
	else
		out.println("The HR Detail is not Creted for some reasons");
	*///con.commit();
	//successrd.include(request, response);
	ps.close();
	con.close();
	
    /*}
	else
	{
		System.out.println("Executing the update query for updating the existed data");
	}*/
	
}//try 
catch(SQLException sqle) 
{
	System.out.println("SQLException occrued "+sqle.getMessage());
	log.warn("",sqle);
if(sqle.getErrorCode()==2292)out.println("FRS User Entry master cannot be Updated : Child record found for the -FRS User name"); 
else if(sqle.getErrorCode()==1)out.println("Already existing FRS User name, pls change.."); 
else if(sqle.getErrorCode()==2291)//integrity reference related
{  
//	out.println("the error msg="+sqle.getMessage()+"A");//.split(".")[1].trim(); out.println("the error msg="+sqle.getMessage().split(".")[1].trim()+"A");
/*if(sqle.getMessage().split("_")[1].trim().equals("ACTIVITYSL) violated - parent key not found"))
	out.println("Entered Activity does not Exist");
else if(sqle.getMessage().split("_")[1].trim().equals("SUBUNIT) violated - parent key not found"))
	out.println("Entered Subunit id does not Exist"); 
else out.println("Other integrity constraint related exception or error");*/
}//else if(sqle.getErrorCode()==2291)
else sqle.printStackTrace();
} 
catch(Exception e)
{
log.warn("",e);
e.printStackTrace();
} 
finally
{
//ps.close();
//if(con!=null)con.close();  
} 	

}
		
	}//doPost()

}//createHRDetails.java
