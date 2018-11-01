<%-- 
    Document   : sidebar
    Created on : Oct 7, 2018, 4:09:47 PM
    Author     : RubySenpaii
--%>

<aside>
    <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
            <li class="mt">
                <a href="index.html">
                    <i class="fa fa-dashboard"></i>
                    <span>Dashboard</span>
                </a>
            </li>
            <li class="sub-menu">
                <a href="/AMS/ExpenditureServlet">
                    <i class="fa fa-dashboard"></i>
                    <span>Set Expenditure Limit</span>
                </a>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-desktop"></i>
                    <span>Asset</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/AssetServlet/Add">Add</a></li>
                    <li><a href="/AMS/AssetServlet/List">List</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-desktop"></i>
                    <span>Purchase Request</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/PurchaseRequest/Add">Create</a></li>
                    <li><a href="/AMS/PurchaseRequest/List">List</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-desktop"></i>
                    <span>Purchase Order</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/PurchaseOrderServlet/List">List</a></li>
                </ul>
            </li>
        </ul>
        <!-- sidebar menu end-->
    </div>
</aside>
