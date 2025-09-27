<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%  session.invalidate(); %>
<title>Iniciar sesión</title>
 <link rel='shortcut icon' type='image/png'  href='https://citnft.online/logo.png'   >
</head>
 <style>

 
 	 body {
    flex-direction: column;
    background-color: #EDEDEE;
    font-family: helvetica;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0 auto;
  }

  #form {
    padding: 20px;
    background-color: #a7abb4;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
    opacity: 1;
    font-size: 19px;
    border-radius: 10px;
    color: black;
  }

  button {
    font-size: 16px;
    border: none;
    padding: 5px;
    color: grey;
    border-radius: 5px;
    color: grey;
  }

  #reg {
    text-align: center;
    background-color: white;
    padding: 7px;
    border-radius: 100px;
    text-decoration: none;
    color: blue;
    display: inline-block;
    margin-top: 100px;
    
 </style>
<body>
<form id ="form" action="Logged" method="post">
  <table>
	<tr>
	<td id="cannot"> </td><td id="out"> </td>
	</tr>   
    <tr>
      <td>Correo electrónico:</td>
      <td><input type="email" name="correo" ></td>
    </tr>
    
      <tr>
      <td>Contraseña:</td>
      <td><input type="text" name="password" required></td>
      </tr>
  </table>
  <button  style="color:black; border-radius:5px;" type="submit">Iniciar sesión</button> <br>

</form>
  <a id="reg" href="sing.jsp" >¿no tiene cuenta? Regístrese aquí</a>

</body>
</html>