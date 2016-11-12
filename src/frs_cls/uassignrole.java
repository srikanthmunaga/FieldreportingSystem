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

public final class uassignrole extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JdbcConnect jc=new JdbcConnect();
	Connection con=null;
	PreparedStatement ps=null;
	int f5=0;
	PrintWriter out;
	String sql1=null;
	String sql2=null;
	String areano1=null;
	String areano2=null;
	private Logger log = Logger.getLogger("LOGFILE");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uassignrole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		////System.out.println("inside the doGet method of uassignrole");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//Below five lines code is to session checking & login redirect
		////System.out.println("inside the doPost method of Uassignrole");
		try{
		out = response.getWriter();
		String ur=(String)request.getSession().getAttribute("userrole");
		String huma_id=request.getParameter("huma_id");
		String username=(String)request.getSession().getAttribute("username");//To get the logged in user
		String huma_id1=(String)request.getSession().getAttribute("huma_id");//To get the logged in userid
		con = jc.getConnection();
				if (request.getSession().getAttribute("username") == null ) {
					response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
					}
				else if (ur.equals("user") || ur.equals("fs") || ur.equals("fx")) {
					out.print("You are not authorised to Assign the Roles");									// login page.
				}
				else if(ur.equals("admin")){
					try{
						update(request,response);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				else if(ur.equals("areahead")){
					//To get the Region name of the logged in user	
					////System.out.println("inside the AreaHead role");
					sql1="select area_ID from area_MSTR where huma_id='"+huma_id1+"'";
					sql2="select area_ID from HUMA_MSTR where HUMA_ID='"+huma_id+"'";
					//System.out.println("sql1= "+sql1);
					//System.out.println("sql2= "+sql2);
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
					//System.out.println("Area1="+areano1+" Area2="+areano2);
					if(areano1.equals(areano2))
					{
						update(request,response);
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
				sql1="select bsflunit_UCODE from BSFLUNIT_MSTR where huma_id='"+huma_id1+"'";
				sql2="select bsflunit_UCODE from HUMA_MSTR where HUMA_ID='"+huma_id+"'";
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
					update(request,response);
				}else{
					out.println("You are not allowed to assign the roles to  other Unit users");
					//System.out.println("Not equal");
				}
					}		
					catch(Exception e){
						e.getMessage();
					}
				
			}//else if(ur.equals("unithead"))
	}//try
	catch(Exception e){
	e.printStackTrace();	
	}
		finally
		{
			try{
				if(con!=null)con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

				}//doPost()
	public void update(HttpServletRequest request,HttpServletResponse response)
	{
RequestDispatcher successrd=getServletContext().getRequestDispatcher("/createusersuccess.jsp");
		
		try {
			con=jc.getConnection();
			out = response.getWriter();
			////log.info("JDBC Connection was created");
			String password=request.getParameter("password");
			////System.out.println("password ="+password);
			StandardStringDigester digester = new StandardStringDigester();
			digester.setAlgorithm("SHA-1");   // optionally set the algorithm
			digester.setIterations(50000);  // increase security by performing 50000 hashing iterations
			String digest = digester.digest(password);
			////System.out.println("Epassword= "+digest);
			String huma_id=request.getParameter("huma_id");
			huma_id=huma_id.substring(huma_id.lastIndexOf("-")+1);
			String username=request.getParameter("username");//System.out.println(username);
			String role=request.getParameter("frs_role");
			String freeze=request.getParameter("freeze");
			String seq_id=request.getParameter("seqid");
			//String user_freeze = request.getParameter("user_freeze"); 
			////System.out.println("The Seq_id="+seq_id);
			Calendar ca1 = Calendar.getInstance();// from here four lines
			// are the system date
			// selection code
           int dd = ca1.get(Calendar.DATE);
           int mm = ca1.get(Calendar.MONTH) + 1; // In Current date Add 1
			// in month
          int yyyy = ca1.get(Calendar.YEAR);
          String FRS_USER_mdate = dd + "-" + mm + "-" + yyyy;
          String FRS_MOD_BY = (String) ((HttpServletRequest) request)
					.getSession().getAttribute("username");
          
          ////System.out.println("inside the uassignRole.java file and freeze_user = "+freeze);


			
			/*if (huma_id.length() >= 4)// if(comp_id2!="")
				huma_id = huma_id.substring(0, 4);*/
			//System.out.println("huma_id= "+huma_id);
			////System.out.println("UserName="+username+" UserRole="+role+" huma_id="+huma_id
				//	+" Password="+password+" Epassword="+digest);
			
			ps=con.prepareStatement("update FRS_USER set USERNAME=?, USERROLE=?,PASSWORD=?,EPASSWORD=?,USER_MBY=?,USER_MDATE=to_date(?,'dd-mm-rrrr'),USER_FREEZE=?,HUMA_ID=? where USER_SEQID=?");
			ps.setString(1, username);
			ps.setString(2, role);
			ps.setString(3, password);
			ps.setString(4, digest);
			ps.setString(5, FRS_MOD_BY);
			ps.setString(6, FRS_USER_mdate);
			ps.setString(7, freeze);
			ps.setString(8, huma_id);
			ps.setString(9, seq_id);
			
			
			request.setAttribute("username",request.getParameter("username"));
			request.setAttribute("userrole",request.getParameter("frs_role"));
			f5=ps.executeUpdate();
			//f5=ps5.executeUpdate();
			if(f5!=0)
				out.println("OK The FRS User Updated successfully & FRS User name= "+request.getParameter("username"));//-FRS User Entry Updated successfully
			else
				out.println("The FRS User is not updated for some reasons");
			//con.commit();
			//successrd.include(request, response);
			ps.close();
			con.close();

			
		}//try 
		catch(SQLException sqle) 
		{
			log.warn("",sqle);
    if(sqle.getErrorCode()==2292)out.println("FRS User Entry master cannot be Updated : Child record found for the -FRS User name"); 
	else if(sqle.getErrorCode()==1){
		sqle.printStackTrace();
		out.println("Already existing FRS User name or huma_id, pls change..");
		
	}
	else if(sqle.getErrorCode()==2291)//integrity reference related
	{  
		
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
	if(con!=null)
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	} 	
		
	

	}//update
}