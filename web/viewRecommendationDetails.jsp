
<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
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

                        Recommendations Details 
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="box box-primary">
                                <div class="box-body box-profile">

                                    <h3 class="profile-username text-center"><c:out value="${recdet.recommendation_name}"></c:out></h3> 
                                        <ul class="list-group list-group-unbordered">


                                            <!--                                        <li class="list-group-item">
                                                                                        <b>Recommendation </b>  <a class="pull-right">
                                        <c:out value="${recdet.recommendation_name}"></c:out>
                                        </a>
                                </li>-->
                                            <li class="list-group-item">
                                                <b>Type</b><a class="pull-right">
                                                <c:out value="${recdet.type}"></c:out>
                                                </a>
                                            </li>
                                            <li class="list-group-item">
                                                <b>Description </b><a class="text-right"><p>
                                                    <c:out value="${recdet.description}"></c:out>
                                                    </p></a>
                                            </li>
                                            <li class="list-group-item">
                                                <b>Phase</b> <a class="pull-right">
                                                <c:out value="${recdet.phase}"></c:out>

                                                </a>
                                            </li>
                                            <li class="list-group-item">
                                                <b>Status</b> <a class="pull-right">
                                                <c:out value="${recdet.status}"></c:out>

                                                </a>
                                            </li>




                                        </ul>

                                    </div>
                                </div>
                            </div>

                                     

                            <div class="col-md-7">
                                <c:if test="${not empty problist}">
                                <div class="box box-info"> 
                                     
                                    <div class="box-header with-border">
                                   
                                        <h3 class="box-title">Problems being solved: </h3>
                                         <div class="box-tools pull-right">
                                              <a tabindex="0" class="" id="popProbSolved" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body">
                                    <c:if test="${not empty problist}">
                                        <table class="table table-bordered" >
                                            <thead>
                                                <tr>
                                                    <th>Problem</th>
                                                    <th>Description</th>
                                                    <th>Details</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <c:forEach var="plist" items="${problist}">

                                                    <tr>	
                                                        <td>${plist.prob_name}</td>
                                                        <td>${plist.prob_details}</td>
                                                        <td><a class="btn btn-primary" href="viewProbDetails?id=${plist.prob_id}" >details</a></td>
                                                    </tr>
                                                </c:forEach>


                                            </tbody>
                                        </table>
                                    </c:if>
                                </div>
                                                        
                            </div>
                                </c:if>
                        </div>


                        <div class="col-md-12" > 
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Recommendation w/ fields</h1>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popRecfield" role="button"><i class="fa fa-question text-orange"></i></a>
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>

                                <div class="box-body">
                                    <table id="recList1" class="table table-bordered" >
                                        <thead>
                                            <tr>
                                                <th>Farm</th>
                                                <th>Farmer</th>
                                                <th>Barangay</th>
                                                <th>Municipality</th>
                                                <th>Date</th>
                                                <th>Status</th>
                                                <th>Duration(days)</th>
                                                <th>Details</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>

                            </div>

                        </div>   
                                                
                    </div>

                    <br>

                </section>

            </div>

        </div>
        <script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="dist/js/app.min.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
                                   $(document).ready(function () {
                                       $('#popRecfield').popover(popRecfield);
                                       $('#popProbSolved').popover(popProbSolved);

                                   });


        </script>
        <script>

            $(document).ready(function () {
                var table = $('#recList1').DataTable({
                    'ajax': {
                        'url': 'viewFRTable?id=${id}'
                    },
                    'columnDefs': [{
                            'targets': 7,

                            'render': function (data, type, full, meta) {
                                return '<a href="viewFieldDetails?id=' + data + '" class="btn btn-primary">Details</a>';
                            }

                        }],'order': [[4, 'desc']]

                });
            });


        </script>
    </body>
</html>
