<%@page import="frs_cls.JdbcConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select user forom DB</title>
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

<script type="text/javascript" language="javascript">

function validatepage()
{
	//alert("inside validation method");
	var shownameshowname;
	   var check=0;
	   var chx = document.getElementsByName("dwr");
	   //alert(chx.length);

	   for (var i=0; i<chx.length; i++) {
			if (chx[i].type == 'radio' && chx[i].checked) {
		       
				check=1;
				//showname=chx[i][0].value;
				//showname=document.getElementsByName(chx[i]).value();
				//alert(chx[i].value);
			}
			
		}

		if(check==1)
			{
			return true;
			}else
			{
				alert("Select any type");
				return false;
			} 

//}//check()
if(dwr.equals("Unit"))
	{
	alert("Unit selected");
	
	}
else
	{
	alert("LSR selected");
	}
if(document.getElementsByName("username")[0].value=="")
{
  alert("Please Select the UserName");
  document.getElementsByName("username")[0].focus();
  document.getElementsByName("username")[0].style.background="#fffacd";
  return false;
}
}
  function raj()
  {
	  
			/* document.getElementById("BSFLUNIT_NAME").disabled=false;
			document.getElementById("huma_id").disabled=false;
			document.getElementById("area_name").disabled=false;
			
			document.getElementsByName("BSFLUNIT_NAME")[0].value = "";
			document.getElementsByName("huma_id")[0].value = "";
			document.getElementsByName("area_name")[0].value = "";
			
			document.getElementById("BSFLUNIT_NAME").disabled=true;
			document.getElementById("huma_id").disabled=true; */
			//document.getElementById("unit").disabled=true;
			 
			alert("r1() function called"); 
			document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
			 document.getElementById("area_name2").style.visibility = "visible";
			 document.getElementById("empcode").style.visibility = "hidden";
		}
  
function show()
{
	/* document.getElementById("BSFLUNIT_NAME").disabled=false;
	document.getElementById("huma_id").disabled=false;
	document.getElementById("area_name").disabled=false;
	
	document.getElementsByName("BSFLUNIT_NAME")[0].value = "";
	document.getElementsByName("huma_id")[0].value = "";
	document.getElementsByName("area_name")[0].value = "";
	
	document.getElementById("area_name").disabled=true;
	document.getElementById("BSFLUNIT_NAME").disabled=true;
	//For hiding the TextBoxes
	 //document.getElementById('area_name').style.visibility='hidden';
	document.getElementById('BSFLUNIT_NAME').style.visibility='hidden';
	document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
	document.getElementById("area_name2").style.visibility = "hidden";
	 */ 
	 //alert("show() function called");
	 
	 
	 /*
	 document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
	 document.getElementById("area_name2").style.visibility = "visible";
	 document.getElementById("empcode").style.visibility = "hidden";
	*/
}
function r2()
{
	/* document.getElementById("BSFLUNIT_NAME").disabled=false;
	document.getElementById("huma_id").disabled=false;
	document.getElementById("area_name").disabled=false;
	
	document.getElementsByName("BSFLUNIT_NAME")[0].value = "";
	document.getElementsByName("huma_id")[0].value = "";
	document.getElementsByName("area_name")[0].value = "";
	
	document.getElementById("area_name").disabled=true;
	document.getElementById("BSFLUNIT_NAME").disabled=true;
	//For hiding the TextBoxes
	 //document.getElementById('area_name').style.visibility='hidden';
	document.getElementById('BSFLUNIT_NAME').style.visibility='hidden';
	document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
	document.getElementById("area_name2").style.visibility = "hidden";
	 */ 
	 //alert("r2() function called");
	 document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
	 document.getElementById("area_name2").style.visibility = "hidden";
	 document.getElementById("empcode").style.visibility = "visible";
	
}

function r3()
{
	/* document.getElementById("BSFLUNIT_NAME").disabled=false;
	document.getElementById("huma_id").disabled=false;
	document.getElementById("area_name").disabled=false;
	
	document.getElementsByName("BSFLUNIT_NAME")[0].value = "";
	document.getElementsByName("huma_id")[0].value = "";
	document.getElementsByName("area_name")[0].value = "";
	
	document.getElementById("area_name").disabled=true;
	document.getElementById("huma_id").disabled=true;
	//document.getElementById("unit").disabled=true;
	document.getElementById('area_name2').style.visibility='visible';
	 */
	 
	 //alert("r3() function called");
	 document.getElementById("BSFLUNIT_NAME2").style.visibility = "visible";
	 document.getElementById("area_name2").style.visibility = "hidden";
	 document.getElementById("empcode").style.visibility = "hidden";
	 
}

 function papu()
 {
	 document.getElementById("BSFLUNIT_NAME2").style.visibility = "hidden";
	 document.getElementById("area_name2").style.visibility = "hidden";
	 document.getElementById("empcode").style.visibility = "hidden";  //lsrcode
	 //document.getElementById("huma_id").style.visibility = "hidden";
 }
</script>


</script><script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%"
	leftmargin="100%" marginwidth="100%" topmargin="100%"
	onload="return papu();">
<form id="form1" name="form1" action="userlistUnit"
						onsubmit="return validatepage();">
						<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="380"></td>
								<td width="380" align="left"><b><a
										href="AdminHomeUnit.jsp">UNIT HOME</a></b></td>
							</tr>
						</table>
						<center>
							<table>
								<tr>
									<!-- <td align="right">Username</td> -->
									<td></td>
								<tr>


									<!-- Added By Rajesh -->
								<tr>
								</tr>
								<tr>
								</tr>
								<tr>
								</tr>
								<tr>
								</tr>
								<tr>
								</tr>
								<tr>
									<td></td>

									<!-- <td><input type="radio" name="dwr" id="area" value="Area" onclick="return show();"/>Region</td> -->
									<td><input type="radio" name="dwr" id="unit" value="Unit"
										onclick="return r3();" />Unit</td>
									<td><input type="radio" name="dwr" id="lsr" value="lsr"
										onclick="return r2();" />LSR</td>

								</tr>

								<tr>
									<td></td>

									<!-- Code inserted by Rajesh -->
									<td align="left"><label id="BSFLUNIT_NAME2">Unit name
											<input type="text" name="BSFLUNIT_NAME" id="BSFLUNIT_NAME"
											align="right"
											value=<%=request.getSession().getAttribute("username")%>
											readOnly="readonly""border-color:#0B4599;">
									</label></td>

									<td align="right"><label id="empcode">Employee
											Code <input type="text" name="huma_id" id="huma_id" align="right"
											onfocus="addSuggestionUnit('empname-username','username');"
											style="border-color: #0B4599;">
									</label></td>

									<td align="right"><label id="area_name2">Region Name
											<input type="text" name="area_name" id="area_name"
											align="right" onfocus="return suggesttonArea();"
											style="border-color: #0B4599;">
									</label></td>

									<td></td>
								</tr>






								<!-- <td align="right">Enter UserName</td>
<td align="left"><input type="text" name="username" id="username" onFocus="addSuggestionUnit('empname-username','username');" class="masterInput"/>  </td>
</tr> -->

								<td>
									<!--<select name="username" >
							<option value="--Select --">--Select User Name --</option>
								<%
									/* JdbcConnect jc=new JdbcConnect();
									Connection con = jc.getConnection();
									Statement st=con.createStatement();
									//ResultSet rs=st.executeQuery("select distinct username from frs_user");
									ResultSet rs=st.executeQuery("select distinct username from FRS_USER f,huma_mstr l where L.BSFLUNIT_NAME='Khurda' and L.huma_id=F.USERNAME");
									while(rs.next()){
    								String str=rs.getString(1); */
								%>
    							<%-- <option value="<%=str%>"><%=str%></option> --%>
    							<%
								/* }
								con.close(); */
								%>
						</select></td>
 --%> 
 <!-- 						<td>
 						<input type="text" name="username">
 						
 						</td>					
 -->
								</tr>
								<tr>
									<td></td>
									<td align="right"><input type="submit" value="Get User" />
										<!-- onClick="return validate();"/> --></td>
								</tr>
							</table>
						</center>
						</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
<%} %>
</html>