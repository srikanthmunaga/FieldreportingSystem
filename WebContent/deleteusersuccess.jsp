<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Freeze user success</title>
<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();">	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script>
<form id="form1" name="form1" action="">
<!-- 						<!--<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="380"></td>
								<td width="380" align="left"><b><a href="AdminHome.jsp">ADMIN
											HOME</a></b></td>
							</tr>
						</table>--><br />
 						<center>

							<h4>
								<font color="blue" style="font-family: serif;">FRS User <b><%=session.getAttribute("un")%></b>
									is Freezed Successfully........!<br></font>
							</h4>
							<table>

								<tr>
									<td>
										<!-- User Name -->
									</td>
									<td><font color="blue" style="font-family: serif;"></font></td>
								</tr>
							</table>

						</center>
	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>

</html>
	