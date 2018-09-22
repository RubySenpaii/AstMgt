<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>SRA | Home</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">

        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content-header">

                    <h1>
                        Plans & Programs List
                    </h1>
                </section>
                <section class="content">
                    <div class="row">

                        <div class="col-md-12"> 
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Plans & Programs List</h1> 
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popViewProgList" role="button"><i class="fa fa-question text-orange"></i></a>
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>

                                <div class="box-body">
                                    <table id="example" class="table display table-hover" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th width="20%">Program</th>
                                                <th>Type</th>
                                                <th>Date</th>
                                                <th width="5%">Total Farms</th>
                                                <th>Status</th>
                                                <th>Progress</th>

                                                <th>Details</th>

                                            </tr>
                                        </thead>

                                    </table>
                                </div>

                            </div>

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


        <script type="text/javascript" src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="dist/js/app.min.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popViewProgList').popover(popViewProgList);

            });


        </script>

        <script>

            $(document).ready(function () {
                var table = $('#example').DataTable({
                    'ajax': {
                        'url': 'viewProgramsTable'
                    },
                    'columnDefs': [
                        {
                            'targets': 5,
                            'render': function (data, type, full, meta) {
                                if (data >= 75) {
                                    return '<span class="badge bg-green" style="width:40%">' + data + '%</span>';
                                } else if (data >= 50) {
                                    return '<span class="badge bg-aqua" style="width:40%">' + data + '%</span>';
                                } else {
                                    return '<span class="badge bg-red" style="width:40%">' + data + '%</span>';
                                }

                            }
                        },
                        {
                            'targets': 6,
                            'render': function (data, type, full, meta) {
                                return '<a href="viewProgramDetails?name=' + data + '" class="btn btn-primary text-center">' + 'more details' + '</a>';
                            }
                        }

                    ], 'order': [[2, 'desc']]
                });
            });


        </script>
    </body>

</html>