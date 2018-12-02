 <aside>
    <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
            <li class="mt">
                <a href="/AMS/HomeServlet">
                    <i class="fa"></i>
                    <span>Home Page</span>
                </a>
            </li>
            <li class="sub-menu">
                <a href="/AMS/ExpenditureServlet">
                    <i class="fa"></i>
                    <span>Set Expenditure Limit</span>
                </a>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa"></i>
                    <span>Asset</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/AssetServlet/Add">Add Asset</a></li>
                    <li><a href="/AMS/AssetServlet/List">Asset List</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa"></i>
                    <span>Equipment</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/InventoryServlet/EquipmentList">Equipment List</a></li>
                    <li><a href="/AMS/InventoryServlet/ShowTrackingRequests">Tracking Requests</a></li>
                    <li><a href="/AMS/AssetServlet/RepairRequests">Repair Requests</a></li>
                    <li><a href="/AMS/AssetServlet/LogTracking">Log Transfer</a></li>
                    <li><a href="/AMS/AssetServlet/LogIncident">Log Incident/Disposal</a></li>
                    <li><a href="/AMS/AssetServlet/LogRepair">Log Asset Repair</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="/AMS/InventoryServlet/SuppliesList">
                    <i class="fa"></i>
                    <span>Supplies</span>
                </a>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa"></i>
                    <span>Purchase Request</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/PurchaseRequest/Add">Create Purchase Request</a></li>
                    <li><a href="/AMS/PurchaseRequest/List">Purchase Request List</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa"></i>
                    <span>Purchase Order</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/PurchaseOrderServlet/List">Purchase Order List</a></li>
                    <li><a href="/AMS/DeliveryInspectionServlet/List">Inspection Requests</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa"></i>
                    <span>Report</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/ReportServlet/GeneralPPE">General Equipment</a></li>
                    <li><a href="/AMS/ReportServlet/SpecificPPE">Specific Equipment</a></li>
                    <li><a href="/AMS/ReportServlet/GeneralSupplies">General Supplies</a></li>
                </ul>
            </li>
        </ul>
        <!-- sidebar menu end-->
        <div class="sidebar-footer">
            <a href="/AMS/management/plans.jsp">Upload Plans</a>
        </div>
    </div>

</aside>