<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
brgy profile

details budget etc.
    update*


improvement since implementation


current vs then diagram

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
                        Plan & Program Details
                    </h1>
                </section>
                <section class="content">
                    <div class="row">

                        <div class="col-md-3">
                            <div class="box box-primary">
                                <div class="box-body box-profile">

                                    <h3 class="profile-username text-center"><b><c:out value="${progdet.prog_name}"></c:out></b></h3>

                                        <ul class="list-group list-group-unbordered">
                                            <li class="list-group-item">
                                                <b>Status of Program</b> <a class="pull-right"><c:out value="${progdet.status}"></c:out></a>
                                            </li>
                                            <li class="list-group-item">
                                                <b>Type of Program</b> <a class="pull-right"><c:out value="${progdet.type}"></c:out></a>
                                            </li>
                                            <li class="list-group-item">
                                                <b>Expected Start Date:</b> <p class="pull-right"><c:out value="${progdet.date_initial}"></c:out></p>
                                            </li>
                                            <li class="list-group-item">
                                                <b>Expected End Date:</b> <p class="pull-right"><c:out value="${progdet.date_end}"></c:out></p>
                                            </li>
                                            
                                            <li class="list-group-item">
                                                <b>Description</b> 
                                                <p class="text-center text-center">
                                                <c:out value="${progdet.description}"></c:out></p>
                                            </li>
                                            <li class="list-group-item">
                                                <b>Total Farms Affected</b> <a  class="pull-right">
                                                <c:out value="${progdet.tFarms}"></c:out>
                                                </a>
                                            </li>

                                        </ul>

                                    </div>
                                    <!-- /.box-body -->
                                </div>    
                            </div>

                            <div class="col-md-6">
                                <!-- LINE CHART -->
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Progress</h3>

                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popProgBar" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                            </button>
                                            <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>
                                    <div class="box-body">

                                        <table class="table table-hover">
                                            <tbody><tr>

                                                </tr>
                                                <tr>

                                                    <td> 
                                                        <div class="progress-group">

                                                            <div class="">
                                                                <div class="progress-bar progress-bar-striped progress-bar-aqua active" style="width: ${progdet.progress}%">  <c:out value="${progdet.progress}"></c:out></div>
                                                            </div>
                                                        </div> <!-- closer of progress bars -->  
                                                    </td>

                                                </tr>




                                            </tbody>
                                        </table>


                                    </div>
                                    <!-- /.box-body -->
                                </div>
                            </div>
                            <div class="col-md-6" > 
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Problems List</h1>
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popProgProbList" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body">
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>Problem</th>
                                                    <th>Description</th>
                                                    <th>more details</th>
                                                    <th>Farms</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${prob}" var="plist">
                                                <tr>
                                                    <td><c:out value="${plist.prob_name}"></c:out></td>
                                                    <td><c:out value="${plist.prob_details}"></c:out></td>
                                                    <td><c:out value="${plist.count}"></c:out></td>
                                                    <td><a target="_blank" href="viewProbDetails?id=${plist.prob_id}" class="btn btn-primary">More details</a></td>
                                                    
                                                    </tr>
                                            </c:forEach>


                                        </tbody>

                                    </table>
                                </div>

                            </div>
                        </div>
                        <div class="col-md-12" > 
                            <div>   
                                <h3>
                                    DISTRICT/BARANGAY :

                                </h3></div>
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Observational Improvement</h1>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popObsImp" role="button"><i class="fa fa-question text-orange"></i></a>
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>   
                                <div class="box-body" id="container1"> </div>
                            </div>

                        </div>





                        <div class="col-md-12" > 
                            <form id="kpitable" action="updateKPIProgram" >
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title"><input name="prog_name" class="form-control hidden" value="${progdet.prog_name}"/><c:out value="${progdet.prog_name}"></c:out></h1>
                                            <div class="box-tools pull-right">
                                                <a tabindex="0" class="text-overflow" id="popUpdKPIProg" role="button"><i class="fa fa-question text-orange"></i></a>
                                                <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                                <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                            </div>
                                        </div>

                                        <div class="box-body">
                                        <c:if test="${not empty kpis}">
                                            <table class="table table-hover">
                                                <thead><tr>
                                                        <th >Performance Indicator</th>

                                                <input value="${kpis[0].kpi_year}" name="kpi_year" class="form-control hidden" />
                                                <input value="${kpis[0].tYears}" name="tYears" class="form-control hidden" />
                                                <c:forEach  var="yrlist" begin="${kpis[0].kpi_year}" end="${kpis[0].kpi_year-1+kpis[0].tYears}">
                                                    <th><c:out value="${yrlist}"/></th>
                                                    <th>Actual</th>
                                                    <th class="hidden editz">Add</th>
                                                    </c:forEach>


                                                </tr>
                                                </thead>

                                                <tbody>
                                                    <c:forEach items="${kpis}" var="kpilist" varStatus="loopCount">
                                                        <tr>
                                                            <td><input class='form-control hidden' name = "kNa${loopCount.count}[]" value='${kpilist.kpi}'/><c:out value="${kpilist.kpi}"/></td>
                                                                <c:forEach items="${kpilist.values}" var="yearloop" varStatus="aCountr">

                                                                <td><c:out value="${yearloop}" /></td>
                                                                <td><c:out value="${kpilist.aValues[aCountr.index]}" /></td>
                                                                <td class="hidden editz"><input class='form-control' type="number" name = "y${loopCount.count}[]" value="0" /></td>
                                                                </c:forEach>
                                                        </tr>
                                                    </c:forEach>

                                                </tbody>
                                            </table>
                                            <button class="btn btn-info pull-right hidden" id="uButton" style="width: 10%" value="submit" form="kpitable">Update</button>
                                            <button class="btn btn-danger pull-right" id="eButton" style="width: 10%" type="button">Edit</button>
                                        </c:if>
                                    </div>

                                </div>
                            </form>
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
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/drilldown.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popProgBar').popover(popProgBar);
                $('#popProgProbList').popover(popProgProbList);
                $('#popObsImp').popover(popObsImp);
                $('#popUpdKPIProg').popover(popUpdKPIProg);

            });


        </script>
        <script>
            $(function () {

                var categ;
                var prce;
                var poce;
                $.ajax({
                    url: 'loadProgramsProductionChart?name=${progdet.prog_name}',
                    type: 'POST',
                    dataType: "JSON",
                    success: function (data) {
                        categ = data.categ;
                        prce = data.prce;
                        poce = data.poce;


                        $('#container1').highcharts({
                            chart: {
                                type: 'column',
                                spacingBottom: 30
                            },
                            title: {
                                text: 'Program-Production Improvement '
                            },
                            legend: {
                                layout: 'vertical',
                                align: 'left',
                                verticalAlign: 'top',
                                x: 100,
                                y: 0,
                                floating: true,
                                borderWidth: 1,
                                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                            },
                            xAxis: {
                                categories: categ
                            },
                            yAxis: {
                                title: {
                                    text: 'Y-Axis'
                                },
                                labels: {
                                    formatter: function () {
                                        return this.value;
                                    }
                                }
                            },
                            tooltip: {
                                formatter: function () {
                                    return '<b>' + this.series.name + '</b><br/>' +
                                            this.x + ': ' + this.y;
                                }
                            },
                            plotOptions: {
                                area: {
                                    fillOpacity: 0.5
                                }
                            },
                            credits: {
                                enabled: false
                            },
                            series: [{
                                    name: 'Pre',
                                    data: prce
                                }, {
                                    name: 'On-Going',
                                    data: poce
                                }]
                        });
                    }
                });
            });

        </script>
        <script>
            $(function () {
                $("#eButton").on("click", function () {
                    $('.editz').removeClass('hidden');
                    $('#eButton').addClass('hidden');
                    $('#uButton').removeClass('hidden');

                });
                $("#uButton").on("click", function () {
                    $('#eButton').removeClass('hidden');
                    $('.editz').addClass('hidden');

                });

            });

        </script>
    </body>

</html>