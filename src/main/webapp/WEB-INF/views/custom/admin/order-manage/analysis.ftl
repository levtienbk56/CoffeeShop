<div id="wrapper">
	<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-4 text-center">
    	            <div class="page-header">
	                		<label for="sel1">Select A Year:</label>
	                    	<select class="form-control" id="select_year"></select>
				      		<label for="sel1">Select A Month:</label>
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
                           	Total Sell In A Month 	
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
                            Number Cup Of Each Coffee Type By Day In A Month
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
