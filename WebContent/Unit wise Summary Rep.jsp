
<%@page import="frs_cls.JdbcConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="dbConn" scope="request" class="frs_cls.JdbcConnect"/> 
<%!
   Connection con=null;
   PreparedStatement ps=null,ps1=null,ps2=null;
   ResultSet rs=null,rs1=null,rs2=null;
   int f=0;
    String e=null;
	
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Unit wise Summary Report</title>
<% 
						String ur=(String)request.getSession().getAttribute("userrole");
						if (request.getSession().getAttribute("username") == null ) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
						/* else if (request.getSession().getAttribute("username") != null && ur.equals("user")) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						} */
						else
						{
						%>

<script type="text/javascript" src="JS/jquery.min.js"></script>
<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>

<script type="text/javascript" language="javascript">
function validateForm()
{//alert("inside the validation and"); //onSubmit="validateForm()" 
 	var date=new RegExp("[0-9]");
	var currentTime = new Date();
       var mm = currentTime.getMonth() + 1;
       var dd = currentTime.getDate();
       var yyyy = currentTime.getFullYear();
       var sysdate=dd+"-"+mm+"-"+yyyy;
	   sysdate=sysdate.split("-");
	   var sysdate = new Date(sysdate[2], sysdate[1]-1, sysdate[0]); //var date1 = new Date(yr1, mon1, dt1); 
 //fdate validation
	if(!date.test(document.form1.fdate.value))
    {
      alert("Please select the From date");
	  document.form1.fdate.focus();
      return false;
    }
  	if(document.form1.fdate.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(document.form1.fdate.value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	var returnval=false;//validformat
	if(!validformat.test(document.form1.fdate.value))
	{
	alert("Please enter the From date correct format");
	document.form1.fdate.focus();
	return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		alert("Invalid month or date found in From date");
		document.form1.fdate.focus();
		return false;
	  }
	// }//if(fdate!="") 
	//date comparision code   
	   var fdate=(document.form1.fdate.value).split("-");
       var fdate = new Date(fdate[2], fdate[1]-1, fdate[0]); //alert("hey to rday date is ="+prdate);
	   if(fdate > sysdate)
     	{
		 alert("From date should not be greater than the System date");
		 document.form1.fdate.focus();
		 return false; 
	     }
	 }//if(fdate!="")  
	if(!date.test(document.form1.todate.value))
    {
      alert("Please select the To date");
	  document.form1.todate.focus();
      return false;
    }
	if(document.form1.todate.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(document.form1.todate.value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	var returnval=false;//validformat
	if(!validformat.test(document.form1.todate.value))
	{
	alert("Please enter the To date correct format");
	document.form1.todate.focus();
	return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		alert("Invalid month or date found in To date");
		document.form1.todate.focus();
		return false;
	  }
	 }//if(todate!="") //alert("hety outside the to date");
	   var todate=(document.form1.todate.value).split("-");
       var todate = new Date(todate[2], todate[1]-1, todate[0]); //alert("hey to todate date is ="+todate);
	   if(todate > sysdate)
     	{
		 alert("To date should not be grater than the System date");
		 document.form1.todate.focus();
		 return false; 
	     }
	   if(document.form1.fdate.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")//checking only if fdate has entered
	   if(fdate > todate)
     	{
		 alert("From date should be less than the To date");
		 document.form1.fdate.focus();
		 return false; 
	     }
//date comparision code exit here*/
//----------------------multiple selection validation
//----------------------------

 var chks = document.getElementsByName('huma_id');//alert("hety got the cheks again="+chks);
 var checkCount = 0;
 for (var i = 0; i < chks.length; i++)
 if (chks[i].checked)
  checkCount++;
 if (checkCount < 1)
 {
 alert("Please select at least one Input");
 return false;
 }
 }//validateForm()
 
function all2()//directly should not use all(),because the 'all' is the reserved keyword
 { //alert("hey start of the all method");
   var chks2 = document.getElementsByName('huma_id');//alert("hety got the cheks again="+chks2);
   // var checkCount2 = 0;
 for (var i = 0; i < chks2.length; i++)
 chks2[i].checked=true; //alert("hey end of the all method");
  }//all()
function none()
 { //alert("hey start of the none method");
   var chks3 = document.getElementsByName('huma_id');//alert("hety got the cheks again="+chks3);
    //var checkCount3 = 0;
 for (var i = 0; i < chks3.length; i++)
 chks3[i].checked=false;//alert("hey end of the none method");
  }//none()
/* 
function OnButton1()
{//alert("inside the onButton1");
 sessioncheck();
if(document.form1.onSubmit==validateForm()) 
 {
  document.form1.action = "reports_gen?decide="+document.form1.decide.value+"&format="+document.form1.format.value+"&key1= &key2= &fdate="+document.form1.fdate.value+"&tdate="+document.form1.tdate.value+"";
  //document.form1.action = "Defaulter report gen.jsp";  
  document.form1.target = "_blank"; // Open in a new window
  return true; 
  }//if 
else { return false; }
//alert("end of onButton1");
}//OnButton1();
*/</script><script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script>
<form id="form1" name="form1" action="FRSReports_servlet"
						method="post" onSubmit="return validateForm();" target="_blank">
<!--						<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="480" align="right"><b><a
										href="Fhome.jsp">HOME</a></b></td>

								<td width="380"></td>
							</tr>
						</table>--><br />

						<center>
							<!-- By Rajesh from here -->
							 <table align="center">
				<tr>
					<td height="">
						<fieldset style="background-color:">
							<!-- <legend class="style23">Region Master</legend> -->
							<legend class="formTitle">Unit Wise Summary Report</legend>
							<table bgcolor="" width="100%" height="91%" border="0"
								align="center" bordercolor="#000000">
								<!-- till here -->


								<tr>
									<td align="right">From Date</td>
									<td><input type="text" name="fdate" id="fdate" size="10"
										maxlength="10" /><a href="#"
										onclick="setYears(1947, 2050); showCalender(this, 'fdate');">
											<img id="cal" src="images/calender.png"
											onClick="allowupdate();" />
									</a>&nbsp;(dd-mm-yyyy)</td>
									<td><input type="hidden" name="report_name"
										id="report_name" value="unitsummary" /></td>
									<td align="right">To Date</td>
									<td><input type="text" name="todate" id="todate" size="10"
										maxlength="10" /><a href="#"
										onclick="setYears(1947, 2050); showCalender(this, 'todate');">
											<img id="cal" src="images/calender.png"
											onClick="allowupdate();" />
									</a>&nbsp;(dd-mm-yyyy)</td>
								</tr>

								<tr>
									<td></td>
									<td align="right"><input type="submit" name="format"
										value="Generate PDF Report" onMouseOver="goLite2(this);" onMouseOut="goDim2(this);"/></td><td></td><td><input type="submit" name="format"
										value="Generate XLS Report" onMouseOver="goLite2(this);" onMouseOut="goDim2(this);"/></td><td><input type="button" id="cancel" name="cancel" class="groovybutton" value="Cancel" title="" onmouseover="goLite(this.form.name,this.name)"   onmouseout="goDim(this.form.name,this.name)" onclick="window.location='Fhome.jsp'"/></td>

								</tr>
								<tr><td colspan="5">
								<table width="60%" align="center" border="1" style="border-style: outset" >
                <!--<select name="busi_id" id="busi_id" onchange="showEmp(this.value);">
                      <option value="-1" selected>Select</option>-->
                <%
    int sumcount2=0; 
	
    try {
    con= dbConn.getConnection();
      	/*ps = con.prepareStatement("select * from rights_mstr where rights_supercheck='"+session.getAttribute("user")+"'");
	    rs = ps.executeQuery(); 
      	ps1 = con.prepareStatement("select * from rights_mstr where rights_subordinatecheck='"+session.getAttribute("user")+"'");
	    rs1 = ps1.executeQuery(); 
		
      
	  if(!((String)session.getAttribute("userType")).equals("SUPER"))
	  { if(rs.next())//query to get all employees information,just like superuser
	      ps2 = con.prepareStatement("select huma_id,huma_fname,huma_lname,row_number() over(order by huma_id) as sno from huma_mstr where busi_id in (select distinct busi_id from activitysl_mstr) and huma_mstr.huma_freeze!='YES' order by huma_id");
		else if(rs1.next())//query to get subordinate and himself report
		  ps2 = con.prepareStatement("select huma_id,huma_fname,huma_lname,row_number() over(order by huma_id) as sno from huma_mstr where busi_id in (select distinct busi_id from activitysl_mstr) and huma_id in (select huma_id from huma_mstr where huma_reporting='"+session.getAttribute("user")+"' or huma_id='"+session.getAttribute("user")+"') and huma_mstr.huma_freeze!='YES' order by huma_id");
        else//query to get just emp without any sobordinates and super rights
	     ps2 = con.prepareStatement("select huma_id,huma_fname,huma_lname,row_number() over(order by huma_id) as sno from huma_mstr where busi_id in (select distinct busi_id from activitysl_mstr) and huma_id='"+session.getAttribute("user")+"' and huma_mstr.huma_freeze!='YES' order by huma_id");
	   }//if(!((String)session.getAttribute("userType")).equals("SUPER"))
	  else//super user query
		*/
		//ps2 = con.prepareStatement("select huma_id,huma_fname,huma_lname,row_number() over(order by huma_id) as sno from huma_mstr where busi_id in (select distinct busi_id from activitysl_mstr) and huma_mstr.huma_freeze!='YES' order by huma_id");
		
		String role=request.getSession().getAttribute("userrole").toString();
	    String huma_id=request.getSession().getAttribute("huma_id").toString();
	    String admin_huma_id="";
	    String areahead_huma_id="";
	    String unithead_huma_id="";
	    String executive_huma_id="";
	    String user_huma_id="";
		//if((role.equals("admin"))||(role.equals("audit")))
		//ps2 = con.prepareStatement("select distinct BSFLUNIT_UCODE,BSFLUNIT_NAME,BSFLUNIT_NAME,row_number() over(order by BSFLUNIT_UCODE) as sno from BSFLUNIT_MSTR order by BSFLUNIT_UCODE");
		//else if(role.equals("areahead"))
		//ps2 = con.prepareStatement("select distinct BSFLUNIT_UCODE,BSFLUNIT_NAME,BSFLUNIT_NAME,row_number() over(order by BSFLUNIT_UCODE) as sno from BSFLUNIT_MSTR where area_id=(select area_id from area_mstr where huma_id='"+huma_id+"') order by BSFLUNIT_UCODE");
		//else if(role.equals("unithead"))
		//ps2 = con.prepareStatement("select distinct BSFLUNIT_UCODE,BSFLUNIT_NAME,BSFLUNIT_NAME,row_number() over(order by BSFLUNIT_UCODE) as sno from BSFLUNIT_MSTR where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='"+huma_id+"') order by BSFLUNIT_UCODE");
		//else
		//	ps2 = con.prepareStatement("select distinct BSFLUNIT_UCODE,BSFLUNIT_NAME,BSFLUNIT_NAME,row_number() over(order by BSFLUNIT_UCODE) as sno from BSFLUNIT_MSTR where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='') order by BSFLUNIT_UCODE");
		if((role.equals("admin"))||(role.equals("audit")))
		ps2 = con.prepareStatement("select distinct BSFLUNIT_UCODE,BSFLUNIT_NAME,BSFLUNIT_NAME,row_number() over(order by BSFLUNIT_UCODE) as sno from BSFLUNIT_MSTR order by BSFLUNIT_UCODE");
		else if(role.equals("areahead"))
		ps2 = con.prepareStatement("select distinct BSFLUNIT_UCODE,BSFLUNIT_NAME,BSFLUNIT_NAME,row_number() over(order by BSFLUNIT_UCODE) as sno from BSFLUNIT_MSTR where area_id in(select area_id from area_mstr where huma_id='"+huma_id+"') order by BSFLUNIT_UCODE");
		else if(role.equals("unithead"))
		//ps2 = con.prepareStatement("select distinct BSFLUNIT_UCODE,BSFLUNIT_NAME,BSFLUNIT_NAME,row_number() over(order by BSFLUNIT_UCODE) as sno from BSFLUNIT_MSTR where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='"+huma_id+"') order by BSFLUNIT_UCODE");
		ps2 = con.prepareStatement("select distinct BSFLUNIT_UCODE,BSFLUNIT_NAME,BSFLUNIT_NAME,row_number() over(order by BSFLUNIT_UCODE) as sno from BSFLUNIT_MSTR where bsflunit_ucode in(select bsflunit_ucode from bsflunit_mstr where huma_id='"+huma_id+"') order by BSFLUNIT_UCODE");
		else
			ps2 = con.prepareStatement("select distinct BSFLUNIT_UCODE,BSFLUNIT_NAME,BSFLUNIT_NAME,row_number() over(order by BSFLUNIT_UCODE) as sno from BSFLUNIT_MSTR where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id='') order by BSFLUNIT_UCODE");	

	    rs2 = ps2.executeQuery();
	   %>
                <tr bgcolor="silver">
                  <th scope="col" ><div align="center" >Sno</div></th>
                  <th scope="col" ><div align="center" >Code</div></th>
                  <th scope="col" ><div align="center" >Name </div></th>
                  <th scope="col" ><div align="left" >Selection:<a href="#"  onclick="all2();">All</a><font >,</font><a href="#"  onclick="none();">None</a></div></th>
                </tr>
                <%
	  while(rs2.next())
		{
		  %>
                <tr>
                  <td><div align="center"><%=rs2.getString(4)%>&nbsp;</div></td>
				  <td><div align="right"><%=rs2.getString(1)%>&nbsp;</div></td>
                  <td><div align="left"><%=rs2.getString(2)%></div></td>
                  <td><div align="left">
                    <input type="checkbox" name="huma_id" value="<%=rs2.getString(1)%>" id="<%=rs2.getString(1)%>"/>
                  </div></td>
                </tr>
                <%
		}
  %>
              </table>
								</td></tr>
								<!-- By Rajesh -->
								</table>
						</fieldset>
					</td> <!-- td closed -->
				</tr>
								<!-- Till here -->
							</table>
						</center>
					</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>
<%
}//try
	catch (Exception e) {
      e.printStackTrace();
    }
 finally 
	{
	 if(rs2!=null)rs2.close();
     if(ps2!=null)ps2.close();
/*	 if(rs3!=null)rs3.close();
     if(ps3!=null)ps3.close();
	 if(rs4!=null)rs4.close();
     if(ps4!=null)ps4.close();
	 if(rs5!=null)rs5.close();
     if(ps5!=null)ps5.close();
*/	 if(con!=null)con.close(); 
    }
   	%>
<%
   }//authorised acess else	//------------------------------------------------------------------------------------
	%>
<!-- Calender Script  --> 

    <table background="images/calender3.gif" id="calenderTable">
        <tbody id="calenderTableHead">
          <tr>
            <td colspan="4" align="center">
	          <select onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
	           this.selectedIndex, false));" id="selectMonth">
	              <option value="0">Jan</option>
	              <option value="1">Feb</option>
	              <option value="2">Mar</option>
	              <option value="3">Apr</option>
	              <option value="4">May</option>
	              <option value="5">Jun</option>
	              <option value="6">Jul</option>
	              <option value="7">Aug</option>
	              <option value="8">Sep</option>
	              <option value="9">Oct</option>
	              <option value="10">Nov</option>
	              <option value="11">Dec</option>
	          </select>
            </td>	
            <td colspan="2" align="center">
			    <select onChange="showCalenderBody(createCalender(this.value, 
				document.getElementById('selectMonth').selectedIndex, false));" id="selectYear">
				</select>
			</td>
            <td align="center">
			    <a href="#" onClick="closeCalender();"><font color="#003333" size="+1">X</font></a>
			</td>
		  </tr>
       </tbody>
       <tbody id="calenderTableDays">
         <tr style="">
           <td>Sun</td><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td>
         </tr>
       </tbody>
       <tbody id="calender"></tbody>
    </table>

<!-- End Calender Script  -->

</html>



