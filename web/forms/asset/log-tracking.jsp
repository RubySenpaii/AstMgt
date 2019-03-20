<%-- 
    Document   : log-tracking
    Created on : 11 10, 18, 8:51:15 PM
    Author     : rubysenpaii
--%>

<%@page import="objects.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Log Tracking</title>
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
                        <div class="col-md-6">
                            <div class="form-panel">
                                <h4>Log Tracking of Asset</h4><br/>
                                <form role="form" class="form-horizontal style-form" action="/AMS/AssetServlet/SubmitTracking">
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Asset Tag</label>
                                        <div class="col-lg-10">
                                            <input type="text" name="asset-tag" placeholder="" class="form-control" autocomplete="off">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Release To</label>
                                        <div class="col-lg-10">
                                            <input type="text" name="release-to" placeholder="" class="form-control" autocomplete="off" list="employee-list">
                                            <datalist id="employee-list">
                                                <%
                                                    ArrayList<Employee> employees = (ArrayList<Employee>) session.getAttribute("employees");
                                                    for (Employee employee : employees) {
                                                %>
                                                <option><%=employee.FullName()%></option>
                                                <%
                                                    }
                                                %>
                                            </datalist>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Transfer Date</label>
                                        <div class="col-lg-10">
                                            <input type="date" name="transfer-date" placeholder="" class="form-control" autocomplete="off">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-2 control-label">Transfer Type</label>
                                        <div class="col-lg-10">
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="transfer-type" value="1">Stocked
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="transfer-type" value="2">Using
                                                </label>
                                            </div>
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
