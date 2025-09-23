

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Form3
 */
@WebServlet("/Form3")
public class Form3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnsupportedEncodingException {
		response.setContentType("text/html");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
		    BufferedReader reader = request.getReader();
		    StringBuilder requestBody = new StringBuilder();
		    String line;
		    while ((line = reader.readLine()) != null) {
		        requestBody.append(line);
		    }
		    JsonNode jsonNode = objectMapper.readTree(requestBody.toString());
		    String inputp = jsonNode.get("inputp").asText();
		    //String inputp = jsonNode.get("inputp").toString(); 
		    response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html");
			writer.println("<html>");
			writer.println("<body>");
			writer.println("P O S T");
			writer.println("Valor ingresado: "+inputp);
			writer.println("</body>");
			writer.println("<html>");
			writer.close();
			System.out.println("El parametro recibido es: " + inputp);
		} catch (Exception e) {
		System.out.println("err");
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}
