<#ftl encoding='UTF-8'> 

<div class="modal fade" id="confirm-modal" role="dialog">
	 <div class="modal-dialog">
		  <!-- Modal content-->
		  <div class="modal-content">
			   <div class="modal-header">
				    <h4 class="modal-title"></h4>
			   </div>
			   <div class="modal-footer">
				    <button type="button" class="btn btn-success" data-dismiss="modal">
				     	<i class="fa fa-check"></i> Yes
				    </button>
				    <button id="reject" type="button" class="btn btn-default" data-dismiss="modal">No</button>
			   </div>
		  </div>
	 </div>
</div>

<script>
	function showConfirmModal(msg){
		$("#confirm-modal").modal('show');
		$("#confirm-modal .modal-title").text(msg);
	}
	
	function hideConfirmModal(){
		$("#confirm-modal").modal('hide');
	}
</script>