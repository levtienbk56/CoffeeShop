<#ftl encoding='UTF-8'>

<!DOCTYPE html>
<html lang="en">

<head>
    <title><@tiles.insertAttribute name="title" /></title>
    <!-- Bootstrap Core CSS -->
    <link href="/CoffeeShop/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- jQuery -->
    <script src="/CoffeeShop/resources/bower_components/jquery/dist/jquery.min.js"></script>
   	<!-- Bootstrap Core JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	
	<!-- MetisMenu CSS -->
    <link href="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <link href="/CoffeeShop/resources/css/sb-admin-2.css" rel="stylesheet">
    
    <!-- Custom Fonts -->
    <link href="/CoffeeShop/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	
	<!-- datepicker CSS -->
   	<link rel="stylesheet" href="/CoffeeShop/resources/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />
   	
	<!-- timepicker CSS-->
	<link href="/CoffeeShop/resources/bower_components/jt.timepicker/jquery.timepicker.css" rel="stylesheet" type="text/css">
	
	<!-- calendar CSS -->
	<link rel='stylesheet' href='/CoffeeShop/resources/bower_components/fullcalendar/dist/fullcalendar.css' />
	
	
	 <!-- moment & locale javascript -->
    <script type="text/javascript" src="/CoffeeShop/resources/bower_components/moment/min/moment.min.js"></script>
    <script type="text/javascript" src="/CoffeeShop/resources/bower_components/moment/locale/ja.js"></script>
    
	<!-- datepicker javascript -->
    <script type="text/javascript" src="/CoffeeShop/resources/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
    
	<!-- timepicker JavaScript -->
	<script src='/CoffeeShop/resources/bower_components/jt.timepicker/jquery.timepicker.min.js'></script>
	
	<!-- moment JavaScript -->
	<script src='/CoffeeShop/resources/bower_components/moment/min/moment.min.js'></script>
	
	<!-- calendar JavaScript -->
	<script src='/CoffeeShop/resources/bower_components/fullcalendar/dist/fullcalendar.js'></script>
	
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
	
    <!-- Metis Menu Plugin JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/CoffeeShop/resources/js/sb-admin-2.js"></script>
    
    <!-- change password -->
	<script type="text/javascript" src="/CoffeeShop/resources/js/shared/changePassword.js" ></script>
    
</html>

 