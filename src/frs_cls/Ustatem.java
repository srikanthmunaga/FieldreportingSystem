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

public final class Ustatem extends HttpServlet {

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
				out.print("You are not authorised to Modify the State Details");									// login page.
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
		       String state_mdate=dd+"-"+mm+"-"+yyyy;

				String country_id = request.getParameter("country_id").trim();
				country_id = country_id.substring(country_id.lastIndexOf("-")+1);
				String state_id = request.getParameter("state_id");//.trim(); 
				//state_id = state_id.substring(state_id.lastIndexOf("-")+1);
				state_id = state_id.substring(state_id.lastIndexOf("-")+1);
				String state_name = request.getParameter("state_name").trim();
				state_name = state_name.substring(state_name.lastIndexOf("-")+1);
				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("username");		
				
				try
				{
		        //Class.forName(driver);
				con= dbConn.getConnection();
		//con = DriverManager.getConnection(url,user,pwd); 
		 

				ps = con.prepareStatement("select state_id from state_mstr where state_id=?");
				ps.setString(1,state_id);
				
				rs = ps.executeQuery();
				if(rs==null)
		         System.out.println("result set is null");
		   		//if((rs.next())==false)//state_id is not already exist
				//{   
		           int x=0;//going to check for the availability of entered country in country_mstr.
				   ps2 = con.prepareStatement("select country_name||'-'||country_id from country_mstr where country_id=?");
				   ps2.setString(1,country_id);
				   rs2 = ps2.executeQuery();
				   if(rs2==null)
		             System.out.println("result set is null");
				   if((rs2.next())==false)
				     x=x+1;
				  
				if(x==0) //means the specified country_id is available in the country_mstr
				{
			    ps1= con.prepareStatement("update state_mstr set country_id=?,state_name=?,state_mdate=to_date(?,'dd-mm-rrrr'),state_mby=? where state_id=?");
				ps1.setString(1,country_id);
				//ps1.setString(1,state_id);
				ps1.setString(2,state_name);
				ps1.setString(3,state_mdate);
				ps1.setString(4,curhuma_id);		
				ps1.setString(5,state_id);
				f=ps1.executeUpdate();
				if(f!=0)
					out.println("OKState master updated successfully");//State master updated successfully
				else
					out.println("State master is not updated for some reasons");
						
		     	ps1.close();
				}//if((x==0) //means the specified country_id is available in the country_mstr
				else //if((x!=0) //means the specified country_id is not available in the country_mstr
					out.println("Entered Country does not Exist");

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
