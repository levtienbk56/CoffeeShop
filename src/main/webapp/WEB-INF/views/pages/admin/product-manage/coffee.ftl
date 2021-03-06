<#ftl encoding='UTF-8'>
<#include "../layout_table.ftl">

<#macro title>
	<title><@spring.message "title.manageCoffee"/></title>
</#macro>

<#macro page_content>
	<div id="page-wrapper">
	    <div class="row">
	        <div class="col-lg-12">
	            <h1 class="page-header"><@spring.message "label.cfManage"/></h1>
	        </div>
	        <!-- /.col-lg-12 -->
	    </div>
	    
	    <!-- /.row -->
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    <@spring.message "label.cfTable"/>
	                </div>
	                <!-- /.panel-heading -->
	                <div class="panel-body">
	                    <div class="dataTable_wrapper">
	                        <table class="table table-striped table-bordered table-hover tbl-responsive">
	                            <thead>
	                                <tr>
	                                    <th class="col-sm-2 col-md-2 text-center">#</th>
	                                    <th class="col-sm-2 col-md-2 text-center"><@spring.message "label.cfName"/></th>
	                                    <th class="col-sm-2 col-md-2 text-center"><@spring.message "label.cfPrice"/></th>
	                                    <th class="col-sm-2 col-md-2 text-center"><@spring.message "label.cfAvailable"/></th>
	                                    <th class="col-sm-2 col-md-2 text-center"></th>
	                                </tr>
	                            </thead>
	                            <tbody>
				                  	<#list model['listCoffee'] as coffee> 
										<tr class="odd gradeX" id="tr-${coffee.id}">
											<td class="text-center td-id">${coffee.id}</td>
		                                    <td class="text-center td-name">${coffee.name}</td>
		                                    <td class="text-center td-price">${coffee.price}</td>
		                                    <td class="text-center td-enabled <#if coffee.available?c = 'true'>info<#else>danger</#if>">${coffee.available?c}</td>
		                                    <td class="text-center" style="padding:4px;">
										        <button value="${coffee.id}" href="#modal-edit"  data-toggle="modal" class="btn btn-sm  btn-default" onClick="editCoffee($(this))" style="margin-right:10px; min-width:90px;">
										        	<i class="fa fa-pencil"></i> <@spring.message "label.global.edit"/>
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
					        	<span class="glyphicon glyphicon-plus"></span> <@spring.message "label.cfNew"/>
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
	          <h3 class="modal-title"><@spring.message "label.cfEdit"/></h3>
	        </div>
	        <div class="modal-body">
	        	<div class="modal-notice"> </div>
	          <table class="table table-striped" id="modal-tbl-edit">
	            <thead id="tblHead">
	              <tr>
	              	<th class="col-md-1 text-center">#</th>
	                <th class="col-md-2 text-center"><@spring.message "label.cfName"/></th>
	                <th class="col-md-2 text-center"><@spring.message "label.cfPrice"/></th>
	                <th class="col-md-2 text-center"><@spring.message "label.cfAvailable"/></th>
	              </tr>
	            </thead>
	            <tbody>
	              <tr>
	              	<td class="text-center col-md-1 td-id"></td>
	                <td class="text-center col-md-2 td-name"><input type="text" class="form-control"></td>
	                <td class="text-center col-md-2 td-price"><input type="number" class="form-control" step="0.01" min="0"></td>
	                <td class="text-center col-md-2 td-enabled">
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
	          <button type="button" class="btn btn-success"  id="update-coffee"><@spring.message "label.global.update"/></button>
	           <button type="button" class="btn btn-default" data-dismiss="modal"><@spring.message "label.global.close"/></button>
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
	          <h3 class="modal-title"><@spring.message "label.cfNew"/></h3>
	        </div>
	        <div class="modal-body">
	        	<div class="modal-notice"> </div>
	          <table class="table table-striped" id="modal-tbl-insert">
	            <thead id="tblHead">
	              <tr>
	              	<th class="col-md-2 text-center"><@spring.message "label.cfName"/></th>
	                <th class="col-md-2 text-center"><@spring.message "label.cfPrice"/></th>
	                <th class="col-md-2 text-center"><@spring.message "label.cfAvailable"/></th>
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
	          <button type="button" class="btn btn-success" id="insert-coffee"><@spring.message "label.global.add"/></button>
	          <button type="button" class="btn btn-default" data-dismiss="modal"><@spring.message "label.global.close"/></button>
	        </div>
					
	      </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->
	<script src="/CoffeeShop/resources/js/model/coffeeModel.js"></script>
	<script src="/CoffeeShop/resources/js/admin/product-manage/coffee.js"></script>
</#macro>


<!-- html code shown here -->
<@display/>