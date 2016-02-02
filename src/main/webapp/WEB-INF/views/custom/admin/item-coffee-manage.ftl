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
                    Coffees Table
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-bordered table-hover table-use-javascript">
                            <thead>
                                <tr>
                                    <th class="col-sm-2 col-md-2">#</th>
                                    <th class="col-sm-2 col-md-2">Coffee Name</th>
                                    <th class="col-sm-2 col-md-2">Price $</th>
                                    <th class="col-sm-2 col-md-2">Functions</th>
                                </tr>
                            </thead>
                            <tbody>
			                  	<#list model['listCoffee'] as coffee> 
									<tr class="odd gradeX" id="tr-${coffee.id}">
										<td class="td-id">${coffee.id}</td>
	                                    <td class="td-name">${coffee.name}</td>
	                                    <td class="td-price">${coffee.price}</td>
	                                    <td>
									        <button value="${coffee.id}" href="#modal-edit"  data-toggle="modal" class="btn btn-default" onClick="editCoffee($(this))">
									        	<span class="glyphicon glyphicon-edit"></span> Edit
									        </button>
	                                    	<button value="${coffee.id}" type="button" class="btn btn-danger btn-sm btn-remove-user" onClick="removeCoffee($(this))">
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
				        	<span class="glyphicon glyphicon-plus"></span> New Coffee
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


<!-- modal edit menu -->
<div class="modal fade" id="modal-edit">
<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
          <h3 class="modal-title">Edit Coffee</h3>
        </div>
        <div class="modal-body">
          <table class="table table-striped" id="modal-tbl-edit">
            <thead id="tblHead">
              <tr>
              	<th>#</th>
                <th>Coffee Name</th>
                <th>Price</th>
              </tr>
            </thead>
            <tbody>
              <tr>
              	<td class="td-id"></td>
                <td class="td-name"><input type="text" class="form-control"></td>
                <td class="td-price text-center"><input type="number" class="form-control" step="0.01" min="0"></td>
              </tr>
            </tbody>
          </table>
		</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary"  data-dismiss="modal" onClick="updateCoffee()">Save Changes</button>
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
          <h3 class="modal-title">New Coffee</h3>
        </div>
        <div class="modal-body">
          <table class="table table-striped" id="modal-tbl-insert">
            <thead id="tblHead">
              <tr>
              	<th>Coffee Name</th>
                <th>Price</th>
              </tr>
            </thead>
            <tbody>
              <tr>
              	<td class="td-name"><input type="text" class="form-control"></td>
                <td class="td-price"><input type="number" min="0" step="0.01" class="form-control"></td>
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

<script src="/CoffeeShop/resources/js/item-coffee-manage.js"></script>

