<%-- 
    Document   : add
    Created on : Oct 24, 2018, 9:30:43 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@page import="objects.AssetRequested"%>
<%@page import="objects.PurchaseOrder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Add Purchase Request</title>
        <jsp:include page="../../shared/css.jsp"/>
    </head>

    <body>
        <section id="container">
            <jsp:include page="../../shared/header.jsp"/>
            <jsp:include page="../../shared/sidebar.jsp"/>
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <div class="row">
                        <div class="form-panel">
                            <%
                                PurchaseOrder purchaseOrder = (PurchaseOrder) session.getAttribute("purchaseOrder");
                            %>
                            <h4>Request For Delivery Inspection</h4><br/>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Stock No</th>
                                        <th>Asset Name</th>
                                        <th>Asset Type</th>
                                        <th>Quantity Ordered</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (AssetRequested asset : purchaseOrder.PurchaseRequest.AssetsRequested) {
                                    %>
                                    <tr>
                                        <td><%=asset.Asset.StockNo%></td>
                                        <td><%=asset.Asset.AssetName%></td>
                                        <td><%=asset.Asset.AssetType%></td>
                                        <td><%=asset.Quantity%></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                            <form class="form-horizontal style-form" action="/AMS/DeliveryInspectionServlet/Submit" enctype="multipart/form-data" method="POST">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Invoice</label>
                                    <div class="col-lg-10">
                                        <input type="file" class="default" name="invoice">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Delivery Receipt</label>
                                    <div class="col-lg-10">
                                        <input type="file" class="default" name="receipt">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">ORS Number</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="orsno" name="orsno" placeholder="ORS Number" autocomplete="off">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">ORS Date</label>
                                    <div class="col-lg-10">
                                        <input type="date" class="form-control" id="orsdate" name="orsdate" placeholder="ORS Date" autocomplete="off">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Assign To</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="assign" name="assign" placeholder="Assign To" list="employee" autocomplete="off">
                                        <datalist id="employee">
                                            <%
                                                ArrayList<Employee> employees = (ArrayList<Employee>) session.getAttribute("employees");
                                                for (Employee employee : employees) {
                                            %>
                                            <option value="<%=employee.FullName()%>">
                                            <%
                                                }
                                            %>
                                        </datalist>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Remarks</label>
                                    <div class="col-lg-10">
                                        <textarea class="form-control" name="remarks"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button class="btn btn-theme" type="submit">Submit</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- /row -->
                </section>
            </section>
            <!--main content end-->
            <jsp:include page="../../shared/footer.jsp"/>
        </section>
    </body>
    <jsp:include page="../../shared/js.jsp"/>
</html>

