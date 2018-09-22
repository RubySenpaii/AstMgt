<%-- 
    Document   : setRecommendationFromWeatherTrends
    Created on : 09 29, 16, 6:07:12 AM
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
                    
                </section>
                <section class="content">



                    <div class="col-md-10" > 
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h1 class="box-title">Recommendation</h1>
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
                                            <th>Recommendation</th>
                                            <th>Description</th>
                                            <th>Duration</th>
                                        </tr>
                                        <tr>	
                                            <td>Irrigation Recommendation for January</td>
                                            <td>Farmers are suggested to put 30 mm or water to their crops</td>
                                            <td><input type="text"  placeholder="mm" style="width: 100%"></td>
                                        </tr>
                                        
                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <div class="btn btn-primary pull-right">
                                        Save Changes
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
