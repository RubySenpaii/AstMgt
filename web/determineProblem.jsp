<%-- 
    Document   : determineProblem
    Created on : 10 2, 16, 10:21:03 PM
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
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
       
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        Determine Problem
                        <small>***Verify Problem***</small>
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                        <form id="frm-example" action="determineFarmProblems">

                            <div class="col-md-6">
                                <div class="box box-solid box-success">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Additional Details</h3>
                                    </div>
                                    <br>
                                    <div class="box-body">
                                        
                                        <div class="form-group">
                                            <label>Message</label>
                                            <p> <textarea type="text" name="msg" id="msg" class="form-control" placeholder="These problems are based on my observed comparison" style="width: 100%"></textarea> </p>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-6" > 
                                <div class="box box-info">
                                    <div class="box-header">
                                        <h1 class="box-title">Send To: </h1>

                                    </div>

                                    <div class="box-body ">

                                        <table class="table table-hover table-no-bordered">
                                            <tbody><tr>


                                                </tr>
                                                <c:if test="${not empty flist}">
                                                
                                                    <c:forEach var="farmer" items="${flist}">
                                                        <tr>
                                                            <td><c:out value="${farmer}"/></td>
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
                                    <div class="box-header">
                                        <h1 class="box-title">Possible Problem: </h1>

                                    </div>

                                    <div class="box-body ">

                                        <table id="example" class="table  display table-hover pull-right" cellspacing="0" width="100%">
                                            <thead>
                                                <tr>
                                                    <th></th>
                                                    <th>Problem</th>
                                                    <th>Type</th>
                                                    <th>Description</th>

                                                </tr>
                                            </thead>

                                        </table>


                                    </div>

                                </div>

                            </div>


                            <div class="col-md-2 text-center pull-right">
                                 <select style="display: none" name="flist" id="flist"><option>${flist}</option></select>
                                <p><button class="btn btn-primary btn-block" type="submit" style="width: 100%" value="submit">Submit</button></p>
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
        <script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="dist/js/app.min.js"></script>
        <script src="Highcharts/highcharts.js"></script>
    <script src="plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript" src="plugins/datatable/dataTables.checkboxes.min.js"></script>
    <script src="plugins/datepicker/bootstrap-datepicker.js"></script>
    <script>
        $(function () {
            $('.datepicker').datepicker({
                autoclose: true
            });
        });
    </script>
    <script>

        $(document).ready(function () {
            var rows_selected = [];

            var table = $('#example').DataTable({
                'ajax': {
                    'url': 'viewOtherProblems'
                },
                'columnDefs': [{
                        'targets': 0,
                        'searchable': false,
                        'orderable': false,
                        'className': 'dt-body-center',
                        'render': function (data, type, full, meta) {
                            return '<input type="checkbox" name="probid[]" id="buttonClick" value="'
                                    + $('<div/>').text(data).html() + '">';
                        }
                    }],
                'select': {
                    'style': 'multi'
                },
                'order': [[1, 'asc']]
                        //      ,
                        //       'rowCallback': function(row, data, dataIndex){
                        //         // Get row ID
                        //       var rowId = data[0];
                        //       // alert(rowId);
                        //         // If row ID is in the list of selected row IDs
                        //         if($.inArray(rowId, rows_selected) !== -1){
                        //            $(row).find('input[type="checkbox"]').prop('checked', true);
                        //            $(row).addClass('selected');
                        //         }
                        //      }     

            });

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
