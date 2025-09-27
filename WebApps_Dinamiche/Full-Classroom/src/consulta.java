

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class consulta
 */
@WebServlet("/consulta")
public class consulta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int count=0;
		
		  String nombre     = request.getParameter("parametro1");
		  String apellido = request.getParameter("parametro2");
		  String email     = request.getParameter("parametro3");
		  String mensaje = request.getParameter("parametro4");
		  
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
					message.setSubject("Consulta Realizada - Curso: Colorimetr�a profesional para peluquer�as");
					message.setContent("<!DOCTYPE html>"+
										"<html;>"+
										"<head>"+
										"<meta charset='UTF-8'>"+
										"</head>"+
										"<body  style='background-color: #EDEDEE'><div style='margin-top: 20px;" + 
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
										"  Nombre: "+nombre+", <br> Apellido: "+apellido+", <br> Email: "+email+". <br>"+
										"  Mensaje: "+mensaje+" <br>"+
										"  <br><br>"+
										"  <div style='margin-top: 10px;" + 
										"    margin-bottom: 0;" + 
										"    text-align: left;'>"+
										"    <img width='30' height='30' src='logo.png' alt='Logo'>"+
										"    <br>CIT."+
										"</div></div>"+	
										"</body>"+
										"</html>", "text/html; charset=UTF-8" );
							
							
							
					// Enviar el mensaje
					Transport.send(message);
					count++;
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		  
		  
		  
		  
		  
		  
		  
		  
		  
		   try {
		    	PrintWriter writer = response.getWriter();
				response.setContentType("text/html");
				String web1 ="<!DOCTYPE html>" + 
						"<html>" + 
						"<head>" + 
						"<meta charset='ISO-8859-1'>" + 
						" <link rel='shortcut icon' type='image/png'  href='https://citnft.online/logo.png'   >"+
						"<title>Mensaje Enviado</title>" + 
						"</head>" + 
						"<body style='font-family: monospace;' >";
				writer.println(web1);
				String webx =""+
						

											
						"<script type='text/javascript'> const chatId = xxxxxx; "+ 	
									"string param1 = '"+nombre+"';"+
								    "string param2 = '"+apellido+"';"+
								    "string param3 = '"+email+"';"+
								    "string param4 = '"+mensaje+"';"+
						
						"	 const data = {"+
						"		      chat_id: chatId,"+
						"		      text:param1  +', '+ param2 + ', ' + param3  + ', ' + param4};" +			    
						"		      fetch('https://api.telegram.org/bot01234567890:abcdefghijklmnopqrstuvwxyz_0123456789/sendMessage', {" +
						"		      method: 'POST',"+
						"		      headers: {"+
						"		        'Content-Type': 'application/json'"+
						"	      },"+
						"		      body: JSON.stringify(data)"+
						"		    })"
						
						 +"</script> Hemos recibido su consulta y Pronto le responderemos<br><br><br>"; 
							
				writer.println(webx);
				
				String web3 ="<div><a  href='app'>volver al aula</a></div>   </body></html>";
				writer.println(web3);
				count++;
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		
		if (count<2) {System.out.println("puede que el mensaje no se este enviado");}
		
		doGet(request, response);
	}

}
