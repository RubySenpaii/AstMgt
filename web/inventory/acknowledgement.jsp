<%-- 
    Document   : acknowledgement
    Created on : Oct 27, 2018, 12:11:52 AM
    Author     : RubySenpaii
--%>

<%@page import="java.util.Date"%>
<%@page import="extra.SharedFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="objects.Employee"%>
<%@page import="objects.PurchaseOrder"%>
<%@page import="objects.AssetRequested"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Acknowledge Asset</title>
        <jsp:include page="../shared/css.jsp"/>
    </head>

    <body>
        <section id="container">
            <jsp:include page="../shared/header.jsp"/>
            <jsp:include page="../shared/sidebar.jsp"/>
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <div class="row">
                        <div class="form-panel">
                            <h4>Property Acknowledgment</h4><br/>
                            <form class="form-horizontal style-form" action="/AMS/InventoryServlet/UpdateItem" style="padding-bottom: 50px;">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Asset Name</th>
                                            <th>Ordered Quantity</th>
                                            <th>Quantity Delivered</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<AssetRequested> items = ((PurchaseOrder) session.getAttribute("purchaseOrder")).PurchaseRequest.AssetsRequested;
                                            for (AssetRequested item : items) {
                                        %>
                                        <tr>
                                            <td><%=item.Asset.AssetName%></td>
                                            <td><%=item.Quantity%></td>
                                            <td><input name="newQuantity"></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                                <button class="btn btn-danger" type="submit">Reject</button>
                                <button class="btn btn-success pull-right" type="submit">Update</button>
                            </form>
                            <form class="form-horizontal style-form" action="/AMS/InventoryServlet/Acknowledge">
                                <table class="table table-bordered">
                                    <thead>
                                        <%
                                            if (items.get(0).Asset.AssetType.toLowerCase().contains("furniture")) {
                                        %>
                                        <tr>
                                            <th>Stock No</th>
                                            <th>Asset Name</th>
                                            <th>Property Tag</th>
                                            <th>Condition</th>
                                            <th>Color</th>
                                            <th>Office Destination</th>
                                            <th>Warranty</th>
                                            <th>End User</th>
                                        </tr>
                                        <%
                                        } else if (items.get(0).Asset.AssetType.toLowerCase().contains("vehicle")) {
                                        %>
                                        <tr>
                                            <th>Stock No</th>
                                            <th>Asset Name</th>
                                            <th>Property Tag</th>
                                            <th>Condition</th>
                                            <th>Engine Number</th>
                                            <th>Chassis Number</th>
                                            <th>Make</th>
                                            <th>Model</th>
                                            <th>Year</th>
                                            <th>Color</th>
                                            <th>Warranty</th>
                                            <th>Additional Info</th>
                                            <th>End User</th>
                                        </tr>
                                        <%
                                        } else if (items.get(0).Asset.AssetType.toLowerCase().contains("appliance")) {
                                        %>
                                        <tr>
                                            <th>Stock No</th>
                                            <th>Asset Name</th>
                                            <th>Property Tag</th>
                                            <th>Condition</th>
                                            <th>Brand</th>
                                            <th>Model</th>
                                            <th>Color</th>
                                            <th>Warranty</th>
                                            <th>End User</th>
                                        </tr>
                                        <%
                                        } else if (items.get(0).Asset.AssetType.toLowerCase().contains("electronics")) {
                                        %>
                                        <tr>
                                            <th>Stock No</th>
                                            <th>Asset Name</th>
                                            <th>Property Tag</th>
                                            <th>Condition</th>
                                            <th>Brand</th>
                                            <th>Model</th>
                                            <th>Serial Number</th>
                                            <th>Warranty</th>
                                            <th>Build Number</th>
                                            <th>Color</th>
                                            <th>End User</th>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </thead>
                                    <datalist id="employee-choices">
                                    <%
                                        ArrayList<Employee> employees = (ArrayList<Employee>) session.getAttribute("employeeList");
                                        for (Employee employee : employees) {
                                    %>
                                    <option value="<%=employee.FullName()%>">
                                    <%
                                        }
                                    %>
                                    </datalist>
                                    <tbody>
                                        <%
                                            for (AssetRequested item : items) {
                                                String assetName = item.Asset.AssetName;
                                                String autofill = assetName.replace("a", "").replace("e", "").replace("i", "").replace("o", "").replace("u", "").toUpperCase().replace(" ", "");
                                                String propertyTag = String.valueOf(item.Asset.AssetType.split(" ")[0].charAt(0));
                                                SharedFormat sf = new SharedFormat();
                                                propertyTag += String.valueOf(item.Asset.AssetType.split(" ")[1].charAt(0));
                                                propertyTag += "-" + new java.sql.Date(System.currentTimeMillis());
                                                propertyTag += "-" + autofill + "-" + item.PurchaseRequestId + "00";
                                                if (item.Asset.AssetType.toLowerCase().contains("furniture")) {
                                                    for (int i = 0; i < item.Quantity; i++) {
                                        %>
                                        <tr>
                                            <td><%=item.Asset.StockNo%></td>
                                            <td><%=item.Asset.AssetName%></td>
                                            <td>
                                                <input type="hidden" name="asset-id" value="<%=item.AssetId%>">
                                                <input class="form-control" name="asset-tag" placeholder="Property Tag" autocomplete="off" value="<%=propertyTag + i%>">
                                            </td>
                                            <td>
                                                <select class="form-control" name="condition" placeholder="Condition" autocomplete="off">
                                                    <option>Brand New</option>
                                                    <option>2nd Hand</option>
                                                </select>
                                            </td>
                                            <td>
                                                <input class="form-control" name="color" placeholder="Color" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="office-destination" placeholder="Office Destination" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="warranty" placeholder="Warranty" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="end-user" list="employee-choices">
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        } else if (item.Asset.AssetType.toLowerCase().contains("vehicle")) {
                                            for (int i = 0; i < item.Quantity; i++) {
                                        %>
                                        <tr>
                                            <td><%=item.Asset.StockNo%></td>
                                            <td><%=item.Asset.AssetName%></td>
                                            <td>
                                                <input type="hidden" name="asset-id" value="<%=item.AssetId%>">
                                                <input class="form-control" name="asset-tag" placeholder="Property Tag" autocomplete="off" value="<%=propertyTag + i%>">
                                            </td>
                                            <td>
                                                <select class="form-control" name="condition" placeholder="Condition" autocomplete="off" id="condition">
                                                    <option>Brand New</option>
                                                    <option>2nd Hand</option>
                                                </select>
                                            </td>
                                            <td>
                                                <input class="form-control" name="engine-number" placeholder="Engine Number" autocomplete="off" required>
                                            </td>
                                            <td>
                                                <input class="form-control" name="chassis-number" placeholder="Chassis Number" autocomplete="off" required>
                                            </td>
                                            <td>
                                                <input class="form-control" name="make" placeholder="Make" autocomplete="off" required>
                                            </td>
                                            <td>
                                                <input class="form-control" name="model" placeholder="Model" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="year" placeholder="Year" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="color" placeholder="Color" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="warranty" placeholder="Warranty" autocomplete="off">
                                            </td>
                                            <td>
                                                <div class="toggler">
                                                    <div class="toggle-form" id="bnew">
                                                        <input name="conduction-sticker" placeholder="Conduction Sticker">
                                                    </div>
                                                    <div class="toggle-form" id="sec-hand" style="display: none">
                                                        <input name="or" placeholder="Official Receipt">
                                                        <input name="cr" placeholder="Certificate of Registration">
                                                        <input name="plate" placeholder="Plate Number">
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <input class="form-control" name="end-user" list="employee-choices">
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        } else if (item.Asset.AssetType.toLowerCase().contains("appliance")) {
                                            for (int i = 0; i < item.Quantity; i++) {
                                        %>
                                        <tr>
                                            <td><%=item.Asset.StockNo%></td>
                                            <td><%=item.Asset.AssetName%></td>
                                            <td>
                                                <input type="hidden" name="asset-id" value="<%=item.AssetId%>">
                                                <input class="form-control" name="asset-tag" placeholder="Property Tag" autocomplete="off" value="<%=propertyTag + i%>">
                                            </td>
                                            <td>
                                                <select class="form-control" name="condition" placeholder="Condition" autocomplete="off">
                                                    <option>Brand New</option>
                                                    <option>2nd Hand</option>
                                                </select>
                                            </td>
                                            <td>
                                                <input class="form-control" name="brand" placeholder="Brand" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="model" placeholder="Model" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="color" placeholder="Color" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="warranty" placeholder="Warranty" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="end-user" list="employee-choices">
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        } else if (item.Asset.AssetType.toLowerCase().contains("electronics")) {
                                            for (int i = 0; i < item.Quantity; i++) {
                                        %>
                                        <tr>
                                            <td><%=item.Asset.StockNo%></td>
                                            <td><%=item.Asset.AssetName%></td>
                                            <td>
                                                <input type="hidden" name="asset-id" value="<%=item.AssetId%>">
                                                <input class="form-control" name="asset-tag" placeholder="Property Tag" autocomplete="off" value="<%=propertyTag + i%>">
                                            </td>
                                            <td>
                                                <select class="form-control" name="condition" placeholder="Condition" autocomplete="off">
                                                    <option>Brand New</option>
                                                    <option>2nd Hand</option>
                                                </select>
                                            </td>
                                            <td>
                                                <input class="form-control" name="brand" placeholder="Brand" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="model" placeholder="Model" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="serial-number" placeholder="Serial Number" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="warranty" placeholder="Warranty" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="build-number" placeholder="Build Number" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="color" placeholder="Color" autocomplete="off">
                                            </td>
                                            <td>
                                                <input class="form-control" name="end-user" list="employee-choices">
                                            </td>
                                        </tr>
                                        <%
                                                    }
                                                }
                                            }
                                        %>
                                    </tbody>
                                </table>
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
            <jsp:include page="../shared/footer.jsp"/>
        </section>
    </body>
    <jsp:include page="../shared/js.jsp"/>

    <script>
        $(function () {
            $('#condition').change(function () {
                if ($(this).val() == 'Brand New') {
                    $('#bnew').show();
                    $('#sec-hand').hide();
                } else {
                    $('#bnew').hide();
                    $('#sec-hand').show();
                }
            });
        });
    </script>
</html>

