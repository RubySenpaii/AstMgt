<%@page import="java.util.ArrayList"%>
<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        Page Header
                        <small>Optional description</small>
                    </h1>
                </section>
                <section class="content">



                    <div class="col-md-10" > 
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h1 class="box-title">Area Harvested</h1>
                                <div class="box-tools pull-right">
                                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                    <!-- In box-tools add this button if you intend to use the contacts pane -->
                                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>

                            <div class="box-body no-padding">
                                <table class="table table-bordered" >
                                    <tbody>
                                        <tr>
                                            <th>Particulars</th>
                                            <th>Estimated Production</th>
                                            <th>Previous</th>
                                            <th>This Week</th>
                                            <th>To Date</th>
                                            <th>Percent Completed</th>	
                                        </tr>
                                        <tr>	
                                            <td>Area</td>
                                            <td>5,000.00</td>
                                            <td>3,850.00</td>
                                            <td>350.00</td>
                                            <td>4,200.00</td>
                                            <td>
                                                <div class="progress-group">
                                                    <span class="progress-number"><b>84 %</b></span>

                                                    <div class="progress sm">
                                                        <div class="progress-bar progress-bar-green" style="width: 84%"></div>
                                                    </div>
                                                </div> <!-- closer of progress bars -->  
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
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
    </body>
</html>

