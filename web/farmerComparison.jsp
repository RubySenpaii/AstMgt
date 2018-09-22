<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
comparison page add current vs historical details(past 2yrs)
^can tell improvement or not


on barangay selection 
        farms list and their productivity
                select to go to profile
                        select comparison to run comparison




--%>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>SRA | Home</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

        <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">

        <link rel="stylesheet" href="plugins/select2/select2.min.css">
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">




    </head>
    <body class="hold-transition skin-blue sidebar-mini">

        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content-header">

                    <h1>
                        Farm Profile
                    </h1>
                </section>
                <section class="content">
                    <div class="row">

                        <div class="col-md-4">
                            <div class="box box-primary">
                                <div class="box-body box-profile">

                                    <h3 class="profile-username text-center">Farm Details for ${farm.year}</h3>
                                    <ul class="list-group list-group-unbordered">
                                        <li class="list-group-item">
                                            <b>Field ID</b> <a class="pull-right">
                                                <c:out value="${farm.id}"/>
                                            </a>
                                        </li>

                                        <li class="list-group-item">
                                            <b>Farmer</b> <a class="pull-right">
                                                <c:out value="${farm.farmer}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Barangay</b> <a class="pull-right">
                                                <c:out value="${farm.barangay}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Municipality</b> <a class="pull-right">
                                                <c:out value="${farm.municipality}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Total Area(ha)</b> <a class="pull-right">
                                                <c:out value="${farm.area}"/></a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Production(tc)</b> <a class="pull-right">
                                                <c:out value="${farm.production}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Harvested Area(ha)</b> <a class="pull-right">
                                                <c:out value="${farm.totalHa}"/>
                                            </a>
                                        </li>

                                        <li class="list-group-item">
                                            <b>Current Yield(tc/ha)</b> <a class="pull-right">
                                                <c:out value="${farm.yield}"/>
                                            </a>
                                        </li>

                                    </ul>
                                    <a href="viewFarmerProfile?name=${farm.farmer}" class="btn btn-primary pull-right">
                                        View Farmer Profile
                                    </a>                              
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="box box-primary">
                                <div class="box-header">
                                    <h1 class="box-title">Crop Validation for ${farm.cropVal.year}</h1>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popCropVal" role="button"><i class="fa fa-question text-orange"></i></a>
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                    </div>
                                </div>
                                <div id="cropVal-content-div" class="box-body box-profile">


                                    <ul class="list-group list-group-unbordered">


                                        <li class="list-group-item">
                                            <b>Crop Variety</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.variety}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Crop Class</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.crop_class}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Texture</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.texture}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Farming System</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.farming_system}"/> </a>
                                        </li>

                                        <li class="list-group-item">
                                            <b>Topography</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.topography}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Furrow Distance(m)</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.furrow_distance}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Planting Density</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.planting_density}"/>
                                            </a>
                                        </li>

                                        <li class="list-group-item">
                                            <b>Number Millable</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.num_millable}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Average Millable Stool</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.avg_millable_stool}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Brix(bx)</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.brix}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Stalk Length(cm)</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.stalk_length}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Diameter(mm)</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.diameter}"/>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Weight(kg)</b> <a class="pull-right">
                                                <c:out value="${farm.cropVal.weight}"/>
                                            </a>
                                        </li>
                                    </ul>


                                </div>

                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="box box-success">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Farm Site Pic</h1>

                                </div>
                                <div class="box-body">


                                    <img src="dist/img/user2-160x160.jpg" alt="crop" width="370" height="400" >
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">

                            <div class="col-md-4">
                                <div class="box box-primary">
                                    <div class="box-header">
                                        <h1 class="box-title">Soil Analysis</h1>
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popSoilAna" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>
                                    <div class="box-body box-profile">
                                        <ul class="list-group list-group-unbordered">

                                            <li class="list-group-item">
                                                <b>PH Level(ph)</b> <a class="pull-right">
                                                    <c:out value="${farm.soilanalysis.ph_lvl}"/></a>
                                            </li>

                                            <li class="list-group-item">
                                                <b>Organic Matter(mg)</b> <a class="pull-right">
                                                    <c:out value="${farm.soilanalysis.organic_matter}"/>
                                                </a>
                                            </li>
                                            <li class="list-group-item">
                                                <b>Phosphorus(mg)</b> <a class="pull-right">
                                                    <c:out value="${farm.soilanalysis.phosphorus}"/>
                                                </a>
                                            </li>

                                            <li class="list-group-item">
                                                <b>Potassium(mg)</b> <a class="pull-right">
                                                    <c:out value="${farm.soilanalysis.potassium}"/>
                                                </a>
                                            </li>
                                        </ul>

                                    </div>
                                    <!-- /.box-body -->
                                </div>

                            </div>

                            <div class="col-md-4">
                                <div class="box box-primary ">
                                    <div class="box-header with-border">
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popFertInfo" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        </div>
                                    </div>
                                    <div class="box-body">
                                        <h3 class="profile-username text-center">Fertilizer Info for ${farm.fertilizer.year}:</h3>
                                        <table id="recTable" class="table  display table-hover" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Fertilizer</th>
                                                    <th>First Dose(bags)</th>
                                                    <th>Second Dose(bags)</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${not empty farm.fertlist}">
                                                    <c:forEach items="${farm.fertlist}" var="fertz">
                                                        <tr>
                                                            <td><c:out value="${fertz.fertilizer}"/></td>
                                                            <td><c:out value="${fertz.first_dose}"/></td>
                                                            <td><c:out value="${fertz.second_dose}"/></td>  
                                                        </tr>
                                                    </c:forEach>


                                                </c:if>
                                            </tbody>

                                        </table>                    
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="box box-primary ">
                                    <div class="box-header with-border">

                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popTillInfo" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        </div>
                                    </div>
                                    <div class="box-body">
                                        <h3 class="profile-username text-center">Tiller Info for ${farm.tillers.year}:</h3>
                                        <table id="recTable" class="table  display table-hover" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>rep</th>
                                                    <th>count</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${not empty farm.tillist}">
                                                    <c:forEach items="${farm.tillist}" var="til">
                                                        <tr>
                                                            <td><c:out value="${til.rep}"/></td>
                                                            <td><c:out value="${til.count}"/></td>
                                                        </tr>
                                                    </c:forEach>


                                                </c:if>
                                            </tbody>

                                        </table>                    
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="col-md-6" > 
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Problems List</h1>
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popFarmProbList" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body">
                                        <table id="probTable" class="table  dispTable table-hover" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Problem</th>
                                                    <th>Status</th>
                                                    <th>Type</th>
                                                    <th>Description</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${not empty farm.problems}">
                                                    <c:forEach items="${farm.problems}" var="prob">
                                                        <tr>
                                                            <td><c:out value="${prob.prob_name}"/></td>
                                                            <td><c:out value="${prob.status}"/></td>
                                                            <td><c:out value="${prob.type}"/></td>
                                                            <td><c:out value="${prob.prob_details}"/></td>
                                                            <td><a class="btn btn-primary" href="viewProbDetails?id=${prob.prob_id}">details</a></td>
                                                        </tr>
                                                    </c:forEach>


                                                </c:if>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-6" > 
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Recommendations List</h1>
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popFarmRecList" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body">
                                        <table class="table  dispTable table-hover" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Recommendation</th>
                                                    <th>Status</th>
                                                    <th>Type</th>
                                                    <th>Description</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${not empty farm.recommendation}">
                                                    <c:forEach items="${farm.recommendation}" var="rec">
                                                        <tr>
                                                            <td><c:out value="${rec.recommendation_name}"/></td>
                                                            <td><c:out value="${rec.status}"/></td>
                                                            <td><c:out value="${rec.type}"/></td>
                                                            <td><c:out value="${rec.description}"/></td>
                                                            <td><a class="btn btn-primary" href="viewRecDetails?id=${rec.id}">details</a></td>

                                                        </tr>
                                                    </c:forEach>


                                                </c:if>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-6" > 
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Programs List</h1>
                                        <div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popFarmProgList" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body">
                                        <table class="table  dispTable table-hover" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Program</th>
                                                    <th>Status</th>
                                                    <th>type</th>
                                                    <th>Description</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${not empty farm.programs}">
                                                    <c:forEach items="${farm.programs}" var="prog">
                                                        <tr>
                                                            <td><c:out value="${prog.prog_name}"/></td>
                                                            <td><c:out value="${prog.status}"/></td>
                                                            <td><c:out value="${prog.type}"/></td>
                                                            <td><c:out value="${prog.description}"/></td>
                                                            <td><a class="btn btn-primary" href="viewProgramDetails?name=${prog.prog_name}">details</a></td>

                                                        </tr>
                                                    </c:forEach>


                                                </c:if>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="col-md-offset-5 col-sm-3 ">
                            <button id="actionButton" class="btn btn-success btn-block" type="button" style="height: 6%;">View Action Tools</button>
                            <button id="obsrvButton" class="btn btn-info btn-block" type="button" style="">Create Observation</button>
                        </div>       
                        <div class="col-md-offset-3 col-sm-6 hidden" id="actionBlock"
                             >
                            <br>
                            <h3> Action Tools<a tabindex="0" class="text-overflow" id="popFarmActionTools" role="button"><i class="fa fa-info-circle text-blue"></i></a></h3>
                            <form id="frm-sendRec" action="viewSendRec">
                                <input name="farmid" type="hidden" value="${id}"/>


                                <button class="btn btn-app btn-linkedin atools" name="atools" id="crec" value="crec">
                                    <i class="fa fa-edit" ></i> Create Recommendations
                                </button>
                                <button class="btn btn-app btn-linkedin atools" name="atools" id="sorec" value="sorec">
                                    <i class="fa fa-edit" ></i> Send Recommendations
                                </button>
                                <button class="btn btn-app btn-linkedin atools" name="atools" id="dprob" value="dprob">
                                    <i class="fa fa-edit" ></i> Determine Problems
                                </button>

                            </form>

                        </div>
                        <div id="observBlock" class="col-md-12 hidden">
                            <br>


                            <h1> Farm Observation <small><a tabindex="0" class="text-overflow" id="popFarmObs" role="button"><i class="fa fa-info-circle text-orange"></i></a></small></h1>

                            <div class="col-md-7">
                                <div class="form-group">
                                    <select id="select2" class="select2" multiple="multiple" data-placeholder="Select a Tag" style="width: 100%;">

                                    </select>
                                </div>
                            </div>

                            <div class="col-sm-1">

                                <button id="sButton" class="btn btn-primary" type="submit" style="width: 100%">Search</button>
                            </div>

                        </div>
                        <form id="frm-FarmDiff" action="viewFarmDifferences">


                            <div class="col-sm-12">
                                <br>
                                <div class="box box-success hidden" id="sbox">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Results:</h1>

                                    </div>
                                    <div class="box-body">

                                        <table id="example" class="table  display table-hover" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <!--<th><input name="select_all" value="1" id="example-select-all" type="checkbox" /></th>-->
                                                    <th></th>
                                                    <th>Farm ID</th>
                                                    <th>Farmer</th>
                                                    <th>Barangay</th>
                                                    <th>Municipality</th>
                                                    <th>Actual</th>
                                                    <th>Yield % Diff.</th>
                                                    <th>More Details</th>

                                                </tr>
                                            </thead>

                                        </table>

                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2 pull-right">

                                <input name="id" type="hidden" value="${id}"/>
                                <input id="taglist" name="taglist" type="hidden" />
                                <div class="box-body">
                                    <button class="btn btn-app btn-linkedin hidden" id="dbox" value="submit" style="width: 100%">
                                        <i class="fa fa-edit" ></i> Create Comparison
                                    </button>
                                </div>
                            </div>
                        </form>

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
        <script src="plugins/select2/select2.min.js"></script>
        <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>

        <script src="dist/js/app.min.js"></script>
        <script type="text/javascript">
            $(function () {
                $('#cropVal-content-div').slimScroll({
                    height: '420px',
                    alwaysVisible: true
                });
            });
        </script>
        <script type="text/javascript">
            $(function () {
                var test;
                $.ajax({
                    url: 'createTagList?id=${id}',
                    type: 'POST',
                    dataType: "JSON",
                    success: function (data) {
//                        alert(data);

                        $("#select2").select2({
                            data: data
                        });

                    }});

                $("#obsrvButton").on("click", function () {
                    $('#observBlock').removeClass('hidden');
                    $('#obsrvButton').addClass('hidden');
                    $('#actionBlock').addClass('hidden');
                    $('#actionButton').removeClass('hidden');
                    //   $('#actionButton').addClass('hidden');

                });
                $("#actionButton").on("click", function () {
                    $('#actionBlock').removeClass('hidden');
                    $('#observBlock').addClass('hidden');
                    $('#actionButton').addClass('hidden');
                    $('#obsrvButton').removeClass('hidden');

                });

                //  var data = ['enhancement', 'bug', 'duplicate','invalid', 'wontfix'];                          
                // var data = [{text: 'enhancement'}, {text: 'bug'}, {text: 'duplicate'}, {text: 'invalid'}, {text: 'wontfix'}];      
                // var data = [{id: 0, text: 'enhancement'}, {id: 1, text: 'bug'}, {id: 2, text: 'duplicate'}, {id: 3, text: 'invalid'}, {id: 4, text: 'wontfix'}];

                $("#sButton").on("click", function () {
                    test = $("#select2").val();
                    document.getElementById("taglist").value = test;
                    //  var rows_selected = [];
                    var limit = 4;
                    $('#sbox').removeClass('hidden');
                    $('#dbox').removeClass('hidden');

                    var table = $('#example').DataTable({
                        destroy: true,
                        'ajax': {
                            'url': 'searchSimilarFarms?tag=' + test + '&id=${id}',
                            type: 'POST'
                        },
                        'columnDefs': [{
                                'targets': 0,
                                'searchable': false,
                                'orderable': false,
                                'className': 'dt-body-center',
                                'render': function (data, type, full, meta) {
                                    return '<input class="multi-checkbox" type="checkbox" name="fids[]" id="buttonClick" value="'
                                            + $('<div/>').text(data).html() + '">';
                                }
                            },
                            {
                                'targets': 6,
                                'searchable': true,
                                'orderable': true,
                                'className': 'dt-body-center',
                                'render': function (data, type, full, meta) {
                                    if (data > 0) {
                                        return '<span class="label label-success">' + data + '%</span>';
                                    } else if (data < 0) {
                                        return '<span class="label label-warning">' + data + '%</span>';
                                    } else {
                                        return '<span class="label label-info">' + data + '%</span>';
                                    }
                                }
                            },
                            {
                                'targets': 7,
                                'searchable': false,
                                'orderable': false,
                                'className': 'dt-body-center',
                                'render': function (data, type, full, meta) {
                                    return '<a href="viewFieldDetails?id=' + data + '" class="btn btn-primary text-center">' + 'more details' + '</a>';

                                }
                            }
                        ],
                        'select': {
                            'style': 'multi'
                        },
                        'order': [[1, 'asc']]
                        ,
                        'rowCallback': function (row, data, dataIndex) {
                            // Get row ID
                            var rowId = data[0];
                            // alert(rowId);
                            // If row ID is in the list of selected row IDs
                            //  alert("notclicked");
//                                         if($.inArray(rowId, rows_selected) !== -1){
//                                              
//                                            $(row).find('input[type="checkbox"]').prop('checked', true);
//                                            $(row).addClass('selected');
//                                          
//                                         }
                            table.$('input[type="checkbox"]', row).on('change', function (evt) {
                                console.log("outentered");
                                var tcounter = 0;

                                table.$('input[type="checkbox"]').each(function () {
                                    if (this.checked) {

                                        tcounter += 1;

                                    }

                                });

                                if (tcounter > limit) {
                                    console.log(tcounter);
                                    console.log("debackdoortho");
                                    this.checked = false;
                                }
                            });
                        }

                    });

                    $('#frm-FarmDiff').on('submit', function (e) {
                        var form = this;
                        // Iterate over all checkboxes in the table
                        table.$('input[type="checkbox"]').each(function () {
                            // If checkbox doesn't exist in DOM
                            if (!$.contains(document, this)) {
                                // If checkbox is checked
                                if (this.checked) {
                                    // Create a hidden element 
                                    $(form).append(
                                            $('<input>')
                                            .attr('type', 'hidden')
                                            .attr('name', this.name)
                                            .val(this.value)
                                            );
                                }
                            }
                        });
                    });


//                    table.$('input[type="checkbox"]').on('click',function (row) {
//                  //      $(row).find('input[type="checkbox"]').prop('checked', true);
//                      console.log(limit);
//                        alert("it entered on change"+limit);
//        
//                    });
//                    $('#example-select-all').on('click', function () {
//                        // Check/uncheck all checkboxes in the table
//                        var rows = table.rows({'search': 'applied'}).nodes();
//                        $('input[type="checkbox"]', rows).prop('checked', this.checked);
//                    });
                });
            });</script>

        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popCropVal').popover(popCropVal);
                $('#popSoilAna').popover(popSoilAna);
                $('#popFertInfo').popover(popFertInfo);
                $('#popTillInfo').popover(popTillInfo);
                $('#popFarmProbList').popover(popFarmProbList);
                $('#popFarmRecList').popover(popFarmRecList);
                $('#popFarmActionTools').popover(popFarmActionTools);
                $('#popFarmObs').popover(popFarmObs);
                $('#popFarmProgList').popover(popFarmProgList);

            });


        </script>
        <script>
// 
//            $(document).ready(function () {
//
//            });
//
//
//        </script>
        <script type="text/javascript">//
            //ORIGINAL SCRIPT 
//            $(function () {
//
//                $.ajax({
//                    url: 'createTagList?id=${id}',
//                    type: 'POST',
//                    dataType: "JSON",
//                    success: function (data) {
//                          $("#select2").select2({
//                    data: data
//                   });
//                    }});
//              
//               //  var data = ['enhancement', 'bug', 'duplicate','invalid', 'wontfix'];                          
//                // var data = [{text: 'enhancement'}, {text: 'bug'}, {text: 'duplicate'}, {text: 'invalid'}, {text: 'wontfix'}];      
//                // var data = [{id: 0, text: 'enhancement'}, {id: 1, text: 'bug'}, {id: 2, text: 'duplicate'}, {id: 3, text: 'invalid'}, {id: 4, text: 'wontfix'}];
//             
//                $("#sButton").on("click", function () {
//                    var test = $("#select2").val();
//
//                    $.ajax({
//                        url: 'searchSimilarFarms?tag=' + test + '&id=${id}',
//                        type: 'POST',
////                    dataType: "JSON",
//                        success: function (data) {
//
//                            alert("it worked!!!!!");
//
//
//                        }
//                    });
//                });
//            });</script>
    </body>

</html>