<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock entry master</title>

<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>
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

function showEmp(decide,key1,key2)
{//alert("hety inside the showEmp");
 sessioncheck();
xmlHttp=GetXmlHttpObject();

 if (xmlHttp==null)
  {
  return;
  }
 var url="getuserupdate";
 key1=key1.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
 key2=key2.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
  url=url+"?decide="+decide+"&key1="+key1+"&key2="+key2;
 
//alert("hey url is made");
 if(key1.length>=3)
 {
 xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
 xmlHttp.send(null);//alert("the ajax request made ");
 	var showdata = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
	//alert("hey goy the show data is:"+showdata);
	showdata=showdata.replace(/::::::$/,"");//removes the "::::::"  from the end of the "data" array
	
	var strar = showdata.split("//////");
	
	 if(strar=='')//if the entered busi_id is not exist in the DB then this if code runs
	 {
		 //alert("The returned object is null--Please Select currect BusinessLine Id");
		  document.getElementById("sta_name").value ='';
		 //document.getElementById("comp_id").value =" ";
	 }
	 else if(strar.length>0)
	 {
		document.form1.sta_name.value=strar[0];
		
		
	 }//else
	}//if(key1>=4)
	else //if(key1<4)
	{
	document.getElementById("sta_name").value ='';
	
	return false;
	}
}//showEmp(decide,key1,key2)

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
}//getXmlObject()
</script>

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
	var JULIAN = 1;
	var year = 0;
	var month = 0;
	var day = 0;
	var julianday = 0.0;
	var modifiedjulianday = 0.0;

	function ipart(r) {
		return Math.round(r - 0.5);
	}
	function getJulianDay() {
		return this.julianday;
	}
	function getModifiedJulianDay() {
		return this.modifiedjulianday;
	}

	function dateFormat(f) {
		df = f;
	}

	function parseDate(dateval) {
		// split is a Javascript 1.2 function
		var dary = dateval.split("-");

		var y = fix2DigitDate(dary[2]);
		m = dary[1];
		d = dary[0];

		var calendar;
		if (y > 1582)
			calendar = GREGORIAN;
		else if (y < 1582)
			calendar = JULIAN;
		else if (m < 10 | (m == 10 && d < 15))
			calendar = JULIAN;
		else
			calendar = GREGORIAN;
		i = new CustomDate(y, m, d, calendar);
		return i;
	}// dateval

	function fix2DigitDate(dateval) {
		var date = dateval + "" ;// dateval must be a string
		if (date.length < 3) {
			date = 1900 + date * 1.0;
			date = date + "" ;// to string
		}
		return date;
	}

	function CustomDate(yr, mo, da, type) {
		year = yr * 1.0; // convert string to float

		month = mo * 1.0;
		day = da * 1.0;
		if (year == 1582 && month == 10 && day > 4 && day < 15) {
			alert("Invalid date: 15 Oct immediately followed 4 Oct in the year 1582");
			document.datainput.dateerr.value = "??";
			return;
		}
		if (year < 0)
			year = year + 1; // B.C. date correction
		var a = ipart((14 - month) / 12);
		var y = year + 4800 - a;
		var m = month + 12 * a - 3;
		if (type == GREGORIAN) {
			julianday = day + ipart((153 * m + 2) / 5) + y * 365 + ipart(y / 4)
					- ipart(y / 100) + ipart(y / 400) - 32045;
		}
		if (type == JULIAN) {
			julianday = day + ipart((153 * m + 2) / 5) + y * 365 + ipart(y / 4)
					- 32083;
		}
		modifiedjulianday = julianday - 2400000.5; // Zero at 17 Nov 1858 00:00:00
													// UTC
		this.getJulianDay = getJulianDay();
		this.getModifiedJulianDay = getModifiedJulianDay();
	}

	function DaysArray(n) {
		for ( var i = 1; i <= n; i++) {
			this[i] = 31;
			if (i == 4 || i == 6 || i == 9 || i == 11) {
				this[i] = 30;
			}
			if (i == 2) {
				this[i] = 29;
			}
		}
		return this;
	}
	var date = new RegExp("[0-9]");
	var currentTime = new Date();
	var mm = currentTime.getMonth() + 1;
	var dd = currentTime.getDate();
	var yyyy = currentTime.getFullYear();
	var sysdate = dd + "-" + mm + "-" + yyyy;
	sysdate = sysdate.split("-");
	var sysdate = new Date(sysdate[2], sysdate[1] - 1, sysdate[0]);
		var stid=document.getElementsByName("st_id")[0].value;
	
	   //alert("language"+lan);
		if(stid=="")
		{
	      alert("Please select stationary ID");
	      document.getElementsByName("st_id")[0].focus();
	      document.getElementsByName("st_id")[0].style.background="#fffacd";
	      return false;
	   }
	   var stname=document.form1.sta_name.value;
	   if(stname == "")
	   {
		   alert("please select stationary stationary name")
	    document.form1.st_id.focus();
	    return false;
	   }
	   if (!date.test(document.form1.date_os.value)) {           //code for date validation
			alert("Please select the date of stock");
			document.form1.date_os.focus();
			return false;
		}// alert("hi1");
		if (document.form1.date_os.value.replace(/^\s+/, '').replace(/\s+$/, '') != "") {
			var vnvdate = (document.form1.date_os.value).split("-");
			var validformat = /^\d{2}-\d{2}-\d{4}$/;
			//var returnval = false;// validformat
			if (!validformat.test(document.form1.date_os.value)) {
				alert("Please enter the date of stock in correct format");
				document.form1.date_os.focus();
				return false;
			}// if date format checking
			var dayfield = vnvdate[0];
			var monthfield = vnvdate[1];
			var yearfield = vnvdate[2];
			var dayobj = new Date(yearfield, monthfield - 1, dayfield);
			if ((dayobj.getMonth() + 1 != monthfield)
					|| (dayobj.getDate() != dayfield)
					|| (dayobj.getFullYear() != yearfield)) {
				alert("Invalid month or date found in date of stock");
				document.form1.date_os.value="";
				document.form1.date_os.focus();
				return false;
			}
			// Date comparision code starts here
			var date_of_s = (document.form1.date_os.value).split("-");
			var date_of_sto = new Date(date_of_s[2], date_of_s[1] - 1,date_of_s[0]); // alert("hey
																				// to
																				// FRS_date
																				// date
																				// is
																				// ="+FRS_date);
			if (date_of_sto > sysdate) {
				alert("date of entry should not be grater than the System date");
				document.form1.date_os.value="";
				document.form1.date_os.focus();
				return false;
			}
			// Date comparision code ends here
			//code for dat validation ends here
		}
		if (document.getElementsByName("no_stock")[0].value == "") {
			alert("Please enter the number stock");
			document.getElementsByName("no_stock")[0].focus();
			document.getElementsByName("no_stock")[0].style.background = "#fffacd";
			return false;
		}

		if (isNaN(document.getElementsByName("no_stock")[0].value)) {
			alert("no of stock must be a numeric value");
			document.getElementsByName("no_stock")[0].value = "";
			document.getElementsByName("no_stock")[0].focus();
			document.getElementsByName("no_stock")[0].style.background = "#fffacd";
			return false;
		}

	   if (document.getElementsByName("v_purchase")[0].value == "") {
			alert("Please enter the value of purchase");
			document.getElementsByName("v_purchase")[0].focus();
			document.getElementsByName("v_purchase")[0].style.background = "#fffacd";
			return false;
		}

		if (isNaN(document.getElementsByName("v_purchase")[0].value)) {
			alert("value of purchase must be a numeric value");
			document.getElementsByName("v_purchase")[0].value = "";
			document.getElementsByName("v_purchase")[0].focus();
			document.getElementsByName("v_purchase")[0].style.background = "#fffacd";
			return false;
		}

  
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
			  program='StockEntry';	
			else  
			   program='StockUpdate'; //alert("hey the program is="+program);	
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

//below code for shortcut keys for menus
//$(document).ready(function() { //alert("hety inside the ready function");
//  var isCtrl = false;
  var isAlt = false;
  var menuDecide=null;
  $(document).keyup(function (e) {
//if(e.which == 17) isCtrl=false;
//if(e.which == 18) isAlt=false;
if(isAlt == true) isAlt=false;
}).keydown(function (e) {
    if(e.which == 17) isCtrl=true;//Ctrl=17 //alert("hey the alt is pressed="+	e.which);
	if(e.which == 18) isAlt=true;//Alt=18
    else if(e.which == 77 && isAlt == true) menuDecide="Masters";//for Master menu shortcut=Alt+m
    else if(e.which == 78 && isAlt == true) menuDecide="Operations";//for Operations shortcut=Alt+n
	else if(e.which == 82 && isAlt == true) menuDecide="Reports";//for Reports menu shortcut=Alt+r
	else if(e.which == 76 && isAlt == true) menuDecide="Logoff";//for Logoff menu shortcut=Alt+l
	if(menuDecide!=null)
	 {
	 	document.getElementById("a"+menuDecide).focus();
		showmenu(menuDecide);//alert('Keyboard shortcuts + JQuery are even more cool!');
		menuDecide=null;
		//isAlt=false;
		}
	if(e.which == 27) //for all menus hide shortcut=Esc
        {
		hidemenu("Masters");
		hidemenu("Operations");
		hidemenu("Reports");
		hidemenu("Logoff");		
		//isAlt=false;
		//alert('Keyboard shortcuts + JQuery are even more cool!');
		}	
//	return true;
 
});//keydown  
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
	//alert(star);
	if(star=='')
	 { 
		 alert("No records found");
		 document.form1.previous.disabled=true;
         document.form1.next.disabled=true;
		 document.getElementById("st_id").value ='';
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
     document.getElementById("st_id").readOnly =true;
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
 // document.getElementById("st_id").disabled=true;
  document.getElementById("st_id").readOnly =true;
  
   //document.getElementById("cal").style.display='inline'; 
   document.getElementById("st_id").value =star[x][0];
  document.getElementById("sta_name").value =star[x][1];
  document.getElementById("date_os").value=star[x][2];
  document.getElementById("no_stock").value=star[x][3];
  document.getElementById("v_purchase").value=star[x][4];
  
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
   document.form1.st_id.disabled=false;
   document.form1.st_id.readOnly=false;
   document.form1.st_id.style.backgroundColor='skyblue';
   document.form1.st_id.focus();
 //document.form1.comp_id.disabled=false;//3 lines, newly added code for the Query functionality
 //document.form1.comp_id.readOnly=false;
 //document.form1.comp_id.style.backgroundColor='skyblue';
 //document.form1.comp_id.focus();
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
 document.form1.st_id.value='';
 document.form1.st_id.disabled=true;
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
 
goDim("form1","new2");
document.form1.new2.disabled=true;
document.form1.query.disabled=true;
document.form1.exct.disabled=true;//from this line newly added code for Query functionality
document.form1.save.style.display='inline';
document.form1.update.style.display='none';
}//enable()
</script>
<script language="javascript" type="text/javascript">var ssssss="<%=(String)session.getAttribute("userType")%>";</script>

<!---------------- Code for FRS theme FRS_Theme_top.js starts here  ------------------------->
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" /> 
<link rel="stylesheet" href="theme/blue.css" type="text/css" /> 
<style type="text/css"> 
body { background-color: #d0e4fe; } h1 { color: orange; text-align: center; } p { font-family: "Times New Roman"; font-size: 20px; }

a {	text-decoration:none;	color:black;}a:hover {	text-decoration:none;	color:#999;	}
.ui-autocomplete        {            position:absolute;            cursor:default;            z-index:4000 !important             }
</style> 
<script type="text/javascript" src="JS/jquery.min.js"></script> 
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>   
<script type="text/javascript">
function goLite(FRM,BTN){		   window.document.forms[FRM].elements[BTN].style.color =  "#ffffff";	   window.document.forms[FRM].elements[BTN].style.backgroundImage="url(images/back04.gif)";}
function goDim(FRM,BTN){   window.document.forms[FRM].elements[BTN].style.color = "";      window.document.forms[FRM].elements[BTN].style.backgroundImage="url(images/back03.gif)";}
function goLite2(BTN){BTN.style.color = "#ffffff";BTN.style.backgroundImage="url(images/back04.gif)";}
function goDim2(BTN){BTN.style.color = "";BTN.style.backgroundImage="url(images/back03.gif)";} 
function showmenu(elmnt){document.getElementById(elmnt).style.visibility="visible";}
function hidemenu(elmnt){document.getElementById(elmnt).style.visibility="hidden";}
function goLite2(BTN)
{
BTN.style.color = "#ffffff";
BTN.style.backgroundImage="url(images/back04.gif)";
}
function goDim2(BTN)
{
BTN.style.color = "";
BTN.style.backgroundImage="url(images/back03.gif)";
}
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
<!---------------- Code for FRS theme FRS_Theme_top.js ends here  ------------------------->
</head>
<body bottommargin="100%" marginheight="100%" rightmargin="100%"
	leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();">
	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script>
	<script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script>
	<script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script>
<!---------------- Code for FRS theme FRS_Theme_top2.js starts here  ------------------------->	
	<table width="100%" cellspacing="0" cellpadding="0" border="0"
		bordercolor="block">
		<tbody>
			<tr>
				<td valign="top">
					<table class="header" cellspacing="0" cellpadding="0" border="0"
						bordercolor="block" width="100%" height="100%">
						<tbody>
							<tr>

							</tr>
							<tr>
								<td>
									<h4>
										<b><font color="white">Bhartiya Samruddhi Finance
												Limited</font></b>
									</h4>
									<br> <strong><font color="white">Field Reporting
											System</font></strong>
								</td>
								
								<td align="right" valign="bottom">
									<b>
									<font color="white" style="font-size: 11px"> 
                                      <i><%=((HttpServletRequest) request).getSession().getAttribute("username").toString() %></i> Logged in from <i><%=((HttpServletRequest) request).getSession().getAttribute("unitname").toString() %></i> Unit
                                    </font>
                                   </b>
                                   
								</td>
								<td align="right" width="10">
								</td>
								<td width="90"><img src="images/basix-logo.gif" width="75"
									height="100" align="middle" />
									             		
									</td>
									
									<!-- By Rajesh for role and user name -->
									<!--
									<td width="200">
									<b><font color="white">User Name : 
									<%=((HttpServletRequest) request).getSession().getAttribute("username").toString() %>
									
									</font></b>
									<br/> <br/> <br/>
									<b><font color="white">Logged in as : 
									<%=((HttpServletRequest) request).getSession().getAttribute("userrole").toString().toUpperCase() %>
									</font></b>
									</td>
									
									
									  -->
							</tr>
						</tbody>
					</table>
				</td>
			</tr> 
			<tr>
				<td valign="top" height="5" align="right" class="nav_head"></td>
			</tr> 
			<tr class="content-area" valign="top">
				<td>
					
<!---------------- Code for FRS theme FRS_Theme_top2.js ends here  ------------------------->
<!---------------- Code for FRS menu.js starts here  ------------------------->
										<!-- drop down code start here-->
										<table width="100%">
											<tr>
												<!-- style="background:url(images/menu08.jpg) repeat scroll 0 0 transparent "> -->
												<td width="14%" onMouseOver="showmenu('Masters')"
													onMouseOut="hidemenu('Masters')" class="bg_menu">
													<div align="center">
														<a href="Chome.jsp" class="style21" id="aMasters">
															<u>M</u>asters</a><br />
													</div>
													<table class="menu" id="Masters" width="182" border="0"
														bordercolor="blue">
   
   <tr><td class="menu"><a href="stationarymaster.jsp">Stationary master</a></td></tr>
    <tr><td class="menu"><a href="stockentry.jsp">Stock entry master</a></td></tr>
    
   <tr><td class="menu"><a href="mchange.jsp">Change pwd</a></td></tr>

													</table>
												</td>
												
												<td width="14%" onMouseOver="showmenu('Operations')"
													onMouseOut="hidemenu('Operations')"  class="bg_menu">
													<div align="center">
														<a href="Chome.jsp" class="style21" id="aOperations">
															Operatio<u>n</u>s
														</a><br />
													</div>
													<table class="menu" id="Operations" width="182" border="0"
														bordercolor="blue">
	   <!--<tr><td class="menu"><a href="lsrrecovery.jsp">LSR Field Entry</a></td></tr>
    <tr><td class="menu"><a href="DiffActivites.jsp">Customer Connect</a></td></tr>  -->
   <tr><td class="menu"><a href="unitindentmaster.jsp">Unit Indent</a></td></tr>
   <tr><td class="menu"><a href="issue1.jsp">Issue Of Indent</a></td></tr>
   <tr><td class="menu"><a href="issuedstock.jsp">Issued Stock</a></td></tr>
   
												</table>
												</td>
											<!-- <td width="17%" onMouseOver="showmenu('Reports')"
													onMouseOut="hidemenu('Reports')" class="bg_menu">
													<div align="center">
														<a href="Chome.jsp" class="style21" id="aReports">
															<u>R</u>eports</a><br />
													</div>
													<table class="menu" id="Reports" width="220" border="0"
														bordercolor="blue">
														<tr>
															<td class="menu"><a href="Unitindentrep.jsp">Indent Report</a></td>
														</tr>
														<tr>
															<td class="menu"><a href="Stationaryindentrep.jsp">Stationary Indent Report</a></td>
														</tr>
														<tr>
															<td class="menu"><a href="Area Day wise Summary Rep.jsp">Region
																	Day Wise Detailed Report</a></td>
														</tr>
														<tr>
															<td class="menu"><a href="Unit Day wise Summary Rep.jsp">Unit
																	Day Wise Detailed Report</a></td>
														</tr>
													</table>
												</td> -->
												<td width="14%" onMouseOver="showmenu('Logoff')"
													onMouseOut="hidemenu('Logoff')" class="bg_menu">
													<div align="center">
														<a href="Chome.jsp" class="style21" id="aLogoff">
															<u>L</u>ogoff</a><br />
													</div>
													<table class="menu" id="Logoff" width="182" border="0"
														bordercolor="blue">
														<tr>
															<td class="menu"><a href="Modules.jsp">Switch Module</a></td>
														</tr>
														<tr>
															<td class="menu"><a href="logout.jsp">Logout</a></td>
														</tr>
													</table>
												</td>
												
												<!-- Newly added by Rajesh-->
												
												
												 
												<td width="41%"  class="bg_menu style21" align="right">Indent Management Module</td>
												
												<!-- <td width="41%"  class="bg_menu"></td> -->
											</tr>
										</table> <!-- drop down code ended here-->

									</td></tr>
<tr class="content-area">
				<td valign="middle" height="350" align="center">
<!------ Code for FRS menu.js ends here  -------------->

<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if((role.equals("admin"))||(role.equals("audit")))// ||role.equals("areahead") || role.equals("unithead"))
{ %>

<form id="form1" name="form1" method="post">
		<!--<input type="text" size="5" maxlength="5"  name="txt" style="border-color:#FF0000"  />-->
         <table cellpadding="1" cellspacing="1">
			<tr>
  			 <!--  <td width="380" align="left"><b><!-- <a href="masters.jsp">Back</a> </b></td>--> <br> 
  			<!--  <td width="380" align="left"><b><a href=" Ahome.jsp">Home</a></b></td>  -->
			</tr>
		</table>
         
		<p align="right">
					<table width="640" align="center">
				<tr>
					<td width="606" height="250">
						<fieldset style="background-color:">
							<legend class="formTitle"> Stock Entry Master</legend>
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
												<td width="24%" align="right"><div align="right">Stationary
														Id</div></td>
												<td><div align="left">
													<input name="st_id" multiple type="text" id="st_id" 
														size="30" maxlength="35" 
														onkeyup="allowupdate();showEmp('s_id.s_name',st_id.value,'');"
														onfocus="addSuggestion('st_id','st_id');"
														onblur="showEmp('s_id.s_name',st_id.value,'');"
														style="border-color: #0099FF;"/>
												</div></td>
											</tr>
								<tr>
												<td height="24" align="right"><div align="right">Stationary
														Name</div></td>
												<td align="left">
													<div align="left">
														<input name="sta_name" id="sta_name" type="text" 
														readonly=""
														/>
																											
														
															
													</div>
												</td>
											</tr>
											<tr>
												<td height="24" align="right"><div align="right">D
														O S</div></td>
												<td align="left"><div align="left">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="27%"><div align="left">
																		<input type="text" name="date_os" id="date_os"
																			size="10" maxlength="10" onKeyUp="allowupdate();" />
																		<a href="#"
																			onclick="setYears(1947, 2050);
       showCalender(this, 'date_os');">
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
											<tr>
												<td height="24" align="right"><div align="right">No. of Stock
														</div></td>
												<td align="left">
													<div align="left">
														<input name="no_stock" id="no_stock" type="text" onkeyup="allowupdate();" />
																											
														
															
													</div>
												</td>
											</tr>
											<tr>
												<td height="24" align="right"><div align="right">Value of Purchase
														</div></td>
												<td align="left">
													<div align="left">
														<input name="v_purchase" id="v_purchase" type="text" onkeyup="allowupdate();"/>
																											
														
															
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
											<td><script type="text/javascript" src="JS/Bbuttons.js"></script>
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
														onclick="showEmpn('STOCK_ENTRY',st_id.value,'');" /> <br />
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
<!---------------- Code for FRS theme FRS_Theme_bottom.js starts here  ------------------------->


				</td>
			</tr>
			<tr>
				<td valign="bottom" class="footer" height="5"></td>
			</tr>
		</tbody>
	</table>
	<center>
		<h5>
			<b><font color="block">All CopyRights Reserved Basix@2013
			</font></b>
		</h5>
	</center>
<!---------------- Code for FRS theme FRS_Theme_bottom.js ends here  ------------------------->
</body>	
<%-- <%}%> --%>
<%
}//authorised acess else------------------------------------------------------------------------------------
else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to create Stock Entry
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
