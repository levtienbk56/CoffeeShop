<#ftl encoding='UTF-8'>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}
</style>
<div id="fullscreen_bg" class="fullscreen_bg"/>

<div class="container">
	<form class="form-signin"  action="j_spring_security_check" method='POST'>
		<h1 class="form-signin-heading text-muted">Sign In</h1>
		<input type="text" class="form-control" placeholder="Email address" name="username" required="" autofocus="">
		<input type="password" class="form-control" placeholder="Password"  name="password" required="">
			<#if (error??)>
				<div class="error"> ${error} </div>
			</#if>
			<#if (msg??)>
				<div class="msg"> ${msg} </div>
			</#if>
		<button class="btn btn-lg btn-primary btn-block" type="submit">
			Sign In
		</button>
	</form>

</div>