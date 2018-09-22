<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
ADD MUNICIPAL/BRGY/FARMER DISTINCTION(CODE) FOR THE TREEMAP LINK SELECTION


--%>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>SRA | Home</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="plugins/pace2/themes/green/pace-theme-center-simple.css" rel="stylesheet" />
        <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
        <link rel="stylesheet" href="plugins/select2/select2.min.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">

        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content-header">

                    <h1>
                        Search Farmer

                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <!-- LINE CHART -->
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h3 class="box-title">#Farmer Details</h3>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popSearchFarmer" role="button"><i class="fa fa-question text-orange"></i></a>

                                    </div>                                  
                                </div>
                                <div class="box-body table-responsive">

                                    <table id="fieldtable" class="table table-hover">
                                        <thead><tr>
                                                <th>Farmer</th>
                                                <th># of Farms</th>
                                                <th>Year</th>
                                                <th>Total Area(ha)</th>
                                                <th>Production(tc)</th>
                                                <th>Details</th>
                                            </tr>
                                        </thead>
                                    </table>


                                </div>
                                <!-- /.box-body -->
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
        <script src="plugins/pace2/pace.min.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
  <script src="plugins/poptest/popoverText.js"></script>
        <script>
                                   $(document).ready(function () {
                                       $('#popSearchFarmer').popover(popSearchFarmer);

                                   });


        </script>

        <script>

            $(document).ready(function () {

                var table = $('#fieldtable').DataTable({
                    'ajax': {
                        'url': 'viewAllFarmers'
                    },
                    'columnDefs': [
                        {
                            'targets': 5,
                            'render': function (data, type, full, meta) {
                                return '<a href="viewFarmerProfile?name=' + data + '" class="btn btn-primary text-center">' + 'more details' + '</a>';
                            }
                        }

                    ]
                });



            });


        </script>
    </body>

</html>