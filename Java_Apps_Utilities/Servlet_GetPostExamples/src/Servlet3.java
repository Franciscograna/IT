import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Servlet2
 */
@WebServlet("/Servlet3")
public class Servlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 //response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
		/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalStateException, UnsupportedOperationException {
		String redirectUrl = null;
		try {	
			
		//response.setHeader("Access-Control-Allow-Origin", "*");
		//response.setContentType("application/json");
		ObjectMapper objectMapper = new ObjectMapper();
		BufferedReader reader = request.getReader();
	    StringBuilder requestBody = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        requestBody.append(line);
	    }    
	    JsonNode jsonNode = objectMapper.readTree(requestBody.toString());
	    String parametro3 = jsonNode.get("parametro3").toString();
	    String parametro4 = jsonNode.get("parametro4").toString();	
	  
		 redirectUrl = "end.html?parametro3=" + URLEncoder.encode(parametro3, "UTF-8") +"&parametro4=" + URLEncoder.encode(parametro4, "UTF-8");
		System.out.println(redirectUrl );
		
			} catch (UnsupportedOperationException e) {
				System.out.println( "error enviando parametros 3 y 4 en url" );
				e.printStackTrace();
			} catch (IllegalStateException  e) {
				System.out.println( " enviando parametros 3 y 4 en url" );
				e.printStackTrace();
			}catch (IOException e) {
				System.out.println( " parametros 3 y 4 en url" );
				e.printStackTrace();
			}catch (Exception e) {
				System.out.println( "3 y 4 en url" );
				e.printStackTrace();
			}
		response.setContentType("text/html");response.getWriter().write(redirectUrl);
		//response.sendRedirect(redirectUrl);											//funcionaba en formularios
		doGet(request, response);	
	
	}
	

}
