<%-- 
    Document   : add
    Created on : Oct 7, 2018, 3:18:42 PM
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
                        <div class="row" style="padding-top: 20px; margin-left: 20px; margin-right: 60px; margin-bottom: 20px">
                            <form class="form-text" action="/AMS/PurchaseRequest/Submit">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Purchase Request Id</label>
                                    <input type="text" class="form-control" id="pri" name="pri" aria-describedby="emailHelp" placeholder="Enter Purchase Request No.">
                                    <small id="emailHelp" class="form-text text-muted">Small description about the fields</small>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Purchase Request No</label>
                                    <input type="text" class="form-control" id="pr" name="pr" aria-describedby="emailHelp" placeholder="Enter Purchase Request No.">
                                    <small id="emailHelp" class="form-text text-muted">Small description about the fields</small>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Responsibility Center Code</label>
                                    <input type="text" class="form-control" id="rcc" name="rcc" placeholder="Responsibility Center Code">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Date</label>
                                    <input type="date" class="form-control" id="date" name="date" placeholder="Date">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Purpose</label>
                                    <input type="text" class="form-control" id="purpose" name="purpose" placeholder="Purpose">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Requested By</label>
                                    <input type="text" class="form-control" id="reqby" name="reqby" placeholder="Requested By">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Requested Date</label>
                                    <input type="date" class="form-control" id="reqdate" name="reqdate" placeholder="Requested Date">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Approved By</label>
                                    <input type="text" class="form-control" id="appby" name="appby" placeholder="Approved By">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Approved Date</label>
                                    <input type="date" class="form-control" id="appdate" name="appdate" placeholder="Approved Date">
                                </div>
                                <button type="submit" class="btn btn-primary">Add</button>
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
