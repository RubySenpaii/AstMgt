<%-- 
    Document   : list
    Created on : Oct 21, 2018, 6:31:23 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.PurchaseOrder"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - List Purchase Order</title>
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
                        <div>
                            <div class="content-panel">
                                <h4>List of Purchase Order</h4>
                                <br/>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>PurchaseOrderId</th>
                                            <th>PurchaseRequestId</th>
                                            <th>PurchaseOrderNo</th>
                                            <th>SupplierId</th>
                                            <th>OrderDate</th>
                                            <th>ModeOfProcurement</th>
                                            <th>Remarks</th>
                                            <th>DeliveryAddress</th>
                                            <th>DeliveryDate</th>
                                            <th>DeliveryTerms</th>
                                            <th>PaymentTerms</th>
                                            <th>ConformeSupplier</th>
                                            <th>ConformeDate</th>
                                            <th>AuthorizedBy</th>
                                            <th>ApprovedBy</th>
                                            <th>ApprovedDate</th>
                                            <th>ORSNumber</th>
                                            <th>ORSDate</th>
                                            <th>ReceivedBy</th>
                                            <th>ReceivedDate</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<PurchaseOrder> PO = (ArrayList<PurchaseOrder>) session.getAttribute("PO");
                                            for (PurchaseOrder po : PO) {
                                        %>
                                        <tr>
                                            <td><c:out value="<%= po.PurchaseOrderId %>"></c:out></td>
                                            <td><c:out value="<%= po.PurchaseRequestId %>"></c:out></td>
                                            <td><c:out value="<%= po.PurchaseOrderNumber %>"></c:out></td>
                                            <td><c:out value="<%= po.SupplierId %>"></c:out></td>
                                            <td><c:out value="<%= po.OrderDate %>"></c:out></td>
                                            <td><c:out value="<%= po.ModeOfProcurement %>"></c:out></td>
                                            <td><c:out value="<%= po.Remarks %>"></c:out></td>
                                            <td><c:out value="<%= po.DeliveryAddress %>"></c:out></td>
                                            <td><c:out value="<%= po.DeliveryDate %>"></c:out></td>
                                            <td><c:out value="<%= po.DeliveryTerms %>"></c:out></td>
                                            <td><c:out value="<%= po.PaymentTerm %>"></c:out></td>
                                            <td><c:out value="<%= po.ConformeSupplier %>"></c:out></td>
                                            <td><c:out value="<%= po.ConformeDate %>"></c:out></td>
                                            <td><c:out value="<%= po.AuthorizedBy %>"></c:out></td>
                                            <td><c:out value="<%= po.ApprovedBy %>"></c:out></td>
                                            <td><c:out value="<%= po.ApprovedDate %>"></c:out></td>
                                            <td><c:out value="<%= po.ORSNumber %>"></c:out></td>
                                            <td><c:out value="<%= po.ORSDate %>"></c:out></td>
                                            <td><c:out value="<%= po.ReceivedBy %>"></c:out></td>
                                            <td><c:out value="<%= po.ReceivedDate %>"></c:out></td>
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
</html>
