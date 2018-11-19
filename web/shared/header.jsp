<%-- 
    Document   : header
    Created on : Oct 7, 2018, 4:08:46 PM
    Author     : RubySenpaii
--%>

<%@page import="extra.SharedFormat"%>
<%@page import="objects.ExpenditureLimit"%>
<header class="header black-bg">
    <div class="sidebar-toggle-box">
        <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
    </div>
    <!--logo start-->
    <a href="index.html" class="logo"><b>DASH<span>IO</span></b></a>
    <!--logo end-->
    <div class="nav notify-row" id="top_menu">
        <!--  notification start -->
        <ul class="nav top-menu">
            <!-- notification dropdown start-->
            <li id="header_notification_bar" class="dropdown">
                <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                    <i class="fa fa-bell-o"></i>
                    <span class="badge bg-warning">7</span>
                </a>
                <ul class="dropdown-menu extended notification">
                    <div class="notify-arrow notify-arrow-yellow"></div>
                    <li>
                        <p class="yellow">You have 7 new notifications</p>
                    </li>
                    <li>
                        <a href="index.html#">
                            <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                            Server Overloaded.
                            <span class="small italic">4 mins.</span>
                        </a>
                    </li>
                    <li>
                        <a href="index.html#">
                            <span class="label label-warning"><i class="fa fa-bell"></i></span>
                            Memory #2 Not Responding.
                            <span class="small italic">30 mins.</span>
                        </a>
                    </li>
                    <li>
                        <a href="index.html#">
                            <span class="label label-danger"><i class="fa fa-bolt"></i></span>
                            Disk Space Reached 85%.
                            <span class="small italic">2 hrs.</span>
                        </a>
                    </li>
                    <li>
                        <a href="index.html#">
                            <span class="label label-success"><i class="fa fa-plus"></i></span>
                            New User Registered.
                            <span class="small italic">3 hrs.</span>
                        </a>
                    </li>
                    <li>
                        <a href="index.html#">See all notifications</a>
                    </li>
                </ul>
            </li>
            <!-- notification dropdown end -->
            <%
                ExpenditureLimit limit = (ExpenditureLimit) session.getAttribute("limit");
            %>
            <li class="header-limit">
                Equipment Spending Limit: Php <%=SharedFormat.doubleToString(limit.Equipment)%>
            </li>
            <li class="header-limit">
                Supplies Spending Limit: Php <%=SharedFormat.doubleToString(limit.Supplies)%>
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