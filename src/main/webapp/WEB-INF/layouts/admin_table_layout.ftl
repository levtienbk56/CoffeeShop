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
    
    <!-- DataTables Responsive CSS -->
    <link href="/CoffeeShop/resources/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
   
    <!-- Custom CSS -->
    <link href="/CoffeeShop/resources/dist/css/sb-admin-2.css" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="/CoffeeShop/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
</head>

<body>

    <div id="wrapper">

        <#include "/WEB-INF/views/custom/admin/static/header.ftl">
        
        <#include "/WEB-INF/views/custom/admin/static/slide-menu.ftl">

        <@tiles.insertAttribute name="page-wrapper" />

    </div>
    
    
    <!-- jQuery -->
    <script src="/CoffeeShop/resources/bower_components/jquery/dist/jquery.min.js"></script>
    
   	<!-- Bootstrap Core JavaScript -->
  	<script src="/CoffeeShop/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	
    <!-- Metis Menu Plugin JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    
    <!-- DataTables JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="/CoffeeShop/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    
    <!-- Custom Theme JavaScript -->
    <script src="/CoffeeShop/resources/dist/js/sb-admin-2.js"></script>
    
    <script>
    $(document).ready(function() {
        $('.table-use-javascript').DataTable({
                responsive: true
        });
    });
</script>
</body>
	
    
</html>

 