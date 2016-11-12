<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="frs_cls.JdbcConnect" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BSFL Unit master</title>
<meta name="description" content="SearchField example"></meta>
<% //-------------------------------------------;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;----
if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
 {
    response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to login page.
	}

else //if (((HttpServletRequest) request).getSession().getAttribute("user") != null)
   {
	   String unit_code = "";
	   String huma_id = request.getSession().getAttribute("huma_id").toString();
	   Connection con = new JdbcConnect().getConnection();
	   PreparedStatement ps = con.prepareStatement("select bsflunit_name||'-'||bsflunit_ucode from bsflunit_mstr where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id=?)");
	   ps.setString(1, huma_id);
	   ResultSet rs = ps.executeQuery();
	   if(rs.next())
	   {
		   unit_code = rs.getString(1);
	   }	
%>
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>

<!--<script type="text/javascript" src="JAVASCRIPT/jquery-1.4.2.min.js"></script>-->

<script type="text/javascript" language="javascript">
function validateForm()
{
	  //alert("hey came to validateForm");
	   
	  // var patt1=new RegExp("^[A-Z]+[A-Za-z]+[0-9]*[ A-Za-z0-9]{2,18}$");
	  // var number1=new RegExp("^[A-Z]+");
	  
	var reason=new RegExp("(\/\/\/\/\/\/|::::::)");
	var patt1=new RegExp("[0-9]{3}");
	var patt12=new RegExp("[0]{3}");
	var patt2=new RegExp("[A-Za-z]+[0-9]*[A-Za-z0-9]*");//new RegExp("^[A-Za-z]+[0-9]*$");
	
   if((!patt1.test(document.form1.bsflunit_ucode.value)) || (document.form1.bsflunit_ucode.value.length<3))
	  {
       alert("Please enter the Unit code,Correctly");
	   document.form1.bsflunit_ucode.focus();
      return false;
	  }
   if(patt12.test(document.form1.bsflunit_ucode.value))
	  {
       alert("Please enter the Unit code,Correctly");
	   document.form1.bsflunit_ucode.focus();
       return false;
	  }
	 if((!patt2.test(document.form1.bsflunit_name.value))||(reason.test(document.form1.bsflunit_name.value))|| (document.form1.bsflunit_name.value.length<4))
    {
      alert("Please enter the Unit name Correctly,with min 4 digits");
      document.form1.bsflunit_name.focus();
      return false;
    }
	 //Added by Rajesh
	 var patt10=new RegExp("[A-Za-z0-9]");
	 if(!patt10.test(document.getElementsByName("busiid")[0].value))   
		{
		  alert("Please enter the value for Area Belongs To");
		  document.getElementsByName("busiid")[0].focus();
		  document.getElementsByName("busiid")[0].style.background="#fffacd";
		  return false;
		}
	 
	 if(!patt10.test(document.getElementsByName("huma_id")[0].value))   
	{
	  alert("Please enter the value for Unit incharge");
	  document.getElementsByName("huma_id")[0].focus();
	  document.getElementsByName("huma_id")[0].style.background="#fffacd";
	  return false;
	}
	 
	 var email= /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/; 
		if(!email.test(document.form1.unit_email.value))
	    {
	      alert("Please Enter valid email address");
		  document.form1.unit_email.focus();
	      return false;
	    }
		//var phone=new RegExp("[0-9]{11}");
		//var mobile=new RegExp("[0-9]{12}");
		var phone = /^[0-9]\d{1,4}(-|\s)?\d{6,9}$/;//Ex:040 39162116/040-39162661/04039162116
		var mobile=/^[+]?[0-9]{2}(-|\s)?[0-9]{8,10}$/;//Ex:91 9396812884/91-9396812884/919396812884
		if((!phone.test(document.form1.unit_phno.value))||((document.form1.unit_phno.value).length<11))
	    {
	      alert("Please enter valid Phone no");
	      document.form1.unit_phno.focus();
	      return false;
	    }
		
	    if(!mobile.test(document.form1.unit_mob.value))
	      {
	      alert("Please enter valid Mobile no");
	      document.form1.unit_mob.focus();
		  return false;
	      }
}//validateForm()]
</script>
<script type="text/javascript">

//hey the update&save butons(submission) code starts here---------------------------------------------------------
$(document).ready(function(){//alert("hey inside the ready functin of jquery update");
		$("#form1").submit(function(){sessioncheck();//alert("hey inside the submit function");
		  if(document.form1.onSubmit==validateForm()) 
			{ var allElements=$(this).serialize();//alert("hey the validation is done");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			if(document.form1.save.style.display=='inline')
			  var program='sbsflm';	
			else  
			   var program='ubsflm'; //alert("hey the program is="+program);	
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
var star;//i;//global variable
var x=0;
function showEmpn(decide,key1,key2,key3)
{//alert("hety inside the showEmpn");
 sessioncheck();
xmlHttp=GetXmlHttpObject();

 if (xmlHttp==null)
  {
  return;
  }
 var url="getuserupdate";
  url=url+"?decide="+decide+"&key1="+key1+"&key2="+key2+"&key3="+key3;
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
		 document.getElementById("bsflunit_ucode").value ='';
		 //document.getElementById("comp_name").disabled=true;
		 //document.getElementById("comp_name").value ='';
	 }
	else if(star.length>=0)
	{ 
	  for(var k=0; k<star.length; k++)
	  star[k] = star[k].split("::::::");
	 //alert("hey the got the object is :"+star);
	 document.form1.previous.disabled=false;
     document.form1.next.disabled=false;
	 goDim("form1","exct");
	document.getElementById("msg").style.visibility="hidden";
	 document.getElementById("exct").disabled=true;
     document.getElementById("bsflunit_ucode").readOnly =true;
	 //document.getElementById("cal").style.display='inline';  
	 //document.getElementById("cal2").style.display='inline';
	 nextprevious("first"); 
	 //alert("hey the nextprevious() is to be called");	
}//esle if
//  }
}//stateChangedn2() 
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
  goDim("form1","update");	
  document.form1.update.disabled=true;
  var f = document.getElementById("form1");
  /*var inputs = f.getElementsByTagName("input");
  var i; 
  for(i = 0; i < inputs.length; i++)
  { 
    if((inputs[i].type=="text")||(inputs[i].type=="password"))
	{ 
     inputs[i].disabled=false;
	 inputs[i].value='';
	 }
   }*/
   document.form1.bsflunit_ucode.disabled=false;
   document.form1.bsflunit_name.disabled=false;
   document.form1.busiid.disabled=false;
   document.form1.huma_id.disabled=false;
   document.form1.unit_email.disabled=false;
   document.form1.unit_phno.disabled=false;
   document.form1.unit_mob.disabled=false;
   //document.getElementById("cal2").style.display='inline';
  
 //alert("hey came to show()");
 document.getElementById("bsflunit_ucode").value =star[x][0];
 document.getElementById("bsflunit_name").value =star[x][1];
 document.getElementById("busiid").value =star[x][2];
 document.getElementById("huma_id").value =star[x][3];
 document.getElementById("unit_email").value =star[x][4];
 document.getElementById("unit_phno").value =star[x][5];
 document.getElementById("unit_mob").value =star[x][6];
 //document.getElementById("pwd2").value =star[x][1];
 //document.getElementById("logtype").value =star[x][2];
 //alert("hey completed the assighing");  
}//show()
function allowupdate()
{
 if((document.form1.update.style.display=="inline") && (document.form1.exct.disabled))
    { 
	document.form1.update.disabled=false;
	  //document.form1.update.focus();
	 }
}//allowupdate()
</script>
<script language="javascript" type="text/javascript">var ssssss="<%=(String)session.getAttribute("userType")%>";</script>
<!-- <script type="text/javascript" src="JS/Atop2.js"></script> -->


<script type="text/javascript">
function queryfunction() 
{ 
//Everything from disable() exceptiong button code,just for primary key enable(replace true by false) and editable(add readOnly=false)
 document.form1.bsflunit_ucode.value='';
 document.form1.bsflunit_name.disabled=true;
 
 document.form1.bsflunit_ucode.disabled=false;//because we allow the user to query using his own value//new code from this line
 document.form1.bsflunit_ucode.readOnly =false;//because it was made readOnly (after retrieving the records),allows to enter
 document.form1.bsflunit_ucode.style.backgroundColor='skyblue';
 document.form1.bsflunit_ucode.focus();
 document.form1.bsflunit_ucode.value="<%=unit_code%>";
 
 
 document.form1.busiid.disabled=false;//because we allow the user to query using his own value//new code from this line
 document.form1.busiid.readOnly =false;//because it was made readOnly (after retrieving the records),allows to enter
 document.form1.busiid.style.backgroundColor='skyblue';
 
 document.form1.huma_id.disabled=false;//because we allow the user to query using his own value//new code from this line
 document.form1.huma_id.readOnly =false;//because it was made readOnly (after retrieving the records),allows to enter
 document.form1.huma_id.style.backgroundColor='skyblue';
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

 document.form1.bsflunit_ucode.value='';
 document.form1.bsflunit_ucode.disabled=true;
 document.form1.bsflunit_ucode.style.backgroundColor='';
 
 document.form1.bsflunit_name.value='';
 document.form1.bsflunit_name.disabled=true;
 
 document.form1.huma_id.value='';
 document.form1.huma_id.disabled=true;
 document.form1.huma_id.style.backgroundColor='';
 
 document.form1.unit_email.value='';
 document.form1.unit_email.disabled=true;
 
 document.form1.unit_phno.value='';
 document.form1.unit_phno.disabled=true;
 
 document.form1.unit_mob.value='';
 document.form1.unit_mob.disabled=true;
 
 document.form1.busiid.value='';
 document.form1.busiid.disabled=true;
 document.form1.busiid.style.backgroundColor='';
  //---------common (button) code
 document.form1.save.disabled=true;
  document.form1.first.disabled=true;
 document.form1.previous.disabled=true;
 document.form1.next.disabled=true;
 document.form1.last.disabled=true;
 document.form1.new2.disabled=false;
 document.form1.query.disabled=false;
 goDim("form1","clear");
 document.form1.clear.disabled=true;
 document.form1.exct.disabled=true;//from this line new added code for Query functionality
 document.form1.save.style.display='inline';
 document.form1.update.style.display='none';
}//disable()
function enable() 
{//alert("hey inside the enable function");
document.form1.bsflunit_ucode.disabled=false;
document.form1.bsflunit_name.disabled=false;
document.form1.busiid.disabled=false;
document.form1.huma_id.disabled=false;
document.form1.unit_email.disabled=false;
document.form1.unit_phno.disabled=false;
document.form1.unit_mob.disabled=false;
document.form1.bsflunit_ucode.readOnly=false;
 var f = document.getElementById("form1");
//var inputs = f.getElementsByTagName("select");
//-------------common (button) code
 for(var i = 0; i < f.length; i++)
 {
  if(f[i].type != "button")
  {
   f[i].disabled = false; 
  }
 }//enable()
 //alert("hey disabling removed");
 //-------------common (button) code
goDim("form1","new2");
document.form1.new2.disabled=true;
document.form1.query.disabled=true;
document.form1.exct.disabled=true;//from this line newly added code for Query functionality
document.form1.save.style.display='inline';
document.form1.update.style.display='none';
}
</script>
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();">	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script>
<!-- <body onLoad="disable();" style="font-size: 62.5%;"> --><form id="form1" name="form1" method="post">
	<table cellpadding="1" cellspacing="1">
			<tr>
  			  <td width="380" align="left"><b><!-- <a href="masters.jsp">Back</a> --></b><br></td>
			</tr>
		</table>
		<table width="604" height="" align="center">
			<tr>
				<td width="570"><fieldset style="background-color:">
						<legend class="formTitle">BSFL Unit Master </legend>
						<table width="100%" border="0" bgcolor="">
							<tr>
								<td><table width="100%" border="0" bgcolor="">
										<tr bordercolor="#000000">
											<td colspan="2" align="right""><script
													type="text/javascript" src="JS/np.js"></script></td>
										</tr>
										<tr>
											<td><div align="right"></div></td>
											<td><div align="left"></div></td>
										</tr>
										<tr>
											<td><div align="right">Unit Code</div></td>
											<td><div align="left">
													<input name="bsflunit_ucode" type="text" id="bsflunit_ucode"
														size="30" maxlength="35"
														onfocus="addSuggestion('bsflunit_ucode','bsflunit_ucode');"
														onkeyup="allowupdate();"/>
												</div></td>
										</tr>
										<tr>
											<td><div align="right">Unit name</div></td>
											<td><div align="left">
													<input name="bsflunit_name" type="text" id="bsflunit_name"
														size="30" maxlength="35" onKeyUp="allowupdate();"/>
												</div></td>
										</tr>

										<tr>
											<td><div align="right">Region Id</div></td>
											<td><div align="left">
													<!-- <input name="bsflunit_name" type="text" id="bsflunit_name" size="25" maxlength="35" onkeyup="allowupdate();" /> -->
													<input type="text" name="busiid" id="busiid" maxlength="30"
														size="30" onKeyUp="allowupdate();"
														onfocus="addSuggestion('busiid','busiid');"
														class="masterInput" />
												</div></td>
										</tr>

										<tr>
											<td><div align="right">Unit incharge</div></td>
											<!-- <td><div align="left">
													<input name="bsflunit_name" type="text" id="bsflunit_name" size="25" maxlength="35" onkeyup="allowupdate();" />
													<input type="text" name="huma_id" id="huma_id" maxlength="30"
														size="30" onKeyUp="allowupdate();"
														onfocus="addSuggestion('huma_id','huma_id');"
														class="masterInput" />
												</div></td> -->
												
												<td><div align="left">
													<!-- <input name="bsflunit_name" type="text" id="bsflunit_name" size="25" maxlength="35" onkeyup="allowupdate();" /> -->
													<input type="text" name="huma_id" id="huma_id" maxlength="30"
														size="30" onkeyup="allowupdate();"
														onfocus="addSuggestion('Huma_huma_id','huma_id');"
														style="border-color: #0099FF;" />
												</div></td>
												
												
										</tr>
										
										<tr>
											<td><div align="right">Unit EmailID</div></td>
											<td><div align="left">
													<input type="text" name="unit_email" id="unit_email" maxlength="30"
														size="30" onKeyUp="allowupdate();"/>
												</div></td>
										</tr>
										
										<tr>
											<td><div align="right">Unit Phone No</div></td>
											<td><div align="left">
													<input type="text" name="unit_phno" id="unit_phno" maxlength="13"
														size="30" onKeyUp="allowupdate();"/>(Ex:04039162116)
														
												</div></td>
										</tr>
										
										<tr>
											<td><div align="right">Unit Mobile No</div></td>
											<td><div align="left">
													<input type="text" name="unit_mob" id="unit_mob" maxlength="14"
														size="30" onKeyUp="allowupdate();"/>(Ex:919396812884)
												</div></td>
										</tr>

										<tr>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td><div align="right"></div></td>
											<td><div align="left"></div></td>
										</tr>
									</table></td>
							</tr>
							<tr height="">
								<td align="center" height=""><table>
										<tr>
											<td><script type="text/javascript" src="JS/Abuttons.js"></script>
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
												onclick="showEmpn('BSFLUNIT_MSTR',bsflunit_ucode.value,busiid.value,huma_id.value);" /> <br />
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
			<!-- 		
					<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />
					<br /> <br /> <br /> <br /> <br /> <br /> <br /></td>
			 -->
			</tr>
		</table>
		<p align="right"></p>
	</form>

<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
	<!-- <script type="text/javascript" src="JS/down1.js"></script> -->
	<!-- <script type="text/javascript" src="JS/down1.js"></script>-->

</body> 
<%

	
    }//authorised acess else
%>
</html>