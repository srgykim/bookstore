<%-- 
    Document   : search_results.jsp
    Author     : Sergey Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="store.*, java.util.*"%>
<jsp:useBean id="tags" scope="page" class="store.CommonTags" />
<jsp:useBean id="books" scope="session" class="store.BookSet" />
<jsp:useBean id="cart" scope="session" class="store.BookSet" />
<jsp:useBean id="customer" scope="session" class="store.Customer" />

<%  
    String searchString = (request.getParameter("searchString") == null) 
            ? "" : request.getParameter("searchString");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Results</title>
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
        	<h2 align="center">Search Results:</h2>
            <%if (books.getBookCount() == 0) out.println("Your search <b>\"" 
                    + searchString + "\"</b> did not match any book.");%>
            <table>
                <%
                    for (int i = 0; i < books.getBookCount(); i++) {
                        Book book = new Book();
                        book = books.getBookAt(i);
                %>
                <tr>
                    <td>
                        <img src="images/<%=book.getIsbn()%>.jpg" 
                             alt="<%=book.getTitle()%>"
                             height="200" width="160">
                        <div align="center"><%=tags.addToCartForm(book.getIsbn())%></div>
                    </td>
                    <%=tags.getColumnSpace(5)%>
                    <td>
                        Title: <b><%=book.getTitle()%></b><br>
                        Author(s):
                        <%
                            try {
                                Store store = new Store();
                                String authors = new String(store.getBookAuthors(book.getIsbn()));
                                out.println(authors);
                            } catch (Exception e) {}
                        %>
                        <br>
                        Publisher: <%=book.getPublisher()%><br>
                        Publication date: <%=book.getPublicationDate()%><br>
                        Book type: <%=book.getBookType()%><br>
                        Categories: 
                        <%
                            try {
                                Store store = new Store();
                                String categories = new String(store.getBookCategories(book.getIsbn()));
                                out.println(categories);
                            } catch (Exception e) {}
                        %>
                        <br>
                        Price: <b>$<%=book.getPrice()%></b>
                        <form action="BookDetailsController" method="post">
                            <input type="image" src="images/details.png" alt='View details' height='26' width='110'>
                            <input type="hidden" name="isbn" value="<%=book.getIsbn()%>">
                        </form>
                    </td>
                </tr>
                <%=tags.getRowSpace(20)%>
                <%}%>
            </table>
        </div>
        <%=tags.getCategoriesForm()%>
    </body>
</html>
