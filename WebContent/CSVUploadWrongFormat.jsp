<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<html>

<head>

<meta http-equiv="Content-Style-Type" content="text/css">
<title>recovery Details submit success</title>
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
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script>
<form id="form1" name="form1" method="post" action="">
						




					<h4 align="center">
						<font color="blue">Incorrect Format of Uploaded File. <br> 
						 Please See the Sample CSV File Format and Upload total sheet again.
						 </font>
					</h4>
					<br /><div align="left">Hints:<ul type="square">
					<li>Column order should be Unit/Emp code, Amount, Date</li>
					<li>Unit codes should be exactly 3 digits or Emp code should be less than 8 digits</li>
					<li>Amount should not be blank, use numeric or 0</li>
					<li>Date should not be blank, use date format for this column</li>
					
					</ul></div>
				<center>	<input type="button" id="idname" value = "Back" onclick="javascript:window.location.href='Ahome.jsp';"/></center><br>
					
				
				
 </form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
				
	<!-- Comment added by Rajashekhar -->
</body>
<%} %>
</html>