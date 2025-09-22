public class Main {
    public static void main(String[] args) {
    	
    		Dao dao = new Dao();
	        String email = "jose@hotmail.com";
	        String password = "1234";
	        System.out.println(dao.login(email, password));
	        

	    }
}

