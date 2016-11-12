<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FRS HomePage</title>
<% 
	 					//String ur="admin";
						String ur=(String)request.getSession().getAttribute("userrole");						
 						//String n=null;
						if (request.getSession().getAttribute("username") == null) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
			 			/* else if (ur.equals("user")) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						} */
 
						//else
						//{
						else if (request.getSession().getAttribute("username") != null && ur!= null) 
						{
						%>
						
						

<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
</script><script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%"
	leftmargin="100%" marginwidth="100%" topmargin="100%">

	<table width="100%" cellspacing="0" cellpadding="0" border="0"
		bordercolor="block">
		<tbody>
			<tr>
				<td valign="top">
					<table class="header" cellspacing="0" cellpadding="0" border="0"
						bordercolor="block" width="100%" height="100%">
						<tbody>
							<tr>
								<td><h4>
										<b><font color="white">Bhartiya Samruddhi Finance
												Limited</font></b>
									</h4>
									<br> <b><font color="white">Field Reporting
											System</font></b></td>
								<td width="90"><img src="images/basix-logo.gif" width="75"
									height="100" align="middle" /></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="top" class="nav_head" height="20" align="right"><a
					href="logout.jsp"><font color="white"><b> Logout</b></font></a></td>
				<td></td>
			</tr>
			<tr valign="top">
				<td>
				
				<%} %>
				
				<%
				if (ur.equals("admin") || ur.equals("unithead") || ur.equals("areahead"))
				{
					%>

					<form action="">

						<center>

							<table width="500" cellpadding="0" cellspacing="0">

								<tr>
									<td>
										<table width="80%" align="center" cellpadding="5"
											cellspacing="5">

											<tr>
												<td>
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0" class="homelinksTab2">
														<tr>
															<td width="3%"><img
																src="images/home_linktab_left.jpg" width="100%"
																height="44" alt="Home links" /></td>
															<td width="94%" align="center"><a
																href="AdminHome.jsp">FRS Administrator</a></td>
															<td width="3%" align="right"><img
																src="images/home_linktab_right.jpg" width="10"
																height="44" alt="Home links" /></td>
														</tr>
													</table>
												</td>
											</tr>

					<tr>
                                  <td>
                                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="homelinksTab2">
                                  <tr>
                                    <td width="3%"><img src="images/home_linktab_left.jpg" width="100%" height="44" alt="Home links" /></td>
                                    <td width="94%" align="center"><a href="lsrrecovery.jsp">LSR Field Entry</a></td>
                                    <td width="3%" align="right"><img src="images/home_linktab_right.jpg" width="10" height="44" alt="Home links" /></td>
                                    </tr>
                                  </table>
                                  </td>
							</tr>
						<tr>
                                  <td>
                                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="homelinksTab2">
                                  <tr>
                                    <td width="3%"><img src="images/home_linktab_left.jpg" width="100%" height="44" alt="Home links" /></td>
                                    <td width="94%" align="center"><a href="Frsreports.jsp">FRS Reports</a></td>
                                    <td width="3%" align="right"><img src="images/home_linktab_right.jpg" width="10" height="44" alt="Home links" /></td>
                                    </tr>
                                  </table>
                                  </td>
							</tr>

						<tr>
                                  <td>
                                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="homelinksTab2">
                                  <tr>
                                    <td width="3%"><img src="images/home_linktab_left.jpg" width="100%" height="44" alt="Home links" /></td>
                                    <td width="94%" align="center"><a href="DiffActivites.jsp">Customer Connect</a></td>
                                    <td width="3%" align="right"><img src="images/home_linktab_right.jpg" width="10" height="44" alt="Home links" /></td>
                                    </tr>
                                  </table>
                                  </td>
							</tr>
							
							<tr>
                                  <td>
                                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="homelinksTab2">
                                  <tr>
                                    <td width="3%"><img src="images/home_linktab_left.jpg" width="100%" height="44" alt="Home links" /></td>
                                    <td width="94%" align="center"><a href="AchangePwd.jsp">Change Password</a></td>
                                    <td width="3%" align="right"><img src="images/home_linktab_right.jpg" width="10" height="44" alt="Home links" /></td>
                                    </tr>
                                  </table>
                                  </td>
							</tr>
							</table></td>
								</tr>
							</table>
 
						</center>
					</form>
					<%} %>
					
					<%
				if (ur.equals("user") || ur.equals("fs") || ur.equals("fx"))
				{
					%>

					<form action="">

						<center>

							<table width="500" cellpadding="0" cellspacing="0">

								<tr>
									<td>
										<table width="80%" align="center" cellpadding="5"
											cellspacing="5">

											<tr>
												<td>
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0" class="homelinksTab2">
														<tr>
															<td width="3%"><img
																src="images/home_linktab_left.jpg" width="100%"
																height="44" alt="Home links" /></td>
															<td width="94%" align="center"><a
																href="EmpDetail">LSR Field Entry</a></td>
															<td width="3%" align="right"><img
																src="images/home_linktab_right.jpg" width="10"
																height="44" alt="Home links" /></td>
														</tr>
													</table>
												</td>
											</tr>

					<!-- <tr>
                                  <td>
                                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="homelinksTab2">
                                  <tr>
                                    <td width="3%"><img src="images/home_linktab_left.jpg" width="100%" height="44" alt="Home links" /></td>
                                    <td width="94%" align="center"><a href="lsrrecovery.jsp">LSR Field Entry</a></td>
                                    <td width="3%" align="right"><img src="images/home_linktab_right.jpg" width="10" height="44" alt="Home links" /></td>
                                    </tr>
                                  </table>
                                  </td>
							</tr> -->
						<tr>
                                  <td>
                                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="homelinksTab2">
                                  <tr>
                                    <td width="3%"><img src="images/home_linktab_left.jpg" width="100%" height="44" alt="Home links" /></td>
                                    <td width="94%" align="center"><a href="Frsreports.jsp">FRS Reports</a></td>
                                    <td width="3%" align="right"><img src="images/home_linktab_right.jpg" width="10" height="44" alt="Home links" /></td>
                                    </tr>
                                  </table>
                                  </td>
							</tr>
							
							<tr>
                                  <td>
                                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="homelinksTab2">
                                  <tr>
                                    <td width="3%"><img src="images/home_linktab_left.jpg" width="100%" height="44" alt="Home links" /></td>
                                    <td width="94%" align="center"><a href="AchangePwd.jsp">Change Password</a></td>
                                    <td width="3%" align="right"><img src="images/home_linktab_right.jpg" width="10" height="44" alt="Home links" /></td>
                                    </tr>
                                  </table>
                                  </td>
							</tr>

						<!-- <tr>
                                  <td>
                                  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="homelinksTab2">
                                  <tr>
                                    <td width="3%"><img src="images/home_linktab_left.jpg" width="100%" height="44" alt="Home links" /></td>
                                    <td width="94%" align="center"><a href="DiffActivites.jsp">Customer Connectivity</a></td>
                                    <td width="3%" align="right"><img src="images/home_linktab_right.jpg" width="10" height="44" alt="Home links" /></td>
                                    </tr>
                                  </table>
                                  </td>
							</tr> -->
							</table></td>
								</tr>
							</table>
 
						</center>
					</form>
					<%} %>
					
					<%
				if (request.getSession().getAttribute("username") != null && ur!= null)
				{
					%>
					
				</td>
			</tr>
			<tr>
				<td valign="bottom" class="footer" height="5"></td>
			</tr>
		</tbody>
	</table>
	<center>
		<h5>
			<b><font color="block">All CopyRights Reserved Basix@2013</font></b>
		</h5>
	</center>

</body>
<%} %>
</html>