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
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	
	<!-- MetisMenu CSS -->
    <link href="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/CoffeeShop/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/CoffeeShop/resources/bower_components/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/CoffeeShop/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>

<body>

    <div id="wrapper">

        <#include "/WEB-INF/views/custom/admin/static/header.ftl">
        
        <#include "/WEB-INF/views/custom/admin/static/slide-menu.ftl">

        <@tiles.insertAttribute name="page-wrapper" />

    </div>
</body>
	<!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
   	<!-- Bootstrap Core JavaScript -->
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
    <!-- Metis Menu Plugin JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/raphael/raphael-min.js"></script>
    <script src="/CoffeeShop/resources/bower_components/morrisjs/morris.min.js"></script>
    <script src="/CoffeeShop/resources/js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/CoffeeShop/resources/dist/js/sb-admin-2.js"></script>
    
</html>

 