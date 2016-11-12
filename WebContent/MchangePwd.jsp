<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Change Pwd</title>
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="JS/jquery.min.js"></script>
  <script type="text/javascript" src="JS/jquery-ui.min.js"></script>
    
  <!--<script type="text/javascript" src="JAVASCRIPT/jquery-1.4.2.min.js"></script>-->

<style>

a {
	text-decoration:none;
	color:blue;
}
a:hover {
	text-decoration:none;
	color:#999;
	//
}
.ui-autostatelete
        {
            position:absolute;
            cursor:default;
            z-index:4000 !important     
        }

</style>
<% //-------------------------------------------;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;----
if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
 {
    response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to login page.
	}

else //if (((HttpServletRequest) request).getSession().getAttribute("user") != null)
   { //System.out.println("hey the  user is:"+((HttpServletRequest) request).getSession().getAttribute("user"));
   	
%>
<script language="javascript">
function GetXmlHttpObject()
{
var xmlHttp=null;
try
 {
 // Firefox, Opera 8.0+, Safari
 xmlHttp=new XMLHttpRequest();
 }
catch (e)
 {
 //Internet Explorer
 try
  {
  xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
  }
 catch (e)
  {
  xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
 }
return xmlHttp;
}//GetXmlHttpObject();
function validateForm()
{
	//var patt2=new RegExp("[A-Za-z]");
	//var patt3=new RegExp("[A-Za-z0-9]");
	//var remarks=new RegExp("[\/:<>=]+");
	
   /* if((!patt2.test(document.form1.visit_narration.value))||(remarks.test(document.form1.visit_narration.value)))      
    {
      alert("Please provide some Narration");
      document.form1.visit_narration.focus();
      return false;
    }*/
	/*var huma_id=new RegExp("^[0-9]{4}$");
	var huma_id2=new RegExp("^[0]{4}$");
   if((!huma_id.test(document.form1.eno.value))||(huma_id2.test(document.form1.eno.value)))
    {
      alert("Please enter the EMP ID correctly");
      document.form1.eno.focus();
      return false;
    }*///eno
    /*if((!patt2.test(document.form1.eno.value))||(remarks.test(document.form1.eno.value)))//if(document.form1.eno.value=="")
    {
      alert("Please Enter the UserID");
      document.form1.eno.focus();
      return false;
    }*/
	if((document.form1.pwd1.value=="") || (document.form1.pwd2.value==""))
    {
		if((document.form1.pwd1.value=="") && (document.form1.pwd2.value==""))
		{
			alert("Enter the password fields"); 
			document.form1.pwd1.focus();
		}
	    else if((document.form1.pwd1.value=="") && (document.form1.pwd2.value!=""))
		{ 
		  document.form1.pwd1.focus(); 
		  alert("Enter the New password");
		 }
	    else if((document.form1.pwd1.value!="") && (document.form1.pwd2.value==""))
		{
		  document.form1.pwd2.focus();
		  alert("Conform the New password");
		}
		return false;
	  }
	/*if((!patt3.test(document.form1.pwd1.value))||(remarks.test(document.form1.pwd1.value)))//if(document.form1.eno.value=="")
    {
      alert("Please Enter the Password correctly");
      document.form1.pwd1.focus();
      return false;
    }
	if((!patt3.test(document.form1.pwd2.value))||(remarks.test(document.form1.pwd2.value)))//if(document.form1.eno.value=="")
    {
      alert("Please Enter the Conform password correctly");
      document.form1.pwd2.focus();
      return false;
    }*/
	
	var strongRegex2 = new RegExp("[A-Za-z0-9]{4}[A-Za-z0-9]");//new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	var strongRegex3 = new RegExp("[A-Za-z0-9]{4}[A-Za-z0-9]");//new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	//var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
	//var enoughRegex = new RegExp("(?=.{6,}).*", "g");
	//var pwd = document.getElementById(password);
	//if (strongRegex2.test(pwd.value)) {
		//strength.innerHTML = '<span style="color:green">Strong!</span>';
	if((!strongRegex2.test(document.form1.pwd1.value)))
    {       //alert("the pwd1.value="+document.form1.pwd1.value);
      alert("Please Enter password of  min 5 chars, in both fields");
	  document.form1.pwd1.focus();
	  return false;
    }
	if((!strongRegex3.test(document.form1.pwd2.value)))
    {       //alert("the pwd2.value="+document.form1.pwd2.value);
      alert("Please Enter password of  min 5 chars, in both fields");
	  document.form1.pwd2.focus();
	  return false;
    }
	
    if((document.form1.pwd1.value!="") && (document.form1.pwd2.value!=""))
	  {
		  if((document.form1.pwd1.value)!=(document.form1.pwd2.value))
		  {
			  document.form1.pwd1.focus();
			  alert("Passwords are not matching");
			  return false;
		   }
		  
	  }
	  /*if((document.form1.logtype.value==''))
	          { 
	     		alert("select the LogType");
		 		document.form1.logtype.focus();
	     		return false;
	  			} */
  
}//validateForm()
//hey the update&save butons(submission) code starts here---------------------------------------------------------
$(document).ready(function(){//alert("hey inside the ready functin of jquery update");
		$("#form1").submit(function(){sessioncheck();//alert("hey inside the submit function");
		  if(document.form1.onSubmit==validateForm()) 
			{ var allElements=$(this).serialize();//alert("hey the validation is done");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			//if(document.form1.save.style.display=='inline')
			  var program='schangePwdm';	
			//else  
			  // var program='ustatem'; //alert("hey the program is="+program);	
				$.ajax({ 
				   	url: program,
		          	data: allElements,
		          	type: 'post',
		          	dataType: "text",
		   			success: function(msg){//alert("hey inside the msg function & msg="+msg);
					msg = msg.replace(/^\s+/,'').replace(/\s+$/,''); //removes starting & ending spaces
					 if(msg.substr(0,2) == 'OK') // Message Sent, check and redirect
						{//alert("hey  in side ok msg.length="+msg.length);				// and direct to the success page
							alert(msg.substr(2,msg.length-2)); 
							disable();
						}
						else
						 alert(msg); //msg='OK';
			          }//msg function
				});//ajax funciton
			}, 0);//time out function
			return false;
         }//if(document.form1.onSubmit==validateForm()) 
         else { return false; }
 		});//submit function		
	});//ready function
//the save& update buttons submission code ends here------------------------------------------------------------
function disable()
{
 document.form1.pwd1.value='';
 document.form1.pwd2.value='';
}
</script>
<%
        
String e=(String)((HttpServletRequest) request).getSession().getAttribute("user");



%>
<script language="javascript" type="text/javascript">var ssssss="<%=(String)session.getAttribute("userType")%>";</script>
<!-- <script type="text/javascript" src="JS/Atop2.js"></script> -->

<!-- <body > -->
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	
</head>	
<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();">	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Mmenu.js"></script>
<form action="store.jsp" method="post"   NAME="form1"  id="form1" >
<p align="right"><!--<input type="button" value="E" onLoad" style=" padding-left:30 " -->
<table width="493" height="" align="center" bordercolor="" >
  <tr><td width="442" height=""><fieldset style="background-color:">
  <legend class="formTitle">Change Password </legend>
    <table width="100%" height="91%" align="center" border="0" bgcolor="">
  <tr bordercolor="#000000" style="visibility:hidden">
    <td  colspan="2"  align="right""><script type="text/javascript" src="JS/np.js"></script>    </td>
  </tr>
  <tr>
    <td width="42%" height="" align="right" class="style22"> User_id </td>
    <%-- <td width="58%" align="left"><input type="text" name="eno" id="eno" size="20" value="<%=e%>" maxlength="4" readonly="" /></td> --%>
    <td width="58%" align="left"><input type="text" name="eno" id="eno" size="20" value="<%=(String)session.getAttribute("username")%>" maxlength="20" readonly="" /></td>
  </tr>
  <tr>
    <td height=""><div align="right"><font class="style22 ">New Password</font></div></td>
    <td align="left"><script language="JavaScript" type="text/javascript"> 
function passwordChanged(password,strength) {
	var strength = document.getElementById(strength);
	var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
	var enoughRegex = new RegExp("(?=.{6,}).*", "g");
	var pwd = document.getElementById(password);
	if (pwd.value.length==0) {
		strength.innerHTML = 'Type Password';
	} else if (false == enoughRegex.test(pwd.value)) {
		strength.innerHTML = 'More Characters';
	} else if (strongRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:green">Strong!</span>';
	} else if (mediumRegex.test(pwd.value)) {
		strength.innerHTML = '<span style="color:orange">Medium!</span>';
	} else { 
		strength.innerHTML = '<span style="color:red">Weak!</span>';
	}
}
  </script>
        <input type="password" name="pwd1" id="pwd1" size="20"  maxlength="15" onchange="allowupdate();"  onkeyup="return passwordChanged('pwd1','strength1');"/><span id="strength1"></span> </td>
  </tr>
  <tr>
    <td height=""><div align="right"><font class="style22 ">Confirm New password </font></div></td>
    <td align="left"><input type="password" name="pwd2" id="pwd2" size="20" maxlength="15" onChange="allowupdate();" onkeyup="return passwordChanged('pwd2','strength2');"/><span id="strength2"></span> </td>
  </tr>
  <tr style="visibility:hidden">
    <td height=""><div align="right">

<font class="style22">Log type </font>

    </div></td>
    <td align="left"><select name="logtype" id="logtype" onChange="allowupdate();">
      <option value="" selected></option>
	  <option value="ADMIN">ADMIN</option>
      <option value="EMP">EMP</option>
    </select></td>
  </tr>
  <tr height="30">
    <td align="left"  colspan="2" height="40"><a href="#" id="pwdHint" name="pwdHint" onmouseover="showmenu('msg2');"
   onmouseout="hidemenu('msg2');">Strong pwd Hint:</a>
            <br />
            <table id="msg2" style="visibility:hidden; position:absolute;" align="" bgcolor="#999999">
			  <tr><td >1.Atleast 8 characters</td></tr>
			  <tr><td >2.Atleast 1 capital letter</td></tr>
			  <tr><td >3.Atleast 1 small letter</td></tr>
			  <tr><td >4.Atleast 1 special character</td></tr>
			  <tr><td >5.Atleast 1 numaric character</td></tr>
			 </table></td>
  </tr>
  <tr height="">
    <td align="center"  colspan="2" height=""><table align="center">
      <tr>
        <td ><input type="submit" id="save" name="save" class="groovybutton" value="Change" title="" onmouseover="goLite(this.form.name,this.name)"
   onmouseout="goDim(this.form.name,this.name)" onclick="return OnButton1();"/>
          &nbsp;&nbsp;&nbsp;
          <input type="reset" id="clear" name="clear" class="groovybutton" value="Clear" onclick="disable();"	 title="" onmouseover="goLite(this.form.name,this.name)"
   onmouseout="goDim(this.form.name,this.name)"/>
          &nbsp;&nbsp;&nbsp;
          <input type="button" id="cancel" name="cancel" class="groovybutton" value="Cancel" title="" onmouseover="goLite(this.form.name,this.name)"
   onmouseout="goDim(this.form.name,this.name)" onclick="window.location='Mhome.jsp'"/>
          &nbsp;&nbsp;&nbsp;<br />
          <table id="msg2" style="visibility:hidden; position:absolute;" align="">
            <tr>
              <td >Execute</td>
            </tr>
          </table></td>
      </tr>
    </table></td>
  </tr>
</table>
  </fieldset> </td></tr></table></form>
   <script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
   <!-- </td></tr>
<tr><td><br><br><br><br><BR><BR><br><br><br><br><br></td></tr>

          <script type="text/javascript" src="JS/down1.js"></script>

</body> -->
<%
	
    }//authorised acess else
%>
</html>
<!-- -->