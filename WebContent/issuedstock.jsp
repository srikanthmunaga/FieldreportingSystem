<%@page import="frs_cls.JdbcConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="dbConn" scope="request" class="frs_cls.JdbcConnect"/> 
<%!
   Connection con=null;
   PreparedStatement ps=null,ps1=null,ps2=null;
   ResultSet rs=null,rs1=null,rs2=null;
   int f=0;
    String e=null;
	
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issued Stock</title>
<% 
						String ur=(String)request.getSession().getAttribute("userrole");
						if (request.getSession().getAttribute("username") == null ) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						}
						/* else if (request.getSession().getAttribute("username")!= null && ur.equals("user")) {
    						response.sendRedirect(request.getContextPath() + "/frslogin.jsp");
    						} */
						else
						{
						%>

<script type="text/javascript" src="JS/jquery.min.js"></script>
<link rel="stylesheet" href="theme/blue.css" type="text/css" />
<link href="styles/basix_styles.css" rel="stylesheet" type="text/css" />
<link href="styles/calendar.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="JS/calendar.js"></script>

<script type="text/javascript" src="JS/FRS_Theme_top.js"></script>	</head>	<body bottommargin="100%" marginheight="100%" rightmargin="100%"  leftmargin="100%" marginwidth="100%" topmargin="100%" onload="disable();"><script language="javascript" type="text/javascript">var username_js="<%=(String)request.getSession().getAttribute("username")%>";</script><script language="javascript" type="text/javascript">var unitname_js="<%=(String)request.getSession().getAttribute("unitname")%>";</script><script language="javascript" type="text/javascript">var userrole_js="<%=(String)request.getSession().getAttribute("userrole")%>";</script><script type="text/javascript" src="JS/FRS_Theme_top2.js"></script><script type="text/javascript" src="JS/Bmenu.js"></script>

<%
String role=(String)((HttpServletRequest) request).getSession().getAttribute("userrole");
if((role.equals("admin"))||(role.equals("audit")))// ||role.equals("areahead") || role.equals("unithead"))
{ %>
<form id="form1" name="form1"
						method="post" onSubmit="return validateForm();" target="_blank">
<!--						<table cellpadding="1" cellspacing="1" background="blue">
							<tr>
								<td width="480" align="right"><b><a
										href="Fhome.jsp">HOME</a></b></td>

								<td width="380"></td>
							</tr>
						</table>--><br />

						<center>
							<!-- By Rajesh from here -->
							 <table align="center">
				<tr>
					<td height="">
						<fieldset style="background-color:">
							<!-- <legend class="style23">Region Master</legend> -->
							<legend class="formTitle">Issued Stock</legend>
							<table bgcolor="" width="100%" height="91%" border="0"
								align="center" bordercolor="#000000">
								<!-- till here -->


								<!-- <tr>
									<td align="right">From Date</td>
									<td><input type="text" name="fdate" id="fdate" size="10"
										maxlength="10" /><a href="#"
										onclick="setYears(1947, 2050); showCalender(this, 'fdate');">
											<img id="cal" src="images/calender.png"
											onClick="allowupdate();" />
									</a>&nbsp;(dd-mm-yyyy)</td>
									<td><input type="text" name="todate" id="todate" size="10"
										maxlength="10" /><a href="#"
										onclick="setYears(1947, 2050); showCalender(this, 'todate');">
											<img id="cal" src="images/calender.png"
											onClick="allowupdate();" />
									</a>&nbsp;(dd-mm-yyyy)</td>
								</tr> -->
								<tr>
									<td colspan="5"></td>
								</tr>
								
								<tr><td colspan="5">
								<table width="80%" align="center" border="1" style="border-style: outset" >
                <%
    int sumcount2=0; 
	
    try {
    con= dbConn.getConnection();
      	
	   // String role=request.getSession().getAttribute("userrole").toString();
		String huma_id=request.getSession().getAttribute("huma_id").toString();
		////System.out.println("inside the AreaDayWiseSummaryreport and role="+role);
		if((role.equals("admin"))||(role.equals("audit")))// || role.equals("areahead") || role.equals("unithead"))
		ps2 = con.prepareStatement("select bsflunit_ucode ,bsflunit_name, s_id,s_name,request_stock,to_char(date_of_indent,'dd-mm-yyyy'),indent_by,iss_stock,to_char(date_of_cou,'dd-mm-yyyy'),row_number() over(order by DATE_OF_COU) as sno  from issue_indent");
		else{
			out.println("No rights reserved to issue a indent master ");
		}
		
	   rs2 = ps2.executeQuery();
	   %>
                <tr bgcolor="silver">
                  <th scope="col" ><div align="center" >S.no</div></th>	
                  <th scope="col" ><div align="center" >Unit Id</div></th>
                  <th scope="col" ><div align="center" >Unit name</div></th>
                  <th scope="col" ><div align="center" >Stationary Id</div></th>
                  <th scope="col" ><div align="center" >Stationary Name </div></th>
                  <th scope="col" ><div align="center" >Requested Stock </div></th>
                  <th scope="col" ><div align="center" >Date Of Indent </div></th>
                  <th scope="col" ><div align="center" >Indent By</div></th>
                  <th scope="col" ><div align="center" >Issued Stock</div></th>
                  <th scope="col" ><div align="center" >Date Of Courier </div></th>
                  
                </tr>
                <%
	  while(rs2.next())
		{
		  %>
                <tr>
                  <td><div align="center"><%=rs2.getString(10)%>&nbsp;</div></td>
                  <td><div align="center"><%=rs2.getString(1)%>&nbsp;</div></td>
				  <td><div align="left"><%=rs2.getString(2)%>&nbsp;</div></td>
                  <td><div align="left"><%=rs2.getString(3)%></div></td>
                  <td><div align="left"><%=rs2.getString(4)%></div></td>
                  <td><div align="left"><%=rs2.getString(5)%></div></td>
                  <td><div align="left"><%=rs2.getString(6)%></div></td>
                  <td><div align="left"><%=rs2.getString(7)%></div></td>
                  <td><div align="left"><%=rs2.getString(8)%></div></td>
                  <td><div align="left"><%=rs2.getString(9)%></div></td>
                   
                  </div></td>
                  
                </tr>
                <%
		}
  %>
              </table>
								</td></tr>
								<!-- By Rajesh -->
								</table>
						</fieldset>
					</td> <!-- td closed -->
				</tr>
								<!-- Till here -->
							</table>
						</center>
					</form>
<script type="text/javascript" src="JS/FRS_Theme_bottom.js"></script>  
</body>
<%
}//try
	catch (Exception e) {
      e.printStackTrace();
    }
 finally 
	{
	 if(rs2!=null)rs2.close();
     if(ps2!=null)ps2.close();
/*	 if(rs3!=null)rs3.close();
     if(ps3!=null)ps3.close();
	 if(rs4!=null)rs4.close();
     if(ps4!=null)ps4.close();
	 if(rs5!=null)rs5.close();
     if(ps5!=null)ps5.close();
*/	 if(con!=null)con.close(); 
    }
   	%>
<%
   }//authorised acess else	//------------------------------------------------------------------------------------
   else
   {
	%>
	<br><br><br><br><div align="center" class="style22">
Sorry,NO Rights to view Issued Stock
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
            <td colspan="2" align="center">
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



