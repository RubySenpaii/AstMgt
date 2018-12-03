<%-- 
    Document   : view
    Created on : 11 1, 18, 7:56:19 PM
    Author     : rubysenpaii
--%>

<%@page import="extra.SharedFormat"%>
<%@page import="objects.AssetRequested"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="objects.PurchaseOrder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - View Purchase Order</title>
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
                            <h4>Purchase Order #<%=purchaseOrder.PurchaseOrderNumber%></h4><br/>
                            <form class="form-horizontal style-form">
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Purchase Request Number</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= purchaseOrder.PurchaseRequest.PurchaseRequestNo %>" ></c:out></label>
                                    </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Supplier Name</label>
                                    <label class="col-lg-10 control-label"> <c:out value="<%= purchaseOrder.Supplier.SupplierName%>" ></c:out></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Mode Of Procurement</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= purchaseOrder.ModeOfProcurement%>" ></c:out></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Remarks</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= purchaseOrder.Remarks%>" ></c:out></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Delivery Address</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= purchaseOrder.DeliveryAddress %>" ></c:out></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Delivery Date</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= purchaseOrder.DeliveryDate%>" ></c:out></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Delivery Terms</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= purchaseOrder.DeliveryTerms%>" ></c:out></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Payment Terms</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= purchaseOrder.PaymentTerm%>" ></c:out></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Supplier Point Person</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= purchaseOrder.ConformeSupplier%>" ></c:out></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">ORS Number</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= purchaseOrder.ORSNumber%>" ></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">ORS Date</label>
                                        <label class="col-lg-10 control-label"> 
                                            <%
                                                if (!purchaseOrder.ORSNumber.equals("")) {
                                            %>
                                            <%=purchaseOrder.ORSDate%>
                                            <%
                                                }
                                            %>
                                        </label>
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
                                                SharedFormat sf = new SharedFormat();
                                                double sum = 0;
                                                for (int i = 0; i < ar.size(); i++) {

                                            %>
                                            <tbody>
                                                <tr>
                                                    <td><c:out value="<%= assetNames.get(i)%>"></c:out></td>
                                                    <td style="text-align: right"><c:out value="<%= ar.get(i).Quantity%>"></c:out></td>
                                                    <td style="text-align: right"><c:out value="<%= sf.doubleToString(ar.get(i).UnitCost)%>"></c:out></td>
                                                    </tr>
                                                </tbody>

                                            <%
                                                    sum += ar.get(i).UnitCost;
                                                }
                                            %>
                                            <tfoot>
                                                <tr>
                                                    <th>Total</th>
                                                    <td></td>
                                                    <th style="text-align: right"><%= sf.doubleToString(sum)%></th>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button class="btn btn-theme" type="submit" formaction="/AMS/DeliveryInspectionServlet/Request">Request Inspection</button>
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
