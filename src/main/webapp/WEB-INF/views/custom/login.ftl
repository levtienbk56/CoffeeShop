<#ftl encoding='UTF-8'>

<div id="fullscreen_bg" class="fullscreen_bg"/>

<div class="container">

    <form class="form-signin"  action="j_spring_security_check" method='POST'>
		<h1 class="form-signin-heading text-muted">Sign In</h1>
		<input type="text" class="form-control" placeholder="username" name="username" required="" autofocus="">
		<input type="password" class="form-control" placeholder="Password" name="password" required="">
		
		<button class="btn btn-lg btn-primary btn-block" type="submit">
			Sign In
		</button>
	</form>

</div>



<!--
<script>
	function validateMailForm(){
		var str		= $("#inputmail").val();
		var patt 	= new RegExp(/^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i);
		var res 	= patt.test(str);
		if(!res){
			alert("input is not an email!");
			return false;
		};
		return true;
	}
</script>
-->
