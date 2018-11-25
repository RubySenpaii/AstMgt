<%-- 
    Document   : list
    Created on : Oct 21, 2018, 6:31:23 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.ExpenditureTracking"%>
<%@page import="java.util.ArrayList"%>
<%@page import="objects.AssetRequested"%>
<%@page import="objects.PurchaseRequest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - List Purchase Request</title>
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
                                PurchaseRequest pr = (PurchaseRequest) session.getAttribute("purchaseRequest");
                            %>
                            <h4>Viewing Purchase Request :  <c:out value="<%= pr.PurchaseRequestId%>"></c:out></h4><br/>
                                <div class="form-horizontal style-form" action="#">
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputEmail1">Purchase Request No</label>
                                        <label class="col-lg-10 control-label" > <c:out value="<%= pr.PurchaseRequestNo%>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Date</label>
                                        <label class="col-lg-10 control-label">  <c:out value="<%= pr.Date%>"></c:out> </label>                                        
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Purpose</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.Purpose%>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Requested By</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.Requester.FullName()%>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Requester's Division</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.Requester.Division%>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Requester's Employee Status</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.Requester.EmployeeStatus%>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Requested Date</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.RequestedDate%>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Approved By</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.Approver.FullName()%>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Approved Date</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.ApprovedDate%>"></c:out> </label>
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
                                                    <td><c:out value="<%= ar.get(i).Quantity%>"></c:out></td>
                                                    <td><c:out value="<%= ar.get(i).UnitCost%>"></c:out></td>
                                                    </tr>
                                                </tbody>

                                            <%
                                                    sum += ar.get(i).getTotalCost();
                                                }
                                            %>
                                            <tfoot>
                                                <tr>
                                                    <th colspan="3">Total</th>
                                                    <th id="sum" value="<%= sum%>"><%= sum%></th>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                                <%
                                    if (pr.ApprovedBy == 0) {
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
                                    <div class="col-lg-6" style="text-align: center">
                                        <form action="/AMS/PurchaseRequest/Reject">
                                            <button class="btn btn-info" name="prid" value="<%= pr.PurchaseRequestId%>" type="submit">Reject</button> 
                                        </form>
                                    </div>

                                    <%
                                        }
                                        if (pr.ApprovedBy != 0 && pr.ApprovedDate != null) {
                                    %>
                                    <form action="/AMS/PurchaseOrderServlet/GoToPO">
                                        <button class="btn btn-info" name="prid" value="<%= pr.PurchaseRequestId%>" type="submit"><span class="fa fa-plus">  Purchase Order </span></button> 
                                    </form>
                                    <%
                                        }
                                    %>
                                </div>
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
                if (sum > equipLimit) {
                    $('#prid').prop("disabled", true);
                }
            }
            if (assetType.includes('Supplies')) {
                if (sum > suppLimit) {
                    $('#prid').prop("disabled", true);
                }
            }

        });
    </script>
</html>
