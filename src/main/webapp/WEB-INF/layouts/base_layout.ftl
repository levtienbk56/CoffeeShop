<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html>
<head>
	<title><@tiles.insertAttribute name="title" /></title>
	<link rel="stylesheet"  href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet"  href="/CoffeeShop/resources/css/header.css">
	<link rel="stylesheet"  href="/CoffeeShop/resources/css/footer.css">
	
  	<meta charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
</head>
<body>
	<div id="wrapper">
		<@tiles.insertAttribute name="page.header" />
		<@tiles.insertAttribute name="page.slideLeftMenu"/>
		<@tiles.insertAttribute name="page.wrapper" />
		<@tiles.insertAttribute name="page.footer" />
	</div>
	
	<@tiles.insertAttribute name="script.vendor"/>
	<@tiles.insertAttribute name="script.common"/>
	
	
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
  	<script type="text/javascript" src="resources/js/order.js" ></script>
  	<script type="text/javascript" src="resources/js/custom.js" ></script>
  	
	<!-- change password -->
	<script type="text/javascript" src="resources/js/shared/changePassword.js" ></script>
  	
</html>