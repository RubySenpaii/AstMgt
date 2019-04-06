<%-- 
    Document   : tracking-requests
    Created on : 12 1, 18, 5:56:52 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.AssetIncident"%>
<%@page import="objects.AssetTracking"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - List of Transfer Requests</title>
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
                        <div class="col-md-12">
                            <div class="content-panel">
                                <h4>List of Transfer Requests</h4>
                                <br/>
                                <table class="table" id="requestList">
                                    <thead>
                                        <tr>
                                            <th>Requested By</th>
                                            <th>Requested Date</th>
                                            <th>Transfer To</th>
                                            <th>Remarks</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<AssetTracking> trackings = (ArrayList<AssetTracking>) session.getAttribute("assetTrackings");
                                            int i = 0;
                                            for (AssetTracking tracking : trackings) {
                                        %>
                                        <tr>
                                            <td><%=tracking.ReleaseBy.FullName()%></td>
                                            <td><%=tracking.TransferDate%></td>
                                            <td><%=tracking.ReleaseTo.FullName()%></td>
                                            <td><%=tracking.Remarks%></td>
                                            <td>
                                                <!-- Button trigger modal -->
                                                <button class="btn btn-success" data-toggle="modal" data-target="#myModal">
                                                    View
                                                </button>
                                                <!-- Modal -->
                                                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                                <h4 class="modal-title" id="myModalLabel">Asset History</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <h3>Asset History Logs</h3>
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
                                                                            for (AssetTracking log : tracking.Trackings) {
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

                                                                <h3>Asset Incident Logs</h3>
                                                                <table class="table table-bordered">
                                                                    <thead>
                                                                        <tr>
                                                                            <th style="width: 20%">Timestamp</th>
                                                                            <th>Remarks</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <%
                                                                            for (AssetIncident log : tracking.Incidents) {
                                                                        %>
                                                                        <tr>
                                                                            <td><%=log.Timestamp%></td>
                                                                            <td><%=log.Remarks%></td>
                                                                        </tr>
                                                                        <%
                                                                            }
                                                                        %>
                                                                    </tbody>
                                                                </table>

                                                                <h3>Receiver's Asset(s)</h3>
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
                                                                            ArrayList<AssetTracking> assets = tracking.EmployeeAssets;
                                                                            for (int j = 0; j < assets.size(); j++) {
                                                                        %>
                                                                        <tr>
                                                                            <td><%=assets.get(j).Equipment.Asset.AssetName%></td>
                                                                            <td><%=assets.get(j).AssetTag%></td>
                                                                            <td><%=assets.get(j).Equipment.Asset.AssetType%></td>
                                                                        </tr>
                                                                        <%
                                                                            }
                                                                        %>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <%
                                                                String userRole = "";
                                                                userRole = (String) session.getAttribute("UserLevel");
                                                                if (userRole.equalsIgnoreCase("Director")) {
                                                            %>
                                                            <div class="modal-footer">
                                                                <form action="/AMS/InventoryServlet/ReviewTracking">
                                                                    <input type="hidden" name="idx" value="<%=i%>">
                                                                    <button type="submit" class="btn btn-success" name="review" value="approve">Approve</button>
                                                                    <button type="submit" class="btn btn-danger" name="review" value="reject">Reject</button>
                                                                </form>
                                                            </div>
                                                            <%
                                                                        }
                                                             %>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <%
                                                i++;
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
    <script>
        $(document).ready(function () {
            $('#requestList').DataTable();
        });
    </script>
</html>
