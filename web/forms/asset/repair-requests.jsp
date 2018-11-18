<%-- 
    Document   : repair-requests
    Created on : 11 18, 18, 6:52:37 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.RepairLog"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Add Purchase Request</title>
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
                                            <th>Asset Tag</th>
                                            <th>Total Cost</th>
                                            <th>Requested By</th>
                                            <th>Requested Date</th>
                                            <th>Approved By</th>
                                            <th>Approved Date</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<RepairLog> repairRequests = (ArrayList<RepairLog>) session.getAttribute("repairRequests");
                                            int i = 0;
                                            for (RepairLog repairRequest : repairRequests) {
                                        %>
                                        <tr>
                                            <td><%=repairRequest.AssetTag%></td>
                                            <td><%=repairRequest.TotalCost%></td>
                                            <td><%=repairRequest.Requester.FullName()%></td>
                                            <td><%=repairRequest.RequestedDate%></td>
                                            <td><%=repairRequest.Approver.FullName()%></td>
                                            <td><%=repairRequest.ApprovedDate%></td>
                                            <td>
                                                <form action="/AMS/AssetServlet/RepairRequest">
                                                    <button name="index" value="<%=i%>">View</button>
                                                </form>
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
