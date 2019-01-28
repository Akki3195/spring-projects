

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/PageFormat.css">
<title>Home Page</title>

</head>
<body>
<div id = "body">
	<div style="backbackground: gray;">
		<marquee direction="left">
			<h1 style="color: green;">MOST CONVENIENT AND RELIABLE TRANSPORT
				SERVICE</h1>
		</marquee>
	</div>
		<img src="resources/image/TransportTruck.jpg" alt="transport_Image"
			height="300px" width="100%" /><br><br>
	<div style="position: relative; width: 100%; height: 25px">
		<div style="position: absolute; left: 0; width: 40%">
			<marquee behavior="slide" direction="right" scrollamount="30">
				<a style="color: blue;font-size: 20px" href="admin/adLogin">Admin Login</a>
			</marquee>
		</div>
		<div style="position: absolute; right: 0; width: 40%">
			<marquee behavior="slide" direction="left" scrollamount="30">
				<a style="color: blue;font-size: 20px" href="manager/mgrLogin">Manager Login</a>
			</marquee>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/Footer.jsp"%>
</body>
</html>