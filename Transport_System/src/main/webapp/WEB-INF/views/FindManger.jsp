<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="Entitlement.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Manager</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/PageFormat.css">
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
<body onload = "search_manager()">
<div id = "body">	
	<div class="container-fluid">
		<form:form method="GET" action="searchByName" modelAttribute="mgrDtl">
			Select Branch: <form:select path="branchdtl">
				<%-- <form:option value="NONE" label="--- Select ---"/> --%>
				<c:forEach var="branchDTL" items="${brnNames}">
					<form:option value="${branchDTL.BRN_ID}"
						label="${branchDTL.BRN_ADDRESS}" />
				</c:forEach>
			</form:select>
			<br>
			Find Manager : <input type="text" id="searchManager" /><br>
			<a href = "javascript:showAllManager()">Click Here</a> for List of All Manager
			<br>
			<div class = "table-resopnsive table-borderless " id = "managerDtl" style = "display:none;">
			<table class="table">
				<thead class="thead-light" >
					<tr>
						<th colspan = "3" class="text-center">Details of Manager</th>
					</tr>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Salary</th>
					</tr>
				</thead>
				<tbody id = "managerDtlTable">
				</tbody>
			</table>
			</div>
			<div class="table-responsive table-borderless" id = "showDetail" style = "display:none;">
			<table class = "table">
			<thead>
				<tr>
					<th>ID</th>
					<th>User Name</th>
					<th>Date of Joining</th>
					<th>Date of Birth</th>
					<th>Salary</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody id="managerDtlRow">
			
			</tbody>
			</table>
			</div>
		</form:form>
	</div>
</div>
	
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
	function search_manager() {
		
		$.ajaxSetup({
			cache : true
		});
		$("#searchManager").autocomplete(
				{

					autoFocus : true,

					source : function(request, response) {
						var manager_name_letter = $("#searchManager").val();
						$.ajax({
							url : "searchByName?manager_name_letter="+manager_name_letter+"&urlParam=",
							dataType : "JSON",
							type : 'GET',
							data : {

							},
							/* data: {
							   term: request.term 
							  searchLetter : $('#searchManager').val()
							}, */
							success : function(data) {
								/* console.log(response);
								response.forEach(function(data) {
								    $("#log").append("<li>" + "NAME" + ":" + data.mgr_NAME + "</li>");
								 }); */
								if (data != null && data.length > 0) {

									response($.map(data, function(item, i) {
										return {
											/* These are passed to select function after clicking on value */
											label : item.mgr_NAME,
											value : item.mgr_NAME,
											mgrId : item.mgr_ID,
											idProof : item.mgr_ID_PROOF,
											dob : item.mgr_DOB,
											doj : item.mgr_JOIN_DT,
											userId : item.mgr_USER_ID,
											salary : item.mgr_SALARY
										};
									}));
								} else {
									response([ {
										label : "No Records Found."
									} ]);
								}
							}
						});
					},
					minLength : 2,
					select : function(event, ui) {
							var dob = getDateFormat(ui.item.dob);
							var doj = getDateFormat(ui.item.doj);
							$("#showDetail").hide();
							
							$("#managerDtlTable").empty();	/* it delets all the child elements of selected tag */
							
							$("#managerDtlTable").append("<tr><td><a href='javascript:showDetail(\""+ui.item.mgrId+"\");'>"+ui.item.mgrId+"</a></td><td>"+ui.item.value+"</td><td>"+ui.item.salary+"</tr>");
							
							$("#managerDtl").show();
					}
				});
	}
</script>
<script>
function showAllManager(){
	/* $('a').click(function(e){ */
		/* e.preventDefault(); */
		$.ajax({
			type : "GET",
			url : "searchByName?manager_name_letter=&urlParam=A",
			dataType : "JSON",
			data : {
			},
			success : function(data){	
				console.log(data);
				$("#managerDtlTable").empty();
				$.each(data , function(i , managerDtl){
					$("#managerDtlTable").append("<tr><td><a href='javascript:showDetail(\""+managerDtl.mgr_ID+"\");'>"+managerDtl.mgr_ID+"</a></td><td>"+managerDtl.mgr_NAME+"</td><td>"+managerDtl.mgr_SALARY+"</td></tr>"); 
				});
				$("#managerDtl").show();
			}
		});
	/* }); */
}
</script>
<script>
	function showDetail(a){
		/* var temp = $(this).val; */
		
			$.ajax({
			type : "GET",
			url : "searchByName?manager_name_letter="+a+"&urlParam=S",
			dataType : "JSON",
			data : {},
			success : function(data) {
				console.log(data);
				$.each(data, function(i, mgrDtl) {
					$("#managerDtlRow").empty();
					
					$("#managerDtlRow").append(
							"<tr><td>" + mgrDtl.mgr_ID + "</td><td>"
									+ mgrDtl.mgr_NAME + "</td><td>"
									+ getDateFormat(mgrDtl.mgr_JOIN_DT) + "</td><td>" + getDateFormat(mgrDtl.mgr_DOB)
									+ "</td><td>" + mgrDtl.mgr_SALARY + "</td><td>"
									+ "<a href = 'javascript:deletManager(\""+mgrDtl.mgr_ID+"\");'>delete</a>"
									+ "</td><tr>")
				});
				$("#showDetail").show();
			}
		});
	}
</script>
<script>
	function deletManager(mgrId){
		$.ajax({
			type : "POST",
			url : "deleteManager",
			dataType : "JSON",
			data : {
				mgrId : mgrId
			},
			success : function(data){
				
				if(data)
				{
					window.alert("Manager Record Deleted");
					$("#showDetail").hide();
					$("#managerDtlRow").empty();
					showAllManager();
				}
				else 
					{ 
						window.alert("error while deleting manager");
					}
			}
		});
	}
</script>
<%@ include file="Footer.jsp"%>
</body>
</html>