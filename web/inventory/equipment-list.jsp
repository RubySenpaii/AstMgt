<%-- 
    Document   : equipment-list
    Created on : Oct 28, 2018, 1:37:12 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.Equipment"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Add Purchase Request</title>
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
                            <h4>Equipment List</h4><br/>
                            <form class="form-horizontal style-form" action="/AMS/InventoryServlet/EquipmentView">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Asset Name</th>
                                            <th>Asset Tag</th>
                                            <th>Condition</th>
                                            <th>Status</th>
                                            <th>Current User</th>
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
                                            <td><%=equipment.Condition%></td>
                                            <td><%=equipment.Status()%></td>
                                            <td><%=equipment.CurrentUser.FullName()%></td>
                                            <td>
                                                <button type="submit" name="asset-tag" value="<%=equipment.AssetTag%>">View</button>
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
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
