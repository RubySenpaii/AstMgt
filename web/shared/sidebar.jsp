<%-- 
    Document   : sidebar
    Created on : Oct 7, 2018, 4:09:47 PM
    Author     : RubySenpaii
--%>

<aside>
    <%
        String userRole = "";
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
            case "supplyofficer":
                jspFile = "role/supply_officer.jsp";
                break;
            case "staff":
                jspFile = "role/staff.jsp";
                break;
            case "finance":
                jspFile = "role/finance.jsp";
                break;
            default:
                jspFile = "role/admin.jsp";
                break;
        }
    %>
    <jsp:include page="<%=jspFile%>"></jsp:include>
</aside>
