import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;
import store.Store;

/**
 * Class DeleteBookFromCartController contains
 * the servlet controller functionality for processing
 * customer's request to delete book from the cart
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class DeleteBookFromCartController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String isbn = request.getParameter("isbn");
        
        // Get cart of the current session.
        HttpSession session = request.getSession();
        BookSet cart;
        cart = (BookSet) session.getAttribute("cart");
        
        try {
            Store store = new Store();
            store.deleteBookFromCart(cart, isbn);
            
            session.setAttribute("cart", cart);
            response.sendRedirect(request.getHeader("referer"));
        } catch(Exception e) {}
        
    }
    
}
