<%-- 
    Document   : Problems
    Created on : 09 21, 16, 3:14:09 PM
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
        <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css"> 
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content-header">

                </section>
                <section class="content">
                    <div class="row">

                        <div class="col-md-12" > 
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Report Recommendation List</h1>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popProbList" role="button"><i class="fa fa-question text-orange"></i></a>
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>

                                <div class="box-body">
                                    <table id="example" class="table table-bordered">
                                        <thead>
                                            <tr>
                                               
                                                <th>Recommendation</th>
                                                <th>Farm</th>
                                                <th>Phase</th>
                                                <th>Type</th>
                                                <th>Status</th>
                                                <th>Description</th>
                                                <th>More Details</th>

                                            </tr>
                                        </thead>
                                    </table>
                                </div>

                            </div>
                        </div>        
                        <br>
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
                $('#popProbList').popover(popProbList);
            });


        </script>
        <script>

            $(document).ready(function () {
                var table = $('#example').DataTable({
                    'ajax': {
                        'url': 'viewRecListWeek?mondayofweek=${MondayofWeek}&sundayofweek=${SundayofWeek}&status=${status}'
                    },
                    'columnDefs': [{
                            'targets': 6,
                            'render': function (data, type, full, meta) {
                                return '<a href="viewRecDetails?id=' + data + '" class="btn btn-primary">More Details</a>';
                            }

                        }], 'order': [[0, 'asc']]
                });
            });


        </script>
    </body>
</html>
