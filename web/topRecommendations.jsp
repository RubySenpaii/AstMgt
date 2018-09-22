<%-- 
    Document   : topTenRecommendations
    Created on : 02 27, 17, 11:04:05 PM
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
        <style type="text/css" media="print">
            @media print {
                a[href]:after {
                    content: none !important;
                }
            }
            img
            {
                display:none;
            }  
            .hidethis
            {
                display:none;
            }
        </style>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        Yearly Improvement Report
                    </h1>
                </section>
                <section class="content">
                      <div class="col-md-6" >
                    <c:forEach var="cat" items="${cat}">
                        <div class="col-md-12" > 
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Top Problems (${cat.phase}) </h1>
                                    <div class="box-tools pull-right hidethis">
                                        <a tabindex="0" class="text-overflow popTP" id="popTP" role="button"><i class="fa fa-question text-orange "></i></a>
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <!-- In box-tools add this button if you intend to use the contacts pane -->
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="box-body no-padding">
                                    <table class="table table-bordered" >
                                        <tbody>
                                            <tr>
                                                <th>Problem Name</th>
                                                <th>Type</th>
                                                <th>Solution</th>
                                                <th>Count</th>
                                            </tr>
                                            <c:forEach var="prob" items="${pro}">
                                                <tr>
                                                    <c:choose>
                                                        <c:when test='${prob.phase == "Germination" && cat.phase == "Germination"}'>
                                                            <td>${prob.prob_name}</td>
                                                            <td>${prob.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty prob.reclist}'>
                                                                    <td>
                                                                        <c:forEach items="${prob.reclist}" var="reclist">
                                                                            <a href="viewRecDetails?id=${reclist.id}" target="_blank">${reclist.recommendation_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${prob.count}</td>
                                                        </c:when>
                                                        <c:when test='${prob.phase == "Planting" && cat.phase == "Planting"}'>
                                                            <td>${prob.prob_name}</td>
                                                            <td>${prob.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty prob.reclist}'>
                                                                    <td>
                                                                        <c:forEach items="${prob.reclist}" var="reclist">
                                                                            <a href="viewRecDetails?id=${reclist.id}" target="_blank">${reclist.recommendation_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${prob.count}</td>
                                                        </c:when>
                                                        <c:when test='${prob.phase == "Tillering" && cat.phase == "Tillering"}'>
                                                            <td>${prob.prob_name}</td>
                                                            <td>${prob.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty prob.reclist}'>
                                                                    <td>
                                                                        <c:forEach items="${prob.reclist}" var="reclist">
                                                                            <a href="viewRecDetails?id=${reclist.id}" target="_blank">${reclist.recommendation_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${prob.count}</td>

                                                        </c:when>
                                                        <c:when test='${prob.phase == "Stalk Elongation" && cat.phase == "Stalk Elongation"}'>
                                                            <td>${prob.prob_name}</td>
                                                            <td>${prob.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty prob.reclist}'>
                                                                    <td>
                                                                        <c:forEach items="${prob.reclist}" var="reclist">
                                                                            <a href="viewRecDetails?id=${reclist.id}" target="_blank">${reclist.recommendation_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${prob.count}</td>
                                                        </c:when>
                                                        <c:when test='${prob.phase == "Yield Formation" && cat.phase == "Yield Formation"}'>
                                                            <td>${prob.prob_name}</td>
                                                            <td>${prob.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty prob.reclist}'>
                                                                    <td>
                                                                        <c:forEach items="${prob.reclist}" var="reclist">
                                                                            <a href="viewRecDetails?id=${reclist.id}" target="_blank">${reclist.recommendation_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${prob.count}</td>
                                                        </c:when>
                                                        <c:when test='${prob.phase == "Ripening" && cat.phase == "Ripening"}'>
                                                            <td>${prob.prob_name}</td>
                                                            <td>${prob.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty prob.reclist}'>
                                                                    <td>
                                                                        <c:forEach items="${prob.reclist}" var="reclist">
                                                                            <a href="viewRecDetails?id=${reclist.id}" target="_blank">${reclist.recommendation_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${prob.count}</td>
                                                        </c:when>
                                                        <c:when test='${prob.phase == "Milling" && cat.phase == "Milling"}'>
                                                            <td>${prob.prob_name}</td>
                                                            <td>${prob.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty prob.reclist}'>
                                                                    <td>
                                                                        <c:forEach items="${prob.reclist}" var="reclist">
                                                                            <a href="viewRecDetails?id=${reclist.id}" target="_blank">${reclist.recommendation_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${prob.count}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>   
                        </div>
                       
                    </c:forEach>
                      </div>
                      <div class="col-md-6" >
                    <c:forEach var="cats" items="${cat}">
                        <div class="col-md-12" > 
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Top Recommendations (${cats.phase})</h1>
                                    <div class="box-tools pull-right hidethis">
                                        <a tabindex="0" class="text-overflow popTR" id="popTR" role="button"><i class="fa fa-question text-orange"></i></a>

                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <!-- In box-tools add this button if you intend to use the contacts pane -->
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>

                                <div class="box-body no-padding">
                                    <table class="table table-bordered" >
                                        <tbody>
                                            <tr>
                                                <th>Recommendation Name</th>
                                                <th>Type</th>
                                                <th>Problems Solved</th>
                                                <th>Count</th>
                                            </tr>
                                            <c:forEach var="recs" items="${rec}">
                                                <tr>
                                                    <c:choose>
                                                        <c:when test='${recs.phase == "Germination" && cats.phase == "Germination"}'>
                                                            <td>${recs.recommendation_name}</td>
                                                            <td>${recs.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty recs.problist}'>
                                                                    <td>
                                                                        <c:forEach items="${recs.problist}" var="problist">
                                                                            <a href="viewProbDetails?id=${problist.prob_id}" target="_blank">${problist.prob_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${recs.counter}</td>
                                                        </c:when>
                                                            <c:when test='${recs.phase == "Planting" && cats.phase == "Planting"}'>
                                                            <td>${recs.recommendation_name}</td>
                                                            <td>${recs.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty recs.problist}'>
                                                                    <td>
                                                                        <c:forEach items="${recs.problist}" var="problist">
                                                                            <a href="viewProbDetails?id=${problist.prob_id}" target="_blank">${problist.prob_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${recs.counter}</td>
                                                        </c:when>
                                                            <c:when test='${recs.phase == "Tillering" && cats.phase == "Tillering"}'>
                                                            <td>${recs.recommendation_name}</td>
                                                            <td>${recs.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty recs.problist}'>
                                                                    <td>
                                                                        <c:forEach items="${recs.problist}" var="problist">
                                                                            <a href="viewProbDetails?id=${problist.prob_id}" target="_blank">${problist.prob_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${recs.counter}</td>
                                                        </c:when>
                                                            <c:when test='${recs.phase == "Stalk Elongation" && cats.phase == "Stalk Elongation"}'>
                                                            <td>${recs.recommendation_name}</td>
                                                            <td>${recs.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty recs.problist}'>
                                                                    <td>
                                                                        <c:forEach items="${recs.problist}" var="problist">
                                                                            <a href="viewProbDetails?id=${problist.prob_id}" target="_blank">${problist.prob_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${recs.counter}</td>
                                                        </c:when>
                                                            <c:when test='${recs.phase == "Yield Formation" && cats.phase == "Yield Formation"}'>
                                                            <td>${recs.recommendation_name}</td>
                                                            <td>${recs.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty recs.problist}'>
                                                                    <td>
                                                                        <c:forEach items="${recs.problist}" var="problist">
                                                                            <a href="viewProbDetails?id=${problist.prob_id}" target="_blank">${problist.prob_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${recs.counter}</td>
                                                        </c:when>
                                                            <c:when test='${recs.phase == "Ripening" && cats.phase == "Ripening"}'>
                                                            <td>${recs.recommendation_name}</td>
                                                            <td>${recs.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty recs.problist}'>
                                                                    <td>
                                                                        <c:forEach items="${recs.problist}" var="problist">
                                                                            <a href="viewProbDetails?id=${problist.prob_id}" target="_blank">${problist.prob_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${recs.counter}</td>
                                                        </c:when>
                                                            <c:when test='${recs.phase == "Milling" && cats.phase == "Milling"}'>
                                                            <td>${recs.recommendation_name}</td>
                                                            <td>${recs.type}</td>
                                                            <c:choose>
                                                                <c:when test='${not empty recs.problist}'>
                                                                    <td>
                                                                        <c:forEach items="${recs.problist}" var="problist">
                                                                            <a href="viewProbDetails?id=${problist.prob_id}" target="_blank">${problist.prob_name}</a><br></br>
                                                                        </c:forEach>
                                                                    </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td>${recs.counter}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </tr>
                                            </c:forEach>
                                               
                                        </tbody>
                                    </table>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                      </div>
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4 hidethis">
                            <a  class="btn btn-primary btn-block" role="button" onClick="window.print();" ><i class="fa fa-print"></i> Print Report</a>
                            <!--                                <button id="cmd" type="button">print PDF</button>-->
                            <br>
                        </div>

                    </div>
                </section>

            </div>

        </div>
        <script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="dist/js/app.min.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
                                $(document).ready(function () {
                                    $('.popTP').popover(popTopProb);
                                    $('.popTR').popover(popTopRec);
                                });
        </script>
    </body>
</html>
