<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>SRA | Home</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="plugins/pace2/themes/green/pace-theme-center-simple.css" rel="stylesheet" />
         <link rel="stylesheet" href="plugins/select2/select2.min.css">
        <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
        <link rel="stylesheet" href="plugins/select2/select2.min.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">

        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content-header">

                    <h1>
                        Crop Assessment List

                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-md-3 pull-right">
                                <label>Year</label>
                                <select class="form-control" id="select3" style="width: 100%;">

                                </select>
                             
                            </div>
                       
                        <div class="col-md-12">
                            <!-- LINE CHART -->
                                <br>
                            <div class="box box-info">
                                
                                <div class="box-header with-border">

                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popSearchFarmer" role="button"><i class="fa fa-question text-orange"></i></a>

                                    </div>                                  
                                </div>
                                <div class="box-body table-responsive">

                                    <table id="fieldtable" class="table table-hover">
                                        <thead><tr>
                                                <th>Year</th>
                                                <th>Date</th>
                                                <th>Phase</th>
                                                <th>Details</th>
                                                <th>Print</th>

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
         <script src="plugins/select2/select2.full.min.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popSearchFarmer').popover(popSearchFarmer);
                $('#popFarmPerf').popover(popFarmPerf);
                $('#popDistImp').popover(popDistImp);
                    
            });


        </script>

        <script>

            $(document).ready(function () {
             
                  $('#select3').on('change', function (evt) {
                    var test = $("#select3").val();
                    console.log(test);

                    var table = $('#fieldtable').DataTable({
                        destroy: true,
                        'ajax': {
                            'url': 'viewAllCropAssessment?calyear=' + test
                        },
                        'columnDefs': [
                            {
                                'targets': 3,
                                'render': function (data, type, full, meta) {
                                    return '<a class="btn btn-primary text-center" href="viewCropAssessment?theweek=' + data + '"> <i class="fa fa-info"></i><span> View Report</span></a>';
                                }
                            },{
                                'targets': 4,
                                'render': function (data, type, full, meta) {
                                    return '<a class="btn btn-danger text-center" target="_blank" href="viewCropAssessment?toprint=true&theweek=' + data + '"> <i class="fa fa-print"></i><span> Print Report</span> </a>';
                                }
                            }], 'order': [[1, 'desc']]
                    });
                });
                $.ajax({
                    url: 'DistinctCropYearList',
                    type: 'POST',
                    dataType: "JSON",
                    success: function (data) {
                       
                        $("#select3").select2({
                            minimumResultsForSearch: Infinity,
                            data: data
                        }).trigger('change');
                     

                    }});
              




            });


        </script>
    </body>

</html>