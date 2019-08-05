<%-- 
    Document   : list
    Created on : Oct 21, 2018, 6:31:23 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.Employee"%>
<%@page import="services.EmployeeService"%>
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
                        <%
                            Boolean isSaved = (Boolean) session.getAttribute("Notification");
                        %>
                        <input type="hidden" id="notif" name="notif" value="<%= isSaved%>">
                        <%
                            session.removeAttribute("Notification");
                        %>
                        <div class="col-md-12">
                            <div class="content-panel">
                                <h4>List of Purchase Request</h4>
                                <br/>
                                <table class="table" id="prList">
                                    <thead>
                                        <tr>
                                            <th>Purchase Request No</th>
                                            <th>Date</th>
                                            <th>Requested By</th>
                                            <th>Requested Date</th>
                                            <th>Reviewed By</th>
                                            <th>Division</th>
                                            <th>User Type</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<PurchaseRequest> PR = (ArrayList<PurchaseRequest>) session.getAttribute("PR");
                                            for (PurchaseRequest pr : PR) {
                                        %>
                                        <tr>
                                            <td><c:out value="<%= pr.PurchaseRequestNo%>"></c:out></td>
                                            <td><c:out value="<%= pr.Date%>"></c:out></td>
                                            <td><c:out value="<%= pr.Requester.FullName()%>"></c:out></td>
                                            <td><c:out value="<%= pr.RequestedDate%>"></c:out></td>
                                            <td><c:out value="<%= pr.Approver.FullName()%>"></c:out></td>
                                            <td><c:out value="<%= pr.Approver.Division%>"></c:out></td>
                                            <td><c:out value="<%= pr.Approver.UserLevel%>"></c:out></td>
                                                <td>
                                                    <form action="/AMS/PurchaseRequest/View">
                                                        <button type="submit" value="<%= pr.PurchaseRequestId%>" name="prid" >View</button>
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
            $('#prList').DataTable();

            var notif = document.getElementById("notif");
            if (notif.value === 'true') {
                alert("Successfully saved the Purchase Request !")
            } else if (notif.value === 'false') {
                alert("Failed to save the Purchase Request !")
            }
        });
    </script>
</html>
