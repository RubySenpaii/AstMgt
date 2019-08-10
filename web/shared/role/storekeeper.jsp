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
                %>
                <a href="/AMS/InventoryServlet/EmployeeEquipment?employeeId=<%=employee.EmployeeId%>">
                    <i class="fa fa-dashboard"></i>
                    <span>Personal Equipment</span>
                </a>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-book"></i>
                    <span>Asset Catalog </span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/AssetServlet/List">List of buyable asset</a></li>
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
                <a href="javascript:;">
                    <i class="fa fa-expand"></i>
                    <span>Equipment</span>
                    <%
                        int tracking = (int) session.getAttribute("trackingSize");
                        int repair = (int) session.getAttribute("repairSize");
                    %>
                    <span class="badge bg-warning"><%=tracking + repair%></span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/InventoryServlet/EquipmentList">Equipment List</a></li>
                </ul>
            </li>
        </ul>
    </div>

</aside>