<%-- 
    Document   : equipment-view
    Created on : Oct 27, 2018, 11:39:41 AM
    Author     : RubySenpaii
--%>

<%@page import="objects.AssetTracking"%>
<%@page import="objects.AssetIncident"%>
<%@page import="objects.Equipment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Equipment View</title>
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
                            <%
                                Equipment equipment = (Equipment) session.getAttribute("equipment");
                            %>
                            <h4><%=equipment.Asset.AssetName + " - " + equipment.AssetTag%></h4><br/>
                            <form role="form" class="form-horizontal style-form" action="/AMS/AssetServlet/EquipmentStatus">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Description</label>
                                    <label class="col-lg-10 control-label"><%=equipment.Asset.Description%></label>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Condition</label>
                                    <label class="col-lg-10 control-label"><%=equipment.Condition%></label>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Status</label>
                                    <label class="col-lg-10 control-label"><%=equipment.Status()%></label>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Date Acquired</label>
                                    <label class="col-lg-10 control-label"><%=equipment.DateAcquired%></label>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Estimated Useful Life</label>
                                    <label class="col-lg-10 control-label"><%=equipment.Asset.EstimatedUsefulLife%></label>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Description</label>
                                    <label class="col-lg-10 control-label"><%=equipment.Description.replace("__", "<br>").replace("//", ": ")%></label>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-12">
                                        <button class="btn" type="button" onclick="document.getElementById('tracking').style.display = 'block'; document.getElementById('repairs').style.display = 'none';">Tracking</button>
                                        <button class="btn" type="button" onclick="document.getElementById('repairs').style.display = 'block'; document.getElementById('tracking').style.display = 'none';">Repairs</button>
                                    </div>
                                    <div class="col-lg-12" id="repairs" style="display: none">
                                        <h4>Incidents</h4>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th style="width: 30%">Timestamp</th>
                                                    <th>Remarks</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    for (AssetIncident incident : equipment.IncidentLogs) {
                                                %>
                                                <tr>
                                                    <td><%=incident.Timestamp%></td>
                                                    <td><%=incident.Remarks%></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div class="col-lg-12" id="tracking" style="display: none">
                                        <h4>Tracking</h4>
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>
                                                    <th style="width: 20%">From</th>
                                                    <th style="width: 15%">Transfer Date</th>
                                                    <th style="width: 20%">To</th>
                                                    <th>Remarks</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    for (AssetTracking log : equipment.TrackingLogs) {
                                                %>
                                                <tr>
                                                    <td><%=log.ReleaseBy.FullName()%></td>
                                                    <td><%=log.TransferDate%></td>
                                                    <td><%=log.ReleaseTo.FullName()%></td>
                                                    <td><%=log.Remarks%></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-6" style="text-align: center">
                                        <input type="hidden" name="asset-tag" value="<%=equipment.AssetTag%>">
                                        <button class="btn btn-info" name="action" value="dispose" type="submit">Dispose</button> 
                                        <button class="btn btn-info" name="action" value="reacquire" type="submit">Dispose and Reacquire</button> 
                                        <button class="btn btn-info" name="action" value="extend" type="submit">Extend</button> 
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
</html>
