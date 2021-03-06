<%-- 
    Document   : release-supplies
    Created on : 11 18, 18, 7:36:33 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.Supplies"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Release of Supplies</title>
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
                            <h4>Release Supplies</h4><br/>
                            <form role="form" class="form-horizontal style-form" action="/AMS/InventoryServlet/SubmitRelease">
                                <%
                                    Supplies supplies = (Supplies) session.getAttribute("supply");
                                %>
                                <input type="hidden" class="max" value="<%=supplies.TotalQuantity%>">
                                <div class="form-group">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Quantity</th>
                                                <th>Division</th>
                                                <th><button class="btn btn-theme" id='addbutton' type="button"><i class="fa fa-plus"></i></button></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="fieldT">
                                                <td><input type="number" name="quantity" class="qty-ordered"></td>
                                                <td>
                                                    <select name="division">
                                                        <option>Admin Services</option>
                                                        <option>General Services</option>
                                                        <option>Procurement</option>
                                                        <option>Finance</option>
                                                        <option>Management</option>
                                                    </select>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-12" style="text-align: center">
                                        <button class="btn btn-theme submit-btn" type="submit">Submit</button>
                                    </div>
                                </div>
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

            $(document).on('change', '.qty-ordered', function () {
                var total = 0;
                $('.qty-ordered').each(function () {
                    total += parseInt(this.value);
                });
                if (total > parseInt($('.max').val())) {
                    $('.submit-btn').prop('disabled', true);
                } else {
                    $('.submit-btn').prop('disabled', false);
                }
            });
        });
    </script>
</html>
