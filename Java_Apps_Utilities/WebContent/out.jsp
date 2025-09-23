<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logged out</title>

<link href="login.css" rel="stylesheet" type="text/css">
</head>
<body>
  <div class="container">

<% session.removeAttribute("unn"); session.invalidate(); %>

<a href=index.jsp>loggin again</a></div>
</body>
</html>