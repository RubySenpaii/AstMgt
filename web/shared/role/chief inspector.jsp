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
                    <span>Asset Catalog</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/AssetServlet/Add">Add Asset</a></li>
                    <li><a href="/AMS/AssetServlet/List">List of buyable assets</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-paperclip"></i>
                    <span>Purchase Request</span>
                </a>
                <ul class="sub">
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