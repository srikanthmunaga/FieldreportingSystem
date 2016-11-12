<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify User Success</title>
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
</script><script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%"
	leftmargin="100%" marginwidth="100%" topmargin="100%">

<form id="form1" name="form1" action="">
						<!--<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="380"></td>
								<td width="380" align="left"><b><a href="AdminHome.jsp">ADMIN
											HOME</a></b></td>
							</tr>
						</table>--><br />
						<center>

							<h4>
								<font color="blue" style="font-family: serif;">FRS User
									is Updated Successfully........!<br>
								</font>
							</h4>
							<table>

								<tr>
									<td><font color="grey">User Name</font></td>
									<td><font color="blue" style="font-family: serif;"><%=session.getAttribute("un")%></font></td>
								</tr>

								<tr>
									<td><font color="grey">User Role</font></td>
									<td><font color="blue" style="font-family: serif;"><%=session.getAttribute("ur")%></font></td>
								</tr>

							</table>

						</center>
	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
<%


//my name is srinu
}//authorised acess else------------------------------------------------------------------------------------
	%>

</body>
</html>