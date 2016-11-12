<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Recovery Targets</title>
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

<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/jquery.min.js"></script>
<script type="text/javascript" src="JS/jquery-ui.min.js"></script>
<script type="text/javascript" src="JS/autoSuggestion.js"></script>

<style type="text/css">

a ove:HOVER {
color: black;	
}

a:HOVER {
color: black;	
}


dislpayLabel

</style>

<script type="text/javascript">

function validate()
{ 
//alert("inside the validation method");
	
	/*if (!fileInput.file[0].fileName.match(/\.(csv|CSV)$/))
    alert('Please select only .csv extension file');
    */
     if (document.form1.elements["file"].value == "")
          {
             alert("Please upload CSV file");
             //document.myform.elements["file"].focus();
             return false;  
         }
   var res_field = document.form1.elements["file"].value;   
  var extension = res_field.substr(res_field.lastIndexOf('.') + 1).toLowerCase();
  //alert("hey the uploaded file extention is="+extension);
  var allowedExtensions = ['csv', 'CSV'];
  //alert("allowedExtensions="+allowedExtensions);
  if (res_field.length > 0)
     {
     //alert(allowedExtensions.); 
     
     //alert("inside if, means grater length and allowedExtensions.indexOf(extension)="+allowedExtensions.indexOf(''));
     //return false;
       
          //if (allowedExtensions.indexOf(extension) == -1)
          if ((extension!='csv')&&(extension!='CSV'))
             {
               //alert('Invalid file Format. Only ' + allowedExtensions.join(', ') + ' are allowed.');
               alert('Invalid file Format, Only [csv, CSV] are allowed.');
               return false;
             }
       //      return false;
    }
}
</script>
<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head><body bottommargin="100%" marginheight="100%" rightmargin="100%" leftmargin="100%" marginwidth="100%" topmargin="100%" onLoad="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Amenu.js"></script>
<%
//if (!request.getSession().getAttribute("userrole").toString().equals("audit") ) {
%>

<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if((role.equals("admin"))||(role.equals("audit")))
{ %>
<!-- <form id="form1" name="form1" method="post" action="XlsUpload" onSubmit="return validate();"> --> 


<!-- <form id="form1" name="form1" method="post" action="CsvPleasewait" enctype="multipart/form-data" onSubmit="return validate();">  -->

<form id="form1" name="form1" method="post" action="CsvPleasewait" enctype="multipart/form-data" onSubmit="return validate();">
<br />
<p align="right">
 <table width="50%" align="center">
  <tr>
  <td valign="top" width="" height="">   
  <fieldset style="background-color:">
	<legend class="formTitle"><b> Upload Recovery Targets</b> </legend>
<br />
<div align="center">Select a file to upload :
<!-- <br/> <br/>  -->
<input type="file" name="file" id="file" size="50" /> 
<br />
<br />
<input type="submit" id="generate" name="generate"
	value="Upload File" onclick="generateTable('war_period');" title=""
	class="groovybutton" onmouseover="goLite(this.form.name,this.name)"
	onmouseout="goDim(this.form.name,this.name)" />
	<br /> <br/>
	<style type="text/css">a:HOVER {color: black;text-decoration: underline;}</style>
	
  <a href="images/sample.csv" class="dislpayLabel" >See Sample format, CSV file</a>	
  </div> </fieldset> 
</table>

					</td>
				</tr>
				<tr>
					<td height="">&nbsp;</td>
				</tr>
			</table>
	
</form>
 
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>

</body>

<%
}//authorised acess else------------------------------------------------------------------------------------
else
{
%>
<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to Upload the File
</div>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>

<%
}
}
	%>
	
</html>

