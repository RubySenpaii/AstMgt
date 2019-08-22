<%@page import="objects.Employee"%>
<aside>
    <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
            <li class="mt">
                <a href="/AMS/HomeServlet">
                    <i class="fa fa-dashboard"></i>
                    <span>Home Page</span>
                </a>
            </li>
            <li class="sub-menu">
                <%
                    Employee employee = (Employee) session.getAttribute("user");
                        int tracking = (int) session.getAttribute("trackingSize");
                        int repair = (int) session.getAttribute("repairSize");
                %>
                <a href="/AMS/InventoryServlet/EmployeeEquipment?employeeId=<%=employee.EmployeeId%>">
                    <i class="fa fa-dashboard"></i>
                    <span>Retiring Employee Asset List</span>
                </a>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-book"></i>
                    <span>Asset Catalog </span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/AssetServlet/List">List of Buyable Asset</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-desktop"></i>
                    <span>Supplier</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/SupplierServlet/List">Supplier List</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <ul class="sub">
                    <li><a href="/AMS/InventoryServlet/EquipmentHistory">Asset History</a></li>
                    <li><a href="/AMS/InventoryServlet/EquipmentList">Asset List</a></li>
                    <li>
                        <a href="/AMS/InventoryServlet/ShowTrackingRequests">
                            Transfer Requests <span class="badge bg-warning"><%=tracking%></span>
                        </a>
                    </li>
                    <li>
                        <a href="/AMS/AssetServlet/RepairRequests">
                            Repair Requests <span class="badge bg-warning"><%=repair%></span>
                        </a>
                    </li>
                    <li><a href="/AMS/AssetServlet/LogTracking">Log Transfer</a></li>
                    <li><a href="/AMS/AssetServlet/LogIncident">Log Incident</a></li>
                    <li><a href="/AMS/AssetServlet/LogRepair">Log Asset Repair</a></li>
                </ul>
            </li>
        </ul>
    </div>

</aside>