
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
<title>Freeze User</title>

<% 
						
						String ur=(String)request.getSession().getAttribute("userrole");
						//System.out.println("it is coming from jsp"+ur);
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
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />

</script><script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%"
	leftmargin="100%" marginwidth="100%" topmargin="100%"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Cmenu.js"></script>
<form id="form1" name="form1" action="deleteuserUnit" method="post">
						<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="380"></td>
								<td width="380" align="left"><b><a
										href="AdminHomeUnit.jsp">UNIT HOME</a></b></td>
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
										id="username"
										onfocus="addSuggestionUnit('empname-username','username');"
										style="border-color: #0B4599;" /></td>
								</tr>

								<td>
									<!--<select name="username">
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
 						<input type="text" name="username" id="username" onfocus="addSuggestion('city_id','city_id');">
 						
 						</td>					
 -->
								</tr>
								<tr>
									<td></td>
									<td align="right"><input type="submit" value="Freeze User" />
									</td>
								</tr>
							</table>
						</center>
	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
</body>

<%} %>
</html>