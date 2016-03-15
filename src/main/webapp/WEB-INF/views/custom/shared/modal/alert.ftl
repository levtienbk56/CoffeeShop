<#ftl encoding='UTF-8'> 

<div class="modal fade" id="alert-modal" role="dialog">
	 <div class="modal-dialog">
		  <!-- Modal content-->
		  <div class="modal-content">
			   <div class="modal-header">
				    <h4 class="modal-title"></h4>
			   </div>
			   <div class="modal-footer">
				    <button id="reject" type="button" class="btn btn-default" data-dismiss="modal">OK</button>
			   </div>
		  </div>
	 </div>
</div>

<script>
	function showAlertModal(msg){
		$("#alert-modal").modal('show');
		$("#alert-modal .modal-title").text(msg);
	}
	
	function hideAlertModal(){
		$("#alert-modal").modal('hide');
	}
</script>