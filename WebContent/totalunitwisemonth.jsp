<%@page import="frs_cls.JdbcConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Monthly Unit Wise Summary Reports</title>
<% 
						String ur=(String)request.getSession().getAttribute("userrole");
						if (request.getSession().getAttribute("username") == null ) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
						else if (request.getSession().getAttribute("username") != null && ur.equals("user")) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
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
//alert("End of validateForm() function");
}//validateForm()
</script>

</head>

<body bottommargin="100%" marginheight="100%" rightmargin="100%"
	leftmargin="100%" marginwidth="100%" topmargin="100%">

<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script>
<form id="form1" name="form1" action="unitmonthsummary"
						method="post" onSubmit="return validateForm();">
						<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="480" align="right"><b><a
										href="Frsreports.jsp">REPORTS HOME</a></b></td>

								<td width="380"></td>
							</tr>
						</table>

						<center>
							<table>

								<tr>
									<td align="right">From Date</td>
									<td><input type="text" name="fdate" id="fdate" size="10"
										maxlength="10" /><a href="#"
										onclick="setYears(1947, 2050); showCalender(this, 'fdate');">
											<img id="cal" src="images/calender.png"
											onClick="allowupdate();" />
									</a>&nbsp;(dd-mm-yyyy)</td>
									<td>&nbsp;&nbsp;</td>
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
									<td></td>
									<td></td>
									<td></td>
									<td><input type="submit" value="Generate XLS Report" onMouseOver="goLite2(this);" onMouseOut="goDim2(this);"/></td>

								</tr>
							</table>
						</center>




	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
<%
   }//authorised acess else	//------------------------------------------------------------------------------------
	%>
<!-- Calender Script  -->

	<table background="images/calender3.gif" id="calenderTable">
		<tbody id="calenderTableHead">
			<tr>
				<td colspan="4" align="center"><select
					onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
	           this.selectedIndex, false));"
					id="selectMonth">
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
				</select></td>
				<td colspan="2" align="center"><select
					onChange="showCalenderBody(createCalender(this.value, 
				document.getElementById('selectMonth').selectedIndex, false));"
					id="selectYear">
				</select></td>
				<td align="center"><a href="#" onClick="closeCalender();"><font
						color="#003333" size="+1">X</font></a></td>
			</tr>
		</tbody>
		<tbody id="calenderTableDays">
			<tr style="">
				<td>Sun</td>
				<td>Mon</td>
				<td>Tue</td>
				<td>Wed</td>
				<td>Thu</td>
				<td>Fri</td>
				<td>Sat</td>
			</tr>
		</tbody>
		<tbody id="calender"></tbody>
	</table>

	<!-- End Calender Script  -->

</html>