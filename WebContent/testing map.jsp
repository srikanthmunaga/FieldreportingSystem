<%@taglib uri="http://java.sun.com/jstl/sql" prefix="sql"%><%@taglib
	uri="http://java.sun.com/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head><body>

&gt;conn sys as sysdba;
<br>&gt;alter user raj account unlock;
	<hr>
	<br>&gt;grant connect,resource to raj;
<form>
		<input type="password"><input type="image"><sql:query var="comp_mstr" sql="select * from company_mstr">
			<c:out value="comp_mstr"> </c:out>
		</sql:query><br>
	</form>
	<table border="5" bordercolor="red" style="color: Chartreuse; background-color: Black">
		<tr>
			<td bgcolor="1">Cell</td>
			<td>Cell</td>
			<td>Cell</td>
		</tr>
		<tr>
			<td>Cell</td>
			<td>Cell</td>
			<td>Cell</td>
		</tr>
	</table>
	<img alt="img" src="images/images.jpg" usemap="#Map1" width="900" height="600" border="1">
<map name="Map1" id="Map1">
<area href="firstjsp.jsp" coords="200,200,250,250" shape="rect">
<area href="secondjsp.jsp" coords="300,300,350,350" shape="rect">
</map>
<br></body>
</html>