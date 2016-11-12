<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Indent Details Submit</title>

<% 
						String ur=(String)request.getSession().getAttribute("userrole");
						
                          if (request.getSession().getAttribute("username") == null) {

    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
						else if (request.getSession().getAttribute("username") != null && ur.equals("user")) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
						else
						{
						%>
<script type="text/javascript" src="JS/validatepages.js"></script>
<!-- 
<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>
<script type="text/javascript" src="JS/validatepages.js"></script>
<style type="text/css">
body {
	background-color: #d0e4fe;
}

h1 {
	color: orange;
	text-align: center;
}

p {
	font-family: "Times New Roman";
	font-size: 20px;
}
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();">	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script>
 -->
<script type="text/javascript">
function main(){
	var JULIAN = 1;
	var year = 0;
	var month = 0;
	var day = 0;
	var julianday = 0.0;
	var modifiedjulianday = 0.0;

	function ipart(r) {
		return Math.round(r - 0.5);
	}
	function getJulianDay() {
		return this.julianday;
	}
	function getModifiedJulianDay() {
		return this.modifiedjulianday;
	}

	function dateFormat(f) {
		df = f;
	}

	function parseDate(dateval) {
		// split is a Javascript 1.2 function
		var dary = dateval.split("-");

		var y = fix2DigitDate(dary[2]);
		m = dary[1];
		d = dary[0];

		var calendar;
		if (y > 1582)
			calendar = GREGORIAN;
		else if (y < 1582)
			calendar = JULIAN;
		else if (m < 10 | (m == 10 && d < 15))
			calendar = JULIAN;
		else
			calendar = GREGORIAN;
		i = new CustomDate(y, m, d, calendar);
		return i;
	}// dateval

	function fix2DigitDate(dateval) {
		var date = dateval + "" ;// dateval must be a string
		if (date.length < 3) {
			date = 1900 + date * 1.0;
			date = date + "" ;// to string
		}
		return date;
	}

	function CustomDate(yr, mo, da, type) {
		year = yr * 1.0; // convert string to float

		month = mo * 1.0;
		day = da * 1.0;
		if (year == 1582 && month == 10 && day > 4 && day < 15) {
			alert("Invalid date: 15 Oct immediately followed 4 Oct in the year 1582");
			document.datainput.dateerr.value = "??";
			return;
		}
		if (year < 0)
			year = year + 1; // B.C. date correction
		var a = ipart((14 - month) / 12);
		var y = year + 4800 - a;
		var m = month + 12 * a - 3;
		if (type == GREGORIAN) {
			julianday = day + ipart((153 * m + 2) / 5) + y * 365 + ipart(y / 4)
					- ipart(y / 100) + ipart(y / 400) - 32045;
		}
		if (type == JULIAN) {
			julianday = day + ipart((153 * m + 2) / 5) + y * 365 + ipart(y / 4)
					- 32083;
		}
		modifiedjulianday = julianday - 2400000.5; // Zero at 17 Nov 1858 00:00:00
													// UTC
		this.getJulianDay = getJulianDay();
		this.getModifiedJulianDay = getModifiedJulianDay();
	}

	function DaysArray(n) {
		for ( var i = 1; i <= n; i++) {
			this[i] = 31;
			if (i == 4 || i == 6 || i == 9 || i == 11) {
				this[i] = 30;
			}
			if (i == 2) {
				this[i] = 29;
			}
		}
		return this;
	}
	var date = new RegExp("[0-9]");
	var currentTime = new Date();
	var mm = currentTime.getMonth() + 1;
	var dd = currentTime.getDate();
	var yyyy = currentTime.getFullYear();
	var sysdate = dd + "-" + mm + "-" + yyyy;
	sysdate = sysdate.split("-");
	var sysdate = new Date(sysdate[2], sysdate[1] - 1, sysdate[0]);
	
	var issue=document.form1.issue_stock.value;
	   if(issue==""){
		   alert("please enter the issued stock correctly");
		   document.form1.issue_stock.focus();
		   return false;
	   }
	   
	   if (!date.test(document.form1.date_is.value)) {           //code for date validation
			alert("Please select the date of issue");
			document.form1.date_is.focus();
			return false;
		}
	
	if (document.form1.date_is.value.replace(/^\s+/, '').replace(/\s+$/, '') != "") {
		var vnvdate = (document.form1.date_is.value).split("-");
		var validformat = /^\d{2}-\d{2}-\d{4}$/;
		//var returnval = false;// validformat
		if (!validformat.test(document.form1.date_is.value)) {
			alert("Please enter the date of issue in correct format");
			document.form1.date_is.focus();
			return false;
		}// if date format checking
		var dayfield = vnvdate[0];
		var monthfield = vnvdate[1];
		var yearfield = vnvdate[2];
		var dayobj = new Date(yearfield, monthfield - 1, dayfield);
		if ((dayobj.getMonth() + 1 != monthfield)
				|| (dayobj.getDate() != dayfield)
				|| (dayobj.getFullYear() != yearfield)) {
			alert("Invalid month or date found in date of issue");
			document.form1.date_is.value="";
			document.form1.date_is.focus();
			return false;
		}
		// Date comparision code starts here
		var date_of_iss = (document.form1.date_is.value).split("-");
		var date_of_issu = new Date(date_of_iss[2], date_of_iss[1] - 1,date_of_iss[0]); // alert("hey
																			// to
																			// FRS_date
																			// date
																			// is
																			// ="+FRS_date);
		if (date_of_issu > sysdate) {
			alert("date of entry should not be grater than the System date");
			document.form1.date_is.value="";
			document.form1.date_is.focus();
			return false;
		}
		// Date comparision code ends here
		//code for dat validation ends here
	}
	
	   var couid=document.form1.cou_id.value;
	   if(couid==""){
		   alert("please enter the courier id");
		   document.form1.cou_id.focus();
		   return false;
	   }
	   var couname=document.form1.cou_name.value;
	   if(couname==""){
		   alert("please enter the courier name correctly");
		   document.form1.cou_name.focus();
		   return false;
	   }
}


</script>

<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Bmenu.js"></script>
<form id="form1" name="form1" method="post" action="Issue33"
						onsubmit="return main();">

						<!--<table cellpadding="1" cellspacing="1" background="blue">
							 <tr>
								<td width="380"></td>
								<td width="360" align="left"><b><a
										href="lsrrecovery.jsp">Lsr Home</a></b></td>
							</tr> 
						</table>--><br />

						<table align="center"> 

							<tr>
								<td bgcolor="white" align="right"></td><td colspan="3"></td>
							</tr>

							<tr>
								<td align="right" class="dislpayLabel">Unit Id</td>
								<td><input type="text" name="bsflunit_ucode" id="bsflunit_ucode"
									value="<%=request.getAttribute("unit_id")%>" readonly="readonly"></td>
								<td></td>
								<td></td>
							</tr>

							<tr>
								<td align="right" class="dislpayLabel">Unit
											name</td>
								<td><input type="text" name="unit_name" id="unit_name"
								<%
								String v="";
								if(request.getAttribute("unit_name")!=null)
									v=request.getAttribute("unit_name").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
								</tr>
								<tr>
								<td align="right" class="dislpayLabel">Staionary
											Id</td>
								<td><input type="text" name="st_id" id="st_id"
									<%-- value="<%=session.getAttribute("lname")%>" readonly="readonly"> --%>
									<%
								 v="";
								if(request.getAttribute("st_id")!=null)
									v=request.getAttribute("st_id").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td align="right" class="dislpayLabel">Staionary
											Name</td>
								<td><input type="text" name="st_name" id="st_name"
									<%-- value="<%=session.getAttribute("lname")%>" readonly="readonly"> --%>
									<%
								 v="";
								if(request.getAttribute("st_name")!=null)
									v=request.getAttribute("st_name").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td align="right" class="dislpayLabel">Last
											Issued</td>
								<td><input type="text" name="last_issued" id="last_issued"
									<%-- value="<%=session.getAttribute("lname")%>" readonly="readonly"> --%>
									<%
								 v="";
								if(request.getAttribute("iss_stock")!=null)
									v=request.getAttribute("iss_stock").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
							</tr>
								<tr>
								<td align="right" class="dislpayLabel">Closing
											Stock</td>
								<td><input type="text" name="closing_stock" id="closing_stock"
									<%-- value="<%=session.getAttribute("lname")%>" readonly="readonly"> --%>
									<%
								 v="";
								if(request.getAttribute("closing_stock")!=null)
									v=request.getAttribute("closing_stock").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
							</tr>
								<tr>
								<td align="right" class="dislpayLabel">Available
											Stock</td>
								<td><input type="text" name="available_stock" id="available_stock"
									<%-- value="<%=session.getAttribute("lname")%>" readonly="readonly"> --%>
									<%
								 v="";
								if(request.getAttribute("available_stock")!=null)
									v=request.getAttribute("available_stock").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td align="right" class="dislpayLabel">Requested
											Quantity</td>
								<td><input type="text" name="req_stock" id="req_stock"
									<%-- value="<%=session.getAttribute("lname")%>" readonly="readonly"> --%>
									<%
								 v="";
								if(request.getAttribute("req_stock")!=null)
									v=request.getAttribute("req_stock").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td align="right" class="dislpayLabel">Date Of
											Indent</td>
								<td><input type="text" name="d_oi" id="d_oi"
									<%-- value="<%=session.getAttribute("lname")%>" readonly="readonly"> --%>
									<%
								 v="";
								if(request.getAttribute("date_of_inden")!=null)
									v=request.getAttribute("date_of_inden").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td align="right" class="dislpayLabel">Indent
											By</td>
								<td><input type="text" name="ind_by" id="ind_by"
									<%-- value="<%=session.getAttribute("lname")%>" readonly="readonly"> --%>
									<%
								 v="";
								if(request.getAttribute("indent_by")!=null)
									v=request.getAttribute("indent_by").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
							</tr>
								<tr>
								<td align="right">Issuing Stock</td>
								<td><input type="text" name="issue_stock"
									id="issue_stock" /></td>
								</tr>
							
							<tr>
								<td align="right">Date of Dispatch</td>
								<td colspan="2" align="left"><input type="text" name="date_is" id="date_is"
									maxlength="10" /><a href="#"  onclick="setYears(1947, 2050);
       showCalender(this, 'date_is');"> <img id="cal1"  src="images/calender.png" onClick="allowupdate();"/>
       </a>(dd-mm-yyyy)</td>
								<!-- 	<td> <input type="date" name="FRS_date" id="FRS_date"> </td> -->
								
								<td></td>
							</tr>

							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>

							
							<tr>
								<td align="right">LR/DOC/NO</td>
								<td><input type="text" name="cou_id"
									id="cou_id" /></td>
								</tr>
							<tr></tr>
							<tr>
								<td align="right">Courier Name</td>
								<td><input type="text" name="cou_name"
									id="cou_name" /></td>
								</tr>
							
							<tr></tr>
							
							

							<tr></tr>
							<tr></tr>
							<tr></tr>
										<tr>
									<td colspan="2" align="center" height="61"><table>
											<tr>
												<td><script type="text/javascript"
														src="JS/Sbuttons.js"></script> <br />
													<table id="msg2"
														style="visibility: hidden; position: absolute;" align="">
														<tr>
															<td>Execute</td>
														</tr>
													</table></td>
												
											</tr>
										</table></td>
								</tr>
						</table>


	<%-- </form>
	
<!-- <script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body> -->
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>
<%


}
	%>
 --%>
 
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