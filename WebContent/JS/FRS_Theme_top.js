//edited
document.write('<!---------------- Code for FRS theme starts here  ------------------------->		<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />		<link rel="stylesheet" href="theme/blue.css" type="text/css" />		<style type="text/css">		body { background-color: #d0e4fe; } h1 { color: orange; text-align: center; } p { font-family: "Times New Roman"; font-size: 20px; }		a {	text-decoration:none;	color:black;}a:hover {	text-decoration:none;	color:green	}		.ui-autocomplete        {            position:absolute;            cursor:default;            z-index:4000 !important             }		</style>		<script type="text/javascript" src="JS/jquery.min.js"></script>		<script type="text/javascript" src="JS/jquery-ui.min.js"></script>		<script type="text/javascript" src="JS/autoSuggestion.js"></script>  		<script type="text/javascript">		function goLite(FRM,BTN){		   window.document.forms[FRM].elements[BTN].style.color =  "#ffffff";	   window.document.forms[FRM].elements[BTN].style.backgroundImage="url(images/back04.gif)";}  function goDim(FRM,BTN){   window.document.forms[FRM].elements[BTN].style.color = "";      window.document.forms[FRM].elements[BTN].style.backgroundImage="url(images/back03.gif)";}	function goLite2(BTN){BTN.style.color = "#ffffff";BTN.style.backgroundImage="url(images/back04.gif)";} function goDim2(BTN){BTN.style.color = "";BTN.style.backgroundImage="url(images/back03.gif)";}	function showmenu(elmnt){document.getElementById(elmnt).style.visibility="visible";}		function hidemenu(elmnt){document.getElementById(elmnt).style.visibility="hidden";}		var logout="logout.jsp";						//$.ajax({ 				   	url: logout,		          	data: "state_id="+ $("#state_id").val(),		          	type: "post",		   			success: function(msg){ 					msg = msg.replace(/^\s+/,"").replace(/\s+$/,""); 					 if(msg!= "") 						{  							document.location="frslogin.jsp";							alert("you have successfully logged out"); 						}						else{ 						 alert(msg); }			          }				});				</script>		<style type="text/css">		a:link {	text-decoration: none;}a:active {	text-decoration: none;}		a{text-decoration:none;}		a:hover{color:#ffffff}		input.groovybutton{   height:24px; background-image:url(images/back03.gif);}		.groovybutton1{height:20px; background-image:url(images/back03.gif);}		 </style>		');
//code to assign shortcut key to the manus
$(document).ready(function() { //alert("hety inside the ready function");
//  var isCtrl = false;
  var isAlt = false;
  var menuDecide=null;
  $(document).keyup(function (e) {
//if(e.which == 17) isCtrl=false;
//if(e.which == 18) isAlt=false;
if(isAlt == true) isAlt=false;
}).keydown(function (e) {
    if(e.which == 17) isCtrl=true;//Ctrl=17 //alert("hey the alt is pressed="+	e.which);
	if(e.which == 18) isAlt=true;//Alt=18
    else if(e.which == 77 && isAlt == true) menuDecide="Masters";//for Master menu shortcut=Alt+m
    else if(e.which == 78 && isAlt == true) menuDecide="Operations";//for Operations shortcut=Alt+n
	else if(e.which == 82 && isAlt == true) menuDecide="Reports";//for Reports menu shortcut=Alt+r
	else if(e.which == 76 && isAlt == true) menuDecide="Logoff";//for Logoff menu shortcut=Alt+l
	if(menuDecide!=null)
	 {
	 	document.getElementById("a"+menuDecide).focus();
		showmenu(menuDecide);//alert('Keyboard shortcuts + JQuery are even more cool!');
		menuDecide=null;
		//isAlt=false;
		}
	if(e.which == 27) //for all menus hide shortcut=Esc
        {
		hidemenu("Masters");
		hidemenu("Operations");
		hidemenu("Reports");
		hidemenu("Logoff");		
		//isAlt=false;
		//alert('Keyboard shortcuts + JQuery are even more cool!');
		}	
//	return true;
 
});//keydown 
  });//ready()
//document.ready()