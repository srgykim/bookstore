import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.Store;

/**
 * Class CustomerSignupController contains
 * the servlet controller functionality for processing
 * customer's request to sign up.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class CustomerSignupController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Ensure that none of the fields are empty.
        if (email.equals("") || password.equals("")) {
            response.sendRedirect(request.getContextPath() + "/signup.jsp?email=" + 
                    email + "&err=Email and password must not be empty!");
        } else {
            try {
                Store store = new Store();
                store.signupCustomer(email, password);
                response.sendRedirect(request.getContextPath() + "/index.jsp?greetings="
                        + "Thank you for registering! Please, log in to make orders.");
            } catch(Exception e) {
            	// Inform customer about already registered email.
                response.sendRedirect(request.getContextPath() + "/signup.jsp?email=" + 
                    email + "&err=This email is already registered!");
            }
        }
    }
    
}
