<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%
String unit_id=request.getParameter("bsflunit_ucode");
String unit_name=request.getParameter("unit_name");
String st_id=request.getParameter("st_id");
String st_name=request.getParameter("st_name");
String last_issued=request.getParameter("last_issued");
String closing_stock=request.getParameter("closing_stock");
String available_stock=request.getParameter("available_stock");
String requested_stock=request.getParameter("req_stock");
String date_of_indent=request.getParameter("d_oi");
String indent_by=request.getParameter("ind_by");
String issue_stock=request.getParameter("issue_stock");
String date_of_issue=request.getParameter("date_is");
String cou_id=request.getParameter("cou_id");
String cou_name=request.getParameter("cou_name");
%>
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

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Bmenu.js"></script>
<form id="form1" name="form1" method="post" action="">
				
					<h4>
						<font color="blue">The Following indent details are
							 submitted by the Field Staff And Mail is not sent to unit as no Email is provided</font>
					</h4>

					<table>
						<tr>
							<td>Unit Id</td>
							<td><%=unit_id%></td>
						</tr>
						<tr>
							<td>Unit Name</td>
							<td><%=unit_name%></td>
						</tr>
						<tr>
							<td>Stationary Id</td>
							<td><%=st_id%></td>
						</tr>
						<tr>
							<td>Stationary Name</td>
							<td><%=st_name%></td>
						</tr>
						<tr>
							<td>last Issued</td>
							<td><%=last_issued%></td>
						</tr>
						<tr>
							<td>Closing Stock</td>
							<td><%=closing_stock%></td>
						</tr>
						<tr>
							<td>Available Stock</td>
							<td><%=available_stock%></td>
						</tr>
						<tr>
							<td>Requested Stock</td>
							<td><%=requested_stock%></td>
						</tr>
						<tr>
							<td>Date Of Indent</td>
							<td><%=date_of_indent%></td>
						</tr>
						<tr>
							<td>Indent By</td>
							<td><%=indent_by%></td>
						</tr>
						<tr>
							<td>Issue Stock</td>
							<td><%=issue_stock%></td>
						</tr>
						
						<tr>
							<td>Date of Issue</td>
							<td><%=date_of_issue%></td>
						</tr>
						<tr>
							<td>Courier Number</td>
							<td><%=cou_id%></td>
						</tr>
						<tr>
							<td>Courier Name</td>
							<td><%=cou_name%></td>
						</tr>

					</table>
				
 </form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
				
	<!-- Comment added by Rajashekhar -->
</body>
<%} %>
</html>