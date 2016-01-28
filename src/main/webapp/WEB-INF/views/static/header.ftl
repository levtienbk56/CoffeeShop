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
                    	<li>
                    		<a href="#" class="">Welcome <#if (username??)><strong>${username}</strong></#if></a>
                    	</li>
                        <li class=""><a href="logout">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>