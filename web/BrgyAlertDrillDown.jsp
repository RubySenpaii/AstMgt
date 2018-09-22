<%-- 
    Document   : BrgyAlertDrillDown
    Created on : 10 13, 16, 1:35:54 PM
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
                        Disaster Report
                        <small>Today's date : </small>
                    </h1>
                </section>
                <section class="content">
                    <div class="row">

                        <div class="box-body no-padding">
                            <div class="col-md-12" > 
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Disaster Report</h1>
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="" id="popDisaster" role="button"><i class="fa fa-question text-orange"></i></a>   
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <!-- In box-tools add this button if you intend to use the contacts pane -->
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>
                                    <div class="box-body">
                                        <table id="example" class="table table-bordered" >
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Barangay</th>
                                                    <th>Recorded Count</th>
                                                    <th>Total Fields</th>
                                                    <th>Percent Affected</th>
                                                    <th style="width: 5%">Alert</th>
                                                </tr>

                                            </thead>
                                        </table>
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
    </div>

</div>
<script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popDisaster').popover(popDisasterB);
            });


        </script>
<script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="dist/js/app.min.js"></script>

<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
 
<script>
                                   $(document).ready(function () {
                                       var table = $('#example').DataTable({
                                           'ajax': {
                                               'url': 'viewAlertByBarangay'
                                           },
                                           'columnDefs': [{
                                                   'targets': 5,
                                                   'render': function (data, type, full, meta) {
                                                       return '<a class="btn btn-primary" href="sendAlert?id=' + data + '">' + 'Send Alert' + '</a>';
                                                   }

                                               }], 'order': [[3, 'desc']]
                                       });
                                   });


</script>
</body>
</html>
