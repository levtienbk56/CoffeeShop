<#ftl encoding='UTF-8'>

<!DOCTYPE html>
<html lang="en">

<head>
    <title><@tiles.insertAttribute name="title" /></title>
    <!-- Bootstrap Core CSS -->
    <link href="/CoffeeShop/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- MetisMenu CSS -->
    <link href="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    
    <!-- DataTables CSS -->
    <link href="/CoffeeShop/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">
    
    <!-- DataTables Responsive CSS -->
    <link href="/CoffeeShop/resources/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
    
    <!-- Timeline CSS -->
    <link href="/CoffeeShop/resources/dist/css/timeline.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <link href="/CoffeeShop/resources/dist/css/sb-admin-2.css" rel="stylesheet">
    
    <!-- Morris Charts CSS -->
    <link href="/CoffeeShop/resources/bower_components/morrisjs/morris.css" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="/CoffeeShop/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	<meta charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
</head>

<body>

    <div id="wrapper">

        <#include "/WEB-INF/views/custom/admin/static/header.ftl">
        
        <#include "/WEB-INF/views/custom/admin/static/slide-menu.ftl">

        <@tiles.insertAttribute name="page-wrapper" />

    </div>
</body>
	<!-- jQuery -->
    <script src="/CoffeeShop/resources/bower_components/jquery/dist/jquery.min.js"></script>
   	<!-- Bootstrap Core JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	
    <!-- Metis Menu Plugin JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <!-- Morris Charts JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/raphael/raphael-min.js"></script>
    <script src="/CoffeeShop/resources/bower_components/morrisjs/morris.min.js"></script>
    <script src="/CoffeeShop/resources/js/morris-data.js"></script>
    
    <!-- DataTables JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
    <script src="/CoffeeShop/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
    
    <!-- Custom Theme JavaScript -->
    <script src="/CoffeeShop/resources/dist/js/sb-admin-2.js"></script>
    
    <!-- change password -->
	<script type="text/javascript" src="resources/js/shared/changePassword.js" ></script>
    
</html>

 