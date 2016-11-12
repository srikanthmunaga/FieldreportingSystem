<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.awt.geom.Area"%>
<%@page import="frs_cls.JdbcConnect" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>WAR Room Weekly Plan</title>
<% 
 if (((HttpServletRequest) request).getSession().getAttribute("username") == null)
 {
    response.sendRedirect("frslogin.jsp"); // Not logged in, redirect to login page.
	}
else 
    {
%>
<%
  String uname_no=null;//=(String)((HttpServletRequest) request).getSession().getAttribute("uname");
  String username=(String)((HttpServletRequest) request).getSession().getAttribute("username");
  String aname=(String)((HttpServletRequest) request).getSession().getAttribute("aname");
  String sql="select huma_id from frs_user where username='"+username+"'";
  				String huma_id=null;
  				String areaid=null;
  				String ucode=null;
  				String acode=null;
				Connection con=new JdbcConnect().getConnection();
				Statement svst=con.createStatement();
				ResultSet svrs=svst.executeQuery(sql);
				if(svrs.next())
				{
				huma_id=svrs.getString(1);
				}//BSFLUNIT_UCODE
				String areacode="";
				String acsql="SELECT AREA_MSTR.AREA_NAME||'-'|| AREA_MSTR.AREA_ID FROM AREA_MSTR where HUMA_ID=(select  frs_user.HUMA_ID  from frs_user where FRS_USER.USERNAME='"+username+"')";
				Statement ust12=con.createStatement();
				ResultSet urs12=ust12.executeQuery(acsql);
				if(urs12.next())
				{
				areacode=urs12.getString(1);
				}//BSFLUNIT_UCODE
				String sqlah="SELECT  distinct HUMA_MSTR.HUMA_FNAME||' '|| HUMA_MSTR.HUMA_LNAME||'-'||HUMA_MSTR.HUMA_ID FROM FRS_USER , HUMA_MSTR where HUMA_MSTR.HUMA_ID  =(select  frs_user.HUMA_ID  from frs_user where FRS_USER.USERNAME='"+username+"')";
				Statement ust1=con.createStatement();
				ResultSet urs1=ust1.executeQuery(sqlah);
				if(urs1.next())
				{
				acode=urs1.getString(1);
				}//BSFLUNIT_UCODE
				//System.out.println("retrived value is :"+acode);
 				String sqlu="select BSFLUNIT_UCODE from HUMA_MSTR where huma_id='"+huma_id+"'";
  				//System.out.println();
				Statement ust=con.createStatement();
				ResultSet urs=svst.executeQuery(sqlu);
				if(urs.next())
				{
				ucode=urs.getString(1);
				}//BSFLUNIT_UCODE
 				String sql1="select BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE,AREA_ID from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"+ucode+"'";
				Statement st=con.createStatement();
				ResultSet rs=svst.executeQuery(sql1);
				if(rs.next())
				{
				uname_no=rs.getString(1);
				areaid=rs.getString(2);
				}//BSFLUNIT_UCODE
				String sql2="select AREA_name from AREA_MSTR where AREA_ID='"+areaid+"'";
				Statement ast=con.createStatement();
				ResultSet ars=svst.executeQuery(sql2);
				if(ars.next())
				{
				aname=ars.getString(1);
				//System.out.println(areaid);
				}//BSFLUNIT_UCODE
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
%>
<%
    	String driver = application.getInitParameter("driver");
		String url = application.getInitParameter("url");
		String user = application.getInitParameter("user");
		String pwd = application.getInitParameter("pwd");
		try{
		con= dbConn.getConnection();
%>

<style type="text/css">
input.addRow{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:24px;     background-image:url(images/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px; display:none;}
input.delRow{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:24px;     background-image:url(images/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px; display:none;}

td.lastCol{  
	display:none;
}
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
function EnforceMaximumLength(fld,len)
{
 if(fld.value.length > len) { fld.value = fld.value.substr(0,len); document.form1.save.focus(); }
}

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
</script>
<script type="text/javascript" language="javascript">
function validateForm()
{
	
	var patt1=new RegExp("[A-Za-z]");//var patt1=new RegExp("^[A-Za-z]+[0-9]*[ A-Za-z0-9]*$");
    var num=new RegExp("[0-9]");
    var reason=new RegExp("(\/\/\/\/\/\/|::::::)");
    var patt2=new RegExp("[A-Za-z0-9]");	  

	  var date=new RegExp("[0-9]");
		if(!date.test(document.form1.war_fdate.value))
	    {
	      alert("Please select the From date");
		  document.form1.war_fdate.focus();
	      return false;
	    }
		if(document.form1.war_fdate.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
		{
		var vnvdate=(document.form1.war_fdate.value).split("-");
		var validformat = /^\d{2}-\d{2}-\d{4}$/;
		var returnval=false;//validformat
		if(!validformat.test(document.form1.war_fdate.value))
		{
		alert("Please enter the From date correct format");
		document.form1.war_fdate.focus();
		return false;
		}//if date format checking
		var dayfield=vnvdate[0];
		var monthfield=vnvdate[1];
		var yearfield=vnvdate[2];
		var dayobj = new Date(yearfield, monthfield-1, dayfield)
		if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
		 {
			alert("Invalid month or date found in From date");
			document.form1.war_fdate.focus();
			return false;
		  }
		 }//if(war_fdate!="") 
	 	   var currentTime = new Date();
	       var mm = currentTime.getMonth() + 1;
	       var dd = currentTime.getDate();
	       var yyyy = currentTime.getFullYear();
	       var sysdate=dd+"-"+mm+"-"+yyyy;
		   sysdate=sysdate.split("-");
		   var sysdate = new Date(sysdate[2], sysdate[1]-1, sysdate[0]); //var date1 = new Date(yr1, mon1, dt1); 

		   var fdate=(document.form1.war_fdate.value).split("-");
	       var fdate = new Date(fdate[2], fdate[1]-1, fdate[0]); //alert("hey to rday date is ="+prdate);
		if(!date.test(document.form1.war_tdate.value))
	    {
	      alert("Please select the To date");
		  document.form1.war_tdate.focus();
	      return false;
	    }
		if(document.form1.war_tdate.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
		{
		var vnvdate=(document.form1.war_tdate.value).split("-");
		var validformat = /^\d{2}-\d{2}-\d{4}$/;
		var returnval=false;//validformat
		if(!validformat.test(document.form1.war_tdate.value))
		{
		alert("Please enter the To date correct format");
		document.form1.war_tdate.focus();
		return false;
		}//if date format checking
		var dayfield=vnvdate[0];
		var monthfield=vnvdate[1];
		var yearfield=vnvdate[2];
		var dayobj = new Date(yearfield, monthfield-1, dayfield)
		if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
		 {
			alert("Invalid month or date found in To date");
			document.form1.war_tdate.focus();
			return false;
		  }
		 }//if(war_tdate!="")  //alert("hety outside the to date");
		   var tdate=(document.form1.war_tdate.value).split("-");
	       var tdate = new Date(tdate[2], tdate[1]-1, tdate[0]); //alert("hey to tdate date is ="+tdate);
		   if(fdate > tdate)
	     	{
			 alert("From date should be less than To date");
			 document.form1.war_fdate.focus();
			 return false; 
		     }
	
		
	 if(userrole_js==="admin"){
/* 			 if(document.form1.huma_id.value=='')
			 {//alert("hey the save is inline and the huma_id is=''");
			   alert("Please enter the Field Staff Id");
			   document.form1.huma_id.focus();
			   return false;
			 }
			 else//if(document.form1.huma_id.value!='')
			 { //alert("hey inside the if: means the huma_id is !=''");
			  var id=document.form1.huma_id.value.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
			      id=id.substr(0,4);
				  //alert("hey the id is="+id);
			  if(!patt2.test(document.form1.huma_id.value))
			    {
			       alert("Please enter the Field Staff Id correctly");
				   document.form1.huma_id.focus();
				   return false;
				}
			  }//else//if(document.getElementById("huma_id").value!='')
 */			  
/* 			  if(document.form1.pod.value > document.form1.tot_tar_pod.value)
			  if(!patt1.test(document.form1.HO_Backlogs.value))
			  {//alert("hey the save is inline and the comp_id is=''");
			   alert("Please enter the Action plan for covering up backlogs, at HO");
			   document.form1.HO_Backlogs.focus();
			   return false;
			  }
				//Reason_AH
			if(document.form1.pod.value > document.form1.tot_tar_pod.value)
			 if(!patt1.test(document.form1.Reason_HO.value))
			  {//alert("hey the save is inline and the comp_id is=''");
			   alert("Please enter the Reason for having Less Target than Actual POD at HO");
			   document.form1.Reason_HO.focus();
			   return false;
			  }		
 */		
	 }

	if(userrole_js==="unithead"){
		 
		 var humaid=new RegExp("[0-9]{3}");///[0-9]{2}-[0-9]{3}/P[0-9]{6}");
		  if(document.form1.BSFLUNIT_UCODE.value=='')
		  {//alert("hey the save is inline and the BSFLUNIT_UCODE is=''");
		   alert("Please enter the unit nubmer correctly");
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
		   }
/* 		   var NUMB=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
			    if((!NUMB.test(document.form1.pod.value)) || (document.form1.pod.value.length < 1) || (Number(document.form1.pod.value) == 0))
			    {
				 alert("Please enter the Actual POD Amount (As on 'From Date')correctly"); 
				 document.form1.pod.focus();
			 	 return false;
				 }
		if(document.form1.pod.value > document.form1.tot_tar_pod.value)				 
		if(!patt1.test(document.form1.UH_Backlogs.value))
		  {//alert("hey the save is inline and the comp_id is=''");
		   alert("Please enter the Action plan for covering up backlogs, at UH");
		   document.form1.UH_Backlogs.focus();
		   return false;
		  }
			//Reason_AH
		if(document.form1.pod.value > document.form1.tot_tar_pod.value)			
		 if(!patt1.test(document.form1.Reason_UH.value))
		  {//alert("hey the save is inline and the comp_id is=''");
		   alert("Please enter the Reason for having Less Target than Actual POD at UH");
		   document.form1.Reason_UH.focus();
		   return false;
		  }		
 */	
	}
	
if(userrole_js==="areahead"){
//area_name
 if(document.form1.area_name.value=='')
  {
  alert("please select Region name correctly");
  document.form1.area_name.focus();
  return false;
  }//busis_name
	//AH_Backlogs
/*   if(!patt1.test(document.form1.AH_Backlogs.value))
  {//alert("hey the save is inline and the comp_id is=''");
   alert("Please enter the Action plan for covering up backlogs, at AH");
   document.form1.AH_Backlogs.focus();
   return false;
  }
	//Reason_AH
 if(!patt1.test(document.form1.Reason_AH.value))
  {//alert("hey the save is inline and the comp_id is=''");
   alert("Please enter the Reason for having Less Target than Actual POD at AH");
   document.form1.Reason_AH.focus();
   return false;
  }	 */
	}

		

//alert("End of the validationForm() function");
	
}//validateForm();
//hey the update&save butons(submission) code starts here---------------------------------------------------------
$(document).ready(function(){//alert("hey inside the ready function of jquery update");
 //document.form1.pod.disabled=true;
 // document.form1.tot_tar_pod.disabled=true;
 // document.form1.tot_rec_pod.disabled=true;

		$("#form1").submit(function(){sessioncheck();//alert("hey inside the submit function");
		   if(document.form1.onSubmit==validateForm()) 
			{  var allElements=$(this).serialize();//alert("hey the validation is done");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			if(document.form1.save.style.display=='inline'){
			  var program='swarRemarks';
			  //alert("hey the program save is="+program);
			  }
			else  
			   var program='uwarRemarks'; 
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
	
		var ops_sno = document.getElementsByName('ops_sno');
		var rowindexLast = ops_sno.length; //alert("rowindexLast="+rowindexLast);
	     var rowindexFirst = rowindexLast-1;
	     var textboxFirst = document.getElementsByName('textbox'+rowindexFirst);
	     var lenFirst = textboxFirst.length;	//alert("lenFirst="+lenFirst)
	     if(rowindexFirst<0) return;
	
	
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
	    $(document).on('click', '.addDynTextBox', function(){

		var par=this.parentNode; 
	 	while(par.nodeName.toLowerCase()!='tr'){   par=par.parentNode;  } //alert("the index of the current row is"+par.rowIndex); 
 		var rowindex=par.rowIndex;

	 	($(this).parent()).append('<input type="text" name="textbox'+rowindex+'" id="textbox'+rowindex+'" size="18" onkeyup="allowupdate();" style="border-color:#0099FF;" maxlength="65"  onfocus="addSuggestion2(\'VCODE\',\'textbox'+rowindex+'\',BSFLUNIT_UCODE.value);">');
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
		($(btnObj).parent()).append('<input type="text" name="textbox'+rowindex+'" id="textbox'+rowindex+'" size="18" onkeyup="allowupdate();" maxlength="65" style="border-color:#0099FF;" onfocus="addSuggestion2(\'VCODE\',\'textbox'+rowindex+'\',BSFLUNIT_UCODE.value);">');
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


function Getpods(decide,key1,key2,key3)
{
// alert("hety inside the Getpods"+key3);
 sessioncheck();
 var date=new RegExp("[0-9]");
	if(!date.test(document.form1.war_fdate.value))
 {
 	return;
   
 }
	if(document.form1.war_fdate.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(document.form1.war_fdate.value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	var returnval=false;//validformat
	if(!validformat.test(document.form1.war_fdate.value))
	{
	return;
	//alert("Please enter the From date correct format");
	//document.form1.war_fdate.focus();
	//return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		return;
		//alert("Invalid month or date found in From date");
		//document.form1.war_fdate.focus();
		//return false;
	  }
	 }//if(war_fdate!="") 
	   var currentTime = new Date();
    var mm = currentTime.getMonth() + 1;
    var dd = currentTime.getDate();
    var yyyy = currentTime.getFullYear();
    var sysdate=dd+"-"+mm+"-"+yyyy;
	   sysdate=sysdate.split("-");
	   var sysdate = new Date(sysdate[2], sysdate[1]-1, sysdate[0]); //var date1 = new Date(yr1, mon1, dt1); 

	   var fdate=(document.form1.war_fdate.value).split("-");
    var fdate = new Date(fdate[2], fdate[1]-1, fdate[0]); //alert("hey to rday date is ="+prdate);
	if(!date.test(document.form1.war_tdate.value))
 {
   return;
   //alert("Please select the To date");
	//  document.form1.war_tdate.focus();
   //return false;
 }
	if(document.form1.war_tdate.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(document.form1.war_tdate.value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	var returnval=false;//validformat
	if(!validformat.test(document.form1.war_tdate.value))
	{
	return;
	//alert("Please enter the To date correct format");
	//document.form1.war_tdate.focus();
	//return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		return;
		//alert("Invalid month or date found in To date");
		//document.form1.war_tdate.focus();
		//return false;
	  }
	 }//if(war_tdate!="")  //alert("hety outside the to date");
	   var tdate=(document.form1.war_tdate.value).split("-");
    var tdate = new Date(tdate[2], tdate[1]-1, tdate[0]); //alert("hey to tdate date is ="+tdate);
	   if(fdate > tdate)
  	{
		 return;
		 //alert("From date should be less than To date");
		 //document.form1.war_fdate.focus();
		 //return false; 
	     }
 xmlHttp=GetXmlHttpObject();
 if (xmlHttp==null)
  return;
 var url="getuserupdate";
 url=url+"?decide="+decide+"&key1="+key1+"&key2="+key2+"&key3="+key3;// alert("hey made the url"+url);
 xmlHttp.open("GET",url,false);
 xmlHttp.send(null);
  	x=0;
  	var showdata = xmlHttp.responseText;
 	showdata=showdata.replace(/^\s+/,'').replace(/\s+$/,'');
	showdata=showdata.replace(/\/\/\/\/\/\/$/,"");//removes the "//////"  from the end of the "data" array
	star=showdata.split("::::::"); //alert("got the data and after splitting="+star);
	if(star==null){
  	alert("You are not Authorised");
  	}
  	if(star=='')
	 {  
		 alert("No records found");
	 }
	else if(star.length>=0)
	{ var k=0;
	  //star[k] = star[k].split("::::::");
	   //alert("data splitting :"+star[0]+ "\t"+ star[1]+"\t"+star[3]);
	  document.getElementById("pod").value =star[0];
	  document.getElementById("tot_tar_pod").value =star[2];
	  document.getElementById("tot_rec_pod").value =star[1];
	  goDim("form1","previous"); document.form1.previous.disabled=true;     
	  goDim("form1","first"); document.form1.first.disabled=true;    
	  goDim("form1","next"); document.form1.next.disabled=true;  
	  goDim("form1","last"); document.form1.last.disabled=true;  

	}//esle if
}//Getpods(decide,key1,key2,key3)



function showEmpn(decide,key1,key2,key3)
{
//	alert("hety inside the showEmpn"+key2+"\t"+key3);
 
		if(key2.replace(/^\s+/,'').replace(/\s+$/,'')!="")
		{
		var validformat = /^\d{2}-\d{2}-\d{4}$/;
		if(!validformat.test(key2))
		{
		alert("Please enter the To date correct format");
		document.form1.war_tdate.focus();
		return false;
		}//if date format checking
		}
		if(key3.replace(/^\s+/,'').replace(/\s+$/,'')!="")
		{
		var validformat = /^\d{2}-\d{2}-\d{4}$/;
		if(!validformat.test(key3))
		{
		alert("Please enter the To date correct format");
		document.form1.war_tdate.focus();
		return false;
		}//if date format checking
		}
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
	 if(userrole_js==="unithead"){
		 document.getElementById("BSFLUNIT_UCODE").value ='';
	 } if(userrole_js==="areahead"){
	 }
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
     //document.getElementById("huma_id").readOnly =true;
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

 //document.getElementById("cal").style.display='none';
 //document.getElementById("cal2").style.display='none';
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
// alert("hey assingning the common static fields");
 //alert("User role is :"+userrole_js);
 document.getElementById("war_fdate").value =star[x][0];
 document.getElementById("war_tdate").value =star[x][1];
 document.getElementById("pod").value =star[x][6];//otd
 document.getElementById("tot_tar_pod").value =star[x][8];
 document.getElementById("tot_rec_pod").value =star[x][7];
 if(userrole_js==="unithead"){
 document.getElementById("BSFLUNIT_UCODE").value =star[x][2];
 document.getElementById("UH_Backlogs").value =star[x][3];
 document.getElementById("Reason_UH").value =star[x][4];
 document.form1.tot_tar_pod.disabled=true;
 document.form1.tot_rec_pod.disabled=true;
  }
 if(userrole_js==="areahead"){
 document.getElementById("area_name").value =star[x][2];
 document.getElementById("AH_Backlogs").value =star[x][3];
  document.getElementById("Reason_AH").value =star[x][4];
  document.form1.pod.disabled=true;
  document.form1.tot_tar_pod.disabled=true;
  document.form1.tot_rec_pod.disabled=true;
 }
 if(userrole_js==="admin"){
 document.getElementById("huma_id").value =star[x][2];
 document.getElementById("HO_Backlogs").value =star[x][3];
 document.getElementById("Reason_HO").value =star[x][4];
  //alert("old f date :"+document.getElementById("ofd").value);
 //alert("old t date :"+document.getElementById("otd").value);
 
 document.getElementById("ofd").value =star[x][0];
 document.getElementById("otd").value =star[x][1];
   //alert("old f date :"+document.getElementById("ofd").value);
 document.form1.pod.disabled=false;
 document.form1.tot_tar_pod.disabled=false;
 document.form1.tot_rec_pod.disabled=false;
 
 //alert("old t date :"+document.getElementById("otd").value);

// document.form1.pod.disabled=true;
// document.form1.tot_tar_pod.disabled=true;
// document.form1.tot_rec_pod.disabled=true;
 //tot_tar_pod
 }
 if(userrole_js==="user"){
 document.getElementById("huma_id").value =star[x][2];
 document.getElementById("REMARKS_Backlogs_FS").value =star[x][3];
 //document.form1.pod.disabled=true;
 document.form1.tot_tar_pod.disabled=true;
 document.form1.tot_rec_pod.disabled=true;
 
 //document.getElementById("Reason_HO").value =star[x][4];
 }
 //alert("Control number is  :"+star[x][5]);
 document.getElementById("REMARKS_CONTROLENO").value =star[x][5];
 //document.form1.war_tdate.readOnly=true;//pod
 document.form1.tot_tar_pod.style.backgroundColor = "#EFFBEF";
 document.form1.pod.style.backgroundColor = "#EFFBEF";
 document.form1.tot_rec_pod.style.backgroundColor = "#EFFBEF";
 document.form1.tot_tar_pod.disabled=true;
 document.form1.tot_rec_pod.disabled=true;
  
 /*if(userrole_js==="unithead"){
  document.form1.pod.disabled=false;//pod
  }
  else*/ 
  document.form1.pod.disabled=true;//pod
 
 
 
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
 //document.getElementById("cal").style.display='none';
 ///document.getElementById("cal2").style.display='none';

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
	  } 
	 //alert("hhh");
	 document.form1.war_fdate.disabled=false;
	 document.form1.war_tdate.disabled=false;
	 document.form1.war_fdate.style.backgroundColor='skyblue';
	 document.form1.war_tdate.style.backgroundColor='skyblue';
	  if(userrole_js==="unithead"){
	 document.form1.BSFLUNIT_UCODE.disabled=false;//newly added code for the Query functionality
	 document.form1.BSFLUNIT_UCODE.readOnly=false;
	 document.form1.BSFLUNIT_UCODE.style.backgroundColor='skyblue';
	 document.form1.BSFLUNIT_UCODE.value="<%=uname_no%>"; 
	  }

	  if(userrole_js==="areahead"){
			 document.form1.area_name.disabled=false;//newly added code for the Query functionality
			 document.form1.area_name.readOnly=false;
			 document.form1.pod.readOnly=true;
			 document.form1.area_name.style.backgroundColor='skyblue';
			document.form1.area_name.value="<%=areacode%>"; 
		  }
		  if(userrole_js==="admin" || userrole_js==="audit"){
		 	 document.form1.huma_id.disabled=false;
			 document.form1.huma_id.disabled=false;//newly added code for the Query functionality
			 document.form1.huma_id.readOnly=false;
			 document.form1.pod.readOnly=true;
			 document.form1.huma_id.style.backgroundColor='skyblue';
			 document.form1.huma_id.value="<%=acode%>"; 
					 	 }

		  if(userrole_js==="user"){
			 	 document.form1.huma_id.disabled=false;
				 document.form1.huma_id.disabled=false;//newly added code for the Query functionality
				 document.form1.huma_id.readOnly=false;
				 document.form1.pod.readOnly=true;
				 document.form1.huma_id.style.backgroundColor='skyblue';
				 document.form1.huma_id.value="<%=acode%>"; 
				 	 }

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
 // document.getElementById("cal").style.display='none';
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
  } 
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
 //document.getElementById("cal").style.display='inline';
 //document.getElementById("cal2").style.display='inline';
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
 //$("#tableID tr:gt(1)").remove();//removing all the rows exception first two rows in current table//newly added for dynamic table

 if(userrole_js==="unithead"){
	 document.form1.BSFLUNIT_UCODE.value="<%=uname_no%>"; //alert("assigned unit_code is="+"<%=uname_no%>");
	 
	 }
 if(userrole_js==="admin" || userrole_js==="audit"){
	 document.form1.pod.readOnly=true;
	 document.form1.huma_id.value="<%=acode%>";
	 
 	 }

  if(userrole_js==="user"){
	  document.form1.pod.readOnly=true;
		 document.form1.huma_id.value="<%=acode%>"; 
	 }
  if(userrole_js==="areahead"){
		 document.form1.pod.readOnly=true;
	  }

  //document.form1.BSFLUNIT_UCODE.value='';
  //document.form1.unitname.value='';
 var MyDate = new Date();
 goDim("form1","new2");
document.form1.new2.disabled=true;
document.form1.query.disabled=true;
document.form1.exct.disabled=true;//from this line newly added code for Query functionality
document.form1.tot_tar_pod.disabled=true;
//document.form1.pod.disabled=false;
document.form1.pod.disabled=true;
 document.form1.tot_rec_pod.disabled=true;
//document.form1.addButton.disabled=false;
//document.form1.removeButton.disabled=false;
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
	
	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Wmenu.js"></script>
<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
//System.out.println("user role is :"+role);
if(role.equals("admin") || role.equals("audit") ||role.equals("areahead") || role.equals("unithead") || role.equals("user"))
{ %>
<form id="form1" name="form1" method="post">
<br />
<p align="right">
 <table width="1150" align="center">
			<tr>
				<td valign="top" width="1115" height="">
					<fieldset style="background-color:">
						<legend class="formTitle">
							<b>WAR Room Remarks</b>
						</legend>
						<table bgcolor="" width="100%" height="91%" border="0"
							align="center" bordercolor="#000000">
							<tr align="center">
							<td colspan="4" align="center" width="100%" height="">
				
						<div class="formTitle" align="">
						<%
if(role.equals("unithead"))
{ %>
							<b> UNIT  Remarks</b>
<%}
if(role.equals("areahead"))
{
%>	
							<b> AREA  Remarks</b>
<%
}
if(role.equals("admin"))
{
%>	
							<b> HO  Remarks</b>
<%
}
if(role.equals("user"))
{ %>
							<b> Field Staff Remarks</b>
<%} %>		
			</div></td>				
							</tr>
							<tr>
								<td colspan="4" align="center">
									<div align="right">
										<script type="text/javascript" src="JS/np.js"></script>
									</div>
								</td>
							</tr>
							<tr>
								<td width="100%" height="" colspan="4" align="center">
									<table align="center">


									
<%
if(role.equals("unithead"))
{ %>

										<tr>
											<td width="" height="" style=""><div align="right">Unit</div></td>
											<!--  <td width="" colspan="2" align="left"><div align="left"><input name="BSFLUNIT_UCODE" id="BSFLUNIT_UCODE" type="text"  style="border-color:#0099FF; size="20" onfocus="addSuggestion('BSFLUNIT_UCODE','BSFLUNIT_UCODE');" onblur="showEmp('BSFLUNIT_NAME.area_name',BSFLUNIT_UCODE.value,'');"/><input type="hidden" name="UHLOG_CONTROLENO2" id="UHLOG_CONTROLENO2"></input></div></td> -->
											<td width="" colspan="2" align="left" style=""><div
													align="left">
													<input name="BSFLUNIT_UCODE" id="BSFLUNIT_UCODE"
														type="text" size="30" maxlength="50"
														style="border-color: #0099FF;"
														onfocus="addSuggestion('BSFLUNIT_UCODE_roles','BSFLUNIT_UCODE');"
														/>
												</div></td>
										</tr>
										<tr>
											<td width="" align="right"><div align="right">From
													Date</div></td>
											<td width="" align="left" style="width: 281px;">
												<table width="" border="0" cellspacing="0" cellpadding="0"
													align="left">
													<tr>
														<td width="" colspan="2" align="left"
															style="width: 134px;"><input type="text"
															name="war_fdate" id="war_fdate" size="10" maxlength="10"
															onkeyup="allowupdate();" /> <a href="#"
															onclick="setYears(1947, 2050); showCalender(this, 'war_fdate');">
																<img id="cal" src="images/calender.png"
																onclick="allowupdate();" />
														</a>
														<div /></td>
														<td width="" colspan="2" style="width: 130px;">(dd-mm-yyyy)<br /></td>
													</tr>
												</table>

											</td>
											<td width="" align="right" style="width: 105px; "><div align="right">To
													Date</div></td>
											<td width="" align="left">
												<table width="" border="0" cellspacing="0" cellpadding="0"
													align="left">
													<tr>
														<td width="130px" align="left"><input type="text"
															name="war_tdate" id="war_tdate" size="10" maxlength="10"
															onkeyup="allowupdate()" /><!--  onblur="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,BSFLUNIT_UCODE.value);" -->
															
															 <a href="#"
															onclick="setYears(1947, 2050); showCalender(this, 'war_tdate');">
																<img id="cal2" src="images/calender.png"
																onclick="allowupdate();"/>
														</a>
														<div /></td>
														<td width="" colspan="2" style="width: 132px;">(dd-mm-yyyy)<br /></td>
													</tr>

												</table>
											</td>
										</tr>
										
<%}
if(role.equals("areahead"))
{
%>
										<tr>
										<td width="" height=""><div align="right">Region</div></td>
										<td width="" colspan="2" align="left" style=""><div align="left">
													<input name="area_name" id="area_name" type="text" size="30" maxlength="50" style="border-color: #0099FF;" onkeyup="allowupdate();" onfocus="addSuggestion('regoin_name','area_name');">
												</div></td>
										</tr>
										<tr>
											<td width="" align="right"><div align="right">From
													Date</div></td>
											<td width="" align="left" style="width: 281px;">
												<table width="" border="0" cellspacing="0" cellpadding="0"
													align="left">
													<tr>
														<td width="" colspan="2" align="left"
															style="width: 134px;"><input type="text"
															name="war_fdate" id="war_fdate" size="10" maxlength="10"
															onkeyup="allowupdate();" /> <a href="#"
															onclick="setYears(1947, 2050); showCalender(this, 'war_fdate');">
																<img id="cal" src="images/calender.png"
																onclick="allowupdate();" />
														</a>
														<div /></td>
														<td width="" colspan="2" style="width: 130px;">(dd-mm-yyyy)<br /></td>
													</tr>
												</table>

											</td>
											<td width="" align="right" style="width: 105px; "><div align="right">To
													Date</div></td>
											<td width="" align="left">
												<table width="" border="0" cellspacing="0" cellpadding="0"
													align="left">
													<tr>
														<td width="130px" align="left"><input type="text"
															name="war_tdate" id="war_tdate" size="10" maxlength="10"
															onkeyup="allowupdate()"  /><!-- onblur="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,area_name.value);" -->
															
															 <a href="#"
															onclick="setYears(1947, 2050); showCalender(this, 'war_tdate');">
																<img id="cal2" src="images/calender.png"
																onclick="allowupdate();"/>
														</a>
														<div /></td>
														<td width="" colspan="2" style="width: 132px;">(dd-mm-yyyy)<tr>
											
											
										</tr><br /></td>
													</tr>

												</table>
											</td>
										</tr>
										
<%
}
if(role.equals("admin"))
{
%>
										<tr>
											<td> 
											<input name="huma_id" type="hidden" id="huma_id"/>
											
											
											</td>
										</tr>
										<tr>
											<td width="" align="right"><div align="right">From
													Date</div></td>
											<td width="" align="left" style="width: 281px;">
												<table width="" border="0" cellspacing="0" cellpadding="0"
													align="left">
													<tr>
														<td width="" colspan="2" align="left"
															style="width: 134px;"><input type="text"
															name="war_fdate" id="war_fdate" size="10" maxlength="10"
															onkeyup="allowupdate();" /> <a href="#"
															onclick="setYears(1947, 2050); showCalender(this, 'war_fdate');">
																<img id="cal" src="images/calender.png"
																onclick="allowupdate();" /><input type="hidden" name="ofd" id="ofd"/>
														</a>
														<div /></td>
														<td width="" colspan="2" style="width: 130px;">(dd-mm-yyyy)<br /></td>
													</tr>
												</table>

											</td>
											<td width="" align="right" style="width: 105px; "><div align="right">To
													Date</div></td>
											<td width="" align="left">
												<table width="" border="0" cellspacing="0" cellpadding="0"
													align="left">
													<tr>
														<td width="130px" align="left"><input type="text"
															name="war_tdate" id="war_tdate" size="10" maxlength="10"
															onkeyup="allowupdate()"  /><input type="hidden" name="otd" id="otd" />
															
															
															 <a href="#"
															onclick="setYears(1947, 2050); showCalender(this, 'war_tdate');">
																<img id="cal2" src="images/calender.png"
																onclick="allowupdate();"/>
														</a>
														<div /></td>
														<td width="" colspan="2" style="width: 132px;">(dd-mm-yyyy)<br /></td>
													</tr>

												</table>
											</td>
										</tr>
										
<%
}
if(role.equals("user"))
{ %>
										<tr>
											<td width="" height="" align="right" style=""><div
													align="right">Field Staff Code</div></td>
											<!--  <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" id="huma_id" style="border-color:#0099FF; width: 264px" onkeyup="allowupdate();" maxlength="57" onfocus="addSuggestion2('huma_id','huma_id',BSFLUNIT_UCODE.value);"></div></td>  -->
											<td width="" colspan="2" align="left"><div align="left">
													<input name="huma_id" type="text" size="40" id="huma_id"
														style="border-color: #0099FF;" onkeyup="allowupdate();"
														maxlength="57"
														onfocus="addSuggestion('huma_id_recovery','huma_id');">
												</div></td>
										</tr>
										<tr>
											<td width="" align="right"><div align="right">From
													Date</div></td>
											<td width="" align="left" style="width: 281px;">
												<table width="" border="0" cellspacing="0" cellpadding="0"
													align="left">
													<tr>
														<td width="" colspan="2" align="left"
															style="width: 134px;"><input type="text"
															name="war_fdate" id="war_fdate" size="10" maxlength="10"
															onkeyup="allowupdate();" /> <a href="#"
															onclick="setYears(1947, 2050); showCalender(this, 'war_fdate');">
																<img id="cal" src="images/calender.png"
																onclick="allowupdate();" />
														</a>
														<div /></td>
														<td width="" colspan="2" style="width: 130px;">(dd-mm-yyyy)<br /></td>
													</tr>
												</table>

											</td>
											<td width="" align="right" style="width: 105px; "><div align="right">To
													Date</div></td>
											<td width="" align="left">
												<table width="" border="0" cellspacing="0" cellpadding="0"
													align="left">
													<tr>
														<td width="130px" align="left"><input type="text"
															name="war_tdate" id="war_tdate" size="10" maxlength="10"
															onkeyup="allowupdate()" /><!--  onblur="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,huma_id.value);" -->
															
															 <a href="#"
															onclick="setYears(1947, 2050); showCalender(this, 'war_tdate');">
																<img id="cal2" src="images/calender.png"
																onclick="allowupdate();Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,huma_id.value);"/>
														</a>
														<div /></td>
														<td width="" colspan="2" style="width: 132px;">(dd-mm-yyyy)<br /></td>
													</tr>

												</table>
											</td>
										</tr>
										
<%} %>	


								
										<tr>
											<td width="" height="" align="right" style=""><div
													align="right"><font color="blue">Actual POD Amount (As on 'From Date') Rs.</font><b></div></td>
											<td width="" align="left" style="width: 281px;">
											<table>
<%
if(role.equals("unithead"))
{ %>
											<tr><td><input name="pod" type="text" id="pod" onkeyup="allowupdate();"   onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,BSFLUNIT_UCODE.value);"></td>									
											<tr>
<%}
if(role.equals("areahead"))
{
%>											
											<tr><td><input name="pod" type="text" id="pod" onkeyup="allowupdate();"  onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,area_name.value);"></td>									
											<tr>
<%}
if(role.equals("admin"))
{
%>											
											<tr><td><input name="pod" type="text" id="pod" onkeyup="allowupdate();"   onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,huma_id.value);"></td>									
											<tr>
<%}
if(role.equals("user"))
{ %>
											
											<tr><td><input name="pod" type="text" id="pod" onkeyup="allowupdate();"  onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,huma_id.value);"></td>									
											<tr>
<%} %>											
											</table>
											</td>
											<td width="" align="right"><div align="right"
													style="width: 175px;"><font color="blue">Total Target POD Rs.</font></div></td>
											<td width=""  align="left">
											<table width="" border="0" cellspacing="0" cellpadding="0"
													align="left">
											<tr><td width="130px" align="left"><input name="tot_tar_pod" type="text" id="tot_tar_pod" disabled="disabled" >
												</td>
											</tr>		
											</table>												
										</tr>
										<tr>
											<td width="" align="right"><div align="right"
													style="width: 175px;"><font color="blue">Total Recovered POD Rs.</font></div></td>
											<td width="" colspan="2" align="left"><div align="left">
													<input name="tot_rec_pod" type="text" id="tot_rec_pod" style="width: 134px;" disabled="disabled">
												</div></td>
										</tr>
										<tr>
											<td><input type="hidden" name="REMARKS_CONTROLENO"
												id="REMARKS_CONTROLENO" /></td>
										</tr>
										<%
if(role.equals("unithead"))
{ %>
<!-- 										<tr>
											<td width="" height="" style=""><div align="right">Unit</div></td>
											 <td width="" colspan="2" align="left"><div align="left"><input name="BSFLUNIT_UCODE" id="BSFLUNIT_UCODE" type="text"  style="border-color:#0099FF; size="20" onfocus="addSuggestion('BSFLUNIT_UCODE','BSFLUNIT_UCODE');" onblur="showEmp('BSFLUNIT_NAME.area_name',BSFLUNIT_UCODE.value,'');"/><input type="hidden" name="UHLOG_CONTROLENO2" id="UHLOG_CONTROLENO2"></input></div></td>
											<td width="" colspan="2" align="left" style=""><div
													align="left">
													<input name="BSFLUNIT_UCODE" id="BSFLUNIT_UCODE"
														type="text" size="30" maxlength="50"
														style="border-color: #0099FF;"
														onfocus="addSuggestion('BSFLUNIT_UCODE_roles','BSFLUNIT_UCODE');"
														onblur="showEmp('BSFLUNIT_NAME.area_name',BSFLUNIT_UCODE.value,'');" /><input
														type="hidden" name="UHLOG_CONTROLENO2"
														id="UHLOG_CONTROLENO2"></input>
												</div></td>
										</tr>
 -->
										<tr>
											<td width="" height="" align="right" style=""><div
													align="right">Action plan for covering up backlogs,
													at UH</div></td>
											<!--  <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" id="huma_id" style="border-color:#0099FF; width: 264px" onkeyup="allowupdate();" maxlength="57" onfocus="addSuggestion2('huma_id','huma_id',BSFLUNIT_UCODE.value);"></div></td>  -->
											<td width="" colspan="2" align="left"><div align="left">
													<textarea name="UH_Backlogs" id="UH_Backlogs" cols="50"
														rows="3"
														onkeyup="EnforceMaximumLength(this,300); allowupdate();"
														onblur="EnforceMaximumLength(this,300);"
														onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,BSFLUNIT_UCODE.value);"></textarea>
												</div></td>
										</tr>
										<tr>
											<td width="" height="" align="right" style="">Reason for
												having Less Target than Actual POD at UH</td>
											<!--  <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" id="huma_id" style="border-color:#0099FF; width: 264px" onkeyup="allowupdate();" maxlength="57" onfocus="addSuggestion2('huma_id','huma_id',BSFLUNIT_UCODE.value);"></div></td>  -->
											<td width="" colspan="2" align="left"><div align="left">
													<textarea name="Reason_UH" id="Reason_UH" cols="50"
														rows="3"
														onkeyup="EnforceMaximumLength(this,300); allowupdate();"
														onblur="EnforceMaximumLength(this,300);"
														onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,BSFLUNIT_UCODE.value);"></textarea>
												</div></td>
										</tr>
<%}
if(role.equals("areahead"))
{
%>
<!-- 										<tr>
											<td width="" height=""><div align="right">Region</div></td>
											<td width="" colspan="2" align="left" style=""><div
													align="left">
													<input name="area_name" id="area_name" type="text"
														size="30" maxlength="50" style="border-color: #0099FF;"
														onkeyup="allowupdate();"
														onfocus="addSuggestion('regoin_name','area_name');" />
												</div></td>
										</tr>
 -->										<tr>
											<td width="" height="" align="right" style=""><div
													align="right">Action plan for covering up backlogs,
													at AH</div></td>
											<!--  <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" id="huma_id" style="border-color:#0099FF; width: 264px" onkeyup="allowupdate();" maxlength="57" onfocus="addSuggestion2('huma_id','huma_id',BSFLUNIT_UCODE.value);"></div></td>  -->
											<td width="" colspan="2" align="left"><div align="left">
													<textarea name="AH_Backlogs" id="AH_Backlogs" cols="50"
														rows="3"
														
														onkeyup="EnforceMaximumLength(this,300); allowupdate();"
														onblur="EnforceMaximumLength(this,300);"
														onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,area_name.value);"></textarea>
												</div></td>
										</tr>
										<tr>
											<td width="" height="" align="right" style="">Reason for
												having Less Target than Actual POD at AH</td>
											<!--  <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" id="huma_id" style="border-color:#0099FF; width: 264px" onkeyup="allowupdate();" maxlength="57" onfocus="addSuggestion2('huma_id','huma_id',BSFLUNIT_UCODE.value);"></div></td>  -->
											<td width="" colspan="2" align="left"><div align="left">
													<textarea name="Reason_AH" id="Reason_AH" cols="50"
														rows="3"
														onkeyup="EnforceMaximumLength(this,300); allowupdate();"
														onblur="EnforceMaximumLength(this,300);"
														onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,area_name.value);"></textarea>
												</div></td>
										</tr>
<%}
if(role.equals("admin"))
{
%>
<!-- 										<tr>
											<td width="" height="" align="right" style=""><div
													align="right">Employee Code</div></td>
											 <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" id="huma_id" style="border-color:#0099FF; width: 264px" onkeyup="allowupdate();" maxlength="57" onfocus="addSuggestion2('huma_id','huma_id',BSFLUNIT_UCODE.value);"></div></td> 
											<td width="" colspan="2" align="left"><div align="left">
													<input name="huma_id" type="text" size="40" id="huma_id"
														style="border-color: #0099FF;" onkeyup="allowupdate();"
														maxlength="57"
														onFocus="addSuggestion('Huma_huma_id','huma_id');">
												</div></td>
										</tr>
 -->										<tr>
											<td width="" height="" align="right" style=""><div
													align="right">Action plan for covering up backlogs,
													at HO</div></td>

											<td width="" colspan="2" align="left"><div align="left">
													<textarea name="HO_Backlogs" id="HO_Backlogs" cols="50"
														rows="3"
														onkeyup="EnforceMaximumLength(this,300); allowupdate();"
														onblur="EnforceMaximumLength(this,300);"
														onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,huma_id.value);"></textarea>
												</div></td><!-- onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,huma_id.value);" -->
														
										</tr>
										<tr>
											<td width="" height="" align="right" style=""><div
													align="right">Reason for having Less Target than
													Actual POD at HO</div></td>
											<!--  <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" id="huma_id" style="border-color:#0099FF; width: 264px" onkeyup="allowupdate();" maxlength="57" onfocus="addSuggestion2('huma_id','huma_id',BSFLUNIT_UCODE.value);"></div></td>  -->
											<td width="" colspan="2" align="left"><div align="left">
													<textarea name="Reason_HO" id="Reason_HO" cols="50"
														rows="3"
														onkeyup="EnforceMaximumLength(this,300); allowupdate();"
														onblur="EnforceMaximumLength(this,300);"
														onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,huma_id.value);"></textarea>
												</div></td>
										</tr>
<%}
if(role.equals("user"))
{ %>
<!-- 										<tr>
											<td width="" height="" align="right" style=""><div
													align="right">Field Staff Code</div></td>
											 <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" id="huma_id" style="border-color:#0099FF; width: 264px" onkeyup="allowupdate();" maxlength="57" onfocus="addSuggestion2('huma_id','huma_id',BSFLUNIT_UCODE.value);"></div></td> 
											<td width="" colspan="2" align="left"><div align="left">
													<input name="huma_id" type="text" size="40" id="huma_id"
														style="border-color: #0099FF;" onkeyup="allowupdate();"
														maxlength="57"
														onfocus="addSuggestion('huma_id_recovery','huma_id');
														onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,huma_id.value);"">
												</div></td>
										</tr>
 -->										<tr>
											<td width="" height="" align="right" style=""><div
													align="right">Action plan for covering up backlogs,
													at Field Staff</div></td>
											<!--  <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" id="huma_id" style="border-color:#0099FF; width: 264px" onkeyup="allowupdate();" maxlength="57" onfocus="addSuggestion2('huma_id','huma_id',BSFLUNIT_UCODE.value);"></div></td>  -->
											<td width="" colspan="2" align="left"><div align="left">
													<textarea name="REMARKS_Backlogs_FS"
														id="REMARKS_Backlogs_FS" cols="50" rows="3"
														onkeyup="EnforceMaximumLength(this,300); allowupdate();"
														onblur="EnforceMaximumLength(this,300);"
														onfocus="Getpods('war_remarks_pods',war_fdate.value,war_tdate.value,huma_id.value);"></textarea>
												</div></td>
										</tr>
<%} %>									<tr>
											<td><input
														type="hidden" name="UHLOG_CONTROLENO2"
														id="UHLOG_CONTROLENO2"></input></td>
										</tr>	
									</table>
								</td>
							</tr>
							</tr>
							<tr></tr>
						</table>
				</td>
			</tr>
			<tr >
       <td colspan="4" align="center"  height="60">
        <div align="center">
          <table>
            <tr>
              <td ><script type="text/javascript" src="JS/Wbuttons.js"></script>
                <br />
                <table id="msg2" style="visibility:hidden; position:absolute;" align="">
                  <tr>
                    <td >Execute</td>
                        </tr>
                  </table></td>
                  <td   width="" align="left" >&nbsp;&nbsp;
                  <%
if(role.equals("unithead"))
{ %>
                    <input type="button" id="exct" name="exct" class="groovybutton" value="&euro;"    title="" onmouseover="goLite(this.form.name,this.name); showmenu('msg');"
   						onmouseout="goDim(this.form.name,this.name); hidemenu('msg');" onclick="showEmpn('war_remarks',BSFLUNIT_UCODE.value,war_fdate.value,war_tdate.value);"/>
<%}
if(role.equals("admin"))
{
%>  
                    <input type="button" id="exct" name="exct" class="groovybutton" value="&euro;"    title="" onmouseover="goLite(this.form.name,this.name); showmenu('msg');"
   						onmouseout="goDim(this.form.name,this.name); hidemenu('msg');" onclick="showEmpn('war_remarks',huma_id.value,war_fdate.value,war_tdate.value);"/>

                   
 <%}
if(role.equals("areahead"))
{
System.out.println("area head in jsp");
%>

                     <input type="button" id="exct" name="exct" class="groovybutton" value="&euro;"    title="" onmouseover="goLite(this.form.name,this.name); showmenu('msg');"
   						onmouseout="goDim(this.form.name,this.name); hidemenu('msg');" onclick="showEmpn('war_remarks',area_name.value,war_fdate.value,war_tdate.value);"/>
 
<%}
if(role.equals("user"))
{
%>    
                     <input type="button" id="exct" name="exct" class="groovybutton" value="&euro;"    title="" onmouseover="goLite(this.form.name,this.name); showmenu('msg');"
   						onmouseout="goDim(this.form.name,this.name); hidemenu('msg');" onclick="showEmpn('war_remarks',huma_id.value,war_fdate.value,war_tdate.value);"/>
<%} %>              <br />
                    <table id="msg" style="visibility:hidden; position:absolute;" align="">
                      <tr>
                        <td >Execute</td>
                        </tr>
                    </table></td>
                </tr>
          </table>
        </div></td>
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
            <td align="center">
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