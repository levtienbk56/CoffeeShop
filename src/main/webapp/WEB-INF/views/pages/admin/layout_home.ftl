<#ftl encoding='UTF-8'>
<#include "../layout_base.ftl">

<#macro css_custom>
	<!-- MetisMenu CSS -->
    <link href="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="/CoffeeShop/resources/css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="/CoffeeShop/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- datepicker CSS -->
   	<link rel="stylesheet" href="/CoffeeShop/resources/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />
	<!-- timepicker CSS-->
	<link href="/CoffeeShop/resources/bower_components/jt.timepicker/jquery.timepicker.css" rel="stylesheet" type="text/css">
	<!-- calendar CSS -->
	<link rel='stylesheet' href='/CoffeeShop/resources/bower_components/fullcalendar/dist/fullcalendar.css' />
</#macro>

<#macro script_custom>
	<!-- moment & locale javascript -->
	<script type="text/javascript" src="/CoffeeShop/resources/bower_components/moment/min/moment.min.js"></script>
	<script type="text/javascript" src="/CoffeeShop/resources/bower_components/moment/locale/ja.js"></script>
	<!-- datepicker javascript -->
	<script type="text/javascript" src="/CoffeeShop/resources/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
	<!-- timepicker JavaScript -->
	<script src='/CoffeeShop/resources/bower_components/jt.timepicker/jquery.timepicker.min.js'></script>
	<!-- moment JavaScript -->
	<script src='/CoffeeShop/resources/bower_components/moment/min/moment.min.js'></script>
	<!-- calendar JavaScript -->
	<script src='/CoffeeShop/resources/bower_components/fullcalendar/dist/fullcalendar.js'></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script src="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="/CoffeeShop/resources/js/sb-admin-2.js"></script>
	<!-- Custom page JavaScript -->
	<script src="/CoffeeShop/resources/js/admin/home/event_calendar.js"></script>
</#macro>


<#macro navbar>
	<div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li>
                    <a href="/CoffeeShop/admin/"><i class="fa fa-calendar fa-fw"></i> Dashboard</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-shopping-cart fa-fw"></i> Manage Order<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/CoffeeShop/admin/orders/order-table"><i class="fa fa-table fa-fw"></i> Order Table</a>
                        </li>
                        <li>
                            <a href="/CoffeeShop/admin/orders/analysis"><i class="fa fa-bar-chart fa-fw"></i> Analysis Order</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                
                <li>
                    <a href="#"><i class="fa fa-cubes fa-fw"></i> Manage Item Products<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/CoffeeShop/admin/items/coffees"><i class="fa fa-coffee fa-fw"></i> Coffees</a>
                        </li>
                        <li>
                            <a href="/CoffeeShop/admin/items/condiments"><i class="fa fa-glass fa-fw"></i> Condiment</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                
                <li>
                    <a href="/CoffeeShop/admin/users"><i class="fa fa-user fa-fw"></i> Manage Users</a>
                </li>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
</#macro>
