<%-- 
    Document   : checkout.jsp
    Author     : Sergey Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="tags" scope="page" class="store.CommonTags" />
<jsp:useBean id="cart" scope="session" class="store.BookSet" />
<jsp:useBean id="customer" scope="session" class="store.Customer" />

<%
    if (customer.getEmail() == null) {
        response.sendRedirect("login.jsp");
    }
%>

<%
    String firstName = (request.getParameter("firstName") == null)
            ? ""
            : request.getParameter("firstName");
    
    String lastName = (request.getParameter("lastName") == null)
            ? ""
            : request.getParameter("lastName");
    
    String address = (request.getParameter("address") == null)
            ? ""
            : request.getParameter("address");
    
    String phone = (request.getParameter("phone") == null)
            ? ""
            : request.getParameter("phone");
    
    String deliveryMethod = (request.getParameter("deliveryMethod") == null)
            ? ""
            : request.getParameter("deliveryMethod");
    
    String err = (request.getParameter("err") == null)
            ? ""
            : request.getParameter("err");
    
    String self = "";
    String courier = "";
    
    if (deliveryMethod.equals("self-pickup")) {
        self = "checked";
    } else if (deliveryMethod.equals("courier")) {
        courier = "checked";
    } else {
        self = "checked";
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
            <h2 align="center">Fill in the fields to proceed your order checkout:</h2>
            <form action="proceed.jsp">
                <table align="center">
                    <tr>
                        <td align="right">
                            First name:<br><br>
                            Last name:<br><br>
                            Address:<br><br>
                            Phone number:<br><br>
                        </td>
                        <td>
                            <input type="text" name="first_name" value="<%=firstName%>"><br><br>
                            <input type="text" name="last_name" value="<%=lastName%>"><br><br>
                            <input type="text" name="address" value="<%=address%>"><br><br>
                            <input type="text" name="phone" value="<%=phone%>"><br><br>
                        </td>
                    </tr>
                </table>
                <div align="center">
                    Delivery method:
                    <label>
                        <input type="radio" name="delivery_method" value="self-pickup" <%=self%>>
                        self-pickup (free)
                    </label>
                    <label>
                        <input type="radio" name="delivery_method" value="courier" <%=courier%>>
                        courier ($5)
                    </label>
                    <br><br>
                    <font color="red"><%=err%></font>
                    <br><br>
                    <input type="image" src="images/proceed.png" alt="Proceed" height="26" width="82">
                </div>
            </form>
        </div>
        <%=tags.getCategoriesForm()%>
    </body>
</html>
