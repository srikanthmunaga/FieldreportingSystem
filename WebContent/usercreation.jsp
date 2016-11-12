<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>FRS User Creation</title>


<% 

//System.out.println("Hai inside the Assign_role jsp and role="+ur);
//if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
	if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
	//	if (request.getSession().getAttribute("username") == null) {
 { 
   response.sendRedirect("frslogin.jsp");  // Not logged in, redirect to login page.
	}
String role=(String)request.getSession().getAttribute("userrole");
//if (((HttpServletRequest) request).getSession().getAttribute("user") != null)
if (role!= null)
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
	  //alert("Please enter the Emp Id");
	  //document.getElementById("huma_id").focus();
	  //document.getElementById("huma_id").style.background="#fffacd";
	  //return false;
	}
	var mobile=/^[+]?[0-9]{2}(-|\s)?[0-9]{8,10}$/;
	var patt2=new RegExp("^[A-Za-z]+[0-9]*[ A-Za-z0-9]*$");
	/*if(!mobile.test(document.getElementById("mobileno").value))
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
	 if(!patt2.test(document.form1.lname.value))
    {
      alert("Please enter the Last name");
      document.form1.lname.focus();
      return false;
    } */
    var user_freeze = document.getElementById("freeze").value;
   // alert("userfreeze value is "+user_freeze);
    if(user_freeze=='N')
    {
   if(document.form1.huma_id.value.replace(/^\s+/,'').replace(/\s+$/,'')=="")
   //if((!patt1.test(document.form1.area_remarks.value)) || (reason.test(document.form1.area_remarks.value)))
    {
      alert("please enter the Emp Id");
      document.form1.huma_id.focus();
      return false;
    }//huma_id
    }
	if(!patt2.test(document.form1.username.value))
    {
      alert("Please enter the Username");
      document.form1.username.focus();
      return false;
    }
    /* if(!patt2.test(document.form1.frs_role.value==""))
    {
      alert("Please select the role");
      document.form1.frs_role.focus();
      return false;
    } */
    if(document.getElementById("frs_role").value=='')
    {
       alert("Please select the Role");
	   document.form1.frs_role.focus();
	   return false;
	}
	
	if(document.getElementById("freeze").value=='')
    {
       alert("What you want? \n To Freeze the user or not! \n Please Select");
	   document.form1.freeze.focus();
	   return false;
	}
	
	if(!patt10.test(document.form1.password.value))
    {
      alert("Please enter the Password");
      document.form1.password.focus();
      return false;
    }
	if(!patt10.test(document.form1.Confirmpassword.value))
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

function focuses() { document.form1.area_name.focus(); }
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
 document.getElementById("fname").value =star[x][0];
 document.getElementById("mobileno").value =star[x][1];
 if(document.getElementById("username").value=='')document.getElementById("username").value =star[x][2];
}

function showEmpn(decide,key1,key2,key3,key4)
{
 //alert("Inside the showEmpn");
 //alert("decide="+decide+" key1="+key1+" key2="+key2+" key3="+key3+" key4="+key4);
	sessioncheck();
xmlHttp=GetXmlHttpObject();

 if (xmlHttp==null)
  {
  return;
  }
 var url="getuserupdate";
  url=url+"?decide="+decide+"&key1="+key1+"&key2="+key2+"&key3="+key3+"&key4="+key4;
//alert("hey url is made and the url is "+url);// xmlHttp.onreadystatechange=stateChangedn2;
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
 document.getElementById("frs_role").value =star[x][4];
 document.getElementById("password").value =star[x][5];
 document.getElementById("Confirmpassword").value =star[x][5];
 document.getElementById("freeze").value =star[x][6];
 document.getElementById("seqid").value =star[x][7];
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
//showList('area_id');
</script>

<script language="javascript" type="text/javascript">var ssssss="<%=(String)session.getAttribute("userType")%>";</script>
<!-- <script type="text/javascript" src="JS/Atop2.js"></script> -->

<script type="text/javascript">
function queryfunction() 
{ 
//Everything from disable() exceptiong button code,just for primary key enable(replace true by false) and editable(add readOnly=false)
 //document.getElementById("comp_id").style.display='none';
 //document.getElementById("area_id").style.display='none';
 //document.getElementById("area_type").style.display='none';
 //document.getElementById("comp_name").style.display='none';
  //document.getElementById("comp_id1").style.display='inline';
 //document.getElementById("comp_name2").style.display='inline';
 //document.getElementById("area_id2").style.display='inline';
 //document.getElementById("area_type1").style.display='inline';
 //document.form1.area_name.value='';
 //document.form1.area_remarks.value='';
// alert("In the Query Function");
 var f = document.getElementById("form1");
 var inputs = f.getElementsByTagName("input");
 //alert("Disabling all the fields");
 for(var i = 0; i < inputs.length; i++)
 { 
   if(inputs[i].type=="text")
   {
    if(inputs[i].style.display!="none")
    inputs[i].value='';
    inputs[i].disabled=true;
   }
  }
 //document.form1.area_remarks.value='';
 //document.form1.area_remarks.disabled=true;
 //document.form1.frs_role.value='';
 //document.form1.frs_role.disabled=true;
 
 //document.form1.password.value='';
 //document.form1.password.disabled=true;
 
 //document.form1.Confirmpassword.value='';
 //document.form1.Confirmpassword.disabled=true;
 
 //document.form1.huma_id.disabled=false;//3 lines, newly added code for the Query functionality
 //document.form1.huma_id.readOnly=false;
 //alert("Enabling the fields");
 document.form1.huma_id.disabled=false;//3 lines, newly added code for the Query functionality
 document.form1.huma_id.style.backgroundColor='skyblue';
 document.form1.username.disabled=false;
 document.form1.username.style.backgroundColor='skyblue';
 //alert("Enabling the username");
 
 document.form1.frs_role.disabled=false;
 document.form1.frs_role.style.backgroundColor='skyblue';
 document.form1.freeze.disabled=false;
 document.form1.freeze.style.backgroundColor='skyblue';
 
 document.form1.username.focus();
 document.form1.username.value = username_js;
 //document.form1.username.value = ((HttpServletRequest) request).getSession().getAttribute("username") ;
// alert("Focusing");
 //alert("hey ging to call the functions");
 //showList('area_id2'); 
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
 //alert("Query function completed");
}//queryfunction();
function disable() 
{
 //document.getElementById("comp_id").style.display='none';
 //document.getElementById("area_id").style.display='none';
 //document.getElementById("area_type").style.display='none';
 //document.getElementById("comp_name").style.display='none';
  //document.getElementById("comp_id1").style.display='inline';
 //document.getElementById("comp_name2").style.display='inline';
// document.getElementById("area_id2").style.display='inline';
 //document.getElementById("area_type1").style.display='inline';
 //document.form1.area_type1.value='';//new added line
 //document.form1.area_remarks.value='';
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
  //document.form1.area_type1.value='';
  //document.form1.area_type1.disabled=true;	
 //document.form1.area_remarks.value='';
 //document.form1.area_remarks.disabled=true;
 document.form1.password.value='';
 document.form1.password.disabled=true;

 document.form1.Confirmpassword.value='';
 document.form1.Confirmpassword.disabled=true;

 document.form1.frs_role.value='';
 document.form1.frs_role.disabled=true;
 
 document.form1.freeze.value='';
 document.form1.freeze.disabled=true;

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
 //document.getElementById("area_id").style.display='inline';
 //document.getElementById("area_type").style.display='inline';
 //document.getElementById("comp_id1").style.display='none';
 //document.getElementById("comp_name2").style.display='none';
 //document.getElementById("area_id2").style.display='none';
// document.getElementById("area_type1").style.display='none';
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
//   sessioncheck();
//xmlHttp=GetXmlHttpObject();
 //alert("hey got the boject="+xmlHttp);
  // if(xmlHttp==null)
  	//  return;
    //var url="getuserupdate";
    //url=url+"?decide=area_id&key1=&key2=";
    //xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
    //xmlHttp.send(null);//alert("the ajax request made ");
	//var id = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,'');//alert("hey goy the show data is:"+id);
	//document.getElementById("huma_id").value=id;//alert("hey the assignment to the area_id field with its value is over");
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
<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script> 
<%
//String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if((role.equals("admin"))||(role.equals("audit")))// ||role.equals("areahead") || role.equals("unithead"))
{ %>

<form id="form1" name="form1" method="post">
	<table cellpadding="1" cellspacing="1" background="blue">
							<tr>

								<!-- <td width="380"></td> 
								<td width="380" align="left"><b><a href="AdminHome.jsp">Administrator HOME</a></b></td> -->
							<!-- 	<td width="380" align="left"><b><a href="Ahome.jsp">HOME</a></b></td>   -->
							<br>
							</tr>
						</table>
<!--  <script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script> <form id="form1" name="form1" method="post"> -->
		<p align="right">
			<table width="656" align="center">
				<tr>
					<td width="622" height="">
						<fieldset style="background-color:">
							<!-- <legend class="style23">Region Master</legend> -->
							<legend class="formTitle">User Creation</legend>
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
										onfocus="addSuggestion('empname-username','username');"
										maxlength="30" size="35"
										onkeyup="allowupdate();"/>
										</td>
								</tr>
								
								
								

								<!-- <tr>
									<td height="37" class="style22"><div align="right">Remarks</div></td>
									<td align="left"><textarea name="area_remarks"
											id="area_remarks" cols="25" rows="2"
											onkeyup="EnforceMaximumLength(this,100); allowupdate();"
											onblur="EnforceMaximumLength(this,100);"></textarea></td>
								</tr> -->
								 <%
								 
									if ((role.equals("admin"))||(role.equals("audit"))) if ((role.equals("admin"))||(role.equals("audit")))
									{
										
								  
								%> 
								<tr>
									<td height="" class="style22"><div align="right">Role</div></td>
									<td><div align="left">
											<select name="frs_role" id="frs_role" size="1"
												onChange="allowupdate();">
											    <option value="" selected="selected">Select</option>
												<option value="admin">Administrator</option>
												<option value="audit">Data Miner</option>
												<option value="areahead">Region Head</option>
												<option value="unithead">Unit Head</option>
												<option value="executive">FX</option>
											<!-- 	<option value="fs">FS</option>  -->
												<option value="user">Field Staff</option>
											</select>
										</div></td>
								</tr>
						<%}%>
						
						<%
							if (role.equals("areahead"))
							{
										
								  
						%> 
								<!-- <tr>
									<td height="" class="style22"><div align="right">Role</div></td>
									<td><div align="left">
											<select name="frs_role" id="frs_role" size="1"
												onChange="allowupdate();">
											<option value="">Select</option>
												<option value="unithead">Unit Head</option>
												<option value="executive">FX</option>
												<option value="user">Field Staff</option>
											</select>
										</div></td>
								</tr>
						<%}%>   
						
						<%
							if (role.equals("unithead"))
							{
										
								  
						%> 
								<tr>
									<td height="" class="style22"><div align="right">Role</div></td>
									<td><div align="left">
											<select name="frs_role" id="frs_role" size="1"
												onChange="allowupdate();">
											<option value="">Select</option>
												<option value="executive">FX</option>
												<option value="user">Field Staff</option>
											</select>
										</div></td>
								</tr> -->
						<%}%>
								<!-- New added by Rajesh -->
								
								<tr>
									<td height="" class="style22"><div align="right">Freeze User</div></td>
									<td><div align="left">
											<select name="freeze" id="freeze" size="1"
												onChange="allowupdate();">
											<option value="" selected="selected">Select</option>
												<option value="N">No</option>
												<option value="Y">Yes</option>
											</select>
										</div></td>
								</tr>
								<tr>
									<td height="24" class="style22"><div align="right">Password</div></td>
									<td align="left"><input type="password" name="password"
										id="password" maxlength="20" size="35" onkeyup="allowupdate();"/>
										</td>
								</tr>
								
								<tr>
									<td height="24" class="style22"><div align="right">Confirm Password</div></td>
									<td align="left"><input type="password" name="Confirmpassword"
										id="Confirmpassword" maxlength="20" size="35" onkeyup="allowupdate();"/>
										</td>
										<td>
										<input type="hidden" name="seqid" id="seqid">
									<!-- 	<input type="hidden" name="user_freeze" id="user_freeze" value='N'>  -->
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
												<script type="text/javascript" src="JS/Abuttons.js"></script> 
											<!-- 	<script type="text/javascript" src="JS/Fbuttons.js"></script>  --> 
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
													onclick="showEmpn('frs_usr_dtls',username.value,huma_id.value,frs_role.value,freeze.value);" /> <br />
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
	<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
	</body>
	
<%-- <%}%> --%>
<%
}//authorised acess else------------------------------------------------------------------------------------
else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to create new User
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
<%
}
}
	%>
	
</html>
