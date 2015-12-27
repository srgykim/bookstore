import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;
import store.Customer;
import store.Store;

/**
 * Class OrderController contains
 * the servlet controller functionality for processing
 * customer's request to make an order.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class OrderController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // Get cart of the current session.
        BookSet cart;
        cart = (BookSet) session.getAttribute("cart");
        
        // Get customer of the current session.
        Customer customer;
        customer = (Customer) session.getAttribute("customer");
        
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String deliveryMethod = request.getParameter("delivery_method");
        
        try {
            Store store = new Store();
            store.addOrder(cart, customer.getEmail(), firstName, lastName, 
                    address, phone, deliveryMethod);
            
            request.getSession().setAttribute("cart", cart);
            request.getSession().setAttribute("customer", customer);
            response.sendRedirect(request.getContextPath() + 
                "/index.jsp?thanks=Your order have been successfully added. "
                + "Thank you!<br> You can view your order history in your cabinet.");
            
        } catch(Exception e) {}
    }
    
}
