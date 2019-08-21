    <%@page import="extra.SharedFormat"%>
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
                                <h4>Supplier Items List</h4>
                                <a href="/AMS/SupplierServlet/List" class="btn-info view-back">Return to Supplier List</a><br/>
                                <form class="form-horizontal style-form" action="/AMS/SupplierServlet/AddSupplyItem">
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
                                            if (sitem.size() == 0) {
                                                checker = true;
                                        %>

                                        <datalist id="asset-list">
                                            <%
                                                try {
                                                    ArrayList<Asset> asset = (ArrayList<Asset>) session.getAttribute("assets");
                                                    for (Asset choice : asset) {
                                            %>
                                            <option><%=choice.AssetName%></option>
                                            <%
                                                    }
                                                } catch (NullPointerException x) {
                                                }
                                            %>
                                        </datalist>
                                        <div class="col-lg-12" style="margin-top: 15px">
                                            <table style="width:100%" name="assetTable" id="assetTable" class="table-bordered table">
                                                <thead>
                                                    <tr>
                                                        <th>Selected</th>
                                                        <th>Asset</th>
                                                        <th>Price</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr class="fieldT">
                                                        <td><input type="checkbox" name="record"></td>
                                                        <td>
                                                            <input list="asset-list" name="assets" autocomplete="off">
                                                        </td>
                                                        <td><input type="text" class="price" name="price" autocomplete="off"></td> 
                                                        <td><button class="btn btn-theme" id='addbutton' type="button"><i class="fa fa-plus"></i></button></td>
                                                    </tr>
                                                </tbody>
                                                <tfoot>
                                                    <tr>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th><button type="button" class="delete-row  btn btn-danger"> Delete Selected</button></th>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                        <%
                                        } else {
                                        %>

                                        <div class="col-lg-12" style="margin-top: 15px">
                                            <table style="width:100%"  class="table-bordered table">
                                                <thead>
                                                    <tr>
                                                        <th>Asset Id</th>
                                                        <th>Asset Name</th>
                                                        <th>Asset Description</th>
                                                        <th>Price</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                        AssetService assetServ = new AssetService();
                                                        for (int i = 0; i < sitem.size(); i++) {
                                                    %>
                                                    <tr>
                                                        <td><%= sitem.get(i).AssetId%></td>
                                                        <td><%= assetServ.GetAsset(sitem.get(i).AssetId).AssetName%></td>
                                                        <td><%= assetServ.GetAsset(sitem.get(i).AssetId).Description%></td>
                                                        <td style="text-align: right"><%= SharedFormat.doubleToMoney(sitem.get(i).price)%></td> 
                                                    </tr>

                                                    <%
                                                        }
                                                    %>
                                                </tbody>
                                            </table>
                                        </div>
                                        <%}
                                        %>
                                    </div>
                                    <%
                                        if (checker) {
                                    %>

                                    <div class="form-group">
                                        <div class="col-lg-12" style="text-align: center">
                                            <button class="btn btn-theme" type="submit">Add Supplier Items</button>
                                        </div>
                                    </div>
                                </form>
                                <%
                                } else {
                                %>
                                <form class="form-horizontal style-form" action="/AMS/SupplierServlet/UpdateItem">
                                    <button class="btn btn-info" name="upsupid" formaction="/AMS/SupplierServlet/UpdateItem" value="<%= supplier.SupplierId%>" onclick="alert('Supplier Item Added!')" type="submit"><span class="fa fa-plus">Update Supplier Items</span></button>
                                </form>
                            </div>
                            <%
                                }
                            %>



                        </div>
                    </div>
                    <!-- /row -->
                </section>
            </section>
            <!--main content end-->
            <jsp:include page="../../shared/footer.jsp"/>
        </section>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js "></script>

        <script>
            $(document).ready(function () {
                var i = 1;
                $("#addbutton").click(function () {
                    $("table tr.fieldT:first").clone().find("input").each(function () {
                        $(this).val('').attr({
                            'id': function (_, id) {
                                return id
                            },
                            'name': function (_, name) {
                                return name
                            },
                            'value': ''
                        });
                    }).end().appendTo("table ");
                    i++;
                });

                $(document.body).on('change', '.price', function () {
                    // initialize the sum (total price) to zero
                    var sum = 0;

                    var price = [], qty = [];
                    // we use jQuery each() to loop through all the textbox with 'price' class
                    // and compute the sum for each loop
                    $('.price').each(function () {
                        price.push($(this).val());
                    });
                    $('.quantity').each(function () {
                        qty.push($(this).val());
                    });

                    for (var i = 0; i < price.length; i++) {
                        sum += (price[i] * qty[i]);
                    }
                    // set the computed value to 'totalPrice' textbox
                    $('#totalPrice').val(sum);
                });

                $('#asset-type').on('change', function () {
                    var type = $(this).val();
                    console.log('asset type' + type);
                    $.ajax({
                        url: '/AMS/AjaxServlet/AssetListWithType',
                        dataType: 'json',
                        data: {type: type},
                        success: function (data) {
                            $('#asset-list').html("");
                            for (var i = 0; i < data.Assets.length; i++) {
                                console.log(data.Assets);
                                $('#asset-list').append('<option>' + data.Assets[i].AssetName + '</option>')
                            }
                        }
                    });
                });

                $(".delete-row").click(function () {
                    $("table tbody").find('input[name="record"]').each(function () {
                        if ($(this).is(":checked")) {
                            $(this).parents("tr").remove();
                        }
                    });
                    var sum = 0;

                    var price = [], qty = [];
                    // we use jQuery each() to loop through all the textbox with 'price' class
                    // and compute the sum for each loop
                    $('.price').each(function () {
                        price.push($(this).val());
                    });
                    $('.quantity').each(function () {
                        qty.push($(this).val());
                    });

                    for (var i = 0; i < price.length; i++) {
                        sum += (price[i] * qty[i]);
                    }
                    // set the computed value to 'totalPrice' textbox
                    $('#totalPrice').val(sum);
                });
            });
        </script>

    </body>
    <jsp:include page="../../shared/js.jsp"/>
</html>
