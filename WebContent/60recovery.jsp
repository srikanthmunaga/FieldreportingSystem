<%@page import="frs_cls.JdbcConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="dbConn" scope="request" class="frs_cls.JdbcConnect"/> 
<%!
   Connection con=null;
   PreparedStatement ps=null,ps1=null,ps2=null;
   ResultSet rs=null,rs1=null,rs2=null;
   int f=0;
    String e=null;
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>60:60 Recovery</title>
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

<!-- Script added By rajesh -->
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="JS/jquery.min.js"></script>
  <script type="text/javascript" src="JS/jquery-ui.min.js"></script>
  <!--<script type="text/javascript" src="JS/jquery-1.2.6.js"></script>-->
  <script type="text/javascript" src="JS/jquery.formatCurrency.js"></script>
  <script type="text/JavaScript" src="JS/calendar.js"></script>
  <link rel="stylesheet" href="styles/calendar.css" type="text/css"/>
  <link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>
<!-- <script type="text/javascript"></script> -->
<script type="text/javascript" src="JS/jquery.table.addrow.js"></script> 
  


<script type="text/javascript" language="javascript">
function validateForm()
{//alert("inside the validation and"); //onSubmit="validateForm()"

	if(document.getElementsByName("unit_id")[0].value=="")
	{
      alert("Please Select UnitID ");
      document.getElementsByName("unit_id")[0].focus();;
      document.getElementsByName("unit_id")[0].style.background="#fffacd";
      return false;
    }
	var numberAmt=new RegExp("[0-9]");
	if(!numberAmt.test(document.getElementsByName("totrecovery")[0].value))
	{
      alert("Please Enter Recovery Collected, Correctly");
      document.getElementsByName("totrecovery")[0].focus();;
      document.getElementsByName("totrecovery")[0].style.background="#fffacd";
      return false;
   }
   var hh=document.getElementsByName("hh")[0].value;
	if(hh=="HH")
	{
      alert("Please select Hours");
      document.getElementsByName("hh")[0].focus();
      document.getElementsByName("hh")[0].style.background="#fffacd";
      return false;
   }
   var mm=document.getElementsByName("mm")[0].value;
	if(mm=="MM")
	{
      alert("Please select Minutes");
      document.getElementsByName("mm")[0].focus();
      document.getElementsByName("mm")[0].style.background="#fffacd";
      return false;
   }

	//return false;
 }
</script>
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Cmenu.js"></script>
<form action="Recovery" method="post" onSubmit="return validateForm();">
<center>
 <table>
 <tr>
 <td  align="right"><font color="Blue"><b>Unit ID</b></font></td>
 <td><input align="left" type="text" name="unit_id" size="12" onfocus="addSuggestion('BSFLUNIT_UCODE_roles','unit_id');"></td>
 </tr>
 <tr>
 
 <td  align="right"><font color="Blue"><b>Recovery collected</b></font></td>
 <td><input align="left" type="text" name="totrecovery" size="12" ></td><!-- </tr>
 <tr> -->
 <td><font color="Blue"><b>Submission Time</b></font></td>
 <td><select name="hh" id="hh">
 	<option value="HH">HH</option>
 	<option>09</option>
 	<option>10</option>
 	<option>11</option>
 	<option>12</option>
 	<option>01</option>
 	<option>02</option>
 	<option>03</option>
 	<option>04</option>
 	<option>05</option>
 	<option>06</option>
 	<option>07</option>
 	<option>08</option>
		
 	</select>
<select name="mm" id="mm">
<option value="MM">MM</option>
<option>00</option>
<option>01</option>
<option>02</option>
<option>03</option>
<option>04</option>
<option>05</option>
<option>06</option>
<option>07</option>
<option>08</option>
<option>09</option>
<option>10</option>
<option>11</option>
<option>12</option>
<option>13</option>
<option>14</option>
<option>15</option>
<option>16</option>
<option>17</option>
<option>18</option>
<option>19</option>
<option>20</option>
<option>21</option>
<option>22</option>
<option>23</option>
<option>24</option>
<option>25</option>
<option>26</option>
<option>27</option>
<option>28</option>
<option>29</option>
<option>30</option>
<option>31</option>
<option>32</option>
<option>33</option>
<option>34</option>
<option>35</option>
<option>36</option>
<option>37</option>
<option>38</option>
<option>39</option>
<option>40</option>
<option>41</option>
<option>42</option>
<option>43</option>
<option>44</option>
<option>45</option>
<option>46</option>
<option>47</option>
<option>48</option>
<option>49</option>
<option>50</option>
<option>51</option>
<option>52</option>
<option>53</option>
<option>54</option>
<option>55</option>
<option>56</option>
<option>57</option>
<option>58</option>
<option>59</option>

 	
 	</select>
 	 <font color="Blue">HH-MM</font></td><tr>
 	 <td></td>
 <td><input type="submit" name="submit"	value="Submit Details"/>
 </td></tr>
 </table></center>
 </form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>
<%
   }//authorised acess else	//------------------------------------------------------------------------------------
	%>

</html>
