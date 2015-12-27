<%-- 
    Document   : order_history.jsp
    Author     : Sergey Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="store.*"%>
<jsp:useBean id="tags" scope="page" class="store.CommonTags" />
<jsp:useBean id="cart" scope="session" class="store.BookSet" />
<jsp:useBean id="orders" scope="session" class="store.OrderSet" />
<jsp:useBean id="customer" scope="session" class="store.Customer" />

<%
    if (customer.getEmail() == null) {
        response.sendRedirect("login.jsp");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cabinet</title>
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
                if (orders.getOrderCount() == 0) {
                    out.println("You have not made any orders.");
                } else {
            %>
            <h2 align="center"><font color="blue"><%=customer.getEmail()%></font>, here is your order history:</h2>
            <table border="1" align="center" class="solid">
                <tr>
                    <th>
                        OrderNo
                    </th>
                    <th>
                        First Name
                    </th>
                    <th>
                        Last Name
                    </th>
                    <th>
                        Address
                    </th>
                    <th>
                        Phone Number
                    </th>
                    <th>
                        Delivery Method
                    </th>
                    <th>
                        Order Date
                    </th>
                    <th>
                        Order Cost
                    </th>
                    <th>
                        Ordered Books
                    </th>
                </tr>
                <%for (int i = 0; i < orders.getOrderCount(); i++) {%>
                <tr>
                    <td align="center"><b><%=orders.getOrderAt(i).getOrderId()%></b></td>
                    <td align="center"><%=orders.getOrderAt(i).getFirstName()%></td>
                    <td align="center"><%=orders.getOrderAt(i).getLastName()%></td>
                    <td align="center"><%=orders.getOrderAt(i).getAddress()%></td>
                    <td align="center"><%=orders.getOrderAt(i).getPhone()%></td>
                    <td align="center"><%=orders.getOrderAt(i).getDeliveryMethod()%></td>
                    <td align="center"><%=orders.getOrderAt(i).getOrderDate()%></td>
                    <td align="center"><font color="red">$<%=orders.getOrderAt(i).getOrderCost()%></font></td>
                    <td>
                        <%
                            try {
                                Store store = new Store();
                                String books = store.getOrderedBooks(orders.getOrderAt(i).getOrderId());
                                out.println(books);
                            } catch(Exception e) {}
                        %>
                    </td>
                    <%}%>
                </tr>
            </table>
            <%}%>
        </div>
        <%=tags.getCategoriesForm()%>
    </body>
</html>
