<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Entitlement.jsp"%>

<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transport System</title>
<link rel="stylesheet" type="text/css" href="../resources/css/PageFormat.css">
<!-- complete jquery -->
<!-- userfull for ajax -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- Complete CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Complete JavaScript -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<!-- Complete JavaScript Bundle -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
<script>
	function getDateFormat(date){
		var d = new Date(date),
		day = ''+d.getDate(),month = ''+(d.getMonth()+1) , year = d.getFullYear();
		if(day.length < 2 ){
			day = '0'+day;
		};
		if(month.length < 2 ){
			month = '0'+month;
		};
		return [ day,month,year ].join('-');
		
	}
</script>
<style type="text/css">
body{
background-color: #C8C8C8;
}

td {
	border-style: double;
}
</style>

</head>
<body>
<div id = "body">
	<div>	
		<a href='javascript:showRouteTowardsDtl();'  style = "font-size:20px"><b>Towards Here</b></a>&emsp;&emsp;
		<a href = 'javascript:shwDtFrInstlTrck("#viewTruckDiv")'  style = "font-size:20px"><b>View Installed Truck</b></a>&emsp;&emsp;
		<a href = 'checkServiceView'  style = "font-size:20px"><b>Check Services</b></a>
	</div>
	<div class = "loader" style = "display:none;"></div>
<!-- View Installed Truck Start -->
	<div id = "viewTruckDiv" style = "display:none;" class = "text-centers">
	<form action = "viewInstlTruck" method = "GET">
		Start From : <input type = "text" name = "strtDtFrm" id = "strtDtFrm">&emsp;&emsp;
		To <input type = "text" name = "strtDtTo" id = "strtDtTo">&emsp;&emsp;
		<input type = "submit" value = "Find" >
	</form>
	</div>
	<div id = "truckDtlListDiv" style = "display:none;">
	<table class="table" >
				<thead class="thead-light">
					<tr style = "border-style:solid;">
						<th colspan = "6" class="text-center" style = "font-size:25px" >Details of Installed Truck</th>
					</tr>
					<tr style = "border-style:solid;">
						<th>Truck Number</th>
						<th>Model</th>
						<th>Route From</th>
						<th>Route To</th>
						<th>Truck Owner</th>
						<th>Installed Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${truckDtlList}" var = "truckDtl" varStatus = "temp">
						<%-- <c:set var = "modifiedDate"><script>getDateFormat("${truckDtl.TRC_INSTL_DT}");</script></c:set> --%>
						<tr>
							<td>${truckDtl.TRC_NO}</td>
							<td>${truckDtl.TRC_MODEL}</td>
							<td>${truckDtl.TRC_ROUTE_FROM}</td>
							<td>${truckDtl.TRC_ROUTE_TO}</td>
							<td>${truckDtl.TRC_OWNER}</td>
							<%-- <td>${truckDtl.TRC_INSTL_DT}</td> --%>
							<td><script>document.write(getDateFormat("${truckDtl.TRC_INSTL_DT}"))</script></td>
						</tr>
						<c:set var = "show" value = "true"></c:set>
					</c:forEach>
				</tbody>
			</table>
			<!-- <td><p id = ><script type="text/javascript">getDateFormat("${truckDtl.TRC_INSTL_DT}");</script></p></td> -->
		</div>
<!-- View Installed Truck End -->

<!-- Truck Details Towards here start-->
	<div class = "table-resopnsive" id = "truckDtlList" style = "display:none;overflow:auto;">
			<table class="table table-bordered">
				<thead class="thead-light" >
					<tr style = "border-style:solid;">
						<th colspan = "5" class="text-center" style = "font-size:25px">Details of Truck And Route</th>
					</tr>
					<tr style = "border-style:solid;">
						<th>Model</th>
						<th>Number</th>
						<th>Owner</th>
						<th>From</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody id = "truckDtlListBody">
				</tbody>
			</table>
			</div>
			
<!-- Truck Details Towards here End-->
	
	<div style = "display:none;" id = "chngStsDiv">
		Enter Current Status of Truck : <input type = "text" id = "RUN_STATUS" >
		<input type = "hidden" id = "chngStsHiddenField">
		<input type = "button" id = "submit" value = "submit" onclick="changeStatus('')">
		<span id = "failedMsg" style='color:red;display:none;'>Updation Failed</span>
	</div>
</div>


<!-- JavaScript Started -->
	<script type="text/javascript">
$(function() {
	$('#strtDtFrm').datepicker();
	$('#strtDtTo').datepicker();
}); 
$(function(){
	var check = "<c:out value='${show}'/>";
	if(check === "true")
		{
			$("#truckDtlListDiv").show();
		}
});

function shwDtFrInstlTrck(id){
	$("#truckDtlList").hide();
	$(id).show();
}

</script>
<script>
	function showRouteTowardsDtl(){
		$("#viewTruckDiv").hide();
		$("#truckDtlListDiv").hide();
		/* $("#truckDtlListDiv").empty(); */
		$("#failedMsg").hide();
	$.ajax({
			type : "GET",
			url : "routeDetails",
			dataType : "JSON",
			beforeSend: function() {
	              $(".loader").show();
	           },
			success : function(data) {
				$("#truckDtlListBody").empty();
				$(".loader").hide();
				$.each(data, function(runningStatus, truckDtlList) {
					 $("#truckDtlListBody").append(
							"<tr><td style='border-style: double;'><a href='javascript:changeStatus(\""
									+ truckDtlList.trc_ID + "\");'>"
									+ truckDtlList.trc_MODEL + "</a></td><td style='border-style: double;'>"
									+ truckDtlList.trc_NO + "</td><td style='border-style: double;'>"
									+ truckDtlList.trc_OWNER + "</td><td style='border-style: double;'>"
									+ truckDtlList.trc_ROUTE_TO + "</td><td style='border-style: double;'>" 
									+ runningStatus
									+ "</td></tr>"); 
									
				});
				$("#truckDtlList").show();
				
			}
		});

	}
</script>	
<script>
	function changeStatus(trcId){
		$("#failedMsg").hide();
		if($("#chngStsDiv").is(":hidden") || trcId != ""){
				$("#chngStsHiddenField").val(trcId);
				$("#chngStsDiv").show();
		}
		else{
				$.ajax({
				type : "GET",
				dataType : "text",
				url : "updateStatus",
				data : {
					"truckId" : $("#chngStsHiddenField").val(),
					"changedSts" : $("#RUN_STATUS").val()
				},
				success : function(data){
					console.log(data);					
					if(data === "Updated"){
						showRouteTowardsDtl();	
						$("#chngStsDiv").hide();
						$("#RUN_STATUS").val("");
					}
					else {
						$("#RUN_STATUS").val("");
						$("#failedMsg").show();
					}
				}
			});
		}
	}
</script>
<%@ include file="Footer.jsp"%>
</body>
</html>