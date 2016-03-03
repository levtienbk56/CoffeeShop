<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html>
<head>
	<title><@tiles.insertAttribute name="title" /></title>
	<!-- Bootstrap Core CSS -->
    <link href="/CoffeeShop/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- jQuery -->
    <script src="/CoffeeShop/resources/bower_components/jquery/dist/jquery.min.js"></script>
   	<!-- Bootstrap Core JavaScript -->
    <script src="/CoffeeShop/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet"  href="/CoffeeShop/resources/css/header.css">
	<link rel="stylesheet"  href="/CoffeeShop/resources/css/footer.css">
	
  	<meta charset=UTF-8">
	<style>
		div.row{
			margin-top:25px;
			margin-bottom:20px;
		}
	</style>
</head>
<body>
		<@tiles.insertAttribute name="header" />
		<@tiles.insertAttribute name="body" />
		<@tiles.insertAttribute name="footer" />
	
	<script type="text/javascript" src="resources/js/shared/custom.js" ></script>
	<!-- change password -->
	<script type="text/javascript" src="resources/js/shared/changePassword.js" ></script>
  	
</body>
</html>