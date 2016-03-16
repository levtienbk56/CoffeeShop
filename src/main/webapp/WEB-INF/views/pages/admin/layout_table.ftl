<#ftl encoding='UTF-8'>
<#include "../layout_home.ftl"> <!-- to get navbar -->

<#macro css_custom>
	<!-- MetisMenu CSS -->
    <link href="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    <!-- DataTables CSS -->
    <link href="/CoffeeShop/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">
    <link href="/CoffeeShop/resources/bower_components/datatables/media/css/jquery.dataTables.min.css" rel="stylesheet">
    <!-- DataTables Responsive CSS -->
    <link href="/CoffeeShop/resources/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
   	<!-- datepicker CSS -->
   	<link rel="stylesheet" href="/CoffeeShop/resources/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />
    <!-- Custom CSS -->
    <link href="/CoffeeShop/resources/css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="/CoffeeShop/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</#macro>

<#macro script_custom>
	<!-- Metis Menu Plugin JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <!-- DataTables JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="/CoffeeShop/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    <!-- moment & locale javascript -->
    <script type="text/javascript" src="/CoffeeShop/resources/bower_components/moment/min/moment.min.js"></script>
    <script type="text/javascript" src="/CoffeeShop/resources/bower_components/moment/locale/ja.js"></script>
    <!-- datepicker javascript -->
    <script type="text/javascript" src="/CoffeeShop/resources/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="/CoffeeShop/resources/js/sb-admin-2.js"></script>
    <!-- change password -->
	<script type="text/javascript" src="/CoffeeShop/resources/js/shared/changePassword.js" ></script>
	<!-- set responsive for table-->
	<script> 
		$(document).ready(function() {
			table = $('.tbl-responsive').DataTable({
				responsive : true,
			});
		});
		$(document).ready(function() {
			table01 = $('.tbl-responsive-nosearch-order').DataTable({
				responsive : true,
				searching : false
			});
			table02 = $('.tbl-responsive-nosearch-cup').DataTable({
				responsive : true,
				searching : false
			});
		});
	</script>
</#macro>
