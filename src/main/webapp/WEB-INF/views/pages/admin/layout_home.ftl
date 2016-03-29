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
	<script src='/CoffeeShop/resources/bower_components/fullcalendar/dist/lang-all.js'></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script src="/CoffeeShop/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="/CoffeeShop/resources/js/sb-admin-2.js"></script>
</#macro>


<#macro page_header>
	<#include "../../static/admin_header.ftl">
</#macro>



<#macro page_footer>
	<#include "../../static/footer.ftl">
</#macro>

<#macro navbar>
	<#include "../../static/admin_menu.ftl">
</#macro>
