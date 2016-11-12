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

public final class Ugradem extends HttpServlet {

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
				out.print("You are not authorised to Modify the Grade Details");									// login page.
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
		       String grade_mdate=dd+"-"+mm+"-"+yyyy;

				String grade_id = request.getParameter("grade_id").trim(); 
				grade_id = grade_id.substring(grade_id.lastIndexOf("-")+1);
				String grade_name = request.getParameter("grade_name").trim();
				//grade_name = grade_name.substring(grade_name.lastIndexOf("-")+1);
				//System.out.println("grade_name="+grade_name);
				String grade_abbreviation = request.getParameter("grade_abbreviation").trim();
				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("username");		
				String area_strategy = request.getParameter("area_strategy").trim();
				try
				{
		        //Class.forName(driver);
				con= dbConn.getConnection();
		//con = DriverManager.getConnection(url,user,pwd); 
		 

				//ps = con.prepareStatement("select * from grade_mstr where grade_id=?");
				//ps.setString(1,grade_id);
				
				//rs = ps.executeQuery();
				//if(rs==null)
		         //System.out.println("result set is null");
		   		//if((rs.next())==false)
				//{   
		       
			    ps1= con.prepareStatement("update grade_mstr set grade_name=?,grade_abbreviation=?,grade_mdate=to_date(?,'dd-mm-rrrr'),grade_mby=?,GRADE_LEVEL=? where grade_id=?");
				ps1.setString(1,grade_name);
				ps1.setString(2,grade_abbreviation);
				ps1.setString(3,grade_mdate);
				ps1.setString(4,curhuma_id);		
				ps1.setString(5,area_strategy);
				ps1.setString(6,grade_id);
				f=ps1.executeUpdate();
				if(f!=0)
					out.println("OKGrade master Updated successfully");//Grade master Updated successfully
				else
					out.println("Grade master is not Updated for some reasons");
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
