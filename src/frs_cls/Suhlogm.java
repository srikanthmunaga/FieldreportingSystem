package frs_cls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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



public class Suhlogm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   Connection con=null;
	   PreparedStatement ps=null,ps2=null,ps1=null,ps3=null,ps4=null,ps5=null,ps6=null,ps7=null,ps8=null,ps9=null,ps10=null;
	   ResultSet rs=null,rs2=null,rs3=null,rs4=null,rs5=null,rs6=null,rs7=null,rs8=null,rs9=null,rs10=null;
	   int f=0,e=0;;
	   ArrayList<String> al;
	   ArrayList<String> al1;

    public Suhlogm() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		////System.out.println("Msr debug1");
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
/*
			if (((HttpServletRequest) request).getSession()
					.getAttribute("username") == null) {
				response.sendRedirect("frslogin.jsp");
			}

			else
*/				
			{
				////System.out.println("Msr debug 3");
				Connection dbConn=null;
				synchronized (request) {
					dbConn = (Connection) _jspx_page_context.getAttribute("dbConn", PageContext.REQUEST_SCOPE);
					if (dbConn == null) {
						dbConn = new JdbcConnect().getConnection();
						_jspx_page_context.setAttribute("dbConn", dbConn,PageContext.REQUEST_SCOPE);
					}
				}
				////System.out.println("Msr debug 4");
				Calendar ca1 =Calendar.getInstance();//from here four lines are the system date selection code
		          int dd=ca1.get(Calendar.DATE);
		          int mm=ca1.get(Calendar.MONTH)+1; // In Current date Add 1 in month
		          int yyyy=ca1.get(Calendar.YEAR);
		          con= dbConn;
		        String username=(String) request.getSession().getAttribute("username");
		        //System.out.println(username);
				String BSFLUNIT_UCODE = request.getParameter("BSFLUNIT_UCODE").trim();			//System.out.println(BSFLUNIT_UCODE);
				String UHLOG_DATE = request.getParameter("UHLOG_DATE").trim(); 
				//String area_name = request.getParameter("area_name").trim(); 
				String VCODE[] = request.getParameterValues("VCODE");
				String ACTIVITY_ID[] = request.getParameterValues("ACTIVITY_ID");
				String UHLOG_AMTSPENT[] = request.getParameterValues("UHLOG_AMTSPENT");
				String UHLOG_OUTREACH[] = request.getParameterValues("UHLOG_OUTREACH");    
				String UHLOG_ODCUST[] = request.getParameterValues("UHLOG_ODCUST");    		
				String UHLOG_ODAMT[] = request.getParameterValues("UHLOG_ODAMT");    
				String UHLOG_REMARKS[] = request.getParameterValues("UHLOG_REMARKS");    
				for(int i=0;i<VCODE.length;i++)
				{
					//-------------------------trim() for loop parameters--------------------
					VCODE[i] = VCODE[i].trim();
					ACTIVITY_ID[i] = ACTIVITY_ID[i].trim();
					UHLOG_AMTSPENT[i] = UHLOG_AMTSPENT[i].trim();
					UHLOG_OUTREACH[i] = UHLOG_OUTREACH[i].trim();
					UHLOG_ODAMT[i] = UHLOG_ODAMT[i].trim();
					UHLOG_ODCUST[i] = UHLOG_ODCUST[i].trim();
					UHLOG_REMARKS[i] =  UHLOG_REMARKS[i].trim();
					//-------------------------trim() for loop parameters--------------------
				}	
				String huma_id=request.getSession().getAttribute("huma_id").toString();
				String Username=request.getSession().getAttribute("username").toString();
				String role=request.getSession().getAttribute("userrole").toString();
				//System.out.println("In suhlogm.java");
				
				String area1="";
				String area2="";
				String area3="";
				String area=request.getParameter("area_name").trim();
				area=area.substring(area.lastIndexOf("-")+1);
				String unit=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf("-")+1);
				//System.out.println("huma_id="+huma_id+" UserName="+Username+" role="+role+" area="+area+" unit="+unit);
				////System.out.println("inside the suhlogm.java and area= "+area);
				//Connection areacon=new JdbcConnect().getConnection();
				Statement areast=con.createStatement();
				//For AreaHead
				ResultSet arears;
				String result="false";
				if(role.equals("unithead")){
					//arears=areast.executeQuery("select area_id from area_mstr where huma_id='"+huma_id+"'");
					//System.out.println("In UnitHead role");
					//arears=areast.executeQuery("select bsflunit_ucode from huma_mstr where huma_id='"+huma_id+"'");
					arears=areast.executeQuery("select bsflunit_ucode from bsflunit_mstr where huma_id='"+huma_id+"'");
					if(arears!=null)
					  {
						//System.out.println("rs is not null");
					     while(arears.next())
					       {
					    	 
					    	 area1=arears.getString(1);
					    	 if(area1.equals(unit))
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
					//********************************
					//System.out.println("area1="+area1);
					if(!result.equals("true"))
					{
						//System.out.println("area3 and area2 are not equal");
						out.println("You are not allowed to do entry of another unit");
						return;
					}
					
					
				}
				
				if(role.equals("areahead")){
					/*arears=areast.executeQuery("select area_id from area_mstr where huma_id='"+huma_id+"'");
					if(arears!=null)
					  {
						System.out.println("rs is not null");
					     while(arears.next())
					       {
					    	 
					    	 area1=arears.getString(1);
					       }//while
					  }*///if
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
						out.println("You are not allowed to do entry of another Region");
						return;
					}
					
				}
				 
				/*
				arears=areast.executeQuery("select area_id from area_mstr where huma_id='"+huma_id+"'");
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
				
				for(int i=0;i<VCODE.length;i++)
				{
					
				}
				
				String uno=BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-')+1);
				//System.out.println(uno);
				//Connection svcon=new JdbcConnect().getConnection();
				String svsql="select * from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"+uno+"'";
				Statement svst=con.createStatement();
				ResultSet svrs=svst.executeQuery(svsql);
				if(!svrs.next())
				{
					out.println("Entered Unit does not Exist ");
					return;
				}

				
				for(int i=0;i<VCODE.length;i++)
				{
					String vcode=VCODE[i];
					vcode=vcode.substring(vcode.lastIndexOf('-')+1);
					
					String svsql1="select * from village_mstr where vcode='"+vcode+"'";
					Statement svst1=con.createStatement();
					ResultSet svrs1=svst1.executeQuery(svsql1);
					if(!svrs1.next())
					{
						out.println("Entered Vcode does not Exist");
						return;
					}
					
					
				}
				

				for(int i=0;i<ACTIVITY_ID.length;i++)
				{
					String aid=ACTIVITY_ID[i];
					aid=aid.substring(aid.lastIndexOf('-')+1);
					//System.out.println("activity id is :"+aid);;
					
					String svsql1="select * from activity_mstr where activity_id='"+aid+"'";
					Statement svst1=con.createStatement();
					ResultSet svrs1=svst1.executeQuery(svsql1);
					if(!svrs1.next())
					{
						out.println("Entered Activity does not Exist");
						return;
					}
					
					
				}
////System.out.println("Msr debug 5");
	/*			//String activity[] = null;
				//System.out.println("Msr debug");
				System.out.println("length of the String :"+acts.length);
				for(int k=0;k<acts.length;k++)
				{
				String s=ACTIVITY_ID[k];
				j = s.lastIndexOf('-');
				String s1=s.substring(j+1);
				System.out.println(s1);
				//al.add(s1);
				System.out.println("aray lsit :"+al);
				}
				//System.out.println(al);
	*/			
/*				for(int k=0;k<cats.length;k++)
				{
				String s=ACTIVITY_IDk];
				j = s.lastIndexOf('-');  
				String s1=s.substring(j+1);//ACTIVITY_ID[i].substring(ACTIVITY_ID[i].lastIndexOf('-')+1)
				al1.add(s1);
				}
				//System.out.println("Msr debug");
*/				
				try
				{
				con= dbConn;
				ps6 = con.prepareStatement("select nvl((select distinct to_number(UHLOG_CONTROLENO+1) from UHLOG_SERVICE where UHLOG_CONTROLENO=(select max(UHLOG_CONTROLENO) from UHLOG_SERVICE)),1) as UHLOG_CONTROLENO from dual");
				rs6 = ps6.executeQuery();	
				rs6.next();
				
				String UHLOG_CONTROLENO=Integer.toString(rs6.getInt(1));
				//System.out.println("Control value was :"+UHLOG_CONTROLENO);
				
				// request.getParameter("UHLOG_CONTROLENO"); //common for each insertion
				int x4=0;// to checke the all 2 dates entered are whether having/refering any ops_desc/holiday year or not
				ps4 = con.prepareStatement("select distinct BSFLUNIT_UCODE from UHLOG_SERVICE p1 where BSFLUNIT_UCODE='"+BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-')+1)+"' and UHLOG_DATE=to_date('"+UHLOG_DATE+"','dd-mm-yyyy')");//query to checke the all 2 dates entered are whether having/refering any ops_desc/already used period
				  		 rs4 = ps4.executeQuery();
				   		if((rs4.next())==false)
				    	 x4=x4+1;
				if(x4==0)
				  {
				   out.println("Already existing date ");
				   return;
				  }
				
				
				
				/*
			int x2=0,x3=0,x5=0,x7=0,x9=0,x10=0;
			ps2 = con.prepareStatement("select fpo_id from fpo_mstr where fpo_id=?");
			ps10 = con.prepareStatement("select pg_id from pg_mstr where pg_id=?");	
			ps9 = con.prepareStatement("select estbl_id from estbl_mstr where estbl_id=?");
			ps3 = con.prepareStatement("select VCODE_id from VCODE_mstr where VCODE_id=?");
			ps5 = con.prepareStatement("select block_id from block_mstr where block_id=?");
			ps7 = con.prepareStatement("select subunit_id from subBSFLUNIT_MSTR where subunit_id=?");
			for(int i=0; i<ops_place.length; i++)
				{
				if(fpo_id[i]!="")
				 { ps2.setString(1,fpo_id[i].substring(0,7));
				   rs2 = ps2.executeQuery();
				   if((rs2.next())==false) x2=x2+1;
				  }
				
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
			   out.println("Entered VCODE id does not Exist"); 
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
*/
		//query to insert the records into UHLOG_SERVICE
				ps1= con.prepareStatement("insert into UHLOG_SERVICE(UHLOG_SEQID,UHLOG_DATE,VCODE,UHLOG_OUTREACH,UHLOG_ODCUST,UHLOG_ODAMT,UHLOG_CBY,ACTIVITY_ID,UHLOG_CONTROLENO,BSFLUNIT_UCODE,UHLOG_REMARKS,UHLOG_AMTSPENT) " +
						"values(UHLOGSERVICE.nextval,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,?,?,?)");
				for(int i=0; i<VCODE.length; i++)
				{
				ps1.setString(1,UHLOG_DATE);//ps1.setString(2,ops_desc); //System.out.println("After assigning huma_id and ops_desc");
				//ps1.setString(2,VCODE[i]);
				ps1.setString(2,VCODE[i].substring(VCODE[i].lastIndexOf('-')+1));
				ps1.setLong(3,Integer.parseInt(UHLOG_OUTREACH[i]));
				ps1.setLong(4,Integer.parseInt(UHLOG_ODCUST[i]));
				ps1.setLong(5,Integer.parseInt(UHLOG_ODAMT[i]));
				ps1.setString(6,username);
				ps1.setString(7,ACTIVITY_ID[i].substring(ACTIVITY_ID[i].lastIndexOf('-')+1));
				ps1.setString(8,UHLOG_CONTROLENO);
				ps1.setString(9,BSFLUNIT_UCODE.substring(BSFLUNIT_UCODE.lastIndexOf('-')+1));
				ps1.setString(10,UHLOG_REMARKS[i]);
				ps1.setLong(11,Integer.parseInt(UHLOG_AMTSPENT[i]));
				////System.out.println("Msr debug beforer executing");
				f=ps1.executeUpdate();
				////System.out.println("Msr debug after executing");
				
				
				}
				if(f!=0)
					out.println("OK UH Activity Entry created successfully");//fae master created successfully
				else
				    out.println("UH Activity Entry is not created for some reasons");
									
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
				else if(sqle.getMessage().split("_")[1].trim().equals("VCODE) violated - parent key not found"))
					out.println("Entered VCODE id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("BLOCK) violated - parent key not found"))
					out.println("Entered Block id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("SUBUNIT) violated - parent key not found"))
					out.println("Entered Subunit id does not Exist");
				else */out.println("Duplicate Farmer Activity Entries found,Pls differ either Activity or Place");
			}//else if(sqle.getErrorCode()==1)
			else if(sqle.getErrorCode()==2291)
			 {	sqle.printStackTrace();  
			 //	out.println("the error msg="+sqle.getMessage()+"A");//.split(".")[1].trim(); out.println("the error msg="+sqle.getMessage().split(".")[1].trim()+"A");
				if(sqle.getMessage().split("_")[1].trim().equals("HUMA) violated - parent key not found"))
					out.println("Entered Employee id does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("ACTIVITYSL) violated - parent key not found"))
					out.println("Entered Activity does not Exist");
				else if(sqle.getMessage().split("_")[1].trim().equals("VCODE) violated - parent key not found"))
					out.println("Entered VCODE id does not Exist");
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
