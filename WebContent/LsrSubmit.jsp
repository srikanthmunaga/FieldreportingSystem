<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Field Staff recovery Submit</title>
<% if (request.getSession().getAttribute("username") == null) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
						else
						{
						%>

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
</style><script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();">	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script><form id="form1" name="form1" method="post" action="RDsubmit"
						onSubmit="return validate();">

						<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="380"></td>
								<!-- 		<td width="360" align="left" ><b><a href="FrsuserHome.jsp">HOME</a></b></td> -->
							</tr>
						</table>

						<table>

							<tr>
								<td width="380" bgcolor="white" align="right"></td>
							</tr>

							<tr>
								<td align="right" class="dislpayLabel">Emp Id</td>
								<td><input type="text" name="huma_id" id="huma_id"
									value="<%=request.getAttribute("huma_id")%>" readonly="readonly"></td>
								<td></td>
								<td></td>
							</tr>

							<%-- <tr>
								<td align="right" class="dislpayLabel">First
											name</td>
								<td><input type="text"
									value="<%=session.getAttribute("fname")%>" readonly="readonly">
								</td>
								<td align="right" class="dislpayLabel">Last
											name</td>
								<td><input type="text"
									value="<%=session.getAttribute("lname")%>" readonly="readonly">
								</td>
							</tr>

							<tr>
								<td align="right" class="dislpayLabel">Unit No</td>
								<td><input type="text"
									value="<%=session.getAttribute("BSFLUNIT_UCODE")%>" readonly="readonly">
								</td>
								<td align="right" class="dislpayLabel">Unit
											Name</td>
								<td><input type="text"
									value="<%=session.getAttribute("BSFLUNIT_NAME")%>"
									readonly="readonly"></td>
							</tr> --%>
							
							<tr>
								<td align="right" class="dislpayLabel">First
											name</td>
								<td><input type="text"
								<%
								String v="";
								if(request.getAttribute("fname")!=null)
									v=request.getAttribute("fname").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
								<td align="right" class="dislpayLabel">Last
											name</td>
								<td><input type="text"
									<%-- value="<%=session.getAttribute("lname")%>" readonly="readonly"> --%>
									<%
								 v="";
								if(request.getAttribute("lname")!=null)
									v=request.getAttribute("lname").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
							</tr>

							<tr>
								<td align="right" class="dislpayLabel">Unit No</td>
								<td><input type="text"
								<%
								 v="";
								if(request.getAttribute("BSFLUNIT_UCODE")!=null)
									v=request.getAttribute("BSFLUNIT_UCODE").toString();
								%>
									value="<%=v%>" readonly="readonly">
								</td>
								<td align="right" class="dislpayLabel">Unit
											Name</td>
								<td><input type="text"
								<%
								 v="";
								if(request.getAttribute("BSFLUNIT_NAME")!=null)
									v=request.getAttribute("BSFLUNIT_NAME").toString();
								%>
									value="<%=v%>"
									readonly="readonly"></td>
							</tr>
							

							<tr>
								<td align="right">Date</td>
								<td><input type="text" name="FRS_date" id="FRS_date"
									maxlength="10" /><a href="#"
									onclick="setYears(1947, 2050); showCalender(this, 'FRS_date');">
										<img id="cal" src="images/calender.png"
										onClick="allowupdate();" />
								</a>(dd-mm-yyyy)</td>
								<!-- 	<td> <input type="date" name="FRS_date" id="FRS_date"> </td> -->
								<td></td>
								<td></td>
							</tr>

							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>

							<tr>
								<td align="right">No. of villages Visited</td>
								<td><input type="text" name="FRS_village_count"
									id="FRS_village_count" /></td>
								<td align="right">No. of customers approached</td>
								<td><input type="text" name="FRS_Cust_count"
									id="FRS_Cust_count" /></td>
							</tr>
							<tr></tr>
							<tr></tr>
							<tr>
								<td align="right">Total Recovery amount collected</td>
								<td><input type="text" name="FRS_total_amt"
									id="FRS_total_amt" /></td>

								<td align="right">No of recovery Accounts</td>
								<td><input type="text" name="FRS_total_accounts"
									id="FRS_total_accounts" /></td>
							</tr>
							<tr></tr>
							<tr></tr>

							<tr>
								<td align="right">OD Amount Collected</td>
								<td><input type="text" name="FRS_od_amt" id="FRS_od_amt" /></td>

								<td align="right">No of OD Accounts closed</td>
								<td><input type="text" name="FRS_od_accounts"
									id="FRS_od_accounts" /></td>

							</tr>
							<!-- Added By rajesh -->
							
							<tr>
								<td align="right">No Of SDR Customers Identified</td>
								<td><input type="text" name="FRS_SDRCUST_COUNT" id="FRS_SDRCUST_COUNT" /></td>
							</tr>

							<tr></tr>
							<tr></tr>
							<tr></tr>
							<tr>
								<td>&nbsp;</td>
								<td></td>
								<td><input type="submit" name="submit"
									value="Submit Details"></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</table>


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