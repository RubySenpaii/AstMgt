<%-- 
    Document   : oneWeekView
    Created on : 10 28, 16, 1:20:47 AM
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
        <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css"> 
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
        
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        ${datepick} Production
                        <small>By Region</small>
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
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
                    
                    <div class="col-md-10" > 
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h1 class="box-title">Production by Week: ${datepick}  (Example: Luzon)</h1>
                                <div class="box-tools pull-right">
                                    <a tabindex="0" class="text-overflow" id="popPBW" role="button"><i class="fa fa-question text-orange"></i></a>
                                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
-                                    <!-- In box-tools add this button if you intend to use the contacts pane -->
                                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>
                            <div class="box-body no-padding">
                                <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                            </div>

                        </div>
                    </div>        
                    <br>
                    <div class="col-md-10"> 
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h1 class="box-title">Production by week : ${datepick} (full view)</h1>
                                <div class="box-tools pull-right">
                                    <a tabindex="0" class="text-overflow" id="popPBWFV" role="button"><i class="fa fa-question text-orange"></i></a>
                                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                    <!-- In box-tools add this button if you intend to use the contacts pane -->
                                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>

                            <div class="box-body">
                                <table id="example2" class="table table-bordered text-right">
                                        <thead>
                                            <tr>
                                                <th>Region</th>
                                                <th>Area</th>
                                                <th>Tons Cane</th>
                                                <th>LKG</th>
                                                <th>Description</th>
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
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popPBW').popover(popAreaHarv);
                $('#popPBWFV').popover(popAreaHarv);
            });


        </script>
<!--        <script>
            $(function () {
                var sel = document.getElementById("particulars");
            var sv = sel.options[sel.selectedIndex].value;
            alert(sv);
            $.ajax({
                url: "viewWeeklyProducedReportByRegion?id="+sv,
                tye: "POST",
                dateType: "JSON",
                success: function(data){ 
                  console.log(data.categories);  
    $('#container').highcharts({
        chart: {
            zoomType 'column'
        },
        title: {
            text:  'Production for the Week: '+ ${datepick}
        },
        xAxis: {
            categories: data.categories,
            crosshair: true
        },
                    yAxis: [{// Primary yAxis
                            labels: {
                                format: '{value} MM',
                                style: {
                                    color: Highcharts.getOptions().colors[1]
                                }
                            },
                            title: {
                                text: 'Rainfall',
                                style: {
                                    color: Highcharts.getOptions().colors[1]
                                }
                            }
                        }, {// Secondary yAxis
                            title: {
                                text: 'Growth',
                                style: {
                                    color: Highcharts.getOptions().colors[0]
                                }
                            },
                            labels: {
                                format: '{value} TC',
                                style: {
                                    color: Highcharts.getOptions().colors[0]
                                }
                            },
                            opposite: true
                        }],
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0"></td>' +
                '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            data: data.prod
            }]
    });
    } });
            sel.onchange = function(){
                var sl = sel.options[sel.selectedIndex].value;
                alert(sl);
                $.ajax({
                url: "viewWeeklyProducedReportByRegion?id="+sl,
                tye: "POST",
                dateType: "JSON",
                success: function(data){ 
                  console.log(data.categories);  
    $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text:  'Production for the Week: '+ ${datepick}
        },
        xAxis: {
            categories: data.categories,
            crosshair: true
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0"></td>' +
                '<td style="padding:0"><b>{point.y:.1f} TC</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            data: data.prod
            }]
    });
    } });
            };
});
        </script>-->
        <script type="text/javascript">
            $(function () {    
            var sel = document.getElementById('particulars');
            var sv = sel.options[sel.selectedIndex].value;
            if (sv === 'TC'){
                        var ti = 'Tons Cane';
                    }
                    else if(sv === 'HA'){
                        var ti = 'Area Harvested';
                    }
                    else if(sv === 'LKG'){
                        var ti = '50 kilograms';
                    }   
                    Pace.track(function(){
            $.ajax({
                    url: "viewWeeklyProducedReportByRegion?id="+sv,
                    type: "POST",
                    dataType: "JSON",
                    success: function(data){
                        
                        console.log(data.prod);
                        console.log(data.categories);
                        
                         $('#container').highcharts({
                    chart: {
                        zoomType: 'xy'
                    },
                    title: {
                        text: 'Production for the Week: '+ ${datepick}
                    },
                    xAxis: [{
                            categories: data.categories,
                            crosshair: true
                        }],
                    yAxis: [{// Primary yAxis
                            labels: {
                                format: '{value} Avg'+ti,
                                style: {
                                    color: Highcharts.getOptions().colors[1]
                                }
                            },
                            title: {
                                text: 'Average '+ti,
                                style: {
                                    color: Highcharts.getOptions().colors[1]
                                }
                            }
                        }, {// Secondary yAxis
                            title: {
                                text: ti,
                                style: {
                                    color: Highcharts.getOptions().colors[0]
                                }
                            },
                            labels: {
                                format: '{value}'+ti,
                                style: {
                                    color: Highcharts.getOptions().colors[0]
                                }
                            },
                            opposite: true
                        }],
                    tooltip: {
                        shared: true
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'left',
                        x: 170,
                        verticalAlign: 'top',
                        y: 90,
                        floating: true,
                        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                    },
                    series: [{
                            name: ti,
                            type: 'column',
                            data: data.prod,
                            tooltip: {
                                valueSuffix: ' '+sv
                            }

                        }, {
                            name: 'Average '+ti,
                            type: 'spline',
                            data: data.avg,
                            tooltip: {
                                valueSuffix: ' '+sv
                            }
                        }]
                });
                    }
                });   
                });
            sel.onchange = function(){
                var sl = sel.options[sel.selectedIndex].value;
                Pace.track(function(){
              $.ajax({
                    url: "viewWeeklyProducedReportByRegion?id="+sl,
                    type: "POST",
                    dataType: "JSON",
                    success: function(data){
                        if (sl === 'TC'){
                        var tis = 'Tons Cane';
                    }
                    else if(sl === 'HA'){
                        var tis = 'Area Harvested';
                    }
                    else if(sl === 'LKG'){
                        var tis = '50 kilograms';
                    } 
                         $('#container').highcharts({
                    chart: {
                        zoomType: 'xy'
                    },
                    title: {
                        text: 'Production for the Week: '+ ${datepick}
                    },
                    xAxis: [{
                            categories: data.categories,
                            crosshair: true
                        }],
                    yAxis: [{// Primary yAxis
                            labels: {
                                format: '{value} Avg '+tis,
                                style: {
                                    color: Highcharts.getOptions().colors[1]
                                }
                            },
                            title: {
                                text: 'Average '+tis,
                                style: {
                                    color: Highcharts.getOptions().colors[1]
                                }
                            }
                        }, {// Secondary yAxis
                            title: {
                                text: tis,
                                style: {
                                    color: Highcharts.getOptions().colors[0]
                                }
                            },
                            labels: {
                                format: '{value}'+tis,
                                style: {
                                    color: Highcharts.getOptions().colors[0]
                                }
                            },
                            opposite: true
                        }],
                    tooltip: {
                        shared: true
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'left',
                        x: 170,
                        verticalAlign: 'top',
                        y: 90,
                        floating: true,
                        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                    },
                    series: [{
                            name: tis,
                            type: 'column',
                            data: data.prod,
                            tooltip: {
                                valueSuffix: ' '+sl
                            }

                        }, {
                            name: 'Average '+tis,
                            type: 'spline',
                            data: data.avg,
                            tooltip: {
                                valueSuffix: ' '+sl
                            }
                        }]
                });
                    }
                }); 
                });
             };       
            });
        </script>
        <script>
            $(document).ready(function () {
                var table = $('#example2').DataTable({
                    'ajax': {
                        'url': 'viewWeeklyProducedReportByRegionT'
                    },
                    'columnDefs': [{
                            'targets': 4,
                        
                            'render': function (data, type, full, meta) {
                                return '<a href="CitiesProdDetails?id=' + data + '" class="btn btn-primary">More Details</a>';
                            }
                            
                        }]
                });
            });
        </script>
    </body>
</html>
