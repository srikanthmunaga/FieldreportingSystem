<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Frs login page</title>
<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function auth(){
	if(document.getElementsByName("username")[0].value=="")
	{
      alert("Please enter the Username");
      document.getElementsByName("username")[0].focus()
      document.getElementsByName("username")[0].style.background="#fffacd";
      return false;
   }
	
	if(document.getElementsByName("password")[0].value=="")
	{
      alert("Please enter the Password");
      document.getElementsByName("password")[0].focus()
      document.getElementsByName("password")[0].style.background="#fffacd";
      return false;
   }
 //alert("Validating");	
}
function goLite2(BTN)
{
BTN.style.color = "#ffffff";
BTN.style.backgroundImage="url(images/back04.gif)";
}
function goDim2(BTN)
{
BTN.style.color = "";
BTN.style.backgroundImage="url(images/back03.gif)";
}
</script><script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%"
	leftmargin="100%" marginwidth="100%" topmargin="100%"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script>
	
					<form name="frm" method="post" action="123"
						onsubmit="return auth();">
						<!-- 
<img src="images/basix-logo.jpg" width="100" height="100" align="right"/>
 -->
						<!-- <center>   -->
						<center>
						<table><tr>
				<td valign="middle" height="350" align="center">
				<center>
							<h4>
								<font color="blue" style="font-family: serif;">User_name
									and Password are Incorrect Please Try Again</font>
							</h4>
						</center>
				<table><tr>
				<td align="center">
						<fieldset style="background-color:; border-color: #0B4599;">
							<!-- <legend class="formTitle"> Login </legend> --> 
							<table>
								<tr><td colspan="2"><fieldset style="background-color:#0B4599; border-color: white;">
							<center class="formTitle"><font color="white">Login</font></center></fieldset></td></tr> 
								<tr><td><br /></td><td></td></tr>
								
								<tr>
									<td align="right" class="dislpayLabel">User Name</font> </td>
									<td><input type="text" maxlength="20" name="username"
										id="username" /></td>
								</tr>
								<tr>
									<td align="right" class="dislpayLabel">Password</td>
									<td><input type="password" maxlength="20" name="password"
										id="password" /></td>
								</tr>

								<tr>
									<td></td>
									<td><big><input type="submit" value="LOGIN" onmouseover="goLite2(this);" onmouseout="goDim2(this);"/></big></td>

								</tr>
							</table>
						</fieldset></td></tr></table></td></tr></table>
						</center>
					</form>
					<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
</body>
</html>