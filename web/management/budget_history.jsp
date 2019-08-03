<%-- 
    Document   : budget_history
    Created on : 06 15, 19, 6:51:54 PM
    Author     : rubysenpaii
--%>

<%@page import="extra.SharedFormat"%>
<%@page import="report.BudgetHistory"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Asset Management - Plans</title>
        <jsp:include page="../shared/css.jsp"/>
    </head>

    <body>
        <section id="container">
            <jsp:include page="../shared/header.jsp"/>
            <jsp:include page="../shared/sidebar.jsp"/>
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-panel">
                                <h4>Budget History</h4>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Division</th>
                                            <th>Quarter</th>
                                            <th>Year</th>
                                            <th>Amount Budgeted</th>
                                            <th>Amount Spent</th>
                                            <th>Amount Remaining</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<BudgetHistory> history = (ArrayList<BudgetHistory>) session.getAttribute("budgetHistory");
                                            for (BudgetHistory budget : history) {
                                        %>
                                        <tr>
                                            <td><%=budget.getDivision()%></td>
                                            <td><%=budget.getQuarter()%></td>
                                            <td><%=budget.getYear()%></td>
                                            <td><%=SharedFormat.doubleToMoney(budget.getLimit())%></td>
                                            <td><%=SharedFormat.doubleToMoney(budget.getSpent())%></td>
                                            <td><%=SharedFormat.doubleToMoney(budget.getLimit() - budget.getSpent())%></td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </section>
            </section>
            <!--main content end-->
            <jsp:include page="../shared/footer.jsp"/>
        </section>
    </body>
    <jsp:include page="../shared/js.jsp"/>
</html>
