<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><title>Stationary Master</title>
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="JS/jquery.min.js"></script>
  <script type="text/javascript" src="JS/jquery-ui.min.js"></script>
  <script type="text/javascript" src="JS/calendar.js"></script>
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
    
  <!--<script type="text/javascript" src="JAVASCRIPT/jquery-1.4.2.min.js"></script>-->

<style>

a {
	text-decoration:none;
	color:blue;
}
a:hover {
	text-decoration:none;
	color:#999;
	
}
.ui-autocountrylete
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
{  
	var reason=new RegExp("(\/\/\/\/\/\/|::::::)");
	var patt1=new RegExp("[0-9]");
	var patt12=new RegExp("[0]");
	var patt2=new RegExp("[A-Za-z]+[0-9]*[A-Za-z0-9]*");
	 var stationary_code=document.getElementsByName("stationary_id")[0].value;
	   //alert("language"+lan);
		if(stationary_code=="")
		{
	      alert("Please select Stationary Id");
	      document.getElementsByName("stationary_id")[0].focus();
	     document.getElementsByName("stationary_id")[0].style.background="#fffacd"; 
	      return false;
	   }
	    if((!patt2.test(document.form1.stationary_name.value))||(reason.test(document.form1.stationary_name.value)))/* || (document.form1.stationary_name.value.length<4)) */
	      {
	      alert("Please enter the Stationary name");
	      document.form1.stationary_name.focus();
	      return false;
	      } 
	    if (isNaN(document.getElementsByName("opening_stock")[0].value)) {
			alert("opening stock must be numeric value");
			document.getElementsByName("opening_stock")[0].value = "";
			document.getElementsByName("opening_stock")[0].focus();
			document.getElementsByName("opening_stock")[0].style.background = "#fffacd";
			return false;
		}
	   if(!patt1.test(document.form1.opening_stock.value)) /* || (document.form1.opening_stock.value.length<3)) */
		  {
	      alert("Please enter the opening stock value");
		  document.form1.opening_stock.focus();
	      return false;
		  }
	 var date=new RegExp("[0-9]");
	   var currentTime = new Date();
	      var mm = currentTime.getMonth() + 1;
	      var dd = currentTime.getDate();
	      var yyyy = currentTime.getFullYear();
	      var sysdate=dd+"-"+mm+"-"+yyyy;
	      sysdate=sysdate.split("-");
	      var sysdate = new Date(sysdate[2], sysdate[1]-1, sysdate[0]); //var date1 = new Date(yr1, mon1, dt1); 
	   //FRS_date validation starts here
	   if(!date.test(document.form1.date_of_entry.value))
	    {
	   	  alert("Please select the date");
	   	  document.form1.date_of_entry.focus();
	   	  return false;
	   	}
	   if(document.form1.date_of_entry.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	   	{
	   	  var vnvdate=(document.form1.date_of_entry.value).split("-");
	      var validformat = /^\d{2}-\d{2}-\d{4}$/;
	   	  var returnval=false;//validformat
	   if(!validformat.test(document.form1.date_of_entry.value))
	   	{
	   	alert("Please enter the date in correct format");
	   	document.form1.date_of_entry.focus();
	   	return false;
	   	}//if date format checking
	   	var dayfield=vnvdate[0];
	   	var monthfield=vnvdate[1];
	   	var yearfield=vnvdate[2];
	   	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	   	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	   	 {
	   		alert("Invalid month or date found in IMS date");
	   		document.form1.date_of_entry.focus();
	   		return false;
	   	  }
	   	//Date comparision code starts here
	   	   var IMS_date=(document.form1.date_of_entry.value).split("-");
	       var IMS_date = new Date(IMS_date[2], IMS_date[1]-1, IMS_date[0]); //alert("hey to FRS_date date is ="+FRS_date);
	   	   if(IMS_date > sysdate)
	     	{
	   		 alert("IMS date should not be grater than the System date");
	   		 document.form1.date_of_entry.focus();
	   		 return false; 
	   	     }
	   	 //Date comparision code ends here
	   	
	    }
	
}//validateForm()
function focuses() { document.form1.stationary_name.focus(); }
//the buttons code save and update buttons code starts here---------------------------------------------------------
//hey the update&save butons(submission) code starts here---------------------------------------------------------
$(document).ready(function(){//alert("hey inside the ready functin of jquery update");
		$("#form1").submit(function(){sessioncheck();//alert("hey inside the submit function");
		  if(document.form1.onSubmit==validateForm()) 
			{ var allElements=$(this).serialize();//alert("hey the validation is done");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			if(document.form1.save.style.display=='inline')
			  var program='sstatm';	
			else  
			   var program='ustatm'; //alert("hey the program is="+program);	
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
 // alert(url);

 xmlHttp.onreadystatechange=stateChangedn;
 xmlHttp.open("GET",url,true);
 xmlHttp.send(null);
}//showEmpn(decide,key1,key2)
var star;//i;//global variable
var x=0;
function stateChangedn() 
{ //alert("hey inside the stateChangedn");
  if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
  { x=0;
	var showdata = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
	showdata=showdata.replace(/\/\/\/\/\/\/$/,"");//removes the "//////"  from the end of the "data" array
	star=showdata.split("//////"); //alert("got the data to prgm");
	if(star=='')
	 {  
		 alert("No records found");
		 document.form1.previous.disabled=true;
         document.form1.next.disabled=true;
		 document.getElementById("stationary_id").value ='';
	 }
	else if(star.length>=0)
	{ 
	  for(var k=0; k<star.length; k++)
	  star[k] = star[k].split("::::::");
	  //alert("hey got the object="+star); //i=star.length;
	 document.form1.previous.disabled=false;
     document.form1.next.disabled=false;
	 goDim("form1","exct");
	 document.getElementById("msg").style.visibility='hidden';
	 document.getElementById("exct").disabled=true;
     document.getElementById("stationary_id").readOnly =true;//alert("hey going to call the nextprevious method");
	 nextprevious("first");
	 //alert("hey end of the next previous method");
	}//esle
  } 
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
{ //alert("hey inside the show method");
  enable();//from here to 3 lines of code is newly added to automate enable all the fields
  document.form1.save.style.display='none';//newly added code
  document.form1.update.style.display='inline';//newly added code
  goDim("form1","update");
  document.form1.update.disabled=true;
  var f = document.getElementById("form1");
  var inputs = f.getElementsByTagName("input");
  for(var i = 0; i < inputs.length; i++)
  { 
    if(inputs[i].type=="text")
	 {
       if(inputs[i].style.display!="none")//new added one
        inputs[i].value='';//new added one
       inputs[i].disabled=false;
	  }
   }
   //alert("hey going to assign the values to the fields");
  document.getElementById("stationary_id").value =star[x][0];
  document.getElementById("stationary_name").value =star[x][1];
  document.getElementById("opening_stock").value =star[x][2];
  document.getElementById("date_of_entry").value =star[x][3];
  
  //alert("hey assignment is completed for the fields");
}//show()
function allowupdate()
{
 if((document.form1.update.style.display=="inline")&& (document.form1.exct.disabled))
    { document.form1.update.disabled=false;
	  //document.form1.update.focus();
	 }
}//allowupdate()
</script>
<script type="text/javascript">
function queryfunction() 
{ 
//Everything from disable() exceptiong button code,just for primary key enable(replace true by false) and editable(add readOnly=false)
 var f = document.getElementById("form1");
  var inputs = f.getElementsByTagName("input");
  var i; 
  for(i = 0; i < inputs.length; i++)
  { 
    if(inputs[i].type=="text")
	 {
       if(inputs[i].style.display!="none")//new added one
    inputs[i].value='';//new added one
	inputs[i].style.backgroundColor='';//new added one to remove any background color
    inputs[i].disabled=true;
	  }
   }
 document.form1.stationary_id.disabled=false;//3 lines, newly added code for the Query functionality
 document.form1.stationary_id.readOnly=false;
 document.form1.stationary_id.style.backgroundColor='skyblue';
 document.form1.stationary_id.focus();
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
  var f = document.getElementById("form1");
  var inputs = f.getElementsByTagName("input");
  var i; 
  for(i = 0; i < inputs.length; i++)
  { 
    if(inputs[i].type=="text")
	 {
       if(inputs[i].style.display!="none")//new added one
    inputs[i].value='';//new added one
	inputs[i].style.backgroundColor='';//new added one to remove any background color
    inputs[i].disabled=true;
	  }
   }
//----------Common (buttons) code
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
var f = document.getElementById("form1");
for(var i = 0; i < f.length; i++)
 {
  if(f[i].type != "button")
  {
   f[i].disabled = false; 
   f[i].style.backgroundColor='';//new added one to remove any background color
  }
 }
 document.form1.stationary_id.readOnly=true;//if it was made editable in queryfunction
//starting automatic id generation code when click on new button-----------------------------------------------------------------
   sessioncheck();
xmlHttp=GetXmlHttpObject();
 //alert("hey got the boject="+xmlHttp);
   if(xmlHttp==null)
  	  return;
    var url="getuserupdate";
    url=url+"?decide=stationary_id&key1=&key2=";
   // alert(url);
    xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
    xmlHttp.send(null);//alert("the ajax request made ");
	var id = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,'');//alert("hey goy the show data is:"+id);
	document.getElementById("stationary_id").value=id;//alert("hey the assignment to the country_id field with its value is over");
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
<!-- <script type="text/javascript" src="JS/Atop2.js"></script> -->

<!-- <body onload="disable();" style="font-size:62.5%;"> -->
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();">	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Bmenu.js"></script>
<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if(role.equals("admin") || role.equals("audit"))
{ %>

<form id="form1" name="form1"  method="post" > 	<!--<input type="text" size="5" maxlength="5"  name="txt" style="border-color:#FF0000"  />-->
<table cellpadding="1" cellspacing="1">
			<tr>
  			 <!--  <td width="380" align="left"><b><!-- <a href="masters.jsp">Back</a> </b></td>--> <br> 
  			<!--  <td width="380" align="left"><b><a href=" Ahome.jsp">Home</a></b></td>  -->
			</tr>
</table>
<p align="right">
 <table width="606" align="center" >
  <tr><td width="572" height="">   <fieldset style="" ><legend class="formTitle" > Stationary Master</legend> 
<table width="100%" height="91%" border="0" align="center" bordercolor="#000000">
    <tr> <td colspan="2"  align="right"">
    <div align="right">        
		<script type="text/javascript" src="JS/np.js"></script></div></td> 
    </tr>
    <tr>
      <td width="24%" align="right" class="style22">Stationary Id </td>
      <td width="76%" align="left"><label>
          <input name="stationary_id" id="stationary_id"  type="text" size="25" onfocus="addSuggestion('stationary_id','stationary_id');" maxlength="25" style="border-color:#0099FF;" readonly=""/>
          <span class="">(Ex: 0001)</span>
      </label></td>
    </tr>
    <tr>
      <td align="right" class="style22">Stationary Name </td>
      <td align="left"><input name="stationary_name" id="stationary_name" type="text" size="25" maxlength="60" onkeyup="allowupdate();" onfocus="addSuggestion('stationary_id','stationary_name');" style="border-color:#0099FF;"/></td>
    </tr>
    <tr>
      <td align="right" class="style22">Opening Stock</td>
      <td align="left"><input name="opening_stock" id="opening_stock" type="text" size="15" maxlength="20" onkeyup="allowupdate();"/></td>
    </tr>
    <tr>
												<td height="25"  class="style22" align="right"><div align="right">DOE
														</div></td>
												<td align="left"><div align="left">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="27%" style="width: 115px; "><div align="left">
																		<input type="text" name="date_of_entry" id="date_of_entry"
																			size="10" maxlength="10" onKeyUp="allowupdate();" 
																			/>
																		<a href="#"
																			onclick="setYears(1947, 2050);
       showCalender(this, 'date_of_entry');">
																			<img id="cal2" src="images/calender.png"
																			onClick="allowupdate();" />
																		</a>
																	</div></td>
																<td width="73%"><div align="left">
																		(dd-mm-yyyy)<br />
																	</div></td>
															</tr>
														</table>
													</div></td>
											</tr>
											
    <tr >
      <td colspan="2" align="center" valign="bottom"  height="40"><table>
        <tr>
          <td >
          <script type="text/javascript" src="JS/Bbuttons.js"></script>
              <br />
              <table id="msg2" style="visibility:hidden; position:absolute;" align="">
                <tr>
                  <td >Execute</td>
                </tr>
            </table></td>
          <td   width="" align="left" >&nbsp;&nbsp;
              <input type="button" id="exct" name="exct" class="groovybutton" value="&euro;"    title="" onmouseover="goLite(this.form.name,this.name); showmenu('msg');"
   onmouseout="goDim(this.form.name,this.name); hidemenu('msg');" onclick="showEmpn('stationary_mstr',stationary_id.value,'');"/>
              <br />
              <table id="msg" style="visibility:hidden; position:absolute;" align="">
                <tr>
                  <td >Execute</td>
                </tr>
            </table></td>
        </tr>
      </table></td>
    </tr>
	 </table>
  </fieldset></td></tr></table>	
</form>
<!-- <br><br><br><br><br><br><br><br><br><br><br><br><br></td>
</tr>

<script type="text/javascript" src="JS/down1.js"></script>
</body> -->
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
<%
}//authorised acess else------------------------------------------------------------------------------------
else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to create new Stationary
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>

<%
}
}
	%>
	
	<!-- Calender Script  -->

	<table background="images/calender3.gif" id="calenderTable">
		<tbody id="calenderTableHead">
			<tr>
				<td colspan="4" align="center"><select
					onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
	           this.selectedIndex, false));"
					id="selectMonth">
						<option value="0">Jan</option>
						<option value="1">Feb</option>
						<option value="2">Mar</option>
						<option value="3">Apr</option>
						<option value="4">May</option>
						<option value="5">Jun</option>
						<option value="6">Jul</option>
						<option value="7">Aug</option>
						<option value="8">Sep</option>
						<option value="9">Oct</option>
						<option value="10">Nov</option>
						<option value="11">Dec</option>
				</select></td>
				<td colspan="2" align="center"><select
					onChange="showCalenderBody(createCalender(this.value, 
				document.getElementById('selectMonth').selectedIndex, false));"
					id="selectYear">
				</select></td>
				<td align="center"><a href="#" onClick="closeCalender();"><font
						color="#003333" size="+1">X</font></a></td>
			</tr>
		</tbody>
		<tbody id="calenderTableDays">
			<tr style="">
				<td>Sun</td>
				<td>Mon</td>
				<td>Tue</td>
				<td>Wed</td>
				<td>Thu</td>
				<td>Fri</td>
				<td>Sat</td>
			</tr>
		</tbody>
		<tbody id="calender"></tbody>
	</table>

	<!-- End Calender Script  -->
	
</html>
