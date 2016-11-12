<%@page import="frs_cls.JdbcConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="dbConn" scope="request" class="frs_cls.JdbcConnect"/> 
<%!
   Connection con=null;
   PreparedStatement ps=null,ps1=null,ps2=null;
   ResultSet rs=null,rs1=null,rs2=null;
   int f=0;   
   // String e=null;
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Region Day wise Detailed Rep</title>
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
<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>

<script type="text/javascript" language="javascript">
function validateForm()
{//alert("inside the validation and"); //onSubmit="validateForm()"

	   var hh=document.getElementsByName("oddays")[0].value;
		if(hh=="select ODDays")
		{
	      alert("Please select ODDays");
	      document.getElementsByName("oddays")[0].focus();
	      document.getElementsByName("oddays")[0].style.background="#fffacd";
	      return false;
	   }
//----------------------multiple selection validation
//----------------------------

 var chks = document.getElementsByName('huma_id');//alert("hety got the cheks again="+chks);
 var checkCount = 0;
 for (var i = 0; i < chks.length; i++)
 if (chks[i].checked)
  checkCount++;
 if (checkCount < 1)
 {
 alert("Please select at least one Region");
 return false;
 }

 }//validateForm()
 
function all2()//directly should not use all(),because the 'all' is the reserved keyword
 { //alert("hey start of the all method");
   var chks2 = document.getElementsByName('huma_id');//alert("hety got the cheks again="+chks2);
   // var checkCount2 = 0;
 for (var i = 0; i < chks2.length; i++)
 chks2[i].checked=true; //alert("hey end of the all method");
  }//all()
function none()
 { //alert("hey start of the none method");
   var chks3 = document.getElementsByName('huma_id');//alert("hety got the cheks again="+chks3);
    //var checkCount3 = 0;
 for (var i = 0; i < chks3.length; i++)
 chks3[i].checked=false;//alert("hey end of the none method");
  }//none()
/* 
function OnButton1()
{//alert("inside the onButton1");
 sessioncheck();
if(document.form1.onSubmit==validateForm()) 
 {
  document.form1.action = "reports_gen?decide="+document.form1.decide.value+"&format="+document.form1.format.value+"&key1= &key2= &fdate="+document.form1.fdate.value+"&tdate="+document.form1.tdate.value+"";
  //document.form1.action = "Defaulter report gen.jsp";  
  document.form1.target = "_blank"; // Open in a new window
  return true; 
  }//if 
else { return false; }
//alert("end of onButton1");
}//OnButton1();
*/
//function goLite2(BTN){BTN.style.color = "#ffffff";BTN.style.backgroundImage="url(images/back04.gif)";}function goDim2(BTN){BTN.style.color = "";BTN.style.backgroundImage="url(images/back03.gif)";}

</script><script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script>
<form id="form1" name="form1" action="SMStoODCustomer" 	method="post" onSubmit="return validateForm();" >
			<center>
 			 <table align="center">
				<tr>
								<td  align="right"><font color="blue"> <b>Telugu SMS Format is Under developing </b></font></td>
					</tr>
</table> 
</center>					 
</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>
<%
   }//authorised acess else	//------------------------------------------------------------------------------------
	%>
<!-- Calender Script  --> 

    <table background="images/calender3.gif" id="calenderTable">
        <tbody id="calenderTableHead">
          <tr>
            <td colspan="4" align="center">
	          <select onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
	           this.selectedIndex, false));" id="selectMonth">
	              <option value="0">Jan</option>
	              <option value="1">Feb</option>
	              <option value="2">Mar</option>
	              <option value="3">Apr</option>
	              <option value="4">May</option>
	              <option value="5">Jun</option>
	              <option value="6">Jul</option>
	              <option value="7">Aug</option>
	              <option value="8">Sep</option>
	              <option value="9">Oct</option>
	              <option value="10">Nov</option>
	              <option value="11">Dec</option>
	          </select>
            </td>	
            <td colspan="2" align="center">
			    <select onChange="showCalenderBody(createCalender(this.value, 
				document.getElementById('selectMonth').selectedIndex, false));" id="selectYear">
				</select>
			</td>
            <td align="center">
			    <a href="#" onClick="closeCalender();"><font color="#003333" size="+1">X</font></a>
			</td>
		  </tr>
       </tbody>
       <tbody id="calenderTableDays">
         <tr style="">
           <td>Sun</td><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td>
         </tr>
       </tbody>
       <tbody id="calender"></tbody>
    </table>

<!-- End Calender Script  -->

</html>
