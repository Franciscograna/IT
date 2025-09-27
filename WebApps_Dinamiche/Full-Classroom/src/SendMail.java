
import java.io.IOException;// envia texto y mail// probar el que adjunta
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





/**
 * Servlet implementation class SendMail
 */
@WebServlet("/SendMail")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//----------------formulario post---------------------//
			  String ok = "incorrectamente";
			  String nombre   = request.getParameter("nombre");
			  String apellido = request.getParameter("apellido");
			  
			  String email   = request.getParameter("correo");
			  String secret   = request.getParameter("password");
			  
			  String pago="0";
			  
			
			  
			// Configurar propiedades del servidor SMTP de Gmail
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.ssl.trust", "*");

				// Nombre de usuario y contrase�a de Gmail  // Del due�o
				String username = "franciscograna@gmail.com";
				String password = "fmyayjsbkzvwjylx";

				// Crear una sesi�n de correo
				Session session = Session.getInstance(props, new Authenticator() {
				    protected PasswordAuthentication getPasswordAuthentication() {
				        return new PasswordAuthentication(username, password);
				    }
				});

				try {
				    // Crear el mensaje imitar esto en las otras mail app
				    Message message = new MimeMessage(session);	   
				    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email +", francisco_grana@hotmail.com" ));
				    message.setSubject("Hola desde CIT - Curso: Colorimetr�a profesional para el Cabello");
				    message.setContent("<!DOCTYPE html>"+
										"<html>"+
										"<head>"+
										"<meta charset='UTF-8'>"+
										"</head>"+
										"<body style='padding: 30px; background-color: #EDEDEE;'><div style='"+//habia error en este div
										  "      margin: 0 auto; max-width: 400px;"+
										  "      background-color: #EDEDEE;"+
										  "      font-size: 12px;" + 
											"    font-family: monospace;" + 
											"    border-radius: 10px;" + 
											"    background-color: grey;" + 
											"    padding: 20px;" + 
											"    color: #EDEDEE;" + 
											"    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);" + 
										 	 "'>"+
									
										"  Hemos creado su usuario con los siguientes datos: <br>"+
										"  Nombre: "+nombre+", <br> Apellido: "+apellido+", <br> Email: "+email+". <br>"+
										"  Si recibi� este mensaje, se ha registrado correctamente, <br>"+
										"  aguarde unos instantes para que le otorgemos acceso a la plataforma."+
										"  <br>Si ya realiz� el pago, pronto recibir� un segundo mail de confirmaci�n, <br>"+
										"  si todav�a no realiz� el pago recuerde abonar "
										+ "a la siguiente cuenta  <br><br>" + 
										"     Ricardo Nestor Ferreyra<br>" + 
										"    CUIL/CUIT: 20129814145</td>" + 
										"      CVU: 0000003100052442267691 <br>  " + 
										"     ALIAS: ricardo.fcolorista<br>" + 
										"  <div style='"
										+   "margin-top: 10px;" + 
										"    margin-bottom: 0;" + 
										"    text-align: left;"	
										+ "'>"+
										"    <img width='30' height='30' src='https://citnft.online/logo.png' alt='Logo'>"+
										"    <br>CIT."+
										"</div></div>"+
									
										"</body>"+
										"</html>","text/html; charset=UTF-8" );
				    		
				    		
				    		
				    // Enviar el mensaje
				    Transport.send(message);
				    try {
						Class.forName("org.sqlite.JDBC"); // default: com.mysql.cj.jdbc.Driver
						Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/franci/Documents/file-db/usersdb.sqlite3");	//  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "xxxx");										
						String insertQuery = "INSERT INTO users (email, secret, nombre, apellido,pago) VALUES (?, ?, ?, ? , ?)";
						PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
						preparedStatement.setString(1, email);
						preparedStatement.setString(2, secret);
						preparedStatement.setString(3, nombre);
						preparedStatement.setString(4, apellido);
						preparedStatement.setString(5, pago);
					
						
						
						preparedStatement.executeUpdate();
						preparedStatement.close();
						connection.close();
						ok="correctamente";
						System.out.println("datos ingresados");
						
					} catch (ClassNotFoundException e) {
						System.out.println("Problema de clase en sql");
						e.printStackTrace();
					} catch (SQLException e) {
						System.out.println("Problema sql");
						e.printStackTrace();
					}
				
				    PrintWriter writer = response.getWriter();
					response.setContentType("text/html");		
					    writer.println("<html>");
					    writer.println("<head>");
					    writer.println(" <link rel='shortcut icon' type='image/png'  href='https://citnft.online/logo.png'   >");
					    writer.println("<title>Colorimetr�a Para el Cabello</title>");
					    writer.println("<style>");
					    writer.println("#container {");
					    writer.println("    width: 600px;");
					    writer.println("    height: 820px;");
					    writer.println("    background-color: #eeeff0;");
					    writer.println("    color: #050000;");
					    writer.println("    border: 1px solid #cdcfd4; border-radius:15px;");
					    writer.println("    padding: 10px;");
					    writer.println("    text-align: center;");
					    writer.println("    margin: auto;");
					    writer.println("    width: 50%;");
					    writer.println("    margin-top: 50px;");
					    writer.println("    font-family: 'Lato', sans-serif;");
					    writer.println("}");
					    writer.println("</style>");
					    writer.println("</head>");
					    writer.println("<body>");   
						//*************************************************************************************//
						writer.println("<div id='container'>	 ");	
						writer.println("<h3>Se ha registrado , compruebe su casilla de correo electr�nico</h3><br>  <br> ");
						writer.println("</div>   ");
						//**************************************************************************************//
					    writer.println("</body>");
					    writer.println("</html>");		
					writer.close();
				
			  		  
			     			System.out.println("El correo se ha enviado "+ok+", esperando que sea verificado");
							} catch (MessagingException e) {
							    e.printStackTrace();
							}
						  
		
		doGet(request, response);
	}

}
