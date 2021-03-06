<%-- 
    Document   : add
    Created on : Oct 7, 2018, 3:18:42 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.AssetRequested"%>
<%@page import="objects.PurchaseRequest"%>
<%@page import="objects.Employee"%>
<%@page import="objects.Asset"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Add Purchase Request</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
                                <h4>Create Purchase Request</h4><br/>
                                <form class="form-horizontal style-form" action="/AMS/PurchaseRequest/Submit">
                                    
                                    <%
                                        Asset asset = (Asset) session.getAttribute("asset");
                                        boolean isSaved = false;
                                        try{
                                        isSaved = (boolean) session.getAttribute("Notification");
                                        }catch(Exception e){
                                        }
                                        String division = (String) session.getAttribute("UserDivision");
                                    %>
                                    <input type="hidden" id="notif" name="notif" value="<%= isSaved%>">
                                    <input type="hidden" name="division" id="division" value="<%=division%>">
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Asset Type</label>
                                        <div class="col-lg-10">
                                            <select class="form-control" id="asset-type" name="asset-type">
                                                <%
                                                    String assetName = "";
                                                    try {
                                                        assetName = asset.AssetName;
                                                %>
                                                <option value="<%=asset.AssetType%>"><%=asset.AssetType%></option>
                                                <%
                                                } catch (NullPointerException x) {
                                                %>
                                                <option selected disabled>-Select Asset Type-</option>
                                                <!--option value="Supplies Office">Supplies Office</option>
                                                <option value="Supplies General">Supplies General</option-->
                                                <%
                                                    Employee user = (Employee) session.getAttribute("user");
                                                    if (!user.EmployeeStatus.equals("Contractual")) {
                                                %>
                                                <option value="Equipment Furniture">Equipment Furniture</option>
                                                <option value="Equipment Appliance">Equipment Appliance</option>
                                                <option value="Equipment Electronics">Equipment Electronics</option>
                                                <option value="Equipment Vehicle">Equipment Vehicle</option>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </select>
                                            <datalist id="asset-list">
                                                <%
                                                    try {
                                                        ArrayList<Asset> choices = (ArrayList<Asset>) session.getAttribute("choices");
                                                        for (Asset choice : choices) {
                                                %>
                                                <option><%=choice.AssetName%></option>
                                                <%
                                                        }
                                                    } catch (NullPointerException x) {
                                                    }
                                                %>
                                            </datalist>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Mode Of Procurement</label>
                                        <div class="col-lg-10">
                                            <select class="form-control" name="mode-of-procurement" placeholder="Mode Of Procurement" autocomplete="off">
                                                <option>Shopping</option>
                                                <option>Donation</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Supplier</label>
                                        <div class="col-lg-10">
                                            <input type="text" class="form-control" id="supplier-name" name="supplier" placeholder="Supplier" list="supplier-list" autocomplete="off">
                                            <datalist id="supplier-list">
                                            </datalist>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Asset Items</label>
                                        <div class="col-lg-12" style="margin-top: 15px">
                                            <table name="assetTable" id="assetTable" class="table-bordered table">
                                                <thead>
                                                    <tr>
                                                        <th>Selected</th>
                                                        <th>Asset</th>
                                                        <th>Quantity</th> 
                                                        <th>Price</th>
                                                        <th>Asset Limit</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr class="fieldT">
                                                        <td><input type="checkbox" name="record"></td>
                                                        <td>
                                                            <input list="asset-list" class="asset-list" name="assets" autocomplete="off" value="<%=assetName%>">
                                                        </td>
                                                        <td><input type="number" class="quantity" name="quantity" autocomplete="off"></td> 
                                                        <td><input type="text" class="price" name="price" autocomplete="off"></td> 
                                                        <td><input type="number" class="limit" id="limit" name="limit" disabled="true" value=""</td>
                                                        <td><button class="btn btn-theme" id='addbutton' type="button"><i class="fa fa-plus"></i></button></td>
                                                    </tr>
                                                </tbody>
                                                <tfoot>
                                                    <tr>
                                                        <th><button type="button" class="delete-row  btn btn-danger">Remove</button></th>
                                                        <th colspan="2" style="text-align: right">Total :</th>
                                                        <th><input name='totalPrice' id='totalPrice' disabled="true">  </th>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label" for="exampleInputPassword1">Purpose</label>
                                        <div class="col-lg-10">
                                            <input type="text" class="form-control" id="purpose" name="purpose" placeholder="Purpose" autocomplete="off">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-12" style="text-align: center">
                                            <button class="btn btn-theme " id="submit" name="submit" type="submit">
                                                Submit
                                            </button>
                                            <span id="warning"  hidden="true"><span class="fa fa-warning" style="color:red" data-toggle="tooltip" title="You cannot add more than the quantity of the asset limit"></span></span>
                                        </div>

                                    </div>
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

    </body>
    <jsp:include page="../../shared/js.jsp"/>
    <script>
        var items = [];
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

//                  $('#prid').prop("disabled", true);
            });

            $(document.body).on('change', '.quantity', function () {
                // initialize the sum (total price) to zero
                var sum = 0;

                var price = [], qty = [];
                // we use jQuery each() to loop through all the textbox with 'price' class
                // and compute the sum for each loop
                $('.price').each(function () {
                    price.push($(this).val());
                });
                $('.quantity').each(function () {
                    if ($(this).val() > $('#limit').val()) {
                        console.log('banned')
                        $('#submit').prop("disabled", true);
                        $('#warning').prop("hidden", false);
                    } else {
                        console.log('allowed')
                        $('#submit').prop("disabled", false);
                        $('#warning').prop("hidden", true);
                    }
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
                    url: '/AMS/AjaxServlet/SupplierListWithType',
                    dataType: 'json',
                    data: {type: type},
                    success: function (data) {
                        $('#supplier-list').html("");
                        for (var i = 0; i < data.Assets.length; i++) {
                            console.log(data.Assets);
                            $('#supplier-list').append('<option>' + data.Assets[i].SupplierName + '</option>')
                        }
                    }
                });
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
                        console.log("AHA TANGINA")
                        for (var i = 0; i < data.Assets.length; i++) {
                            $('#asset-list').append('<option>' + data.Assets[i].AssetName + '</option>')
                        }
                    }
                });
            });

            $('#supplier-name').on('change', function () {
                var supplierName = $(this).val();
                console.log('supplier name', supplierName);
                $.ajax({
                    url: '/AMS/AjaxServlet/SupplierItem',
                    dataType: 'json',
                    data: {supplierName: supplierName},
                    success: function (data) {
                        console.log("supplier name ajax", data);
                        $('#asset-list').html('');
                        items = [];
                        for (var i = 0; i < data.SupplierItems.length; i++) {
                            $('#asset-list').append('<option>' + data.SupplierItems[i].Asset.AssetName + '</option>');
                            items.push({
                                'name': data.SupplierItems[i].Asset.AssetName,
                                'price': data.SupplierItems[i].price,
                                'glimit': data.SupplierItems[i].Asset.GeneralQuantityLimit
                            });
                        }
                    }
                });
            });

            $(document.body).on('change', '.asset-list', function () {
                for (var i = 0; i < items.length; i++) {
                    console.log('in forloop', items[i].name.length, ' = ', $(this).val().length)
                    if (items[i].name.trim() === $(this).val().trim()) {
                        console.log('value', items[i]);
                        $(this).find('td').each(function () {

                        });
                        var division = $('#division').val();
                        $(this).closest('tr').find('td:nth-child(4)').find('.price').val(items[i].price);
                        $(this).closest('tr').find('td:nth-child(5)').find('.limit').val(items[i].glimit);
                        }
                    }
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
</html>
