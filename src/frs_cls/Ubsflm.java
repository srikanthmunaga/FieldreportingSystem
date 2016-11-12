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

public final class Ubsflm extends HttpServlet {

	   Connection con=null;
	   PreparedStatement ps=null;
	   PreparedStatement ps1=null;
	   ResultSet rs=null;
	   int f=0;
	   int g=0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("inside the doGet method of Ubsflm");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 //System.out.println("inside the doPost method of Ubsflm");

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
			else if (ur.equals("unithead") || ur.equals("user") || ur.equals("fs") || ur.equals("fx")) {
				out.print("You are not authorised to Modify the Unit Details");									// login page.
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
		       String bsflunit_mdate=dd+"-"+mm+"-"+yyyy;
			   
			    String bsflunit_ucode = request.getParameter("bsflunit_ucode").trim();
			    bsflunit_ucode = bsflunit_ucode.substring(bsflunit_ucode.lastIndexOf("-")+1);
				String bsflunit_name = request.getParameter("bsflunit_name").trim(); 
				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("username");		
				
				String area1=request.getParameter("busiid").trim();
				int i = area1.lastIndexOf('-');  
				String area=area1.substring(i+1);
				System.out.println("Region Name="+area);
				
				
				String huma_id1=request.getParameter("huma_id").trim();
				int j = huma_id1.lastIndexOf('-');  
				String huma_id=huma_id1.substring(j+1);
				System.out.println("Raj huma_id is="+huma_id);
				String email=request.getParameter("unit_email").trim();
				String unit_ph=request.getParameter("unit_phno").trim();
				String unit_mob=request.getParameter("unit_mob").trim();
				try
				{
		        //Class.forName(driver);
				con= dbConn.getConnection();
				
				/*
				 * Codded by rajesh 
				 * server side validation to implement user roles
				 * 
				 */
				
				String huma_id2=request.getSession().getAttribute("huma_id").toString();
				String role=request.getSession().getAttribute("userrole").toString();
				String areano1=null;
				if(role.equals("areahead"))
				{
					//To find out the area_id of the loggedin user
				ps = con.prepareStatement("select area_id from area_mstr where huma_id='"+huma_id2+"'");
				rs = ps.executeQuery();
				if(rs!=null)
				  {
					while(rs.next())
				       {
					      areano1=rs.getString(1);
				       }//while
				  }//if
				if(!areano1.equals(area))
				{
					out.println("You are not allowed to Update unit of other Region");
					return;
					//failrd.include(request, response);
					//return;
				}
				System.out.println("Region= "+area+" Region1= "+areano1);
				}//if(role.equals("areahead"))
				/*
				 * Code completed
				 * 
				 */

				con.setAutoCommit(false);
			    ps1= con.prepareStatement("update BSFLUNIT_MSTR set bsflunit_name=?," +
			    		"bsflunit_mdate=to_date(?,'dd-mm-rrrr'),bsflunit_mby=?,area_ID=?,huma_id=?," +
			    		"bsflunit_EMAIL=?,bsflunit_PHONE=?,bsflunit_MOBILE=? where bsflunit_ucode=?");
				ps1.setString(1,bsflunit_name);
				ps1.setString(2,bsflunit_mdate);
				ps1.setString(3,curhuma_id);
				ps1.setString(4,area);
				ps1.setString(5,huma_id);
				ps1.setString(6,email);
				ps1.setString(7,unit_ph);
				ps1.setString(8,unit_mob);
				ps1.setString(9,bsflunit_ucode);
				f=ps1.executeUpdate();
				
				ps1= con.prepareStatement("update huma_MSTR set bsflunit_ucode=? where huma_id=?");
				ps1.setString(1,bsflunit_ucode);
				ps1.setString(2,huma_id);
				g=ps1.executeUpdate();
				if(f!=0 && g!=0){
					
					out.println("OKBSFL Unit master Updated successfully");//BSFL Unit master Updated successfully
				    con.commit();
				}
				else{
				   con.rollback();	
				   out.println("BSFL Unit master is not Updated for some reasons");
				}
			 }//try 
		   catch(Exception e) { e.printStackTrace();} 
		   finally 
			{
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ps1!=null) ps1.close();
			if(con!=null) con.close(); 
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
