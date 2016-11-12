package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

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

/**
 * Servlet implementation class Recovery
 */
public class Recovery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	String Unitname;
	String time;
	int recovery; 
	JdbcConnect js=new JdbcConnect();
	Connection con=null;
	PreparedStatement ps = null;
	
	
	public Recovery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("Msr debug");
		JspFactory _jspxFactory = null;
		PageContext pageContext = null;
		HttpSession session = null;
		ServletContext application = null;
		ServletConfig config = null;
		JspWriter out = null;
		Object page = this;
		JspWriter _jspx_out = null;
		PageContext _jspx_page_context = null;
		String BSFLUNIT_UCODE=request.getParameter("unit_id");
		String unitcode=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf("-")+1);
		String unitname=BSFLUNIT_UCODE.substring(0,BSFLUNIT_UCODE.lastIndexOf("-"));
		System.out.println(unitcode);
		System.out.println(unitname);
		try {
			////System.out.println("Msr debug2");
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
			con=js.getConnection();
			String hh=request.getParameter("hh");
			String mm=request.getParameter("mm");
			time=hh+"-"+mm;
			System.out.println("Before validating " +time);
			if(request.getParameter("totrecovery").trim()=="")
			{
				out.println("Please Enter Recovery Amount");
			}
			if(hh.equals("HH"))
			{
				System.out.println("am i hh");
				out.println("Please Select Hours");
			}
			if(mm.equals("MM"))
			{
				System.out.println("am i mm");
				out.println("Please Select Minutes");
			}
			Calendar ca1 =Calendar.getInstance();//from here four lines are the system date selection code
	          int dd=ca1.get(Calendar.DATE);
	          int mm2=ca1.get(Calendar.MONTH)+1; // In Current date Add 1 in month
	          int yyyy=ca1.get(Calendar.YEAR);
	       String bsflunit_cdate=dd+"-"+mm2+"-"+yyyy;

			System.out.println("After validating");
			recovery=Integer.parseInt(request.getParameter("totrecovery"));
			HttpSession hs=request.getSession(false);
			String unitcode1=(String) hs.getAttribute("unitcode");
			Unitname=(String) hs.getAttribute("unitname");
			RequestDispatcher adminrd=getServletContext().getRequestDispatcher("/recoverysucc.jsp");
			hs.setAttribute("recovery", request.getParameter("totrecovery"));
			hs.setAttribute("time",time);
			String sql="insert into sixtyrecovery(BSFLUNIT_UCODE,BSFLUNIT_NAME,TotRecovery,time,SIXTYRECOVERY_CBY) values(?,?,?,?,?)";
			////System.out.println("Msr debug1");
			
			ps=con.prepareStatement(sql);
			ps.setString(1,unitcode);
			ps.setString(2,unitname);
			ps.setInt(3,recovery);
			ps.setString(4,time);
			//ps.setString(5,bsflunit_cdate);
			System.out.println(new String((String) request.getSession().getAttribute("username")));
			ps.setString(5,new String((String) request.getSession().getAttribute("username")));
			Boolean status=ps.execute();
			System.out.println("After saving "+status);
			if(status==false)
				adminrd.include(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{ try{
			 if(ps!=null)ps.close();
			 if(con!=null)con.close();
		}
				 catch (SQLException e) {
					e.printStackTrace();
				} 
		}//finally
		
		
		System.out.println(recovery+ "\t" +time);
	}

}
