<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">
        <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
        <title>Asset Management - Employee Registration</title>

        <!-- Favicons -->
        <link href="img/favicon.png" rel="icon">
        <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Bootstrap core CSS -->
        <link href="../../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!--external css-->
        <link href="../../lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <!-- Custom styles for this template -->
        <link href="/AMS/lib/dashio/css/style.css" rel="stylesheet">
        <link href="/AMS/lib/dashio/css/style-responsive.css" rel="stylesheet">
    </head>

    <body>
        <!-- Main Content-->
        <div id="login-page">
            <div class="container">
                <form class="form-login" action="/AMS/Register" method="post">
                    <h2 class="form-login-heading">Register</h2>
                    <div class="login-wrap">
                        <input type="text" class="form-control" name="FirstName" id="FirstName" placeholder="FirstName" autocomplete="off">
                        <br>
                        <input type="text" class="form-control" name="LastName" id="LastName" placeholder="LastName" autocomplete="off">
                        <br>
                        <input type="email" class="form-control" name="Email" id="Email" placeholder="Email" autocomplete="off">
                        <br>
                        <input type="text" class="form-control" name="ContactNo" id="ContactNo" placeholder="ContactNo" autocomplete="off">
                        <br>
                        <input type="text" class="form-control" name="EntityName" id="EntityName" placeholder="EntityName" autocomplete="off">
                        <br>
                        <input type="text" class="form-control" name="Office" id="Office" placeholder="Office" autocomplete="off">
                        <br>
                        <input type="text" class="form-control" name="Division" id="Division" placeholder="Division" autocomplete="off">
                        <br>
                        <input type="text" class="form-control" name="CivilStatus" id="CivilStatus" placeholder="CivilStatus" autocomplete="off">
                        <br>
                        <input type="date" class="form-control" name="BirthDate" id="BirthDate" placeholder="BirthDate" autocomplete="off">
                        <br>
                        <input type="text" class="form-control" name="Gender" id="Gender" placeholder="Gender" autocomplete="off">
                        <br>
                        <input type="text" class="form-control" name="EmployeeStatus" id="EmployeeStatus" placeholder="EmployeeStatus" autocomplete="off">
                        <br>
                        <input type="text" class="form-control" name="Username" id="Username" placeholder="Username" autocomplete="off">
                        <br>
                        <input type="text" class="form-control" name="UserLevel" id="UserLevel" placeholder="UserLevel" autocomplete="off">
                        <br>
                        <input type="password" class="form-control" name="Password" id="Password" placeholder="Password" autocomplete="off">
                        <br>
                        <input type="date" class="form-control" name="EndDate" id="EndDate" placeholder="EndDate" autocomplete="off">
                        <br>
                        <input type="text" class="form-control" name="Flag" id="Flag" placeholder="Flag" autocomplete="off">
                        <br>
                        <button class="btn btn-theme btn-block" value="Sign In" type="submit"><i class="fa fa-save"></i> Register </button>
                    </div>
                </form>
            </div>
        </div>
        <!-- js placed at the end of the document so the pages load faster -->
        <script src="../../lib/jquery/jquery.min.js"></script>
        <script src="../../lib/bootstrap/js/bootstrap.min.js"></script>
    </body>

</html>
