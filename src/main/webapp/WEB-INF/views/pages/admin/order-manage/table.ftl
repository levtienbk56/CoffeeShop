<#ftl encoding='UTF-8'>
<#include "../layout_table.ftl">

<#macro title>
	<title> Order TableData Page</title>
</#macro>

<#macro page_content>
	<div id="page-wrapper">
	    <!-- /.row -->
	    <div class="row">
	    	<div class="container">
			    <div class='col-lg-3'>
				    <div class="panel-heading">
	                    <strong>From</strong>
	                </div>
			        <div class="form-group">
			            <div class='input-group date' id='datetimepicker6'>
			                <input type='text' class="form-control" />
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
			        </div>
			    </div>
			    <!-- /.col-lg-3 -->
			    
			    <div class='col-lg-3'>
			    	<div class="panel-heading">
	                    <strong>To</strong>
	                </div>
			        <div class="form-group">
			            <div class='input-group date' id='datetimepicker7'>
			                <input type='text' class="form-control" />
			                <span class="input-group-addon">
			                    <span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
			        </div>
			    </div>
			    <!-- /.col-lg-3 -->
			    
			    <div class='col-lg-3'>
			    	<div class="panel-heading">
	                    <strong>&#160</strong>
	                </div>
			        <div class="form-group">
			            <button type="button" class="btn btn-default" id="btn-search-by-range">
					      	<span class="glyphicon glyphicon-search"></span> Search
					    </button>
			        </div>
			    </div>
			    <!-- /.col-lg-3 -->
			</div>
			<!-- /.container -->
			
	    	<!-- order table -->
	        <div class="col-lg-6">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    Orders Table
	                </div>
		    
	                <!-- /.panel-heading -->
	                <div class="panel-body">
	                    <div class="dataTable_wrapper">
	                        <table id="order-tbl" class="table table-striped table-bordered table-hover tbl-responsive-nosearch-order">
	                            <thead>
	                                <tr>
	                                    <th class="col-md-1 text-center">Id</th>
	                                    <th class="col-md-2 text-center">Username</th>
	                                    <th class="col-md-2 text-center">Time</th>
	                                    <th class="col-md-1 text-center">Total</th>
	                                </tr>
	                            </thead>
	                            <tbody>
				                  	
	                            </tbody>
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
	        <div class="col-lg-6">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    Cups in Order
	                </div>
	                <!-- /.panel-heading -->
	                <div class="panel-body">
	                    <div class="dataTable_wrapper">
	                        <table class="table table-striped table-bordered table-hover tbl-responsive-nosearch-cup">
	                            <thead>
	                                <tr>
	                                    <th class="col-md-2 text-center">Coffee</th>
	                                    <th class="col-md-1 text-center">Size</th>
	                                    <th class="col-md-2 text-center">Condiment</th>
	                                    <th class="col-md-1 text-center">Price</th>
	                                </tr>
	                            </thead>
	                            <tbody>
				                  	
	                            </tbody>
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