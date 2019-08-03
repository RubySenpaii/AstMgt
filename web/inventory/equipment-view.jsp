<%-- 
    Document   : equipment-view
    Created on : Oct 27, 2018, 11:39:41 AM
    Author     : RubySenpaii
--%>

<%@page import="extra.SharedFormat"%>
<%@page import="objects.RepairLog"%>
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
                        <div class="form-panel form-horizontal style-form" >
                            <%
                                Equipment equipment = (Equipment) session.getAttribute("equipment");
                            %>
                            <button class="pull-right" onclick="printWin()">Print Asset Tag</button>
                            <h4 style="display: none" id="asset-tag"><%=equipment.AssetTag%></h4>
                            <h4><%=equipment.Asset.AssetName + " - " + equipment.AssetTag%></h4><br/>
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
                                <label class="col-lg-2 control-label">Mode of Procurement</label>
                                <label class="col-lg-10 control-label"><%=equipment.ModeOfProcurement%></label>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">Age of Asset</label>
                                <label class="col-lg-10 control-label"><%=equipment.getAge() + " year(s)"%></label>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">Estimated Useful Life</label>
                                <label class="col-lg-10 control-label"><%=equipment.Asset.EstimatedUsefulLife%></label>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">Acquisition Cost</label>
                                <label class="col-lg-10 control-label"><%=SharedFormat.doubleToMoney(equipment.AcquisitionCost)%></label>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">Description</label>
                                <label class="col-lg-10 control-label"><%=equipment.Description.replace("__", "<br>").replace("//", ": ")%></label>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <button class="btn" type="button" onclick="document.getElementById('tracking').style.display = 'block'; document.getElementById('repairs').style.display = 'none'; document.getElementById('incidents').style.display = 'none';">Tracking</button>
                                    <button class="btn" type="button" onclick="document.getElementById('repairs').style.display = 'block'; document.getElementById('tracking').style.display = 'none'; document.getElementById('incidents').style.display = 'none';">Repairs</button>
                                    <button class="btn" type="button" onclick="document.getElementById('incidents').style.display = 'block'; document.getElementById('tracking').style.display = 'none'; document.getElementById('repairs').style.display = 'none';">Incidents</button>
                                </div>
                                <div class="col-lg-12" id="incidents" style="display: none">
                                    <h4>Incidents</h4>
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th style="width: 30%">Timestamp</th>
                                                <th>Severity</th>
                                                <th>Remarks</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (AssetIncident incident : equipment.IncidentLogs) {
                                            %>
                                            <tr>
                                                <td><%=incident.Timestamp%></td>
                                                <td><%=incident.getSeverity()%></td>
                                                <td><%=incident.Remarks%></td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>

                                <div class="col-lg-12" id="repairs" style="display: none">
                                    <h4>Repairs</h4>
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th style="width: 30%">Timestamp</th>
                                                <th>Total Cost</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                double total = 0;
                                                for (RepairLog log : equipment.RepairLogs) {
                                                    total += log.Cost;
                                            %>
                                            <tr>
                                                <td><%=log.RequestedDate%></td>
                                                <td><%=SharedFormat.doubleToMoney(log.TotalCost)%></td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Grand Total</th>
                                                <th><%=total%></th>
                                            </tr>
                                        </tfoot>
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
                                <div class="col-lg-6" style="text-align: center; display: flex">
                                    <%
                                        if (equipment.IncidentLogs.size() > 0 && equipment.getAge() < equipment.Asset.EstimatedUsefulLife) {
                                    %>
                                    <button class="btn btn-info" name="action" data-toggle="modal" data-target="#disposeModal">Dispose</button> 
                                    <!-- Button trigger modal -->

                                    <%
                                        }
                                    %>
                                    <button class="btn btn-info" name="action" data-toggle="modal" data-target="#disposeAndModal" >Dispose and Reacquire</button> 
                                    <%
                                        if (equipment.Flag != 4) {
                                    %>
                                    <button class="btn btn-info" name="action" data-toggle="modal" data-target="#extendModal">Extend</button> 
                                    <%
                                        }
                                    %>
                                    <form role="form" action="/AMS/AssetServlet/EquipmentStatus">
                                        <input type="hidden" name="asset-tag" value="<%=equipment.AssetTag%>">

                                        <!-- Modal -->
                                        <div class="modal fade" id="disposeModal" role="dialog">
                                            <div class="modal-dialog">

                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Confirmation</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <label>Are you sure you want to dispose this item?</label>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button class="btn btn-info" name="action" value="dispose" type="submit">Dispose</button> 
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal fade" id="disposeAndModal" role="dialog">
                                            <div class="modal-dialog">

                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Confirmation</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <label>Are you sure you want to <b style="color: #000">dispose and reaquire </b> this item?</label>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button class="btn btn-info" name="action"  value="reacquire" type="submit">Dispose and </button> 
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="modal fade" id="extendModal" role="dialog">
                                            <div class="modal-dialog">

                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Confirmation</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <label>Are you sure you want to <b style="color: #000"> extend  </b>  this item?</label>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button class="btn btn-info" name="action"  value="extend" type="submit">Extend</button> 
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </form>

                                    <button class="btn btn-info" data-toggle="modal" data-target="#myModal" style="margin-left: 5px">
                                        Donate
                                    </button>


                                    <!-- Modal -->
                                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                    <h4 class="modal-title" id="myModalLabel">Donation Form</h4>
                                                </div>
                                                <form action="/AMS/AssetServlet/Donate">
                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                            <label class="col-lg-2 control-label">Receiver</label>
                                                            <div class="col-lg-10">
                                                                <input type="text" class="form-control" id="purpose" name="receiver" placeholder="Receiver" autocomplete="off">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <input type="hidden" name="asset-tag" value="<%=equipment.AssetTag%>">
                                                        <button type="submit" class="btn btn-success" name="review" value="approve">Donate</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
        function printWin() {
            var w = window.open();
            var html = $('#asset-tag').html();
            
            $(w.document.body).html(html);
            w.print();
        }
    </script>
</html>
