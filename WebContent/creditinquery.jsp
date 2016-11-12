<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.awt.geom.Area"%>
<%@page import="frs_cls.JdbcConnect" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Credit Inquiry form</title>
<% 
 if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
 {
    response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to login page.
	}

else //if (((HttpServletRequest) request).getSession().getAttribute("user") != null)
    {
   // chain.doFilter(request, response); // Logged in, so just continue.
%>
<%//getting BSFLUNIT_UCODE to assign in BSFLUNIT_UCODE field automatically in enable() function

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
  			
				//Connection con=new JdbcConnect().getConnection();
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
  
/*	if(username.equals("basix"))
	{
	uname_no="";
	aname="";
	}*/
  
%>
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="JS/jquery.min.js"></script>
  <script type="text/javascript" src="JS/jquery-ui.min.js"></script>
  <!--<script type="text/javascript" src="JS/jquery-1.2.6.js"></script>-->
  <script type="text/javascript" src="JS/jquery.formatCurrency.js"></script>
<%@ page import="java.sql.*" %>
<jsp:useBean id="dbConn" scope="request" class="frs_cls.JdbcConnect"/> 
<%!
   Connection con=null,con1=null;
   PreparedStatement ps2=null;//,ps3=null,ps4=null,ps5=null;
   ResultSet rs2=null;//,rs3=null,rs4=null,rs5=null;
   //int f=0;
    //String e=null;
	//int e=0;
%>
<%
    	String driver = application.getInitParameter("driver");
		String url = application.getInitParameter("url");
		String user = application.getInitParameter("user");
		String pwd = application.getInitParameter("pwd");
		try{
		con= dbConn.getConnection();
%>

<style>
input.addRow{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:24px;     background-image:url(images/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px;}
input.delRow{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:24px;     background-image:url(images/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px;}
</style>
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
		  document.getElementById("area_name").value ='';
		 //document.getElementById("comp_id").value =" ";
	 }
	 else if(strar.length>0)
	 {
		document.form1.area_name.value=strar[0];
	 }//else
	}//if(key1>=4)
	else //if(key1<4)
	{
	document.getElementById("area_name").value ='';
	return false;
	}
}//showEmp(decide,key1,key2)


 function goLite2(BTN) 
 {		 
	BTN.style.color =  "#ffffff";
	BTN.style.backgroundImage="url(images/back04.gif)";
 }
 function goDim2(BTN)
 {   
	BTN.style.color = "";
	BTN.style.backgroundImage="url(images/back03.gif)";
 }  

var j=0,emp2='';
var star2;//,i;//global variable
function showEmpn2(decide,obj,emp)
{//alert("hey you came to showEmpn,the supllied string is="+emp);
//if (emp.length<4) return;//just stoping execution if value is less 
 var par=obj.parentNode; 
 while(par.nodeName.toLowerCase()!='tr'){   par=par.parentNode;  } //alert("the index of the current row is"+par.rowIndex); 
 var i=par.rowIndex;

var ops_activitysl4 = document.getElementsByName('UHLOG_REMARKS');//alert("hety got the ops_activitysl again="+ops_activitysl4);
 var n=ops_activitysl4.length;
 j=i-1;
 emp2=emp;

var ACTCAT_ID = document.getElementsByName('ACTCAT_ID');//alert("hety got the fpo_id="+fpo_id);
		 var ACTIVITY_ID = document.getElementsByName('ACTIVITY_ID');//alert("hety got the fpo_id="+fpo_id);
		addSuggestion2('ACTIVITY_ID','ACTIVITY_ID',ACTCAT_ID[j].value);
	// }//else if(star2.length>=0)
}//showEmpn2();
</script>
<script type="text/javascript" language="javascript">
function validateForm()
{
   var patt1=new RegExp("[A-Za-z]");//var patt1=new RegExp("^[A-Za-z]+[0-9]*[ A-Za-z0-9]*$");
   var patt2=new RegExp("[A-Za-z0-9]");
   var reason=new RegExp("(\/\/\/\/\/\/|::::::)");

  var humaid=new RegExp("[0-9]{3}");///[0-9]{2}-[0-9]{3}/P[0-9]{6}");
  if(document.form1.BSFLUNIT_UCODE.value=='')
  {//alert("hey the save is inline and the BSFLUNIT_UCODE is=''");
   alert("Please enter the unit numer correctly");
   document.form1.BSFLUNIT_UCODE.focus();
   return false;
  }
  else//if(document.form1.BSFLUNIT_UCODE.value!='')
  { //alert("hey inside the if: means the BSFLUNIT_UCODE is !=''");
   var id=document.form1.BSFLUNIT_UCODE.value.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
       id=id.substr(0,3);
	   //alert("hey the id is="+id);
   if(!humaid.test(document.form1.BSFLUNIT_UCODE.value))
     {
       alert("Please enter the Unit code correctly");
	   document.form1.BSFLUNIT_UCODE.focus();
	   return false;
	 }
   }//else//if(document.getElementById("BSFLUNIT_UCODE").value!='')
/*   if((!patt1.test(document.form1.ops_desc.value))||(reason.test(document.form1.ops_desc.value)))
    {
	//if(!patt1.test(document.form1.ops_desc.value))
    //{
      alert("Please enter the UH Service Entry Description correctly");
      document.form1.ops_desc.focus();
      return false;
    }*/
  var date=new RegExp("[0-9]");
//UHLOG_DATE validation
	if(!date.test(document.form1.UHLOG_DATE.value))
    {
      alert("Please enter the Date");
	  document.form1.UHLOG_DATE.focus();
      return false;
    }
	if(document.form1.UHLOG_DATE.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(document.form1.UHLOG_DATE.value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	var returnval=false;//validformat
	if(!validformat.test(document.form1.UHLOG_DATE.value))
	{
	alert("Please enter the Date correct format");
	document.form1.UHLOG_DATE.focus();
	return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		alert("Invalid month or date found in Date");
		document.form1.UHLOG_DATE.focus();
		return false;
	  }
	 }//if(UHLOG_DATE!="") 
//date comparision code   
	   var currentTime = new Date();
       var mm = currentTime.getMonth() + 1;
       var dd = currentTime.getDate();
       var yyyy = currentTime.getFullYear();
       var sysdate=dd+"-"+mm+"-"+yyyy;
	   sysdate=sysdate.split("-");
	   var sysdate = new Date(sysdate[2], sysdate[1]-1, sysdate[0]); //var date1 = new Date(yr1, mon1, dt1); 

	   var ldate=(document.form1.UHLOG_DATE.value).split("-");
       var ldate = new Date(ldate[2], ldate[1]-1, ldate[0]); //alert("hey to rday date is ="+prdate);
	   if(ldate > sysdate)
     	{
		 alert("selected Date should not more than the System date");
		 document.form1.UHLOG_DATE.focus();
		 return false; 
	     }
//date comparision code exit here*/
//validation for the dynamic payment schedule table
	 
    var UHLOG_REMARKS = document.getElementsByName('UHLOG_REMARKS');
	var ACTIVITY_ID = document.getElementsByName('ACTIVITY_ID');
	var VCODE = document.getElementsByName('VCODE');
    var estbl_id = document.getElementsByName('estbl_id');
    var UHLOG_AMTSPENT = document.getElementsByName('UHLOG_AMTSPENT');
    var UHLOG_OUTREACH = document.getElementsByName('UHLOG_OUTREACH');
	var UHLOG_ODCUST = document.getElementsByName('UHLOG_ODCUST');//var ops_narration = document.getElementsByName('ops_narration');
	var UHLOG_ODAMT = document.getElementsByName('UHLOG_ODAMT');
	var n= UHLOG_REMARKS.length;
    for (var k = 0; k < n; k++)
    {
//village c

//alert("MSR DEBUG VCODE");
  if(VCODE[k].value=='')  
   {
	 alert("Please Select village");
	 VCODE[k].focus();
 	 return false;
	}
//alert("MSR DEBUG ACTIVITY");
//ACTIVITY_ID validation start here	 
  if(ACTIVITY_ID[k].value=='')  
   {
	 alert("Please select an Activity");
	 ACTIVITY_ID[k].focus();
 	 return false;
	}


//UHLOG_AMTSPENT validation start here	 
  var Area=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
  if(UHLOG_AMTSPENT[k].style.visibility=='visible')
  {
   if((!Area.test(UHLOG_AMTSPENT[k].value)) || (UHLOG_AMTSPENT[k].value.length < 1))// || (Number(UHLOG_AMTSPENT[k].value) == 0))
   { //alert("hey the inside ops_place");
	 alert("Please enter the Amt spent (Rs) correctly"); 
	 UHLOG_AMTSPENT[k].focus();
	 return false;
	 }
	}

//UHLOG_OUTREACH validation start here	 
   var Area=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
   if(UHLOG_OUTREACH[k].style.visibility=='visible')
   {//alert("the number after the point="+UHLOG_OUTREACH[k].value.split('.')[1].length);
    if((!Area.test(UHLOG_OUTREACH[k].value)) || (UHLOG_OUTREACH[k].value.length < 1) || (Number(UHLOG_OUTREACH[k].value) == 0))
    { //alert("hey the inside ops_place");
	 alert("Please enter the total outreach amount correctly (non zero)"); 
	 UHLOG_OUTREACH[k].focus();
 	 return false;
	 }
//	if((UHLOG_OUTREACH[k].value.length>10)&&(UHLOG_OUTREACH[k].value.split('.')[0].length>10))
	//{
	// alert("Please enter the Area correctly,with max of two decimals"); 
	 //UHLOG_OUTREACH[k].focus();
 	 //return false;
	// }
	}//if(UHLOG_OUTREACH[k].style.visibility=='visible') alert("the number after the point="+UHLOG_OUTREACH[k].value.split('.')[1]);
//UHLOG_ODCUST validation start here	 
   var Quantity=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
   if(UHLOG_ODCUST[k].style.visibility=='visible')
   {//alert("the number after the point="+UHLOG_ODCUST[k].value.split('.')[1].length);
    if((!Quantity.test(UHLOG_ODCUST[k].value)) || (UHLOG_ODCUST[k].value.length < 1))// || (Number(UHLOG_ODCUST[k].value) == 0))
    { //alert("hey the inside ops_place");
	 alert("Please enter the OD Custmers correctly"); 
	 UHLOG_ODCUST[k].focus();
 	 return false;
	 }
	//if((UHLOG_ODCUST[k].value.length>10)&&(UHLOG_ODCUST[k].value.split('.')[0].length>10))
//	{
	// alert("Please enter the Quantity correctly,with max of two decimals"); 
	 //UHLOG_ODCUST[k].focus();
 	 //return false;
	 //}
	}//if(UHLOG_ODCUST[k].style.visibility=='visible') alert("the number after the point="+UHLOG_ODCUST[k].value.split('.')[1]);
//UHLOG_ODAMT validation start here	 
   var Price=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
   if(UHLOG_ODAMT[k].style.visibility=='visible')
   {//alert("the number after the point="+UHLOG_ODAMT[k].value.split('.')[1].length);
    if((!Price.test(UHLOG_ODAMT[k].value)) || (UHLOG_ODAMT[k].value.length < 1))// || (Number(UHLOG_ODAMT[k].value) == 0))
    { //alert("hey the inside ops_place");
	 alert("Please enter the OD amount correctly"); 
	 UHLOG_ODAMT[k].focus();
 	 return false;
	 }
	//if((UHLOG_ODAMT[k].value.length>10)&&(UHLOG_ODAMT[k].value.split('.')[0].length>10))
//	{
	// alert("Please enter the Price correctly,with max of two decimals"); 
	 //UHLOG_ODAMT[k].focus();
 	 //return false;
	 //}
	}//if(UHLOG_ODAMT[k].style.visibility=='visible') alert("the number after the point="+UHLOG_ODAMT[k].value.split('.')[1]);
//UHLOG_ODAMT validation start here	 
  /* var Price=new RegExp("[0-9]");
   //if(UHLOG_ODAMT[k].style.visibility=='visible')
  // {//alert("the number after the point="+UHLOG_ODAMT[k].value.split('.')[1].length);
    if((!Quantity.test(UHLOG_ODAMT[k].value)) || (UHLOG_ODAMT[k].value.length < 1))// || (Number(UHLOG_ODAMT[k].value) == 0))
    { //alert("hey the inside ops_place");
	 alert("Please enter the OD amount correctly"); 
	 UHLOG_ODAMT[k].focus();
 	 return false;
	 }
	*///if((UHLOG_ODAMT[k].value.length>10)&&(UHLOG_ODAMT[k].value.split('.')[0].length>10))
//	{
	// alert("Please enter the Price correctly"); 
	 //UHLOG_ODAMT[k].focus();
 	 //return false;
	 //}
	//}//if(UHLOG_ODAMT[k].style.visibility=='visible') alert("the number after the point="+UHLOG_ODAMT[k].value.split('.')[1]);
//UHLOG_REMARKS validation start here	 
//UHLOG_REMARKS validation start here	 
	 if((!patt1.test(UHLOG_REMARKS[k].value))||(reason.test(UHLOG_REMARKS[k].value)))
      {
        alert("Please enter the Remarks correctly");
        UHLOG_REMARKS[k].focus();
        return false;
      }
	}//for (var k = 0; k < n; k++) 
//Duplicate Farmer Activity Entries validation starts here
 for (var i = 0; i < n; i++)
   for (var j = 0; j < n; j++)
   {//alert("the visibility="+ACTIVITY_ID[i].style.visibility); 
   if(i!=j)// if((patt1.test(UHLOG_REMARKS[i].value)) && (patt1.test(UHLOG_REMARKS[j].value)))
	 {
		//-------------------------------------------
		//if((ACTIVITY_ID[i].value==ACTIVITY_ID[j].value)&&(fpo_id[i].value==fpo_id[j].value)&&(pg_id[i].value==pg_id[j].value)&&(estbl_id[i].value==estbl_id[j].value)&&(UHLOG_OUTREACH[i].value==UHLOG_OUTREACH[j].value)&&(UHLOG_ODCUST[i].value==UHLOG_ODCUST[j].value)&&(UHLOG_ODAMT[i].value==UHLOG_ODAMT[j].value)&&(ops_place[i].value==ops_place[j].value))
		if((ACTIVITY_ID[i].value==ACTIVITY_ID[j].value)&&(VCODE[i].value==VCODE[j].value))
		{
		 alert("Duplicate rows found with similar values (Activity and Village), Use common description & make single row");
		 ACTIVITY_ID[j].focus();
        return false;
		}
		//--------------------------------------------
		/*if(ACTIVITY_ID[i].value==ACTIVITY_ID[j].value)
		{ //alert("the visibility client_name visibility="+client_name3[i].style.visibility);
        //Checkin for the only services required activities
		if((fpo_id[i].style.visibility=='hidden') && (fpo_id[j].style.visibility=='hidden'))
		if((pg_id[i].style.visibility=='hidden') && (pg_id[j].style.visibility=='hidden'))
		if((estbl_id[i].style.visibility=='hidden') && (estbl_id[j].style.visibility=='hidden'))
		if((UHLOG_ODCUST[i].style.visibility=='hidden') && (UHLOG_ODCUST[j].style.visibility=='hidden'))
		if((UHLOG_ODAMT[i].style.visibility=='hidden') && (UHLOG_ODAMT[j].style.visibility=='hidden'))
		if((ops_place[i].style.visibility=='hidden') && (ops_place[j].style.visibility=='hidden'))
        {
		  alert("Duplicate Farmer Activity Entries found,Pls differ Activity");//differ your client names when using same Activity
 		  return false;
		  }
        //Checkin for the only services and fpo_id required activities
		//if((fpo_id[i].style.visibility=='hidden') && (fpo_id[j].style.visibility=='hidden'))
		if(ops_place[i].value.substr(0,7)==ops_place[j].value.substr(0,7))
		{
		if((fpo_id[i].style.visibility=='hidden') && (fpo_id[j].style.visibility=='hidden'))
	    if(UHLOG_ODCUST[i].value==UHLOG_ODCUST[j].value)
		{
		  alert("Duplicate Farmer Activity Entries found,Pls differ Quantity or place");//differ your client names when using same Activity
 		  return false;
		  }
	    if((UHLOG_ODCUST[i].style.visibility=='hidden') && (UHLOG_ODCUST[j].style.visibility=='hidden'))
		if(fpo_id[i].value.substr(0,7)==fpo_id[j].value.substr(0,7))
        {
		  alert("Duplicate Farmer Activity Entries found,Pls differ FPO or place");//differ your client names when using same Activity
 		  return false;
		  }		  
		if(((UHLOG_ODCUST[i].style.visibility=='visible') && (UHLOG_ODCUST[j].style.visibility=='visible'))&&((fpo_id[i].style.visibility=='visible') && (fpo_id[j].style.visibility=='visible')))
		if((UHLOG_ODCUST[i].value==UHLOG_ODCUST[j].value) && (fpo_id[i].value.substr(0,7)==fpo_id[j].value.substr(0,7)))
        {
		  alert("Duplicate Farmer Activity Entries found,Pls differ either FPO or Quantity");//differ your client names when using same Activity
 		  return false;
		  }		  
		}//if(ops_place[i].value.substr(0,7)==ops_place[j].value.substr(0,7))
	  }//if(ACTIVITY_ID[i].value==ACTIVITY_ID[j].value)
	 */}//if(i!=j)
	}//for
//alert("End of the validationForm() function");	
}//validateForm();
//hey the update&save butons(submission) code starts here---------------------------------------------------------
$(document).ready(function(){//alert("hey inside the ready function of jquery update");
			
		$("#form1").submit(function(){sessioncheck();//alert("hey inside the submit function");
		   if(document.form1.onSubmit==validateForm()) 
			{  var allElements=$(this).serialize();//alert("hey the validation is done");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			if(document.form1.save.style.display=='inline')
			  var program='suhlogm';	
			else  
			   var program='uuhlogm'; //alert("hey the program is="+program);	
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
    
	//$(".useDefault").addDefaultText();
	$(".addRow").btnAddRow({rowNumColumn:"rowNumber",maxRow:0});
	$(".delRow").btnDelRow();
	//$(":text").labelify();
	//alert("hey end of the ready function");
//Currency converter function code
//$('#UHLOG_ODAMT').blur(function() { if($('#UHLOG_ODAMT').val()!='') $('#UHLOG_ODAMT').formatCurrency(); });
	});//ready function
//the save& update buttons submission code ends here------------------------------------------------------------
function colon(fld,len) 
{//alert("hey the value="+fld.value);
//alert("hey the third position value is="+fld.value.substr(2,1));
var e = event.keyCode;//alert(e);//var ename=String.fromCharCode(e);//here len=2
if((e!="46")&&(e!="8")&&(e!="37")&&(e!="38")&&(e!="39")&&(e!="40"))
{
 if(fld.value.substr(1,len)!=":")
 {
 if((fld.value.length == len) ||(fld.value.length == len+1))
  if((fld.value.substr(0,1)!=":")&&(fld.value.substr(0,1)!=":")) { fld.value = fld.value.substr(0,len)+":"; /*document.form1.huma_pin.focus();*/fld.focus(); }
 if(fld.value.length >= len+1){//alert("hey the value="+fld.value);
  if((fld.value.substr(0,1)!=":") && (fld.value.substr(1,1)!=":")) { //alert("HETY THE THIRD VALUE="+fld.value.substr(2,1)); 
  fld.value = setCharAt(fld.value,2,':');; /*document.form1.huma_pin.focus();*/fld.focus(); }
  }}
}
}//colon(fld,len) //if(fld.value.substr(2,len+1)!=":")
function EnforceMaximumLength(fld,len) 
{
 if(fld.value.length > len) { fld.value = fld.value.substr(0,len-2); /*document.form1.huma_pin.focus();*/fld.focus(); }
}//EnforceMaximumLength(fld,len) 
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
		 document.getElementById("UHLOG_DATE").value ='';
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
 {//alert("hey inside the for and controle no="+star[x2][5]);
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
 //alert("hey assingning the common static fields");
 document.getElementById("UHLOG_CONTROLENO2").value =star[x][0];
 document.getElementById("BSFLUNIT_UCODE").value =star[x][1];
 document.getElementById("area_name").value =star[x][2];
 document.getElementById("UHLOG_DATE").value =star[x][3];
 //document.getElementById("generate").style.visibility='visible';// makes generate visible if the addTable method is not ging call & the previous call make generate hidden
  count=c2[x1];//alert("hey the row count in show function is="+count); 
  for(var i=1; i<count; i++)  $(".addRow").trigger('click'); //alert("hey after dynamic table generation");
 //assigning values to dynamic fields
 var VCODE = document.getElementsByName('VCODE');
 var ACTIVITY_ID = document.getElementsByName('ACTIVITY_ID');
 var ACTCAT_ID = document.getElementsByName('ACTCAT_ID');
 var UHLOG_AMTSPENT = document.getElementsByName('UHLOG_AMTSPENT');
 var UHLOG_OUTREACH = document.getElementsByName('UHLOG_OUTREACH');
 var UHLOG_ODCUST = document.getElementsByName('UHLOG_ODCUST');//var ops_narration = document.getElementsByName('ops_narration');
 var UHLOG_ODAMT = document.getElementsByName('UHLOG_ODAMT');
 var UHLOG_REMARKS = document.getElementsByName('UHLOG_REMARKS');
 //alert("hey the assignment to the dinemic fields is startted,c2[x1]="+c2[x1]);
 var k = x;
 for (var j = 0; j < c2[x1]; j++)
 {//alert("hinsiede the for loop,and the  head="+star[k][2]+"--");
  if(k>x+c2[x1])
   break; //alert("the activitsl_id="+star[k][5]);
  VCODE[j].value=star[k][4];//alert("the VCODE_id="+star[k][6]);
  ACTIVITY_ID[j].value=star[k][5];
  ACTCAT_ID[j].value=star[k][6];
  UHLOG_AMTSPENT[j].value=star[k][7];
  UHLOG_OUTREACH[j].value=star[k][8];
  UHLOG_ODCUST[j].value=star[k][9];
  UHLOG_ODAMT[j].value=star[k][10];
  UHLOG_REMARKS[j].value=star[k][11];
 //Currency converter function code
 
    k++;
 }// for (var j = 0; j < c2[x1]; j++)
 //alert("hey the assigning to the dinamic fields is completed");
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
<!--<script type="text/javascript" src="JS/Atop2.js"></script>-->
<script type="text/JavaScript" src="JS/calendar.js"></script>
<link rel="stylesheet" href="styles/calendar.css" type="text/css"/>


  

<script type="text/javascript">
function queryfunction() 
{ 
//Everything from disable() exceptiong button code,just for primary key enable(replace true by false) and editable(add readOnly=false)
 document.getElementById("cal1").style.display='none';
 //document.getElementById("cal2").style.display='none';
 var f = document.getElementById("form1");
 var inputs = f.getElementsByTagName("input");
 for(var i = 0; i < inputs.length; i++)
 { 
     if(inputs[i].type=="text")
   {
    inputs[i].style.visibility="visible";//new added to visible the hidden fields
	if(inputs[i].style.display!="none")//new added one
    inputs[i].value='';//new added one
	inputs[i].style.backgroundColor='';//new added one to remove any background color
    inputs[i].disabled=true;
	}
  }
  var textareas = f.getElementsByTagName("textarea"); //alert("hey you got the textareas[]"+textareas);
 for(var j = 0; j < textareas.length; j++)
 { 
    //alert("hey you got the values:"+textareas[j].value);
    if(textareas[j].style.display!="none")//new added one
    textareas[j].value='';//new added one 
    textareas[j].disabled=true;
  }
 var selects = f.getElementsByTagName("select"); //alert("hey you got the selects[]"+selects);
 for(var j = 0; j < selects.length; j++)
 { 
    //alert("hey you got the values:"+selects[j].value);
    if(selects[j].style.display!="none")//new added one
    selects[j].value='';//new added one
    selects[j].disabled=true;
  }//alert("hey going to read the deliverables of the dynamic table");
 $("#tableID tr:gt(1)").remove();//removing all the rows exception first two rows in current table//newly added for dynamic table
 document.form1.remove.style.display='none';//newly added for dynamic table
 document.form1.add.disabled=true; //newly added for dynamic table
<%
%>
  
 document.form1.BSFLUNIT_UCODE.value="<%=uname_no%>";
 document.form1.area_name.value="<%=aname%>";
 
 //document.form1.BSFLUNIT_UCODE.value='';
 //document.form1.area_name.value='';
 
 document.form1.BSFLUNIT_UCODE.disabled=false;//newly added code for the Query functionality
 document.form1.UHLOG_DATE.disabled=false;
 //document.form1.BSFLUNIT_UCODE.readOnly=false; 
 //below two lines code to remove the background color of the querying fields
 document.form1.BSFLUNIT_UCODE.style.backgroundColor='skyblue';
 document.form1.UHLOG_DATE.style.backgroundColor='skyblue';
 document.getElementById("cal1").style.display='inline';//to select the date this is needed
 //addSuggestion('BSFLUNIT_UCODE2UHLOG_SERVICE','BSFLUNIT_UCODE');//To apply LOV as per huma entries made in ops
 //addSuggestion('ops_desc','ops_desc');///for ops query with ops_desc
 document.form1.BSFLUNIT_UCODE.focus();//To focus at field cont_id at the time of query
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
{ //alert("inside the disable function");
 document.getElementById("cal1").style.display='none';
// document.getElementById("cal2").style.display='none';
 var f = document.getElementById("form1");
 var inputs = f.getElementsByTagName("input");
 for(var i = 0; i < inputs.length; i++)
 { 
   if(inputs[i].type=="text")
   {
    inputs[i].style.visibility="visible";//new added to visible the hidden fields
	if(inputs[i].style.display!="none")//new added one
    inputs[i].value='';//new added one
	inputs[i].style.backgroundColor='';//new added one to remove any background color
    inputs[i].disabled=true;
	}
  }
 var textareas = f.getElementsByTagName("textarea"); //alert("hey you got the textareas[]"+textareas);
 for(var j = 0; j < textareas.length; j++)
 { 
   // alert("hey you got the values in text area:"+textareas[j].value);
    if(textareas[j].style.display!="none")//new added one
    textareas[j].value='';//new added one 
    textareas[j].disabled=true;
  }
 var selects = f.getElementsByTagName("select"); //alert("hey you got the selects[]"+selects);
 for(var j = 0; j < selects.length; j++)
 { 
    //alert("hey you got the values in select:"+selects[j].value);
    if(selects[j].style.display!="none")//new added one
    selects[j].value='';//new added one
    selects[j].disabled=true;
  } //alert("hey going to read the deliverables of the dynamic table");
 $("#tableID tr:gt(1)").remove();//removing all the rows exception first two rows in current table//newly added for dynamic table
 document.form1.remove.style.display='none';//newly added for dynamic table
 document.form1.add.disabled=true;//newly added for dynamic table
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
{
 document.getElementById("cal1").style.display='inline';//alert("hey inside the enable function");
// document.getElementById("cal2").style.display='inline';
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
  //document.form1.cont_id.readOnly=true;//if it was made editable in queryfunction
 $("#tableID tr:gt(1)").remove();//removing all the rows exception first two rows in current table//newly added for dynamic table
 document.form1.remove.style.display='none';//newly added for dynamic table
 document.form1.add.disabled=false;//newly added for dynamic table
document.form1.ops_sno.value=1;//newly added for dynamic table

 //addSuggestion('BSFLUNIT_UCODE2','BSFLUNIT_UCODE');//To apply LOV as per huma entries made in huma_mstr
 //addSuggestion('ops_desc','ops_desc');//To apply LOV as per entries made in ops_desc to avoid duplication
 document.form1.BSFLUNIT_UCODE.value="<%=uname_no%>"; //alert("assigned unit_code is="+"<%=uname_no%>");
 document.form1.area_name.value="<%=aname%>";
  
  //document.form1.BSFLUNIT_UCODE.value='';
  //document.form1.area_name.value='';
 var MyDate = new Date();
 document.form1.UHLOG_DATE.value=('0' + MyDate.getDate()).slice(-2) + '-'
             + ('0' + (MyDate.getMonth()+1)).slice(-2) + '-'
             + MyDate.getFullYear();
/*//starting automatic id generation code when click on new button without refresh for each entrty-------------------------------   sessioncheck();
xmlHttp=GetXmlHttpObject();
 //alert("hey got the boject="+xmlHttp);
   if(xmlHttp==null)
  	  return;
    var url="getuserupdate";
    url=url+"?decide=cont_id&key1=&key2=";
    xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
    xmlHttp.send(null);//alert("the ajax request made ");
	var id = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,'');//alert("hey goy the show data is:"+id);
	document.getElementById("cont_id").value=id;//alert("hey the assignment to the cont_id field with its value is over");
 *///starting automatic id generation code when click on new button refresh for each entrty--------------------------------------- 
 //-------------common (button) code
goDim("form1","new2");
document.form1.new2.disabled=true;
document.form1.query.disabled=true;
document.form1.exct.disabled=true;//from this line newly added code for Query functionality
document.form1.save.style.display='inline';
document.form1.update.style.display='none';
}//enable()
</script>
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>
<!-- <script type="text/javascript"></script> -->
<script type="text/javascript" src="JS/jquery.table.addrow.js"></script> 
	</head>
	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();">
	
	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Cmenu.js"></script>
	<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if(role.equals("admin") || role.equals("audit") ||role.equals("areahead") || role.equals("unithead"))
{ %>
<br><form id="form1" name="form1" action="CreditReport.jsp" method="post"><!-- creditinquiry -->
<br />
<p align="right">
 <table width="1200" align="center">
  <tr>
  <td valign="top" width="1165" height="">   
  
     <table bgcolor="" width="100%" height="91%" border="0" align="center" bordercolor="#000000" style="width: 835px; ">
    
    <tr>
      <td width="100%" height="" colspan="2" align="center" style="width: 653px; ">
      <table width="80%" style="width: 1132px; "><tr>
      <td width="" style="width: 143px; "><div align="right">Customer  ID</div></td><td width="" align="left" style="width: 115px; "><input name="CustomerID" id="CustomerID" type="text" size="20"/>
       
    </tr>
    <tr>
    <td></td>
    <td><div align="left"><input type="submit" value="Get Customert Details"></div>
    </td>
    </tr>
    
    </table>
    </td></tr>
    

	 </table>
 
					</td>
				</tr>
				<tr>
					
				</tr>
			</table>
	<% }	else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to do </div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<!-- <script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>  -->

<%
}
 %>		
			<%}
 finally 
	{
	 if(rs2!=null)rs2.close();
     if(ps2!=null)ps2.close();
	 if(con!=null)con.close(); 
    }
   	%>
			
	</form>
	
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>
</body>

<%-- 
<%}
 finally 
	{
	 if(rs2!=null)rs2.close();
     if(ps2!=null)ps2.close();
	 if(con!=null)con.close(); 
    }
   	%>
   	--%>
<%
   }//authorised acess else	//------------------------------------------------------------------------------------
	%>

<!-- Calender Script  --> 

    <table background="images/calender3.gif" id="calenderTable">
        <tbody id="calenderTableHead">
          <tr>
            <td colspan="4" align="center">
	          <select onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
	           this.selectedIndex, false));" id="selectMonth">
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
	          </select>
            </td>	
            <td colspan="2" align="center">
			    <select onChange="showCalenderBody(createCalender(this.value, 
				document.getElementById('selectMonth').selectedIndex, false));" id="selectYear">
				</select>
			</td>
            <td align="center">
			    <a href="#" onClick="closeCalender();"><font color="#003333" size="+1">X</font></a>
			</td>
		  </tr>
       </tbody>
       <tbody id="calenderTableDays">
         <tr style="">
           <td>Sun</td><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td>
         </tr>
       </tbody>
       <tbody id="calender"></tbody>
    </table>

<!-- End Calender Script  -->

</html>