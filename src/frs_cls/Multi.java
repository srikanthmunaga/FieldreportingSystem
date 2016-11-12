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
import java.util.StringTokenizer;

public final class Multi extends HttpServlet {

	   Connection con=null;
	   PreparedStatement ps=null;
	   PreparedStatement ps1=null,ps2=null;
	   ResultSet rs=null,rs2=null;
	   int f=0;
	   int f2=0;
	   int g=0;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// //System.out.println("inside the doGet method");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside the doPost method");

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
			System.out.println(ur);

			if (((HttpServletRequest) request).getSession()
					.getAttribute("username") == null) {
				response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to
													// login page.
			}
			else if (ur.equals("user") || ur.equals("fs") || ur.equals("fx")) {
				out.print("You are not authorised to Create a Indent");									// login page.
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
		       String bsflunit_cdate=dd+"-"+mm+"-"+yyyy;

				String bsflunit_ucode = request.getParameter("bsflunit_ucode"); //common for each insertion
				System.out.println(bsflunit_ucode);
				String bsflunit_name = request.getParameter("unit_name");
				System.out.println(bsflunit_name);
				//String sta_id=request.getParameter("st_id").trim();
				//System.out.println("stationary id is :"+sta_id);
				//String sta_name = request.getParameter("sta_name");
				String doi = request.getParameter("date_of_indent").trim();
				System.out.println(doi);
				//String open_stock = request.getParameter("o_stock");
				//String close_stock = request.getParameter("c_stock");
				//String new_stock = request.getParameter("n_stock");
				String inde_by = request.getParameter("in_by");
				System.out.println(inde_by);
				String pre_by = request.getParameter("p_by");
				System.out.println(pre_by);
				String unit_code=null;
				String unitcode=null;
				unitcode=bsflunit_ucode.substring(bsflunit_ucode.lastIndexOf("-")+1);
				unit_code=unitcode;
				System.out.println("unit code= "+unit_code);
				
				String sno[] = request.getParameterValues("sno");
				String staid[] = request.getParameterValues("st_id");
				String staname[] = request.getParameterValues("st_name");
				String closingstock[] = request
						.getParameterValues("closing_stock");
				String newstock[] = request
						.getParameterValues("new_stock");
				//String Description[] = request
					//	.getParameterValues("Description");
				
				for (int i = 0; i < sno.length; i++) {
					sno[i] = sno[i].trim();
					System.out.println(sno[i]);
					staid[i] = staid[i].trim();
					System.out.println(staid[i]);
					staname[i] = staname[i].trim();
					System.out.println(staname[i]);
					closingstock[i] = closingstock[i].trim();
					System.out.println(closingstock[i]);
					newstock[i] = newstock[i].trim();
					System.out.println(newstock[i]);
				}
					//Description[i] = Description[i].trim();
				try {
			        //Class.forName(driver);
					con= dbConn.getConnection();
					System.out.println();
					int x = 0;
					ps = con.prepareStatement("select bsflunit_ucode from bsflunit_mstr where bsflunit_ucode=?");
					//ps.setString(1, comp_id.substring(0, 4));
					ps.setString(1, unit_code);
					rs = ps.executeQuery();
					if (rs == null)
						System.out.println("result set is null in ubm.jsp");
					if ((rs.next()) == false)
						x = x + 1; System.out.println("hey the x="+x);
					
					String sql="select bsflunit_name from bsflunit_mstr where bsflunit_ucode='"
							+ unit_code + "'";
					Statement st=con.createStatement();
					ResultSet rs = st.executeQuery(sql);
					if (rs != null) {
						while (rs.next()) {
							
							bsflunit_name = rs.getString(1);
						}
						System.out.println("the name is"+ bsflunit_name);
						session.setAttribute("unit_name", bsflunit_name);
//					ps = con.prepareStatement("select * from BSFLUNIT_MSTR where bsflunit_ucode=?");//select * from BSFLUNIT_MSTR where upper(bsflunit_ucode)= upper('business development')
//					ps.setString(1,bsflunit_ucode.substring(0,3));
//					
//					rs = ps.executeQuery();
//					if(rs==null)
//			         System.out.println("result set is null");
//					int r=1;
//					if ((rs.next()) == false)
//						//r = r + 1;
//						r=0;
//					System.out.println("R value="+r);
//					//Added By Rajesh
//					int p = 0;
//					ps = con.prepareStatement("select area_ID from area_MSTR where area_ID=?");
//					ps.setString(1, area);
//					rs = ps.executeQuery();
//					if (rs == null)
//						System.out.println("result set is null in ubm.jsp");
//					if ((rs.next()) == false)
//						p = p + 1;
//					
//					System.out.println("Value of P="+p);
//					
//					
//					int q = 0;
//					ps = con.prepareStatement("select USERNAME from FRS_USER where HUMA_ID=?");
//					//String humaaid=humaid.substring(humaid.lastIndexOf("-")+1);
//					ps.setString(1, humaid);
//					//ps.setString(1, humaaid);
//					rs = ps.executeQuery();
//					if (rs == null)
//						System.out.println("result set is null in ubm.jsp");
//					if ((rs.next()) == false)
//						q = q + 1;
//					
//					System.out.println("Value of q="+q);
//					
//					//Rajesh complete
//			   		//if(((rs.next())==false)&&(p == 0)&&(q == 0))
						 										
					if((x==0))
					{
					con.setAutoCommit(false);
//			        System.out.println("HERE R P Q are zero");		
//					ps=con.prepareStatement("select unit_code from unit_msrt where ")
				    ps1= con.prepareStatement("insert into unit_inde(BSFLUNIT_UCODE,BSFLUNIT_NAME,DATE_OF_INDENT,INDE_BY,PREP_BY)values(?,?,TO_DATE(?,'DD-MM-YYYY'),?,?)");
					//for(int i=0; i<sno.length; i++){
						//ps1.setString(1,bsflunit_ucode);
						//ps1.setString(2, bsflunit_name);
						//ps1.setString(3, staid[i]);
						//ps1.setString(4, staname[i]);
						//ps1.setString(3,doi);
						//ps1.setString(4,inde_by);
						//ps1.setString(5,pre_by);
						//ps1.setString(6, closingstock[i]);
						//ps1.setString(7, newstock[i]);
						//ps1.setString(8, inde_by);
						//ps1.setString(9, pre_by);
				    ps1.setString(1,unit_code);
					ps1.setString(2,bsflunit_name);
					ps1.setString(3,doi);
					ps1.setString(4,inde_by);
					ps1.setString(5,pre_by);
					}    
					 
					int f=ps1.executeUpdate();
					System.out.println(f);
					//System.out.println("hey the PreparedStatement made");
					
					
					
					ps2=con.prepareStatement("insert into unit_data23(BSFLUNIT_UCODE,SNO,S_ID,S_NAME,CLOS_STOCK,NEW_STOCK)VALUES(?,?,?,?,?,?)");
					for(int i=0; i<sno.length; i++){
						ps2.setString(1,bsflunit_ucode);
						ps2.setString(2, sno[i]);
						ps2.setString(3, staid[i]);
						ps2.setString(4, staname[i]);
						ps2.setString(5, closingstock[i]);
						ps2.setString(6, newstock[i]);
						f2=ps2.executeUpdate();
						//System.out.println("inserted");
						//ps1.setString(8, inde_by);
						//ps1.setString(9, pre_by);
					}
					
					System.out.println(f2);
					
					if((f!=0) &&(f2!=0)){  
						con.commit();
			            
			            out.println("OKindent has been sucessfully placed");  


			          }  
			          else{  
			            
			            out.println("indent has not been placed for different reasons");
			           
			          }
					} 
					else if(x!=0)
						out.println("entered unitcode doesnot exist");
					
			     	ps1.close();
			      ps.close();
					
		
					
					
				
				}//try 
			   catch(Exception e) { e.printStackTrace();} 
			   
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
