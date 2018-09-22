<%-- 
    Document   : CitiesWeekView
    Created on : 10 28, 16, 7:49:03 AM
    Author     : Bryll Joey Delfin
--%>
<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>SRA | Home</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <style type="text/css" media="print">
            img
            {
                display:none;
            }  
            .hidethis
            {
                display:none;
            }
        </style>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
          
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        Weekly Production Statistics Report
                        <small>${todayDate}</small>
                    </h1>
                </section>
                <section class="content">
            <div class="row">
                    <div class="col-md-12" > 
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h1 class="box-title"><b>Weekly Production Statistics Report : ${todayDate}</b></h1>
                                <div class="box-tools pull-right hidethis">
                                    <a tabindex="0" class="text-overflow" id="popWeeklyProd" role="button"><i class="fa fa-question text-orange"></i></a>
                                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                    <!-- In box-tools add this button if you intend to use the contacts pane -->
                                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>

                            <div class="box-body no-padding">
                                <table  class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>District</th>
                                                <th>Estimated Production</th>
                                                <th>This Week (Area)</th>
                                                <th>This Week (Tons Cane)</th>
                                                <th>This Week (LKG)</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="prod" items="${crop}">
                                            <tr>
                                                <td><a href="Homepage.jsp">${prod.district}</a></td>
                                                <td class="text-right">${prod.estimated}</td>
                                                <td class="text-right">${prod.area}</td>
                                                <td class="text-right">${prod.tc}</td>
                                                <td class="text-right">${prod.lkg}</td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                            </div>

                        </div>
                    </div>   
                                <div class="col-md-4 col-md-offset-4 hidethis">
                                    <a  class="btn btn-primary btn-block" role="button" onClick="window.print();" ><i class="fa fa-print"></i> Print Report</a>
                                    <!--                                <button id="cmd" type="button">print PDF</button>-->
                                    <br>
                                </div>     
            </div>
                </section>
            </div>
                                <footer class="main-footer">
                <div class="pull-right hidden-xs">
                    <b>Version</b> 2.3.3
                </div>
                <strong>Copyright &copy; 2014-2015 <a href="http://sra.com">Sugar Regulatory Association</a>.</strong>
            </footer>
        </div>
        <script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="dist/js/app.min.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popWeeklyProd').popover(popWPSR);
            });


        </script>
        <script>

            $(document).ready(function () {
                var table = $('#example').DataTable({
                    'ajax': {
                        'url': '#'
                    },
                    'columnDefs': [{
                            'targets': 6,
                        
                            'render': function (data, type, full, meta) {
                                return '<a href="viewProbDetails?id=' + data + '" class="btn btn-primary">More Details</a>';
                            }
                            
                        }]
                });
            });


        </script>
    </body>
</html>
