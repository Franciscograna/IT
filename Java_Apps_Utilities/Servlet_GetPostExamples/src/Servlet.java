

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalStateException, UnsupportedOperationException {
		response.setContentType("text/html");
	try {
			PrintWriter writer = response.getWriter();
			writer.println("<html>");
			writer.println("<body>"+request.getParameter("parametro1"));
			writer.println("</body>");
			writer.println("<html>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Headers", "multipart/form-data");
		 response.setStatus(HttpServletResponse.SC_OK);
		 response.getWriter().write(" Solicitud procesada ");
		String  parametro1 = request.getParameter("parametro1");
		String 	parametro2 = request.getParameter("parametro2");
		String redirectUrl="";
		System.out.println( "parametro1: "+parametro1 +", parametro2: "+ parametro2  );
			try {
				 redirectUrl = "servlet.html?parametro1=" + URLEncoder.encode(parametro1, "UTF-8")//.jsp
				    + "&parametro2=" + URLEncoder.encode(parametro2, "UTF-8");
			} catch (Exception e) {
				System.out.println( "error enviando parametros 1 y 2 en url" );
				e.printStackTrace();
			}
		  response.sendRedirect(redirectUrl);
		doGet(request, response);
	}

}
