import java.io.IOException;//
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForumServlet
 * @param <httpSession>
 */

@WebServlet("/ForumServlet")
public class ForumServlet<httpSession> extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
        
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	 resp.setContentType("text/html;charset=UTF-8");

         PrintWriter out = resp.getWriter();
         out.println("<html><body>");
         out.println("<h1>Messages</h1>");

				        try {
				        	 String url="jdbc:sqlite:/home/franci/Documents/file-db/foro.sqlite3";
				        		Class.forName("org.sqlite.JDBC");
				        		Connection con=DriverManager.getConnection(url);
				        	String sql = "SELECT * FROM messages";
				            Statement st = con.createStatement();
				            ResultSet rs = st.executeQuery(sql);
				            String user = rs.getString("username");
			                String msg = rs.getString("message");
			                String fecha = rs.getString("created_at");
			                
			                out.println("<p><b>" + user + "</b> (" + fecha + "):<br/>" + msg + "</p><hr>");
				            while (rs.next()&&rs!=null&&con!=null) {
				                 user = rs.getString("username");
				                 msg = rs.getString("message");
				                 fecha = rs.getString("created_at");
				                
				                out.println("<p><b>" + user + "</b> (" + fecha + "):<br/>" + msg + "</p><hr>");
				            }
				        } catch (SQLException e) {
				            throw new ServletException("Error consultando la BD", e);
				        } catch (ClassNotFoundException e) {
				        	 throw new ServletException("Error de Clase");
							
						}
        out.println("<form method='post' action='ForumServlet'>");
        out.println("<h2>New Message</h2>  ");
        out.println("User:<input type='text' name='username'/><br/>");
        out.println("Message:<textarea name='message'></textarea><br/>");
        out.println("<input type='submit' value='Send'/> ");
        out.println("</form>");
        out.println("</body> </html>");
       
  							}
    @Override
    protected void doPost(HttpServletRequest req2, HttpServletResponse resp2) throws ServletException, IOException {
        resp2.setContentType("text/html;charset=UTF-8");
        
        String username = req2.getParameter("username");
        String message = req2.getParameter("message");
       
        try { 
        	 String url="jdbc:sqlite:/home/franci/Documents/file-db/foro.sqlite3";
        	Class.forName("org.sqlite.JDBC");
        	Connection con=DriverManager.getConnection(url);
            String sql = "INSERT INTO messages (username, message) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, message);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error al insertar en la BD", e);
        }
        resp2.sendRedirect("index.html");
                
    }  
}
