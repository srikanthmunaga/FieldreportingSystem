package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

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

/**
 * Servlet implementation class sstatm
 */
public class sstatm extends HttpServlet {

	   Connection con=null;
	   PreparedStatement ps_id=null,ps=null,ps1=null,ps2=null;
	   ResultSet rs_id=null,rs=null,rs1=null,rs2=null;
	   int f=0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
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
				out.print("You are not authorised to Create a Stationary");									// login page.
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
		       String stationary_cdate=dd+"-"+mm+"-"+yyyy;

				String stationary_id = "";//request.getParameter("country_id"); 
				String stationary_name = request.getParameter("stationary_name").trim();
				String opening_stock = request.getParameter("opening_stock").trim();
				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("username");		
				try
				{
		        //Class.forName(driver);
				con= dbConn.getConnection();
		//con = DriverManager.getConnection(url,user,pwd); 
		 
				
				//Automatic id generation code starts here to work with the multiuser on this form @ same time-----------------------	
			   ps_id = con.prepareStatement("select s_id from stationary_mstr where s_id=(select max(s_id) from stationary_mstr)");
				rs_id = ps_id.executeQuery();		
				if(rs_id==null) 
				{
					System.out.println("rs is null");
				}
				if(rs_id.next()){ 
				do
				{
					
				int n=Integer.parseInt(rs_id.getString(1));
				 n=n+1;
				 if(n<=9)
					stationary_id="00"+n;
				 if((n>=10) && (n<=99))
					stationary_id="0"+n; 
				 if((n>=100) && (n<=999))
					stationary_id=""+n;
				 if(n>999)//if(n>9999)
					throw new Exception(); 
				 }while(rs_id.next());//while
				}//if
				else 
				 stationary_id="001";
				//Automatic id generation code ends here ------------------------------------------------------------------------------
				System.out.println("stationary_id:"+stationary_id);
				//System.out.println("hey the country_id="+country_id);
				ps = con.prepareStatement("select s_id from stationary_mstr where s_id=?");
				ps.setString(1,stationary_id);
				
				rs = ps.executeQuery();
				if(rs==null)
		         System.out.println("result set is null");
		   		if((rs.next())==false)
				{   
		       
			    /*ps1= con.prepareStatement("insert into stationary_mstr values(?,country_seqid.nextval,?,?,to_date(?,'dd-mm-rrrr'),to_date(?,'dd-mm-rrrr'),?,?)");*/
		   		//ps1 = con.prepareStatement("insert into stationary_mstr (stationary_id,stationary_name,opening_stock,closing_stock,date_of_entry)values(?,?,?,?,to_date(?,'dd-mm-rrrr'))");
		   		ps1= con.prepareStatement("insert into stationary_mstr(s_id,s_name,s_cdate,s_opening_stock) values(?,?,to_date(?,'dd-mm-rrrr'),?)");
		   		//ps1= con.prepareStatement("insert into stationary_mstr values(?,?,to_date(?,'dd-mm-rrrr'),?)");
				ps1.setString(1,stationary_id);
				ps1.setString(2,stationary_name);
				ps1.setString(3,stationary_cdate);
				ps1.setString(4,opening_stock);
				
				
				
				/*ps1.setString(5,null);
				ps1.setString(6,curhuma_id);
				ps1.setString(7,null);*/				
				f=ps1.executeUpdate();
				if(f!=0)
					out.println("OK Stationary master created successfully & Stationary_Id= "+stationary_id);
				else
					out.println("Stationary master is not created for some reasons");
						
		     	ps1.close();
				}
				else
					out.println("Already existing one.To differ press refresh and continue");
			
			 }//try 
		   catch(Exception e) { e.printStackTrace();} 
		   finally 
			{
			 if(ps_id!=null)ps_id.close();
			 if(rs_id!=null)rs_id.close();
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



