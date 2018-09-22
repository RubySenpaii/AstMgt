<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--
CREATE MUNICPAL SUMMARY
CREATE BRGY SUMMARY
ADD MUNICIPAL/BRGY/FARMER DISTINCTION(CODE) FOR THE TREEMAP LINK SELECTION


--%>
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
                        Farmer Profile(Farmer Activity)
                        <small>Optional description</small>
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="box box-primary">
                                <div class="box-body box-profile">

                                    <h3 class="profile-username text-center"><c:out value="${farmDet.name}"/> </h3>

                                    <!-- <p class="text-muted text-center">Jose</p> -->

                                    <ul class="list-group list-group-unbordered">

                                        <li class="list-group-item">
                                            <b>Phone Number</b> <a class="pull-right"><c:out value="${farmDet.phone}"/> </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Gender</b> <a class="pull-right">
                                                Male
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Education</b> <a class="pull-right">
                                                Highschool
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Civil Status</b> <a class="pull-right">
                                                Married</a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Address</b> <a class="">#3 sugarcane st. brgy regulations</a>
                                        </li>

                                    </ul>

                                </div>
                                <!-- /.box-body -->
                            </div>    
                        </div>

                        <div class="col-md-4">
                            <div class="box box-primary">
                                <div class="box-tools pull-right">
                                    <a tabindex="0" class="text-overflow" id="popFarmerProdDet" role="button"><i class="fa fa-question text-orange"></i></a>
                                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                </div>
                                <div class="box-body box-profile">

                                    <h3 class="profile-username text-center">Production Details : <c:out value="${farmDet.tYears}"/> year/s worth of data </h3>

                                    <!-- <p class="text-muted text-center">Jose</p> -->

                                    <ul class="list-group list-group-unbordered">

                                        <li class="list-group-item">
                                            <b>Current Production(tc)</b> <a class="pull-right"><c:out value="${farmDet.curProd}"/> </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Current Area Harvested(ha)</b> <a class="pull-right"><c:out value="${farmDet.curHA}"/> </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Current Yield(tc/ha)</b>  <a class="pull-right"><c:out value="${farmDet.curYield}"/></a>
                                        </li>
                                        <!--                                        <li class="list-group-item">
                                                                                    <b>Total Production</b> <a class="pull-right"><c:out value="${farmDet.totalProd}"/> </a>
                                                                                </li>
                                                                                
                                                                                <li class="list-group-item">
                                                                                    <b>Total Average Production</b> <a class="pull-right"><c:out value="${farmDet.avgProd}"/> </a>
                                                                                </li>
                                                                                <li class="list-group-item">
                                                                                    <b>Total Average Area Harvested</b> <a class="pull-right">
                                        <%--<c:out value="${farmDet.avgArea}"/>--%> 
                                     </a>
                                 </li>-->
                                        <li class="list-group-item">
                                            <b>Total Average Yield (tc/ha)</b> <a class="pull-right"><c:out value="${farmDet.tavgYield}"/> 
                                            </a>
                                        </li>
                                    </ul>

                                </div>
                                <!-- /.box-body -->
                            </div>    
                        </div>
                        <div class="col-md-12">
                            <div class="col-md-6" > 
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Problems List</h1>
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popFarmerProbList" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body">
                                        <table id="probTable" class="table  dispTable table-hover" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Problem</th>
                                                    <th>Description</th>
                                                    <th></th>
                                                </tr>
                                            </thead>

                                        </table>
                                    </div>

                                </div>

                            </div> 
                            <div class="col-md-6"> 
                                <div class="box box-info" >
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Recommendation List</h1>
                                        <div class="box-tools pull-right " >
                                            <a tabindex="0" class="text-overflow" id="popRecProjList" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool"  data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body">
                                        <table id="recTable" class="table  display table-hover" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Recommendation</th>
                                                    <th>Description</th>
                                                    <th></th>

                                                </tr>
                                            </thead>

                                        </table>
                                    </div>

                                </div>

                            </div>

                        </div>
                        <div class="col-md-12">
                            <div class="col-md-6"> 
                                <div class="box box-info collapsed-box" >
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Program List</h1>
                                        <div class="box-tools pull-right" >
                                            <a tabindex="0" class="text-overflow" id="popFarmerProgList" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool"  data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body">
                                        <table id="progTable" class="table  display table-hover" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Program</th>
                                                    <th>Description</th>
                                                    <th></th>

                                                </tr>
                                            </thead>

                                        </table>
                                    </div>

                                </div>

                            </div>
                        </div>
                        <div class="col-md-12">
                            <!-- LINE CHART -->
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h3 class="box-title">#Farms Details</h3>

                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popFarmerFarmList" role="button"><i class="fa fa-question text-orange"></i></a>
                                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                        </button>
                                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="box-body table-responsive">

                                    <table id="fieldtable" class="table table-hover">
                                        <thead><tr>
                                                <th>id</th>
                                                <th>Barangay</th>
                                                <th>Municipality</th>
                                                <th>Total Area(ha)</th>
                                                <th>Tons Cane(tc)</th>
                                                <th>Harvested Area(ha)</th>

                                                <th>Yield(tc/ha)</th>
                                                <th>% completed</th>

                                                <th></th>
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

        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popFarmerProdDet').popover(popFarmerProdDet);
                $('#popFarmerProbList').popover(popFarmerProbList);
                $('#popRecProjList').popover(popRecProjList);
                $('#popFarmerFarmList').popover(popFarmerFarmList);
                $('#popFarmerProgList').popover(popFarmerProgList);

            });

        </script>
        <script>

            $(document).ready(function () {


                var table = $('#fieldtable').DataTable({
                    'ajax': {
                        'url': 'viewFarmersFieldTable?name=${farm}'
                    },
                    'columnDefs': [
                       
                        {
                            'targets': 7,
                            'render': function (data, type, full, meta) {
                                return   '<div class="progress-group"> <span class="progress-number"><b>' + data + ' </b></span>  <div class="progress progress-sm progress-striped active"><div class="progress-bar progress-bar-primary" style="width: ' + data + '%"></div></div> </div>';

                            }
                        }, {
                            'targets': 8,
                            'render': function (data, type, full, meta) {
                                return '<a class="btn btn-primary" href="viewFieldDetails?id=' + data + '">details</a>';
                            }
                        }, {className: "text-right", "targets": [3, 4,5,6]}]
                });
                var table = $('#recTable').DataTable({
                    'ajax': {
                        'url': 'viewFarmerRecT?name=${farm}'
                    },
                    'columnDefs': [{
                            'targets': 2,
                            'render': function (data, type, full, meta) {
                                return '<a class="btn btn-primary" href="viewRecDetails?id=' + data + '">details</a>';
                            }
                        }]
                });

                var table1 = $('#probTable').DataTable({
                    'ajax': {
                        'url': 'viewFarmerProbT?name=${farm}'
                    },
                    'columnDefs': [{
                            'targets': 2,
                            'render': function (data, type, full, meta) {
                                return '<a class="btn btn-primary" href="viewProbDetails?id=' + data + '">details</a>';
                            }
                        }]
                });
                var table2 = $('#progTable').DataTable({
                    'ajax': {
                        'url': 'viewFarmerProgT?name=${farm}'
                    },
                    'columnDefs': [{
                            'targets': 2,
                            'render': function (data, type, full, meta) {
                                return '<a class="btn btn-primary" href="viewProgramDetails?name=' + data + '">details</a>';
                            }
                        }]
                });
                setInterval(function () {
                    autoloadpage();
                }, 30000);
                function autoloadpage() {
                    $('#fieldtable').DataTable({
                        destroy: true,
                        'ajax': {
                            'url': 'viewFarmersFieldTable?name=${farm}'
                        },
                        'columnDefs': [
                     {className: "text-right", "targets": [3,4,5,6]},{
                                'targets': 7,
                                'render': function (data, type, full, meta) {
                                    return   '<div class="progress-group"> <span class="progress-number"><b>' + data + ' </b></span>  <div class="progress progress-sm progress-striped active"><div class="progress-bar progress-bar-primary" style="width: ' + data + '%"></div></div> </div>';

                                }
                            }, {
                                'targets': 8,
                                'render': function (data, type, full, meta) {
                                    return '<a class="btn btn-primary" href="viewFieldDetails?id=' + data + '">details</a>';
                                }
                            }]
                    });
                }
            });


        </script>
    </body>

</html>