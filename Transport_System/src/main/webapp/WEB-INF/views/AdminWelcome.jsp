<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="Entitlement.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <%@ page isELIgnored="false" %> --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/PageFormat.css">
<title>Insert title here</title>
<script>
function saveMessage(){
	if ("${value}" === "S") {
			alert("Data Saved Successfully");
		}
	else if("${value}" === "F")
		{
			alert("Data Saving Failed");
		}
	}
</script>
</head>
<body style="background-color: #C8C8C8; ">
<div id = "body">
<div>
	<a href="create_new?new=M" style = "font-size:20px">New Manager</a>&emsp;&emsp;
	<a href="create_new?new=T" style = "font-size:20px">Install new Truck s</a>&emsp;&emsp;
	<a href="find/M" style = "font-size:20px">Find Manager</a>&emsp;&emsp;
	<a href="find/T" style = "font-size:20px">Find Truck</a>&emsp;&emsp;
</div>

<div>
	<c:if test = "${value ne null}">
		<script>saveMessage();</script>
	</c:if>
</div>
</div>
<%@ include file="Footer.jsp"%>
</body>
</html>