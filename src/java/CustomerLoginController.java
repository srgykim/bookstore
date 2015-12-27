import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.Customer;
import store.Store;

/**
 * Class CustomerLoginController contains
 * the servlet controller functionality for processing
 * customer's request to log in to the store.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class CustomerLoginController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Ensure that none of the fields are empty.
        if (email.equals("") || password.equals("")) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?email=" + 
                    email + "&err=Email and password must not be empty!");
        } else {
            Customer customer = null;

            try {
            		Store store = new Store();
	                customer = store.validateCustomer(email, password);
	                request.getSession().setAttribute("customer", customer);
	                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } catch(Exception e) {
            	// Inform customer about incorrect input data.
                response.sendRedirect(request.getContextPath() + "/login.jsp?email=" + 
                            email + "&err=Incorrect email or password!");
            }
        }
    }
    
}
