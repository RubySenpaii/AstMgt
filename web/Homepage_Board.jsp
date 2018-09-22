<%-- 
    Document   : Homepage_Board
    Created on : 10 28, 16, 12:02:14 AM
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
     <link href="plugins/pace2/themes/green/pace-theme-loading-bar.css" rel="stylesheet" />
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        Dashboard
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                        <c:if test="${milling == true }">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label>Type</label>
                                    <select class="form-control" id="particulars">
                                        <option>LKG</option>
                                        <option>TC</option>
                                        <option>HA</option> 
                                    </select>
                                </div>

                            </div>

                            <div class="col-md-12" > 
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Line Graph</h1>
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popLineG" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <!-- In box-tools add this button if you intend to use the contacts pane -->
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body no-padding">
                                        <div id="container" style="height: 500px"></div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <br>
                        <div class="col-md-12" > 
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Ongoing Programs List : ${todayDate}</h1>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popOPL" role="button"><i class="fa fa-question text-orange"></i></a>
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <!-- In box-tools add this button if you intend to use the contacts pane -->
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>

                                <div class="box-body">
                                    <table id="example" class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th style="width: 20%">Project Name</th>
                                                <th style="">Date Started</th>
                                                <th style="">Date Ended</th>
                                                <th style="width:10%">Description</th>
                                                <th>District</th>
                                                <th style="width: 10%">Details</th>
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
        <script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="dist/js/app.min.js"></script>
        <script src="plugins/pace2/pace.min.js"></script>
         <script src="plugins/poptest/popoverText.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>
       
        <script>
            $(document).ready(function () {
                $('#popLineG').popover(popLineGraph);
                $('#popOPL').popover(popProdByWeek);
            });


        </script>
        <script>

            $(document).ready(function () {
                var table = $('#example').DataTable({
                    'ajax': {
                        'url': 'viewOngoingProjectsBoard'
                    },
                    'columnDefs': [{
                            'targets': 5,
                            'render': function (data, type, full, meta) {
                                return '<a href="viewProgramDetails?name=' + data + '" class="btn btn-primary">More Details</a>';
                            }

                        }]
                });
            });


        </script>

        <script>
            $(function () {
                var sel = document.getElementById("particulars");
                var sv = sel.options[sel.selectedIndex].value;
                $.ajax({
                    url: "viewWeeklyProducedReport?id=" + sv,
                    tye: "POST",
                    dateType: "JSON",
                    success: function (data) {
                        if (sv === 'TC') {
                            var ti = 'Tons Cane';
                        } else if (sv === 'HA') {
                            var ti = 'Area Harvested';
                        } else if (sv === 'LKG') {
                            var ti = '50 kilograms';
                        }
                        $(function () {
                            Highcharts.chart('container', {
                                title: {
                                    text: ' National Production by Week',
                                    x: -20 //center
                                },
                                xAxis: {
                                    categories: data.dates
                                },
                                plotOptions: {
                                    series: {
                                        cursor: 'pointer',
                                        point: {
                                            events: {
                                                click: function () {
                                                    location.href = 'viewWeeklyProducedReportByWeek?id=' + this.x + '&type=' + sv;
                                                }
                                            }
                                        }
                                    }

                                }
                                ,
                                yAxis: {
                                    title: {
                                        text: ti
                                    },
                                    plotLines: [{
                                            value: 0,
                                            width: 1,
                                            color: '#808080'
                                        }]
                                },
                                tooltip: {
                                    valueSuffix: ti
                                },
                                legend: {
                                    layout: 'vertical',
                                    align: 'right',
                                    verticalAlign: 'middle',
                                    borderWidth: 0
                                },
                                series: [{
                                        name: 'Total' + ti,
                                        data: data.prod
                                    }, {
                                        name: 'Average' + ti,
                                        data: data.average
                                    }]
                            });
                        });
                    }
                });


                sel.onchange = function () {
                    var sl = sel.options[sel.selectedIndex].value;
                    $.ajax({
                        url: "viewWeeklyProducedReport?id=" + sl,
                        tye: "POST",
                        dateType: "JSON",
                        success: function (data) {
                            if (sv === 'TC') {
                                var ti = 'Tons Cane';
                            } else if (sv === 'HA') {
                                var ti = 'Area Harvested';
                            } else if (sv === 'LKG') {
                                var ti = '50 kilograms';
                            }
                            $(function () {
                                Highcharts.chart('container', {
                                    title: {
                                        text: 'National Production by Week',
                                        x: -20 //center
                                    },
                                    xAxis: {
                                        categories: data.dates
                                    },
                                    plotOptions: {
                                        series: {
                                            cursor: 'pointer',
                                            point: {
                                                events: {
                                                    click: function () {
                                                        location.href = 'viewWeeklyProducedReportByWeek?id=' + this.x + '&type=' + sv;
                                                    }
                                                }
                                            }
                                        }

                                    }
                                    ,
                                    yAxis: {
                                        title: {
                                            text: ti
                                        },
                                        plotLines: [{
                                                value: 0,
                                                width: 1,
                                                color: '#808080'
                                            }]
                                    },
                                    tooltip: {
                                        valueSuffix: ti
                                    },
                                    legend: {
                                        layout: 'vertical',
                                        align: 'right',
                                        verticalAlign: 'middle',
                                        borderWidth: 0
                                    },
                                    series: [{
                                            name: 'Total',
                                            data: data.prod
                                        }, {
                                            name: 'Average',
                                            data: data.average
                                        }]
                                });
                            });
                        }
                    });
                };

//// end of the ajax 
//                }
//            });
//            };    
            });
        </script>
        

    </body>
</html>
