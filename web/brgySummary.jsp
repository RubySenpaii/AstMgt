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
        <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
        <link rel="stylesheet" href="plugins/select2/select2.min.css">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">

        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content-header">

                    <h1>
                        Barangay Summary : <c:out value="${brgydet.barangay}"/> 
                    
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                         <div class="col-md-4">
                            <div class="box box-primary">
                                <div class="box-body box-profile">

                                    <h3 class="profile-username text-center">Barangay <c:out value="${brgydet.barangay}"/> </h3>
                                    
                                    <ul class="list-group list-group-unbordered">
                                        <li class="list-group-item">
                                            <b>Total Farmers</b> <a class="pull-right"><c:out value="${brgydet.tfarmers}"/> </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Total Fields</b> <a class="pull-right"><c:out value="${brgydet.tfields}"/> </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Total Area(ha)</b> <a class="pull-right"><c:out value="${brgydet.area}"/> </a>
                                        </li>
                                        
                                    </ul>

                                </div>
                                <!-- /.box-body -->
                            </div>    
                        </div>


                        <div class="col-md-8"> 
                            <div class="box box-info" >
                                <div class="box-header with-border">
                                    <h1 class="box-title">Barangay Yearly Production</h1>
                                    <div class="box-tools pull-right " >
                                      <a tabindex="0" class="text-overflow" id="popBrgyYrProdChart" role="button"><i class="fa fa-question text-orange"></i></a>
                                      <button class="btn btn-box-tool"  data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>

                                <div class="box-body">
                                       
                                    <div id="container2"></div>
                                    
                                    
                                </div>




                            </div>

                        </div>
                        <div class="col-md-12"> 
                            <div class="box box-info" >
                                <div class="box-header with-border">
                                    <h1 class="box-title">Farmer Production for Current Year</h1>
                                    <div class="box-tools pull-right " >
                                       <a tabindex="0" class="text-overflow" id="PopFarmerProdCurrYear" role="button"><i class="fa fa-question text-orange"></i></a>
                                      <button class="btn btn-box-tool"  data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>

                                <div class="box-body">
 <div class="col-md-2 pull-right">
                                <select class="form-control" style="width:70%" id="select2">
                                  <option selected="selected">Tons Cane</option>
                                  <option>Area</option>
                                  <option>Yield</option>
                                </select>
                                          </div>
<div  id="container3"></div>


                            </div>
</div>
                        </div>

                        <div class="col-md-12">
                            <!-- LINE CHART -->
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h3 class="box-title">#Farmer Details</h3>

                                    <div class="box-tools pull-right">
                                         <a tabindex="0" class="text-overflow" id="PopBrgyFarmerList" role="button"><i class="fa fa-question text-orange"></i></a>
                                      <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                        </button>
                                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
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
        <script src="plugins/select2/select2.full.min.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
           <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/drilldown.js"></script>
<script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
            $('#popBrgyYrProdChart').popover(popBrgyYrProdChart);
                $('#PopFarmerProdCurrYear').popover(PopFarmerProdCurrYear);
                $('#PopBrgyFarmerList').popover(PopBrgyFarmerList);

            });
            
            </script>
        <script>

            $(document).ready(function () {
var categ;
 var bar,line;
                $.ajax({
                    url: 'loadBrgySumYrsChart?name=${brgydet.barangay}',
                    type: 'POST',
                    dataType: "JSON",
                    success: function (data) {
                        categ=data.categ;
                       bar=data.bar;
                        line=data.line;
                    // Create the chart
                    
                    
                    console.log(bar);
                    console.log(categ);
                  $(function () {
    $('#container2').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Yearly Production(tc)'
        },
       
        xAxis: {
            categories: categ,
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Tons Cane (tc)'
            }
        },
           legend: {
              enabled: false
               },
        
        tooltip: {
       
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }, line: {
               marker: {
            enabled: false
        }
            }
                       
        },
        series: [{
            name: 'Year',
            data: bar

        },{
            name: 'District Avg',
            data: line,
            type:'line'

        }]
    });
});
                }
            });
var categ2;
 var bar2;
 var curyr;
 var avgprod;
  $('#select2').on('change', function (evt) {
                   var type = $("#select2").val();
    $.ajax({
                    url: 'loadAllFarmersChart?name=${brgydet.barangay}&type='+type+'',
                    type: 'POST',
                    dataType: "JSON",
                    success: function (data) {
                        categ2=data.categ;
                       bar2=data.bar;
                       curyr=data.curyr;
                       avgprod=data.avgprod;
                    // Create the chart
                    console.log(bar);
                    console.log(categ);
                  $(function () {
    $('#container3').highcharts({
        chart: {
            type: 'column',
            zoomType: 'x'
            
        },
        title: {
            text: 'Distributed '+type+' for '+curyr+ '' 
        },
       
        xAxis: {
            categories: categ2,
            crosshair: true
        },
        
        yAxis: {
            min: 0,
            title: {
                text: type
            }
        },
           legend: {
              enabled: false
               },
        
        tooltip: {
       
        },
        plotOptions: {
            line: {
                 marker: {
            enabled: false
        }
            },
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Brgys',
            data: bar2

        },{
            name: 'District Year Avg',
            data: avgprod,
            type:'line'

        }]
    });
});
                }
            });
            });
             $("#select2").select2({
                               minimumResultsForSearch: Infinity
                            }).trigger('change');
            
            
            
               var table = $('#fieldtable').DataTable({
                    'ajax': {
                        'url': 'viewFarmerBrgyTable?name=${brgydet.barangay}'
                    },
                    'columnDefs': [
                { className: "text-right", "targets": [3,4] },
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