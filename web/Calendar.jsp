<%-- 
    Document   : Calendar
    Created on : 09 19, 16, 11:24:54 PM
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
          <!-- fullCalendar 2.2.5-->
  
  <link rel="stylesheet" href="plugins/fullcalendar/fullcalendar.min.css">
  <link rel="stylesheet" href="plugins/fullcalendar/fullcalendar.print.css" media="print">
  <link rel="stylesheet" href="bootstrap/css/bootstrap-year-calendar.min.css">
  <link rel="stylesheet" href="bootstrap/css/bootstrap-year-calendar.css">
     <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker-bs3.css">
        <link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
  <!-- Theme style -->


  <!-- Theme style -->

    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        Calendar
                        <small>Optional description</small>
                    </h1>
                </section>
                <section class="content">
                    <div class="row">
                    <form name="editdates" action="editDates">
                    <div class="col-md-3">
          <div class="box box-solid">
            <div class="box-header with-border">
              <h4 class="box-title">Legends</h4>
            </div>
            <div class="box-body">
              <div id="external-events">
                  <div class="external-event" style="background-color: #8B4513; color: white"> Planting</div>
                <div class="external-event bg-green">Germination</div>
                <div class="external-event bg-purple">Tillering</div>
                <div class="external-event bg-red">Stalk Elongation</div>
                <div class="external-event bg-orange">Yield Formation</div>
                <div class="external-event" style="background-color: #CD5C5C; color: white"> Ripening</div>
                <div class="external-event bg-blue">Milling</div>
              </div>
            </div>
          </div>
          <div class="box box-danger">
                            <div class="box-header with-border">
                                <h1 class="box-title">Dates</h1>
                                <div class="box-tools pull-right">
                                    <a tabindex="0" class="text-overflow" id="popCalendar" role="button"><i class="fa fa-question text-orange"></i></a>
                                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                    <!-- In box-tools add this button if you intend to use the contacts pane -->
                                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i>  </button>
                                </div>
                            </div>

                            <div class="box-body no-padding">
                                <label> Planting Phase:</label> <div class="input-group date" id="date1">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                    <input type="text" class="form-control reservation pull-right" name="pstart" id="reservation" value="${test0}">
                                            </div> <br>
                                            
                                <label> Germination Phase:</label> <div class="input-group date" id="date2">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control pull-right reservation" name="gstart" id="datepickerstart" value="${test1}">
                                            </div> <br>
                                            
                                            <label> Tillering Phase:</label> <div class="input-group date" id="date3">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control pull-right reservation" name="tstart" id="datepickerstart" value="${test2}">
                                            </div>  <br>
                                            
                                            <label>Stalk Elongation Phase:</label> <div class="input-group date" id="date4">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control pull-right reservation" name="sstart" id="datepickerstart" value="${test3}">
                                            </div>  <br>
                                            
                                <label> Yield Formation Phase:</label> <div class="input-group date" id="date5">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control pull-right reservation" name="ystart" id="datepickerstart" value="${test4}">
                                            </div>  <br>
                                            
                                            <label> Ripening Phase:</label> <div class="input-group date" id="date6">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control pull-right reservation" name="rstart" id="datepickerstart" value="${test5}">
                                            </div>  <br>
                                            
                                <label> Milling Phase:</label> <div class="input-group date" id="date7">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input type="text" class="form-control pull-right reservation" name="mstart" id="datepickerstart" value="${test6}">
                                            </div>  <br>
                                           
                                </div>
                            </div>
          <button class="btn btn-app btn-linkedin btn-lg pull-right" type="submit">
                                            <i class="fa fa-edit"></i> Edit dates
                                        </button>
            </div>
                    </form>
                        <div class="col-md-9" > 
                            <div class="box box-info">
                                <div class="box-header with-border">
                                    <h1 class="box-title">Calendar</h1>
                                    <div class="box-tools pull-right">
                                        <a tabindex="0" class="text-overflow" id="popDates" role="button"><i class="fa fa-question text-orange"></i></a>
                                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                        <!-- In box-tools add this button if you intend to use the contacts pane -->
                                        <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                    </div>
                                </div>

                            <div class="box-body no-padding">
                                <div id="calendar"></div>
                            </div>
                        </div>
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h1 class="box-title">Improvements Duration</h1>
                                <div class="box-tools pull-right">
                                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                                    <!-- In box-tools add this button if you intend to use the contacts pane -->
                                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                </div>
                            </div>
                            <form id="frm-example" action="updateDurationByPhases">
                            <div class="box-body no-padding">
                                 <table class="table table-bordered" >
                                    <tbody>
                                        <tr>
                                            <th>Phase</th>
                                            <th>Starting</th>
                                            <th>Ending</th>
                                            <th>Config</th>
                                        </tr>
                                        <c:forEach var="ph" items="${phase}">
                                        <tr>	
                                            <td>${ph.phase}</td>
                                            <td>${ph.starting}</td>
                                            <td>${ph.ending}</td>
                                            <td style="width: 35%"> <input style="width: 100%" type="number" name="duration${ph.phase}" min="1" max="100" placeholder="Enter the duration for improvment"></td>
                                            
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                                <input type="submit" class="btn btn-success pull-right" value="Submit Duration">
                            </form>
                        </div>
                    </div>        
                    <br>
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
        <!-- jQuery 2.2.0 -->
<script src="plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- Bootstrap Calendar -->
<script src="bootstrap/js/bootstrap-year-calendar.js"></script>


<!-- Bootstrap Calendar -->
<script src="bootstrap/js/bootstrap-year-calendar.min.js"></script>

<!-- jQuery UI 1.11.4 -->
<script src="plugins/jQueryUI/jquery-ui.min.js"></script>
<!-- Slimscroll -->
<script src="plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<!-- fullCalendar 2.2.5 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
 <script src="plugins/select2/select2.full.min.js"></script>
<script src="plugins/fullcalendar/fullcalendar.min.js"></script>
     <script src="plugins/input-mask/jquery.inputmask.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
        <script src="plugins/input-mask/jquery.inputmask.extensions.js"></script>
        <script src="plugins/daterangepicker/moment.min.js"></script>
        <script src="plugins/daterangepicker/daterangepicker.js"></script>
        <script src="plugins/datepicker/bootstrap-datepicker.js"></script>
        <script src="plugins/poptest/popoverText.js"></script>
        <script>
            $(document).ready(function () {
                $('#popCalendar').popover(popCalendar);
                $('#popDates').popover(popDates);
            });


        </script>
        <script>
            $(function () {
              //Datemask dd/mm/yyyy
                //Datemask2 mm/dd/yyyy
                $('.datepicker').datepicker({
                    autoclose: true
                });

                $('.reservation').daterangepicker();
              

            });
        </script>
<!--        <script>
            $(function () {

   var currentDate= "${test}";
//     $("#datepicker").datepicker("setDate", currentDate);
     $("#date1").datepicker("setDate", currentDate);

       $('#date1').datepicker({
                    autoclose: true
                });
            });
        </script>-->
        
<!-- Page specific script -->
 
<script>
         $(function() {
    var currentYear = new Date().getFullYear();
    $.ajax({
        url:"viewCalendar",
        type:"POST",
        dataType:"JSON",
        success: function(data){
            $('#calendar').calendar({ 
                
        enableContextMenu: true,
        enableRangeSelection: true,
        mouseOnDay: function(e) {
            if(e.events.length > 0) {
                var content = '';
                
                for(var i in e.events) {
                    content += '<div class="event-tooltip-content">'
                                    + '<div class="event-name" style="color:' + e.events[i].color + '">' + e.events[i].name + '</div>'
                                + '</div>';
                }
            
                $(e.element).popover({ 
                    trigger: 'manual',
                    container: 'body',
                    html:true,
                    content: content
                });
                
                $(e.element).popover('show');
            }
        },
        mouseOutDay: function(e) {
            if(e.events.length > 0) {
                $(e.element).popover('hide');
            }
        },
        dayContextMenu: function(e) {
            $(e.element).popover('hide');
        },
        dataSource: data
    });
        }
    });
    
});
</script>

        <script>
  $(function () {
   
    /* initialize the external events
     -----------------------------------------------------------------*/
    function ini_events(ele) {
      ele.each(function () {

        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
        // it doesn't need to have a start or end
        var eventObject = {
          title: $.trim($(this).text()) // use the element's text as the event title
        };

        // store the Event Object in the DOM element so we can get to it later
        $(this).data('eventObject', eventObject);

        

      });
    }

    ini_events($('#external-events div.external-event'));

  });
</script>
    </body>
</html>
