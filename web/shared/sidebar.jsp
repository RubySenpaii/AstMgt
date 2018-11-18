<%-- 
    Document   : sidebar
    Created on : Oct 7, 2018, 4:09:47 PM
    Author     : RubySenpaii
--%>

<aside>
    <%
        String userRole = "admin";
        try {
            userRole = (String) session.getAttribute("UserLevel");
        }catch(Exception e){
            userRole = "admin";
        }
        String jspFile;
        switch (userRole) {
            case "admin":
                jspFile = "role/admin.jsp";
                break;
            case "inspector":
                jspFile = "role/inspector.jsp";
                break;
            case "divisionchief":
                jspFile = "role/divisionchief.jsp";
                break;
            default:
                jspFile = "role/admin.jsp";
                break;
        }
    %>
    <jsp:include page="<%=jspFile%>"></jsp:include>
</aside>
