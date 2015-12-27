import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;
import store.Store;

/**
 * Class CategorizeController contains
 * the servlet controller functionality for processing
 * customer's request to show the books of a particular category.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class CategorizeController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String category = request.getParameter("category");
        BookSet categorizedBooks = new BookSet();

        try {
            Store store = new Store();
            categorizedBooks = store.categorize(category);
            // Set categorizedBooks attribute to provide books of a category.
            request.getSession().setAttribute("categorizedBooks", categorizedBooks);
            response.sendRedirect(request.getContextPath() + "/categorized_books.jsp?category=" + category);
        } catch (Exception e) {}

    }
    
}
