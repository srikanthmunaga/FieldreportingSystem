<%@page import="frs_cls.JdbcConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select user forom DB</title>
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

<script type="text/javascript">

function validate()
{
if(document.getElementsByName("username")[0].value=="")
{
  alert("Please Select the UserName");
  document.getElementsByName("username")[0].focus();
  document.getElementsByName("username")[0].style.background="#fffacd";
  return false;
}
}



</script>
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
								<td>
									<h4>
										<b><font color="white">Bhartiya Samruddhi Finance
												Limited</font></b>
									</h4>
									<br> <b><font color="white">Field Reporting
											System</font></b>
								</td>
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
					<!-- <form id="form1" name="form1" action="userlist" > -->
					<form id="form1" name="form1" action="HRuserlist">
						<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="380"></td>
								<td width="380" align="left"><b><a href="HRDetails.jsp">HR
											Details</a></b></td>
							</tr>
						</table>
						<center>
							<table>
								<tr>
									<!-- <td align="right">Username</td> -->
									<td></td>
								<tr>
									<td align="right">Enter UserName</td>
									<td align="left"><input type="text" name="username"
										id="username" onFocus="addSuggestion('empname-username','username');"
										style="border-color: #0B4599;" /></td>
								</tr>

								<td>
									<!--<select name="username" >
							<option value="--Select --">--Select User Name --</option>
								<%
									JdbcConnect jc=new JdbcConnect();
									Connection con = jc.getConnection();
									Statement st=con.createStatement();
									ResultSet rs=st.executeQuery("select distinct username from frs_user");
									while(rs.next()){
    								String str=rs.getString(1);
								%>
    							<option value="<%=str%>"><%=str%></option>
    							<%
								}
								con.close();
								%>
						</select></td>
 --%> 
 <!-- 						<td>
 						<input type="text" name="username">
 						
 						</td>					
 -->
								</tr>
								<tr>
									<td></td>
									<td align="right"><input type="submit" value="Get User"
										onClick="return validate();" /></td>
								</tr>
							</table>
						</center>
						</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
<%} %>
</html>