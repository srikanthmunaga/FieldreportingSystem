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
<title>STATE HEAD Monthly Work Plan</title>
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
  				String ss=null;
  				//String aname=null;
  				String ucode=null;
  				String designation=null;
  				String name=null;
  				String city=null;
				Connection con=new JdbcConnect().getConnection();
				Statement svst=con.createStatement();
				ResultSet svrs=svst.executeQuery(sql);
				if(svrs.next())
				{
				huma_id=svrs.getString(1);
				//name=svrs.getString(2);
				System.out.println("huma_id :"+huma_id);
				}//BSFLUNIT_UCODE

 				String sqlu="select HUMA_DESIGNATION,CITY_ID from HUMA_MSTR where huma_id='"+huma_id+"'";
  				//System.out.println();
  			
				
				Statement ust=con.createStatement();
				ResultSet urs=svst.executeQuery(sqlu);
				if(urs.next())
				{
				designation=urs.getString(1);
				city=urs.getString(2);
				//System.out.println("UCODE :"+ucode);	
				
				}//BSFLUNIT_UCODE

				String sql6="select BSFLUNIT_UCODE from HUMA_MSTR where huma_id='"+huma_id+"'";
  				//System.out.println();
  			
				
				Statement st6=con.createStatement();
				ResultSet rs6=svst.executeQuery(sql6);
				if(rs6.next())
				{
				uname_no=rs6.getString(1);
				//city=urs.getString(2);
				System.out.println("UCODE :"+uname_no);	
				
				}//BSFLUNIT_UCODE                       
 				//String sql1="select BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE,AREA_ID from BSFLUNIT_MSTR where BSFLUNIT_UCODE='"+ucode+"'";
  				//System.out.println();
  			
				
				//Statement st=con.createStatement();
				//ResultSet rs=svst.executeQuery(sql1);
				//if(rs.next())
			//	{
				//uname_no=rs.getString(1);
				//areaid=rs.getString(2);
					//			System.out.println("UNAME NUMBER :"+uname_no);
								//System.out.println("AREA CODE :"+areaid);
				
				//}//BSFLUNIT_UCODE
				String sql2="select AREA_name from AREA_MSTR where AREA_ID='"+areaid+"'";
				
				Statement ast=con.createStatement();
				ResultSet ars=svst.executeQuery(sql2);
				if(ars.next())
				{
				aname=ars.getString(1);
				//System.out.println(areaid);
				
				
				}//BSFLUNIT_UCODE
				
				String sql3="select HUMA_FNAME from HUMA_MSTR where HUMA_ID='"+huma_id+"'";
  				//System.out.println();
  			
				
				Statement st4=con.createStatement();
				ResultSet rs4=svst.executeQuery(sql3);
				if(rs4.next())
				{
				name=rs4.getString(1);
				System.out.println(name);
				//areaid=rs.getString(2);
								//System.out.println("UNAME NUMBER :"+uname_no);
								//System.out.println("AREA CODE :"+areaid);
				
				}
				
				String sql5="select GRADE_ABBREVIATION from GRADE_MSTR where GRADE_ID='"+designation+"'";
  				//System.out.println();
  			
				
				Statement st5=con.createStatement();
				ResultSet rs5=svst.executeQuery(sql5);
				if(rs5.next())
				{
				ss=rs5.getString(1);
				//areaid=rs.getString(2);
								//System.out.println("UNAME NUMBER :"+uname_no);
								//System.out.println("AREA CODE :"+areaid);
				
				}
  
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

<style type="text/css">
input.addRow{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:24px;     background-image:url(images/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px; display:none;}
input.delRow{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:24px;     background-image:url(images/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px; display:none;}

td.lastCol{  
	display:none;
}

/*table.tableID2 tr > td:last-child { 

}*/
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

<script type="text/javascript">
var j=0,emp2='';
var star2;//,i;//global variable
function showEmps2(decide,obj,emp)
{
alert("Inside showEmps2()");
//alert("emp is"+emp);
var activityvalue=emp;
//alert("activity values is"+activityvalue);
var activity= activityvalue.substring(activityvalue.lastIndexOf('-')+1);
alert("the new activity is "+activity);
var par=obj.parentNode;
while(par.nodeName.toLowerCase()!='tr'){   par=par.parentNode;  } 
var i=par.rowIndex;
j=i-1;
 
 	
		if((activity!="") && (activity=='Field Visit')){ //FieldVisit
	//alert("came to");		
		var unitvisited=document.getElementsByName('visited');
		unitvisited[j].style.visibility='visible';
		//alert(j);
		
	}
		else if(activity=='Unit Visit'){ //FieldVisit
			//	alert("came to"+value);		
			var unitvisited=document.getElementsByName('visited');
			unitvisited[j].style.visibility='visible';
			//alert(j);
			
		}else{
			var unitvisited=document.getElementsByName('visited');
			unitvisited[j].style.visibility='hidden';	
		}
 	
			/* var date1=document.getElementsByName('war_date');
			for(var x=0;x<=f;x++){
				alert("Date Value = "+document.getElementsByName('war_date')[x].value);
				var cate = document.getElementsByName('act_id')[x].value;
				var s=document.getElementById('act_id').value;
				alert("s="+s);
				//alert("Day Value = "+document.getElementsByName('war_day')[x].value);
			//	alert("Cate = "+cate);
				if(document.getElementsByName('act_id')[x].value == "AC4")
					{
				//	alert("Field Visit Selected");
					document.getElementsByName('unit_visited')[x].style.visibility='hidden';
					}
				
				/* else if(document.getElementsByName('act_id')[x].value == ""){
					
				} */
				/* else if((document.getElementsByName('act_id')[x].value != "") && (document.getElementsByName('act_id')[x].value != "006")){
					//alert("Other Selected");
					//var textboxx = 'textbox' + (x+1);
					//alert("textboxx = "+textboxx);
					document.getElementsByName('unit_visited')[x].style.visibility='hidden';
					document.getElementsByName('addButton')[x].style.visibility='hidden';
					document.getElementsByName('removeButton')[x].style.visibility='hidden';
					document.getElementsByName('visited_with')[x].style.visibility='hidden';
					document.getElementsByName('war_cust_count')[x].style.visibility='hidden';
					document.getElementsByName('war_sdrcust_count')[x].style.visibility='hidden'; */
				//	document.getElementsByName('TextBoxesGroup')[x].style.visibility='hidden';
					//var name=prompt("please narrate the remarks","");
				//	while(name=="" || name==null)
				//	{ 
					//   name = prompt("Please narrate the Remarks","");
						
			//		}
				//	while(name.length>50)
					//{
					// name = prompt("Please narrate the billing ,max of 50 characters",name);
					 // while(name==null) 
					 // {
					  // name = prompt("Please narrate the billing","");
					  // while(name=="") 
					  // name = prompt("Please narrate the billing","");
					  //}//while(name2==null) 
					 //}
					//document.getElementsByName('observations')[x].value=name;
					/* var unitvisited=document.getElementsByName('unit_visited');
					unitvisited[x].style.visibility='hidden';
					var addbtn=document.getElementsByName('addButton');
					addbtn[j].style.visibility='hidden';
					var delbtn=document.getElementsByName('removeButton');
					delbtn[j].style.visibility='hidden';
					var namevisited=document.getElementsByName('visited_with');
					namevisited[j].style.visibility='hidden';
					var custcount=document.getElementsByName('war_cust_count');
					custcount[j].style.visibility='hidden';
					var sdrcount=document.getElementsByName('war_sdrcust_count');
					sdrcount[j].style.visibility='hidden';
					 */
				}
		
			
		
		
	</script>



<script type="text/javascript">
var j=0,emp2='';
var star2;//,i;//global variable
function showEmps1(decide,obj,emp)
{

//alert("came here to showemps");
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
 //alert(url);
 xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
 xmlHttp.send(null);//alert("the ajax request made ");
 	var showdata = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
	//alert("hey goy the show data is:"+showdata);
	showdata=showdata.replace(/::::::$/,"");//removes the "::::::"  from the end of the "data" array

	var strar = showdata.split("::::::");
	//alert("Strar = "+strar);
	 if(strar=='')//if the entered area_id is not exist in the DB then this if code runs
	 {
		document.getElementsByName("act_id").value='';
	 }
	 else if(strar.length>0)
	 {
	// alert("strar.length>0");
	// alert("Getting the element name");	
	//var transferedunit = document.getElementsByName('transferedunit');
	
	//var currentunit = document.getElementsByName('currentunit');

					
		var value = strar[0];
		//alert("the value from getuser update is "+value);	
		if(value=="006"){
 			//alert("came to"+value);		
 			var unitvisited=document.getElementsByName('visited');
			unitvisited[j].style.visibility='visible';
			var remarks=document.getElementsByName('activities_planned');
			remarks[j].style.visibility='visible';
		
		
		}
		       
				else if(value=="002"){
	 			//alert("came to"+value);		
	     		var unitvisited=document.getElementsByName('visited');
				//alert("enabling");
				unitvisited[j].style.visibility='visible';	
				var remarks=document.getElementsByName('observations');
				remarks[j].style.visibility='visible';
			
				}
			
		else{
				//var name=prompt("please narrate","");
				
				//var rowindex=j;
				//alert("the value of j is"+j);
				var unitvisited=document.getElementsByName('visited');
				unitvisited[j].style.visibility='hidden';
				var remarks=document.getElementsByName('activities_planned');
	//			alert("came here");
					
				var name=prompt("please narrate","");
				while(name=="" || name==null)
				{ //if(name2.length>50)
				    //name2 = prompt("Please narrate the billing ,max of 50 characters",name2);
				  //else if(name2.length==0 || name2==null)
				   name = prompt("Please narrate the billing","");
					//while(name2.length>50) name2 = prompt("Please narrate the billing ,max of 50 characters",name2);
					//alert("hey the name2="+name2);
				}
				//if(name2!="")
				while(name.length>50)
				{
				 name = prompt("Please narrate the billing ,max of 50 characters",name);
				  while(name==null) 
				  {
				   name = prompt("Please narrate the billing","");
				   while(name=="") 
				   name = prompt("Please narrate the billing","");
				  }//while(name2==null) 
				 }
				
				remarks[j].value=name;

			}
			
		}
		
			
		
		//var sta_name = document.getElementsByName('unit_visited'); 
		//sta_name[j].value = strar[0];
		
		else
		{
		act_id[j].value = "";
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
	function generateTable(war_period) {// this funciton just used to get the row count of the table
		if (document.form1.onSubmit == validateTop()) {
			///*//*/alert("hey inside the generateTable function and going to get the moth genereted dates");
			sessioncheck();
			xmlHttp = GetXmlHttpObject();
			///*//*/alert("hey got the boject="+xmlHttp);
			if (xmlHttp == null)
				return;
			var url = "getuserupdate";
			var huma_id = document.form1.emp_no.value;
			//var weekoff_id = document.form1.weekoff_id.value;//document.getElementById("weekoff_id").value;
			/////*//*/alert("hey just got the weekoff_id="+weekoff_id);
			var war_fdate = document.form1.war_fdate.value;
			var war_tdate = document.form1.war_tdate.value;
			///*//*/alert("hey just got the war_tdate="+war_tdate);
			url = url + "?decide=war_period&key1=" + war_fdate + "&key2="
					+ war_tdate + "&huma_id=" + huma_id;///*//*/alert("hey the url="+url);
					//alert(url);
			xmlHttp.open("GET", url, false);//xmlHttp.open("GET",url,true);
			xmlHttp.send(null);///*//*/alert("the ajax request made ");

			var showdata = xmlHttp.responseText.replace(/^\s+/, '').replace(
					/\s+$/, ''); //removes the starting & ending spaces spaces
			showdata = showdata.replace(/\/\/\/\/\/\/$/, "");//removes the "//////"  from the end of the "data" array
			star3 = showdata.split("//////"); ///*//*/alert("hey the star3 object length="+star3.length);
			///*//*/alert("hey the got object is="+star3);

			if (star3[0] == '')//for some all holidays selection,then it rerutns only '' as responce data object
			{
				alert("No records found");
				$("#tableID tr:gt(1)").remove();//deleteRow('tableID');
			} else if (star3[0].substr(0, 2) == 'NO')//if(star3=='')//if(star3.length<=3)//because here 1st 3 items of validation self empid,and existance of plan ,dates are in the selected weekoff or not
			{
				alert(star3[0].substr(2, star3[0].length - 2)); ///*//*/alert("No records found"); 
				$("#tableID tr:gt(1)").remove();//deleteRow('tableID');
			} else if (star3.length >= 0) {///*//*/alert("hey inside the else if :this means the star is not empty");
				//  for(var k=0; k<star.length; k++)
				//  star[k] = star[k].split("::::::");
				var f = document.getElementById("form1");//going to make four fields of table as enable mode
				for ( var i = 0; i < f.length; i++) {
					if (f[i].type != "button") {
						f[i].disabled = false;
					}
				}//end of making four fields of table as enable mode
				document.form1.huma_id.readOnly = true;
				//document.form1.weekoff_id.readOnly = true;
				document.form1.war_fdate.readOnly = true;
				document.form1.war_tdate.readOnly = true;
				document.getElementById("cal").style.display = 'none';
				document.getElementById("cal2").style.display = 'none';
				goDim("form1", "generate");
				document.form1.generate.disabled = true;//generate button should be disable after generating the table of dates
				count = star3.length;
				//$(".addRow").trigger('click');//addTable('tableID'); 
				
				///*//*/alert("hey the addTable functin called successfully and going to asign the dates to the fields");
				var war_date = document.getElementsByName('war_date');
				var war_day = document.getElementsByName('war_day');
				//alert("Rajesgh Check Length = "+star3.length);
				for ( var i = 0; i < star3.length; i++)
					{
					war_date[i].value = star3[i].split(" ")[0];
					//alert("123");
					war_day[i].value = star3[i].split(" ")[1];
					//alert("456");
					if(i!=star3.length-1)
						{
						//alert("abc");
					$(".addRow").trigger('click');//addTable('tableID');
						}
					}
				
			}//else if of the star3 object is not empty
		}// if(document.form1.onSubmit==validateTop()) 
		else {
			return false;
		}
	}//generateTable()
//===============================================
function validateTop()
{
   var empid=new RegExp("[0-9]{4}");
   var weekoffid=new RegExp("[0-9]{2}");
   var patt2=new RegExp("[A-Za-z0-9]");	  
   
 if(document.form1.huma_id.value=='')
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
	  /* if(fdate > sysdate)
     	{
		 alert("From date should not be greater than the System date");
		 return false; 
	     }*/
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
	   /*if(tdate > sysdate)
     	{
		 alert("To date should not be grater than the System date");
		 return false; 
	     }*/
	   if(fdate > tdate)
     	{
		 alert("From date should be less than To date");
		 document.form1.war_fdate.focus();
		 return false; 
	     }
	//date comparision code exit here*/

}//validateTop()
function validateForm()
{
if(!(document.form1.generate.disabled))
{//alert("hey inside the generate button enable");
   alert("Please Generate Week and enter the plan first");
   document.form1.generate.focus();
   return false;
 }
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
  //huma_id validation
  if(document.form1.huma_id.value=='')
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
  var date=new RegExp("[0-9]");
//war_date validation
/*	if(!date.test(document.form1.war_date.value))
    {
      alert("Please enter the Date");
	  document.form1.war_date.focus();
      return false;
    }
	if(document.form1.war_date.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(document.form1.war_date.value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	var returnval=false;//validformat
	if(!validformat.test(document.form1.war_date.value))
	{
	alert("Please enter the Date correct format");
	document.form1.war_date.focus();
	return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		alert("Invalid month or date found in Date");
		document.form1.war_date.focus();
		return false;
	  }
	 }//if(war_date!="") 
//date comparision code   
	   var currentTime = new Date();
       var mm = currentTime.getMonth() + 1;
       var dd = currentTime.getDate();
       var yyyy = currentTime.getFullYear();
       var sysdate=dd+"-"+mm+"-"+yyyy;
	   sysdate=sysdate.split("-");
	   var sysdate = new Date(sysdate[2], sysdate[1]-1, sysdate[0]); //var date1 = new Date(yr1, mon1, dt1); 

	   var ldate=(document.form1.war_date.value).split("-");
       var ldate = new Date(ldate[2], ldate[1]-1, ldate[0]); //alert("hey to rday date is ="+prdate);
	   if(ldate > sysdate)
     	{
		 alert("selected Date should not more than the System date");
		 document.form1.war_date.focus();
		 return false; 
	     }
//date comparision code exit here
*/
//validation for the dynamic payment schedule table
	 
	var war_date = document.getElementsByName('war_date');
	//var war_villages = document.getElementsByName('war_villages');
    //var war_cust_count = document.getElementsByName('war_cust_count');
	//var war_sdrcust_count = document.getElementsByName('war_sdrcust_count');//var ops_narration = document.getElementsByName('ops_narration');
//	var war_sdrcust_count_opted = document.getElementsByName('war_sdrcust_count_opted');
	var n= war_date.length;
 
//alert("MSR DEBUG war_date before for loop");
for (var k = 0; k < n; k++)
{
	if(!date.test(war_date[k].value))
	//if(war_date[k].value=='')  
   {
	 alert("Please enter Date correctly");
	 war_date[k].focus();
 	 return false;
	}

//war_village validation and manupulations
//	}//for (var k = 0; k < n; k++) 
	//var m = k+1;
	//alert("value of the m :"+m);
//	var textbox = document.getElementsByName('textbox'+m);
//	var len=textbox.length;
//	var blankCount=0;
//	for(var j=0; j<len; j++)
//	{	//alert("textbox[j].value="+textbox[j].value);
	
		
//war_cust_count validation start here	 
		  // var Area=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
		//   if(war_cust_count[k].style.visibility=='visible')
		 //  {
			   //alert("the number after the point="+war_cust_count[k].value.split('.')[1].length);
		   // if((!Area.test(war_cust_count[k].value)) || (war_cust_count[k].value.length < 1) || (Number(war_cust_count[k].value) == 0))
		   // {// alert("hey the inside ops_place");
		//	 alert("Please enter the total No.of Customers to be visited correctly"); 
		//	 war_cust_count[k].focus();
		// 	 return false;
			// }
//war_sdrcust_count validation start here	 
		//   var Quantity=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
		 //  if(war_sdrcust_count[k].style.visibility=='visible')
		  /// {//alert("the number after the point="+war_sdrcust_count[k].value.split('.')[1].length);
		  //  if((!Quantity.test(war_sdrcust_count[k].value)) || (war_sdrcust_count[k].value.length < 1))// || (Number(war_sdrcust_count[k].value) == 0))
		   // { //alert("hey the inside ops_place");
			// alert("Please enter No.of Customers to be moved towards SDR correctly"); 
			// war_sdrcust_count[k].focus();
		 	// return false;
			// }
			//}//if(war_sdrcust_count[k].style.visibility=='visible') alert("the number after the point="+war_sdrcust_count[k].value.split('.')[1]);

//war_sdrcust_count_opted validation start here	 
//		   var Price=new RegExp("^[ ]*[0-9]{1,10}(\.[0-9]{1,2})?[ ]*$");
	///	   if(war_sdrcust_count_opted[k].style.visibility=='visible')
		//   {//alert("the number after the point="+war_sdrcust_count_opted[k].value.split('.')[1].length);
		  //  if((!Price.test(war_sdrcust_count_opted[k].value)) || (war_sdrcust_count_opted[k].value.length < 1))// || (Number(war_sdrcust_count_opted[k].value) == 0))
		    //{ //alert("hey the inside ops_place");
			// alert("Please enter Actual No.of customers opted for SDR correctly"); 
			/// war_sdrcust_count_opted[k].focus();
		 	 //return false;
			// }
		   //}  
//cross checking parseInt()
	/*
		if(parseInt(war_sdrcust_count[k].value) > parseInt(war_cust_count[k].value))// || (Number(war_sdrcust_count_opted[k].value) == 0))
		    { 
		    	alert("Customers to be visited should not less than No.of Customers to be moved towards SDR");
		    	war_cust_count[k].focus();
		 		return false;
			 }
	*/
//Assigning dynamic villages in to war_villages fields
		//	war_villages[k].value='';
		//	for(var j=0; j<len; j++)
		//	{
			//alert("going add="+textbox[j].value)
			//alert("Bfr add string="+war_villages[k].value);
			//			if((textbox[j].value).trim()!='')
				//		war_villages[k].value+=textbox[j].value+"::";
					//	else
						//break;
			//alert("after add string="+war_villages[k].value);
			}
			//alert("war_village="+war_villages[k].value);
			//war_villages[k].value = war_villages[k].value.substr(0,war_villages[k].value.length-2);
			//alert("war_village="+war_villages[k].value);

//	}//END OF THE FOR LOOP	
	
//Duplicate Village Entries validation starts here
 
 
//alert("End of the validationForm() function");
	
}
//validateForm();
//hey the update&save butons(submission) code starts here---------------------------------------------------------
$(document).ready(function(){//alert("hey inside the ready function of jquery update");
			
		$("#form1").submit(function(){sessioncheck();//alert("hey inside the submit function");
		   if(document.form1.onSubmit==validateForm()) 
			{  var allElements=$(this).serialize();//alert("hey the validation is done");
			this.timer = setTimeout(function () {//alert("hey inside the setTimeout functin of jquery");
			if(document.form1.save.style.display=='inline'){
			//alert("hey the program save is="+program);
			  var program='WorkPlanNew';	}
			else  
				
			   var program='UpdateWorkplan'; //alert("hey the program is="+program);	
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
//$('#war_sdrcust_count_opted').blur(function() { if($('#war_sdrcust_count_opted').val()!='') $('#war_sdrcust_count_opted').formatCurrency(); });
	 	//var counter = 2;
//---------------add function for inner dynamic filds 
	    //$(".addDynTextBox").addDynText(function () {
	    $(document).on('click', '.addDynTextBox', function(){

		var par=this.parentNode; 
	 	while(par.nodeName.toLowerCase()!='tr'){   par=par.parentNode;  } //alert("the index of the current row is"+par.rowIndex); 
 		var rowindex=par.rowIndex;
 		j=rowindex-1;

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
		($(btnObj).parent()).append('<input type="hidden" name="textbox'+rowindex+'" id="textbox'+rowindex+'" size="18" onkeyup="allowupdate();" maxlength="65" style="border-color:#0099FF;" onfocus="addSuggestion2(\'VCODE\',\'textbox'+rowindex+'\',BSFLUNIT_UCODE.value);">');
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
		 document.getElementById("emp_no").value ='';
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

 document.getElementById("cal").style.display='none';
 document.getElementById("cal2").style.display='none';
 document.form1.generate.disabled = true;//generate button should be enable when we click on new.
  
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
 document.getElementById("emp_no").value =star[x][1];
 document.getElementById("area_name").value =star[x][2];
 document.getElementById("huma_id").value =star[x][3];
 document.getElementById("place_of").value =star[x][4];

 count=c2[x1];
// alert("hey the row count in show function is="+count); 
 for(var i=1; i<count; i++)  $(".addRow").trigger('click'); 
 //alert("hey after dynamic table generation");
 
 var war_date = document.getElementsByName('war_date');
 var war_day = document.getElementsByName('war_day');
 var with_uhs = document.getElementsByName('visited');
 var war_sdrcust_count = document.getElementsByName('activities_planned');
 var activity=document.getElementsByName('act_id');
 //var ops_narration = document.getElementsByName('ops_narration');
// var war_sdrcust_count_opted = document.getElementsByName('war_sdrcust_count_opted');
 //var war_villages = document.getElementsByName('war_villages');
 //alert("hey the assignment to the dinemic fields is startted,c2[x1]="+c2[x1]);
 var k = x;

 for (var j = 0; j < c2[x1]; j++)
 {
	  activity[j].value=star[k][9];
	// alert("testing");
 //alert("hinsiede the for loop,and the  head="+star[k][2]+"--");
	 if(star[k][9]!="AC2-Field Visit"){
	     
	  
  if(k>x+c2[x1])
   break; //alert("the activitsl_id="+star[k][5]);
  war_date[j].value=star[k][5];
 // alert("the war_date_id="+star[k][5]);
  if(j==0)
	  document.getElementById("war_fdate").value=star[k][5];
  
  if(j==c2[x1]-1)
	  document.getElementById("war_tdate").value=star[k][5];
  war_day[j].value=star[k][6];
  // village1[j].value=star[k][7];
    with_uhs[j].style.visibility='hidden';
   //with_uhs[j].value=star[k][7];
  war_sdrcust_count[j].value=star[k][8];
  //activity[j].value=star[k][9];
	 } else{
		 if(k>x+c2[x1])
			   break; //alert("the activitsl_id="+star[k][5]);
			  war_date[j].value=star[k][5];
			 // alert("the war_date_id="+star[k][5]);
			  if(j==0)
				  document.getElementById("war_fdate").value=star[k][5];
			  
			  if(j==c2[x1]-1)
				  document.getElementById("war_tdate").value=star[k][5];
			  war_day[j].value=star[k][6];
			  // village1[j].value=star[k][7];
			    with_uhs[j].style.visibility='visible';
			   
			    with_uhs[j].value=star[k][7];
			  war_sdrcust_count[j].value=star[k][8];
	 }
  //war_sdrcust_count_opted[j].value=star[k][7];
  //alert("star[k][8]="+star[k][8])
  //var villages=star[k][8].split("::");
  //var textBoxLen = villages.length;
  //alert("textBoxLen="+textBoxLen);
 // var textboxAssign2 = document.getElementsByName('textbox'+(j+1));
  //delVillageField(j+1,textboxAssign2.length);//alert("deleted previous rows");
  //addVillageField(j+1,textBoxLen-1); //alert("added new village fields");
  //var textboxAssign = document.getElementsByName('textbox'+(j+1));
  //alert("before assigning the values to added villages field and villages="+villages);
  //for(var m=0; m<textBoxLen; m++)
  // {  
  //alert("First village value is :"+villages[m]);
  // textboxAssign[m].value = villages[m];
   //}
  k++;
 }

}// for (var j = 0; j < c2[x1]; j++)
 //alert("hey the assigning to the dinamic fields is completed");
//show()
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
//var textboxAssign = document.getElementsByName('textbox1');
 //delVillageField(1,textboxAssign.length);
 document.getElementById("cal").style.display='none';
 document.getElementById("cal2").style.display='none';

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
	// var textareas = f.getElementsByTagName("textarea"); //alert("hey you got the textareas[]"+textareas);
	// for(var j = 0; j < textareas.length; j++)
	 //{ 
	  //  if(textareas[j].style.display!="none")//new added one
	   // textareas[j].value='';//new added one 
	    //textareas[j].disabled=true;
	 // }
	 //var selects = f.getElementsByTagName("select"); //alert("hey you got the selects[]"+selects);
	 //for(var j = 0; j < selects.length; j++)
	 //{ 
	  //  if(selects[j].style.display!="none")//new added one
	   // selects[j].value='';//new added one
	   // selects[j].disabled=true;
	  //} //alert("hey going to read the deliverables of the dynamic table");
	 $("#tableID tr:gt(1)").remove();//removing all the rows exception first two rows in current table//newly added for dynamic table
	  document.form1.generate.disabled=true;//to disable the generate button
	// document.form1.remove.style.display='none';//newly added for dynamic table
	// document.form1.add.disabled=true;//newly added for dynamic table

	 //alert("hhh");
	 document.form1.BSFLUNIT_UCODE.disabled=false;//newly added code for the Query functionality
	 document.form1.huma_id.disabled=false;
	 document.form1.BSFLUNIT_UCODE.readOnly=false;
	 document.form1.huma_id.readOnly=false;
	 document.form1.huma_id.focus();
	 //below two lines code to remove the background color of the querying fields
	 document.form1.BSFLUNIT_UCODE.style.backgroundColor='skyblue';
	 document.form1.huma_id.style.backgroundColor='skyblue';
	// document.getElementById("cal1").style.display='inline';//to select the date this is needed
	 //addSuggestion('BSFLUNIT_UCODE2UHLOG_SERVICE','BSFLUNIT_UCODE');//To apply LOV as per huma entries made in ops
	 //addSuggestion('ops_desc','ops_desc');///for ops query with ops_desc
	// document.form1.BSFLUNIT_UCODE.focus();//To focus at field cont_id at the time of query
	 //alert("hiioiu");
	document.form1.BSFLUNIT_UCODE.VALUE="<%=uname_no%>";
 document.form1.emp_no.value="<%=huma_id%>"; //alert("assigned unit_code is="+"<%=uname_no%>");
 document.form1.area_name.value="<%=city%>";
 document.form1.place_of.value="<%=ss%>";
 document.form1.huma_id.value= "<%=name%>";
	
	//alert("common ciode"); 
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
  document.getElementById("cal").style.display='none';
 document.getElementById("cal2").style.display='none';

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
  document.form1.generate.disabled=true;//to disable the generate button
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


///-------------------------  15-11-2013 Coding Started here fro new logic of day wise rows-------
/*
function generateTable()
{//this funciton just used to get the row count of the table
	alert("in genearatetable() ");

 if(true)//document.form1.onSubmit==validateTop() 
  {
alert("hey inside the generateTable function and going to get the moth genereted dates");
 sessioncheck();
xmlHttp=GetXmlHttpObject();
 //alert("hey got the boject="+xmlHttp);
   if(xmlHttp==null)
  	  return;
    var url="getuserupdate";
	var huma_id=document.form1.huma_id.value;
	var weekoff_id=document.form1.weekoff_id.value;//document.getElementById("weekoff_id").value;
	//alert("hey just got the weekoff_id="+weekoff_id);
	var plan_fdate=document.form1.plan_fdate.value;
	var plan_tdate=document.form1.plan_tdate.value;
	//alert("hey just got the plan_tdate="+plan_tdate);
    url=url+"?decide=plan_table&key1="+plan_fdate+"&key2="+plan_tdate+"&huma_id="+huma_id+"&weekoff_id="+weekoff_id;//alert("hey the url="+url);
    xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
    xmlHttp.send(null);//alert("the ajax request made ");
	
	var showdata = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
	showdata=showdata.replace(/\/\/\/\/\/\/$/,"");//removes the "//////"  from the end of the "data" array
	star3=showdata.split("//////"); //alert("hey the star3 object length="+star3.length);
	//alert("hey the got object is="+star3);
	
	if(star3[0]=='')//for some all holidays selection,then it rerutns only '' as responce data object
	{ 
		alert("No records found");
		deleteRow('tableID');
	 }		
	else if(star3[0].substr(0,2) == 'NO')//if(star3=='')//if(star3.length<=3)//because here 1st 3 items of validation self empid,and existance of plan ,dates are in the selected weekoff or not
	{
	 alert(star3[0].substr(2,star3[0].length-2)); //alert("No records found"); 
	 deleteRow('tableID');
	 }
	else if(star3.length>=0)
	{//alert("hey inside the else if :this means the star is not empty");
	//  for(var k=0; k<star.length; k++)
	//  star[k] = star[k].split("::::::");
	var f = document.getElementById("form1");//going to make four fields of table as enable mode
	 for(var i = 0; i < f.length; i++)
	 {
	  if(f[i].type != "button")
	  {
	   f[i].disabled = false; 
	  }
	 }//end of making four fields of table as enable mode
	 document.form1.huma_id.readOnly=true;
	 document.form1.weekoff_id.readOnly=true;
	 document.form1.plan_fdate.readOnly=true;
	 document.form1.plan_tdate.readOnly=true;
	 document.getElementById("cal").style.display='none';
 	 document.getElementById("cal2").style.display='none';
	 goDim("form1","generate");
	 document.form1.generate.disabled=true;//generate button should be disable after generating the table of dates
	count=star3.length;
	addTable('tableID');
	//alert("hey the addTable functin called successfully and going to asign the dates to the fields");
	var plan_date = document.getElementsByName('plan_date');
	//alert("hey the star3 object length="+star3.length);
	//alert("hey the constructed rows length="+plan_date.length);
	 for(var i=0; i<star3.length; i++)
	  	     plan_date[i].value=star3[i]; 
	 
	 //alert("hey the for loop executed sucessfully");
	}//else if of the star3 object is not empty
  }// if(document.form1.onSubmit==validateTop()) 
 else { return false; }
}//generateTable()
*/
///-------------------------  15-11-2013 Coding End here fro new logic of day wise rows-------
function enable() 
{
 document.getElementById("cal").style.display='inline';
 document.getElementById("cal2").style.display='inline';
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
 document.form1.BSFLUNIT_UCODE.VALUE="<%=uname_no%>";
 document.form1.emp_no.value="<%=huma_id%>"; //alert("assigned unit_code is="+"<%=uname_no%>");
 document.form1.area_name.value="<%=city%>";
 document.form1.place_of.value="<%=ss%>";
 document.form1.huma_id.value= "<%=name%>";
  //document.form1.BSFLUNIT_UCODE.value='';
  //document.form1.unitname.value='';
 var MyDate = new Date();
 /*document.form1.war_date.value=('0' + MyDate.getDate()).slice(-2) + '-'
             + ('0' + (MyDate.getMonth()+1)).slice(-2) + '-'
             + MyDate.getFullYear();
*/
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
 document.form1.generate.disabled=false;//generate button should be enable when we click on new.
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
	
	<!--  <script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Wmenu.js"></script>-->
	<script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><!-- <script type="text/javascript" src="JS/Fmenu.js"></script> -->
	
	<!---------------- Code for FRS menu.js starts here  ------------------------->
										<!-- drop down code start here-->
										<table width="100%">
											<tr>
												<!-- style="background:url(images/menu08.jpg) repeat scroll 0 0 transparent "> -->
												<td width="14%" onMouseOver="showmenu('Masters')"
													onMouseOut="hidemenu('Masters')" class="bg_menu">
													<div align="center">
														<a href="Mhome.jsp" class="style21" id="aMasters">
															<u>M</u>asters</a><br />
													</div>
													<table class="menu" id="Masters" width="182" border="0"
														bordercolor="blue">
  <!--  <tr><td class="menu"><a href="activitycategory.jsp">Activity Category Master</a></td></tr> -->
   <tr><td class="menu"><a href="stateheadactivities.jsp">Activity Master</a></td></tr>
   <tr><td class="menu"><a href="MchangePwd.jsp">Change pwd</a></td></tr>

													</table>
												</td>
												<td width="14%" onMouseOver="showmenu('Operations')"
													onMouseOut="hidemenu('Operations')"  class="bg_menu">
													<div align="center">
														<a href="Mhome.jsp" class="style21" id="aOperations">
															Operatio<u>n</u>s
														</a><br />
													</div>
													<table class="menu" id="Operations" width="182" border="0"
														bordercolor="blue">
	<tr><td class="menu"><a href="workplannew.jsp">Work Plan</a></td></tr>
   
   <tr><td class="menu"><a href="newactual.jsp">Actual Work Done</a></td></tr>
   <!--  <tr><td class="menu"><a href="monthlyplan1.jsp">Work Plan1</a></td></tr>-->
	   <!-- <tr><td class="menu"><a href="lsrrecovery.jsp">LSR Field Entry</a></td></tr>
    <tr><td class="menu"><a href="DiffActivites.jsp">Customer Connect</a></td></tr> 
   <tr><td class="menu"><a href="usercreation.jsp">User Creation</a></td></tr>
   <tr><td class="menu"><a href="deleteuser.jsp">Freeze User</a></td></tr>
      <tr><td class="menu"><a href="SMStoODCustomer.jsp">SMS to OD Customers</a></td></tr>   
     <tr><td class="menu"><a href="excelupload.jsp">Upload Recovery Targets</a></td></tr>
     <tr><td class="menu"><a href="target pod.jsp">Upload Unit Actual PODs</a></td></tr>
     <tr><td class="menu"><a href="unittransfer.jsp">Unit Transfer</a></td></tr> -->
												</table>
												</td> 
											 <td width="17%" onMouseOver="showmenu('Reports')"
													onMouseOut="hidemenu('Reports')" class="bg_menu">
													<div align="center">
														<a href="Mhome.jsp" class="style21" id="aReports">
															<u>R</u>eports</a><br />
													</div>
													<table class="menu" id="Reports" width="220" border="0"
														bordercolor="blue">
														<tr>
															<td class="menu"><a href="ActualPlanRep.jsp">Actual Entry Report</a></td>
														</tr>
														<tr>
															<td class="menu"><a href="MonthlyPlanRep.jsp">Work Plan Report</a></td>
														</tr>
														<!-- <tr>
															<td class="menu"><a href="HrSummaryUnitRep.jsp">Hr Unit Summary Report</a></td>
														</tr>
														<tr>
															<td class="menu"><a href="HrSummaryRep.jsp">HR Summary Report</a></td>
														</tr> -->
													</table>
												</td> 
												<td width="14%" onMouseOver="showmenu('Logoff')"
													onMouseOut="hidemenu('Logoff')" class="bg_menu">
													<div align="center">
														<a href="Mhome.jsp" class="style21" id="aLogoff">
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
															<td width="41%"  class="bg_menu style21" align="right">Work Plan Module</td>
											</tr>
										</table> <!-- drop down code ended here-->
	
	<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if(role.equals("admin") || role.equals("audit") ||role.equals("areahead") || role.equals("unithead") || role.equals("user"))
{ %>
<form id="form1" name="form1" method="post">
<br />
<p align="right">
 <table width="1150" align="center">
  <tr>
  <td valign="top" width="1115" height="">   
  <fieldset style="background-color:">
	<legend class="formTitle"><b> STATE HEAD Monthly Work Plan</b> </legend>
     <table bgcolor="" width="100%" height="91%" border="0" align="center" bordercolor="#000000" >
    <tr> <td colspan="4" align="center">
      <div align="right">
        <script type="text/javascript" src="JS/np.js"></script>
        </div></td></tr>
    <tr>
      <td width="100%" height="" colspan="4" align="center">
      <table align="center">
     	<input type="hidden" name="BSFLUNIT_UCODE" id="BSFLUNIT_UCODE" VALUE=<%=uname_no %>></input>
      <tr>
      <td width="" height="" style=""><div align="right">Emp No</div></td>
      <!--  <td width="" colspan="2" align="left"><div align="left"><input name="BSFLUNIT_UCODE" id="BSFLUNIT_UCODE" type="text"  style="border-color:#0099FF; size="20" onfocus="addSuggestion('BSFLUNIT_UCODE','BSFLUNIT_UCODE');" onblur="showEmp('BSFLUNIT_NAME.area_name',BSFLUNIT_UCODE.value,'');"/><input type="hidden" name="UHLOG_CONTROLENO2" id="UHLOG_CONTROLENO2"></input></div></td> -->
      <td width="" colspan="2" align="left" style=""><div align="left"><input name="emp_no" id="emp_no" type="text" size="30" maxlength="50" style="border-color:#0099FF;" onfocus="addSuggestion('BSFLUNIT_UCODE_roles','BSFLUNIT_UCODE');" onblur="showEmp('BSFLUNIT_NAME.area_name',BSFLUNIT_UCODE.value,'');"/><input type="hidden" name="UHLOG_CONTROLENO2" id="UHLOG_CONTROLENO2"></input></div></td>
	</tr>
      <tr> <td width="" height=""><div align="right">Place Of Posting</div></td>
      <td width="" colspan="2" align="left" style=""><div align="left"><input name="area_name" id="area_name" type="text" size="30" maxlength="50" readonly="readonly"/></div></td>
     </tr>
      <tr>      <td width="" height="" align="right" style=""><div align="right">Name</div></td>
     <!--  <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" id="huma_id" style="border-color:#0099FF; width: 264px" onkeyup="allowupdate();" maxlength="57" onfocus="addSuggestion2('huma_id','huma_id',BSFLUNIT_UCODE.value);"></div></td>  -->
      <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" size="40" id="huma_id" style="border-color:#0099FF;" onkeyup="allowupdate();"  maxlength="57" onfocus="addSuggestion2('lsrcode','huma_id',BSFLUNIT_UCODE.value);"></div></td>
      </tr>
      <tr>      <td width="" height="" align="right" style=""><div align="right">Designation</div></td>
     <!--  <td width="" colspan="2" align="left"><div align="left"><input name="huma_id" type="text" id="huma_id" style="border-color:#0099FF; width: 264px" onkeyup="allowupdate();" maxlength="57" onfocus="addSuggestion2('huma_id','huma_id',BSFLUNIT_UCODE.value);"></div></td>  -->
      <td width="" colspan="2" align="left"><div align="left"><input name="place_of" type="text" size="40" id="place_of" style="border-color:#0099FF;" onkeyup="allowupdate();"  maxlength="57" onfocus="addSuggestion2('lsrcode','huma_id',BSFLUNIT_UCODE.value);"></div></td>
      </tr>
      <tr>
      <td width=""align="right"><div align="right">From Date</div></td><td width="" align="left" style="width: 281px; ">
      <table width="" border="0" cellspacing="0" cellpadding="0" align="left">
      		<tr><td width="" colspan="2" align="left" style="width: 134px; "><input type="text" name="war_fdate" id="war_fdate" size="10" maxlength="10" onkeyup="allowupdate();" />
           		<a href="#"  onclick="setYears(1947, 2050);
       showCalender(this, 'war_fdate');"> <img id="cal"  src="images/calender.png" onclick="allowupdate();"/></a></div></td>
           		<td width="" colspan="2" style="width: 130px; ">(dd-mm-yyyy)<br /></td>

	</tr>
      </table>
      
    </td>
      <td width="" align="right"><div align="right">To Date</div></td>
      <td width="" align="left">
      <table width="" border="0" cellspacing="0" cellpadding="0" align="left">
      		<tr><td width="130px" align="left"><input type="text" name="war_tdate" id="war_tdate" size="10" maxlength="10" onkeyup="allowupdate();" />
           		<a href="#"  onclick="setYears(1947, 2050);
       showCalender(this, 'war_tdate');"> <img id="cal2"  src="images/calender.png" onclick="allowupdate();"/></a></div></td>
           		<td width="" colspan="2" style="width: 132px; ">(dd-mm-yyyy)<br /></td>
	</tr>
      </table>
      
    </td>
    </tr>
    <tr><td colspan="2">
              <div align="center">
                <input type="button" id="generate" name="generate"
	value="Generate Days" onclick="generateTable('war_period');" title=""
	class="groovybutton" onmouseover="goLite(this.form.name,this.name)"
	onmouseout="goDim(this.form.name,this.name)" />
              </div></td></tr></table>
    </td></tr>
    <!--<tr>
      <td align="right" class="style22"><div align="right">Country id</div></td>
      <td align="left"><div align="left">
          <input name="country_id" id="country_id"  type="text" size="60" onkeyup="allowupdate();" onfocus="addSuggestion('country_id','country_id');" maxlength="60" style="border-color:#0099FF;"/>
      </div></td>
    </tr>-->

    <tr>
            <td colspan="4" height="100%" valign="top" align="center"><div align="left"><b> <!--WAR Room Data  Entry--></b></div>
              <table id="tableID" width="100%" style="border:1px solid #708090;;width: 100%" ><!--  -->
                  <tr style="background-image:url(images/TableHeadBg.gif)">
                  <!-- <tr bgcolor="back03.gif" style="opacity: 0.65; filter: alpha(opacity=65);"> -->
                    <th width="1%" scope="col" class="style21" bgcolor="#E6E6FA" style="height: 16px;"><div align="center">Sno</div></th>
                    <th width="10%" scope="col" class="style21" bgcolor="#E6E6FA" style="height: 16px;"><div align="center">Date </div></th>
                    <th width="10%" scope="col" class="style21" bgcolor="#E6E6FA" style="height: 16px;"><div align="center">Day </div></th>
                    <th width="10%" scope="col" class="style21" bgcolor="#E6E6FA" style="height: 16px;"><div align="center">Category </div></th>
					<th width="30%" scope="col" class="style21" bgcolor="#E6E6FA" style="height: 16px; width: 420px" style="width: 303px; "><div align="center">Name Of Unit To Be Visited</div></th>
					<th width="30%" scope="col" class="style21" bgcolor="#E6E6FA" style="height: 16px; width: 420px" style="width: 303px; "><div align="center">Activities Planned</div></th>
					<!-- <th width="30%" scope="col" class="style21" bgcolor="#E6E6FA" style="height: 16px; width: 420px" style="width: 303px; "><div align="center">Name Of The Activities Planned</div></th> -->
                    
 					
 					<th width="1%" bgcolor="#E6E6FA" style="height: 16px;" class="lastCol">
					  <div align="center">
					    <input type="button" id="add" name="add" value="+" class="addRow" onmouseover="goLite(this.form.name,this.name)" onmouseout="goDim(this.form.name,this.name)" onclick=""/>
			      </div></th>
			      
			      </tr>
                  <tr>
                    <td style="" valign="top">
                      <div align="center">
                        <!--Business Development
                      <input type="hidden" name="head_bd" id="head_bd" value="Business Development"/>-->
                        <input type="text" name="ops_sno" id="ops_sno" maxlength="2" size="1" value="1" class="rowNumber" readonly="readonly"/>
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
--%>               <td style="" valign="top"><div align="center"><input name="war_date" type="text" id="war_date" onkeyup="allowupdate();" maxlength="10" size="10" readonly="readonly"/>
<%-- <select name="war_date">
<option value="-- Please Select LSR --">-- Please Select LSR --</option>
<%
String lsrsql="select HUMA_FNAME||' '||HUMA_LNAME||'-'||huma_id as huma_id  from huma_mstr WHERE huma_id not in (select distinct huma_id from frs_user where user_freeze='Y' and huma_id is not null) and bsflunit_ucode='"+ucode+"' order by huma_id";
Connection lsrcon=new JdbcConnect().getConnection();
Statement lsrst=svcon.createStatement();
ResultSet lsrrs=svst.executeQuery(lsrsql);
while(lsrrs.next()){
System.out.println("lsr name was :"+lsrrs.getString("huma_id"));
 %>
<option value="<%=lsrrs.getString("HUMA_ID")%>"><%=lsrrs.getString("HUMA_ID")%></option>
<%}%>
</select>
 --%></div></td>
<td style="" valign="top"><div align="center"><input name="war_day" type="text" id="war_day" onkeyup="allowupdate();" maxlength="10" size="10" readonly="readonly"/></td><!--  					<td><div align="center"><input name="activities_planned" type="text" id="activities_planned"  style="border-color:#0099FF; width:95%" onkeyup="allowupdate();" onfocus="addSuggestion('activities_planned','activities_planned');"maxlength="57"  /></div></td>
 --> 					<!-- <td><div align="center"><input name="ACTIVITY_ID" type="text" id="ACTIVITY_ID"  style="border-color:#0099FF; width:95%" onkeyup="allowupdate();" maxlength="57" onfocus="showEmpn2('activities_planned.ACTIVITY_ID',this,this.value);"/></div></td> -->
					
					<td style="" valign="top"><div align="center">
 				
				<select name="act_id" id="act_id"  size="0" 
												onChange="allowupdate(); showEmps2('act_name',this,this.value);">
				<option value="">Select</option>
				<%
				//Connection con = null;
				Statement stmt = null;
				ResultSet rs = null;
 try{
 JdbcConnect jc=new JdbcConnect();  
 //System.out.println("JdbcConnect jc="+jc);
 con = jc.getConnection();  
 stmt = con.createStatement();  
 rs = stmt.executeQuery("select act_id,act_name,activity_category from statehead_activities");  
   while(rs.next()){
   //System.out.println("Option value= "+rs.getString(1));
   %>
   <option value="<%=rs.getString(3)%>-<%=rs.getString(2)%>"><%=rs.getString(2)%></option>
   
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
 					 <td style="" valign="top"><div align="center"><input name="visited" type="text" id="visited" onkeyup="allowupdate();" style="border-color:#0099FF; width:95%" maxlength="57" onkeyup="allowupdate();" onfocus="addSuggestion('BSFLUNIT_UCODE_roles','visited');" /></div></td>
 					 <td style="" valign="top"><div align="center"><input name="activities_planned" type="text" id="activities_planned" onkeyup="allowupdate();" style=":#0099FF; width:95%" maxlength="57" onkeyup="allowupdate();"/></div>
 					 <!-- <input type="hidden" name="ACTIVITY_ID" id="ACTIVITY_ID" /> -->
 					 </td>
 					 <td style="width: 395px; " align="left" valign="top">
 									<input type="hidden" name="ACTIVITY_ID" id="ACTIVITY_ID" />
 								<!-- 	<div id='TextBoxesGroup' align="left" > -->
									<input type="hidden" value="+" id="addButton" name="addButton" class="addDynTextBox">
									<input type="hidden" value="-" id="removeButton" name="removeButton" class="delDynTextBox">
									<!-- <input type="text" name="textbox1" id="textbox1" size="18" style="border-color:#0099FF;" onkeyup="allowupdate();" maxlength="65" onfocus="showEmpn2('ACTCAT_ID.ACTIVITY_ID',this,this.value);"> -->
									<!-- </div>	 -->
					</td>
					
	<!-- <td valign="top"><div align="center"></div></td> -->
 				 <td class="lastCol"> <div align="center">
					  <input type="button" id="remove" name="remove" value="-" class="delRow" onclick="allowupdate();" onmouseover="goLite2(this)" onmouseout="goDim2(this)"/>
					</div></td> 
                  </tr>
</table></td>
          </tr>
    <tr >
      <td colspan="4" align="center"  height="60">
        <div align="center">
          <table>
            <tr>
              <td ><script type="text/javascript" src="JS/workplanbutton.js"></script>
                <br />
                <table id="msg2" style="visibility:hidden; position:absolute;" align="">
                  <tr>
                    <td >Execute</td>
                        </tr>
                  </table></td>
                  <td   width="" align="left" >&nbsp;&nbsp;
                    <input type="button" id="exct" name="exct" class="groovybutton" value="&euro;"    title="" onmouseover="goLite(this.form.name,this.name); showmenu('msg');"
   						onmouseout="goDim(this.form.name,this.name); hidemenu('msg');" onclick="showEmpn('plan_work',BSFLUNIT_UCODE.value,emp_no.value,war_date.value);"/>
                    
                    <br />
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