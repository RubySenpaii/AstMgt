<%-- 
    Document   : log-repair
    Created on : 11 18, 18, 3:49:56 PM
    Author     : rubysenpaii
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Add Asset</title>
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
                            <h4>Log Repair</h4><br/>
                            <form role="form" class="form-horizontal style-form" action="/AMS/AssetServlet/SubmitRepair">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Asset Tag</label>
                                    <div class="col-lg-10">
                                        <input type="text" name="asset-tag" placeholder="" class="form-control" autocomplete="off">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Repair Costs</label>
                                    <div class="col-lg-10">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Article</th>
                                                    <th>Cost</th>
                                                    <th><button class="btn btn-theme" id='addbutton' type="button"><i class="fa fa-plus"></i></button></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="fieldT">
                                                    <td><input type="text" name="article"  autocomplete="off"></td>
                                                    <td><input type="number" class="cost" name="cost" autocomplete="off"></td>
                                                </tr>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <th colspan="1">Total : </th>
                                                    <th><input name='totalCost' id='totalCost' disabled="true"> </th>
                                                </tr>
                                            </tfoot>
                                        </table>
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
    </body>
    <jsp:include page="../../shared/js.jsp"/>
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
        });
        $(document.body).on('change', '.cost', function () {
            // initialize the sum (total price) to zero
            var sum = 0;

            var cost = [];
            // we use jQuery each() to loop through all the textbox with 'price' class
            // and compute the sum for each loop
            $('.cost').each(function () {
                cost.push($(this).val());
            });

            for (var i = 0; i < cost.length; i++) {
                sum += Number(cost[i]);
            }

            // set the computed value to 'totalPrice' textbox
            $('#totalCost').val(sum);

        });
    </script>
</html>
