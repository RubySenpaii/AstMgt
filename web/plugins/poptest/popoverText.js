
//****HOME PAGE ****
//Crop Estimate


var popCropEst = {toggle: "popover", trigger: "focus", placement: "auto",html: true,

    title: 'Crop Estimate', 
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The crop estimate shows the current week`s current production in comparison(difference) to the estimated production</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Any needed changes to the estimate can be done in the "crop estimate" page which is usually done upon noticing a varied difference</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }

};
//yield map
var popYieldMap = {toggle: "popover", trigger: "focus", placement: "auto",html: true,

    title: 'Yield Map', 
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The yield map shows the entire production of the district. The divisions correspond to the municipalities inside the district. The bigger the part of that municipal, the higher the production</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li> Click on the map to drilldown to barangay and farmer level </li>\n\
                             <li> Click directly on the municipality name to go to its production </li>\n\
                               <li> Use the dropdown to change between Tons Cane, Area and Yield </li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//area harvested
var popAreaHarv = {toggle: "popover", trigger: "focus", placement: "auto",html: true,

    title: 'Area Harvested', 
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>Area Harvested shows the how much of the total area in hectares have been harvested for the week</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Estimated production is the total forecasted area and production of the district for the year</li>\n\
                             <li>"Previous" refers to the area harvested and production in the previous weeks</li>\n\
\n\                            <li>"To Date" refers to current the total area and production  </li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

//linegraph
var popLineGraph = {toggle: "popover", trigger: "focus", placement: "left",html: true,

    title: 'Line Graph', 
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The graph shows the production of all districts each week</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Use the dropdown to change between Tons Cane, Area and LKG</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//production by week
var popProdByWeek = {toggle: "popover", trigger: "focus", placement: "auto",html: true,

    title: 'Line Graph', 
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the list of ongoing programs</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Click more details to view the programs details</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//production by week full view
var popProdByWeekFV = {toggle: "popover", trigger: "focus", placement: "auto",html: true,

    title: 'Prodcution By Week', 
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>hello world</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>hello world</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//production by week full view
var popProdByWeekFV = {toggle: "popover", trigger: "focus", placement: "auto",html: true,

    title: 'Production By Week Full View', 
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>hello world</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>hello world</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//ongoing project list
var popWPSR = {toggle: "popover", trigger: "focus", placement: "auto",html: true,

    title: 'WeeklyProduction Statistics Report', 
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The report shows the weekly production report with all the districts</dd>\n\
                   \
                        <dd>\n\
                </dl>';
    }
};

//standing crop
var popStandCrop = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Standing Crop   ',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>Standing crop shows the remaining hectares of the crop that have yet to be harvested</dd>\n\
                 \n\
                        <dd>\n\
                </dl>';
    }
};
//popWeatherForecast
var popWeatherForecast = {toggle: "popover", trigger: "focus", placement: "auto",html: true,

    title: 'Weather Forecast', 
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the amount of rain for each week</dd>\n\
                    \n\
                        <dd>\n\
                </dl>';
    }
};
//narrative report
var popNarativeRep = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Narrative Report',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This report shows the factors and parameters that affect the overall production for the week</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li> The problems come from the reports sent by the farmer</li>\n\
                            <li> "Overall Analysis" is the summary of all the factors reported for the week</li>\n\
\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//Board narrative
var popBoardNarativeRep = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Board Narrative Report',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This narrative can be used as a tool for communicating any suggestions and findings to the MDO</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li> Findings may include any significant trend or report problems that need to be addressed immediately</li>\n\
                            <li> Overall Analysis section may be used to report any programs that were created that may be of significance to the MDOs</li>\n\
\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//**district improvement status report
//farmer performance
var popFarmPerf = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Farmer Performance Report',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This report shows the farmers that have the highest and lowest contribution for the week</dd>\n\
                        <dd>This report also shows the farmers that had the highest and lowest yields of the week</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li> The arrow colors signify whether it has improved or not</li>\n\
                            <li> Improvement can be seen using the arrow direction as to how higher or lower a farmer has done</li>\n\
\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//district improvements
var popDistImp = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'District Improvement Report',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This report shows the status report of the district with regards to recommendations and problems</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li> The recommendations are based from the past week</li>\n\
                            <li> The data is all based from farmer to MDO data</li>\n\
\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//****RECOMMENDATIONS PAGE****
//
var popRec = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Recommendations',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the list of recommendations</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>The "Improvement" column shows if the recommendation is for improvement or not</li>\n\
\                           <li>Use the search box to easily access the details of the chosen recommendation</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//
//****Recommendation Details****
//prob being solved
var popProbSolved = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Problems being solved',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>These are the problems that are aimed to be solved by the recommendation</dd>\n\
                    \n\
                </dl>';
    }
};
//Recommendation w/fields
var popRecfield = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Recommendations w/ fields',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the recommendations that are being implemented on the farms</dd>\n\
                    \n\
                </dl>';
    }
};
//****PROBLEM PAGE****
var popProbList = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Problems List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the list of all the problems that have occured</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>A "Subjective" problem refers to problems that are reported through raw observations"</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//
//****Plans and Programs List****
var popViewProgList = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Programs List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>List of programs created</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>The progress bar shows the completion of the program</li>\n\
                            <li>"Total Farms" show the number of farms that are part of the program</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//****Plans & Programs Details page****
var popProgBar = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Progress Bar',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows how much completion the program has</dd>\n\
                   \n\
                </dl>';
    }
};
var popProgProbList = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Program Problem List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the list of all problems that will be addressed by the program/dd>\n\
                   \
                </dl>';
    }
};
var popObsImp = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Observational Improvement',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the comparison of how much the production/yield has improved</dd>\n\
                    n\
                        <dd>\n\
                </dl>';
    }
};
var popUpdKPIProg = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Update program KPI',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The KPIs are updated to reflect how much was the progress </dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Input the number for the update not exceeding the remaining value of the KPI</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//****Search Farmer page****

var popSearchFarmer = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Search Farmer',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This is where all the farmers of the district can be searched</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Input the farmers name like searching in Google</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//****Crop Estimate(options) page****
var popYrlyTCEst = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Yearly Tons Cane Estimate',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The graph shows the 3 estimations of tons cane for each year</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>The highlighted graph refers to the one which is closest to the actual tons cane</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
var popTCGenForc = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Generate Forecast Form',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The forecast form prompts the user to input the parameters to generate a crop estimate</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                              <li>Input the values in the text field using the previous years data as guide</li>\n\
\                           <li>The dropdown lets the user choose between the 3 estimations that will update the crop assessment</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
var popTCForcSim = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Forecast Simulations',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the forecast tests generated after inputting the parameters</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Use this to evaluate which of the 3 forecast is the closest to the actual value</li>\n\
\n\                         <li>The evaluation can be used as a basis to choose from the dropdown</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
var popDistTCDrill = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'District TC Estimate Drilldown',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The graph shows the comparison of the actual Tons Cane and the estimated</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Click on the blue graph to drilldown to the monthly actual Tons Cane</li>\n\
                            <li>Click on the black graph to drilldown to the monthly estimated Tons Cane</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
var popWeekDistEst = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Weekly TC District Estimates',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the crop estimate per week in the district</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                          \
                            <li>Use the dropdown to choose from year</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
var popYrMuniEst = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Yearly TC Municipal Estimates',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the crop estimate in the municipalities per week</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Use the dropdown to choose from year</li>\n\
                            <li>Click more details to view the production of the municipality</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};


//****Crop Estimate (Board/LKG) page****

var popYrlyLKGEst = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Yearly LKG Estimate',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                         <dd>The graph shows the 3 estimations of LKG for each year</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>The highlighted graph refers to the one which is closest to the actual LKG</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var popDistLKGEstDrill = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'District LKG Estimate Drilldown',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The graph shows the comparison of the actual LKG and the estimated</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Click on the blue graph to drilldown to the monthly actual LKG</li>\n\
                            <li>Click on the black graph to drilldown to the monthly estimated LKG</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var popWeeklyDistLKGEst = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Weekly LKG District Estimate',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the crop estimate per week in the district</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                          \
                            <li>Use the dropdown to choose from year</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var popLKGGenForc = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'LKG Generate Forecast',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                          <dd>The forecast form prompts the user to input the parameters to generate a crop estimate for LKG</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                              <li>Input the values in the text field using the previous years data as guide</li>\n\
\                           <li>The dropdown lets the user choose between the 3 estimations that will update the crop assessment</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
var popLKGForcSim = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'LKG Forecast Simulation',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                             <dd>This shows the forecast tests generated after inputting the parameters</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Use this to evaluate which of the 3 forecast is the closest to the actual value</li>\n\
\n\                         <li>The evaluation can be used as a basis to choose from the dropdown</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//****Crop Assessment Report page****
//uses the same as homepage for Area Harvested 
//                              Standing Crop
//                              Narrative Report
//                      ^YOU MAY CHANGE IT IF YOU WISH

//****Municipal Summary Page****
var popMuniYrProdChart = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Municipal Yearly Production',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The graph shows the yearly production of the municipal</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>The line bar is the average district production</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var PopBrgyProdCurrYear = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Current Year Brgy Production',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The graph shows the yearly production of all the barangays within the municipal</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li> Use the dropdown to change between Tons Cane, Area and Yield</li>\n\
\n\                      <li>The line bar is the average district production/li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var PopMuniBrgys = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Municipal Brgy List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the list of barangays within the municipal</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Click more details to access the barangay production details</li>\n\
\n                          <li>Use the search box to quickly find the intended barangay</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//****BRGY SUMMARY PAGE****

var popBrgyYrProdChart = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Barangay Yearly Production',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                         <dd>The graph shows the yearly production of the barangay</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>The line bar is the average district production/li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var PopFarmerProdCurrYear = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Farmer Production for Curr Year',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The graph shows the yearly production of all the farmers within the barangay</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li> Use the dropdown to change between Tons Cane, Area and Yield</li>\n\
\n\                      <li>The line bar is the average district production/li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
var PopBrgyFarmerList = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Brgy Farmer List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the list of farmers within the barangay</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Click more details to access the farmer profile</li>\n\
                        <li>Use the search box to quickly find the intended farmer</li>\n\
                       </ul>\n\
                    <dd>\n\
                     \
                </dl>';
    }
};
//****Farmer Profile Page****

var popFarmerProdDet = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Farmer Production Details',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the production details of the farm in terms of Tons Cane, Area and Yield</dd>\n\
                  \
                </dl>';
    }
};

var popFarmerProbList = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Farmer Problem List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the list of all the problems for all the farms of the farmer</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Click more details to access the problem details</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var popRecProjList = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Farmer Rec List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows all the recommendations that the farmer is practicing in his farms</dd>\n\
                    \n\
                </dl>';
    }
};

var popFarmerProgList = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Farmer Program List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows all the programs that the farmer is involved in based on his problems</dd>\n\
                    \n\
                </dl>';
    }
};

var popFarmerFarmList = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Farmer Farm List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the list of farm owned by the farmer</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Click details to access the farm profile</li>\n\
                        <li>Use the search box to quickly find the intended farm</li>\n\
                         <li>The progress bar shows the completion of that farms harvest</li>\n\
                       </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//****VIEW FIELD DET/ FARM PROFILE page****

var popCropVal = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Crop Validation',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the farm information from the crop validation survey</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>This is updated annually</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
  
var popSoilAna = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Soil Analysis',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>Soil analysis shows the amount of nutrients in the soil of the farm</dd>\n\
                   \
                </dl>';
    }
};

var popFertInfo = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Fertilizer Info',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the amount of fertilizer applied within the crop year</dd>\n\
                   \n\
                </dl>';
    }
};

var popTillInfo = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Tiller Info',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the tiller count for the farm</dd>\n\
                    \n\
                </dl>';
    }
};

var popFarmProbList = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Farm Problem List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the all the problems being encountered/experienced by the farm</dd>\n\
                   \n\
                </dl>';
    }
};

var popFarmRecList = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Farm Recommendation List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows all the recommendations that the farm is implementing</dd>\n\
                    \n\
                </dl>';
    }
};
var popFarmProgList = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Farm Program List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows all the programs that the farm is involved in based on his problems</dd>\n\
                    \n\
                </dl>';
    }
};

var popFarmActionTools = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Action Tools',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>These are options that the user can choose from for providing assistance to the farm</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Choose from the 3 buttons on which to use </li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var popFarmObs = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Farm Observation Tool',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This is where the user filters the farms by their similar attributes eg.(Area, barangay, Yield)</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Search for tag/s to filter the farms</li>\n\
                             <li>Select farms to create comparison</li>\n\
                                <li>Only 5 farms can be selected for comparison</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//****Send other Recommendations page(farm profile (one of the action tools))****

var popSendto = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Send to farm owners',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The user can select which farm/s to send the recommendations or problems</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>By default all farms are selected, user can select which farms to include or exclude</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var popMsg = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Message to be notified',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>hello world</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>hello world</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var popSelRec = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Selected Recommendations',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>These are the recommendations that have been selected to be sent to the farms</dd>\n\
                   \
                </dl>';
    }
};
//****Farm Comparison page****
//

var popFarmBasicDet = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Comparison Farm Basic Details',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the farms main details like owner, area and production</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                               <li>Blue text refers to the selected farm</li>\n\
                         <li>Green text refers to similar information between the farms</li>\n\
                         <li>Red text refers to the different information between the farms</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
var popFarmComparison = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Comparison Farm All Details',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the overall details of the farm</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Blue text refers to the selected farm</li>\n\
\n\                         <li>Green text refers to similar information between the farms</li>\n\
\n\                         <li>Red text refers to the different information between the farms</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var popSendRec = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Send Recommendations',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This is where the user selects recommendations to be sent to the farms</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Click the checkbox to select recommendations</li>\n\
                               \n\<li>Click the button to access the details</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};

var popSendProb = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Report Problems',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                         <dd>This is where the user selects problems to be sent to the farms</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Click the checkbox to select problems</li>\n\
                               \n\<li>Click the button to access the details</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//popSendto shared with other 

//****Confirm/Validate Recommendation page(on press of send in comparison page)****

//popSendto also shared with other 
// popMsg also shared

var popVSelRec = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Validate Selected Recommendations',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This is where the user finalizes the recommendations to be sent to the farmer/s</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Check/Uncheck any recommendations as needed</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
};
//

//shows recorded count per municipality  
var popDisasterR = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Disaster Report',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows all of the disaster reports around the district</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Click Print Report to generate a compilation of all of the disasters by type</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};
//shows recorded count per baranagay
var popDisasterB = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Disaster Report',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the list of disaster reports</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>The recorded count refers to the number of farms affected in the barangay</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};

//for weather trends
var popWeatherT = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Weather Trends',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The graph shows the amount of rainfall which correlates to the production for each month</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Use the dropdown to select the year for comparison</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};
//Forum
var popForumD = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Forum',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the lists of post in the forum\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>The status can be filtered to find pending posts</li>\n\
                            </ul>\n\
                        </dd>\n\
                </dl>';
    }
    
};
var popPostpage = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Post',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This is where the user approves or rejects the post</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Accept the if the post is new</li>\n\
\n\                         <li>Rejecting the post will give options to link to problem, recommendation or post</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};
var popPosLTR = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Link to Recommendation',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This is where the post can be linked to a recommendation when it is rejected</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Choose from the recommendations on which to link to the post</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};
var popPosLTP = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Link to Problem',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                       <dd>This is where the post can be linked to a problem when it is rejected</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Choose from the problems list on which to link to the post</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};
var popPosLTPost = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Link to Post',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This refers to linking the current post to an existing and related post</dd>\n\
                    \
                </dl>';
    }
    
};
var popPosCP = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Create Problem',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This is where new problems are created</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>The "Period" refers to which crop phase the problem normally occurs</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};
var popPosCR = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Create Recommendation',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                       <dd>This is where new recommendations/farming practices are created which will be sent to the farmers</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Clicking "Aims to solve a problem" shows a list of problems that you want to be addressed"</li>\n\
                            <li>Clicking "Aims to improve" prompts for the duration of the recommendation"</li>\n\ \
                        <dd>\n\
                </dl>';
    }
    
};
// create new recommendation
var popCreateNR = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Create New Recommendation',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                       <dd>This is where new recommendations/farming practices are created which will be sent to the farmers</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Clicking "Aims to solve a problem" shows a list of problems that you want to be addressed"</li>\n\
                            <li>Clicking "Aims to improve" prompts for the duration of the recommendation"</li>\n\     \
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};

// problem list
var popPL = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Problem List',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This is the list of all the problems encountered in the district</dd>\n\
                    \
                </dl>';
    }
    
};
var popCalendar = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Dates',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the duration of each crop phases</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Click edit to modify the dates for the crop phases for the next crop year</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};
var popDates = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Yearly Calendar',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This shows the crop calendar where the duration of the phases are seen</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Each crop phase has a corresponding color that highlights the calendar</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};

var popWeatherTS = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Weather Trends',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The graph shows the amount of rainfall which correlates to the production for each month</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Use the dropdown to select the year for comparison</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};
// create new recommendation from comment
var popCRFC = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Create New Recommendation',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This is where new recommendations/farming practices are created which will be sent to the farmers</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Clicking "Aims to solve a problem" shows a list of problems that you want to be addressed"</li>\n\
                            <li>Clicking "Aims to improve" prompts for the duration of the recommendation"</li>\n\                           \n\
        </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};
// sending alerts details
var popProbD = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Sending Alerts',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>The disaster or alert details are displayed which will be sent to the farmers</dd>\n\
                   \
                </dl>';
    }
    
};
// sending alerts details
var popMessD = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Sending Alerts',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This is where the user inputs the message regarding the disaster to the farmers affected</dd>\n\
                 \
                </dl>';
    }
    
};
//****Confirm/Validate Problems(on press of report in comparison page)****
//popsendto also shared 

var popProbMsg = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Problem Related Message Notification',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                        <dd>This is the message to be inputted when sending problems</dd>\n\
                    \
                </dl>';
    }
};

var popVSelProb = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Selected Problems',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                  <dd>This is where the user finalizes the problems to be sent to the farmer/s</dd>\n\
                    <dt>Tips</dt>\n\
                        <dd> <ul>\n\
                            <li>Check/Uncheck any problems as needed</li>\n\
                            </ul>\n\
                        <dd>\n\
                </dl>';
    }
    
};

var popDbyDistrict = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Damage By District',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                  <dd>This is where the damage by district can be seen</dd>\n\
                </dl>';
    }
    
};

var popDbyDisasterType = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Damage By Disaster Type',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                  <dd>This is where the damage by disaster type can be seen</dd>\n\
                </dl>';
    }
    
};
var popTopProb = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Top Problems',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                  <dd>Shows the top problems per phase per year</dd>\n\
                </dl>';
    }
    
};
var popTopRec = {toggle: "popover", trigger: "focus", placement: "auto", html: true,

    title: 'Top Recommendation',
    content: function () {
        return '<dl>\n\
                    <dt>Description</dt>\n\
                  <dd>Shows the top recommendations per phase per year</dd>\n\
                </dl>';
    }
    
};

//****another page****





