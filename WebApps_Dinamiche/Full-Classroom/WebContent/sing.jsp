<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="tuf-8">
 <link rel='shortcut icon' type='image/png'  href='https://citnft.online/logo.png'   >
<title>Registro</title>
</head>
 <style>
 body {
    background-color: #EDEDEE;
    font-family: Helvetica;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
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
    font-size: 17px;
    border: none;
    padding: 5px;
    color: grey;
    border-radius: 5px;
    color: grey;
  }
 </style>
<body>
<form id ="form" action="SendMail" method="post">
  <table>
    <tr>
      <td>Nombre:</td>
      <td><input type="text" name="nombre" required></td>
    </tr>
    <tr>
      <td>Apellido:</td>
      <td><input type="text" name="apellido" required></td>
    </tr>
       <tr>
      <td>Contraseña:</td>
      <td><input type="text" name="password" required></td>
    </tr>
    <tr>
      <td>Correo electrónico:</td>
      <td><input type="email" name="correo" required></td>
    </tr>
	<tr><td><br><td></tr>

    <tr>
      <td>Datos del pago:</td>
    </tr>
    
     <tr>
     <td>Ricardo Nestor Ferreyra</td>
    </tr>
    
    <tr>
    <td>CUIL/CUIT: 20129814145</td>
    </tr>
    
    <tr>
      <td>CVU: 0000003100052442267691 </td>   
    </tr>
    
	<tr>
     <td>ALIAS: ricardo.fcolorista</td>
    </tr>
    
	<tr><td><br><td></tr>
	
    <tr>
    <td> <button  style="color:black; border-radius:5px;" type="submit">Registrarse</button></td>
    </tr>
    
	<tr><td><br><td/></tr>
	
	<tr>
      <td><a style=" padding:5px;color:black; border-radius:5px;background-color:#EDEDEE; text-decoration:none;" href="index.jsp" >Regresar</a></td>
    </tr>
    
  </table>
  

</form>
  
</body>
</html>