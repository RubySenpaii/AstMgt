<%-- 
    Document   : acknowledgement
    Created on : Oct 27, 2018, 12:11:52 AM
    Author     : RubySenpaii
--%>

<%@page import="objects.Employee"%>
<%@page import="objects.PurchaseOrder"%>
<%@page import="objects.AssetRequested"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Acknowledge Asset</title>
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
                            <h4>Property Acknowledgment</h4><br/>
                            <form class="form-horizontal style-form" action="/AMS/InventoryServlet/Acknowledge">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Stock No</th>
                                            <th>Asset Name</th>
                                            <th colspan="2">Asset Details</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<AssetRequested> items = ((PurchaseOrder) session.getAttribute("purchaseOrder")).PurchaseRequest.AssetsRequested;
                                            for (AssetRequested item : items) {
                                                if (item.Asset.AssetType.contains("Equipment")) {
                                                    for (int i = 0; i < item.Quantity; i++) {
                                        %>
                                        <tr>
                                            <td><%=item.Asset.StockNo%></td>
                                            <td><%=item.Asset.AssetName%></td>
                                            <td>
                                                <input type="hidden" name="asset-id" value="<%=item.AssetId%>">
                                                <input class="form-control" name="asset-tag" placeholder="Asset Tag" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="condition" placeholder="Condition" autocomplete="off">
                                            </td>
                                        </tr>
                                        <%
                                                    }
                                                } else {
                                        %>
                                        <tr>
                                            <td><%=item.Asset.StockNo%></td>
                                            <td><%=item.Asset.AssetName%></td>
                                            <td>Quantity Ordered: <%=item.Quantity%></td>
                                        </tr>
                                        <%
                                                }
                                            }
                                        %>
                                    </tbody>
                                </table>
                                <div class="form-group">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button class="btn btn-theme" type="submit">Submit</button>
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

