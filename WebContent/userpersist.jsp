<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%
String huma_id=request.getParameter("huma_id");
String Date=request.getParameter("FRS_date");
String NoOfVillagesVisited=request.getParameter("FRS_village_count");
String NoOfCustomersMeet=request.getParameter("FRS_Cust_count");
String AmountRecovered=request.getParameter("FRS_total_amt");
String NoOfAccountsRecovered=request.getParameter("FRS_total_accounts");
String NoOfOldAmountRecovered=request.getParameter("FRS_od_amt");
String NoOfOldAccountsClosed=request.getParameter("FRS_od_accounts");
String NoOfSDRCustomers=request.getParameter("FRS_SDRCUST_COUNT");
%>
<html>

<head>

<meta http-equiv="Content-Style-Type" content="text/css">
<title>Field Staff Recovery Detatils Submit success</title>
<% if (request.getSession().getAttribute("username") == null) {
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

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script>
<form id="form1" name="form1" method="post" action="">

					<h4>
						<font color="blue">The Following recovery details are
							successfully submitted by the Field Staff</font>
					</h4>

					<table>
						<tr>
							<td>Emp Id</td>
							<td><%=huma_id%></td>
						</tr>
						<tr>
							<td>Date</td>
							<td><%=Date%></td>
						</tr>
						<tr>
							<td>NoOfVillagesVisited</td>
							<td><%=NoOfVillagesVisited%></td>
						</tr>
						<tr>
							<td>NoOfCustomersMeet</td>
							<td><%=NoOfCustomersMeet%></td>
						</tr>
						<tr>
							<td>AmountRecovered</td>
							<td><%=AmountRecovered%></td>
						</tr>
						<tr>
							<td>NoOfAccountsRecovered</td>
							<td><%=NoOfAccountsRecovered%></td>
						</tr>
						<tr>
							<td>ODAmountRecovered</td>
							<td><%=NoOfOldAmountRecovered%></td>
						</tr>
						<tr>
							<td>NoOfODAccountsClosed</td>
							<td><%=NoOfOldAccountsClosed%></td>
						</tr>
						<tr>
							<td>NoOfSDRCustomers</td>
							<td><%=NoOfSDRCustomers%></td>
						</tr>

					</table>
									
 </form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
				
	<!-- Comment added by Rajashekhar -->
</body>
<%} %>
</html>