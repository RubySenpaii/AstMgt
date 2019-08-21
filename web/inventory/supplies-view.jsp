<%-- 
    Document   : supplies-view
    Created on : Oct 27, 2018, 11:38:51 AM
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
        <title>Asset Management - View Supplies</title>
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
                        <div class="form-panel">
                            <h4>Supplies Count</h4><br/>
                            <form role="form" class="form-horizontal style-form" action="/AMS/PurchaseRequest/Add">
                                <div class="form-group">
                                    <div class="col-lg-12" style="text-align: center">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Timestamp</th>
                                                    <th>Amount Acquired</th>
                                                    <th>Amount Consumed</th>
                                                    <th>Division</th>
                                                    <th>Total Quantity</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    ArrayList<Supplies> supplies = (ArrayList<Supplies>) session.getAttribute("supplies");
                                                    for (Supplies supply : supplies) {
                                                %>
                                                <tr>
                                                    <td><%=supply.Timestamp%></td>
                                                    <td><%=supply.AmountAcquired%></td>
                                                    <td><%=supply.AmountConsumed%></td>
                                                    <td><%=supply.Division%></td>
                                                    <td><%=supply.TotalQuantity%></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                        <a href="/AMS/SupplierServlet/List">Back to Supplier List</a>
                                        <button type="submit" class="btn" name="asset-id" value="<%=supplies.get(0).Asset.AssetId%>">Re-acquire</button>
                                    </div>
                                </div>
                            </form>
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
