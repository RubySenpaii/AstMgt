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
        <title>Asset Management - List of Delivery Inspections</title>
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
                        <%
                            Boolean isSaved = (Boolean) session.getAttribute("notif");
                             Boolean isApproved = (Boolean) session.getAttribute("approveNotif");
                        %>
                        <input type="hidden" id="notif" name="notif" value="<%= isSaved%>">
                        <input type="hidden" id="anotif" name="anotif" value="<%= isApproved%>">
                        <%
                            session.removeAttribute("Notification");
                            session.removeAttribute("approveNotif");
                        %>
                        <div class="col-md-12">
                            <div class="content-panel">
                                <h4>List of Requests For Delivery Inspection</h4>
                                <br/>
                                <table class="table" id="delList">
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
                                                <%
                                                    String userRole = "";
                                                    String userDivision = "";
                                                    userRole = (String) session.getAttribute("UserLevel");
                                                    userDivision = (String) session.getAttribute("UserDivision");
                                                    if ((!userRole.equalsIgnoreCase("staff"))) {
                                                %>
                                                <form>
                                                    <input type="hidden" name="requestId" value="<%=requestInspection.DeliveryInspectionId%>">
                                                    <%
                                                        if (status.equals("Pending")) {
                                                            if (userRole.equalsIgnoreCase("Division Chief")) {
                                                                if (userDivision.toLowerCase().equalsIgnoreCase("management")) {


                                                    %>
                                                    <button class="btn btn-success" name="action" value="Approve" formaction="/AMS/DeliveryInspectionServlet/Review" type="submit">Approve</button>
                                                    <%                                                            }
                                                        }
                                                    } else if (userRole.equalsIgnoreCase("Inspector")) {
                                                    %>
                                                    <button class="btn" formaction="/AMS/InventoryServlet/AcknowledgementRequest">Acknowledge</button>
                                                    <%
                                                        }
                                                    %>
                                                </form>
                                                <%
                                                    }
                                                %>
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
    <script>
        $(document).ready(function () {
            $('#delList').DataTable({
                "order":[]
            });
        });

        var notif = document.getElementById("notif");
        if (notif.value === 'true') {
            alert("Successfully requested for delivery inspection!")
        } else if (notif.value === 'false') {
            alert("Failed requested for delivery inspection!")
        }
        
        var anotif = document.getElementById("anotif");
        if (anotif.value === 'true') {
            alert("Approved Request Inspection!")
        } else if (aotif.value === 'false') {
            alert("Failed tp approved Request Inspection!")
        }
    </script>
</html>
