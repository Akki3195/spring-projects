<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Entitlement.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Truck</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/PageFormat.css">
<!-- complete jquery -->
<!-- userfull for ajax -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- Complete CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- Complete JavaScript -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<!-- Complete JavaScript Bundle -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
</head>
<body onload = "searchTruck()" style="background-color: #C8C8C8; ">
<div id = "body">
	<div>
		<form:form method = "POST" action = "searchByName" modelAttribute="truckDtl">
			Select Branch : <form:select path="branchdtl">
						<c:forEach var = "brnchDtl" items = "${brnNames}">
							<form:option value="${brnchDtl.BRN_ID}" label="${brnchDtl.BRN_ADDRESS}" />
						</c:forEach>
						</form:select>
						
			Find Truck : <input type = "text" id = "TRC_NO" /><br>
			
			<div class = "table-resopnsive table-borderless " id = "truckDtlTable" style = "display:none;">
			<table class="table">
				<thead class="thead-light" >
					<tr>
						<th colspan = "3" class="text-center">Details of Truck</th>
					</tr>
					<tr>
						<th>Truck Id</th>
						<th>Truck No</th>
						<th>Model No</th>
					</tr>
				</thead>
				<tbody id = "truckDtlTableBody">
				</tbody>
			</table>
			</div>
			<div class="table-responsive table-borderless" id = "showDetail" style = "display:none;">
			<table class = "table" id = "truckTable">
			 <thead class = "truckInfo" > 
				<tr id = "TRC_Number">
					<th>Truck No</th>
				</tr>
				<tr id = "TRC_OWNER">
					<th>Truck Owner Name</th>
				</tr>
				<tr id = "TRC_INSR_YN">
					<th>Insurance Y/N</th>
				</tr>
				<tr id = "TRC_INSR_COMP_NAME">
					<th>Insurance Company Name</th>
				</tr>
				<tr id = "TRC_CONTACT">
					<th>Contact Number</th>
				</tr>
				<tr id = "TRC_INSTL_DT">
					<th>Installation Date</th>
				</tr>
				<tr id = "TRC_ROUTE_FROM">
					<th>Truck Route From</th>
				</tr>
				<tr id = "TRC_ROUTE_TO">
					<th>Truck Route To</th>
				</tr>
			</thead> 
			<!-- <tbody id = "temp">
			
			</tbody> -->
			</table>
			</div>
		</form:form>
	
	</div>
</div>


<!-- Javascript Started -->
	<script>
		function searchTruck(){
		$.ajaxSetup({
				cache : false
			});
			$("#TRC_NO").autocomplete(
					{
						autoFocus : true,

						source : function(request, response) {
							/* var searchLetter = $("#TRC_NO").val(); */
							var searchLetter = request.term;
							$.ajax({
								url : "searchTruckByName?differ=L",
								type : 'POST',
								dataType : "JSON",
								data : {
									"letter" : request.term
								/* request object contains the value textbox */
								},
								success : function(data) {
									console.log(data);
									if (data != null && data.length > 0) {

										response($.map(data, function(item, i) {
											return {
												label : item.trc_NO,
												value : item.trc_NO,
												trcMODEL : item.trc_MODEL,
												trcID : item.trc_ID
											};

										}));
									}

									else {
										response([ {
											label : "NO RECORD FOUND."
										} ]);
									}
								},

							});
						},

						minLength : 4,
						select : function(event, ui) {

							$("#truckDtlTableBody").empty(); /* it delets all the child elements of selected tag  */

							$("#truckDtlTableBody").append(
									"<tr><td><a href='javascript:showTruckDetail(\""
											+ ui.item.value + "\");'>"
											+ ui.item.trcID + "</a></td><td>"
											+ ui.item.label + "</td><td>"
											+ ui.item.trcMODEL + "</tr>");

							$("#truckDtlTable").show();
							$("#showDetail").hide();
						}
					});
		}
	</script>
	<script>
	function getDateFormat(date) {
		var d = new Date(date), month = '' + (d.getMonth()), day = ''
				+ d.getDate(), year = d.getFullYear();
		var monName = [	"January", "February", "March",
		    			"April", "May", "June", "July",
		    			"August", "September", "October",
		    			"November", "December"
		    		  ];
		/* if (month.length < 2)    For getting month number
			month = '0' + month; */
		if (day.length < 2)
			day = '0' + day;
		if(day == '01'){
			day = day + '<sup>st</sup>';
		}
		else if(day == '02' ){
			day = day + '<sup>nd</sup>';
		}
		else if (day == '03'){
			day = day + '<sup>rd</sup>';
		}
		else 
			day = day + '<sup>th</sup>';
		/* var options = { weekday: "long", year: "numeric", month: "short", day: "numeric" };
		var options = {day : '2-digit',month : 'short',year : 'numeric'}; 
		var date = new Date();
		date.toLocaleDateString('en-GB', {day: 'numeric', month: 'short', year: 'numeric'}).replace(/ /g, '-'); 
		date.toLocaleDateString(options);
		return date; */
		 return [ day, monName[month], year ].join(' '); 
	};
</script>
<script>
	function showTruckDetail(a){
			$.ajax({
			type : "POST",
			url : "searchTruckByName?letter="+a+"&differ=S",
			dataType : "JSON",
			data : {},
			success : function(data) {
				$("thead.truckInfo > tr").find('th:eq(1)').remove();
				$.each(data, function(i, truckObj) {
					$("#TRC_Number").append("<th>"+truckObj.trc_NO+"</th>");  
				    $("#TRC_OWNER").append("<th>"+truckObj.trc_OWNER+"</th>");
					$("#TRC_INSR_YN").append("<th>"+truckObj.trc_INSR_YN+"</th>");
					$("#TRC_INSR_COMP_NAME").append("<th>"+truckObj.trc_INSR_COMP_NAME+"</th>");
					$("#TRC_CONTACT").append("<th>"+truckObj.trc_CONTACT+"</th>");
					$("#TRC_INSTL_DT").append("<th>"+getDateFormat(truckObj.trc_INSTL_DT)+"</th>");
					$("#TRC_ROUTE_FROM").append("<th>"+truckObj.trc_ROUTE_FROM+"</th>");
					$("#TRC_ROUTE_TO").append("<th>"+truckObj.trc_ROUTE_TO+"</th>"); 
				});
				 $("#showDetail").show(); 
			}
		});
	}
</script>
<%@ include file="Footer.jsp"%>
</body>
</html>