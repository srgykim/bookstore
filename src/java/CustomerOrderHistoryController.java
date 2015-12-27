import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.OrderSet;
import store.Customer;
import store.Store;

/**
 * Class CustomerOrderHistoryController contains
 * the servlet controller functionality for processing
 * customer's request to view order history.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class CustomerOrderHistoryController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // Get current customer to show his/her order history
        Customer customer;
        customer = (Customer) session.getAttribute("customer");
        
        OrderSet orders = new OrderSet();
        
        try {
            Store store = new Store();
            orders = store.getOrderHistory(customer.getEmail());
            
            request.getSession().setAttribute("orders", orders);
            response.sendRedirect(request.getContextPath() + "/order_history.jsp");
        } catch(Exception e) {}

    }
}
