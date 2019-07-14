<%-- 
    Document   : general-ppe
    Created on : 11 24, 18, 4:01:24 PM
    Author     : rubysenpaii
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Property Plant Equipment Report</title>
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
                        <div class="col-md-12">
                            <div class="content-panel">
                                <h4>General Property Plan Equipment Reports</h4>
                                <br>
                                <div class="row" style="border: 1px solid black; margin: 5px 30px">
                                    <form action="/AMS/ReportServlet/GenerateGeneralPPE">
                                        <h3 style="margin: 15px">Generate Reports</h3>
                                        <div class="form-group">
                                            <label class="control-label col-md-3">Date Range</label>
                                            <div class="col-md-4">
                                                <div class="input-group input-large" data-date="01/01/2014" data-date-format="mm/dd/yyyy">
                                                    <input type="text" class="form-control dpd1" name="from">
                                                    <span class="input-group-addon">To</span>
                                                    <input type="text" class="form-control dpd2" name="to">
                                                </div>
                                                <span class="help-block">Select date range</span>
                                            </div>
                                        </div>
                                        <div class="col-md-2"></div>
                                        <div class="col-md-8" style="margin: 20px auto; text-align: center">
                                            <input type="text" name="certified-by" placeholder="Certified By" style="margin: 10px; width: 200px">
                                            <input type="text" name="approved-by" placeholder="Approved By" style="margin: 10px; width: 200px">
                                            <input type="text" name="verified-by" placeholder="Verified By" style="margin: 10px; width: 200px">
                                            <button class="btn" type="submit">Generate Report</button>
                                        </div>
                                    </form>
                                </div>
                                <div class="row">
                                    <div class="col-md-4">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Name</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    ArrayList<String> files = (ArrayList<String>) session.getAttribute("fileList");
                                                    for (String file : files) {
                                                %>
                                                <tr>
                                                    <td>
                                                        <button onclick="document.getElementById('pdfViewer').setAttribute('data', '/AMS/pdf/general-ppe/<%=file%>')"><%=file%></button>
                                                    </td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="col-md-8">
                                        <object id="pdfViewer" data="" type="application/pdf" width="100%" height="800px" style="padding: 20px">
                                        </object>
                                    </div>
                                </div>
                            </div>
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
