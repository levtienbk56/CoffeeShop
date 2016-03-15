<#ftl encoding='UTF-8'>
<#include "../base.ftl">
<#macro title> 
	<title>Login</title>
</#macro>
<#macro css_custom>
	<link rel="stylesheet" href="/CoffeeShop/resources/css/login.css">
</#macro>

<#macro content> 
	<div id="fullscreen_bg" class="fullscreen_bg"> </div>
	<div class="container">
		<form class="form-signin"  action="j_spring_security_check" method='POST'>
			<h1 class="form-signin-heading text-muted">Sign In</h1>
			<input type="text" class="form-control" placeholder="Username" name="username" required="" autofocus="">
			<input type="password" class="form-control" placeholder="Password"  name="password" required="">
				<#if (error??)>
					<div class="error"> ${error} </div>
				</#if>
				<#if (msg??)>
					<div class="msg"> ${msg} </div>
				</#if>
			<button class="btn bn-lg btn-primary btn-block" type="submit">
				Sign In
			</button>
		</form>
	</div>
</#macro>

<!-- code shown here -->
<@display/>