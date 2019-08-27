<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">
        <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
        <title>DAR-AMIS - Asset Management Information System</title>

        <!-- Favicons -->
        <link href="img/darlogo.jpg" rel="icon">
        <link href="img/darlogo.jpg" rel="apple-touch-icon">

        <!-- Bootstrap core CSS -->
        <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!--external css-->
        <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <!-- Custom styles for this template -->
        <link href="/AMS/lib/dashio/css/style.css" rel="stylesheet">
        <link href="/AMS/lib/dashio/css/style-responsive.css" rel="stylesheet">
    </head>

    <body>
        <!-- Main Content -->
        <div id="login-page">
            <h1 class="text-center text-bold">Department of Agrarian Reform</h1>
            <h2 class="text-center text-bold">Asset Management Information System</h2>
            <div class="container">

                <form class="form-login" action="Login" method="post">
                    <h2 class="form-login-heading">Log In to Start your Session</h2>
                    <div class="login-wrap">
                        <%
                            boolean checker = true;
                            boolean loggedout = false;
                            try {
                                loggedout = (boolean) session.getAttribute("loggedout");
                                checker = (boolean) session.getAttribute("logged");
                            } catch (Exception e) {
                            }
                            if (!checker) {
                        %>
                        <div class=" alert alert-danger"> <b> Invalid Username/Password ! </b> </div>

                        <%                            }
                        %>
                        
                         <input type="hidden" id="loggedout" name="loggedout" value="<%= loggedout%>">
                        <br>
                        <%
                            session.removeAttribute("loggedout");
                        %>
                        <input type="text" class="form-control" name="username" id="username" placeholder="User ID" autofocus>
                        <br>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                        <br>
                        <button class="btn btn-theme btn-block" value="Sign In" type="submit"><i class="fa fa-lock"></i> SIGN IN</button>
                        <br>
                        <!--<a class="btn btn-theme btn-block" href="/AMS/forms/admin/register.jsp"><i class="fa fa-user-plus"></i> REGISTER</a> -->
                    </div>
                </form>
            </div>
        </div>
        <!-- js placed at the end of the document so the pages load faster -->
        <script src="lib/jquery/jquery.min.js"></script>
        <script src="lib/bootstrap/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                var notif = document.getElementById("loggedout");
                console.log(notif)
                if (notif.value === 'true') {
                    alert("Successfully logged out !")
                }
            });
        </script>
    </body>

</html>
