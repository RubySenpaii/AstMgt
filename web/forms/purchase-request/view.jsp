<%-- 
    Document   : list
    Created on : Oct 21, 2018, 6:31:23 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.PurchaseRequest"%>
<%@page import="java.util.ArrayList"%>
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
                                ArrayList<PurchaseRequest> PR = (ArrayList<PurchaseRequest>) session.getAttribute("PR");
                                for (PurchaseRequest pr : PR) {
                            %>
                            <h4>Viewing Purchase Request :  <c:out value="<%= pr.PurchaseRequestId%>"></c:out></h4><br/>
                                <div class="form-horizontal style-form" action="#">
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputEmail1">Purchase Request No</label>
                                        <div class="col-lg-10">
                                            <label> <c:out value="<%= pr.PurchaseRequestNo%>"></c:out> </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Responsibility Center Code</label>
                                        <div class="col-lg-10">
                                            <label> <c:out value="<%= pr.ResponsibilityCenterCode%>"></c:out> </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Date</label>
                                        <div class="col-lg-10">
                                            <label>  <c:out value="<%= pr.Date%>"></c:out> </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Purpose</label>
                                        <div class="col-lg-10">
                                            <label> <c:out value="<%= pr.Purpose%>"></c:out> </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Requested By</label>
                                        <div class="col-lg-10">
                                            <label> <c:out value="<%= pr.RequestedBy%>"></c:out> </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Requested Date</label>
                                        <div class="col-lg-10">
                                            <label> <c:out value="<%= pr.RequestedDate%>"></c:out> </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Approved By</label>
                                        <div class="col-lg-10">
                                            <label> <c:out value="<%= pr.ApprovedBy%>"></c:out> </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Approved Date</label>
                                        <div class="col-lg-10">
                                            <label> <c:out value="<%= pr.ApprovedDate%>"></c:out> </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-6" style="text-align: center">
                                            <form action="/AMS/PurchaseOrderServlet/Add">
                                            <%
                                                session.setAttribute("purchaseRequest", pr);
                                            %>
                                            <button class="btn btn-info" type="submit">Approve</button> 
                                        </form>
                                    </div>
                                    <div class="col-lg-6" style="text-align: center">
                                        <button class="btn btn-warning" type="submit">Reject</button> 
                                    </div>
                                </div>
                            </div>
                            <%                                }
                            %>
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
