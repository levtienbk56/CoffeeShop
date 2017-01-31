<#ftl encoding='UTF-8'>
<#include "../layout_base.ftl">

<#macro title> 
	<title>Error 403</title>
</#macro>
<#macro css_custom>
	<style>
	.mcenter{
		margin: auto;
		margin-top: 100px;
	    width: 40%;
	    border: 3px solid #73AD21;
	    padding: 10px;
	}
	</style>
</#macro>
<#macro script_custom>
	<script>
		window.setTimeout(function(){
			location.href = "/CoffeeShop/";
		}, 5000);
	</script>
</#macro>

<#macro page_wrapper> 
	<div class="text-center mcenter">
		<h4><b>Error 403: </b>You are not allowed to access this page!</h4>
		<p><a href="/CoffeeShop/">click to go to home page</a></p>
		<i>(auto redirect in 5 seconds)</i>
	</div>
</#macro>

<!-- code shown here -->
<@display/>