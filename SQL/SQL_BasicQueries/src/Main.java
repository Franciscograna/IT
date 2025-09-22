import java.sql.*;
public class Main {

	public static void main(String[] args) {
		
		String usernameInput = "jey";
		String passwordInput = "4321segura";
		String query = "SELECT * FROM users;";
	    //String query ="INSERT INTO users (name, password)	VALUES (?,?);";
		// String query =" SELECT * FROM users WHERE name = '"+ usernameInput+"' AND password = '"+passwordInput +"'; ";	
		SqlExecuter(usernameInput, passwordInput,  query);
	
	
	
	}
	
	static void SqlExecuter(String name, String pass, String query) {
		
		 String url="jdbc:sqlite:usersdb.sqlite3";// String url="com.mysql://localhost:3306/Db/Table";
	
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection(url);//Connection con=DriverManager.getConnection(url,userSql,passwordSql);
			if(con!=null) {System.out.println("Connection Started");}
			
			Statement st = con.createStatement();
			if (!query.contains("INSERT")) {
				
										ResultSet rs = st.executeQuery(query);
										if (rs.next() && rs!=null) {
											 System.out.println(rs.getString("name")+" "+rs.getString("password"));
											while (rs.next()){
								            System.out.println(rs.getString("name")+" "+rs.getString("password"));
										}} else {
								            System.out.println("Usuario o contraseña incorrectos.");
								        }
										rs.close();
								}
			else 				{
			
										    PreparedStatement pstmt = con.prepareStatement(query);
										    pstmt.setString( 1, name);
										    pstmt.setString( 2, pass);
										    int filasInsertadas = pstmt.executeUpdate(); 
										    pstmt.close();
								}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		
		
	
}
