<html>  
<head>  
   
 <script type="text/javascript" src="JS/jquery.min.js"></script>
 <script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>
 
<!-- <script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>
 --> 
 <script type="text/javascript">  

  $().ready(function() { 
	  alert("MSR DEBUG");
   $('#add').click(function() {  
	    var foo = []; 
	    $('#select1 :selected').each(function(i, selected){ 
	    	alert("MSR DEBUG 23"+$(selected).text());
	    	$('#myform').append('<input type="hidden" name="unitnames" value="'+$(selected).text()+'" />');
	    	foo[i] = $(selected).text(); 
	    });
    return !$('#select1 option:selected').remove().appendTo('#select2');
    
   });
   
  
   $('#remove').click(function() {
	   alert("MSR DEBUG 3");
    return !$('#select2 option:selected').remove().appendTo('#select1');  
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
  		 alert("Please enter the existing Unit ID");
/*   		 document.getElementById("unit_id").value ='';
  		 document.getElementById("area_id").value ='';
  		 document.getElementById("comp_id").value =" ";
  		 document.form1.unit_id.focus();
 */  	 }
  	 else if(strar.length>0)
  	 {
  		 alert("List was came="+strar.length);
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
 	  alert("MSR DEBUG bbb");

  }
 </script>  
   
 <style type="text/css">  
  a {  
   display: block;  
   border: 1px solid #aaa;  
   text-decoration: none;  
   background-color: #fafafa;  
   color: #123456;  
   margin: 2px;  
   clear:both;  
  }  
  div {  
   float:left;  
   text-align: center;  
   margin: 10px;  
  }  
  select {  
   width: 100px;  
   height: 80px;  
  }  
 </style>  
   
</head>  
  
<body>  
<form action="test1" name="myform" id="myform" method="post" onSubmit="return validate();">

<div>
<input type="text" name="areaname1" id="areaname1" onFocus="addSuggestion('AREA','areaname1');" onblur="showEmp('Unitname.code',areaname1.value,'');">
<input name="BSFLUNIT_UCODE" id="BSFLUNIT_UCODE" type="text" size="30" maxlength="50" style="border-color:#0099FF;" onfocus="addSuggestion('AREA','BSFLUNIT_UCODE');" onblur="showEmp('Unitname.code',BSFLUNIT_UCODE.value,'');"/> 
</div>
<div>
<input type="text" name="areaname1" onFocus="addSuggestion('AREA','areaname1');"> 
</div>

 <div>  
  <select multiple id="select1" name="select1">  
  </select>  
  <a href="#" id="add">add &gt;&gt;</a>  
 </div>  
 <div>  
  <select multiple id="select2"></select>  
  <a href="#" id="remove">&lt;&lt; remove</a>  
 </div>  
 <input type="submit" value="Go To Servlet" >
 </form>
 
</body>

  
</html>  