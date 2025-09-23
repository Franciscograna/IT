import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Demo
 */
@WebServlet("/GetRedirect")
public class GetRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalStateException, UnsupportedOperationException {
			response.setContentType("text/html");
			String redirectUrl="";
			try {
				String inputg= request.getParameter("inputg");
				System.out.println(inputg);
				 redirectUrl = "end.html?inputg=" +  URLEncoder.encode(inputg, "UTF-8");
				 
			} catch (Exception e) {
				System.out.println( "error enviando parametro inputg" );
				e.printStackTrace();
			} 
		
			response.sendRedirect(redirectUrl);
	}


}
