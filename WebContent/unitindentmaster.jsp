<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*"%>
<%@page import="frs_cls.JdbcConnect"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Unit Indent</title>
<%
	if (((HttpServletRequest) request).getSession().getAttribute(
			"username") == null) {
		response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to login page.
	}

	else //if (((HttpServletRequest) request).getSession().getAttribute("user") != null)
	{
		// chain.doFilter(request, response); // Logged in, so just continue.
%>
<%
	//getting huma_id to assign in huma_id field automatically in enable() function
		String curhuma_id = (String) ((HttpServletRequest) request)
				.getSession().getAttribute("username");
%>
<%//getting BSFLUNIT_UCODE to assign in BSFLUNIT_UCODE field automatically in enable() function

  String uname_no=null;//=(String)((HttpServletRequest) request).getSession().getAttribute("uname");
  String username=(String)((HttpServletRequest) request).getSession().getAttribute("username");
  //System.out.println("i am in uh connectvity jsp "+username);
  //String aname=(String)((HttpServletRequest) request).getSession().getAttribute("aname");
 
  String sql="select huma_id from frs_user where username='"+username+"'";
  //String sql="select LOWER(BSFLUNIT_NAME) from BSFLUNIT_MSTR where BSFLUNIT_NAME='"+username+"'";
  //System.out.println();
  				String huma_id=null;
  				String areaid=null;
  				//String aname=null;
  	
  				String ucode=null;
  				
				Connection con1=new JdbcConnect().getConnection();
				Statement svst=con1.createStatement();
				ResultSet svrs=svst.executeQuery(sql);
				if(svrs.next())
				{
				huma_id=svrs.getString(1);
				//System.out.println("huma_id :"+huma_id);
				}//BSFLUNIT_UCODE

 				String sqlu="select BSFLUNIT_UCODE from HUMA_MSTR where huma_id='"+huma_id+"'";
  				//System.out.println();
  			
				
				Statement ust=con1.createStatement();
				ResultSet urs=svst.executeQuery(sqlu);
				if(urs.next())
				{
				ucode=urs.getString(1);
				//System.out.println("UCODE :"+ucode);	
				
				}//BSFLUNIT_UCODE


 				String sql1="select BSFLUNIT_NAME from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"+ucode+"'";
  				//System.out.println();
  			
	             			
				Statement st=con1.createStatement();
				ResultSet rs1=svst.executeQuery(sql1);
				if(rs1.next())
				{
				uname_no=rs1.getString(1);
				
				}//BSFLUNIT_UCODE
				//String sql2="select AREA_name from AREA_MSTR where AREA_ID='"+areaid+"'";
				
			//	Statement ast=con.createStatement();
				//ResultSet ars=svst.executeQuery(sql2);
				//if(ars.next())
				//{
				//aname=ars.getString(1);
				//System.out.println(areaid);
				
				
//				}//BSFLUNIT_UCODE
  
/*	if(username.equals("basix"))
	{
	uname_no="";
	aname="";
	}*/
  
%>

<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>

<script type="text/javascript">


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
	
	var strar = showdata.split("::::::");
	 if(strar=='')//if the entered area_id is not exist in the DB then this if code runs
	 {
		 //alert("The returned object is null--Please Select currect Area Id");
		  document.getElementById("unit_name").value ='';
		 //document.getElementById("comp_id").value =" ";
	 }
	 else if(strar.length>0)
	 {
		document.form1.unit_name.value=strar[0];
	 }//else
	}//if(key1>=4)
	else //if(key1<4)
	{
	document.getElementById("unit_name").value ='';
	return false;
	}
}//showEmp(decide,key1,key2)
</script>

<!--<script type="text/javascript" src="JAVASCRIPT/jquery-1.4.2.min.js"></script>-->
<script type="text/javascript">
var j=0,emp2='';
var star2;//,i;//global variable
function showEmps1(decide,obj,emp)
{
//decide=transferedUnit
//obj=this
//emp=this.value

//below three lines code to retrieve current dynamic row number
 var par=obj.parentNode; 
 while(par.nodeName.toLowerCase()!='tr'){   par=par.parentNode;  } 
 //alert("the index of the current row is"+par.rowIndex); 
 var i=par.rowIndex;
 j=i-1;
 //alert("J = "+j);

 sessioncheck();
xmlHttp=GetXmlHttpObject();

 if (xmlHttp==null)
  return;
 var url="getuserupdate";
 emp=emp.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
 //obj=obj.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
  url=url+"?decide="+decide+"&key1="+emp+"&key2=''";
 xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
 xmlHttp.send(null);//alert("the ajax request made ");
 	var showdata = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
	//alert("hey goy the show data is:"+showdata);
	showdata=showdata.replace(/::::::$/,"");//removes the "::::::"  from the end of the "data" array

	var strar = showdata.split("::::::");
	//alert("Strar = "+strar);
	 if(strar=='')//if the entered area_id is not exist in the DB then this if code runs
	 {
		document.getElementsByName("st_name").value='';
	 }
	 else if(strar.length>0)
	 {
	// alert("strar.length>0");
	// alert("Getting the element name");	
	//var transferedunit = document.getElementsByName('transferedunit');
	
	//var currentunit = document.getElementsByName('currentunit');

		var value = strar[0];
		//alert("the value from getuser update is "+value);	
		var sta_name = document.getElementsByName('st_name'); 
		sta_name[j].value = strar[0];
		}
		else
		{
		st_name[j].value = "";
		return false;
		}
	 }//showEmps1(decide,emp,obj)

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
function validateForm()
{
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
	var patt2=new RegExp("[A-Za-z]+[0-9]*[A-Za-z0-9]*");
	//var busiid=new RegExp("[0-9]{3}");
  if(document.form1.bsflunit_ucode.value=='')
  {//alert("hey the save is inline and the area_id is=''");
   alert("Please enter the Unit Id");
   document.form1.bsflunit_ucode.focus();
   return false;
  }
  else//if(document.form1.area_id.value!='')
  { //alert("hey inside the if: means the area_id is !=''");
   var id=document.form1.bsflunit_ucode.value.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
       id=id.substr(0,3);
	   
   }
       //var patt1=new RegExp("[A-Za-z]");
	   //var patt2=new RegExp("^[A-Za-z]+[0-9]*[ A-Za-z0-9]*$");

	  //var huma_id=new RegExp("^[0-9]{4}$");
if (!date.test(document.form1.date_of_indent.value)) {           //code for date validation
			alert("Please select the date of stock");
			document.form1.date_of_indent.focus();
			return false;
		}// alert("hi1");
		if (document.form1.date_of_indent.value.replace(/^\s+/, '').replace(/\s+$/, '') != "") {
			var vnvdate = (document.form1.date_of_indent.value).split("-");
			var validformat = /^\d{2}-\d{2}-\d{4}$/;
			//var returnval = false;// validformat
			if (!validformat.test(document.form1.date_of_indent.value)) {
				alert("Please enter the date of stock in correct format");
				document.form1.date_of_indent.focus();
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
				document.form1.date_of_indent.value="";
				document.form1.date_of_indent.focus();
				return false;
			}
			// Date comparision code starts here
			var date_of_s = (document.form1.date_of_indent.value).split("-");
			var date_of_sto = new Date(date_of_s[2], date_of_s[1] - 1,date_of_s[0]); // alert("hey
																				// to
																				// FRS_date
																				// date
																				// is
																				// ="+FRS_date);
			if (date_of_sto > sysdate) {
				alert("date of entry should not be grater than the System date");
				document.form1.date_of_indent.value="";
				document.form1.date_of_indent.focus();
				return false;
			}
			// Date comparision code ends here
			//code for dat validation ends here
		}
		if(!patt2.test(document.form1.in_by.value))
		   {
			alert("please enter the name correctly");
		    document.getElementsByName("in_by")[0].value="";
		    document.getElementsByName("in_by")[0].focus();
		    document.getElementsByName("in_by")[0].style.background = "#fffacd";
		    return false;
		   }
			if(!patt2.test(document.form1.p_by.value))
			   {
				alert("please enter the prepared by correctly");
			    document.getElementsByName("p_by")[0].value="";
			    document.getElementsByName("p_by")[0].focus();
			    document.getElementsByName("p_by")[0].style.background = "#fffacd";
			    return false;
			   }
    
    var st_id = document.getElementsByName('st_id');
	//var closing_stock1 = document.getElementsByName('closing_stock');
    //var new_stock1 = document.getElementsByName('new_stock');
	var n= st_id.length;
	//alert(n);
    for (var k = 0; k < n; k++)
    {
//village c

//alert("MSR DEBUG VCODE");
  if(st_id[k].value=="")  
   {
	 alert("Please Select stationary id");
	 st_id[k].focus();
 	 return false;
	}
  if (document.getElementsByName("closing_stock")[k].value == "") {
		alert("Please enter the Closing Stock");
		document.getElementsByName("closing_stock")[k].focus();
		document.getElementsByName("closing_stock")[k].style.background = "#fffacd";
		return false;
	}

	if (isNaN(document.getElementsByName("closing_stock")[k].value)) {
		alert("Plz enter only numeric value");
		document.getElementsByName("closing_stock")[k].value = "";
		document.getElementsByName("closing_stock")[k].focus();
		document.getElementsByName("closing_stock")[k].style.background = "#fffacd";
		return false;
	}
	if (document.getElementsByName("new_stock")[k].value == "") {
		alert("Please enter the Requested Stock");
		document.getElementsByName("new_stock")[k].focus();
		document.getElementsByName("new_stock")[k].style.background = "#fffacd";
		return false;
	}

	if (isNaN(document.getElementsByName("new_stock")[k].value)) {
		alert("Plz enter only numeric value");
		document.getElementsByName("new_stock")[k].value = "";
		document.getElementsByName("new_stock")[k].focus();
		document.getElementsByName("new_stock")[k].style.background = "#fffacd";
		return false;
	}


   // if(closing_stock1[k].value=='')  
	 //  {
		// alert("Please enter the Closing Stock");
		// closing_stock1[k].focus();
	 	// return false;
		//}
    //if(new_stock1[k].value=='')  
	  // {
		// alert("Please enter the new stock");
		 //new_stock1[k].focus();
	 	 //return false;
		//}
   }	
	
	}  //For loop
    //Validation Completed for st_id Related Fields
//validateForm()

//function focuses() { document.form1.huma_fname.focus(); }
//function focuses1() { document.form1.huma_address.focus();}
//function EnforceMaximumLength(fld,len) /
//{
// if(fld.value.length > len) { fld.value = fld.value.substr(0,len); document.form1.huma_pin.focus();/*fld.focus();*/ }/
//}

//hey the update&save butons(submission) code starts here---------------------------------------------------------
$(document).ready(function(){//alert("hey inside the ready functin of jquery update");
		$("#form1").submit(function(){sessioncheck();//alert("hey inside the submit function");
		  if(document.form1.onSubmit==validateForm()) 
			{ var allElements=$(this).serialize();//alert("hey the validation is done");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			if(document.form1.save.style.display=='inline')
			  var program='MultipleStock';	
			else  
			   var program='UpdateStationary'; //alert("hey the program is="+program);	
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
	$(".addRow").btnAddRow({rowNumColumn:"rowNumber",maxRow:0});
 
	$(".delRow").btnDelRow();
	});//ready function
//the save& update buttons submission code ends here------------------------------------------------------------
var star;//i;//global variable
var x=0;
function showEmpn(decide,key1,key2,key3)
{//alert("hety inside the showEmpn");
 sessioncheck();
xmlHttp=GetXmlHttpObject();
 if (xmlHttp==null)
  return;
 var url="getuserupdate";
  url=url+"?decide="+decide+"&key1="+key1+"&key2="+key2+"&key3="+key3; //alert("hey made the url");
 //xmlHttp.onreadystatechange=stateChangedn;
 xmlHttp.open("GET",url,false);
 xmlHttp.send(null);
  	x=0;
  	var showdata = xmlHttp.responseText;
  	
	//var showdata = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces
	showdata=showdata.replace(/^\s+/,'').replace(/\s+$/,'');
	showdata=showdata.replace(/\/\/\/\/\/\/$/,"");//removes the "//////"  from the end of the "data" array
	star=showdata.split("//////"); //alert("got the data and after splitting="+star);
	
	if(star==null){
  	alert("You are not Authorised");
  	}
  	if(star=='')
	 {  
		 alert("No records found");
		 document.form1.previous.disabled=true;
         document.form1.next.disabled=true;
		 //document.getElementById("prod_id").value ='';
		 document.getElementById("BSFLUNIT_UCODE").value ='';
		 //document.getElementById("UHLOG_DATE").value ='';
	 }
	else if(star.length>=0)
	{ 
	  for(var k=0; k<star.length; k++)
	  star[k] = star[k].split("::::::");
	 //alert("hey the got the object is :"+star);
	 document.form1.previous.disabled=false;
     document.form1.next.disabled=false;
	 goDim("form1","exct");
	 document.getElementById("msg").style.visibility='hidden';
	 document.getElementById("exct").disabled=true;
     //document.getElementById("VCODE_id").readOnly =true;
	 nextprevious("first"); 
	}//esle if
}//showEmpn(decide,key1,key2,key3)
var x1=0;
function nextprevious(np)
{
 if(np=="first") 
 { //deleteRow('tableID');
  x1=0; x=0;//c2[x1];//alert("hey you are in the nextprevious()");
  document.form1.first.disabled=false;
  document.form1.previous.disabled=false;
  document.form1.next.disabled=false;
  document.form1.last.disabled=false;
  show();
  }	
 else if(np=="previous")
  {
   //deleteRow('tableID');
   x1=x1-1;  x=x-c2[x1]; 
   document.form1.next.disabled=false;
   document.form1.last.disabled=false;
   show();
   }	
 else if(np=="next")
  {
   //deleteRow('tableID');
   x=x+c2[x1]; x1=x1+1; //(or)x1=x1+1; x=x+c2[x1-1];  
   document.form1.previous.disabled=false;
   document.form1.first.disabled=false;
   show();
   }//alert("hey the x1="+x1);
 else if(np=="last")
  {//deleteRow('tableID');
   x1=x2-1; x=star.length-c2[x1]; 
   document.form1.previous.disabled=false;
   document.form1.first.disabled=false;
   show();
   }	
 if(x1<=0)
   { goDim("form1","previous"); document.form1.previous.disabled=true;     
     goDim("form1","first"); document.form1.first.disabled=true;     
    }
 if(x1>=x2-1)
   { goDim("form1","next"); document.form1.next.disabled=true;  
     goDim("form1","last"); document.form1.last.disabled=true;  
   }
}//nextprevious(np)
 var x3=0,x2=1;
 var c=1,c2=[],x22=1;//because we need to include the compared word also
function show()
{ 
  enable();//from here to 3 lines of code is newly added to automate enable all the fields
  document.form1.save.style.display='none';//newly added code
  document.form1.update.style.display='inline';//newly added code
  goDim("form1","update");	
  document.form1.update.disabled=true;
  //alert("hey came to show() and length="+star.length);
  var l=star.length;
 c=1,x2=1,x22=1;//,x3=0;
if(l>1)
{//alert("hey inside the if");
 c2[x2-1]=x22;
for(var x5=1; x5<l; x5++)
 {
  //alert("hey inside the for and controle no="+star[x2][5]);
  if(star[x5-1][0]!=star[x5][0])//here star[][0] holds uhlog_contrleno
  {
   x2++;//holds the distinct no of controls(same for each call)
   x22=1;
   }
   else
   x22++;
   c2[x2-1]=x22;   //alert("hey the x2-1="+x2);
  }}
  else{//alert("inside the else");
  c2[x2-1]=x22;} // //alert("hey x2 value="+x2); 
// alert("hey assingning the common static fields");
 document.getElementById("UHLOG_CONTROLENO2").value =star[x][0];
 document.getElementById("bsflunit_ucode").value =star[x][1];
 document.getElementById("unit_name").value =star[x][2];
 document.getElementById("date_of_indent").value =star[x][3];
 document.getElementById("in_by").value =star[x][4];
 document.getElementById("p_by").value =star[x][5];
 //document.getElementById("generate").style.visibility='visible';// makes generate visible if the addTable method is not ging call & the previous call make generate hidden
  count=c2[x1];
 //alert("hey the row count in show function is="+count); 
  for(var i=1; i<count; i++)  $(".addRow").trigger('click'); //alert("hey after dynamic table generation");
 //assigning values to dynamic fields
 var SID = document.getElementsByName('st_id');
 var STNAME = document.getElementsByName('st_name');
 var CLOSING = document.getElementsByName('closing_stock');
 var NEW = document.getElementsByName('new_stock');
 //var UHLOG_OUTREACH = document.getElementsByName('UHLOG_OUTREACH');
 //var UHLOG_ODCUST = document.getElementsByName('UHLOG_ODCUST');//var ops_narration = document.getElementsByName('ops_narration');
 //var UHLOG_ODAMT = document.getElementsByName('UHLOG_ODAMT');
 //var UHLOG_REMARKS = document.getElementsByName('UHLOG_REMARKS');
 //alert("hey the assignment to the dinemic fields is startted,c2[x1]="+c2[x1]);
 var k = x;
 for (var j = 0; j < c2[x1]; j++)
 {//alert("hinsiede the for loop,and the  head="+star[k][2]+"--");
  if(k>x+c2[x1])
   break; //alert("the activitsl_id="+star[k][5]);
  SID[j].value=star[k][6];//alert("the VCODE_id="+star[k][6]);
  STNAME[j].value=star[k][7];
  CLOSING[j].value=star[k][8];
  NEW[j].value=star[k][9];
  //UHLOG_OUTREACH[j].value=star[k][8];
  //UHLOG_ODCUST[j].value=star[k][9];
  //UHLOG_ODAMT[j].value=star[k][10];
  //UHLOG_REMARKS[j].value=star[k][11];
 //Currency converter function code
 
    k++;
 }// for (var j = 0; j < c2[x1]; j++)
 //alert("hey the assigning to the dinamic fields is completed");
}
function allowupdate()
{
 if((document.form1.update.style.display=="inline") && (document.form1.exct.disabled))
    { 
	document.form1.update.disabled=false;
	  //document.form1.update.focus();
	 }
}//allowupdate()
</script>
<script language="javascript" type="text/javascript">var ssssss="<%=(String) session.getAttribute("userType")%>";</script>
<!-- <script type="text/javascript" language="javascript" src="JS/Htop2.js"></script> -->
<script type="text/JavaScript" src="JS/calendar.js"></script>
<link rel="stylesheet" href="CSS/calendar.css" type="text/css" />

<script type="text/javascript">
function queryfunction() 
{ //alert("hey inside the query");
//Everything from disable() exceptiong button code,just for primary key enable(replace true by false) and editable(add readOnly=false)
 var f = document.getElementById("form1");
 var inputs = f.getElementsByTagName("input");
 for(var i = 0; i < inputs.length; i++)
 { 
   if(inputs[i].type=="text")
   {
    if(inputs[i].style.display!="none")//new added one
    inputs[i].value='';//new added one
	inputs[i].style.backgroundColor='';//new added one to remove any background color
    inputs[i].disabled=true;
	}
  }
 
 
 document.form1.bsflunit_ucode.disabled = false;
 document.form1.bsflunit_ucode.style.backgroundColor = 'skyblue';
 
		
		//document.form1.unit_id.disabled = false;
		//document.form1.unit_id.readOnly = false;
		document.form1.bsflunit_ucode.style.backgroundColor = 'skyblue';
		document.form1.bsflunit_ucode.focus();
		
		//document.form1.area_id.disabled = false;
		//document.form1.area_id.readOnly = false;
		//document.form1.area_id.style.backgroundColor = 'skyblue';
		//document.form1.area_id.focus();
		
		
		
		
		//document.form1.huma_reporting.disabled = false;
		//document.form1.huma_reporting.readOnly = false;
		//document.form1.huma_reporting.style.backgroundColor = 'skyblue';
		
		
		
		//--------------------------------------Common (buttons) code
		document.form1.save.style.display = 'none';
		document.form1.update.style.display = 'inline';
		goDim("form1", "update");
		document.form1.update.disabled = true;
		document.form1.exct.disabled = false;
		document.form1.first.disabled = true;
		document.form1.previous.disabled = true;
		document.form1.next.disabled = true;
		document.form1.last.disabled = true;
		document.form1.new2.disabled = true;
		goDim("form1", "query");
		document.form1.query.disabled = true;
		document.form1.clear.disabled = false;
	}//queryfunction();
	function disable() { //alert("hey the geetrtin session from top is="+x);
		document.getElementById("cal").style.display = 'none';
		//document.getElementById("cal2").style.display = 'none';
		var f = document.getElementById("form1");
		var inputs = f.getElementsByTagName("input");
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "text") {
				if (inputs[i].style.display != "none")//new added one
					inputs[i].value = '';//new added one
				inputs[i].style.backgroundColor = '';//new added one to remove any background color
				inputs[i].disabled = true;
			}
		}
		      
        var selects = f.getElementsByTagName("select"); //alert("hey you got the selects[]"+selects);
 for(var j = 0; j < selects.length; j++)
 { 
    if(selects[j].style.display!="none")//new added one
    selects[j].value = "";//new added one
    selects[j].disabled=true;
  }
  
  $("#tableID tr:gt(1)").remove();//removing all the rows exception first two rows in current table//newly added for dynamic table
  //document.form1.generate.disabled=true;//to disable the generate button
 document.form1.remove.style.display='none';//newly added for dynamic table
 document.form1.add.disabled=true;//newly added for dynamic table
 
        document.form1.st_id.disabled=true;
        document.form1.st_id.value="";
      //  document.form1.Description.value="";
        //document.form1.Description.disabled=true;
		//---------common (button) code
		document.form1.save.disabled = true;
		document.form1.first.disabled = true;
		document.form1.previous.disabled = true;
		document.form1.next.disabled = true;
		document.form1.last.disabled = true;
		document.form1.new2.disabled = false;
		document.form1.query.disabled = false;
		goDim("form1", "clear");
		document.form1.clear.disabled = true;
		document.form1.exct.disabled = true;//from this line new added code for Query functionality
		document.form1.save.style.display = 'inline';
		document.form1.update.style.display = 'none';
	}//disable()
	function enable() {
		document.getElementById("cal").style.display = 'inline';
	//	document.getElementById("cal2").style.display = 'inline';
		var f = document.getElementById("form1");
		//var inputs = f.getElementsByTagName("select");
		for ( var i = 0; i < f.length; i++) {
			if (f[i].type != "button") {
				f[i].disabled = false;
				f[i].style.backgroundColor = '';//new added one to remove any background color
			}
		}
		document.form1.bsflunit_ucode.value="<%=ucode%>"; //alert("assigned unit_code is="+"<%=uname_no%>");
		 document.form1.unit_name.value="<%=uname_no%>";
		document.form1.sno.value=1;
		document.form1.add.disabled=false;
		document.form1.new_stock.disabled = false;
		document.form1.closing_stock.disabled = false;
		//document.form1.huma_address.disabled = false;
	//	document.form1.Description.disabled = false;
	//	document.form1.huma_photo.disabled = true;//make false to enable the photo field
		//instead of automatic id generation allowing user to enter the id----------------------------
		//document.getElementById("huma_id").readOnly = false;
	//	document.form1.FRS_st_id.disabled = true;
	  //document.form1.cont_id.readOnly=true;//if it was made editable in queryfunction
 		$("#tableID tr:gt(1)").remove();//removing all the rows exception first two rows in current table//newly added for dynamic table
 		document.form1.remove.style.display='none';//newly added for dynamic table
 		document.form1.add.disabled=false;//newly added for dynamic table
		//document.form1.schedule_id.value=1;//newly added for dynamic table
	
	//	document.form1.closing_stock.value = document.form1.unit_id.value;
		
		//automatic id generation code ends here(for all the remaining masters)-----------------------
		//-------------common (button) code
		goDim("form1", "new2");
		document.form1.new2.disabled = true;
		document.form1.query.disabled = true;
		document.form1.exct.disabled = true;//from this line newly added code for Query functionality
		document.form1.save.style.display = 'inline';
		document.form1.update.style.display = 'none';
	}//enable()
</script>
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>
<script type="text/javascript" src="JS/jquery.table.addrow.js"></script>
</head>
<body bottommargin="100%" marginheight="100%" rightmargin="100%"
	leftmargin="100%" marginwidth="100%" topmargin="100%"
	onLoad="disable();">
	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script>
	<script type="text/javascript" src="JS/Bmenu.js"></script>
	<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if((role.equals("admin"))||(role.equals("audit"))||(role.equals("user")))
{ %>
	
	<form id="form1" name="form1" method="post">
		<!--<table cellpadding="1" cellspacing="1" background="blue">
							<tr>

								<td width="380"></td> 	
								<td width="380" align="left"><b><a href="AdminHome.jsp">Administrator HOME</a></b></td>
							</tr>
						</table>-->
		<br />

		<table width="672" align="center">
			<tr>
				<td width="638" height="">
					<fieldset>
						<legend class="formTitle">Unit Indent </legend>
						<table width="100%" height="86%" border="0" align="center"
							bordercolor="#000000">
							<tr height="28" valign="top">
								<td colspan="2" align="right"><script
										type="text/javascript" src="JS/np.js"></script>
								</td>
							</tr>
							<tr>
								<td width="24%" height="" align="right" class="style22"><div
										align="right">Unit id</div><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script></td>
								<td width="76%" align="left"><div align="left">
										<!--  <input name="unit_id" id="unit_id" type="text" size="30" onkeyup="allowupdate(); showEmp('unit_id.comp_id',unit_id.value,'');" onfocus="addSuggestion('unit_id2','unit_id');" maxlength="30" style="border-color:#0099FF;" onblur="showEmp('unit_id.comp_id',unit_id.value,'');" />  -->
										<input name="bsflunit_ucode" id="bsflunit_ucode" type="text" size="35"
											onKeyUp="allowupdate();"
												onfocus="addSuggestion('BSFLUNIT_UCODE_roles','bsflunit_ucode');"
											style="border-color: #0099FF;"
											onBlur="showEmp('BSFLUNIT_UCODE',bsflunit_ucode.value,'');"/><input type="hidden" name="UHLOG_CONTROLENO2" id="UHLOG_CONTROLENO2"></input>
											</div></td>
							</tr>
							<tr>
												<td height="24" align="right"><div align="right">Unit
														Name</div></td>
												<td align="left">
													<div align="left">
														<input name="unit_name" id="unit_name" type="text" readonly=""/>
													</div>
												</td>
											</tr>
						   	<tr>
								<td height="25" class="style22" align="right"><div
										align="right">D O I</div></td>
								<td align="left"><div align="left">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="27%">
													<div align="left">
														<input type="text" name="date_of_indent" id="date_of_indent" size="10"
															maxlength="10" onKeyUp="allowupdate();" /> <a href="#"
															onclick="setYears(1947, 2050);
       showCalender(this, 'date_of_indent');">
															<img id="cal" src="images/calender.png"
															onClick="allowupdate();" />
														</a>
													</div>
												</td>
												<td width="73%"><div align="left">
														(dd-mm-yyyy)<br />
													</div></td>
											</tr>
										</table>
									</div></td>
							</tr>
							<tr>
												<td height="24" align="right"><div align="right">Indent
														By</div></td>
												<td align="left">
													<div align="left">
														<input name="in_by" id="in_by" type="text"
															size="35" maxlength="35"
															
														onKeyUp="allowupdate();"	/>
													</div>
												</td>
											</tr>
											<tr>
												<td height="24" align="right"><div align="right">prepared
														By</div></td>
												<td align="left">
													<div align="left">
														<input name="p_by" id="p_by" type="text"
															size="35" maxlength="35" 
														onKeyUp="allowupdate();"	/>
													</div>
												</td>
											</tr>
											
							
							
						<!-- 	<table id="tableID" width="100%"  border="1" style="border-style:outset; border-color:blue" >   -->
					<tr><td align="center" colspan="2"><table id="tableID" width="100%" style="border:1px solid #708090;width: 100%" align="center">
                  <tr style="background-image:url(images/TableHeadBg.gif)">
                  <!-- <tr bgcolor="back03.gif" style="opacity: 0.65; filter: alpha(opacity=65);"> -->
                    <th width="1%" scope="col" class="style21"><div align="center">Sno</div></th>
                    <th width="6%" scope="col" class="style21"><div align="center">Stationary Id</div></th>
                    <th width="10%" scope="col" class="style21"><div align="center">Stationary Name</div></th>
                    <th width="10%" scope="col" class="style21"><div align="center">Closing Stock</div></th>
                    <th width="10%" scope="col" class="style21"><div align="center">Stock Indent Now</div></th>
                    
                    <!-- 
                    <th width="10%" scope="col" class="style21"><div align="center">Amt Spent (Rs)</div></th>
                    <th width="10%" class="style21" scope="col"><div align="center">Total Outreach</div></th>
					<th width="10%" class="style21" scope="col"><div align="center">OD Customers</div></th>
					<th width="10%" class="style21" scope="col"><div align="center">OD Amount (Rs)</div></th>
					<th width="14%" class="style21" scope="col"><div align="center">Remarks</div></th>
					 -->
					<th width="1%">
					  <div align="center">
					    <input type="button" id="add" name="add" value="+" class="addRow" onmouseover="goLite(this.form.name,this.name)" onmouseout="goDim(this.form.name,this.name)"/>
			      </div></th></tr>
                  <tr>
                    <td>
                      <div align="center">
                        <!--Business Development
                      <input type="hidden" name="head_bd" id="head_bd" value="Business Development"/>-->
                        <input type="text" name="sno" id="sno" maxlength="2" size="1" value="1" class="rowNumber" readonly=""/>
                        </div>                    </td>
<%--                     <td><div align="center">
                      <select name="ACTIVITY_ID" id="ACTIVITY_ID" onchange="showEmpn2('ops_changeActivitysl',this,this.value); allowupdate();">
        <option value="" selected="selected"></option>
 <% try 
   { 
	 ps2 = con.prepareStatement("select s.ACTIVITY_ID,f.activityfl_abbr||' - '||s.activitysl_name as activitysl_name from activitysl_mstr s,activityfl_mstr f where s.activityfl_id=f.activityfl_id and f.activityfl_type!='MA' and f.activityfl_type!='TTF'");
	 rs2 = ps2.executeQuery();
	 while(rs2.next())
	 { %>
        <option value="<%=rs2.getString(1)%>"><%=rs2.getString(2)%></option>
     <% }//while
	}//try
	catch (Exception e) {  e.printStackTrace(); }
	%>
      </select>
                      </div></td>
 --%>               <td><div align="center">
 
 				
				<select name="st_id" id="st_id" size="1"
												onChange="allowupdate(); showEmps1('st_name',this,this.value);">
				<option value="">Select</option>
				<%
				Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;
 try{
 JdbcConnect jc=new JdbcConnect();  
 //System.out.println("JdbcConnect jc="+jc);
 con = jc.getConnection();  
 stmt = con.createStatement();  
 rs = stmt.executeQuery("select s_id,s_name from stationary_mstr");  
   while(rs.next()){
   //System.out.println("Option value= "+rs.getString(1));
   %>
   <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
   
   <%
   }  
 }
 catch(Exception e){
     System.out.println(e);
 }
 finally 
	{
	if(rs!=null)rs.close();
	if(stmt!=null)stmt.close();
	if(con!=null)con.close();
	}
 %>
 	
 	</select>				
 					</div></td>
 				
 					<td><div align="center"><input name="st_name" type="text" id="st_name"  width:95%" onkeyup="allowupdate();" readonly="readonly"/></div></td>
 					<td><div align="center"><input name="closing_stock" type="text" id="closing_stock"  width:95%" onkeyup="allowupdate();"/></div></td>
 					<td><div align="center"><input name="new_stock" type="text" id="new_stock"  onkeyup="allowupdate();"/></div></td>
 					
 				<!-- 	<td><div align="center"><input name="seqid" type="hidden" id="seqid"/></div></td>    -->
 					<!-- 
 					<td><div align="center"><input name="UHLOG_AMTSPENT" type="text" id="UHLOG_AMTSPENT" style="width:90%" size="2" onkeyup="allowupdate();" maxlength="13"/></div></td>
					<td><div align="center"><input name="UHLOG_OUTREACH" type="text" id="UHLOG_OUTREACH" style="width:90%" size="2" onkeyup="allowupdate();" maxlength="13"/></div></td>
					<td><div align="center"><input name="UHLOG_ODCUST" type="text" id="UHLOG_ODCUST" style="width:90%" size="2" onkeyup="allowupdate();" maxlength="13"/></div></td>
					<td><div align="center"><input name="UHLOG_ODAMT" type="text" id="UHLOG_ODAMT" style="width:90%" size="2" onkeyup="allowupdate();" maxlength="10"/></div></td>
					<td ><div align="center"><textarea name="UHLOG_REMARKS" id="UHLOG_REMARKS" style="width:95%" rows="2" onkeyup="EnforceMaximumLength(this,300); allowupdate();" onblur="EnforceMaximumLength(this,300);"></textarea></div></td>
 					 -->
 					<td> <div align="center">
					  <input type="button" id="remove" name="remove" value="-" class="delRow" onclick="allowupdate();" onmouseover="goLite2(this)" onmouseout="goDim2(this)"/>
					</div></td> 
                  </tr>
</table></td></tr>

   </table>

<!-- Completed Here -->
							<tr>
								<td colspan="2" align="center" height="40"><table>
										<tr>
											<td><script type="text/javascript" src="JS/Bbuttons.js"></script>
												<br />
												<table id="msg2"
													style="visibility: hidden; position: absolute;">
													<tr>
														<td>Execute</td>
													</tr>
												</table></td>
											<td width="" align="left">&nbsp;&nbsp; <input
												type="button" id="exct" name="exct" class="groovybutton"
												value="&euro;" title=""
												onMouseOver="goLite(this.form.name,this.name); showmenu('msg');"
												onmouseout="goDim(this.form.name,this.name); hidemenu('msg');"
												onClick="showEmpn('unit_indent',bsflunit_ucode.value,'');" 
												/> <br />
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
						<!-- 
					</fieldset>
				</td>
			</tr>
		</table>   -->
	</form>
	<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>
<%
}//authorised acess else------------------------------------------------------------------------------------
else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to create new Human Resource
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>

<%
}
}
	%>
	
</html>
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
