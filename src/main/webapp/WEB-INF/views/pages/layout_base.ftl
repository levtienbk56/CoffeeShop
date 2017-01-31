<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>

<!-- meta data -->
<#macro meta>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<!-- csrf enabled -->
	<meta name="_csrf" content="${_csrf.token}"/>
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
 </#macro>
<!-- title-->
<#macro title>
	<title><@spring.message "label.newOrder"/></title>
</#macro>

<!-- global css-->
<#macro css_global>
	<!-- Bootstrap Core CSS -->
    <link href="/CoffeeShop/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
</#macro>

<!-- custom css -->
<#macro css_custom></#macro>

<#macro html_custom>
	<!-- modal change password-->
	<#include "../static/modal/changePassword.ftl">
	<!-- modal confirm -->
	<#include "../static/modal/confirm.ftl">
	<!-- modal confirm -->
	<#include "../static/modal/alert.ftl">
</#macro>

<!-- page content-->
<#macro page_header></#macro>
<#macro page_content></#macro>
<#macro page_footer></#macro>

<!-- global script -->
<#macro script_global>
	<!-- jQuery -->
    <script src="/CoffeeShop/resources/bower_components/jquery/dist/jquery.min.js"></script>
   	<!-- Bootstrap Core JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- common javascript -->
    <script src="/CoffeeShop/resources/js/global/common.js"></script>
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
		<div class="container">
			<@html_custom/>
			<@page_header/>
			<@page_wrapper/>
			<@page_footer/>
			<@script_custom/>
		<div/>
	</body>
	</html>
</#macro>