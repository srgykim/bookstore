<%-- 
    Document   : index.jsp
    Author     : Sergey Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="store.*"%>
<jsp:useBean id="tags" scope="page" class="store.CommonTags" />
<jsp:useBean id="cart" scope="session" class="store.BookSet" />
<jsp:useBean id="customer" scope="session" class="store.Customer" />

<%  
    String greetings = (request.getParameter("greetings") == null) 
            ? "" : request.getParameter("greetings");
    
    String thanks = (request.getParameter("thanks") == null) 
            ? "" : request.getParameter("thanks");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
        <%=tags.getCss()%>
    </head>
    <body>
        <div id="header">
            <%=tags.getHeader()%>
            <%
                if (customer.getEmail() == null) {
                    out.println(tags.getCustomerForm());
                } else {
                    out.println(tags.getLoggedCustomerForm(customer.getEmail()));
                }
            %>
        </div>
        <div id="cart">
            <%=tags.getCartView()%>
            <div align="center"><font color="white" size="4"><i>Cart items: <b><%=cart.getBookCount()%></b></i></font></div>
        </div>
        <div id="form">
            <%
                if (!greetings.equals("")) {
                    out.println("<h1>" + greetings + "<h1>");
                } else if (!thanks.equals("")) {
                    out.println("<h1>" + thanks + "</h1>");
                } else {
                    out.println("<h2 align='center'>New Releases:</h2>");
                    try {
                        Store store = new Store();
                        BookSet latestBooks = store.getLatestBooks();
                        
                        out.println("<table>");
                        for (int i = 0; i < latestBooks.getBookCount(); i++) {
                            Book book = latestBooks.getBookAt(i);
                            out.println("<tr>");
                            out.println("<td>");
                            out.println("<img src='images/" + book.getIsbn() + ".jpg' "
                                    + "alt='" + book.getTitle() + "' height='200' width='160'>");
                            out.println("<div align='center'>" + tags.addToCartForm(book.getIsbn()) + "</div>");
                            out.println("</td>");
                            out.println(tags.getColumnSpace(5));
                            out.println("<td>");
                            out.println("Title: <b>" + book.getTitle() + "</b><br>");
                            out.println("Author(s): " + store.getBookAuthors(book.getIsbn()) + "<br>");
                            out.println("Publisher: " + book.getPublisher() + "<br>");
                            out.println("Publication date: " + book.getPublicationDate() + "<br>");
                            out.println("Book type: " + book.getBookType() + "<br>");
                            out.println("Categories: " + store.getBookCategories(book.getIsbn()) + "<br>");
                            out.println("Price: <b>$" + book.getPrice() + "</b>");
                            out.println("<form action='BookDetailsController' method='post'>"
                                    + "<input type='image' src='images/details.png' alt='View details' height='26' width='110'>"
                                    + "<input type='hidden' name='isbn' value='" + book.getIsbn() + "'>"
                                    + "</form>");
                            out.println("</td>");
                            out.println("</tr>");
                            out.println(tags.getRowSpace(20));
                        }
                        
                        out.println("</table>");
                        
                    } catch(Exception e) {}
                }
            %>
        </div>
        <%=tags.getCategoriesForm()%>
    </body>
</html>
