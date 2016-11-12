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

import org.jasypt.digest.StandardStringDigester;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public final class SchangePwdm extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Connection con=null;
	   PreparedStatement ps=null;
	   PreparedStatement ps1=null;
	   ResultSet rs=null;
	   int f=0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("inside the doGet method of schangePwdm");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 //System.out.println("inside the doPost method of schangePwdm");

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

				String driver = application.getInitParameter("driver");
				String url = application.getInitParameter("url");
				String user = application.getInitParameter("user");
				String pwd = application.getInitParameter("pwd");
				Calendar ca1 =Calendar.getInstance();//from here four lines are the system date selection code
		          int dd=ca1.get(Calendar.DATE);
		          int mm=ca1.get(Calendar.MONTH)+1; // In Current date Add 1 in month
		          int yyyy=ca1.get(Calendar.YEAR);
		        String mdate=dd+"-"+mm+"-"+yyyy;

				String emp_id = request.getParameter("eno").trim();//.toUpperCase();
				String pws = request.getParameter("pwd1").trim();//.toUpperCase();
				StandardStringDigester digester = new StandardStringDigester();
				digester.setAlgorithm("SHA-1");   // optionally set the algorithm
				digester.setIterations(50000);  // increase security by performing 50000 hashing iterations
				String digest = digester.digest(pws);
				System.out.println("emp_id="+emp_id+" pws="+pws+" EPassword="+digest);
				//String log = request.getParameter("logtype").trim();

				
				
				try
				{
		        //Class.forName(driver);
				con= dbConn.getConnection();
		//con = DriverManager.getConnection(url,user,pwd); 
		 

				ps = con.prepareStatement("update FRS_USER set PASSWORD=?,EPASSWORD=? where USERNAME=?");
				ps.setString(1,pws);
				ps.setString(2,digest);
				ps.setString(3,emp_id);
				f = ps.executeUpdate();
				if(f!=0)
					out.println("OK Password changed successfully");//Password changed successfully
				else
					out.println("Password is not changed for some reasons");
				
			
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
