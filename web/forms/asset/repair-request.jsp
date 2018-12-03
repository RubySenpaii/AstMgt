<%-- 
    Document   : repair-request
    Created on : 11 18, 18, 6:52:44 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.RepairLog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Repair Request</title>
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
                                RepairLog log = (RepairLog) session.getAttribute("repairRequest");
                            %>
                            <h4>Repair Request for Asset Tag :  <c:out value="<%= log.AssetTag%>"></c:out></h4><br/>
                                <div class="form-horizontal style-form" action="#">
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputEmail1">Requested By</label>
                                        <label class="col-lg-10 control-label" > <c:out value="<%= log.Requester.FullName()%>"></c:out> </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Requested Date</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= log.RequestedDate%>"></c:out> </label>
                                    </div>
                                <%
                                    if (log.ApprovedBy > 0) {
                                %>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Approved By</label>
                                    <label class="col-lg-10 control-label">  <c:out value="<%= log.Approver.FullName()%>"></c:out> </label>                                        
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Purpose</label>
                                        <label class="col-lg-10 control-label"> <c:out value="<%= log.ApprovedDate%>"></c:out> </label>
                                    </div>
                                <%
                                } else {
                                %>
                                <div class="form-group">
                                    <div class="col-lg-12" style="text-align: center">
                                        <form action="/AMS/AssetServlet/ApproveRepair">
                                            <button class="btn btn-info" type="submit">Approve</button> 
                                        </form>
                                    </div>
                                </div>
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
