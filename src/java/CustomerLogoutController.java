import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Class CustomerLogoutController contains
 * the servlet controller functionality for processing
 * customer's request to log out.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class CustomerLogoutController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	// Get session
    	HttpSession session = request.getSession();
    	
    	// End session
		session.invalidate();
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
    
}
