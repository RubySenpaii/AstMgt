<%-- 
    Document   : add
    Created on : Oct 7, 2018, 3:18:42 PM
    Author     : RubySenpaii
--%>

<%@page import="objects.Asset"%>
<%@page import="java.util.ArrayList"%>
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
                            <form class="form-horizontal style-form" action="/AMS/PurchaseRequest/Submit">
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Responsibility Center Code</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="rcc" name="rcc" placeholder="Responsibility Center Code">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Purpose</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" id="purpose" name="purpose" placeholder="Purpose">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-2 control-label" for="exampleInputPassword1">Asset Items</label>
                                    <div class="col-lg-10">
                                        <table style="width:100%" name="assetTable" id="assetTable">
                                            <tr>
                                                <th>Asset</th>
                                                <th>Quantity</th> 
                                                <th>Price</th>
                                                <th></th>
                                            </tr>
                                            <tr>
                                                <td><input list="ass" name="assets">
                                                    <datalist id="ass">
                                                        <%
                                                            ArrayList<Asset> alist = new ArrayList<Asset>();
                                                            alist = (ArrayList<Asset>) session.getAttribute("assets");
                                                            for (Asset asset : alist) {
                                                        %>
                                                        <option value="<%= asset.AssetName%>">
                                                            <%
                                                                }
                                                            %>
                                                    </datalist></td>
                                                <td><input type="number" name="quantity"></td> 
                                                <td><input type="number" name="price"></td> 
                                                <td><button class="btn btn-theme" onclick="addCell()" type="button"><i class="fa fa-plus"></i></button></td>
                                            </tr>
                                        </table>
                                                    <script>
                                                        function  addCell(){
                                                            var table = document.getElementById("assetTable");
                                                            var row = table.insertRow(2);
                                                            var cell1 = row.insertCell(0);
                                                            var cell2 = row.insertCell(1);
                                                            var cell3 = row.insertCell(2);
                                                            var cell4 = row.insertCell(3);
                                                            cell1.innerHTML = "<td><input list='ass' name='assets'>";
                                                            cell2.innerHTML = "<input type='number' name='quantity'>";
                                                            cell3.innerHTML = "<input type='number' name='price'>";
                                                            cell4.innerHTML = "<button class='btn btn-danger' onclick='remCell()' type='button'><i class='fa fa-minus'></i></button>"
                                                        }
                                                    </script>
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
