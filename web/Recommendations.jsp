<%-- 
    Document   : Recommendations
    Created on : 09 27, 16, 12:03:24 AM
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
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="plugins/pace2/themes/green/pace-theme-center-simple.css" rel="stylesheet" />
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        Recommendations
                        <small>Shows a list of recommendation</small>
                    </h1>
                </section>
                <section class="content">
                    <div class="row">


                        <div class="col-md-12" > 
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Recommendations</h1>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popRec" role="button"><i class="fa fa-question text-orange"></i></a>
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>

                                <div class="box-body">
                                    <table id="recList" class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Recommendation</th>
                                                <th>Type</th>
                                                <th>Description</th>
                                                <th>Improvement</th>
                                                <th>Phase</th>
                                                <th></th>
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
        <script src="plugins/pace2/pace.min.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popRec').popover(popRec);

            });


        </script>
        <script>

            $(document).ready(function () {
                var table = $('#recList').DataTable({
                    'ajax': {
                        'url': 'viewRecList'
                    },
                    'columnDefs': [{
                            'targets': 5,
                            'render': function (data, type, full, meta) {
                                return '<a href="viewRecDetails?id=' + data + '" class="btn btn-primary text-center">' + 'more details' + '</a>';
                            }

                        }]
                });
            });


        </script>
    </body>
</html>
