<%@page import="java.util.Iterator"%>
<%@page import="frs_cls.loginuser"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify User</title>
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
<%
						HttpSession hs=request.getSession(false);
						
						String userrole="",user_freeze="";
						userrole=(String)hs.getAttribute("userrole");
						user_freeze=(String)hs.getAttribute("user_freeze");
						%>

<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
function assignValues() 
{//alert("inside assignValue() method to assign drop down fields values"); 
 document.form1.userrole.value="<%=userrole%>";
 document.form1.user_freeze.value="<%=user_freeze%>";
 //alert("End of assignValue() function");
}//assignValues()

</script>

<!-- Validation added by Rajesh -->
</script>
<script type="text/javascript">
function checkEmail() {
   var email=document.getElementsByName("emailid")[0].value;
   var i=0;
  var count=0;
  var count1=0;
 for(i=0;i<email.length;i++)
 {
   var sp=email.charAt(i);
   if(sp=="@")
    {
      var pos1=i;
      count=count+1;
    }
   }
 
 for(i=0;i<email.length;i++)
 {
   var sp=email.charAt(i);
   if(sp==".")
    {
      var pos2=i;
      count1=count1+1;
    }
   }
   if(count!=1){
    alert("Enter a valid email address");
    document.getElementsByName("emailid")[0].value="";
    document.getElementsByName("emailid")[0].focus();
    return false;
   }
   
   if(count1!=1){
    alert("Enter a valid email address");
    document.getElementsByName("emailid")[0].value="";
    document.getElementsByName("emailid")[0].focus();
    return false;
   }
   if(pos1>pos2){
    alert("Enter a valid email address");
    document.getElementsByName("emailid")[0].value="";
    document.getElementsByName("emailid")[0].focus();
    return false;
   }
  return true; 
 }//checkEmail()



function validateService(){
var pwd=document.getElementsByName("password")[0].value;
var cpwd=document.getElementsByName("Confirmpassword")[0].value;
//var res=pwd.compareTo(cpwd);
if(pwd==cpwd)
{
	return true;
}
else
{
alert("Password and confirm password should be same");
document.getElementsByName("Confirmpassword")[0].value="";
document.getElementsByName("Confirmpassword")[0].focus();
document.getElementsByName("Confirmpassword")[0].style.background="#fffacd";
return false;
}
 }//validateService()
 
 function chkMobileNo(mobileno)
{
 
 var flag=0;
 var i=0;
 for(i=0;i<mobileno.length;i++)
 {
   var sp=mobileno.charAt(i);
   if(sp==" ")
    {
      alert("Mobile Number Space Not Allowed");
      document.getElementsByName("mobileno")[0].value="";
      document.getElementsByName("mobileno")[0].focus();
      return false;
    }
                
     }
        		
     if(mobileno.length<10)
     {
       alert("Mobile Number Should be of 10 or 12 digits");
       document.getElementsByName("mobileno")[0].value="";
       document.getElementsByName("mobileno")[0].focus();
     
       return false;
     }
     if(mobileno.length>12)
     {
       alert("Mobile Number Should be of 10 or 12 digits");
       document.getElementsByName("mobileno")[0].value="";
       document.getElementsByName("mobileno")[0].focus();
     
       return false;
     }
        		
     if(mobileno=="0000000000" || mobileno=="000000000000")
     {
     alert("Invalid Mobile Number");
     document.getElementsByName("mobileno")[0].value="";
     document.getElementsByName("mobileno")[0].focus();
     
     return false;
     }
     
     return true;
} //chkContactNo()

 function chkPcodeeNo(pcode)
{
 
 var flag=0;
 var i=0;
 for(i=0;i<pcode.length;i++)
 {
   var sp=pcode.charAt(i);
   if(sp==" ")
    {
      alert("In Postal Code Space Not Allowed");
      document.getElementsByName("pcode")[0].value="";
      document.getElementsByName("pcode")[0].focus();
      return false
    }
                
     }//for
        		
     if(pcode.length<6)
     {
       alert("Postal Code Should be of 6 digits");
       document.getElementsByName("pcode")[0].value="";
       document.getElementsByName("pcode")[0].focus();
     
       return false;
     }
     if(pcode.length>6)
     {
       alert("Postal Code Should be of 6 digits");
       document.getElementsByName("pcode")[0].value="";
       document.getElementsByName("pcode")[0].focus();
     
       return false;
     }
       		
     if(pcode=="000000" || pcode=="000000")
     {
     alert("Invalid Postal Code");
     document.getElementsByName("pcode")[0].value="";
     document.getElementsByName("pcode")[0].focus();
     
     return false;
     }
     
     return true;
} //chkContactNo()

function validate(){
if(document.getElementsByName("name")[0].value=="")
	{
      alert("Please enter the Name");
      document.getElementsByName("name")[0].focus();
      document.getElementsByName("name")[0].style.background="#fffacd";
      return false;
   }
  

   
if(document.getElementsByName("username")[0].value=="")
{
  alert("Please enter the username");
  document.getElementsByName("username")[0].focus();

  document.getElementsByName("username")[0].style.background="#fffacd";
  return false;
}
if(document.getElementsByName("userrole")[0].value=="0")
{
alert("Please select the user role");
document.getElementsByName("userrole")[0].focus();
document.getElementsByName("userrole")[0].style.background="#fffacd";
return false;
}
if(document.getElementsByName("password")[0].value=="")
{
  alert("Please enter the password");
  document.getElementsByName("password")[0].focus();
  document.getElementsByName("password")[0].style.background="#fffacd";
  return false;
}
if(document.getElementsByName("Confirmpassword")[0].value=="")
{
  alert("Please enter the Confirmpassword");
  document.getElementsByName("Confirmpassword")[0].focus();
  document.getElementsByName("Confirmpassword")[0].style.background="#fffacd";
  return false;
}
//Passwords codes compatision code starts here
var pwd=document.getElementsByName("password")[0].value;
var cpwd=document.getElementsByName("Confirmpassword")[0].value;
//var res=pwd.compareTo(cpwd);
/* if(pwd==cpwd)
{
	return true;
}
 */if(pwd!=cpwd)//else
{
alert("Password and confirm password should be same");
document.getElementsByName("Confirmpassword")[0].value="";
document.getElementsByName("Confirmpassword")[0].focus();
document.getElementsByName("Confirmpassword")[0].style.background="#fffacd";
return false;
}
//Passwords comparision code ends here
/*if(!validateService())
{
return false;
}
return true; */
}//validate()
</script>




<!-- Validation logic completed -->
</head><body onLoad="assignValues();" bottommargin="100%" marginheight="100%"
	rightmargin="100%" leftmargin="100%" marginwidth="100%"
	topmargin="100%">
<form id="form1" name="form1" action="updateuser" method="post">
						<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="280"></td>
								<td width="380"><b><a href="AdminHome.jsp">ADMIN
											HOME</a></b></td>
							</tr>
						</table>

						<!-- <center> -->
						<%
							//HttpSession hs=request.getSession(false);
							%>

						<table>
							<tr>
								<td width="380" bgcolor="white" align="right"><font
									color="block">Contact Details</font></td>
								<!--     								<td width="380" bgcolor="grey" align="left"></td>
  									<td width="380" bgcolor="grey" align="left"></td>
    								<td width="380" bgcolor="grey" align="left"></td>
 -->
							</tr>

							<tr>
								<td width="380"></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>


							<tr>
								<td align="right">Name<font color="#FF0000">*</font></td>
								<td><input type="text" maxlength="20" name="name"
									value="<%=hs.getAttribute("name")%>" /></td>
								<td align="right">Email_Id</td>
								<td><input type="text" maxlength="30" name="emailid"
									value="<%=hs.getAttribute("emailid")%>" /></td>
							</tr>

							<tr>
								<td align="right">Mobile No</td>
								<td><input type="text" maxlength="13" name="mobileno"
									value="<%=hs.getAttribute("mno")%>" /></td>
								<td align="right">Land No</td>
								<td><input type="text" maxlength="13" name="landno"
									value="<%=hs.getAttribute("lno")%>" /></td>
							</tr>
							<tr>
								<td width="380" bgcolor="white" align="right"><font
									color="block">Address</font></td>
								<!--     								<td width="380" bgcolor="grey" align="left"></td>
  									<td width="380" bgcolor="grey" align="left"></td>
    								<td width="380" bgcolor="grey" align="left"></td>
 -->
							</tr>

							<tr>
								<td width="380"></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>

							<tr>
								<td align="right">House No</td>
								<td><input type="text" maxlength="15" name="houseno"
									value="<%=hs.getAttribute("hno")%>" /></td>
								<td align="right">Locality</td>
								<td><input type="text" maxlength="15" name="locality"
									value="<%=hs.getAttribute("locality")%>" /></td>
							</tr>

							<tr>
								<td align="right">City</td>
								<td><input type="text" maxlength="15" name="city"
									value="<%=hs.getAttribute("city")%>" /></td>
								<td align="right">District</td>
								<td><input type="text" maxlength="15" name="district"
									value="<%=hs.getAttribute("dist")%>" /></td>
							</tr>

							<tr>
								<td align="right">State</td>
								<td><input type="text" maxlength="15" name="state"
									value="<%=hs.getAttribute("state")%>" /></td>
								<td align="right">Country</td>
								<td><input type="text" maxlength="15" name="country"
									value="<%=hs.getAttribute("country")%>" /></td>
							</tr>

							<tr>
								<td align="right">Postal Code</td>
								<td><input type="text" maxlength="6" name="pcode"
									value="<%=hs.getAttribute("pc")%>" /></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td width="380" bgcolor="white" align="right"><font
									color="block">Login Details </font></td>
								<!--     								<td width="380" bgcolor="grey" align="left"></td>
  									<td width="380" bgcolor="grey" align="left"></td>
    								<td width="380" bgcolor="grey" align="left"></td>
 -->
							</tr>
							<tr>
								<td width="380"></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>

							<tr>
								<td align="right">User Name<font color="#FF0000">*</font></td>
								<td><input type="text" maxlength="20" name="username"
									value="<%=hs.getAttribute("un")%>" /></td>
								<td align="right">User type<font color="#FF0000">*</font></td>
								<td><select id="userrole" name="userrole">
										<option value="0">--select user role--</option>
										<option value="admin">admin</option>
										<option value="user">user</option>
								</select></td>

							</tr>
							<tr>
								<td align="right">Password<font color="#FF0000">*</font></td>
								<td><input type="text" maxlength="20" name="password" /></td>
								<td align="right">Confirm Password<font color="#FF0000">*</font></td>
								<td><input type="text" maxlength="20"
									name="Confirmpassword" /></td>
							</tr>
							<tr>
								<td align="right">Freeze User</td>
								<td><select id="user_freeze" name="user_freeze">
										<option value="N" selected="selected">No</option>
										<option value="Y">Yes</option>
								</select></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" name="submit" value="Modify User"
									onclick="return validate();" /></td>
							</tr>


						</table>


						<!-- </center> -->
	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
</body>
<%} %>
</html>