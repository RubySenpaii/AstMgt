<%-- 
    Document   : expenditure_limit
    Created on : Oct 14, 2018, 7:38:53 PM
    Author     : RubySenpaii
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Set Expenditure Limit</title>
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
                        <div class="col-md-6">
                            <div class="form-panel">
                                <h4>Annual Procurement Plan</h4>
                                <input type="file" onChange="readFile(event)">
                                <br><br>
                                <h4>Work and Financial Plan</h4>
                                <input type="file" onChange="readWfp(event)">
                            </div>
                        </div>
                        <%
                            session.removeAttribute("Notification");
                        %>
                        <div class="col-md-6" style="display: none">
                            <div class="form-panel">
                                <select onchange="document.getElementById('pdfViewer').setAttribute('data', '/AMS/uploaded-files/app/' + document.getElementById('select-file').value)" id="select-file">
                                    <option selected="true" disabled>- Select an Option -</option>
                                    <%
                                        ArrayList<String> files = (ArrayList<String>) session.getAttribute("fileList");
                                        for (String file : files) {
                                    %>
                                    <option value="<%=file%>"><%=file%></option>
                                    <%
                                        }
                                    %>
                                </select>
                                <object id="pdfViewer" data="" type="application/pdf" width="100%" height="800px" style="padding: 20px">
                                </object>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-panel">
                                <h4>Set Limit of Expenditure for Asset</h4><br/>
                                <form role="form" class="form-horizontal style-form" action="/AMS/ExpenditureServlet/Submit" enctype="multipart/form-data" method="POST">
                                    <div class="form-group">
                                        <div class="col-lg-12">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Division</th>
                                                        <th>Equipment</th>
                                                        <!--<th>Supplies</th>-->
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>Procurement Division</td>
                                                        <td>
                                                            <input type="text" name="procurement-equipment" id="procurement" onchange="total()" autocomplete="off" style="text-align: right; width: 100%">
                                                        </td>
                                                        <!--                                                        <td>
                                                                                                                    <input type="number" name="procurement-supplies" autocomplete="off">
                                                                                                                </td>-->
                                                    </tr>
                                                    <tr>
                                                        <td>Personnel</td>
                                                        <td>
                                                            <input type="text" name="management-equipment" id="management" onchange="total()" autocomplete="off" style="text-align: right; width: 100%">
                                                        </td>
                                                        <!--                                                        <td>
                                                                                                                    <input type="number" name="management-supplies" autocomplete="off">
                                                                                                                </td>-->
                                                    </tr>
                                                    <tr>
                                                        <td>Admin Services</td>
                                                        <td>
                                                            <input type="text" name="admin-equipment" id="admin" onChange="total()" autocomplete="off" style="text-align: right; width: 100%">
                                                        </td>
                                                        <!--                                                        <td>
                                                                                                                    <input type="number" name="admin-supplies" autocomplete="off">
                                                                                                                </td>-->
                                                    </tr>
                                                    <tr>
                                                        <td>General Services</td>
                                                        <td>
                                                            <input type="text" name="general-equipment" id="general" onChange="total()" autocomplete="off" style="text-align: right; width: 100%">
                                                        </td>
                                                        <!--                                                        <td>
                                                                                                                    <input type="number" name="general-supplies" autocomplete="off">
                                                                                                                </td>-->
                                                    </tr>
                                                    <tr>
                                                        <td>Records</td>
                                                        <td>
                                                            <input type="text" name="finance-equipment" id="finance" onChange="total()" autocomplete="off" style="text-align: right; width: 100%">
                                                        </td>
                                                        <!--                                                        <td>
                                                                                                                    <input type="number" name="finance-supplies" autocomplete="off">
                                                                                                                </td>-->
                                                    </tr>
                                                    <tr>
                                                        <td>Repair and Maintenance</td>
                                                        <td>
                                                            <input type="text" name="repair-maintenance" id="repair" onChange="total()" autocomplete="off" style="text-align: right; width: 100%">
                                                        </td>
                                                    </tr>
                                                </tbody>
                                                <tfoot>
                                                    <tr>
                                                        <th>Total</th>
                                                        <th style="text-align: right"><span id="totalValue" style="text-align: right"></span></th>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                    <div style="text-align: center">
                                        <button type="submit" class="btn btn-theme">Submit</button>
                                    </div>
                                </form>
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
    <script>
        function total() {
            var procurement = document.getElementById('procurement').value ? document.getElementById('procurement').value : 0;
            var management = document.getElementById('management').value ? document.getElementById('management').value : 0;
            var admin = document.getElementById('admin').value ? document.getElementById('admin').value : 0;
            var general = document.getElementById('general').value ? document.getElementById('general').value : 0;
            var finance = document.getElementById('finance').value ? document.getElementById('finance').value : 0;
            var repair = document.getElementById('repair').value ? document.getElementById('repair').value : 0;
            var total = Number(procurement) + Number(management) + Number(admin) + Number(general) + Number(finance) + Number(repair);
            document.getElementById('totalValue').textContent = total;
        }

        function readFile(event) {
            var file = event.target.files[0];

            var reader = new FileReader();
            reader.onload = function (e) {
                var csv = e.target.result;
                var data = $.csv.toArrays(csv);
                console.log(data);

                var qtrIdx = -1;
                var priceIdx = -1;
                var qtr = getQuarter();
                for (var i = 0; i < data[0].length; i++) {
                    if (data[0][i] == qtr) {
                        qtrIdx = i;
                    } else if (data[0][i].toLowerCase().includes('price')) {
                        priceIdx = i;
                    }
                }
                console.log('quarter idx', qtrIdx);
                console.log('price idx', priceIdx);
                var totalAmount = 0;
                if (qtrIdx == -1 || priceIdx == -1) {
                    alert('CSV Parsing Error. Quantity and/or Price Not Available');
                } else {
                    var filteredData = [], equipmentFlag = false;
                    for (var i = 30; i < data.length; i++) {
                        if (data[i][0].toLowerCase().includes('equipment') || data[i][1].toLowerCase().includes('equipment') ||
                                data[i][0].toLowerCase().includes('devices') || data[i][1].toLowerCase().includes('devices')) {
                            filteredData.push(data[i]);
                            equipmentFlag = true;
                        } else if (equipmentFlag) {
                            if (data[i][0] != "" && data[i][1] != "") {
                                filteredData.push(data[i]);
                                totalAmount += (Number(data[i][qtrIdx].replace(/ /g, '').replace(/,/g, '')) * Number(data[i][priceIdx].replace(/ /g, '').replace(/,/g, '')));
                                console.log('row total', totalAmount);
                            } else {
                                equipmentFlag = false;
                            }
                        }
                    }
                }
                totalAmount = Math.round(totalAmount * 100) / 100;
                var amountPerDivision = Math.round((totalAmount / 5) * 100) / 100;
                console.log('allocation amount', totalAmount);
                console.log('amount per division', amountPerDivision);
                document.getElementById('admin').value = amountPerDivision;
                document.getElementById('procurement').value = amountPerDivision;
                document.getElementById('management').value = amountPerDivision;
                document.getElementById('general').value = amountPerDivision;
                document.getElementById('finance').value = amountPerDivision;

                console.log('filtered data', filteredData);
                total();
            };

            reader.onerror = function (ex) {
                console.log(ex);
            };

            reader.readAsText(file);
        }

        function readWfp(event) {
            console.log('read wfp');
            var file = event.target.files[0];

            var index = {
                "repair": 4,
                "q1": 14,
                "q2": 15,
                "q3": 16,
                "q4": 17,
                "total": 18
            }

            var reader = new FileReader();
            reader.onload = function (e) {
                var csv = e.target.result;
                var data = $.csv.toArrays(csv);
                console.log(data);

                var flag = false;
                var repairs = 0;
                for (var i = 0; i < data.length; i++) {
                    if ((data[i][index.repair] != null || data[i][index.repair] != '') && data[i][index.repair].toLowerCase().includes('repair')) {
                        flag = true;
                        continue;
                    }

                    if (flag && data[i][index.repair] != '') {
                        repairs += parseFloat(data[i][index.total].replace(/,/g, ''));
                    } else if (flag) {
                        break;
                    }
                }
                document.getElementById('repair').value = repairs;
                total();
            };

            reader.onerror = function (ex) {
                console.log(ex);
            };

            reader.readAsText(file);
        }

        function getQuarter() {
            var date = new Date();
            var month = date.getMonth();
            switch (month) {
                case 0:
                case 1:
                case 2:
                    return "Q1";
                case 3:
                case 4:
                case 5:
                    return "Q2";
                case 6:
                case 7:
                case 8:
                    return "Q3";
                case 9:
                case 10:
                case 11:
                    return "Q4";
            }
        }
    </script>
</html>
