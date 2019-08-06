<%-- 
    Document   : template
    Created on : Oct 7, 2018, 3:18:56 PM
    Author     : RubySenpaii
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Index</title>
        <jsp:include page="/shared/css.jsp"/>
    </head>

    <body>
        <section id="container">
            <jsp:include page="shared/header.jsp"/>
            <jsp:include page="shared/sidebar.jsp"/>
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <div class="row">
                        <%
                            Boolean isSaved = (Boolean) session.getAttribute("notif");
                        %>
                        <input type="hidden" id="notif" name="notif" value="<%= isSaved%>">
                        <%
                            session.removeAttribute("notif");
                        %>
                    </div>
                    <!-- /row -->
                </section>
            </section>
            <!--main content end-->
            <jsp:include page="shared/footer.jsp"/>
        </section>
    </body>
    <jsp:include page="shared/js.jsp"/>
    <script>
        var notif = document.getElementById("notif");
        if (notif.value === 'true') {
            alert("Successfully saved the expenditure limit !");
        } else if (notif.value === 'false') {
            alert("Failed to save the expenditure limit !");
        }
    </script> 
</html>
