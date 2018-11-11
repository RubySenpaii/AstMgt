<%-- 
    Document   : list
    Created on : Oct 24, 2018, 9:36:10 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.RequestForDeliveryInspection"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Add Purchase Request</title>
        <jsp:include page="../../shared/css.jsp"/>
    </head>

    <body>
        <section id="container">
            <jsp:include page="../../shared/header.jsp"/>
            <jsp:include page="../../shared/sidebar.jsp"/>
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-panel">
                                <h4>List of Requests For Delivery Inspection</h4>
                                <br/>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Purchase Order No</th>
                                            <th>Items Ordered</th>
                                            <th>Status</th>
                                            <th>Created By</th>
                                            <th>Created Date</th>
                                            <th>Assigned To</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<RequestForDeliveryInspection> requests = (ArrayList<RequestForDeliveryInspection>) session.getAttribute("requests");
                                            for (RequestForDeliveryInspection requestInspection : requests) {
                                                String status = "Approved";
                                                if (requestInspection.ApprovedBy == 0) {
                                                    status = "Pending";
                                                }
                                        %>
                                        <tr>
                                            <td><%=requestInspection.PurchaseOrder.PurchaseOrderNumber%></td>
                                            <td><%=requestInspection.PurchaseOrder.PurchaseRequest.AssetsRequested.size()%></td>
                                            <td><%=status%></td>
                                            <td><%=requestInspection.Creator.FullName()%></td>
                                            <td><%=requestInspection.CreatedDate%></td>
                                            <td><%=requestInspection.Assigned.FullName()%></td>
                                            <td>
                                                <form>
                                                    <input type="hidden" name="requestId" value="<%=requestInspection.DeliveryInspectionId%>">
                                                    <%
                                                        if (status.equals("Pending")) {
                                                    %>
                                                    <button class="btn btn-success" name="action" value="Approve" formaction="/AMS/DeliveryInspectionServlet/Review" type="submit">Approve</button>
                                                    <%
                                                    } else {
                                                    %>
                                                    <button class="btn" formaction="/AMS/InventoryServlet/AcknowledgementRequest">Acknowledge</button>
                                                    <%
                                                        }
                                                    %>
                                                </form>
                                            </td>
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
            <jsp:include page="../../shared/footer.jsp"/>
        </section>
    </body>
    <jsp:include page="../../shared/js.jsp"/>
</html>
