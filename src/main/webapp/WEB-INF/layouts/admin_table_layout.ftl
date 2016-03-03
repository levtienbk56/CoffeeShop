<#ftl encoding='UTF-8'>
<!-- this layout for table format pages
include: user-manage, item-manage -->

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title><@tiles.insertAttribute name="title" /></title>
    
    <!-- Bootstrap Core CSS -->
	<link href="/CoffeeShop/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- MetisMenu CSS -->
    <link href="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    
    <!-- DataTables CSS -->
    <link href="/CoffeeShop/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">
    
    <link href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css" rel="stylesheet">
    <!-- DataTables Responsive CSS -->
    <link href="/CoffeeShop/resources/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
   
   	<!-- datepicker CSS -->
   	<link rel="stylesheet" href="/CoffeeShop/resources/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />
   	
    <!-- Custom CSS -->
    <link href="/CoffeeShop/resources/css/sb-admin-2.css" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="/CoffeeShop/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <!-- jQuery -->
    <script src="/CoffeeShop/resources/bower_components/jquery/dist/jquery.min.js"></script>
    
   	<!-- Bootstrap Core JavaScript -->
  	<script src="/CoffeeShop/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>

<body>

    <div id="wrapper">

        <#include "/WEB-INF/views/custom/admin/static/header.ftl">
        
        <#include "/WEB-INF/views/custom/admin/static/slide-menu.ftl">

        <@tiles.insertAttribute name="page-wrapper" />

    </div>
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
</body>
</html>

 