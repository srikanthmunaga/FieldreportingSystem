<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee id Getting</title>
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
if(document.getElementsByName("huma_id")[0].value=="")
{
  alert("Please enter the Emp Id");
  document.getElementsByName("huma_id")[0].focus()
  document.getElementsByName("huma_id")[0].style.background="#fffacd";
  return false;
}
}
</script><script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script>
<form id="form1" name="form1" action="EmpDetailUnit" method="post"
						onSubmit="return validate();">

						<table cellpadding="1" cellspacing="1" background="blue">
							<tr>

								<td width="380"></td>
								<td width="380" align="left"><b><a
										href="lsrrecoveryUnit.jsp">LSR Home</a></b></td>
							</tr>
						</table>


						<center>
							<table>
								<tr>
									<td>Employee Id</td>
									<td><input type="text" name="huma_id" name="huma_id"
										onfocus="addSuggestionUnit('huma_id','huma_id');"
										style="border-color: #0B4599;" /></td>
								</tr>
								<tr>
									<td></td>
									<td><input type="submit" value="Get Submit Form">
									</td>
								</tr>
							</table>
						</center>

					</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>
<%} %>
</html>