package store;

/**
 * A class representing a customer
 * 
 * @author Sergey Kim
 * @version 1.0
 */
public class Customer {
	
    private String email; // customer's email
    private String password;	// customer's password
    
    /**
     * class Customer constructor
     */
    public Customer() {}
    
    /**
     * class Customer constructor.
     * @param inEmail String customer's email
     * @param inPassword String customer's password
     */
    public Customer(String inEmail, String inPassword) {
        email = inEmail;
        password = inPassword;
    }
    
    /**
     * Accessor for email
     * @return fString
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Mutator for email
     * @param inEmail input email
     * @return void
     */
    public void setEmail(String inEmail) {
        email = inEmail;
    }
    
    /**
     * Accessor for password
     * @return String
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Mutator for password
     * @param inPassword input password
     * @return void
     */
    public void setPassword(String inPassword) {
        password = inPassword;
    }
    
}
