<%-- 
    Document   : WeatherTrends
    Created on : 09 19, 16, 1:34:00 PM
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
        <meta content="width=device-width, i/nitial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
      
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        Page Header
                        <small>Optional description</small>
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group">
                                            <label>Year</label>
                                            <select class="form-control" id="year1">
                                                <option>2014</option>
                                                <option>2015</option>
                                                <option>2016</option>
                                                <option>2017</option>
                                            </select>
                                        </div>
                        </div>
                        <div class="col-md-11" > 
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Weather Trends</h1>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="" id="popWeatherT" role="button"><i class="fa fa-question text-orange"></i></a>  
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="box-body no-padding" id="container2"></div>
                            </div>                                        
                                    </button> 
                        </div>
                        <br>
                        <div class="col-md-3">
                            <div class="form-group">
                                            <label>Year</label>
                                            <select class="form-control" id="year2">
                                                <option>2014</option>
                                                <option>2015</option>
                                                <option>2017</option>
                                            </select>
                                        </div>
                        </div>
                        <div class="col-md-11" > 
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Weather Trends</h1>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="" id="popWeatherT1" role="button"><i class="fa fa-question text-orange"></i></a>  
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="box-body no-padding" id="container1"></div>
                            </div>
                            <button class="pull-right"><a href="viewWeatherRecommendations"><b>Set Weather Recommendation</b></a>
                                        
                                    </button> 
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
        
        <script type="text/javascript">
            $(function () {    
            var sel = document.getElementById('year1');
            var sv = sel.options[sel.selectedIndex].value;
            
            $.ajax({
                    url: "viewWeatherTrends?id="+${todayYear},
                    type: "POST",
                    dataType: "JSON",
                    success: function(data){
                        
                        console.log(data[1].prod);
                        
                         $('#container2').highcharts({
                    chart: {
                        zoomType: 'xy'
                    },
                    title: {
                        text: 'Annual Rainfall and Production Trends Year : ' + '( ' + ${todayYear} + ' )'
                    },
                    xAxis: [{
                            categories: data[0].months,
                            crosshair: true
                        }],
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
                        shared: true
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'left',
                        x: 120,
                        verticalAlign: 'top',
                        y: 100,
                        floating: true,
                        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                    },
                    series: [{
                            name: 'Production',
                            type: 'column',
                            yAxis: 1,
                            data: data[1].prod,
                            tooltip: {
                                valueSuffix: 'TC'
                            }

                        }, {
                            name: 'Rainfall',
                            type: 'spline',
                            data: data[2].rain,
                            tooltip: {
                                valueSuffix: 'MM'
                            }
                        }]
                });
                    }
                });   
            sel.onchange = function(){
                var sl = sel.options[sel.selectedIndex].value;
                alert(sl);
               
                $.ajax({
                    url: "viewWeatherTrends?id="+sl,
                    type: "POST",
                    dataType: "JSON",
                    success: function(data){
                        
                        console.log(data[1].prod);
                        
                         $('#container2').highcharts({
                    chart: {
                        zoomType: 'xy'
                    },
                    title: {
                        text: 'Annual Rainfall and Production Trends Year : ' + '( ' + sv + ' )'
                    },
                    xAxis: [{
                            categories: data[0].months,
                            crosshair: true
                        }],
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
                        shared: true
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'left',
                        x: 120,
                        verticalAlign: 'top',
                        y: 100,
                        floating: true,
                        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                    },
                    series: [{
                            name: 'Production',
                            type: 'column',
                            yAxis: 1,
                            data: data[1].prod,
                            tooltip: {
                                valueSuffix: 'TC'
                            }

                        }, {
                            name: 'Rainfall',
                            type: 'spline',
                            data: data[2].rain,
                            tooltip: {
                                valueSuffix: 'MM'
                            }
                        }]
                });
                    }
                });    
             };       
            });
        </script>
         <script type="text/javascript">
            $(function () {    
            var sel = document.getElementById('year2');
            var sv = sel.options[sel.selectedIndex].value;
            sel.onchange = function(){
                var sl = sel.options[sel.selectedIndex].value;
                alert(sl);
               
                $.ajax({
                    url: "viewWeatherTrends?id="+sl,
                    type: "POST",
                    dataType: "JSON",
                    success: function(data){
                        
                        console.log(data[1].prod);
                        
                         $('#container1').highcharts({
                    chart: {
                        zoomType: 'xy'
                    },
                    title: {
                       text: 'Annual Rainfall and Production Trends Year : ' + '( ' + sv + ' )'
                    },
                    xAxis: [{
                            categories: data[0].months,
                            crosshair: true
                        }],
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
                        shared: true
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'left',
                        x: 120,
                        verticalAlign: 'top',
                        y: 100,
                        floating: true,
                        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                    },
                    series: [{
                            name: 'Production',
                            type: 'column',
                            yAxis: 1,
                            data: data[1].prod,
                            tooltip: {
                                valueSuffix: 'TC'
                            }

                        }, {
                            name: 'Rainfall',
                            type: 'spline',
                            data: data[2].rain,
                            tooltip: {
                                valueSuffix: 'MM'
                            }
                        }]
                });
                    }
                });    
             };       
            });
        </script>
        

        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="dist/js/app.min.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/exporting.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popWeatherT').popover(popWeatherT);
                $('#popWeatherT1').popover(popWeatherTS);
            });


        </script>
    </body>
</html>
