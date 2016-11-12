<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company master</title>

<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>
<style>
a {
	text-decoration: none;
	color: blue;
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
  var compid=new RegExp("C[0-9]{3}"); //(compid.test(document.form1.comp_name.value))
   if((!patt1.test(document.form1.comp_name.value)) || (reason.test(document.form1.comp_name.value)) || (compid.test(document.form1.comp_name.value)))
    {
      alert("please enter Company name correctly");
      document.form1.comp_name.focus();
      return false;
    }//comp_name
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
			var program;//='scm';
				var allElements=$(this).serialize();
			 	//alert("hey inside the submit functin of jquery");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			//alert(document.form1.save.style.display);
			if(document.form1.save.style.display=='inline')
			  program='scm';	
			else  
			   program='ucm'; //alert("hey the program is="+program);	
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
		 document.getElementById("comp_id").value ='';
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
     document.getElementById("comp_id").readOnly =true;
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
   document.getElementById("comp_id").value =star[x][0];
  document.getElementById("comp_name").value =star[x][1];
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
 document.form1.comp_id.disabled=false;//3 lines, newly added code for the Query functionality
 document.form1.comp_id.readOnly=false;
 document.form1.comp_id.style.backgroundColor='skyblue';
 document.form1.comp_id.focus();
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
 document.form1.comp_id.readOnly=true;//if it was made editable in queryfunction
 //alert("just bfr session check2");
 //starting automatic id generation code when click on new button-----------------------------------------------------------------
   sessioncheck();
xmlHttp=GetXmlHttpObject();
 //alert("hey got the boject="+xmlHttp);
   if(xmlHttp==null)
  	  return;
    var url="getuserupdate";
    url=url+"?decide=comp_id&key1=&key2=";
    xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
    xmlHttp.send(null);//alert("the ajax request made ");
	var id = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,'');//alert("hey goy the show data is:"+id);
	document.getElementById("comp_id").value=id;//alert("hey the assignment to the weekoff_id field with its value is over");
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
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>
</head><body bottommargin="100%" marginheight="100%" rightmargin="100%"			leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();">
<!---------------- Code for FRS theme starts here  ------------------------->
<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script>
<!---------------- Code for FRS theme top.js ends here  -------------------------><form id="form1" name="form1" method="post">
		<!--<input type="text" size="5" maxlength="5"  name="txt" style="border-color:#FF0000"  />-->

		<p align="right">
			<table width="640" align="center">
				<tr>
					<td width="606" height="250"><fieldset
							style="background-color:">
							<legend class="formTitle"> Company Master</legend>
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
												title="" onmouseover="goLite(this.form.name,this.name)"
												onmouseout="goDim(this.form.name,this.name)"
												onclick="nextprevious(this.name);" />
										</div>
									</td>
								</tr>
								<tr>
									<td width="24%" align="right"><div
											align="right">Company id</div></td>
									<td width="76%" align="left">
											<div align="left">
												<input name="comp_id" id="comp_id" type="text" size="55"
													onfocus="addSuggestion('comp_id2','comp_id');"
													maxlength="55" class="masterInput" readonly="readonly"/>(Ex: C001)
											</div>
								</td>
								</tr>
								<tr>
									<td height="24" align="right"><div
											align="right" >Company name</div></td>
									<td align="left">
										<div align="left">
											<input name="comp_name" id="comp_name" type="text" size="55"
												maxlength="60" onkeyup="allowupdate();"
												onfocus="addSuggestion('comp_id2','comp_name');"
												class="masterInput"  />
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
													<td><input type="button" id="new2" name="new2"
														class="groovybutton" value="New" title=""
														onmouseover="goLite(this.form.name,this.name)"
														onmouseout="goDim(this.form.name,this.name)"
														onclick="enable();" /> &nbsp;&nbsp;&nbsp; <input
														type="submit" id="save" name="save" class="groovybutton"
														value="Save" title=""
														onmouseover="goLite(this.form.name,this.name)"
														onmouseout="goDim(this.form.name,this.name)"
														 /> <input type="submit"
														id="update" name="update" class="groovybutton"
														value="Upda" title=""
														onmouseover="goLite(this.form.name,this.name)"
														onmouseout="goDim(this.form.name,this.name)"
														 /> &nbsp;&nbsp;&nbsp; <input
														type="reset" id="clear" name="clear" class="groovybutton"
														value="Clear" onclick="disable();" title=""
														onmouseover="goLite(this.form.name,this.name)"
														onmouseout="goDim(this.form.name,this.name)" />

														&nbsp;&nbsp;&nbsp; <input type="button" id="cancel"
														name="cancel" class="groovybutton" value="Cancel" title=""
														onmouseover="goLite(this.form.name,this.name)"
														onmouseout="goDim(this.form.name,this.name)"
														onclick="window.location='AdminHome.jsp'" />

														&nbsp;&nbsp;&nbsp; <input type="button" id="query"
														name="query" class="groovybutton" value="Query" title=""
														onmouseover="goLite(this.form.name,this.name)"
														onmouseout="goDim(this.form.name,this.name)"
														onclick="queryfunction();" /> <br />
														<table id="msg2"
															style="visibility: hidden; position: absolute;">
															<tr>
																<td>Execute</td>
															</tr>
														</table></td>
													<td width="" align="left">&nbsp;&nbsp; <input
														type="button" id="exct" name="exct" class="groovybutton"
														value="&euro;" title=""
														onmouseover="goLite(this.form.name,this.name); showmenu('msg');"
														onmouseout="goDim(this.form.name,this.name); hidemenu('msg');"
														onclick="showEmpn('comp_mstr',comp_id.value,'');" /> <br />
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
<!---------------- Code for FRS theme bottom starts here  ------------------------->
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>
<!---------------- Code for FRS theme bottom ends here  ------------------------->	
<%}%>
</html>
