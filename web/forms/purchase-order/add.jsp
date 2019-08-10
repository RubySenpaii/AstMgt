<%-- 
    Document   : add
    Created on : Oct 7, 2018, 3:18:42 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.PurchaseRequest"%>
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
                                <%
                                    String division = (String) session.getAttribute("UserDivision");
                                    boolean isSaved = false;
                                    try {
                                        isSaved = (boolean) session.getAttribute("notif");
                                    } catch (Exception e) {
                                    }
                                %>
                                <input type="hidden" id="notif" name="notif" value="<%= isSaved%>">
                                <%
                                    session.removeAttribute("Notification");
                                %>
                                <input type="hidden" name="division" id="division" value="<%=division%>">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Supplier Name</label>
                                    <div class="col-lg-10">
                                        <%
                                            PurchaseRequest pr = (PurchaseRequest) session.getAttribute("purchaseRequest");

                                        %>
                                        <input list="supp" name="supplier" id="supplier" class="form-control" autocomplete="off" value="<%=pr.Supplier.SupplierName%>">

                                        <datalist id="supp">
                                            <%
                                                ArrayList<Supplier> slist = new ArrayList<Supplier>();
                                                slist = (ArrayList<Supplier>) session.getAttribute("supplierList");
                                                for (Supplier supp : slist) {
                                            %>
                                            <option value="<%= supp.SupplierName%>">
                                                <%
                                                    }
                                                %>
                                        </datalist>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Remarks</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="remarks" name="remarks" placeholder="Remarks" autocomplete="off">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Delivery Date</label>
                                    <div class="col-lg-10">
                                        <input type="date" class="form-control" id="deldate" name="deldate" placeholder="Requested Date" autocomplete="off">
                                    </div>
                                </div>
                                <!--<div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Delivery Terms</label>
                                    <div class="col-lg-10">
                                        <select class="form-control" id="delterms" name="delterms" autocomplete="off">
                                            <option></option>
                                        </select>
                                    </div>
                                </div> -->
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Payment Terms</label>
                                    <div class="col-lg-10">
                                        <select class="form-control" id="payterms" name="payterms">
                                            <option>15 days</option>
                                            <option>30 days</option>
                                            <option>45 days</option>
                                            <option>60 days</option>
                                            <option>90 days</option>
                                            <option>120 days</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Supplier Point Person</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="consupp" name="consupp" placeholder="Conforme Supplier" autocomplete="off">
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
    <script>
        $(document).ready(function () {
            var type = document.getElementById('supplier').value;
            console.log('supplier' + type);
            $.ajax({
                url: '/AMS/AjaxServlet/RetrieveContactPerson',
                dataType: 'json',
                data: {supplier: type},
                success: function (data) {
                    console.log(data);
                    $('#consupp').val(data.ContactPerson);
                }
            });
            $('#supplier').on('change', function () {
                var type = $(this).val();
                console.log('supplier' + type);
                $.ajax({
                    url: '/AMS/AjaxServlet/RetrieveContactPerson',
                    dataType: 'json',
                    data: {supplier: type},
                    success: function (data) {
                        console.log(data);
                        $('#consupp').val(data.ContactPerson);
                    }
                });
            });
//            var notif = document.getElementById("notif");
//            if (notif.value === 'true') {
//                alert("Successfully saved the Purchase Request !")
//            } else if (notif.value === 'false') {
//                alert("Failed to save the Purchase Request !")
//            }
        });
    </script>
</html>
