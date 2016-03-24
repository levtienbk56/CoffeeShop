<#ftl encoding='UTF-8'>
<#include "../layout_chart.ftl">

<#macro title>
	<title><@spring.message "title.manageOrderChart"/></title>
</#macro>

<#macro page_content>
	<div id="wrapper">
		<div id="page-wrapper">
	            <div class="row">
	                <div class="col-lg-4 text-center">
	    	            <div class="page-header">
		                		<label for="sel1"><@spring.message "label.chartSelectYear"/></label>
		                    	<select class="form-control" id="select_year"></select>
					      		<label for="sel1"><@spring.message "label.chartSelectMonth"/></label>
						      	<select class="form-control col-lg-3" id="select_month"></select>
						      	<div><h1></h1></div>
				      	</div>
		                <!-- /.page-header -->
	                </div>
	            	<!-- /.col-lg-12 -->
	            </div>
	            <!-- /.row -->
	            <div class="row">
	                <!-- /.col-lg-6 -->
	                <div class="col-lg-12">
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                           	<@spring.message "label.chartTable1"/>
	                        </div>
	                        <!-- /.panel-heading -->
	                        <div class="panel-body">
	                            <div class="flot-chart">
	                                <div class="flot-chart-content" id="flot-bar-chart"></div>
	                            </div>
	                        </div>
	                        <!-- /.panel-body -->
	                    </div>
	                    <!-- /.panel -->
	                </div>
	                
	                <!-- /.col-lg-6 -->
	                <div class="col-lg-12">
	                    <div class="panel panel-default">
	                        <div class="panel-heading">
	                            <@spring.message "label.chartTable2"/>
	                        </div>
	                        <!-- /.panel-heading -->
	                        <div class="panel-body">
	                            <div class="flot-chart">
	                                <div class="flot-chart-content" id="flot-stack-bar-chart"></div>
	                            </div>
	                        </div>
	                        <!-- /.panel-body -->
	                    </div>
	                    <!-- /.panel -->
	                </div>
	                
	            </div>
	            <!-- /.row -->
	        </div>
	        <!-- /#page-wrapper -->
	
	    </div>
	    <!-- /#wrapper -->
	    
	<!-- custom script -->
	<script src="/CoffeeShop/resources/js/admin/order-manage/analysis.js"></script>
</#macro>

<!-- html code shown here -->
<@display/>