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


                        <div class="pull-right col-md-2">
                            <div class="box box-info">
                                <div class="box-header" style="height:4%">
                                    <h5 class="box-title">Legend:</h5>
                                   
                                </div>
                                <div class="box-body ">
                                    <table class="table table-hover table-bordered">
                                        <tbody>
                                            <tr><td>
                                                    <button type="button" class="btn btn-primary btn-flat"></button>
                                                </td>
                                                <td>Selected Farm</td> 
                                            </tr>
                                            <tr><td>
                                                    <button type="button" class="btn btn-success btn-flat"></button>
                                                </td>
                                                <td>Similar</td> 
                                            </tr>
                                            <tr><td>
                                                    <button type="button" class="btn btn-danger btn-flat"></button>
                                                </td>
                                                <td>Different</td> 
                                            </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-12">
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h3 class="box-title">Basic Details</h3>
                                     <div class="box-tools pull-right">
                                      <a tabindex="0" class="text-overflow" id="popFarmBasicDet" role="button"><i class="fa fa-question text-orange"></i></a>
                                         </div>
                                </div>
                                <div class="box-body">
                                    <table id="fieldtable" class="table table-hover table-responsive">
                                        <thead><tr>
                                                <th></th>
                                                <th><c:out value="${farm.id}"/></th>
                                                    <c:forEach var="flow" items="${flist}">

                                                    <th><c:out value="${flow.id}"/></th>
                                                    </c:forEach>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th>Farmer</th>
                                                <td class="text-blue"><c:out value="${farm.farmer}"></c:out> </td>

                                                <c:forEach var="flow" items="${flist}">
                                                    <c:choose>
                                                        <c:when test='${flow.farmer !=farm.farmer}'>
                                                            <td class="text-red"><c:out value="${flow.farmer}"></c:out> </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="text-green"><c:out value="${flow.farmer}"></c:out> </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th>Location</th>
                                                <td class="text-blue"><c:out value="${farm.barangay}"/>,  <c:out value="${farm.municipality}"/></td>
                                                <c:forEach var="flow" items="${flist}">
                                                    <c:choose>
                                                        <c:when test='${flow.municipality !=farm.municipality and flow.barangay!=farm.barangay}'>
                                                            <td class="text-red"><c:out value="${flow.barangay}"/>,  <c:out value="${farm.municipality}"/></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="text-green"><c:out value="${flow.barangay}"/>,  <c:out value="${farm.municipality}"/></td>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </tr>

                                            <tr>
                                                <th>Production(tc)</th>
                                                <td class="text-blue"><c:out value="${farm.production}"/></td>
                                                <c:forEach var="flow" items="${flist}">
                                                    <c:choose>
                                                        <c:when test='${flow.production !=farm.production}'>
                                                            <td class="text-red"><c:out value="${flow.production}"/></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="text-green"><c:out value="${flow.production}"/></td>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th>Harvest Area(ha)</th>
                                                <td class="text-blue"><c:out value="${farm.totalHa}"/></td>
                                                <c:forEach var="flow" items="${flist}">
                                                    <c:choose>
                                                        <c:when test='${flow.totalHa !=farm.totalHa}'>
                                                            <td class="text-red"><c:out value="${flow.totalHa}"/></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="text-green"><c:out value="${flow.totalHa}"/></td>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </tr>
                                            <tr>
                                                <th>Total Area(ha)</th>
                                                <td class="text-blue"><c:out value="${farm.area}"/></td>
                                                <c:forEach var="flow" items="${flist}">
                                                    <c:choose>
                                                        <c:when test='${flow.area !=farm.area}'>
                                                            <td class="text-red"><c:out value="${flow.area}"/></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="text-green"><c:out value="${flow.area}"/></td>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </tr>

                                            <tr>
                                                <th>Current Yield(tc/ha)</th>
                                                <td class="text-blue"><c:out value="${farm.yield}"/></td>
                                                <c:forEach var="flow" items="${flist}">
                                                    <c:choose>
                                                        <c:when test='${flow.yield !=farm.yield}'>
                                                            <td class="text-red"><c:out value="${flow.yield}"/></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="text-green"><c:out value="${flow.yield}"/></td>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:forEach>
                                            </tr>


                                        </tbody>


                                    </table>
                                </div>
                            </div>
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h3 class="box-title">#Farmer Details</h3>

                                    <div class="box-tools pull-right">
                                      <a tabindex="0" class="text-overflow" id="popFarmComparison" role="button"><i class="fa fa-question text-orange"></i></a>
                                         <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                        </button>
                                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>
                                <div class="box-body">
                                    <ul class="nav nav-tabs">
                                        <li>  </li>
                                        <li class="active"><a href=".tab_1" data-toggle="tab"><b>Crop Validation</b></a></li>
                                        <li><a href=".tab_2" data-toggle="tab"><b>Soil Analysis</b></a></li>
                                        <li><a href=".tab_3" data-toggle="tab"><b>Fertilizer</b></a></li>
                                        <li><a href=".tab_4" data-toggle="tab"><b>Tiller</b></a></li>
                                        <li><a href=".tab_5" data-toggle="tab"><b>Problems </b></a></li>
                                        <li><a href=".tab_6" data-toggle="tab"><b>Recommendations</b></a></li>



                                    </ul>

                                    <div class="tab-content">

                                        <div class="tab-pane active tab_1" id="tab_2">
                                            <h3 class="profile-username text-center"></h3>
                                            <table id="fieldtable" class="table table-hover table-responsive">
                                                <thead><tr>
                                                        <th>Details</th>
                                                        <th><c:out value="${farm.id}"/></th>
                                                            <c:forEach var="flow" items="${flist}">

                                                            <th><c:out value="${flow.id}"/></th>
                                                            </c:forEach>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <th>Crop Variety</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.variety}"></c:out> </td>

                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.variety !=farm.cropVal.variety}'>
                                                                    <td class="text-red"><c:out value="${flow.cropVal.variety}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.cropVal.variety}"></c:out></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Crop Class</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.crop_class}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.crop_class !=farm.cropVal.crop_class}'>
                                                                    <td class="text-red"><c:out value="${flow.cropVal.crop_class}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.cropVal.crop_class}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </c:forEach>
                                                    </tr>

                                                    <tr>
                                                        <th>Texture</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.texture}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.texture !=farm.cropVal.texture}'>
                                                                    <td class=" text-red"><c:out value="${flow.cropVal.texture}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class=" text-green"><c:out value="${flow.cropVal.texture}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Farming System</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.farming_system}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.farming_system !=farm.cropVal.farming_system}'>
                                                                    <td class=" text-red"><c:out value="${flow.cropVal.farming_system}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.cropVal.farming_system}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </c:forEach>
                                                    </tr>

                                                    <tr>
                                                        <th>Topography</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.topography}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.topography !=farm.cropVal.topography}'>
                                                                    <td class="text-red"><c:out value="${flow.cropVal.topography}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.cropVal.topography}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Furrow Distance(m)</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.furrow_distance}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.furrow_distance !=farm.cropVal.furrow_distance}'>
                                                                    <td class="text-red"><c:out value="${flow.cropVal.furrow_distance}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.cropVal.furrow_distance}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Planting Density</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.planting_density}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.planting_density !=farm.cropVal.planting_density}'>
                                                                    <td class="text-red"><c:out value="${flow.cropVal.planting_density}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.cropVal.planting_density}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Number Millable</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.num_millable}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.num_millable !=farm.cropVal.num_millable}'>
                                                                    <td class="text-red"><c:out value="${flow.cropVal.num_millable}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.cropVal.num_millable}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Average Millable</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.avg_millable_stool}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.avg_millable_stool !=farm.cropVal.avg_millable_stool}'>
                                                                    <td class="text-red"><c:out value="${flow.cropVal.avg_millable_stool}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class=" text-green"><c:out value="${flow.cropVal.avg_millable_stool}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Brix(bx)</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.brix}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.brix !=farm.cropVal.brix}'>
                                                                    <td class="text-red"><c:out value="${flow.cropVal.brix}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.cropVal.brix}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Stalk Length(cm)</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.stalk_length}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.stalk_length !=farm.cropVal.stalk_length}'>
                                                                    <td class="text-red"><c:out value="${flow.cropVal.stalk_length}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class=" text-green"><c:out value="${flow.cropVal.stalk_length}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Diameter(mm)</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.diameter}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.diameter !=farm.cropVal.diameter}'>
                                                                    <td class=" text-red"><c:out value="${flow.cropVal.diameter}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class=" text-green"><c:out value="${flow.cropVal.diameter}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Weight(kg)</th>
                                                        <td class="text-blue"><c:out value="${farm.cropVal.weight}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.cropVal.weight !=farm.cropVal.weight}'>
                                                                    <td class="text-red"><c:out value="${flow.cropVal.weight}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.cropVal.weight}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>


                                                </tbody>


                                            </table>
                                        </div>
                                        <div class="tab-pane tab_2" id="tab_8">
                                            <h3 class="profile-username text-center"></h3>
                                            <table id="fieldtable" class="table table-hover table-responsive">
                                                <thead><tr>
                                                        <th>Details</th>
                                                        <th><c:out value="${farm.id}"/></th>
                                                            <c:forEach var="flow" items="${flist}">

                                                            <th><c:out value="${flow.id}"/></th>
                                                            </c:forEach>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <th>PH Level(ph)</th>
                                                        <td class="text-blue"><c:out value="${farm.soilanalysis.ph_lvl}"></c:out> </td>

                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.soilanalysis.ph_lvl !=farm.soilanalysis.ph_lvl}'>
                                                                    <td class="text-red"><c:out value="${flow.soilanalysis.ph_lvl}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.soilanalysis.ph_lvl}"></c:out></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Organic Matter(mg)</th>
                                                        <td class="text-blue"><c:out value="${farm.soilanalysis.organic_matter}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.soilanalysis.organic_matter !=farm.soilanalysis.organic_matter}'>
                                                                    <td class="text-red"><c:out value="${flow.soilanalysis.organic_matter}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.soilanalysis.organic_matter}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </c:forEach>
                                                    </tr>

                                                    <tr>
                                                        <th>Phosphorus(mg)</th>
                                                        <td class="text-blue"><c:out value="${farm.soilanalysis.phosphorus}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.soilanalysis.phosphorus !=farm.soilanalysis.phosphorus}'>
                                                                    <td class=" text-red"><c:out value="${flow.soilanalysis.phosphorus}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class=" text-green"><c:out value="${flow.soilanalysis.phosphorus}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Potassium(mg)</th>
                                                        <td class="text-blue"><c:out value="${farm.soilanalysis.potassium}"/></td>
                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.soilanalysis.potassium !=farm.soilanalysis.potassium}'>
                                                                    <td class=" text-red"><c:out value="${flow.soilanalysis.potassium}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.soilanalysis.potassium}"></c:out>  </td>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </c:forEach>
                                                    </tr>




                                                </tbody>


                                            </table>
                                        </div>
                                        <div class="tab-pane tab_8" id="tab_8">
                                            <h3 class="profile-username text-center"></h3>
                                            <table id="fieldtable" class="table table-hover table-responsive">
                                                <thead><tr>
                                                        <th>Details</th>
                                                        <th><c:out value="${farm.id}"/></th>
                                                            <c:forEach var="flow" items="${flist}">

                                                            <th><c:out value="${flow.id}"/></th>
                                                            </c:forEach>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <th>Fertilizer</th>
                                                        <td class="text-blue"><c:out value="${farm.fertilizer.fertilizer}"></c:out> </td>

                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.fertilizer.fertilizer !=farm.fertilizer.fertilizer}'>
                                                                    <td class="text-red"><c:out value="${flow.fertilizer.fertilizer}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.fertilizer.fertilizer}"></c:out></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>First Dose</th>
                                                        <td class="text-blue"><c:out value="${farm.fertilizer.first_dose}"></c:out> </td>

                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.fertilizer.first_dose !=farm.fertilizer.first_dose}'>
                                                                    <td class="text-red"><c:out value="${flow.fertilizer.first_dose}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.fertilizer.first_dose}"></c:out></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Second Dose</th>
                                                        <td class="text-blue"><c:out value="${farm.fertilizer.second_dose}"></c:out> </td>

                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.fertilizer.second_dose !=farm.fertilizer.second_dose}'>
                                                                    <td class="text-red"><c:out value="${flow.fertilizer.second_dose}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.fertilizer.second_dose}"></c:out></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                </tbody>


                                            </table>
                                        </div>
                                        <div class="tab-pane tab_7" id="tab_8">
                                            <h3 class="profile-username text-center"></h3>
                                            <table id="fieldtable" class="table table-hover table-responsive">
                                                <thead><tr>
                                                        <th>Details</th>
                                                        <th><c:out value="${farm.id}"/></th>
                                                            <c:forEach var="flow" items="${flist}">

                                                            <th><c:out value="${flow.id}"/></th>
                                                            </c:forEach>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <th>Repetition</th>
                                                        <td class="text-blue"><c:out value="${farm.tillers.rep}"></c:out> </td>

                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.tillers.rep !=farm.tillers.rep}'>
                                                                    <td class="text-red"><c:out value="${flow.tillers.rep}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.tillers.rep}"></c:out></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>
                                                    <tr>
                                                        <th>Count</th>
                                                        <td class="text-blue"><c:out value="${farm.tillers.count}"></c:out> </td>

                                                        <c:forEach var="flow" items="${flist}">
                                                            <c:choose>
                                                                <c:when test='${flow.tillers.count !=farm.tillers.count}'>
                                                                    <td class="text-red"><c:out value="${flow.tillers.count}"></c:out> </td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td class="text-green"><c:out value="${flow.tillers.count}"></c:out></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </tr>

                                                </tbody>


                                            </table>
                                        </div>
                                        <div class="tab-pane tab_5" id="tab_8">
                                            <h3 class="profile-username text-center"></h3>
                                            <table id="fieldtable" class="table table-hover table-responsive">
                                                <thead><tr>
                                                        <th>Problem(only active)</th>
                                                        <th>Phase</th>
                                                        <th>Solutions</th>
                                                        <th><c:out value="${farm.id}"/></th>
                                                            <c:forEach var="flow" items="${flist}">

                                                            <th><c:out value="${flow.id}"/></th>
                                                            </c:forEach>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${not empty comprob}">
                                                        <c:forEach var="flow" items="${comprob}" >
                                                            <tr>
                                                                <th><c:out value="${flow.prob_name}"/></th>
                                                                <td><c:out value="${flow.phase}"/></td>
                                                                <c:choose>
                                                                    <c:when test='${not empty flow.reclist}'>
                                                                        <td>
                                                                            <c:forEach items="${flow.reclist}" var="reclist">
                                                                                <a href="viewRecDetails?id=${reclist.id}" target="_blank">${reclist.recommendation_name}</a><br></br>
                                                                            </c:forEach>
                                                                        </td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td></td>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <c:forEach var="fly" items="${flow.farms}">

                                                                    <c:choose>
                                                                        <c:when test='${fly!=null}'>
                                                                            <td class="text-green"><i class="fa fa-check fa-2x" aria-hidden="true"></i></td>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                            <td class="text-red"><i class="fa fa-close fa-2x" aria-hidden="true"></i></td>
                                                                            </c:otherwise>
                                                                        </c:choose>


                                                                </c:forEach>

                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>



                                                </tbody>


                                            </table>
                                        </div>
                                        <div class="tab-pane tab_6" id="tab_8">
                                            <h3 class="profile-username text-center"></h3>
                                            <table id="fieldtable" class="table table-hover table-responsive">
                                                <thead><tr>
                                                        <th>Recommendation(only active)</th>
                                                        <th>Phase</th>
                                                        <th>Problems Targeted</th>
                                                        <th><c:out value="${farm.id}"/></th>
                                                            <c:forEach var="flow" items="${flist}">

                                                            <th><c:out value="${flow.id}"/></th>
                                                            </c:forEach>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${not empty comprec}">
                                                        <c:forEach var="flow" items="${comprec}" >
                                                            <tr>

                                                                <th class="pull-left"><c:out value="${flow.recommendation_name}"/></th>
                                                                <td class=""><c:out value="${flow.phase}"/></td>
                                                                <c:choose>
                                                                    <c:when test='${not empty flow.problist}'>
                                                                        <td>
                                                                            <c:forEach items="${flow.problist}" var="problist">
                                                                                <a href="viewProbDetails?id=${problist.prob_id}" target="_blank"> ${problist.prob_name}</a><br></br>
                                                                            </c:forEach>
                                                                        </td>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <td></td>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <c:forEach var="fly" items="${flow.farms}">

                                                                    <c:choose>
                                                                        <c:when test='${fly!=null}'>
                                                                            <td class="text-green"><i class="fa fa-check fa-2x" aria-hidden="true"></i></td>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                            <td class="text-red"><i class="fa fa-close fa-2x" aria-hidden="true"></i></td>
                                                                            </c:otherwise>
                                                                        </c:choose>


                                                                </c:forEach>

                                                            </tr>
                                                        </c:forEach>

                                                    </c:if>


                                                </tbody>


                                            </table>
                                        </div>
                                        <div class="tab-pane tab_4" id="tab_8">

                                            <div class="col-md-2">
                                                <h3 class="profile-username text-center text-bold">${farm.id}</h3>
                                                <table id="fieldtable" class="table table-hover table-responsive">
                                                    <thead><tr>

                                                            <th>Rep</th>
                                                            <th>Count</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:if test="${not empty farm.tillist}">
                                                            <c:forEach items="${farm.tillist}" var="til" varStatus="status">
                                                                <tr>
                                                                    <td><c:out value="${til.rep}"/></td>
                                                                    <td class="text-blue"><c:out value="${til.count}"/></td>
                                                                </tr>
                                                            </c:forEach>


                                                        </c:if>


                                                    </tbody>


                                                </table>
                                            </div>
                                            <c:forEach items="${flist}" var="flow">
                                                <div class="col-md-2">
                                                    <h3 class="profile-username text-center text-bold">${flow.id}</h3>
                                                    <table id="fieldtable" class="table table-hover table-responsive">
                                                        <thead><tr>

                                                                <th>Rep</th>
                                                                <th>Count</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:if test="${not empty flow.tillist}">
                                                                <c:forEach items="${flow.tillist}" var="til" varStatus="status">
                                                                    <tr>
                                                                        <td> <c:out value="${til.rep}"/></td>
                                                                        <c:choose>
                                                                            <c:when test='${til.count <farm.tillist[status.index].count}'>
                                                                                <td class="text-red"><c:out value="${til.count}"/></td>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <td class="text-green"><c:out value="${til.count}"/></td>
                                                                            </c:otherwise>
                                                                        </c:choose>

                                                                    </tr>
                                                                </c:forEach>


                                                            </c:if>


                                                        </tbody>


                                                    </table>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="tab-pane tab_3" id="tab_8">

                                            <div class="col-md-3">
                                                <h3 class="profile-username text-center text-bold">${farm.id}</h3>
                                                <table id="fieldtable" class="table table-hover table-responsive">
                                                    <thead><tr>
                                                            <th>Fertilizer</th>
                                                            <th>First Dose(bags)</th>
                                                            <th>Second Dose(bags)</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:if test="${not empty farm.fertlist}">
                                                            <c:forEach items="${farm.fertlist}" var="til" varStatus="status">
                                                                <tr>
                                                                    <th class="text-blue"><c:out value="${til.fertilizer}"/></th>
                                                                    <td class="text-blue"><c:out value="${til.first_dose}"/></td>
                                                                    <td class="text-blue"><c:out value="${til.second_dose}"/></td>
                                                                </tr>
                                                            </c:forEach>


                                                        </c:if>


                                                    </tbody>


                                                </table>
                                            </div>
                                            <c:forEach items="${flist}" var="flow">
                                                <div class="col-md-3">
                                                    <h3 class="profile-username text-center text-bold">${flow.id}</h3>
                                                    <table id="fieldtable" class="table table-hover table-responsive">
                                                        <thead><tr>
                                                                <th>Fertilizer</th>
                                                                <th>First Dose (bags)</th>
                                                                <th>Second Dose (bags)</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:if test="${not empty flow.fertlist}">
                                                                <c:forEach items="${flow.fertlist}" var="til" varStatus="status">
                                                                    <tr>
                                                                        <c:choose>
                                                                            <c:when test='${til.fertilizer !=farm.fertlist[status.index].fertilizer}'>
                                                                                <th class="text-red"><c:out value="${til.fertilizer}"/></th>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                <th class="text-green"><c:out value="${til.fertilizer}"/></th>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                            <c:choose>
                                                                                <c:when test='${til.first_dose !=farm.fertlist[status.index].first_dose}'>
                                                                                <td class="text-red"><c:out value="${til.first_dose}"/></td>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <td class="text-green"><c:out value="${til.first_dose}"/></td>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                        <c:choose>
                                                                            <c:when test='${til.second_dose !=farm.fertlist[status.index].second_dose}'>
                                                                                <td class="text-red"><c:out value="${til.second_dose}"/></td>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <td class="text-green"><c:out value="${til.second_dose}"/></td>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </tr>
                                                                </c:forEach>


                                                            </c:if>


                                                        </tbody>


                                                    </table>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>

                                </div>
                                <!-- /.box-body -->
                            </div>

                        </div>

                        <form id="frm-FarmDiff" action="viewSendRecObserv">
                            <div class="col-md-offset-5 col-sm-3 ">
                                <button id="createRecs" class="btn btn-info btn-block" name="atools" id="crec" value="crec" type="submit" style="height: 6%;">Create Recommendations</button>
                                <button id="viewRecs" class="btn btn-success btn-block" type="button" style="height: 6%;">Send Recommendations</button>
                                <button id="viewProbs" class="btn btn-danger btn-block" type="button" style="height: 6%;">Report Problems</button>
                            </div>   












                            <div class="col-md-12 hidden" id="recBlock">
                                <br>
                                <h1> Send Recommendations<small><a tabindex="0" class="text-overflow" id="popSendRec" role="button"><i class="fa fa-info-circle text-orange"></i></a></small>
                                       </h1>
                                <div class="col-md-6" > 

                                    <div class="box box-info">
                                        <div class="box-header">
                                            <h1 class="box-title">All Recommendations: </h1>

                                        </div>

                                        <div class="box-body ">

                                            <table id="allrecs" class="table  display table-hover pull-right" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th>Recommendation</th>
                                                        <th>Type</th>
                                                        <th>Phase</th>
                                                        <th>More Info</th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${not empty darecs}">
                                                        <c:forEach items="${darecs}" var="rec" >
                                                            <tr>
                                                                <td><input type="checkbox" name="recsid[]" class="checkbox" id="buttonClick" value="${rec.id}"> </td>
                                                                <td><c:out value="${rec.recommendation_name}"/></td>
                                                                <td><c:out value="${rec.type}"/></td>
                                                                <td><c:out value="${rec.phase}"/></td>
                                                                <td><a href="viewRecDetails?id=${rec.id}" target="_blank" class="btn btn-primary text-center">...</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>
                                                </tbody>
                                            </table>




                                        </div>

                                    </div>

                                </div>

                                <div class="col-md-6"> 
                                    <div class="box box-info">
                                        <div class="box-header">
                                            <h1 class="box-title">Selected Recommendations: </h1>

                                        </div>

                                        <div class="box-body ">
                                            <table id="finalrecs" class="table  display table-hover pull-right" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th>Recommendation</th>
                                                        <th>Type</th>
                                                        <th>Phase</th>
                                                        <th>More Info</th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${not empty autorec}">
                                                        <c:forEach items="${autorec}" var="rec">
                                                            <tr>
                                                                <td><input type="checkbox" name="recsid[]" class="checkbox" id="buttonClick" value="${rec.id}" checked> </td>
                                                                <td><c:out value="${rec.recommendation_name}"/></td>
                                                                <td><c:out value="${rec.type}"/></td>
                                                                <td><c:out value="${rec.phase}"/></td>
                                                                <td><a href="viewRecDetails?id=${rec.id}" target="_blank" class="btn btn-primary text-center">...</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>


                                </div>
                            </div>
                            <div class="col-md-12 hidden" id="probBlock">
                                <br>
                                <h1> Report Problems<small><a tabindex="0" class="text-overflow" id="popSendProb" role="button"><i class="fa fa-info-circle text-orange"></i></a></small></h1>
                                
                                <div class="col-md-6" > 

                                    <div class="box box-info">
                                        <div class="box-header">
                                            <h1 class="box-title">All Problems: </h1>

                                        </div>

                                        <div class="box-body ">

                                            <table id="allprobs" class="table  display table-hover pull-right" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th>Problems</th>
                                                        <th>Type</th>
                                                        <th>Phase</th>
                                                        <th>More Info</th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${not empty daprobs}">
                                                        <c:forEach items="${daprobs}" var="prob" >
                                                            <tr>
                                                                <td><input type="checkbox" name="probsid[]" class="checkbox" id="buttonClick" value="${prob.prob_id}"> </td>
                                                                <td><c:out value="${prob.prob_name}"/></td>
                                                                <td><c:out value="${prob.type}"/></td>
                                                                <td><c:out value="${prob.phase}"/></td>
                                                                <td><a href="viewProbDetails?id=${prob.prob_id}" target="_blank" class="btn btn-primary text-center">...</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>
                                                </tbody>
                                            </table>




                                        </div>

                                    </div>

                                </div>

                                <div class="col-md-6"> 
                                    <div class="box box-info">
                                        <div class="box-header">
                                            <h1 class="box-title">Selected Problems: </h1>

                                        </div>

                                        <div class="box-body ">
                                            <table id="finalprobs" class="table  display table-hover pull-right" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th>Problems</th>
                                                        <th>Type</th>
                                                        <th>Phase</th>
                                                        <th>More Info</th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${not empty autoprob}">
                                                        <c:forEach items="${autoprob}" var="prob">
                                                            <tr>
                                                                <td><input type="checkbox" name="probsid[]" class="checkbox" id="buttonClick" value="${prob.prob_id}" checked> </td>
                                                                <td><c:out value="${prob.prob_name}"/></td>
                                                                <td><c:out value="${prob.type}"/></td>
                                                                <td><c:out value="${prob.phase}"/></td>
                                                                <td><a href="viewProbDetails?id=${prob.prob_id}" target="_blank" class="btn btn-primary text-center">...</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>


                                </div>
                            </div>
                            <div class="col-md-offset-9 col-md-4 hidden" id="checkBlock">
                                    
                                <input  style="width: 16; height: 16" value="${farm.id}" name="allid[]" class="form-control hidden">
                                <c:forEach var="flow" items="${flist}">
                                    <input  style="width: 16; height: 16" value="${flow.id}" name="allid[]" class="form-control hidden">
                                </c:forEach> 
                                <div class="box box-info">
                                      
                                    <div class="box-header with-border">
                                      <a tabindex="0" class="text-overflow" id="popSendto" role="button"><i class="fa fa-question text-orange"></i></a>
                                      
                                        </div>
                                     
                                    <div class="box-body">
                                         
                                        <table id="selecttable" class="table table-hover table-bordered table-responsive">
                                            <tbody>
                                                <tr>   <td><input type="checkbox" style="width: 16; height: 16" value="${farm.id}" name="farmid[]" class="msgCheckbox pull-left" checked></td>
                                                    <td>Farm : ${farm.id} </td> 
                                                </tr>
                                                <c:forEach var="flow" items="${flist}">
                                                    <tr>
                                                        <td><input type="checkbox" style="width: 16; height: 16" value="${flow.id}" name="farmid[]" class="msgCheckbox pull-left" checked></td>
                                                        <td>Farm : ${flow.id} </td> 
                                                    </tr>
                                                </c:forEach> 
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-offset-2 col-md-7 text-center">
                                
                                <button class="btn btn-app btn-linkedin atools hidden" name="atools" id="crec" value="crec">
                                    <i class="fa fa-edit" ></i> Create Recommendations
                                </button>
                                <button class="btn btn-app btn-linkedin atools hidden" name="atools" id="srec" value="vrec">
                                    <i class="fa fa-edit" ></i> Send Recommendations
                                </button>
                                <button class="btn btn-app btn-linkedin atools hidden" name="atools" id="dprob" value="dprob">
                                    <i class="fa fa-edit" ></i> Report Problems
                                </button>
                               
                            </div>

                        </form>

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
        <script type="text/javascript">             $(function () {
                $('.box-profile').slimScroll({
                    height: '420px',
                    alwaysVisible: true
                });
            });
        </script>
        <script type="text/javascript">
            $(".atools").on("click", function () {
                //var checkedValue = $('.msgCheckbox:checked').val();   

                var checkedValue = [];
                var inputElements = document.getElementsByClassName('msgCheckbox');
                for (var i = 0; inputElements[i]; ++i) {
                    if (inputElements[i].checked) {
                        checkedValue.push(inputElements[i].value);
                        console.log(checkedValue);

                    }

                }




            });
        </script>
        <script src="plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
         <script src="plugins/poptest/popoverText.js"></script>
        <script>
                                   $(document).ready(function () {
                                       $('#popFarmBasicDet').popover(popFarmBasicDet);
                                       $('#popFarmComparison').popover(popFarmComparison);
                                       $('#popSendRec').popover(popSendRec);
                                       $('#popSendProb').popover(popSendProb);
                                       $('#popSendto').popover(popSendto);
  });


        </script>
        <script>  $(document).ready(function () {

                $("#viewRecs").on("click", function () {
                    $('#recBlock').removeClass('hidden');
                    $('#checkBlock').removeClass('hidden');
                    $('#srec').removeClass('hidden');
                    $('#dprob').addClass('hidden');
                    $('#viewRecs').addClass('hidden');
                    $('#viewProbs').removeClass('hidden');
                    $('#probBlock').addClass('hidden');

                });

                $("#viewProbs").on("click", function () {
                    $('#probBlock').removeClass('hidden');
                    $('#checkBlock').removeClass('hidden');
                    $('#dprob').removeClass('hidden');
                    $('#viewRecs').removeClass('hidden');
                    $('#viewProbs').addClass('hidden');
                    $('#srec').addClass('hidden');
                    $('#recBlock').addClass('hidden');
                });
            });
        </script>
        <script>
            $(document).ready(function () {


                var table = $('#allrecs').DataTable({
                    'order': [[1, 'asc']]
                            //      ,
                            //       'rowCallback': function(row, data, dataIndex){
                            //         // Get row ID
                            //       var rowId = data[0];
                            //       // alert(rowId);
                            //         // If row ID is in the list of selected row IDs
                            //         if($.inArray(rowId, rows_selected) !== -1){
                            //            $(row).find('input[type="checkbox"]').prop('checked', true);
                            //            $(row).addClass('selected');
                            //         }
                            //      }     

                });



                $('body').on('click', '#allrecs tbody tr td input.checkbox', function () {
                    if ($(this.checked)) {

                        var row = $(this).closest('tr').clone();
                        $('#finalrecs tbody').append(row);
                        table.row(this.closest('tr')).remove().draw();
                    }

                });
                $('body').on('click', '#finalrecs tbody tr td input.checkbox', function () {
                    //if( $(this).attr('checked')){
                    var row = $(this).closest('tr').clone();
                    table.row.add(row).draw();

                    $(this).closest('tr').remove();
                    //}

                });
                var table1 = $('#finalrecs');
                $('#frm-FarmDiff').on('submit', function (e) {
                    var form = this;

                    // Iterate over all checkboxes in the table
                    table1.$('input[type="checkbox"]').each(function () {
                        // If checkbox doesn't exist in DOM
                        if (!$.contains(document, this)) {
                            // If checkbox is checked
                            if (this.checked) {
                                // Create a hidden element 
                                $(form).append(
                                        $('<input>')
                                        .attr('type', 'hidden')
                                        .attr('name', this.name)
                                        .val(this.value)
                                        );
                            }
                        }
                    });
                });

            });
        </script>
        <script>
            $(document).ready(function () {


                var table2 = $('#allprobs').DataTable({
                    'order': [[1, 'asc']]
                            //      ,
                            //       'rowCallback': function(row, data, dataIndex){
                            //         // Get row ID
                            //       var rowId = data[0];
                            //       // alert(rowId);
                            //         // If row ID is in the list of selected row IDs
                            //         if($.inArray(rowId, rows_selected) !== -1){
                            //            $(row).find('input[type="checkbox"]').prop('checked', true);
                            //            $(row).addClass('selected');
                            //         }
                            //      }     

                });



                $('body').on('click', '#allprobs tbody tr td input.checkbox', function () {
                    if ($(this.checked)) {

                        var row = $(this).closest('tr').clone();
                        $('#finalprobs tbody').append(row);
                        table2.row(this.closest('tr')).remove().draw();
                    }

                });
                $('body').on('click', '#finalprobs tbody tr td input.checkbox', function () {
                    //if( $(this).attr('checked')){
                    var row = $(this).closest('tr').clone();
                    table2.row.add(row).draw();
                    $(this).closest('tr').remove();
                    //}

                });
                var table3 = $('#finalprobs');
                $('#frm-FarmDiff').on('submit', function (e) {
                    var form = this;

                    // Iterate over all checkboxes in the table
                    table3.$('input[type="checkbox"]').each(function () {
                        // If checkbox doesn't exist in DOM
                        if (!$.contains(document, this)) {
                            // If checkbox is checked
                            if (this.checked) {
                                // Create a hidden element 
                                $(form).append(
                                        $('<input>')
                                        .attr('type', 'hidden')
                                        .attr('name', this.name)
                                        .val(this.value)
                                        );
                            }
                        }
                    });
                });

            });
        </script>

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
