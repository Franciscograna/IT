<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logged</title>
<link href="login.css" rel="stylesheet" type="text/css">
</head>
<body>

<h2>Benvenuto!</h2>
  <div class="container">
   
    <div class="card">
<% if (session.getAttribute("unn")==null){
response.sendRedirect("index.jsp");		
	}	
%>
<%= session.getAttribute("unn") %>
<br>
<%= session.getAttribute("mensaje") %>
 

</div>
<form id ="form" action="out.jsp" method="post">
<input   type="submit" value="out">
</form>
</div>
<script>

var mensaje = '<%= session.getAttribute("mensaje") %>';
console.log(mensaje);
</script>
</body>

</html>