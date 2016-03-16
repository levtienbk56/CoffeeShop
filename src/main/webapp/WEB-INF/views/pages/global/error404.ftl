<#ftl encoding='UTF-8'>
<#include "../layout_base.ftl">

<#macro title> 
	<title>Error 404</title>
</#macro>
<#macro css_custom>
	<style>
	.mcenter{
		margin: auto;
		margin-top: 100px;
	    width: 40%;
	    border: 3px solid #0000FF;
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

<#macro page_content> 
	<div class="text-center mcenter">
		<h4><b>Error: </b>Page is not found!</h4>
		<p><a href="/CoffeeShop/">click to go to home page</a></p>
		<i>(auto redirect in 5 seconds)</i>
	</div>
</#macro>

<!-- code shown here -->
<@display/>
