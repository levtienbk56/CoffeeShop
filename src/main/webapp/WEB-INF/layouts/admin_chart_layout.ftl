<#ftl encoding='UTF-8'>

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

    <!-- Custom CSS -->
    <link href="/CoffeeShop/resources/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/CoffeeShop/resources/bower_components/morrisjs/morris.css" rel="stylesheet">

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

    <!-- Flot Charts JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/flot/excanvas.min.js"></script>
    <script src="/CoffeeShop/resources/bower_components/flot/jquery.flot.js"></script>
    <script src="/CoffeeShop/resources/bower_components/flot/jquery.flot.pie.js"></script>
    <script src="/CoffeeShop/resources/bower_components/flot/jquery.flot.stack.js"></script>
    <script src="/CoffeeShop/resources/bower_components/flot/jquery.flot.resize.js"></script>
    <script src="/CoffeeShop/resources/bower_components/flot/jquery.flot.time.js"></script>
    <script src="/CoffeeShop/resources/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
    
    <!-- Custom Theme JavaScript -->
    <script src="/CoffeeShop/resources/js/sb-admin-2.js"></script>
    
    <!-- change password -->
	<script type="text/javascript" src="/CoffeeShop/resources/js/shared/changePassword.js" ></script>
	
</body>
</html>

 