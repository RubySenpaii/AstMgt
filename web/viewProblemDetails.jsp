<%-- 
    Document   : viewProblemDetails
    Created on : 09 29, 16, 7:34:58 AM
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
                        View Problem
                        <small>Problem Details</small>
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                    <div class="col-md-4">
                        <div class="box box-solid box-primary">
                            <div class="box-header with-border">
                                <h3 class="box-title">Problem Details</h3>
                            </div>
                            <br>
                            <div class="box-body">
                                <ul class="list-group list-group-unbordered">


                                    <li class="list-group-item">
                                        <b>Problem Name : </b> <a class="pull-right"><b>
                                                <c:out value="${problem.prob_name}"></c:out>
                                                </b> </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Type</b> <a class="pull-right">
                                            <c:out value="${problem.type}"></c:out>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Total Farms Affected </b> <a class="pull-right">
                                            <c:out value="${problem.totalFarms}"></c:out>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Number of Solutions</b> <a class="pull-right" href="#myModal" data-toggle="modal" data-target="#myModal">
                                            <c:out value="${problem.tSolutions}"></c:out>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Number of Programs</b> <a class="pull-right" href="#myModal1" data-toggle="modal" data-target="#myModal1">
                                            <c:out value="${problem.tPrograms}"></c:out>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <b>Description</b> <a class="">
                                            <c:out value="${problem.prob_details}"></c:out>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel">Send Recommendation</h4>
                                    </div>
                                    <div class="modal-body">
                                        <table class="table table-bordered" >
                                            <tbody>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Solution</th>
                                                    <th>Description</th>
                                                </tr>

                                            <c:forEach var="sol" items="${solution}">
                                                <tr>
                                                    <td>${sol.id}</td>
                                                    <td>${sol.recommendation_name}</td>
                                                    <td><button><a href="viewRecDetails?id=${sol.id}"><b> View Details</b></a></button></td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                        <!-- Modal -->
                        <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel">Send Programs</h4>
                                    </div>
                                    <div class="modal-body">
                                        <table class="table table-bordered" >
                                            <tbody>
                                                <tr>
                                                    <th>Programs Name</th>
                                                    <th>Description</th>
                                                    <th>More</th>
                                                </tr>

                                            <c:forEach var="prg" items="${programs}">
                                                <tr>
                                                    <td>${prg.prog_name}</td>
                                                    <td>${prg.description}</td>
                                                    <td><button><a href="viewProgramDetails?name=${prg.prog_name}"><b> View Details</b></a></button></td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-8" > 
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h1 class="box-title">Problem</h1>
                                <div class="box-tools pull-right">
                                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                    <!-- In box-tools add this button if you intend to use the contacts pane -->
                                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>

                            <div class="box-body">
                                <table class="table table-bordered" id="example">
                                    <thead>
                                        <tr>
                                            <th style="width:15%">Farm</th>
                                            <th>Farmer</th>
                                            <th>Barangay</th>
                                            <th>Date</th>
                                            <th>Status</th>
                                            <th>Damage</th>
                                            <th>Details</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>

                        </div>
                    </div>        
                    <br>
                    <div class="col-md-3">   
                        <p><a class="btn btn-success" style="width: 100%" href="createNewRecommendation.jsp"><i class="fa fa-gear "></i> Create Recommendation</a></p>
                    </div>
                    </div>
                </section>

            </div>

        </div>
        <script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="dist/js/app.min.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script>

            $(document).ready(function () {
                var table = $('#example').DataTable({
                    'ajax': {
                        'url': 'viewPRT?probid=${probid}'
                    },
                    'columnDefs': [{
                            'targets': 6,
                            'render': function (data, type, full, meta) {
                                    return   '<a class="btn btn-primary" target="blank" href="viewFieldDetails?id=' + data + '">details</a>';
                               
                            }}],'order': [[3, 'desc']]
                    
                    
                });
            });


        </script>
    </body>
</html>
