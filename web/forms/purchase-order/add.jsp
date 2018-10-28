<%-- 
    Document   : add
    Created on : Oct 7, 2018, 3:18:42 PM
    Author     : RubySenpaii
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="objects.Supplier"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Add Purchase Order</title>
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
                            <h4>Create Purchase Order</h4><br/>
                            <form class="form-horizontal style-form" action="/AMS/PurchaseOrderServlet/Submit">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Supplier Id</label>
                                    <div class="col-lg-10">
                                        <input list="supp" name="supplier">
                                        <datalist id="supp">
                                            <%
                                                ArrayList<Supplier> slist = new ArrayList<Supplier>();
                                                slist = (ArrayList<Supplier>) session.getAttribute("supplier");
                                                for (Supplier supp : slist) {
                                            %>
                                            <option value="<%= supp.SupplierName %>">
                                                <%
                                                    }
                                                %>
                                        </datalist>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Mode Of Procurement</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="mop" name="mop" placeholder="Mode Of Procurement">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Remarks</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="remarks" name="remarks" placeholder="Remarks">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Delivery Address</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="deladd" name="deladd" placeholder="Delivery Address">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Delivery Date</label>
                                    <div class="col-lg-10">
                                        <input type="date" class="form-control" id="deldate" name="deldate" placeholder="Requested Date">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Delivery Terms</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="delterms" name="delterms" placeholder="Delivery Terms">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Payment Terms</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="payterms" name="payterms" placeholder="Payment Terms">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Conforme Supplier</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="consupp" name="consupp" placeholder="Conforme Supplier">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Conforme Date</label>
                                    <div class="col-lg-10">
                                        <input type="date" class="form-control" id="condate" name="condate" placeholder="Requested Date">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">ORS Number</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="orsno" name="orsno" placeholder="ORS Number">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">ORS Date</label>
                                    <div class="col-lg-10">
                                        <input type="date" class="form-control" id="orsdate" name="orsdate" placeholder="ORS Date">
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
