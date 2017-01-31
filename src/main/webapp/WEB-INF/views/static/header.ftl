<link href="/CoffeeShop/resources/css/header.css" rel="stylesheet">

<!-- csrt for log out-->
<form action="/CoffeeShop/logout" method="POST" id="logoutForm">
  	<input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
	
<div class="navbar-wrapper">
    <nav class="navbar">
        <div class="navbar-header">
            <a class="active navbar-brand" href="#"><strong>Yucky </strong></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">                    
            <ul class="nav navbar-nav pull-right">
            	<li class=" dropdown"><a href="" class="dropdown-toggle active" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Welcome <#if (username??)><strong>${username}</strong></#if><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#updatepw-modal"  data-toggle="modal"><@spring.message "label.global.changePass"/></a></li>
                        <li><a href="javascript:formSubmit()"><@spring.message "label.global.logout"/></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</div>