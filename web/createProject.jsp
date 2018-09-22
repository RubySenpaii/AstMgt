<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>SRA | Home</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

        <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker-bs3.css">
        <link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
        <link rel="stylesheet" href="plugins/select2/select2.min.css">
        <!--<link rel="stylesheet" href="plugins/formWiz/custom.css">-->
        <link rel="stylesheet" href="plugins/formWiz/components.min.css">
        <link rel="stylesheet" href="plugins/formWiz/plugins.min.css">
        <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
        <!--<link rel="stylesheet" href="plugins/formWiz/prettify.css">-->

    </head>
    <body class="hold-transition skin-blue sidebar-mini">

        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content-header">

                    <h1>
                        Create Plans & Programs
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="box box-info">
                                <div class="box-body">
                                    <div class="form" id="rootwizard">
                                        <form class="form-horizontal" action="createNewProgram" id="submit_form" method="POST" novalidate="novalidate">
                                            <div class="form-wizard">
                                                <div class="form-body">
                                                    <ul class="nav nav-pills nav-justified steps">
                                                        <li class="active">
                                                            <a href="#tab1" data-toggle="tab" class="step" aria-expanded="true">
                                                                <span class="number"> 1 </span>
                                                                <span class="desc">
                                                                    <i class="fa fa-check"></i>Problem Selection </span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="#tab2" data-toggle="tab" class="step">
                                                                <span class="number"> 2 </span>
                                                                <span class="desc">
                                                                    <i class="fa fa-check"></i> Program Setup </span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="#tab3" data-toggle="tab" class="step active">
                                                                <span class="number"> 3 </span>
                                                                <span class="desc">
                                                                    <i class="fa fa-check"></i> Performance Indicators </span>
                                                            </a>
                                                        </li>
                                                        <!--                                                        <li>
                                                                                                                    <a href="#tab4" data-toggle="tab" class="step">
                                                                                                                        <span class="number"> 4 </span>
                                                                                                                        <span class="desc">
                                                                                                                            <i class="fa fa-check"></i> Confirm </span>
                                                                                                                    </a>
                                                                                                                </li>-->
                                                    </ul>
                                                    <div id="bar" class="progress">
                                                        <div class="progress-bar progress-bar-striped progress-bar-aqua active"  aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>

                                                    </div>                 
                                                    <div class="tab-content">
                                                        <div class="tab-pane active" id="tab1">
                                                            <h3 class="block">Program is aimed to solve:</h3>

                                                            <table id="probTable" class="table  dispTable table-hover" cellspacing="0" width="100%">
                                                                <thead>
                                                                    <tr>
                                                                        <th></th>
                                                                        <th>Problem</th>
                                                                        <th>Description</th>
                                                                        <th>Status</th>
                                                                        <th>Type</th>
                                                                        <th># Aff. Farms</th>
                                                                        <th># Solutions</th>
                                                                    </tr>
                                                                </thead>

                                                            </table>

                                                            <div class="col-md-offset-9 col-md-9 pull-right">
                                                                <a class="btn btn-outline green button-next"> Continue
                                                                    <i class="fa fa-angle-right"></i>
                                                                </a>

                                                            </div>


                                                        </div>
                                                        <div class="tab-pane" id="tab2">
                                                            <h3 class="block">Provide the Program details</h3>
                                                            <div class="form-group">
                                                                <label class="control-label col-md-3">Program Name
                                                                    <span class="required" aria-required="true"> * </span>
                                                                </label>
                                                                <div class="col-md-4">
                                                                    <input type="text" class="form-control" name="projectname" id="projectname" placeholder="Name...">

                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label col-md-3">Type
                                                                    <span class="required" aria-required="true"> * </span>
                                                                </label>
                                                                <div class="col-md-4">
                                                                    <select class="form-control" id="projecttype" name="projecttype">
                                                                        <option>Development</option>
                                                                        <option>Education</option>
                                                                        <option>Management</option>
                                                                        <option>Practices</option>
                                                                        <option>Research</option>
                                                                        <option>Technology</option>
                                                                        <option>Weather</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label col-md-3">Program Duration
                                                                    <span class="required" aria-required="true"> * </span>
                                                                </label>
                                                                <div class="col-md-4">
                                                                    <div class="input-group">
                                                                        <div class="input-group-addon">
                                                                            <i class="fa fa-calendar"></i>
                                                                        </div>
                                                                        <input type="text" class="form-control reservation pull-right" name="reservation" id="reservation">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label col-md-3">Description
                                                                    <span class="required" aria-required="true"> * </span>
                                                                </label>
                                                                <div class="col-md-4">
                                                                    <textarea class="form-control" id="projectdescription" name="Description" rows="2"  placeholder="Enter ..."></textarea>

                                                                </div>
                                                            </div>
                                                            <div class="col-md-offset-9 col-md-9 pull-right">

                                                                <a class="btn btn-outline green progdetbtn button-next "> Continue
                                                                    <i class="fa fa-angle-right"></i>
                                                                </a>

                                                            </div>
                                                        </div>
                                                        <div class="tab-pane" id="tab3">
                                                            <h3 class="block">Input Performance Indicators</h3>
                                                            <input id="kpi_year" name="kpi_year" class="form-control hidden" />
                                                            <input id="tYears" name="tYears" class="form-control hidden" />
                                                            <table class = "table table-bordered bull" id = "listOfTargets">
                                                                <thead>
                                                                    <tr>
                                                                        <th style="width: 30%">Performance Indicator</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>                      
                                                                </tbody>


                                                            </table>
                                                            <div class="col-md-2 pull-right">
                                                                <button  class="btn btn-danger"  type="button" id ="adddRow"><span class = "glyphicon glyphicon-plus"></span> Row</button>
                                                            </div>
                                                            <br/>



                                                            <div class="col-md-offset-9 col-md-9 pull-right">
                                                                <a  class="btn default button-previous" >
                                                                    <i class="fa fa-angle-left"></i> Back </a>
                                                                <button class="btn green finish" form="submit_form" value="submit">Submit  <i class="fa fa-check"></i></button>

                                                            </div>
                                                        </div>
                                                        <!--                                                        <div class="tab-pane" id="tab4">
                                                                                                                    <div style="float:right">
                                                                                                                        <input type='button' class='btn button-next' name='next' value='Next' />
                                                                                                                    </div>
                                                                                                                    <div style="float:left">
                                                                                                                        <input type='button' class='btn button-previous' name='previous' value='Previous' />
                                                                                                                    </div>
                                                                                                                </div>-->
                                                    </div>
                                                </div>

                                            </div>
                                        </form>
                                    </div>



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


        <script type="text/javascript" src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="plugins/formWiz/jquery.bootstrap.wizard.js"></script>
        <script src="plugins/select2/select2.full.min.js"></script>
        <!--<script src="plugins/formWiz/prettify.js"></script>-->
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript" src="plugins/datatable/dataTables.checkboxes.min.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <script src="plugins/daterangepicker/moment.min.js"></script>
        <script src="plugins/daterangepicker/daterangepicker.js"></script>
        <script src="plugins/datepicker/bootstrap-datepicker.js"></script>
        <script src="dist/js/app.min.js"></script>
        <!--<script type="text/javascript">
                    $(document).ready(function () {
                        
                    $("#progdetbtn").on("click", function () {
                        
                        if(document.getElementById("reservation").value.length===0){
                              $('#progdetbtn').addClass('hidden');
                              $('#progdetbtn').remove('button-next');
                              
                        }
                          
                            //   $('#actionButton').addClass('hidden');
        
                        });
                $('#reservation').keydown(function () {
                            if ($(this).val().length !== 0)
        //                        $('.btnprojdetails').attr('hidden', false);
                                 $('.btnprojdetails').removeClass('hidden');
                            else
                                $('.btnprojdetails').addClass('hidden');
                        });
                        function stoppedTyping() {
                          
                            var x = 0;
                            if (x === 0) {
        
        
                                if (this.value.length > 0) {
                                    document.getElementByClassName('.button-next').disabled = false;
                                } else {
                                    document.getElementByClassName('.button-next').disabled = true;
                                }
                            } else if (x === 1) {
                                if (this.value.length > 0) {
                                    $('.btnprojdetails').attr('disabled', false);
                                } else {
        
                                    $('.btnprojdetails').attr('disabled', false);
                                }
                            }
                        }
                    });
                </script>-->
        <script>

            $(document).ready(function () {

                var chckr = false;

                $('#rootwizard').bootstrapWizard({
                    onTabClick: function (tab, navigation, index) {
                        return false;
                    },
                    'tabClass': 'nav nav-pills',
                    'onNext': function (tab, navigation, index) {
                        //checks if values are empty or not
                        if (index === 1) {


                            var nocheck = $('#probTable').find('input[type="checkbox"]:checked').length;
                            if (nocheck === 0) {
                                return false;
                            }
                        } else if (index === 2) {
                            if (document.getElementById('reservation').value.length === 0) {
                                return false;
                            } else if (document.getElementById('projecttype').value.length === 0) {
                                return false;
                            } else if (document.getElementById('projectname').value.length === 0) {
                                return false;
                            } else if (document.getElementById('projectdescription').value.length === 0) {
                                return false;
                            }
                        }
                    }, onTabShow: function (tab, navigation, index) {
                        //runs these when tabs are gonna show
                        if (index === 2 && chckr === false) {
                            chckr = true;
                            if (document.getElementById('reservation').value.length === 0) {
                                console.log(tab);
                                console.log(navigation);
                                console.log(index);
                                index = index - 1;
                                return false;
                            }
                            var x = document.getElementById('reservation').value.split('-');
                            var ini = x[0].split('/');
                            var end = x[1].split('/');

                            ival = ini[2];
                            fval = end[2];

                            count += fval - ival;

                            for (var b = 0; b <= count; b++) {

                                $('#listOfTargets tr:last').append("<th>" + (ival * 1 + b) + "</th>");
                            }
                            $('#listOfTargets tr:last').append("<th></th>");


//add counter and initial val to form
                            document.getElementById("kpi_year").value = ival;
                            document.getElementById("tYears").value = count;


                        }

                        var $total = navigation.find('li').length;
                        var $current = index + 1;
                        var $percent = ($current / $total) * 100;
                        $('#rootwizard .progress-bar').css({width: $percent + '%'});
                    },
                    'nextSelector': '.button-next', 'previousSelector': '.button-previous'
                });
                $('#rootwizard .finish').click(function () {
                    var nocheck = document.getElementsByClassName("dakpis").length;
                    console.log(nocheck);
                    if (nocheck === 0) {
                        return false;
                    }

                });

                $("#adddRow").on("click", function () {
                    var rowz = "";
                    for (var b = 0; b <= count; b++) {

                        rowz += "<td><input class='form-control dakpis' type='number' value='0' name = 'y" + rowNum + "[]'  required/></td>";
                    }

                    $('#listOfTargets tbody:last').append("<tr>\n\
                                  <td><input class='form-control' style='width: 100%' name = 'kpis" + rowNum + "'  required/></td>\n\\n\
                                        " + rowz + " \n\
                    //<td id='deletedrow'><div class='glyphicon glyphicon-remove'></div></td> \n\
        </tr>");

                    rowNum++;

                });

            });

        </script>

        <script>
            $(function () {

                //Datemask dd/mm/yyyy
                $("#datemask").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});
                //Datemask2 mm/dd/yyyy
                $("#datemask2").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});
                $('.datepicker').datepicker({
                    autoclose: true
                });

                $('#reservation').daterangepicker();
                $('#datepicker').datepicker({
                    autoclose: true
                });



            });
        </script>
        <!--        <script>
        
                    var rowNum = 1;
        
        
                    $('#listOfItems').ready(function () {
                        $("#listOfItems").on('click', '#deleterow', function () {
                            var $killrow = $(this).parent('tr');
                            $killrow.addClass("danger");
                            $killrow.fadeOut(1000, function () {
                                $(this).remove();
                            });
                        });
                    });
        
        
                    $(document).ready(function () {
                        $('#listOfItems tbody:last').append("<tr>\n\
                                              <td><input class='form-control' style='width: 100%' name = 'kpi" + rowNum + "' value='Hectares rehabilitated' required/></td>\n\
                                            <td><input class='form-control' name = 'y" + rowNum + "[]' value='700' required/></td>\n\
                                            <td><input class='form-control'  name = 'y" + rowNum + "[]' value='20000' required/></td>\n\
                                            <td><input class='form-control' name = 'y" + rowNum + "[]' value='20000' /></td>\n\
                                            <td id='deleterow'><div class='glyphicon glyphicon-remove'></div></td> \n\
                </tr>");
        
                        rowNum++;
        
                        $("#addRow").on("click", function () {
        
                            $('#listOfItems tbody:last').append("<tr>\n\
                                          <td><input class='form-control' style='width: 100%' name = 'kpi" + rowNum + "'  required/></td>\n\
                                            <td><input class='form-control' name = 'y" + rowNum + "[]'  required/></td>\n\
                                            <td><input class='form-control'  name = 'y" + rowNum + "[]' required/></td>\n\
                                            <td><input class='form-control' name = 'y" + rowNum + "[]'  /></td>\n\
                                            <td id='deleterow'><div class='glyphicon glyphicon-remove'></div></td> \n\
                                             </tr>");
                            rowNum++;
        
                        });
        
                    });
        
        
        
                </script>-->
        <script>

            var rowNum = 1;


            $('#listOfTargets').ready(function () {
                $("#listOfTargets").on('click', '#deletedrow', function () {
                    var $killrow = $(this).parent('tr');
                    $killrow.addClass("danger");
                    $killrow.fadeOut(1000, function () {
                        $(this).remove();
                    });
                });
            });
            var ival = 0;
            var fval = 0;
            var count = 0;
            $("#addcolumn").on("click", function () {
                var x = document.getElementById('reservation').value.split('-');
                var ini = x[0].split('/');
                var end = x[1].split('/');

                ival = ini[2];
                fval = end[2];

                count += fval - ival;

                for (var b = 0; b <= count; b++) {

                    $('#listOfTargets tr:last').append("<th>" + (ival * 1 + b) + "</th>");
                }
                $('#listOfTargets tr:last').append("<th></th>");


//add counter and initial val to form
                document.getElementById("kpi_year").value = ival;
                document.getElementById("tYears").value = count;


            });






        </script>
        <script>

            $(document).ready(function () {
                var rows_selected = [];


                var table1 = $('#probTable').DataTable({
                    'ajax': {
                        'url': 'viewPlansProblemTable'
                    },
                    'columnDefs': [{
                            'targets': 0,
                            'searchable': false,
                            'orderable': false,
                            'className': 'dt-body-center',
                            'render': function (data, type, full, meta) {
                                return '<input type="checkbox" id="inputbox" name="probid[]"  value="'
                                        + $('<div/>').text(data).html() + '">';
                            }
                        }],
                    'select': {
                        'style': 'multi'
                    },
                    'order': [[5, 'desc']]

                });
                $('#frm-example').on('submit', function (e) {
                    var form = this;

                    // Iterate over all checkboxes in the table
//              
                    table1.$('input[type="checkbox"]').each(function () {
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


//                $('#probTable-select-all').on('click', function () {
//                    // Check/uncheck all checkboxes in the table
//                    var rows = table1.rows({'search': 'applied'}).nodes();
//                    $('input[type="checkbox"]', rows).prop('checked', this.checked);
//
//                });


            });


        </script>



    </body>

</html>