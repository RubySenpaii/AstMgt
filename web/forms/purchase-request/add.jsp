<%-- 
    Document   : add
    Created on : Oct 7, 2018, 3:18:42 PM
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
        <title>Asset Management - Add Purchase Request</title>
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
                        <div class="form-panel">
                            <h4>Create Purchase Request</h4><br/>
                            <form class="form-horizontal style-form" action="/AMS/PurchaseRequest/Submit">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Responsibility Center Code</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="rcc" name="rcc" placeholder="Responsibility Center Code">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Purpose</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="purpose" name="purpose" placeholder="Purpose">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Asset Items</label>
                                    <div class="col-lg-10">
                                        <table style="width:100%" name="assetTable" id="assetTable">
                                            <tr>
                                                <th>Asset</th>
                                                <th>Quantity</th> 
                                                <th>Price</th>
                                                <th></th>
                                            </tr>
                                            <tr class="fieldT">
                                                <td><input list="ass" name="assets">
                                                    <datalist id="ass">
                                                        <%
                                                            ArrayList<Asset> alist = new ArrayList<Asset>();
                                                            alist = (ArrayList<Asset>) session.getAttribute("assets");
                                                            for (Asset asset : alist) {
                                                        %>
                                                        <option value="<%= asset.AssetName%>">
                                                            <%
                                                                }
                                                            %>
                                                    </datalist></td>
                                                <td><input type="number" class="quantity" name="quantity"></td> 
                                                <td><input type="number" class="price" name="price"></td> 
                                                <td><button class="btn btn-theme" id='addbutton' type="button"><i class="fa fa-plus"></i></button></td>
                                            </tr>
                                        </table>

                                    </div>
                                    <div class="col-lg-10" style="align-self: auto">
                                        <label>Total : </label>
                                        <input name='totalPrice' id='totalPrice' disabled="true">  
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button class="btn btn-theme" type="submit">Submit</button>
                                    </div>
                                </div>


                            </form>
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
                    console.log('change price pls');
                    // initialize the sum (total price) to zero
                    var sum = 0;
                    
                    var price = [], qty = [];
                    // we use jQuery each() to loop through all the textbox with 'price' class
                    // and compute the sum for each loop
                    $('.price').each(function () {
                        price.push($(this).val());
                    });
                    $('.quantity').each(function() {
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
