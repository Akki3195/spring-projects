<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Entitlement.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Installation of Truck</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/PageFormat.css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">
	$(function(){
		$("#TRC_INSTL_DT").datepicker();
	});

</script>

</head>
<body>
<div id = "body">
	<!-- TRC_INSR_YN 	TRC_INSR_COMP_NAME	TRC_OWNER	TRC_CONTACT	TRC_ROUTE_FROM	TRC_ROUTE_TO	TRC_INSTL_DT(date) -->
	<h3>Fill the details below</h3>
	<div>
		<form:form method="POST" action="submit_form"
			modelAttribute="new_truck_form">
			<input type="hidden" name="saveVar" value="T" />
			<pre>
			Truck Number :<form:input type="text" path="TRC_NO" id="TRC_NO" />
			<br>
			Truck Insurance Y/N :<form:radiobutton path="TRC_INSR_YN" value="Y" />Y<form:radiobutton
					path="TRC_INSR_YN" value="N" />N
								 
			<%-- <form:input type="text" path="TRC_INSR_YN" id="TRC_INSR_YN" /> --%>
			<br>
			Insurance Company name<form:input path="TRC_INSR_COMP_NAME"
					id="TRC_INSR_COMP_NAME" />
			<br>
			Truck Owner Name :<form:input type="text" path="TRC_OWNER"
					id="TRC_OWNER" />
			<br>
			Contact Number :<form:input type="text" path="TRC_CONTACT"
					id="TRC_CONTACT" />
			<br>
			Route From :<form:input type="text" path="TRC_ROUTE_FROM"
					id="TRC_ROUTE_FROM" />
			<br>
			Route To :<form:input type="text" path="TRC_ROUTE_TO"
					id="TRC_ROUTE_TO" />
			<br>
			Installed Date :<form:input type="text" path="TRC_INSTL_DT"
					id="TRC_INSTL_DT" />
			<br>
			Branch :<form:select path="branchdtl.BRN_ID">
				<c:forEach var="branchDTL" items="${brnNames}">
				<form:option value="${branchDTL.BRN_ID}"
							label="${branchDTL.BRN_ADDRESS}" />
				</c:forEach>
			</form:select>
			<br>
			<input type="submit" value="Submit">
			</pre>
		</form:form>
	</div>
</div>
<%@ include file="Footer.jsp"%>
</body>
</html>