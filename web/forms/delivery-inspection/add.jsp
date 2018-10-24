<%-- 
    Document   : add
    Created on : Oct 24, 2018, 9:30:43 PM
    Author     : RubySenpaii
--%>

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
                            <form class="form-horizontal style-form" action="/AMS/DeliveryInspectionServlet/Submit">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputEmail1">Invoice</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="pr" name="pr" aria-describedby="emailHelp" placeholder="Enter Purchase Request No.">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Delivery Receipt</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="rcc" name="rcc" placeholder="Responsibility Center Code">
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

