<%@include file="security.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>SRA | Home</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link href="plugins/pace2/themes/green/pace-theme-loading-bar.css" rel="stylesheet" />
        <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css"> 
        <link rel="stylesheet" href="plugins/select2/select2.min.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        Today's Date: ${todayDate}
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4">
                            <c:if test="${empty narrative}">
                                <a class="btn btn-info btn-block" href="viewCropAssessment">Create Crop Assessment</a>
                            </c:if>
                            <br>
                        </div>
                        <div class="col-md-12"> 
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Crop Estimate</h1>
                                    <div class="box-tools pull-right">

                                        <a tabindex="0" class="text-overflow" id="popCropEst" role="button"><i class="fa fa-question text-orange"></i></a>  
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>    
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>   
                                <div class="box-body">
                                    <table id="munitable" class="table table-bordered text-right">
                                        <thead>
                                            <tr>
                                                <th style="width: 5%">Year</th>
                                                <th>Week Ending</th>
                                                <th>Harvest Area (ha)</th>
                                                <th>Rainfall (mm)</th>
                                                <th>Actual Production (tc)</th>
                                                <th>Estimated Production (tc)</th>
                                                <th>% Difference</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12" > 
                            <div class="box box-info" style="height:120%">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Yield Tree Map (Annual) </h1>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="" id="popYieldMap" role="button"><i class="fa fa-question text-orange"></i></a>  
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>   
                                <div class="box-body">
                                    <div class="col-md-2 pull-right">
                                        <select class="form-control treeselect" style="width:50%" id="select3">

                                        </select>
                                    </div>
                                    <div class="col-md-2 pull-right">
                                        <select class="form-control treeselect" style="width:70%" id="select6">
                                            <option selected="selected">Tons Cane</option>
                                            <option>Area</option>
                                            <option>Yield</option>
                                        </select>

                                    </div>

                                    <div id="container1" style="height:90%"></div>


                                </div>
                            </div>

                        </div>
                        <br>
                        <c:if test="${not empty CropAss}">
                            <div class="col-md-10" > 
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Area Harvested : <c:out value="${todayDate}"></c:out> </h1>
                                            <div class="box-tools pull-right">
                                                <a tabindex="0" class="" id="popAreaHarv" role="button"><i class="fa fa-question text-orange"></i></a>  
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>

                                        <div class="box-body no-padding">
                                            <table class="table table-bordered"  >
                                                <thead>
                                                    <tr>
                                                        <th>Particulars</th>
                                                        <th>Estimated Production</th>
                                                        <th>Previous</th>
                                                        <th>This Week</th>
                                                        <th>To Date</th>
                                                        <th>Percent Completed</th>	
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="ca" items="${CropAss}">
                                                    <tr>	
                                                        <td>${ca.particulars}</td>
                                                        <td><span class="pull-right">${ca.estimated} </span></td>
                                                        <td><span class="pull-right">${ca.previous} </span></td>
                                                        <td><span class="pull-right">${ca.thisweek} </span></td>
                                                        <td><span class="pull-right">${ca.todate} </span></td>
                                                        <td>
                                                            <div class="progress-group" >
                                                                <span class="progress-number">
                                                                    <b>
                                                                        ${ca.percent}%
                                                                    </b>
                                                                </span>
                                                                <span>
                                                                    <div class="progress progress-sm progress-striped-active">
                                                                        <div class="progress-bar progress-bar-primary" style="width : ${ca.percent}%"></div>
                                                                    </div>
                                                            </div>
                                                            </b>
                                                            </span>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Standing Crop</h1>
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="" id="popStandCrop" role="button"><i class="fa fa-question text-orange"></i></a>  
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body no-padding">
                                        <table class="table table-bordered" >
                                            <thead>
                                                <tr>
                                                    <th>Particulars</th>
                                                    <th>Estimated Production</th>	
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="ca" items="${CropAss}">
                                                    <tr>	
                                                        <td><span class="pull-left">${ca.particulars}</span></td>
                                                        <td><span class="pull-right">${ca.standing}</span></td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div> 
                        </c:if>
                        <br>
                        <c:if test="${not empty rainfall}">
                            <div class="col-md-8" > 
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Weather Forecast (Today)</h1>
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="" id="popWeatherForecast" role="button"><i class="fa fa-question text-orange"></i></a>  
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>   
                                    <div class="box-body no-padding" id="container1">
                                        <table class="table table-bordered" >
                                            <thead>
                                                <c:forEach var="rain" items="${rainfall}">

                                                <th> mm of rain of week ending : ${rain.week_ending}</th>

                                            </c:forEach>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="r" items="${rainfall}">

                                                <td> <span class="pull-right">${r.rainfall} mm</span></td>

                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${not empty narrative}">
                            <div class="col-md-6" > 
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Narrative Report for Week Ending <c:out value="${narrative.weekending}" /> </h1>
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="" id="popNarativeRep" role="button"><i class="fa fa-question text-orange"></i></a>  
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>   
                                    <div class="box-body no-padding" id="container1">
                                        <table class="table table-bordered">
                                            <tbody>

                                                <tr>
                                                    <th style="width:20%">WEATHER</th>
                                                    <td><c:out value="${narrative.dweather}" /></td>
                                                </tr>
                                                <tr>
                                                    <th>PRICE OF SUGAR</th>
                                                    <td> <c:out value="${narrative.dprice}" /></td>
                                                </tr>
                                                <tr>
                                                    <th>MILL OPERATION</th>
                                                    <td> <c:out value="${narrative.dmill}" /></td>
                                                </tr>
                                                <tr>
                                                    <th>PRICES OF INPUTS</th>
                                                    <td> <c:out value="${narrative.dinput}" /></td>
                                                </tr>
                                                
                                                <tr>
                                                    <th>FINDINGS</th>
                                                    <td> <c:out value="${narrative.dother}" /></td>
                                                </tr>
                                                <tr>
                                                    <th>OVERALL ANALYSIS</th>
                                                    <td><c:out value="${narrative.danalysis}" /></td>
                                                </tr>
                                            <th class="text-center text-red" colspan="2">BOARD NARRATIVE</th>
                                                <tr>
                                                    <th class="text-red">FINDINGS (PROBLEMS & SUGGESTIONS)</th>
                                                    <td class="text-red"><c:out value="${hboardnarrative.dbfindings}"/></td>
                                                </tr>
                                                <tr>
                                                    <th class="text-red">OVERALL ANALYSIS</th>
                                                   <td class="text-red"><c:out value="${hboardnarrative.dbanalysis}" /></td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                            </div>
                            <div class="col-md-4 col-md-offset-9">
                                <a href="viewCropAssessment?toprint=true" class="btn btn-danger" role="button" target="_blank">Print Crop Assessment</a>

                            </div>       
                        </c:if>
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
                $('#popCropEst').popover(popCropEst);
                $('#popYieldMap').popover(popYieldMap);
                $('#popAreaHarv').popover(popAreaHarv);
                $('#popStandCrop').popover(popStandCrop);
                $('#popWeatherForecast').popover(popWeatherForecast);
                $('#popNarativeRep').popover(popNarativeRep);


            });


        </script>

        <script type="text/javascript">
            $(document).ready(function () {

                $('.treeselect').on('change', function (evt) {
                    var test = $("#select3").val();
                    var type = $("#select6").val();
                    Pace.track(function () {
                        $.ajax({
                            url: 'loadTreeMapData?tag=' + test + '&type=' + type + '',
                            type: 'POST',
                            dataType: "JSON",
                            success: function (data) {
                                var points = [],
                                        municipalP,
                                        municipalI = 0,
                                        municipalVal,
                                        barangayP,
                                        barangayI,
                                        farmerP,
                                        farmerI,
                                        municipal,
                                        barangay,
                                        farmer;

                                for (municipal in data) {
                                    if (data.hasOwnProperty(municipal)) {
                                        municipalVal = 0.0;
                                        municipalP = {
                                            id: 'id_' + municipalI,
                                            name: municipal,
                                            color: Highcharts.getOptions().colors[municipalI]
                                        };
                                        barangayI = 0;
                                        for (barangay in data[municipal]) {
                                            if (data[municipal].hasOwnProperty(barangay)) {
                                                barangayP = {
                                                    id: municipalP.id + '_' + barangayI,
                                                    name: barangay,
                                                    parent: municipalP.id

                                                };
                                                points.push(barangayP);

                                                farmerI = 0;
                                                for (farmer in data[municipal][barangay]) {
                                                    if (data[municipal][barangay].hasOwnProperty(farmer)) {
                                                        farmerP = {
                                                            id: barangayP.id + '_' + farmerI,
                                                            name: farmer,
                                                            parent: barangayP.id,
                                                            value: +data[municipal][barangay][farmer]
                                                        };
                                                        municipalVal += farmerP.value;
                                                        points.push(farmerP);
                                                        farmerI = farmerI + 1;
                                                    }
                                                }
                                                barangayI = barangayI + 1;
                                            }
                                        }
                                        municipalP.value = municipalVal;
                                        points.push(municipalP);
                                        municipalI = municipalI + 1;
                                    }
                                }
                                $('#container1').highcharts({
                                    series: [{
                                            type: 'treemap',
                                            layoutAlgorithm: 'squarified',
                                            allowDrillToNode: true,
                                            animationLimit: 1000,
                                            turboThreshold: 10000,
                                            dataLabels: {
                                                enabled: false
                                            },
                                            levelIsConstant: false,
                                            levels: [{
                                                    level: 1,
                                                    dataLabels: {useHTML: true,
                                                        enabled: true,
                                                        formatter: function () {


                                                            if (this.point.isLeaf) {
                                                                return this.key;
                                                            } else {

                                                                return '<a style="color:white" href="viewFarmerProfile?name=' + this.key + '"   target="_blank">' + this.key + '</div>';

                                                            }
                                                        }
                                                    },
                                                    borderWidth: 3
                                                }],
                                            data: points
                                        }],
                                    subtitle: {
                                        text: 'Click points to drill down'
                                    },
                                    title: {
                                        text: 'Production of last year'
                                    }
                                });


                            }});
                    });
                });

                $("#select6").select2({
                    minimumResultsForSearch: Infinity
                });

                $.ajax({
                    url: 'loadTreeMapYearList',
                    type: 'POST',
                    dataType: "JSON",
                    success: function (data) {
                        $("#select3").select2({
                            minimumResultsForSearch: Infinity,
                            data: data
                        }).trigger('change');
                        var yr = $("#select3").val();

                    }});




            });
        </script>


        <script>

            $(document).ready(function () {
                var table = $('#munitable').DataTable({
                    'ajax': {
                        'url': 'viewDistCropEstimate?year=${todayYear}'
                    },
                    "paging": false,
                    "ordering": false,
                    "info": false,
                    "searching": true
                });
                $('#munitable').DataTable().search('${Week_ending}').draw();
                $('#munitable_filter').addClass('hidden');
            });

            $("#pCA").on("click", function () {
                $.ajax({
                    url: 'printCA?cropyear=${todayYear}&weekending=${Week_ending}',
                    type: 'POST',
                    dataType: "JSON",
                    success: function (data) {
                        alert("printed");

                    }});

            });

        </script>


        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/treemap.js"></script>
        <script src="Highcharts/highcharts-more.js"></script>
        <script src="Highcharts/modules/solid-gauge.js"></script>


    </body>

</html>
