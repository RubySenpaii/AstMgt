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
                    <i class="fa fa-expand"></i>
                    <span>Asset</span>
                    <%
                        int tracking = (int) session.getAttribute("trackingSize");
                        int repair = (int) session.getAttribute("repairSize");
                    %>
                    <span class="badge bg-warning"><%=tracking + repair%></span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/InventoryServlet/EquipmentList">Equipment List</a></li>
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
                    <li><a href="/AMS/AssetServlet/LogTracking">Transfer</a></li>
                    <li><a href="/AMS/AssetServlet/LogIncident">Log Incident</a></li>
                    <li><a href="/AMS/AssetServlet/LogRepair">Log Asset Repair</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-paperclip"></i>
                    <span>Purchase Request</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/PurchaseRequest/Add">Create Purchase Request</a></li>
                    <li><a href="/AMS/PurchaseRequest/List">Purchase Request List</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-paper-plane"></i>
                    <span>Purchase Order</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/PurchaseOrderServlet/List">Purchase Order List</a></li>
                    <li><a href="/AMS/DeliveryInspectionServlet/List">Inspection Requests</a></li>
                </ul>
            </li>
        </ul>
        <!-- sidebar menu end-->
        <div class="sidebar-footer">
            <a href="/AMS/management/plans.jsp">Upload Plans</a>
        </div>
    </div>

</aside>