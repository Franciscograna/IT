

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
 * Servlet implementation class DemoP
 */
@WebServlet("/DemoP")
public class DemoP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalStateException, UnsupportedOperationException {
		response.setContentType("text/html");
		 request.authenticate(response);
		 request.toString();
		 request.getQueryString();
		 response.setHeader("Access-Control-Allow-Headers", "multipart/form-data");
		 String inputp = null;
		 String redirectUrl="";
		try {
			
			 inputp= request.getParameter("inputp") ;System.out.println(inputp );
			
			if (inputp == null){
				ObjectMapper objectMapper = new ObjectMapper();
			    BufferedReader reader = request.getReader();
			    StringBuilder requestBody = new StringBuilder();
			    String line;
			    while ((line = reader.readLine()) != null) {
			        requestBody.append(line);
			    }
			    JsonNode jsonNode = objectMapper.readTree(requestBody.toString());
			     inputp = jsonNode.get("inputp").toString();
			     
			};System.out.println(inputp );
			response.setContentType("text/html");
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html");
			writer.println("<html>");
			writer.println("<body>");
			writer.println("P O S T");
			writer.println("Valor POST ingresado,  : "+inputp);
			writer.println("</body>");
			writer.println("<html>");
	
			
			redirectUrl = "end.html?inputp=" +  inputp;
			 response.sendRedirect(redirectUrl);
			 
		} catch (Exception e) {
			System.out.println( "error enviando parametro inputP" );
			e.printStackTrace();
		} 
		doGet(request, response);
	}

}
