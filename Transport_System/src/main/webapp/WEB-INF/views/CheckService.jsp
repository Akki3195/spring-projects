<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="Entitlement.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transport System</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/PageFormat.css">
<!-- complete jquery -->
<!-- userfull for ajax -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- Complete CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Complete JavaScript -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<!-- Complete JavaScript Bundle -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
<style type="text/css">
	
body {
	background-color: #C8C8C8;
}
#home{
padding-left : 30px;
}
</style>
</head>
<body>
<div id = "body">
<div class = "loader"></div>
<!-- 	<div>
		<a href='javascript:showRouteTowardsDtl();' style="font-size: 20px"><b>Towards
				Here</b></a>&emsp;&emsp; <a	
			href='javascript:shwDtFrInstlTrck("#viewTruckDiv")'
			style="font-size: 20px"><b>View Installed Truck</b></a>&emsp;&emsp; <a
			href='checkServiceView' style="font-size: 20px"><b>Check
				Services</b></a>
	</div> -->
	<div>
		<a href='${pageContext.request.contextPath}/web/manager/checkService'  style = "font-size:20px"><b id = "home">Back</b></a>
	</div>
	<br>
	<div style="text-align: center;">
		<form:form action="${pageContext.request.contextPath}/web/manager/checkService/0" modelAttribute="truckDtl"
			method="GET">
		Route From : <form:input path="TRC_ROUTE_FROM" id="TRC_ROUTE_FROM" /> &emsp;&emsp; To: <form:input
				path="TRC_ROUTE_TO" id="TRC_ROUTE_FROM" />
			&emsp;&emsp;
			<input type="submit" value="Find">
		</form:form>
	</div>
	<br>
	<table
		class="table table-striped table-bordered table-hover table-condensed"
		id="truckListTable" style="display: none;">
		<thead class="thead-light">
			<tr>
				<th>Model</th>
				<th>Truck No.</th>
				<th>Owner</th>
				<th>Mobile</th>
				<th>Insurance</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${truckDtlList}" var="truckObj">
				<tr class="table-info">
					<td>${truckObj.TRC_MODEL}</td>
					<td>${truckObj.TRC_NO}</td>
					<td>${truckObj.TRC_OWNER}</td>
					<td>${truckObj.TRC_CONTACT}</td>
					<c:choose>
						<c:when test="${truckObj.TRC_INSR_YN eq 'Y'}">
							<td>${truckObj.TRC_INSR_COMP_NAME}</td>
						</c:when>
						<c:when test="${truckObj.TRC_INSR_YN eq 'N'} ">
							<td></td>
						</c:when>
					</c:choose>
					<c:set var="display" value="true"></c:set>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- Pagination links in spring mvc. -->
		<div style = "text-align:center;">
		<ul class="pagination pagination-sm center">
			<c:forEach var="count" begin="1" end="${totalNoOfPages}">
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/web/manager/checkService/${count}"><c:out
							value="${count}" /></a></li>
			</c:forEach>
		</ul>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			var dis = "<c:out value='${display}'/>";
			if (dis === "true") {
				$("#truckListTable").show();
			}
		});
	</script>
	<!-- To send post  request using href anchor tag 
	 <script>
	$(function() {
		  $("#home").on("click",function(e) {
		    e.preventDefault(); // cancel the link itself
		    $.post('loginSuccessfull',function(data) {
		    	console.log(data);
		    	/* $("body").remove(); */
		        $(this).html(data);
		    }); 
		  });
		});
	
	
	</script> -->
	<script>
		$(document).ready(function(){
			hideLoader();
		});
		function hideLoader(){
			$(".loader").hide()	;
		}
	</script>
	
</body>
<%@ include file="Footer.jsp"%>
</html>