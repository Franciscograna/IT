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
 * Servlet implementation class Logged
 */
@WebServlet("/Logged")
public class Logged extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
						String granted = "no";
						
			  String email     = request.getParameter("correo");
			  String secret = request.getParameter("password");
			  String nombre="";
			  String apellido="";
			  String pago="1";
			  
			 
				   try {
					    Class.forName("org.sqlite.JDBC");
						Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/franci/Documents/file-db/usersdb.sqlite3");//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "xxxx!");										
						String insertQuery = "INSERT INTO users (email, secret, nombre, apellido, pago) VALUES (?, ?, ?, ?, ?)";
						PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
						//preparedStatement.close();
								
						
						Statement statement = connection.createStatement();
						String query = "SELECT * FROM users";
					    ResultSet resultSet = statement.executeQuery(query);					    
					      
					    
					    while (resultSet.next()) {
					    String columna1 = resultSet.getString("email");
				        String columna2 = resultSet.getString("secret");
				        String columna3 = resultSet.getString("nombre");
				        String columna4 = resultSet.getString("apellido");
				        String columna5 = resultSet.getString("pago");
				      
							        
				        System.out.println("ingreso a la base de datos");
				        
				        		if ((columna1.equals(email))&&(columna2.equals(secret))&&columna5.equals(pago)) 
						        { 
						        	granted = "yes";
						        email=columna1; secret=columna2; nombre=columna3; apellido=columna4; pago=columna5;
						        		}	  
				        			}
					    
					    
					    
					    System.out.println("salida bd");
					    connection.close();	   			    
					} catch (ClassNotFoundException e) {
						System.out.println("Problema de clase en sql");
						e.printStackTrace();
					} catch (SQLException e) {
						System.out.println("Problema sql");
						e.printStackTrace();
					}				
					 System.out.println(granted); 
					 PrintWriter out = response.getWriter();
						response.setContentType("text/html");
					 	
					 if (granted.equals("yes")) {
							try {
						 HttpSession hs = request.getSession();
						    request.getSession().setAttribute("email", email);
						    request.getSession().setAttribute("nombre", nombre);
						    request.getSession().setAttribute("apellido", apellido);
							hs.setAttribute("email",email);
							hs.setAttribute("nombre",nombre);
							hs.setAttribute("apellido",apellido);
							System.out.println("redirect");
							response.sendRedirect("app");
							
						
								
							} catch (Exception e) {
								
								out.println("<html><head></head><body><h5 style='position: fixed; top: 70%;		left: 50%;	transform: translate(-50%, -50%); ' > usuario o contrase�a incorrecto  o momentaneamente no esta diponibla la pagina <h5><body><html>");
								e.printStackTrace();
							}
					 } else { 
						 
							out.println("<html><head></head><body><h5 style='position: fixed; top: 70%;		left: 50%;	transform: translate(-50%, -50%); ' > usuario, contrase�a incorrecto <h5><body><html>");
							RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
							rd.include(request, response);
						 			 
					 }
					 
					 
					 
					 if ("admin@admin.admin".equals(email) && "admin".equals(secret)) {
						    try {
						    	PrintWriter writer = response.getWriter();
								response.setContentType("text/html");
								String web1 ="<!DOCTYPE html>" + 
										"<html>" + 
										"<head>" + 
										"<meta charset='ISO-8859-1'>" + 
										"<title>ADMINISTRADOR</title>" + 
										"</head>" + 
										"<body >";
								writer.println(web1);
								String webx ="<div style=' border-radius:15px; background-color:black; display: flex; justify-content: center; padding:20px; '><br></br>"+
										"<form  action='admin' method='post' >" + 
										"    <input type='text' name='email' value="+email+">" + 
										"    <input style='opacity:0;' id='boton' type='submit' value='continuar'> " + //puse boton al centro ojo si falla
										"    <input type='text' name='secret' value="+secret+">" + 
										"</form> </div><br><br>"+
										"<script type='text/javascript'>window.setTimeout(function() { "+ 
										"	document.getElementById('boton').click()" + 
										"	}, 1000);" + 
										" " + 
										"	document.getElementById('boton').addEventListener('click', function() {" + 
										"	console.log('ingresando');" + 
										"	}, false); "
										+ "</script>"; 
									   
								writer.println(webx);
								String web3 ="</body></html>";
								writer.println(web3);
								
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					    
					    
					    
									
		//doGet(request, response);
	}

}
