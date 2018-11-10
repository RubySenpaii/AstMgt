<%-- 
    Document   : supplies-list
    Created on : Oct 28, 2018, 1:37:22 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.Supplies"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Add Purchase Request</title>
        <jsp:include page="../shared/css.jsp"/>
    </head>

    <body>
        <section id="container">
            <jsp:include page="../shared/header.jsp"/>
            <jsp:include page="../shared/sidebar.jsp"/>
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-panel">
                                <h4>List of Assets</h4>
                                <br/>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Stock Number</th>
                                            <th>Asset Name</th>
                                            <th>Quantity</th>
                                            <th>Last Updated</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Supplies> supplies = (ArrayList<Supplies>) session.getAttribute("supplies");
                                            for (Supplies supply : supplies) {
                                        %>
                                        <tr>
                                            <td><%=supply.Asset.StockNo%></td>
                                            <td><%=supply.Asset.AssetName%></td>
                                            <td><%=supply.TotalQuantity%></td>
                                            <td><%=supply.Timestamp%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- /row -->
                </section>
            </section>
            <!--main content end-->
            <jsp:include page="../shared/footer.jsp"/>
        </section>
    </body>
    <jsp:include page="../shared/js.jsp"/>
</html>
