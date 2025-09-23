import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	static void Metho(String msg, String correo) {
	 
	
	  
	try {
		// Configurar propiedades del servidor SMTP de Gmail
			Properties props = new Properties();
			props. put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.ssl.trust", "*");

			// Nombre de usuario y contrase�a de Gmail  //okay
			String username = "franciscograna@gmail.com";
			String password = "xxxxxxxxxxx";

			// Crear una sesi�n de correo
			Session session = Session.getInstance(props, new Authenticator() {
			    protected PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication(username, password);
			    }
			});

			    // Crear el mensaje
			    Message message = new MimeMessage(session);
			   
			    
			    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo +", itribunales@gmail.com"+", francisco_grana@hotmail.com"+", fargettasandra@gmail.com" ));
			    message.setSubject("Ciao da Java");
			    message.setContent(msg,"text/html;charset=UTF-8");

			    // Enviar el mensaje
			    Transport.send(message);
	} catch (AddressException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	  
	
	  
	}
			public static void main(String[] args) {
				
				String html= "									<!DOCTYPE html>								\n"
						+ "										<html style='background-color: #EDEDEE;'>	\n"
						+ "										<head>										\n"
						+ "										<meta charset='UTF-8'>						\n"
						+ "										</head>										\n"
						+ "										<body style='								\n"
						+ "										        margin: 20px; margin-left: 20px;	\n"
						+ "										        background-color: #EDEDEE;			\n"
						+ "										        display: flex;				 \n"
						+ "										        font-size: 20px;  					\n"
						+ "											    font-family: monospace;  			\n"
						+ "											    border-radius: 10px;  				\n"
						+ "											    background-color: grey;  			\n"
						+ "											    padding: 20px;  					\n"
						+ "											    color: #EDEDEE;  					\n"
						+ "											    text-align: left;  					\n"
						+ "											    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);  \n"
						+ "											    flex-direction:column;  					\n"
						+ "											    align-items: flex-start;					\n"
						+ "																	 	 '>												\n"
						
						+ "										  Hemos creado su usuario con los siguientes datos: <br>  \n"
						+ "										  Nombre: n, <br> Apellido: a, <br> Email: @. <br>				\n"
						+ "										  Si recibió este mensaje, se ha registrado correctamente, <br>	\n"
						+ "										  aguarde unos instantes para que le otorgemos acceso a la plataforma.	\n"
						+ "										  <br>										\n"
						+ "										  <div style='									\n"
						+ "											margin-top: 10px; 							\n"
						+ "										    margin-bottom: 0; 							\n"
						+ "										    text-align: left;width:100%; 							\n"
						+ "											'></div></body>													\n"
						+ "										    <img width='30' height='30' src='logo.png' alt='Logo'>	\n"
						+ "										    <br> Azienda.												\n"
					
						+ "										</html>									\n";
				
				
				Metho(html,"frangra273@gmail.com");
				
			} 		
				  


}
