import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;

/**
 * Class DecreaseQuantityController contains
 * the servlet controller functionality for processing
 * customer's request to decrease the number of books of a title in the cart
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class DecreaseQuantityController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String isbn = request.getParameter("isbn");
        
        // Get cart of the current session.
        HttpSession session = request.getSession();
        BookSet cart;
        cart = (BookSet) session.getAttribute("cart");
        
        for (int i = 0; i < cart.getBookCount(); i++) {
            if (cart.getBookAt(i).getIsbn().equals(isbn) && cart.getBookAt(i).getQuantity() > 1) {
                cart.getBookAt(i).setQuantity(cart.getBookAt(i).getQuantity() - 1);
                break;
            }
        }
        
        session.setAttribute("cart", cart);
        response.sendRedirect(request.getHeader("referer"));
    }
    
}
