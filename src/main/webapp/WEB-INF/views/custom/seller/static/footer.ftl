
<#ftl encoding='UTF-8'> 

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<footer>
    <div class="footer" id="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-xs-6">
                    <h3> <a href="https://www.hr-s.co.jp/"> HR-Solution</a> </h3>
                    <ul>
                        <li> <a href="#"> ONWARD Park Building 10F, 3-10-5, Nihonbashi, Chuo-ku, Tokyo </a> </li>
                        <li> <a href="#"> TEL: +81 03-3548-8711 </a> </li>
                        <li> <a href="#"> FAX: +81 03-3548-8864 </a> </li>
                    </ul>
                </div>
                <div class="col-lg-3 col-xs-6">
                    <h3><a href="http://www.ntq-solution.com.vn/"> NTQ-Solution </a> </h3>
                    <ul>
                        <li> <a href="#"> 6th Floor, Sudico Building (HH3), Me Tri Street, Nam Tu Liem District, Hanoi City, Vietnam </a> </li>
                        <li> <a href="#"> Tel: (+84) 4 6297 5613 </a> </li>
                        <li> <a href="#"> son.pham@ntq-solution.com.vn </a> </li>
                    </ul>
                </div>
               
            </div>
            <!--/.row--> 
        </div>
        <!--/.container--> 
    </div>
    <!--/.footer-->
    
    <div class="footer-bottom">
        <div class="container">
            <p class="pull-left"> Copyright © ABC. All right reserved. </p>
            <div class="pull-right">
                <ul class="nav nav-pills payments">
                	<li><a href="" class="fa fa-facebook-square" onClick="changeLocale('en')"></a></li>
                	<li><a href="" class="fa fa-facebook-square" onClick="changeLocale('jp')"></a></li>
                </ul> 
            </div>
        </div>
    </div>
    <!--/.footer-bottom--> 
</footer>

<script>
function changeLocale(lang){
	$.ajax({
		type : "POST",
		url : "locale",
		data : {language: lang},
		timeout : 100000,
		success : function(data) {
			location.reload();
		},
		error : function(e) {
			console.log("ERROR " + e);
		},
		done : function(e) {
			console.log("DONE " + e);
		}
	});
}
</script>