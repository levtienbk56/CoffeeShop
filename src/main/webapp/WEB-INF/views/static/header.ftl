<link href="/CoffeeShop/resources/css/header.css" rel="stylesheet">

<div class="navbar-wrapper" style="margin-bottom:56px">
    <div class="container-fluid">
        <nav class="navbar navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    </button>
                    <a class="active navbar-brand" href="#"><strong>Yucky </strong></a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">                    
                    <ul class="nav navbar-nav pull-right">
                    	<li class=" dropdown"><a href="#" class="dropdown-toggle active" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Welcome <#if (username??)><strong>${username}</strong></#if><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#updatepw-modal"  data-toggle="modal"><@spring.message "label.global.changePass"/></a></li>
                            </ul>
                        </li>
                        <li class=""><a href="logout"><@spring.message "label.global.logout"/></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>