<%-- 
    Document   : list
    Created on : Oct 21, 2018, 6:31:23 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.Asset"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Asset List</title>
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
                                <h4>List of Assets</h4>
                                <br/>
                                <div class="col-md-12 content-panel">
                                    <table class="table" id="assetList">
                                        <thead>
                                            <tr>
                                                <th>Asset Id</th>
                                                <th>Asset Name</th>
                                                <th>Asset Type</th>
                                                <th>Description</th>
                                                <th>Estimated Useful Life</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                ArrayList<Asset> assets = (ArrayList<Asset>) session.getAttribute("assets");
                                                for (Asset asset : assets) {
                                            %>
                                            <tr>
                                                <td><%=asset.AssetId%></td>
                                                <td><%=asset.AssetName%></td>
                                                <td><%=asset.AssetType%></td>
                                                <td><%=asset.Description%></td>
                                                <td><%=asset.EstimatedUsefulLife%> year(s)</td>
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
            $('#assetList').DataTable();
        });
    </script>
</html>
