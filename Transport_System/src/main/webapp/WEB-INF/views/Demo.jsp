<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<TITLE>Spring MVC Example with AJAX call</TITLE>
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script> -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!-- <script type="text/javascript">
    function crunchifyAjax() {
        $.ajax({
            url : 'ajaxtest',
            success : function(data) {
                $('#result').html(data);
            }
        });
    }
</script> -->
 
<script type="text/javascript">
    var intervalId = 0;
    intervalId = setInterval(crunchifyAjax, 3000);
</script>
</head>
<body>
<div align="center">
        <br> <br> ${message} <br> <br>
        <div id="result"></div>
        <br>
        <p>
            by <a href="https://crunchify.com">Crunchify.com</a>
        </p>
    </div>
</body>
</html> --%>