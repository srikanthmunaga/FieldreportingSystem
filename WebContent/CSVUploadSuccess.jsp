<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%
String tot_csv_record=request.getAttribute("tot_no_of_csv_record").toString();
String csv_ins=request.getAttribute("csv_inserted").toString();
String csv_update=request.getAttribute("csv_updated").toString();
/*
String NoOfCustomersMeet=request.getParameter("FRS_Cust_count");
String AmountRecovered=request.getParameter("FRS_total_amt");
String NoOfAccountsRecovered=request.getParameter("FRS_total_accounts");
String NoOfOldAmountRecovered=request.getParameter("FRS_od_amt");
String NoOfOldAccountsClosed=request.getParameter("FRS_od_accounts");
String NoOfSDRCustomers=request.getParameter("FRS_SDRCUST_COUNT");
*/
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
<script type="text/javascript">
function goToOther(){
	alert("before going to target pod.jsp")
    window.location = '/target pod.jsp';
}
</script>
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script>
<form id="form1" name="form1" method="post" action="" >
						


<div align="center">

					<h4>
						<font color="blue">CSV File Uploaded Successfully.</font>
					</h4>

			<table>
				<tr>
					<td>Total Number of Success Records </td>
					<td>:</td>
					<td><%=tot_csv_record%></td>
				</tr>

				<tr>
					<td>Number of. Records Inserted </td>
					<td>:</td>
					<td><%=csv_ins%></td>
				</tr>

				<tr>
					<td>Number of. Records Updated </td>
					<td>:</td>
					<td><%=csv_update%></td>
				</tr>
				<tr>
				<td>
				
			     <input type="button" id="idname" value = "Back" onclick="javascript:window.location.href='Ahome.jsp';"/><br>
				</td>
				</tr>
			</table>
		</div>		
 </form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
				
	<!-- Comment added by Rajashekhar -->
</body>
<%} %>
</html>