package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class uhconnupdate
 */
public class uhconnupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   Connection con=null;
	   PreparedStatement ps=null,ps1=null,ps2=null,ps3=null,ps4=null,ps5=null,ps6=null,ps7=null,ps8=null,ps9=null,ps10=null;
	   ResultSet rs=null,rs2=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null,rs8=null,rs9=null,rs10=null;
	   int f=0,e=0; boolean f2=false;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public uhconnupdate() {
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
					.getAttribute("user") == null) {
				response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to
													// login page.
			}

			else // if (((HttpServletRequest)
					// request).getSession().getAttribute("user") != null)
			{
				// chain.doFilter(request, response); // Logged in, so just
				// continue.

				Connection dbConn = null;
				synchronized (request) {
					dbConn = (Connection) _jspx_page_context.getAttribute(
							"dbConn", PageContext.REQUEST_SCOPE);
					if (dbConn == null) {
						dbConn = new JdbcConnect().getConnection();
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
		       String UHLOG_CDATE=dd+"-"+mm+"-"+yyyy;
			//System.out.println("the parameters going to read");
				String UHLOG_CONTROLENO2 = request.getParameter("UHLOG_CONTROLENO2"); //common for each insertion
		        String username=(String) request.getSession().getAttribute("username");
		        System.out.println(username);
				String uname = request.getParameter("uname");
				System.out.println(uname);
				String date = request.getParameter("cus_date"); 
				String aname = request.getParameter("aname"); 
				String vil[] = request.getParameterValues("village");
				String acts[] = request.getParameterValues("act");
				String subacts[] = request.getParameterValues("subact");
				String UHLOG_OUTREACH[] = request.getParameterValues("UHLOG_OUTREACH");    
				String UHLOG_ODAMT[] = request.getParameterValues("UHLOG_ODCUST");    		
				String UHLOG_ODCUST[] = request.getParameterValues("UHLOG_ODAMT");    
				String remarks[] = request.getParameterValues("remarks");    
				//System.out.println("Msr debug 5");
				//dynamic cields need to checked for their existiance but the common fields can be directly caught in exeception
/*				String dates[];//used to get the ops_desc
				String curhuma_id=(String)((HttpServletRequest) request).getSession().getAttribute("user");
*/				//System.out.println("hey after reading each insertion repeated parameters");
				/*
				int n1=ACTIVITY_ID.length;
				int n2=UHLOG_REMARKS.length;
				int n3=ops_place.length;//here n1=n2=n3
				//System.out.println("hey the ACTIVITY_ID length length is="+n1);
				//System.out.println("hey the ops_place length length is="+n1);*/
				
				try
				{//System.out.println("het inside the try");	
				//Class.forName(driver);
				con= dbConn;
		//con = DriverManager.getConnection(url,user,pwd); 
		 
		/*		//---------------------------------------controleno generation
				ps6 = con.prepareStatement("select nvl((select to_number(UHLOG_CONTROLENO+1) from UHLOG_SERVICE where UHLOG_CONTROLENO=(select max(UHLOG_CONTROLENO) from UHLOG_SERVICE)),1) as UHLOG_CONTROLENO from dual");
				rs6 = ps6.executeQuery();	
				rs6.next();
		String UHLOG_CONTROLENO =Integer.toString(rs6.getInt(1));// request.getParameter("UHLOG_CONTROLENO"); //common for each insertion
		//System.out.println("hey the e(UHLOG_CONTROLENO)="+UHLOG_CONTROLENO);
				//end of UHLOG_CONTROLENO generation code--------------------------------------------------------------*/
		//=========Newly added code to get ops_desc feils dynamically fae.jsp============================================
		    //dates=new String[2];  
			 //dates[0] =  UHLOG_DATE; dates[1]= ops_tdate; //System.out.println("dates.length="+dates.length); 
				String BSFLUNIT_UCODE="";
				int x4=0;// to checke the all 2 dates entered are whether having/refering any ops_desc/holiday year or not
				ps4 = con.prepareStatement("select distinct BSFLUNIT_UCODE from UHLOG_SERVICE p1 where BSFLUNIT_UCODE='"+BSFLUNIT_UCODE+"' and UHLOG_CONTROLENO!="+UHLOG_CONTROLENO2+" and UHLOG_DATE=to_date('"+date+"','dd-mm-yyyy')");//query to checke the all 2 dates entered are whether having/refering any ops_desc/already used period
				   //for(int i=0; i<dates.length; i++)
				    // { //System.out.println("het inside the for loop and the dates[i]="+dates[i]);
					  //	 ps4.setString(1,dates[i]);
				  		 rs4 = ps4.executeQuery();
				   		if((rs4.next())==false)
				    	 x4=x4+1;
					  //}//for(int i=0; i<2; i++)
				  //System.out.println("hey outside the for loop and the x4="+x4);
				if(x4==0)
				  {//data="NOYou conn't select a date that does not comes under any of holiday year period";
				   out.println("Already existing date ");
				   return;
				  }
		//==========================================================================================================
		//Checking ops_desc availability
		/*	  int x2=0;
			  ps2 = con.prepareStatement("select distinct ops_desc from UHLOG_SERVICE where ops_desc=? and UHLOG_CONTROLENO!=?");
			  ps2.setString(1,ops_desc);
			  ps2.setString(2,UHLOG_CONTROLENO2);
			  rs2 = ps2.executeQuery();
			  if((rs2.next())==false)
			    x2=x2+1;
			  if(x2==0)//entered field exists
			  {
			   out.println("Already existing UH Activity Entry description,pls change..");
			   return;//stops exeution here
			  }*/
		//Checking vbs_place(village,block,subunit) availability
			int x2=0,x3=0,x5=0,x7=0,x9=0,x10=0;
			ps2 = con.prepareStatement("select fpo_id from fpo_mstr where fpo_id=?");
			ps10 = con.prepareStatement("select pg_id from pg_mstr where pg_id=?");		
			ps9 = con.prepareStatement("select estbl_id from estbl_mstr where estbl_id=?");	
			ps3 = con.prepareStatement("select village_id from village_mstr where village_id=?");
			ps5 = con.prepareStatement("select block_id from block_mstr where block_id=?");
			ps7 = con.prepareStatement("select subunit_id from subBSFLUNIT_MSTR where subunit_id=?");
/*			for(int i=0; i<ops_place.length; i++)
				{//System.out.println("hey inside the for loop");
				//fpo_id existance checking
				if(fpo_id[i]!="")
				 { ps2.setString(1,fpo_id[i].substring(0,7));
				   rs2 = ps2.executeQuery();
				   if((rs2.next())==false) x2=x2+1;
				  }
				//pg_id existance checking
				if(pg_id[i]!="")
				 { ps10.setString(1,pg_id[i].substring(0,9));
				   rs10 = ps10.executeQuery();
				   if((rs10.next())==false) x10=x10+1;
				  }
				//estbl_id existance checking
				if(estbl_id[i]!="")
				 { ps9.setString(1,estbl_id[i].substring(0,7));
				   rs9 = ps9.executeQuery();
				   if((rs9.next())==false) x9=x9+1;
				  }
				//ops_place existing checking
				if(ops_place[i]!="")
				if(ops_place[i].substring(0,2).equals("VI"))
				 { ps3.setString(1,ops_place[i].substring(0,7));
				   rs3 = ps3.executeQuery();
				   if((rs3.next())==false) x3=x3+1;
				  }
				else if(ops_place[i].substring(0,2).equals("BL"))
				 { ps5.setString(1,ops_place[i].substring(0,7));
				   rs5 = ps5.executeQuery();
				   if((rs5.next())==false) x5=x5+1;
				  }
				else if(ops_place[i].substring(0,2).equals("SU"))
				 { ps7.setString(1,ops_place[i].substring(0,7));
				   rs7 = ps7.executeQuery();
				   if((rs7.next())==false) x7=x7+1;
				  }
				}//for(int i=0; i<ops_place.length; i++)
			  if(x2!=0)//entered field not exists
			  {
			   out.println("Entered FPO id does not Exist"); 
			   return;//stops exeution here
			  }
			  if(x10!=0)//entered field not exists
			  {
			   out.println("Entered PG id does not Exist"); 
			   return;//stops exeution here
			  }
			  if(x9!=0)//entered field not exists
			  {
			   out.println("Entered Establishment (Business Agencies) id does not Exist"); 
			   return;//stops exeution here
			  }
			  if(x3!=0)//entered field not exists
			  {
			   out.println("Entered Village id does not Exist"); 
			   return;//stops exeution here
			  }
			  if(x5!=0)//entered field not exists
			  {
			   out.println("Entered Block id does not Exist"); 
			   return;//stops exeution here
			  }
			  if(x7!=0)//entered field not exists
			  {
			   out.println("Entered Subunit id does not Exist"); 
			   return;//stops exeution here
			  }
*/		//Getting cdate,cby fields for current use		
				ps8 = con.prepareStatement("select distinct to_char(UHLOG_CDATE,'dd-mm-yyyy hh:mi:ss am'),UHLOG_CBY from UHLOG_SERVICE where UHLOG_CONTROLENO=?");//getting the cdate,cby  from previus rows
				ps8.setString(1,UHLOG_CONTROLENO2);
				rs8 = ps8.executeQuery();//System.out.println("hey going to next the rs8");		
				rs8.next();
		//Query to delete all the existing rows with current UHLOG_CONTROLENO
		        ps6= con.prepareStatement("delete from UHLOG_SERVICE where UHLOG_CONTROLENO=?");//delete from UHLOG_SERVICE where huma_id=? and UHLOG_DATE=to_date(?,'dd-mm-yyyy')/deleting existing rows.
				ps6.setString(1,UHLOG_CONTROLENO2);//ps6.setString(2,UHLOG_DATE);
				f2=ps6.execute();//deleting the previous rows of corrspnding person & UHLOG_DATE(here fdate/tdate can be used,both are uniques)
				/*
				if(!f2)
				 //System.out.println("Deleted Successfully");		
		    	else
			     //System.out.println("Deletion failed in  ");*/
				 
/*				 ps1= con.prepareStatement("insert into UHLOG_SERVICE values(?,ops_seqid.nextval,?,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,?,?,to_date(?,'dd-mm-yyyy hh:mi:ss am'),nvl(sysdate,to_date(?,'dd-mm-yyyy')),?,?,?,?,?,?)");//System.out.println("hey the PreparedStatement1 made");
				for(int i=0; i<ops_place.length; i++)
				{//System.out.println("hey inside the for loop");
				ps1.setString(1,huma_id.substring(0,4));//ps1.setString(2,ops_desc); //System.out.println("After assigning huma_id and ops_desc");
				ps1.setString(2,UHLOG_CONTROLENO2);
				ps1.setString(3,UHLOG_DATE);//ps1.setString(5,ops_tdate);
				ps1.setString(4,ops_sno[i]);	
				ps1.setString(5,ACTIVITY_ID[i]);
				if(fpo_id[i]!="") ps1.setString(6,fpo_id[i].substring(0,7)); else ps1.setString(6,fpo_id[i]);
				ps1.setString(7,null);
				ps1.setString(8,UHLOG_ODCUST[i]);		
				if(ops_place[i]!=""){
				if(ops_place[i].substring(0,2).equals("VI")){ps1.setString(9,ops_place[i].substring(0,7)); ps1.setString(10,null); ps1.setString(11,null);}
				else if(ops_place[i].substring(0,2).equals("BL")){ps1.setString(9,null); ps1.setString(10,ops_place[i].substring(0,7)); ps1.setString(11,null);}
				else if(ops_place[i].substring(0,2).equals("SU")){ps1.setString(9,null); ps1.setString(10,null); ps1.setString(11,ops_place[i].substring(0,7));}}
				else {ps1.setString(9,ops_place[i]); ps1.setString(10,ops_place[i]); ps1.setString(11,ops_place[i]);}		
				ps1.setString(12,UHLOG_REMARKS[i]);
				
				ps1.setString(13,rs8.getString(1));//UHLOG_CDATE parameter got from using last controleno
				ps1.setString(14,UHLOG_CDATE);//UHLOG_MDATE
				ps1.setString(15,rs8.getString(2));//Got cby using last controle no
				ps1.setString(16,curhuma_id);//UHLOG_MBY
				if(estbl_id[i]!="") ps1.setString(17,estbl_id[i].substring(0,7)); else ps1.setString(17,estbl_id[i]);		
				if(pg_id[i]!="") ps1.setString(18,pg_id[i].substring(0,9)); else ps1.setString(18,pg_id[i]);		
				ps1.setString(19,UHLOG_UHLOG_ODAMT[i].replaceAll(",", ""));
				ps1.setString(20,UHLOG_OUTREACH[i]);		
				 f=ps1.executeUpdate();
				}//for(int i=0; i<ops_place.length; i++)
*/								ps1= con.prepareStatement("insert into UHLOG_SERVICE(UHLOG_SEQID,UHLOG_DATE,UHLOG_VILSEQID,UHLOG_OUTREACH,UHLOG_UHLOG_ODCUST,UHLOG_UHLOG_ODAMT,UHLOG_MBY,ACTIVITY_ID,UHLOG_CONTROLENO,UHLOG_MDATE) values(UHLOGSERVICE.nextval,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,nvl(sysdate,to_date(?,'dd-mm-yyyy')))");
for(int i=0; i<vil.length; i++)
{
ps1.setString(1,date);//ps1.setString(2,ops_desc); //System.out.println("After assigning huma_id and ops_desc");
ps1.setString(2,vil[i]);
ps1.setLong(3,Integer.parseInt(UHLOG_OUTREACH[i]));
ps1.setLong(4,Integer.parseInt(UHLOG_ODCUST[i]));
ps1.setLong(5,Integer.parseInt(UHLOG_ODAMT[i]));
ps1.setString(6,username);
ps1.setLong(7,Integer.parseInt(acts[i]));
ps1.setString(8,UHLOG_CONTROLENO2);
//ps1.setString(8,"1330");
f=ps1.executeUpdate();
}

				if(f!=0)
					out.println("OKUH Activity Entry updated successfully");//fae master created successfully
				else
				    out.println("UH Activity Entry is not updated for some reasons");
									
		     	ps1.close();
			 }//try 
		 catch(SQLException sqle) 
		 { 
		    if(sqle.getErrorCode()==2292)out.println("UH Activity Entry cannot be Updated : Child record found for the -UH Activity Entry id"); 
			else if(sqle.getErrorCode()==1)
			{ //ops_HUMA_ACTIVITYSL_PLACEUQ
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
		     if(dbConn!=null)dbConn.close();

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
