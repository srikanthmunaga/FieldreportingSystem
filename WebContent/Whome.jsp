<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>WAR Room Module</title>
	<%
if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
 {
    response.sendRedirect("frslogin.jsp"); // NotF<> logged in, redirect to login page.
	}

else //if (((HttpServletRequest) request).getSession().getAttribute("user") != null)
   {
   // chain.doFilter(request, response); // Logged in, so just continue.
   %>
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>
</head>

<body bottommargin="100%" marginheight="100%" rightmargin="100%"
	leftmargin="100%" marginwidth="100%" topmargin="100%"
	onload="disable();">
	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><!-- <script type="text/javascript" src="JS/Fmenu.js"></script> -->
	<!-- <script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script> -->
	<!-- <body tracingsrc="images/100_0497.JPG" tracingopacity="8"> -->

	<!---------------- Code for FRS menu.js starts here  ------------------------->
										<!-- drop down code start here-->
										<table width="100%">
											<tr>
												<!-- style="background:url(images/menu08.jpg) repeat scroll 0 0 transparent "> -->
												<td width="14%" onMouseOver="showmenu('Masters')"
													onMouseOut="hidemenu('Masters')" class="bg_menu">
													<div align="center">
														<a href="Whome.jsp" class="style21" id="aMasters">
															<u>M</u>asters</a><br />
													</div>
													<table class="menu" id="Masters" width="182" border="0"
														bordercolor="blue">
														<!-- <tr>
															<td class="menu"><a href="F_BsflUnit master.jsp">Unit Master</a></td>
														</tr> -->
														<tr><td class="menu"><a href="Village master.jsp">Village Master</a></td></tr>
														<tr>
														
															<td class="menu"><a href="WchangePwd.jsp">Change pwd</a></td>
														</tr>
													</table>
												</td>
												<td width="14%" onMouseOver="showmenu('Operations')"
													onMouseOut="hidemenu('Operations')"  class="bg_menu">
													<div align="center">
														<a href="Whome.jsp" class="style21" id="aOperations">
															Operatio<u>n</u>s
														</a><br />
													</div>
													<table class="menu" id="Operations" width="182" border="0"
														bordercolor="blue">
														<tr><td class="menu"><a href="war target entry.jsp">WAR Room - LSR Weekly Plan</a></td></tr>
														<tr><td class="menu"><a href="warRemarks.jsp">WAR Room - Remarks</a></td></tr>
													</table>
												</td>
												<td width="17%" onMouseOver="showmenu('Reports')"
													onMouseOut="hidemenu('Reports')" class="bg_menu">
													<div align="center">
														<a href="Whome.jsp" class="style21" id="aReports">
															<u>R</u>eports</a><br />
													</div>
													<table class="menu" id="Reports" width="220" border="0"
														bordercolor="blue">
														<tr><td class="menu"  ><a href="WARFieldStaffRep.jsp" >WAR Room Field Staff Report</a></td></tr>
														<tr><td class="menu"  ><a href="WARUnitRep.jsp" >WAR Room Unit Report</a></td></tr>
														<tr><td class="menu"  ><a href="WARAreaRep.jsp" >WAR Room Region Report</a></td></tr>
														<tr><td class="menu"  ><a href="WARHORep.jsp" >WAR Room HO Report</a></td></tr>
														<tr><td class="menu"  ><a href="WARweeklyPlanRep.jsp" >WAR Room Field Staff Weekly Plan Report</a></td></tr>
													</table>
												</td>
												<td width="14%" onMouseOver="showmenu('Logoff')"
													onMouseOut="hidemenu('Logoff')" class="bg_menu">
													<div align="center">
														<a href="Whome.jsp" class="style21" id="aLogoff">
															<u>L</u>ogoff</a><br />
													</div>
													<table class="menu" id="Logoff" width="182" border="0"
														bordercolor="blue">
														<tr>
															<td class="menu"><a href="Modules.jsp">Switch Module</a></td>
														</tr>
														<tr>
															<td class="menu"><a href="logout.jsp">Logout</a></td>
														</tr>
													</table>
												</td>
												<td width="41%"  class="bg_menu style21" align="right">WAR Room Module</td>
											</tr>
										</table> <!-- drop down code ended here-->

									</td></tr>
<tr class="content-area">
				<td valign="middle" height="350" align="center">
<!------ Code for FRS menu.js ends here  -------------->
<p style="color: blue;" >Welcome To WAR Room Module</p></td>
					</tr>
	<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>
	<%
  }//authorised acess else
%>
</html>

