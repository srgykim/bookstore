import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;
import store.Store;

/**
 * Class AddToCartController contains the
 * servlet controller functionality for processing
 * customer's request to add a book into the cart.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class AddToCartController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String isbn = request.getParameter("isbn");
        
        // Get session to add book into the cart.
        HttpSession session = request.getSession();
        BookSet cart;
        cart = (BookSet) session.getAttribute("cart");
        
        try {
            Store store = new Store();
            store.addBookToCart(cart, isbn);
            request.getSession().setAttribute("cart", cart);
            response.sendRedirect(request.getHeader("referer"));
        } catch(Exception e) {}

    }
    
}
