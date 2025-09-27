

import java.io.IOException;
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
 * Servlet implementation class altabaja
 */
@WebServlet("/altabaja")
public class altabaja extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	  /**
     * @see HttpServlet#HttpServlet()
     */
    public altabaja() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			String parametroa="";
			String parametrob="";
			String parametroc="";
			String parametrod="";
			String parametroe="";
			
				 parametroa = request.getParameter("parametroa");
				 parametrob = request.getParameter("parametrob");
				 parametroc = request.getParameter("parametroc");
				 parametrod = request.getParameter("parametrod");
				 parametroe = request.getParameter("parametroe");
			
			System.out.println(parametroa);
			
			
			
			 try {
				 
				 
				 
				 	Class.forName("org.sqlite.JDBC");
				 	Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/franci/Documents/file-db/usersdb.sqlite3");	// default: Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "xxxx!");										
					String updateQuery = "UPDATE users SET pago = ? WHERE email = ?";
				    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

				    preparedStatement.setString(1, parametroe); // Establecer el nuevo valor de pago
				    preparedStatement.setString(2, parametroa); // Condici�n basada en el email

				    int rowsUpdated = preparedStatement.executeUpdate();

				    preparedStatement.close();
				    connection.close();

				    if (rowsUpdated > 0) {
				        System.out.println("Fila actualizada exitosamente.");
				    } else {
				        System.out.println("No se encontr� la fila para actualizar.");
				    }
		
				
					
					
				 } catch (ClassNotFoundException e) {
					System.out.println("Problema de clase en sql");
					e.printStackTrace();
				  } catch (SQLException e) {
					System.out.println("Problema sql");
					e.printStackTrace();
				   }
			
			
			 response.sendRedirect("out.jsp");	
		//doGet(request, response);
	}

}
