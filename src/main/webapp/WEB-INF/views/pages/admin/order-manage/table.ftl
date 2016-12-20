<#ftl encoding='UTF-8'>
<#include "../layout_table.ftl">

<#macro title>
	<title><@spring.message "title.manageOrderTable"/></title>
</#macro>

<#macro page_content>
	<div id="page-wrapper">
	    <div class="row">
	    	<!-- search filter -->
	    	<div>
			    <div class='col-lg-4'>
			        <div class="form-group">
			            <div class='input-group date' id='datetimepicker6'>
			                <input type="text" class="form-control"  placeholder="<@spring.message "label.orderFrom"/>" />
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
			        </div>
			    </div>
			    <!-- /.col-lg-4 -->
			    
			    <div class='col-lg-4'>
			        <div class="form-group">
			            <div class='input-group date' id='datetimepicker7'>
			                <input type="text" class="form-control" placeholder="<@spring.message "label.orderTo"/>"/>
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
			        </div>
			    </div>
			    <!-- /.col-lg-4 -->
			    
			    <div class='col-lg-3'>
			        <div class="form-group">
			            <button type="button" class="btn btn-default" id="btn-search-by-range">
					      	<span class="glyphicon glyphicon-search"></span> <@spring.message "label.global.search"/>
					    </button>
			        </div>
			    </div>
			    <!-- /.col-lg-3 -->
			</div>
			<!-- /search filter -->
			
	    	<!-- order table -->
	        <div class="col-lg-12">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    <@spring.message "label.orderTable"/>
	                </div>
		    
	                <!-- /.panel-heading -->
	                <div class="panel-body">
	                    <div class="dataTable_wrapper">
	                        <table id="order-tbl" class="table table-striped table-bordered table-hover tbl-responsive-nosearch-order">
	                            <thead>
	                                <tr>
	                                    <th class="col-md-1 text-center">#</th>
	                                    <th class="col-md-2 text-center"><@spring.message "label.userName"/></th>
	                                    <th class="col-md-2 text-center"><@spring.message "label.orderTime"/></th>
	                                    <th class="col-md-1 text-center"><@spring.message "label.orderTotal"/></th>
	                                </tr>
	                            </thead>
	                        </table>
	                    </div>
	                    <!-- /.table-responsive -->
	                </div>
	                <!-- /.panel-body -->
	            </div>
	            <!-- /.panel -->
	        </div>
	        <!-- /.col-lg-6 -->
	        
	        
	        <!-- cup table -->
	        <div class="col-lg-12">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    <@spring.message "label.orderCups" />
	                </div>
	                <!-- /.panel-heading -->
	                <div class="panel-body">
	                    <div class="dataTable_wrapper">
	                        <table class="table table-striped table-bordered table-hover tbl-responsive-nosearch-cup">
	                            <thead>
	                                <tr>
	                                    <th class="col-md-2 text-center"><@spring.message "label.cfName"/></th>
	                                    <th class="col-md-1 text-center"><@spring.message "label.cfSize"/></th>
	                                    <th class="col-md-2 text-center"><@spring.message "label.cdName"/></th>
	                                    <th class="col-md-1 text-center"><@spring.message "label.cfPrice"/></th>
	                                </tr>
	                            </thead>
	                        </table>
	                    </div>
	                    <!-- /.table-responsive -->
	                </div>
	                <!-- /.panel-body -->
	            </div>
	            <!-- /.panel -->
	        </div>
	        <!-- /.col-lg-6 -->
	        
	    </div>
	</div>
	<!-- /#page-wrapper -->
	
	<!-- script for order-table page -->
	<script type="text/javascript" src="/CoffeeShop/resources/js/admin/order-manage/order-table.js"></script>
</#macro>

<!-- HTML code is shown here -->
<@display/>