<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Entitlement.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/PageFormat.css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- <script>
            $(function () {
                $("#datepicker").datepicker();
            });
       </script> -->
<script type="text/javascript">
	$(function() {
		$('#MGR_DOB').datepicker();
	}); 
</script>
<script type="text/javascript">
	$(function() {
		$('#MGR_JOIN_DT').datepicker();
	});
</script>

<title>Insert title here</title>
</head>
<body>
<div id = "body">
	<h3>Fill the details below</h3>
	<div>
		<form:form method="POST" action="submit_form"
			modelAttribute="new_manager_form">
			<input type="hidden" name="saveVar" value="M">
			<pre>
			User Id  <form:input type="text" path="MGR_USER_ID" id="id" />
			<br>
			Password  <form:input type="password" path="MGR_PASSWORD"
					id="password" />
			<br>
			<%-- Branch Name <form:input path="branchdtl.BRN_NAME" id="branch_id" /> --%>
			
			<form:select path="branchdtl.BRN_ID">
				<c:forEach var="branchDTL" items="${brnNames}">
				<form:option value="${branchDTL.BRN_ID}"
							label="${branchDTL.BRN_ADDRESS}" />
				</c:forEach>
			</form:select>
			<br>
			<br>
			Name	  <form:input type="text" path="MGR_NAME" id="MGR_NAME" />
			<br>
			Aadhaar No <form:input type="text" path="MGR_ID_PROOF" id="MGR_ID_PROOF" />
			<br> 	
			DOB: <form:input id="MGR_DOB" path="MGR_DOB" />
			<br>
			Date of Joining : <form:input id="MGR_JOIN_DT" path="MGR_JOIN_DT" />
			<br>
			Salary : <form:input id="MGR_SALARY" path="MGR_SALARY" />
                <input type="submit" value="Submit" />
			</pre>
		</form:form>
	</div>
</div>
<%@ include file="Footer.jsp"%>
</body>
</html>