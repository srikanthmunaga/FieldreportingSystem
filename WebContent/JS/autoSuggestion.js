
//1.put below links in header tag
/*<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="JS/jquery.min.js"></script>
  <script src="JS/jquery-ui.min.js"></script>*/
//2.put in the lable tag= onfocus="addSuggestion('city_id','city_id');"
/*3.below three lines of code to get just ID from LOV, as a parameter
		String huma_id1=request.getParameter("huma_id");
		int i = huma_id1.lastIndexOf('-');  
		String huma_id=huma_id1.substring(i+1);System.out.print("huma_id="+huma_id);*/
/*4.implement below two lines code in getuserList class 
 //for distinct BSFLUNIT_UCODE list
	if(parameter.equals("BSFLUNIT_UCODE"))//for BSFLUNIT_UCODE list 
	ps = con.prepareStatement("select distinct BSFLUNIT_NAME||'-'||BSFLUNIT_UCODE from huma_mstr order by BSFLUNIT_UCODE");*/

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
 try { xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");}
 catch (e) {xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");}
 }
return xmlHttp;
}//getXmlObject()
//jqhery autosuggestion.js codestart here---------------------------------------------------------------------------------------------
var suggestionText;
function showList(field,input1)
{ 
	//alert("hey inside the showList() and field ="+field+"and input is "+input1);
 
 xmlHttp=GetXmlHttpObject();
 
 if(xmlHttp==null)
  { //alert("hey the mxlhttp obj is null="+xmlHttp);
  	return;
  } 
  //alert("hey got the boject="+xmlHttp);
 var url="getuserList";
  url=url+"?parameter="+field+"&input1="+input1;
//alert("url is in auto suggestion is "+url+"field is"+field);
  //alert(url);
 //xmlHttp.onreadystatechange=stateChangedList;
 xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
 xmlHttp.send(null);//alert("the ajax request made ");
 //}//showList()
 
//function stateChangedList() 
//{
  //if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
  //{ //alert("hey inside the  statechangedn");
	var showdata = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,''); //removes the starting & ending spaces spaces
	//alert("hey goy the show data is:"+showdata);
	showdata=showdata.replace(/::::::$/,"");//removes the "::::::"  from the end of the "data" array
	suggestionText=showdata.split("::::::");
	
	//alert("hey goy the text one is:"+suggestionText);
  // }
//}//stateChangedn() 
}//showList()
//showList('busi_id1');
 function addSuggestion(field,fieldId)
 {//alert("hey inside the addSuggestion method");//alert("hey the arrat2 is;"+suggestionText);
// var f2=document.getElementByName(field);
 //sessioncheck();
 showList(field,''); //alert("hey the list of items got successfully and fieldId="+fieldId);
  $(document).ready(function() { //alert("hety inside the ready functiok");
    $(document.getElementsByName(fieldId)).autocomplete({ 
    source: suggestionText,
	minLength: 3,
	 extraParams: {
	       // geonames doesn't support q and limit, which are the autocomplete plugin defaults, so let's blank them out.
	       maxRows: 50
	     },
	max: 5
}).dblclick(function(){
	if ($(this).autocomplete("widget").is(":visible"))
       return;
	$(this).autocomplete( "option", "minLength", 0 );
    $(this).data("autocomplete").search($(this).val());
});
  });// alert("hey end of the addSuggestion");
 }//addSuggestion()
 function addSuggestion2(field,fieldId,input1)
 {//alert("hey inside the addSuggestion method");//alert("hey the arrat2 is;"+suggestionText);
	 //var f2=document.getElementByName(field);
    // alert(f2); 
	 //alert(f2.value); 
	 //sessioncheck();
 showList(field,input1); 
 //alert("hey the list of items got successfully and fieldId="+fieldId);
  $(document).ready(function() { //alert("hety inside the ready functiok");
    $(document.getElementsByName(fieldId)).autocomplete({ 
    source: suggestionText,
	minLength: 3,
	 extraParams: {
	       // geonames doesn't support q and limit, which are the autocomplete plugin defaults, so let's blank them out.
	       maxRows: 50
	     },
	max: 5
}).dblclick(function(){
	if ($(this).autocomplete("widget").is(":visible"))
       return;
	$(this).autocomplete( "option", "minLength", 0 );
    $(this).data("autocomplete").search($(this).val());
});
  });// alert("hey end of the addSuggestion");
 }//addSuggestion2()

 //jqhery autosuggestion.js codeends here---------------------------------------------------------------------------------------------

//starting session checking code when click the ajax requests made-----------------------------------------------------------------
function sessioncheck()
{
   
  xmlHttp=GetXmlHttpObject();
 //alert("hey got the boject="+xmlHttp);
   if(xmlHttp==null)
  	  return;
    var url="sessioncheck";
    xmlHttp.open("GET",url,false);//xmlHttp.open("GET",url,true);
    xmlHttp.send(null);//alert("the ajax request made ");
	var msg = xmlHttp.responseText.replace(/^\s+/,'').replace(/\s+$/,'');//alert("hey goy the show data is:"+msg);
		 if(msg.substr(0,2) == 'OK') // Message Sent, check and redirect
			{//alert("hey  in side ok msg.length="+msg.length);
				//alert(msg.substr(2,msg.length-2)); 
			}//if
			else
			{//alert("hey  in side else msg.length="+msg.length);// and direct to the home page
			 document.location="frslogin.jsp";//automatic redirection to the home page
			 alert(msg); //this alerts "Sorry the current session is expired" 
			 }//else
}//sessioncheck()
//starting session checking code when click the ajax requests made--------------------------------------------------------------- 
