<%-- 
    Document   : expenditure_limit
    Created on : Oct 14, 2018, 7:38:53 PM
    Author     : RubySenpaii
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Index</title>
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
                        <div class="col-md-3"></div>
                        <div class="form-panel col-md-6 col-md-offset-3">
                        <h4>Set Limit of Expenditure for Asset</h4><br/>
                        <form role="form" class="form-horizontal style-form" action="/AMS/ExpenditureServlet/Submit" enctype="multipart/form-data" method="POST">
                                <div class="form-group">
                                    <div class="col-lg-12">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Division</th>
                                                    <th>Equipment</th>
                                                    <th>Supplies</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Procurement Division</td>
                                                    <td>
                                                        <input type="number" name="procurement-equipment" autocomplete="off">
                                                    </td>
                                                    <td>
                                                        <input type="number" name="procurement-supplies" autocomplete="off">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Management Division</td>
                                                    <td>
                                                        <input type="number" name="management-equipment" autocomplete="off">
                                                    </td>
                                                    <td>
                                                        <input type="number" name="management-supplies" autocomplete="off">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Admin Services</td>
                                                    <td>
                                                        <input type="number" name="admin-equipment" autocomplete="off">
                                                    </td>
                                                    <td>
                                                        <input type="number" name="admin-supplies" autocomplete="off">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>General Services</td>
                                                    <td>
                                                        <input type="number" name="general-equipment" autocomplete="off">
                                                    </td>
                                                    <td>
                                                        <input type="number" name="general-supplies" autocomplete="off">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Finance Division</td>
                                                    <td>
                                                        <input type="number" name="finance-equipment" autocomplete="off">
                                                    </td>
                                                    <td>
                                                        <input type="number" name="finance-supplies" autocomplete="off">
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div style="text-align: center">
                                    <button type="submit" class="btn btn-theme">Submit</button>
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
</html>
