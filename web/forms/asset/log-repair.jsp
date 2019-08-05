<%-- 
    Document   : log-repair
    Created on : 11 18, 18, 3:49:56 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.RepairLog"%>
<%@page import="objects.AssetTracking"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Log Repair</title>
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
                                        <select name="asset-tag" placeholder="" id="asset-tag" class="form-control" autocomplete="off" >
                                            <option disabled selected>- Select an Option -</option>
                                            <%
                                                ArrayList<AssetTracking> userAssets = (ArrayList<AssetTracking>) session.getAttribute("userAssets");
                                                ArrayList<RepairLog> userRepairrequest = ( ArrayList<RepairLog> ) session.getAttribute("repairRequestsperAsset");
                                                for (AssetTracking userAsset : userAssets) {
                                            %>
                                            <option value="<%=userAsset.AssetTag + '*' + userAsset.Equipment.AcquisitionCost + '*' + userAsset.Equipment.Description%>"><%=userAsset.AssetTag%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Warranty</label>
                                    <div class="col-lg-10">
                                        <span id="warranty"></span>
                                    </div>
                                </div>
                                <input type="hidden" id="acquisition-cost">
                                <input type="hidden" id="userRepairRequest" value="<%= userRepairrequest%>">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Repair Costs</label>
                                    <div class="col-lg-10">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Selected</th>
                                                    <th>Article</th>
                                                    <th>Cost</th>
                                                    <th><button class="btn btn-theme" id='addbutton' type="button"><i class="fa fa-plus"></i></button></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="fieldT">
                                                    <td><input type="checkbox" name="record" </td>
                                                    <td><input type="text" name="article"  autocomplete="off"></td>
                                                    <td><input type="number" class="cost" name="cost" autocomplete="off"></td>
                                                </tr>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <th><button class="delete-row btn btn-danger" type="button">Delete Selected</button></th>
                                                    <th colspan="1">Total : </th>
                                                    <th><input name='totalCost' id='totalCost' disabled="true"> </th>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button class="btn btn-theme" id="submit" type="submit">Submit</button>
                                        <span id="warning"  hidden="true"><span class="fa fa-warning" style="color:red" data-toggle="tooltip" title="You cannot request for a repair more than 30% of its total amount"></span></span>
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
            $(".delete-row").click(function () {
                $("table tbody").find('input[name="record"]').each(function () {
                    if ($(this).is(":checked")) {
                        $(this).parents("tr").remove();
                    }
                });
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
        });

        $(document.body).on('change', '#asset-tag', function () {
            var acquisitionCost = Number($(this).val().split('*')[1]);
            var description = $(this).val().split('*')[2];
            description = description.split('__');
            for (var i = 0; i < description.length; i++) {
                if (description[i].includes('Warranty')) {
                    $('#warranty').text(description[i].split("//")[1]);
                }
            }
            $('#acquisition-cost').val(acquisitionCost);
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

            if (Number($('#acquisition-cost').val()) * 0.3 <= Number($('#totalCost').val())) {
                console.log(true);
                 $('#warning').prop("hidden", false);
                $('#submit').prop('disabled', true);
            } else {
                console.log(false);
                 $('#warning').prop("hidden", true);
                $('#submit').prop('disabled', false);
            }
        });


    </script>
</html>
