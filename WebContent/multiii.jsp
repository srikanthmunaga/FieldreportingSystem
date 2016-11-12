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
 alert("the index of the current row is"+par.rowIndex); 
 var i=par.rowIndex;
 j=i-1;
 alert("J = "+j);

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
		alert("the value from getuser update is "+value);	
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
		
		//document.form1.closing_stock.value=document.form1.unit_id.value;
		
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
	   
   }
       var patt1=new RegExp("[A-Za-z]");
	   var patt2=new RegExp("^[A-Za-z]+[0-9]*[ A-Za-z0-9]*$");

	  //var huma_id=new RegExp("^[0-9]{4}$");
	  
    
    var st_id = document.getElementsByName('st_id');
	var closing_stock = document.getElementsByName('closing_stock');
    var new_stock = document.getElementsByName('new_stock');
	
//	var UHLOG_ODCUST = document.getElementsByName('UHLOG_ODCUST');//var ops_narration = document.getElementsByName('ops_narration');
//	var UHLOG_ODAMT = document.getElementsByName('UHLOG_ODAMT');
	var n= st_id.length;
    for (var k = 0; k < n; k++)
    {
//village c

//alert("MSR DEBUG VCODE");
  if(st_id[k].value=="-1")  
   {
	 alert("Please Select st_id");
	 st_id[k].focus();
 	 return false;
	}

    if(st_id[k].value=="1")  
   {
	if(closing_stock[k].value=='')  
   {
	 alert("Please enter the Current Unit");
	 closing_stock[k].focus();
 	 return false;
	}
	
	if(new_stock[k].value=='')  
   {
	 alert("Please enter the Transferred Unit");
	 new_stock[k].focus();
 	 return false;
	}
   }	
	
	}  //For loop
    //Validation Completed for st_id Related Fields
}//validateForm()

function focuses() { document.form1.huma_fname.focus(); }
//function focuses1() { document.form1.huma_address.focus();}
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
		 //document.getElementById("huma_id").value ='';
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
    // document.getElementById("huma_id").readOnly =true;
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
 //document.getElementById("comp_id").value =star[x][0];
 //document.getElementById("area_id").value =star[x][1];
 //document.getElementById("huma_id").value =star[x][2];
 //document.getElementById("huma_fname").value =star[x][3];
 //document.getElementById("huma_lname").value =star[x][4];
 //document.getElementById("huma_freeze").value =star[x][5]; 
 //document.getElementById("huma_designation").value =star[x][6]; 
 //document.getElementById("huma_reporting").value =star[x][7];
 //document.getElementById("city_id").value =star[x][8];//place of posting
 //document.getElementById("huma_dob").value =star[x][9];
// document.getElementById("huma_doj").value =star[x][10];
 //document.getElementById("huma_address").value =star[x][11];
// document.getElementById("huma_pin").value =star[x][12];
 //document.getElementById("huma_email").value =star[x][13];
 //document.getElementById("huma_phone").value =star[x][14];
 //document.getElementById("huma_mobile").value =star[x][15];
 //document.getElementById("HR_st_id").value =star[x][16];
 document.getElementById("unit_id").value =star[x][0];
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
 var st_id = document.getElementsByName('st_id');
 var sta_name = document.getElementsByName('sta_name');
 var closing_stock = document.getElementsByName('closing_stock');
 var new_stock = document.getElementsByName('new_stock');
 //var Description = document.getElementsByName('Description');
 var seqid = document.getElementsByName('seqid');
 
 for(var k = 0; k < rows; k++)
 { 
 	//alert("hey inside the for loop and assigning values to dynamic fields gor "+k+" time");
   //sno[k].value =star[x][i]; i++;
  
   i++;
   st_id[k].value ="";
   //document.getElementById("FRS_st_id").value = "";
   /* if(st_id[k].value==1)
   {
   new_stock[k].readOnly = false;
   closing_stock[k].readOnly = false;
   //new_stock[k].disabled = false;
   //closing_stock[k].disabled = false;;
   }
   else
   {
   new_stock[k].readOnly = true;
   closing_stock[k].readOnly = true;
   //new_stock[k].disabled = true;
   //closing_stock[k].disabled = true;
   } */
   i++;
   
   sta_name[k].value ="";
   //alert("The date is "+star[x][i]);
    i++;
   closing_stock[k].value = "";//Crosscheck no increment is happening here------------------
   
   //Description[k].value =""; i++;
   seqid[k].value =""; i++;
   closing_stock[k].value =""; i++;
   new_stock[k].value =""; i++;
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
 var st_id = document.getElementsByName('st_id');
 var sta_name = document.getElementsByName('sta_name');
 var closing_stock = document.getElementsByName('closing_stock');
 var new_stock = document.getElementsByName('new_stock');
// var Description = document.getElementsByName('Description');
 var seqid = document.getElementsByName('seqid');
 
 for(var k = 0; k < rows; k++)
 { 
 	//alert("hey inside the for loop and assigning values to dynamic fields gor "+k+" time");
   //sno[k].value =star[x][i]; i++;
  
   i++;
   st_id[k].value =star[x][i];
   //document.getElementById("FRS_st_id").value = star[x][i];
   if(st_id[k].value==1)
   {
   new_stock[k].readOnly = false;
   closing_stock[k].readOnly = false;
   //new_stock[k].disabled = false;
   //closing_stock[k].disabled = false;;
   }
   else
   {
   new_stock[k].readOnly = true;
   closing_stock[k].readOnly = true;
   //new_stock[k].disabled = true;
   //closing_stock[k].disabled = true;
   }
   i++;
   
   sta_name[k].value =star[x][i];
   //alert("The date is "+star[x][i]);
    i++;
   closing_stock[k].value = star[x][17];//Crosscheck no increment is happening here------------------
   
   //Description[k].value =star[x][i]; i++;
   seqid[k].value =star[x][i]; i++;
   closing_stock[k].value =star[x][i]; i++;
   new_stock[k].value =star[x][i]; i++;
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
 
 
 document.form1.unit_id.disabled = false;
 document.form1.unit_id.style.backgroundColor = 'skyblue';
 
		
		document.form1.unit_id.disabled = false;
		document.form1.unit_id.readOnly = false;
		document.form1.unit_id.style.backgroundColor = 'skyblue';
		document.form1.unit_id.focus();
		
		document.form1.area_id.disabled = false;
		document.form1.area_id.readOnly = false;
		document.form1.area_id.style.backgroundColor = 'skyblue';
		document.form1.area_id.focus();
		
		
		
		
		document.form1.huma_reporting.disabled = false;
		document.form1.huma_reporting.readOnly = false;
		document.form1.huma_reporting.style.backgroundColor = 'skyblue';
		
		
		
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
 					
 					
 					<td><div align="center"><input name="st_name" type="text" id="st_name"  width:95%" onkeyup="allowupdate();"/></div></td>
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
												onClick="showEmpn('huma_mstr',huma_id.value,unit_id.value,area_id.value,huma_designation.value,huma_reporting.value,HR_st_id.value,FRS_st_id.value);"
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
