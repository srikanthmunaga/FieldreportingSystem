<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Employee id Getting</title>
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

<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>


<script type="text/javascript">

function validate()
{
if(document.getElementsByName("huma_id")[0].value=="")
{
  alert("Please enter the Emp Id");
  document.getElementsByName("huma_id")[0].focus();
  document.getElementsByName("huma_id")[0].style.background="#fffacd";
  return false;
}
//Common code for dates validation
var date=new RegExp("[0-9]");
var currentTime = new Date();
   var mm = currentTime.getMonth() + 1;
   var dd = currentTime.getDate();
   var yyyy = currentTime.getFullYear();
   var sysdate=dd+"-"+mm+"-"+yyyy;
   sysdate=sysdate.split("-");
   var sysdate = new Date(sysdate[2], sysdate[1]-1, sysdate[0]); //var date1 = new Date(yr1, mon1, dt1); 
//FRS_date validation starts here
if(!date.test(document.form1.FRS_date.value))
{
	  alert("Please select the FRS date");
	  document.form1.FRS_date.focus();
	  return false;
	}
		if(document.form1.FRS_date.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(document.form1.FRS_date.value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	var returnval=false;//validformat
	if(!validformat.test(document.form1.FRS_date.value))
	{
	alert("Please enter the FRS date correct format");
	document.form1.FRS_date.focus();
	return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		alert("Invalid month or date found in FRS date");
		document.form1.FRS_date.focus();
		return false;
	  }
	//Date comparision code starts here
	   var FRS_date=(document.form1.FRS_date.value).split("-");
    var FRS_date = new Date(FRS_date[2], FRS_date[1]-1, FRS_date[0]); //alert("hey to FRS_date date is ="+FRS_date);
	   if(FRS_date > sysdate)
  	{
		 alert("FRS date should not be grater than the System date");
		 document.form1.FRS_date.focus();
		 return false; 
	     }
	 //Date comparision code ends here
	
 }//if(FRS_date!="") 
//FRS date validation code ends here
}
</script><script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script>
<form id="form1" name="form1" action="submitdetailsUnit"
						method="post" onSubmit="return validate();">
						<table cellpadding="1" cellspacing="1" background="blue">
							<tr>

								<td width="380"></td>
								<td width="380" align="left"><b><a
										href="lsrrecoveryUnit.jsp">LSR Home</a></b></td>
							</tr>
						</table>

						<center>
							<table>
								<tr>
									<td>Employee Id</td>
									<td><input type="text" id="huma_id" name="huma_id"
										onFocus="addSuggestionUnit('huma_id','huma_id');"
										style="border-color: #0B4599;" /></td>
									<td>&nbsp;&nbsp;</td>
									<td align="right">Date</td>
									<td><input type="text" name="FRS_date" id="FRS_date"
										size="10" maxlength="10" /><a href="#"
										onclick="setYears(1947, 2050); showCalender(this, 'FRS_date');">
											<img id="cal" src="images/calender.png"
											onClick="allowupdate();" />
									</a>(dd-mm-yyyy)</td>
									<!-- 	<td> <input type="date" name="FRS_date" id="FRS_date"> </td> -->
								</tr>
								<tr>
									<td></td>
									<td></td>
									<td>&nbsp;&nbsp;</td>
									<td></td>
									<td></td>
								</tr>

								<tr>
									<td></td>
									<td></td>
									<td>&nbsp;&nbsp;</td>
									<td><input type="submit" value="Get Submit Form">
									</td>
									<td></td>
								</tr>
							</table>
						</center>

	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
<%


//my name is srinu
}//authorised acess else------------------------------------------------------------------------------------
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