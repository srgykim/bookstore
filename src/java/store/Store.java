package store;

import java.sql.*;

/**
 * Class Store represents the functionality of the store.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class Store {
	
	// singleton DBWrapper
    private DBWrapper myConnection = null;
    
    /**
     * class Store constructor
     * @throws Exception
     */
    public Store() 
    		throws Exception {
	
    	myConnection = DBWrapper.Instance();
    }
    
    /**
     * search searches books in the store by input title, author's first name, author's last name.
     * @param searchString
     * @return books BookSet collection of found books.
     * @throws Exception
     */
    public BookSet search(String searchString) 
            throws Exception {
        
        BookSet books = new BookSet();
        
        String query = "SELECT DISTINCT isbn, title, description, publisher, "
                + "TO_CHAR(publication_date, 'Month dd, yyyy') publication_date, book_type, price "
                + "FROM BOOKAUTHORS "
                + "NATURAL JOIN BOOKS "
                + "NATURAL JOIN AUTHORS "
                + "WHERE LOWER(title) LIKE LOWER('%" + searchString + "%') OR "
                + "LOWER(first_name) LIKE LOWER('%" + searchString + "%') OR "
                + "LOWER(last_name) LIKE LOWER('%" + searchString + "%') OR "
                + "LOWER(CONCAT(first_name, ' ' || last_name)) LIKE LOWER('%" + searchString+ "%') OR "
                + "LOWER(CONCAT(last_name, ' ' || first_name)) LIKE LOWER('%" + searchString+ "%') OR "
                + "LOWER(CONCAT(first_name, last_name)) LIKE LOWER('%" + searchString+ "%') OR "
                + "LOWER(CONCAT(last_name, first_name)) LIKE LOWER('%" + searchString+ "%') "
                + "ORDER BY title";
                
        
        ResultSet r = myConnection.runQuery(query);
        while (r.next()) {
            Book temp = new Book(r.getString("isbn"), r.getString("title"), r.getString("description"), 
                    r.getString("publisher"), r.getString("publication_date"), 
                    r.getString("book_type"), r.getFloat("price"));
            
            books.addBook(temp);
        }
        
        return books;
    }
    
    /**
     * getLatestBooks searches books that are published in 2015 or later.
     * @return books BookSet collection of found books.
     * @throws Exception
     */
    public BookSet getLatestBooks() 
            throws Exception {
        BookSet books = new BookSet();
        
        String query = "SELECT isbn, title, description, publisher, publication_date as pub_date, "
                + "TO_CHAR(publication_date, 'Month dd, yyyy') publication_date, book_type, price FROM BOOKS "
                + "WHERE publication_date >= TO_DATE('01-Jan-2015', 'dd-Mon-yyyy')";
        
        ResultSet r = myConnection.runQuery(query);
        while (r.next()) {
            Book temp = new Book(r.getString("isbn"), r.getString("title"), r.getString("description"), 
                    r.getString("publisher"), r.getString("publication_date"), 
                    r.getString("book_type"), r.getFloat("price"));
            
            books.addBook(temp);
        }
        
        return books;
    }
    
    /**
     * categorize returns the books of the specified category.
     * @param category String chosen category of books.
     * @return books BookSet collection of books of the specified category.
     * @throws Exception
     */
    public BookSet categorize(String category) 
            throws Exception {
        
        BookSet books = new BookSet();
        
        String query = "SELECT isbn, title, description, publisher, publication_date as pub_date, "
                + "TO_CHAR(publication_date, 'Month dd, yyyy') publication_date, book_type, price FROM BOOKCATEGORIES "
                + "NATURAL JOIN BOOKS "
                + "WHERE category_name = '" + category + "' "
                + "ORDER BY title";
                
        
        ResultSet r = myConnection.runQuery(query);
        while (r.next()) {
            Book temp = new Book(r.getString("isbn"), r.getString("title"), r.getString("description"), 
                    r.getString("publisher"), r.getString("publication_date"), 
                    r.getString("book_type"), r.getFloat("price"));
            
            books.addBook(temp);
        }
        
        return books;
    }
    
    /**
     * getBookDetails returns the book specified by its ISBN.
     * @param isbn String ISBN of the book.
     * @return book Book book specified by its ISBN.
     * @throws Exception
     */
    public Book getBookDetails(String isbn) 
            throws Exception {
        
        String query = "SELECT isbn, title, description, publisher, publication_date as pub_date, "
                + "TO_CHAR(publication_date, 'Month dd, yyyy') publication_date, book_type, price FROM BOOKS "
                + "WHERE isbn = '" + isbn + "'";
        ResultSet r = myConnection.runQuery(query);
        r.next();
        Book book = new Book(r.getString("isbn"), r.getString("title"), r.getString("description"), 
                r.getString("publisher"), r.getString("publication_date"), 
                r.getString("book_type"), r.getFloat("price"));
        r.close();
        
        return book;
    }
    
    /**
     * getBookCategories returns the string of categories to which the book belongs to.
     * @param isbn String ISBN of the book.
     * @return String
     * @throws Exception
     */
    public String getBookCategories(String isbn)
            throws Exception {
        
        String categories = "";
        String query = "SELECT category_name FROM BOOKCATEGORIES WHERE isbn = '" + isbn + "'";
        ResultSet result = myConnection.runQuery(query);
        ResultSet count = myConnection.runQuery("SELECT COUNT(isbn) AS num FROM BOOKCATEGORIES WHERE isbn ='" + isbn + "'");
        count.next();
        int num = count.getInt("num");
        count.close();
        
        for (int i = 0; i < num - 1 ; i++) {
            result.next();
            categories += result.getString("category_name") + ", ";
        }
        
        result.next();
        categories += result.getString("category_name");
        
        return categories;
    }
    
    /**
     * getBookAuthors returns the string of book authors.
     * @param isbn String ISBN of the book.
     * @return String
     * @throws Exception
     */
    public String getBookAuthors(String isbn)
            throws Exception {
        
        String authors = "";
        String query = "SELECT first_name, last_name FROM BOOKAUTHORS "
                + "NATURAL JOIN AUTHORS WHERE isbn = '" + isbn + "'";
        ResultSet result = myConnection.runQuery(query);
        ResultSet count = myConnection.runQuery("SELECT COUNT(isbn) AS num FROM BOOKAUTHORS WHERE isbn ='" + isbn + "'");
        count.next();
        int num = count.getInt("num");
        count.close();
        
        for (int i = 0; i < num - 1 ; i++) {
            result.next();
            authors += result.getString("first_name") + " " + 
                    result.getString("last_name") + ", ";
        }
        
        result.next();
        authors += result.getString("first_name") + " " + 
                    result.getString("last_name");
        
        return authors;
    }
    
    /**
     * addBookToCart adds book into the cart.
     * @param cart BookSet cart to which the book will be added.
     * @param isbn String ISBN of the book.
     * @throws Exception
     */
    public void addBookToCart(BookSet cart, String isbn) 
            throws Exception{
        
        boolean check = false;
        
        for (int i = 0; i < cart.getBookCount(); i++) {
            if (cart.getBookAt(i).getIsbn().equals(isbn)) {
                check = true;
                break;
            }
        }
        
        if (!check) {
            cart.addBook(getBookDetails(isbn));
        }
    }
    
    /**
     * deleteBookFromCart deletes book from the cart.
     * @param cart BookSet cart from which the book will be deleted.
     * @param isbn String ISBN of the book.
     * @throws Exception
     */
    public void deleteBookFromCart(BookSet cart, String isbn) 
            throws Exception {
        
        for (int i = 0; i < cart.getBookCount(); i++) {
            if (cart.getBookAt(i).getIsbn().equals(isbn)) {
                cart.removeBookAt(i);
                break;
            }
        }
    }
    
    /**
     * validateCustomer validates the customer when he/she logs in.
     * @param email String input email
     * @param password String input password
     * @return customer Customer customer object if email and password are correct.
     * @throws Exception
     */
    public Customer validateCustomer(String email, String password) 
            throws Exception {
        
        String checkQuery = "SELECT * FROM CUSTOMERS WHERE email = '" + email + "' AND "
                + "password = '" + password + "'";
        
        Customer customer = null;
        ResultSet r = myConnection.runQuery(checkQuery);
        
        if (r == null) {
            return null;
        } else {
            r.next();
            customer = new Customer(r.getString("email"), r.getString("password"));
            r.close();
            
            return customer;
        }
    }
    
    /**
     * signupCustomer registers a new customer.
     * @param email String input email
     * @param password String input password
     * @throws Exception
     */
    public void signupCustomer(String email, String password) 
            throws Exception {
        
        String query = "INSERT INTO CUSTOMERS VALUES ('" + email + "', '" + password + "')";
        int r = myConnection.runUpdate(query);
    }
    
    /**
     * addOrder adds a new order.
     * @param cart Cart cart containing the books for the order.
     * @param email String customer's email
     * @param firstName String customer's first name
     * @param lastName String customer's last name
     * @param address String customer's address
     * @param phone String customer's phone number
     * @param deliveryMethod String chosen delivery method
     * @throws Exception
     */
    public void addOrder(BookSet cart, String email, String firstName, 
            String lastName, String address, String phone, String deliveryMethod) 
            throws Exception {
        
        float orderCost = 0;
        for (int i = 0; i < cart.getBookCount(); i++) {
            orderCost += cart.getBookAt(i).getPrice() * cart.getBookAt(i).getQuantity();
        }
        
        if (deliveryMethod.equals("courier")) {
            orderCost += 5.0;
        }
        
        String orderQuery = "INSERT INTO ORDERS VALUES "
                + "(nextval('order_numbers'), '"+email+"', "
                + "'" + firstName + "', '" + lastName + "', '" + address + "', '" + phone + "', "
                + "'" + deliveryMethod + "', current_date, " + orderCost + ")";

        
        int r1 = myConnection.runUpdate(orderQuery);
        
        for (int i = 0; i < cart.getBookCount(); i++) {
            String orderItemsQuery = "INSERT INTO ORDERITEMS VALUES ("
                    + "currval('order_numbers'), '" + cart.getBookAt(i).getIsbn() + "', "
                    + cart.getBookAt(i).getQuantity() + ""
                    + ")";
            
            int r2 = myConnection.runUpdate(orderItemsQuery);
        }
    }
    
    /**
     * getOrderHistory returns a collection of orders made by the customer.
     * @param email String customer's email
     * @return orders OrderSet orders made by the customer
     * @throws Exception
     */
    public OrderSet getOrderHistory(String email) 
            throws Exception {
        
        OrderSet orders = new OrderSet();
        
        String query = "SELECT order_id, email, first_name, last_name, "
                + "address, phone, delivery_method, TO_CHAR(order_date, 'Month dd, yyyy') as order_date, "
                + "order_cost FROM ORDERS WHERE email = '" + email + "'";
        
        ResultSet r = myConnection.runQuery(query);
        while (r.next()) {
            Order temp = new Order(r.getInt("order_id"), r.getString("email"), 
                    r.getString("first_name"), r.getString("last_name"), 
                    r.getString("address"), r.getString("phone"), 
                    r.getString("delivery_method"), r.getString("order_date"), 
                    r.getFloat("order_cost"));
            
            orders.addOrder(temp);
        }
        
        return orders;
    }
    
    /**
     * getOrderedBooks returns HTML representation of books in an order.
     * @param orderId int order number
     * @return String
     * @throws Exception
     */
    public String getOrderedBooks(int orderId) 
            throws Exception{
    	
        String books = "<font size='2'>";
        
        String query = "SELECT title, isbn, quantity "
                + "FROM ORDERITEMS "
                + "NATURAL JOIN ORDERS "
                + "NATURAL JOIN BOOKS "
                + "WHERE order_id = " + orderId + "";
        
        ResultSet r = myConnection.runQuery(query);
        
        int number = 1;
        while (r.next()) {
            books += number + ". <i>" + r.getString("title") + "</i> by " 
                    + getBookAuthors(r.getString("isbn")) + "<br>" + "&nbsp;&nbsp;&nbsp;&nbsp;Quantity: " 
                    + r.getString("quantity") + "<br>";
            number++;
        }
        
        books += "</font>";
        
        return books;
    }
}   
