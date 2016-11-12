<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*"%>
<%@page import="frs_cls.JdbcConnect"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Human Resource master</title>
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

<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>

<!--<script type="text/javascript" src="JAVASCRIPT/jquery-1.4.2.min.js"></script>-->

<script type="text/javascript" language="javascript">

function showEmp(decide,key1,key2)
{//alert("hety inside the showEmp="+decide);
 if(!(document.form1.exct.disabled))
	return;//stop executing this function in case of query functioning
//if(patt.test(document.getElementById("unit_id").value))
 //{
 
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
		 //alert("strar is empty");
		 alert("Please enter the existing Unit ID");
		 document.getElementById("unit_id").value ='';
		 document.getElementById("area_id").value ='';
		 document.getElementById("comp_id").value =" ";
		 document.form1.unit_id.focus();
		 
	 }
	 else if(strar.length>0)
	 {
		document.form1.area_id.value=strar[0];
		document.form1.comp_id.value=strar[1];
		
		//document.form1.currentunit.value=document.form1.unit_id.value;
		
	 }//else
	}//if(key1>=4)
	else //if(key1<4)
	{
	document.getElementById("area_id").value ='';
	document.getElementById("comp_id").value ='';
	return false;
	}
	//}
}//showEmp(decide,key1,key2)

var j=0,emp2='';
var star2;//,i;//global variable
function showTransferedUnit(decide,obj,emp)
{
//decide=transferedUnit
//obj=this
//emp=this.value

//below three lines code to retrieve current dynamic row number
 var par=obj.parentNode; 
 while(par.nodeName.toLowerCase()!='tr'){   par=par.parentNode;  } //alert("the index of the current row is"+par.rowIndex); 
 var i=par.rowIndex;
 j=i-1;

//alert("hety inside the showTransferedUnit="+decide);
//alert("The key is "+emp);
// if(!(document.form1.exct.disabled))
//	return;//stop executing this function in case of query functioning
 sessioncheck();
 //alert("session not expired");
xmlHttp=GetXmlHttpObject();

//alert("got xmlHttp and xmlHttp="+xmlHttp);
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
		// alert("Please Select Correct Status");
	 }
	 else if(strar.length>0)
	 {
	// alert("strar.length>0");
	// alert("Getting the element name");	
	var transferedunit = document.getElementsByName('transferedunit');
	var currentunit = document.getElementsByName('currentunit');

		var Transfer_Staus = strar[0];
		//alert("Transfer_Staus is "+Transfer_Staus);
		//if(Transfer_Staus.isMatch("Y"))
		if(Transfer_Staus == 'Y' || Transfer_Staus == 'y')
		{
		//currentunit[j].disabled = false;
		//transferedunit[j].disabled = false;
		currentunit[j].value = document.getElementById("unit_id").value;
		transferedunit[j].readOnly=false;
		transferedunit[j].value = "";
		//transferedunit[j].disabled = false;
		//alert("Transfer Selected");
		}
		else
		{
		//currentunit[j].disabled = true;
		//transferedunit[j].disabled = true;
		//alert("Not Transfered");
		//currentunit[j].readonly="readonly";
		currentunit[j].value = "";
		currentunit[j].setAttribute('readonly', 'readonly');
		transferedunit[j].setAttribute('readonly', 'readonly');
		transferedunit[j].value = "";
		}
	 }//else
}//showTransferedUnit(decide,emp,obj)

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
   var busiid=new RegExp("[0-9]{3}");
  if(document.form1.unit_id.value=='')
  {//alert("hey the save is inline and the area_id is=''");
   alert("Please enter the Unit Id");
   document.form1.unit_id.focus();
   return false;
  }
  else//if(document.form1.area_id.value!='')
  { //alert("hey inside the if: means the area_id is !=''");
   var id=document.form1.unit_id.value.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
       id=id.substr(0,3);
	   //alert("hey the id is="+id);
   //if(!busiid.test(document.form1.unit_id.value))
    // {
     //  alert("Please enter the Unit Id correctly");
	  // document.form1.unit_id.focus();
	   //return false;
	 //}
   }//else//if(document.getElementById("area_id").value!='')
       var patt1=new RegExp("[A-Za-z]");
	   var patt2=new RegExp("^[A-Za-z]+[0-9]*[ A-Za-z0-9]*$");

	  var huma_id=new RegExp("^[0-9]{4}$");
	  var huma_id2=new RegExp("^[0]{4}$");
   if((!patt2.test(document.form1.huma_id.value))||(huma_id2.test(document.form1.huma_id.value)))
    {
      alert("Please enter the EMP ID correctly");
      document.form1.huma_id.focus();
      return false;
    }//huma_id
   if(!patt2.test(document.form1.huma_fname.value))
    {
      alert("Please enter the First name");
      document.form1.huma_fname.focus();
      return false;
    }
	if(!patt2.test(document.form1.huma_lname.value))
    {
      alert("Please enter the Last name");
      document.form1.huma_lname.focus();
      return false;
    }
	//huma_freeze validation
/*	if(document.getElementById("huma_freeze").value=='')
    {
       alert("Please mention the employee Freeze");
	   document.form1.huma_freeze.focus();
	   return false;
	}*/
	//huma_designation (grade_id) validation
  var gradeid=new RegExp("G[0-9]{2}");
  if((document.form1.huma_designation.value=='') || (!gradeid.test(document.form1.huma_designation.value)))  
   { //alert("hey the inside log_place");
	 alert("Please enter the Grade id correctly");// of client required heads");
	 document.form1.huma_designation.focus();
 	 return false;
	}//huma_designation	
	var huma_id=new RegExp("[0-9]{4}");
 
 if(document.form1.huma_reporting.value=='')
 {//alert("hey the save is inline and the huma_reporting is=''");
   alert("Please enter Reporting Officer Id");
   document.form1.huma_reporting.focus();
   return false;
 }
/* else//if(document.form1.huma_reporting.value!='')
 { 
   if(document.form1.huma_reporting.value!='')
    {
       alert("Please enter the Reporting Officer Id correctly");
	   document.form1.huma_reporting.focus();
	   return false;
	}
  }//else//if(document.getElementById("huma_reporting").value!='')
  */
 var cityid=new RegExp("CT[0-9]{5}");
  if((document.form1.city_id.value=='') || (!cityid.test(document.form1.city_id.value)))  
   { //alert("hey the inside log_place");
	 alert("Please enter the Place of posting(city) correctly");// of client required heads");
	 document.form1.city_id.focus();
 	 return false;
	}//city_id
	var date=new RegExp("[0-9]");
	if(!date.test(document.form1.huma_dob.value))
    {
      alert("Please select the DOB");
	  document.form1.huma_dob.focus();
      return false;
    }
	if(document.form1.huma_dob.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(document.form1.huma_dob.value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	var returnval=false;//validformat
	if(!validformat.test(document.form1.huma_dob.value))
	{
	alert("Please enter the DOB correct format");
	document.form1.huma_dob.focus();
	return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		alert("Invalid month or date found in DOB");
		document.form1.huma_dob.focus();
		return false;
	  }
	 }//if(huma_dob!="")
	//date comparision code   
	   var currentTime = new Date();
       var mm = currentTime.getMonth() + 1;
       var dd = currentTime.getDate();
       var yyyy = currentTime.getFullYear();
       var sysdate=dd+"-"+mm+"-"+yyyy;
	   sysdate=sysdate.split("-");
	   var sysdate1 = new Date(sysdate[2], sysdate[1]-1, sysdate[0]); //var date1 = new Date(yr1, mon1, dt1); 

	   var hdob=(document.form1.huma_dob.value).split("-");
       var hdob1 = new Date(hdob[2], hdob[1]-1, hdob[0]); //alert("hey to rday date is ="+prdate);
	   /*if(hdob1 > sysdate1)
     	{
		 alert("DOB cannot be greater than System date");
		 document.form1.huma_dob.focus();
		 return false; 
	     }
		 else
		 { // var i=sysdate[2]-hdob[2];alert("hey inside the else,difference is:"+i);
		   if(sysdate[2]-hdob[2]< 18)
		   { 
		    alert("Age cannot not be less than 18 yrs");
			document.form1.huma_dob.focus();
		    return false; 
			}
		  }*/	 
	//date comparision code exit here
	//huma_doj validation    
	if(!date.test(document.form1.huma_doj.value))
    {
      alert("Please select the DOJ");
	  document.form1.huma_doj.focus();
      return false;
    }
	if(document.form1.huma_doj.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(document.form1.huma_doj.value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	var returnval=false;//validformat
	if(!validformat.test(document.form1.huma_doj.value))
	{
	alert("Please enter the DOJ correct format");
	document.form1.huma_doj.focus();
	return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		alert("Invalid month or date found in DOJ");
		document.form1.huma_doj.focus();
		return false;
	  }
	 }//if(huma_doj!="")

	//date comparision code   
	   var hdoj=(document.form1.huma_doj.value).split("-");
       var hdoj1 = new Date(hdoj[2], hdoj[1]-1, hdoj[0]); //alert("hey to rday date is ="+prdate);
	   if(hdoj1 > sysdate1)
     	{
		 alert("DOJ cannot be greater than System date");
		 document.form1.huma_doj.focus();
		 return false; 
	     }
		 else if(hdoj1 < hdob1)
     	{
		 alert("DOJ cannot be less than DOB");
		 document.form1.huma_doj.focus();
		 return false; 
	     }
		 else if(hdoj[2]-hdob[2]< 18)
		{ 
		  alert("As on DOJ,Age cannot not be less than 18 yrs");
		  document.form1.huma_dob.focus();
		  return false; 
		 }
	//date comparision code exit here
	var reason=new RegExp("(\/\/\/\/\/\/|::::::)");
	if((!patt1.test(document.form1.huma_address.value))||(reason.test(document.form1.huma_address.value)))
    {
	//if(!patt1.test(document.form1.huma_address.value))
    //{
      alert("Please enter the Address correctly");
      document.form1.huma_address.focus();
      return false;
    }
	var pin=new RegExp("[0-9]{6}");
	if(!pin.test(document.form1.huma_pin.value))
    {
      alert("Please enter a valid Pincode");
      document.form1.huma_pin.focus();
      return false;
    }
	var email= /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/; 
	if(!email.test(document.form1.huma_email.value))
    {
      alert("Please Enter valid email address");
	  document.form1.huma_email.focus();
      return false;
    }
	//var phone=new RegExp("[0-9]{11}");
	//var mobile=new RegExp("[0-9]{12}");
	var phone = /^[0-9]\d{1,4}(-|\s)?\d{6,9}$/;//Ex:040 39162116/040-39162661/04039162116
	var mobile=/^[+]?[0-9]{2}(-|\s)?[0-9]{8,10}$/;//Ex:91 9396812884/91-9396812884/919396812884
	if((!phone.test(document.form1.huma_phone.value))||((document.form1.huma_phone.value).length<11))
    {
      alert("Please enter valid Phone no");
      document.form1.huma_phone.focus();
      return false;
    }
	
    if(!mobile.test(document.form1.huma_mobile.value))
      {
      alert("Please enter valid Mobile no");
      document.form1.huma_mobile.focus();
	  return false;
      }
	if(document.form1.huma_photo.disabled=="false")
	if(!patt1.test(document.form1.huma_photo.value))
    {
      alert("Please upload your valid pass photo");
      document.form1.huma_photo.focus();
      return false;
    }
    
    if(document.form1.HR_Status.value=="")
      {
      alert("Please enter the Status at HR");
      document.form1.HR_Status.focus();
	  return false;
      }
    
    //Validation for Status Related Fields
    
    var status = document.getElementsByName('status');
	var effectdate = document.getElementsByName('effectdate');
	var Description = document.getElementsByName('Description');
    var currentunit = document.getElementsByName('currentunit');
    var transferedunit = document.getElementsByName('transferedunit');
	
//	var UHLOG_ODCUST = document.getElementsByName('UHLOG_ODCUST');//var ops_narration = document.getElementsByName('ops_narration');
//	var UHLOG_ODAMT = document.getElementsByName('UHLOG_ODAMT');
	var n= status.length;
    for (var k = 0; k < n; k++)
    {
//village c

//alert("MSR DEBUG VCODE");
  if(status[k].value=="-1")  
   {
	 alert("Please Select Status");
	 status[k].focus();
 	 return false;
	}
//alert("MSR DEBUG ACTIVITY");
//ACTIVITY_ID validation start here	 
//var date=new RegExp("[0-9]");
 /* 
  if(effectdate[k].value=='')  
   {
	 alert("Please enter the Effect Date");
	 effectdate[k].focus();
 	 return false;
	}
	*/
 
 if(!date.test(effectdate[k].value))
    {
      alert("Please enter the Effect Date");
	  effectdate[k].focus();
      return false;
    }
    
if(effectdate[k].value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(effectdate[k].value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	returnval=false;//validformat
	if(!validformat.test(effectdate[k].value))
	{
	alert("Please enter the Effect Date in correct format");
	effectdate[k].focus();
	return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		alert("Invalid month or date found in Effect Date");
		effectdate[k].focus();
		return false;
	  }
	 }//if(huma_doj!="")
	 //date comparision code   
	   var EffDate=(effectdate[k].value).split("-");
       var EffDate1 = new Date(EffDate[2], EffDate[1]-1, EffDate[0]); //alert("hey to rday date is ="+prdate);
	   if(EffDate1 > sysdate1)
     	{
		 alert("Effect Date cannot be greater than System date");
		 effectdate[k].focus();
		 return false; 
	     }
  //Entry should happen in date Order
  if(k>0)
  {
   	   var EffDate2=(effectdate[k-1].value).split("-");
       var EffDate3 = new Date(EffDate2[2], EffDate2[1]-1, EffDate2[0]);
       if(EffDate3 > EffDate1)
     	{
		 alert("Current Effect Date Should not be greater than Previous Effect Date");
		 document.form1.effectdate[k].focus();
		 return false; 
	     }
  }
	
 if(status[k].value=="1")  
   {
	if(currentunit[k].value=='')  
   {
	 alert("Please enter the Current Unit");
	 currentunit[k].focus();
 	 return false;
	}
	
	if(transferedunit[k].value=='')  
   {
	 alert("Please enter the Transferred Unit");
	 transferedunit[k].focus();
 	 return false;
	}
	//Spliting the Value to get the Unit_code
	
	//alert("The value of curUnit="+currentunit[k].value);
	//alert("The value of TransUnit="+transferedunit[k].value);
	var curunit = currentunit[k].value;
	var trnsUnit = transferedunit[k].value;
	trnsUnit = trnsUnit.substring(trnsUnit.lastIndexOf('-')+1);
	//trnsUnit = trnsUnit.substring(trnsUnit.lastIndexOf('-'));
	//alert("After Splitting trnsUnit ="+trnsUnit);
	if(curunit == trnsUnit)
	{
		alert("Please Check!\n While Transferring can not be Transferred to the Same Unit");
		transferedunit[k].focus();
		return false;
	}
	
	}
	if(Description[k].value=='')  
   {
	 alert("Please enter the Description");
	 Description[k].focus();
 	 return false;
	}


//UHLOG_AMTSPENT validation start here	 
  var Area=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
 /* if(UHLOG_AMTSPENT[k].style.visibility=='visible')
  {
   if((!Area.test(UHLOG_AMTSPENT[k].value)) || (UHLOG_AMTSPENT[k].value.length < 1))// || (Number(UHLOG_AMTSPENT[k].value) == 0))
   { //alert("hey the inside ops_place");
	 alert("Please enter the Amt spent (Rs) correctly"); 
	 UHLOG_AMTSPENT[k].focus();
	 return false;
	 }
	}
*/
//UHLOG_OUTREACH validation start here	 
 /*  var Area=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
   if(UHLOG_OUTREACH[k].style.visibility=='visible')
   {//alert("the number after the point="+UHLOG_OUTREACH[k].value.split('.')[1].length);
    if((!Area.test(UHLOG_OUTREACH[k].value)) || (UHLOG_OUTREACH[k].value.length < 1) || (Number(UHLOG_OUTREACH[k].value) == 0))
    { //alert("hey the inside ops_place");
	 alert("Please enter the total outreach amount correctly (non zero)"); 
	 UHLOG_OUTREACH[k].focus();
 	 return false;
	 }
	}  *///if(UHLOG_OUTREACH[k].style.visibility=='visible') alert("the number after the point="+UHLOG_OUTREACH[k].value.split('.')[1]);
//UHLOG_ODCUST validation start here	 
 /*  var Quantity=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
   if(UHLOG_ODCUST[k].style.visibility=='visible')
   {//alert("the number after the point="+UHLOG_ODCUST[k].value.split('.')[1].length);
    if((!Quantity.test(UHLOG_ODCUST[k].value)) || (UHLOG_ODCUST[k].value.length < 1))// || (Number(UHLOG_ODCUST[k].value) == 0))
    { //alert("hey the inside ops_place");
	 alert("Please enter the OD Custmers correctly"); 
	 UHLOG_ODCUST[k].focus();
 	 return false;
	 }
	} */  //if(UHLOG_ODCUST[k].style.visibility=='visible') alert("the number after the point="+UHLOG_ODCUST[k].value.split('.')[1]);
//UHLOG_ODAMT validation start here	 
/*   var Price=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
   if(UHLOG_ODAMT[k].style.visibility=='visible')
   {//alert("the number after the point="+UHLOG_ODAMT[k].value.split('.')[1].length);
    if((!Price.test(UHLOG_ODAMT[k].value)) || (UHLOG_ODAMT[k].value.length < 1))// || (Number(UHLOG_ODAMT[k].value) == 0))
    { //alert("hey the inside ops_place");
	 alert("Please enter the OD amount correctly"); 
	 UHLOG_ODAMT[k].focus();
 	 return false;
	 }
	}  */  //if(UHLOG_ODAMT[k].style.visibility=='visible') alert("the number after the point="+UHLOG_ODAMT[k].value.split('.')[1]);
 /*	 if((!patt1.test(UHLOG_REMARKS[k].value))||(reason.test(UHLOG_REMARKS[k].value)))
      {
        alert("Please enter the Remarks correctly");
        UHLOG_REMARKS[k].focus();
        return false;
      }  */
	}  //For loop
    //Validation Completed for Status Related Fields
}//validateForm()

function focuses() { document.form1.huma_fname.focus(); }
function focuses1() { document.form1.huma_address.focus();}
function EnforceMaximumLength(fld,len) 
{
 if(fld.value.length > len) { fld.value = fld.value.substr(0,len); document.form1.huma_pin.focus();/*fld.focus();*/ }
}

//hey the update&save butons(submission) code starts here---------------------------------------------------------
$(document).ready(function(){//alert("hey inside the ready functin of jquery update");
		$("#form1").submit(function(){sessioncheck();//alert("hey inside the submit function");
		  if(document.form1.onSubmit==validateForm()) 
			{ var allElements=$(this).serialize();//alert("hey the validation is done");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			if(document.form1.save.style.display=='inline')
			  var program='shm';	
			else  
			   var program='uhm'; //alert("hey the program is="+program);	
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
function showEmpn(decide,key1,key2,key3,key4,key5,key6,key7)
{//alert("hety inside the showEmpn");
 sessioncheck();
xmlHttp=GetXmlHttpObject();

 if (xmlHttp==null)
  {
  return;
  }
 var url="getuserupdate";
  //url=url+"?decide="+decide+"&key1="+key1+"&key2="+key2;
  url=url+"?decide="+decide+"&key1="+key1+"&key2="+key2+"&key3="+key3+"&key4="+key4+"&key5="+key5+"&key6="+key6+"&key7="+key7;
//alert("hey url is made and the url is "+url);// xmlHttp.onreadystatechange=stateChangedn2;
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
		 document.getElementById("huma_id").value ='';
		 document.getElementById("unit_id").value ='';
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
	 document.getElementById("msg").style.visibility='hidden';
	 document.getElementById("exct").disabled=true;
     document.getElementById("huma_id").readOnly =true;
	 document.getElementById("cal").style.display='inline'; 
	 document.getElementById("cal2").style.display='inline'; 
	 nextprevious("first");	
}//esle if
//  }
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
  document.form1.huma_address.disabled=false; 
  document.form1.huma_freeze.disabled=false;*/
   //document.form1.huma_photo.disabled=false;
 document.getElementById("comp_id").value =star[x][0];
 document.getElementById("area_id").value =star[x][1];
 document.getElementById("huma_id").value =star[x][2];
 document.getElementById("huma_fname").value =star[x][3];
 document.getElementById("huma_lname").value =star[x][4];
 document.getElementById("huma_freeze").value =star[x][5]; 
 document.getElementById("huma_designation").value =star[x][6]; 
 document.getElementById("huma_reporting").value =star[x][7];
 document.getElementById("city_id").value =star[x][8];//place of posting
 document.getElementById("huma_dob").value =star[x][9];
 document.getElementById("huma_doj").value =star[x][10];
 document.getElementById("huma_address").value =star[x][11];
 document.getElementById("huma_pin").value =star[x][12];
 document.getElementById("huma_email").value =star[x][13];
 document.getElementById("huma_phone").value =star[x][14];
 document.getElementById("huma_mobile").value =star[x][15];
 document.getElementById("HR_Status").value =star[x][16];
 document.getElementById("unit_id").value =star[x][17];
 //alert("checking "+star[x][18]);
 if(star[x][18] == "")
 {
 var rows=(star[x].length-18)/7;
 //alert("rows="+rows);
 //var i=17;//here 17 is the no of fields in cont_mstr form,7 is the noof repeated fields in star
 var i=18;
 document.form1.add.disabled=false;//newly added for dynamic table
 for(var k = 0; k < rows-1; k++)//row-1 because one row has already created
    {
     //alert("K value="+k);
     $(".addRow").trigger('click'); 
     //alert("hey going to assign the values to the dynamic table contents");
    }
 //var sno = document.getElementsByName('sno');
 var status = document.getElementsByName('status');
 var effectdate = document.getElementsByName('effectdate');
 var currentunit = document.getElementsByName('currentunit');
 var transferedunit = document.getElementsByName('transferedunit');
 var Description = document.getElementsByName('Description');
 var seqid = document.getElementsByName('seqid');
 
 for(var k = 0; k < rows; k++)
 { 
 	//alert("hey inside the for loop and assigning values to dynamic fields gor "+k+" time");
   //sno[k].value =star[x][i]; i++;
  
   i++;
   status[k].value ="";
   document.getElementById("FRS_Status").value = "";
   /* if(status[k].value==1)
   {
   transferedunit[k].readOnly = false;
   currentunit[k].readOnly = false;
   //transferedunit[k].disabled = false;
   //currentunit[k].disabled = false;;
   }
   else
   {
   transferedunit[k].readOnly = true;
   currentunit[k].readOnly = true;
   //transferedunit[k].disabled = true;
   //currentunit[k].disabled = true;
   } */
   i++;
   
   effectdate[k].value ="";
   //alert("The date is "+star[x][i]);
    i++;
   currentunit[k].value = "";//Crosscheck no increment is happening here------------------
   
   Description[k].value =""; i++;
   seqid[k].value =""; i++;
   currentunit[k].value =""; i++;
   transferedunit[k].value =""; i++;
  }//for (var i = 0; i < rows; i++)
 
 }else
 {
 //alert("Inside the Else block");
 //Newly Added By Rajesh
 //var rows=(star[x].length-17)/7; //alert("hey end of the show() ,and dynamic rows="+rows);
 var rows=(star[x].length-18)/7;
 //alert("rows="+rows);
 //var i=17;//here 17 is the no of fields in cont_mstr form,7 is the noof repeated fields in star
 var i=18;
 document.form1.add.disabled=false;//newly added for dynamic table
 for(var k = 0; k < rows-1; k++)//row-1 because one row has already created
    {
     //alert("K value="+k);
     $(".addRow").trigger('click'); 
     //alert("hey going to assign the values to the dynamic table contents");
    }
 //var sno = document.getElementsByName('sno');
 var status = document.getElementsByName('status');
 var effectdate = document.getElementsByName('effectdate');
 var currentunit = document.getElementsByName('currentunit');
 var transferedunit = document.getElementsByName('transferedunit');
 var Description = document.getElementsByName('Description');
 var seqid = document.getElementsByName('seqid');
 
 for(var k = 0; k < rows; k++)
 { 
 	//alert("hey inside the for loop and assigning values to dynamic fields gor "+k+" time");
   //sno[k].value =star[x][i]; i++;
  
   i++;
   status[k].value =star[x][i];
   document.getElementById("FRS_Status").value = star[x][i];
   if(status[k].value==1)
   {
   transferedunit[k].readOnly = false;
   currentunit[k].readOnly = false;
   //transferedunit[k].disabled = false;
   //currentunit[k].disabled = false;;
   }
   else
   {
   transferedunit[k].readOnly = true;
   currentunit[k].readOnly = true;
   //transferedunit[k].disabled = true;
   //currentunit[k].disabled = true;
   }
   i++;
   
   effectdate[k].value =star[x][i];
   //alert("The date is "+star[x][i]);
    i++;
   currentunit[k].value = star[x][17];//Crosscheck no increment is happening here------------------
   
   Description[k].value =star[x][i]; i++;
   seqid[k].value =star[x][i]; i++;
   currentunit[k].value =star[x][i]; i++;
   transferedunit[k].value =star[x][i]; i++;
  }//for (var i = 0; i < rows; i++)
 }//else 
 //New code ends here
// document.getElementById("huma_photo").value = star[x][14];
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
 document.form1.huma_freeze.value='';
 document.form1.huma_address.value='';
 document.form1.huma_photo.value='';
 document.form1.huma_freeze.disabled=true;
 document.form1.huma_address.disabled=true;
 document.form1.huma_photo.disabled=true;
 
 document.form1.unit_id.disabled = false;
 document.form1.unit_id.style.backgroundColor = 'skyblue';
 //document.form1.area_id.value='abc';
 //document.form1.comp_id.value='';
 //document.form1.huma_id.value="<%=curhuma_id%>";
 document.form1.huma_id.value="";
		document.form1.huma_id.disabled = false;//3 lines, newly added code for the Query functionality
		document.form1.huma_id.readOnly = false;
		document.form1.huma_id.style.backgroundColor = 'skyblue';
		//document.form1.huma_id.focus();
		
		document.form1.unit_id.disabled = false;
		document.form1.unit_id.readOnly = false;
		document.form1.unit_id.style.backgroundColor = 'skyblue';
		document.form1.unit_id.focus();
		
		document.form1.area_id.disabled = false;
		document.form1.area_id.readOnly = false;
		document.form1.area_id.style.backgroundColor = 'skyblue';
		document.form1.area_id.focus();
		document.form1.huma_id.value="<%=request.getSession().getAttribute("huma_id")%>";
		
		document.form1.huma_designation.disabled = false;
		document.form1.huma_designation.readOnly = false;
		document.form1.huma_designation.style.backgroundColor = 'skyblue';
		document.form1.huma_designation.focus();
		
		document.form1.huma_reporting.disabled = false;
		document.form1.huma_reporting.readOnly = false;
		document.form1.huma_reporting.style.backgroundColor = 'skyblue';
		
		document.form1.HR_Status.disabled = false;
		//document.form1.huma_reporting.readOnly = false;
		document.form1.HR_Status.style.backgroundColor = 'skyblue';
		
		document.form1.FRS_Status.disabled = false;
		//document.form1.huma_reporting.readOnly = false;
		document.form1.FRS_Status.style.backgroundColor = 'skyblue';
		
		document.form1.huma_id.focus();
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
		document.getElementById("cal2").style.display = 'none';
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
		document.form1.huma_freeze.value = '';
		document.form1.huma_address.value = '';
		document.form1.huma_photo.value = '';
		document.form1.Description.value = '';
		document.form1.huma_freeze.disabled = true;
		document.form1.huma_address.disabled = true;
		document.form1.Description.disabled = true;
		document.form1.huma_photo.disabled = true;
		//document.form1.status.value='';
		
		var textareas = f.getElementsByTagName("textarea"); //alert("hey you got the textareas[]"+textareas);
       for(var j = 0; j < textareas.length; j++)
        { 
          if(textareas[j].style.display!="none")//new added one
          textareas[j].value='';//new added one 
          textareas[j].disabled=true;
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
 
        document.form1.status.disabled=true;
        document.form1.status.value="";
        document.form1.Description.value="";
        document.form1.Description.disabled=true;
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
		document.getElementById("cal2").style.display = 'inline';
		var f = document.getElementById("form1");
		//var inputs = f.getElementsByTagName("select");
		for ( var i = 0; i < f.length; i++) {
			if (f[i].type != "button") {
				f[i].disabled = false;
				f[i].style.backgroundColor = '';//new added one to remove any background color
			}
		}
		document.form1.sno.value=1;
		document.form1.add.disabled=false;
		document.form1.transferedunit.disabled = false;
		document.form1.currentunit.disabled = false;
		document.form1.huma_address.disabled = false;
		document.form1.Description.disabled = false;
		document.form1.huma_photo.disabled = true;//make false to enable the photo field
		//instead of automatic id generation allowing user to enter the id----------------------------
		document.getElementById("huma_id").readOnly = false;
		document.form1.FRS_Status.disabled = true;
	  //document.form1.cont_id.readOnly=true;//if it was made editable in queryfunction
 		$("#tableID tr:gt(1)").remove();//removing all the rows exception first two rows in current table//newly added for dynamic table
 		document.form1.remove.style.display='none';//newly added for dynamic table
 		document.form1.add.disabled=false;//newly added for dynamic table
		//document.form1.schedule_id.value=1;//newly added for dynamic table
	
	//	document.form1.currentunit.value = document.form1.unit_id.value;
		
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
	<script type="text/javascript" src="JS/Amenu.js"></script>
	<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if((role.equals("admin"))||(role.equals("audit")))// ||role.equals("areahead") || role.equals("unithead"))
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
						<legend class="formTitle">Human Resource Master </legend>
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
										<input name="unit_id" id="unit_id" type="text" size="35"
											onKeyUp="allowupdate();"
											onFocus="addSuggestion('unit_id2','unit_id');" maxlength="30"
											style="border-color: #0099FF;"
											onBlur="showEmp('unit_id.area_id.comp_id',unit_id.value,'');" />
									</div></td>
							</tr>
							<tr>
								<td width="24%" height="" align="right" class="style22"><div
										align="right">Region id</div></td>
								<td width="76%" align="left"><div align="left">
										<input name="area_id" id="area_id" type="text" size="35"
											onFocus="addSuggestion('area_id','area_id');" maxlength="30" readonly="" />
									</div></td>
							</tr>
							<tr>
								<td height="26" align="right" class="style22"><div
										align="right">Company id</div></td>
								<td align="left"><div align="left">
										<input name="comp_id" id="comp_id" type="text" size="35"
											readonly="" />
									</div></td>
							</tr>
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">Employee id</div></td>
								<td align="left"><div align="left">
										<input name="huma_id" id="huma_id" type="text" size="35"
											onFocus="addSuggestion('Huma_huma_id','huma_id');" maxlength="55"
											readonly="" onKeyUp="allowupdate();" />
									</div></td>
							</tr>
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">First name</div></td>
								<td align="left"><div align="left">
										<input name="huma_fname" id="huma_fname" type="text"
											maxlength="35" size="35" onKeyUp="allowupdate();" />
									</div></td>
							</tr>
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">Last name</div></td>
								<td align="left"><div align="left">
										<input name="huma_lname" type="text" id="huma_lname"
											onKeyUp="allowupdate();" size="35" maxlength="30" />
									</div></td>
							</tr>
							<!-- <tr>
      <td height="22" class="style22" align="right"><div align="right">Freeze emp</div></td>
      <td align="left"><div align="left">
          <select name="huma_freeze" id="huma_freeze" onchange="allowupdate();">
            <option value="" selected="selected"></option>
            <option value="NO">NO</option>
            <option value="YES">YES</option>
           </select>
      </div></td>
    </tr>  -->
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">Grade</div></td>
								<td align="left">
									<div align="left">
										<input name="huma_designation" type="text"
											id="huma_designation" style="border-color: #0099FF;"
											onKeyDown="allowupdate();"
											onFocus="addSuggestion('grade_id','huma_designation');"
											size="35" maxlength="60" /> <input type="hidden"
											name="huma_freeze" id="huma_freeze" onChange="allowupdate();">
									</div>
								</td>
							</tr>
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">Reporting Officer</div></td>
								<td align="left"><div align="left">
										<input name="huma_reporting" type="text" id="huma_reporting"
											style="border-color: #0099FF;" onKeyDown="allowupdate();"
											onFocus="addSuggestion('huma_id','huma_reporting');"
											size="35" maxlength="55" />
									</div></td>
							</tr>
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">Place of posting</div></td>
								<td align="left"><div align="left">
										<input name="city_id" type="text" id="city_id"
											style="border-color: #0099FF;" onKeyDown="allowupdate();"
											onFocus="addSuggestion('city_id','city_id');" size="35"
											maxlength="60" />
									</div></td>
							</tr>
							<tr>
								<td height="25" class="style22" align="right"><div
										align="right">D O B</div></td>
								<td align="left"><div align="left">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="27%">
													<div align="left">
														<input type="text" name="huma_dob" id="huma_dob" size="10"
															maxlength="10" onKeyUp="allowupdate();" /> <a href="#"
															onclick="setYears(1947, 2050);
       showCalender(this, 'huma_dob');">
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
								<td height="25" class="style22" align="right"><div
										align="right">D O J</div></td>
								<td align="left"><div align="left">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="27%"><div align="left">
														<input type="text" name="huma_doj" id="huma_doj" size="10"
															maxlength="10" onKeyUp="allowupdate();" /> <a href="#"
															onclick="setYears(1947, 2050);
       showCalender(this, 'huma_doj');">
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
								<td height="38" class="style22" valign="top" align="right"><div
										align="right">Residential Address</div></td>
								<td align="left">
									<div align="left">
										<textarea name="huma_address" rows="2" id="huma_address"
											onKeyUp="EnforceMaximumLength(this,100); allowupdate();"
											onBlur="EnforceMaximumLength(this,300);"></textarea>
										<!--  
          <br>
          Pin code &nbsp;  
          <input name="huma_pin" id="huma_pin" type="text" size="10" maxlength="6" onkeyup="allowupdate();"/>      
         -->
									</div>
								</td>
							</tr>
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">Pin code &nbsp;</div></td>
								<td align="left"><label for="textfield"></label>
									<div align="left">
										<input name="huma_pin" id="huma_pin" type="text" size="10"
											maxlength="6" onKeyUp="allowupdate();" />
									</div></td>
							</tr>
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">Email</div></td>
								<td align="left"><label for="textfield"></label>
									<div align="left">
										<input name="huma_email" type="text" id="huma_email"
											maxlength="35" size="30" onKeyUp="allowupdate();" />
									</div></td>
							</tr>
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">Phone</div></td>
								<td align="left"><div align="left">
										<input name="huma_phone" type="text" id="huma_phone"
											onKeyUp="allowupdate();" size="13" maxlength="12" />
										(Ex:04039162116)
									</div></td>
							</tr>
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">Mobile</div></td>
								<td align="left"><div align="left">
										<input name="huma_mobile" type="text" id="huma_mobile"
											onKeyUp="allowupdate();" size="14" maxlength="13" />
										(Ex:919396812884)
									</div></td>
							</tr>
							<!-- Added by Rajesh -->
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">Current Status (At FRS)</div></td>
								<td align="left"><div align="left">
									<!-- 	<input name="FRS_Status" type="text" id="FRS_Status"
											onKeyUp="allowupdate();" readonly="readonly"/>
										 -->
									<select name="FRS_Status" id="FRS_Status" size="1" 
												onChange="allowupdate(); showTransferedUnit('transferedUnit',this,this.value);">
				<option value="">Select</option>
				<%
				Connection con15 = null;
				Statement stmt15 = null;
				ResultSet rs15 = null;
 try{
 JdbcConnect jc=new JdbcConnect();  
 //System.out.println("JdbcConnect jc="+jc);
 con15 = jc.getConnection();  
 stmt15 = con15.createStatement();  
 rs15 = stmt15.executeQuery("select HRSTATUS_id,HRSTATUS_NAME from hrstatus_mstr");  
   while(rs15.next()){
   //System.out.println("Option value= "+rs15.getString(1));
   %>
   <option value="<%=rs15.getString(1)%>"><%=rs15.getString(2)%></option>
   
   <%
   }  
 }
 catch(Exception e){
     System.out.println(e);
 }
 finally 
	{
	if(rs15!=null)rs15.close();
	if(stmt15!=null)stmt15.close();
	if(con15!=null)con15.close();
	}
 %>
 	
 	</select>				
 	
									</div></td>
							</tr>
							
							<tr>
								<td height="22" class="style22" align="right"><div
										align="right">Current Status (At HR)</div></td>
								<td align="left"><div align="left">
										<input name="HR_Status" type="text" id="HR_Status" style="border-color: #0099FF;"
											onKeyUp="allowupdate();" onFocus="addSuggestion('HR_Status','HR_Status');"/>
										
									</div></td>
							</tr>
							<tr style="visibility: hidden;">
								<td height="36" class="style22" valign="top" align="right"><div
										align="right">Photo</div></td>
								<td align="left"><input name="huma_photo" type="file"
									id="huma_photo" size="12" maxlength="100"
									onChange="allowupdate();" /> <label> <label></label> <font
										style=""> (max 10kb) <br /> <a href="photo.txt"
											target="_blank">know how to make photo 10kb</a></font></td>
							</tr>
							
							<!-- Added by Rajesh -->
							
						<!-- 	<table id="tableID" width="100%"  border="1" style="border-style:outset; border-color:blue" >   -->
					<tr><td align="center" colspan="2"><table id="tableID" width="100%" style="border:1px solid #708090;width: 100%" align="center">
                  <tr style="background-image:url(images/TableHeadBg.gif)">
                  <!-- <tr bgcolor="back03.gif" style="opacity: 0.65; filter: alpha(opacity=65);"> -->
                    <th width="1%" scope="col" class="style21"><div align="center">Sno</div></th>
                    <th width="6%" scope="col" class="style21"><div align="center">Status</div></th>
                    <th width="10%" scope="col" class="style21"><div align="center">Effect Date<br>(dd-mm-yyyy)</div></th>
                    <th width="10%" scope="col" class="style21"><div align="center">Current Unit NO</div></th>
                    <th width="10%" scope="col" class="style21"><div align="center">Transfered Unit NO</div></th>
                    <th width="10%" scope="col" class="style21"><div align="center">Description</div></th>
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
 
 				
				<select name="status" id="status" size="1"
												onChange="allowupdate(); showTransferedUnit('transferedUnit',this,this.value);">
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
 rs = stmt.executeQuery("select HRSTATUS_id,HRSTATUS_NAME from hrstatus_mstr");  
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
 					
 					
 					<td><div align="center"><input name="effectdate" type="text" id="effectdate"  width:95%" onkeyup="allowupdate();"/></div></td>
 					<td><div align="center"><input name="currentunit" type="text" id="currentunit"  width:95%" onkeyup="allowupdate();" readonly="readonly"/></div></td>
 					<td><div align="center"><input name="transferedunit" type="text" id="transferedunit"  style="border-color:#0099FF; width:95%" onkeyup="allowupdate();" onfocus="addSuggestion('unit_id2','transferedunit');"/></div></td>
 					<td><div align="center">
 					<textarea name="Description" id="Description"  width:95%" onkeyup="allowupdate();"></textarea>
 					<input name="seqid" type="hidden" id="seqid"/>
 					</div></td>
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
											<td><script type="text/javascript" src="JS/Abuttons.js"></script>
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
												onClick="showEmpn('huma_mstr',huma_id.value,unit_id.value,area_id.value,huma_designation.value,huma_reporting.value,HR_Status.value,FRS_Status.value);"
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
