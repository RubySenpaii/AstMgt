<%-- 
    Document   : equipment-history
    Created on : 04 6, 19, 5:55:00 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.Equipment"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Equipment List</title>
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
                            <h4>Equipment History</h4><br/>
                            <table class="table table-bordered" id="equip-list">
                                <thead>
                                    <tr>
                                        <th>Asset Name</th>
                                        <th>Asset Tag</th>
                                        <th>Date Acquired</th>
                                        <th>Price</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        ArrayList<Equipment> equipments = (ArrayList<Equipment>) session.getAttribute("equipments");
                                        for (Equipment equipment : equipments) {
                                    %>
                                    <tr>
                                        <td><%=equipment.Asset.AssetName%></td>
                                        <td><%=equipment.AssetTag%></td>
                                        <td><%=equipment.DateAcquired%></td>
                                        <td><%=equipment.AcquisitionCost%></td>
                                        <td></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
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
        $(document).ready(function () {
            $('#equip-list').DataTable({
                "order": [[2, "desc"]]
            });
        });
    </script>
</html>
