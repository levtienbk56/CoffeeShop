<#ftl encoding='UTF-8'>
<#include "../layout_table.ftl">

<#macro title>
	<title> <@spring.message "title.manageUser"/></title>
</#macro>

<#macro page_content>
	<div id="page-wrapper">
	    <div class="row">
	        <div class="col-lg-12">
	            <h1 class="page-header"><@spring.message "label.userManage"/></h1>
	        </div>
	        <!-- /.col-lg-12 -->
	    </div>
	    <!-- /.row -->
	    <div class="row">
	        <div class="col-lg-12">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    <@spring.message "label.userTable"/>
	                </div>
	                <!-- /.panel-heading -->
	                <div class="panel-body">
	                    <div class="dataTable_wrapper">
	                        <table class="table table-striped table-bordered table-hover tbl-responsive">
	                            <thead>
	                                <tr>
	                                    <th class="col-sm-2 col-md-2 text-center"><@spring.message "label.userName"/></th>
	                                    <th class="col-sm-2 col-md-2 text-center"><@spring.message "label.userActive"/></th>
	                                    <th class="col-sm-2 col-md-2 text-center"><@spring.message "label.userRole"/></th>
	                                    <th class="col-sm-2 col-md-2 text-center"></th>
	                                </tr>
	                            </thead>
	                            <tbody>
				                  	<#list model['listUser'] as user> 
										<tr class="odd gradeX" id="tr-${user.username}">
		                                    <td class="td-username text-center">${user.username}</td>
		                                    <td class="td-enabled text-center <#if user.enabled?c = 'true'>info<#else>danger</#if>">${user.enabled?c}</td>
		                                    <td class="center td-role text-center">${user.role}</td>
		                                    <td style="padding:4px;" class=" text-center">
										        <button value="${user.username}" href="#modal-edit"  data-toggle="modal" class="btn btn-default btn-sm " onClick="editUser($(this))"  style="min-width:100px">
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
					        	<span class="glyphicon glyphicon-plus"></span> <@spring.message "label.userNew"/>
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
	          <h3 class="modal-title"><@spring.message "label.userEdit"/></h3>
	        </div>
	        <div class="modal-body">
	        	<div class="modal-notice"></div>
	          <table class="table table-striped" id="modal-tbl-edit">
	            <thead id="tblHead">
	              <tr>
	              	<th class="col-sm-2 col-md-2 text-center"><@spring.message "label.userName"/></th>
                    <th class="col-sm-2 col-md-2 text-center"><@spring.message "label.userActive"/></th>
                    <th class="col-sm-2 col-md-2 text-center"><@spring.message "label.userRole"/></th>
	              </tr>
	            </thead>
	            <tbody>
	              <tr>
	              	<td class="text-center td-username"></td>
	                <td class="text-center td-enabled text-center">
	                	<select class="form-control">
						    <option value="true">Active</option>
						    <option value="false">Deleted</option>
						</select>
	                </td>
	                <td class="text-center td-role">
	                	<select class="form-control">
						    <option value="SELLER">Seller</option>
						    <option value="ADMIN">Admin</option>
						</select>
					</td>
	              </tr>
	            </tbody>
	          </table>
			</div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-success" id="update-user"><@spring.message "label.global.update"/></button>
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
	          <h3 class="modal-title"><@spring.message "label.userNew"/></h3>
	        </div>
	        <div class="modal-body">
	        	<div class="modal-notice"></div>
	          <table class="table table-striped table-bordered table-hover" id="modal-tbl-insert">
	            <thead id="tblHead">
	              <tr>
	              	<th class="col-sm-2 col-md-2 text-center"><@spring.message "label.userName"/></th>
                    <th class="col-sm-2 col-md-2 text-center"><@spring.message "label.userPassword"/></th>
                    <th class="col-sm-2 col-md-2 text-center"><@spring.message "label.userActive"/></th>
                    <th class="col-sm-2 col-md-2 text-center"><@spring.message "label.userRole"/></th>
	              </tr>
	            </thead>
	            <tbody>
	              <tr>
	              	<td class="text-center td-username"><input type="text" class="form-control"></td>
	                <td class="text-center td-password"><input type="password" class="form-control"></td>
	                <td class="text-center td-enabled text-center">
	                	<select class="form-control">
						    <option value="true">Active</option>
						    <option value="false">Deleted</option>
						</select>
	                </td>
	                <td class="text-center td-role">
	                	<select class="form-control">
						    <option value="SELLER">Seller</option>
						    <option value="ADMIN">Admin</option>
						</select>
	                </td>
	              </tr>
	            </tbody>
	          </table>
			</div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-success"  id="insert-user"><@spring.message "label.global.add"/></button>
	          <button type="button" class="btn btn-default" data-dismiss="modal"><@spring.message "label.global.close"/></button>
	        </div>
	      </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->
	
	<script src="/CoffeeShop/resources/js/model/userModel.js"></script>
	<script src="/CoffeeShop/resources/js/admin/user-manage/user.js"></script>
</#macro>


<!-- html code shown here -->
<@display/>