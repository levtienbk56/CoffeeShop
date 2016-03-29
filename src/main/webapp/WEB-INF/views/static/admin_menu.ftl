<div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li>
                    <a href="/CoffeeShop/admin/"><i class="fa fa-calendar fa-fw"></i> <@spring.message "menu.home"/></a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-shopping-cart fa-fw"></i> <@spring.message "menu.order"/><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/CoffeeShop/admin/orders/order-table"><i class="fa fa-table fa-fw"></i> <@spring.message "menu.orderTable"/></a>
                        </li>
                        <li>
                            <a href="/CoffeeShop/admin/orders/analysis"><i class="fa fa-bar-chart fa-fw"></i> <@spring.message "menu.orderChart"/></a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                
                <li>
                    <a href="#"><i class="fa fa-cubes fa-fw"></i> <@spring.message "menu.item"/><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/CoffeeShop/admin/items/coffees"><i class="fa fa-coffee fa-fw"></i> <@spring.message "menu.itemCf"/></a>
                        </li>
                        <li>
                            <a href="/CoffeeShop/admin/items/condiments"><i class="fa fa-glass fa-fw"></i> <@spring.message "menu.itemCd"/></a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                
                <li>
                    <a href="/CoffeeShop/admin/users"><i class="fa fa-user fa-fw"></i> <@spring.message "menu.user"/></a>
                </li>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>