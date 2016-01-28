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
                    DataTables Advanced Tables
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-bordered table-hover" id="dataTables_example">
                            <thead>
                                <tr>
                                    <th class="col-sm-2 col-md-2">Username</th>
                                    <th class="col-sm-2 col-md-2">Password</th>
                                    <th class="col-sm-2 col-md-2">Enabled?</th>
                                    <th class="col-sm-2 col-md-2">Role</th>
                                    <th class="col-sm-2 col-md-2">Functions</th>
                                </tr>
                            </thead>
                            <tbody>
			                  	<#list model['listUser'] as user> 
									<tr class="odd gradeX" id="tr-${user.username}">
	                                    <td>${user.username}</td>
	                                    <td>${user.password}</td>
	                                    <td>${user.enabled?c}</td>
	                                    <td class="center">${user.role}</td>
	                                    <td>
	                                    	<button value="${user.username}" type="button" class="btn btn-default btn-sm btn-edit-user" onClick="editUser($(this))">
									          	<span class="glyphicon glyphicon-edit"></span> Edit
									        </button>
	                                    	<button value="${user.username}" type="button" class="btn btn-danger btn-sm btn-remove-user" onClick="removeUser($(this).attr('value'))">
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
                        <h4>DataTables Usage Information</h4>
                        <p>DataTables is a very flexible, advanced tables plugin for jQuery</p>
                        <a class="btn btn-default btn-lg btn-block" target="_blank" href="https://datatables.net/">View DataTables Documentation</a>
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


<script src="/CoffeeShop/resources/js/user-manage.js"></script>
