<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FRS HomePage for Unit</title>
<% 
	 					//String ur="admin";
						String ur=(String)request.getSession().getAttribute("userrole");						
 						//String n=null;
						if (request.getSession().getAttribute("username") == null) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
			 			else if (ur.equals("user")) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
 
						else
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
																href="AdminHomeUnit.jsp">FRS Administrator</a></td>
															<td width="3%" align="right"><img
																src="images/home_linktab_right.jpg" width="10"
																height="44" alt="Home links" /></td>
														</tr>
													</table>
												</td>
											</tr>

											<tr>
												<td>
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0" class="homelinksTab2">
														<tr>
															<td width="3%"><img
																src="images/home_linktab_left.jpg" width="100%"
																height="44" alt="Home links" /></td>
															<td width="94%" align="center"><a
																href="lsrrecoveryUnit.jsp">LSR Field Entry</a></td>
															<td width="3%" align="right"><img
																src="images/home_linktab_right.jpg" width="10"
																height="44" alt="Home links" /></td>
														</tr>
													</table>
												</td>
											</tr>

											<tr>
												<td>
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0" class="homelinksTab2">
														<tr>
															<td width="3%"><img
																src="images/home_linktab_left.jpg" width="100%"
																height="44" alt="Home links" /></td>
															<td width="94%" align="center"><a
																href="FrsreportsUnit.jsp">FRS Reports</a></td>
															<td width="3%" align="right"><img
																src="images/home_linktab_right.jpg" width="10"
																height="44" alt="Home links" /></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>

						</center>




	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
</body>
<%} %>
</html>