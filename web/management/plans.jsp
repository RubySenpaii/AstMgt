<%-- 
    Document   : plan
    Created on : 12 1, 18, 6:59:26 PM
    Author     : rubysenpaii
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
                            <h4>Submit WFP</h4><br/>
                            <form role="form" class="form-horizontal style-form" action="/AMS/ExpenditureServlet/SubmitWFP" enctype="multipart/form-data" method="POST">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">WFP</label>
                                    <div class="col-lg-10">
                                        <input type="file" class="default" name="wfp">
                                    </div>
                                </div>
                                <div style="text-align: center">
                                    <button type="submit" class="btn btn-theme">Submit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-3"></div>
                        <div class="form-panel col-md-6 col-md-offset-3">
                            <h4>Submit APP</h4><br/>
                            <form role="form" class="form-horizontal style-form" action="/AMS/ExpenditureServlet/SubmitAPP" enctype="multipart/form-data" method="POST">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">APP</label>
                                    <div class="col-lg-10">
                                        <input type="file" class="default" name="app">
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
