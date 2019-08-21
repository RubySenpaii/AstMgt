<%-- 
    Document   : incident-submission
    Created on : 08 21, 19, 1:26:53 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.AssetIncident"%>
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
                            <h4>Log Incident</h4><br/>
                            <%
                                AssetIncident incident = (AssetIncident) session.getAttribute("assetIncident");
                            %>
                            <form role="form" class="form-horizontal style-form" action="/AMS/AssetServlet/SubmitIncidentSubmission">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Asset Tag</label>
                                    <div class="col-lg-10">
                                        <input placeholder="" id="asset-tag" class="form-control" disabled value="<%=incident.AssetTag%>" autocomplete="off" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Incident Description</label>
                                    <div class="col-lg-10">
                                        <textarea type="text" placeholder="" class="form-control" disabled><%=incident.Remarks%></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Severity</label>
                                    <div class="col-lg-10">
                                        <select name="severity" placeholder="" class="form-control" onchange="doSomething(this.value)">
                                            <option value="1">Low</option>
                                            <option value="2">Medium</option>
                                            <option value="3">High</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label">Inspector Recommendation</label>
                                    <div class="col-lg-10">
                                        <textarea type="text" name="remarks" placeholder="" class="form-control"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-12" style="text-align: center">
                                        <input type="hidden" name="empId" value="<%=incident.ReportedBy%>">
                                        <button class="btn btn-theme" type="submit">Submit</button>
                                        <button class="btn btn-theme" id="sbmt1" type="submit" name="repair" value="repair">Submit and Create Repair</button>
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
        function doSomething(val) {
            var sbmt1 = document.getElementById('sbmt1');
            if (val == 3) {
                sbmt1.style.display = 'none';
            } else {
                sbmt1.style.display = 'inline-block';
            }
        }
    </script>
</html>

