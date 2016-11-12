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
%>
<html>

<head>

<meta http-equiv="Content-Style-Type" content="text/css">
<title>Fail Submittion</title>
<% 
						//System.out.println("ur="+(String)request.getSession().getAttribute("userrole"));
						String ur=(String)request.getSession().getAttribute("userrole");//(String)request.getSession().getAttribute("userrole");
						//System.out.println("ur="+ur);
						//System.out.println("un="+request.getSession().getAttribute("username"));
						if (request.getSession().getAttribute("username") == null ) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
/* 						else if (request.getSession().getAttribute("username") != null && ur.equals("user")) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
 */						else
						{
						%>

<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script>
<form id="form1" name="form1" action="" method="post">
						


					<table>
							<font color="red">Already Existing date entry for current
								details (or) Other error..
							</font>
						
					</table>
 					</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>
					
					

<%} %>
</html>