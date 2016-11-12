<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UH Log Report</title>
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

<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery.min.js"></script>
<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script>
<!-- <form name="auto"  action="CSVUpload" method="post" enctype="multipart/form-data"> -->
<form name="auto"  action="Targetpod_Upload" method="post" enctype="multipart/form-data">

<table bgcolor="" width="100%" height="91%" border="0"
							align="center" bordercolor="#000000">
							<tr align="center"><td  colspan="3"><img alt="" src="images/loading-bars.gif"></td></tr>
							<tr align="center">
								<td colspan="3" align="center">
								
								<font color="red" size="3"><b>Please wait, Uploading is under Process....! </b></font>
								</td></tr></table>
</form>

	<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
	<script>
//alert("Srinu DEBUG 1");
    document.auto.submit();
</script>
	
</body>
<%
	}//authorised acess else	//------------------------------------------------------------------------------------
%>


</html>
