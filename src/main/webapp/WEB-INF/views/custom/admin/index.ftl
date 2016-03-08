<#ftl encoding='UTF-8'> 
<style>

</style>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Event Calendar</h1>
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

<!-- modal add-event -->
<div class="modal fade" id="modal-add-event" role="dialog">
	 <div class="modal-dialog">
		  <!-- Modal content-->
		  <div class="modal-content form-horizontal">
			   <div class="modal-header">
				    <button id="close-insertE" type="button" class="close" data-dismiss="modal">&times;</button>
				    <h4 class="modal-title">New Event</h4>
			   </div>
			   <!-- /.modal-header -->
			
			   <div class="modal-body">
			   		<!-- notification -->
			   		<div class="modal-notice">
				    </div>
				    
				    <!-- input name-->
				    <div class="form-group">
					     <label class="col-sm-2 control-label" for="newEname">Title:</label>
					     <div class="col-sm-10">
		                    <input type="text" class="form-control" id="title-add" name="newEname">
	                  	</div>
				    </div>
				    <!-- input START hour&date -->
				    <div class="form-group">
				    	<!-- select hour -->
		              	<label class="col-sm-2 control-label" for="starts">Starts:</label>
                  		<div class="col-sm-5">
	                  		<div class="input-group">
	                      		<input type="text" class="form-control timepicker start-time" id="start-time-add" data-container="#addNewEvent" data-plugin="datepicker">
	                      		<span class="input-group-addon">
	                        		<i class="fa fa-clock-o" aria-hidden="true"></i>
	                      		</span>
	                    	</div>
	                  	</div>
	                  	<!-- select date -->
                  		<div class="col-sm-5">
	                  		<div class="input-group">
	                      		<input type="text" class="form-control start-date" id="start-date-add" data-container="#addNewEvent" data-plugin="datepicker">
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
	                      		<input type="text" class="form-control timepicker end-time" id="end-time-add" data-container="#addNewEvent" data-plugin="datepicker">
	                      		<span class="input-group-addon">
	                        		<i class="fa fa-clock-o" aria-hidden="true"></i>
	                      		</span>
	                    	</div>
	                  	</div>
	                  	<!-- select date -->
                  		<div class="col-sm-5">
	                  		<div class="input-group">
	                      		<input type="text" class="form-control end-date" id="end-date-add" data-container="#addNewEvent" data-plugin="datepicker">
	                      		<span class="input-group-addon">
	                        		<i class="fa fa-calendar" aria-hidden="true"></i>
	                      		</span>
	                    	</div>
	                  	</div>
	                </div>
		            <!-- input COLOR -->
				    <div class="form-group">
		              	<label class="col-sm-2 control-label" for="starts">color:</label>    
		                <div class="col-sm-10">
		                    <select class="form-control" id='colors-add'>
							    <option value="Red">Red</option>
							    <option value="Orange">Orange</option>
							    <option value="Yellow">Yellow</option>
							    <option value="Green">Green</option>
							    <option value="Blue">Blue</option>
							    <option value="Purple">Purple </option>
							</select>
	                  	</div>
	              	</div>
				</div>
				<!-- /.modal-body -->
				
			   	<div class="modal-footer">
				    <button id="btn-add-event" type="button" class="btn btn-success" data-dismiss="modal">
				     	<i class="fa fa-check"></i>Add
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

<!-- modal edit-event -->
<div class="modal fade" id="modal-edit-event" role="dialog">
	 <div class="modal-dialog">
		  <!-- Modal content-->
		  <div class="modal-content form-horizontal">
			   <div class="modal-header">
				    <button id="close-insertE" type="button" class="close" data-dismiss="modal">&times;</button>
				    <h4 class="modal-title">Update Event</h4>
			   </div>
			   <!-- /.modal-header -->
			
			   <div class="modal-body">
			   		<!-- notification -->
			   		<div class="modal-notice">
				    </div>
				    
				    <!-- input name-->
				    <div class="form-group">
					     <label class="col-sm-2 control-label" for="newEname">Title:</label>
					     <div class="col-sm-10">
					     	<input type="text" class="form-control" id="id-edit" name="newEname" style="display: none;">
		                    <input type="text" class="form-control" id="title-edit" name="newEname">
	                  	</div>
				    </div>
				    <!-- input START hour&date -->
				    <div class="form-group">
				    	<!-- select hour -->
		              	<label class="col-sm-2 control-label" for="starts">Starts:</label>
                  		<div class="col-sm-5">
	                  		<div class="input-group">
	                      		<input type="text" class="form-control timepicker" id="start-time-edit" data-container="#editEvent" data-plugin="datepicker">
	                      		<span class="input-group-addon">
	                        		<i class="fa fa-clock-o" aria-hidden="true"></i>
	                      		</span>
	                    	</div>
	                  	</div>
	                  	<!-- select date -->
                  		<div class="col-sm-5">
	                  		<div class="input-group">
	                      		<input type="text" class="form-control start-date" id="start-date-edit" data-container="#editEvent" data-plugin="datepicker">
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
	                      		<input type="text" class="form-control timepicker" id="end-time-edit" data-container="#editEvent" data-plugin="datepicker">
	                      		<span class="input-group-addon">
	                        		<i class="fa fa-clock-o" aria-hidden="true"></i>
	                      		</span>
	                    	</div>
	                  	</div>
	                  	<!-- select date -->
                  		<div class="col-sm-5">
	                  		<div class="input-group">
	                      		<input type="text" class="form-control end-date" id="end-date-edit" data-container="#editEvent" data-plugin="datepicker">
	                      		<span class="input-group-addon">
	                        		<i class="fa fa-calendar" aria-hidden="true"></i>
	                      		</span>
	                    	</div>
	                  	</div>
	                </div>
		            <!-- input COLOR -->
				    <div class="form-group">
		              	<label class="col-sm-2 control-label" for="starts">color:</label>    
		                <div class="col-sm-10">
		                    <select class="form-control" id='colors-edit'>
							    <option value="Red">Red</option>
							    <option value="Orange">Orange</option>
							    <option value="Yellow">Yellow</option>
							    <option value="Green">Green</option>
							    <option value="Blue">Blue</option>
							    <option value="Purple">Purple </option>
							</select>
	                  	</div>
	              	</div>
				</div>
				<!-- /.modal-body -->
				
			   	<div class="modal-footer">
				    <button id="btn-remove-event" type="button" class="btn btn-danger" data-dismiss="modal">
				     	<i class="fa fa-trash"></i> Remove
				    </button>
				    
				    <button id="btn-update-event" type="button" class="btn btn-success" data-dismiss="modal">
				     	<i class="fa fa-check"></i> Update
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
<script src="/CoffeeShop/resources/js/admin/admin_calendar.js"></script>
  