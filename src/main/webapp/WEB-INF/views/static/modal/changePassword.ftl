<#ftl encoding='UTF-8'> 
<div class="modal fade" id="updatepw-modal" role="dialog">
	 <div class="modal-dialog">
		  <!-- Modal content-->
		  <div class="modal-content">
			   <div class="modal-header">
				    <button id="close-updatepw" type="button" class="close" data-dismiss="modal">&times;</button>
				    <h4 class="modal-title">Change Password</h4>
			   </div>
			
			   <div id="response-body" class="modal-body">
			    
			    <div id="changePassNotification">
			     <!-- content -->
			    </div>
			    
			    <!-- Password input-->
			    <div class="form-group row">
				     <label class="col-md-4 col-md-offset-1">Current Password</label>
				     <div class="col-md-6">
				      	<input id="curPass" type="password" class="form-control input-md">
				     </div>
			    </div>
			    <hr>
			
			    <!-- Password input-->
			    <div class="form-group row">
				     <label class="col-md-4 col-md-offset-1">New Password</label>
				     <div class="col-md-6">
				      	<input id="newPass" type="password" class="form-control input-md">
				     </div>
			    </div>
			
			    <!-- Password input-->
			    <div class="form-group row">
				     <label class="col-md-4 col-md-offset-1">Repeat New Password</label>
				     <div class="col-md-6">
				      	<input id="repPass"type="password" class="form-control input-md">
				     </div>
			    	</div>
			   </div>
			
			   <div class="modal-footer">
				    <button id="update-pass" type="button" class="btn btn-success" data-dismiss="modal">
				     	<i class="fa fa-check"></i>OK
				    </button>
				    <button id="reject" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			   </div>
		  </div>
	 </div>
</div>

<!-- script -->
<script  type="text/javascript"  src="/CoffeeShop/resources/js/global/changePassword.js"></script>
