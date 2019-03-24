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
                <a href="/AMS/ExpenditureServlet">
                    <i class="fa fa-money"></i>
                    <span>Set Expenditure Limit</span>
                </a>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-book"></i>
                    <span>Asset</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/AssetServlet/Add">Add Asset</a></li>
                    <li><a href="/AMS/AssetServlet/List">Asset List</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-desktop"></i>
                    <span>Supplier</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/SupplierServlet/Add">Add Supplier</a></li>
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
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-reply"></i>
                    <span>Report</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/ReportServlet/GeneralPPE">General Equipment</a></li>
                    <li><a href="/AMS/ReportServlet/SpecificPPE">Specific Equipment</a></li>
                    <li><a href="/AMS/ReportServlet/Expenditure">Expenditure</a></li>
                    <!--<li><a href="/AMS/ReportServlet/GeneralSupplies">General Supplies</a></li> -->
                </ul>
            </li>
        </ul>
        <!-- sidebar menu end-->
        <div class="sidebar-footer">
            <a href="/AMS/management/plans.jsp">Upload Plans</a>
        </div>
    </div>

</aside>