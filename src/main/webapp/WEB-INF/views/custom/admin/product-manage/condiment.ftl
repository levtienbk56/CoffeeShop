<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Condiments Tables</h1>
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
                        <table class="table table-striped table-bordered table-hover tbl-responsive">
                            <thead>
                                <tr>
                                    <th class="col-sm-2 col-md-1 text-center">#</th>
                                    <th class="col-sm-2 col-md-2 text-center">Condiment Name</th>
                                    <th class="col-sm-2 col-md-2 text-center">Price ($)</th>
                                    <th class="col-sm-2 col-md-2 text-center">Available?</th>
                                    <th class="col-sm-2 col-md-2 text-center">Functions</th>
                                </tr>
                            </thead>
                            <tbody>
			                  	<#list model['listCondiment'] as condiment> 
									<tr class="odd gradeX" id="tr-${condiment.id}">
										<td class="text-center td-id">${condiment.id}</td>
	                                    <td class="text-center td-name">${condiment.name}</td>
	                                    <td class="text-center td-price">${condiment.price}</td>
	                                    <td class="text-center td-enabled <#if condiment.enabled?c = 'true'>info<#else>danger</#if>">${condiment.enabled?c}</td>
	                                    <td class="text-center" style="padding:4px;">
									        <button value="${condiment.id}" href="#modal-edit"  data-toggle="modal" class="btn btn-sm btn-default" onClick="editCondiment($(this))"  style="margin-right:10px; min-width:90px;">
									        	<i class="fa fa-pencil"></i> Edit
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
				        	<span class="glyphicon glyphicon-plus"></span> New Condiment
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
          <h3 class="modal-title">Edit Condiment</h3>
        </div>
        <div class="modal-body">
        	<div class="modal-notice">	</div>
          <table class="table table-striped" id="modal-tbl-edit">
            <thead id="tblHead">
              <tr>
              	<th class="text-center col-md-1">#</th>
                <th class="text-center col-md-2">Condiment Name</th>
                <th class="text-center col-md-2">Price($)</th>
                <th class="text-center col-md-2">Active?</th>
              </tr>
            </thead>
            <tbody>
              <tr>
              	<td class="text-center td-id"></td>
                <td class="text-center td-name"><input type="text" class="form-control"></td>
                <td class="text-center td-price"><input type="number" class="form-control" step="0.01" min="0"></td>
                <td class="text-center td-enabled">
                	<select class="form-control">
					    <option value="true">Available</option>
					    <option value="false">Unavailable</option>
					</select>
				</td>
              </tr>
            </tbody>
          </table>
		</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-success" id="update-condiment">Save Changes</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
				
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
  
   <!-- modal insert -->
<div class="modal fade" id="modal-insert">
<div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
          <h3 class="modal-title">New Condiment</h3>
        </div>
        <div class="modal-body">
           <div class="modal-notice">	</div>
          <table class="table table-striped" id="modal-tbl-insert">
            <thead id="tblHead">
              <tr>
              	<th class="text-center col-md-2 ">Condiment Name</th>
                <th class="text-center col-md-2 ">Price($)</th>
                <th class="text-center col-md-2 ">enabled?</th>
              </tr>
            </thead>
            <tbody>
              <tr>
              	<td class="text-center td-name"><input type="text" class="form-control"></td>
                <td class="text-center td-price"><input type="number" min="0" step="0.01" class="form-control"></td>
                <td class="text-center td-enabled">
                	<select class="form-control">
					    <option value="true">Available</option>
					    <option value="false">Unavailable</option>
					</select>
				</td>
              </tr>
            </tbody>
          </table>
		</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-success" id="insert-condiment">Insert</button>
          <button type="button" class="btn btn-default " data-dismiss="modal">Close</button>
        </div>
				
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->

<script src="/CoffeeShop/resources/js/admin/product-manage/condiment.js"></script>

