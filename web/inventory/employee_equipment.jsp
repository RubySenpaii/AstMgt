<%-- 
    Document   : employee_equipment
    Created on : 08 3, 19, 4:54:36 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.Employee"%>
<%@page import="objects.Equipment"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Employee Equipment List</title>
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
                                <%
                                    Employee user = (Employee) session.getAttribute("user");
                                %>
                                <table class="table table-bordered" id="equip-list">
                                    <thead>
                                        <tr>
                                            <%
                                                if (user.UserLevel.equals("Custodian")) {
                                            %>
                                            <th><input type="checkbox" onclick="for (c in document.getElementsByName('asset-name')) document.getElementsByName('asset-name').item(c).checked = this.checked"></th>
                                            <%
                                                }
                                            %>
                                            <th>Asset Name</th>
                                            <th>Asset Tag</th>
                                            <th>Date Acquired</th>
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
                                            <%
                                                if (user.UserLevel.equals("Custodian")) {
                                            %>
                                            <td><input type="checkbox" name="asset-tag" value="<%=equipment.AssetTag%>"></td>
                                            <%
                                                }
                                            %>
                                            <td><%=equipment.Asset.AssetName%></td>
                                            <td><%=equipment.AssetTag%></td>
                                            <td><%=equipment.DateAcquired%></td>
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
                                <%
                                    if (equipments.size() > 0 && user.EmployeeId == equipments.get(0).CurrentUser.EmployeeId) {
                                %>
                                <button class="btn-info" formaction="/AMS/InventoryServlet/RetiringEmployee" type="submit">Retire</button>
                                <%
                                    }
                                    if (user.UserLevel.equals("Custodian")) {
                                %>
                                <button class="btn-success">Collect Item</button>
                                <%
                                    }
                                %>
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
    <script>
        $(document).ready(function () {
            $('#equip-list').DataTable({
                "order": [[2, "desc"]]
            });
        });
    </script>
</html>
