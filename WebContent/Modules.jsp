<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modules</title>
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

<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>

<script type="text/javascript">


</script>
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script>
<form id="form1" name="form1" >
						

 

 
				<!-- <td valign="middle" height="350">  -->  

					
						<center>
						<table width="100%" cellpadding="0" cellspacing="0">
						<tr class="content-area"><td align="right"><a	href="logout.jsp"><font color="blue"><b> Logout</b></font></a></td></tr>
						<tr class="content-area">

							<!-- <table width="100%" cellpadding="0" cellspacing="0"> -->
								<tr>
									


								</tr>

								<tr>
									<td height="380">
										<table width="80%" align="center" cellpadding="5"
										cellspacing="5">

										<tr>
										<%
								 
											//String ur=(String)request.getSession().getAttribute("userrole");
											if((ur.equals("admin"))||(ur.equals("audit")))
											{
										
								  
											%>
											<td width=20%>
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" class="homelinksTab2">
													<tr>
														<td width="3%"><img
															src="images/home_linktab_left.jpg" width="100%"
															height="44" alt="Home links" /></td>
															<% String rajesh="Rajesh"; %>
														<td width="94%" align="center">
														<%((HttpServletRequest) request).getSession().setAttribute("module","Administrator"); %>
														<a href="Cchome.jsp" 
														style="color: white;">
																Administrator Module</a></td>
														<td width="3%" align="right"><img
															src="images/home_linktab_right.jpg" width="10"
															height="44" alt="Home links" /></td>
													</tr>
												</table>
											</td>
											<%}%>
											<td width=20%>
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" class="homelinksTab2">
													<tr>
														<td width="3%"><img
															src="images/home_linktab_left.jpg" width="100%"
															height="44" alt="Home links" /></td>
														<td width="94%" align="center">
														<%((HttpServletRequest) request).getSession().setAttribute("module","LSR - War Module"); %>
														<a href="Whome.jsp"
															style="color: white;">WAR Room   </a></td>
														<td width="3%" align="right"><img
															src="images/home_linktab_right.jpg" width="10"
															height="44" alt="Home links" /></td>
													</tr>
													
												</table>
											</td>
											<td width=20%>
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" class="homelinksTab2">
													<tr>
														<td width="3%"><img
															src="images/home_linktab_left.jpg" width="100%"
															height="44" alt="Home links" /></td>
														<td width="94%" align="center">
														<%((HttpServletRequest) request).getSession().setAttribute("module","Field Staff"); %>
														<a href="Fhome.jsp"
															style="color: white;"> Field Staff Module</a></td>
														<td width="3%" align="right"><img
															src="images/home_linktab_right.jpg" width="10"
															height="44" alt="Home links" /></td>
													</tr>
												</table>
											</td>
											<!-- Added By Rajesh	 -->
											 
											<td width=20%>
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" class="homelinksTab2">
													<tr>
														<td width="3%"><img
															src="images/home_linktab_left.jpg" width="100%"
															height="44" alt="Home links" /></td>
														<td width="94%" align="center">
														<%((HttpServletRequest) request).getSession().setAttribute("module","Indent management"); %>
														<a href="Chome.jsp"
															style="color: white;"> Indent Management Module</a></td>
														<td width="3%" align="right"><img
															src="images/home_linktab_right.jpg" width="10"
															height="44" alt="Home links" /></td>
													</tr>
												</table>
											</td>
											 <td width=20%>
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" class="homelinksTab2">
													<tr>
														<td width="3%"><img
															src="images/home_linktab_left.jpg" width="100%"
															height="44" alt="Home links" /></td>
														<td width="94%" align="center">
														<%((HttpServletRequest) request).getSession().setAttribute("module","SH/UH/FX Work Plan"); %>
														<a href="Mhome.jsp"
															style="color: white;"> SH/UH/FX Work Plan</a></td>
														<td width="3%" align="right"><img
															src="images/home_linktab_right.jpg" width="10"
															height="44" alt="Home links" /></td>
													</tr>
												</table>
											</td>
											  
											
											<!-- Completed By rajesh -->
											
										</tr>
									<%-- 	<tr>
										<td></td>
										<td width="33%">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" class="homelinksTab2">
													<tr>
														<td width="3%"><img
															src="images/home_linktab_left.jpg" width="100%"
															height="44" alt="Home links" /></td>
														<td width="94%" align="center">
														<%((HttpServletRequest) request).getSession().setAttribute("module","Customer Connect"); %>
														<a href="Chome.jsp"
															style="color: white;"> Customer Connect Module</a></td>
														<td width="3%" align="right"><img
															src="images/home_linktab_right.jpg" width="10"
															height="44" alt="Home links" /></td>
													</tr>
													
												</table>
											</td>
										</tr>
									 --%>	
									</table> <!-- Outer table -->
									</td>
								</tr>
								
							</table>
							

						</center>


	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>
<%} %>
</html>