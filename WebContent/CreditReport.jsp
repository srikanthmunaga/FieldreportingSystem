<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.awt.geom.Area"%>
<%@page import="frs_cls.JdbcConnect" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>UH Service Entry</title>
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
  String aname=(String)((HttpServletRequest) request).getSession().getAttribute("aname");
 
  String sql="select huma_id from frs_user where username='"+username+"'";
  //String sql="select LOWER(BSFLUNIT_NAME) from BSFLUNIT_MSTR where BSFLUNIT_NAME='"+username+"'";
  //System.out.println();
  				String huma_id=null;
  				String areaid=null;
  				//String aname=null;
  				String ucode=null;
				Connection con=new JdbcConnect().getConnection();
				Statement svst=con.createStatement();
				ResultSet svrs=svst.executeQuery(sql);
				if(svrs.next())
				{
				huma_id=svrs.getString(1);
				//System.out.println("huma_id :"+huma_id);
				}//BSFLUNIT_UCODE

 				String sqlu="select BSFLUNIT_UCODE from HUMA_MSTR where huma_id='"+huma_id+"'";
  				//System.out.println();
  			
				//Connection ucon=new JdbcConnect().getConnection();
				Statement ust=con.createStatement();
				ResultSet urs=svst.executeQuery(sqlu);
				if(urs.next())
				{
				ucode=urs.getString(1);
				//System.out.println("UCODE :"+ucode);	
				
				}//BSFLUNIT_UCODE


 				String sql1="select BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE,AREA_ID from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"+ucode+"'";
  				//System.out.println();
  			
				//Connection con=new JdbcConnect().getConnection();
				Statement st=con.createStatement();
				ResultSet rs=svst.executeQuery(sql1);
				if(rs.next())
				{
				uname_no=rs.getString(1);
				areaid=rs.getString(2);
								//System.out.println("UNAME NUMBER :"+uname_no);
								//System.out.println("AREA CODE :"+areaid);
				
				}//BSFLUNIT_UCODE
				String sql2="select AREA_name from AREA_MSTR where AREA_ID='"+areaid+"'";
			//	Connection con=new JdbcConnect().getConnection();
				Statement ast=con.createStatement();
				ResultSet ars=svst.executeQuery(sql2);
				if(ars.next())
				{
				aname=ars.getString(1);
				//System.out.println(areaid);
				
				
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
<style type="text/css">
	div{
		padding:2px;
	}
div.dynTextBox{
    float: left;
    width:10px;
    margin-left: 18px;
    margin-right: 16px;
    margin-top: 10px;
}

</style>
<script language="javascript" type="text/javascript">
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
/*
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

var lsrname = document.getElementsByName('lsrname');//alert("hety got the fpo_id="+fpo_id);
var ACTIVITY_ID = document.getElementsByName('ACTIVITY_ID');//alert("hety got the fpo_id="+fpo_id);
		addSuggestion2('ACTIVITY_ID','ACTIVITY_ID',lsrname[j].value);
}//showEmpn2();
*/
</script>
<script type="text/javascript" language="javascript">
function validateForm()
{
   var patt1=new RegExp("[A-Za-z]");//var patt1=new RegExp("^[A-Za-z]+[0-9]*[ A-Za-z0-9]*$");
   var patt2=new RegExp("[A-Za-z0-9]");
   var reason=new RegExp("(\/\/\/\/\/\/|::::::)");
   var reason2=new RegExp("(\/\/|::)");

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
//war_tar_DATE validation
	if(!date.test(document.form1.war_tar_DATE.value))
    {
      alert("Please enter the Date");
	  document.form1.war_tar_DATE.focus();
      return false;
    }
	if(document.form1.war_tar_DATE.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(document.form1.war_tar_DATE.value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	var returnval=false;//validformat
	if(!validformat.test(document.form1.war_tar_DATE.value))
	{
	alert("Please enter the Date correct format");
	document.form1.war_tar_DATE.focus();
	return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		alert("Invalid month or date found in Date");
		document.form1.war_tar_DATE.focus();
		return false;
	  }
	 }//if(war_tar_DATE!="") 
//date comparision code   
	   var currentTime = new Date();
       var mm = currentTime.getMonth() + 1;
       var dd = currentTime.getDate();
       var yyyy = currentTime.getFullYear();
       var sysdate=dd+"-"+mm+"-"+yyyy;
	   sysdate=sysdate.split("-");
	   var sysdate = new Date(sysdate[2], sysdate[1]-1, sysdate[0]); //var date1 = new Date(yr1, mon1, dt1); 

	   var ldate=(document.form1.war_tar_DATE.value).split("-");
       var ldate = new Date(ldate[2], ldate[1]-1, ldate[0]); //alert("hey to rday date is ="+prdate);
	   if(ldate > sysdate)
     	{
		 alert("selected Date should not more than the System date");
		 document.form1.war_tar_DATE.focus();
		 return false; 
	     }
//date comparision code exit here*/
//validation for the dynamic payment schedule table
	 
	var lsrcode = document.getElementsByName('lsrcode');
	var war_villages = document.getElementsByName('war_villages');
    var totcust = document.getElementsByName('totcust');
	var sdr_cust = document.getElementsByName('sdr_cust');//var ops_narration = document.getElementsByName('ops_narration');
	var act_sdr_cust = document.getElementsByName('act_sdr_cust');
	var n= lsrcode.length;
 
//alert("MSR DEBUG lsrcode before for loop");
for (var k = 0; k < n; k++)
{
	if(!date.test(lsrcode[k].value))
	//if(lsrcode[k].value=='')  
   {
	 alert("Please enter Field Staff ID correctly");
	 lsrcode[k].focus();
 	 return false;
	}

//war_village validation and manupulations
//	}//for (var k = 0; k < n; k++) 
	var m = k+1;
	//alert("value of the m :"+m);
	var textbox = document.getElementsByName('textbox'+m);
	var len=textbox.length;
	var blankCount=0;
	for(var j=0; j<len; j++)
	{	//alert("textbox[j].value="+textbox[j].value);
		if((textbox[j].value).trim()=='')
			break;
		else
			{
			blankCount++;
			   if((!patt1.test(textbox[j].value))||(reason2.test(textbox[j].value)))
			    {
			      alert("Please enter Village name correctly");
			      textbox[j].focus();
			      return false;
			    }					
			}
	}
	if(blankCount==0)
	{
		alert("Please Enter atleast one Village name");
		textbox[j].focus();
		return false;
	}
		
//totcust validation start here	 
		   var Area=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
		//   if(totcust[k].style.visibility=='visible')
		 //  {
			   //alert("the number after the point="+totcust[k].value.split('.')[1].length);
		    if((!Area.test(totcust[k].value)) || (totcust[k].value.length < 1) || (Number(totcust[k].value) == 0))
		    {// alert("hey the inside ops_place");
			 alert("Please enter the total No.of Customers to be visited"); 
			 totcust[k].focus();
		 	 return false;
			 }
//sdr_cust validation start here	 
		   var Quantity=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
		   if(sdr_cust[k].style.visibility=='visible')
		   {//alert("the number after the point="+sdr_cust[k].value.split('.')[1].length);
		    if((!Quantity.test(sdr_cust[k].value)) || (sdr_cust[k].value.length < 1))// || (Number(sdr_cust[k].value) == 0))
		    { //alert("hey the inside ops_place");
			 alert("Please enter No.of Customers to be moved towards SDR"); 
			 sdr_cust[k].focus();
		 	 return false;
			 }
			}//if(sdr_cust[k].style.visibility=='visible') alert("the number after the point="+sdr_cust[k].value.split('.')[1]);

//act_sdr_cust validation start here	 
		   var Price=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
		   if(act_sdr_cust[k].style.visibility=='visible')
		   {//alert("the number after the point="+act_sdr_cust[k].value.split('.')[1].length);
		    if((!Price.test(act_sdr_cust[k].value)) || (act_sdr_cust[k].value.length < 1))// || (Number(act_sdr_cust[k].value) == 0))
		    { //alert("hey the inside ops_place");
			 alert("Please enter Actual No.of customers opted for SDR"); 
			 act_sdr_cust[k].focus();
		 	 return false;
			 }
		   }  
//cross checking parseInt()
		if(parseInt(sdr_cust[k].value) > parseInt(totcust[k].value))// || (Number(act_sdr_cust[k].value) == 0))
		    { //alert("hey the inside ops_place");
		    	alert("Customers to be visited should not less than No.of Customers to be moved towards SDR");
		    	totcust[k].focus();
		 		return false;
			 }
			 
			 
		 


			 
//Assigning dynamic villages in to war_villages fields
			war_villages[k].value='';
			for(var j=0; j<len; j++)
			{
			//alert("going add="+textbox[j].value)
			//alert("Bfr add string="+war_villages[k].value);
						if((textbox[j].value).trim()!='')
						war_villages[k].value+=textbox[j].value+"::";
						else
						break;
			//alert("after add string="+war_villages[k].value);
			}
			//alert("war_village="+war_villages[k].value);
			war_villages[k].value = war_villages[k].value.substr(0,war_villages[k].value.length-2);
			//alert("war_village="+war_villages[k].value);

	}//END OF THE FOR LOOP	
	
//Duplicate Village Entries validation starts here
 for (var i = 0; i < war_villages.length; i++){
 var village=war_villages[i].value;   
 var villages=village.split("::");
 var vilLen = villages.length;
   for (var j = 0; j<vilLen ; j++)
   for (var k = 0; k<vilLen ; k++)
   { 
   if(j!=k)
	 {
	   if(villages[j]==villages[k])
		   {
		   alert("Duplicate rows found with similar values (Village), Use common description & make single row");
	        return false;
		   }
	   }
	 }
 }
//alert("End of the validationForm() function");	
}//validateForm();
//hey the update&save butons(submission) code starts here---------------------------------------------------------
$(document).ready(function(){//alert("hey inside the ready function of jquery update");
			
		$("#form1").submit(function(){sessioncheck();//alert("hey inside the submit function");
		   if(document.form1.onSubmit==validateForm()) 
			{  var allElements=$(this).serialize();//alert("hey the validation is done");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			if(document.form1.save.style.display=='inline'){
				//alert("hey the program save is="+program);
			  var program='swartarget';	}
			else  
				
			   var program='uwartarget'; //alert("hey the program is="+program);	
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
//--------------------------------------------------------------------------------recent Working area start	
	$(document).on('click', '.addRow', function(){
	
	//alert("inside onclick of bigger row and gonig delete inner fields");
	
	//$(".delDynTextBox").trigger('click');	
		var ops_sno = document.getElementsByName('ops_sno');
		var rowindexLast = ops_sno.length; //alert("rowindexLast="+rowindexLast);
	     //var textboxLast = document.getElementsByName('textbox'+rowindexLast-1);
	     var rowindexFirst = rowindexLast-1;
	     var textboxFirst = document.getElementsByName('textbox'+rowindexFirst);
	     //var lenLast = textboxLast.length;	alert("lenLast"+lenLast)
	     var lenFirst = textboxFirst.length;	//alert("lenFirst="+lenFirst)
	     //alert("Present row index: =textbox"+rowindexLast);
	     //alert("Pre row index: =textbox"+rowindexFirst);
	     
	     if(rowindexFirst<0) return;

	     //for(var i=0;i<lenFirst;i++)
	     var i=lenFirst-1;
	     do
	     {
	     //alert("inside first for loop");
	     var par=(textboxFirst[i]).parentNode; //alert("for loop par="+par);
	     if(par==null) return;
	 	 while(par.nodeName.toLowerCase()!='tr'){   par=par.parentNode;  } //alert("the index of the current row is"+par.rowIndex); 
 		 var lenFirstPar=par.rowIndex;	//alert("lenFirstPar="+lenFirstPar);alert("end of first for and going to remove");
 		 if(lenFirstPar==rowindexLast)
	         $(textboxFirst[i]).remove();
	     else break;
	     i--;
	     }while(i>0); 
	     
		//below code to add fresh textbox after removing all inherited textboxes
		addVillageField(rowindexLast,1);
		//addButton
		//removeButton
		
	});
//--------------------------------------------------------------------recent Working area end	
	//alert("hey end of the ready function");
//Currency converter function code
//$('#act_sdr_cust').blur(function() { if($('#act_sdr_cust').val()!='') $('#act_sdr_cust').formatCurrency(); });
	 	//var counter = 2;
//---------------add function for inner dynamic filds 
	    //$(".addDynTextBox").addDynText(function () {
	    $(document).on('click', '.addDynTextBox', function(){

		var par=this.parentNode; 
	 	while(par.nodeName.toLowerCase()!='tr'){   par=par.parentNode;  } //alert("the index of the current row is"+par.rowIndex); 
 		var rowindex=par.rowIndex;

	 	($(this).parent()).append('<input type="text" name="textbox'+rowindex+'" id="textbox'+rowindex+'" size="8" onkeyup="allowupdate();" maxlength="25">');
	 	//counter++;
	     });
	     
//---------------remove function for inner dynamic filds 
	     //$(".delDynTextBox").delDynText(function () {
	     $(document).on('click', '.delDynTextBox', function(){
	     
	    var par=this.parentNode; 
	 	while(par.nodeName.toLowerCase()!='tr'){   par=par.parentNode;  } //alert("the index of the current row is"+par.rowIndex); 
 		var rowindex=par.rowIndex;
	     var textbox = document.getElementsByName('textbox'+rowindex);
	     var len = textbox.length;
	     //alert("'textbox'+rowindex=textbox"+rowindex);
	     //if(len)
	     $(textbox[len-1]).remove();
	     if(len==1) addVillageField(rowindex, 1);
	     });

	     if (typeof String.prototype.trim != "function") {
	    	  String.prototype.trim = function () {
	    	    return this.replace(/^\s+|\s+$/g, '');
	    	  };
	    	}
	     
	     });//ready function
//the save& update buttons submission code ends here------------------------------------------------------------

function addVillageField(rowindex,count)
	{
		//alert("inside addVillageField()");
		var addButton = document.getElementsByName('addButton');
		var btnObj = addButton[rowindex-1];
		for(var r=0; r<count; r++)
		($(btnObj).parent()).append('<input type="text" name="textbox'+rowindex+'" id="textbox'+rowindex+'" size="8" onkeyup="allowupdate();" maxlength="25">');
		//alert("end of addVillageField()");
	}
function delVillageField(rowindex,count)
	{
		//alert("inside delVillageField() and rowIndex="+rowindex);alert("count="+count);
		var textbox = document.getElementsByName('textbox'+rowindex);
	    var len = textbox.length;
		for(var r=0; r<count; r++)
		{
	     //alert("'textbox'+rowindex=textbox"+rowindex);
	     if(len-1-r<0)
	    	 break;
	     else
	     $(textbox[len-1-r]).remove();
		}
	    addVillageField(rowindex, 1);//adding one if the only first dynamic field deleted
		//alert("end of del VillageField()");
	}
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
		 document.getElementById("war_tar_DATE").value ='';
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
     //document.getElementById("lsrcode_id").readOnly =true;
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
var vl=0;
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
 document.getElementById("war_tar_DATE").value =star[x][3];
 
 count=c2[x1];//alert("hey the row count in show function is="+count); 
 for(var i=1; i<count; i++)  $(".addRow").trigger('click'); //alert("hey after dynamic table generation");
 
 var lsrcode = document.getElementsByName('lsrcode');
 var totcust = document.getElementsByName('totcust');
 var sdr_cust = document.getElementsByName('sdr_cust');//var ops_narration = document.getElementsByName('ops_narration');
 var act_sdr_cust = document.getElementsByName('act_sdr_cust');
 var war_villages = document.getElementsByName('war_villages');
 //alert("hey the assignment to the dinemic fields is startted,c2[x1]="+c2[x1]);
 var k = x;

 for (var j = 0; j < c2[x1]; j++)
 {
 //alert("hinsiede the for loop,and the  head="+star[k][2]+"--");
  if(k>x+c2[x1])
   break; //alert("the activitsl_id="+star[k][5]);
  lsrcode[j].value=star[k][4];//alert("the lsrcode_id="+star[k][6]);
  // village1[j].value=star[k][7];
  totcust[j].value=star[k][5];
  sdr_cust[j].value=star[k][6];
  act_sdr_cust[j].value=star[k][7];
  //alert("star[k][7]="+star[k][7])
  var villages=star[k][8].split("::");
  var textBoxLen = villages.length;
  //alert("textBoxLen="+textBoxLen);
  var textboxAssign2 = document.getElementsByName('textbox'+(j+1));
  delVillageField(j+1,textboxAssign2.length);//alert("deleted previous rows");
  addVillageField(j+1,textBoxLen-1); //alert("added new village fields");
  var textboxAssign = document.getElementsByName('textbox'+(j+1));
  //alert("before assigning the values to added villages field and villages="+villages);
  for(var m=0; m<textBoxLen; m++)
   {  
   //alert("First village value is :"+villages[m]);
   textboxAssign[m].value = villages[m];
   }
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

<script language="javascript" type="text/javascript">
function rrefresh()
{
var counter = 2;
}
function queryfunction() 
{ 
//Everything from disable() exceptiong button code,just for primary key enable(replace true by false) and editable(add readOnly=false)
	 //alert("inside the disable function");
var textboxAssign = document.getElementsByName('textbox1');
 delVillageField(1,textboxAssign.length);
	document.getElementById("cal1").style.display='none';
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
	    if(textareas[j].style.display!="none")//new added one
	    textareas[j].value='';//new added one 
	    textareas[j].disabled=true;
	  }
	 var selects = f.getElementsByTagName("select"); //alert("hey you got the selects[]"+selects);
	 for(var j = 0; j < selects.length; j++)
	 { 
	    if(selects[j].style.display!="none")//new added one
	    selects[j].value='';//new added one
	    selects[j].disabled=true;
	  } //alert("hey going to read the deliverables of the dynamic table");
	 $("#tableID tr:gt(1)").remove();//removing all the rows exception first two rows in current table//newly added for dynamic table
	 document.form1.remove.style.display='none';//newly added for dynamic table
	 document.form1.add.disabled=true;//newly added for dynamic table

	 //alert("hhh");
	 document.form1.BSFLUNIT_UCODE.disabled=false;//newly added code for the Query functionality
	 document.form1.war_tar_DATE.disabled=false;
	 document.form1.BSFLUNIT_UCODE.readOnly=false;
	 document.form1.war_tar_DATE.readOnly=false;
	 //below two lines code to remove the background color of the querying fields
	 document.form1.BSFLUNIT_UCODE.style.backgroundColor='skyblue';
	 document.form1.war_tar_DATE.style.backgroundColor='skyblue';
	 document.getElementById("cal1").style.display='inline';//to select the date this is needed
	 //addSuggestion('BSFLUNIT_UCODE2UHLOG_SERVICE','BSFLUNIT_UCODE');//To apply LOV as per huma entries made in ops
	 //addSuggestion('ops_desc','ops_desc');///for ops query with ops_desc
	 document.form1.BSFLUNIT_UCODE.focus();//To focus at field cont_id at the time of query
	 //alert("hiioiu");
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
//	 alert("end");
}//queryfunction();
function disable() 
{ //alert("inside the disable function");
var textboxAssign = document.getElementsByName('textbox1');
 delVillageField(1,textboxAssign.length);
 document.getElementById("cal1").style.display='none';
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
    if(textareas[j].style.display!="none")//new added one
    textareas[j].value='';//new added one 
    textareas[j].disabled=true;
  }
 var selects = f.getElementsByTagName("select"); //alert("hey you got the selects[]"+selects);
 for(var j = 0; j < selects.length; j++)
 { 
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
	document.form1.addButton.disabled=true;
	document.form1.removeButton.disabled=true;
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
  //document.form1.unitname.value='';
 var MyDate = new Date();
 document.form1.war_tar_DATE.value=('0' + MyDate.getDate()).slice(-2) + '-'
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
document.form1.addButton.disabled=false;
document.form1.removeButton.disabled=false;
document.form1.save.style.display='inline';
document.form1.update.style.display='none';
}//enable()
</script>
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>
<!-- <script language="javascript" type="text/javascript"></script> -->
<script type="text/javascript" src="JS/jquery.table.addrow.js"></script> 
	</head>
	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();">
	
	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Fmenu.js"></script>
	<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if(role.equals("admin") || role.equals("audit") ||role.equals("areahead") || role.equals("unithead"))
{ %>
<form id="form1" name="form1" method="post">
<br />
<p align="right">
 <table width="1150" align="center">
  <tr>
  <td valign="top" width="1115" height="">   
  					<fieldset style="background-color:">
						<legend class="formTitle">Credit Report </legend>
						<table bgcolor="" width="100%" height="91%" border="0"
							align="center" bordercolor="#000000">



							<tr>
								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Segment
												Identifier</td>
											<td width="" align="left"><div align="left">
													<input name="SEGMENT_IDENTIFIER" id="SEGMENT_IDENTIFIER "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Credit
												Request Type</td>
											<td width="" align="left"><div align="left">
													<input name="CR_REQUEST_TYPE" id="CR_REQUEST_TYPE "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Credit
												Report Transaction ID</td>
											<td width="" align="left"><div align="left">
													<input name="CR_REPORT_TRANSACTION_ID"
														id="CR_REPORT_TRANSACTION_ID " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Credit
												Inquiry Purpose Type</td>
											<td width="" align="left"><div align="left">
													<input name="CR_INQUIRY_PURPOSE_TYPE"
														id="CR_INQUIRY_PURPOSE_TYPE " type="text" size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Credit
												Inquiry Purpose Type Description</td>
											<td width="" align="left"><div align="left">
													<input name="CR_INQU_PURP_TYPE_DESC"
														id="CR_INQU_PURP_TYPE_DESC " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Credit
												Inquiry Stage</td>
											<td width="" align="left"><div align="left">
													<input name="CR_INQUIRY_STAGE" id="CR_INQUIRY_STAGE "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Credit
												Report Transaction Date Time</td>
											<td width="" align="left"><div align="left">
													<input name="CR_REPORT_TRAN_DATE_TIME"
														id="CR_REPORT_TRAN_DATE_TIME " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Name1</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_NAME1" id="APPLCNT_NAME1 " type="text"
														size="20" /></div></td>

										</tr>
									</table>
								</td>
							</tr>
							<tr>

								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Applicant
												Name2</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_NAME2" id="APPLCNT_NAME2 " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Name3</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_NAME3" id="APPLCNT_NAME3 " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Name4</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_NAME4" id="APPLCNT_NAME4 " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Name5</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_NAME5" id="APPLCNT_NAME5 " type="text"
														size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Member
												Father Name</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_FATHER_NAME" id="MEM_FATHER_NAME "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Member
												Mother Name</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_MOTHER_NAME" id="MEM_MOTHER_NAME "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Member
												Spouse Name</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_SPOUSE_NAME" id="MEM_SPOUSE_NAME "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Member
												relationship Type 1</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_REL_TYPE1" id="MEM_REL_TYPE1 " type="text"
														size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>

								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Member
												relationship Name 1</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_REL_NAME1" id="MEM_REL_NAME1 " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Member
												relationship Type 2</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_REL_TYPE2" id="MEM_REL_TYPE2 " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Member
												relationship Name 2</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_REL_NAME2" id="MEM_REL_NAME2 " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Member
												relationship Type 3</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_REL_TYPE3" id="MEM_REL_TYPE3 " type="text"
														size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>

								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Member
												relationship Name 3</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_REL_NAME3" id="MEM_REL_NAME3 " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Member
												relationship Type 4</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_REL_TYPE4" id="MEM_REL_TYPE4 " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Member
												relationship Name 4</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_REL_NAME4" id="MEM_REL_NAME4 " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Birth Date</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_BIRTHDATE" id="APPLCNT_BIRTHDATE "
														type="text" size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>

								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Applicant
												Age</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_AGE" id="APPLCNT_AGE " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Age as on date</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_AGE_ASONDATE"
														id="APPLCNT_AGE_ASONDATE " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												ID Type 1</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_ID_TYPE1" id="APPLCNT_ID_TYPE1 "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												ID 1</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNTID1" id="APPLCNTID1 " type="text"
														size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>

								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Applicant
												ID Type 2</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNTID_TYPE2" id="APPLCNTID_TYPE2 "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												ID 2</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNTID_2" id="APPLCNTID_2 " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Acct Open
												Date</td>
											<td width="" align="left"><div align="left">
													<input name="ACCT_OPEN_DATE" id="ACCT_OPEN_DATE "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Application-ID/
												Account-No</td>
											<td width="" align="left"><div align="left">
													<input name="APPL_ID_ACCOUNT_NO" id="APPL_ID_ACCOUNT_NO "
														type="text" size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>

								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Branch ID</td>
											<td width="" align="left"><div align="left">
													<input name="BRANCH_ID" id="BRANCH_ID " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Member ID</td>
											<td width="" align="left"><div align="left">
													<input name="MEM_ID" id="MEM_ID " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Kendra ID</td>
											<td width="" align="left"><div align="left">
													<input name="KENDRA_ID" id="KENDRA_ID " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applied for
												Amount/Current Balance</td>
											<td width="" align="left"><div align="left">
													<input name="APPLIED_FOR_AMOUNT" id="APPLIED_FOR_AMOUNT "
														type="text" size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>

								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Key Person
												Name</td>
											<td width="" align="left"><div align="left">
													<input name="KEY_PERSON_NAME" id="KEY_PERSON_NAME "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Key Person
												Relation</td>
											<td width="" align="left"><div align="left">
													<input name="KEY_PERSON_RELATION" id="KEY_PERSON_RELATION "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Nominee
												Name</td>
											<td width="" align="left"><div align="left">
													<input name="NOMINEE_NAME" id="NOMINEE_NAME " type="text"
														size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Nominee
												Relationship Type</td>
											<td width="" align="left"><div align="left">
													<input name="NOMINEE_REL_TYPE" id="NOMINEE_REL_TYPE "
														type="text" size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>

								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Applicant
												Telephone Number Type1</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_TELE_NUM_TYPE1"
														id="APPLCNT_TELE_NUM_TYPE1 " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Telephone Number 1</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_TELE_NUM1" id="APPLCNT_TELE_NUM1 "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Telephone Number Type2</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_TELE_NUM_TYPE2"
														id="APPLCNT_TELE_NUM_TYPE2 " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Telephone Number 2</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_TELE_NUM2" id="APPLCNT_TELE_NUM2 "
														type="text" size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>


											<td width="" height="" align="right" style="width: 111px;">Applicant
												Address Type 1</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_ADDRESS_TYPE1"
														id="APPLCNT_ADDRESS_TYPE1 " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Address1</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_ADDRESS1" id="APPLCNT_ADDRESS1 "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Address1 City</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_ADDRESS1_CITY"
														id="APPLCNT_ADDRESS1_CITY " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Address1 State</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_ADDRESS1_STATE"
														id="APPLCNT_ADDRESS1_STATE " type="text" size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>

								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Applicant
												Address1 PIN Code</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_ADDRESS1_PINCODE"
														id="APPLCNT_ADDRESS1_PINCODE " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Address Type 2</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_ADDRESS_TYPE2"
														id="APPLCNT_ADDRESS_TYPE2 " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Address2</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_ADDRESS2" id="APPLCNT_ADDRESS2 "
														type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Address2 City</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_ADDRESS2_CITY"
														id="APPLCNT_ADDRESS2_CITY " type="text" size="20" /></div></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>

								<td width="100%" height="" colspan="2" align="center"
									style="width: 1129px;">
									<table width="80%" style="width: 1132px;">
										<tr>

											<td width="" height="" align="right" style="width: 111px;">Applicant
												Address2 State</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_ADDRESS2_STATE"
														id="APPLCNT_ADDRESS2_STATE " type="text" size="20" /></div></td>
											<td width="" height="" align="right" style="width: 111px;">Applicant
												Address2 PIN Code</td>
											<td width="" align="left"><div align="left">
													<input name="APPLCNT_ADDRESS2_PINCODE"
														id="APPLCNT_ADDRESS2_PINCODE " type="text" size="20" /></div></td>
										</tr>
										</tr>
    <tr>
    <td></td>
    <td><div align="left"><input type="submit" value="Get Customert Details"></div>
    </td>
    </tr>
									</table>
								</td>
							</tr>


						</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td height="">&nbsp;</td>
				</tr>
			</table>
	<% }	else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to do LSR WAR Room Target Entry
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
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