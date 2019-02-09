<%-- 
    Document   : list
    Created on : Oct 21, 2018, 6:31:23 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.Supplier"%>
<%@page import="objects.Asset"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Supplier List</title>
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
                                <h4>List of Supplier</h4>
                                <br/>
                                <div class="col-md-12 content-panel">
                                    <table class="table" id="suppList">
                                        <thead>
                                            <tr>
                                                <th>Supplier Id</th>
                                                <th>Supplier Name</th>
                                                <th>Supplier Address</th>
                                                <th>Supplier TIN</th>
                                                <th>Supplier Type</th>
                                                <th>Contact Number</th>
                                                <th>Contact Person</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                ArrayList<Supplier> suppList = (ArrayList<Supplier>) session.getAttribute("supplier");
                                                for (Supplier supp : suppList) {
                                            %>
                                            <tr>
                                                <td><%=supp.SupplierId %></td>
                                                <td><%=supp.SupplierName %></td>
                                                <td><%=supp.SupplierAddress %></td>
                                                <td><%=supp.SupplierTIN %></td>
                                                <td><%=supp.SupplierType %></td>
                                                <td><%=supp.ContactNumber %></td>
                                                <td><%=supp.ContactPerson %></td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
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
    <script>
        $(document).ready(function () {
            $('#suppList').DataTable();
        });
    </script>
</html>
