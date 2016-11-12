<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.text.DateFormat" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Calendar" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<title>jQuery UI Tabs - Default functionality: no luck it i svery slow in we editor</title>

<sx:head parseContent="false" />

<script type="text/javascript" src="scripts/jquery.min.js"></script>
<script type="text/javascript" src="scripts/jquery-ui.min.js"></script>
<script type="text/javascript" src="scripts/jquery.table.addrow.js"></script>

<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>

<link href="styles/basix_styles.css" rel="stylesheet" type="text/css"/>
<link href="styles/jquery-ui.css" rel="stylesheet" type="text/css"/>
<link href="styles/multi/tree.css" rel="stylesheet" type="text/css"/>
 
<!--<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<script type='text/javascript' src='/Stems/dwr/interface/BranchDao.js'></script>
<script type='text/javascript' src='/Stems/dwr/engine.js'></script>
<script type='text/javascript' src='/Stems/dwr/util.js'></script>
<script type="text/javascript" src="/Stems/scripts/prototype/prototype.js"></script>
<script type="text/javascript" src="/Stems/scripts/script.aculo.us/effects.js"></script>
<script type="text/javascript" src="/Stems/scripts/script.aculo.us/controls.js"></script>
 -->

<script type="text/javascript" src="scripts/basixvalidation.js"></script>
<script type="text/javascript" src="scripts/yahoo.js"></script>
<script type="text/javascript" src="scripts/event.js"></script>
<script type="text/javascript" src="scripts/treeview.js"></script>
<script type="text/javascript" src="scripts/jktreeview.js"></script>
<script type="text/javascript" src="scripts/rsvalidation.js"></script>
<script type="text/javascript" src="scripts/EmployeeDetailsValidate.js"></script>

<!-- 
<script type="text/javascript" src="scripts/jquery-1.9.1.js"></script>
<script type="text/javascript" src="scripts/jquery-ui.js"></script> -->
<!--<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>-->

<!-- <link rel="stylesheet" href="/resources/demos/style.css" /> -->
<script>
	$(function() {
		$("#tabs").tabs();
	});
	
	$(document).ready(function(){//alert("hey inside the ready function of jquery update");
	$(".addRow").btnAddRow({rowNumColumn:"rowNumber",maxRow:0});
	$(".delRow").btnDelRow();
	});//ready function

</script>

<style>
input.addRow{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:24px;     background-image:url(images/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px;}
input.delRow{   font-size:13px;   font-family: Verdana, Arial, Helvetica, sans-serif;   height:24px;     background-image:url(images/back03.gif);   border-style:inset;   border-color:#DDDDDD;   border-width:1px;}
</style>

<!-- Checked By Rajesh -->
<script type="text/javascript">
function Serious_MedCond()
    {
	//alert("Inside the serious_cond() method");
	//alert("another alert message");
	//alert(document.getElementById("serious_cond").value);
	var value=0;
	value=document.getElementsByName("serious_cond")[0].value;
	//alert("The value is "+value);
	//document.getElementsByName("serious_cond_dtls")[0].disabled=true;
	if(value=="Y")
		{
		//alert("Yes Selected");
		document.getElementsByName("serious_cond_dtls")[0].value="";
		document.getElementsByName("serious_cond_dtls")[0].disabled=false;
		}
	else
		{
		//alert("No Selected");
		document.getElementsByName("serious_cond_dtls")[0].value="";
	    document.getElementsByName("serious_cond_dtls")[0].disabled=true;
		}
	}
function hide_field(){
	//alert("Body onload function called");
	//document.getElementById("hide_field").style.visibility = "hidden";
	document.getElementsByName("serious_cond_dtls")[0].disabled=true;
}
</script>

</head>
<body onload="return hide_field();">
	<table width="95%" border="0" align="center" cellpadding="0"
		cellspacing="0" class="maintable">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top" bgcolor="#c6ced1"><img
							src="images/page_sidebar.jpg" width="2" height="464"
							alt="align bar" /></td>


						<td width="100%" valign="top">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="topbar">
										<!-- Header : Logo and Welcome Message with Log Out-->
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="20%"><a href="User_login.action"><img
														src="images/basix_logo.jpg" alt="BASIX" width="99"
														height="83" border="0" /> </a></td>
												<td width="80%">
													<table width="100%" border="0" cellpadding="2"
														cellspacing="5" class="welcomeMsg">
														<tr>
															<td class="welcomeMsg1"><s:text
																	name="header.loggeduser" /> &nbsp; <s:property
																	value="#session.name" /></td>
														</tr>
														<tr>
															<td class="welcomeMsg1"><s:if
																	test="#session.userRole == 'STEM_OPERATOR'">
																	<s:text name="homepage.stemuser" />
																</s:if> <s:elseif test="#session.userRole == 'INTCON_AGENT'">
																	<s:text name="homepage.intconuser" />
																</s:elseif> <s:elseif test="#session.userRole == 'SYSTEM_ADMIN'">
																	<s:text name="homepage.adminstrator" />
																</s:elseif></td>
														</tr>
													</table>
												</td>
											</tr>
										</table> <!-- END OF Header : Logo and Welcome Messae with Log Out-->
									</td>
								</tr>
								<tr>
									<td>
										<!-- Top Links : Advanced Search And Help-->
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="topLinks">
											<tr>
												<td width="12%"><img src="images/EquityForEquity.jpg"
													width="104" height="21" alt="Equity" /></td>
												<td width="10%" align="right">
													<!-- 	<img src="images/Version.bmp" width="100" height="21"/>   -->
												</td>
												<td width="60%" align="right"><a href="#">Advanced
														Search</a></td>
												<td width="8%" align="right"><img src="images/help.jpg"
													width="62" height="21" alt="Help" /></td>
												<td width="10%" align="right"><span class="welcomeMsg"><a
														href="User_logout.action">Log Out</a> </span></td>
											</tr>
										</table> <!-- END OF Top Links : Advanced Search And Help-->
									</td>
								</tr>
								<tr>
									<td>
										<table width="96%" cellpadding="5" cellspacing="5">
											<tr>

												<td width="20%" align="left" valign="top">
													<!-- MAIN : Content area --> <s:form
														action="RSSave_Request" theme="simple" name="myform">
														<div id="tabs">
															<ul>
																<li><a href="#tabs-7"><b><font size="2">Basic Info</font></b></a></li>
																<li><a href="#tabs-8"><b><font size="2">Contact Info</font></b></a></li>
																<li><a href="#tabs-9"><b><font size="2">Edu Info</font></b></a></li>
																<li><a href="#tabs-1"><b><font size="2">Identity Info</font></b></a></li>
																<li><a href="#tabs-2"><b><font size="2">Bank Details</font></b></a></li>
																<li><a href="#tabs-3"><b><font size="2">Health Info</font></b></a></li>
																<li><a href="#tabs-4"><b><font size="2">Exp Others</font></b></a></li>
																<li><a href="#tabs-5"><b><font size="2">Exp Basix</font></b></a></li>
																<li><a href="#tabs-6"><b><font size="2">Pay Details Basix</font></b></a></li>
															</ul>


															<div id="tabs-7">
																<table width="100%" border="0" cellspacing="3"
																	cellpadding="3">
																	<tr>
																		<td>
																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td colspan="3" align="center">
																						<div id="errorMsg" class="errorMessage" />
																					</td>
																				</tr>
																				<tr>
																					<td colspan="3" align="center"><s:actionerror />
																						<s:fielderror /></td>
																				</tr>
																				
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									 Employee Basic Information </td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Code" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="empcode" name="empcode" /></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Family Name/Surname" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="surname" name="surname" /></td>

																										</tr>

																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="First/Given Name" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="fname" name="fname" /> <!-- <input type="text"  name="voterid"/> -->
																											</td>


																											<td align="right" nowrap="nowrap"><s:label
																													key="Middle/Maiden Name" /></td>
																											<td class="descText">
																												<!-- <input type="text"  name="panno"/> -->
																												<s:textfield size="24" key="mname"
																													name="mname" />
																											</td>



																										</tr>
																										<tr>



																											<td align="right" nowrap="nowrap"><s:label
																													key="Father/Spouse Name " /></td>
																											<td class="descText"><s:textfield
																													size="24" name="fathername" maxlength="15"
																													key="fathername" /></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="BASIX mail id" /></td>

																											<td class="descText"><s:textfield
																													size="24" name="basixmail" maxlength="25"
																													key="basixmail" /></td>



																										</tr>
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Personal mail id" /></td>
																											<td class="descText"><s:textfield
																													size="24" name="personalmail"
																													maxlength="25" key="personalmail" /></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Date of Birth" /></td>

																											<td class="descText"><s:textfield
																													size="24" name="empdob" maxlength="10"
																													key="empdob" />(DD/MM/YYYY)</td>
																										</tr>
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Place of Birth" /></td>

																											<td class="descText"><s:textfield
																													size="24" name="placeofbirth" maxlength="7"
																													key="placeofbirth" /></td>




																										</tr>

																									</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>

																</table>

															</div>


															<div id="tabs-8">
																<table width="100%" border="0" cellspacing="3"
																	cellpadding="3">
																	<tr>
																		<td>
																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td colspan="3" align="center">
																						<div id="errorMsg" class="errorMessage" />
																					</td>
																				</tr>
																				<tr>
																					<td colspan="3" align="center"><s:actionerror />
																						<s:fielderror /></td>
																				</tr>
																				<tr>
																				
																				
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									Employee Contact Details</td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">

																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Code" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="empcode" name="empcode" style="border-color:#0088FF; width:65%"/></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Name" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="surname" name="surname" style="border-color:#0088FF; width:65%"/></td>

																										</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>			

																										
																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Permanent Address" /></td>
																											<td class="descText"><s:textarea
																													name="perminentadress"
																													key="perminentadress" /></td>
																										</tr>
																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Current Home Address" /></td>
																											<td class="descText"><s:textarea
																													name="presentadress" key="presentadress" />

																											</td>
																										</tr>
																										<tr>


																											<td align="right" nowrap="nowrap"><s:label
																													key="Emergency Contact Person 1" /></td>
																											<td class="descText"><s:textfield
																													size="30" name="emrgcp1" key="emrgcp1"
																													maxlength="30" /></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Emergency Contact Person 2" /></td>
																											<td class="descText"><s:textfield
																													size="30" name="emrgcp1" key="emrgcp1"
																													maxlength="30" /></td>

																										</tr>
																										<tr>


																											<td align="right" nowrap="nowrap"><s:label
																													key="Emergency Contact Number 1" /></td>
																											<td class="descText"><s:textfield
																													size="30" name="emrgcn1" key="emrgcn1"
																													maxlength="30" /></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Emergency Contact Number 2" /></td>
																											<td class="descText"><s:textfield
																													size="30" name="emrgcn21" key="emrgcn2"
																													maxlength="30" /></td>

																										</tr>
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Blood Group" /></td>
																											<td class="descText"><s:textfield
																													size="30" name="bloodgroup"
																													key="bloodgroup" maxlength="30" /></td>
																										</tr>

																									</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>

																</table>
																<p></p>
															</div>


															<div id="tabs-9">
																<table width="100%" border="0" cellspacing="3"
																	cellpadding="3">
																	<tr>
																		<td>
																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td colspan="3" align="center">
																						<div id="errorMsg" class="errorMessage" />
																					</td>
																				</tr>
																				<tr>
																					<td colspan="3" align="center"><s:actionerror />
																						<s:fielderror /></td>
																				</tr>

																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									Employee Full Time Educational Information</td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">
<%-- 																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Code" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="empcode" name="empcode" /></td>


																										</tr>

 --%>		
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Code" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="empcode" name="empcode" style="border-color:#0088FF; width:65%"/></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Name" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="surname" name="surname" style="border-color:#0088FF; width:65%"/></td>

																										</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>																						
																									<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Year 12th Class of School/Equivalent passed year" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="passedyear12"
																													name="passedyear12" /></td>

																										</tr>
																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Year of first Full Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="yearof1d" name="yearof1d" />
																											</td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Name of First Full Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="1dname" name="1dname" /></td>



																										</tr>
																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Year of Second  Full Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="yearof1d" name="yearof1d" />
																											</td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Name of Second  Full Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="1dname" name="1dname" /></td>
																										</tr>
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Year of Third   Full Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="yearof1d" name="yearof1d" />
																											</td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Name of Third   Full Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="1dname" name="1dname" /></td>
																										</tr>
																									</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td>

																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									Employee Part Time Educational Information</td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">
																										<tr>

																										</tr>
																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Year of first Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="yearofp1d" name="yearofp1d" />
																											</td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Name of First Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="p1dname" name="p1dname" /></td>



																										</tr>
																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Year of Second  Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="yearofp1d" name="yearofp1d" />
																											</td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Name of Second  Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="p1dname" name="p1dname" /></td>
																										</tr>
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Year of Third   Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="yearofp1d" name="yearofp1d" />
																											</td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Name of Third   Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="p1dname" name="p1dname" /></td>
																										</tr>

																									</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>



																		</td>
																	</tr>
																	<tr>
																		<td>

																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									Employee Work Experience In Between Education
																									Time</td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">
																										<tr>

																										</tr>
																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Year of first Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="yearofp1d" name="yearofp1d" />
																											</td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Name of First Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="p1dname" name="p1dname" /></td>



																										</tr>
																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Year of Second  Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="yearofp1d" name="yearofp1d" />
																											</td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Name of Second  Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="p1dname" name="p1dname" /></td>
																										</tr>
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Year of Third   Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="yearofp1d" name="yearofp1d" />
																											</td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Name of Third   Part Time Degree/Diploma" />
																											</td>
																											<td class="descText"><s:textfield
																													size="24" key="p1dname" name="p1dname" /></td>
																										</tr>


																									</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>



																		</td>
																	</tr>

																</table>
																<p></p>
															</div>



															<div id="tabs-1">
																<table width="100%" border="0" cellspacing="3"
																	cellpadding="3">
																	<tr>
																		<td>
																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td colspan="3" align="center">
																						<div id="errorMsg" class="errorMessage" />
																					</td>
																				</tr>
																				<tr>
																					<td colspan="3" align="center"><s:actionerror />
																						<s:fielderror /></td>
																				</tr>

																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									Identity Information </td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">
																									
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Code" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="empcode" name="empcode" style="border-color:#0088FF; width:65%"/></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Name" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="surname" name="surname" style="border-color:#0088FF; width:65%"/></td>

																										</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>	
																										
																										<tr>
																										  <td align="right" nowrap="nowrap"><s:label
																													key="Nationality" /></td>
																											<td class="descText">
																											<s:select key="plan" name="plan"
																												headerValue="Select Nationality" headerKey="0"
																												list="#{'1':'Afghan','2':'Albanian','3':'Algerian','4':'Andorran',
																												       '5':'Angolan','6':'Argentinian','7':'Armenian','8':'Australian',
																												       '9':'Austrian','10':'Azerbaijani','11':'Bahamian','12':'Bahraini',
																												       '13':'Bangladeshi','14':'Barbadian','15':'Belarusian or Belarusan',
																												       '16':'Belgian','17':'Belizean','18':'Beninese','19':'Bhutanese',
																												       '20':'Bolivian','21':'Bosnian','22':'Botswanan','23':'Brazilian',
																												       '24':'British','25':'Bruneian','26':'Bulgarian','27':'Burkinese',
																												       '28':'Burmese','29':'Burundian','30':'Cambodian','31':'Cameroonian',
																												       '32':'Canadian','33':'Cape Verdean','34':'Chadian','35':'Chilean',
																												       '36':'Chinese','37':'Colombian','38':'Congolese','39':'Costa Rican',
																												       '40':'Croat or Croatian','41':'Cuban','42':'Cypriot','43':'Czech',
																												       '44':'Danish','45':'Djiboutian','46':'Dominican','47':'Ecuadorean',
																												       '48':'Egyptian','49':'Salvadorean','50':'English','51':'Eritrean',
																												       '52':'Estonian','53':'Ethiopian','54':'Fijian','55':'Finnish',
																												       '56':'French','57':'Gabonese','58':'Gambian','59':'Georgian',
																												       '60':'German','61':'Ghanaian','62':'Greek','63':'Grenadian',
																												       '64':'Guatemalan','65':'Guinean','66':'Guyanese','67':'Haitian',
																												       '68':'Dutch','69':'Honduran','70':'Hungarian','71':'Icelandic',
																												       '72':'Indian','73':'Indonesian','74':'Iranian','75':'Iraqi',
																												       '76':'Irish','77':'Italian','78':'Jamaican','79':'Japanese',
																												       '80':'Jordanian','81':'Kazakh','82':'Kenyan','83':'Kuwaiti',
																												       '84':'Laotian','85':'Latvian','86':'Lebanese','87':'Liberian',
																												       '88':'Libyan','89':'Lithuanian','90':'Macedonian','91':'Malagasy or Madagascan',
																												       '92':'Malawian','93':'Malaysian','94':'Maldivian','95':'Malian','96':'Maltese',
																												       '97':'Mauritanian','98':'Mauritian','99':'Mexican','100':'Moldovan',
																												       '101':'Monégasque or Monacan','102':'Mongolian','103':'Montenegrin',
																												       '104':'Moroccan','105':'Mozambican','106':'Namibian','107':'Nepalese',
																												       '108':'Nicaraguan','109':'Nigerien','110':'North Korean','111':'Norwegian',
																												       '112':'Omani','113':'Pakistani','114':'Panamanian','115':'Papua New Guinean or Guinean',
																												       '116':'Paraguayan','117':'Peruvian','118':'Philippine','119':'Polish',
																												       '120':'Portuguese','121':'Qatari','122':'Romanian','123':'Russian',
																												       '124':'Rwandan','125':'Saudi Arabian or Saudi','126':'Scottish',
																												       '127':'Senegalese','128':'Serb or Serbian','129':'Seychellois',
																												       '130':'Sierra Leonian','131':'Singaporean','132':'Slovak','133':'Slovene or Slovenian',
																												       '134':'Somali','135':'South African','136':'South Korean','137':'Spanish',
																												       '138':'Sri Lankan','139':'Sudanese','140':'Surinamese','141':'Swazi',
																												       '142':'Swedish','143':'Swiss','144':'Syrian','145':'Taiwanese','146':'Tajik or Tadjik',
																												       '147':'Tanzanian','148':'Thai','149':'Togolese','150':'Trinidadian Tobagan/Tobagonian',
																												       '151':'Tunisian','152':'Turkish','153':'Turkmen or Turkoman','154':'Tuvaluan',
																												       '155':'Ugandan','156':'Ukrainian','157':'Emirates','158':'British','159':'Uruguayan',
																												       '160':'Uzbek','161':'Vanuatuan','162':'Venezuelan','163':'Vietnamese','164':'Welsh',
																												       '165':'Western Samoan','166':'Yemeni','167':'Yugoslav','168':'Zaïrean','169':'Zambian',
																												       '170':'Zimbabwean'}"
																												       
																											/>
																											</td>
																										</tr>
																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Aadhaar Card No" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="adharcardno"
																													name="adharcardno" /> <s:hidden
																													name="dtConcept" key="dtConcept"/></td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Voter ID No" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="voteridno" name="voteridno" />

																												<!-- <input type="text"  name="voterid"/> -->
																											</td>


																										</tr>

																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="PAN No" /></td>
																											<td class="descText">
																												<!-- <input type="text"  name="panno"/> -->
																												<s:textfield size="24" key="panno"
																													name="panno" />
																											</td>



																										</tr>
																										<tr>



																											<td align="right" nowrap="nowrap"><s:label
																													key="Driving License No" /></td>
																											<td class="descText"><s:textfield
																													size="24" name="drvlcnsno" maxlength="7"
																													key="proposalno" /></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Valid Till (DD/MM/YYYY)" /></td>

																											<td class="descText"><s:textfield
																													size="24" name="drvlcnsevalid"
																													maxlength="10" key="drvlcnsevalid" /></td>



																										</tr>
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Passport No" /></td>
																											<td class="descText"><s:textfield
																													size="24" name="passportno" maxlength="7"
																													key="passportno" /></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Valid Till(DD/MM/YYYY)" /></td>

																											<td class="descText"><s:textfield
																													size="24" name="passportvalid"
																													maxlength="10" key="passportvalid" /></td>


																										</tr>


																										</tr>

																									</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>

																</table>

															</div>




															<div id="tabs-2">
															
															<!-- 	<table width="100%" border="0" cellspacing="3"
																	cellpadding="3">
																	<tr>
																		<td>

																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									 Bank Details </td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">
																										<tr>

																										</tr>

																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Code" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="empcode" name="empcode" style="border-color:#0088FF; width:65%"/></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Name" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="surname" name="surname" style="border-color:#0088FF; width:65%"/></td>

																										</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>
																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Bank Name" /></td>
																											<td class="descText"><s:textfield
																													size="30" maxlength="30" name="bankname"
																													key="bankname" /></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Branch Name" /></td>
																											<td class="descText"><s:textfield
																													size="30" maxlength="30" name="branchname"
																													key="branchname" /></td>


																										</tr>
																										<tr>


																											<td align="right" nowrap="nowrap"><s:label
																													key="IFSC Code" /></td>
																											<td class="descText"><s:textfield
																													size="30" name="ifsccode" key="ifsccode"
																													maxlength="30" /></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Bank A/C No" /></td>
																											<td class="descText"><s:textfield
																													size="30" name="accountno" key="accountno"
																													maxlength="30" /></td>

																										</tr>

																										</tr>





																									</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>



																		</td>
																	</tr>

																</table>
																 -->
																<table width="100%" border="0" cellspacing="3"
																	cellpadding="3">
																	<tr>
																		<td>
																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td colspan="3" align="center">
																						<div id="errorMsg" class="errorMessage" />
																					</td>
																				</tr>
																				<tr>
																					<td colspan="3" align="center"><s:actionerror />
																						<s:fielderror /></td>
																				</tr>

																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									Bank Details</td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">

 
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Code" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="empcode" name="empcode" style="border-color:#0088FF; width:65%"/></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Name" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="surname" name="surname" style="border-color:#0088FF; width:65%"/></td>

																										</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>
																	<tr>
																		<td colspan="4" height="100%" valign="top"
																			align="center"><div align="left">
																				<b><!-- HR Experience Others --></b>
																			</div>

																			<table id="tableID" width="100%" border="1"
																				style="border-style: outset; border-color: blue">
																				<!--  -->
																				
																				
																				<tr
																					style="background-image: url(images/TableHeadBg.gif)">

																			
																					<th width="2%" scope="col" class="style21"><div
																							align="center">Sno</div></th>
																					<th width="16%" scope="col" class="style21"><div
																							align="center">Bank Name</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">Branch Name</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">IFSC Code</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">Bank A/C No</div></th>
																					
																					
																					
																					
																					<th width="1%">
																						<div align="center">
																							<input type="button" id="add" name="add"
																								value="+" class="addRow"
																								onmouseover="goLite(this.form.name,this.name)"
																								onmouseout="goDim(this.form.name,this.name)" />
																						</div>
																					</th>
																				</tr>
																				<tr>
																					<td>
																						<div align="center">
																							<input type="text" name="hr_sno" id="hr_sno"
																								maxlength="2" size="1" value="1"
																								class="rowNumber" readonly="" />
																						</div>
																					</td>
																					<td><div align="center">
																							<s:textfield
																								name="bankname"
																								type="text"
																								id="bankname"
																								style="border-color:#0088FF; width:95%"
																								onkeyup="allowupdate();" maxlength="57"/>
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="branchname"
																								type="text"
																								id="branchname"
																								style="width:95%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="ifsccode"
																								type="text"
																								id="ifsccode"
																								style="border-color:#0099FF; width:95%"
																								onkeyup="allowupdate();"
																								onfocus="addSuggestion('hr_ExperienceOthers_Designation','hr_ExperienceOthers_Designation');"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="accountno"
																								type="text"
																								id="accountno"
																								style="width:90%" onkeyup="allowupdate();"
																								maxlength="15" />
																						</div></td>
																					
																					
																					
																					
																					<td>
																						<div align="center">
																							<input type="button" id="remove" name="remove"
																								value="-" class="delRow"
																								onclick="allowupdate();"
																								onmouseover="goLite2(this)"
																								onmouseout="goDim2(this)" />
																						</div>
																					</td>
																				</tr>
																			</table>
																	</tr>

																									</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>

																</table>
																
															</div>
															
															
															<div id="tabs-3">
																<table width="100%" border="0" cellspacing="3"
																	cellpadding="3">
																	<tr>
																		<td>
																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td colspan="3" align="center">
																						<div id="errorMsg" class="errorMessage" />
																					</td>
																				</tr>
																				<tr>
																					<td colspan="3" align="center"><s:actionerror />
																						<s:fielderror /></td>
																				</tr>

																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									Health Information </td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">
																									
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Code" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="empcode" name="empcode" style="border-color:#0088FF; width:65%"/></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Name" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="surname" name="surname" style="border-color:#0088FF; width:65%"/></td>

																										</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>
																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Blood Group" /></td>
																											<td class="descText">
																											<%-- <s:textfield
																													size="24" name="bloodgroup"
																													key="bloodgroup" />
																											 --%>
																											 <s:select key="bloodgroup" name="bloodgroup"
																											 headerValue="Select Blood Group" headerKey="0"
																												list="#{'1':'A+','2':'A-','3':'B+','4':'B-','5':'AB+','6':'AB-','7':'O+','8':'O-'}"/>
																											 </td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Diabetes" /></td>
																											<td class="descText">
																											<%-- <s:textfield
																											   size="24" name="Diabetes" key="Diabetes" /> --%>
																											 <s:select key="Diabetes" name="Diabetes"
																											 headerValue="Select Yes/No" headerKey="0"
																												list="#{'Y':'Yes','N':'NO'}"/>

																											</td>


																										</tr>

																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Hypertensive" /></td>
																											<td class="descText">
																											<%-- <s:textfield
																													size="24" name="Hypertensive"
																													key="Hypertensive" /> --%>
																												<s:select key="Hypertensive" name="Hypertensive"
																											 headerValue="Select Yes/No" headerKey="0"
																												list="#{'Y':'Yes','N':'NO'}"/>
																												
																													
																											</td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Disabled" /></td>
																											<td class="descText">
																										<!-- 	<s:textfield
																											size="24" name="Disabled" key="Disabled" />   -->
																											<s:select key="Disabled" name="Disabled"
																											 headerValue="Select Yes/No" headerKey="0"
																												list="#{'Y':'Yes','N':'NO'}"/>

																											</td>


																										</tr>


																										<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Do You Have Any  Serious Medical Conditions" />
																											</td>
																											<td class="descText">
																											<%-- <s:textfield
																													size="24" name="serious_cond"
																													key="serious_cond" /> --%>
																											<s:select key="serious_cond" name="serious_cond"
																											 headerValue="Select Yes/No" headerKey="0"
																												list="#{'Y':'Yes','N':'NO'}" onchange="return Serious_MedCond();"/>
																													
																											</td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Please Enter the Details" name="serious_cond_dtls_label"
																													id="serious_cond_dtls_label"/></td>
																											<td class="descText"><s:textfield
																													size="24" name="serious_cond_dtls" key="serious_cond_dtls" />

																											</td>
																											
																											</tr>
																											<tr>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Any other" /></td>
																											<td class="descText"><s:textfield
																													size="24" name="anyother" key="anyother" />

																											</td>
																										</tr>
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Doctor's Name" /></td>
																											<td class="descText"><s:textfield
																													size="24" name="doctor_name"
																													key="doctor_name" /></td>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Doctor's No" /></td>
																											<td class="descText"><s:textfield
																													size="24" name="doctor_no" key="doctor_no" />
																											</td>
																										</tr>
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Medical Insurance No" /></td>
																											<td class="descText"><s:textfield
																													size="24" name="Insuranceno"
																													key="Insuranceno" /></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Valid till" /></td>
																											<td class="descText"><s:textfield
																													size="24" name="medinsvalid"
																													key="medinsvalid" /></td>
																										</tr>


																										

																									</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>

																</table>

															</div>
															
															<div id="tabs-4">
																<table width="100%" border="0" cellspacing="3"
																	cellpadding="3">
																	<tr>
																		<td>
																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td colspan="3" align="center">
																						<div id="errorMsg" class="errorMessage" />
																					</td>
																				</tr>
																				<tr>
																					<td colspan="3" align="center"><s:actionerror />
																						<s:fielderror /></td>
																				</tr>

																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									Experience from Otherthan Basix </td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">

 
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Code" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="empcode" name="empcode" style="border-color:#0088FF; width:65%"/></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Name" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="surname" name="surname" style="border-color:#0088FF; width:65%"/></td>

																										</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>
																	<tr>
																		<td colspan="4" height="100%" valign="top"
																			align="center"><div align="left">
																				<b><!-- HR Experience Others --></b>
																			</div>

																			<table id="tableID" width="100%" border="1"
																				style="border-style: outset; border-color: blue">
																				<!--  -->
																				
																				
																				<tr
																					style="background-image: url(images/TableHeadBg.gif)">

																			
																					<th width="2%" scope="col" class="style21"><div
																							align="center">Sno</div></th>
																					<th width="16%" scope="col" class="style21"><div
																							align="center">Organization</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">Starting month and year</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">Designation(At the Time Of
																							joining)</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">Designation(At the Time of
																							Leaving)</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">PF No</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">Salary(In terms of
																							CarryHomePack,Perks and CTC)</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">Work Experiance(In Years)</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">Ending month and year</div></th>
																					<th width="1%">
																						<div align="center">
																							<input type="button" id="add" name="add"
																								value="+" class="addRow"
																								onmouseover="goLite(this.form.name,this.name)"
																								onmouseout="goDim(this.form.name,this.name)" />
																						</div>
																					</th>
																				</tr>
																				<tr>
																					<td>
																						<div align="center">
																							<input type="text" name="hr_sno" id="hr_sno"
																								maxlength="2" size="1" value="1"
																								class="rowNumber" readonly="" />
																						</div>
																					</td>
																					<td><div align="center">
																							<s:textfield
																								name="hr_ExperienceOthers_Organization"
																								type="text"
																								id="hr_ExperienceOthers_Organization"
																								style="border-color:#0088FF; width:95%"
																								onkeyup="allowupdate();" maxlength="57"
																								onfocus="addSuggestion2('hr_ExperienceOthers_Organization','hr_ExperienceOthers_Organization',BSFLUNIT_UCODE.value);" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="hr_ExperienceOthers_StartYearMonth"
																								type="text"
																								id="hr_ExperienceOthers_StartYearMonth"
																								style="width:95%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="hr_ExperienceOthers_Designation_Joining"
																								type="text"
																								id="hr_ExperienceOthers_Designation_Joining"
																								style="border-color:#0099FF; width:95%"
																								onkeyup="allowupdate();"
																								onfocus="addSuggestion('hr_ExperienceOthers_Designation','hr_ExperienceOthers_Designation');"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="hr_ExperienceOthers_Designation_Leaving"
																								type="text"
																								id="hr_ExperienceOthers_Designation_Leaving"
																								style="width:90%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield name="hr_ExperienceOthers_PFNo"
																								type="text" id="hr_ExperienceOthers_PFNo"
																								style="width:90%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield name="hr_ExperienceOthers_Salary"
																								type="text" id="hr_ExperienceOthers_Salary"
																								style="width:90%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="hr_ExperienceOthers_Experiance"
																								type="text" id="hr_ExperienceOthers_Experiance"
																								style="width:90%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="hr_ExperienceOthers_EndYearMonth"
																								type="text"
																								id="hr_ExperienceOthers_EndYearMonth"
																								style="width:90%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td>
																						<div align="center">
																							<input type="button" id="remove" name="remove"
																								value="-" class="delRow"
																								onclick="allowupdate();"
																								onmouseover="goLite2(this)"
																								onmouseout="goDim2(this)" />
																						</div>
																					</td>
																				</tr>
																			</table>
																	</tr>

																									</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>

																</table>

															</div>
															
															<div id="tabs-5">
																<table width="100%" border="0" cellspacing="3"
																	cellpadding="3">
																	<tr>
																		<td>
																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td colspan="3" align="center">
																						<div id="errorMsg" class="errorMessage" />
																					</td>
																				</tr>
																				<tr>
																					<td colspan="3" align="center"><s:actionerror />
																						<s:fielderror /></td>
																				</tr>

																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									Experience in Basix </td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">

																									
																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Code" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="empcode" name="empcode" style="border-color:#0088FF; width:65%"/></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Name" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="surname" name="surname" style="border-color:#0088FF; width:65%"/></td>

																										</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>
																	<tr>
																		<td colspan="4" height="100%" valign="top"
																			align="center"><div align="left">
																				<b><!-- Experience Basix --></b>
																			</div>
																			<table id="tableID" width="100%" border="1"
																				style="border-style: outset; border-color: blue">
																				<tr
																					style="background-image: url(images/TableHeadBg.gif)">

																					<th width="2%" scope="col" class="style21"><div
																							align="center">Sno</div></th>
																					<th width="16%" scope="col" class="style21"><div
																							align="center">Organization</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">Designation</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">Starting month and year</div></th>
																					<th width="13%" scope="col" class="style21"><div
																							align="center">Ending month and year</div></th>

																					<th width="1%">
																						<div align="center">
																							<input type="button" id="add" name="add"
																								value="+" class="addRow"
																								onmouseover="goLite(this.form.name,this.name)"
																								onmouseout="goDim(this.form.name,this.name)" />
																						</div>
																					</th>
																				</tr>
																				<tr>
																					<td>
																						<div align="center">

																							<input type="text" name="hr_sno" id="hr_sno"
																								maxlength="2" size="1" value="1"
																								class="rowNumber" readonly="" />
																						</div>
																					</td>
																					<td><div align="center">
																							<s:textfield
																								name="hr_ExperienceOthers_Organization"
																								type="text"
																								id="hr_ExperienceOthers_Organization"
																								style="border-color:#0088FF; width:95%"
																								onkeyup="allowupdate();" maxlength="57"
																								onfocus="addSuggestion2('hr_ExperienceOthers_Organization','hr_ExperienceOthers_Organization',BSFLUNIT_UCODE.value);" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="hr_ExperienceOthers_Designation"
																								type="text" id="hr_ExperienceOthers_Designation"
																								style="border-color:#0099FF; width:95%"
																								onkeyup="allowupdate();"
																								onfocus="addSuggestion('hr_ExperienceOthers_Designation','hr_ExperienceOthers_Designation');"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="hr_ExperienceOthers_StartYearMonth"
																								type="text"
																								id="hr_ExperienceOthers_StartYearMonth"
																								style="width:95%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="hr_ExperienceOthers_EndYearMonth"
																								type="text"
																								id="hr_ExperienceOthers_EndYearMonth"
																								style="width:90%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td>
																						<div align="center">
																							<input type="button" id="remove" name="remove"
																								value="-" class="delRow"
																								onclick="allowupdate();"
																								onmouseover="goLite2(this)"
																								onmouseout="goDim2(this)" />
																						</div>
																					</td>
																				</tr>
																			</table></td>
																	</tr>
																	</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>

																</table>

															</div>
															<br>
																														<div id="tabs-6">
																<table width="100%" border="0" cellspacing="3"
																	cellpadding="3">
																	<tr>
																		<td>
																			<table width="100%" border="0" cellspacing="0"
																				cellpadding="0">
																				<tr>
																					<td colspan="3" align="center">
																						<div id="errorMsg" class="errorMessage" />
																					</td>
																				</tr>
																				<tr>
																					<td colspan="3" align="center"><s:actionerror />
																						<s:fielderror /></td>
																				</tr>

																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="sectionHeader">
																							<tr>
																								<td width="2%"><img
																									src="images/tableHeader_left.jpg" width="8"
																									height="34" alt="header" /></td>
																								<td width="97%" class="sectionHeadertext">
																									Payment Details Of Basix </td>
																								<td width="1%" align="right"><img
																									src="images/tableHeader_right.jpg" width="8"
																									height="34" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="loginbox">
																						<table width="100%" border="0" cellspacing="3"
																							cellpadding="3">
																							<tr>
																								<td>
																									<table width="100%" border="0" cellpadding="2"
																										cellspacing="2" class="descTable">

																										<tr>
																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Code" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="empcode" name="empcode" style="border-color:#0088FF; width:65%"/></td>

																											<td align="right" nowrap="nowrap"><s:label
																													key="Employee Name" /></td>
																											<td class="descText"><s:textfield
																													size="24" key="surname" name="surname" style="border-color:#0088FF; width:65%"/></td>

																										</tr>
																									<tr>
																									<td width="" height="12"></td>
																									<td width=""></td>
																									</tr>
																	<tr>
																		<td colspan="4" height="100%" valign="top"
																			align="center"><div align="left">
																				<b><!-- Pay Details Basix --></b>
																			</div>
																			<table id="tableID" width="100%" border="1"
																				style="border-style: outset; border-color: blue">
																				<!--  -->
																				<tr>
<!--class="style21" 
style="background-image: url(images/TableHeadBg.gif)"
 -->
																					<th width="2%" scope="col" ><div align="center">Sno</div></th>
																					<th width="16%" scope="col" ><div
																							align="center">Basic salary</div></th>
																					<th width="13%" scope="col" ><div
																							align="center">First Half Year CPIX</div></th>
																					<th width="13%" scope="col" ><div
																							align="center">Second Half Year CPIX</div></th>
																					<th width="13%" scope="col" ><div
																							align="center">Basic Salary(From DOJ till
																							March 31st)</div></th>
																					<th width="13%" scope="col" ><div
																							align="center">Year Wise History</div></th>
																					<th width="1%">
																						<div align="center">
																							<input type="button" id="add" name="add"
																								value="+" class="addRow"
																								onmouseover="goLite(this.form.name,this.name)"
																								onmouseout="goDim(this.form.name,this.name)" />
																						</div>
																					</th>
																				</tr>
																				<tr>
																					<td>
																						<div align="center">
																							<!--Business Development
                      																		<input type="hidden" name="head_bd" id="head_bd" value="Business Development"/>-->
																							<input type="text" name="hr_sno" id="hr_sno"
																								maxlength="2" size="1" value="1"
																								class="rowNumber" readonly="" />
																						</div>
																					</td>
																					<td><div align="center">
																							<s:textfield name="hr_PayDetails_BasicSalary"
																								type="text" id="hr_PayDetails_BasicSalary"
																								onkeyup="allowupdate();" maxlength="57"
																								onfocus="addSuggestion2('hr_ExperienceOthers_Organization','hr_ExperienceOthers_Organization',BSFLUNIT_UCODE.value);" />
																						</div></td>
																					<!-- 	<td><div align="center"><input name="hr_PayDetails_FirstHYCPIX" type="text" id="hr_PayDetails_FirstHYCPIX"  style="width:95%" onkeyup="allowupdate();" maxlength="57"/></div></td>   -->
																					<td><div align="center">
																							<s:textfield name="hr_PayDetails_FirstHYCPIX"
																								type="text" id="hr_PayDetails_FirstHYCPIX"
																								style="width:95%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield name="hr_PayDetails_SecondHYCPIX"
																								type="text" id="hr_PayDetails_SecondHYCPIX"
																								onkeyup="allowupdate();"
																								onfocus="addSuggestion('hr_ExperienceOthers_Designation','hr_ExperienceOthers_Designation');"
																								maxlength="57" /><%-- style="border-color:#0099FF; width:95%" --%>
																						</div></td>
																					<td><div align="center">
																							<s:textfield
																								name="hr_PayDetailsCurrentBasicSalary"
																								type="text" id="hr_PayDetailsCurrentBasicSalary"
																								style="width:90%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td><div align="center">
																							<s:textfield name="hr_PayDetails_History"
																								type="text" id="hr_PayDetails_History"
																								style="width:90%" onkeyup="allowupdate();"
																								maxlength="57" />
																						</div></td>
																					<td>
																						<div align="center">
																							<input type="button" id="remove" name="remove"
																								value="-" class="delRow"
																								onclick="allowupdate();"
																								onmouseover="goLite2(this)"
																								onmouseout="goDim2(this)" />
																						</div>
																					</td>
																				</tr>
																			</table></td>
																	</tr>
																	</table>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<table width="100%" border="0" cellpadding="0"
																							cellspacing="0" class="tablefooters2">
																							<tr>
																								<td width="2%"><img
																									src="images/table2_bottom_left.jpg" width="8"
																									height="8" alt="header" /></td>
																								<td width="1%" align="right"><img
																									src="images/table2_bottom_right.jpg" width="8"
																									height="8" alt="table header" /></td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>

																</table>

															</div>

														</div>




														<table align="center">
															<tr>
																<td></td>
																<td>
															<tr>
																<td colspan="2" align="center"><input type="submit"
																	name="button3" id="button3" value="Save" />
																	<!-- 
																	onclick="return validateForm();" --></td>
															</tr>
															</td>
															</tr>
														</table>
													</s:form> <!--END OF MAIN : Content area -->
													<p>&nbsp;</p>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
						<td valign="top" bgcolor="#c6ced1"><img
							src="images/page_sidebar.jpg" alt="align bar" width="2"
							height="464" align="right" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<!-- FOOTER : Footer Content area -->

				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="footer">
					<tr>
						<td width="4%"><img src="images/footer_left.jpg" width="30"
							height="67" alt="Footer Align" /></td>
						<td width="92%" align="center">
							<p>Copyright &copy;2009 BASIX&#13;</p>
						</td>
						<td width="4%"><img src="images/footer_right.jpg"
							alt="Footer Align" width="30" height="67" align="right" /></td>
					</tr>
				</table> <!-- END OF FOOTER : Footer Content area -->
			</td>
		</tr>
	</table>
</body>
</html>



