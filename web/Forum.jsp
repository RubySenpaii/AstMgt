<%-- 
    Document   : Forum
    Created on : 09 21, 16, 12:38:23 PM
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
                    <h1>
                        Forum
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="box">
                                <div class="box-header pull-right">
                                     <a tabindex="0" class="" id="popForums" role="button"><i class="fa fa-question text-orange"></i></a> 
                                </div>
                                <div class="box-body">
                                    <ul class="nav nav-tabs">
                                        <li>  </li>
                                        <li class="active"><a href="#tab_7" data-toggle="tab"><b>All</b></a></li>
                                        <li><a href="#tab_8" data-toggle="tab"><b>Accepted</b></a></li>
                                        <li><a href="#tab_9" data-toggle="tab"><b>Pending</b></a></li>

                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="tab_7">
                                            <table id="myTable1" class="table table-striped table-bordered" cellspacing="10" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Topic</th>
                                                        <th>Recommendation</th>
                                                        <th>Counter</th>
                                                        <th>Problem</th>
                                                        <th>Counter</th>
                                                        <th>Status</th>
                                                        <th>Date</th>
                                                        <th style="width:5%">Details</th>
                                                    </tr>
                                                </thead>
                                            </table>  
                                        </div>
                                        <!-- /.tab-pane -->
                                        <div class="tab-pane" id="tab_8">
                                            <table id="myTable2" class="table table-striped table-bordered" cellspacing="10" width="100%">
                                                <thead>
                                                    <tr>

                                                        <th>Topic</th>
                                                        <th>Recommendation</th>
                                                        <th>Counter</th>
                                                        <th>Problem</th>
                                                        <th>Counter</th>
                                                        <th>Status</th>
                                                        <th>Date</th>
                                                        <th style="width:5%">Details</th>
                                                    </tr>
                                                </thead>
                                            </table>
                                        </div>
                                        <!-- /.tab-pane -->
                                        <div class="tab-pane" id="tab_9">
                                            <table id="myTable3" class="table table-striped table-bordered" cellspacing="10" width="100%">
                                                <thead>
                                                    <tr>

                                                        <th>Topic</th>
                                                        <th>Recommendation</th>
                                                        <th>Counter</th>
                                                        <th>Problem</th>
                                                        <th>Counter</th>
                                                        <th>Status</th>
                                                        <th>Date</th>
                                                        <th style="width:5%">Details</th>
                                                    </tr>
                                                </thead>
                                            </table>
                                        </div>
                                        <!-- /.tab-pane -->
                                    </div>
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
        <script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="dist/js/app.min.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popForums').popover(popForumD);
            });


        </script>
        <script>

            $(document).ready(function () {
                $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
                    $.fn.dataTable.tables({visible: false, api: true}).columns.adjust();
                });
                $('table.table').DataTable({
                    'ajax': {'url': 'viewForumList'
                    },
                    scrollY: 200,
                    scrollCollapse: true,
                    paging: false,
                    'columnDefs': [{
                            'targets': 7,
                            'searchable': false,
                            'orderable': false,
                            'render': function (data, type, full, meta) {
                                return '<a href="viewPostDetails?id=' + data + '" class="btn btn-primary">More Details</a>';
                            }
                        }]
                });

                // Apply a search to the second table for the demo
                $('#myTable2').DataTable().search('Accepted').draw();
                $('#myTable3').DataTable().search('Pending').draw();
            });


        </script>
    </body>
</html>
