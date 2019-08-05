<%-- 
    Document   : expenditurev2
    Created on : 03 31, 19, 5:12:09 PM
    Author     : rubysenpaii
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
                        <div class="col-md-12">
                            <div class="form-panel">
                                <h4>Set Limit of Expenditure for Asset</h4><br/>
                                <form role="form" class="form-horizontal style-form" action="/AMS/ExpenditureServlet/Submitv2" enctype="multipart/form-data" method="POST">
                                    <div class="form-group">
                                        <div class="col-lg-12">
                                            <input type="file" onChange="readFile(event)">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Asset Name</th>
                                                        <th>Asset Type</th>
                                                        <th>Available Quantity</th>
                                                        <th>Price</th>
                                                        <th>Estimated Useful Life</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody id="item-expenditure"></tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div style="text-align: center">
                                        <button type="button" class="btn btn" id="compute">Compute</button>
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
//        $(document).ready(function() {
//            $("#compute").on("click", function() {
//                var admin = $("input[name='admin']");
//                var procurement = $("input[name='procurement']");
//                var general = $("input[name='general']");
//                var personnel = $("input[name='personnel']");
//                var records = $("input[name='records']");
//                var price = $("input[name='price']");
//                var adminPrice = 0, procurementPrice = 0, generalPrice = 0, personnelPrice = 0, recordsPrice = 0;
//                for (var i = 0; i < price.length; i++) {
//                    adminPrice += Number(admin[i].value) * Number(price[i].value);
//                    procurementPrice += Number(procurement[i].value) * Number(price[i].value);
//                    generalPrice += Number(general[i].value) * Number(price[i].value);
//                    personnelPrice += Number(personnel[i].value) * Number(price[i].value);
//                    recordsPrice += Number(records[i].value) * Number(price[i].value);
//                }
//                $("#adminTotal").val(adminPrice);
//                $("#procurementTotal").val(procurementPrice);
//                $("#generalTotal").val(generalPrice);
//                $("#personnelTotal").val(personnelPrice);
//                $("#recordsTotal").val(recordsPrice);
//            });
//        });
        
        function total() {
            var procurement = document.getElementById('procurement').value;
            var management = document.getElementById('management').value;
            var admin = document.getElementById('admin').value;
            var general = document.getElementById('general').value;
            var finance = document.getElementById('finance').value;
            var total = Number(procurement) + Number(management) + Number(admin) + Number(general) + Number(finance);
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
                var customArray = [];
                var totalAmount = 0;
                if (qtrIdx == -1 || priceIdx == -1) {
                    alert('CSV Parsing Error. Quantity and/or Price Not Available');
                } else {
                    var filteredData = [], equipmentFlag = false, type;
                    for (var i = 0; i < data.length; i++) {
                        if (data[i][0].toLowerCase().includes('equipment') || data[i][1].toLowerCase().includes('equipment') ||
                                data[i][0].toLowerCase().includes('devices') || data[i][1].toLowerCase().includes('devices')) {
                            filteredData.push(data[i]);
                            type = data[i][0];
                            equipmentFlag = true;
                        } else if (equipmentFlag) {
                            if (data[i][0] != "" && data[i][1] != "") {
                                filteredData.push(data[i]);
                                totalAmount += (Number(data[i][qtrIdx].replace(/ /g, '').replace(/,/g, '')) * Number(data[i][priceIdx].replace(/ /g, '').replace(/,/g, '')));
                                var customObj = {};
                                customObj.name = data[i][1];
                                customObj.description = data[i][2];
                                customObj.quantity = Number(data[i][qtrIdx].replace(/ /g, '').replace(/,/g, ''));
                                customObj.price = Number(data[i][priceIdx].replace(/ /g, '').replace(/,/g, ''));
                                customObj.type = type;
                                customObj.estUsefulLife = data[i][23];
                                customArray.push(customObj);
                                console.log('row total', totalAmount);
                            } else {
                                equipmentFlag = false;
                            }
                        }
                    }
                }
                totalAmount = Math.round(totalAmount * 100) / 100;
                var amountPerDivision = Math.round((totalAmount / 5) * 100) / 100;

                var tableBody = document.getElementById('item-expenditure');
                for (var i = 0; i < customArray.length; i++) {
                    var tableRow = document.createElement('tr');
                    var cell1 = document.createElement('td');
                    var cell1Text = document.createTextNode(customArray[i].name);
                    var cell1Input = document.createElement('input');
                    cell1Input.setAttribute('type', 'hidden');
                    cell1Input.setAttribute('name', 'name');
                    cell1Input.value = customArray[i].name;
                    cell1.appendChild(cell1Input);
                    cell1.appendChild(cell1Text);
                    var cell2 = document.createElement('td');
                    var cell2Text = document.createTextNode(customArray[i].type);
                    var cell2Input = document.createElement('input');
                    cell2Input.setAttribute('type', 'hidden');
                    cell2Input.setAttribute('name', 'type');
                    cell2Input.value = customArray[i].type;
                    cell2.appendChild(cell2Input);
                    cell2.appendChild(cell2Text);
                    var cell3 = document.createElement('td');
                    var cell3Text = document.createTextNode(customArray[i].quantity);
                    var quantityPerDivision = Math.floor(Number(customArray[i].quantity));
                    cell3.appendChild(cell3Text);
                    var cell4 = document.createElement('td');
                    var cell4Text = document.createTextNode(customArray[i].price);
                    var cell4Input = document.createElement('input');
                    cell4Input.setAttribute('type', 'hidden');
                    cell4Input.setAttribute('name', 'price')
                    cell4Input.value = customArray[i].price;
                    cell4.appendChild(cell4Input);
                    cell4.appendChild(cell4Text);
                    var cell11 = document.createElement('td');
                    var cell11Text = document.createTextNode(customArray[i].estUsefulLife);
                    var cell11Input = document.createElement('input');
                    cell11Input.setAttribute('type', 'hidden');
                    cell11Input.setAttribute('name', 'estUsefulLife')
                    cell11Input.value = customArray[i].estUsefulLife;
                    cell11.appendChild(cell11Input);
                    cell11.appendChild(cell11Text);
                    tableRow.appendChild(cell1);
                    tableRow.appendChild(cell2);
                    tableRow.appendChild(cell3);
                    tableRow.appendChild(cell4);
//                    var cell5Input = document.createElement('input');
//                    cell5Input.setAttribute('name', 'admin');
//                    cell5Input.setAttribute('value', quantityPerDivision);
//                    var cell5 = document.createElement('td');
//                    cell5.appendChild(cell5Input);
                    var cell6Input = document.createElement('input');
                    cell6Input.setAttribute('name', 'general');
                    cell6Input.setAttribute('value', quantityPerDivision);
                    var cell6 = document.createElement('td');
                    cell6.appendChild(cell6Input);
//                    var cell7Input = document.createElement('input');
//                    cell7Input.setAttribute('name', 'procurement');
//                    cell7Input.setAttribute('value', quantityPerDivision);
//                    var cell7 = document.createElement('td');
//                    cell7.appendChild(cell7Input);
//                    var cell8Input = document.createElement('input');
//                    cell8Input.setAttribute('name', 'personnel');
//                    cell8Input.setAttribute('value', quantityPerDivision);
//                    var cell8 = document.createElement('td');
//                    cell8.appendChild(cell8Input);
//                    var cell9Input = document.createElement('input');
//                    cell9Input.setAttribute('name', 'records');
//                    cell9Input.setAttribute('value', quantityPerDivision);
//                    var cell9 = document.createElement('td');
//                    cell9.appendChild(cell9Input);
//                    var cell10input = document.createElement('input');
//                    cell10input.setAttribute('name', 'description');
//                    cell10input.setAttribute('value', customArray[i].description);
//                    tableRow.appendChild(cell5);
                    tableRow.appendChild(cell11);
                    tableRow.appendChild(cell6);
//                    tableRow.appendChild(cell7);
//                    tableRow.appendChild(cell8);
//                    tableRow.appendChild(cell9);
//                    tableRow.appendChild(cell10input);
                    tableBody.appendChild(tableRow);
                }

                console.log('filtered data', filteredData);
                console.log('custom array', customArray);
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
