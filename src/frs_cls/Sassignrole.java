package frs_cls;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;

import org.apache.log4j.Logger;
import org.jasypt.digest.StandardStringDigester;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public final class Sassignrole extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	PreparedStatement ps=null;
	int f5=0;
	PrintWriter out;
	String sql=null;
	String sql1=null;
	String sql2=null;
	String areano1=null;
	String areano2=null;
	private Logger log = Logger.getLogger("LOGFILE");
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sassignrole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//////System.out.println("inside the doGet method of Sassignrole");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try{
		// TODO Auto-generated method stub
		//Below five lines code is to session checking & login redirect
		//////System.out.println("inside the doPost method of Sassignrole");
		PrintWriter out = response.getWriter();
		con = jc.getConnection();
		String ur=(String)request.getSession().getAttribute("userrole");
		//String deleteuser=request.getParameter("username");//To get the username to be deleted
		String username=(String)request.getSession().getAttribute("username");//To get the logged in user
		String huma_id=(String)request.getSession().getAttribute("huma_id");//To get the logged in userid
		String huma_id1=request.getParameter("huma_id");
		
		
		if (huma_id1.length() >= 4)
			//huma_id1 = huma_id1.substring(0, 4);
			huma_id1 = huma_id1.substring(huma_id1.lastIndexOf("-")+1);
		////System.out.println("huma_id= "+huma_id1);
		//String ur=(String)request.getSession().getAttribute("userrole");
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else if (ur.equals("user") || ur.equals("fs") || ur.equals("fx")) {
					out.print("You are not authorised to Assign the Roles");
				}
				else if(ur.equals("admin")){
					try{
						save(request,response);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				else if(ur.equals("areahead")){
						//To get the Region name of the logged in user	
						//////System.out.println("inside the AreaHead role");
						sql1="select area_ID from area_MSTR where huma_id='"+huma_id+"'";
						sql2="select AREA_ID from BSFLUNIT_MSTR where BSFLUNIT_UCODE=(select BSFLUNIT_UCODE from HUMA_MSTR where HUMA_ID='"+huma_id1+"')";
						//sql2="select area_ID from HUMA_MSTR where HUMA_ID='"+huma_id+"'";
						//System.out.println("sql1= "+sql1);
						//System.out.println("sql2= "+sql2);
						try{
							////System.out.println("inside the try of areahead block");
						//Connection con1=jc.getConnection();
						Statement st1=con.createStatement();
						ResultSet rs1=st1.executeQuery(sql1);
						//System.out.println("Query executed");
						if(rs1!=null)
						  {
						     while(rs1.next())
						       {
							      areano1=rs1.getString(1);
						       }//while
						  }//if
						//System.out.println("area1="+areano1);
						try{
						//Connection con2=jc.getConnection();
						//System.out.println("got connection con2");
						Statement st2=con.createStatement();
						//System.out.println("Got the statement");
						//ResultSet rs2=st2.executeQuery(sql2);
						ResultSet rs2=st2.executeQuery(sql2);
						//System.out.println("query2 executed");
						if(rs2!=null)
						  {
						     while(rs2.next())
						       {
							      areano2=rs2.getString(1);
						       }//while
						  }//if
						}catch(Exception e){
							e.printStackTrace();
						}
						//System.out.println("Area1="+areano1+" Area2="+areano2);
						if(areano1.equals(areano2))
						{
							save(request,response);
						}else{
							out.println("You are not allowed to assign the roles to other Region users");
							//System.out.println("Not equal");
						}
							}		
							catch(Exception e){
								e.getMessage();
							}
						
					}//if(ur.equals("areahead"))
		//PrintWriter out = response.getWriter();	
		//RequestDispatcher successrd=getServletContext().getRequestDispatcher("/createusersuccess.jsp");
				else if(ur.equals("unithead"))
				{
					////System.out.println("inside the unitHead role");
					sql1="select bsflunit_UCODE from BSFLUNIT_MSTR where huma_id='"+huma_id+"'";
					sql2="select bsflunit_UCODE from HUMA_MSTR where HUMA_ID='"+huma_id1+"'";
					try{
					//Connection con1=jc.getConnection();
					Statement st1=con.createStatement();
					ResultSet rs1=st1.executeQuery(sql1);
					if(rs1!=null)
					  {
					     while(rs1.next())
					       {
						      areano1=rs1.getString(1);
					       }//while
					  }//if
					else
					{
						//out.println("");
						//System.out.println("This Employee "+huma_id1+" is not belongs to any unit");
					}
					
					
					//Connection con2=jc.getConnection();
					Statement st2=con.createStatement();
					ResultSet rs2=st2.executeQuery(sql2);
					if(rs2!=null)
					  {
					     while(rs2.next())
					       {
						      areano2=rs2.getString(1);
					       }//while
					  }//if
					//System.out.println("Unit1="+areano1+" Unit2="+areano2);
					if(areano1.equals(areano2))
					
					{
						save(request,response);
					}else{
						out.println("You are not allowed to assign the roles to  other Unit users");
						//System.out.println("Not equal");
					}
						}		
						catch(Exception e){
							e.getMessage();
						}
					
				}//else if(ur.equals("unithead"))
				
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}//doPost()
	public void save(HttpServletRequest request,HttpServletResponse response)
	{
		try {
		RequestDispatcher successrd=getServletContext().getRequestDispatcher("/createusersuccess.jsp"); 
		
			out = response.getWriter();
			con=jc.getConnection();
			////log.info("JDBC Connection was created");
			String password=request.getParameter("password");
			//System.out.println("password ="+password);
			StandardStringDigester digester = new StandardStringDigester();
			digester.setAlgorithm("SHA-1");   // optionally set the algorithm
			digester.setIterations(50000);  // increase security by performing 50000 hashing iterations
			String digest = digester.digest(password);
			//System.out.println("Epassword= "+digest);
			String huma_id=request.getParameter("huma_id");
			
			

			/*if (huma_id.length() >= 4)
				huma_id = huma_id.substring(0, 4);
			*/
			huma_id=huma_id.substring(huma_id.lastIndexOf("-")+1);
			//System.out.println("huma_id= "+huma_id);
			String sql="insert into FRS_USER (NAME,USERNAME,PASSWORD,EPASSWORD,USERROLE," +
					    "MOBILENO,user_seqid,user_cby,HUMA_ID,USER_FREEZE)" +
					    "values(?,?,?,?,?,?,user_seqid2.nextval	,?,?,?)";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, null);//request.getParameter("fname")
			ps.setString(2, request.getParameter("username"));
			ps.setString(3, request.getParameter("password"));
			ps.setString(4, new String(digest));
			ps.setString(5, request.getParameter("frs_role"));
			ps.setString(6, null);//request.getParameter("mobileno")
			ps.setString(7, new String((String) request.getSession().getAttribute("username")));
			ps.setString(8, new String(huma_id));
			ps.setString(9, request.getParameter("freeze"));
			
			request.setAttribute("username",request.getParameter("username"));
			request.setAttribute("userrole",request.getParameter("role"));
			f5=ps.executeUpdate();
			if(f5!=0)
				out.println("OK The FRS User Created successfully & FRS User name= "+request.getParameter("username"));//-FRS User Entry Updated successfully
			else
				out.println("The FRS User is not Creted for some reasons");
			ps.close();
			con.close();

			
		}//try 
		catch(SQLException sqle) 
		{
			log.warn("",sqle);
    if(sqle.getErrorCode()==2292)out.println("FRS User Entry master cannot be Updated : Child record found for the -FRS User name"); 
	else if(sqle.getErrorCode()==1)
	{	//sqle.printStackTrace();
		out.println("Already existing FRS User name or huma_id, pls change..");
		
		} 
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
	if(con!=null){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  }
	} //finally	

	}//save


}
	