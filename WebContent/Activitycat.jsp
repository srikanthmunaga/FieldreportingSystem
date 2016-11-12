<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Activity Category Master</title>

<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>
<style>
a {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: none;
	color: #999;
}

.ui-autocomplete {
	position: absolute;
	cursor: default;
	z-index: 4000 !important
}
</style>
<% //-------------------------------------------;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;----
if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
 {
    System.out.println("Control forwarded to home page");
	response.sendRedirect("frslogin.jsp");  // Not logged in, redirect to login page.
	}

else //if (((HttpServletRequest) request).getSession().getAttribute("user") != null)
   {
   	
%>

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
 //alert("hey got the xml object");
return xmlHttp;
}//GetXmlHttpObject()
function validateForm()
{ //alert("hey inside the validateForm() function");
  var patt1=new RegExp("[A-Za-z]");
  //var num=new RegExp("[0-9]");
  var reason=new RegExp("(\/\/\/\/\/\/|::::::)");
  var compid=new RegExp("C[0-9]{3}"); //(compid.test(document.form1.ac_name.value))
   if((!patt1.test(document.form1.ac_name.value)) || (reason.test(document.form1.ac_name.value)) || (compid.test(document.form1.ac_name.value)))
    {
      alert("please enter category name correctly");
      document.form1.ac_name.focus();
      return false;
    }//ac_name
 //alert("hey end of the validateFom function");
  
}//validateForm()

function EnforceMaximumLength(fld,len) 
{
 if(fld.value.length > len) { fld.value = fld.value.substr(0,len); document.form1.comp_ifsccode.focus();/*fld.focus();*/ }
}
$(document).ready(function(){//alert("hey inside the ready functin of jquery update");
		$("#form1").submit(function(){sessioncheck();//alert("hey inside the submit function");
		  if(document.form1.onSubmit==validateForm()) 
			{ //alert("hey the validation is done");

				var allElements=$(this).serialize();
			 	//alert("hey inside the submit functin of jquery");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			//alert(document.form1.save.style.display);
			if(document.form1.save.style.display=='inline'){
			//alert("Before executing actcat");
				  var program='actcat';	}
			else  
			   var program='actcatupdate'; //alert("hey the program is="+program);	
			   	$.ajax({ 
				   	url: program,
		          	data: allElements,
		          	type: 'post',
		          	dataType: "text",
		   			success: function(msg){//alert("hey inside the msg function & msg="+msg);
					msg = msg.replace(/^\s+/,'').replace(/\s+$/,''); //removes starting & ending spaces
					//alert("after removing the starting& ending spaces  msg="+msg);
						if(msg.substr(0,2) == 'OK') // Message Sent, check and redirect
						{//alert("hey  in side ok msg.length="+msg.length);				// and direct to the success page
							alert(msg.substr(2,msg.length-2)); 
							disable();
						}
						else
						{//alert("hey  in side the else of ok");
							alert(msg); //msg='OK';
			            }
					},//msg function
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
				        alert("Status: " + textStatus); alert("Error: " + errorThrown); 
				    }
				});//ajax funciton
			}, 0);//time out function
			return false;
}//if(document.form1.onSubmit==validateForm()) 
else { return false; }

 		});//submit function		
	});//ready function

	
	
	var star;//i;//global variable
var x=0;
function showEmpn(decide,key1,key2)
{//alert("hety inside the showEmpn");
 // key1=key1.toUpperCase();
 //var patt=new RegExp("[A-Za-z0-9]");
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
/*}//showEmpn(decide,key1,key2)
var star;//i;//global variable
var x=0;
function stateChangedn2() 
{*/ x=0; //alert("hey inside the stateChangedn");
  //if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
  //{ 
var showdata = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
	showdata=showdata.replace(/\/\/\/\/\/\/$/,"");//removes the "//////"  from the end of the "data" array
	star=showdata.split("//////");//alert("hey the star="+star);
	if(star=='')
	 { 
		 alert("No records found");
		 document.form1.previous.disabled=true;
         document.form1.next.disabled=true;
		 document.getElementById("cat_id").value ='';
	 }
	else if(star.length>=0)
	{ 
	  for(var k=0; k<star.length; k++)
	  star[k] = star[k].split("::::::");
	 //i=star.length;
	 document.form1.previous.disabled=false;
     document.form1.next.disabled=false;
	 goDim("form1","exct");
	 document.getElementById("msg").style.visibility='hidden';
	 document.getElementById("exct").disabled=true;
	 //msr
     document.getElementById("cat_id").readOnly =false;
	 nextprevious("first");
	}//esle
//  } 
}//stateChangedn() 
function nextprevious(np)
{
 if(np=="first") 
 { 
  x=0; //here x=0;//alert("hey you are in the nextprevious()");
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
  
  var f = document.getElementById("form1");
  var inputs = f.getElementsByTagName("input");
  var i; 
  for(i = 0; i < inputs.length; i++)
  { 
    if(inputs[i].type=="text")
	 {
       inputs[i].disabled=false; 
	  }
   }
   //document.getElementById("cal").style.display='inline'; 
   document.getElementById("cat_id").value =star[x][0];
  document.getElementById("ac_name").value =star[x][1];
 }//show()
function allowupdate()
{
 if((document.form1.update.style.display=="inline") && (document.form1.exct.disabled))
    { 
	document.form1.update.disabled=false;
	  //document.form1.update.focus();
	 }
}//allowupdate()
function queryfunction() 
{ //alert("hey inside the query function");
//Everything from disable() exceptiong button code,just for primary key enable(replace true by false) and editable(add readOnly=false)
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
 document.form1.cat_id.disabled=false;//3 lines, newly added code for the Query functionality
 document.form1.cat_id.readOnly=false;
 document.form1.cat_id.style.backgroundColor='skyblue';
 document.form1.cat_id.focus();
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
 document.form1.clear.disabled=false; //alert("hey end of the query");
}//queryfunction();
function disable()
{ //alert("inside the disable");
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
 //alert("Going for butons common code");
//----------Common (buttons) code
 
 document.form1.save.disabled=true; //alert("save");
 document.form1.first.disabled=true;//alert("first");
 document.form1.previous.disabled=true;//alert("pre");
 document.form1.next.disabled=true;//alert("next");
 document.form1.last.disabled=true;//alert("last");
 document.form1.new2.disabled=false;//alert("new2");
 document.form1.query.disabled=false;//alert("query before goDim");
 goDim("form1","clear");//alert("gdim");
 document.form1.clear.disabled=true;
 document.form1.exct.disabled=true;//from this line new added code for Query functionality
 //alert("disabled exct");
 document.form1.save.style.display='inline';//alert("made save inline");
 document.form1.update.style.display='none';//alert("made update none display");
}//disable(); 
function enable() 
{ //alert("inside the enable");
var f = document.getElementById("form1");
//var inputs = f.getElementsByTagName("select");
 for(var i = 0; i < f.length; i++)
 {
  if(f[i].type != "button")
  {
   f[i].disabled = false; 
   f[i].style.backgroundColor='';//new added one to remove any background color
  }
 } //alert("just bfr session check");
 document.form1.cat_id.readOnly=true;//if it was made editable in queryfunction
 //alert("just bfr session check2");
 //starting automatic id generation code when click on new button-----------------------------------------------------------------
   sessioncheck();
xmlHttp=GetXmlHttpObject();
 //alert("hey got the boject="+xmlHttp);
   if(xmlHttp==null)
  	  return;
    var url="getuserupdate";
    url=url+"?decide=cat_id&key1=&key2=";
    xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
    xmlHttp.send(null);//alert("the ajax request made ");
	var id = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,'');//alert("hey goy the show data is:"+id);
	document.getElementById("cat_id").value=id;//alert("hey the assignment to the weekoff_id field with its value is over");
 //starting automatic id generation code when click on new button----------------------------------------------------------------- 
//-------------------common (buttons) code for
goDim("form1","new2");
document.form1.new2.disabled=true;
document.form1.query.disabled=true;
document.form1.exct.disabled=true;//from this line newly added code for Query functionality
document.form1.save.style.display='inline';
document.form1.update.style.display='none';
}//enable()
</script>
<script language="javascript" type="text/javascript">var ssssss="<%=(String)session.getAttribute("userType")%>";</script>

<!---------------- Code for FRS theme starts here  ------------------------->
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" /> 
<link rel="stylesheet" href="theme/blue.css" type="text/css" /> 
<style type="text/css"> 
body { background-color: #d0e4fe; } h1 { color: orange; text-align: center; } p { font-family: "Times New Roman"; font-size: 20px; }

a {	text-decoration:none;	color:blue;}a:hover {	text-decoration:none;	color:#999;	}
.ui-autocomplete        {            position:absolute;            cursor:default;            z-index:4000 !important             }
</style> 
<script type="text/javascript" src="JS/jquery.min.js"></script> 
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>   
<script type="text/javascript">
function goLite(FRM,BTN){		   window.document.forms[FRM].elements[BTN].style.color =  "#ffffff";	   window.document.forms[FRM].elements[BTN].style.backgroundImage="url(images/back04.gif)";}
function goDim(FRM,BTN){   window.document.forms[FRM].elements[BTN].style.color = "";      window.document.forms[FRM].elements[BTN].style.backgroundImage="url(images/back03.gif)";} 
function showmenu(elmnt){document.getElementById(elmnt).style.visibility="visible";}
function hidemenu(elmnt){document.getElementById(elmnt).style.visibility="hidden";}
var logout="logout.jsp";					
//$.ajax({ 				   	url: logout,		          	data: "state_id="+ $("#state_id").val(),		          	type: "post",		   			success: function(msg){ 					msg = msg.replace(/^\s+/,"").replace(/\s+$/,""); 					 if(msg!= "") 						{  							document.location="frslogin.jsp";							alert("you have successfully logged out"); 						}						else{ 						 alert(msg); }			          }				});			
</script>
<style type="text/css">
a:link {	text-decoration: none;}a:active {	text-decoration: none;}
a{text-decoration:none;}
a:hover{color:#ffffff}
input.groovybutton{   height:24px; background-image:url(images/back03.gif);}
.groovybutton1{height:20px; background-image:url(images/back03.gif);}


 </style>

	<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>
	</head>
	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();">
	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Cmenu.js"></script>
<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if((role.equals("admin"))||(role.equals("audit")))// ||role.equals("areahead") || role.equals("unithead"))
{ %>
	
<form id="form1" name="form1" method="post">
		<!--<input type="text" size="5" maxlength="5"  name="txt" style="border-color:#FF0000"  />-->
							<!--<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
							
								<td width="380"></td>
							 	<td width="380" align="left"><b><a href="uhChome.jsp">UH CC_HOME</a></b></td>  
							<td width="380" align="left"><b><a href="Chome.jsp">HOME</a></b></td> 

							</tr>
							</table>--><br />

		<p align="right">
			<table width="640" align="center">
				<tr>
					<td width="620" height="250"><fieldset
							style="background-color:; width: 662px">
							<legend class="formTitle">Activity Category Master</legend>
							<table bgcolor="" width="100%" height="91%" border="0"
								align="center" bordercolor="#000000">
								<tr>
									<td height="44" colspan="2" align="right">
										<div align="right">
											<input type="button" id="first" name="first"
												class="groovybutton1" value="|&lt;" title=""
												onmouseover="goLite(this.form.name,this.name)"
												onmouseout="goDim(this.form.name,this.name)"
												onclick="nextprevious(this.name);" /> <input type="button"
												id="previous" name="previous" class="groovybutton1"
												value="&lt;" title=""
												onmouseover="goLite(this.form.name,this.name)"
												onmouseout="goDim(this.form.name,this.name)"
												onclick="nextprevious(this.name);" />
											&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" id="next"
												name="next" class="groovybutton1" value="&gt;" title=""
												onmouseover="goLite(this.form.name,this.name)"
												onmouseout="goDim(this.form.name,this.name)"
												onclick="nextprevious(this.name);" /> <input type="button"
												id="last" name="last" class="groovybutton1" value="&gt;|"
												title="" onMouseOver="goLite(this.form.name,this.name)"
												onmouseout="goDim(this.form.name,this.name)"
												onclick="nextprevious(this.name);" />
										</div>
									</td>
								</tr>
								<tr>
									<td width="24%" align="right"><div
											align="right">Activity Category ID</div></td>
									<td  align="left">
											<div align="left">
												<input name="cat_id" id="cat_id" type="text" size="55" maxlength="55" onFocus="addSuggestion('ACTCAT_ID','cat_id');" readonly="readonly"/>(Ex: AC001)<!-- onfocus="addSuggestion('cat_id2','cat_id');" -->
												
											</div>
								</td>
								</tr>
								<tr>
								
									<td height="24" align="right" ><div  style="width: 159px; ">Activity Category Name</div></td>
									<td align="left" >
										<div align="left">
											<input name="ac_name" id="ac_name" type="text" size="55"
												maxlength="60" onKeyUp="allowupdate();"
												onfocus="addSuggestion('ACTCAT_ID','ac_name');"
												 /><!--class="masterInput"  -->
										</div>
									</td>
								</tr>
								<tr>
									<td height="24" align="right">&nbsp;</td>
									<td align="left">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2" align="center" valign="bottom" height="61">
										<div align="center">
											<table>
												<tr>
													<td ><script type="text/javascript" src="JS/Cbuttons.js"></script>
                <br />
                <table id="msg2" style="visibility:hidden; position:absolute;" align="">
                  <tr>
                    <td >Execute</td>
                        </tr>
                  </table></td>
													<td width="" align="left">&nbsp;&nbsp; <input
														type="button" id="exct" name="exct" class="groovybutton"
														value="&euro;" title=""
														onmouseover="goLite(this.form.name,this.name); showmenu('msg');"
														onmouseout="goDim(this.form.name,this.name); hidemenu('msg');"
														onclick="showEmpn('comp_mstr1',cat_id.value,'');" /> <br />
														<table id="msg"
															style="visibility: hidden; position: absolute;">
															<tr>
																<td>Execute</td>
															</tr>
														</table></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
				</fieldset>
				</td>
				</tr>
			</table>
	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
<%
}//authorised acess else------------------------------------------------------------------------------------
else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to create new Activity Category
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>

<%
}
}
	%>
	
</html>
