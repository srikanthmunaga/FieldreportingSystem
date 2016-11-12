
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
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%"	leftmargin="100%" marginwidth="100%" topmargin="100%">
<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script> 
<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if(role.equals("admin") ||role.equals("areahead") || role.equals("unithead"))
{ %>
<script type="text/javascript">

function validate()
{
	var patt1=new RegExp("[A-Za-z0-9]");
	if(!patt1.test(document.getElementsByName("username")[0].value))
	{
	  alert("Please enter the username");
	  document.getElementsByName("username")[0].focus();
	  document.getElementsByName("username")[0].style.background="#fffacd";
	  return false;
	}
}
</script>
<form id="form1" name="form1" action="deleteuser" method="post" onSubmit="return validate();">
<!-- 					<table cellpadding="1" cellspacing="1" background="blue">
							<tr>

								<td width="380"></td> 
								<td width="380" align="left"><b><a href="AdminHome.jsp">Administrator HOME</a></b></td>
								<td width="380" align="left"><b><a href="Ahome.jsp">HOME</a></b></td>
							</tr>
						</table>
						<!--<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="380"></td>
								<td width="380" align="left"><b><a href="AdminHome.jsp">ADMIN
											HOME</a></b></td>
							</tr>
						</table>--><br />
 						<center>
							<table>
								<tr>
									<td></td>
									
								<tr>
									<td align="right">Enter UserName</td>
									<td align="left"><input type="text" name="username"
										id="username" onFocus="addSuggestion('empname-username','username');"
										style="border-color: #0B4599;" /></td>
								</tr>
	                      <%--   <td>
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
 
 
								</tr> --%>
								
								<tr>
									<td></td>
									<!-- <td align="right"><input type="submit" value="Freeze User"/></td> -->
									<td align="right"><input type="submit" id="generate" name="generate"
	value="Freeze User" onclick="generateTable('war_period');" title=""
	class="groovybutton" onmouseover="goLite(this.form.name,this.name)"
	onmouseout="goDim(this.form.name,this.name)" /></td>
									
								</tr>
							</table>
						</center>
	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
</body>

<%
}//authorised acess else------------------------------------------------------------------------------------
else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to Freeze the user
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>

<%
}
}
	%>
	
</html>
