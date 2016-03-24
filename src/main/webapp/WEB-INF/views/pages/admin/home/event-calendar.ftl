<#ftl encoding='UTF-8'>
<#include "../layout_home.ftl"> 

<#macro title>
	<title><@spring.message "title.adminHome"/></title>
</#macro>

<#macro page_content> 
	<div id="page-wrapper">
	    <div class="row">
	        <div class="col-lg-12">
	            <h1 class="page-header"><@spring.message "label.eventCalendar"/></h1>
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
	<div class="modal fade" id="modal-insert" role="dialog">
		 <div class="modal-dialog">
			  <!-- Modal content-->
			  <div class="modal-content form-horizontal">
				   <div class="modal-header">
					    <button id="close-insertE" type="button" class="close" data-dismiss="modal">&times;</button>
					    <h4 class="modal-title"><@spring.message "label.newEvent"/></h4>
				   </div>
				   <!-- /.modal-header -->
				
				   <div class="modal-body">
				   		<!-- notification -->
				   		<div class="modal-notice">
					    </div>
					    
					    <!-- input name-->
					    <div class="form-group">
						     <label class="col-sm-2 control-label" for="newEname"><@spring.message "label.eventTitle"/></label>
						     <div class="col-sm-10">
			                    <input type="text" class="form-control" id="title-add" name="newEname">
		                  	</div>
					    </div>
					    <!-- input START hour&date -->
					    <div class="form-group">
					    	<!-- select hour -->
			              	<label class="col-sm-2 control-label" for="starts"><@spring.message "label.eventStart"/></label>
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
			              	<label class="col-sm-2 control-label" for="starts"><@spring.message "label.eventEnd"/></label>
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
			              	<label class="col-sm-2 control-label" for="starts"><@spring.message "label.eventColor"/></label>    
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
					    <button id="btn-insert-event" type="button" class="btn btn-success">
					     	<i class="fa fa-check"></i><@spring.message "label.global.add"/>
					    </button>
					    <button id="reject" type="button" class="btn btn-default" data-dismiss="modal"><@spring.message "label.global.close"/></button>
				   	</div>
				   	<!-- /.modal-footer -->
			  	</div>
			  	<!-- modal-content -->
		 </div>
		 <!-- /.modal-dialog -->
	</div>
	<!-- /.modal fade-->
	
	<!-- modal edit-event -->
	<div class="modal fade" id="modal-edit" role="dialog">
		 <div class="modal-dialog">
			  <!-- Modal content-->
			  <div class="modal-content form-horizontal">
				   <div class="modal-header">
					    <button id="close-insertE" type="button" class="close" data-dismiss="modal">&times;</button>
					    <h4 class="modal-title"><@spring.message "label.updateEvent"/></h4>
				   </div>
				   <!-- /.modal-header -->
				
				   <div class="modal-body">
				   		<!-- notification -->
				   		<div class="modal-notice">
					    </div>
					    
					    <!-- input name-->
					    <div class="form-group">
						     <label class="col-sm-2 control-label" for="newEname"><@spring.message "label.eventTitle"/></label>
						     <div class="col-sm-10">
						     	<input type="text" class="form-control" id="id-edit" name="newEname" style="display: none;">
			                    <input type="text" class="form-control" id="title-edit" name="newEname">
		                  	</div>
					    </div>
					    <!-- input START hour&date -->
					    <div class="form-group">
					    	<!-- select hour -->
			              	<label class="col-sm-2 control-label" for="starts"><@spring.message "label.eventStart"/></label>
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
			              	<label class="col-sm-2 control-label" for="starts"><@spring.message "label.eventEnd"/></label>
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
			              	<label class="col-sm-2 control-label" for="starts"><@spring.message "label.eventColor"/></label>    
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
					     	<i class="fa fa-trash"></i> <@spring.message "label.global.remove"/>
					    </button>
					    
					    <button id="btn-update-event" type="button" class="btn btn-success">
					     	<i class="fa fa-check"></i> <@spring.message "label.global.update"/>
					    </button>
					    
					    <button id="reject" type="button" class="btn btn-default" data-dismiss="modal"><@spring.message "label.global.close"/></button>
				   	</div>
				   	<!-- /.modal-footer -->
			  	</div>
			  	<!-- modal-content -->
		 </div>
		 <!-- /.modal-dialog -->
	</div>
	<!-- /.modal fade-->
	
	<!-- Custom page JavaScript -->
	<script src="/CoffeeShop/resources/js/admin/home/event_calendar.js"></script>
</#macro>

<!-- html code shown here -->
<@display/>