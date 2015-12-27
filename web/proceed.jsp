<%-- 
    Document   : proceed.jsp
    Author     : Sergey Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="store.*"%>
<jsp:useBean id="tags" scope="page" class="store.CommonTags" />
<jsp:useBean id="cart" scope="session" class="store.BookSet" />
<jsp:useBean id="customer" scope="session" class="store.Customer" />

<%
    if (customer.getEmail() == null) {
        response.sendRedirect("login.jsp");
    }
    
    String firstName = (request.getParameter("first_name") == null)
            ? ""
            : request.getParameter("first_name");
    
    String lastName = (request.getParameter("last_name") == null)
            ? ""
            : request.getParameter("last_name");
    
    String address = (request.getParameter("address") == null)
            ? ""
            : request.getParameter("address");
    
    String phone = (request.getParameter("phone") == null)
            ? ""
            : request.getParameter("phone");
    
    String deliveryMethod = (request.getParameter("delivery_method") == null)
            ? ""
            : request.getParameter("delivery_method");
    
    if (firstName.equals("") || lastName.equals("") ||
            address.equals("") || phone.equals("") ||
            deliveryMethod.equals("")) {
        
        response.sendRedirect("checkout.jsp?firstName=" + firstName + ""
                + "&lastName=" + lastName + "&address=" + address + ""
                + "&phone=" + phone + "" + "&deliveryMethod=" + deliveryMethod + ""
                + "&err=Please, fill in empty fields!");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
        <%=tags.getCss()%>
    </head>
    <body>
        <div id="header">
            <%=tags.getHeader()%>
            <%=tags.getLoggedCustomerForm(customer.getEmail())%>
        </div>
        <div id="cart">
            <%=tags.getCartView()%>
            <div align="center"><font color="white" size="4"><i>Cart items: <b><%=cart.getBookCount()%></b></i></font></div>
        </div>
        <div id="form">
            <h1 align="center">Order Summary</h1><br>    
            <table align="center">
                <tr>
                    <td>
                        <div align="center"><font color="blue" size="4"><b>Order Details</b></font></div><br>
                        <b>First name:</b> <%=firstName%>
                        <br>

                        <b>Last name:</b> <%=lastName%>
                        <br>

                        <b>Address:</b> <%=address%>
                        <br>

                        <b>Phone number:</b> <%=phone%>
                        <br>

                        <b>Delivery method:</b> <%=deliveryMethod%>
                        <br>
                        <%
                            float orderCost = 0;
                            if (deliveryMethod.equals("courier")) {
                                orderCost += 5;
                            }
                            for (int k = 0; k < cart.getBookCount(); k++) {
                                Book book = cart.getBookAt(k);
                                orderCost += book.getPrice() * book.getQuantity();
                            }
                        %>     
                        <b>Order cost including delivery:</b> <font color="red"><b>$<%=orderCost%></b></font>
                    </td>
                    <%=tags.getColumnSpace(40)%>
                    <td>
                        <div align="center"><font color="blue" size="4"><b>Order Items</b></font></div><br>
                        <%
                            float subtotal = 0;
                            for (int i = 0; i < cart.getBookCount(); i++) {
                                Book book = cart.getBookAt(i);
                                
                                subtotal = book.getPrice() * book.getQuantity();
                                
                                out.println(i + 1 + ". <b>" + book.getTitle() + "</b> by ");
                                try {
                                    Store store = new Store();
                                    String authors = new String(store.getBookAuthors(book.getIsbn()));
                                    out.println(authors + "<br>");
                                } catch (Exception e) {}
                                
                                out.println(tags.getSpace(4) + "Price: $" + book.getPrice() + "<br>");
                                out.println(tags.getSpace(4) + "Quantity: " + book.getQuantity() + "<br>");
                                out.println(tags.getSpace(4) + "Subtotal: <font color='red'>$" + subtotal + "</font><br>");
                            }
                        %>
                    </td>
                </tr>
            </table>
            <br><br>
            <div align="center">
                <form action="OrderController" method="post">
                    <input type="hidden" name="first_name" 
                               value="<%=firstName%>">
                    <input type="hidden" name="last_name" 
                               value="<%=lastName%>">
                    <input type="hidden" name="address" 
                               value="<%=address%>">
                    <input type="hidden" name="phone" 
                               value="<%=phone%>">
                    <input type="hidden" name="delivery_method" 
                               value="<%=deliveryMethod%>">
                    <input type="image" src="images/confirm.png" alt="Confirm" height="26" width="83">
                </form>
            </div>
        </div>
        <%=tags.getCategoriesForm()%>
    </body>
</html>
