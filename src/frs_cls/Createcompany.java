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
 *///raja2

public class Createcompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	PreparedStatement ps=null;
	int f5=0;
	private Logger log = Logger.getLogger(createHRDetails.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Createcompany() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("inside the doGet() of Createcompany");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("inside the doPost() of Createcompany");
		

		/*MY NAME IS SREENIVASARAO*/
		
		
		
		
		/* Added for ajax test   */
		//$name=$_POST["companyID"];
		//$username=$_POST["CompanyName"];
		String comp_name = request.getParameter("comp_name").trim();
		System.out.println("comp_name= "+comp_name);
		
		String comp_id = request.getParameter("comp_id").trim();
		System.out.println("comp_id= "+comp_id);
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
	/*String eid=request.getParameter("huma_id");
	//System.out.println("inside the try huma_id="+eid);
	String qry="select * from huma_mstr where huma_id='"+eid+"'";
	System.out.println("Query is "+qry);
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery(qry);
	boolean empty = false;
	while(rs.next()){
		//System.out.println("inside the loop");
		String huma_id=(String)rs.getString(1);
		System.out.println("huma_id= "+huma_id);
		empty=true;
	}
	System.out.println("Status of empty is "+empty);
	if(empty==true)
	{
		System.out.println("Executing the Update query");
		String sql2 = "UPDATE huma_mstr SET FIRSTNAME=?,LASTNAME=?,EMAIL=?,MOBILENUMBER=?," +
				"MOBILENUMBER2=?,area_name=?,BSFLUNIT_NAME=?,BSFLUNIT_UCODE=?,DESIGNATION=? WHERE huma_id=?";
		ps=con.prepareStatement(sql2);
		ps.setString(1, request.getParameter("fname"));
		//System.out.println("first name "+request.getParameter("fname"));
		ps.setString(2, request.getParameter("lname"));
		//ps.setString(3,request.getParameter("huma_id"));
		ps.setString(3,request.getParameter("emailid"));
		ps.setString(4, request.getParameter("mobileno1"));
		ps.setString(5, request.getParameter("mobileno2"));
		ps.setString(6, request.getParameter("area_name"));
		ps.setString(7, request.getParameter("BSFLUNIT_NAME"));
		ps.setString(8, request.getParameter("BSFLUNIT_UCODE"));
		ps.setString(9, request.getParameter("designation"));
		ps.setString(10,request.getParameter("huma_id"));
		
		request.setAttribute("username",request.getParameter("username"));
		request.setAttribute("userrole",request.getParameter("userrole"));
		f5=ps.executeUpdate();
		if(f5!=0)
			out.println("The HR Details Updated successfully & The User name= "+request.getParameter("fname"));
		else
			out.println("The HR Detail is not Updated for some reasons");
		
	}
	else
	{*/
		System.out.println("Executing the insert query");
		String sql1 = "insert into comp_mstr " +
				"(comp_id,comp_name)"+
				"values(?,?)";
		String sql="insert into comp_mstr(comp_id,comp_name) values('"+comp_id+"','"+comp_name+"')";
		//String cid=request.getParameter("id").toString();
		//String cname=request.getParameter("name").toString();
		//System.out.println("Cid="+cid+" cname="+cname);
		//System.out.println("cid="+request.getParameter("comp_id"));
		System.out.println("Query= "+sql);
		ps=con.prepareStatement(sql);   
		//ps.setString(1, request.getParameter("cid"));    //comp_id    comp_name
		//ps.setString(1, comp_name);
		//System.out.println("Company Id="+request.getParameter("cid"));
		//ps.setString(1, comp_id);
		//ps.setString(2, request.getParameter("cname"));
		//System.out.println("Company Name="+request.getParameter("cname"));
		
		
		
		/*ps.setString(3,request.getParameter("huma_id"));
		ps.setString(4,request.getParameter("emailid"));
		ps.setString(5, request.getParameter("mobileno1"));
		ps.setString(6, request.getParameter("mobileno2"));
		ps.setString(7, request.getParameter("area_name"));
		ps.setString(8, request.getParameter("BSFLUNIT_NAME"));
		ps.setString(9, request.getParameter("BSFLUNIT_UCODE"));
		ps.setString(10, request.getParameter("designation"));
		
		request.setAttribute("username",request.getParameter("username"));
		request.setAttribute("userrole",request.getParameter("userrole"));
		*/
		
		
		//f5=ps.executeUpdate();
		f5=ps.executeUpdate();
		System.out.println("Executing the insert query");
		if(f5!=0)
			out.println("The Company was Created successfully with the name "+comp_name);//-FRS User Entry Updated successfully
		else
			out.println("The Company was not Creted for some reasons");
		
		
		
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
	
		
		
		//ps.close();
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
