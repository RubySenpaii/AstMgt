<%-- 
    Document   : add
    Created on : Oct 21, 2018, 5:06:34 PM
    Author     : RubySenpaii
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
                            <h4>Add an Asset</h4><br/>
                            <form role="form" class="form-horizontal style-form" action="/AMS/AssetServlet/Submit">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Stock No</label>
                                    <div class="col-lg-10">
                                        <input type="text" name="stock-no" placeholder="" class="form-control"  autocomplete="off">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Asset Name</label>
                                    <div class="col-lg-10">
                                        <input type="text" name="asset-name" placeholder="" class="form-control" autocomplete="off">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Unit</label>
                                    <div class="col-lg-10">
                                        <input type="text" name="unit" placeholder="" class="form-control" autocomplete="off">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Description</label>
                                    <div class="col-lg-10">
                                        <input type="text" name="description" placeholder="" class="form-control" autocomplete="off">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Asset Type</label>
                                    <div class="col-lg-10">
                                        <select class="form-control" name="asset-type">
                                            <option>Supplies Office</option>
                                            <option>Supplies General</option>
                                            <option>Equipment Furniture</option>
                                            <option>Equipment Appliance</option>
                                            <option>Equipment Electronics</option>
                                            <option>Equipment Vehicle</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Fund Cluster</label>
                                    <div class="col-lg-10">
                                        <input type="text" name="fund-cluster" placeholder="" class="form-control" autocomplete="off">
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
</html>
