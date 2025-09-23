import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class Demo
 */
@WebServlet("/DemoG")
public class DemoG extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter writer;
		try {
			
			writer = response.getWriter();
			response.setContentType("text/html");
			writer.println("<html>");
			writer.println("<head> <link href=\"index.css\" rel=\"stylesheet\" type=\"text/css\">");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<br>");
			writer.println("G E T");
			if (request.getParameter("inputg")==null) {
				String inputg = null;
				
				writer.println("Valor ingresado es: "+ inputg);	
			}else {
			writer.println("Valor ingresado es: "+request.getParameter("inputg"));
			}
			writer.println("</body>");
			writer.println("<html>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}


}
