<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.awt.geom.Area"%>
<%@page import="frs_cls.JdbcConnect" %>
<%@ page import="java.sql.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Village master</title>
<meta name="description" content="SearchField example"></meta>
<% //-------------------------------------------;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;----
if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
 {
    response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to login page.
	}

else //if (((HttpServletRequest) request).getSession().getAttribute("user") != null)
   {
   	//Added by Rajesh
   	
   	String uname_no=null;//=(String)((HttpServletRequest) request).getSession().getAttribute("uname");
  String username=(String)((HttpServletRequest) request).getSession().getAttribute("username");
  //System.out.println("i am in uh connectvity jsp "+username);
  //String aname=(String)((HttpServletRequest) request).getSession().getAttribute("aname");
  
  String sql="select huma_id from frs_user where username='"+username+"'";
  //String sql="select LOWER(BSFLUNIT_NAME) from BSFLUNIT_MSTR where BSFLUNIT_NAME='"+username+"'";
  System.out.println();
  				String huma_id=null;
  				String areaid=null;
  				String aname=null;
  				String ucode=null;
				Connection con=new JdbcConnect().getConnection();
				Statement svst=con.createStatement();
				ResultSet svrs=svst.executeQuery(sql);
				if(svrs.next())
				{
				huma_id=svrs.getString(1);
				System.out.println("huma_id :"+huma_id);
				}//BSFLUNIT_UCODE


 				String sqlu="select BSFLUNIT_UCODE from HUMA_MSTR where huma_id='"+huma_id+"'";
  				System.out.println();
  			
				
				Statement ust=con.createStatement();
				ResultSet urs=svst.executeQuery(sqlu);
				if(urs.next())
				{
				ucode=urs.getString(1);
				System.out.println("UCODE :"+ucode);	
				
				}//BSFLUNIT_UCODE


 				String sql1="select BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE,AREA_ID from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"+ucode+"'";
  				System.out.println();
  			
				
				Statement st=con.createStatement();
				ResultSet rs=svst.executeQuery(sql1);
				if(rs.next())
				{
				uname_no=rs.getString(1);
				areaid=rs.getString(2);
								System.out.println("UNAME NUMBER :"+uname_no);
								System.out.println("AREA CODE :"+areaid);
				
				}//BSFLUNIT_UCODE
				String sql2="select AREA_NAME from AREA_MSTR where AREA_ID='"+areaid+"'";
				
				Statement ast=con.createStatement();
				ResultSet ars=svst.executeQuery(sql2);
				if(ars.next())
				{
				aname=ars.getString(1);
				System.out.println(areaid);
				
				
				}//BSFLUNIT_UCODE
   	
   	
   	//Completed by Rajesh
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
	var number1=new RegExp("[0-9]");
	var patt2=new RegExp("[A-Za-z]+[0-9]*[A-Za-z0-9]*");//new RegExp("^[A-Za-z]+[0-9]*$");
	
/*    if((!patt1.test(document.form1.vcode.value)) || (document.form1.vcode.value.length<3))
	  {
       alert("Please enter the Village code,Correctly");
	   document.form1.vcode.focus();
      return false;
	  } */
	 var patt10=new RegExp("[A-Za-z0-9]");
	 var patt11=new RegExp("^[A-Za-z0-9]");
	 if(!patt1.test(document.getElementsByName("BSFLUNIT_UCODE")[0].value))   
		{
		  alert("Please enter the value for Unit Belongs To");
		  document.getElementsByName("BSFLUNIT_UCODE")[0].focus();
		  document.getElementsByName("BSFLUNIT_UCODE")[0].style.background="#fffacd";
		  return false;
		}
	//alert("vcode value="+document.getElementById("vcode").value);	
	if(!number1.test(document.getElementById("vcode").value))   
		{
	      alert("Please enter the Village code,Correctly");
		  document.getElementById("vcode").focus();
		  document.getElementById("vcode").style.background="#fffacd";
		  return false;
		}
		

   /*if(document.form1.vcode.value='')
	  {
       alert("Please enter the Village code,Correctly");
	   document.form1.vcode.focus();
       return false;
	  }*/	

	 if((!patt2.test(document.form1.vname.value))||(reason.test(document.form1.vname.value))|| (document.form1.vname.value.length<4))
    {
      alert("Please enter the Village name Correctly,with min 4 digits");
      document.form1.vname.focus();
      return false;
    }

		if(document.form1.DISTRICT.value=='')
	    {
	      alert("Please Enter District correctly");
		  document.form1.DISTRICT.focus();
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
			var program;
			if(document.form1.save.style.display=='inline'){
			//alert("before calling url");
			  program='Svillagem';	}
			else  
			  program='Uvillagem';//alert("hey the program is="+program);	
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
function showEmpn(decide,key1,key2)
{//alert("hety inside the showEmpn");
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
		 document.getElementById("vcode").value ='';
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
     document.getElementById("vcode").readOnly =false;
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
  enable();//from here to 3 lines of code is newly added to automate enable all the fields
  document.form1.save.style.display='none';//newly added code
  document.form1.update.style.display='inline';//newly added code
  goDim("form1","update");	
  document.form1.update.disabled=true;//alert("star[x]="+star[x]);
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
   //document.getElementById("cal2").style.display='inline';
  
 //alert("hey came to show()");
 document.getElementById("vname").value =star[x][0];
 document.getElementById("vcode").value =star[x][1];
 document.getElementById("BSFLUNIT_UCODE").value =star[x][2];
 //document.getElementById("huma_id").value =star[x][3];
 document.getElementById("DISTRICT").value =star[x][3];
 document.getElementById("VILLAGE_SEQID").value =star[x][4];
 //document.getElementById("village_phno").value =star[x][5];
 //document.getElementById("village_mob").value =star[x][6];
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
 document.form1.vcode.disabled=false;//3 lines, newly added code for the Query functionality
 document.form1.vcode.readOnly=false;
 document.form1.vcode.style.backgroundColor='skyblue';
 //document.form1.vcode.focus();
 document.form1.BSFLUNIT_UCODE.disabled=false;//3 lines, newly added code for the Query functionality
 document.form1.BSFLUNIT_UCODE.readOnly=false;
 document.form1.BSFLUNIT_UCODE.style.backgroundColor='skyblue';
 document.form1.BSFLUNIT_UCODE.focus();
// document.form1.BSFLUNIT_UCODE.focus();
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
 document.form1.BSFLUNIT_UCODE.value="<%=uname_no%>";
 <%
 //String unitname=request.getSession().getAttribute("unitname").toString();
 //String unitcode=request.getSession().getAttribute("unitcode").toString();
 %>
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
 //document.form1.a_id.readOnly=true;//if it was made editable in queryfunction
 //alert("just bfr session check2");
 //starting automatic id generation code when click on new button-----------------------------------------------------------------
  
 //starting automatic id generation code when click on new button----------------------------------------------------------------- 
//-------------------common (buttons) code for
goDim("form1","new2");
document.form1.new2.disabled=true;
document.form1.query.disabled=true;
document.form1.exct.disabled=true;//from this line newly added code for Query functionality
document.form1.save.style.display='inline';
document.form1.update.style.display='none';
document.form1.BSFLUNIT_UCODE.value="<%=uname_no%>";
}//enable()
</script>
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();">	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Wmenu.js"></script>
<!-- <body onLoad="disable();" style="font-size: 62.5%;"> -->
<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if(role.equals("admin") || role.equals("audit") ||role.equals("areahead") || role.equals("unithead"))
{ %>

<form id="form1" name="form1" method="post">
							<!--	<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
							
								<td width="280"></td>
							 	<td width="0" align="left"><b><a href="uhChome.jsp">UH CC_HOME</a></b></td>  
							<td width="0" align="left"><b><a href="Chome.jsp">HOME</a></b></td> 
							
							</tr>
							</table>--><br />
	
		<table width="604" height="" align="center">
			<tr>
				<td width="570"><fieldset style="background-color:">
						<legend class="formTitle">Village master </legend>
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
											<td><div align="right">Unit ID</div></td>
											<td><div align="left">
													<!-- <input name="vname" type="text" id="vname" size="25" maxlength="35" onkeyup="allowupdate();" /> -->
													<input type="text" name="BSFLUNIT_UCODE" id="BSFLUNIT_UCODE" maxlength="30"
														size="30" onKeyUp="allowupdate();"
														onfocus="addSuggestion('BSFLUNIT_UCODE_roles','BSFLUNIT_UCODE');"
														class="masterInput" />
												</div></td>
										</tr>

										
										<tr>
											<td><div align="right">Village Code</div></td>
											<td><div align="left">
													<input name="vcode" type="text" id="vcode"
														size="30" maxlength="30"
														onfocus="addSuggestion2('VCODE','vcode',BSFLUNIT_UCODE.value);"
														onkeyup="allowupdate();" />
												</div></td>
										</tr>
										<tr>
											<td><div align="right">Village name</div></td>
											<td><div align="left">
													<input name="vname" type="text" id="vname"
													onfocus="addSuggestion2('VNAME','vname',BSFLUNIT_UCODE.value);"
														size="30" maxlength="30" onKeyUp="allowupdate();" />
												</div></td>
										</tr>
										<tr>
												<input name="VILLAGE_SEQID" type="hidden" id="VILLAGE_SEQID" />
										
										</tr> 
										
										<tr>
											<td><div align="right">District</div></td>
											<td><div align="left">
													<input type="text" name="DISTRICT" id="DISTRICT" maxlength="30"
														size="30" onKeyUp="allowupdate();"
														onfocus="addSuggestion('DISTRICT','DISTRICT');"
														/>
												</div></td>
										</tr>
										
										<!-- <tr>
											<td><div align="right">Village Phone No</div></td>
											<td><div align="left">
													<input type="text" name="village_phno" id="village_phno" maxlength="13"
														size="30" onKeyUp="allowupdate();"
														class="masterInput" />(Ex:04039162116)
												</div></td>
										</tr>
										
										<tr>
											<td><div align="right">Village Mobile No</div></td>
											<td><div align="left">
													<input type="text" name="village_mob" id="village_mob" maxlength="14"
														size="30" onKeyUp="allowupdate();"
														class="masterInput" />(Ex:919396812884)
												</div></td>
										</tr>
 -->
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
											<td><script type="text/javascript" src="JS/Wbuttons.js"></script>
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
												onclick="showEmpn('village_mstr',BSFLUNIT_UCODE.value,vcode.value);" /> <br />
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
	<!-- <script type="text/javascript" src="JS/down1.js"></script>

</body> -->
<%
}//authorised acess else------------------------------------------------------------------------------------
else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to create new Village
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>

<%
}
}
	%>
	
</html>



