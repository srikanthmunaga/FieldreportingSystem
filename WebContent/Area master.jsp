<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="frs_cls.JdbcConnect" %>
<%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Region master</title>

<% 
////System.out.println("inside the Region master.jsp and user name="+((HttpServletRequest) request).getSession().getAttribute("username"));
////System.out.println("inside the Region master.jsp and user role="+((HttpServletRequest) request).getSession().getAttribute("userrole"));
//String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
//System.out.println("Role="+role);
if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
 { 
   response.sendRedirect("frslogin.jsp");  // Not logged in, redirect to login page.
	}

else //if(role.equals("admin") || role.equals("audit") ||role.equals("areahead") || role.equals("unithead"))
//if (((HttpServletRequest) request).getSession().getAttribute("user") != null)
   {
   // chain.doFilter(request, response); // Logged in, so just continue.
   ////System.out.println("inside the Scriptlet of Area Master");
   String Region_id = "";
   String huma_id = request.getSession().getAttribute("huma_id").toString();
   Connection con = new JdbcConnect().getConnection();
   PreparedStatement ps = con.prepareStatement("select area_name||'-'||area_id from area_mstr where area_id=(select area_id from bsflunit_mstr where bsflunit_ucode=(select bsflunit_ucode from huma_mstr where huma_id=?))");
   ps.setString(1, huma_id);
   ResultSet rs = ps.executeQuery();
   if(rs.next())
   {
   Region_id = rs.getString(1);
   } 
   //System.out.println("Huma_id="+huma_id);
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
	var patt1=new RegExp("[A-Za-z]");//var patt1=new RegExp("^[A-Za-z]+[0-9]*[ A-Za-z0-9]*$");
    var num=new RegExp("[0-9]");
    var reason=new RegExp("(\/\/\/\/\/\/|::::::)");

  var compid=new RegExp("C[0-9]{3}");
  if(document.form1.comp_id.value=='')
  {//alert("hey the save is inline and the comp_id is=''");
   alert("Please enter the Company Id");
   document.form1.comp_id.focus();
   return false;
  }
  else//if(document.form1.comp_id.value!='')
  { //alert("hey inside the if: means the comp_id is !=''");
   var id=document.form1.comp_id.value.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
       id=id.substr(0,4);
	   //alert("hey the id is="+id);
   /* if(!compid.test(document.form1.comp_id.value))
     {
       alert("Please enter the Company Id correctly");
	   document.form1.comp_id.focus();
	   return false;
	 } */
   }//else//if(document.getElementById("comp_id").value!='')
   var busiid=new RegExp("B[0-9]{3}"); //(busiid.test(document.form1.area_name.value))
   if((!patt1.test(document.form1.area_name.value)) || (reason.test(document.form1.area_name.value)) || (busiid.test(document.form1.area_name.value)))
    {
      alert("please enter Region name correctly");
      document.form1.area_name.focus();
      return false;
    }//busis_name
   if(document.form1.area_remarks.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
   if((!patt1.test(document.form1.area_remarks.value)) || (reason.test(document.form1.area_remarks.value)))
    {
      alert("please enter the Remarks correctly, if entering");
      document.form1.area_remarks.focus();
      return false;
    }//busis_remarks
    		   var lan=document.getElementsByName("area_lang")[0].value;
		   //alert("language"+lan);
			if(lan=="")
			{
		      alert("Please select Languagge");
		      document.getElementsByName("area_lang")[0].focus();
		      document.getElementsByName("area_lang")[0].style.background="#fffacd";
		      return false;
		   }
    
    var patt10=new RegExp("[A-Za-z0-9]");
	if(!patt10.test(document.getElementsByName("huma_id")[0].value))   
	{
	  alert("Please enter the Emp Id");
	  document.getElementsByName("huma_id")[0].focus();
	  document.getElementsByName("huma_id")[0].style.background="#fffacd";
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
			  var program='sbm';	
			else  
			   var program='ubm'; //alert("hey the program is="+program);	
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
		 document.getElementById("area_id").value ='';
	 }
	 else if(star.length>=0)
	{ 
	   for(var k=0; k<star.length; k++)
	  star[k] = star[k].split("::::::"); 
	 //i=star.length;
	 //document.form1.previous.disabled=false;
     //document.form1.next.disabled=false;
		goDim("form1","exct");
	   document.getElementById("msg").style.visibility='hidden';
	   document.getElementById("exct").disabled=true;
     document.getElementById("area_id").readOnly =true;
	 nextprevious("first"); 
	}     //esle
 // } 
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
  /*
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
  document.form1.area_remarks.disabled=false; 
  document.form1.area_strategy.disabled=false; 
  *///document.form1.area_type1.disabled=false;
	 //document.getElementById("comp_id1").value =star[x][0];
 //alert("comp_id="+star[x][0]+" area_id="+star[x][1]+" area_name="+star[x][2]+" area_remarks="+star[x][3]+" area_stragegy="+star[x][4]+" area_lang="+star[x][5]+" huma_id="+star[x][6]);
 document.getElementById("comp_id").value =star[x][0];
 document.getElementById("area_id").value =star[x][1];
 document.getElementById("area_name").value =star[x][2];
 document.getElementById("area_remarks").value =star[x][3];
 document.getElementById("area_strategy").value =star[x][4];
 document.getElementById("area_lang").value =star[x][5];
 document.getElementById("huma_id").value =star[x][6];
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
 document.form1.area_remarks.value='';
 document.form1.area_remarks.disabled=true;
 document.form1.area_strategy.value='';
 document.form1.area_strategy.disabled=true;
 document.form1.area_id.disabled=false;//3 lines, newly added code for the Query functionality
 document.form1.area_id.readOnly=false;
 document.form1.area_id.style.backgroundColor='skyblue';
 document.form1.area_id.focus();
 document.form1.area_id.value ="<%=Region_id%>";
 document.form1.huma_id.disabled=false;//3 lines, newly added code for the Query functionality
 //document.form1.huma_id.readOnly=false;
 document.form1.huma_id.style.backgroundColor='skyblue';
 addSuggestion('Area_huma_id','huma_id');
 document.form1.area_lang.disabled=false;
 document.form1.area_lang.style.backgroundColor='skyblue';
 
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
 document.form1.area_remarks.value='';
 document.form1.area_remarks.disabled=true;
 document.form1.area_strategy.value='';
 document.form1.area_strategy.disabled=true;
 
 document.form1.area_lang.value='';
 document.form1.area_lang.disabled=true;
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
 document.form1.area_id.readOnly=true;//if it was made editable in queryfunction
 //starting automatic id generation code when click on new button-----------------------------------------------------------------
   sessioncheck();
 //  onfocus=addSuggestion('Huma_huma_id','huma_id');
xmlHttp=GetXmlHttpObject();
 //alert("hey got the boject="+xmlHttp);
   if(xmlHttp==null)
  	  return;
    var url="getuserupdate";
    url=url+"?decide=area_id&key1=&key2=";
    xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
    xmlHttp.send(null);//alert("the ajax request made ");
	var id = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,'');//alert("hey goy the show data is:"+id);
	document.getElementById("area_id").value=id;//alert("hey the assignment to the area_id field with its value is over");
 //starting automatic id generation code when click on new button----------------------------------------------------------------- 
 //-------------common (buttons) code
goDim("form1","new2");
document.form1.new2.disabled=true;
document.form1.query.disabled=true;
document.form1.exct.disabled=true;//from this line newly added code for Query functionality
document.form1.save.style.display='inline';
document.form1.update.style.display='none';
}//enable()
</script>
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();">	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script>
<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if((role.equals("admin"))||(role.equals("audit")))// ||role.equals("areahead") || role.equals("unithead"))
{ %>
<form id="form1" name="form1" method="post">
		<table cellpadding="1" cellspacing="1">
			<tr>
  			 <!--  <td width="380" align="left"><b><!-- <a href="masters.jsp">Back</a> </b></td>--> <br> 
  			 <!-- <td width="380" align="left"><b><a href=" Ahome.jsp">Home</a></b></td> -->
  			  
			</tr>
		</table>
		<p align="right" style="height: 12px; ">
			<table width="656" align="center">
				<tr>
					<td width="622" height="">
						<fieldset style="background-color:">
							<!-- <legend class="style23">Region Master</legend> -->
							<legend class="formTitle">Region Master</legend>
							<table bgcolor="" width="100%" height="91%" border="0"
								align="center" bordercolor="#000000">
								<tr>
									<td colspan="2" align="right"><script
											type="text/javascript" src="JS/np.js"></script></td>
								</tr>
								<tr>
									<td width="22%" height="" align="right" class="style22">Company
										id</td>
									<td width="78%" align="left"><label> <input
											name="comp_id" id="comp_id" type="text" size="30"
											onkeyup="allowupdate();"
											onfocus="addSuggestion('comp_id2','comp_id');" maxlength="55"
											style="border-color: #0099FF;" />
									</label></td>
								</tr>
								<tr>
									<td height="24" class="style22"><div align="right">Region id
											</div></td>
									<td align="left"><input name="area_id" id="area_id"
										type="text" size="30"
										onfocus="addSuggestion('area_id2','area_id');" maxlength="30"
										style="border-color: #0099FF;" readonly="" /></td>
								</tr>
								<tr>
									<td height="24"><div align="right">Region Name
											</div></td>
									<td align="left"><input type="text" name="area_name"
										id="area_name" maxlength="30" size="30"
										onkeyup="allowupdate();" onfocus="addSuggestion('area_id2','area_name');"/>
								   </td>

								</tr>

								<tr>
									<td height="24"><div align="right">Region Language
											</div></td>
									<td align="left"><select name="area_lang" id="area_lang"> 
									<option value="">Select Language</option>
									<option value="H">Hindi</option>
									<option value="O">Odia</option>
									<option value="T">Telugu</option>
									</select>
								   </td>

								</tr>
								<tr>
									<td height="37" class="style22"><div align="right">Remarks</div></td>
									<td align="left"><textarea name="area_remarks"
											id="area_remarks" cols="24" rows="2"
											onkeyup="EnforceMaximumLength(this,100); allowupdate();"
											onblur="EnforceMaximumLength(this,100);"></textarea></td>
								</tr>
								<tr style="visibility:hidden;">
									<td height="" class="style22"><div align="right">SBU
											Strategy</div></td>
									<td><div align="left">
											<select name="area_strategy" id="area_strategy" size="1"
												onChange="allowupdate();">
												<option value="" selected="selected"></option>
												<option value="BD">Business Development</option>
												<option value="SF">Support functions</option>
											</select>
										</div></td>
								</tr>
								<!-- New added by Rajesh -->
								<tr>
									<td height="24" class="style22"><div align="right">Region
											InCharge</div></td>
									<!-- <td align="left"><input type="text" name="huma_id" ondblclick="allowupdate();"
										id="huma_id" maxlength="30" size="30" onkeyup="allowupdate();"
										onfocus="addSuggestion('huma_id','huma_id');"
										style="border-color: #0099FF;" /></td> -->
										
										<td align="left">
									<!-- 	<input type="text" name="huma_id"
										id="huma_id" maxlength="30" size="30" onkeyup="allowupdate();"
										onfocus="addSuggestion('Huma_huma_id','huma_id');"
										style="border-color: #0099FF;" />   -->
										
										<input type="text" name="huma_id"
										id="huma_id" maxlength="30" size="30" onkeyup="allowupdate();"
										style="border-color: #0099FF;" />
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
												<td><script type="text/javascript"
														src="JS/Abuttons.js"></script> <br />
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
													onclick="showEmpn('area_mstr',area_id.value,huma_id.value,area_lang.value);" /> <br />
													<table id="msg"
														style="visibility: hidden; position: absolute;" align="">
														<tr>
															<td>Execute</td>
														</tr>
													</table></td>
											</tr>
										</table></td>
								</tr>
								<!--  -->
							</table>
						</fieldset>
					</td> <!-- td closed -->
				</tr>  <!-- Tr closed -->
				<tr>
					<td height="">&nbsp;</td>
				</tr>
			</table>
	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>

<%
}//authorised acess else------------------------------------------------------------------------------------
else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to create new Region
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>

<%
}
}
	%>
	
</html>

