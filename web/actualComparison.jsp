<%@include file="security.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--
comparison page add current vs historical details(past 2yrs)
^can tell improvement or not


--%>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>SRA | Home</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">

        <link rel="stylesheet" href="plugins/select2/select2.min.css">



    </head>
    <body class="hold-transition skin-blue sidebar-mini">

        <div class="wrapper">

            <div class="content-wrapper">
                <section class="content-header">

                    <h1>
                        Farmer Comparison
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="col-md-4">
                                <h3 class="text-center text-green">High</h3>
                            </div>
                            <div class="col-md-4">
                                <h3 class="text-center text-blue">Current</h3>
                            </div>
                            <div class="col-md-4">
                                <h3 class="text-center text-red">Low</h3>
                            </div>
                        </div>
<form id="frm-FarmDiff" action="viewSendRec">

                        <div class="col-md-4">

                            <c:if test="${not empty flist}">
                                <c:forEach var="flow" items="${flist}" varStatus="loop">
                                    <c:if test="${ flow.yield >= farm.yield }">

                                        <div class="box box-success">
                                            <div class="box-header">
                                                <input type="checkbox" style="width: 16; height: 16" value="${flow.id}" name="farmid[]" class="msgCheckbox pull-left">
                                                <h4 class="text-center"><b><c:out value="${flow.id}"/></b></h4>

                                                <div class="box-tools pull-right">
                                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                                    </button>
                                                </div>

                                            </div>
                                            <!-- /.box-header -->
                                            <div class="box-body">
                                                <ul class="nav nav-tabs">
                                                    <li>  </li>
                                                    <li class="active"><a href=".tab_${flow.id}${loop.index}" data-toggle="tab"><b>Details</b></a></li>
                                                    <li><a href=".tab_${flow.id}${loop.index+1}" data-toggle="tab"><b>Recommendations</b></a></li>
                                                    <li><a href=".tab_${flow.id}${loop.index+2}" data-toggle="tab"><b>Problems </b></a></li>

                                                </ul>

                                                <div class="tab-content">
                                                    <div class="tab-pane active tab_${flow.id}${loop.index}" >
                                                        <div class="box-body box-profile">
                                                            <ul class="list-group list-group-unbordered">
                                                                <li class="list-group-item">
                                                                    <b>Farmer</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.farmer !=farm.farmer}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.farmer}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.farmer}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Location</b> <b class="pull-right text-blue">
                                                                        <c:out value="${flow.barangay}"/> ,  <c:out value="${farm.municipality}"/>
                                                                    </b>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Production(TC)</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.production !=farm.production}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.production}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.production}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Total Area</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.area !=farm.area}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.area}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.area}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>

                                                                <li class="list-group-item">
                                                                    <b>Average Yield(tc/ha)</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.yield !=farm.yield}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.yield}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.yield}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Crop Variety</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.variety !=farm.cropVal.variety}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.variety}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.variety}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Crop Class</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.crop_class !=farm.cropVal.crop_class}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.crop_class}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.crop_class}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Texture</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.texture !=farm.cropVal.texture}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.texture}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.texture}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Farming System</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.farming_system !=farm.cropVal.farming_system}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.farming_system}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.farming_system}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>

                                                                <li class="list-group-item">
                                                                    <b>Topography</b>
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.topography !=farm.cropVal.topography}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.topography}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.topography}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Furrow Distance</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.furrow_distance !=farm.cropVal.furrow_distance}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.furrow_distance}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.furrow_distance}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Planting Density</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.planting_density !=farm.cropVal.planting_density}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.planting_density}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.planting_density}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Planting Date???</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.planting_date !=farm.cropVal.planting_date}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.planting_date}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.planting_date}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Harvest Date</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.harvest_date !=farm.cropVal.harvest_date}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.harvest_date}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.harvest_date}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Date Millable</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.date_millable !=farm.cropVal.date_millable}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.date_millable}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.date_millable}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Number Millable</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.num_millable !=farm.cropVal.num_millable}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.num_millable}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.num_millable}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Average Millable Stool</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.avg_millable_stool !=farm.cropVal.avg_millable_stool}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.avg_millable_stool}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.avg_millable_stool}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Brix</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.brix !=farm.cropVal.brix}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.brix}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.brix}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Stalk Length</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.stalk_length !=farm.cropVal.stalk_length}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.stalk_length}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.stalk_length}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Diameter</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.diameter !=farm.cropVal.diameter}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.diameter}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.diameter}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Weight</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.weight !=farm.cropVal.weight}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.weight}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.weight}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>PH Level</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.soilanalysis.ph_lvl !=farm.soilanalysis.ph_lvl}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.soilanalysis.ph_lvl}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.soilanalysis.ph_lvl}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>

                                                                <li class="list-group-item">
                                                                    <b>Organic Matter</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.soilanalysis.organic_matter !=farm.soilanalysis.organic_matter}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.soilanalysis.organic_matter}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.soilanalysis.organic_matter}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Phosphorus</b>
                                                                    <c:choose>
                                                                        <c:when test='${flow.soilanalysis.phosphorus !=farm.soilanalysis.phosphorus}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.soilanalysis.phosphorus}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.soilanalysis.phosphorus}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>

                                                                <li class="list-group-item">
                                                                    <b>Potassium</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.soilanalysis.potassium !=farm.soilanalysis.potassium}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.soilanalysis.potassium}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.soilanalysis.potassium}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                            </ul>
                                                          <a href="viewFieldDetails?id=${flow.id}" class="btn btn-primary pull-right">
                                                            View Farm Profile
                                                         </a>     
                                                        </div>
                                                    </div>
                                                    <!-- /.tab-pane -->
                                                    <div class="tab-pane tab_${flow.id}${loop.index+1}" id="tab_8">
                                                        <h3 class="profile-username text-center"></h3>
                                                        <table class="table table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>Recommendation</th>
                                                                    <th>Type</th>
                                                                    <th>more info</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                                <c:if test="${not empty flow.recommendation}">
                                                                    <c:forEach var="rect" items="${flow.recommendation}" varStatus="loop">
                                                                        <tr>

                                                                            <td><c:out value="${rect.recommendation_name}"/></td>
                                                                            <td><c:out value="${rect.type}"/></td>
                                                                            <td><c:out value="${rect.id}"/></td>
                                                                        </tr>

                                                                    </c:forEach>
                                                                </c:if>



                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <!-- /.tab-pane -->
                                                    <div class="tab-pane tab_${flow.id}${loop.index+2}" id="tab_9">
                                                        <table class="table table-hover">
                                                            <thead><tr>
                                                                    <th>Problem</th>
                                                                      <th>Type</th>
                                                                    <th>More Info</th>
                                                                </tr>
                                                                </thead>
                                                            <tbody>

                                                                <c:if test="${not empty flow.problems}">
                                                                    <c:forEach var="probt" items="${flow.problems}" varStatus="loop">
                                                                        <tr>

                                                                            <td><c:out value="${probt.prob_name}"/></td>
                                                                            <td><c:out value="${probt.type}"/></td>
                                                                            <td><c:out value="${probt.prob_id}"/></td>
                                                                        </tr>

                                                                    </c:forEach>
                                                                </c:if>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <!-- /.tab-pane -->
                                                </div>
                                                <!-- /.tab-content -->


                                            </div>

                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                            <!-- nav-tabs-custom -->


                        </div>

                        <div class="col-md-4">
                            <c:if test="${not empty farm}">
                                <div class="box box-primary">
                                    <div class="box-header">
                                       <input type="checkbox" style="width: 16; height: 16" value="${farm.id}" name="farmid[]" class="msgCheckbox pull-left">
                                        <h4 class="text-center"><b><c:out value="${farm.id}"/></b></h4>

                                        <div class="box-tools pull-right">
                                            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                            </button>
                                        </div>

                                    </div>
                                    <!-- /.box-header -->
                                    <div class="box-body">
                                        <ul class="nav nav-tabs">
                                            <li>  </li>
                                            <li class="active"><a href="#tab_1" data-toggle="tab"><b>Details</b></a></li>
                                            <li><a href="#tab_2" data-toggle="tab"><b>Recommendations</b></a></li>
                                            <li><a href="#tab_3" data-toggle="tab"><b>Problems </b></a></li>

                                        </ul>

                                        <div class="tab-content">
                                            <div class="tab-pane active" id="tab_1">
                                                <div class="box-body box-profile">
                                                    <ul class="list-group list-group-unbordered">
                                                        <li class="list-group-item">
                                                            <b>Farmer</b> <b class="pull-right text-blue">
                                                                <c:out value="${farm.farmer}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Location</b> <b class="pull-right text-blue">
                                                                <c:out value="${farm.barangay}"/> ,  <c:out value="${farm.municipality}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Production(TC)</b> <b class="pull-right text-blue">
                                                                <c:out value="${farm.production}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Total Area</b> <b class="pull-right text-blue">
                                                                <c:out value="${farm.area}"/>
                                                            </b>
                                                        </li>

                                                        <li class="list-group-item">
                                                            <b>Average Yield(tc/ha)</b> <b class="pull-right text-blue">
                                                                <c:out value="${farm.yield}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Crop Variety</b> <b class="pull-right text-blue">
                                                                <c:out value="${farm.cropVal.variety}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Crop Class</b> <b class="pull-right text-blue">
                                                                <c:out value="${farm.cropVal.crop_class}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Texture</b> <b class="pull-right text-yellow">
                                                                <c:out value="${farm.cropVal.texture}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Farming System</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.farming_system}"/> 
                                                            </b>
                                                        </li>

                                                        <li class="list-group-item">
                                                            <b>Topography</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.topography}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Furrow Distance</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.furrow_distance}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Planting Density</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.planting_density}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Planting Date???</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.planting_date}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Harvest Date</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.harvest_date}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Date Millable</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.date_millable}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Number Millable</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.num_millable}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Average Millable Stool</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.avg_millable_stool}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Brix</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.brix}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Stalk Length</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.stalk_length}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Diameter</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.diameter}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Weight</b> <b class="pull-right">
                                                                <c:out value="${farm.cropVal.weight}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>PH Level</b> <b class="pull-right">
                                                                <c:out value="${farm.soilanalysis.ph_lvl}"/></b>
                                                        </li>

                                                        <li class="list-group-item">
                                                            <b>Organic Matter</b> <b class="pull-right">
                                                                <c:out value="${farm.soilanalysis.organic_matter}"/>
                                                            </b>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <b>Phosphorus</b> <b class="pull-right">
                                                                <c:out value="${farm.soilanalysis.phosphorus}"/>
                                                            </b>
                                                        </li>

                                                        <li class="list-group-item">
                                                            <b>Potassium</b> <b class="pull-right">
                                                                <c:out value="${farm.soilanalysis.potassium}"/>
                                                            </b>
                                                        </li>
                                                    </ul>
                                                   <a href="viewFieldDetails?id=${farm.id}" class="btn btn-primary pull-right">
                                                            View Farm Profile
                                                         </a>   
                                                </div>
                                            </div>
                                            <!-- /.tab-pane -->
                                            <div class="tab-pane" id="tab_2">
                                                <h3 class="profile-username text-center"></h3>
                                                <table class="table table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>Recommendation</th>
                                                                    <th>Type</th>
                                                                    <th>more info</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                                <c:if test="${not empty flow.recommendation}">
                                                                    <c:forEach var="rect" items="${flow.recommendation}" varStatus="loop">
                                                                        <tr>

                                                                            <td><c:out value="${rect.recommendation_name}"/></td>
                                                                            <td><c:out value="${rect.type}"/></td>
                                                                            <td><c:out value="${rect.id}"/></td>
                                                                        </tr>

                                                                    </c:forEach>
                                                                </c:if>



                                                            </tbody>
                                                        </table>
                                            </div>
                                            <!-- /.tab-pane -->
                                            <div class="tab-pane" id="tab_3">
                                                <table class="table table-hover">
                                                            <thead><tr>
                                                                    <th>Problem</th>
                                                                      <th>Type</th>
                                                                    <th>More Info</th>
                                                                </tr>
                                                                </thead>
                                                            <tbody>

                                                                <c:if test="${not empty flow.problems}">
                                                                    <c:forEach var="probt" items="${flow.problems}" varStatus="loop">
                                                                        <tr>

                                                                            <td><c:out value="${probt.prob_name}"/></td>
                                                                            <td><c:out value="${probt.type}"/></td>
                                                                            <td><c:out value="${probt.prob_id}"/></td>
                                                                        </tr>

                                                                    </c:forEach>
                                                                </c:if>

                                                            </tbody>
                                                        </table>
                                            </div>
                                            <!-- /.tab-pane -->
                                        </div>
                                        <!-- /.tab-content -->


                                    </div>

                                </div>


                            </c:if>
                            <!-- nav-tabs-custom -->
                        </div>

                        <div class="col-md-4">
                            <c:if test="${not empty flist}">
                                <c:forEach var="flow" items="${flist}" varStatus="loop">
                                    <c:if test="${ flow.yield < farm.yield }">

                                        <div class="box box-danger">
                                            <div class="box-header">
                                                <input type="checkbox" style="width: 16; height: 16" value="${flow.id}" name="farmid[]" class="msgCheckbox pull-left">
                                                <h4 class="text-center"><b><c:out value="${flow.id}"/></b></h4>

                                                <div class="box-tools pull-right">
                                                    <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                                    </button>
                                                </div>

                                            </div>
                                            <!-- /.box-header -->
                                            <div class="box-body">
                                                <ul class="nav nav-tabs">
                                                    <li>  </li>
                                                    <li class="active"><a href=".tab_${flow.id}${loop.index}" data-toggle="tab"><b>Details</b></a></li>
                                                    <li><a href=".tab_${flow.id}${loop.index+1}" data-toggle="tab"><b>Recommendations</b></a></li>
                                                    <li><a href=".tab_${flow.id}${loop.index+2}" data-toggle="tab"><b>Problems </b></a></li>

                                                </ul>

                                                <div class="tab-content">
                                                    <div class="tab-pane active tab_${flow.id}${loop.index}" >
                                                        <div class="box-body box-profile">
                                                            <ul class="list-group list-group-unbordered">
                                                                <li class="list-group-item">
                                                                    <b>Farmer</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.farmer !=farm.farmer}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.farmer}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.farmer}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Location</b> <b class="pull-right text-blue">
                                                                        <c:out value="${flow.barangay}"/> ,  <c:out value="${farm.municipality}"/>
                                                                    </b>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Production(TC)</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.production !=farm.production}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.production}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.production}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Total Area</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.area !=farm.area}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.area}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.area}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>

                                                                <li class="list-group-item">
                                                                    <b>Average Yield(tc/ha)</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.yield !=farm.yield}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.yield}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.yield}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Crop Variety</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.variety !=farm.cropVal.variety}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.variety}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.variety}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Crop Class</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.crop_class !=farm.cropVal.crop_class}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.crop_class}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.crop_class}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Texture</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.texture !=farm.cropVal.texture}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.texture}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.texture}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Farming System</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.farming_system !=farm.cropVal.farming_system}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.farming_system}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.farming_system}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>

                                                                <li class="list-group-item">
                                                                    <b>Topography</b>
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.topography !=farm.cropVal.topography}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.topography}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.topography}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Furrow Distance</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.furrow_distance !=farm.cropVal.furrow_distance}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.furrow_distance}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.furrow_distance}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Planting Density</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.planting_density !=farm.cropVal.planting_density}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.planting_density}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.planting_density}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Planting Date???</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.planting_date !=farm.cropVal.planting_date}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.planting_date}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.planting_date}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Harvest Date</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.harvest_date !=farm.cropVal.harvest_date}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.harvest_date}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.harvest_date}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Date Millable</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.date_millable !=farm.cropVal.date_millable}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.date_millable}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.date_millable}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Number Millable</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.num_millable !=farm.cropVal.num_millable}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.num_millable}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.num_millable}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Average Millable Stool</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.avg_millable_stool !=farm.cropVal.avg_millable_stool}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.avg_millable_stool}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.avg_millable_stool}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Brix</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.brix !=farm.cropVal.brix}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.brix}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.brix}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Stalk Length</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.stalk_length !=farm.cropVal.stalk_length}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.stalk_length}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.stalk_length}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Diameter</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.diameter !=farm.cropVal.diameter}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.diameter}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.diameter}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Weight</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.cropVal.weight !=farm.cropVal.weight}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.cropVal.weight}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.cropVal.weight}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>PH Level</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.soilanalysis.ph_lvl !=farm.soilanalysis.ph_lvl}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.soilanalysis.ph_lvl}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.soilanalysis.ph_lvl}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>

                                                                <li class="list-group-item">
                                                                    <b>Organic Matter</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.soilanalysis.organic_matter !=farm.soilanalysis.organic_matter}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.soilanalysis.organic_matter}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.soilanalysis.organic_matter}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                                <li class="list-group-item">
                                                                    <b>Phosphorus</b>
                                                                    <c:choose>
                                                                        <c:when test='${flow.soilanalysis.phosphorus !=farm.soilanalysis.phosphorus}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.soilanalysis.phosphorus}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.soilanalysis.phosphorus}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>

                                                                <li class="list-group-item">
                                                                    <b>Potassium</b> 
                                                                    <c:choose>
                                                                        <c:when test='${flow.soilanalysis.potassium !=farm.soilanalysis.potassium}'>
                                                                            <b class="pull-right text-red"><c:out value="${flow.soilanalysis.potassium}"></c:out> </b>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <b class="pull-right text-green"><c:out value="${flow.soilanalysis.potassium}"></c:out>  </b>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </li>
                                                            </ul>
                                                           <a href="viewFieldDetails?id=${farm.id}" class="btn btn-primary pull-right">
                                                            View Farm Profile
                                                         </a>   
                                                        </div>
                                                    </div>
                                                    <!-- /.tab-pane -->
                                                    <div class="tab-pane tab_${flow.id}${loop.index+1}" id="tab_8">
                                                        <h3 class="profile-username text-center"></h3>
                                                        <table class="table table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>Recommendation</th>
                                                                    <th>Type</th>
                                                                    <th>more info</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>

                                                                <c:if test="${not empty flow.recommendation}">
                                                                    <c:forEach var="rect" items="${flow.recommendation}" varStatus="loop">
                                                                        <tr>

                                                                            <td><c:out value="${rect.recommendation_name}"/></td>
                                                                            <td><c:out value="${rect.type}"/></td>
                                                                            <td><c:out value="${rect.id}"/></td>
                                                                        </tr>

                                                                    </c:forEach>
                                                                </c:if>



                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <!-- /.tab-pane -->
                                                    <div class="tab-pane tab_${flow.id}${loop.index+2}" id="tab_9">
                                                        <table class="table table-hover">
                                                            <thead><tr>
                                                                    <th>Problem</th>
                                                                      <th>Type</th>
                                                                    <th>More Info</th>
                                                                </tr>
                                                                </thead>
                                                            <tbody>

                                                                <c:if test="${not empty flow.problems}">
                                                                    <c:forEach var="probt" items="${flow.problems}" varStatus="loop">
                                                                        <tr>

                                                                            <td><c:out value="${probt.prob_name}"/></td>
                                                                            <td><c:out value="${probt.type}"/></td>
                                                                            <td><c:out value="${probt.prob_id}"/></td>
                                                                        </tr>

                                                                    </c:forEach>
                                                                </c:if>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <!-- /.tab-pane -->
                                                </div>
                                                <!-- /.tab-content -->


                                            </div>

                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:if>


                            <!-- nav-tabs-custom -->
                        </div>





                        <div class="col-md-7 text-center">
                            <div class="box box-danger">
                                <div class="box-header">
                                    <h3 class="box-title">Action Tools</h3>
                                </div>
                                <div class="box-body">
                                    <a class="btn btn-app btn-linkedin">
                                        <i class="fa fa-edit"></i> Create Survey
                                    </a>
                                    <a class="btn btn-app btn-adn">
                                        <i class="fa fa-bank"></i> Create Recommendation
                                    </a>
                                  <button class="btn btn-app btn-linkedin" id="sButton" value="submit">
                                        <i class="fa fa-edit" ></i> Send Recommendations
                                    </button>

                                    <a class="btn btn-app btn-soundcloud" href="determineProblem.jsp">

                                        <i class="fa fa-inbox"></i> Determine Problem
                                    </a>


                                </div>
                            
                                <!-- /.box-body -->
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
        <script src="plugins/select2/select2.full.min.js"></script>
        <script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>

        <script src="dist/js/app.min.js"></script>

        <script type="text/javascript">
            $(function () {
                $(".select2").select2();
            });
        </script>
 <script type="text/javascript">
            $(function () {
                $('.box-profile').slimScroll({
                    height: '420px',
                    alwaysVisible: true
                });
            });
        </script>
        <script type="text/javascript">
          $("#sButton").on("click", function () {
           //var checkedValue = $('.msgCheckbox:checked').val();   
           
           var checkedValue = []; 
var inputElements = document.getElementsByClassName('msgCheckbox');
for(var i=0; inputElements[i]; ++i){
      if(inputElements[i].checked){
           checkedValue.push(inputElements[i].value) ;
            console.log(checkedValue);
     
      }
      
}
for(var b=0; b<checkedValue.length;b++){
    console.log(checkedValue[b]+"+"+b);
}


          
          });
        </script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>

        <script>

            //            $(document).ready(function () {
            //                var rows_selected = [];
            //
            //                var table = $('#example').DataTable({
            //                    'ajax': {
            //                        'url': 'viewBrgyList'
            //                    },
            //                    'columnDefs': [{
            //                            'targets': 0,
            //                            'searchable': false,
            //                            'orderable': false,
            //                            'className': 'dt-body-center',
            //                            'render': function (data, type, full, meta) {
            //                                return '<input type="checkbox" name="id[]" id="buttonClick" value="'
            //                                        + $('<div/>').text(data).html() + '">';
            //                            }
            //                        }],
            //                    'select': {
            //                        'style': 'multi'
            //                    },
            //                    'order': [[1, 'asc']]
            //                            //      ,
            //                            //       'rowCallback': function(row, data, dataIndex){
            //                            //         // Get row ID
            //                            //       var rowId = data[0];
            //                            //       // alert(rowId);
            //                            //         // If row ID is in the list of selected row IDs
            //                            //         if($.inArray(rowId, rows_selected) !== -1){
            //                            //            $(row).find('input[type="checkbox"]').prop('checked', true);
            //                            //            $(row).addClass('selected');
            //                            //         }
            //                            //      }     
            //
            //                });
            //
            //                $('#frm-example').on('submit', function (e) {
            //                    var form = this;
            //
            //                    // Iterate over all checkboxes in the table
            //                    table.$('input[type="checkbox"]').each(function () {
            //                        // If checkbox doesn't exist in DOM
            //                        if (!$.contains(document, this)) {
            //                            // If checkbox is checked
            //                            if (this.checked) {
            //                                // Create a hidden element 
            //                                $(form).append(
            //                                        $('<input>')
            //                                        .attr('type', 'hidden')
            //                                        .attr('name', this.name)
            //                                        .val(this.value)
            //                                        );
            //                            }
            //                        }
            //                    });
            //                });
            //            });


        </script>
    </body>

</html>