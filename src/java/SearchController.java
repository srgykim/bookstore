import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import store.BookSet;
import store.Store;

/**
 * Class SearchController contains
 * the servlet controller functionality for processing
 * customer's request to search a book by title or author.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class SearchController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String searchString = request.getParameter("searchString");
        
        // Ensure that search request is not empty.
        if (searchString.equals("")) {
        	response.sendRedirect(request.getHeader("referer"));
        } else {
            BookSet books = new BookSet();

            try {
                Store store = new Store();
                books = store.search(searchString);
                
                request.getSession().setAttribute("books", books);
                response.sendRedirect(request.getContextPath() + "/search_results.jsp?searchString=" + searchString);
            } catch (Exception e) {}

        }
    }
    
}
