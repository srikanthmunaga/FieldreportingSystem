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

public final class Ucitym extends HttpServlet {

	   Connection con=null;
	   PreparedStatement ps=null,ps1=null,ps2=null;
	   ResultSet rs=null,rs1=null,rs2=null;
	   int f=0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// //System.out.println("inside the doGet method");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// //System.out.println("inside the doPost method");

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
			String ur=(String)request.getSession().getAttribute("userrole");

			if (((HttpServletRequest) request).getSession()
					.getAttribute("username") == null) {
				response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to
													// login page.
			}
			else if (ur.equals("user") || ur.equals("fs") || ur.equals("fx")) {
				out.print("You are not authorised to Modify the City Details");									// login page.
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

		       	String driver = application.getInitParameter("driver");
				String url = application.getInitParameter("url");
				String user = application.getInitParameter("user");
				String pwd = application.getInitParameter("pwd");
				Calendar ca1 =Calendar.getInstance();//from here four lines are the system date selection code
		          int dd=ca1.get(Calendar.DATE);
		          int mm=ca1.get(Calendar.MONTH)+1; // In Current date Add 1 in month
		          int yyyy=ca1.get(Calendar.YEAR);
		       String city_mdate=dd+"-"+mm+"-"+yyyy;
				
				String state_id = request.getParameter("state_id").trim();
				state_id = state_id.substring(state_id.lastIndexOf("-")+1);
				String city_id = request.getParameter("city_id").trim(); 
				city_id = city_id.substring(city_id.lastIndexOf("-")+1);
				String city_name = request.getParameter("city_name").trim();
				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("username");		
				
				try
				{
		        //Class.forName(driver);
				con= dbConn.getConnection();
		//con = DriverManager.getConnection(url,user,pwd); 
		 

				ps = con.prepareStatement("select city_id from city_mstr where city_id=?");
				ps.setString(1,city_id);
				
				rs = ps.executeQuery();
				if(rs==null)
		         System.out.println("result set is null");
		   		//if((rs.next())==false)//city_id is not already exist
				//{   
		           int x=0;//going to check for the availability of entered state in state_mstr.
				   ps2 = con.prepareStatement("select state_id||' '||state_name from state_mstr where state_id=?");
				   ps2.setString(1,state_id.substring(0,3));
				   rs2 = ps2.executeQuery();
				   if(rs2==null)
		             System.out.println("result set is null");
				   if((rs2.next())==false)
				     x=x+1;
				  
				if(x==0) //means the specified state_id is available in the state_mstr
				{
			    ps1= con.prepareStatement("update city_mstr set state_id=?,city_name=?,city_mdate=to_date(?,'dd-mm-rrrr'),city_mby=? where city_id=?");
				ps1.setString(1,state_id.substring(0,3));
				//ps1.setString(1,city_id);
				ps1.setString(2,city_name);
				ps1.setString(3,city_mdate);
				ps1.setString(4,curhuma_id);
				ps1.setString(5,city_id);
				f=ps1.executeUpdate();
				if(f!=0)
					{ 
					//System.out.println("OK");//City master updated successfully
					out.print("OKCity master updated successfully");//City master updated successfully
				    }
				else
					{ 
					//System.out.println("City master is not updated for some reasons");
					out.println("City master is not updated for some reasons");
					}
						
		     	ps1.close();
				}//if((x==0) //means the specified state_id is available in the state_mstr
				else //if((x!=0) //means the specified state_id is not available in the state_mstr
				{ 
				//System.out.println("Entered State does not Exist");
				out.println("Entered State does not Exist");
				}//else if((x!=0) //means the specified state_id is not available in the state_mstr
				/*}//if that checks the existingness of the city_id
				else
				{   %> <jsp:include page="City master.jsp" flush="true" />	
				 <p align="center"><font color="red" class="style22" align="center">Already existing one</font></p>
									   <%
				}*/
			
			 }//try 
		   catch(Exception e) { e.printStackTrace();} 
		   finally 
			{
			 if(ps!=null)ps.close();
			 if(rs!=null)rs.close();
			 if(ps1!=null)ps1.close();
			 if(rs1!=null)rs1.close();
			 if(ps2!=null)ps2.close();
			 if(rs2!=null)rs2.close();
		     if(con!=null)con.close();  
		    }
		   }//authorised acess else

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
