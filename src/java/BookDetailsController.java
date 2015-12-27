import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.Book;
import store.Store;

/**
 * Class BookDetailsController contains
 * the servlet controller functionality for processing
 * customer's request to see the details about a book.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class BookDetailsController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String isbn = request.getParameter("isbn");
        
        Book book = new Book();
        
        try {
            Store store = new Store();
            book = store.getBookDetails(isbn);
            // Set book session attribute to provide its details.
            request.getSession().setAttribute("book", book);
            response.sendRedirect(request.getContextPath() + "/book_details.jsp?isbn=" + isbn);
        } catch(Exception e) {}
    }
    
}
