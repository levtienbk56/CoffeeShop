<#ftl encoding='UTF-8'>
<#include "../layout_base.ftl">
<#macro title> 
	<title>Login</title>
</#macro>
<#macro css_custom>
	<link rel="stylesheet" href="/CoffeeShop/resources/css/login.css">
</#macro>

<#macro page_content> 
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
			<!-- enable csrf protection -->
			<input type="hidden" 
				name="${_csrf.parameterName}"
            	value="${_csrf.token}" />
		</form>
	</div>
</#macro>

<!-- code shown here -->
<@display/>