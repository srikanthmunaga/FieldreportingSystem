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

public final class Ucm extends HttpServlet {

	   Connection con=null;
	   Statement st=null,st1=null;
	   PreparedStatement ps=null,ps1=null;
	   ResultSet rs=null,rs1=null;
	   int f=0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 ////System.out.println("inside the doGet method");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 ////System.out.println("inside the doPost method");

		JspFactory _jspxFactory = null;
		PageContext pageContext = null;
		HttpSession session = null;
		ServletContext application = null;
		ServletConfig config = null;
		JspWriter out = null;
		Object page = this;
		JspWriter _jspx_out = null;
		PageContext _jspx_page_context = null;

		try {//System.out.println("ionside try");
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
				out.print("You are not authorised to Modify the Company Details");									// login page.
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
		       String comp_mdate=dd+"-"+mm+"-"+yyyy;

				String comp_id = request.getParameter("comp_id").trim();
				comp_id = comp_id.substring(comp_id.lastIndexOf("-")+1);
				String comp_name = request.getParameter("comp_name").trim();
				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("username");
				//System.out.println("comp_id="+comp_id);
				try
				{
		        //Class.forName(driver);
				con= dbConn.getConnection();
		//con = DriverManager.getConnection(url,user,pwd); 
		 

			    ps1= con.prepareStatement("update comp_mstr set comp_name=?,comp_mdate=to_date(?,'dd-mm-rrrr'),comp_mby=? where comp_id=?");
				//update comp_mstr set comp_name=?,comp_mdate=to_date(?,'dd-mm-rrrr'),comp_bnfryname=?,comp_accno=?,comp_bank=?,comp_bankbranch=?,comp_bankbranchaddr=?,comp_ifsccode=?,comp_swiftcode=?,comp_panno=?,comp_staxno=?,comp_mby=?, where comp_id=?
				ps1.setString(1,comp_name);
				//ps1.setString(3,comp_mdate);
				ps1.setString(2,comp_mdate);
				ps1.setString(3,curhuma_id);
				ps1.setString(4,comp_id);
						
				f=ps1.executeUpdate();
				if(f!=0)
				 out.print("OKCompany master updated successfully");
				else
				 out.print("Company master is not updated for some reasons");
			    	
			 }//try 
		   catch(Exception e) { e.printStackTrace();} 
		   finally 
			{
			 if(st!=null)st.close();
			 if(ps!=null)ps.close();
			 if(rs!=null)rs.close();
			 if(st1!=null)st1.close();
			 if(ps1!=null)ps1.close();
			 if(rs1!=null)rs1.close();
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
