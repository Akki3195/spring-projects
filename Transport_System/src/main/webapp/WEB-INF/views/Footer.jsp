<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<meta charset="UTF-8">
<style>
.footer {
   position: relative;
   height:150px;	
   left: 0;
   bottom: 0px;
   width: 100%;
   background-color: red;
   color: white;
   text-align: center;
}

</style>
</head>
<body>
	<div class="footer">
		<p>
			<a href="${pageContext.request.contextPath}/index.jsp">Home</a><span> | </span><a href="#">About
				Us</a><span> | </span><a href="#">Contact Us</a><span>
				| </span><a href="#">Testimonials</a><span> | </span><a
				href="#">Donate</a>
		</p>
		<p>
			While using this site, you agree to have read and accepted our <a
				href="/terms_of_service">Terms of Service</a> and <a
				href="/privacy">Privacy Policy</a>.
		</p>
		<p>We use advertisements to support this website and fund the
			development of new content.</p>
		<p>
			<a href="/copyright">Copyright</a> &copy; 2017-2018
			TransportSystem.com All rights reserved.
		</p>
	</div>
</body>
</html>