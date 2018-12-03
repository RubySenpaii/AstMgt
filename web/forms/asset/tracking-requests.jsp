<%-- 
    Document   : tracking-requests
    Created on : 12 1, 18, 5:56:52 PM
    Author     : rubysenpaii
--%>

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
                                <h4>List of Repair Requests</h4>
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
                                                i++;
                                        %>
                                        <tr>
                                            <td><%=tracking.ReleaseBy.FullName()%></td>
                                            <td><%=tracking.TransferDate%></td>
                                            <td><%=tracking.ReleaseTo.FullName()%></td>
                                            <td><%=tracking.Remarks%></td>
                                            <td>
                                                <form action="/AMS/InventoryServlet/ReviewTracking">
                                                    <input type="hidden" name="idx" value="<%=i%>">
                                                    <button type="submit" class="btn btn-success" name="review" value="approve">Approve</button>
                                                    <button type="submit" class="btn btn-danger" name="review" value="reject">Reject</button>
                                                </form>
                                            </td>
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
    <script>
        $(document).ready(function () {
            $('#requestList').DataTable();
        });
    </script>
</html>
