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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public final class UpdateStationary extends HttpServlet {

	   Connection con=null;
	   PreparedStatement ps=null,ps1=null,ps2=null,ps3=null,ps4=null,ps5=null,ps6=null,ps7=null,ps8=null,ps9=null,ps10=null;
	   ResultSet rs=null,rs2=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null,rs8=null,rs9=null,rs10=null;
	   int f=0,e=0; boolean f2=false;

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

				/*Connection dbConn=null;
				synchronized (request) {
					dbConn = (Connection) _jspx_page_context.getAttribute("dbConn", PageContext.REQUEST_SCOPE);
					if (dbConn == null) {
						dbConn = new JdbcConnect().getConnection();
						_jspx_page_context.setAttribute("dbConn", dbConn,PageContext.REQUEST_SCOPE);
					}
				}*/
				
				con=new JdbcConnect().getConnection();
				String driver = application.getInitParameter("driver");
				String url = application.getInitParameter("url");
				String user = application.getInitParameter("user");
				String pwd = application.getInitParameter("pwd");
				Calendar ca1 =Calendar.getInstance();//from here four lines are the system date selection code
		          int dd=ca1.get(Calendar.DATE);
		          int mm=ca1.get(Calendar.MONTH)+1; // In Current date Add 1 in month
		          int yyyy=ca1.get(Calendar.YEAR);
		       String UHLOG_cdate=dd+"-"+mm+"-"+yyyy;
			//System.out.println("the parameters going to read");
		       	String username=(String) request.getSession().getAttribute("username");
				String BSFLUNIT_UCODE = request.getParameter("bsflunit_ucode").trim();			//System.out.println(BSFLUNIT_UCODE);
				System.out.println("The unit code is "+BSFLUNIT_UCODE);
				String unitcode=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf("-")+1);
				String unit_code=unitcode;
				System.out.println("unit code= "+unit_code);
				String unitname = request.getParameter("unit_name").trim();
				System.out.println("The unit name is "+unitname);
				String date = request.getParameter("date_of_indent").trim();
				System.out.println("The date of indent is "+date);
				String indentby = request.getParameter("in_by");
				System.out.println("Indent is dene by "+indentby);
				String preby = request.getParameter("p_by");
				System.out.println("The indent is prepared by "+preby);
				String sno[] = request.getParameterValues("sno");
				String stid[] = request.getParameterValues("st_id");    
				String stname[] = request.getParameterValues("st_name");    		
				String closingstock[] = request.getParameterValues("closing_stock");    
				String newstock[] = request.getParameterValues("new_stock"); 
				for(int i=0;i<sno.length;i++)
				{
					//-------------------------trim() for loop parameters--------------------
					sno[i] = sno[i].trim();
					stid[i] = stid[i].trim();
					stname[i] = stname[i].trim();
					closingstock[i] = closingstock[i].trim();
					newstock[i] = newstock[i].trim();
				//	UHLOG_ODCUST[i] = UHLOG_ODCUST[i].trim();
				//	UHLOG_REMARKS[i] =  UHLOG_REMARKS[i].trim();
					//-------------------------trim() for loop parameters--------------------
				}	
				
				/*
				 * Added by rajesh 
				 * code for server side validation to implement user roles 
				 * 
				 * 
				 */
				
				String huma_id=request.getSession().getAttribute("huma_id").toString();
				String Username=request.getSession().getAttribute("username").toString();
				String role=request.getSession().getAttribute("userrole").toString();
				//System.out.println("In suhlogm.java");
				
				String area1="";
				String area2="";
				String area3="";
				//String area=request.getParameter("area_name").trim();
				//area=area.substring(area.lastIndexOf("-")+1);
				//String unit=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf("-")+1);
				//System.out.println("huma_id="+huma_id+" UserName="+Username+" role="+role+" area="+area+" unit="+unit);
				////System.out.println("inside the suhlogm.java and area= "+area);
				//Connection areacon=new JdbcConnect().getConnection();
				//Statement areast=con.createStatement();
				//For AreaHead
				//ResultSet arears;
				//String result="false";
				//if(role.equals("unithead")){
					//arears=areast.executeQuery("select area_id from area_mstr where huma_id='"+huma_id+"'");
					//System.out.println("In UnitHead role");
				//	arears=areast.executeQuery("select bsflunit_ucode from bsflunit_mstr where huma_id='"+huma_id+"'");
				//	if(arears!=null)
				//	  {
						//System.out.println("rs is not null");
					//     while(arears.next())
					  //     {
					    	 
					    //	 area1=arears.getString(1);
					   // 	 if(area1.equals(unit))
					   // 	 {
					    //		 result="true";
					    	//	 break;
					    	 //}//if
					    	 //else
					    	 //{
					    		// result="false";
					    	 //}//else
					     //  }//while
					  //}//if
					//********************************
					//System.out.println("area1="+area1);
					//if(!area1.equals(unit))
				/*	if(!result.equals("true"))
					{
						//System.out.println("area3 and area2 are not equal");
						out.println("You are not allowed to Update the data of another unit");
						return;
					}
					
					
				}
				
				if(role.equals("areahead")){
					
					//********************************
					arears=areast.executeQuery("select area_id from bsflunit_mstr where bsflunit_ucode='"+unit+"'");
					if(arears!=null)
					  {
						//System.out.println("rs is not null");
					     while(arears.next())
					       {
					    	 
					    	 area2=arears.getString(1);
					       }//while
					  }//if
					arears=areast.executeQuery("select area_id from area_mstr where huma_id='"+huma_id+"'");
					if(arears!=null)
					  {
						//System.out.println("rs is not null");
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
						//System.out.println("area3 and area2 are not equal");
						out.println("You are not allowed to Update the data of another Region");
						return;
					}
					
				}
				*/
				//*******************************************
				/*
				String huma_id=request.getSession().getAttribute("huma_id").toString();
				String Username=request.getSession().getAttribute("username").toString();
				String role=request.getSession().getAttribute("userrole").toString();
				String area1="";
				String area2="";
				String area3="";
				String area=request.getParameter("area_name").trim();
				area=area.substring(area.lastIndexOf("-")+1);
				////System.out.println("inside the suhlogm.java and area= "+area);
				Connection areacon=new JdbcConnect().getConnection();
				Statement areast=areacon.createStatement();
				//For AreaHead
				ResultSet arears=areast.executeQuery("select area_id from area_mstr where huma_id='"+huma_id+"'");
				if(arears!=null)
				  {
					System.out.println("rs is not null");
				     while(arears.next())
				       {
				    	 //System.out.println("inside the while loop");
				    	 area1=arears.getString(1);
				       }//while
				  }//if
				//for UnitHead
				
				arears=areast.executeQuery("select area_id from bsflunit_mstr where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='"+huma_id+"')");
				if(arears!=null)
				  {
					System.out.println("rs is not null");
				     while(arears.next())
				       {
				    	 //System.out.println("inside the while loop");
				    	 area3=arears.getString(1);
				       }//while
				  }//if
				
				//To get the area_id of the entered unitno
				String chkunit=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf("-")+1);
				arears=areast.executeQuery("select area_id from bsflunit_mstr where bsflunit_ucode='"+chkunit+"'");
				if(arears!=null)
				  {
				     while(arears.next())
				       {
				    	 area2=arears.getString(1);
				       }//while
				  }//if
				//System.out.println("inside the suhlogm and area1= "+area1+" area2= "+area2+" area3= "+area3);
				if(role.equals("areahead"))
				{
					////System.out.println("inside the areahead role");
				//if(area1!=area2)
					if(!area1.equals(area2))
				{
					//System.out.println("area1 and area2 are not equal");
					out.println("You are not allowed to do entry of another unit");
					return;
				}
				}//areahead
				if(role.equals("unithead"))
				{
					//System.out.println("inside the unithead role");
					if(!area3.equals(area2))
					{
						//System.out.println("area3 and area2 are not equal");
						out.println("You are not allowed to do entry of another unit");
						return;
					}
					}//unithead	
				*/
				
				
				//********************************************
				/*  
				 * code completed
				 * 
				 */
				String UHLOG_CONTROLENO2 = request.getParameter("UHLOG_CONTROLENO2");
				//dynamic cields need to checked for their existiance but the common fields can be directly caught in exeception
				String dates[];//used to get the UHLOG_desc
				String curBSFLUNIT_UCODE=(String)((HttpServletRequest) request).getSession().getAttribute("user");
				//System.out.println("hey after reading each insertion repeated parameters");
				/*
				int n1=activitysl_id.length;
				int n2=UHLOG_services.length;
				int n3=UHLOG_place.length;//here n1=n2=n3
				//System.out.println("hey the activitysl_id length length is="+n1);
				//System.out.println("hey the UHLOG_place length length is="+n1);*/
				String uno=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-')+1);
			//	Connection svcon=new JdbcConnect().getConnection();
				String svsql="select * from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"+uno+"'";
				Statement svst=con.createStatement();
				ResultSet svrs=svst.executeQuery(svsql);
				if(!svrs.next())
				{
					out.println("Entered Unit does not Exist ");
					return;
				}

				
				for(int i=0;i<sno.length;i++)
				{
					String statid=stid[i];
					//statid=statid.substring(vcode.lastIndexOf('-')+1);
					//Connection svcon1=new JdbcConnect().getConnection();
					String svsql1="select * from stationary_mstr where s_id='"+statid+"'";
					Statement svst1=con.createStatement();
					ResultSet svrs1=svst1.executeQuery(svsql1);
					if(!svrs1.next())
					{
						out.println("Entered stationary id does not Exist");
						return;
					}
					
					
				}
				
				try
				{
		//Query to delete all the existing rows with current UHLOG_controleno
		        ps6= con.prepareStatement("delete from stockindent where controlno=?");//delete from uhlog_service where BSFLUNIT_UCODE=? and UHLOG_DATE=to_date(?,'dd-mm-yyyy')/deleting existing rows.
				ps6.setString(1,UHLOG_CONTROLENO2);//ps6.setString(2,UHLOG_DATE);
				f2=ps6.execute();//deleting the previous rows of corrspnding person & UHLOG_DATE(here fdate/tdate can be used,both are uniques)
				
				String Status="N";
				ps1= con.prepareStatement("insert into stockindent(SEQID,S_ID,S_NAME,CLO_STOCK,NEW_STOCK,BSFLUNIT_UCODE,BSFLUNIT_NAME,IN_BY,PRE_BY,DATE_OF_INDENT,CONTROLNO,STATUS)VALUES(INDENTSEQUENCE.nextval,?,?,?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'),?,?)");
				for(int i=0; i<stid.length; i++){
				    ps1.setString(1,stid[i]);
					
				    ps1.setString(2,stname[i]);
				   // ps1.setString(3,sno[i]);
					ps1.setString(3, closingstock[i]);
					ps1.setString(4, newstock[i]);
					ps1.setString(5, unitcode);
					ps1.setString(6, unitname);
					ps1.setString(7, indentby);
					ps1.setString(8, preby);
					ps1.setString(9, date);
					ps1.setString(10,UHLOG_CONTROLENO2);
					ps1.setString(11,Status);
					f=ps1.executeUpdate();
					System.out.println(f);
					System.out.println("inserted succesfully");
				}
				////System.out.println("Msr debug after executing-uuhlogm");

				

				if(f!=0)
					out.println("OK Unit indent updated successfully");//fae master created successfully
				else
				    out.println("Unit indent is not updated for some reasons");
									
		     	ps1.close();
			 }//try 
		 catch(SQLException sqle) 
		 { 
		    if(sqle.getErrorCode()==2292)out.println("UH Activity Entry cannot be Updated : Child record found for the -UH Activity Entry id"); 
			else if(sqle.getErrorCode()==1)
			{ //UHLOG_HUMA_ACTIVITYSL_PLACEUQ
			/*	if(sqle.getMessage().split("_")[1].trim().equals("HUMA) violated - parent key not found"))
					out.println("Entered Employee id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("ACTIVITYSL) violated - parent key not found"))
					out.println("Entered Activity does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("VILLAGE) violated - parent key not found"))
					out.println("Entered Village id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("BLOCK) violated - parent key not found"))
					out.println("Entered Block id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("SUBUNIT) violated - parent key not found"))
					out.println("Entered Subunit id does not Exist");
				else */out.println("Duplicate Farmer Activity Entries found,Pls differ either Activity or Place");
			}//else if(sqle.getErrorCode()==1)
			else if(sqle.getErrorCode()==2291)
			 { sqle.printStackTrace();
			 	out.println("the error msg="+sqle.getMessage()+"A");//.split(".")[1].trim(); out.println("the error msg="+sqle.getMessage().split(".")[1].trim()+"A");
				if(sqle.getMessage().split("_")[1].trim().equals("HUMA) violated - parent key not found"))
					out.println("Entered Employee id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("ACTIVITYSL) violated - parent key not found"))
					out.println("Entered Activity does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("VILLAGE) violated - parent key not found"))
					out.println("Entered Village id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("BLOCK) violated - parent key not found"))
					out.println("Entered Block id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("SUBUNIT) violated - parent key not found"))
					out.println("Entered Subunit id does not Exist");
				else out.println("Other integrity constraint related exception or error");
			 }//else if(sqle.getErrorCode()==2291)
			else sqle.printStackTrace();
			}//catch(SQLException sqle) 
		   catch(Exception e) { e.printStackTrace();} 
		   finally 
			{
			 if(rs!=null)rs.close();
		     if(ps!=null)ps.close();
			 if(ps1!=null)ps1.close();
			 if(ps2!=null)ps2.close();
			 if(ps3!=null)ps3.close();
			 if(rs2!=null)rs2.close();
			 if(rs3!=null)rs3.close();
		     if(ps4!=null)ps4.close();
			 if(rs4!=null)rs4.close();
			 if(ps5!=null)ps5.close();
			 if(rs5!=null)rs5.close();
			 if(ps6!=null)ps6.close();
			 if(rs6!=null)rs6.close();
			 if(ps7!=null)ps7.close();
			 if(rs7!=null)rs7.close();
			 if(ps8!=null)ps8.close();
			 if(rs8!=null)rs8.close();
			 if(ps9!=null)ps9.close();
			 if(rs9!=null)rs9.close();
			 if(ps10!=null)ps10.close();
			 if(rs10!=null)rs10.close();
		     if(con!=null)con.close();
		     //if(dbConn!=null)dbConn.close();
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
