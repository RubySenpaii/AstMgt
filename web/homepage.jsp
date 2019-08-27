<%-- 
    Document   : homepage
    Created on : 11 25, 18, 5:32:35 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.RepairLog"%>
<%@page import="objects.AssetRequested"%>
<%@page import="extra.SharedFormat"%>
<%@page import="objects.AssetIncident"%>
<%@page import="objects.RequestForDeliveryInspection"%>
<%@page import="objects.Employee"%>
<%@page import="objects.PurchaseOrder"%>
<%@page import="objects.Equipment"%>
<%@page import="objects.Supplies"%>
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
                        <%
                            String notification = (String) session.getAttribute("notification");
                            String notif = "";
                            String userRole = "";
                            String userDivision = "";
                            userRole = (String) session.getAttribute("UserLevel");
                            userDivision = (String) session.getAttribute("UserDivision");
                            try {
                                notif = (String) session.getAttribute("notif");
                            } catch (Exception e) {

                            }
                        %>
                        <input type="hidden" id="notif" name="notif" value="<%= notif%>">
                        <% session.removeAttribute("notif");%>
                        <input type="hidden" id="notification" name="notification" value="<%= notification%>">
                        <input type="hidden" id="userRole" name="userRole" value="<%= userRole%>">
                        <input type="hidden" id="userDivision" name="userDivision" value="<%= userDivision%>">
                        <div class="col-md-6" id="PPR">
                            <%
                                if ((!userDivision.equalsIgnoreCase("management") || !userRole.equalsIgnoreCase("Chief Inspector")) && (!userDivision.equalsIgnoreCase("management") || !userRole.equalsIgnoreCase("inspector"))) {
                            %>
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
                                            <td style="text-align: right"><%=SharedFormat.doubleToMoney(pendingRequest.TotalCost)%></td>
                                            <td>
                                                <form action="/AMS/PurchaseRequest/View">
                                                    <button type="submit" value="<%=pendingRequest.PurchaseRequestId%>" name="prid" >View</button>
                                                </form>
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                            <%
                                }
                            %>
                        </div>
                        <%
                            if (!userRole.equalsIgnoreCase("staff") && (!userDivision.equalsIgnoreCase("management") || !userRole.equalsIgnoreCase("Chief Inspector")) && (!userDivision.equalsIgnoreCase("management") || !userRole.equalsIgnoreCase("inspector"))) {
                        %>
                        <div class="col-md-6" id="APR">
                            <div class="form-panel">
                                <h3>Approved Purchase Requests With No Purchase Order</h3>
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
                                            <td style="text-align: right"><%=SharedFormat.doubleToMoney(approvedRequest.TotalCost)%></td>
                                            <td>
                                                <form action="/AMS/PurchaseRequest/View">
                                                    <button type="submit" value="<%=approvedRequest.PurchaseRequestId%>" name="prid" >View</button>
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
                        <%
                            }
                        %>
                    </div>
                    <div class="row">
                        <%
                            if (!userRole.equalsIgnoreCase("staff") && (!userDivision.equalsIgnoreCase("general") && !userRole.equalsIgnoreCase("Division Chief"))) {
                        %>
                        <div class="col-md-6" id="EE">
                            <div class="form-panel">
                                <h3>Expiring Equipments</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Asset Name</th>
                                            <th>Date Acquired</th>
                                            <th>Estimated Useful Life</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Equipment> equipments = (ArrayList<Equipment>) session.getAttribute("expiringEquipments");
                                            for (Equipment equipment : equipments) {
                                        %>
                                        <tr>
                                            <td><%=equipment.Asset.AssetName%></td>
                                            <td><%=equipment.DateAcquired%></td>
                                            <td><%=equipment.Asset.EstimatedUsefulLife%></td>
                                            <td>
                                                <form action="/AMS/InventoryServlet/EquipmentView">
                                                    <button type="submit" name="asset-tag" value="<%=equipment.AssetTag%>">View</button>
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
                        <%
                            }
                        %>
                        <%
                            if ((!userDivision.equalsIgnoreCase("management") || !userRole.equalsIgnoreCase("Chief Inspector")) && (!userDivision.equalsIgnoreCase("management") || !userRole.equalsIgnoreCase("inspector"))) {
                        %>
                        <div class="col-md-6" id="RPR">
                            <div class="form-panel">
                                <h3>Rejected Purchase Requests</h3>
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
                                            ArrayList<PurchaseRequest> rejectedRequests = (ArrayList<PurchaseRequest>) session.getAttribute("rejectedPurchaseRequests");
                                            for (PurchaseRequest rejectedRequest : rejectedRequests) {
                                        %>
                                        <tr>
                                            <td><%=rejectedRequest.PurchaseRequestNo%></td>
                                            <td><%=rejectedRequest.Requester.FullName()%></td>
                                            <td style="text-align: right"><%=SharedFormat.doubleToMoney(rejectedRequest.TotalCost)%></td>
                                            <td>
                                                <form action="/AMS/PurchaseRequest/View">
                                                    <button type="submit" value="<%=rejectedRequest.PurchaseRequestId%>" name="prid" >View</button>
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
                        <%
                            }
                        %>
                    </div>
                    <!-- /row -->
                    <div class="row">
                        <%
                            if (!userRole.equalsIgnoreCase("staff") && (!userDivision.equalsIgnoreCase("general") && !userRole.equalsIgnoreCase("Division Chief"))) {
                        %>
                        <div class="col-md-6" id="UED" >
                            <div class="form-panel">
                                <h3>Upcoming Expected Delivery</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Purchase Order Number</th>
                                            <th>Expected Delivery Date</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<PurchaseOrder> purchaseOrderDelivering = (ArrayList<PurchaseOrder>) session.getAttribute("purchaseOrderDelivering");
                                            for (PurchaseOrder purchaseOrder : purchaseOrderDelivering) {
                                        %>
                                        <tr>
                                            <td><%=purchaseOrder.PurchaseOrderNumber%></td>
                                            <td><%=purchaseOrder.DeliveryDate%></td>
                                            <td>
                                                <form action="/AMS/PurchaseOrderServlet/View">
                                                    <button type="submit" name="poId" value="<%=purchaseOrder.PurchaseOrderId%>">View</button>
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
                        <%
                            }
                        %>
                        <%
                            if (!userRole.equalsIgnoreCase("staff") && (!userDivision.equalsIgnoreCase("general") && !userRole.equalsIgnoreCase("Division Chief")) && (!userDivision.equalsIgnoreCase("management") || !userRole.equalsIgnoreCase("Chief Inspector")) && userRole.equalsIgnoreCase("custodian")) {
                        %>
                        <div class="col-md-6" id="RE">
                            <div class="form-panel">
                                <h3>Retiring Employees</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Employee Name</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Employee> retiringEmployees = (ArrayList<Employee>) session.getAttribute("retiringEmployees");
                                            for (Employee employee : retiringEmployees) {
                                        %>
                                        <tr>
                                            <td><a href="/AMS/InventoryServlet/EmployeeEquipment?employeeId=<%=employee.EmployeeId%>"><%=employee.FullName()%></a></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                    <div class="row">
                        <div class="col-md-6" id="PA">
                            <div class="form-panel">
                                <h3>Pending Acknowledgment</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Purchase Order No</th>
                                            <th>Unique Items Ordered</th>
                                            <th>Delivery Inspector</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<RequestForDeliveryInspection> pendingInspections = (ArrayList<RequestForDeliveryInspection>) session.getAttribute("pendingInspections");
                                            for (RequestForDeliveryInspection pendingInspection : pendingInspections) {
                                        %>
                                        <tr>
                                            <td><%=pendingInspection.PurchaseOrder.PurchaseOrderNumber%></td>
                                            <td><%=pendingInspection.PurchaseOrder.PurchaseRequest.AssetsRequested.size()%></td>
                                            <td><%=pendingInspection.Assigned.FullName()%></td>
                                            <%
                                                if (userRole.equalsIgnoreCase("Inspector")) {
                                            %>

                                            <td>
                                                <form>
                                                    <button class="btn" formaction="/AMS/InventoryServlet/AcknowledgementRequest" name="requestId" value="<%=pendingInspection.DeliveryInspectionId%>">Acknowledge</button>
                                                </form>
                                            </td>
                                            <%
                                                }
                                            %>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-md-6" id="PRDI">
                            <div class="form-panel">
                                <h3>Pending Request For Delivery Inspection</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Purchase Order No</th>
                                            <th>Unique Items Ordered</th>
                                            <th>Created By</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<PurchaseOrder> poNoRfi = (ArrayList<PurchaseOrder>) session.getAttribute("poNoInspection");
                                            for (PurchaseOrder poNoRf : poNoRfi) {
                                        %>
                                        <tr>
                                            <td><%=poNoRf.PurchaseOrderNumber%></td>
                                            <td><%=poNoRf.PurchaseRequest.AssetsRequested.size()%></td>
                                            <td><%=poNoRf.PurchaseRequest.Requester.FullName()%></td>
                                            <%
                                                if (userRole.equalsIgnoreCase("Chief Inspector")) {
                                            %>
                                            <td>
                                                <form>
                                                    <button class="btn btn-theme" type="submit" formaction="/AMS/DeliveryInspectionServlet/Request" name="purchaseOrder" value="<%=poNoRf.PurchaseOrderId%>">Request Inspection</button>
                                                </form>
                                            </td>
                                            <%
                                                }
                                            %>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <%
                        Employee employee = (Employee) session.getAttribute("user");
                        if (employee.UserLevel.equals("Inspector")) {
                    %>
                    <div class="row">
                        <div class="col-md-6" id="PAI">
                            <div class="form-panel">
                                <h3>Pending Asset Incidents</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Asset Tag</th>
                                            <th>Remarks</th>
                                            <th>Reported By</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<AssetIncident> pendingAssetIncidents = (ArrayList<AssetIncident>) session.getAttribute("pendingAssetIncidents");
                                            for (AssetIncident pendingAssetIncident : pendingAssetIncidents) {
                                        %>
                                        <tr>
                                            <td><%=pendingAssetIncident.AssetTag%></td>
                                            <td><%=pendingAssetIncident.Remarks%></td>
                                            <td><%=pendingAssetIncident.Reporter.FullName()%></td>
                                            <td>
                                                <a href="/AMS/AssetServlet/IncidentSubmission?assetTag=<%=pendingAssetIncident.AssetTag%>&timestamp=<%=pendingAssetIncident.Timestamp%>" class="btn">View</a>
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
                    <%
                    } else if (employee.UserLevel.equals("Custodian")) {
                    %>
                    <div class="row">
                        <div class="col-md-6" id="TA">
                            <div class="form-panel">
                                <h3>Temporary Assets</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Asset Tag</th>
                                            <th>Asset Name</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Equipment> tempEquipments = (ArrayList<Equipment>) session.getAttribute("tempEquip");
                                            for (Equipment tempEquip : tempEquipments) {
                                        %>
                                        <tr>
                                            <td><%=tempEquip.AssetTag%></td>
                                            <td><%=tempEquip.Asset.AssetName%></td>
                                            <td>
                                                <a href="/AMS/InventoryServlet/EquipmentView?asset-tag=<%=tempEquip.AssetTag%>">View</a>
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-md-6" id="ARR">
                            <div class="form-panel">
                                <h3>Approved Repair Requests</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Asset Tag</th>
                                            <th>Asset Name</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<RepairLog> repairEquip = (ArrayList<RepairLog>) session.getAttribute("rlogsz");
                                            int i = 0;
                                            for (RepairLog tempEquip : repairEquip) {
                                        %>
                                        <tr>
                                            <td><%=tempEquip.AssetTag%></td>
                                            <td><%=tempEquip.AssetName%></td>
                                            <td>
                                                <a href="/AMS/AssetServlet/RepairRequest2?index=<%=i%>">View</a>
                                            </td>
                                        </tr>
                                        <%
                                                    i++;
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <%
                    } else if (employee.UserLevel.equals("Division Chief") && employee.Division.equals("General")) {
                    %>
                    <div class="row">
                        <div class="col-md-6" id="RL">
                            <div class="form-panel">
                                <h3>Refund List</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Purchase Order Number</th>
                                            <th>Asset Name</th>
                                            <th>Quantity Ordered</th>
                                            <th>Quantity Delivered</th>
                                            <th>Quantity Refunded</th>
                                            <th>Unit Cost</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            double total = 0;
                                            ArrayList<AssetRequested> refundList = (ArrayList<AssetRequested>) session.getAttribute("refundList");
                                            for (AssetRequested ar : refundList) {
                                                total += (ar.UnitCost * ar.QuantityRefunded);
                                        %>
                                        <tr>
                                            <td><%=ar.PurchaseOrderNumber%></td>
                                            <td><%=ar.AssetName%></td>
                                            <td><%=ar.Quantity%></td>
                                            <td><%=ar.Quantity - ar.QuantityRefunded%></td>
                                            <td><%=ar.QuantityRefunded%></td>
                                            <td><%=ar.UnitCost%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td colspan="5">Total</td>
                                            <td style="text-align: right"><%=SharedFormat.doubleToMoney(total)%></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </section>
            </section>
            <!--main content end-->
            <jsp:include page="shared/footer.jsp"/>
        </section>
    </body>
    <jsp:include page="shared/js.jsp"/>
    <script>
        $(document).ready(function () {
            console.log('ready');
            var notification = document.getElementById("notification");
            var arrayNotif = ["PPR", "RPR", "APR", "EE", "UED", "RE", "PA", "PRDI", "PAI", "TA", "ARR", "RL"];
            for (var notif of arrayNotif) {
                if (notification.value === 'None') {
                    break;
                }
                if (notification.value === notif) {
                    console.log('will stay')
                } else {
                    var hiddener = "#";
                    hiddener += notif;
                    console.log(hiddener);
                    $(hiddener).prop("hidden", true);
                }
            }
            var notif = document.getElementById("notif");
            if (notif.value === 'true') {
                alert("Successfully saved the expenditure limit v2 !");
            } else if (notif.value === 'false') {
                alert("Failed to save the expenditure limit v2 !");
            }

        });
    </script>
</html>
