//edited
document.write('<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" /> <link rel="stylesheet" href="theme/blue.css" type="text/css" /> <style type="text/css"> body { background-color: #d0e4fe; } h1 { color: orange; text-align: center; } p { font-family: "Times New Roman"; font-size: 20px; } </style> <script type="text/javascript" src="JS/jquery.min.js"></script> <script type="text/javascript" src="JS/jquery-ui.min.js"></script><script type="text/javascript" src="JS/autoSuggestion.js"></script>   <!--<script type="text/javascript" src="jquery-1.4.2.min.js"></script>--><style>a {	text-decoration:none;	color:blue;}a:hover {	text-decoration:none;	color:#999;	}.ui-autocomplete        {            position:absolute;            cursor:default;            z-index:4000 !important             }</style><script type="text/javascript">function goLite(FRM,BTN){		   window.document.forms[FRM].elements[BTN].style.color =  "#ffffff";	   window.document.forms[FRM].elements[BTN].style.backgroundImage="url(IMAGES/back04.gif)";}function goDim(FRM,BTN){   window.document.forms[FRM].elements[BTN].style.color = "";      window.document.forms[FRM].elements[BTN].style.backgroundImage="url(IMAGES/back03.gif)";} function showmenu(elmnt){document.getElementById(elmnt).style.visibility="visible";}function hidemenu(elmnt){document.getElementById(elmnt).style.visibility="hidden";}function logout() {			  var logout="logout.jsp";					$.ajax({ 				   	url: logout,		          	data: "state_id="+ $("#state_id").val(),		          	type: "post",		   			success: function(msg){ 					msg = msg.replace(/^\s+/,"").replace(/\s+$/,""); 					 if(msg!= "") 						{  							document.location="frslogin.jsp";							alert("you have successfully logged out"); 						}						else{ 						 alert(msg); }			          }				});			}</script><style type="text/css">a:link {	text-decoration: none;}a:active {	text-decoration: none;}.style1 {	font-size: 12px;	font-family: Verdana, Arial, Helvetica, sans-serif;	color: #000000;}.TitleSub1 {font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 14px; }.TitleSub1 {	font-size: 12.5px;	font-weight: bold;}.style24 {	color: #FFFFFF;	font-family: Geneva, Arial, Helvetica, sans-serif;	font-size: 22px;	font-weight: bold;</style><style>body{font-family:arial;}.formTitle {	font-size: 13px;	font-weight: bold;	font-family: Verdana, Arial, Helvetica, sans-serif;}.style22 {	font-size: 12px;	font-family: Courier New, Courier, monospace;}table{font-size:12.5px; border:none}a{text-decoration:none;font: Verdana, Arial, Helvetica, sans-serif}a:hover{color:#ffffff}td.menu{background: url(IMAGES/back06.gif);}table.menu{font-size:12.5px;position:absolute;visibility:hidden;}input.groovybutton{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:24px;     background-image:url(IMAGES/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px;}.groovybutton1{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:20px;     background-image:url(IMAGES/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px;}</style></head> <body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"> <table width="100%" cellspacing="0" cellpadding="0" border="0"	bordercolor="block"> <tbody> <tr> <td valign="top"> <table class="header" cellspacing="0" cellpadding="0" border="0" bordercolor="block" width="100%" height="100%"> <tbody> <tr> <td> <h4><b><font color="white">Bhartiya Samruddhi Finance Limited</font></b></h4><br> <b><font color="white">Field Reporting System</font></b> </td> <td width="90"><img src="images/basix-logo.gif" width="75" height="100" align="middle" /></td> </tr> </tbody> </table> </td> </tr> <tr> <td valign="top" class="nav_head" height="20" align="right"><a href="logout.jsp"><font color="white"><b> Logout</b></font></a></td> </tr> <tr valign="top"> <td valign="middle" height="350" > </head>');



//document.write('<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/> <script type="text/javascript" src="JS/jquery.min.js"></script> <script type="text/javascript" src="JS/jquery-ui.min.js"></script><script type="text/javascript" src="JS/autoSuggestion.js"></script>   <!--<script type="text/javascript" src="jquery-1.4.2.min.js"></script>--><style>a {	text-decoration:none;	color:blue;}a:hover {	text-decoration:none;	color:#999;	}.ui-autocomplete        {            position:absolute;            cursor:default;            z-index:4000 !important             }</style><script type="text/javascript">function goLite(FRM,BTN){		   window.document.forms[FRM].elements[BTN].style.color =  "#ffffff";	   window.document.forms[FRM].elements[BTN].style.backgroundImage="url(IMAGES/back04.gif)";}function goDim(FRM,BTN){   window.document.forms[FRM].elements[BTN].style.color = "";      window.document.forms[FRM].elements[BTN].style.backgroundImage="url(IMAGES/back03.gif)";} function showmenu(elmnt){document.getElementById(elmnt).style.visibility="visible";}function hidemenu(elmnt){document.getElementById(elmnt).style.visibility="hidden";}function logout() {			  var logout="logout.jsp";					$.ajax({ 				   	url: logout,		          	data: "state_id="+ $("#state_id").val(),		          	type: "post",		   			success: function(msg){ 					msg = msg.replace(/^\s+/,"").replace(/\s+$/,""); 					 if(msg!= "") 						{  							document.location="frslogin.jsp";							alert("you have successfully logged out"); 						}						else{ 						 alert(msg); }			          }				});			}</script><style type="text/css">a:link {	text-decoration: none;}a:active {	text-decoration: none;}.style1 {	font-size: 12px;	font-family: Verdana, Arial, Helvetica, sans-serif;	color: #000000;}.TitleSub1 {font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 14px; }.TitleSub1 {	font-size: 12.5px;	font-weight: bold;}.style24 {	color: #FFFFFF;	font-family: Geneva, Arial, Helvetica, sans-serif;	font-size: 22px;	font-weight: bold;</style><style>body{font-family:arial;}.formTitle {	font-size: 13px;	font-weight: bold;	font-family: Verdana, Arial, Helvetica, sans-serif;}.style22 {	font-size: 12px;	font-family: Courier New, Courier, monospace;}table{font-size:12.5px; border:none}a{text-decoration:none;font: Verdana, Arial, Helvetica, sans-serif}a:hover{color:#ffffff}td.menu{background: url(IMAGES/back06.gif);}table.menu{font-size:12.5px;position:absolute;visibility:hidden;}input.groovybutton{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:24px;     background-image:url(IMAGES/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px;}.groovybutton1{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:20px;     background-image:url(IMAGES/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px;}</style></head><body tracingsrc="IMAGES/100_0497.JPG" tracingopacity="8"><table width="100%" height="100%" ><tr ><td width="100%" height="100%"  valign="top"> <table width="100%"  border="0" >  <tr><TD colspan="2" > <TABLE width="100%" height="100%"  >    <TR ><td width="" height="10"   bgcolor="#0033FF" > <h1 align="center" class="style24">MIS For IGS </h1>        <div align="center" class="style24" style="font-size:13px">(Version 2.0)</div></td><TD rowspan="2" align="down" bgcolor="#0033FF" width="20" ><img src="IMAGES/basix-logo.jpg" width="65" height="83" /> </TD><tr> <td height="" bgcolor="#cccccc">');
//document.write(' <!-- drop down code start here,top2.js also start here--> <table width="100%" height=""> <tr style="background:url(IMAGES/back08.jpg)"> ');



//Added By Rajesh

//edited
//document.write(' <script type="text/javascript" src="JS/jquery.min.js"></script> <script type="text/javascript" src="JS/jquery-ui.min.js"></script><script type="text/javascript" src="JS/autoSuggestion.js"></script> <script type="text/javascript">function goLite(FRM,BTN) { window.document.forms[FRM].elements[BTN].style.color =  "#ffffff"; window.document.forms[FRM].elements[BTN].style.backgroundImage= "url(IMAGES/back04.gif)"; function goDim(FRM,BTN){ window.document.forms[FRM].elements[BTN].style.color = ""; window.document.forms[FRM].elements[BTN].style.backgroundImage= "url(IMAGES/back03.gif)";} function showmenu(elmnt){ document.getElementById(elmnt).style.visibility="visible"; } function hidemenu(elmnt){document.getElementById(elmnt).style.visibility="hidden";}</script><link rel="stylesheet" href="theme/blue.css" type="text/css" /> <link href="styles/basix_styles.css" rel="stylesheet" type="text/css" /> <style type="text/css"> body { background-color: #d0e4fe; } h1 { color: orange; text-align: center; } p { font-family: "Times New Roman"; font-size: 20px; } </style>  </head> <body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"> <table width="100%" cellspacing="0" cellpadding="0" border="0"	bordercolor="block"> <tbody> <tr> <td valign="top"> <table class="header" cellspacing="0" cellpadding="0" border="0" bordercolor="block" width="100%" height="100%"> <tbody> <tr> <td> <h4><b><font color="white">Bhartiya Samruddhi Finance Limited</font></b></h4><br> <b><font color="white">Field Reporting System</font></b> </td> <td width="90"><img src="images/basix-logo.gif" width="75" height="100" align="middle" /></td> </tr> </tbody> </table> </td> </tr> <tr> <td valign="top" class="nav_head" height="20" align="right"><a href="logout.jsp"><font color="white"><b> Logout</b></font></a></td> </tr> <tr valign="top"> <td valign="middle" height="350" > </head>');



//document.write(' <script type="text/javascript" src="JS/jquery.min.js"></script> <script type="text/javascript" src="JS/jquery-ui.min.js"></script><script type="text/javascript" src="JS/autoSuggestion.js"></script> <link rel="stylesheet" href="theme/blue.css" type="text/css" /> <link href="styles/basix_styles.css" rel="stylesheet" type="text/css" /> <style type="text/css"> body { background-color: #d0e4fe; } h1 { color: orange; text-align: center; } p { font-family: "Times New Roman"; font-size: 20px; } </style> </head> <body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"> <table width="100%" cellspacing="0" cellpadding="0" border="0"	bordercolor="block"> <tbody> <tr> <td valign="top"> <table class="header" cellspacing="0" cellpadding="0" border="0" bordercolor="block" width="100%" height="100%"> <tbody> <tr> <td> <h4><b><font color="white">Bhartiya Samruddhi Finance Limited</font></b></h4><br> <b><font color="white">Field Reporting System</font></b> </td> <td width="90"><img src="images/basix-logo.gif" width="75" height="100" align="middle" /></td> </tr> </tbody> </table> </td> </tr> <tr> <td valign="top" class="nav_head" height="20" align="right"><a href="logout.jsp"><font color="white"><b> Logout</b></font></a></td> </tr> <tr valign="top"> <td valign="middle" height="350" > </head>');
//document.write(' <!-- drop down code start here,top2.js also start here--> <table width="100%" height=""> <tr style="background:url(IMAGES/back08.jpg)"> ');







//document.write('<td width="16%" onMouseOver="showmenu(\'Masters\')" onMouseOut="hidemenu(\'Masters\')">    <div align="center"><a href="Afrslogin.jsp" class="TitleSub1" id="aMasters"><u>M</u>asters</a><br />     </div>    <table class="menu" id="Masters" width="140">   <tr><td class="menu"><a href="company master.jsp">Company master</a></td></tr>   <tr><td class="menu"><a href="Area master.jsp">Region master</a></td></tr> <tr><td class="menu"><a href="Head master.jsp">Activity master</a></td></tr><!--  <tr><td class="menu"><a href="Product&amp;Service master.jsp">Product/Service master</a></td></tr> --><tr><td class="menu"><a href="AHuman resource master.jsp">Human resource master</a></td></tr>  <tr><td class="menu"><a href="Holiday master.jsp">Holiday master</a></td></tr>   <tr><td class="menu"><a href="AchangePwd.jsp">Change pwd</a></td></tr>    </table>  </td>  <td width="14%" onMouseOver="showmenu(\'Main menu\')" onMouseOut="hidemenu(\'Main menu\')">    <div align="center"><a href="Afrslogin.jsp" class="TitleSub1" id="aMain menu">Mai<u>n</u> menu</a><br />     </div>    <table class="menu" id="Main menu" width="130">   <tr><td class="menu"><a href="new.jsp">New user</a></td></tr>   <tr><td class="menu"><a href="Rights controle master.jsp">Rights Controle master</a></td></tr>   </table>  </td>  <td width="15%" onMouseOver="showmenu(\'Reports\')" onMouseOut="hidemenu(\'Reports\')">    <div align="center"><a href="Afrslogin.jsp" class="TitleSub1" id="aReports"><u>R</u>eports</a><br />     </div>    <table class="menu" id="Reports" width="135">  <tr><td class="menu"><a href="ActivityRepSbu.jsp">Activity List Report</a></td></tr> <tr><td class="menu"><a href="Defaulter report.jsp">Defaulter Report</a></td></tr>   <tr><td class="menu"><a href="Activity summary report.jsp">Activity Summary Report</a></td></tr> <tr><td class="menu"><a href="ActivityVsClientSummaryRep.jsp">ActivityVsClient Summary Report</a></td></tr>  </table>  </td>  <td width="14%" onMouseOver="showmenu(\'Logoff\')" onMouseOut="hidemenu(\'Logoff\')">    <div align="center"><a href="Afrslogin.jsp" class="TitleSub1" id="aLogoff"><u>L</u>ogoff</a><br />     </div>    <table class="menu" id="Logoff" width="130">   <tr><td class="menu"><a href=""Modules.jsp"">Switch Module</a></td></tr>   <tr><td class="menu"><a href="javascript:logout();">Logout</a></td></tr>    </table>  </td>  <td width="41%"> </td> </tr></table><!-- drop down code ended here-->  </td> </TR></TABLE></td></tr><!--master code started here,the below <tr height="" > <td align="center"> also included in top2.js -->   <tr height="" >    <td valign="middle" width="" align="center" >');
$(document).ready(function() {   
	var isCtrl = false;  
	var isAlt = false;  
	var menuDecide=null; 
	/*
	$(document).keyup(function (e) 
			{
		if(isAlt == true) isAlt=false;
		}
	).keydown(function (e) {    
		if(e.which == 17) 
			isCtrl=true;	
		if(e.which == 18) 
			isAlt=true;    
		else if(e.which == 77 && isAlt == true) 
			menuDecide="Masters";    
		else if(e.which == 78 && isAlt == true) 
			menuDecide="Main menu";	
		else if(e.which == 82 && isAlt == true) 
			menuDecide="Reports";	
		else if(e.which == 76 && isAlt == true) 
			menuDecide="Logoff";	
		if(menuDecide!=null)	 
		{document.getElementById("a"+menuDecide).focus();		
		showmenu(menuDecide);		
		menuDecide=null;		
		}	
		if(e.which == 27)         
		{		
			hidemenu("Masters");		
			hidemenu("Main menu");		
			hidemenu("Reports");		
			hidemenu("Logoff");				
			}	 
		});  */ 
	});