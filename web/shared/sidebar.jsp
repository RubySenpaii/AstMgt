<%-- 
    Document   : sidebar
    Created on : Oct 7, 2018, 4:09:47 PM
    Author     : RubySenpaii
--%>

<aside>
    <%
        String userRole = "";
        String userDivision = "";
        try {
            userRole = (String) session.getAttribute("UserLevel");
            userDivision = (String) session.getAttribute("UserDivision");
        } catch (Exception e) {
            userRole = "admin";
            userDivision = "general";
        }
        String jspFile = "";
        switch (userRole.toLowerCase()) {
            case "admin":
                jspFile = "role/admin.jsp";
                break;
            case "inspector":
                jspFile = "role/inspector.jsp";
                break;
            case "division chief":
                if(userDivision.toLowerCase().equalsIgnoreCase("general")){
                    jspFile = "role/gsdchief.jsp";
                }else if (userDivision.toLowerCase().equalsIgnoreCase("management")){
                    jspFile = "role/mgtchief.jsp";
                }else{
                    jspFile = "role/divisionchief.jsp";
                }
                break;
            case "supply officer":
                jspFile = "role/supply_officer.jsp";
                break;
            case "staff":
                jspFile = "role/staff.jsp";
                break;
            case "finance":
                jspFile = "role/finance.jsp";
                break;
            case "storekeeper":
            case "custodian":
                jspFile = "role/storekeeper.jsp";
                break;
            default:
                jspFile = "role/admin.jsp";
                break;
        }
    %>
    <jsp:include page="<%=jspFile%>"></jsp:include>
</aside>

<script>
    console.log()
</script>