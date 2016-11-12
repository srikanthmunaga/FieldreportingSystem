package frs_cls;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.jasypt.digest.StandardStringDigester;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private JdbcConnect jc=new JdbcConnect();
	private lsrbean lsrdetails;
	private Logger log = Logger.getLogger("LOGFILE");
	//log.debug("Some string to print out");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	 */
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    public lsrbean getLsrBean(String huma_id) throws SQLException, ClassNotFoundException
    {
    	lsrbean lb=new lsrbean();
    	
    	String sql="select * from huma_mstr where huma_id='"+huma_id+"'";//select * from huma_mstr where huma_id='E2317'
    	con=jc.getConnection();
    	Statement st=con.createStatement();
    	ResultSet rs=st.executeQuery(sql);

    	while(rs.next())
    	{
    		//System.out.println(rs.getString(2));
    		//System.out.println(rs.getString(3));
    		//System.out.println(rs.getString(6));
    		//System.out.println(rs.getString(7));

    		lb.sethuma_id(huma_id);
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
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		////System.out.println("inside the post() of login.java");
		/*if (request.getSession().getAttribute("username") == null ) {
			response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
			}
		else
		{*/
		HttpSession ses=request.getSession();
		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		System.out.println("username is :"+username);
		String password=request.getParameter("password");
		StandardStringDigester digester = new StandardStringDigester();
		digester.setAlgorithm("SHA-1");   // optionally set the algorithm
		digester.setIterations(50000);  // increase security by performing 50000 hashing iterations
		
		//RequestDispatcher adminrd=getServletContext().getRequestDispatcher("/FrsHome.jsp");
		RequestDispatcher adminrd=getServletContext().getRequestDispatcher("/Modules.jsp");
		RequestDispatcher user_fx_fs=getServletContext().getRequestDispatcher("/FrsHome.jsp");
		RequestDispatcher userrd=getServletContext().getRequestDispatcher("/EmpDetail");///FrsuserHome.jsp");
		RequestDispatcher failrd=request.getRequestDispatcher("/Frsloginfail.jsp");
		try {
			Boolean status=false;
			con=jc.getConnection();
			////System.out.println("Msr debug");
			String sql="select username,EPASSWORD,user_freeze from frs_user where upper(username)=upper('"+username+"')";//"select * from FRS_USER";//Edited by Rajashekhar
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			while(rs.next())
			{
				//System.out.println(rs.getString("username"));
				//if(username.equals(rs.getString(10)))//&&password.equals(rs.getString(3))
				//{
					//successrd.include(request, response);
					if ((digester.matches(password, rs.getString("EPASSWORD")))&&(rs.getString("user_freeze").equals("N"))) {
						  // correct!
						status=true;
						username = rs.getString("username");
						//System.out.println(rs.getString("username"));
						break;
						
						}
					else
					{
						//status=false;
						break;
					}
					
					//status=true;
					
				//}
			}
			//System.out.println("just bfr status checking and status="+status);
			if(status==true) 
			{
				String userrole=checkRloe(username);
				String unitname = null;
				String unitcode = null;
				String uno = null;
				String aname = null;
				String uname=username.toUpperCase();
				//System.out.println("upper case user name is :"+uname);
				String huma_id=null;
				////System.out.println("inside user role and status true");
				String uhsql="select BSFLUNIT_UCODE,(select area_name from area_mstr where area_id=BSFLUNIT_MSTR.area_id) as area_name from BSFLUNIT_MSTR where BSFLUNIT_NAME='"+uname+"'";
				Connection con1=jc.getConnection();
				Statement uhst=con1.createStatement();
				ResultSet rs1=uhst.executeQuery(uhsql);
				String sql3="select u.HUMA_ID,(select bsflunit_name from bsflunit_mstr where bsflunit_ucode=(select h.bsflunit_ucode from huma_mstr h where h.huma_id=u.huma_id)) as unitname,(select h.bsflunit_ucode from huma_mstr h where h.huma_id=u.huma_id) as unitcode from FRS_USER u where username='"+username+"'";
				Connection con3=jc.getConnection();
				Statement st3=con1.createStatement();
				ResultSet rs3=st.executeQuery(sql3);
				//System.out.println("condition for result set checking");
/*				if(rs1!=null)
				{
				while(rs1.next())
				{
					uno=rs1.getString(1);
					System.out.println("Unit number is : "+uno);
					if(uno==null)
					{
						uno="";
					}
					aname=rs1.getString(2);
					if(aname==null)
					{
						aname="";
					}
					
					System.out.println("AREA NAME IS "+aname);
					System.out.println("unit number is :"+uno);
				}
				}
*/				if(rs3!=null)
				{
					while(rs3.next())
					{
					huma_id=rs3.getString(1);
					unitname=rs3.getString(2);
					unitcode=rs3.getString(3);
					
					if(huma_id==null) huma_id="";
					if(unitname==null) unitname="";
					if(unitcode==null) unitcode="";
					System.out.println("In login.java huma_id_2="+huma_id);
				}
				}
				String uname_no=uname+" "+uno;
				//System.out.println("unit number and unit name : "+uname_no);
				System.out.println("user role is :"+userrole);
				ses.setAttribute("username", username);
				ses.setAttribute("userrole", userrole);
				ses.setAttribute("unitname", unitname);
				ses.setAttribute("unitcode", unitcode);
				ses.setAttribute("uno", uno);
			//	ses.setAttribute("uname",uname_no);
			//	ses.setAttribute("aname",aname);
				ses.setAttribute("huma_id",huma_id);
				log.info(username+" logged in");
				//System.out.println(unitname = null);
				
				//Added by Rajesh
				/*
				if(userrole.equals("admin") || userrole.equals("areahead") || userrole.equals("unithead"))
				    adminrd.include(request, response);
				else if(userrole.equals("user") || userrole.equals("fx") || userrole.equals("fs"))
					user_fx_fs.include(request, response);
				*/
				adminrd.include(request, response);
				
				/*if(userrole.equals("admin"))
				{
				
				adminrd.include(request, response);
				}
				else
				{
					
					userrd.include(request, response);
				}*/
				
			}
			else
			{
				log.info(username+" Login fail");
				failrd.forward(request, response);
			}

			//	String name=rs.getString(1);
				//String pass=rs.getString(3);

				/*userbean ub=new userbean();
				ub.setUsername(rs.getString(1));
				ub.setPassword(rs.getString(3));
				HashSet< userbean> hs=new HashSet<userbean>();
				hs.add(ub);

 * 
 * 				//int seq=rs.getInt(2);
				System.out.print(name+"  "+pass);
				if() 
				{
					System.out.println(name);
				}
				else
				{
					failrd.forward(request, response);
					break;
				}
			}*/
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info(e);
			e.printStackTrace();
		//} catch (ClassNotFoundException e) {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(e);
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


		
//	} //else	
	
	} //doPost()
	
	public String checkRloe(String username) throws SQLException
	{
		String userrole=null;
		try {
			con=jc.getConnection();
			String sql="select USERROLE from FRS_USER where USERNAME='"+username+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				userrole=rs.getString(1);
			}
			
			
		//} catch (ClassNotFoundException e) {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//log.info(e);
			log.error("Driver class bot found :", e);
			e.printStackTrace();
		}finally{
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//System.out.println("End of login.java and role="+userrole);
		return userrole;
	
	}//doPost()

}//Class
