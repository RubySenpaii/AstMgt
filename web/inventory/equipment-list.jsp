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
        <title>Asset Management - Asset List</title>
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
                            
                            <%
                               String isSaved = "";
                               String isTracked = "";
                               String invNotif = "";
                            try {
                                isSaved = (String) session.getAttribute("repairnotif");
                                isTracked = (String) session.getAttribute("trackingnotif");
                                invNotif = (String) session.getAttribute("invNotif");
                            }
                            catch(Exception e){
                                
                            }
                            %>
                            <input type="hidden" id="repairnotif" name="repairnotif" value="<%= isSaved%>">
                            <input type="hidden" id="trackingnotif" name="trackingnotif" value="<%= isTracked%>">
                            <input type="hidden" id="invNotif" name="invNotif" value="<%= invNotif%>">
                            <%
                                session.removeAttribute("repairnotif");
                                session.removeAttribute("trackingnotif");
                                session.removeAttribute("invNotif");
                            %>
                            <h4>Asset List</h4><br/>
                            <form class="form-horizontal style-form" action="/AMS/InventoryServlet/EquipmentView">
                                <table class="table table-bordered" id="equip-list">
                                    <thead>
                                        <tr>
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
                                            String fullName = equipment.CurrentUser.FullName();
                                                if (equipment.CurrentUser.UserLevel.toLowerCase().contains("custodian")) {
                                                    fullName = "";
                                                }
                                        %>
                                        <tr>
                                            <td><%=equipment.Asset.AssetName%></td>
                                            <td><%=equipment.AssetTag%></td>
                                            <td><%=equipment.DateAcquired%></td>
                                            <td><%=equipment.Condition%></td>
                                            <td><%=equipment.Status()%></td>
                                            <td><%=fullName%></td>
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
    <script>
        $(document).ready(function () {
            $('#equip-list').DataTable({
                "order": [[2, "desc"]]
            });
            var notif = document.getElementById("repairnotif");
            if (notif.value === 'true') {
                alert("Successfully saved the repair request!");
            } else if (notif.value === 'false') {
                alert("Failed to save the repair request!");
            }
            var tnotif = document.getElementById("trackingnotif");
            if (tnotif.value === 'true') {
                alert("Successfully saved the tracking request!");
            } else if (notif.value === 'false') {
                alert("Failed to save the tracking request!");
            }
            
            var invnotif = document.getElementById("invNotif");
            if (invnotif.value === 'true') {
                alert("Successfully acknowledged!");
            } else if (notif.value === 'false') {
                alert("Failed to acknowledged!");
            }
        });
    </script>
</html>
