import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Input
 * @param <httpSession>
 */
@WebServlet("/Input")
public class Input<httpSession> extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hola="Hai entrato corretamente al nostro server";
		String n   = request.getParameter("correo");
		String pwd   = request.getParameter("password");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		
		if((n.equals("fr@g.c"))&&(pwd.equals("key"))) {
			
			HttpSession hs = request.getSession();
			hs.setAttribute("mensaje", hola);
			hs.setAttribute("unn",n);
			response.sendRedirect("logged.jsp");
			
			try {
				
			} catch (Exception e) {
				out.println("Sorry no parameter");
				e.printStackTrace();
			}
			
		}
		else {
			out.println("<div class='card' class='container'>Sorry, not found</div>");
			//response.sendRedirect("index.html");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}	
		doGet(request, response);
	}

}
