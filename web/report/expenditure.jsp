<%-- 
    Document   : expenditure
    Created on : 03 24, 19, 8:14:19 PM
    Author     : rubysenpaii
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Expenditure Report</title>
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
                                <h4>Expenditure Reports</h4>
                                <br>
                                <div class="row" style="border: 1px solid black; margin: 5px 30px">
                                    <form action="/AMS/ReportServlet/GenerateExpenditure">
                                        <h3 style="margin: 15px">Generate Reports</h3>
                                        <div class="col-md-2"></div>
                                        <div class="col-md-8" style="margin: 20px auto; text-align: center">
                                            <input type="text" name="certified-by" placeholder="Certified By" style="margin: 10px; width: 200px">
                                            <input type="text" name="approved-by" placeholder="Approved By" style="margin: 10px; width: 200px">
                                            <input type="text" name="verified-by" placeholder="Verified By" style="margin: 10px; width: 200px">
                                            <button class="btn" type="submit" onclick="alert('Expenditure Report Generated!')">Generate Report</button>
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
                                                        <button onclick="document.getElementById('pdfViewer').setAttribute('data', '/AMS/pdf/expenditure/<%=file%>')"><%=file%></button>
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
