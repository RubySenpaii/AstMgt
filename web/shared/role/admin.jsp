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
                    <span>Personal Asset List</span>
                </a>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-money"></i>
                    <span>Expenditure</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/ExpenditureServlet">Set Expenditure Limit</a></li>
                    <li><a href="/AMS/management/expenditurev2.jsp">Expenditure Limit v2</a></li>
                    <li><a href="/AMS/ExpenditureServlet/BudgetHistory">Budget History</a></li>
                </ul>
            </li>
            <li class="sub-menu">
                <a href="javascript:;">
                    <i class="fa fa-book"></i>
                    <span>Asset Catalog</span>
                </a>
                <ul class="sub">
                    <li><a href="/AMS/AssetServlet/Add">Add Asset </a></li>
                    <li><a href="/AMS/AssetServlet/List">List of Buyable Assets</a></li>
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
                    <span>Asset</span>
                    <%
                        int tracking = (int) session.getAttribute("trackingSize");
                        int repair = (int) session.getAttribute("repairSize");
                    %>
                    <span class="badge bg-warning"><%=tracking + repair%></span>
                </a>
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
                    <li><a href="/AMS/ReportServlet/MonthlyExpenditureTrend">Expenditure Trend</a></li>
                    <li><a href="/AMS/ReportServlet/AssetShares">Asset Share</a></li>
                    <!--<li><a href="/AMS/ReportServlet/GeneralSupplies">General Supplies</a></li> -->
                </ul>
            </li>
        </ul>
    </div>

</aside>
