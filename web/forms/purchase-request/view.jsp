<%-- 
    Document   : list
    Created on : Oct 21, 2018, 6:31:23 PM
    Author     : RubySenpaii
--%>

<%@page import="extra.SharedFormat"%>
<%@page import="objects.AssetTracking"%>
<%@page import="objects.ExpenditureTracking"%>
<%@page import="java.util.ArrayList"%>
<%@page import="objects.AssetRequested"%>
<%@page import="objects.PurchaseRequest"%>
<%@page import="objects.Equipment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - View Purchase Request</title>
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
                        <div class="col-md-7">
                            <div class="form-panel">
                                <%
                                    PurchaseRequest pr = (PurchaseRequest) session.getAttribute("purchaseRequest");
                                %>
                                <h4>Viewing Purchase Request :  <c:out value="<%= pr.PurchaseRequestId%>"></c:out>
                                        <a href="/AMS/PurchaseRequest/List" class="view-back btn-info">View Purchase Request List</a></h4><br/>
                                    <div class="form-horizontal style-form" action="#">
                                        <div class="form-group" style="width: 100%">
                                            <label class="col-lg-2 control-label" for="exampleInputEmail1">Purchase Request No</label>
                                            <label class="col-lg-10 control-label" > <c:out value="<%= pr.PurchaseRequestNo%>"></c:out> </label>
                                        </div>
                                        <div class="form-group" style="width: 100%">
                                            <label class="col-lg-2 control-label" for="exampleInputPassword1">Date</label>
                                            <label class="col-lg-10 control-label">  <c:out value="<%= pr.Date%>"></c:out> </label>                                        
                                        </div>
                                        <div class="form-group" style="width: 100%">
                                            <label class="col-lg-2 control-label" for="exampleInputPassword1">Purpose</label>
                                            <label class="col-lg-10 control-label"> <c:out value="<%= pr.Purpose%>"></c:out> </label>
                                        </div>
                                        <div class="form-group" style="width: 100%">
                                            <label class="col-lg-2 control-label" for="exampleInputPassword1">Requested By</label>
                                            <label class="col-lg-10 control-label"> <c:out value="<%= pr.Requester.FullName()%>"></c:out> </label>
                                        </div>
                                        <div class="form-group" style="width: 100%">
                                            <label class="col-lg-2 control-label" for="exampleInputPassword1">Requester's Division</label>
                                            <label class="col-lg-10 control-label"> <c:out value="<%= pr.Requester.Division%>"></c:out> </label>
                                        </div>
                                        <div class="form-group" style="width: 100%">
                                            <label class="col-lg-2 control-label" for="exampleInputPassword1">Requester's Employee Status</label>
                                            <label class="col-lg-10 control-label"> <c:out value="<%= pr.Requester.EmployeeStatus%>"></c:out> </label>
                                        </div>
                                        <div class="form-group" style="width: 100%">
                                            <label class="col-lg-2 control-label" for="exampleInputPassword1">Requested Date</label>
                                            <label class="col-lg-10 control-label"> <c:out value="<%= pr.RequestedDate%>"></c:out> </label>
                                        </div>
                                        <div class="form-group" style="width: 100%">
                                            <label class="col-lg-2 control-label" for="exampleInputPassword1">Approved By</label>
                                            <label class="col-lg-10 control-label"> <c:out value="<%= pr.Approver.FullName()%>"></c:out> </label>
                                        </div>
                                        <div class="form-group" style="width: 100%">
                                            <label class="col-lg-2 control-label" for="exampleInputPassword1">Approved Date</label>
                                            <label class="col-lg-10 control-label"> <c:out value="<%= pr.ApprovedDate%>"></c:out> </label>
                                        </div>
                                        <div class="form-group" style="width: 100%">
                                            <label class="col-lg-2 control-label" for="exampleInputPassword1">Remarks</label>
                                            <label class="col-lg-10 control-label"> <c:out value="<%= pr.Remarks%>"></c:out> </label>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-12 control-label">
                                                <h3>Assets Requested</h3>
                                                <table style="width:100%" class="table table-bordered">
                                                    <thead>
                                                        <tr>
                                                            <th>Asset</th>
                                                            <th>Asset Type</th>
                                                            <th>Quantity</th>
                                                            <th>Price</th>
                                                        </tr>
                                                    </thead>
                                                <%
                                                    ExpenditureTracking limit = (ExpenditureTracking) session.getAttribute("limit");
                                                    ArrayList<AssetRequested> ar = (ArrayList<AssetRequested>) session.getAttribute("assetRequested");
                                                    double sum = 0;
                                                    for (int i = 0; i < ar.size(); i++) {

                                                %>
                                                <tbody>
                                                    <tr>
                                                        <td><c:out value="<%= ar.get(i).Asset.AssetName%>"></c:out></td>
                                                        <td><c:out value="<%= ar.get(i).Asset.AssetType%>"></c:out></td>
                                                        <td style="text-align: right"><c:out value="<%= ar.get(i).Quantity%>"></c:out></td>
                                                        <td style="text-align: right"><c:out value="<%= SharedFormat.doubleToMoney(ar.get(i).UnitCost)%>"></c:out></td>
                                                        </tr>
                                                    </tbody>

                                                <%
                                                        sum += ar.get(i).getTotalCost();
                                                    }
                                                %>
                                                <tfoot>
                                                    <tr>
                                                        <th colspan="3">Total</th>
                                                        <th id="sum" value="<%= sum%>" style="text-align: right"><%= SharedFormat.doubleToMoney(sum)%></th>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                    <%
                                        String userRole = "";
                                        String userName = "";
                                        String userDivision = "";
                                        userRole = (String) session.getAttribute("UserLevel");
                                        userName = (String) session.getAttribute("UserName");
                                        userDivision = (String) session.getAttribute("UserDivision");
                                        boolean editable = false;
                                        try {
                                            String test = pr.Remarks;
                                            editable = true;
                                        } catch (NullPointerException e) {
                                            editable = false;
                                        }
                                    %>

                                    <%
                                        if (pr.ApprovedBy == 0 && (!userRole.equalsIgnoreCase("staff")) && pr.PurchaseOrderNumber != -1) {
                                    %>

                                    <div class="form-group">
                                        <div class="col-lg-6" style="text-align: center">
                                            <form action="/AMS/PurchaseOrderServlet/Add">
                                                <input type="hidden" id="asset-type" value="<%= ar.get(0).Asset.AssetType%>">
                                                <input type="hidden" id="supplies-limit" value="<%= limit.Supplies%>">
                                                <input type="hidden" id="equipment-limit" value="<%= limit.Equipment%>">
                                                <button class="btn btn-info" id="prid" name="prid" value="<%= pr.PurchaseRequestId%>" type="submit">Approve</button> 
                                            </form>
                                        </div>
                                            <%
                                                if (!userRole.equalsIgnoreCase("finance")){
                                            %>
                                        <div class="col-lg-6" style="text-align: center">
                                            <button class="btn btn-info" name="prid" value="<%= pr.PurchaseRequestId%>" data-toggle="modal" data-target="#myModal" type="submit">Reject</button> 
                                        </div>
                                        <%
                                            }
                                            %>

                                        <%

                                            }
                                            if (pr.ApprovedBy != 0 && pr.ApprovedDate != null && !pr.Requester.FullName().equalsIgnoreCase(userName) && (!userRole.equalsIgnoreCase("finance")) && (!userDivision.equalsIgnoreCase("management") || !userRole.equalsIgnoreCase("inspector"))) {
                                        %>
                                        <form action="/AMS/PurchaseOrderServlet/GoToPO">
                                            <button class="btn btn-info" name="prid" value="<%= pr.PurchaseRequestId%>" type="submit"><span class="fa fa-plus">  Purchase Order </span></button> 
                                        </form>
                                        <%
                                        } else if (editable && pr.ApprovedBy != 0 && pr.ApprovedDate == null && (!userRole.equalsIgnoreCase("finance"))) {
                                        %>


                                        <form action="/AMS/PurchaseRequest/Redit">
                                            <button class="btn btn-danger" name="edit" value="<%= pr.PurchaseRequestId%>" type="submit"><span class="fa fa-plus">  Edit </span></button> 
                                        </form>

                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                            <!-- Modal -->
                            <div class="modal fade" id="myModal" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Reaspns for Rejection</h4>
                                        </div>
                                        <form action="/AMS/PurchaseRequest/Reject">
                                            <div class="modal-body">
                                                <select class="form-control" name="reason">
                                                    <option>Stock Available on Custodian</option>
                                                    <option>Asset is Existing in Requestor's Inventory</option>
                                                    <option>Wrong Details</option>
                                                    <option>Low on Budget - Not Priority</option>
                                                </select>
                                                <input type="text" class="form-control" name="remarks" placeholder="Remarks" id="remarks">
                                            </div>
                                            <div class="modal-footer">
                                                <button class="btn btn-info" id="prid" name="prid" value="<%= pr.PurchaseRequestId%>" type="submit">Reject</button> 
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button onclick="document.getElementById('asset').style.display = 'block'; document.getElementById('requester').style.display = 'hidden';">Asset</button>
                        <button onclick="document.getElementById('requester').style.display = 'block'; document.getElementById('asset').style.display = 'hidden';">Requester</button>
                        <div class="col-md-4" id="asset" style="display: none">
                            <div class="form-panel">
                                <h3>Assets</h3>
                                <table class="table table-bordered" id="equipList">
                                    <thead>
                                        <tr>
                                            <th>Asset Name</th>
                                            <th>Asset Tag</th>
                                            <th>Current User</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Equipment> equipments = (ArrayList<Equipment>) session.getAttribute("equipments");
                                            for (Equipment equipment : equipments) {
                                        %>
                                        <tr>
                                            <td><%=equipment.Asset.AssetName%></td>
                                            <td><%=equipment.AssetTag%></td>
                                            <td><%=equipment.CurrentUser.FullName()%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-md-4" id="requester" style="display: none"> 
                            <div class="form-panel">
                                <h3>Requester's Asset(s)</h3>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Asset Name</th>
                                            <th>Asset Tag</th>
                                            <th>Asset Type</th>
                                        </tr
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<AssetTracking> assets = (ArrayList<AssetTracking>) session.getAttribute("requesterAssets");
                                            for (int i = 0; i < assets.size(); i++) {
                                        %>
                                        <tr>
                                            <td><%=assets.get(i).Equipment.Asset.AssetName%></td>
                                            <td><%=assets.get(i).AssetTag%></td>
                                            <td><%=assets.get(i).Equipment.Asset.AssetType%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
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
    <script type='text/javascript'>

        $(document).ready(function () {
            $('#equipList').DataTable({
                "order": []
            });
        });

        $(function () {
            var equipLimit = document.getElementById('equipment-limit').value;
            var suppLimit = document.getElementById('supplies-limit').value;
            var sum = $('#sum').text();
            var assetType = document.getElementById('asset-type').value;
            console.log(equipLimit);
            console.log(suppLimit);
            console.log(sum);
            console.log(assetType);
            if (assetType.includes('Equipment')) {
                if (Number(sum) > Number(equipLimit)) {
                    $('#prid').prop("disabled", true);
                }
            }
            if (assetType.includes('Supplies')) {
                if (Number(sum) > Number(suppLimit)) {
                    console.log('went here');
                    $('#prid').prop("disabled", true);
                }
            }

        });
    </script>
</html>
