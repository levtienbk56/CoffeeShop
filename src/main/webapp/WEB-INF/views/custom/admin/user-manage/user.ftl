<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">User Management</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    User Account Table
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-bordered table-hover table-use-javascript">
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
	                                    <td class="td-username">${user.username}</td>
	                                    <td class="td-password">${user.password}</td>
	                                    <td class="td-enabled">${user.enabled?c}</td>
	                                    <td class="center td-role">${user.role}</td>
	                                    <td>
									        <button value="${user.username}" href="#modal-edit"  data-toggle="modal" class="btn btn-default" onClick="editUser($(this))">
									        	<span class="glyphicon glyphicon-edit"></span> Edit
									        </button>
	                                    	<button value="${user.username}" type="button" class="btn btn-danger btn-sm btn-remove-user" onClick="removeUser($(this))">
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
</div>
<!-- /#page-wrapper -->


<!-- modal edit menu -->
<div class="modal fade" id="modal-edit">
<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
          <h3 class="modal-title">Edit User</h3>
        </div>
        <div class="modal-body">
          <table class="table table-striped" id="modal-tbl-edit-user">
            <thead id="tblHead">
              <tr>
              	<th>Username</th>
                <th>Password</th>
                <th>Enabled?</th>
                <th class="text-right">Role</th>
              </tr>
            </thead>
            <tbody>
              <tr>
              	<td class="td-username"></td>
                <td class="td-password"><input type="text" class="form-control"></td>
                <td class="td-enabled text-center"><input type="checkbox"></td>
                <td class="td-role"><input type="text" class="form-control"></td>
              </tr>
            </tbody>
          </table>
		</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary"  data-dismiss="modal" onClick="updateUser()">Save Changes</button>
        </div>
				
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
  
  <!-- modal insert user -->
<div class="modal fade" id="modal-insert">
<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
          <h3 class="modal-title">New User</h3>
        </div>
        <div class="modal-body">
          <table class="table table-striped" id="modal-tbl-insert-user">
            <thead id="tblHead">
              <tr>
              	<th>Username</th>
                <th>Password</th>
                <th>Enabled?</th>
                <th class="text-right">Role</th>
              </tr>
            </thead>
            <tbody>
              <tr>
              	<td class="td-username"><input type="text" class="form-control"></td>
                <td class="td-password"><input type="text" class="form-control"></td>
                <td class="td-enabled text-center"><input type="checkbox" checked></td>
                <td class="td-role"><input type="text" class="form-control"></td>
              </tr>
            </tbody>
          </table>
		</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary"  data-dismiss="modal" onClick="insertUser()">Insert</button>
        </div>
				
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->


<script src="/CoffeeShop/resources/js/user-manage.js"></script>
