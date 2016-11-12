<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- pleaseWait.jsp -->
    <!-- This is the page where the form is ACTUALLY submitted -->
PLEASE WAIT processing this request <!-- this is the message shown while the page submits -->
<form name="auto" action="SMSCountconfirm" >
  <%--   <input type="hidden" name="username" value="<%=request.getParameter("username")%>"> --%>
</form>
<!-- this script submits the form AFTER it has been completely loaded -->
<script>
    document.auto.submit();
</script>
</body>
</html>