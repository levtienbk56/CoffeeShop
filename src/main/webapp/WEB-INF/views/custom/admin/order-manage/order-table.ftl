<div id="page-wrapper">
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Order Table
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-bordered table-hover table-use-javascript">
                            <thead>
                                <tr>
                                    <th class="col-sm-2 col-md-2 text-center">Id</th>
                                    <th class="col-sm-2 col-md-2 text-center">Username</th>
                                    <th class="col-sm-2 col-md-2 text-center">Purchase Time</th>
                                    <th class="col-sm-2 col-md-2 text-center">Total</th>
                                    <th class="col-sm-2 col-md-2 text-center">Functions</th>
                                </tr>
                            </thead>
                            <tbody>
			                  	<#list model['listOrder'] as order> 
									<tr class="odd gradeX" id="tr-${order.id}">
	                                    <td class="td-id text-center">${order.id}</td>
	                                    <td class="td-username text-center">${(order.username)!"<i>NULL</i>"}</td>
	                                    <td class="td-purchase-time">${order.purchaseTime}</td>
	                                    <td class="center td-total text-center">${order.total}</td>
	                                    <td class="text-center">
									        <button value="${order.id}" href="#modal-edit"  data-toggle="modal" class="btn btn-default" onClick="editOrder($(this))">
									        	<span class="glyphicon glyphicon-edit"></span> Edit
									        </button>
	                                    	<button value="${order.id}" type="button" class="btn btn-danger btn-sm btn-remove-user" onClick="removeOrder($(this))">
									          	<span class="glyphicon glyphicon-remove"></span> Remove 
									        </button>
	                                    </td>
                                	</tr>
								</#list>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                    <div class="well">
                        <button href="#modal-insert"  data-toggle="modal" class="btn btn-success">
				        	<span class="glyphicon glyphicon-plus"></span> New Order
				        </button>
                    </div>
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
</div>
<!-- /#page-wrapper -->