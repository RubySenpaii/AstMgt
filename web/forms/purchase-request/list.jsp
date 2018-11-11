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
                        <div class="col-md-12">
                            <div class="content-panel">
                                <h4>List of Purchase Request</h4>
                                <br/>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Purchase Request No</th>
                                            <th>Date</th>
                                            <th>Requested By</th>
                                            <th>Requested Date</th>
                                            <th>Approved By</th>
                                            <th>Division</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<PurchaseRequest> PR = (ArrayList<PurchaseRequest>) session.getAttribute("PR");
                                            EmployeeService eDB = new EmployeeService();
                                            for (PurchaseRequest pr : PR) {
                                            Employee e  = eDB.FindEmployeeById(pr.ApprovedBy);
                                            String name = e.LastName + ", " + e.FirstName;
                                        %>
                                        <tr>
                                            <td><c:out value="<%= pr.PurchaseRequestNo %>"></c:out></td>
                                            <td><c:out value="<%= pr.Date %>"></c:out></td>
                                            <td><c:out value="<%= pr.RequestedBy %>"></c:out></td>
                                            <td><c:out value="<%= pr.RequestedDate %>"></c:out></td>
                                            <td><c:out value="<%= name%>"></c:out></td>
                                            <td><c:out value="<%= e.Division %>"></c:out></td>
                                            <td>
                                                <form action="/AMS/PurchaseRequest/View">
                                                    <%
                                                        session.setAttribute("id", pr.PurchaseRequestId);
                                                    %>
                                                <button type="submit" >View</button>
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
</html>
