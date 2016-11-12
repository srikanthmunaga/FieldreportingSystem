package frs_cls;



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

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public final class Svillagem extends HttpServlet {

	   Connection con=null;
	   PreparedStatement ps=null;
	   PreparedStatement ps1=null,ps2=null;
	   ResultSet rs=null,rs2=null;
	   int f=0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// //System.out.println("inside the doGet method");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 //System.out.println("inside the doPost method");

		JspFactory _jspxFactory = null;
		PageContext pageContext = null;
		HttpSession session = null;
		ServletContext application = null;
		ServletConfig config = null;
		JspWriter out = null;
		Object page = this;
		JspWriter _jspx_out = null;
		PageContext _jspx_page_context = null;

		try {
			_jspxFactory = JspFactory.getDefaultFactory();
			response.setContentType("text/html");
			pageContext = _jspxFactory.getPageContext(this, request, response,
					"Error.jsp", true, 8192, true);
			_jspx_page_context = pageContext;
			application = pageContext.getServletContext();
			config = pageContext.getServletConfig();
			session = pageContext.getSession();
			out = pageContext.getOut();
			_jspx_out = out;

			if (((HttpServletRequest) request).getSession()
					.getAttribute("username") == null) {
				response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to
													// login page.
			}

			else // if (((HttpServletRequest)
					// request).getSession().getAttribute("user") != null)
			{
				// chain.doFilter(request, response); // Logged in, so just
				// continue.

				JdbcConnect dbConn = null;
				synchronized (request) {
					dbConn = (JdbcConnect) _jspx_page_context.getAttribute(
							"dbConn", PageContext.REQUEST_SCOPE);
					if (dbConn == null) {
						dbConn = new JdbcConnect();
						_jspx_page_context.setAttribute("dbConn", dbConn,
								PageContext.REQUEST_SCOPE);
					}
				}
				con=dbConn.getConnection();
				String role=request.getSession().getAttribute("userrole").toString();
				String huma_idd=request.getSession().getAttribute("huma_id").toString();
				String driver = application.getInitParameter("driver");
				String url = application.getInitParameter("url");
				String user = application.getInitParameter("user");
				String pwd = application.getInitParameter("pwd");
				Calendar ca1 =Calendar.getInstance();//from here four lines are the system date selection code
		          int dd=ca1.get(Calendar.DATE);
		          int mm=ca1.get(Calendar.MONTH)+1; // In Current date Add 1 in month
		          int yyyy=ca1.get(Calendar.YEAR);
		       String village_cdate=dd+"-"+mm+"-"+yyyy;
		       try {
				String vcode = request.getParameter("vcode").trim(); //common for each insertion
				if(vcode.length()>10)
				{
					out.println("Village Code is too large, pls check!.. ");
					return;
				}
				String vname = request.getParameter("vname").trim();
				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("username");		
				String unit1=request.getParameter("BSFLUNIT_UCODE").trim();
				String District=request.getParameter("DISTRICT").trim();

				String uno=unit1.substring(unit1.lastIndexOf('-')+1);
				String uname="";
				//Connection svcon=new JdbcConnect().getConnection();
				String svsql="select distinct BSFLUNIT_NAME from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"+uno+"'";
				Statement svst=con.createStatement();
				ResultSet svrs=svst.executeQuery(svsql);
				if(svrs.next())
				{
					uname=svrs.getString(1);
				}

				//select distinct BSFLUNIT_NAME||' '||BSFLUNIT_UCODE from BSFLUNIT_MSTR");
				/*				int i = unit1.lastIndexOf('-');  
				String BSFLUNIT_NAME=unit1.substring(0,i);
				String BSFLUNIT_UCODE=unit1.substring(i+1);
*/				
				//String uno=unit1.substring(BSFLUNIT_UCODE.lastIndexOf('-')+1);
				//Connection svcon1=new JdbcConnect().getConnection();
				String unt=unit1.substring(unit1.lastIndexOf('-')+1);
				String svsql1="select 'Exist' from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"+unt+"'";

				Statement svst1=con.createStatement();
				ResultSet svrs1=svst1.executeQuery(svsql1);
				if(!svrs1.next())
				{
					out.println("Entered Unit does not Exist ");
					return;
				}

				//Connection svcon11=new JdbcConnect().getConnection();
				String svsql11="select 'Exist' from VILLAGE_MSTR where VCODE='"+vcode+"' and bsflunit_ucode='"+unt+"'";
				Statement svst11=con.createStatement();
				ResultSet svrs11=svst11.executeQuery(svsql11);
				if(svrs11.next())
				{
					out.println("Entered Village code already Exist ");
					return;
				}

				
				/*System.out.println("Unit Name="+unit);
				System.out.println("vcode="+vcode.substring(0,3));
				*/
				//String huma_id1=request.getParameter("huma_id").trim();
				//int j = huma_id1.lastIndexOf('-');  
				//String huma_id=huma_id1.substring(j+1);
				//System.out.println("Raj huma_id is="+huma_id);
				//System.out.println("Rajesh checking");
//				System.out.println("TEST checking");
			
				//String village_ph=request.getParameter("village_phno").trim();
				//String village_mob=request.getParameter("village_mob").trim();
				//System.out.println("email="+email+" village_ph="+village_ph+" village_mob="+village_mob);
				//try {
			        //Class.forName(driver);
					con= dbConn.getConnection();
			//con = DriverManager.getConnection(url,user,pwd); 
			 

/*					ps = con.prepareStatement("select * from village_mstr where vcode=?");//select * from village_mstr where upper(vcode)= upper('business development')
					ps.setString(1,vcode.substring(0,3));
					
					rs = ps.executeQuery();
					if(rs==null)
			         System.out.println("result set is null");
					int r=1;
					if ((rs.next()) == false)
						//r = r + 1;
						r=0;
					System.out.println("R value="+r);
					//Added By Rajesh
					int p = 0;
					ps = con.prepareStatement("select area_ID from area_MSTR where area_ID=?");
					ps.setString(1, unit);
					rs = ps.executeQuery();
					if (rs == null)
						System.out.println("result set is null in ubm.jsp");
					if ((rs.next()) == false)
						p = p + 1;
					
					System.out.println("Value of P="+p);
					
					
					int q = 0;
					ps = con.prepareStatement("select USERNAME from FRS_USER where USERNAME=?");
					ps.setString(1, huma_id);
					rs = ps.executeQuery();
					if (rs == null)
						System.out.println("result set is null in ubm.jsp");
					if ((rs.next()) == false)
						q = q + 1;
					
					System.out.println("Value of q="+q);
					
					//Rajesh complete
			   		//if(((rs.next())==false)&&(p == 0)&&(q == 0))
					if((r==0)&&(p == 0)&&(q == 0))
					{   
					
					//**************Added By Rajesh*************************/
					 
					
					//Connection areacon=new JdbcConnect().getConnection();
						ResultSet arears;
						Statement areast=con.createStatement();
						String area1=null;
						String area2=null;
						String result="false";
						String unitno=unit1.substring(unit1.lastIndexOf('-')+1);
						if(role.equals("unithead"))
						{
							arears=areast.executeQuery("select bsflunit_ucode from bsflunit_mstr where huma_id='"+huma_idd+"'");
							if(arears!=null)
							  {
								System.out.println("rs is not null");
							     while(arears.next())
							       {
							    	 
							    	 area1=arears.getString(1);
							    	 if(area1.equals(unitno))
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
							//********************************
							if(!result.equals("true"))
							{
								//data=null;
								out.println("You are not authorised to create village of another Unit");
								return;
							}
						}//if(role.equals("unithead"))
						
						if(role.equals("areahead")){
						/*	arears=areast.executeQuery("select area_id from area_mstr where huma_id='"+huma_idd+"'");
							if(arears!=null)
							  {
							     while(arears.next())
							       {
							    	 
							    	 area1=arears.getString(1);
							       }//while
							  }//if
							  
							  */
							arears=areast.executeQuery("select area_id from bsflunit_mstr where bsflunit_ucode='"+unitno+"'");
							if(arears!=null)
							  {
							     while(arears.next())
							       {
							    	 
							    	 area2=arears.getString(1);
							       }//while
							  }//if
							
							arears=areast.executeQuery("select area_id from area_mstr where huma_id='"+huma_idd+"'");
							if(arears!=null)
							  {
							     while(arears.next())
							       {
							    	 
							    	 area1=arears.getString(1);
							    	 if(area1.equals(area2))
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
							
							//if(!area1.equals(area2))
							if(!result.equals("true"))
							{
								//data=null;
								out.println("You are not authorised to create Village of another Region");
								return;
							}
						}
						
						
					
					
					
					
					
					
					//**************Ends Here*************************/
					
		        //System.out.println("Msr debug");		   
				    ps1= con.prepareStatement("insert into village_mstr values(?,?,?,?,village_seqid.nextval,to_date(?,'dd-mm-yyyy'),to_date(?,'dd-mm-yyyy'),?,?)");
System.out.println(unit1.substring(unit1.lastIndexOf('-')+1));
					ps1.setString(1,unit1.substring(unit1.lastIndexOf('-')+1));
					//ps1.setString(2,uname);
					ps1.setString(2,vcode);
					ps1.setString(3,vname);
					ps1.setString(4,District);
					ps1.setString(5,village_cdate);
					ps1.setString(6,null);
					ps1.setString(7,curhuma_id);
					ps1.setString(8,null);
					//ps1.setString(7,unit);
					//ps1.setString(8,huma_id);
					//ps1.setString(9,email);
					//ps1.setString(10,village_ph);
					//ps1.setString(11,village_mob);
					//System.out.println("hey the PreparedStatement made");
			        //System.out.println("Msr debug");
					f=ps1.executeUpdate();
					if(f!=0)
						out.println("OKBSFL Village master created successfully");//BSFL Village master created successfully
					else
						out.println("BSFL Village master is not created for some reasons");
							
			     	ps1.close();
					//}//count(*)>=12 else
/*					}//main if((rs.next())==false)
					else if(r!=0)
						out.println("Already existing one,To differ press refresh and continue");
*/			   		
/*					else if(p!=0)
						// entered company not exits
						out.println("Entered Unit Belongs to ID does not Exist");
					else if(q!=0)
							// entered company not exits
							out.println("Entered Village Incharge ID does not Exist");
*/				 }//try 
			   catch(Exception e) { e.printStackTrace();} 
			   finally 
				{
				 if(rs!=null)rs.close();
			     if(ps!=null)ps.close();
				 if(rs2!=null)rs2.close();
			     if(ps2!=null)ps2.close();
			     if(con!=null)con.close(); 
			    }
			}// authorised acess else

		} catch (Throwable t) {
			if (!(t instanceof SkipPageException)) {
				out = _jspx_out;
				if (out != null && out.getBufferSize() != 0)
					out.clearBuffer();
				if (_jspx_page_context != null)
					_jspx_page_context.handlePageException(t);
			}
		} finally {
			if (_jspxFactory != null)
				_jspxFactory.releasePageContext(_jspx_page_context);
		}
	}
}
