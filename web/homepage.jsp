<%-- 
    Document   : homepage
    Created on : 11 25, 18, 5:32:35 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.PurchaseRequest"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Home Page</title>
        <jsp:include page="/shared/css.jsp"/>
    </head>

    <body>
        <section id="container">
            <jsp:include page="shared/header.jsp"/>
            <jsp:include page="shared/sidebar.jsp"/>
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-panel">
                                <h3>Pending Purchase Requests</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Purchase Request Number</th>
                                            <th>Requested By</th>
                                            <th>Total Cost</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<PurchaseRequest> pendingRequests = (ArrayList<PurchaseRequest>) session.getAttribute("pendingPurchaseRequests");
                                            for (PurchaseRequest pendingRequest : pendingRequests) {
                                        %>
                                        <tr>
                                            <td><%=pendingRequest.PurchaseRequestNo%></td>
                                            <td><%=pendingRequest.Requester.FullName()%></td>
                                            <td>Php 21231</td>
                                            <td></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-panel">
                                <h3>Approved Purchase Requests</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Purchase Request Number</th>
                                            <th>Requested By</th>
                                            <th>Total Cost</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<PurchaseRequest> approvedRequests = (ArrayList<PurchaseRequest>) session.getAttribute("approvedPurchaseRequests");
                                            for (PurchaseRequest approvedRequest : approvedRequests) {
                                        %>
                                        <tr>
                                            <td><%=approvedRequest.PurchaseRequestNo%></td>
                                            <td><%=approvedRequest.Requester.FullName()%></td>
                                            <td>Php 21231</td>
                                            <td></td>
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
            <jsp:include page="shared/footer.jsp"/>
        </section>
    </body>
    <jsp:include page="shared/js.jsp"/>
</html>
