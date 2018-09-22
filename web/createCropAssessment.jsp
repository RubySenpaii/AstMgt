<%@include file="security.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>SRA | Home</title>
        <!--<link href="plugins/pace2/pace-theme-barber-shop.css" rel="stylesheet" />-->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

        <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css"> 
        <link rel="stylesheet" href="plugins/select2/select2.min.css">
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

                </section>
                <section class="content">
                    <div class="row" >
                        <h3 class="text-center text-bold">CROP ASSESSMENT REPORT</h3>
                        <h3 class="text-center text-bold">Crop Year: ${cayear}</h3>

                        <h3 >&nbsp  Week Ending: ${SundayofWeek}</h3>

                        <h3 class="text-bold">&nbsp PART 1:PRODUCTION STATUS DATA</h3>
                        <c:if test="${not empty CropAss}">
                            <div class="col-md-12"  > 
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title ">Area Harvested</h1>
                                        <div class="box-tools pull-right hidethis">
                                            <a tabindex="0" class="text-overflow" id="popAreaHarv" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body no-padding">
                                        <table class="table table-bordered"  >
                                            <thead>

                                                <tr >
                                                    <th class="text-center" rowspan="2">Particulars</th>
                                                    <th class="text-center" colspan="3">Estimated Production</th>
                                                    <th style="width:15%" class="text-center"  rowspan="2">Percent Completed</th>	
                                                </tr>
                                                <tr>
                                                    <th class="text-center">Previous</th>
                                                    <th class="text-center">This Week</th>
                                                    <th class="text-center">To Date</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="ca" items="${CropAss}">
                                                    <tr>	
                                                        <td >${ca.particulars}</td>
                                                        <td>${ca.estimated}</td>
                                                        <td>${ca.previous}</td>
                                                        <td>${ca.thisweek}</td>
                                                        <td>${ca.todate}</td>
                                                        <td>
                                                            <div class="progress-group" >
                                                                <span class="progress-number">
                                                                    <b>
                                                                        ${ca.percent}%
                                                                    </b>
                                                                </span>
                                                                <span>
                                                                    <div id="bypassme" class="progress progress-sm progress-striped-active">
                                                                        <div class="progress-bar progress-bar-primary" style="width : ${ca.percent}%"></div>
                                                                    </div>
                                                                </span>
                                                            </div>



                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-4"  >
                                <div class="box box-info">
                                    <div class="box-header with-border">
                                        <h1 class="box-title">Standing Crop</h1>
                                        <div class="box-tools pull-right hidethis">
                                            <a tabindex="0" class="text-overflow" id="popStandCrop" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                            <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body no-padding">
                                        <table class="table table-bordered"  >
                                            <thead>
                                                <tr>
                                                    <th>Particulars</th>
                                                    <th>Estimated Production</th>	
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="ca" items="${CropAss}">
                                                    <tr>	
                                                        <td>${ca.particulars}</td>
                                                        <td>${ca.standing}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>

                            </div>
                        </c:if>
                        <div class="col-md-8">


                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Weather Update</h1>
                                    <div class="box-tools pull-right hidethis">
                                        <a tabindex="0" class="" id="popWeatherForecast" role="button"><i class="fa fa-question text-orange"></i></a>  
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                    </div>
                                </div>   
                                <div class="box-body no-padding" id="container1">
                                    <table class="table table-bordered" >
                                        <thead>

                                        <th class="text-center" colspan="7"> Rainfall Amount(mm) of the week </th>
                                        <tr>
                                            <c:forEach var="rain" items="${carain}">

                                                <th>${rain.dayname}</th>

                                            </c:forEach>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="r" items="${carain}">

                                            <td>${r.rainfall} mm</td>

                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                            </div>

                        </div>
                        <div class="col-md-12" id="improvement" >
                            <h3 class="text-bold">PART 2: DISTRICT IMPROVEMENT STATUS REPORT</h3>
                            <div class="col-md-12"  > 
                                <div class="box box-info">
                                    <div class="box-header">
                                        <h1 class="box-title">A. Farmer Performance</h1>
                                        <div class="box-tools pull-right hidethis">
                                            <a tabindex="0" class="text-overflow" id="popFarmPerf" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body no-padding">
                                        <table class="table table-bordered"  >
                                            <thead>

                                                <tr >
                                                    <th class="text-center" rowspan="2">Particulars</th>
                                                    <!--<th class="text-center" colspan="2">Improvements</th>-->
                                                    <c:forEach var="sr" items="${statusRep}" varStatus="status">
                                                        <th class="text-center" colspan="2">
                                                            <c:if test="${status.first}">   
                                                                <br> Last Week
                                                            </c:if>
                                                            <c:if test="${status.last}">   
                                                                <br> This Week
                                                            </c:if>
                                                        </th>

                                                    </c:forEach>
                                                    <th style="width:15%" class="text-center"  rowspan="2">Improvement</th>	
                                                </tr>
                                                <tr>
                                                    <c:forEach var="sr" items="${statusRep}" varStatus="status">
                                                        <th class="text-center">Name </th>
                                                        <th class="text-center"> TC/Yield </th>

                                                    </c:forEach>
                                                    <!--                                                <th class="text-center">JAN ____ TO JAN ____</th>
                                                                                                    <th class="text-center">THIS WEEK</th>-->

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td> Highest Producing Farmer</td>
                                                    <c:forEach var="sr" items="${statusRep}" varStatus="status">
                                                        <td> <a id="helloworld" href="viewFarmerProfile?name=${sr.highestProdFarmer.name}" >${sr.highestProdFarmer.name} </a></td>
                                                        <td>${sr.highestProdFarmer.production} TC</td>

                                                    </c:forEach>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${statusRep[0].highestProdFarmer.production<=statusRep[1].highestProdFarmer.production}">
                                                                <div>
                                                                    <span class="fa fa-arrow-up text-green">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[1].highestProdFarmer.production-statusRep[0].highestProdFarmer.production}"/>
                                                                    </span>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div>
                                                                    <span class="fa fa-arrow-down text-red">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[0].highestProdFarmer.production-statusRep[1].highestProdFarmer.production}" />
                                                                    </span>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose> 
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td> Lowest Producing Farmer</td>
                                                    <c:forEach var="sr" items="${statusRep}" varStatus="status">
                                                        <td><a href="viewFarmerProfile?name=${sr.lowestProdFarmer.name}" >${sr.lowestProdFarmer.name}</a> </td>
                                                        <td>${sr.lowestProdFarmer.production} TC</td>
                                                    </c:forEach>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${statusRep[0].lowestProdFarmer.production<=statusRep[1].lowestProdFarmer.production}">
                                                                <div>
                                                                    <span class="fa fa-arrow-up text-green">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[1].lowestProdFarmer.production-statusRep[0].lowestProdFarmer.production}"/>
                                                                    </span>
                                                                </div>
                                                            </c:when>

                                                            <c:otherwise>
                                                                <div>
                                                                    <span class="fa fa-arrow-down text-red">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[0].lowestProdFarmer.production-statusRep[1].lowestProdFarmer.production}" />
                                                                    </span>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td> Highest Yielding Farmer</td>
                                                    <c:forEach var="sr" items="${statusRep}" varStatus="status">
                                                        <td><a href="viewFarmerProfile?name=${sr.highestYieldFarmer.name}" >${sr.highestYieldFarmer.name}</a></td>
                                                        <td>${sr.highestYieldFarmer.tYield} Yield</td>
                                                    </c:forEach>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${statusRep[0].highestYieldFarmer.tYield<=statusRep[1].highestYieldFarmer.tYield}">
                                                                <div>
                                                                    <span class="fa fa-arrow-up text-green">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[1].highestYieldFarmer.tYield-statusRep[0].highestYieldFarmer.tYield}"/>
                                                                    </span>
                                                                </div>
                                                            </c:when>

                                                            <c:otherwise>
                                                                <div>
                                                                    <span class="fa fa-arrow-down text-red">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[0].highestYieldFarmer.tYield-statusRep[1].highestYieldFarmer.tYield}" />
                                                                    </span>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td> Lowest Yielding Farmer</td>
                                                    <c:forEach var="sr" items="${statusRep}" varStatus="status">
                                                        <td> <a href="viewFarmerProfile?name=${sr.lowestYieldFarmer.name}" >${sr.lowestYieldFarmer.name}</a></td>
                                                        <td>${sr.lowestYieldFarmer.tYield} Yield</td>
                                                    </c:forEach>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${statusRep[0].lowestYieldFarmer.tYield<=statusRep[1].lowestYieldFarmer.tYield}">
                                                                <div>
                                                                    <span class="fa fa-arrow-up text-green">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[1].lowestYieldFarmer.tYield-statusRep[0].lowestYieldFarmer.tYield}"/>
                                                                    </span>
                                                                </div>
                                                            </c:when>

                                                            <c:otherwise>
                                                                <div>
                                                                    <span class="fa fa-arrow-down text-red">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[0].lowestYieldFarmer.tYield-statusRep[1].lowestYieldFarmer.tYield}" />

                                                                    </span>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-12"  > 
                                <div class="box box-info">
                                    <div class="box-header">
                                        <h1 class="box-title">B. District Improvements</h1>
                                        <div class="box-tools pull-right hidethis">
                                            <a tabindex="0" class="text-overflow" id="popDistImp" role="button"><i class="fa fa-question text-orange"></i></a>
                                            <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        </div>
                                    </div>

                                    <div class="box-body no-padding">
                                        <table class="table table-bordered"  >
                                            <thead>

                                                <tr >
                                                    <th class="text-center" rowspan="2">Particulars</th>
                                                    <th class="text-center" colspan="2">Improvements</th>
                                                    <th style="width:15%" class="text-center"  rowspan="2">Improvement</th>	
                                                </tr>
                                                <tr>
                                                    <c:forEach var="sr" items="${statusRep}" varStatus="status">
                                                        <th class="text-center">

                                                            <c:if test="${status.first}">  
                                                                Previous Week
                                                            </c:if>
                                                            <c:if test="${status.last}">   
                                                                This Week
                                                            </c:if>
                                                        </th>

                                                    </c:forEach>
                                                    <!--                                                <th class="text-center">JAN ____ TO JAN ____</th>
                                                                                                    <th class="text-center">THIS WEEK</th>-->

                                                </tr>
                                            </thead>
                                            <tbody>

                                                <tr>
                                                    <td> Recommendations Suggested</td>
                                                    <c:forEach var="sr" items="${statusRep}" varStatus="status">
                                                        <td><a target="_blank" href="selectReportList?MondayofWeek=${sr.weekStarting}&SundayofWeek=${sr.weekEnding}&repType=rec&status=verifying">${sr.recsSuggested}</a></td>
                                                    </c:forEach>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${statusRep[0].recsSuggested<statusRep[1].recsSuggested}">
                                                                <div>
                                                                    <span class="fa fa-arrow-up text-green">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[1].recsSuggested-statusRep[0].recsSuggested}"/>
                                                                    </span>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div>
                                                                    <span class="fa fa-arrow-down text-red">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[0].recsSuggested-statusRep[1].recsSuggested}"/>
                                                                    </span>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td >  Recommendations Implemented</td>
                                                    <c:forEach var="sr" items="${statusRep}" varStatus="status">
                                                        <td> <a target="_blank" href="selectReportList?MondayofWeek=${sr.weekStarting}&SundayofWeek=${sr.weekEnding}&repType=rec&status=active">${sr.recsImplemented}</a></td>
                                                    </c:forEach>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${statusRep[0].recsImplemented<statusRep[1].recsImplemented}">
                                                                <div>
                                                                    <span class="fa fa-arrow-up text-green">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[1].recsImplemented-statusRep[0].recsImplemented}"/>
                                                                    </span>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div>
                                                                    <span class="fa fa-arrow-down text-red">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[0].recsImplemented-statusRep[1].recsImplemented}"/>
                                                                    </span>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td> Problems Reported</td>
                                                    <c:forEach var="sr" items="${statusRep}" varStatus="status">
                                                        <td><a target="_blank" href="selectReportList?MondayofWeek=${sr.weekStarting}&SundayofWeek=${sr.weekEnding}&repType=prob&status=active"> ${sr.probsReported}</a></td>
                                                    </c:forEach>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${statusRep[0].probsReported<statusRep[1].probsReported}">
                                                                <div>
                                                                    <span class="fa fa-arrow-up text-red">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[1].probsReported-statusRep[0].probsReported}"/>
                                                                    </span>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div>
                                                                    <span class="fa fa-arrow-down text-green">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[0].probsReported-statusRep[1].probsReported}"/>
                                                                    </span>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td> Problems Solved</td>
                                                    <c:forEach var="sr" items="${statusRep}" varStatus="status">
                                                        <td><a target="_blank" href="selectReportList?MondayofWeek=${sr.weekStarting}&SundayofWeek=${sr.weekEnding}&repType=prob&status=inactive">${sr.probsSolved}</a></td>
                                                    </c:forEach>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${statusRep[0].probsSolved<statusRep[1].probsSolved}">
                                                                <div>
                                                                    <span class="fa fa-arrow-up text-green">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[1].probsSolved-statusRep[0].probsSolved}"/>
                                                                    </span>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div>
                                                                    <span class="fa fa-arrow-down text-red">
                                                                        <fmt:formatNumber type="number" pattern="###.##" value="${statusRep[0].probsSolved-statusRep[1].probsSolved}"/>
                                                                    </span>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>

                        </div>
                        <form action="submitCA" method="POST">
                            <div class="col-md-12" id="content" >
                                <h3 class="text-bold">PART 3:NARRATIVE REPORT </h3>



                                <input name="SundayofWeek" value="${SundayofWeek}" type="hidden">
                                <input name="cropyear" value="${cayear}" type="hidden">
                                <div class="box box-info">   
                                    <div class="box-header">

                                        <h1 class="box-title">A. MDO Narrative</h1>


                                        <div class="box-tools pull-right hidethis">
                                            <a tabindex="0" class="text-overflow" id="popNarativeRep" role="button"><i class="fa fa-question text-orange"></i></a>

                                        </div>
                                    </div>
                                    <div class="box-body">


                                        <table class="table table-bordered">
                                            <tbody>

                                                <tr>
                                                    <th style="width:20%">WEATHER</th>
                                                    <td> <textarea style="resize:none" id="dweatr" class="form-control mdoform readonly" name="dweather" rows="2"  placeholder="Enter ..." >${MdoNarrative.dweather}</textarea></td>
                                                </tr>
                                                <tr>
                                                    <th>PRICE OF SUGAR</th>
                                                    <td> <textarea style="resize:none" class="form-control mdoform" name="dprice" rows="2"  placeholder="Enter ...">${MdoNarrative.dprice}</textarea></td>
                                                </tr>
                                                <tr>
                                                    <th>MILL OPERATION</th>
                                                    <td> <textarea style="resize:none" class="form-control mdoform" name="dmill" rows="2"  placeholder="Enter ...">${MdoNarrative.dmill}</textarea></td>
                                                </tr>
                                                <tr>
                                                    <th>PRICES OF INPUTS</th>
                                                    <td> <textarea style="resize:none" class="form-control mdoform" name="dinput" rows="2"  placeholder="Enter ...">${MdoNarrative.dinput}</textarea></td>
                                                </tr>
                                                <tr>
                                                    <th style="width:20%">FINDINGS (PROBLEMS & RECOMMENDATIONS)</th>
                                                    <td> <textarea style="resize:none" class="form-control mdoform" name="dother" rows="2"  placeholder="Enter ...">${MdoNarrative.dother}</textarea></td>
                                                </tr>
                                                <tr>
                                                    <th>OVERALL ANALYSIS</th>
                                                    <td> <textarea style="resize:none" class="form-control mdoform" name="danalysis" rows="2"  placeholder="Enter ...">${MdoNarrative.danalysis}</textarea></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <c:if test="${(user.group eq 'MDO' and not empty boardNarrative) or user.group eq 'Board'}">
                                    <div class="box box-info">   
                                        <div class="box-header">

                                            <h1 class="box-title">B. Board Narrative</h1>


                                            <div class="box-tools pull-right hidethis">
                                                <a tabindex="0" class="text-overflow" id="popBoardNarativeRep" role="button"><i class="fa fa-question text-orange"></i></a>

                                            </div>
                                        </div>
                                        <div class="box-body">


                                            <table class="table table-bordered">
                                                <tbody>


                                                    <tr>
                                                        <th style="width:20%">FINDINGS (PROBLEMS & SUGGESTIONS)</th>
                                                        <td> <textarea style="resize:none" class="form-control boardform" name="dbfindings" rows="2"  placeholder="Enter ...">${boardNarrative.dbfindings}</textarea></td>
                                                    </tr>
                                                    <tr>
                                                        <th>OVERALL ANALYSIS</th>
                                                        <td> <textarea style="resize:none" class="form-control boardform" name="dbanalysis" rows="2"  placeholder="Enter ...">${boardNarrative.dbanalysis}</textarea></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </c:if>
                            </div>

                            <div class="row">
                                <div class="col-md-4 col-md-offset-4 hidethis">
                                    <a  class="btn btn-primary btn-block" role="button" onClick="window.print();" ><i class="fa fa-print"></i> Print Report</a>
                                    <button class="btn btn-info btn-block" id="mdosubmit" type="submit" >Submit</button>

                                    <!--                                <button id="cmd" type="button">print PDF</button>-->
                                    <br>
                                </div>

                            </div>
                        </form>

                    </div>
                </section>

            </div>

            <footer class="main-footer">

                <div class="pull-right hidden-xs">
                    <b>Version</b> 2.3.3
                </div>
                <strong>Copyright &copy; 2014-2015 <a href="http://sra.com">Sugar Regulatory Association</a>.</strong>
            </footer>
        </div>


        <script type="text/javascript" src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="dist/js/app.min.js"></script>
        <script src="plugins/pace2/pace.min.js"></script>
        <script src="plugins/select2/select2.full.min.js"></script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>



        <script>
                                        $(document).ready(function () {

                                            if (${not empty MdoNarrative}) {
                                                $('.mdoform').prop('readonly', true);
                                                $('#mdosubmit').addClass('hidden');

                                            } else if (${user.group eq 'Board'}) {
                                                $('.mdoform').prop('readonly', true);
                                            }
                                            if (${not empty boardNarrative}) {
                                                $('.boardform').prop('readonly', true);

                                            } else if (${user.group eq 'Board'}) {
                                                $('#mdosubmit').removeClass('hidden');
                                            }


                                            $('#popAreaHarv').popover(popAreaHarv);
                                            $('#popStandCrop').popover(popStandCrop);
                                            $('#popWeatherForecast').popover(popWeatherForecast);
                                            $('#popNarativeRep').popover(popNarativeRep);
                                            $('#popFarmPerf').popover(popFarmPerf);
                                            $('#popDistImp').popover(popDistImp);


                                        });


        </script>
        <script>
            if (${not empty printca}) {
                window.onload = function () {
                    window.print();
                };
            }

        </script>

        <script src="Highcharts/highcharts.js"></script>
        <script src="Highcharts/modules/treemap.js"></script>
        <script src="Highcharts/highcharts-more.js"></script>
        <script src="Highcharts/modules/solid-gauge.js"></script>
    </body>

</html>
