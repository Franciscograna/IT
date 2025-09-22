import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Dao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Login-Dao-Demo");
    
    public String login(String email, String password) {
										    	
		EntityManager em = emf.createEntityManager();
										        
						try {
				            TypedQuery<User> q = em.createQuery(
				            		   
				                "SELECT u FROM User u WHERE u.email = :email AND u.password = :password", 
				                User.class
				            );
													           
				            q.setParameter("email", email);
				            q.setParameter("password", password);
				            
				            boolean pass= !q.getResultList().isEmpty();
				           
				            if (pass) {
								return "✅ Bienvenido " + email;
							}else 
								return "❌ Usuario o password incorrecto";
				            
				        } finally {
				            em.close();
				        }
		
    }
}
