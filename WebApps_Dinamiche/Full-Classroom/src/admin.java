import java.io.IOException;//
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("u").append(request.getContextPath());
	}
   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String secret = request.getParameter("secret");
		
		 if (email.equals("admin@admin.admin") && secret.equals("admin")) { 
			 System.out.println("in admin");
			 
			
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection2 = DriverManager.getConnection("jdbc:sqlite:/home/franci/Documents/file-db/usersdb.sqlite3");	// default: Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "xxxxxx");										
			String insertQuery2 = "INSERT INTO users (email, secret, nombre, apellido, pago) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection2.prepareStatement(insertQuery2);
			//preparedStatement.close();
					
			
			  
			Statement statement2 = connection2.createStatement();
			String query2 = "SELECT * FROM users";
			ResultSet resultSet = statement2.executeQuery(query2);					    
			  
			
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html");
			String web1 ="<!DOCTYPE html>" + 
					"<html>" + 
					"<head>" + 
					"<meta charset='ISO-8859-1'>" + 
					"<title>ADMINISTRADOR</title>" + 
					"</head>" + 
					"<body>";
					
			writer.println(web1);
			int i=0;
			while (resultSet.next()) {
			i++;
			String columna1 = resultSet.getString("email");
			String columna2 = resultSet.getString("secret");
			String columna3 = resultSet.getString("nombre");
			String columna4 = resultSet.getString("apellido");
			String columna5 = resultSet.getString("pago");
			String webx =" <div style=' border-radius:10px; background-color:black; padding: 20px;  display: flex;" + 
					"  justify-content: center;   '><br></br>"+		
					"	 <form action='altabaja' method='post' >" + 
						"    <input id='a"+i+"' type='text' name='parametroa' value="+columna1+">" + 
						"    <input id='b"+i+"' type='text' name='parametrob' value="+columna2+">" + 
						"    <input id='c"+i+"' type='text' name='parametroc' value="+columna3+">" + 
						"    <input id='d"+i+"' type='text' name='parametrod' value="+columna4+">" + 
						"    <input id='e"+i+"'type='text' name='parametroe' value="+columna5+">" + 
						"    <input  type='submit' value='cambiar acceso'> " + //responder consultas dede admin
					//	"    <button  onclick='clicked"+i+"()'>modificar</button>" + 
						"</form>"+
						 "</div>" + 
			"<script>"+
			"function clicked"+i+"(){"+
				 "const paramA"+i+" = document.getElementById('a"+i+"').value;"+
				 "const paramB"+i+" = document.getElementById('b"+i+"').value;"+
				 "const paramC"+i+" = document.getElementById('c"+i+"').value;"+
				 "const paramD"+i+" = document.getElementById('d"+i+"').value;"+
				 "const paramE"+i+" = document.getElementById('e"+i+"').value;"+
				 "console.log(paramE"+i+");"+
				 "user"+i+"= true;"+
				"}</script>";
				 
			writer.println("");
			writer.println(webx);
								}
				        

			
			String web3 ="<br></br><a style=' border-radius:5px; background-color:grey; padding: 5px; display: flex; justify-content: center;' href='out.jsp'>cerrar admin</a> </body></html>";
			writer.println(web3);
			System.out.println("salida bd admin");
			connection2.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}	
		} 
	
		doGet(request, response);
	}

}
