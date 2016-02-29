<#ftl encoding='UTF-8'> 

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Dashboard</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div id='calendar'></div>
    </div>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->

<div class="modal fade" id="modal-add-event" role="dialog">
	 <div class="modal-dialog">
		  <!-- Modal content-->
		  <div class="modal-content form-horizontal">
			   <div class="modal-header">
				    <button id="close-updatepw" type="button" class="close" data-dismiss="modal">&times;</button>
				    <h4 class="modal-title">New Event</h4>
			   </div>
			   <!-- /.modal-header -->
			
			   <div class="modal-body">
				    <!-- input name-->
				    <div class="form-group">
					     <label class="col-sm-2 control-label" for="newEname">Name:</label>
					     <div class="col-sm-10">
		                    <input type="text" class="form-control" id="newEname" name="newEname">
	                  	</div>
				    </div>
				    <!-- input START hour&date -->
				    <div class="form-group">
				    	<!-- select hour -->
		              	<label class="col-sm-2 control-label" for="starts">Starts:</label>
                  		<div class="col-sm-5">
	                  		<div class="input-group">
	                      		<input type="text" class="form-control timepicker" id="start-time" data-container="#addNewEvent" data-plugin="datepicker">
	                      		<span class="input-group-addon">
	                        		<i class="fa fa-clock-o" aria-hidden="true"></i>
	                      		</span>
	                    	</div>
	                  	</div>
	                  	<!-- select date -->
                  		<div class="col-sm-5">
	                  		<div class="input-group">
	                      		<input type="text" class="form-control" id="start-date" data-container="#addNewEvent" data-plugin="datepicker">
	                      		<span class="input-group-addon">
	                        		<i class="fa fa-calendar" aria-hidden="true"></i>
	                      		</span>
	                    	</div>
	                  	</div>
	                </div>
	                
	                <!-- input END hour&date -->
				    <div class="form-group">
				    	<!-- select hour -->
		              	<label class="col-sm-2 control-label" for="starts">Ends:</label>
                  		<div class="col-sm-5">
	                  		<div class="input-group">
	                      		<input type="text" class="form-control timepicker" id="end-time" data-container="#addNewEvent" data-plugin="datepicker">
	                      		<span class="input-group-addon">
	                        		<i class="fa fa-clock-o" aria-hidden="true"></i>
	                      		</span>
	                    	</div>
	                  	</div>
	                  	<!-- select date -->
                  		<div class="col-sm-5">
	                  		<div class="input-group">
	                      		<input type="text" class="form-control" id="end-date" data-container="#addNewEvent" data-plugin="datepicker">
	                      		<span class="input-group-addon">
	                        		<i class="fa fa-calendar" aria-hidden="true"></i>
	                      		</span>
	                    	</div>
	                  	</div>
	                </div>
				</div>
				<!-- /.modal-body -->
				
			   	<div class="modal-footer">
				    <button id="update-pass" type="button" class="btn btn-success" data-dismiss="modal">
				     	<i class="fa fa-check"></i>OK
				    </button>
				    <button id="reject" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			   	</div>
			   	<!-- /.modal-footer -->
		  	</div>
		  	<!-- modal-content -->
	 </div>
	 <!-- /.modal-dialog -->
</div>
<!-- /.modal fade-->
  
  
<!-- Custom page JavaScript -->
<script src="/CoffeeShop/resources/js/admin_calendar.js"></script>
  