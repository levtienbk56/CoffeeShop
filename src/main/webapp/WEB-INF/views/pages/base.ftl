<#ftl encoding='UTF-8'>
<!-- meta data -->
<#macro meta>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
 </#macro>
<!-- title-->
<#macro title></#macro>

<!-- global css-->
<#macro css_global>
	<!-- Bootstrap Core CSS -->
    <link href="/CoffeeShop/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
</#macro>

<!-- custom css -->
<#macro css_custom></#macro>

<!-- page content-->
<#macro content></#macro>

<!-- global script -->
<#macro script_global>
	<!-- jQuery -->
    <script src="/CoffeeShop/resources/bower_components/jquery/dist/jquery.min.js"></script>
   	<!-- Bootstrap Core JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</#macro>

<!-- custom script-->
<#macro script_custom></#macro>

<!-- navbar as menu in admin page -->
<#macro navbar></#macro>

<#macro display> 
	<!DOCTYPE html>
	<html>
	<head>
		<@meta/>
    	<@title/>
		<@css_global/>    
		<@script_global/>
		<@css_custom/>
	</head>
	<body>
		<@navbar/>
		<@content/>
		<@script_custom/>
	</body>
	</html>
</#macro>