<%-- 
    Document   : add
    Created on : Oct 7, 2018, 3:18:42 PM
    Author     : RubySenpaii
--%>

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
                        <div class="col-md-10">
                            <div class="form-panel">
                                <h4>Create Supplier</h4><br/>

                                <form class="form-horizontal style-form" action="/AMS/SupplierServlet/Submit">
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Name</label>
                                        <div class="col-lg-10">
                                            <input type="text" name="name" placeholder="" class="form-control"  autocomplete="off">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Address</label>
                                        <div class="col-lg-10">
                                            <input type="text" name="address" placeholder="" class="form-control"  autocomplete="off">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Supplier TIN</label>
                                        <div class="col-lg-10">
                                            <input type="text" name="supptin" placeholder="" class="form-control"  autocomplete="off">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Contact Number</label>
                                        <div class="col-lg-10">
                                            <input type="text" name="contactno" placeholder="" class="form-control"  autocomplete="off">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Contact Person</label>
                                        <div class="col-lg-10">
                                            <input type="text" name="contactperson" placeholder="" class="form-control"  autocomplete="off">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Supplier Type</label>
                                        <div class="col-lg-10">
                                            <select class="form-control" name="supptype">
                                                <option>Equipment Furniture</option>
                                                <option>Equipment Appliance</option>
                                                <option>Equipment Electronics</option>
                                                <option>Equipment Vehicle</option>
                                            </select>
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

    </body>
    <jsp:include page="../../shared/js.jsp"/>
</html>
