import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Scanner;

public class Interact {
 
	static void Message(String msg) { 
        try { 
            HttpClient client = HttpClient.newHttpClient();
            String json = "{\"chat_id\":xXxXxXxXxX, \"text\":\"->"+ msg+".\"}";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.telegram.org/bot0123456789:AbCdEfG1234567_vv-012345678xXxXxXxX/sendMessage"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
          
            System.out.println("---> "+LocalDateTime.now().getHour()+":"+ LocalDateTime.now().getMinute()+"  | Telegram :" +msg); //+" | status: " + response.statusCode()
          //  System.out.println("Respuesta: " + response.body()); 
        } catch (Exception e) {
            System.out.println("Error al enviar:");
            e.printStackTrace();
        }
        
   
            }
        
        
    
   
		static void Publicador(String msg) {
            try {
                String path = "/opt/tomcat/webapps/ROOT/file.html"; 
                File file = new File(path);
                FileWriter writer = new FileWriter(file);
                writer.write(msg);
                writer.close();
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo:");
                e.printStackTrace();
            }

		}
static String Lector() {
    try {
        String path = "/opt/tomcat/webapps/ROOT/file.html";
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        if (scanner.hasNextLine()) {
            String primeraLinea = scanner.nextLine();
            scanner.close();
            return primeraLinea;
        }

        scanner.close();
    } catch (IOException e) {
        System.out.println("Error al leer el archivo:");
        e.printStackTrace();
    }
    return "";
}
        
    
		static void Sms(String msg,String cel) {
	        try {
	        	
	        	   String body = "BTCUSDT";		String to = "+39000000000";		String from = "+14155238886";
	        	   String json = "{\"to\":"+to+", \"from\":"+from+",\"body\":\"->"+ body+".\"}";
	               String url ="https://api.twilio.com/2010-04-01/Accounts/nnnnnnnnnnnnnnnnnnnnn/Messages.json";
	               String auth = "XXXXXXXXXXXXXXXXXXXXXX"+":"+"XXXXXXXXXXXXXXXXXXXXXXXX";
	               String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
	               
	            HttpClient client = HttpClient.newHttpClient();
	            
	            HttpRequest request = HttpRequest.newBuilder()
	            		 .uri(URI.create(url))
	            		.header("Content-Type", "application/json")
	            		.header("Authorization", "Basic"+auth)
	            		.POST(HttpRequest.BodyPublishers.ofString(json))
	                    .build();
	            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	            System.out.println(response);
	          } catch (Exception e) {
	            System.out.println("Error al enviar:");
	            e.printStackTrace();
	        }
		}
    
		
		public static void main(String[] args) throws Exception {
		
			//Debug zone
			
		}     
		

}
