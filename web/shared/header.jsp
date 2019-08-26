<%-- 
    Document   : header
    Created on : Oct 7, 2018, 4:08:46 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.AssetRequested"%>
<%@page import="objects.RepairLog"%>
<%@page import="objects.AssetIncident"%>
<%@page import="objects.RequestForDeliveryInspection"%>
<%@page import="objects.ExpenditureLimit"%>
<%@page import="objects.PurchaseOrder"%>
<%@page import="objects.Equipment"%>
<%@page import="objects.Supplies"%>
<%@page import="objects.PurchaseRequest"%>
<%@page import="java.util.ArrayList"%>
<%@page import="objects.Employee"%>
<%@page import="extra.SharedFormat"%>
<%@page import="objects.ExpenditureTracking"%>
<header class="header black-bg">
    <div class="sidebar-toggle-box">
        <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
    </div>
    <!--logo start-->
    <a href="index.html" class="logo"><b>DAR<span>AMIS</span></b></a>
    <!--logo end-->
    <div class="nav notify-row" id="top_menu">
        <!--  notification start -->
        <ul class="nav top-menu">
            <!-- notification dropdown start-->
            <li id="header_notification_bar" class="dropdown">
                <%
                    int pendingPurchaseRequests = ((ArrayList<PurchaseRequest>) session.getAttribute("pendingPurchaseRequests")).size();
                    int approvedPurchaseRequests = ((ArrayList<PurchaseRequest>) session.getAttribute("approvedPurchaseRequests")).size();
                    int expiringEquipment = ((ArrayList<Equipment>) session.getAttribute("expiringEquipments")).size();
                    int rejectedPurchaseRequests = ((ArrayList<PurchaseRequest>) session.getAttribute("rejectedPurchaseRequests")).size();
                    int expectedDelivery = ((ArrayList<PurchaseOrder>) session.getAttribute("purchaseOrderDelivering")).size();
                    int retiringEmployees = ((ArrayList<Employee>) session.getAttribute("retiringEmployees")).size();
                    int pendingInspections = ((ArrayList<RequestForDeliveryInspection>) session.getAttribute("pendingInspections")).size();
                    int poNoPoSize = ((ArrayList<PurchaseOrder>) session.getAttribute("poNoInspection")).size();
                    int pendingIncidents = ((ArrayList<AssetIncident>) session.getAttribute("pendingAssetIncidents")).size();
                    int tempEquipSize = ((ArrayList<Equipment>) session.getAttribute("tempEquip")).size();
                    int rlogsz = ((ArrayList<RepairLog>) session.getAttribute("rlogsz")).size();
                    int refunds = ((ArrayList<AssetRequested>) session.getAttribute("refundList")).size();
                %>
                <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                    <i class="fa fa-bell-o"></i>
                    <span class="badge bg-warning"><%=pendingPurchaseRequests + approvedPurchaseRequests + expiringEquipment + expectedDelivery + rejectedPurchaseRequests%></span>
                </a>
                <ul class="dropdown-menu extended notification">
                    <%
                    Employee user = (Employee) session.getAttribute("user");
                    %>
                    <div class="notify-arrow notify-arrow-yellow"></div>
                    <li>
                        <p class="yellow">You have <%=pendingPurchaseRequests + approvedPurchaseRequests + expiringEquipment%> new notifications</p>
                    </li>
                    <%
                        if (user.UserLevel.equals("Finance") || user.UserLevel.equals("Director") || user.UserLevel.equals("Division Chief")|| user.UserLevel.equals("Staff") || user.UserLevel.equals("Custodian")) {
                    %>
                    <li>
                        <a href="/AMS/HomeServlet/PPR">
                            <%=pendingPurchaseRequests%> Pending Purchase Requests
                        </a>
                    </li>
                    <% 
                    }
                    %>
                    <%
                        if (user.UserLevel.equals("Finance") || user.UserLevel.equals("Director") || user.UserLevel.equals("Division Chief") || user.UserLevel.equals("Custodian") ) {
                    %>
                    <li>
                        <a href="/AMS/HomeServlet/APR">
                            <%=approvedPurchaseRequests%> Approved PR But No PO
                        </a>
                    </li>
                    <% 
                    }
                    %>
                    <%
                        if (user.UserLevel.equals("Finance") || user.UserLevel.equals("Director") || user.UserLevel.equals("Chief Inspector") || user.UserLevel.equals("Inspector") || user.UserLevel.equals("Custodian")) {
                    %>
                    <li>
                        <a href="/AMS/HomeServlet/EE">
                            <%=expiringEquipment%> Equipments Expiring
                        </a>
                    </li>
                    <% 
                    }
                    %>
                    <%
                        if (user.UserLevel.equals("Finance") || user.UserLevel.equals("Director") || user.UserLevel.equals("Division Chief")|| user.UserLevel.equals("Staff") || user.UserLevel.equals("Custodian")) {
                    %>
                    <li>
                        <a href="/AMS/HomeServlet/RPR">
                            <%=rejectedPurchaseRequests%> Rejected Purchase Requests
                        </a>
                    </li>
                    <% 
                    }
                    %>
                    <%
                        if (user.UserLevel.equals("Finance") || user.UserLevel.equals("Director") || user.UserLevel.equals("Chief Inspector")|| user.UserLevel.equals("Inspector") || user.UserLevel.equals("Custodian")) {
                    %>
                    <li>
                        <a href="/AMS/HomeServlet/UED">
                            <%=expectedDelivery%> Upcoming Equipment Deliveries
                        </a>
                    </li>
                    <% 
                    }
                    %>
                    <%
                        if (user.UserLevel.equals("Custodian")) {
                    %>
                    <li>
                        <a href="/AMS/HomeServlet/RE">
                            <%=retiringEmployees%> Retiring Employee
                        </a>
                    </li>
                    <% 
                    }
                    %>
                    <li>
                        <a href="/AMS/HomeServlet/PA">
                            <%=pendingInspections%> Pending Acknowledgment
                        </a>
                    </li>
                    <li>
                        <a href="/AMS/HomeServlet/PRDI">
                            <%=poNoPoSize%> Pending Request for Delivery Inspection
                        </a>
                    </li>
                    <%
                        if (user.UserLevel.equals("Inspector")) {
                    %>
                    <li>
                        <a href="/AMS/HomeServlet/PAI">
                            <%=pendingIncidents%> Pending Asset Incident
                        </a>
                    </li>
                    <%
                        } else if (user.UserLevel.equals("Custodian")) {
                    %>
                    <li>
                        <a href="/AMS/HomeServlet/TA">
                            <%=tempEquipSize%> Temporary Asset
                        </a>
                    </li>
                    <li>
                        <a href="/AMS/HomeServlet/ARR">
                            <%=rlogsz%> Approved Repair Requests
                        </a>
                    </li>
                    <%
                        } else if (user.UserLevel.equals("Division Chief") && user.Division.equals("General")) {
                    %>
                    <li>
                        <a href="/AMS/HomeServlet/RL">
                           <%=refunds%> Refund List
                        </a>
                    </li>
                    <%
                        }
                    %>
                </ul>
            </li>
            <!-- notification dropdown end -->
            <%
                ExpenditureTracking limit = (ExpenditureTracking) session.getAttribute("limit");
                String display = user.FullName() + " - " + user.Division + ": " + user.UserLevel;
                ExpenditureLimit xlimit = (ExpenditureLimit) session.getAttribute("xlimit");
                String bcolor = "white";
                if (xlimit.Equipment * 0.1 >= limit.Equipment) {
                    bcolor = "red";
                }
            %>
            <li class="fill-header"></li>
            <%
                if (limit.Equipment > 0) {
            %>
            <li class="header-limit" style="color: <%=bcolor%>">
                Equipment Spending Limit for <%=SharedFormat.getQuarter()%>: Php <%=SharedFormat.doubleToString(limit.Equipment)%>
            </li>
            <%
                }
            %>
<!--            <li class="header-limit">
                Supplies Spending Limit: Php <%=SharedFormat.doubleToString(limit.Supplies)%>
            </li>-->
            <li class="header-limit" style="text-align: right">
                <%=display%>
            </li>
        </ul>
        <!--  notification end -->
    </div>
    <div class="top-menu">
        <ul class="nav pull-right top-menu">
            <li><a class="logout" href="/AMS/login.jsp">Logout</a></li>
        </ul>
    </div>
</header>
