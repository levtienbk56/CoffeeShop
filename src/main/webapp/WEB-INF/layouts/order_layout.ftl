<#ftl encoding='UTF-8'>
<html>
<head>
	<title><@tiles.insertAttribute name="title" /></title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/header.css">
	<link rel="stylesheet" href="resources/css/footer.css">
	<link rel="stylesheet" href="resources/css/login.css">
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
	
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
  	<script type="text/javascript" src="resources/js/order.js" ></script>
  	<script type="text/javascript" src="resources/js/custom.js" ></script>
  	
</html>