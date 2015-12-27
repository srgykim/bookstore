<%-- 
    Document   : login.jsp
    Author     : Sergey Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="store.*"%>
<jsp:useBean id="tags" scope="page" class="store.CommonTags" />
<jsp:useBean id="cart" scope="session" class="store.BookSet" />

<%
    String err = (request.getParameter("err") == null)
            ? ""
            : request.getParameter("err");
    
    String email = (request.getParameter("email") == null)
            ? ""
            : request.getParameter("email");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>
        <%=tags.getCss()%>
    </head>
    <body>
        <div id="header">
            <%=tags.getHeader()%>
            <%=tags.getCustomerForm()%>
            <%=tags.getSpace(77)%>
        </div>
        <div id="cart">
            <%=tags.getCartView()%>
            <div align="center"><font color="white" size="4"><i>Cart items: <b><%=cart.getBookCount()%></b></i></font></div>
        </div>
        <div id="form">
            <h3 align="center">Please, log in to make orders.</h3>
            <form action="CustomerLoginController" method="post">
                <table align="center">
                    <tr>
                        <td align="right">
                            Email:<br><br>
                            Password:<br>
                        </td>
                        <td>
                            <input type="text" name="email" value="<%=email%>"><br><br>
                            <input type="password" name="password"><br>
                        </td>
                    </tr>
                    <tr>
                        <%=tags.getColumnSpace(1)%>
                        <td align="center">
                            <br>
                            <input type="image" src="images/login.png" alt="Log in" width="65" height="26">
                        </td>
                    </tr>
                </table>
            </form>
            <br>
            <div align="center"><font color="red"><%=err%></font></div>
        </div>
        <%=tags.getCategoriesForm()%>
    </body>
</html>
