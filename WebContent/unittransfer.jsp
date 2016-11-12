<%@taglib uri="http://java.sun.com/jstl/sql_rt" prefix="sql_rt"%><%@taglib
	uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" http-equiv="refresh" content="5">

<title>Modify Recovery Entry</title>
<% 
						String ur=(String)request.getSession().getAttribute("userrole");
						if (request.getSession().getAttribute("username") == null ) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
						/* else if (request.getSession().getAttribute("username") != null && ur.equals("user")) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						} */
						else
						{
						%>

<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>
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

<style type="text/css">  
  table.tab td a {  
   display: block;  
   border: 1px solid #aaa;  
   text-decoration: none;  
   background-color: #fafafa;  
   color: #123456;  
   margin: 2px;  
   clear:both;  
  }  
  div1 {  
   float:left;  
   text-align: center;  
   margin: 10px;  
  }  
  select {  
   width: 100px;  
   height: 80px;  
  }  
 </style>  

<script type="text/javascript">

function validate()
{
if(document.getElementsByName("huma_id")[0].value=="")
{
  alert("Please enter the Emp Id");
  document.getElementsByName("huma_id")[0].focus();
  document.getElementsByName("huma_id")[0].style.background="#fffacd";
  return false;
}
//Common code for dates validation
var date=new RegExp("[0-9]");
var currentTime = new Date();
   var mm = currentTime.getMonth() + 1;
   var dd = currentTime.getDate();
   var yyyy = currentTime.getFullYear();
   var sysdate=dd+"-"+mm+"-"+yyyy;
   sysdate=sysdate.split("-");
   var sysdate = new Date(sysdate[2], sysdate[1]-1, sysdate[0]); //var date1 = new Date(yr1, mon1, dt1); 
//FRS_date validation starts here
if(!date.test(document.form1.FRS_date.value))
{
	  alert("Please select the FRS date");
	  document.form1.FRS_date.focus();
	  return false;
	}
		if(document.form1.FRS_date.value.replace(/^\s+/,'').replace(/\s+$/,'')!="")
	{
	var vnvdate=(document.form1.FRS_date.value).split("-");
	var validformat = /^\d{2}-\d{2}-\d{4}$/;
	var returnval=false;//validformat
	if(!validformat.test(document.form1.FRS_date.value))
	{
	alert("Please enter the FRS date correct format");
	document.form1.FRS_date.focus();
	return false;
	}//if date format checking
	var dayfield=vnvdate[0];
	var monthfield=vnvdate[1];
	var yearfield=vnvdate[2];
	var dayobj = new Date(yearfield, monthfield-1, dayfield)
	if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield))
	 {
		alert("Invalid month or date found in FRS date");
		document.form1.FRS_date.focus();
		return false;
	  }
	//Date comparision code starts here
	   var FRS_date=(document.form1.FRS_date.value).split("-");
    var FRS_date = new Date(FRS_date[2], FRS_date[1]-1, FRS_date[0]); //alert("hey to FRS_date date is ="+FRS_date);
	   if(FRS_date > sysdate)
  	{
		 alert("FRS date should not be grater than the System date");
		 document.form1.FRS_date.focus();
		 return false; 
	     }
	 //Date comparision code ends here
	
 }//if(FRS_date!="") 
//FRS date validation code ends here
}
</script>
<script type="text/javascript">  

  $().ready(function() { 
	  //alert("MSR DEBUG");
	  
		$("#form1").submit(function(){
			sessioncheck();//alert("hey inside the submit function");
		   if(document.form1.onSubmit==validate()) 
			{var allElements=$(this).serialize();//alert("hey the validation is done");
			this.timer = setTimeout(function ()
			{//alert("hey inside the setTimeout functin of jquery");
			  var program='unittransfer';
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
							location.reload();
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
 	  
 	  $('#PageRefresh').click(function() {
 
    	      location.reload();
 
	});
 	  
   $('#next').click(function() {  
	    var foo = []; 
	    $('#select1 :selected').each(function(i, selected){ 
	    	//alert("ADDING HIDDEN FIELDS :"+$(selected).text());
	    	$('#form1').append('<input type="hidden" name="unitnames" value="'+$(selected).text()+'" />');
	    	foo[i] = $(selected).text(); 
	    });
    return !$('#select1 option:selected').remove().appendTo('#select2');
    
   });
   
  
   $('#previous').click(function() {
	//  alert("MSR DEBUG 3");
    return $('#select2 option:selected').removeAttr('selected').appendTo('#select1');
      
   }); 
  });   
  
   function showEmp(decide,key1,key2)
  {
	//alert("hety inside the showEmp="+decide);
	//alert("hety inside the showEmp="+key1);
  // if(!(document.form1.exct.disabled))
  	//return;//stop executing this function in case of query functioning
  //if(patt.test(document.getElementById("unit_id").value))
   //{
   
  // sessioncheck();
  xmlHttp=GetXmlHttpObject();

   if (xmlHttp==null)
    {
	  // alert("hey url is made and it is null");
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
  		// alert("Please enter the existing Unit ID");
/*   		 document.getElementById("unit_id").value ='';
  		 document.getElementById("area_id").value ='';
  		 document.getElementById("comp_id").value =" ";
  		 document.form1.unit_id.focus();
 */  	 }
  	 else if(strar.length>0)
  	 {
  		 //alert("List was came="+strar.length);
		for(var i=0;i<strar.length;i++)
			{
  		//document.getElementById("select1").value =strar[0];
		//alert("strar[0]="+strar[i]);
		$('#select1')
		   .append ( $('<option/>')
		      .attr('value', strar[i])
		      .html(strar[i])
		   );
			}
  	 
		//document.form1.select1.value=strar[0];
  		//document.form1.area_id.value=strar[0];
  		//document.form1.comp_id.value=strar[1];
  		//document.form1.currentunit.value=document.form1.unit_id.value;
  		//alert("List was came"+strar.length);
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
  function validate()
  {
  if(document.form1.BSFLUNIT_UCODE.value=='')
  {
   alert("Please enter the Source AREA correctly");
   document.form1.BSFLUNIT_UCODE.focus();
   return false;
  }
  if(document.form1.areaname2.value=='')
  {//alert("hey the save is inline and the BSFLUNIT_UCODE is=''");
   alert("Please enter the Destination AREA correctly");
   document.form1.areaname2.focus();
   return false;
  }
  
   var tr_units = document.getElementsByName('unitnames');
  	var n=0;
  	n=tr_units.length;
	if(n==0)
	{
	   alert("please select at least one UNIT to TRANSFER OR CANCEL");
	   return false;
	}
  }
  
  function reset()
  {
  $('#list option').each(function(index, option) {
    $(option).remove();
});
  }
 </script>  
   
 <link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>
<!-- <script language="javascript" type="text/javascript"></script> -->
<script type="text/javascript" src="JS/jquery.table.addrow.js"></script> 
 

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script>
<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if(role.equals("admin") || role.equals("audit") ||role.equals("areahead") || role.equals("unithead"))
{ %>
<form id="form1" name="form1"  method="post"><!-- action="unittransfer" onSubmit="return validate();"-->
						<table cellpadding="1" cellspacing="1" background="blue">
							<!-- <tr>

								<td width="380"></td>
								<td width="380" align="left"><b><a
										href="Fhome.jsp">Home</a></b></td>
							</tr> -->
						</table>

						<center>
							<table>
								<tr>
					
					<td><b>Source Area</b> </td>
									<td>
									<input name="BSFLUNIT_UCODE" id="BSFLUNIT_UCODE" type="text" size="" maxlength="50" style="border-color:#0099FF;" onfocus="addSuggestion('AREA','BSFLUNIT_UCODE');" onblur="showEmp('Unitname.code',BSFLUNIT_UCODE.value,'');"/>

									</td>
									
									<td><b>Destination  Area</b></td>
									<td>
									 <input type="text" name="areaname2" onFocus="addSuggestion('AREA','areaname2');" style="border-color:#0099FF;"> 
									</td>
									
									<tr>
									<td>
									
									</td>
									<td><select multiple id="select1" name="select1" size="" style="height: 120px; width: 150px; ">
								
									
									</select>  </td>
									<td>
									<!-- <a href="#" id="add" style="width: 29px; "><b> &gt;&gt;</b></a><br><br><a href="#" id="remove" style="width: 30px; "><b>&lt;&lt;</b></a> -->
									<center>
									 <input type="button" id="next" name="next" class="groovybutton1" value="&gt;&gt;" title="" onmouseover="goLite(this.form.name,this.name)"   onmouseout="goDim(this.form.name,this.name)" onclick="nextprevious(this.name);"/>
									 <input type="button" id="previous" name="previous" class="groovybutton1" value="&lt;&lt;" title="" onMouseOver="goLite(this.form.name,this.name)"   onMouseOut="goDim(this.form.name,this.name)" onclick="nextprevious(this.name);"/>  &nbsp;&nbsp;&nbsp;&nbsp;
									</center>
									
									</td>
									
									<td><select multiple id="select2" style="height: 120px; width: 150px; "></select></td>
									</tr>
								

<tr>
<td></td>
<td align="right"><input type="button" id="cancel" name="cancel" class="groovybutton" value="Cancel" title="" onmouseover="goLite(this.form.name,this.name)"   onmouseout="goDim(this.form.name,this.name)" onclick="window.location='Ahome.jsp'"/></td>
<td align="center"><input type="button" id="PageRefresh" name="PageRefresh" class="groovybutton" value="clear" title="" onmouseover="goLite(this.form.name,this.name)"   onmouseout="goDim(this.form.name,this.name)"/></td>

<td align="left"><input type="submit" value="Transfer the units" class="groovybutton"  title="" onmouseover="goLite(this.form.name,this.name)"   onmouseout="goDim(this.form.name,this.name)"/></td>
</tr>
</table>
</center>

	</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script></body>
<%
}//authorised acess else------------------------------------------------------------------------------------
else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to Modify the Recovery Details
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