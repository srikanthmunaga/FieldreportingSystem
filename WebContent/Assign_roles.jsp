<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Bussiness line master</title>

<% 
String ur=(String)request.getSession().getAttribute("userrole");
//System.out.println("Hai inside the Assign_role jsp and role="+ur);
//if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
	if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
	//	if (request.getSession().getAttribute("username") == null) {
 { 
   response.sendRedirect("frslogin.jsp");  // Not logged in, redirect to login page.
	}

//if (((HttpServletRequest) request).getSession().getAttribute("user") != null)
if (ur!= null)
   {
   // chain.doFilter(request, response); // Logged in, so just continue.
%>
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>

<!--<script type="text/javascript" src="JAVASCRIPT/jquery-1.4.2.min.js"></script>-->


<script type="text/javascript" language="javascript">
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
}//GetXmlHttpObject()

function validateForm()
{
    //alert("Inside the validateform");
    var patt10=new RegExp("[A-Za-z0-9]");
	if(!patt10.test(document.getElementById("huma_id").value))   
	{
	  alert("Please enter the Emp Id");
	  document.getElementById("huma_id").focus();
	  document.getElementById("huma_id").style.background="#fffacd";
	  return false;
	}
	var mobile=/^[+]?[0-9]{2}(-|\s)?[0-9]{8,10}$/;
	var patt2=new RegExp("^[A-Za-z]+[0-9]*[ A-Za-z0-9]*$");
	if(!mobile.test(document.getElementById("mobileno").value))
    {
    alert("Please enter valid Mobile no");
    document.form1.mobileno.focus();
	  return false;
    }
	if(!patt2.test(document.form1.fname.value))
    {
      alert("Please enter the Name");
      document.form1.fname.focus();
      return false;
    }
	/* if(!patt2.test(document.form1.lname.value))
    {
      alert("Please enter the Last name");
      document.form1.lname.focus();
      return false;
    } */
	if(!patt2.test(document.form1.username.value))
    {
      alert("Please enter the Username");
      document.form1.username.focus();
      return false;
    }
    /* if(!patt2.test(document.form1.role.value==""))
    {
      alert("Please select the role");
      document.form1.role.focus();
      return false;
    } */
    if(document.getElementById("role").value=='')
    {
       alert("Please select the Role");
	   document.form1.role.focus();
	   return false;
	}
	if(!patt2.test(document.form1.password.value))
    {
      alert("Please enter the Password");
      document.form1.password.focus();
      return false;
    }
	if(!patt2.test(document.form1.Confirmpassword.value))
    {
      alert("Please enter the confirm Password");
      document.form1.Confirmpassword.focus();
      return false;
    }
	var pwd=document.getElementById("password").value;
	var cpwd=document.getElementById("Confirmpassword").value;
	 if(pwd!=cpwd)
	{
	alert("Password and confirm password should be same");
	document.getElementById("Confirmpassword").value="";
	document.getElementById("Confirmpassword").focus();
	document.getElementById("Confirmpassword").style.background="#fffacd";
	return false;
	}
}//validateForm()

function focuses() { document.form1.busi_name.focus(); }
function EnforceMaximumLength(fld,len)
{
 if(fld.value.length > len) { fld.value = fld.value.substr(0,len); document.form1.save.focus(); }
}

//hey the update&save butons(submission) code starts here---------------------------------------------------------
$(document).ready(function(){//alert("hey inside the ready functin of jquery update");
		$("#form1").submit(function(){sessioncheck();//alert("hey inside the submit function");
		  if(document.form1.onSubmit==validateForm()) 
			{ var allElements=$(this).serialize();//alert("hey the validation is done");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			if(document.form1.save.style.display=='inline')
			  var program='sassignrole';	
			else  
			   var program='uassignrole'; //alert("hey the program is="+program);	
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
var star;//i;//global variable
var x=0;
var patt=new RegExp("[A-Za-z0-9]");
//Added by Rajesh
function showDetails(decide,key1,key2)
{
if(!(document.form1.exct.disabled))
	return;//stop executing this function in case of query functioning
if(patt.test(document.getElementById("huma_id").value))
 {
 
sessioncheck();
xmlHttp=GetXmlHttpObject();

 if (xmlHttp==null)
  {
  return;
  }
 var url="getuserupdate";
  url=url+"?decide="+decide+"&key1="+key1+"&key2="+key2;
//alert("hey url is made");// xmlHttp.onreadystatechange=stateChangedn2;
 xmlHttp.open("GET",url,false);
 xmlHttp.send(null);
 x=0; //alert("hey inside the stateChangedn");
var showdata = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
	showdata=showdata.replace(/\/\/\/\/\/\/$/,"");//removes the "//////"  from the end of the "data" array
	star=showdata.split("//////");//alert("hey the star="+star);
	if(star=='')
	 { 
		 alert("No records found");
		 document.form1.previous.disabled=true;
         document.form1.next.disabled=true;
		 document.getElementById("huma_id").value ='';  
		 document.getElementById("huma_id").focus();
	 }
	 else if(star.length>=0)
	{ 
	   for(var k=0; k<star.length; k++)
	  star[k] = star[k].split("::::::"); 
	 //nextprevious("first");
	 Display();
	}     //esle
 }
 }//stateChangedn() 

function Display()
{ 
  enable();//from here to 3 lines of code is newly added to automate enable all the fields
  document.form1.save.style.display='inline';//newly added code   
  document.form1.update.style.display='none';//newly added code
  goDim("form1","update");	
  document.form1.update.disabled=true;//alert("star[x]="+star[x]);
  
 //document.getElementById("huma_id").value =star[x][0];
 document.getElementById("fname").value =star[x][0];
 //document.getElementById("fname").readonly=true;
 //document.getElementById("fname").setattribute("readonly","readonly");
 document.getElementById("mobileno").value =star[x][1];
// document.getElementById("mobileno").readonly="readonly";
 document.getElementById("username").value =star[x][0];
 //document.getElementById("username").readonly="readonly";
 //document.getElementById("role").value =star[x][4];
 //document.getElementById("huma_id").value =star[x][5];
}

function showEmpn(decide,key1,key2)
{
 //alert("Inside the showEmpn");
	sessioncheck();
xmlHttp=GetXmlHttpObject();

 if (xmlHttp==null)
  {
  return;
  }
 var url="getuserupdate";
  url=url+"?decide="+decide+"&key1="+key1+"&key2="+key2;
//alert("hey url is made");// xmlHttp.onreadystatechange=stateChangedn2;
 xmlHttp.open("GET",url,false);
 xmlHttp.send(null);
 x=0; //alert("hey inside the stateChangedn");
var showdata = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
	showdata=showdata.replace(/\/\/\/\/\/\/$/,"");//removes the "//////"  from the end of the "data" array
	star=showdata.split("//////");//alert("hey the star="+star);
	if(star=='')
	 { 
		 alert("No records found");
		 document.form1.previous.disabled=true;
         document.form1.next.disabled=true;
		 document.getElementById("huma_id").value ='';
	 }
	 else if(star.length>=0)
	{ 
	   for(var k=0; k<star.length; k++)
	  star[k] = star[k].split("::::::"); 
	 nextprevious("first"); 
	}     //esle
}//stateChangedn() 


function nextprevious(np)
{
 if(np=="first") 
 { 
  x=0; //here x=0;alert("hey you are in the nextprevious()");
  document.form1.first.disabled=false;
  document.form1.previous.disabled=false;
  document.form1.next.disabled=false;
  document.form1.last.disabled=false;
  show();
  }	
 else if(np=="previous")
  { 
   x=x-1; 
   document.form1.next.disabled=false;
   document.form1.last.disabled=false;
   show();
   }	
 else if(np=="next")
  {
   x=x+1;
   document.form1.previous.disabled=false;
   document.form1.first.disabled=false;
   show();
   }	
 else if(np=="last")
  {
   x=star.length-1;
   document.form1.previous.disabled=false;
   document.form1.first.disabled=false;
   show();
   }	
 if(x<=0)
   { goDim("form1","previous"); document.form1.previous.disabled=true;     
     goDim("form1","first"); document.form1.first.disabled=true;     
    }
 if(x>=star.length-1)
   { goDim("form1","next"); document.form1.next.disabled=true;  
     goDim("form1","last"); document.form1.last.disabled=true;  
   }
}//nextprevious(np)

function show()
{ 
  enable();//from here to 3 lines of code is newly added to automate enable all the fields
  document.form1.save.style.display='none';//newly added code
  document.form1.update.style.display='inline';//newly added code
  goDim("form1","update");	
  document.form1.update.disabled=true;//alert("star[x]="+star[x]);
  
 document.getElementById("huma_id").value =star[x][0];
 document.getElementById("fname").value =star[x][1];
 document.getElementById("mobileno").value =star[x][2];
 document.getElementById("username").value =star[x][3];
 document.getElementById("role").value =star[x][4];
 document.getElementById("password").value =star[x][5];
 document.getElementById("Confirmpassword").value =star[x][5];
 //document.getElementById("huma_id").value =star[x][5];
}//show()
</script>
<script type="text/javascript" language="javascript">
function allowupdate()
{
 if((document.form1.update.style.display=="inline") && (document.form1.exct.disabled))
    { 
	document.form1.update.disabled=false;
	  //document.form1.update.focus();
	 }
}//allowupdate()
//showList('busi_id');
</script>

<script language="javascript" type="text/javascript">var ssssss="<%=(String)session.getAttribute("userType")%>";</script>
<!-- <script type="text/javascript" src="JS/Atop2.js"></script> -->

<script type="text/javascript">
function queryfunction() 
{ 
//Everything from disable() exceptiong button code,just for primary key enable(replace true by false) and editable(add readOnly=false)
 //document.getElementById("comp_id").style.display='none';
 //document.getElementById("busi_id").style.display='none';
 //document.getElementById("busi_type").style.display='none';
 //document.getElementById("comp_name").style.display='none';
  //document.getElementById("comp_id1").style.display='inline';
 //document.getElementById("comp_name2").style.display='inline';
 //document.getElementById("busi_id2").style.display='inline';
 //document.getElementById("busi_type1").style.display='inline';
 //document.form1.busi_name.value='';
 //document.form1.busi_remarks.value='';
 var f = document.getElementById("form1");
 var inputs = f.getElementsByTagName("input");
 for(var i = 0; i < inputs.length; i++)
 { 
   if(inputs[i].type=="text")
   {
    if(inputs[i].style.display!="none")
    inputs[i].value='';
    inputs[i].disabled=true;
   }
  }
 //document.form1.busi_remarks.value='';
 //document.form1.busi_remarks.disabled=true;
 document.form1.role.value='';
 document.form1.role.disabled=true;
 
 document.form1.password.value='';
 document.form1.password.disabled=true;
 
 document.form1.Confirmpassword.value='';
 document.form1.Confirmpassword.disabled=true;
 
 //document.form1.huma_id.disabled=false;//3 lines, newly added code for the Query functionality
 //document.form1.huma_id.readOnly=false;
 document.form1.huma_id.disabled=true;//3 lines, newly added code for the Query functionality
 document.form1.huma_id.readOnly=true;
 document.form1.username.disabled=false;
 document.form1.username.style.backgroundColor='skyblue';
 document.form1.username.focus();
 //alert("hey ging to call the functions");
 //showList('busi_id2'); 
// alert("hey the methods are called");
//--------------------------------------Common (buttons) code
document.form1.save.style.display='none';
 document.form1.update.style.display='inline';
 goDim("form1","update");
 document.form1.update.disabled=true;
 document.form1.exct.disabled=false;
  document.form1.first.disabled=true;
 document.form1.previous.disabled=true;
 document.form1.next.disabled=true;
 document.form1.last.disabled=true;
 document.form1.new2.disabled=true;
 goDim("form1","query");
 document.form1.query.disabled=true;
 document.form1.clear.disabled=false;
}//queryfunction();
function disable() 
{
 //document.getElementById("comp_id").style.display='none';
 //document.getElementById("busi_id").style.display='none';
 //document.getElementById("busi_type").style.display='none';
 //document.getElementById("comp_name").style.display='none';
  //document.getElementById("comp_id1").style.display='inline';
 //document.getElementById("comp_name2").style.display='inline';
// document.getElementById("busi_id2").style.display='inline';
 //document.getElementById("busi_type1").style.display='inline';
 //document.form1.busi_type1.value='';//new added line
 //document.form1.busi_remarks.value='';
 var f = document.getElementById("form1");
 var inputs = f.getElementsByTagName("input");
 for(var i = 0; i < inputs.length; i++)
 { 
   if(inputs[i].type=="text")
   {
   if(inputs[i].style.display!="none")//new added one
   inputs[i].value='';//new added one
   inputs[i].disabled=true;
   }
  }
  //document.form1.busi_type1.value='';
  //document.form1.busi_type1.disabled=true;	
 //document.form1.busi_remarks.value='';
 //document.form1.busi_remarks.disabled=true;
 document.form1.password.value='';
 document.form1.password.disabled=true;

 document.form1.Confirmpassword.value='';
 document.form1.Confirmpassword.disabled=true;

 document.form1.role.value='';
 document.form1.role.disabled=true;

 //---------common (buttons) code
document.form1.save.disabled=true;
  document.form1.first.disabled=true;
 document.form1.previous.disabled=true;
 document.form1.next.disabled=true;
 document.form1.last.disabled=true;
 document.form1.new2.disabled=false;
 document.form1.query.disabled=false
 goDim("form1","clear");
 document.form1.clear.disabled=true;
 document.form1.exct.disabled=true;//from this line new added code for Query functionality
 document.form1.save.style.display='inline';
 document.form1.update.style.display='none';
}//disable()
function enable() 
{
 //document.getElementById("comp_id").style.display='inline';
 //document.getElementById("comp_name").style.display='inline';
 //document.getElementById("busi_id").style.display='inline';
 //document.getElementById("busi_type").style.display='inline';
 //document.getElementById("comp_id1").style.display='none';
 //document.getElementById("comp_name2").style.display='none';
 //document.getElementById("busi_id2").style.display='none';
// document.getElementById("busi_type1").style.display='none';
 var f = document.getElementById("form1");
//var inputs = f.getElementsByTagName("select");
 for(var i = 0; i < f.length; i++)
 {
  if(f[i].type != "button")
  {
   f[i].disabled = false; 
   f[i].style.backgroundColor='';//new added one to remove any background color
  }
 }
 //document.form1.huma_id.readOnly=true;//if it was made editable in queryfunction
 //starting automatic id generation code when click on new button-----------------------------------------------------------------
   sessioncheck();
xmlHttp=GetXmlHttpObject();
 //alert("hey got the boject="+xmlHttp);
   if(xmlHttp==null)
  	  return;
    var url="getuserupdate";
    url=url+"?decide=busi_id&key1=&key2=";
    xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
    xmlHttp.send(null);//alert("the ajax request made ");
	var id = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,'');//alert("hey goy the show data is:"+id);
	//document.getElementById("huma_id").value=id;//alert("hey the assignment to the busi_id field with its value is over");
 //starting automatic id generation code when click on new button----------------------------------------------------------------- 
 //-------------common (buttons) code
goDim("form1","new2");
document.form1.new2.disabled=true;
document.form1.query.disabled=true;
document.form1.exct.disabled=true;//from this line newly added code for Query functionality
document.form1.save.style.display='inline';
document.form1.save.disabled=false;
document.form1.update.style.display='none';
}//enable()
</script>
<!-- <body onload="disable();" style="font-size: 62.5%;"> -->
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();">	
<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script> <form id="form1" name="form1" method="post">
		<p align="right">
			<table width="656" align="center">
				<tr>
					<td width="622" height="">
						<fieldset style="background-color:">
							<!-- <legend class="style23">Region Master</legend> -->
							<legend class="formTitle">Assigning Roles</legend>
							<table bgcolor="" width="100%" height="91%" border="0"
								align="center" bordercolor="#000000">
								<tr>
									<td colspan="2" align="right">
									<!-- for navigation -->
									<script type="text/javascript" src="JS/np.js"></script>
									</td>
								</tr>
								<!-- <tr>
									<td width="22%" height="" align="right" class="style22">Emp Id</td>
									<td width="78%" align="left"><label> <input
											name="huma_id" id="huma_id" type="text" size="55"
											onkeyup="allowupdate();"
											onfocus="addSuggestion('comp_id2','comp_id');" maxlength="55"
											style="border-color: #0099FF;" />
									</label></td>
								</tr> -->
								
								<tr>
									<td width="22%" height="" align="right" class="style22">Emp Id</td>
									<td width="78%" align="left"><label> <input
											name="huma_id" id="huma_id" type="text" size="35"
											onkeyup="allowupdate();"
											onfocus="addSuggestion('Huma_huma_id','huma_id');" maxlength="55"
											style="border-color: #0099FF;" 
											onblur="showDetails('huma_dtl',huma_id.value,'');"/>
									</label></td>
								</tr>
								
								
								<tr>
									<td height="24" class="style22"><div align="right">Name</div></td>
									<td align="left"><input name="fname" id="fname"
										type="text" size="35"  maxlength="30"
										 readonly="readonly" /></td>
								</tr>
								
								<tr>
									<td height="24" class="style22"><div align="right">Mobile No</div></td>
									<td align="left"><input type="text" name="mobileno"
										id="mobileno" maxlength="13" size="15"
										onkeyup="allowupdate();"
										readonly="readonly"/></td>
								</tr>
								
								<!-- <tr>
									<td height="24" class="style22"><div align="right">LastName</div></td>
									<td align="left"><input name="lname" id="lname"
										type="text" size="30"  maxlength="30"
										style="border-color: #0099FF;" readonly="readonly" /></td>
								</tr> -->
								
								<!-- <tr>
									<td height="24" class="style22"><div align="right">Reporting Officer</div></td>
									<td align="left"><input type="text" name="Reportingofficer"
										id="Reportingofficer" maxlength="30" size="30"
										onkeyup="allowupdate();"
										style="border-color: #0099FF;" 
										readonly="readonly"/></td>/></td>
								</tr> -->
								
								<tr>
									<td height="24" class="style22"><div align="right">User Name</div></td>
									<td align="left"><input type="text" name="username"
										id="username" 
										onfocus="addSuggestion('frs_user_name','username');"
										maxlength="30" size="35"
										onkeyup="allowupdate();"/>
										</td>
								</tr>
								

								<!-- <tr>
									<td height="37" class="style22"><div align="right">Remarks</div></td>
									<td align="left"><textarea name="busi_remarks"
											id="busi_remarks" cols="25" rows="2"
											onkeyup="EnforceMaximumLength(this,100); allowupdate();"
											onblur="EnforceMaximumLength(this,100);"></textarea></td>
								</tr> -->
								 <%
								 
									if ((ur.equals("admin"))||(ur.equals("audit")))
									{
										
								  
								%> 
								<tr>
									<td height="" class="style22"><div align="right">Role</div></td>
									<td><div align="left">
											<select name="role" id="role" size="1"
												onChange="allowupdate();">
												<option value="" selected="selected"></option>
												<option value="admin">Administrator</option>
												<option value="areahead">Region Head</option>
												<option value="unithead">Unit Head</option>
												<option value="fx">FX</option>
												<option value="fs">FS</option>
												<option value="user">LSR</option>
											</select>
										</div></td>
								</tr>
						<%}%>
						
						<%
							if (ur.equals("areahead"))
							{
										
								  
						%> 
								<tr>
									<td height="" class="style22"><div align="right">Role</div></td>
									<td><div align="left">
											<select name="role" id="role" size="1"
												onChange="allowupdate();">
												<option value="" selected="selected"></option>
												<option value="unithead">Unit Head</option>
												<option value="fx">FX</option>
												<option value="fs">FS</option>
												<option value="user">LSR</option>
											</select>
										</div></td>
								</tr>
						<%}%>   
						
						<%
							if (ur.equals("unithead"))
							{
										
								  
						%> 
								<tr>
									<td height="" class="style22"><div align="right">Role</div></td>
									<td><div align="left">
											<select name="role" id="role" size="1"
												onChange="allowupdate();">
												<option value="" selected="selected"></option>
												<option value="fx">FX</option>
												<option value="fs">FS</option>
												<option value="user">LSR</option>
											</select>
										</div></td>
								</tr>
						<%}%>
								<!-- New added by Rajesh -->
								<tr>
									<td height="24" class="style22"><div align="right">Password</div></td>
									<td align="left"><input type="password" name="password"
										id="password" maxlength="20" size="35" onkeyup="allowupdate();"/>
										</td>
								</tr>
								
								<tr>
									<td height="24" class="style22"><div align="right">Conform Password</div></td>
									<td align="left"><input type="password" name="Confirmpassword"
										id="Confirmpassword" maxlength="20" size="35" onkeyup="allowupdate();"/>
										</td>
								</tr>
								

								<!-- New ends -->
								<tr>
									<td height="29" class="style22">&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2" align="center" height="61"><table>
											<tr>
												<td>
												<!-- for the buttons in the footer -->
												<script type="text/javascript" src="JS/Fbuttons.js"></script> 
												<br />
													<table id="msg2"
														style="visibility: hidden; position: absolute;" align="">
														<tr>
															<td>Execute</td>
														</tr>
													</table></td>
												<td width="" align="left">&nbsp;&nbsp; <input
													type="button" id="exct" name="exct" class="groovybutton"
													value="&euro;" title=""
													onmouseover="goLite(this.form.name,this.name); showmenu('msg');"
													onmouseout="goDim(this.form.name,this.name); hidemenu('msg');"
													onclick="showEmpn('frs_usr_dtls',username.value,'');" /> <br />
													<table id="msg"
														style="visibility: hidden; position: absolute;" align="">
														<tr>
															<td>Execute</td>
														</tr>
													</table></td>
											</tr>
										</table></td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td height="">&nbsp;</td>
				</tr>
			</table>
	</form>
	<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
	<!-- </td>
	</tr>

	<script type="text/javascript" src="JS/down1.js"></script>
</body> -->
<%}%>
</html>

