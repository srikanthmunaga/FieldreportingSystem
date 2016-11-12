<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UH Log Report</title>
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
<script type="text/javascript" src="JS/jquery.min.js"></script>
<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script>
<form id="form1" name="form1" action="Pleasewait2" method="post" onSubmit="return validateForm();">
		<center>
			<table align="center">
				<tr>
					<td height="">
						<!-- 						<fieldset style="background-color:">
							<legend class="style23">Region Master</legend>
							<legend class="formTitle">Confirmation to Send SMS</legend>
 -->
						<table bgcolor="" width="100%" height="91%" border="0"
							align="center" bordercolor="#000000">
							<tr align="center">
								<%
									HttpSession ses = request.getSession(false);
								%>

								<td colspan="3" align="center">
									<table align="center">
										<tr>
											<td class="style23" align="right"><font color="blue"><b>Total
														number of OD Customer found (to send SMS with OD days >= <%=ses.getAttribute("oddays")%>) :&nbsp;&nbsp;&nbsp;<font color="red"><%=ses.getAttribute("total")%></font></b></font>
											</td>
										</tr>
										<tr>
											<td align="right"><font color="blue"><b>Out of
														Units :&nbsp;&nbsp;&nbsp;<%=ses.getAttribute("unitcount")%></b></font>
											</td>
										</tr>
										<tr>
											<td align="right"><font color="blue"><b>Under
														Areas :&nbsp;&nbsp;&nbsp; <%=ses.getAttribute("areacount")%></b></font><br>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr><td colspan="3"><br></td></tr>
							<tr align="center">

								<td align="center" class="style23" colspan="3"><font color="red"><b>Do
											you want to proceed ? </b></font></td>
								</tr><tr><td colspan="3" align="center"><table align="center">
								<tr>
								<td><input
										type="button" onclick="window.location.href='SMStoODCustomer.jsp'" value="Cancel" onMouseOver="goLite2(this);"
										onMouseOut="goDim2(this);" name="cancel"></td>
										<td><input type="submit" onMouseOver="goLite2(this);"
									onMouseOut="goDim2(this);" value="Proceed"></td>
										</tr></table>
								<!-- <input type="submit" onMouseOver="goLite2(this);"  onMouseOut="goDim2(this);" value="NO" onclick="document.location.href='SMStoODCustomer.jsp'"> -->



							</tr>
							<tr>


							</tr>
						</table> <!-- 						</fieldset> -->
					</td>
				</tr>
			</table>
		</center>
	</form>
	<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>
<%
	}//authorised acess else	//------------------------------------------------------------------------------------
%>


</html>
