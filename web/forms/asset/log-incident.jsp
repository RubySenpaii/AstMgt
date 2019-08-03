<%-- 
    Document   : log-incident
    Created on : 11 10, 18, 8:51:04 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.AssetTracking"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Log Incident</title>
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
                            <form role="form" class="form-horizontal style-form" action="/AMS/AssetServlet/SubmitIncident">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Asset Tag</label>
                                    <div class="col-lg-10">
                                        <select name="asset-tag" placeholder="" id="asset-tag" class="form-control" autocomplete="off" >
                                            <option disabled selected>- Select an Option -</option>
                                            <%
                                                ArrayList<AssetTracking> userAssets = (ArrayList<AssetTracking>) session.getAttribute("userAssets");
                                                for (AssetTracking userAsset : userAssets) {
                                            %>
                                            <option value="<%=userAsset.AssetTag + '*' + userAsset.Equipment.AcquisitionCost%>"><%=userAsset.AssetTag%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Severity</label>
                                    <div class="col-lg-10">
                                        <select name="severity" placeholder="" class="form-control">
                                            <option value="1">Low</option>
                                            <option value="2">Medium</option>
                                            <option value="3">High</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Remarks</label>
                                    <div class="col-lg-10">
                                        <textarea type="text" name="remarks" placeholder="" class="form-control"></textarea>
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
