<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Tables</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Condiments Table
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-bordered table-hover table-use-javascript">
                            <thead>
                                <tr>
                                    <th class="col-sm-2 col-md-2">#</th>
                                    <th class="col-sm-2 col-md-2">Condiment Name</th>
                                    <th class="col-sm-2 col-md-2">Price $</th>
                                    <th class="col-sm-2 col-md-2">Functions</th>
                                </tr>
                            </thead>
                            <tbody>
			                  	<#list model['listCondiment'] as condiment> 
									<tr class="odd gradeX" id="tr-${condiment.id}">
										<td class="td-id">${condiment.id}</td>
	                                    <td class="td-username">${condiment.name}</td>
	                                    <td class="td-password">${condiment.price}</td>
	                                    <td>
									        <button value="${condiment.id}" href="#modal-edit"  data-toggle="modal" class="btn btn-default" onClick="editCoffee($(this))">
									        	<span class="glyphicon glyphicon-edit"></span> Edit
									        </button>
	                                    	<button value="${condiment.id}" type="button" class="btn btn-danger btn-sm btn-remove-user" onClick="removeCoffee($(this))">
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
				        	<span class="glyphicon glyphicon-plus"></span> New User
				        </button>
                    </div>
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->
