<%-- 
    Document   : list
    Created on : Oct 21, 2018, 6:31:23 PM
    Author     : RubySenpaii
--%>

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
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.Requester.FullName() %>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Requester's Division</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.Requester.Division %>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Requester's Employee Status</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.Requester.EmployeeStatus %>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Requested Date</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.RequestedDate%>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Approved By</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= pr.Approver.FullName() %>"></c:out> </label>
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
                                                        <th>Quantity</th>
                                                        <th>Price</th>
                                                    </tr>
                                                </thead>
                                            <%
                                                ArrayList<AssetRequested> ar = (ArrayList<AssetRequested>) session.getAttribute("assetRequested");
                                                ArrayList<String> assetNames = (ArrayList<String>) session.getAttribute("assetNames");
                                                for (int i = 0; i < ar.size(); i++) {

                                            %>
                                            <tbody>
                                                <tr>
                                                    <td><c:out value="<%= assetNames.get(i)%>"></c:out></td>
                                                    <td><c:out value="<%= ar.get(i).Quantity%>"></c:out></td>
                                                    <td><c:out value="<%= ar.get(i).UnitCost%>"></c:out></td>
                                                    </tr>
                                                </tbody>

                                            <%                                                }
                                            %>
                                        </table>
                                    </div>
                                </div>
                                <%
                                    if (pr.ApprovedBy == 0) {
                                %>
                                <div class="form-group">
                                    <div class="col-lg-6" style="text-align: center">
                                        <form action="/AMS/PurchaseOrderServlet/Add">
                                            <button class="btn btn-info" name="prid" value="<%= pr.PurchaseRequestId%>" type="submit">Approve</button> 
                                        </form>
                                    </div>
                                    <div class="col-lg-6" style="text-align: center">
                                        <button class="btn btn-warning" type="submit">Reject</button> 
                                    </div>

                                    <%
                                    } else {
                                    %>
                                    <form action="/AMS/PurchaseOrderServlet/GoToPO">
                                        <button class="btn btn-info" name="prid" value="<%= pr.PurchaseRequestId%>" type="submit">Purchase Order</button> 
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
</html>
