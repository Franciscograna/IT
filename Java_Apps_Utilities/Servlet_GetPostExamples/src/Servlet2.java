import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
		/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalStateException, UnsupportedOperationException {
		try {	
			
		response.setHeader("Access-Control-Allow-Origin", "*");
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
	    try {
	    	response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html");	
			writer.println("<html>");
			writer.println("<body>");
			writer.println("P O S T");
			writer.println("Valor POst ingresado: "+parametro3+" "+parametro4);
			writer.println("</body>");
			writer.println("<html>");
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		System.out.println("parametro3: "+ parametro3+", parametro4: "+ parametro4  );		
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
		doGet(request, response);	
	}
	

}
