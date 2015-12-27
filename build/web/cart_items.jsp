<%-- 
    Document   : cart_items.jsp
    Author     : Sergey Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, store.*"%>
<jsp:useBean id="tags" scope="page" class="store.CommonTags" />
<jsp:useBean id="cart" scope="session" class="store.BookSet" />
<jsp:useBean id="customer" scope="session" class="store.Customer" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Items</title>
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
                boolean check = true;
                if (cart.getBookCount() == 0) { 
                    out.println("You have no books in your cart.");
                    check = false;
                } else {
                    float sum = 0;
                    for (int i = 0; i < cart.getBookCount(); i++) {
                        sum += cart.getBookAt(i).getPrice() * cart.getBookAt(i).getQuantity();
                    }
                    out.println("<h3 align='center'>Cart Subtotal: <font color='red'>$" + sum + "</font></h3>");          
            %>
            <table>
            <%
                    for (int i = 0; i < cart.getBookCount(); i++) {
                        Book book = cart.getBookAt(i);
            %>
                <tr>
                    <%=tags.getColumnSpace(5)%>
                    <td>
                        <img src="images/<%=book.getIsbn()%>.jpg" 
                             alt="<%=book.getTitle()%>"
                             height="100" width="80">
                        <form action="BookDetailsController" method="post">
                            <input type="image" src="images/details.png" alt='View details' height='19' width='80'>
                            <input type="hidden" name="isbn" value="<%=book.getIsbn()%>">
                        </form>
                    </td>
                    <%=tags.getColumnSpace(5)%>
                    <td>
                        <b><%=book.getTitle()%></b> by 
                        <%
                            try {
                                Store store = new Store();
                                String authors = new String(store.getBookAuthors(book.getIsbn()));
                                out.println(authors);
                            } catch (Exception e) {}
                        %>
                        <br>
                        Price: $<%=book.getPrice()%><br>
                        <form action="DeleteBookFromCartController" method="post">
                            <input type="image" src="images/remove.png" alt='Remove' width='64' height='21'>
                            <input type="hidden" name="isbn" value="<%=book.getIsbn()%>">
                        </form>
                    </td>
                    <%=tags.getColumnSpace(20)%>
                    <td align="center">
                        Quantity:<br>
                        <%=book.getQuantity()%>
                        &nbsp;<br>
                        &nbsp;<br>
                    </td>
                    <td>
                        <form action="IncreaseQuantityController" method="post">
                            <input type="image" src="images/increase.png" alt="increase" width="30" height="30">
                            <input type="hidden" name="isbn" value="<%=book.getIsbn()%>">
                        </form>
                        <form action="DecreaseQuantityController" method="post">
                            <input type="image" src="images/decrease.png" alt="increase" width="30" height="30">
                            <input type="hidden" name="isbn" value="<%=book.getIsbn()%>">
                        </form>
                        &nbsp;<br>
                    </td>
                    <%=tags.getColumnSpace(20)%>
                    <td>
                        Subtotal: 
                        <font color="red">
                            $<%out.println(cart.getBookAt(i).getPrice() * cart.getBookAt(i).getQuantity());%>
                        </font>
                        &nbsp;<br>
                        &nbsp;<br>
                    </td>
                </tr>
                <%=tags.getRowSpace(20)%>
            <%}}%>
            </table>
            <br>
            <%if (check) {%>
            <table align="center">
                <tr>
                    <td>
                        <form action="index.jsp">
                            <input type="image" src="images/continue.png" 
                                   alt="Continue Shopping" width="160" height="26">
                        </form>
                    </td>
                    <td>
                        <form action="checkout.jsp">
                            <input type="image" src="images/checkout.png" 
                                     alt="Checkout" width="95" height="26">
                        </form>
                    </td>
                </tr>
            </table>
            <%}%>
        </div>
        <%=tags.getCategoriesForm()%>
    </body>
</html>
