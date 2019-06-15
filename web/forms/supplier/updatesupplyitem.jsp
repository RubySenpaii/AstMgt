<%-- 
    Document   : addsupplyitem
    Created on : Oct 7, 2018, 3:18:42 PM
    Author     : RubySenpaii
--%>

<%@page import="services.AssetService"%>
<%@page import="objects.SupplierItem"%>
<%@page import="objects.Supplier"%>
<%@page import="objects.Employee"%>
<%@page import="objects.Asset"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Add Supply Item</title>
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
                            <div class="form-panel">
                                <h4>Update Supplier Items</h4><br/>
                                <form class="form-horizontal style-form" action="/AMS/SupplierServlet/UpdateItems">
                                    <%
                                        Supplier supplier = (Supplier) session.getAttribute("supplier");
                                    %>
                                    <div class="form-group">
                                        <b class="col-lg-2 control-label" for="exampleInputPassword1">Supplier Name</b>
                                        <div class="col-lg-4">
                                            <label class="col-lg-2 control-label"><%= supplier.SupplierName%></label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <b class="col-lg-2 control-label" for="exampleInputPassword1">Supplier Type</b>
                                        <div class="col-lg-4">
                                            <label class="col-lg-2 control-label"><%= supplier.SupplierType%></label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <b class="col-lg-2 control-label" for="exampleInputPassword1">Supplier Address</b>
                                        <div class="col-lg-4">
                                            <label class="col-lg-2 control-label"><%= supplier.SupplierAddress%></label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <b class="col-lg-2 control-label" for="exampleInputPassword1">Supplier Items</b>
                                        <%
                                            boolean checker = false;
                                            ArrayList<SupplierItem> sitem = (ArrayList<SupplierItem>) session.getAttribute("supplier items");
                                        %>
                                        <input type="number" class="suppid" name="suppid" autocomplete="off" value="<%= supplier.SupplierId%>" hidden="true"> 
                                        <div class="col-lg-12" style="margin-top: 15px">
                                            <table style="width:100%"  class="table-bordered table">
                                                <thead>
                                                    <tr>
                                                        <th>Asset Id</th>
                                                        <th>Asset Name</th>
                                                        <th>Asset Description</th>
                                                        <th>Price</th>
                                                        <th>New Price</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        AssetService assetServ = new AssetService();
                                                        for (int i = 0; i < sitem.size(); i++) {
                                                    %>
                                                    <tr>
                                                <input type="number" class="assetid" name="assetid" autocomplete="off" value="<%= sitem.get(i).AssetId%>" hidden="true"> 
                                                <input type="number" class="oldprice" name="oldprice" autocomplete="off" value="<%= (int) Math.round(sitem.get(i).price)%>" hidden="true">
                                                        <td><%= sitem.get(i).AssetId%></td>
                                                        <td><%= assetServ.GetAsset(sitem.get(i).AssetId).AssetName%></td>
                                                        <td><%= assetServ.GetAsset(sitem.get(i).AssetId).Description%></td>
                                                        <td><%= sitem.get(i).price%></td>
                                                        <td><input type="number" class="asset" name="newprice" autocomplete="off" placeholder="<%= sitem.get(i).price%>"> </td>
                                                    </tr>

                                                    <%
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <%//                                        if (checker) {
                                    %>

                                    <div class="form-group">
                                        <div class="col-lg-12" style="text-align: center">
                                            <button class="btn btn-theme" type="submit">Update Items</button>
                                        </div>
                                    </div>

                                    <%//                                        }
%>

                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- /row -->
                </section>
            </section>
            <!--main content end-->
            <jsp:include page="../../shared/footer.jsp"/>
        </section>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js "></script>

    </body>
    <jsp:include page="../../shared/js.jsp"/>
</html>
