<%-- 
    Document   : header
    Created on : Oct 7, 2018, 4:08:46 PM
    Author     : RubySenpaii
--%>

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
                %>
                <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                    <i class="fa fa-bell-o"></i>
                    <span class="badge bg-warning"><%=pendingPurchaseRequests + approvedPurchaseRequests + expiringEquipment%></span>
                </a>
                <ul class="dropdown-menu extended notification">
                    <div class="notify-arrow notify-arrow-yellow"></div>
                    <li>
                        <p class="yellow">You have <%=pendingPurchaseRequests + approvedPurchaseRequests + expiringEquipment%> new notifications</p>
                    </li>
                    <li>
                        <a href="/AMS/HomeServlet">
                            <%=pendingPurchaseRequests%> Pending Purchase Requests
                        </a>
                    </li>
                    <li>
                        <a href="/AMS/HomeServlet">
                            <%=approvedPurchaseRequests%> Approved PR But No PO
                        </a>
                    </li>
                    <li>
                        <a href="/AMS/HomeServlet">
                            <%=expiringEquipment%> Equipments Expiring
                        </a>
                    </li>
                </ul>
            </li>
            <!-- notification dropdown end -->
            <%
                ExpenditureTracking limit = (ExpenditureTracking) session.getAttribute("limit");
                Employee user = (Employee) session.getAttribute("user");
                String display = user.FullName() + " - " + user.Division;
            %>
            <li class="fill-header"></li>
            <li class="header-limit">
                Equipment Spending Limit: Php <%=SharedFormat.doubleToString(limit.Equipment)%>
            </li>
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