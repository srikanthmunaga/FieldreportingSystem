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
<title>Monthly Total Summary Reports</title>
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
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>


<script type="text/javascript" language="javascript">
 function check()
 {
	 //alert("checking");
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
		 //CODE FOR RADIO BUTTONS
		   var shownameshowname;
		   var check=0;
		   var chx = document.getElementsByName("dwr");
		   //alert(chx.length);

		   for (var i=0; i<chx.length; i++) {
				if (chx[i].type == 'radio' && chx[i].checked) {
			       
					check=1;
					//showname=chx[i][0].value;
					//showname=document.getElementsByName(chx[i]).value();
					//alert(chx[i].value);
				}
				
			}

			if(check==1)
				{
				return true;
				}else
				{
					alert("Select any type");
					return false;
				} 

 }//check()
</script>



<script type="text/javascript" language="javascript">
  
  function raj()
  {
	  
			/* document.getElementById("BSFLUNIT_NAME").disabled=false;
			document.getElementById("huma_id").disabled=false;
			document.getElementById("area_name").disabled=false;
			
			document.getElementsByName("BSFLUNIT_NAME")[0].value = "";
			document.getElementsByName("huma_id")[0].value = "";
			document.getElementsByName("area_name")[0].value = "";
			
			document.getElementById("BSFLUNIT_NAME").disabled=true;
			document.getElementById("huma_id").disabled=true; */
			//document.getElementById("unit").disabled=true;
			 
			alert("r1() function called"); 
			document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
			 document.getElementById("area_name2").style.visibility = "visible";
			 document.getElementById("empcode").style.visibility = "hidden";
		}
  
</script>

<script type="text/javascript" language="javascript">
function show()
{
	/* document.getElementById("BSFLUNIT_NAME").disabled=false;
	document.getElementById("huma_id").disabled=false;
	document.getElementById("area_name").disabled=false;
	
	document.getElementsByName("BSFLUNIT_NAME")[0].value = "";
	document.getElementsByName("huma_id")[0].value = "";
	document.getElementsByName("area_name")[0].value = "";
	
	document.getElementById("area_name").disabled=true;
	document.getElementById("BSFLUNIT_NAME").disabled=true;
	//For hiding the TextBoxes
	 //document.getElementById('area_name').style.visibility='hidden';
	document.getElementById('BSFLUNIT_NAME').style.visibility='hidden';
	document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
	document.getElementById("area_name2").style.visibility = "hidden";
	 */ 
	 //alert("r2() function called");
	 document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
	 document.getElementById("area_name2").style.visibility = "visible";
	 document.getElementById("empcode").style.visibility = "hidden";
	
}

</script>

<script type="text/javascript" language="javascript">
function r2()
{
	/* document.getElementById("BSFLUNIT_NAME").disabled=false;
	document.getElementById("huma_id").disabled=false;
	document.getElementById("area_name").disabled=false;
	
	document.getElementsByName("BSFLUNIT_NAME")[0].value = "";
	document.getElementsByName("huma_id")[0].value = "";
	document.getElementsByName("area_name")[0].value = "";
	
	document.getElementById("area_name").disabled=true;
	document.getElementById("BSFLUNIT_NAME").disabled=true;
	//For hiding the TextBoxes
	 //document.getElementById('area_name').style.visibility='hidden';
	document.getElementById('BSFLUNIT_NAME').style.visibility='hidden';
	document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
	document.getElementById("area_name2").style.visibility = "hidden";
	 */ 
	 //alert("r2() function called");
	 document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
	 document.getElementById("area_name2").style.visibility = "hidden";
	 document.getElementById("empcode").style.visibility = "visible";
	
}

</script>

<script type="text/javascript" language="javascript">
function r3()
{
	/* document.getElementById("BSFLUNIT_NAME").disabled=false;
	document.getElementById("huma_id").disabled=false;
	document.getElementById("area_name").disabled=false;
	
	document.getElementsByName("BSFLUNIT_NAME")[0].value = "";
	document.getElementsByName("huma_id")[0].value = "";
	document.getElementsByName("area_name")[0].value = "";
	
	document.getElementById("area_name").disabled=true;
	document.getElementById("huma_id").disabled=true;
	//document.getElementById("unit").disabled=true;
	document.getElementById('area_name2').style.visibility='visible';
	 */
	 
	 //alert("r3() function called");
	 document.getElementById("BSFLUNIT_NAME2").style.visibility = "visible";
	 document.getElementById("area_name2").style.visibility = "hidden";
	 document.getElementById("empcode").style.visibility = "hidden";
	 
}
</script>

<script type="text/javascript" language="javascript">
 function papu()
 {
	 document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
	 document.getElementById("area_name2").style.visibility = "hidden";
	 document.getElementById("empcode").style.visibility = "hidden";
 }
</script>

</head>

<body bottommargin="100%" marginheight="100%" rightmargin="100%"
	leftmargin="100%" marginwidth="100%" topmargin="100%"
	onLoad="return papu();">

<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script>
<form id="form1" name="form1" action="montotsummary" method="post">
						<!--  onSubmit="return validatePage();">  -->
						<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="330" align="right"><b><a
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
									<td align="right">&nbsp;&nbsp;To Date</td>
									<td align="left"><input type="text" name="todate"
										id="todate" size="10" maxlength="10" /><a href="#"
										onclick="setYears(1947, 2050); showCalender(this, 'todate');">
											<img id="cal" src="images/calender.png"
											onClick="allowupdate();" />
									</a>&nbsp;(dd-mm-yyyy)</td>
								</tr>



								<!-- Added By Rajesh -->
								<tr>
								</tr>
								<tr>
								</tr>
								<tr>
								</tr>
								<tr>
								</tr>
								<tr>
								</tr>
								<tr>
									<td></td>
									
									<%
				                       if ((ur.equals("admin"))||(ur.equals("audit")))
				                    {
					                %>
					               <td><input type="radio" name="dwr" id="area" value="Area"
										onClick="return show();" />
										Area
									</td>
									<td><input type="radio" name="dwr" id="unit" value="Unit"
										onClick="return r3();" />
										Unit
									</td>
									<td><input type="radio" name="dwr" id="lsr" value="lsr"
										onClick="return r2();" />
										LSR
									</td>
					
					              <%} %>
					              
					              <%
				                       if (ur.equals("areahead"))
				                    {
					                %>
					               <!-- <td><input type="radio" name="dwr" id="area" value="Area"
										onClick="return show();" />
										Area
									</td> -->
									<td><input type="radio" name="dwr" id="unit" value="Unit"
										onClick="return r3();" />
										Unit
									</td>
									<td><input type="radio" name="dwr" id="lsr" value="lsr"
										onClick="return r2();" />
										LSR
									</td>
					
					              <%} %>
					              
					              
					              <%
				                       if (ur.equals("unithead"))
				                    {
					                %>
					               <!-- <td><input type="radio" name="dwr" id="area" value="Area"
										onClick="return show();" />
										Area
									</td>
									<td><input type="radio" name="dwr" id="unit" value="Unit"
										onClick="return r3();" />
										Unit
									</td> -->
									<td><input type="radio" name="dwr" id="lsr" value="lsr"
										onClick="return r2();" />
										LSR
									</td>
					
					              <%} %>
					              
									

									<!-- <td><input type="radio" name="dwr" id="area" value="Area"
										onClick="return show();" />
										Area
									</td>
									<td><input type="radio" name="dwr" id="unit" value="Unit"
										onClick="return r3();" />
										Unit
									</td>
									<td><input type="radio" name="dwr" id="lsr" value="lsr"
										onClick="return r2();" />
										LSR
									</td> -->

								</tr>
								
								<%
				                 if ((ur.equals("admin"))||(ur.equals("audit")))
				                 {
					             %>
                                 <tr>
									<td></td>
									<!-- <td><input type="radio" name="dwr" id="area" value="Area" onclick="return r1();"/>Region</td> -->
									<td align="right"><label id="area_name2">Region Name
											<input type="text" name="area_name" id="area_name"
											align="right" onFocus="addSuggestion('area_name','area_name');"
											style="border-color: #0B4599;">
									</label></td>
									<!-- Code inserted by Rajesh -->
									<td align="right"><label id="BSFLUNIT_NAME2">Unit name
											<input type="text" name="BSFLUNIT_NAME" id="BSFLUNIT_NAME"
											align="right" onFocus="addSuggestion('BSFLUNIT_NAME','BSFLUNIT_NAME');"
											style="border-color: #0B4599;">
									</label></td>

									<td align="right"><label id="empcode">Employee
											Code <input type="text" name="huma_id" id="huma_id" align="right"
											onFocus="addSuggestion('huma_id','huma_id');"
											style="border-color: #0B4599;">
									</label></td>

									<td></td>
								</tr>

					            <%} %>
					            
					            <%
				                 if (ur.equals("areahead"))
				                 {
					             %>
                                 <tr>
									<td></td>
									<!-- <td><input type="radio" name="dwr" id="area" value="Area" onclick="return r1();"/>Region</td> -->
									<td align="right"><label id="area_name2">Region Name
											<input type="text" name="area_name" id="area_name"
											align="right" onFocus="addSuggestion('area_name','area_name');"
											style="border-color: #0B4599;">
									</label></td>
									<!-- Code inserted by Rajesh -->
									<td align="right"><label id="BSFLUNIT_NAME2">Unit name
											<input type="text" name="BSFLUNIT_NAME" id="BSFLUNIT_NAME"
											align="right" onFocus="addSuggestion('BSFLUNIT_NAME','BSFLUNIT_NAME');"
											style="border-color: #0B4599;">
									</label></td>

									<td align="right"><label id="empcode">Employee
											Code <input type="text" name="huma_id" id="huma_id" align="right"
											onFocus="addSuggestion('Allhuma_id','huma_id');"
											style="border-color: #0B4599;">
									</label></td>

									<td></td>
								</tr>

					            <%} %>
					            
					            
					            <%
				                 if (ur.equals("unithead"))
				                 {
					             %>
                                 <tr>
									<td></td>
									<!-- <td><input type="radio" name="dwr" id="area" value="Area" onclick="return r1();"/>Region</td> -->
									<td align="right"><label id="area_name2">Region Name
											<input type="text" name="area_name" id="area_name"
											align="right" onFocus="addSuggestion('area_name','area_name');"
											style="border-color: #0B4599;">
									</label></td>
									<!-- Code inserted by Rajesh -->
									<td align="right"><label id="BSFLUNIT_NAME2">Unit name
											<input type="text" name="BSFLUNIT_NAME" id="BSFLUNIT_NAME"
											align="right" onFocus="addSuggestion('BSFLUNIT_NAME','BSFLUNIT_NAME');"
											style="border-color: #0B4599;">
									</label></td>

									<td align="right"><label id="empcode">Employee Code
									<input type="text" name="huma_id" id="huma_id" align="right"
											onFocus="addSuggestion('Allhuma_id','huma_id');"
											style="border-color: #0B4599;">
									</label></td>

									<td></td>
								</tr>

					            <%} %>
					            

								<!-- <tr>
									<td></td>
									<td><input type="radio" name="dwr" id="area" value="Area" onclick="return r1();"/>Region</td>
									<td align="right"><label id="area_name2">Region Name
											<input type="text" name="area_name" id="area_name"
											align="right" onFocus="addSuggestion('area_name','area_name');"
											style="border-color: #0B4599;">
									</label></td>
									Code inserted by Rajesh
									<td align="right"><label id="BSFLUNIT_NAME2">Unit name
											<input type="text" name="BSFLUNIT_NAME" id="BSFLUNIT_NAME"
											align="right" onFocus="addSuggestion('BSFLUNIT_NAME','BSFLUNIT_NAME');"
											style="border-color: #0B4599;">
									</label></td>

									<td align="right"><label id="empcode">Employee
											Code <input type="text" name="huma_id" id="huma_id" align="right"
											onFocus="addSuggestion('huma_id','huma_id');"
											style="border-color: #0B4599;">
									</label></td>

									<td></td>
								</tr> -->



								<!-- <tr>
<td></td>
<td align="right">
<label id="BSFLUNIT_NAME2" >Unit name
 <input type="text" name="BSFLUNIT_NAME" id="BSFLUNIT_NAME" align="right" onfocus="addSuggestion('BSFLUNIT_NAME','BSFLUNIT_NAME');" class="masterInput"> 
 </label>
 </td>
<td></td>
</tr> 

<tr>
<td></td>
<td align="right">
<label id="empcode">Employee Code
 <input type="text" name="huma_id" id="huma_id" align="right" onfocus="addSuggestion('huma_id','huma_id');" class="masterInput"> 
 </label>
 </td>
<td></td>
</tr> 
 -->

<tr>  </tr> <tr>   </tr>
<tr>
<td></td>
<td><input type="submit" name="format" id="format" value="Generate XLS Report" onClick="return check();"/> </td>
<td><input type="submit" name="format" id="format" value="Generate PDF Report" onClick="return check();"/> </td>
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