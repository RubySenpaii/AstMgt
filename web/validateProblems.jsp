<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        Confirm Problems
                    </h1>
                </section>
                <section class="content">
                    <div class="row">


                        <form action="sendRelatedRec" id="submit_form" method="POST">
                            <div class="col-md-6" > 
                                <div class="box box-info">
                                    <div class="box-header">
                                        <h1 class="box-title">Send To: </h1>
<div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popSendto" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body ">

                                        <table class="table table-hover table-no-bordered">
                                            <thead>
                                            <th>Names</th>    
                                            </thead>
                                            <tbody>
                                                <c:if test="${not empty flist}">
                                                    <c:forEach var="farmer" items="${flist}">
                                                        <tr>
                                                            <td><input class="form-control hidden" name="farmz[]" value="${farmer}"><c:out value="${farmer}"/></td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </tbody>
                                        </table>


                                    </div>

                                </div>

                            </div>
                           <div class="col-md-6 pull-right">
                                <div class="box box-info" id="genform">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Additional Message</h1>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popProbMsg" role="button"><i class="fa fa-question text-orange"></i></a>
                                           <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                    </div>
                                </div>

                                <div class="box-body">



                                    <div class="form-group">
                                        <label class="control-label col-md-3">Message

                                        </label>
                                        <div class="col-md-9">
                                            <textarea class="form-control" name="description" rows="3" >These problems are based on my observed comparison</textarea>
                                        </div>
                                    </div>


                                </div>
                            </div>
                               </div>
                            <div class="col-md-12" > 
                                
                                <div class="box box-info">
                                    <div class="box-header">
                                        <h1 class="box-title">Selected Problems: </h1>
 <div class="box-tools pull-right">
                                            <a tabindex="0" class="text-overflow" id="popVSelProb" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body ">

                                        <table id="allrecs" class="table  display table-hover pull-right" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                            
                                                <th>Problem</th>
                                                <th>Type</th>
                                                <th>Phase</th>
                                                <th>Description</th>
                                                <th>More Info</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                  <c:if test="${not empty daprobs}">
                                       <c:forEach items="${daprobs}" var="prob" >
                                           <tr>
                                     
                                       <td><c:out value="${prob.prob_name}"/></td>
                                       <td><c:out value="${prob.type}"/></td>
                                       <td><c:out value="${prob.phase}"/></td>
                                       <td><c:out value="${prob.prob_details}"/></td>
                                       <td><a href="viewProbDetails?id=${prob.prob_id}" target="_blank" class="btn btn-primary text-center">...</a></td>
                                           </tr>
                                           </c:forEach>
                                           </c:if>
                                        </tbody>
                                    </table>


                                    </div>

                                </div>

                            </div>


                            <div class="col-md-2 text-center pull-right">       
                                 
                                <p><button class="btn btn-primary btn-block" style="width: 100%" value="submit">Send</button></p>
                            </div>
                        </form>  
                    </div>
                </section>
            </div>


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
    <script src="Highcharts/highcharts.js"></script>
    <script src="plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript" src="plugins/datatable/dataTables.checkboxes.min.js"></script>
    <script src="plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="plugins/poptest/popoverText.js"></script>
    <script>
        $(document).ready(function () {
            $('#popSendto').popover(popSendto);
            $('#popProbMsg').popover(popProbMsg);
            $('#popVSelProb').popover(popVSelProb);
        });


    </script>
    <script>
        $(function () {
            $('.datepicker').datepicker({
                autoclose: true
            });
        });</script>
    <script>

        $(document).ready(function () {
//            var rows_selected = [];
//
            var table = $('#allrecs').DataTable();
//                'ajax': {
//                    'url': 'viewOtherRecommendations'
//                },
//                'columnDefs': [{
//                        'targets': 0,
//                        'searchable': false,
//                        'orderable': false,
//                        'className': 'dt-body-center',
//                        'render': function (data, type, full, meta) {
//                            return '<input type="checkbox" name="id[]" id="buttonClick" value="'
//                                    + $('<div/>').text(data).html() + '">';
//                        }
//                    }, {
//                        'targets': 4,
//                        'render': function (data, type, full, meta) {
//                            return '<a href="viewRecDetails?id=' + data + '" class="btn btn-primary text-center">' + 'more details' + '</a>';
//                        }
//                    }],
//                'select': {
//                    'style': 'multi'
//                },
//                'order': [[1, 'asc']]
//                        //      ,
//                        //       'rowCallback': function(row, data, dataIndex){
//                        //         // Get row ID
//                        //       var rowId = data[0];
//                        //       // alert(rowId);
//                        //         // If row ID is in the list of selected row IDs
//                        //         if($.inArray(rowId, rows_selected) !== -1){
//                        //            $(row).find('input[type="checkbox"]').prop('checked', true);
//                        //            $(row).addClass('selected');
//                        //         }
//                        //      }     
//
//            });

            $('#frm-example').on('submit', function (e) {
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
        });


    </script>
</body>

</html>